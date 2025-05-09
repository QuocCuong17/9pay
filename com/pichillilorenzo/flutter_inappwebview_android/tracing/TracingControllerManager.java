package com.pichillilorenzo.flutter_inappwebview_android.tracing;

import androidx.webkit.TracingConfig;
import androidx.webkit.TracingController;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import io.flutter.plugin.common.MethodChannel;

/* loaded from: classes2.dex */
public class TracingControllerManager implements Disposable {
    protected static final String LOG_TAG = "TracingControllerMan";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_tracingcontroller";
    public static TracingController tracingController;
    public TracingControllerChannelDelegate channelDelegate;
    public InAppWebViewFlutterPlugin plugin;

    public TracingControllerManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.plugin = inAppWebViewFlutterPlugin;
        this.channelDelegate = new TracingControllerChannelDelegate(this, new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
    }

    public static void init() {
        if (tracingController == null && WebViewFeature.isFeatureSupported("TRACING_CONTROLLER_BASIC_USAGE")) {
            tracingController = TracingController.getInstance();
        }
    }

    public static TracingConfig buildTracingConfig(TracingSettings tracingSettings) {
        TracingConfig.Builder builder = new TracingConfig.Builder();
        for (Object obj : tracingSettings.categories) {
            if (obj instanceof String) {
                builder.addCategories((String) obj);
            }
            if (obj instanceof Integer) {
                builder.addCategories(((Integer) obj).intValue());
            }
        }
        if (tracingSettings.tracingMode != null) {
            builder.setTracingMode(tracingSettings.tracingMode.intValue());
        }
        return builder.build();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        TracingControllerChannelDelegate tracingControllerChannelDelegate = this.channelDelegate;
        if (tracingControllerChannelDelegate != null) {
            tracingControllerChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        this.plugin = null;
    }
}
