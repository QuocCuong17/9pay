package io.grpc.okhttp;

import androidx.webkit.Profile;
import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ChoiceChannelCredentials;
import io.grpc.CompositeCallCredentials;
import io.grpc.CompositeChannelCredentials;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannelBuilder;
import io.grpc.TlsChannelCredentials;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.AtomicBackoff;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.KeepAliveManager;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourceHolder;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.SslSocketFactoryChannelCredentials;
import io.grpc.okhttp.internal.CipherSuite;
import io.grpc.okhttp.internal.ConnectionSpec;
import io.grpc.okhttp.internal.Platform;
import io.grpc.okhttp.internal.TlsVersion;
import io.grpc.util.CertificateUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/* loaded from: classes5.dex */
public final class OkHttpChannelBuilder extends AbstractManagedChannelImplBuilder<OkHttpChannelBuilder> {
    public static final int DEFAULT_FLOW_CONTROL_WINDOW = 65535;
    static final ObjectPool<Executor> DEFAULT_TRANSPORT_EXECUTOR_POOL;
    private static final SharedResourceHolder.Resource<Executor> SHARED_EXECUTOR;
    private static final EnumSet<TlsChannelCredentials.Feature> understoodTlsFeatures;
    private ConnectionSpec connectionSpec;
    private int flowControlWindow;
    private final boolean freezeSecurityConfiguration;
    private HostnameVerifier hostnameVerifier;
    private long keepAliveTimeNanos;
    private long keepAliveTimeoutNanos;
    private boolean keepAliveWithoutCalls;
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private int maxInboundMetadataSize;
    private NegotiationType negotiationType;
    private ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
    private SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactory;
    private ObjectPool<Executor> transportExecutorPool;
    private TransportTracer.Factory transportTracerFactory;
    private final boolean useGetForSafeMethods;
    private static final Logger log = Logger.getLogger(OkHttpChannelBuilder.class.getName());
    static final ConnectionSpec INTERNAL_DEFAULT_CONNECTION_SPEC = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256).tlsVersions(TlsVersion.TLS_1_2).supportsTlsExtensions(true).build();
    private static final long AS_LARGE_AS_INFINITE = TimeUnit.DAYS.toNanos(1000);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum NegotiationType {
        TLS,
        PLAINTEXT
    }

    static {
        SharedResourceHolder.Resource<Executor> resource = new SharedResourceHolder.Resource<Executor>() { // from class: io.grpc.okhttp.OkHttpChannelBuilder.1
            @Override // io.grpc.internal.SharedResourceHolder.Resource
            public Executor create() {
                return Executors.newCachedThreadPool(GrpcUtil.getThreadFactory("grpc-okhttp-%d", true));
            }

            @Override // io.grpc.internal.SharedResourceHolder.Resource
            public void close(Executor executor) {
                ((ExecutorService) executor).shutdown();
            }
        };
        SHARED_EXECUTOR = resource;
        DEFAULT_TRANSPORT_EXECUTOR_POOL = SharedResourcePool.forResource(resource);
        understoodTlsFeatures = EnumSet.of(TlsChannelCredentials.Feature.MTLS, TlsChannelCredentials.Feature.CUSTOM_MANAGERS);
    }

    public static OkHttpChannelBuilder forAddress(String str, int i) {
        return new OkHttpChannelBuilder(str, i);
    }

    public static OkHttpChannelBuilder forAddress(String str, int i, ChannelCredentials channelCredentials) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i), channelCredentials);
    }

    public static OkHttpChannelBuilder forTarget(String str) {
        return new OkHttpChannelBuilder(str);
    }

    public static OkHttpChannelBuilder forTarget(String str, ChannelCredentials channelCredentials) {
        SslSocketFactoryResult sslSocketFactoryFrom = sslSocketFactoryFrom(channelCredentials);
        if (sslSocketFactoryFrom.error != null) {
            throw new IllegalArgumentException(sslSocketFactoryFrom.error);
        }
        return new OkHttpChannelBuilder(str, channelCredentials, sslSocketFactoryFrom.callCredentials, sslSocketFactoryFrom.factory);
    }

    private OkHttpChannelBuilder(String str, int i) {
        this(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    private OkHttpChannelBuilder(String str) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        this.scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.freezeSecurityConfiguration = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpChannelBuilder(String str, ChannelCredentials channelCredentials, CallCredentials callCredentials, SSLSocketFactory sSLSocketFactory) {
        this.transportTracerFactory = TransportTracer.getDefaultFactory();
        this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        this.scheduledExecutorServicePool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
        this.connectionSpec = INTERNAL_DEFAULT_CONNECTION_SPEC;
        this.negotiationType = NegotiationType.TLS;
        this.keepAliveTimeNanos = Long.MAX_VALUE;
        this.keepAliveTimeoutNanos = GrpcUtil.DEFAULT_KEEPALIVE_TIMEOUT_NANOS;
        this.flowControlWindow = 65535;
        this.maxInboundMetadataSize = Integer.MAX_VALUE;
        this.useGetForSafeMethods = false;
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(str, channelCredentials, callCredentials, new OkHttpChannelTransportFactoryBuilder(), new OkHttpChannelDefaultPortProvider());
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = sSLSocketFactory == null ? NegotiationType.PLAINTEXT : NegotiationType.TLS;
        this.freezeSecurityConfiguration = true;
    }

    /* loaded from: classes5.dex */
    private final class OkHttpChannelTransportFactoryBuilder implements ManagedChannelImplBuilder.ClientTransportFactoryBuilder {
        private OkHttpChannelTransportFactoryBuilder() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
        public ClientTransportFactory buildClientTransportFactory() {
            return OkHttpChannelBuilder.this.buildTransportFactory();
        }
    }

    /* loaded from: classes5.dex */
    private final class OkHttpChannelDefaultPortProvider implements ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider {
        private OkHttpChannelDefaultPortProvider() {
        }

        @Override // io.grpc.internal.ManagedChannelImplBuilder.ChannelBuilderDefaultPortProvider
        public int getDefaultPort() {
            return OkHttpChannelBuilder.this.getDefaultPort();
        }
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    OkHttpChannelBuilder setTransportTracerFactory(TransportTracer.Factory factory) {
        this.transportTracerFactory = factory;
        return this;
    }

    public OkHttpChannelBuilder transportExecutor(@Nullable Executor executor) {
        if (executor == null) {
            this.transportExecutorPool = DEFAULT_TRANSPORT_EXECUTOR_POOL;
        } else {
            this.transportExecutorPool = new FixedObjectPool(executor);
        }
        return this;
    }

    public OkHttpChannelBuilder socketFactory(@Nullable SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }

    @Deprecated
    public OkHttpChannelBuilder negotiationType(io.grpc.okhttp.NegotiationType negotiationType) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(negotiationType, "type");
        int i = AnonymousClass2.$SwitchMap$io$grpc$okhttp$NegotiationType[negotiationType.ordinal()];
        if (i == 1) {
            this.negotiationType = NegotiationType.TLS;
        } else if (i == 2) {
            this.negotiationType = NegotiationType.PLAINTEXT;
        } else {
            throw new AssertionError("Unknown negotiation type: " + negotiationType);
        }
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
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

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j > 0, "keepalive timeout must be positive");
        long nanos = timeUnit.toNanos(j);
        this.keepAliveTimeoutNanos = nanos;
        this.keepAliveTimeoutNanos = KeepAliveManager.clampKeepAliveTimeoutInNanos(nanos);
        return this;
    }

    public OkHttpChannelBuilder flowControlWindow(int i) {
        Preconditions.checkState(i > 0, "flowControlWindow must be positive");
        this.flowControlWindow = i;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder keepAliveWithoutCalls(boolean z) {
        this.keepAliveWithoutCalls = z;
        return this;
    }

    public OkHttpChannelBuilder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.sslSocketFactory = sSLSocketFactory;
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder hostnameVerifier(@Nullable HostnameVerifier hostnameVerifier) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    public OkHttpChannelBuilder connectionSpec(com.squareup.okhttp.ConnectionSpec connectionSpec) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkArgument(connectionSpec.isTls(), "plaintext ConnectionSpec is not accepted");
        this.connectionSpec = Utils.convertSpec(connectionSpec);
        return this;
    }

    public OkHttpChannelBuilder tlsConnectionSpec(String[] strArr, String[] strArr2) {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        Preconditions.checkNotNull(strArr, "tls versions must not null");
        Preconditions.checkNotNull(strArr2, "ciphers must not null");
        this.connectionSpec = new ConnectionSpec.Builder(true).supportsTlsExtensions(true).tlsVersions(strArr).cipherSuites(strArr2).build();
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder usePlaintext() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.PLAINTEXT;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder useTransportSecurity() {
        Preconditions.checkState(!this.freezeSecurityConfiguration, "Cannot change security when using ChannelCredentials");
        this.negotiationType = NegotiationType.TLS;
        return this;
    }

    public OkHttpChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorServicePool = new FixedObjectPool((ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public OkHttpChannelBuilder maxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "negative max");
        this.maxInboundMessageSize = i;
        return this;
    }

    OkHttpTransportFactory buildTransportFactory() {
        return new OkHttpTransportFactory(this.transportExecutorPool, this.scheduledExecutorServicePool, this.socketFactory, createSslSocketFactory(), this.hostnameVerifier, this.connectionSpec, this.maxInboundMessageSize, this.keepAliveTimeNanos != Long.MAX_VALUE, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpChannelBuilder disableCheckAuthority() {
        this.managedChannelImplBuilder.disableCheckAuthority();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpChannelBuilder enableCheckAuthority() {
        this.managedChannelImplBuilder.enableCheckAuthority();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.grpc.okhttp.OkHttpChannelBuilder$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$NegotiationType;
        static final /* synthetic */ int[] $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType;

        static {
            int[] iArr = new int[NegotiationType.values().length];
            $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType = iArr;
            try {
                iArr[NegotiationType.PLAINTEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[NegotiationType.TLS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[io.grpc.okhttp.NegotiationType.values().length];
            $SwitchMap$io$grpc$okhttp$NegotiationType = iArr2;
            try {
                iArr2[io.grpc.okhttp.NegotiationType.TLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$okhttp$NegotiationType[io.grpc.okhttp.NegotiationType.PLAINTEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    int getDefaultPort() {
        int i = AnonymousClass2.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return 80;
        }
        if (i == 2) {
            return GrpcUtil.DEFAULT_PORT_SSL;
        }
        throw new AssertionError(this.negotiationType + " not handled");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.managedChannelImplBuilder.setStatsEnabled(z);
    }

    @Nullable
    SSLSocketFactory createSslSocketFactory() {
        int i = AnonymousClass2.$SwitchMap$io$grpc$okhttp$OkHttpChannelBuilder$NegotiationType[this.negotiationType.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            try {
                if (this.sslSocketFactory == null) {
                    this.sslSocketFactory = SSLContext.getInstance(Profile.DEFAULT_PROFILE_NAME, Platform.get().getProvider()).getSocketFactory();
                }
                return this.sslSocketFactory;
            } catch (GeneralSecurityException e) {
                throw new RuntimeException("TLS Provider failure", e);
            }
        }
        throw new RuntimeException("Unknown negotiation type: " + this.negotiationType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SslSocketFactoryResult sslSocketFactoryFrom(ChannelCredentials channelCredentials) {
        KeyManager[] keyManagerArr;
        TrustManager[] createTrustManager;
        if (channelCredentials instanceof TlsChannelCredentials) {
            TlsChannelCredentials tlsChannelCredentials = (TlsChannelCredentials) channelCredentials;
            Set<TlsChannelCredentials.Feature> incomprehensible = tlsChannelCredentials.incomprehensible(understoodTlsFeatures);
            if (!incomprehensible.isEmpty()) {
                return SslSocketFactoryResult.error("TLS features not understood: " + incomprehensible);
            }
            if (tlsChannelCredentials.getKeyManagers() != null) {
                keyManagerArr = (KeyManager[]) tlsChannelCredentials.getKeyManagers().toArray(new KeyManager[0]);
            } else if (tlsChannelCredentials.getPrivateKey() == null) {
                keyManagerArr = null;
            } else {
                if (tlsChannelCredentials.getPrivateKeyPassword() != null) {
                    return SslSocketFactoryResult.error("byte[]-based private key with password unsupported. Use unencrypted file or KeyManager");
                }
                try {
                    keyManagerArr = createKeyManager(tlsChannelCredentials.getCertificateChain(), tlsChannelCredentials.getPrivateKey());
                } catch (GeneralSecurityException e) {
                    log.log(Level.FINE, "Exception loading private key from credential", (Throwable) e);
                    return SslSocketFactoryResult.error("Unable to load private key: " + e.getMessage());
                }
            }
            if (tlsChannelCredentials.getTrustManagers() != null) {
                createTrustManager = (TrustManager[]) tlsChannelCredentials.getTrustManagers().toArray(new TrustManager[0]);
            } else if (tlsChannelCredentials.getRootCertificates() != null) {
                try {
                    createTrustManager = createTrustManager(tlsChannelCredentials.getRootCertificates());
                } catch (GeneralSecurityException e2) {
                    log.log(Level.FINE, "Exception loading root certificates from credential", (Throwable) e2);
                    return SslSocketFactoryResult.error("Unable to load root certificates: " + e2.getMessage());
                }
            } else {
                createTrustManager = null;
            }
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS", Platform.get().getProvider());
                sSLContext.init(keyManagerArr, createTrustManager, null);
                return SslSocketFactoryResult.factory(sSLContext.getSocketFactory());
            } catch (GeneralSecurityException e3) {
                throw new RuntimeException("TLS Provider failure", e3);
            }
        }
        if (channelCredentials instanceof InsecureChannelCredentials) {
            return SslSocketFactoryResult.plaintext();
        }
        if (channelCredentials instanceof CompositeChannelCredentials) {
            CompositeChannelCredentials compositeChannelCredentials = (CompositeChannelCredentials) channelCredentials;
            return sslSocketFactoryFrom(compositeChannelCredentials.getChannelCredentials()).withCallCredentials(compositeChannelCredentials.getCallCredentials());
        }
        if (channelCredentials instanceof SslSocketFactoryChannelCredentials.ChannelCredentials) {
            return SslSocketFactoryResult.factory(((SslSocketFactoryChannelCredentials.ChannelCredentials) channelCredentials).getFactory());
        }
        if (channelCredentials instanceof ChoiceChannelCredentials) {
            StringBuilder sb = new StringBuilder();
            Iterator<ChannelCredentials> it = ((ChoiceChannelCredentials) channelCredentials).getCredentialsList().iterator();
            while (it.hasNext()) {
                SslSocketFactoryResult sslSocketFactoryFrom = sslSocketFactoryFrom(it.next());
                if (sslSocketFactoryFrom.error == null) {
                    return sslSocketFactoryFrom;
                }
                sb.append(", ");
                sb.append(sslSocketFactoryFrom.error);
            }
            return SslSocketFactoryResult.error(sb.substring(2));
        }
        return SslSocketFactoryResult.error("Unsupported credential type: " + channelCredentials.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyManager[] createKeyManager(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            X509Certificate[] x509Certificates = CertificateUtils.getX509Certificates(byteArrayInputStream);
            GrpcUtil.closeQuietly(byteArrayInputStream);
            byteArrayInputStream = new ByteArrayInputStream(bArr2);
            try {
                try {
                    PrivateKey privateKey = CertificateUtils.getPrivateKey(byteArrayInputStream);
                    GrpcUtil.closeQuietly(byteArrayInputStream);
                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    try {
                        keyStore.load(null, null);
                        keyStore.setKeyEntry("key", privateKey, new char[0], x509Certificates);
                        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                        keyManagerFactory.init(keyStore, new char[0]);
                        return keyManagerFactory.getKeyManagers();
                    } catch (IOException e) {
                        throw new GeneralSecurityException(e);
                    }
                } catch (IOException e2) {
                    throw new GeneralSecurityException("Unable to decode private key", e2);
                }
            } finally {
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustManager[] createTrustManager(byte[] bArr) throws GeneralSecurityException {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            keyStore.load(null, null);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                X509Certificate[] x509Certificates = CertificateUtils.getX509Certificates(byteArrayInputStream);
                GrpcUtil.closeQuietly(byteArrayInputStream);
                for (X509Certificate x509Certificate : x509Certificates) {
                    keyStore.setCertificateEntry(x509Certificate.getSubjectX500Principal().getName("RFC2253"), x509Certificate);
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                return trustManagerFactory.getTrustManagers();
            } catch (Throwable th) {
                GrpcUtil.closeQuietly(byteArrayInputStream);
                throw th;
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class SslSocketFactoryResult {
        public final CallCredentials callCredentials;
        public final String error;
        public final SSLSocketFactory factory;

        private SslSocketFactoryResult(SSLSocketFactory sSLSocketFactory, CallCredentials callCredentials, String str) {
            this.factory = sSLSocketFactory;
            this.callCredentials = callCredentials;
            this.error = str;
        }

        public static SslSocketFactoryResult error(String str) {
            return new SslSocketFactoryResult(null, null, (String) Preconditions.checkNotNull(str, "error"));
        }

        public static SslSocketFactoryResult plaintext() {
            return new SslSocketFactoryResult(null, null, null);
        }

        public static SslSocketFactoryResult factory(SSLSocketFactory sSLSocketFactory) {
            return new SslSocketFactoryResult((SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "factory"), null, null);
        }

        public SslSocketFactoryResult withCallCredentials(CallCredentials callCredentials) {
            Preconditions.checkNotNull(callCredentials, "callCreds");
            if (this.error != null) {
                return this;
            }
            if (this.callCredentials != null) {
                callCredentials = new CompositeCallCredentials(this.callCredentials, callCredentials);
            }
            return new SslSocketFactoryResult(this.factory, callCredentials, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class OkHttpTransportFactory implements ClientTransportFactory {
        private boolean closed;
        final ConnectionSpec connectionSpec;
        private final boolean enableKeepAlive;
        final Executor executor;
        private final ObjectPool<Executor> executorPool;
        final int flowControlWindow;

        @Nullable
        final HostnameVerifier hostnameVerifier;
        private final AtomicBackoff keepAliveBackoff;
        private final long keepAliveTimeNanos;
        private final long keepAliveTimeoutNanos;
        private final boolean keepAliveWithoutCalls;
        final int maxInboundMetadataSize;
        final int maxMessageSize;
        final ScheduledExecutorService scheduledExecutorService;
        private final ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool;
        final SocketFactory socketFactory;

        @Nullable
        final SSLSocketFactory sslSocketFactory;
        final TransportTracer.Factory transportTracerFactory;
        final boolean useGetForSafeMethods;

        private OkHttpTransportFactory(ObjectPool<Executor> objectPool, ObjectPool<ScheduledExecutorService> objectPool2, @Nullable SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, ConnectionSpec connectionSpec, int i, boolean z, long j, long j2, int i2, boolean z2, int i3, TransportTracer.Factory factory, boolean z3) {
            this.executorPool = objectPool;
            this.executor = objectPool.getObject();
            this.scheduledExecutorServicePool = objectPool2;
            this.scheduledExecutorService = objectPool2.getObject();
            this.socketFactory = socketFactory;
            this.sslSocketFactory = sSLSocketFactory;
            this.hostnameVerifier = hostnameVerifier;
            this.connectionSpec = connectionSpec;
            this.maxMessageSize = i;
            this.enableKeepAlive = z;
            this.keepAliveTimeNanos = j;
            this.keepAliveBackoff = new AtomicBackoff("keepalive time nanos", j);
            this.keepAliveTimeoutNanos = j2;
            this.flowControlWindow = i2;
            this.keepAliveWithoutCalls = z2;
            this.maxInboundMetadataSize = i3;
            this.useGetForSafeMethods = z3;
            this.transportTracerFactory = (TransportTracer.Factory) Preconditions.checkNotNull(factory, "transportTracerFactory");
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            final AtomicBackoff.State state = this.keepAliveBackoff.getState();
            OkHttpClientTransport okHttpClientTransport = new OkHttpClientTransport(this, (InetSocketAddress) socketAddress, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes(), clientTransportOptions.getHttpConnectProxiedSocketAddress(), new Runnable() { // from class: io.grpc.okhttp.OkHttpChannelBuilder.OkHttpTransportFactory.1
                @Override // java.lang.Runnable
                public void run() {
                    state.backoff();
                }
            });
            if (this.enableKeepAlive) {
                okHttpClientTransport.enableKeepAlive(true, state.get(), this.keepAliveTimeoutNanos, this.keepAliveWithoutCalls);
            }
            return okHttpClientTransport;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.scheduledExecutorService;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        @CheckReturnValue
        @Nullable
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials) {
            SslSocketFactoryResult sslSocketFactoryFrom = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCredentials);
            if (sslSocketFactoryFrom.error != null) {
                return null;
            }
            return new ClientTransportFactory.SwapChannelCredentialsResult(new OkHttpTransportFactory(this.executorPool, this.scheduledExecutorServicePool, this.socketFactory, sslSocketFactoryFrom.factory, this.hostnameVerifier, this.connectionSpec, this.maxMessageSize, this.enableKeepAlive, this.keepAliveTimeNanos, this.keepAliveTimeoutNanos, this.flowControlWindow, this.keepAliveWithoutCalls, this.maxInboundMetadataSize, this.transportTracerFactory, this.useGetForSafeMethods), sslSocketFactoryFrom.callCredentials);
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.executorPool.returnObject(this.executor);
            this.scheduledExecutorServicePool.returnObject(this.scheduledExecutorService);
        }
    }
}
