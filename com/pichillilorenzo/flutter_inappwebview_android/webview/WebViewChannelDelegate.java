package com.pichillilorenzo.flutter_inappwebview_android.webview;

import android.net.Uri;
import android.os.Build;
import android.webkit.ValueCallback;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserActivity;
import com.pichillilorenzo.flutter_inappwebview_android.in_app_browser.InAppBrowserSettings;
import com.pichillilorenzo.flutter_inappwebview_android.print_job.PrintJobSettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.ClientCertChallenge;
import com.pichillilorenzo.flutter_inappwebview_android.types.ClientCertResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.ContentWorld;
import com.pichillilorenzo.flutter_inappwebview_android.types.CreateWindowAction;
import com.pichillilorenzo.flutter_inappwebview_android.types.CustomSchemeResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.DownloadStartRequest;
import com.pichillilorenzo.flutter_inappwebview_android.types.GeolocationPermissionShowPromptResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.HitTestResult;
import com.pichillilorenzo.flutter_inappwebview_android.types.HttpAuthResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.HttpAuthenticationChallenge;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsAlertResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsBeforeUnloadResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsConfirmResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.JsPromptResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.NavigationAction;
import com.pichillilorenzo.flutter_inappwebview_android.types.NavigationActionPolicy;
import com.pichillilorenzo.flutter_inappwebview_android.types.PermissionResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.SafeBrowsingResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.ServerTrustAuthResponse;
import com.pichillilorenzo.flutter_inappwebview_android.types.ServerTrustChallenge;
import com.pichillilorenzo.flutter_inappwebview_android.types.SslCertificateExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.SyncBaseCallbackResultImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLRequest;
import com.pichillilorenzo.flutter_inappwebview_android.types.UserScript;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePortCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceErrorExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceResponseExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebViewSettings;
import com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageChannel;
import com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageListener;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebViewChannelDelegate extends ChannelDelegateImpl {
    static final String LOG_TAG = "WebViewChannelDelegate";
    private InAppWebView webView;

    /* loaded from: classes5.dex */
    public static class CallJsHandlerCallback extends BaseCallbackResultImpl<Object> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public Object decodeResult(Object obj) {
            return obj;
        }
    }

    public WebViewChannelDelegate(InAppWebView inAppWebView, MethodChannel methodChannel) {
        super(methodChannel);
        this.webView = inAppWebView;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        try {
            boolean z = false;
            switch (AnonymousClass8.$SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.valueOf(methodCall.method).ordinal()]) {
                case 1:
                    InAppWebView inAppWebView = this.webView;
                    result.success(inAppWebView != null ? inAppWebView.getUrl() : null);
                    return;
                case 2:
                    InAppWebView inAppWebView2 = this.webView;
                    result.success(inAppWebView2 != null ? inAppWebView2.getTitle() : null);
                    return;
                case 3:
                    InAppWebView inAppWebView3 = this.webView;
                    result.success(inAppWebView3 != null ? Integer.valueOf(inAppWebView3.getProgress()) : null);
                    return;
                case 4:
                    if (this.webView != null) {
                        this.webView.loadUrl(URLRequest.fromMap((Map) methodCall.argument("urlRequest")));
                    }
                    result.success(true);
                    return;
                case 5:
                    if (this.webView != null) {
                        this.webView.postUrl((String) methodCall.argument("url"), (byte[]) methodCall.argument("postData"));
                    }
                    result.success(true);
                    return;
                case 6:
                    if (this.webView != null) {
                        this.webView.loadDataWithBaseURL((String) methodCall.argument("baseUrl"), (String) methodCall.argument("data"), (String) methodCall.argument("mimeType"), (String) methodCall.argument("encoding"), (String) methodCall.argument("historyUrl"));
                    }
                    result.success(true);
                    return;
                case 7:
                    if (this.webView != null) {
                        try {
                            this.webView.loadFile((String) methodCall.argument("assetFilePath"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            result.error(LOG_TAG, e.getMessage(), null);
                            return;
                        }
                    }
                    result.success(true);
                    return;
                case 8:
                    if (this.webView != null) {
                        this.webView.evaluateJavascript((String) methodCall.argument("source"), ContentWorld.fromMap((Map) methodCall.argument("contentWorld")), new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.1
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str) {
                                result.success(str);
                            }
                        });
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 9:
                    if (this.webView != null) {
                        this.webView.injectJavascriptFileFromUrl((String) methodCall.argument("urlFile"), (Map) methodCall.argument("scriptHtmlTagAttributes"));
                    }
                    result.success(true);
                    return;
                case 10:
                    if (this.webView != null) {
                        this.webView.injectCSSCode((String) methodCall.argument("source"));
                    }
                    result.success(true);
                    return;
                case 11:
                    if (this.webView != null) {
                        this.webView.injectCSSFileFromUrl((String) methodCall.argument("urlFile"), (Map) methodCall.argument("cssLinkHtmlTagAttributes"));
                    }
                    result.success(true);
                    return;
                case 12:
                    InAppWebView inAppWebView4 = this.webView;
                    if (inAppWebView4 != null) {
                        inAppWebView4.reload();
                    }
                    result.success(true);
                    return;
                case 13:
                    InAppWebView inAppWebView5 = this.webView;
                    if (inAppWebView5 != null) {
                        inAppWebView5.goBack();
                    }
                    result.success(true);
                    return;
                case 14:
                    InAppWebView inAppWebView6 = this.webView;
                    if (inAppWebView6 != null && inAppWebView6.canGoBack()) {
                        z = true;
                    }
                    result.success(Boolean.valueOf(z));
                    return;
                case 15:
                    InAppWebView inAppWebView7 = this.webView;
                    if (inAppWebView7 != null) {
                        inAppWebView7.goForward();
                    }
                    result.success(true);
                    return;
                case 16:
                    InAppWebView inAppWebView8 = this.webView;
                    if (inAppWebView8 != null && inAppWebView8.canGoForward()) {
                        z = true;
                    }
                    result.success(Boolean.valueOf(z));
                    return;
                case 17:
                    InAppWebView inAppWebView9 = this.webView;
                    if (inAppWebView9 != null) {
                        inAppWebView9.goBackOrForward(((Integer) methodCall.argument("steps")).intValue());
                    }
                    result.success(true);
                    return;
                case 18:
                    InAppWebView inAppWebView10 = this.webView;
                    if (inAppWebView10 != null && inAppWebView10.canGoBackOrForward(((Integer) methodCall.argument("steps")).intValue())) {
                        z = true;
                    }
                    result.success(Boolean.valueOf(z));
                    return;
                case 19:
                    InAppWebView inAppWebView11 = this.webView;
                    if (inAppWebView11 != null) {
                        inAppWebView11.stopLoading();
                    }
                    result.success(true);
                    return;
                case 20:
                    InAppWebView inAppWebView12 = this.webView;
                    if (inAppWebView12 != null && inAppWebView12.isLoading()) {
                        z = true;
                    }
                    result.success(Boolean.valueOf(z));
                    return;
                case 21:
                    if (this.webView != null) {
                        this.webView.takeScreenshot((Map) methodCall.argument("screenshotConfiguration"), result);
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 22:
                    InAppWebView inAppWebView13 = this.webView;
                    if (inAppWebView13 != null && (inAppWebView13.getInAppBrowserDelegate() instanceof InAppBrowserActivity)) {
                        InAppBrowserActivity inAppBrowserActivity = (InAppBrowserActivity) this.webView.getInAppBrowserDelegate();
                        InAppBrowserSettings inAppBrowserSettings = new InAppBrowserSettings();
                        HashMap<String, Object> hashMap = (HashMap) methodCall.argument("settings");
                        inAppBrowserSettings.parse2((Map<String, Object>) hashMap);
                        inAppBrowserActivity.setSettings(inAppBrowserSettings, hashMap);
                    } else if (this.webView != null) {
                        InAppWebViewSettings inAppWebViewSettings = new InAppWebViewSettings();
                        HashMap<String, Object> hashMap2 = (HashMap) methodCall.argument("settings");
                        inAppWebViewSettings.parse2((Map<String, Object>) hashMap2);
                        this.webView.setSettings(inAppWebViewSettings, hashMap2);
                    }
                    result.success(true);
                    return;
                case 23:
                    InAppWebView inAppWebView14 = this.webView;
                    if (inAppWebView14 != null && (inAppWebView14.getInAppBrowserDelegate() instanceof InAppBrowserActivity)) {
                        result.success(((InAppBrowserActivity) this.webView.getInAppBrowserDelegate()).getCustomSettings());
                        return;
                    } else {
                        InAppWebView inAppWebView15 = this.webView;
                        result.success(inAppWebView15 != null ? inAppWebView15.getCustomSettings() : null);
                        return;
                    }
                case 24:
                    InAppWebView inAppWebView16 = this.webView;
                    if (inAppWebView16 != null && (inAppWebView16.getInAppBrowserDelegate() instanceof InAppBrowserActivity)) {
                        ((InAppBrowserActivity) this.webView.getInAppBrowserDelegate()).close(result);
                        return;
                    } else {
                        result.notImplemented();
                        return;
                    }
                case 25:
                    InAppWebView inAppWebView17 = this.webView;
                    if (inAppWebView17 != null && (inAppWebView17.getInAppBrowserDelegate() instanceof InAppBrowserActivity)) {
                        ((InAppBrowserActivity) this.webView.getInAppBrowserDelegate()).show();
                        result.success(true);
                        return;
                    } else {
                        result.notImplemented();
                        return;
                    }
                case 26:
                    InAppWebView inAppWebView18 = this.webView;
                    if (inAppWebView18 != null && (inAppWebView18.getInAppBrowserDelegate() instanceof InAppBrowserActivity)) {
                        ((InAppBrowserActivity) this.webView.getInAppBrowserDelegate()).hide();
                        result.success(true);
                        return;
                    } else {
                        result.notImplemented();
                        return;
                    }
                case 27:
                    InAppWebView inAppWebView19 = this.webView;
                    if (inAppWebView19 != null && (inAppWebView19.getInAppBrowserDelegate() instanceof InAppBrowserActivity)) {
                        result.success(Boolean.valueOf(((InAppBrowserActivity) this.webView.getInAppBrowserDelegate()).isHidden));
                        return;
                    } else {
                        result.notImplemented();
                        return;
                    }
                case 28:
                    InAppWebView inAppWebView20 = this.webView;
                    result.success(inAppWebView20 != null ? inAppWebView20.getCopyBackForwardList() : null);
                    return;
                case 29:
                    if (this.webView != null && WebViewFeature.isFeatureSupported("START_SAFE_BROWSING")) {
                        WebViewCompat.startSafeBrowsing(this.webView.getContext(), new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.2
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(Boolean bool) {
                                result.success(bool);
                            }
                        });
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 30:
                    InAppWebView inAppWebView21 = this.webView;
                    if (inAppWebView21 != null) {
                        inAppWebView21.clearAllCache();
                    }
                    result.success(true);
                    return;
                case 31:
                    InAppWebView inAppWebView22 = this.webView;
                    if (inAppWebView22 != null) {
                        inAppWebView22.clearSslPreferences();
                    }
                    result.success(true);
                    return;
                case 32:
                    if (this.webView != null) {
                        this.webView.findAllAsync((String) methodCall.argument("find"));
                    }
                    result.success(true);
                    return;
                case 33:
                    if (this.webView != null) {
                        this.webView.findNext(((Boolean) methodCall.argument("forward")).booleanValue());
                    }
                    result.success(true);
                    return;
                case 34:
                    InAppWebView inAppWebView23 = this.webView;
                    if (inAppWebView23 != null) {
                        inAppWebView23.clearMatches();
                    }
                    result.success(true);
                    return;
                case 35:
                    if (this.webView != null) {
                        this.webView.scrollTo((Integer) methodCall.argument(ViewHierarchyNode.JsonKeys.X), (Integer) methodCall.argument(ViewHierarchyNode.JsonKeys.Y), (Boolean) methodCall.argument("animated"));
                    }
                    result.success(true);
                    return;
                case 36:
                    if (this.webView != null) {
                        this.webView.scrollBy((Integer) methodCall.argument(ViewHierarchyNode.JsonKeys.X), (Integer) methodCall.argument(ViewHierarchyNode.JsonKeys.Y), (Boolean) methodCall.argument("animated"));
                    }
                    result.success(true);
                    return;
                case 37:
                    InAppWebView inAppWebView24 = this.webView;
                    if (inAppWebView24 != null) {
                        inAppWebView24.onPause();
                    }
                    result.success(true);
                    return;
                case 38:
                    InAppWebView inAppWebView25 = this.webView;
                    if (inAppWebView25 != null) {
                        inAppWebView25.onResume();
                    }
                    result.success(true);
                    return;
                case 39:
                    InAppWebView inAppWebView26 = this.webView;
                    if (inAppWebView26 != null) {
                        inAppWebView26.pauseTimers();
                    }
                    result.success(true);
                    return;
                case 40:
                    InAppWebView inAppWebView27 = this.webView;
                    if (inAppWebView27 != null) {
                        inAppWebView27.resumeTimers();
                    }
                    result.success(true);
                    return;
                case 41:
                    if (this.webView != null && Build.VERSION.SDK_INT >= 21) {
                        PrintJobSettings printJobSettings = new PrintJobSettings();
                        Map<String, Object> map = (Map) methodCall.argument("settings");
                        if (map != null) {
                            printJobSettings.parse2(map);
                        }
                        result.success(this.webView.printCurrentPage(printJobSettings));
                        return;
                    }
                    result.success(null);
                    return;
                case 42:
                    InAppWebView inAppWebView28 = this.webView;
                    if (inAppWebView28 instanceof InAppWebView) {
                        result.success(Integer.valueOf(inAppWebView28.getContentHeight()));
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 43:
                    InAppWebView inAppWebView29 = this.webView;
                    if (inAppWebView29 instanceof InAppWebView) {
                        inAppWebView29.getContentWidth(new ValueCallback<Integer>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.3
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(Integer num) {
                                result.success(num);
                            }
                        });
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 44:
                    if (this.webView != null && Build.VERSION.SDK_INT >= 21) {
                        this.webView.zoomBy((float) ((Double) methodCall.argument("zoomFactor")).doubleValue());
                    }
                    result.success(true);
                    return;
                case 45:
                    InAppWebView inAppWebView30 = this.webView;
                    result.success(inAppWebView30 != null ? inAppWebView30.getOriginalUrl() : null);
                    return;
                case 46:
                    InAppWebView inAppWebView31 = this.webView;
                    if (inAppWebView31 instanceof InAppWebView) {
                        result.success(Float.valueOf(inAppWebView31.getZoomScale()));
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 47:
                    if ((this.webView instanceof InAppWebView) && Build.VERSION.SDK_INT >= 19) {
                        this.webView.getSelectedText(new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.4
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str) {
                                result.success(str);
                            }
                        });
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 48:
                    InAppWebView inAppWebView32 = this.webView;
                    if (inAppWebView32 instanceof InAppWebView) {
                        result.success(HitTestResult.fromWebViewHitTestResult(inAppWebView32.getHitTestResult()).toMap());
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 49:
                    if (this.webView != null) {
                        result.success(Boolean.valueOf(this.webView.pageDown(((Boolean) methodCall.argument("bottom")).booleanValue())));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 50:
                    if (this.webView != null) {
                        result.success(Boolean.valueOf(this.webView.pageUp(((Boolean) methodCall.argument(ViewHierarchyConstants.DIMENSION_TOP_KEY)).booleanValue())));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 51:
                    if (this.webView != null) {
                        this.webView.saveWebArchive((String) methodCall.argument("filePath"), ((Boolean) methodCall.argument("autoname")).booleanValue(), new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.5
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str) {
                                result.success(str);
                            }
                        });
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 52:
                    InAppWebView inAppWebView33 = this.webView;
                    if (inAppWebView33 != null) {
                        result.success(Boolean.valueOf(inAppWebView33.zoomIn()));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 53:
                    InAppWebView inAppWebView34 = this.webView;
                    if (inAppWebView34 != null) {
                        result.success(Boolean.valueOf(inAppWebView34.zoomOut()));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 54:
                    InAppWebView inAppWebView35 = this.webView;
                    if (inAppWebView35 != null) {
                        inAppWebView35.clearFocus();
                    }
                    result.success(true);
                    return;
                case 55:
                    if (this.webView != null) {
                        this.webView.setContextMenu((Map) methodCall.argument("contextMenu"));
                    }
                    result.success(true);
                    return;
                case 56:
                    InAppWebView inAppWebView36 = this.webView;
                    if (inAppWebView36 != null) {
                        result.success(inAppWebView36.requestFocusNodeHref());
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 57:
                    InAppWebView inAppWebView37 = this.webView;
                    if (inAppWebView37 != null) {
                        result.success(inAppWebView37.requestImageRef());
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 58:
                    InAppWebView inAppWebView38 = this.webView;
                    if (inAppWebView38 != null) {
                        result.success(Integer.valueOf(inAppWebView38.getScrollX()));
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 59:
                    InAppWebView inAppWebView39 = this.webView;
                    if (inAppWebView39 != null) {
                        result.success(Integer.valueOf(inAppWebView39.getScrollY()));
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 60:
                    InAppWebView inAppWebView40 = this.webView;
                    if (inAppWebView40 != null) {
                        result.success(SslCertificateExt.toMap(inAppWebView40.getCertificate()));
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 61:
                    InAppWebView inAppWebView41 = this.webView;
                    if (inAppWebView41 != null) {
                        inAppWebView41.clearHistory();
                    }
                    result.success(true);
                    return;
                case 62:
                    InAppWebView inAppWebView42 = this.webView;
                    if (inAppWebView42 != null && inAppWebView42.getUserContentController() != null) {
                        result.success(Boolean.valueOf(this.webView.getUserContentController().addUserOnlyScript(UserScript.fromMap((Map) methodCall.argument("userScript")))));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 63:
                    InAppWebView inAppWebView43 = this.webView;
                    if (inAppWebView43 != null && inAppWebView43.getUserContentController() != null) {
                        result.success(Boolean.valueOf(this.webView.getUserContentController().removeUserOnlyScriptAt(((Integer) methodCall.argument(FirebaseAnalytics.Param.INDEX)).intValue(), UserScript.fromMap((Map) methodCall.argument("userScript")).getInjectionTime())));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 64:
                    InAppWebView inAppWebView44 = this.webView;
                    if (inAppWebView44 != null && inAppWebView44.getUserContentController() != null) {
                        this.webView.getUserContentController().removeUserOnlyScriptsByGroupName((String) methodCall.argument("groupName"));
                    }
                    result.success(true);
                    return;
                case 65:
                    InAppWebView inAppWebView45 = this.webView;
                    if (inAppWebView45 != null && inAppWebView45.getUserContentController() != null) {
                        this.webView.getUserContentController().removeAllUserOnlyScripts();
                    }
                    result.success(true);
                    return;
                case 66:
                    if (this.webView != null && Build.VERSION.SDK_INT >= 21) {
                        this.webView.callAsyncJavaScript((String) methodCall.argument("functionBody"), (Map) methodCall.argument(Constant.PARAM_SQL_ARGUMENTS), ContentWorld.fromMap((Map) methodCall.argument("contentWorld")), new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.6
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(String str) {
                                result.success(str);
                            }
                        });
                        return;
                    } else {
                        result.success(null);
                        return;
                    }
                case 67:
                    if (this.webView != null && Build.VERSION.SDK_INT >= 21) {
                        this.webView.isSecureContext(new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate.7
                            @Override // android.webkit.ValueCallback
                            public void onReceiveValue(Boolean bool) {
                                result.success(bool);
                            }
                        });
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 68:
                    InAppWebView inAppWebView46 = this.webView;
                    if (inAppWebView46 != null) {
                        if ((inAppWebView46 instanceof InAppWebView) && WebViewFeature.isFeatureSupported("CREATE_WEB_MESSAGE_CHANNEL")) {
                            result.success(this.webView.createCompatWebMessageChannel().toMap());
                            return;
                        } else {
                            result.success(null);
                            return;
                        }
                    }
                    result.success(null);
                    return;
                case 69:
                    if (this.webView != null && WebViewFeature.isFeatureSupported("POST_WEB_MESSAGE")) {
                        WebMessageCompatExt fromMap = WebMessageCompatExt.fromMap((Map) methodCall.argument("message"));
                        String str = (String) methodCall.argument("targetOrigin");
                        ArrayList arrayList = new ArrayList();
                        List<WebMessagePortCompatExt> ports = fromMap.getPorts();
                        if (ports != null) {
                            for (WebMessagePortCompatExt webMessagePortCompatExt : ports) {
                                WebMessageChannel webMessageChannel = this.webView.getWebMessageChannels().get(webMessagePortCompatExt.getWebMessageChannelId());
                                if (webMessageChannel != null && (this.webView instanceof InAppWebView)) {
                                    arrayList.add(webMessageChannel.compatPorts.get(webMessagePortCompatExt.getIndex()));
                                }
                            }
                        }
                        Object data = fromMap.getData();
                        if (this.webView instanceof InAppWebView) {
                            try {
                                if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_ARRAY_BUFFER") && data != null && fromMap.getType() == 1) {
                                    WebViewCompat.postWebMessage(this.webView, new WebMessageCompat((byte[]) data, (WebMessagePortCompat[]) arrayList.toArray(new WebMessagePortCompat[0])), Uri.parse(str));
                                } else {
                                    WebViewCompat.postWebMessage(this.webView, new WebMessageCompat(data != null ? data.toString() : null, (WebMessagePortCompat[]) arrayList.toArray(new WebMessagePortCompat[0])), Uri.parse(str));
                                }
                                result.success(true);
                                return;
                            } catch (Exception e2) {
                                result.error(LOG_TAG, e2.getMessage(), null);
                                return;
                            }
                        }
                        return;
                    }
                    result.success(true);
                    return;
                case 70:
                    if (this.webView != null) {
                        Map map2 = (Map) methodCall.argument("webMessageListener");
                        InAppWebView inAppWebView47 = this.webView;
                        WebMessageListener fromMap2 = WebMessageListener.fromMap(inAppWebView47, inAppWebView47.getPlugin().messenger, map2);
                        if ((this.webView instanceof InAppWebView) && WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
                            try {
                                this.webView.addWebMessageListener(fromMap2);
                                result.success(true);
                                return;
                            } catch (Exception e3) {
                                result.error(LOG_TAG, e3.getMessage(), null);
                                return;
                            }
                        }
                        result.success(true);
                        return;
                    }
                    result.success(true);
                    return;
                case 71:
                    InAppWebView inAppWebView48 = this.webView;
                    if (inAppWebView48 != null) {
                        result.success(Boolean.valueOf(inAppWebView48.canScrollVertically()));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 72:
                    InAppWebView inAppWebView49 = this.webView;
                    if (inAppWebView49 != null) {
                        result.success(Boolean.valueOf(inAppWebView49.canScrollHorizontally()));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 73:
                    InAppWebView inAppWebView50 = this.webView;
                    if (inAppWebView50 != null) {
                        result.success(Boolean.valueOf(inAppWebView50.isInFullscreen()));
                        return;
                    } else {
                        result.success(false);
                        return;
                    }
                case 74:
                    InAppWebView inAppWebView51 = this.webView;
                    if (inAppWebView51 != null) {
                        inAppWebView51.clearFormData();
                    }
                    result.success(true);
                    return;
                default:
                    return;
            }
        } catch (IllegalArgumentException unused) {
            result.notImplemented();
        }
    }

    /* renamed from: com.pichillilorenzo.flutter_inappwebview_android.webview.WebViewChannelDelegate$8, reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods;

        static {
            int[] iArr = new int[WebViewChannelDelegateMethods.values().length];
            $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods = iArr;
            try {
                iArr[WebViewChannelDelegateMethods.getUrl.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getTitle.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getProgress.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.loadUrl.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.postUrl.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.loadData.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.loadFile.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.evaluateJavascript.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.injectJavascriptFileFromUrl.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.injectCSSCode.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.injectCSSFileFromUrl.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.reload.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.goBack.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.canGoBack.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.goForward.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.canGoForward.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.goBackOrForward.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.canGoBackOrForward.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.stopLoading.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.isLoading.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.takeScreenshot.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.setSettings.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getSettings.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.close.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.show.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.hide.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.isHidden.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getCopyBackForwardList.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.startSafeBrowsing.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.clearCache.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.clearSslPreferences.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.findAll.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.findNext.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.clearMatches.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.scrollTo.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.scrollBy.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.pause.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.resume.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.pauseTimers.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.resumeTimers.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.printCurrentPage.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getContentHeight.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getContentWidth.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.zoomBy.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getOriginalUrl.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getZoomScale.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getSelectedText.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getHitTestResult.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.pageDown.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.pageUp.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.saveWebArchive.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.zoomIn.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.zoomOut.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.clearFocus.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.setContextMenu.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.requestFocusNodeHref.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.requestImageRef.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getScrollX.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getScrollY.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.getCertificate.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.clearHistory.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.addUserScript.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.removeUserScript.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.removeUserScriptsByGroupName.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.removeAllUserScripts.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.callAsyncJavaScript.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.isSecureContext.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.createWebMessageChannel.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.postWebMessage.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.addWebMessageListener.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.canScrollVertically.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.canScrollHorizontally.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.isInFullscreen.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                $SwitchMap$com$pichillilorenzo$flutter_inappwebview_android$webview$WebViewChannelDelegateMethods[WebViewChannelDelegateMethods.clearFormData.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
        }
    }

    @Deprecated
    public void onFindResultReceived(int i, int i2, boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("activeMatchOrdinal", Integer.valueOf(i));
        hashMap.put("numberOfMatches", Integer.valueOf(i2));
        hashMap.put("isDoneCounting", Boolean.valueOf(z));
        channel.invokeMethod("onFindResultReceived", hashMap);
    }

    public void onLongPressHitTestResult(HitTestResult hitTestResult) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onLongPressHitTestResult", hitTestResult.toMap());
    }

    public void onScrollChanged(int i, int i2) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(ViewHierarchyNode.JsonKeys.X, Integer.valueOf(i));
        hashMap.put(ViewHierarchyNode.JsonKeys.Y, Integer.valueOf(i2));
        channel.invokeMethod("onScrollChanged", hashMap);
    }

    public void onDownloadStartRequest(DownloadStartRequest downloadStartRequest) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onDownloadStartRequest", downloadStartRequest.toMap());
    }

    public void onCreateContextMenu(HitTestResult hitTestResult) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onCreateContextMenu", hitTestResult.toMap());
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(ViewHierarchyNode.JsonKeys.X, Integer.valueOf(i));
        hashMap.put(ViewHierarchyNode.JsonKeys.Y, Integer.valueOf(i2));
        hashMap.put("clampedX", Boolean.valueOf(z));
        hashMap.put("clampedY", Boolean.valueOf(z2));
        channel.invokeMethod("onOverScrolled", hashMap);
    }

    public void onContextMenuActionItemClicked(int i, String str) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.valueOf(i));
        hashMap.put("androidId", Integer.valueOf(i));
        hashMap.put("iosId", null);
        hashMap.put("title", str);
        channel.invokeMethod("onContextMenuActionItemClicked", hashMap);
    }

    public void onHideContextMenu() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onHideContextMenu", new HashMap());
    }

    public void onEnterFullscreen() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onEnterFullscreen", new HashMap());
    }

    public void onExitFullscreen() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onExitFullscreen", new HashMap());
    }

    /* loaded from: classes5.dex */
    public static class JsAlertCallback extends BaseCallbackResultImpl<JsAlertResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public JsAlertResponse decodeResult(Object obj) {
            return JsAlertResponse.fromMap((Map) obj);
        }
    }

    public void onJsAlert(String str, String str2, Boolean bool, JsAlertCallback jsAlertCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            jsAlertCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("message", str2);
        hashMap.put("isMainFrame", bool);
        channel.invokeMethod("onJsAlert", hashMap, jsAlertCallback);
    }

    /* loaded from: classes5.dex */
    public static class JsConfirmCallback extends BaseCallbackResultImpl<JsConfirmResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public JsConfirmResponse decodeResult(Object obj) {
            return JsConfirmResponse.fromMap((Map) obj);
        }
    }

    public void onJsConfirm(String str, String str2, Boolean bool, JsConfirmCallback jsConfirmCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            jsConfirmCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("message", str2);
        hashMap.put("isMainFrame", bool);
        channel.invokeMethod("onJsConfirm", hashMap, jsConfirmCallback);
    }

    /* loaded from: classes5.dex */
    public static class JsPromptCallback extends BaseCallbackResultImpl<JsPromptResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public JsPromptResponse decodeResult(Object obj) {
            return JsPromptResponse.fromMap((Map) obj);
        }
    }

    public void onJsPrompt(String str, String str2, String str3, Boolean bool, JsPromptCallback jsPromptCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            jsPromptCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("message", str2);
        hashMap.put("defaultValue", str3);
        hashMap.put("isMainFrame", bool);
        channel.invokeMethod("onJsPrompt", hashMap, jsPromptCallback);
    }

    /* loaded from: classes5.dex */
    public static class JsBeforeUnloadCallback extends BaseCallbackResultImpl<JsBeforeUnloadResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public JsBeforeUnloadResponse decodeResult(Object obj) {
            return JsBeforeUnloadResponse.fromMap((Map) obj);
        }
    }

    public void onJsBeforeUnload(String str, String str2, JsBeforeUnloadCallback jsBeforeUnloadCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            jsBeforeUnloadCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("message", str2);
        channel.invokeMethod("onJsBeforeUnload", hashMap, jsBeforeUnloadCallback);
    }

    /* loaded from: classes5.dex */
    public static class CreateWindowCallback extends BaseCallbackResultImpl<Boolean> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public Boolean decodeResult(Object obj) {
            return Boolean.valueOf((obj instanceof Boolean) && ((Boolean) obj).booleanValue());
        }
    }

    public void onCreateWindow(CreateWindowAction createWindowAction, CreateWindowCallback createWindowCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            createWindowCallback.defaultBehaviour(null);
        } else {
            channel.invokeMethod("onCreateWindow", createWindowAction.toMap(), createWindowCallback);
        }
    }

    public void onCloseWindow() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onCloseWindow", new HashMap());
    }

    /* loaded from: classes5.dex */
    public static class GeolocationPermissionsShowPromptCallback extends BaseCallbackResultImpl<GeolocationPermissionShowPromptResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public GeolocationPermissionShowPromptResponse decodeResult(Object obj) {
            return GeolocationPermissionShowPromptResponse.fromMap((Map) obj);
        }
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsShowPromptCallback geolocationPermissionsShowPromptCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            geolocationPermissionsShowPromptCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("origin", str);
        channel.invokeMethod("onGeolocationPermissionsShowPrompt", hashMap, geolocationPermissionsShowPromptCallback);
    }

    public void onGeolocationPermissionsHidePrompt() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onGeolocationPermissionsHidePrompt", new HashMap());
    }

    public void onConsoleMessage(String str, int i) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", str);
        hashMap.put("messageLevel", Integer.valueOf(i));
        channel.invokeMethod("onConsoleMessage", hashMap);
    }

    public void onProgressChanged(int i) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("progress", Integer.valueOf(i));
        channel.invokeMethod("onProgressChanged", hashMap);
    }

    public void onTitleChanged(String str) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("title", str);
        channel.invokeMethod("onTitleChanged", hashMap);
    }

    public void onReceivedIcon(byte[] bArr) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("icon", bArr);
        channel.invokeMethod("onReceivedIcon", hashMap);
    }

    public void onReceivedTouchIconUrl(String str, boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("precomposed", Boolean.valueOf(z));
        channel.invokeMethod("onReceivedTouchIconUrl", hashMap);
    }

    /* loaded from: classes5.dex */
    public static class PermissionRequestCallback extends BaseCallbackResultImpl<PermissionResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public PermissionResponse decodeResult(Object obj) {
            return PermissionResponse.fromMap((Map) obj);
        }
    }

    public void onPermissionRequest(String str, List<String> list, Object obj, PermissionRequestCallback permissionRequestCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            permissionRequestCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("origin", str);
        hashMap.put("resources", list);
        hashMap.put(TypedValues.AttributesType.S_FRAME, obj);
        channel.invokeMethod("onPermissionRequest", hashMap, permissionRequestCallback);
    }

    public void onPermissionRequestCanceled(String str, List<String> list) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("origin", str);
        hashMap.put("resources", list);
        channel.invokeMethod("onPermissionRequestCanceled", hashMap);
    }

    /* loaded from: classes5.dex */
    public static class ShouldOverrideUrlLoadingCallback extends BaseCallbackResultImpl<NavigationActionPolicy> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public NavigationActionPolicy decodeResult(Object obj) {
            return NavigationActionPolicy.fromValue(Integer.valueOf(obj instanceof Integer ? ((Integer) obj).intValue() : NavigationActionPolicy.CANCEL.rawValue()).intValue());
        }
    }

    public void shouldOverrideUrlLoading(NavigationAction navigationAction, ShouldOverrideUrlLoadingCallback shouldOverrideUrlLoadingCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            shouldOverrideUrlLoadingCallback.defaultBehaviour(null);
        } else {
            channel.invokeMethod("shouldOverrideUrlLoading", navigationAction.toMap(), shouldOverrideUrlLoadingCallback);
        }
    }

    public void onLoadStart(String str) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        channel.invokeMethod("onLoadStart", hashMap);
    }

    public void onLoadStop(String str) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        channel.invokeMethod("onLoadStop", hashMap);
    }

    public void onUpdateVisitedHistory(String str, boolean z) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("isReload", Boolean.valueOf(z));
        channel.invokeMethod("onUpdateVisitedHistory", hashMap);
    }

    public void onReceivedError(WebResourceRequestExt webResourceRequestExt, WebResourceErrorExt webResourceErrorExt) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request", webResourceRequestExt.toMap());
        hashMap.put("error", webResourceErrorExt.toMap());
        channel.invokeMethod("onReceivedError", hashMap);
    }

    public void onReceivedHttpError(WebResourceRequestExt webResourceRequestExt, WebResourceResponseExt webResourceResponseExt) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request", webResourceRequestExt.toMap());
        hashMap.put("errorResponse", webResourceResponseExt.toMap());
        channel.invokeMethod("onReceivedHttpError", hashMap);
    }

    /* loaded from: classes5.dex */
    public static class ReceivedHttpAuthRequestCallback extends BaseCallbackResultImpl<HttpAuthResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public HttpAuthResponse decodeResult(Object obj) {
            return HttpAuthResponse.fromMap((Map) obj);
        }
    }

    public void onReceivedHttpAuthRequest(HttpAuthenticationChallenge httpAuthenticationChallenge, ReceivedHttpAuthRequestCallback receivedHttpAuthRequestCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            receivedHttpAuthRequestCallback.defaultBehaviour(null);
        } else {
            channel.invokeMethod("onReceivedHttpAuthRequest", httpAuthenticationChallenge.toMap(), receivedHttpAuthRequestCallback);
        }
    }

    /* loaded from: classes5.dex */
    public static class ReceivedServerTrustAuthRequestCallback extends BaseCallbackResultImpl<ServerTrustAuthResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public ServerTrustAuthResponse decodeResult(Object obj) {
            return ServerTrustAuthResponse.fromMap((Map) obj);
        }
    }

    public void onReceivedServerTrustAuthRequest(ServerTrustChallenge serverTrustChallenge, ReceivedServerTrustAuthRequestCallback receivedServerTrustAuthRequestCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            receivedServerTrustAuthRequestCallback.defaultBehaviour(null);
        } else {
            channel.invokeMethod("onReceivedServerTrustAuthRequest", serverTrustChallenge.toMap(), receivedServerTrustAuthRequestCallback);
        }
    }

    /* loaded from: classes5.dex */
    public static class ReceivedClientCertRequestCallback extends BaseCallbackResultImpl<ClientCertResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public ClientCertResponse decodeResult(Object obj) {
            return ClientCertResponse.fromMap((Map) obj);
        }
    }

    public void onReceivedClientCertRequest(ClientCertChallenge clientCertChallenge, ReceivedClientCertRequestCallback receivedClientCertRequestCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            receivedClientCertRequestCallback.defaultBehaviour(null);
        } else {
            channel.invokeMethod("onReceivedClientCertRequest", clientCertChallenge.toMap(), receivedClientCertRequestCallback);
        }
    }

    public void onZoomScaleChanged(float f, float f2) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("oldScale", Float.valueOf(f));
        hashMap.put("newScale", Float.valueOf(f2));
        channel.invokeMethod("onZoomScaleChanged", hashMap);
    }

    /* loaded from: classes5.dex */
    public static class SafeBrowsingHitCallback extends BaseCallbackResultImpl<SafeBrowsingResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public SafeBrowsingResponse decodeResult(Object obj) {
            return SafeBrowsingResponse.fromMap((Map) obj);
        }
    }

    public void onSafeBrowsingHit(String str, int i, SafeBrowsingHitCallback safeBrowsingHitCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            safeBrowsingHitCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("threatType", Integer.valueOf(i));
        channel.invokeMethod("onSafeBrowsingHit", hashMap, safeBrowsingHitCallback);
    }

    /* loaded from: classes5.dex */
    public static class FormResubmissionCallback extends BaseCallbackResultImpl<Integer> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public Integer decodeResult(Object obj) {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            return null;
        }
    }

    public void onFormResubmission(String str, FormResubmissionCallback formResubmissionCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            formResubmissionCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        channel.invokeMethod("onFormResubmission", hashMap, formResubmissionCallback);
    }

    public void onPageCommitVisible(String str) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        channel.invokeMethod("onPageCommitVisible", hashMap);
    }

    public void onRenderProcessGone(boolean z, int i) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("didCrash", Boolean.valueOf(z));
        hashMap.put("rendererPriorityAtExit", Integer.valueOf(i));
        channel.invokeMethod("onRenderProcessGone", hashMap);
    }

    public void onReceivedLoginRequest(String str, String str2, String str3) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_REALM, str);
        hashMap.put("account", str2);
        hashMap.put("args", str3);
        channel.invokeMethod("onReceivedLoginRequest", hashMap);
    }

    /* loaded from: classes5.dex */
    public static class LoadResourceWithCustomSchemeCallback extends BaseCallbackResultImpl<CustomSchemeResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public CustomSchemeResponse decodeResult(Object obj) {
            return CustomSchemeResponse.fromMap((Map) obj);
        }
    }

    public void onLoadResourceWithCustomScheme(WebResourceRequestExt webResourceRequestExt, LoadResourceWithCustomSchemeCallback loadResourceWithCustomSchemeCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            loadResourceWithCustomSchemeCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request", webResourceRequestExt.toMap());
        channel.invokeMethod("onLoadResourceWithCustomScheme", hashMap, loadResourceWithCustomSchemeCallback);
    }

    /* loaded from: classes5.dex */
    public static class SyncLoadResourceWithCustomSchemeCallback extends SyncBaseCallbackResultImpl<CustomSchemeResponse> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public CustomSchemeResponse decodeResult(Object obj) {
            return new LoadResourceWithCustomSchemeCallback().decodeResult(obj);
        }
    }

    public CustomSchemeResponse onLoadResourceWithCustomScheme(WebResourceRequestExt webResourceRequestExt) throws InterruptedException {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request", webResourceRequestExt.toMap());
        return (CustomSchemeResponse) Util.invokeMethodAndWaitResult(channel, "onLoadResourceWithCustomScheme", hashMap, new SyncLoadResourceWithCustomSchemeCallback());
    }

    /* loaded from: classes5.dex */
    public static class ShouldInterceptRequestCallback extends BaseCallbackResultImpl<WebResourceResponseExt> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public WebResourceResponseExt decodeResult(Object obj) {
            return WebResourceResponseExt.fromMap((Map) obj);
        }
    }

    public void shouldInterceptRequest(WebResourceRequestExt webResourceRequestExt, ShouldInterceptRequestCallback shouldInterceptRequestCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            shouldInterceptRequestCallback.defaultBehaviour(null);
        } else {
            channel.invokeMethod("shouldInterceptRequest", webResourceRequestExt.toMap(), shouldInterceptRequestCallback);
        }
    }

    /* loaded from: classes5.dex */
    public static class SyncShouldInterceptRequestCallback extends SyncBaseCallbackResultImpl<WebResourceResponseExt> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public WebResourceResponseExt decodeResult(Object obj) {
            return new ShouldInterceptRequestCallback().decodeResult(obj);
        }
    }

    public WebResourceResponseExt shouldInterceptRequest(WebResourceRequestExt webResourceRequestExt) throws InterruptedException {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return null;
        }
        return (WebResourceResponseExt) Util.invokeMethodAndWaitResult(channel, "shouldInterceptRequest", webResourceRequestExt.toMap(), new SyncShouldInterceptRequestCallback());
    }

    /* loaded from: classes5.dex */
    public static class RenderProcessUnresponsiveCallback extends BaseCallbackResultImpl<Integer> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public Integer decodeResult(Object obj) {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            return null;
        }
    }

    public void onRenderProcessUnresponsive(String str, RenderProcessUnresponsiveCallback renderProcessUnresponsiveCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            renderProcessUnresponsiveCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        channel.invokeMethod("onRenderProcessUnresponsive", hashMap, renderProcessUnresponsiveCallback);
    }

    /* loaded from: classes5.dex */
    public static class RenderProcessResponsiveCallback extends BaseCallbackResultImpl<Integer> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public Integer decodeResult(Object obj) {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            return null;
        }
    }

    public void onRenderProcessResponsive(String str, RenderProcessResponsiveCallback renderProcessResponsiveCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            renderProcessResponsiveCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        channel.invokeMethod("onRenderProcessResponsive", hashMap, renderProcessResponsiveCallback);
    }

    public void onCallJsHandler(String str, String str2, CallJsHandlerCallback callJsHandlerCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            callJsHandlerCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("handlerName", str);
        hashMap.put("args", str2);
        channel.invokeMethod("onCallJsHandler", hashMap, callJsHandlerCallback);
    }

    /* loaded from: classes5.dex */
    public static class PrintRequestCallback extends BaseCallbackResultImpl<Boolean> {
        @Override // com.pichillilorenzo.flutter_inappwebview_android.types.BaseCallbackResultImpl, com.pichillilorenzo.flutter_inappwebview_android.types.ICallbackResult
        public Boolean decodeResult(Object obj) {
            return Boolean.valueOf((obj instanceof Boolean) && ((Boolean) obj).booleanValue());
        }
    }

    public void onPrintRequest(String str, String str2, PrintRequestCallback printRequestCallback) {
        MethodChannel channel = getChannel();
        if (channel == null) {
            printRequestCallback.defaultBehaviour(null);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("printJobId", str2);
        channel.invokeMethod("onPrintRequest", hashMap, printRequestCallback);
    }

    public void onRequestFocus() {
        MethodChannel channel = getChannel();
        if (channel == null) {
            return;
        }
        channel.invokeMethod("onRequestFocus", new HashMap());
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.webView = null;
    }
}
