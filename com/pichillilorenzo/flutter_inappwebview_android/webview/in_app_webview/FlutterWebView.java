package com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.find_interaction.FindInteractionController;
import com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh.PullToRefreshLayout;
import com.pichillilorenzo.flutter_inappwebview_android.pull_to_refresh.PullToRefreshSettings;
import com.pichillilorenzo.flutter_inappwebview_android.types.URLRequest;
import com.pichillilorenzo.flutter_inappwebview_android.types.UserScript;
import com.pichillilorenzo.flutter_inappwebview_android.webview.PlatformWebView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class FlutterWebView implements PlatformWebView {
    static final String LOG_TAG = "IAWFlutterWebView";
    public String keepAliveId;
    public PullToRefreshLayout pullToRefreshLayout;
    public InAppWebView webView;

    public FlutterWebView(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin, Context context, Object obj, HashMap<String, Object> hashMap) {
        DisplayListenerProxy displayListenerProxy = new DisplayListenerProxy();
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        displayListenerProxy.onPreWebViewInitialization(displayManager);
        this.keepAliveId = (String) hashMap.get("keepAliveId");
        Map<String, Object> map = (Map) hashMap.get("initialSettings");
        Map map2 = (Map) hashMap.get("contextMenu");
        Integer num = (Integer) hashMap.get("windowId");
        List list = (List) hashMap.get("initialUserScripts");
        Map<String, Object> map3 = (Map) hashMap.get("pullToRefreshSettings");
        InAppWebViewSettings inAppWebViewSettings = new InAppWebViewSettings();
        inAppWebViewSettings.parse2(map);
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(UserScript.fromMap((Map) it.next()));
            }
        }
        this.webView = new InAppWebView(context, inAppWebViewFlutterPlugin, obj, num, inAppWebViewSettings, map2, inAppWebViewSettings.useHybridComposition.booleanValue() ? null : inAppWebViewFlutterPlugin.flutterView, arrayList);
        displayListenerProxy.onPostWebViewInitialization(displayManager);
        this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        PullToRefreshSettings pullToRefreshSettings = new PullToRefreshSettings();
        pullToRefreshSettings.parse2(map3);
        PullToRefreshLayout pullToRefreshLayout = new PullToRefreshLayout(context, inAppWebViewFlutterPlugin, obj, pullToRefreshSettings);
        this.pullToRefreshLayout = pullToRefreshLayout;
        pullToRefreshLayout.addView(this.webView);
        this.pullToRefreshLayout.prepare();
        FindInteractionController findInteractionController = new FindInteractionController(this.webView, inAppWebViewFlutterPlugin, obj, null);
        this.webView.findInteractionController = findInteractionController;
        findInteractionController.prepare();
        this.webView.prepare();
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public View getView() {
        PullToRefreshLayout pullToRefreshLayout = this.pullToRefreshLayout;
        return pullToRefreshLayout != null ? pullToRefreshLayout : this.webView;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.webview.PlatformWebView
    public void makeInitialLoad(HashMap<String, Object> hashMap) {
        URLRequest fromMap;
        Message message;
        if (this.webView == null) {
            return;
        }
        Integer num = (Integer) hashMap.get("windowId");
        Map map = (Map) hashMap.get("initialUrlRequest");
        String str = (String) hashMap.get("initialFile");
        Map map2 = (Map) hashMap.get("initialData");
        if (num != null) {
            if (this.webView.plugin == null || this.webView.plugin.inAppWebViewManager == null || (message = this.webView.plugin.inAppWebViewManager.windowWebViewMessages.get(num)) == null) {
                return;
            }
            ((WebView.WebViewTransport) message.obj).setWebView(this.webView);
            message.sendToTarget();
            if (WebViewFeature.isFeatureSupported(WebViewFeature.DOCUMENT_START_SCRIPT)) {
                this.webView.post(new Runnable() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.FlutterWebView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (FlutterWebView.this.webView != null) {
                            FlutterWebView.this.webView.prepareAndAddUserScripts();
                        }
                    }
                });
                return;
            }
            return;
        }
        if (str != null) {
            try {
                this.webView.loadFile(str);
                return;
            } catch (IOException e) {
                Log.e(LOG_TAG, str + " asset file cannot be found!", e);
                return;
            }
        }
        if (map2 != null) {
            this.webView.loadDataWithBaseURL((String) map2.get("baseUrl"), (String) map2.get("data"), (String) map2.get("mimeType"), (String) map2.get("encoding"), (String) map2.get("historyUrl"));
            return;
        }
        if (map == null || (fromMap = URLRequest.fromMap(map)) == null) {
            return;
        }
        this.webView.loadUrl(fromMap);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void dispose() {
        InAppWebView inAppWebView;
        if (this.keepAliveId != null || (inAppWebView = this.webView) == null) {
            return;
        }
        inAppWebView.dispose();
        this.webView = null;
        PullToRefreshLayout pullToRefreshLayout = this.pullToRefreshLayout;
        if (pullToRefreshLayout != null) {
            pullToRefreshLayout.dispose();
            this.pullToRefreshLayout = null;
        }
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onInputConnectionLocked() {
        InAppWebView inAppWebView = this.webView;
        if (inAppWebView == null || inAppWebView.inAppBrowserDelegate != null || this.webView.customSettings.useHybridComposition.booleanValue()) {
            return;
        }
        this.webView.lockInputConnection();
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onInputConnectionUnlocked() {
        InAppWebView inAppWebView = this.webView;
        if (inAppWebView == null || inAppWebView.inAppBrowserDelegate != null || this.webView.customSettings.useHybridComposition.booleanValue()) {
            return;
        }
        this.webView.unlockInputConnection();
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onFlutterViewAttached(View view) {
        InAppWebView inAppWebView = this.webView;
        if (inAppWebView == null || inAppWebView.customSettings.useHybridComposition.booleanValue()) {
            return;
        }
        this.webView.setContainerView(view);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onFlutterViewDetached() {
        InAppWebView inAppWebView = this.webView;
        if (inAppWebView == null || inAppWebView.customSettings.useHybridComposition.booleanValue()) {
            return;
        }
        this.webView.setContainerView(null);
    }
}
