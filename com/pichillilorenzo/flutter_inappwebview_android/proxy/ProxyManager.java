package com.pichillilorenzo.flutter_inappwebview_android.proxy;

import androidx.webkit.ProxyConfig;
import androidx.webkit.ProxyController;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl;
import com.pichillilorenzo.flutter_inappwebview_android.types.ProxyRuleExt;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class ProxyManager extends ChannelDelegateImpl {
    protected static final String LOG_TAG = "ProxyManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_proxycontroller";
    public static ProxyController proxyController;
    public InAppWebViewFlutterPlugin plugin;

    public ProxyManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        super(new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
        this.plugin = inAppWebViewFlutterPlugin;
    }

    public static void init() {
        if (proxyController == null && WebViewFeature.isFeatureSupported(WebViewFeature.PROXY_OVERRIDE)) {
            proxyController = ProxyController.getInstance();
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        init();
        String str = methodCall.method;
        str.hashCode();
        if (str.equals("clearProxyOverride")) {
            if (proxyController != null) {
                clearProxyOverride(result);
                return;
            } else {
                result.success(false);
                return;
            }
        }
        if (str.equals("setProxyOverride")) {
            if (proxyController != null) {
                HashMap hashMap = (HashMap) methodCall.argument("settings");
                ProxySettings proxySettings = new ProxySettings();
                if (hashMap != null) {
                    proxySettings.parse2((Map<String, Object>) hashMap);
                }
                setProxyOverride(proxySettings, result);
                return;
            }
            result.success(false);
            return;
        }
        result.notImplemented();
    }

    private void setProxyOverride(ProxySettings proxySettings, final MethodChannel.Result result) {
        if (proxyController != null) {
            ProxyConfig.Builder builder = new ProxyConfig.Builder();
            Iterator<String> it = proxySettings.bypassRules.iterator();
            while (it.hasNext()) {
                builder.addBypassRule(it.next());
            }
            Iterator<String> it2 = proxySettings.directs.iterator();
            while (it2.hasNext()) {
                builder.addDirect(it2.next());
            }
            for (ProxyRuleExt proxyRuleExt : proxySettings.proxyRules) {
                if (proxyRuleExt.getSchemeFilter() != null) {
                    builder.addProxyRule(proxyRuleExt.getUrl(), proxyRuleExt.getSchemeFilter());
                } else {
                    builder.addProxyRule(proxyRuleExt.getUrl());
                }
            }
            if (proxySettings.bypassSimpleHostnames != null && proxySettings.bypassSimpleHostnames.booleanValue()) {
                builder.bypassSimpleHostnames();
            }
            if (proxySettings.removeImplicitRules != null && proxySettings.removeImplicitRules.booleanValue()) {
                builder.removeImplicitRules();
            }
            if (proxySettings.reverseBypassEnabled != null && WebViewFeature.isFeatureSupported("PROXY_OVERRIDE_REVERSE_BYPASS")) {
                builder.setReverseBypassEnabled(proxySettings.reverseBypassEnabled.booleanValue());
            }
            proxyController.setProxyOverride(builder.build(), new Executor() { // from class: com.pichillilorenzo.flutter_inappwebview_android.proxy.ProxyManager.1
                @Override // java.util.concurrent.Executor
                public void execute(Runnable runnable) {
                    runnable.run();
                }
            }, new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.proxy.ProxyManager.2
                @Override // java.lang.Runnable
                public void run() {
                    result.success(true);
                }
            });
        }
    }

    private void clearProxyOverride(final MethodChannel.Result result) {
        ProxyController proxyController2 = proxyController;
        if (proxyController2 != null) {
            proxyController2.clearProxyOverride(new Executor() { // from class: com.pichillilorenzo.flutter_inappwebview_android.proxy.ProxyManager.3
                @Override // java.util.concurrent.Executor
                public void execute(Runnable runnable) {
                    runnable.run();
                }
            }, new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.proxy.ProxyManager.4
                @Override // java.lang.Runnable
                public void run() {
                    result.success(true);
                }
            });
        }
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.ChannelDelegateImpl, com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        super.dispose();
        this.plugin = null;
    }
}
