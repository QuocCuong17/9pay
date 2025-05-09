package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.text.TextUtils;
import android.webkit.ValueCallback;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import com.pichillilorenzo.flutter_inappwebview_android.plugin_scripts_js.JavaScriptBridgeJS;
import com.pichillilorenzo.flutter_inappwebview_android.webview.InAppWebViewInterface;
import com.pichillilorenzo.flutter_inappwebview_android.webview.web_message.WebMessageChannel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class WebMessagePort {
    public String name;
    public WebMessageChannel webMessageChannel;
    public boolean isClosed = false;
    public boolean isTransferred = false;
    public boolean isStarted = false;

    public WebMessagePort(String str, WebMessageChannel webMessageChannel) {
        this.name = str;
        this.webMessageChannel = webMessageChannel;
    }

    public void setWebMessageCallback(final ValueCallback<Void> valueCallback) throws Exception {
        if (this.isClosed || this.isTransferred) {
            throw new Exception("Port is already closed or transferred");
        }
        this.isStarted = true;
        WebMessageChannel webMessageChannel = this.webMessageChannel;
        InAppWebViewInterface inAppWebViewInterface = (webMessageChannel == null || webMessageChannel.webView == null) ? null : this.webMessageChannel.webView;
        if (inAppWebViewInterface == null) {
            if (valueCallback != null) {
                valueCallback.onReceiveValue(null);
                return;
            }
            return;
        }
        inAppWebViewInterface.evaluateJavascript("(function() {  var webMessageChannel = window.flutter_inappwebview._webMessageChannels['" + this.webMessageChannel.f110id + "'];  if (webMessageChannel != null) {      webMessageChannel." + this.name + ".onmessage = function (event) {          window." + JavaScriptBridgeJS.JAVASCRIPT_BRIDGE_NAME + ".callHandler('onWebMessagePortMessageReceived', {              'webMessageChannelId': '" + this.webMessageChannel.f110id + "',              'index': " + (1 ^ (this.name.equals("port1") ? 1 : 0)) + ",              'message': event.data          });      }  }})();", null, new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePort.1
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str) {
                ValueCallback valueCallback2 = valueCallback;
                if (valueCallback2 != null) {
                    valueCallback2.onReceiveValue(null);
                }
            }
        });
    }

    public void postMessage(WebMessage webMessage, final ValueCallback<Void> valueCallback) throws Exception {
        String str;
        if (this.isClosed || this.isTransferred) {
            throw new Exception("Port is already closed or transferred");
        }
        WebMessageChannel webMessageChannel = this.webMessageChannel;
        InAppWebViewInterface inAppWebViewInterface = (webMessageChannel == null || webMessageChannel.webView == null) ? null : this.webMessageChannel.webView;
        if (inAppWebViewInterface != null) {
            List<WebMessagePort> list = webMessage.ports;
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (WebMessagePort webMessagePort : list) {
                    if (webMessagePort == this) {
                        throw new Exception("Source port cannot be transferred");
                    }
                    if (webMessagePort.isStarted) {
                        throw new Exception("Port is already started");
                    }
                    if (webMessagePort.isClosed || webMessagePort.isTransferred) {
                        throw new Exception("Port is already closed or transferred");
                    }
                    webMessagePort.isTransferred = true;
                    arrayList.add("window.flutter_inappwebview._webMessageChannels['" + this.webMessageChannel.f110id + "']." + webMessagePort.name);
                }
                str = "[" + TextUtils.join(", ", arrayList) + "]";
            } else {
                str = "null";
            }
            inAppWebViewInterface.evaluateJavascript("(function() {  var webMessageChannel = window.flutter_inappwebview._webMessageChannels['" + this.webMessageChannel.f110id + "'];  if (webMessageChannel != null) {      webMessageChannel." + this.name + ".postMessage('" + (webMessage.data != null ? Util.replaceAll(webMessage.data, "'", "\\'") : "null") + "', " + str + ");  }})();", null, new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePort.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(String str2) {
                    valueCallback.onReceiveValue(null);
                }
            });
        } else {
            valueCallback.onReceiveValue(null);
        }
        webMessage.dispose();
    }

    public void close(final ValueCallback<Void> valueCallback) throws Exception {
        if (this.isTransferred) {
            throw new Exception("Port is already transferred");
        }
        this.isClosed = true;
        WebMessageChannel webMessageChannel = this.webMessageChannel;
        InAppWebViewInterface inAppWebViewInterface = (webMessageChannel == null || webMessageChannel.webView == null) ? null : this.webMessageChannel.webView;
        if (inAppWebViewInterface != null) {
            inAppWebViewInterface.evaluateJavascript("(function() {  var webMessageChannel = window.flutter_inappwebview._webMessageChannels['" + this.webMessageChannel.f110id + "'];  if (webMessageChannel != null) {      webMessageChannel." + this.name + ".close();  }})();", null, new ValueCallback<String>() { // from class: com.pichillilorenzo.flutter_inappwebview_android.types.WebMessagePort.3
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(String str) {
                    valueCallback.onReceiveValue(null);
                }
            });
            return;
        }
        valueCallback.onReceiveValue(null);
    }

    public void dispose() {
        this.isClosed = true;
        this.webMessageChannel = null;
    }
}
