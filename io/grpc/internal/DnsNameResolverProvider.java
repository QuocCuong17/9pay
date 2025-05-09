package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.firebase.sessions.settings.RemoteSettings;
import io.grpc.InternalServiceProviders;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes5.dex */
public final class DnsNameResolverProvider extends NameResolverProvider {
    private static final String SCHEME = "dns";

    @Override // io.grpc.NameResolver.Factory
    public String getDefaultScheme() {
        return SCHEME;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.NameResolverProvider
    public boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.NameResolverProvider
    public int priority() {
        return 5;
    }

    @Override // io.grpc.NameResolver.Factory
    public DnsNameResolver newNameResolver(URI uri, NameResolver.Args args) {
        if (!SCHEME.equals(uri.getScheme())) {
            return null;
        }
        String str = (String) Preconditions.checkNotNull(uri.getPath(), "targetPath");
        Preconditions.checkArgument(str.startsWith(RemoteSettings.FORWARD_SLASH_STRING), "the path component (%s) of the target (%s) must start with '/'", str, uri);
        return new DnsNameResolver(uri.getAuthority(), str.substring(1), args, GrpcUtil.SHARED_CHANNEL_EXECUTOR, Stopwatch.createUnstarted(), InternalServiceProviders.isAndroid(getClass().getClassLoader()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.NameResolverProvider
    public Collection<Class<? extends SocketAddress>> getProducedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }
}
