package io.flutter.plugins.webviewflutter;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.WebResourceErrorCompat;
import androidx.webkit.WebViewClientCompat;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl;

/* loaded from: classes5.dex */
public class WebViewClientHostApiImpl implements GeneratedAndroidWebView.WebViewClientHostApi {
    private final WebViewClientFlutterApiImpl flutterApi;
    private final InstanceManager instanceManager;
    private final WebViewClientCreator webViewClientCreator;

    /* loaded from: classes5.dex */
    public interface ReleasableWebViewClient extends Releasable {
    }

    /* loaded from: classes5.dex */
    public static class WebViewClientImpl extends WebViewClient implements ReleasableWebViewClient {
        private WebViewClientFlutterApiImpl flutterApi;
        private final boolean shouldOverrideUrlLoading;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onPageFinished$1(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onPageStarted$0(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onReceivedError$2(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onReceivedError$3(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$release$6(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$shouldOverrideUrlLoading$4(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$shouldOverrideUrlLoading$5(Void r0) {
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        }

        public WebViewClientImpl(WebViewClientFlutterApiImpl webViewClientFlutterApiImpl, boolean z) {
            this.shouldOverrideUrlLoading = z;
            this.flutterApi = webViewClientFlutterApiImpl;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onPageStarted(this, webView, str, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda1
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$onPageStarted$0((Void) obj);
                    }
                });
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onPageFinished(this, webView, str, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda0
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$onPageFinished$1((Void) obj);
                    }
                });
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onReceivedRequestError(this, webView, webResourceRequest, webResourceError, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda2
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$onReceivedError$2((Void) obj);
                    }
                });
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onReceivedError(this, webView, Long.valueOf(i), str, str2, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda3
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$onReceivedError$3((Void) obj);
                    }
                });
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.requestLoading(this, webView, webResourceRequest, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda5
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$shouldOverrideUrlLoading$4((Void) obj);
                    }
                });
            }
            return this.shouldOverrideUrlLoading;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.urlLoading(this, webView, str, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda6
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$shouldOverrideUrlLoading$5((Void) obj);
                    }
                });
            }
            return this.shouldOverrideUrlLoading;
        }

        @Override // io.flutter.plugins.webviewflutter.Releasable
        public void release() {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.dispose(this, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientImpl$$ExternalSyntheticLambda4
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientImpl.lambda$release$6((Void) obj);
                    }
                });
            }
            this.flutterApi = null;
        }
    }

    /* loaded from: classes5.dex */
    public static class WebViewClientCompatImpl extends WebViewClientCompat implements ReleasableWebViewClient {
        private WebViewClientFlutterApiImpl flutterApi;
        private final boolean shouldOverrideUrlLoading;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onPageFinished$1(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onPageStarted$0(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onReceivedError$2(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onReceivedError$3(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$release$6(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$shouldOverrideUrlLoading$4(Void r0) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$shouldOverrideUrlLoading$5(Void r0) {
        }

        @Override // android.webkit.WebViewClient
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        }

        public WebViewClientCompatImpl(WebViewClientFlutterApiImpl webViewClientFlutterApiImpl, boolean z) {
            this.shouldOverrideUrlLoading = z;
            this.flutterApi = webViewClientFlutterApiImpl;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onPageStarted(this, webView, str, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda1
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$onPageStarted$0((Void) obj);
                    }
                });
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onPageFinished(this, webView, str, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda0
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$onPageFinished$1((Void) obj);
                    }
                });
            }
        }

        @Override // androidx.webkit.WebViewClientCompat
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceErrorCompat webResourceErrorCompat) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onReceivedRequestError(this, webView, webResourceRequest, webResourceErrorCompat, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda2
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$onReceivedError$2((Void) obj);
                    }
                });
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.onReceivedError(this, webView, Long.valueOf(i), str, str2, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda3
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$onReceivedError$3((Void) obj);
                    }
                });
            }
        }

        @Override // androidx.webkit.WebViewClientCompat, android.webkit.WebViewClient, org.chromium.support_lib_boundary.WebViewClientBoundaryInterface
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.requestLoading(this, webView, webResourceRequest, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda5
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$shouldOverrideUrlLoading$4((Void) obj);
                    }
                });
            }
            return this.shouldOverrideUrlLoading;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.urlLoading(this, webView, str, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda6
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$shouldOverrideUrlLoading$5((Void) obj);
                    }
                });
            }
            return this.shouldOverrideUrlLoading;
        }

        @Override // io.flutter.plugins.webviewflutter.Releasable
        public void release() {
            WebViewClientFlutterApiImpl webViewClientFlutterApiImpl = this.flutterApi;
            if (webViewClientFlutterApiImpl != null) {
                webViewClientFlutterApiImpl.dispose(this, new GeneratedAndroidWebView.WebViewClientFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl$WebViewClientCompatImpl$$ExternalSyntheticLambda4
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewClientHostApiImpl.WebViewClientCompatImpl.lambda$release$6((Void) obj);
                    }
                });
            }
            this.flutterApi = null;
        }
    }

    /* loaded from: classes5.dex */
    public static class WebViewClientCreator {
        public WebViewClient createWebViewClient(WebViewClientFlutterApiImpl webViewClientFlutterApiImpl, boolean z) {
            if (Build.VERSION.SDK_INT >= 24) {
                return new WebViewClientImpl(webViewClientFlutterApiImpl, z);
            }
            return new WebViewClientCompatImpl(webViewClientFlutterApiImpl, z);
        }
    }

    public WebViewClientHostApiImpl(InstanceManager instanceManager, WebViewClientCreator webViewClientCreator, WebViewClientFlutterApiImpl webViewClientFlutterApiImpl) {
        this.instanceManager = instanceManager;
        this.webViewClientCreator = webViewClientCreator;
        this.flutterApi = webViewClientFlutterApiImpl;
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewClientHostApi
    public void create(Long l, Boolean bool) {
        this.instanceManager.addDartCreatedInstance(this.webViewClientCreator.createWebViewClient(this.flutterApi, bool.booleanValue()), l.longValue());
    }
}
