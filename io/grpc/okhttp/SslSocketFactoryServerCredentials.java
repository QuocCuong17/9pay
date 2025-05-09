package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.squareup.okhttp.ConnectionSpec;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes5.dex */
public final class SslSocketFactoryServerCredentials {
    private SslSocketFactoryServerCredentials() {
    }

    public static io.grpc.ServerCredentials create(SSLSocketFactory sSLSocketFactory) {
        return new ServerCredentials(sSLSocketFactory);
    }

    public static io.grpc.ServerCredentials create(SSLSocketFactory sSLSocketFactory, ConnectionSpec connectionSpec) {
        return new ServerCredentials(sSLSocketFactory, Utils.convertSpec(connectionSpec));
    }

    /* loaded from: classes5.dex */
    static final class ServerCredentials extends io.grpc.ServerCredentials {
        private final io.grpc.okhttp.internal.ConnectionSpec connectionSpec;
        private final SSLSocketFactory factory;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ServerCredentials(SSLSocketFactory sSLSocketFactory) {
            this(sSLSocketFactory, OkHttpChannelBuilder.INTERNAL_DEFAULT_CONNECTION_SPEC);
        }

        ServerCredentials(SSLSocketFactory sSLSocketFactory, io.grpc.okhttp.internal.ConnectionSpec connectionSpec) {
            this.factory = (SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "factory");
            this.connectionSpec = (io.grpc.okhttp.internal.ConnectionSpec) Preconditions.checkNotNull(connectionSpec, "connectionSpec");
        }

        public SSLSocketFactory getFactory() {
            return this.factory;
        }

        public io.grpc.okhttp.internal.ConnectionSpec getConnectionSpec() {
            return this.connectionSpec;
        }
    }
}
