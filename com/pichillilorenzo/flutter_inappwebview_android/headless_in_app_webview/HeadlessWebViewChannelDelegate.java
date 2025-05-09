package com.pichillilorenzo.flutter_inappwebview_android.headless_in_app_webview;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.Size2D;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class HeadlessWebViewChannelDelegate extends ChannelDelegateImpl {
    private HeadlessInAppWebView headlessWebView;

    public HeadlessWebViewChannelDelegate(HeadlessInAppWebView headlessInAppWebView, MethodChannel methodChannel) {
        super(methodChannel);
        this.headlessWebView = headlessInAppWebView;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x002a, code lost:
    
        if (r0.equals("dispose") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 1;
        switch (str.hashCode()) {
            case -75151241:
                if (str.equals("getSize")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1671767583:
                break;
            case 1984958339:
                if (str.equals("setSize")) {
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
                HeadlessInAppWebView headlessInAppWebView = this.headlessWebView;
                if (headlessInAppWebView != null) {
                    Size2D size = headlessInAppWebView.getSize();
                    result.success(size != null ? size.toMap() : null);
                    return;
                } else {
                    result.success(null);
                    return;
                }
            case 1:
                HeadlessInAppWebView headlessInAppWebView2 = this.headlessWebView;
                if (headlessInAppWebView2 != null) {
                    headlessInAppWebView2.dispose();
                    result.success(true);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 2:
                if (this.headlessWebView != null) {
                    Size2D fromMap = Size2D.fromMap((Map) methodCall.argument("size"));
                    if (fromMap != null) {
                        this.headlessWebView.setSize(fromMap);
                    }
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

    public void onWebViewCreated() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onWebViewCreated", new HashMap());
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.headlessWebView = null;
    }
}
