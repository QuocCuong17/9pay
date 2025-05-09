package io.flutter.plugins.webviewflutter;

import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;

/* loaded from: classes5.dex */
public class JavaObjectHostApiImpl implements GeneratedAndroidWebView.JavaObjectHostApi {
    private final InstanceManager instanceManager;

    public JavaObjectHostApiImpl(InstanceManager instanceManager) {
        this.instanceManager = instanceManager;
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.JavaObjectHostApi
    public void dispose(Long l) {
        this.instanceManager.remove(l.longValue());
    }
}
