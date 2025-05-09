package io.grpc.okhttp;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.InternalChannelz;
import io.grpc.InternalInstrumented;
import io.grpc.InternalLogId;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerListener;
import io.grpc.okhttp.OkHttpServerTransport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class OkHttpServer implements InternalServer {
    private static final Logger log = Logger.getLogger(OkHttpServer.class.getName());
    private SocketAddress actualListenAddress;
    private final InternalChannelz channelz;
    private InternalInstrumented<InternalChannelz.SocketStats> listenInstrumented;
    private ServerListener listener;
    private final SocketAddress originalListenAddress;
    private ScheduledExecutorService scheduledExecutorService;
    private final ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
    private ServerSocket serverSocket;
    private boolean shutdown;
    private final ServerSocketFactory socketFactory;
    private final OkHttpServerTransport.Config transportConfig;
    private Executor transportExecutor;
    private final ObjectPool<Executor> transportExecutorPool;

    public OkHttpServer(OkHttpServerBuilder okHttpServerBuilder, List<? extends ServerStreamTracer.Factory> list, InternalChannelz internalChannelz) {
        this.originalListenAddress = (SocketAddress) Preconditions.checkNotNull(okHttpServerBuilder.listenAddress, "listenAddress");
        this.socketFactory = (ServerSocketFactory) Preconditions.checkNotNull(okHttpServerBuilder.socketFactory, "socketFactory");
        this.transportExecutorPool = (ObjectPool) Preconditions.checkNotNull(okHttpServerBuilder.transportExecutorPool, "transportExecutorPool");
        this.scheduledExecutorServicePool = (ObjectPool) Preconditions.checkNotNull(okHttpServerBuilder.scheduledExecutorServicePool, "scheduledExecutorServicePool");
        this.transportConfig = new OkHttpServerTransport.Config(okHttpServerBuilder, list);
        this.channelz = (InternalChannelz) Preconditions.checkNotNull(internalChannelz, "channelz");
    }

    @Override // io.grpc.internal.InternalServer
    public void start(ServerListener serverListener) throws IOException {
        this.listener = (ServerListener) Preconditions.checkNotNull(serverListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ServerSocket createServerSocket = this.socketFactory.createServerSocket();
        try {
            createServerSocket.bind(this.originalListenAddress);
            this.serverSocket = createServerSocket;
            this.actualListenAddress = createServerSocket.getLocalSocketAddress();
            this.listenInstrumented = new ListenSocket(createServerSocket);
            this.transportExecutor = this.transportExecutorPool.getObject();
            this.scheduledExecutorService = this.scheduledExecutorServicePool.getObject();
            this.channelz.addListenSocket(this.listenInstrumented);
            this.transportExecutor.execute(new Runnable() { // from class: io.grpc.okhttp.OkHttpServer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OkHttpServer.this.acceptConnections();
                }
            });
        } catch (IOException e) {
            createServerSocket.close();
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptConnections() {
        while (true) {
            try {
                try {
                    OkHttpServerTransport okHttpServerTransport = new OkHttpServerTransport(this.transportConfig, this.serverSocket.accept());
                    okHttpServerTransport.start(this.listener.transportCreated(okHttpServerTransport));
                } catch (IOException e) {
                    if (!this.shutdown) {
                        throw e;
                    }
                    this.listener.serverShutdown();
                    return;
                }
            } catch (Throwable th) {
                log.log(Level.SEVERE, "Accept loop failed", th);
                this.listener.serverShutdown();
                return;
            }
        }
    }

    @Override // io.grpc.internal.InternalServer
    public void shutdown() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        if (this.serverSocket == null) {
            return;
        }
        this.channelz.removeListenSocket(this.listenInstrumented);
        try {
            this.serverSocket.close();
        } catch (IOException unused) {
            log.log(Level.WARNING, "Failed closing server socket", this.serverSocket);
        }
        this.transportExecutor = this.transportExecutorPool.returnObject(this.transportExecutor);
        this.scheduledExecutorService = this.scheduledExecutorServicePool.returnObject(this.scheduledExecutorService);
    }

    @Override // io.grpc.internal.InternalServer
    public SocketAddress getListenSocketAddress() {
        return this.actualListenAddress;
    }

    @Override // io.grpc.internal.InternalServer
    public InternalInstrumented<InternalChannelz.SocketStats> getListenSocketStats() {
        return this.listenInstrumented;
    }

    @Override // io.grpc.internal.InternalServer
    public List<? extends SocketAddress> getListenSocketAddresses() {
        return Collections.singletonList(getListenSocketAddress());
    }

    @Override // io.grpc.internal.InternalServer
    public List<InternalInstrumented<InternalChannelz.SocketStats>> getListenSocketStatsList() {
        return Collections.singletonList(getListenSocketStats());
    }

    /* loaded from: classes5.dex */
    private static final class ListenSocket implements InternalInstrumented<InternalChannelz.SocketStats> {

        /* renamed from: id, reason: collision with root package name */
        private final InternalLogId f128id;
        private final ServerSocket socket;

        public ListenSocket(ServerSocket serverSocket) {
            this.socket = serverSocket;
            this.f128id = InternalLogId.allocate(getClass(), String.valueOf(serverSocket.getLocalSocketAddress()));
        }

        @Override // io.grpc.InternalInstrumented
        public ListenableFuture<InternalChannelz.SocketStats> getStats() {
            return Futures.immediateFuture(new InternalChannelz.SocketStats(null, this.socket.getLocalSocketAddress(), null, new InternalChannelz.SocketOptions.Builder().build(), null));
        }

        @Override // io.grpc.InternalWithLogId
        public InternalLogId getLogId() {
            return this.f128id;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("logId", this.f128id.getId()).add("socket", this.socket).toString();
        }
    }
}
