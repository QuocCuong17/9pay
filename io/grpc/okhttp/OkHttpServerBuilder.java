package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.ChoiceServerCredentials;
import io.grpc.ForwardingServerBuilder;
import io.grpc.InsecureServerCredentials;
import io.grpc.ServerBuilder;
import io.grpc.ServerCredentials;
import io.grpc.ServerStreamTracer;
import io.grpc.TlsServerCredentials;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryServerCredentials;
import io.grpc.okhttp.internal.Platform;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* loaded from: classes5.dex */
public final class OkHttpServerBuilder extends ForwardingServerBuilder<OkHttpServerBuilder> {
    private static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    static final long MAX_CONNECTION_AGE_GRACE_NANOS_INFINITE = Long.MAX_VALUE;
    static final long MAX_CONNECTION_AGE_NANOS_DISABLED = Long.MAX_VALUE;
    static final long MAX_CONNECTION_IDLE_NANOS_DISABLED = Long.MAX_VALUE;
    final HandshakerSocketFactory handshakerSocketFactory;
    final SocketAddress listenAddress;
    boolean permitKeepAliveWithoutCalls;
    private static final Logger log = Logger.getLogger(OkHttpServerBuilder.class.getName());
    private static final long MIN_MAX_CONNECTION_IDLE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long MIN_MAX_CONNECTION_AGE_NANO = TimeUnit.SECONDS.toNanos(1);
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);
    private static final ObjectPool<Executor> DEFAULT_TRANSPORT_EXECUTOR_POOL = OkHttpChannelBuilder.DEFAULT_TRANSPORT_EXECUTOR_POOL;
    private static final EnumSet<TlsServerCredentials.Feature> understoodTlsFeatures = EnumSet.of(TlsServerCredentials.Feature.MTLS, TlsServerCredentials.Feature.CUSTOM_MANAGERS);
    final ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new ServerImplBuilder.ClientTransportServersBuilder() { // from class: io.grpc.okhttp.OkHttpServerBuilder$$ExternalSyntheticLambda0
        @Override // io.grpc.internal.ServerImplBuilder.ClientTransportServersBuilder
        public final InternalServer buildClientTransportServers(List list) {
            return OkHttpServerBuilder.this.buildTransportServers(list);
        }
    });
    TransportTracer.Factory transportTracerFactory = TransportTracer.getDefaultFactory();
    ObjectPool<Executor> transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
    ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
    ServerSocketFactory socketFactory = ServerSocketFactory.getDefault();
    long keepAliveTimeNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIME_NANOS;
    long keepAliveTimeoutNanos = GrpcUtil.DEFAULT_SERVER_KEEPALIVE_TIMEOUT_NANOS;
    int flowControlWindow = 65535;
    int maxInboundMetadataSize = 8192;
    int maxInboundMessageSize = 4194304;
    long maxConnectionIdleInNanos = Long.MAX_VALUE;
    long permitKeepAliveTimeInNanos = TimeUnit.MINUTES.toNanos(5);
    long maxConnectionAgeInNanos = Long.MAX_VALUE;
    long maxConnectionAgeGraceInNanos = Long.MAX_VALUE;

    @Deprecated
    public static OkHttpServerBuilder forPort(int i) {
        throw new UnsupportedOperationException();
    }

    public static OkHttpServerBuilder forPort(int i, ServerCredentials serverCredentials) {
        return forPort(new InetSocketAddress(i), serverCredentials);
    }

    public static OkHttpServerBuilder forPort(SocketAddress socketAddress, ServerCredentials serverCredentials) {
        HandshakerSocketFactoryResult handshakerSocketFactoryFrom = handshakerSocketFactoryFrom(serverCredentials);
        if (handshakerSocketFactoryFrom.error != null) {
            throw new IllegalArgumentException(handshakerSocketFactoryFrom.error);
        }
        return new OkHttpServerBuilder(socketAddress, handshakerSocketFactoryFrom.factory);
    }

    OkHttpServerBuilder(SocketAddress socketAddress, HandshakerSocketFactory handshakerSocketFactory) {
        this.listenAddress = (SocketAddress) Preconditions.checkNotNull(socketAddress, "address");
        this.handshakerSocketFactory = (HandshakerSocketFactory) Preconditions.checkNotNull(handshakerSocketFactory, "handshakerSocketFactory");
    }

    @Override // io.grpc.ForwardingServerBuilder
    protected ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpServerBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    public OkHttpServerBuilder transportExecutor(Executor executor) {
        if (executor == null) {
            this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        } else {
            this.transportExecutorPool = new FixedObjectPool(executor);
        }
        return this;
    }

    public OkHttpServerBuilder socketFactory(ServerSocketFactory serverSocketFactory) {
        if (serverSocketFactory == null) {
            this.socketFactory = ServerSocketFactory.getDefault();
        } else {
            this.socketFactory = serverSocketFactory;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive time must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeNanos = nanos;
        long clampKeepAliveTimeInNanos = KeepAliveManager.clampKeepAliveTimeInNanos(nanos);
        this.keepAliveTimeNanos = clampKeepAliveTimeInNanos;
        if (clampKeepAliveTimeInNanos >= AS_LARGE_AS_INFINITE) {
            this.keepAliveTimeNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxConnectionIdle(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "max connection idle must be positive: %s", j);
        long nanos = timeUnit.toNanos(j);
        this.maxConnectionIdleInNanos = nanos;
        if (nanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionIdleInNanos = Long.MAX_VALUE;
        }
        long j2 = this.maxConnectionIdleInNanos;
        long j3 = MIN_MAX_CONNECTION_IDLE_NANO;
        if (j2 < j3) {
            this.maxConnectionIdleInNanos = j3;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxConnectionAge(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "max connection age must be positive: %s", j);
        long nanos = timeUnit.toNanos(j);
        this.maxConnectionAgeInNanos = nanos;
        if (nanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeInNanos = Long.MAX_VALUE;
        }
        long j2 = this.maxConnectionAgeInNanos;
        long j3 = MIN_MAX_CONNECTION_AGE_NANO;
        if (j2 < j3) {
            this.maxConnectionAgeInNanos = j3;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxConnectionAgeGrace(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "max connection age grace must be non-negative: %s", j);
        long nanos = timeUnit.toNanos(j);
        this.maxConnectionAgeGraceInNanos = nanos;
        if (nanos >= AS_LARGE_AS_INFINITE) {
            this.maxConnectionAgeGraceInNanos = Long.MAX_VALUE;
        }
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutNanos = nanos;
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(nanos);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder permitKeepAliveTime(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "permit keepalive time must be non-negative: %s", j);
        this.permitKeepAliveTimeInNanos = timeUnit.toNanos(j);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder permitKeepAliveWithoutCalls(boolean z) {
        this.permitKeepAliveWithoutCalls = z;
        return this;
    }

    public OkHttpServerBuilder flowControlWindow(int i) {
        Preconditions.checkState(i > 0, "flowControlWindow must be positive");
        this.flowControlWindow = i;
        return this;
    }

    public OkHttpServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorServicePool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public OkHttpServerBuilder maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max bytes");
        this.maxInboundMessageSize = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.serverImplBuilder.setStatsEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalServer buildTransportServers(List<? extends ServerStreamTracer.Factory> list) {
        return new OkHttpServer(this, list, this.serverImplBuilder.getChannelz());
    }

    static HandshakerSocketFactoryResult handshakerSocketFactoryFrom(ServerCredentials serverCredentials) {
        KeyManager[] keyManagerArr;
        TrustManager[] createTrustManager;
        ClientCertRequestingSocketFactory clientCertRequestingSocketFactory;
        if (serverCredentials instanceof TlsServerCredentials) {
            TlsServerCredentials tlsServerCredentials = (TlsServerCredentials) serverCredentials;
            Set<TlsServerCredentials.Feature> incomprehensible = tlsServerCredentials.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return HandshakerSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            if (tlsServerCredentials.getKeyManagers() != null) {
                keyManagerArr = (KeyManager[]) tlsServerCredentials.getKeyManagers().toArray(new KeyManager[0]);
            } else if (tlsServerCredentials.getPrivateKey() == null) {
                keyManagerArr = null;
            } else {
                if (tlsServerCredentials.getPrivateKeyPassword() != null) {
                    return HandshakerSocketFactoryResult.error("byte[]-based private key with password unsupported. Use unencrypted file or KeyManager");
                }
                try {
                    keyManagerArr = OkHttpChannelBuilder.createKeyManager(tlsServerCredentials.getCertificateChain(), tlsServerCredentials.getPrivateKey());
                } catch (GeneralSecurityException e) {
                    log.log(Level.FINE, "Exception loading private key from credential", (Throwable) e);
                    return HandshakerSocketFactoryResult.error("Unable to load private key: " + e.getMessage());
                }
            }
            if (tlsServerCredentials.getTrustManagers() != null) {
                createTrustManager = (TrustManager[]) tlsServerCredentials.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsServerCredentials.getRootCertificates() != null) {
                try {
                    createTrustManager = OkHttpChannelBuilder.createTrustManager(tlsServerCredentials.getRootCertificates());
                } catch (GeneralSecurityException e2) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", (Throwable) e2);
                    return HandshakerSocketFactoryResult.error("Unable to load root certificates: " + e2.getMessage());
                }
            } else {
                createTrustManager = null;
            }
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS", Platform.get().getProvider());
                sSLContext.init(keyManagerArr, createTrustManager, null);
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                int i = AnonymousClass1.$SwitchMap$io$grpc$TlsServerCredentials$ClientAuth[tlsServerCredentials.getClientAuth().ordinal()];
                if (i == 1) {
                    clientCertRequestingSocketFactory = new ClientCertRequestingSocketFactory(socketFactory, false);
                } else if (i == 2) {
                    clientCertRequestingSocketFactory = new ClientCertRequestingSocketFactory(socketFactory, true);
                } else {
                    if (i != 3) {
                        return HandshakerSocketFactoryResult.error("Unknown TlsServerCredentials.ClientAuth value: " + tlsServerCredentials.getClientAuth());
                    }
                    return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory(new SslSocketFactoryServerCredentials.ServerCredentials(socketFactory)));
                }
                socketFactory = clientCertRequestingSocketFactory;
                return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory(new SslSocketFactoryServerCredentials.ServerCredentials(socketFactory)));
            } catch (GeneralSecurityException e3) {
                throw new RuntimeException("TLS Provider failure", e3);
            }
        }
        if (serverCredentials instanceof InsecureServerCredentials) {
            return HandshakerSocketFactoryResult.factory(new PlaintextHandshakerSocketFactory());
        }
        if (serverCredentials instanceof SslSocketFactoryServerCredentials.ServerCredentials) {
            return HandshakerSocketFactoryResult.factory(new TlsServerHandshakerSocketFactory((SslSocketFactoryServerCredentials.ServerCredentials) serverCredentials));
        }
        if (serverCredentials instanceof ChoiceServerCredentials) {
            StringBuilder sb = new StringBuilder();
            Iterator<ServerCredentials> it = ((ChoiceServerCredentials) serverCredentials).getCredentialsList().iterator();
            while (it.hasNext()) {
                HandshakerSocketFactoryResult handshakerSocketFactoryFrom = handshakerSocketFactoryFrom(it.next());
                if (handshakerSocketFactoryFrom.error == null) {
                    return handshakerSocketFactoryFrom;
                }
                sb.append(", ");
                sb.append(handshakerSocketFactoryFrom.error);
            }
            return HandshakerSocketFactoryResult.error(sb.substring(2));
        }
        return HandshakerSocketFactoryResult.error("Unsupported credential type: " + serverCredentials.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.grpc.okhttp.OkHttpServerBuilder$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth;

        static {
            int[] iArr = new int[TlsServerCredentials.ClientAuth.values().length];
            $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth = iArr;
            try {
                iArr[TlsServerCredentials.ClientAuth.OPTIONAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth[TlsServerCredentials.ClientAuth.REQUIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$TlsServerCredentials$ClientAuth[TlsServerCredentials.ClientAuth.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class HandshakerSocketFactoryResult {
        public final String error;
        public final HandshakerSocketFactory factory;

        private HandshakerSocketFactoryResult(HandshakerSocketFactory handshakerSocketFactory, String str) {
            this.factory = handshakerSocketFactory;
            this.error = str;
        }

        public static HandshakerSocketFactoryResult error(String str) {
            return new HandshakerSocketFactoryResult(null, (String) Preconditions.checkNotNull(str, "error"));
        }

        public static HandshakerSocketFactoryResult factory(HandshakerSocketFactory handshakerSocketFactory) {
            return new HandshakerSocketFactoryResult((HandshakerSocketFactory) Preconditions.checkNotNull(handshakerSocketFactory, "factory"), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ClientCertRequestingSocketFactory extends SSLSocketFactory {
        private final boolean required;
        private final SSLSocketFactory socketFactory;

        public ClientCertRequestingSocketFactory(SSLSocketFactory sSLSocketFactory, boolean z) {
            this.socketFactory = (SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "socketFactory");
            this.required = z;
        }

        private Socket apply(Socket socket) throws IOException {
            if (!(socket instanceof SSLSocket)) {
                throw new IOException("SocketFactory " + this.socketFactory + " did not produce an SSLSocket: " + socket.getClass());
            }
            SSLSocket sSLSocket = (SSLSocket) socket;
            if (this.required) {
                sSLSocket.setNeedClientAuth(true);
            } else {
                sSLSocket.setWantClientAuth(true);
            }
            return sSLSocket;
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return apply(this.socketFactory.createSocket(socket, str, i, z));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i) throws IOException {
            return apply(this.socketFactory.createSocket(str, i));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return apply(this.socketFactory.createSocket(str, i, inetAddress, i2));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return apply(this.socketFactory.createSocket(inetAddress, i));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return apply(this.socketFactory.createSocket(inetAddress, i, inetAddress2, i2));
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getDefaultCipherSuites() {
            return this.socketFactory.getDefaultCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getSupportedCipherSuites() {
            return this.socketFactory.getSupportedCipherSuites();
        }
    }
}
