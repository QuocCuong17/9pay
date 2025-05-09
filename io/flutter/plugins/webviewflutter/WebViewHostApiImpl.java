package io.flutter.plugins.webviewflutter;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.View;
import android.view.ViewParent;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugins.webviewflutter.DownloadListenerHostApiImpl;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl;
import io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class WebViewHostApiImpl implements GeneratedAndroidWebView.WebViewHostApi {
    private final View containerView;
    private Context context;
    private final InstanceManager instanceManager;
    private final WebViewProxy webViewProxy;

    /* loaded from: classes5.dex */
    public static class WebViewProxy {
        public WebViewPlatformView createWebView(Context context) {
            return new WebViewPlatformView(context);
        }

        public InputAwareWebViewPlatformView createInputAwareWebView(Context context, View view) {
            return new InputAwareWebViewPlatformView(context, view);
        }

        public void setWebContentsDebuggingEnabled(boolean z) {
            WebView.setWebContentsDebuggingEnabled(z);
        }
    }

    /* loaded from: classes5.dex */
    private static class ReleasableValue<T extends Releasable> {
        private T value;

        ReleasableValue() {
        }

        ReleasableValue(T t) {
            this.value = t;
        }

        void set(T t) {
            release();
            this.value = t;
        }

        T get() {
            return this.value;
        }

        void release() {
            T t = this.value;
            if (t != null) {
                t.release();
            }
            this.value = null;
        }
    }

    /* loaded from: classes5.dex */
    public static class WebViewPlatformView extends WebView implements PlatformView, Releasable {
        private final ReleasableValue<DownloadListenerHostApiImpl.DownloadListenerImpl> currentDownloadListener;
        private final ReleasableValue<WebChromeClientHostApiImpl.WebChromeClientImpl> currentWebChromeClient;
        private final ReleasableValue<WebViewClientHostApiImpl.ReleasableWebViewClient> currentWebViewClient;
        private final Map<String, ReleasableValue<JavaScriptChannel>> javaScriptInterfaces;

        @Override // io.flutter.plugin.platform.PlatformView
        public View getView() {
            return this;
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onFlutterViewAttached(View view) {
            PlatformView.CC.$default$onFlutterViewAttached(this, view);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onFlutterViewDetached() {
            PlatformView.CC.$default$onFlutterViewDetached(this);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onInputConnectionLocked() {
            PlatformView.CC.$default$onInputConnectionLocked(this);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onInputConnectionUnlocked() {
            PlatformView.CC.$default$onInputConnectionUnlocked(this);
        }

        public WebViewPlatformView(Context context) {
            super(context);
            this.currentWebViewClient = new ReleasableValue<>();
            this.currentDownloadListener = new ReleasableValue<>();
            this.currentWebChromeClient = new ReleasableValue<>();
            this.javaScriptInterfaces = new HashMap();
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void dispose() {
            destroy();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.currentWebViewClient.set((WebViewClientHostApiImpl.ReleasableWebViewClient) webViewClient);
            WebChromeClientHostApiImpl.WebChromeClientImpl webChromeClientImpl = this.currentWebChromeClient.get();
            if (webChromeClientImpl != null) {
                webChromeClientImpl.setWebViewClient(webViewClient);
            }
        }

        @Override // android.webkit.WebView
        public void setDownloadListener(DownloadListener downloadListener) {
            super.setDownloadListener(downloadListener);
            this.currentDownloadListener.set((DownloadListenerHostApiImpl.DownloadListenerImpl) downloadListener);
        }

        @Override // android.webkit.WebView
        public void setWebChromeClient(WebChromeClient webChromeClient) {
            super.setWebChromeClient(webChromeClient);
            this.currentWebChromeClient.set((WebChromeClientHostApiImpl.WebChromeClientImpl) webChromeClient);
        }

        @Override // android.webkit.WebView
        public void addJavascriptInterface(Object obj, String str) {
            super.addJavascriptInterface(obj, str);
            if (obj instanceof JavaScriptChannel) {
                ReleasableValue<JavaScriptChannel> releasableValue = this.javaScriptInterfaces.get(str);
                if (releasableValue != null && releasableValue.get() != obj) {
                    releasableValue.release();
                }
                this.javaScriptInterfaces.put(str, new ReleasableValue<>((JavaScriptChannel) obj));
            }
        }

        @Override // android.webkit.WebView
        public void removeJavascriptInterface(String str) {
            super.removeJavascriptInterface(str);
            this.javaScriptInterfaces.get(str).release();
            this.javaScriptInterfaces.remove(str);
        }

        @Override // io.flutter.plugins.webviewflutter.Releasable
        public void release() {
            this.currentWebViewClient.release();
            this.currentDownloadListener.release();
            this.currentWebChromeClient.release();
            Iterator<ReleasableValue<JavaScriptChannel>> it = this.javaScriptInterfaces.values().iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.javaScriptInterfaces.clear();
        }
    }

    /* loaded from: classes5.dex */
    public static class InputAwareWebViewPlatformView extends InputAwareWebView implements PlatformView, Releasable {
        private final ReleasableValue<DownloadListenerHostApiImpl.DownloadListenerImpl> currentDownloadListener;
        private final ReleasableValue<WebChromeClientHostApiImpl.WebChromeClientImpl> currentWebChromeClient;
        private final ReleasableValue<WebViewClientHostApiImpl.ReleasableWebViewClient> currentWebViewClient;
        private final Map<String, ReleasableValue<JavaScriptChannel>> javaScriptInterfaces;

        @Override // io.flutter.plugin.platform.PlatformView
        public View getView() {
            return this;
        }

        @Override // io.flutter.plugins.webviewflutter.InputAwareWebView, android.view.View
        public /* bridge */ /* synthetic */ boolean checkInputConnectionProxy(View view) {
            return super.checkInputConnectionProxy(view);
        }

        @Override // io.flutter.plugins.webviewflutter.InputAwareWebView, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ void clearFocus() {
            super.clearFocus();
        }

        public InputAwareWebViewPlatformView(Context context, View view) {
            super(context, view);
            this.currentWebViewClient = new ReleasableValue<>();
            this.currentDownloadListener = new ReleasableValue<>();
            this.currentWebChromeClient = new ReleasableValue<>();
            this.javaScriptInterfaces = new HashMap();
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onFlutterViewAttached(View view) {
            setContainerView(view);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onFlutterViewDetached() {
            setContainerView(null);
        }

        @Override // io.flutter.plugins.webviewflutter.InputAwareWebView, io.flutter.plugin.platform.PlatformView
        public void dispose() {
            super.dispose();
            destroy();
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onInputConnectionLocked() {
            lockInputConnection();
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onInputConnectionUnlocked() {
            unlockInputConnection();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.currentWebViewClient.set((WebViewClientHostApiImpl.ReleasableWebViewClient) webViewClient);
            WebChromeClientHostApiImpl.WebChromeClientImpl webChromeClientImpl = this.currentWebChromeClient.get();
            if (webChromeClientImpl != null) {
                webChromeClientImpl.setWebViewClient(webViewClient);
            }
        }

        @Override // android.webkit.WebView
        public void setDownloadListener(DownloadListener downloadListener) {
            super.setDownloadListener(downloadListener);
            this.currentDownloadListener.set((DownloadListenerHostApiImpl.DownloadListenerImpl) downloadListener);
        }

        @Override // android.webkit.WebView
        public void setWebChromeClient(WebChromeClient webChromeClient) {
            super.setWebChromeClient(webChromeClient);
            this.currentWebChromeClient.set((WebChromeClientHostApiImpl.WebChromeClientImpl) webChromeClient);
        }

        @Override // android.webkit.WebView
        public void addJavascriptInterface(Object obj, String str) {
            super.addJavascriptInterface(obj, str);
            if (obj instanceof JavaScriptChannel) {
                ReleasableValue<JavaScriptChannel> releasableValue = this.javaScriptInterfaces.get(str);
                if (releasableValue != null && releasableValue.get() != obj) {
                    releasableValue.release();
                }
                this.javaScriptInterfaces.put(str, new ReleasableValue<>((JavaScriptChannel) obj));
            }
        }

        @Override // android.webkit.WebView
        public void removeJavascriptInterface(String str) {
            super.removeJavascriptInterface(str);
            this.javaScriptInterfaces.get(str).release();
            this.javaScriptInterfaces.remove(str);
        }

        @Override // io.flutter.plugins.webviewflutter.Releasable
        public void release() {
            this.currentWebViewClient.release();
            this.currentDownloadListener.release();
            this.currentWebChromeClient.release();
            Iterator<ReleasableValue<JavaScriptChannel>> it = this.javaScriptInterfaces.values().iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.javaScriptInterfaces.clear();
        }
    }

    public WebViewHostApiImpl(InstanceManager instanceManager, WebViewProxy webViewProxy, Context context, View view) {
        this.instanceManager = instanceManager;
        this.webViewProxy = webViewProxy;
        this.context = context;
        this.containerView = view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void create(Long l, Boolean bool) {
        Object createInputAwareWebView;
        DisplayListenerProxy displayListenerProxy = new DisplayListenerProxy();
        DisplayManager displayManager = (DisplayManager) this.context.getSystemService("display");
        displayListenerProxy.onPreWebViewInitialization(displayManager);
        if (bool.booleanValue()) {
            createInputAwareWebView = this.webViewProxy.createWebView(this.context);
        } else {
            createInputAwareWebView = this.webViewProxy.createInputAwareWebView(this.context, this.containerView);
        }
        displayListenerProxy.onPostWebViewInitialization(displayManager);
        this.instanceManager.addDartCreatedInstance(createInputAwareWebView, l.longValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void dispose(Long l) {
        ViewParent viewParent = (WebView) this.instanceManager.getInstance(l.longValue());
        if (viewParent != null) {
            ((Releasable) viewParent).release();
            this.instanceManager.remove(l.longValue());
        }
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void loadData(Long l, String str, String str2, String str3) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).loadData(str, str2, str3);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void loadDataWithBaseUrl(Long l, String str, String str2, String str3, String str4, String str5) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void loadUrl(Long l, String str, Map<String, String> map) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).loadUrl(str, map);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void postUrl(Long l, String str, byte[] bArr) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).postUrl(str, bArr);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public String getUrl(Long l) {
        return ((WebView) this.instanceManager.getInstance(l.longValue())).getUrl();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Boolean canGoBack(Long l) {
        return Boolean.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).canGoBack());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Boolean canGoForward(Long l) {
        return Boolean.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).canGoForward());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void goBack(Long l) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).goBack();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void goForward(Long l) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).goForward();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void reload(Long l) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).reload();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void clearCache(Long l, Boolean bool) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).clearCache(bool.booleanValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void evaluateJavascript(Long l, String str, final GeneratedAndroidWebView.Result<String> result) {
        WebView webView = (WebView) this.instanceManager.getInstance(l.longValue());
        Objects.requireNonNull(result);
        webView.evaluateJavascript(str, new ValueCallback() { // from class: io.flutter.plugins.webviewflutter.WebViewHostApiImpl$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                GeneratedAndroidWebView.Result.this.success((String) obj);
            }
        });
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public String getTitle(Long l) {
        return ((WebView) this.instanceManager.getInstance(l.longValue())).getTitle();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void scrollTo(Long l, Long l2, Long l3) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).scrollTo(l2.intValue(), l3.intValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void scrollBy(Long l, Long l2, Long l3) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).scrollBy(l2.intValue(), l3.intValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Long getScrollX(Long l) {
        return Long.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).getScrollX());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Long getScrollY(Long l) {
        return Long.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).getScrollY());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public GeneratedAndroidWebView.WebViewPoint getScrollPosition(Long l) {
        Objects.requireNonNull((WebView) this.instanceManager.getInstance(l.longValue()));
        return new GeneratedAndroidWebView.WebViewPoint.Builder().setX(Long.valueOf(r4.getScrollX())).setY(Long.valueOf(r4.getScrollY())).build();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setWebContentsDebuggingEnabled(Boolean bool) {
        this.webViewProxy.setWebContentsDebuggingEnabled(bool.booleanValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setWebViewClient(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setWebViewClient((WebViewClient) this.instanceManager.getInstance(l2.longValue()));
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void addJavaScriptChannel(Long l, Long l2) {
        WebView webView = (WebView) this.instanceManager.getInstance(l.longValue());
        JavaScriptChannel javaScriptChannel = (JavaScriptChannel) this.instanceManager.getInstance(l2.longValue());
        webView.addJavascriptInterface(javaScriptChannel, javaScriptChannel.javaScriptChannelName);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void removeJavaScriptChannel(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).removeJavascriptInterface(((JavaScriptChannel) this.instanceManager.getInstance(l2.longValue())).javaScriptChannelName);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setDownloadListener(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setDownloadListener((DownloadListener) this.instanceManager.getInstance(l2.longValue()));
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setWebChromeClient(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setWebChromeClient((WebChromeClient) this.instanceManager.getInstance(l2.longValue()));
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setBackgroundColor(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setBackgroundColor(l2.intValue());
    }

    public InstanceManager getInstanceManager() {
        return this.instanceManager;
    }
}
