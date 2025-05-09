package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;

/* loaded from: classes5.dex */
public abstract class ForwardingLoadBalancer extends LoadBalancer {
    protected abstract LoadBalancer delegate();

    @Override // io.grpc.LoadBalancer
    public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        delegate().handleResolvedAddresses(resolvedAddresses);
    }

    @Override // io.grpc.LoadBalancer
    public void handleNameResolutionError(Status status) {
        delegate().handleNameResolutionError(status);
    }

    @Override // io.grpc.LoadBalancer
    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        delegate().handleSubchannelState(subchannel, connectivityStateInfo);
    }

    @Override // io.grpc.LoadBalancer
    public void shutdown() {
        delegate().shutdown();
    }

    @Override // io.grpc.LoadBalancer
    public boolean canHandleEmptyAddressListFromNameResolution() {
        return delegate().canHandleEmptyAddressListFromNameResolution();
    }

    @Override // io.grpc.LoadBalancer
    public void requestConnection() {
        delegate().requestConnection();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
