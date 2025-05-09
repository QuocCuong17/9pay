package io.grpc;

import io.grpc.NameResolver;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes5.dex */
public abstract class NameResolverProvider extends NameResolver.Factory {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int priority();

    /* JADX INFO: Access modifiers changed from: protected */
    public String getScheme() {
        return getDefaultScheme();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<Class<? extends SocketAddress>> getProducedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }
}
