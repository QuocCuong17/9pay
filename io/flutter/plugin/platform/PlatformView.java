package io.flutter.plugin.platform;

import android.view.View;

/* loaded from: classes5.dex */
public interface PlatformView {

    /* renamed from: io.flutter.plugin.platform.PlatformView$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static void $default$onFlutterViewAttached(PlatformView _this, View view) {
        }

        public static void $default$onFlutterViewDetached(PlatformView _this) {
        }

        public static void $default$onInputConnectionLocked(PlatformView _this) {
        }

        public static void $default$onInputConnectionUnlocked(PlatformView _this) {
        }
    }

    void dispose();

    View getView();

    void onFlutterViewAttached(View view);

    void onFlutterViewDetached();

    void onInputConnectionLocked();

    void onInputConnectionUnlocked();
}
