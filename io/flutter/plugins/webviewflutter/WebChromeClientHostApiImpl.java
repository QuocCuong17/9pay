package io.flutter.plugins.webviewflutter;

import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl;

/* loaded from: classes5.dex */
public class WebChromeClientHostApiImpl implements GeneratedAndroidWebView.WebChromeClientHostApi {
    private final WebChromeClientFlutterApiImpl flutterApi;
    private final InstanceManager instanceManager;
    private final WebChromeClientCreator webChromeClientCreator;

    /* loaded from: classes5.dex */
    public static class WebChromeClientImpl extends WebChromeClient implements Releasable {
        private WebChromeClientFlutterApiImpl flutterApi;
        private WebViewClient webViewClient;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onProgressChanged$0(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$release$1(Void r0) {
        }

        public WebChromeClientImpl(WebChromeClientFlutterApiImpl webChromeClientFlutterApiImpl, WebViewClient webViewClient) {
            this.flutterApi = webChromeClientFlutterApiImpl;
            this.webViewClient = webViewClient;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            return onCreateWindow(webView, message, new WebView(webView.getContext()));
        }

        boolean onCreateWindow(final WebView webView, Message message, WebView webView2) {
            WebViewClient webViewClient = new WebViewClient() { // from class: io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl.WebChromeClientImpl.1
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView3, WebResourceRequest webResourceRequest) {
                    if (WebChromeClientImpl.this.webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest)) {
                        return true;
                    }
                    webView.loadUrl(webResourceRequest.getUrl().toString());
                    return true;
                }

                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView3, String str) {
                    if (WebChromeClientImpl.this.webViewClient.shouldOverrideUrlLoading(webView, str)) {
                        return true;
                    }
                    webView.loadUrl(str);
                    return true;
                }
            };
            if (webView2 == null) {
                webView2 = new WebView(webView.getContext());
            }
            webView2.setWebViewClient(webViewClient);
            ((WebView.WebViewTransport) message.obj).setWebView(webView2);
            message.sendToTarget();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            WebChromeClientFlutterApiImpl webChromeClientFlutterApiImpl = this.flutterApi;
            if (webChromeClientFlutterApiImpl != null) {
                webChromeClientFlutterApiImpl.onProgressChanged(this, webView, Long.valueOf(i), new GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl$WebChromeClientImpl$$ExternalSyntheticLambda0
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebChromeClientHostApiImpl.WebChromeClientImpl.lambda$onProgressChanged$0((Void) obj);
                    }
                });
            }
        }

        public void setWebViewClient(WebViewClient webViewClient) {
            this.webViewClient = webViewClient;
        }

        @Override // io.flutter.plugins.webviewflutter.Releasable
        public void release() {
            WebChromeClientFlutterApiImpl webChromeClientFlutterApiImpl = this.flutterApi;
            if (webChromeClientFlutterApiImpl != null) {
                webChromeClientFlutterApiImpl.dispose(this, new GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl$WebChromeClientImpl$$ExternalSyntheticLambda1
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebChromeClientHostApiImpl.WebChromeClientImpl.lambda$release$1((Void) obj);
                    }
                });
            }
            this.flutterApi = null;
        }
    }

    /* loaded from: classes5.dex */
    public static class WebChromeClientCreator {
        public WebChromeClientImpl createWebChromeClient(WebChromeClientFlutterApiImpl webChromeClientFlutterApiImpl, WebViewClient webViewClient) {
            return new WebChromeClientImpl(webChromeClientFlutterApiImpl, webViewClient);
        }
    }

    public WebChromeClientHostApiImpl(InstanceManager instanceManager, WebChromeClientCreator webChromeClientCreator, WebChromeClientFlutterApiImpl webChromeClientFlutterApiImpl) {
        this.instanceManager = instanceManager;
        this.webChromeClientCreator = webChromeClientCreator;
        this.flutterApi = webChromeClientFlutterApiImpl;
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebChromeClientHostApi
    public void create(Long l, Long l2) {
        this.instanceManager.addDartCreatedInstance(this.webChromeClientCreator.createWebChromeClient(this.flutterApi, (WebViewClient) this.instanceManager.getInstance(l2.longValue())), l.longValue());
    }
}
