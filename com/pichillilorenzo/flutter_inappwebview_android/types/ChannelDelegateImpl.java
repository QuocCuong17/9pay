package com.pichillilorenzo.flutter_inappwebview_android.types;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes5.dex */
public class ChannelDelegateImpl implements IChannelDelegate {
    private MethodChannel channel;

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
    }

    public ChannelDelegateImpl(MethodChannel methodChannel) {
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.IChannelDelegate
    public MethodChannel getChannel() {
        return this.channel;
    }

    public void dispose() {
        MethodChannel methodChannel = this.channel;
        if (methodChannel != null) {
            methodChannel.setMethodCallHandler(null);
            this.channel = null;
        }
    }
}
