package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.flutter.plugins.firebase.database.Constants;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class RetriableStream<ReqT> implements ClientStream {
    private final Executor callExecutor;
    private Status cancellationStatus;
    private final long channelBufferLimit;
    private final ChannelBufferMeter channelBufferUsed;
    private final Metadata headers;

    @Nullable
    private final HedgingPolicy hedgingPolicy;
    private boolean isClosed;
    private final boolean isHedging;
    private ClientStreamListener masterListener;
    private final MethodDescriptor<ReqT, ?> method;
    private long nextBackoffIntervalNanos;
    private final long perRpcBufferLimit;
    private long perRpcBufferUsed;

    @Nullable
    private final RetryPolicy retryPolicy;
    private Status savedCancellationReason;
    private final ScheduledExecutorService scheduledExecutorService;
    private FutureCanceller scheduledHedging;
    private FutureCanceller scheduledRetry;

    @Nullable
    private final Throttle throttle;
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    private static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    private static Random random = new Random();
    private final Executor listenerSerializeExecutor = new SynchronizationContext(new Thread.UncaughtExceptionHandler() { // from class: io.grpc.internal.RetriableStream.1
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            throw Status.fromThrowable(th).withDescription("Uncaught exception in the SynchronizationContext. Re-thrown.").asRuntimeException();
        }
    });
    private final Object lock = new Object();
    private final InsightBuilder closedSubstreamsInsight = new InsightBuilder();
    private volatile State state = new State(new ArrayList(8), Collections.emptyList(), null, null, false, false, false, 0);
    private final AtomicBoolean noMoreTransparentRetry = new AtomicBoolean();
    private final AtomicInteger localOnlyTransparentRetries = new AtomicInteger();
    private final AtomicInteger inFlightSubStreams = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public interface BufferEntry {
        void runWith(Substream substream);
    }

    abstract ClientStream newSubstream(Metadata metadata, ClientStreamTracer.Factory factory, int i, boolean z);

    abstract void postCommit();

    @CheckReturnValue
    @Nullable
    abstract Status prestart();

    /* JADX INFO: Access modifiers changed from: package-private */
    public RetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, Metadata metadata, ChannelBufferMeter channelBufferMeter, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService, @Nullable RetryPolicy retryPolicy, @Nullable HedgingPolicy hedgingPolicy, @Nullable Throttle throttle) {
        this.method = methodDescriptor;
        this.channelBufferUsed = channelBufferMeter;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService;
        this.headers = metadata;
        this.retryPolicy = retryPolicy;
        if (retryPolicy != null) {
            this.nextBackoffIntervalNanos = retryPolicy.initialBackoffNanos;
        }
        this.hedgingPolicy = hedgingPolicy;
        Preconditions.checkArgument(retryPolicy == null || hedgingPolicy == null, "Should not provide both retryPolicy and hedgingPolicy");
        this.isHedging = hedgingPolicy != null;
        this.throttle = throttle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CheckReturnValue
    @Nullable
    public Runnable commit(final Substream substream) {
        final Future<?> future;
        final Future<?> future2;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> collection = this.state.drainedSubstreams;
            this.state = this.state.committed(substream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledRetry = null;
                future = markCancelled;
            } else {
                future = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                Future<?> markCancelled2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
                future2 = markCancelled2;
            } else {
                future2 = null;
            }
            return new Runnable() { // from class: io.grpc.internal.RetriableStream.1CommitTask
                @Override // java.lang.Runnable
                public void run() {
                    for (Substream substream2 : collection) {
                        if (substream2 != substream) {
                            substream2.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    Future future3 = future;
                    if (future3 != null) {
                        future3.cancel(false);
                    }
                    Future future4 = future2;
                    if (future4 != null) {
                        future4.cancel(false);
                    }
                    RetriableStream.this.postCommit();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitAndRun(Substream substream) {
        Runnable commit = commit(substream);
        if (commit != null) {
            commit.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public Substream createSubstream(int i, boolean z) {
        int i2;
        do {
            i2 = this.inFlightSubStreams.get();
            if (i2 < 0) {
                return null;
            }
        } while (!this.inFlightSubStreams.compareAndSet(i2, i2 + 1));
        Substream substream = new Substream(i);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(substream);
        substream.stream = newSubstream(updateHeaders(this.headers, i), new ClientStreamTracer.Factory() { // from class: io.grpc.internal.RetriableStream.2
            @Override // io.grpc.ClientStreamTracer.Factory
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                return bufferSizeTracer;
            }
        }, i, z);
        return substream;
    }

    final Metadata updateHeaders(Metadata metadata, int i) {
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        return metadata2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
    
        if (r0 == null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
    
        r8.listenerSerializeExecutor.execute(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
    
        r0 = r9.stream;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        if (r8.state.winningSubstream != r9) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        r9 = r8.cancellationStatus;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004c, code lost:
    
        r0.cancel(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004a, code lost:
    
        r9 = io.grpc.internal.RetriableStream.CANCELLED_BECAUSE_COMMITTED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007d, code lost:
    
        r2 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0085, code lost:
    
        if (r2.hasNext() == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0087, code lost:
    
        r4 = (io.grpc.internal.RetriableStream.BufferEntry) r2.next();
        r4.runWith(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0092, code lost:
    
        if ((r4 instanceof io.grpc.internal.RetriableStream.StartEntry) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0094, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0095, code lost:
    
        if (r1 == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0097, code lost:
    
        r4 = r8.state;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009b, code lost:
    
        if (r4.winningSubstream == null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009f, code lost:
    
        if (r4.winningSubstream == r9) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a4, code lost:
    
        if (r4.cancelled == false) goto L72;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void drain(Substream substream) {
        int min;
        Runnable runnable = null;
        boolean z = false;
        ArrayList arrayList = null;
        int i = 0;
        while (true) {
            synchronized (this.lock) {
                State state = this.state;
                if (z) {
                    if (state.winningSubstream == null || state.winningSubstream == substream) {
                        if (state.cancelled) {
                        }
                    }
                }
                if (i == state.buffer.size()) {
                    this.state = state.substreamDrained(substream);
                    if (!isReady()) {
                        return;
                    } else {
                        runnable = new Runnable() { // from class: io.grpc.internal.RetriableStream.3
                            @Override // java.lang.Runnable
                            public void run() {
                                if (RetriableStream.this.isClosed) {
                                    return;
                                }
                                RetriableStream.this.masterListener.onReady();
                            }
                        };
                    }
                } else {
                    if (substream.closed) {
                        return;
                    }
                    min = Math.min(i + 128, state.buffer.size());
                    if (arrayList == null) {
                        arrayList = new ArrayList(state.buffer.subList(i, min));
                    } else {
                        arrayList.clear();
                        arrayList.addAll(state.buffer.subList(i, min));
                    }
                }
            }
            i = min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class StartEntry implements BufferEntry {
        StartEntry() {
        }

        @Override // io.grpc.internal.RetriableStream.BufferEntry
        public void runWith(Substream substream) {
            substream.stream.start(new Sublistener(substream));
        }
    }

    @Override // io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        Throttle throttle;
        this.masterListener = clientStreamListener;
        Status prestart = prestart();
        if (prestart != null) {
            cancel(prestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new StartEntry());
        }
        Substream createSubstream = createSubstream(0, false);
        if (createSubstream == null) {
            return;
        }
        if (this.isHedging) {
            FutureCanceller futureCanceller = null;
            synchronized (this.lock) {
                this.state = this.state.addActiveHedge(createSubstream);
                if (hasPotentialHedging(this.state) && ((throttle = this.throttle) == null || throttle.isAboveThreshold())) {
                    futureCanceller = new FutureCanceller(this.lock);
                    this.scheduledHedging = futureCanceller;
                }
            }
            if (futureCanceller != null) {
                futureCanceller.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
            }
        }
        drain(createSubstream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushbackHedging(@Nullable Integer num) {
        if (num == null) {
            return;
        }
        if (num.intValue() < 0) {
            freezeHedging();
            return;
        }
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            if (futureCanceller == null) {
                return;
            }
            Future<?> markCancelled = futureCanceller.markCancelled();
            FutureCanceller futureCanceller2 = new FutureCanceller(this.lock);
            this.scheduledHedging = futureCanceller2;
            if (markCancelled != null) {
                markCancelled.cancel(false);
            }
            futureCanceller2.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller2), num.intValue(), TimeUnit.MILLISECONDS));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public final class HedgingRunnable implements Runnable {
        final FutureCanceller scheduledHedgingRef;

        HedgingRunnable(FutureCanceller futureCanceller) {
            this.scheduledHedgingRef = futureCanceller;
        }

        @Override // java.lang.Runnable
        public void run() {
            RetriableStream retriableStream = RetriableStream.this;
            final Substream createSubstream = retriableStream.createSubstream(retriableStream.state.hedgingAttemptCount, false);
            if (createSubstream == null) {
                return;
            }
            RetriableStream.this.callExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.HedgingRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    FutureCanceller futureCanceller;
                    boolean z;
                    synchronized (RetriableStream.this.lock) {
                        futureCanceller = null;
                        z = false;
                        if (HedgingRunnable.this.scheduledHedgingRef.isCancelled()) {
                            z = true;
                        } else {
                            RetriableStream.this.state = RetriableStream.this.state.addActiveHedge(createSubstream);
                            if (RetriableStream.this.hasPotentialHedging(RetriableStream.this.state) && (RetriableStream.this.throttle == null || RetriableStream.this.throttle.isAboveThreshold())) {
                                RetriableStream retriableStream2 = RetriableStream.this;
                                futureCanceller = new FutureCanceller(RetriableStream.this.lock);
                                retriableStream2.scheduledHedging = futureCanceller;
                            } else {
                                RetriableStream.this.state = RetriableStream.this.state.freezeHedging();
                                RetriableStream.this.scheduledHedging = null;
                            }
                        }
                    }
                    if (!z) {
                        if (futureCanceller != null) {
                            futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), RetriableStream.this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                        }
                        RetriableStream.this.drain(createSubstream);
                        return;
                    }
                    createSubstream.stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                }
            });
        }
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
        Substream substream = new Substream(0);
        substream.stream = new NoopClientStream();
        Runnable commit = commit(substream);
        if (commit != null) {
            this.savedCancellationReason = status;
            commit.run();
            if (this.inFlightSubStreams.addAndGet(Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                safeCloseMasterListener(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
                return;
            }
            return;
        }
        Substream substream2 = null;
        synchronized (this.lock) {
            if (this.state.drainedSubstreams.contains(this.state.winningSubstream)) {
                substream2 = this.state.winningSubstream;
            } else {
                this.cancellationStatus = status;
            }
            this.state = this.state.cancelled();
        }
        if (substream2 != null) {
            substream2.stream.cancel(status);
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        Iterator<Substream> it = collection.iterator();
        while (it.hasNext()) {
            bufferEntry.runWith(it.next());
        }
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void sendMessage(final ReqT reqt) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.writeMessage(this.method.streamRequest(reqt));
        } else {
            delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1SendMessageEntry
                /* JADX WARN: Multi-variable type inference failed */
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(RetriableStream.this.method.streamRequest(reqt));
                    substream.stream.flush();
                }
            });
        }
    }

    @Override // io.grpc.internal.Stream
    public final void request(final int i) {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1RequestEntry
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.request(i);
                }
            });
        }
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
        State state = this.state;
        if (state.passThrough) {
            state.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1FlushEntry
                @Override // io.grpc.internal.RetriableStream.BufferEntry
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    @Override // io.grpc.internal.Stream
    public final boolean isReady() {
        Iterator<Substream> it = this.state.drainedSubstreams.iterator();
        while (it.hasNext()) {
            if (it.next().stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    @Override // io.grpc.internal.Stream
    public void optimizeForDirectExecutor() {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1OptimizeDirectEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.optimizeForDirectExecutor();
            }
        });
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1CompressorEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setFullStreamDecompression(final boolean z) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1FullStreamDecompressionEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(z);
            }
        });
    }

    @Override // io.grpc.internal.Stream
    public final void setMessageCompression(final boolean z) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1MessageCompressionEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(z);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1HalfCloseEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setAuthority(final String str) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1AuthorityEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setAuthority(str);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1DecompressorRegistryEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1MaxInboundMessageSizeEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(i);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1MaxOutboundMessageSizeEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(i);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() { // from class: io.grpc.internal.RetriableStream.1DeadlineEntry
            @Override // io.grpc.internal.RetriableStream.BufferEntry
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    @Override // io.grpc.internal.ClientStream
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        State state;
        synchronized (this.lock) {
            insightBuilder.appendKeyValue("closed", this.closedSubstreamsInsight);
            state = this.state;
        }
        if (state.winningSubstream != null) {
            InsightBuilder insightBuilder2 = new InsightBuilder();
            state.winningSubstream.stream.appendTimeoutInsight(insightBuilder2);
            insightBuilder.appendKeyValue(Constants.COMMITTED, insightBuilder2);
            return;
        }
        InsightBuilder insightBuilder3 = new InsightBuilder();
        for (Substream substream : state.drainedSubstreams) {
            InsightBuilder insightBuilder4 = new InsightBuilder();
            substream.stream.appendTimeoutInsight(insightBuilder4);
            insightBuilder3.append(insightBuilder4);
        }
        insightBuilder.appendKeyValue("open", insightBuilder3);
    }

    static void setRandom(Random random2) {
        random = random2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasPotentialHedging(State state) {
        return state.winningSubstream == null && state.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state.hedgingFrozen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void freezeHedging() {
        Future<?> future;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            future = null;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
                future = markCancelled;
            }
            this.state = this.state.freezeHedging();
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeCloseMasterListener(final Status status, final ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
        this.listenerSerializeExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.4
            @Override // java.lang.Runnable
            public void run() {
                RetriableStream.this.isClosed = true;
                RetriableStream.this.masterListener.closed(status, rpcProgress, metadata);
            }
        });
    }

    /* loaded from: classes5.dex */
    private final class Sublistener implements ClientStreamListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final Substream substream;

        Sublistener(Substream substream) {
            this.substream = substream;
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void headersRead(final Metadata metadata) {
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RetriableStream.this.masterListener.headersRead(metadata);
                    }
                });
            }
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            FutureCanceller futureCanceller;
            synchronized (RetriableStream.this.lock) {
                RetriableStream retriableStream = RetriableStream.this;
                retriableStream.state = retriableStream.state.substreamClosed(this.substream);
                RetriableStream.this.closedSubstreamsInsight.append(status.getCode());
            }
            if (RetriableStream.this.inFlightSubStreams.decrementAndGet() == Integer.MIN_VALUE) {
                RetriableStream retriableStream2 = RetriableStream.this;
                retriableStream2.safeCloseMasterListener(retriableStream2.savedCancellationReason, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
                return;
            }
            if (this.substream.bufferLimitExceeded) {
                RetriableStream.this.commitAndRun(this.substream);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.safeCloseMasterListener(status, rpcProgress, metadata);
                    return;
                }
                return;
            }
            if (rpcProgress != ClientStreamListener.RpcProgress.MISCARRIED || RetriableStream.this.localOnlyTransparentRetries.incrementAndGet() <= 1000) {
                if (RetriableStream.this.state.winningSubstream == null) {
                    boolean z = false;
                    if (rpcProgress == ClientStreamListener.RpcProgress.MISCARRIED || (rpcProgress == ClientStreamListener.RpcProgress.REFUSED && RetriableStream.this.noMoreTransparentRetry.compareAndSet(false, true))) {
                        final Substream createSubstream = RetriableStream.this.createSubstream(this.substream.previousAttemptCount, true);
                        if (createSubstream == null) {
                            return;
                        }
                        if (RetriableStream.this.isHedging) {
                            synchronized (RetriableStream.this.lock) {
                                RetriableStream retriableStream3 = RetriableStream.this;
                                retriableStream3.state = retriableStream3.state.replaceActiveHedge(this.substream, createSubstream);
                                RetriableStream retriableStream4 = RetriableStream.this;
                                if (!retriableStream4.hasPotentialHedging(retriableStream4.state) && RetriableStream.this.state.activeHedges.size() == 1) {
                                    z = true;
                                }
                            }
                            if (z) {
                                RetriableStream.this.commitAndRun(createSubstream);
                            }
                        } else if (RetriableStream.this.retryPolicy == null || RetriableStream.this.retryPolicy.maxAttempts == 1) {
                            RetriableStream.this.commitAndRun(createSubstream);
                        }
                        RetriableStream.this.callExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.2
                            @Override // java.lang.Runnable
                            public void run() {
                                RetriableStream.this.drain(createSubstream);
                            }
                        });
                        return;
                    }
                    if (rpcProgress == ClientStreamListener.RpcProgress.DROPPED) {
                        if (RetriableStream.this.isHedging) {
                            RetriableStream.this.freezeHedging();
                        }
                    } else {
                        RetriableStream.this.noMoreTransparentRetry.set(true);
                        if (RetriableStream.this.isHedging) {
                            HedgingPlan makeHedgingDecision = makeHedgingDecision(status, metadata);
                            if (makeHedgingDecision.isHedgeable) {
                                RetriableStream.this.pushbackHedging(makeHedgingDecision.hedgingPushbackMillis);
                            }
                            synchronized (RetriableStream.this.lock) {
                                RetriableStream retriableStream5 = RetriableStream.this;
                                retriableStream5.state = retriableStream5.state.removeActiveHedge(this.substream);
                                if (makeHedgingDecision.isHedgeable) {
                                    RetriableStream retriableStream6 = RetriableStream.this;
                                    if (retriableStream6.hasPotentialHedging(retriableStream6.state) || !RetriableStream.this.state.activeHedges.isEmpty()) {
                                        return;
                                    }
                                }
                            }
                        } else {
                            RetryPlan makeRetryDecision = makeRetryDecision(status, metadata);
                            if (makeRetryDecision.shouldRetry) {
                                final Substream createSubstream2 = RetriableStream.this.createSubstream(this.substream.previousAttemptCount + 1, false);
                                if (createSubstream2 == null) {
                                    return;
                                }
                                synchronized (RetriableStream.this.lock) {
                                    RetriableStream retriableStream7 = RetriableStream.this;
                                    futureCanceller = new FutureCanceller(retriableStream7.lock);
                                    retriableStream7.scheduledRetry = futureCanceller;
                                }
                                futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.1RetryBackoffRunnable
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        RetriableStream.this.callExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.1RetryBackoffRunnable.1
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                RetriableStream.this.drain(createSubstream2);
                                            }
                                        });
                                    }
                                }, makeRetryDecision.backoffNanos, TimeUnit.NANOSECONDS));
                                return;
                            }
                        }
                    }
                }
                RetriableStream.this.commitAndRun(this.substream);
                if (RetriableStream.this.state.winningSubstream == this.substream) {
                    RetriableStream.this.safeCloseMasterListener(status, rpcProgress, metadata);
                    return;
                }
                return;
            }
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                RetriableStream.this.safeCloseMasterListener(Status.INTERNAL.withDescription("Too many transparent retries. Might be a bug in gRPC").withCause(status.asRuntimeException()), rpcProgress, metadata);
            }
        }

        private RetryPlan makeRetryDecision(Status status, Metadata metadata) {
            long j = 0;
            boolean z = false;
            if (RetriableStream.this.retryPolicy != null) {
                boolean contains = RetriableStream.this.retryPolicy.retryableStatusCodes.contains(status.getCode());
                Integer pushbackMills = getPushbackMills(metadata);
                boolean z2 = (RetriableStream.this.throttle == null || (!contains && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
                if (RetriableStream.this.retryPolicy.maxAttempts > this.substream.previousAttemptCount + 1 && !z2) {
                    if (pushbackMills == null) {
                        if (contains) {
                            j = (long) (RetriableStream.this.nextBackoffIntervalNanos * RetriableStream.random.nextDouble());
                            RetriableStream.this.nextBackoffIntervalNanos = Math.min((long) (r10.nextBackoffIntervalNanos * RetriableStream.this.retryPolicy.backoffMultiplier), RetriableStream.this.retryPolicy.maxBackoffNanos);
                            z = true;
                        }
                    } else if (pushbackMills.intValue() >= 0) {
                        j = TimeUnit.MILLISECONDS.toNanos(pushbackMills.intValue());
                        RetriableStream retriableStream = RetriableStream.this;
                        retriableStream.nextBackoffIntervalNanos = retriableStream.retryPolicy.initialBackoffNanos;
                        z = true;
                    }
                }
                return new RetryPlan(z, j);
            }
            return new RetryPlan(false, 0L);
        }

        private HedgingPlan makeHedgingDecision(Status status, Metadata metadata) {
            Integer pushbackMills = getPushbackMills(metadata);
            boolean z = !RetriableStream.this.hedgingPolicy.nonFatalStatusCodes.contains(status.getCode());
            return new HedgingPlan((z || ((RetriableStream.this.throttle == null || (z && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold() ^ true)) ? false : true, pushbackMills);
        }

        @Nullable
        private Integer getPushbackMills(Metadata metadata) {
            String str = (String) metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (str == null) {
                return null;
            }
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            State state = RetriableStream.this.state;
            Preconditions.checkState(state.winningSubstream != null, "Headers should be received prior to messages.");
            if (state.winningSubstream != this.substream) {
                return;
            }
            RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.3
                @Override // java.lang.Runnable
                public void run() {
                    RetriableStream.this.masterListener.messagesAvailable(messageProducer);
                }
            });
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            if (RetriableStream.this.isReady()) {
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() { // from class: io.grpc.internal.RetriableStream.Sublistener.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (RetriableStream.this.isClosed) {
                            return;
                        }
                        RetriableStream.this.masterListener.onReady();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class State {
        final Collection<Substream> activeHedges;

        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;

        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> list, Collection<Substream> collection, Collection<Substream> collection2, @Nullable Substream substream, boolean z, boolean z2, boolean z3, int i) {
            this.buffer = list;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.winningSubstream = substream;
            this.activeHedges = collection2;
            this.cancelled = z;
            this.passThrough = z2;
            this.hedgingFrozen = z3;
            this.hedgingAttemptCount = i;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState((z2 && substream == null) ? false : true, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(substream)) || (collection.size() == 0 && substream.closed), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((z && substream == null) ? false : true, "cancelled should imply committed");
        }

        @CheckReturnValue
        State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, true, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State substreamDrained(Substream substream) {
            Collection unmodifiableCollection;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                unmodifiableCollection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                unmodifiableCollection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            Collection collection = unmodifiableCollection;
            Substream substream2 = this.winningSubstream;
            boolean z = substream2 != null;
            List<BufferEntry> list = this.buffer;
            if (z) {
                Preconditions.checkState(substream2 == substream, "Another RPC attempt has already committed");
                list = null;
            }
            return new State(list, collection, this.activeHedges, this.winningSubstream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State substreamClosed(Substream substream) {
            substream.closed = true;
            if (!this.drainedSubstreams.contains(substream)) {
                return this;
            }
            ArrayList arrayList = new ArrayList(this.drainedSubstreams);
            arrayList.remove(substream);
            return new State(this.buffer, Collections.unmodifiableCollection(arrayList), this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State committed(Substream substream) {
            List<BufferEntry> list;
            Collection emptyList;
            boolean z;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            List<BufferEntry> list2 = this.buffer;
            if (this.drainedSubstreams.contains(substream)) {
                list = null;
                z = true;
                emptyList = Collections.singleton(substream);
            } else {
                list = list2;
                emptyList = Collections.emptyList();
                z = false;
            }
            return new State(list, emptyList, this.activeHedges, substream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State freezeHedging() {
            return this.hedgingFrozen ? this : new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State addActiveHedge(Substream substream) {
            Collection unmodifiableCollection;
            Preconditions.checkState(!this.hedgingFrozen, "hedging frozen");
            Preconditions.checkState(this.winningSubstream == null, "already committed");
            if (this.activeHedges == null) {
                unmodifiableCollection = Collections.singleton(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.activeHedges);
                arrayList.add(substream);
                unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            return new State(this.buffer, this.drainedSubstreams, unmodifiableCollection, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount + 1);
        }

        @CheckReturnValue
        State removeActiveHedge(Substream substream) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        @CheckReturnValue
        State replaceActiveHedge(Substream substream, Substream substream2) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            arrayList.add(substream2);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttemptCount;
        ClientStream stream;

        Substream(int i) {
            this.previousAttemptCount = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class BufferSizeTracer extends ClientStreamTracer {
        long bufferNeeded;
        private final Substream substream;

        BufferSizeTracer(Substream substream) {
            this.substream = substream;
        }

        @Override // io.grpc.StreamTracer
        public void outboundWireSize(long j) {
            if (RetriableStream.this.state.winningSubstream != null) {
                return;
            }
            synchronized (RetriableStream.this.lock) {
                if (RetriableStream.this.state.winningSubstream == null && !this.substream.closed) {
                    long j2 = this.bufferNeeded + j;
                    this.bufferNeeded = j2;
                    if (j2 <= RetriableStream.this.perRpcBufferUsed) {
                        return;
                    }
                    if (this.bufferNeeded <= RetriableStream.this.perRpcBufferLimit) {
                        long addAndGet = RetriableStream.this.channelBufferUsed.addAndGet(this.bufferNeeded - RetriableStream.this.perRpcBufferUsed);
                        RetriableStream.this.perRpcBufferUsed = this.bufferNeeded;
                        if (addAndGet > RetriableStream.this.channelBufferLimit) {
                            this.substream.bufferLimitExceeded = true;
                        }
                    } else {
                        this.substream.bufferLimitExceeded = true;
                    }
                    Runnable commit = this.substream.bufferLimitExceeded ? RetriableStream.this.commit(this.substream) : null;
                    if (commit != null) {
                        commit.run();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        long addAndGet(long j) {
            return this.bufferUsed.addAndGet(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Throttle(float f, float f2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (f2 * 1000.0f);
            int i = (int) (f * 1000.0f);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                if (i == 0) {
                    return false;
                }
                i2 = i - 1000;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            return i2 > this.threshold;
        }

        void onSuccess() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                i2 = this.maxTokens;
                if (i == i2) {
                    return;
                }
            } while (!this.tokenCount.compareAndSet(i, Math.min(this.tokenRatio + i, i2)));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Throttle)) {
                return false;
            }
            Throttle throttle = (Throttle) obj;
            return this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        RetryPlan(boolean z, long j) {
            this.shouldRetry = z;
            this.backoffNanos = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class HedgingPlan {

        @Nullable
        final Integer hedgingPushbackMillis;
        final boolean isHedgeable;

        public HedgingPlan(boolean z, @Nullable Integer num) {
            this.isHedgeable = z;
            this.hedgingPushbackMillis = num;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class FutureCanceller {
        boolean cancelled;
        Future<?> future;
        final Object lock;

        FutureCanceller(Object obj) {
            this.lock = obj;
        }

        void setFuture(Future<?> future) {
            synchronized (this.lock) {
                if (!this.cancelled) {
                    this.future = future;
                }
            }
        }

        @CheckForNull
        Future<?> markCancelled() {
            this.cancelled = true;
            return this.future;
        }

        boolean isCancelled() {
            return this.cancelled;
        }
    }
}
