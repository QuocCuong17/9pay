package com.pichillilorenzo.flutter_inappwebview_android.webview.web_message;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebMessageChannelChannelDelegate extends ChannelDelegateImpl {
    private WebMessageChannel webMessageChannel;

    public WebMessageChannelChannelDelegate(WebMessageChannel webMessageChannel, MethodChannel methodChannel) {
        super(methodChannel);
        this.webMessageChannel = webMessageChannel;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0030, code lost:
    
        if (r0.equals("close") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 0;
        switch (str.hashCode()) {
            case 94756344:
                break;
            case 556190586:
                if (str.equals("setWebMessageCallback")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1490029383:
                if (str.equals("postMessage")) {
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
                WebMessageChannel webMessageChannel = this.webMessageChannel;
                if (webMessageChannel != null && (webMessageChannel.webView instanceof InAppWebView)) {
                    this.webMessageChannel.closeForInAppWebView((Integer) methodCall.argument(FirebaseAnalytics.Param.INDEX), result);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 1:
                WebMessageChannel webMessageChannel2 = this.webMessageChannel;
                if (webMessageChannel2 != null && (webMessageChannel2.webView instanceof InAppWebView)) {
                    this.webMessageChannel.setWebMessageCallbackForInAppWebView(((Integer) methodCall.argument(FirebaseAnalytics.Param.INDEX)).intValue(), result);
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 2:
                WebMessageChannel webMessageChannel3 = this.webMessageChannel;
                if (webMessageChannel3 != null && (webMessageChannel3.webView instanceof InAppWebView)) {
                    this.webMessageChannel.postMessageForInAppWebView((Integer) methodCall.argument(FirebaseAnalytics.Param.INDEX), WebMessageCompatExt.fromMap((Map) methodCall.argument("message")), result);
                    return;
                }
                result.success(false);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    public void onMessage(int i, WebMessageCompatExt webMessageCompatExt) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(FirebaseAnalytics.Param.INDEX, Integer.valueOf(i));
        hashMap.put("message", webMessageCompatExt != null ? webMessageCompatExt.toMap() : null);
        channel.invokeMethod("onMessage", hashMap);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.webMessageChannel = null;
    }
}
