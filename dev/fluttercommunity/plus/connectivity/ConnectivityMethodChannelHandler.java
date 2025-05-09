package dev.fluttercommunity.plus.connectivity;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes5.dex */
class ConnectivityMethodChannelHandler implements MethodChannel.MethodCallHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Connectivity connectivity;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectivityMethodChannelHandler(Connectivity connectivity) {
        this.connectivity = connectivity;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if ("check".equals(methodCall.method)) {
            result.success(this.connectivity.getNetworkTypes());
        } else {
            result.notImplemented();
        }
    }
}
