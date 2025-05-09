package io.flutter.plugins.webviewflutter;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;

/* loaded from: classes5.dex */
public class WebChromeClientFlutterApiImpl extends GeneratedAndroidWebView.WebChromeClientFlutterApi {
    private final InstanceManager instanceManager;

    public WebChromeClientFlutterApiImpl(BinaryMessenger binaryMessenger, InstanceManager instanceManager) {
        super(binaryMessenger);
        this.instanceManager = instanceManager;
    }

    public void onProgressChanged(WebChromeClient webChromeClient, WebView webView, Long l, GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        super.onProgressChanged(Long.valueOf(getIdentifierForClient(webChromeClient)), identifierForStrongReference, l, reply);
    }

    public void dispose(WebChromeClient webChromeClient, GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply<Void> reply) {
        if (this.instanceManager.containsInstance(webChromeClient)) {
            dispose(Long.valueOf(getIdentifierForClient(webChromeClient)), reply);
        } else {
            reply.reply(null);
        }
    }

    private long getIdentifierForClient(WebChromeClient webChromeClient) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webChromeClient);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebChromeClient.");
        }
        return identifierForStrongReference.longValue();
    }
}
