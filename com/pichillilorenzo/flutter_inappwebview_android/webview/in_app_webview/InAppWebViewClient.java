package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.webkit.WebResourceRequestCompat;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.CredentialDatabase;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserDelegate;
import com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js.JavaScriptBridgeJS;
import com.pichillilorenzo.flutter_inappwebview_android.types.ClientCertChallenge;
import com.pichillilorenzo.flutter_inappwebview_android.types.ClientCertResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomSchemeResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.HttpAuthResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.HttpAuthenticationChallenge;
import com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction;
import com.pichillilorenzo.flutter_inappwebview_android.types.NavigationActionPolicy;
import com.pichillilorenzo.flutter_inappwebview_android.types.ServerTrustAuthResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.ServerTrustChallenge;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLCredential;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLProtectionSpace;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLRequest;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceErrorExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceResponseExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class InAppWebViewClient extends WebViewClient {
    protected static final String LOG_TAG = "IAWebViewClient";
    private static List<URLCredential> credentialsProposed;
    private static int previousAuthRequestFailureCount;
    private InAppBrowserDelegate inAppBrowserDelegate;

    @Override // android.webkit.WebViewClient
    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
    }

    public InAppWebViewClient(InAppBrowserDelegate inAppBrowserDelegate) {
        this.inAppBrowserDelegate = inAppBrowserDelegate;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0048  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        boolean z;
        boolean isRedirect;
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (!inAppWebView.customSettings.useShouldOverrideUrlLoading.booleanValue()) {
            return false;
        }
        if (WebViewFeature.isFeatureSupported("WEB_RESOURCE_REQUEST_IS_REDIRECT")) {
            isRedirect = WebResourceRequestCompat.isRedirect(webResourceRequest);
        } else if (Build.VERSION.SDK_INT >= 24) {
            isRedirect = webResourceRequest.isRedirect();
        } else {
            z = false;
            onShouldOverrideUrlLoading(inAppWebView, webResourceRequest.getUrl().toString(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders(), webResourceRequest.isForMainFrame(), webResourceRequest.hasGesture(), z);
            if (inAppWebView.regexToCancelSubFramesLoadingCompiled == null) {
                if (webResourceRequest.isForMainFrame()) {
                    return true;
                }
                return inAppWebView.regexToCancelSubFramesLoadingCompiled.matcher(webResourceRequest.getUrl().toString()).matches();
            }
            return webResourceRequest.isForMainFrame();
        }
        z = isRedirect;
        onShouldOverrideUrlLoading(inAppWebView, webResourceRequest.getUrl().toString(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders(), webResourceRequest.isForMainFrame(), webResourceRequest.hasGesture(), z);
        if (inAppWebView.regexToCancelSubFramesLoadingCompiled == null) {
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (!inAppWebView.customSettings.useShouldOverrideUrlLoading.booleanValue()) {
            return false;
        }
        onShouldOverrideUrlLoading(inAppWebView, str, ShareTarget.METHOD_GET, null, true, false, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void allowShouldOverrideUrlLoading(WebView webView, String str, Map<String, String> map, boolean z) {
        if (z) {
            if (Build.VERSION.SDK_INT >= 21) {
                webView.loadUrl(str, map);
            } else {
                webView.loadUrl(str);
            }
        }
    }

    public void onShouldOverrideUrlLoading(final InAppWebView inAppWebView, final String str, String str2, final Map<String, String> map, final boolean z, boolean z2, boolean z3) {
        NavigationAction navigationAction = new NavigationAction(new URLRequest(str, str2, null, map), z, z2, z3);
        WebViewChannelDelegate.ShouldOverrideUrlLoadingCallback shouldOverrideUrlLoadingCallback = new WebViewChannelDelegate.ShouldOverrideUrlLoadingCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.1
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(NavigationActionPolicy navigationActionPolicy) {
                if (AnonymousClass7.$SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy[navigationActionPolicy.ordinal()] != 1) {
                    return false;
                }
                InAppWebViewClient.this.allowShouldOverrideUrlLoading(inAppWebView, str, map, z);
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(NavigationActionPolicy navigationActionPolicy) {
                InAppWebViewClient.this.allowShouldOverrideUrlLoading(inAppWebView, str, map, z);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str3, String str4, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(", ");
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((NavigationActionPolicy) null);
            }
        };
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.shouldOverrideUrlLoading(navigationAction, shouldOverrideUrlLoadingCallback);
        } else {
            shouldOverrideUrlLoadingCallback.defaultBehaviour(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient$7, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy;

        static {
            int[] iArr = new int[NavigationActionPolicy.values().length];
            $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy = iArr;
            try {
                iArr[NavigationActionPolicy.ALLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$types$NavigationActionPolicy[NavigationActionPolicy.CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public void loadCustomJavaScriptOnPageStarted(WebView webView) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
            return;
        }
        String generateWrappedCodeForDocumentStart = inAppWebView.userContentController.generateWrappedCodeForDocumentStart();
        if (Build.VERSION.SDK_INT >= 19) {
            inAppWebView.evaluateJavascript(generateWrappedCodeForDocumentStart, null);
        } else {
            inAppWebView.loadUrl("javascript:" + generateWrappedCodeForDocumentStart.replaceAll("[\r\n]+", ""));
        }
    }

    public void loadCustomJavaScriptOnPageFinished(WebView webView) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        String generateWrappedCodeForDocumentEnd = inAppWebView.userContentController.generateWrappedCodeForDocumentEnd();
        if (Build.VERSION.SDK_INT >= 19) {
            inAppWebView.evaluateJavascript(generateWrappedCodeForDocumentEnd, null);
        } else {
            inAppWebView.loadUrl("javascript:" + generateWrappedCodeForDocumentEnd.replaceAll("[\r\n]+", ""));
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.isLoading = true;
        inAppWebView.disposeWebMessageChannels();
        inAppWebView.userContentController.resetContentWorlds();
        loadCustomJavaScriptOnPageStarted(inAppWebView);
        super.onPageStarted(webView, str, bitmap);
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didStartNavigation(str);
        }
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onLoadStart(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.isLoading = false;
        loadCustomJavaScriptOnPageFinished(inAppWebView);
        previousAuthRequestFailureCount = 0;
        credentialsProposed = null;
        super.onPageFinished(webView, str);
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didFinishNavigation(str);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            inAppWebView.evaluateJavascript(JavaScriptBridgeJS.PLATFORM_READY_JS_SOURCE, null);
        } else {
            inAppWebView.loadUrl("javascript:" + JavaScriptBridgeJS.PLATFORM_READY_JS_SOURCE.replaceAll("[\r\n]+", ""));
        }
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onLoadStop(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        super.doUpdateVisitedHistory(webView, str, z);
        String url = webView.getUrl();
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didUpdateVisitedHistory(url);
        }
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onUpdateVisitedHistory(url, z);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (webResourceRequest.isForMainFrame()) {
            if (inAppWebView.customSettings.disableDefaultErrorPage.booleanValue()) {
                inAppWebView.stopLoading();
                inAppWebView.loadUrl("about:blank");
            }
            inAppWebView.isLoading = false;
            previousAuthRequestFailureCount = 0;
            credentialsProposed = null;
            InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
            if (inAppBrowserDelegate != null) {
                inAppBrowserDelegate.didFailNavigation(webResourceRequest.getUrl().toString(), webResourceError.getErrorCode(), webResourceError.getDescription().toString());
            }
        }
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onReceivedError(WebResourceRequestExt.fromWebResourceRequest(webResourceRequest), WebResourceErrorExt.fromWebResourceError(webResourceError));
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.customSettings.disableDefaultErrorPage.booleanValue()) {
            inAppWebView.stopLoading();
            inAppWebView.loadUrl("about:blank");
        }
        inAppWebView.isLoading = false;
        previousAuthRequestFailureCount = 0;
        credentialsProposed = null;
        InAppBrowserDelegate inAppBrowserDelegate = this.inAppBrowserDelegate;
        if (inAppBrowserDelegate != null) {
            inAppBrowserDelegate.didFailNavigation(str2, i, str);
        }
        WebResourceRequestExt webResourceRequestExt = new WebResourceRequestExt(str2, null, false, false, true, ShareTarget.METHOD_GET);
        WebResourceErrorExt webResourceErrorExt = new WebResourceErrorExt(i, str);
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onReceivedError(webResourceRequestExt, webResourceErrorExt);
        }
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onReceivedHttpError(WebResourceRequestExt.fromWebResourceRequest(webResourceRequest), WebResourceResponseExt.fromWebResourceResponse(webResourceResponse));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x002a  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onReceivedHttpAuthRequest(final WebView webView, final HttpAuthHandler httpAuthHandler, final String str, final String str2) {
        int port;
        InAppWebView inAppWebView;
        String url = webView.getUrl();
        String str3 = "https";
        if (url != null) {
            try {
                URI uri = new URI(url);
                str3 = uri.getScheme();
                port = uri.getPort();
            } catch (URISyntaxException e) {
                Log.e(LOG_TAG, "", e);
            }
            previousAuthRequestFailureCount++;
            if (credentialsProposed != null) {
                credentialsProposed = CredentialDatabase.getInstance(webView.getContext()).getHttpAuthCredentials(str, str3, str2, Integer.valueOf(port));
            }
            List<URLCredential> list = credentialsProposed;
            HttpAuthenticationChallenge httpAuthenticationChallenge = new HttpAuthenticationChallenge(new URLProtectionSpace(str, str3, str2, port, webView.getCertificate(), null), previousAuthRequestFailureCount, (list != null || list.size() <= 0) ? null : credentialsProposed.get(0));
            inAppWebView = (InAppWebView) webView;
            final String str4 = str3;
            final int i = port;
            WebViewChannelDelegate.ReceivedHttpAuthRequestCallback receivedHttpAuthRequestCallback = new WebViewChannelDelegate.ReceivedHttpAuthRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.2
                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public boolean nonNullSuccess(HttpAuthResponse httpAuthResponse) {
                    Integer action = httpAuthResponse.getAction();
                    if (action == null) {
                        return true;
                    }
                    int intValue = action.intValue();
                    if (intValue == 1) {
                        String username = httpAuthResponse.getUsername();
                        String password = httpAuthResponse.getPassword();
                        if (httpAuthResponse.isPermanentPersistence()) {
                            CredentialDatabase.getInstance(webView.getContext()).setHttpAuthCredential(str, str4, str2, Integer.valueOf(i), username, password);
                        }
                        httpAuthHandler.proceed(username, password);
                    } else if (intValue != 2) {
                        List unused = InAppWebViewClient.credentialsProposed = null;
                        int unused2 = InAppWebViewClient.previousAuthRequestFailureCount = 0;
                        httpAuthHandler.cancel();
                    } else if (InAppWebViewClient.credentialsProposed.size() > 0) {
                        URLCredential uRLCredential = (URLCredential) InAppWebViewClient.credentialsProposed.remove(0);
                        httpAuthHandler.proceed(uRLCredential.getUsername(), uRLCredential.getPassword());
                    } else {
                        httpAuthHandler.cancel();
                    }
                    return false;
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public void defaultBehaviour(HttpAuthResponse httpAuthResponse) {
                    InAppWebViewClient.super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
                public void error(String str5, String str6, Object obj) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str5);
                    sb.append(", ");
                    if (str6 == null) {
                        str6 = "";
                    }
                    sb.append(str6);
                    Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                    defaultBehaviour((HttpAuthResponse) null);
                }
            };
            if (inAppWebView.channelDelegate == null) {
                inAppWebView.channelDelegate.onReceivedHttpAuthRequest(httpAuthenticationChallenge, receivedHttpAuthRequestCallback);
                return;
            } else {
                receivedHttpAuthRequestCallback.defaultBehaviour(null);
                return;
            }
        }
        port = 0;
        previousAuthRequestFailureCount++;
        if (credentialsProposed != null) {
        }
        List<URLCredential> list2 = credentialsProposed;
        HttpAuthenticationChallenge httpAuthenticationChallenge2 = new HttpAuthenticationChallenge(new URLProtectionSpace(str, str3, str2, port, webView.getCertificate(), null), previousAuthRequestFailureCount, (list2 != null || list2.size() <= 0) ? null : credentialsProposed.get(0));
        inAppWebView = (InAppWebView) webView;
        final String str42 = str3;
        final int i2 = port;
        WebViewChannelDelegate.ReceivedHttpAuthRequestCallback receivedHttpAuthRequestCallback2 = new WebViewChannelDelegate.ReceivedHttpAuthRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.2
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(HttpAuthResponse httpAuthResponse) {
                Integer action = httpAuthResponse.getAction();
                if (action == null) {
                    return true;
                }
                int intValue = action.intValue();
                if (intValue == 1) {
                    String username = httpAuthResponse.getUsername();
                    String password = httpAuthResponse.getPassword();
                    if (httpAuthResponse.isPermanentPersistence()) {
                        CredentialDatabase.getInstance(webView.getContext()).setHttpAuthCredential(str, str42, str2, Integer.valueOf(i2), username, password);
                    }
                    httpAuthHandler.proceed(username, password);
                } else if (intValue != 2) {
                    List unused = InAppWebViewClient.credentialsProposed = null;
                    int unused2 = InAppWebViewClient.previousAuthRequestFailureCount = 0;
                    httpAuthHandler.cancel();
                } else if (InAppWebViewClient.credentialsProposed.size() > 0) {
                    URLCredential uRLCredential = (URLCredential) InAppWebViewClient.credentialsProposed.remove(0);
                    httpAuthHandler.proceed(uRLCredential.getUsername(), uRLCredential.getPassword());
                } else {
                    httpAuthHandler.cancel();
                }
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(HttpAuthResponse httpAuthResponse) {
                InAppWebViewClient.super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str5, String str6, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str5);
                sb.append(", ");
                if (str6 == null) {
                    str6 = "";
                }
                sb.append(str6);
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((HttpAuthResponse) null);
            }
        };
        if (inAppWebView.channelDelegate == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0044  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        String str;
        int i;
        InAppWebView inAppWebView;
        URI uri;
        String str2 = "https";
        try {
            uri = new URI(sslError.getUrl());
            str = uri.getHost();
        } catch (URISyntaxException e) {
            e = e;
            str = "";
        }
        try {
            str2 = uri.getScheme();
            i = uri.getPort();
        } catch (URISyntaxException e2) {
            e = e2;
            Log.e(LOG_TAG, "", e);
            i = 0;
            ServerTrustChallenge serverTrustChallenge = new ServerTrustChallenge(new URLProtectionSpace(str, str2, null, i, sslError.getCertificate(), sslError));
            inAppWebView = (InAppWebView) webView;
            WebViewChannelDelegate.ReceivedServerTrustAuthRequestCallback receivedServerTrustAuthRequestCallback = new WebViewChannelDelegate.ReceivedServerTrustAuthRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.3
                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public boolean nonNullSuccess(ServerTrustAuthResponse serverTrustAuthResponse) {
                    Integer action = serverTrustAuthResponse.getAction();
                    if (action == null) {
                        return true;
                    }
                    if (action.intValue() == 1) {
                        sslErrorHandler.proceed();
                        return false;
                    }
                    sslErrorHandler.cancel();
                    return false;
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public void defaultBehaviour(ServerTrustAuthResponse serverTrustAuthResponse) {
                    InAppWebViewClient.super.onReceivedSslError(webView, sslErrorHandler, sslError);
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
                public void error(String str3, String str4, Object obj) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(", ");
                    if (str4 == null) {
                        str4 = "";
                    }
                    sb.append(str4);
                    Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                    defaultBehaviour((ServerTrustAuthResponse) null);
                }
            };
            if (inAppWebView.channelDelegate == null) {
            }
        }
        ServerTrustChallenge serverTrustChallenge2 = new ServerTrustChallenge(new URLProtectionSpace(str, str2, null, i, sslError.getCertificate(), sslError));
        inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.ReceivedServerTrustAuthRequestCallback receivedServerTrustAuthRequestCallback2 = new WebViewChannelDelegate.ReceivedServerTrustAuthRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.3
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(ServerTrustAuthResponse serverTrustAuthResponse) {
                Integer action = serverTrustAuthResponse.getAction();
                if (action == null) {
                    return true;
                }
                if (action.intValue() == 1) {
                    sslErrorHandler.proceed();
                    return false;
                }
                sslErrorHandler.cancel();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(ServerTrustAuthResponse serverTrustAuthResponse) {
                InAppWebViewClient.super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, io.flutter.plugin.common.MethodChannel.Result
            public void error(String str3, String str4, Object obj) {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                sb.append(", ");
                if (str4 == null) {
                    str4 = "";
                }
                sb.append(str4);
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((ServerTrustAuthResponse) null);
            }
        };
        if (inAppWebView.channelDelegate == null) {
            inAppWebView.channelDelegate.onReceivedServerTrustAuthRequest(serverTrustChallenge2, receivedServerTrustAuthRequestCallback2);
        } else {
            receivedServerTrustAuthRequestCallback2.defaultBehaviour(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onReceivedClientCertRequest(final WebView webView, final ClientCertRequest clientCertRequest) {
        String scheme;
        final InAppWebView inAppWebView;
        String url = webView.getUrl();
        String host = clientCertRequest.getHost();
        int port = clientCertRequest.getPort();
        if (url != null) {
            try {
                scheme = new URI(url).getScheme();
            } catch (URISyntaxException e) {
                Log.e(LOG_TAG, "", e);
            }
            ClientCertChallenge clientCertChallenge = new ClientCertChallenge(new URLProtectionSpace(host, scheme, null, port, webView.getCertificate(), null), clientCertRequest.getPrincipals(), clientCertRequest.getKeyTypes());
            inAppWebView = (InAppWebView) webView;
            WebViewChannelDelegate.ReceivedClientCertRequestCallback receivedClientCertRequestCallback = new WebViewChannelDelegate.ReceivedClientCertRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.4
                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public boolean nonNullSuccess(ClientCertResponse clientCertResponse) {
                    Integer action = clientCertResponse.getAction();
                    if (action == null || inAppWebView.plugin == null) {
                        return true;
                    }
                    int intValue = action.intValue();
                    if (intValue != 1) {
                        if (intValue == 2) {
                            clientCertRequest.ignore();
                            return false;
                        }
                        clientCertRequest.cancel();
                        return false;
                    }
                    Util.PrivateKeyAndCertificates loadPrivateKeyAndCertificate = Util.loadPrivateKeyAndCertificate(inAppWebView.plugin, clientCertResponse.getCertificatePath(), clientCertResponse.getCertificatePassword(), clientCertResponse.getKeyStoreType());
                    if (loadPrivateKeyAndCertificate != null) {
                        clientCertRequest.proceed(loadPrivateKeyAndCertificate.privateKey, loadPrivateKeyAndCertificate.certificates);
                        return false;
                    }
                    clientCertRequest.cancel();
                    return false;
                }

                @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
                public void defaultBehaviour(ClientCertResponse clientCertResponse) {
                    InAppWebViewClient.super.onReceivedClientCertRequest(webView, clientCertRequest);
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
                    Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                    defaultBehaviour((ClientCertResponse) null);
                }
            };
            if (inAppWebView.channelDelegate == null) {
                inAppWebView.channelDelegate.onReceivedClientCertRequest(clientCertChallenge, receivedClientCertRequestCallback);
                return;
            } else {
                receivedClientCertRequestCallback.defaultBehaviour(null);
                return;
            }
        }
        scheme = "https";
        ClientCertChallenge clientCertChallenge2 = new ClientCertChallenge(new URLProtectionSpace(host, scheme, null, port, webView.getCertificate(), null), clientCertRequest.getPrincipals(), clientCertRequest.getKeyTypes());
        inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.ReceivedClientCertRequestCallback receivedClientCertRequestCallback2 = new WebViewChannelDelegate.ReceivedClientCertRequestCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.4
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(ClientCertResponse clientCertResponse) {
                Integer action = clientCertResponse.getAction();
                if (action == null || inAppWebView.plugin == null) {
                    return true;
                }
                int intValue = action.intValue();
                if (intValue != 1) {
                    if (intValue == 2) {
                        clientCertRequest.ignore();
                        return false;
                    }
                    clientCertRequest.cancel();
                    return false;
                }
                Util.PrivateKeyAndCertificates loadPrivateKeyAndCertificate = Util.loadPrivateKeyAndCertificate(inAppWebView.plugin, clientCertResponse.getCertificatePath(), clientCertResponse.getCertificatePassword(), clientCertResponse.getKeyStoreType());
                if (loadPrivateKeyAndCertificate != null) {
                    clientCertRequest.proceed(loadPrivateKeyAndCertificate.privateKey, loadPrivateKeyAndCertificate.certificates);
                    return false;
                }
                clientCertRequest.cancel();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(ClientCertResponse clientCertResponse) {
                InAppWebViewClient.super.onReceivedClientCertRequest(webView, clientCertRequest);
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
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((ClientCertResponse) null);
            }
        };
        if (inAppWebView.channelDelegate == null) {
        }
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        super.onScaleChanged(webView, f, f2);
        InAppWebView inAppWebView = (InAppWebView) webView;
        inAppWebView.zoomScale = f2 / Util.getPixelDensity(inAppWebView.getContext());
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onZoomScaleChanged(f, f2);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onSafeBrowsingHit(final WebView webView, final WebResourceRequest webResourceRequest, final int i, final SafeBrowsingResponse safeBrowsingResponse) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.SafeBrowsingHitCallback safeBrowsingHitCallback = new WebViewChannelDelegate.SafeBrowsingHitCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.5
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse safeBrowsingResponse2) {
                Integer action = safeBrowsingResponse2.getAction();
                if (action == null) {
                    return true;
                }
                boolean isReport = safeBrowsingResponse2.isReport();
                int intValue = action.intValue();
                if (intValue == 0) {
                    safeBrowsingResponse.backToSafety(isReport);
                    return false;
                }
                if (intValue == 1) {
                    safeBrowsingResponse.proceed(isReport);
                    return false;
                }
                safeBrowsingResponse.showInterstitial(isReport);
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse safeBrowsingResponse2) {
                InAppWebViewClient.super.onSafeBrowsingHit(webView, webResourceRequest, i, safeBrowsingResponse);
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
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse) null);
            }
        };
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onSafeBrowsingHit(webResourceRequest.getUrl().toString(), i, safeBrowsingHitCallback);
        } else {
            safeBrowsingHitCallback.defaultBehaviour(null);
        }
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequestExt webResourceRequestExt) {
        CustomSchemeResponse onLoadResourceWithCustomScheme;
        WebResourceResponseExt shouldInterceptRequest;
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.webViewAssetLoaderExt != null && inAppWebView.webViewAssetLoaderExt.loader != null) {
            try {
                WebResourceResponse shouldInterceptRequest2 = inAppWebView.webViewAssetLoaderExt.loader.shouldInterceptRequest(Uri.parse(webResourceRequestExt.getUrl()));
                if (shouldInterceptRequest2 != null) {
                    return shouldInterceptRequest2;
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "", e);
            }
        }
        WebResourceResponse webResourceResponse = null;
        if (inAppWebView.customSettings.useShouldInterceptRequest.booleanValue()) {
            if (inAppWebView.channelDelegate != null) {
                try {
                    shouldInterceptRequest = inAppWebView.channelDelegate.shouldInterceptRequest(webResourceRequestExt);
                } catch (InterruptedException e2) {
                    Log.e(LOG_TAG, "", e2);
                    return null;
                }
            } else {
                shouldInterceptRequest = null;
            }
            if (shouldInterceptRequest == null) {
                return null;
            }
            String contentType = shouldInterceptRequest.getContentType();
            String contentEncoding = shouldInterceptRequest.getContentEncoding();
            byte[] data = shouldInterceptRequest.getData();
            Map<String, String> headers = shouldInterceptRequest.getHeaders();
            Integer statusCode = shouldInterceptRequest.getStatusCode();
            String reasonPhrase = shouldInterceptRequest.getReasonPhrase();
            ByteArrayInputStream byteArrayInputStream = data != null ? new ByteArrayInputStream(data) : null;
            if (Build.VERSION.SDK_INT >= 21 && statusCode != null && reasonPhrase != null) {
                return new WebResourceResponse(contentType, contentEncoding, statusCode.intValue(), reasonPhrase, headers, byteArrayInputStream);
            }
            return new WebResourceResponse(contentType, contentEncoding, byteArrayInputStream);
        }
        String lowerCase = webResourceRequestExt.getUrl().split(":")[0].toLowerCase();
        try {
            lowerCase = Uri.parse(webResourceRequestExt.getUrl()).getScheme();
        } catch (Exception unused) {
        }
        if (inAppWebView.customSettings.resourceCustomSchemes != null && inAppWebView.customSettings.resourceCustomSchemes.contains(lowerCase)) {
            if (inAppWebView.channelDelegate != null) {
                try {
                    onLoadResourceWithCustomScheme = inAppWebView.channelDelegate.onLoadResourceWithCustomScheme(webResourceRequestExt);
                } catch (InterruptedException e3) {
                    Log.e(LOG_TAG, "", e3);
                    return null;
                }
            } else {
                onLoadResourceWithCustomScheme = null;
            }
            if (onLoadResourceWithCustomScheme != null) {
                try {
                    webResourceResponse = inAppWebView.contentBlockerHandler.checkUrl(inAppWebView, webResourceRequestExt, onLoadResourceWithCustomScheme.getContentType());
                } catch (Exception e4) {
                    Log.e(LOG_TAG, "", e4);
                }
                return webResourceResponse != null ? webResourceResponse : new WebResourceResponse(onLoadResourceWithCustomScheme.getContentType(), onLoadResourceWithCustomScheme.getContentType(), new ByteArrayInputStream(onLoadResourceWithCustomScheme.getData()));
            }
        }
        if (inAppWebView.contentBlockerHandler.getRuleList().size() <= 0) {
            return null;
        }
        try {
            return inAppWebView.contentBlockerHandler.checkUrl(inAppWebView, webResourceRequestExt);
        } catch (Exception e5) {
            Log.e(LOG_TAG, "", e5);
            return null;
        }
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return shouldInterceptRequest(webView, new WebResourceRequestExt(str, null, false, false, true, ShareTarget.METHOD_GET));
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return shouldInterceptRequest(webView, WebResourceRequestExt.fromWebResourceRequest(webResourceRequest));
    }

    @Override // android.webkit.WebViewClient
    public void onFormResubmission(final WebView webView, final Message message, final Message message2) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        WebViewChannelDelegate.FormResubmissionCallback formResubmissionCallback = new WebViewChannelDelegate.FormResubmissionCallback() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewClient.6
            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public boolean nonNullSuccess(Integer num) {
                if (num.intValue() == 0) {
                    message2.sendToTarget();
                    return false;
                }
                message.sendToTarget();
                return false;
            }

            @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
            public void defaultBehaviour(Integer num) {
                InAppWebViewClient.super.onFormResubmission(webView, message, message2);
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
                Log.e(InAppWebViewClient.LOG_TAG, sb.toString());
                defaultBehaviour((Integer) null);
            }
        };
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onFormResubmission(inAppWebView.getUrl(), formResubmissionCallback);
        } else {
            formResubmissionCallback.defaultBehaviour(null);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageCommitVisible(WebView webView, String str) {
        super.onPageCommitVisible(webView, str);
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onPageCommitVisible(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.customSettings.useOnRenderProcessGone.booleanValue() && inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onRenderProcessGone(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
            return true;
        }
        return super.onRenderProcessGone(webView, renderProcessGoneDetail);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        InAppWebView inAppWebView = (InAppWebView) webView;
        if (inAppWebView.channelDelegate != null) {
            inAppWebView.channelDelegate.onReceivedLoginRequest(str, str2, str3);
        }
    }

    public void dispose() {
        if (this.inAppBrowserDelegate != null) {
            this.inAppBrowserDelegate = null;
        }
    }
}
