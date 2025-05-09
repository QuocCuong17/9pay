package io.grpc.inprocess;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerStreamTracer;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.inprocess.InProcessTransport;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InUseStateAggregator;
import io.grpc.internal.InsightBuilder;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.NoopClientStream;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.internal.ServerStream;
import io.grpc.internal.ServerStreamListener;
import io.grpc.internal.ServerTransport;
import io.grpc.internal.ServerTransportListener;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.lang.Thread;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class InProcessTransport implements ServerTransport, ConnectionClientTransport {
    private static final Logger log = Logger.getLogger(InProcessTransport.class.getName());
    private final SocketAddress address;
    private final Attributes attributes;
    private final String authority;
    private final int clientMaxInboundMetadataSize;
    private ManagedClientTransport.Listener clientTransportListener;
    private final InUseStateAggregator<InProcessStream> inUseState;
    private final boolean includeCauseWithStatus;
    private final InternalLogId logId;
    private final Optional<ServerListener> optionalServerListener;
    private int serverMaxInboundMetadataSize;
    private ScheduledExecutorService serverScheduler;
    private ObjectPool<ScheduledExecutorService> serverSchedulerPool;
    private Attributes serverStreamAttributes;
    private List<ServerStreamTracer.Factory> serverStreamTracerFactories;
    private ServerTransportListener serverTransportListener;
    private boolean shutdown;
    private Status shutdownStatus;
    private final Set<InProcessStream> streams;
    private boolean terminated;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final String userAgent;

    private InProcessTransport(SocketAddress socketAddress, int i, String str, String str2, Attributes attributes, Optional<ServerListener> optional, boolean z) {
        this.streams = Collections.newSetFromMap(new IdentityHashMap());
        this.uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() { // from class: io.grpc.inprocess.InProcessTransport.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                if (th instanceof Error) {
                    throw new Error(th);
                }
                throw new RuntimeException(th);
            }
        };
        this.inUseState = new InUseStateAggregator<InProcessStream>() { // from class: io.grpc.inprocess.InProcessTransport.2
            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleInUse() {
                InProcessTransport.this.clientTransportListener.transportInUse(true);
            }

            @Override // io.grpc.internal.InUseStateAggregator
            protected void handleNotInUse() {
                InProcessTransport.this.clientTransportListener.transportInUse(false);
            }
        };
        this.address = socketAddress;
        this.clientMaxInboundMetadataSize = i;
        this.authority = str;
        this.userAgent = GrpcUtil.getGrpcUserAgent("inprocess", str2);
        Preconditions.checkNotNull(attributes, "eagAttrs");
        this.attributes = Attributes.newBuilder().set(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY).set(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, attributes).set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, socketAddress).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, socketAddress).build();
        this.optionalServerListener = optional;
        this.logId = InternalLogId.allocate(getClass(), socketAddress.toString());
        this.includeCauseWithStatus = z;
    }

    public InProcessTransport(SocketAddress socketAddress, int i, String str, String str2, Attributes attributes, boolean z) {
        this(socketAddress, i, str, str2, attributes, Optional.absent(), z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InProcessTransport(String str, int i, String str2, String str3, Attributes attributes, ObjectPool<ScheduledExecutorService> objectPool, List<ServerStreamTracer.Factory> list, ServerListener serverListener, boolean z) {
        this(new InProcessSocketAddress(str), i, str2, str3, attributes, Optional.of(serverListener), z);
        this.serverMaxInboundMetadataSize = i;
        this.serverSchedulerPool = objectPool;
        this.serverStreamTracerFactories = list;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    @CheckReturnValue
    public synchronized Runnable start(ManagedClientTransport.Listener listener) {
        this.clientTransportListener = listener;
        if (this.optionalServerListener.isPresent()) {
            this.serverScheduler = this.serverSchedulerPool.getObject();
            this.serverTransportListener = this.optionalServerListener.get().transportCreated(this);
        } else {
            InProcessServer findServer = InProcessServer.findServer(this.address);
            if (findServer != null) {
                this.serverMaxInboundMetadataSize = findServer.getMaxInboundMetadataSize();
                ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool = findServer.getScheduledExecutorServicePool();
                this.serverSchedulerPool = scheduledExecutorServicePool;
                this.serverScheduler = scheduledExecutorServicePool.getObject();
                this.serverStreamTracerFactories = findServer.getStreamTracerFactories();
                this.serverTransportListener = findServer.register(this);
            }
        }
        if (this.serverTransportListener == null) {
            final Status withDescription = Status.UNAVAILABLE.withDescription("Could not find server: " + this.address);
            this.shutdownStatus = withDescription;
            return new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (InProcessTransport.this) {
                        InProcessTransport.this.notifyShutdown(withDescription);
                        InProcessTransport.this.notifyTerminated();
                    }
                }
            };
        }
        return new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (InProcessTransport.this) {
                    Attributes build = Attributes.newBuilder().set(Grpc.TRANSPORT_ATTR_REMOTE_ADDR, InProcessTransport.this.address).set(Grpc.TRANSPORT_ATTR_LOCAL_ADDR, InProcessTransport.this.address).build();
                    InProcessTransport inProcessTransport = InProcessTransport.this;
                    inProcessTransport.serverStreamAttributes = inProcessTransport.serverTransportListener.transportReady(build);
                    InProcessTransport.this.clientTransportListener.transportReady();
                }
            }
        };
    }

    @Override // io.grpc.internal.ClientTransport
    public synchronized ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        int metadataSize;
        StatsTraceContext newClientContext = StatsTraceContext.newClientContext(clientStreamTracerArr, getAttributes(), metadata);
        Status status = this.shutdownStatus;
        if (status != null) {
            return failedClientStream(newClientContext, status);
        }
        metadata.put(GrpcUtil.USER_AGENT_KEY, this.userAgent);
        if (this.serverMaxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = metadataSize(metadata)) > this.serverMaxInboundMetadataSize) {
            return failedClientStream(newClientContext, Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Request metadata larger than %d: %d", Integer.valueOf(this.serverMaxInboundMetadataSize), Integer.valueOf(metadataSize))));
        }
        return new InProcessStream(methodDescriptor, metadata, callOptions, this.authority, newClientContext).clientStream;
    }

    private ClientStream failedClientStream(final StatsTraceContext statsTraceContext, final Status status) {
        return new NoopClientStream() { // from class: io.grpc.inprocess.InProcessTransport.5
            @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
            public void start(ClientStreamListener clientStreamListener) {
                statsTraceContext.clientOutboundHeaders();
                statsTraceContext.streamClosed(status);
                clientStreamListener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            }
        };
    }

    @Override // io.grpc.internal.ClientTransport
    public synchronized void ping(final ClientTransport.PingCallback pingCallback, Executor executor) {
        if (this.terminated) {
            final Status status = this.shutdownStatus;
            executor.execute(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.6
                @Override // java.lang.Runnable
                public void run() {
                    pingCallback.onFailure(status.asRuntimeException());
                }
            });
        } else {
            executor.execute(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport.7
                @Override // java.lang.Runnable
                public void run() {
                    pingCallback.onSuccess(0L);
                }
            });
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public synchronized void shutdown(Status status) {
        if (this.shutdown) {
            return;
        }
        this.shutdownStatus = status;
        notifyShutdown(status);
        if (this.streams.isEmpty()) {
            notifyTerminated();
        }
    }

    @Override // io.grpc.internal.ServerTransport
    public synchronized void shutdown() {
        shutdown(Status.UNAVAILABLE.withDescription("InProcessTransport shutdown by the server-side"));
    }

    @Override // io.grpc.internal.ServerTransport, io.grpc.internal.ManagedClientTransport
    public void shutdownNow(Status status) {
        Preconditions.checkNotNull(status, "reason");
        synchronized (this) {
            shutdown(status);
            if (this.terminated) {
                return;
            }
            Iterator it = new ArrayList(this.streams).iterator();
            while (it.hasNext()) {
                ((InProcessStream) it.next()).clientStream.cancel(status);
            }
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.logId.getId()).add("address", this.address).toString();
    }

    @Override // io.grpc.InternalWithLogId
    public InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.internal.ConnectionClientTransport
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.internal.ServerTransport
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.serverScheduler;
    }

    @Override // io.grpc.InternalInstrumented
    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        create.set(null);
        return create;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyShutdown(Status status) {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        this.clientTransportListener.transportShutdown(status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyTerminated() {
        if (this.terminated) {
            return;
        }
        this.terminated = true;
        ScheduledExecutorService scheduledExecutorService = this.serverScheduler;
        if (scheduledExecutorService != null) {
            this.serverScheduler = this.serverSchedulerPool.returnObject(scheduledExecutorService);
        }
        this.clientTransportListener.transportTerminated();
        ServerTransportListener serverTransportListener = this.serverTransportListener;
        if (serverTransportListener != null) {
            serverTransportListener.transportTerminated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int metadataSize(Metadata metadata) {
        byte[][] serialize = InternalMetadata.serialize(metadata);
        if (serialize == null) {
            return 0;
        }
        long j = 0;
        for (int i = 0; i < serialize.length; i += 2) {
            j += serialize[i].length + 32 + serialize[i + 1].length;
        }
        return (int) Math.min(j, 2147483647L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class InProcessStream {
        private volatile String authority;
        private final CallOptions callOptions;
        private final InProcessClientStream clientStream;
        private final Metadata headers;
        private final MethodDescriptor<?, ?> method;
        private final InProcessServerStream serverStream;

        private InProcessStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, String str, StatsTraceContext statsTraceContext) {
            this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method");
            this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
            this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
            this.authority = str;
            this.clientStream = new InProcessClientStream(callOptions, statsTraceContext);
            this.serverStream = new InProcessServerStream(methodDescriptor, metadata);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void streamClosed() {
            synchronized (InProcessTransport.this) {
                boolean remove = InProcessTransport.this.streams.remove(this);
                if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                    InProcessTransport.this.inUseState.updateObjectInUse(this, false);
                }
                if (InProcessTransport.this.streams.isEmpty() && remove && InProcessTransport.this.shutdown) {
                    InProcessTransport.this.notifyTerminated();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public class InProcessServerStream implements ServerStream {
            private Status clientNotifyStatus;
            private Metadata clientNotifyTrailers;
            private ArrayDeque<StreamListener.MessageProducer> clientReceiveQueue = new ArrayDeque<>();
            private int clientRequested;
            private ClientStreamListener clientStreamListener;
            private boolean closed;
            private int outboundSeqNo;
            final StatsTraceContext statsTraceCtx;
            private final SynchronizationContext syncContext;

            @Override // io.grpc.internal.Stream
            public void flush() {
            }

            @Override // io.grpc.internal.Stream
            public void optimizeForDirectExecutor() {
            }

            @Override // io.grpc.internal.Stream
            public void setCompressor(Compressor compressor) {
            }

            @Override // io.grpc.internal.ServerStream
            public void setDecompressor(Decompressor decompressor) {
            }

            @Override // io.grpc.internal.Stream
            public void setMessageCompression(boolean z) {
            }

            @Override // io.grpc.internal.ServerStream
            public int streamId() {
                return -1;
            }

            InProcessServerStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata) {
                this.syncContext = new SynchronizationContext(InProcessTransport.this.uncaughtExceptionHandler);
                this.statsTraceCtx = StatsTraceContext.newServerContext(InProcessTransport.this.serverStreamTracerFactories, methodDescriptor.getFullMethodName(), metadata);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public synchronized void setListener(ClientStreamListener clientStreamListener) {
                this.clientStreamListener = clientStreamListener;
            }

            @Override // io.grpc.internal.ServerStream
            public void setListener(ServerStreamListener serverStreamListener) {
                InProcessStream.this.clientStream.setListener(serverStreamListener);
            }

            @Override // io.grpc.internal.Stream
            public void request(int i) {
                if (InProcessStream.this.clientStream.serverRequested(i)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    InProcessTransport.InProcessStream.InProcessServerStream.this.m1117x211aea5f();
                                }
                            });
                        }
                    }
                }
                this.syncContext.drain();
            }

            /* renamed from: lambda$request$0$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1117x211aea5f() {
                this.clientStreamListener.onReady();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean clientRequested(int i) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    int i2 = this.clientRequested;
                    boolean z = i2 > 0;
                    this.clientRequested = i2 + i;
                    while (this.clientRequested > 0 && !this.clientReceiveQueue.isEmpty()) {
                        this.clientRequested--;
                        final StreamListener.MessageProducer poll = this.clientReceiveQueue.poll();
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessServerStream.this.m1113x66a40dea(poll);
                            }
                        });
                    }
                    if (this.clientReceiveQueue.isEmpty() && this.clientNotifyStatus != null) {
                        this.closed = true;
                        InProcessStream.this.clientStream.statsTraceCtx.clientInboundTrailers(this.clientNotifyTrailers);
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(this.clientNotifyStatus);
                        final Status status = this.clientNotifyStatus;
                        final Metadata metadata = this.clientNotifyTrailers;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessServerStream.this.m1114xa8bb3b49(status, metadata);
                            }
                        });
                    }
                    boolean z2 = this.clientRequested > 0;
                    this.syncContext.drain();
                    return !z && z2;
                }
            }

            /* renamed from: lambda$clientRequested$1$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1113x66a40dea(StreamListener.MessageProducer messageProducer) {
                this.clientStreamListener.messagesAvailable(messageProducer);
            }

            /* renamed from: lambda$clientRequested$2$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1114xa8bb3b49(Status status, Metadata metadata) {
                this.clientStreamListener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clientCancelled(Status status) {
                internalCancel(status);
            }

            @Override // io.grpc.internal.Stream
            public void writeMessage(InputStream inputStream) {
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                    this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                    InProcessStream.this.clientStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                    InProcessStream.this.clientStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                    this.outboundSeqNo++;
                    final SingleMessageProducer singleMessageProducer = new SingleMessageProducer(inputStream);
                    int i = this.clientRequested;
                    if (i > 0) {
                        this.clientRequested = i - 1;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda6
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessServerStream.this.m1119x4a642b29(singleMessageProducer);
                            }
                        });
                    } else {
                        this.clientReceiveQueue.add(singleMessageProducer);
                    }
                    this.syncContext.drain();
                }
            }

            /* renamed from: lambda$writeMessage$3$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1119x4a642b29(StreamListener.MessageProducer messageProducer) {
                this.clientStreamListener.messagesAvailable(messageProducer);
            }

            @Override // io.grpc.internal.Stream
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.clientRequested > 0;
            }

            @Override // io.grpc.internal.ServerStream
            public void writeHeaders(final Metadata metadata) {
                int metadataSize;
                if (InProcessTransport.this.clientMaxInboundMetadataSize != Integer.MAX_VALUE && (metadataSize = InProcessTransport.metadataSize(metadata)) > InProcessTransport.this.clientMaxInboundMetadataSize) {
                    Status withDescription = Status.CANCELLED.withDescription("Client cancelled the RPC");
                    InProcessStream.this.clientStream.serverClosed(withDescription, withDescription);
                    notifyClientClose(Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Response header metadata larger than %d: %d", Integer.valueOf(InProcessTransport.this.clientMaxInboundMetadataSize), Integer.valueOf(metadataSize))), new Metadata());
                } else {
                    synchronized (this) {
                        if (this.closed) {
                            return;
                        }
                        InProcessStream.this.clientStream.statsTraceCtx.clientInboundHeaders();
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessServerStream.this.m1118xd98ac769(metadata);
                            }
                        });
                        this.syncContext.drain();
                    }
                }
            }

            /* renamed from: lambda$writeHeaders$4$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1118xd98ac769(Metadata metadata) {
                this.clientStreamListener.headersRead(metadata);
            }

            @Override // io.grpc.internal.ServerStream
            public void close(Status status, Metadata metadata) {
                InProcessStream.this.clientStream.serverClosed(Status.OK, status);
                if (InProcessTransport.this.clientMaxInboundMetadataSize != Integer.MAX_VALUE) {
                    int metadataSize = InProcessTransport.metadataSize(metadata) + (status.getDescription() == null ? 0 : status.getDescription().length());
                    if (metadataSize > InProcessTransport.this.clientMaxInboundMetadataSize) {
                        status = Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "Response header metadata larger than %d: %d", Integer.valueOf(InProcessTransport.this.clientMaxInboundMetadataSize), Integer.valueOf(metadataSize)));
                        metadata = new Metadata();
                    }
                }
                notifyClientClose(status, metadata);
            }

            private void notifyClientClose(Status status, final Metadata metadata) {
                final Status cleanStatus = InProcessTransport.cleanStatus(status, InProcessTransport.this.includeCauseWithStatus);
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    if (this.clientReceiveQueue.isEmpty()) {
                        this.closed = true;
                        InProcessStream.this.clientStream.statsTraceCtx.clientInboundTrailers(metadata);
                        InProcessStream.this.clientStream.statsTraceCtx.streamClosed(cleanStatus);
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessServerStream.this.m1116xce68b7c5(cleanStatus, metadata);
                            }
                        });
                    } else {
                        this.clientNotifyStatus = cleanStatus;
                        this.clientNotifyTrailers = metadata;
                    }
                    this.syncContext.drain();
                    InProcessStream.this.streamClosed();
                }
            }

            /* renamed from: lambda$notifyClientClose$5$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1116xce68b7c5(Status status, Metadata metadata) {
                this.clientStreamListener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
            }

            @Override // io.grpc.internal.ServerStream
            public void cancel(Status status) {
                if (internalCancel(Status.CANCELLED.withDescription("server cancelled stream"))) {
                    InProcessStream.this.clientStream.serverClosed(status, status);
                    InProcessStream.this.streamClosed();
                }
            }

            private boolean internalCancel(final Status status) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    this.closed = true;
                    while (true) {
                        StreamListener.MessageProducer poll = this.clientReceiveQueue.poll();
                        if (poll == null) {
                            InProcessStream.this.clientStream.statsTraceCtx.streamClosed(status);
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessServerStream$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    InProcessTransport.InProcessStream.InProcessServerStream.this.m1115x6881a277(status);
                                }
                            });
                            this.syncContext.drain();
                            return true;
                        }
                        while (true) {
                            InputStream next = poll.next();
                            if (next != null) {
                                try {
                                    next.close();
                                } catch (Throwable th) {
                                    InProcessTransport.log.log(Level.WARNING, "Exception closing stream", th);
                                }
                            }
                        }
                    }
                }
            }

            /* renamed from: lambda$internalCancel$6$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessServerStream, reason: not valid java name */
            public /* synthetic */ void m1115x6881a277(Status status) {
                this.clientStreamListener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            }

            @Override // io.grpc.internal.ServerStream
            public Attributes getAttributes() {
                return InProcessTransport.this.serverStreamAttributes;
            }

            @Override // io.grpc.internal.ServerStream
            public String getAuthority() {
                return InProcessStream.this.authority;
            }

            @Override // io.grpc.internal.ServerStream
            public StatsTraceContext statsTraceContext() {
                return this.statsTraceCtx;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public class InProcessClientStream implements ClientStream {
            final CallOptions callOptions;
            private boolean closed;
            private int outboundSeqNo;
            private boolean serverNotifyHalfClose;
            private ArrayDeque<StreamListener.MessageProducer> serverReceiveQueue = new ArrayDeque<>();
            private int serverRequested;
            private ServerStreamListener serverStreamListener;
            final StatsTraceContext statsTraceCtx;
            private final SynchronizationContext syncContext;

            @Override // io.grpc.internal.ClientStream
            public void appendTimeoutInsight(InsightBuilder insightBuilder) {
            }

            @Override // io.grpc.internal.Stream
            public void flush() {
            }

            @Override // io.grpc.internal.Stream
            public void optimizeForDirectExecutor() {
            }

            @Override // io.grpc.internal.Stream
            public void setCompressor(Compressor compressor) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setFullStreamDecompression(boolean z) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setMaxInboundMessageSize(int i) {
            }

            @Override // io.grpc.internal.ClientStream
            public void setMaxOutboundMessageSize(int i) {
            }

            @Override // io.grpc.internal.Stream
            public void setMessageCompression(boolean z) {
            }

            InProcessClientStream(CallOptions callOptions, StatsTraceContext statsTraceContext) {
                this.syncContext = new SynchronizationContext(InProcessTransport.this.uncaughtExceptionHandler);
                this.callOptions = callOptions;
                this.statsTraceCtx = statsTraceContext;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public synchronized void setListener(ServerStreamListener serverStreamListener) {
                this.serverStreamListener = serverStreamListener;
            }

            @Override // io.grpc.internal.Stream
            public void request(int i) {
                if (InProcessStream.this.serverStream.clientRequested(i)) {
                    synchronized (this) {
                        if (!this.closed) {
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    InProcessTransport.InProcessStream.InProcessClientStream.this.m1109x59d828e7();
                                }
                            });
                        }
                    }
                    this.syncContext.drain();
                }
            }

            /* renamed from: lambda$request$0$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            public /* synthetic */ void m1109x59d828e7() {
                this.serverStreamListener.onReady();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean serverRequested(int i) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    int i2 = this.serverRequested;
                    boolean z = i2 > 0;
                    this.serverRequested = i2 + i;
                    while (this.serverRequested > 0 && !this.serverReceiveQueue.isEmpty()) {
                        this.serverRequested--;
                        final StreamListener.MessageProducer poll = this.serverReceiveQueue.poll();
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessClientStream.this.m1110x455a5dea(poll);
                            }
                        });
                    }
                    if (this.serverReceiveQueue.isEmpty() && this.serverNotifyHalfClose) {
                        this.serverNotifyHalfClose = false;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessClientStream.this.m1111x87718b49();
                            }
                        });
                    }
                    boolean z2 = this.serverRequested > 0;
                    this.syncContext.drain();
                    return !z && z2;
                }
            }

            /* renamed from: lambda$serverRequested$1$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            public /* synthetic */ void m1110x455a5dea(StreamListener.MessageProducer messageProducer) {
                this.serverStreamListener.messagesAvailable(messageProducer);
            }

            /* renamed from: lambda$serverRequested$2$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            public /* synthetic */ void m1111x87718b49() {
                this.serverStreamListener.halfClosed();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void serverClosed(Status status, Status status2) {
                internalCancel(status, status2);
            }

            @Override // io.grpc.internal.Stream
            public void writeMessage(InputStream inputStream) {
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    this.statsTraceCtx.outboundMessage(this.outboundSeqNo);
                    this.statsTraceCtx.outboundMessageSent(this.outboundSeqNo, -1L, -1L);
                    InProcessStream.this.serverStream.statsTraceCtx.inboundMessage(this.outboundSeqNo);
                    InProcessStream.this.serverStream.statsTraceCtx.inboundMessageRead(this.outboundSeqNo, -1L, -1L);
                    this.outboundSeqNo++;
                    final SingleMessageProducer singleMessageProducer = new SingleMessageProducer(inputStream);
                    int i = this.serverRequested;
                    if (i > 0) {
                        this.serverRequested = i - 1;
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessClientStream.this.m1112x832169b1(singleMessageProducer);
                            }
                        });
                    } else {
                        this.serverReceiveQueue.add(singleMessageProducer);
                    }
                    this.syncContext.drain();
                }
            }

            /* renamed from: lambda$writeMessage$3$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            public /* synthetic */ void m1112x832169b1(StreamListener.MessageProducer messageProducer) {
                this.serverStreamListener.messagesAvailable(messageProducer);
            }

            @Override // io.grpc.internal.Stream
            public synchronized boolean isReady() {
                if (this.closed) {
                    return false;
                }
                return this.serverRequested > 0;
            }

            @Override // io.grpc.internal.ClientStream
            public void cancel(Status status) {
                Status cleanStatus = InProcessTransport.cleanStatus(status, InProcessTransport.this.includeCauseWithStatus);
                if (internalCancel(cleanStatus, cleanStatus)) {
                    InProcessStream.this.serverStream.clientCancelled(status);
                    InProcessStream.this.streamClosed();
                }
            }

            private boolean internalCancel(final Status status, Status status2) {
                synchronized (this) {
                    if (this.closed) {
                        return false;
                    }
                    this.closed = true;
                    while (true) {
                        StreamListener.MessageProducer poll = this.serverReceiveQueue.poll();
                        if (poll == null) {
                            InProcessStream.this.serverStream.statsTraceCtx.streamClosed(status2);
                            this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    InProcessTransport.InProcessStream.InProcessClientStream.this.m1108x1d108641(status);
                                }
                            });
                            this.syncContext.drain();
                            return true;
                        }
                        while (true) {
                            InputStream next = poll.next();
                            if (next != null) {
                                try {
                                    next.close();
                                } catch (Throwable th) {
                                    InProcessTransport.log.log(Level.WARNING, "Exception closing stream", th);
                                }
                            }
                        }
                    }
                }
            }

            /* renamed from: lambda$internalCancel$4$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            public /* synthetic */ void m1108x1d108641(Status status) {
                this.serverStreamListener.closed(status);
            }

            @Override // io.grpc.internal.ClientStream
            public void halfClose() {
                synchronized (this) {
                    if (this.closed) {
                        return;
                    }
                    if (this.serverReceiveQueue.isEmpty()) {
                        this.syncContext.executeLater(new Runnable() { // from class: io.grpc.inprocess.InProcessTransport$InProcessStream$InProcessClientStream$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                InProcessTransport.InProcessStream.InProcessClientStream.this.m1107x9d711d4c();
                            }
                        });
                    } else {
                        this.serverNotifyHalfClose = true;
                    }
                    this.syncContext.drain();
                }
            }

            /* renamed from: lambda$halfClose$5$io-grpc-inprocess-InProcessTransport$InProcessStream$InProcessClientStream, reason: not valid java name */
            public /* synthetic */ void m1107x9d711d4c() {
                this.serverStreamListener.halfClosed();
            }

            @Override // io.grpc.internal.ClientStream
            public void setAuthority(String str) {
                InProcessStream.this.authority = str;
            }

            @Override // io.grpc.internal.ClientStream
            public void start(ClientStreamListener clientStreamListener) {
                InProcessStream.this.serverStream.setListener(clientStreamListener);
                synchronized (InProcessTransport.this) {
                    this.statsTraceCtx.clientOutboundHeaders();
                    InProcessTransport.this.streams.add(InProcessStream.this);
                    if (GrpcUtil.shouldBeCountedForInUse(this.callOptions)) {
                        InProcessTransport.this.inUseState.updateObjectInUse(InProcessStream.this, true);
                    }
                    InProcessTransport.this.serverTransportListener.streamCreated(InProcessStream.this.serverStream, InProcessStream.this.method.getFullMethodName(), InProcessStream.this.headers);
                }
            }

            @Override // io.grpc.internal.ClientStream
            public Attributes getAttributes() {
                return InProcessTransport.this.attributes;
            }

            @Override // io.grpc.internal.ClientStream
            public void setDeadline(Deadline deadline) {
                InProcessStream.this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
                InProcessStream.this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status cleanStatus(Status status, boolean z) {
        if (status == null) {
            return null;
        }
        Status withDescription = Status.fromCodeValue(status.getCode().value()).withDescription(status.getDescription());
        return z ? withDescription.withCause(status.getCause()) : withDescription;
    }

    /* loaded from: classes5.dex */
    private static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }

        @Override // io.grpc.internal.StreamListener.MessageProducer
        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }
    }
}
