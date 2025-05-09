package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.util.Log;
import android.webkit.WebView;
import androidx.webkit.WebViewFeature;
import androidx.webkit.WebViewRenderProcess;
import androidx.webkit.WebViewRenderProcessClient;
import com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate;

/* loaded from: classes5.dex */
public class InAppWebViewRenderProcessClient extends WebViewRenderProcessClient {
    protected static final String LOG_TAG = "IAWRenderProcessClient";

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
    }

    @Override // androidx.webkit.WebViewRenderProcessClient
    public void onRenderProcessUnresponsive(WebView webView, final WebViewRenderProcess webViewRenderProcess) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.RenderProcessUnresponsiveCallback renderProcessUnresponsiveCallback = new WebViewChannelDelegate.RenderProcessUnresponsiveCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewRenderProcessClient.1
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(Integer num) {
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(Integer num) {
                if (webViewRenderProcess == null) {
                    return true;
                }
                if (num.intValue() != 0 || !WebViewFeature.isFeatureSupported("WEB_VIEW_RENDERER_TERMINATE")) {
                    return false;
                }
                webViewRenderProcess.terminate();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str, String str2, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(", ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                Log.e(InAppWebViewRenderProcessClient.LOG_TAG, sb.toString());
                defaultBehaviour((Integer) null);
            }
        };
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onRenderProcessUnresponsive(inAppWebView.getUrl(), renderProcessUnresponsiveCallback);
        } else {
            renderProcessUnresponsiveCallback.defaultBehaviour(null);
        }
    }

    @Override // androidx.webkit.WebViewRenderProcessClient
    public void onRenderProcessResponsive(WebView webView, final WebViewRenderProcess webViewRenderProcess) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.RenderProcessResponsiveCallback renderProcessResponsiveCallback = new WebViewChannelDelegate.RenderProcessResponsiveCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewRenderProcessClient.2
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(Integer num) {
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(Integer num) {
                if (webViewRenderProcess == null) {
                    return true;
                }
                if (num.intValue() != 0 || !WebViewFeature.isFeatureSupported("WEB_VIEW_RENDERER_TERMINATE")) {
                    return false;
                }
                webViewRenderProcess.terminate();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str, String str2, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(", ");
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(str2);
                Log.e(InAppWebViewRenderProcessClient.LOG_TAG, sb.toString());
                defaultBehaviour((Integer) null);
            }
        };
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onRenderProcessResponsive(inAppWebView.getUrl(), renderProcessResponsiveCallback);
        } else {
            renderProcessResponsiveCallback.defaultBehaviour(null);
        }
    }
}
