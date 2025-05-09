package com.pichillilorenzo.flutter_inappwebview_android.webview.web_message;

import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebMessageListenerChannelDelegate extends ChannelDelegateImpl {
    private WebMessageListener webMessageListener;

    public WebMessageListenerChannelDelegate(WebMessageListener webMessageListener, MethodChannel methodChannel) {
        super(methodChannel);
        this.webMessageListener = webMessageListener;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        if (str.equals("postMessage")) {
            WebMessageListener webMessageListener = this.webMessageListener;
            if (webMessageListener != null && (webMessageListener.webView instanceof InAppWebView)) {
                this.webMessageListener.postMessageForInAppWebView(WebMessageCompatExt.fromMap((Map) methodCall.argument("message")), result);
                return;
            } else {
                result.success(false);
                return;
            }
        }
        result.notImplemented();
    }

    public void onPostMessage(WebMessageCompatExt webMessageCompatExt, String str, boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", webMessageCompatExt != null ? webMessageCompatExt.toMap() : null);
        hashMap.put("sourceOrigin", str);
        hashMap.put("isMainFrame", Boolean.valueOf(z));
        channel.invokeMethod("onPostMessage", hashMap);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.webMessageListener = null;
    }
}
