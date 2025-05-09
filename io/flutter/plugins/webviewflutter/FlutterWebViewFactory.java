package io.flutter.plugins.webviewflutter;

import android.content.Context;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

/* loaded from: classes5.dex */
class FlutterWebViewFactory extends PlatformViewFactory {
    private final InstanceManager instanceManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlutterWebViewFactory(InstanceManager instanceManager) {
        super(StandardMessageCodec.INSTANCE);
        this.instanceManager = instanceManager;
    }

    @Override // io.flutter.plugin.platform.PlatformViewFactory
    public PlatformView create(Context context, int i, Object obj) {
        PlatformView platformView = (PlatformView) this.instanceManager.getInstance(((Integer) obj).intValue());
        if (platformView != null) {
            return platformView;
        }
        throw new IllegalStateException("Unable to find WebView instance: " + obj);
    }
}
