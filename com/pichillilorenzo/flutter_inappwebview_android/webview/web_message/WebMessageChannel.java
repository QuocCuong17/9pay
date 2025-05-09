package com.pichillilorenzo.flutter_inappwebview_android.webview.web_message;

import android.webkit.ValueCallback;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessageCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePort;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePortCompatExt;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface;
import com.pichillilorenzo.flutter_inappwebview_android.webview.in_app_webview.InAppWebView;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebMessageChannel implements Disposable {
    protected static final String LOG_TAG = "WebMessageChannel";
    public static final String METHOD_CHANNEL_NAME_PREFIX = "com.pichillilorenzo/flutter_inappwebview_web_message_channel_";
    public WebMessageChannelChannelDelegate channelDelegate;
    public final List<WebMessagePortCompat> compatPorts;

    /* renamed from: id, reason: collision with root package name */
    public String f110id;
    public final List<WebMessagePort> ports;
    public InAppWebViewInterface webView;

    public WebMessageChannel(String str, InAppWebViewInterface inAppWebViewInterface) {
        this.f110id = str;
        this.channelDelegate = new WebMessageChannelChannelDelegate(this, new MethodChannel(inAppWebViewInterface.getPlugin().messenger, METHOD_CHANNEL_NAME_PREFIX + str));
        if (inAppWebViewInterface instanceof InAppWebView) {
            this.compatPorts = new ArrayList(Arrays.asList(WebViewCompat.createWebMessageChannel((InAppWebView) inAppWebViewInterface)));
            this.ports = new ArrayList();
        } else {
            this.ports = Arrays.asList(new WebMessagePort("port1", this), new WebMessagePort("port2", this));
            this.compatPorts = new ArrayList();
        }
        this.webView = inAppWebViewInterface;
    }

    public void initJsInstance(InAppWebViewInterface inAppWebViewInterface, final ValueCallback<WebMessageChannel> valueCallback) {
        if (inAppWebViewInterface != null) {
            inAppWebViewInterface.evaluateJavascript("(function() {window.flutter_inappwebview._webMessageChannels['" + this.f110id + "'] = new MessageChannel();})();", null, new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageChannel.1
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(String str) {
                    valueCallback.onReceiveValue(this);
                }
            });
            return;
        }
        valueCallback.onReceiveValue(this);
    }

    public void setWebMessageCallbackForInAppWebView(final int i, MethodChannel.Result result) {
        if (this.webView != null && this.compatPorts.size() > 0 && WebViewFeature.isFeatureSupported("WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK")) {
            try {
                this.compatPorts.get(i).setWebMessageCallback(new WebMessagePortCompat.WebMessageCallbackCompat() { // from class: com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageChannel.2
                    @Override // androidx.webkit.WebMessagePortCompat.WebMessageCallbackCompat
                    public void onMessage(WebMessagePortCompat webMessagePortCompat, WebMessageCompat webMessageCompat) {
                        super.onMessage(webMessagePortCompat, webMessageCompat);
                        this.onMessage(i, webMessageCompat != null ? WebMessageCompatExt.fromMapWebMessageCompat(webMessageCompat) : null);
                    }
                });
                result.success(true);
                return;
            } catch (Exception e) {
                result.error(LOG_TAG, e.getMessage(), null);
                return;
            }
        }
        result.success(true);
    }

    public void postMessageForInAppWebView(Integer num, WebMessageCompatExt webMessageCompatExt, MethodChannel.Result result) {
        if (this.webView != null && this.compatPorts.size() > 0 && WebViewFeature.isFeatureSupported("WEB_MESSAGE_PORT_POST_MESSAGE")) {
            WebMessagePortCompat webMessagePortCompat = this.compatPorts.get(num.intValue());
            ArrayList arrayList = new ArrayList();
            List<WebMessagePortCompatExt> ports = webMessageCompatExt.getPorts();
            if (ports != null) {
                for (WebMessagePortCompatExt webMessagePortCompatExt : ports) {
                    WebMessageChannel webMessageChannel = this.webView.getWebMessageChannels().get(webMessagePortCompatExt.getWebMessageChannelId());
                    if (webMessageChannel != null) {
                        arrayList.add(webMessageChannel.compatPorts.get(webMessagePortCompatExt.getIndex()));
                    }
                }
            }
            Object data = webMessageCompatExt.getData();
            try {
                if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_ARRAY_BUFFER") && data != null && webMessageCompatExt.getType() == 1) {
                    webMessagePortCompat.postMessage(new WebMessageCompat((byte[]) data, (WebMessagePortCompat[]) arrayList.toArray(new WebMessagePortCompat[0])));
                } else {
                    webMessagePortCompat.postMessage(new WebMessageCompat(data != null ? data.toString() : null, (WebMessagePortCompat[]) arrayList.toArray(new WebMessagePortCompat[0])));
                }
                result.success(true);
                return;
            } catch (Exception e) {
                result.error(LOG_TAG, e.getMessage(), null);
                return;
            }
        }
        result.success(true);
    }

    public void closeForInAppWebView(Integer num, MethodChannel.Result result) {
        if (this.webView != null && this.compatPorts.size() > 0 && WebViewFeature.isFeatureSupported("WEB_MESSAGE_PORT_CLOSE")) {
            try {
                this.compatPorts.get(num.intValue()).close();
                result.success(true);
                return;
            } catch (Exception e) {
                result.error(LOG_TAG, e.getMessage(), null);
                return;
            }
        }
        result.success(true);
    }

    public void onMessage(int i, WebMessageCompatExt webMessageCompatExt) {
        WebMessageChannelChannelDelegate webMessageChannelChannelDelegate = this.channelDelegate;
        if (webMessageChannelChannelDelegate != null) {
            webMessageChannelChannelDelegate.onMessage(i, webMessageCompatExt);
        }
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.f110id);
        return hashMap;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_PORT_CLOSE")) {
            Iterator<WebMessagePortCompat> it = this.compatPorts.iterator();
            while (it.hasNext()) {
                try {
                    it.next().close();
                } catch (Exception unused) {
                }
            }
        }
        WebMessageChannelChannelDelegate webMessageChannelChannelDelegate = this.channelDelegate;
        if (webMessageChannelChannelDelegate != null) {
            webMessageChannelChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        this.compatPorts.clear();
        this.webView = null;
    }
}
