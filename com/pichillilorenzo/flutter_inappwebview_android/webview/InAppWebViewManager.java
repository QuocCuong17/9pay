package com.pichillilorenzo.flutter_inappwebview_android.webview;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.FlutterWebView;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class InAppWebViewManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "InAppWebViewManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_manager";
    public final Map<String, FlutterWebView> keepAliveWebViews;
    public InAppWebViewFlutterPlugin plugin;
    public int windowAutoincrementId;
    public final Map<Integer, Message> windowWebViewMessages;

    public InAppWebViewManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.keepAliveWebViews = new HashMap();
        this.windowWebViewMessages = new HashMap();
        this.windowAutoincrementId = 0;
        this.plugin = inAppWebViewFlutterPlugin;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0094, code lost:
    
        if (r0.equals("disableWebView") == false) goto L4;
     */
    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Context context;
        String str = methodCall.method;
        str.hashCode();
        char c = 0;
        switch (str.hashCode()) {
            case -1496477679:
                break;
            case -910403233:
                if (str.equals("setWebContentsDebuggingEnabled")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -633518365:
                if (str.equals("getVariationsHeader")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -436220260:
                if (str.equals("clearClientCertPreferences")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 258673215:
                if (str.equals("getSafeBrowsingPrivacyPolicyUrl")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 426229521:
                if (str.equals("setSafeBrowsingAllowlist")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 643643439:
                if (str.equals("getDefaultUserAgent")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1033609166:
                if (str.equals("clearAllCache")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1586319888:
                if (str.equals("getCurrentWebViewPackage")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1667434977:
                if (str.equals("isMultiProcessEnabled")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1867011305:
                if (str.equals("disposeKeepAlive")) {
                    c = '\n';
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
                if (Build.VERSION.SDK_INT >= 28) {
                    WebView.disableWebView();
                }
                result.success(true);
                return;
            case 1:
                boolean booleanValue = ((Boolean) methodCall.argument("debuggingEnabled")).booleanValue();
                if (Build.VERSION.SDK_INT >= 19) {
                    WebView.setWebContentsDebuggingEnabled(booleanValue);
                }
                result.success(true);
                return;
            case 2:
                if (WebViewFeature.isFeatureSupported("GET_VARIATIONS_HEADER")) {
                    result.success(WebViewCompat.getVariationsHeader());
                    return;
                } else {
                    result.success(null);
                    return;
                }
            case 3:
                if (Build.VERSION.SDK_INT >= 21) {
                    WebView.clearClientCertPreferences(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewManager.1
                        @Override // java.lang.Runnable
                        public void run() {
                            result.success(true);
                        }
                    });
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case 4:
                if (WebViewFeature.isFeatureSupported("SAFE_BROWSING_PRIVACY_POLICY_URL")) {
                    result.success(WebViewCompat.getSafeBrowsingPrivacyPolicyUrl().toString());
                    return;
                } else {
                    result.success(null);
                    return;
                }
            case 5:
                if (WebViewFeature.isFeatureSupported("SAFE_BROWSING_ALLOWLIST")) {
                    WebViewCompat.setSafeBrowsingAllowlist(new HashSet((List) methodCall.argument("hosts")), new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewManager.2
                        @Override // android.webkit.ValueCallback
                        public void onReceiveValue(Boolean bool) {
                            result.success(bool);
                        }
                    });
                    return;
                } else if (WebViewFeature.isFeatureSupported("SAFE_BROWSING_WHITELIST")) {
                    WebViewCompat.setSafeBrowsingWhitelist((List) methodCall.argument("hosts"), new ValueCallback<Boolean>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewManager.3
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
            case 6:
                InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin = this.plugin;
                if (inAppWebViewFlutterPlugin != null) {
                    result.success(WebSettings.getDefaultUserAgent(inAppWebViewFlutterPlugin.applicationContext));
                    return;
                } else {
                    result.success(null);
                    return;
                }
            case 7:
                InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin2 = this.plugin;
                if (inAppWebViewFlutterPlugin2 != null) {
                    Context context2 = inAppWebViewFlutterPlugin2.activity;
                    if (context2 == null) {
                        context2 = this.plugin.applicationContext;
                    }
                    if (context2 != null) {
                        clearAllCache(context2, ((Boolean) methodCall.argument("includeDiskFiles")).booleanValue());
                    }
                }
                result.success(true);
                return;
            case '\b':
                InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin3 = this.plugin;
                if (inAppWebViewFlutterPlugin3 != null) {
                    context = inAppWebViewFlutterPlugin3.activity;
                    if (context == null) {
                        context = this.plugin.applicationContext;
                    }
                } else {
                    context = null;
                }
                PackageInfo currentWebViewPackage = context != null ? WebViewCompat.getCurrentWebViewPackage(context) : null;
                result.success(currentWebViewPackage != null ? convertWebViewPackageToMap(currentWebViewPackage) : null);
                return;
            case '\t':
                if (WebViewFeature.isFeatureSupported(WebViewFeature.MULTI_PROCESS)) {
                    result.success(Boolean.valueOf(WebViewCompat.isMultiProcessEnabled()));
                    return;
                } else {
                    result.success(false);
                    return;
                }
            case '\n':
                String str2 = (String) methodCall.argument("keepAliveId");
                if (str2 != null) {
                    disposeKeepAlive(str2);
                }
                result.success(true);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    public Map<String, Object> convertWebViewPackageToMap(PackageInfo packageInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("versionName", packageInfo.versionName);
        hashMap.put("packageName", packageInfo.packageName);
        return hashMap;
    }

    public void disposeKeepAlive(String str) {
        ViewGroup viewGroup;
        FlutterWebView flutterWebView = this.keepAliveWebViews.get(str);
        if (flutterWebView != null) {
            flutterWebView.keepAliveId = null;
            View view = flutterWebView.getView();
            if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
                viewGroup.removeView(view);
            }
            flutterWebView.dispose();
        }
        if (this.keepAliveWebViews.containsKey(str)) {
            this.keepAliveWebViews.put(str, null);
        }
    }

    public void clearAllCache(Context context, boolean z) {
        WebView webView = new WebView(context);
        webView.clearCache(z);
        webView.destroy();
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        for (FlutterWebView flutterWebView : this.keepAliveWebViews.values()) {
            if (flutterWebView.keepAliveId != null) {
                disposeKeepAlive(flutterWebView.keepAliveId);
            }
        }
        this.keepAliveWebViews.clear();
        this.windowWebViewMessages.clear();
        this.plugin = null;
    }
}
