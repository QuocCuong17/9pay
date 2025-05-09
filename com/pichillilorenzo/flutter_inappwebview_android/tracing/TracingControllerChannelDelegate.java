package com.pichillilorenzo.flutter_inappwebview_android.tracing;

import androidx.webkit.TracingController;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class TracingControllerChannelDelegate extends ChannelDelegateImpl {
    private TracingControllerManager tracingControllerManager;

    public TracingControllerChannelDelegate(TracingControllerManager tracingControllerManager, MethodChannel methodChannel) {
        super(methodChannel);
        this.tracingControllerManager = tracingControllerManager;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0036, code lost:
    
        if (r1.equals("isTracing") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        FileOutputStream fileOutputStream;
        TracingControllerManager.init();
        TracingController tracingController = TracingControllerManager.tracingController;
        String str = methodCall.method;
        str.hashCode();
        char c = 0;
        switch (str.hashCode()) {
            case -1647175624:
                break;
            case 3540994:
                if (str.equals("stop")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 109757538:
                if (str.equals("start")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (tracingController != null) {
                    result.success(Boolean.valueOf(tracingController.isTracing()));
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 1:
                if (tracingController != null && WebViewFeature.isFeatureSupported("TRACING_CONTROLLER_BASIC_USAGE")) {
                    String str2 = (String) methodCall.argument("filePath");
                    if (str2 != null) {
                        try {
                            fileOutputStream = new FileOutputStream(str2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            result.success(false);
                            return;
                        }
                    } else {
                        fileOutputStream = null;
                    }
                    result.success(Boolean.valueOf(tracingController.stop(fileOutputStream, Executors.newSingleThreadExecutor())));
                    return;
                }
                result.success(false);
                return;
            case 2:
                if (tracingController != null && WebViewFeature.isFeatureSupported("TRACING_CONTROLLER_BASIC_USAGE")) {
                    Map<String, Object> map = (Map) methodCall.argument("settings");
                    TracingSettings tracingSettings = new TracingSettings();
                    tracingSettings.parse2(map);
                    tracingController.start(TracingControllerManager.buildTracingConfig(tracingSettings));
                    result.success(true);
                    return;
                }
                result.success(false);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.tracingControllerManager = null;
    }
}
