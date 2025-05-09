package io.flutter.plugins.webviewflutter;

import android.os.Build;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.WebResourceErrorCompat;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebViewClientFlutterApiImpl extends GeneratedAndroidWebView.WebViewClientFlutterApi {
    private final InstanceManager instanceManager;

    static GeneratedAndroidWebView.WebResourceErrorData createWebResourceErrorData(WebResourceError webResourceError) {
        return new GeneratedAndroidWebView.WebResourceErrorData.Builder().setErrorCode(Long.valueOf(webResourceError.getErrorCode())).setDescription(webResourceError.getDescription().toString()).build();
    }

    static GeneratedAndroidWebView.WebResourceErrorData createWebResourceErrorData(WebResourceErrorCompat webResourceErrorCompat) {
        return new GeneratedAndroidWebView.WebResourceErrorData.Builder().setErrorCode(Long.valueOf(webResourceErrorCompat.getErrorCode())).setDescription(webResourceErrorCompat.getDescription().toString()).build();
    }

    static GeneratedAndroidWebView.WebResourceRequestData createWebResourceRequestData(WebResourceRequest webResourceRequest) {
        Map<String, String> hashMap;
        GeneratedAndroidWebView.WebResourceRequestData.Builder method = new GeneratedAndroidWebView.WebResourceRequestData.Builder().setUrl(webResourceRequest.getUrl().toString()).setIsForMainFrame(Boolean.valueOf(webResourceRequest.isForMainFrame())).setHasGesture(Boolean.valueOf(webResourceRequest.hasGesture())).setMethod(webResourceRequest.getMethod());
        if (webResourceRequest.getRequestHeaders() != null) {
            hashMap = webResourceRequest.getRequestHeaders();
        } else {
            hashMap = new HashMap<>();
        }
        GeneratedAndroidWebView.WebResourceRequestData.Builder requestHeaders = method.setRequestHeaders(hashMap);
        if (Build.VERSION.SDK_INT >= 24) {
            requestHeaders.setIsRedirect(Boolean.valueOf(webResourceRequest.isRedirect()));
        }
        return requestHeaders.build();
    }

    public WebViewClientFlutterApiImpl(BinaryMessenger binaryMessenger, InstanceManager instanceManager) {
        super(binaryMessenger);
        this.instanceManager = instanceManager;
    }

    public void onPageStarted(WebViewClient webViewClient, WebView webView, String str, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        onPageStarted(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, str, reply);
    }

    public void onPageFinished(WebViewClient webViewClient, WebView webView, String str, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        onPageFinished(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, str, reply);
    }

    public void onReceivedRequestError(WebViewClient webViewClient, WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        onReceivedRequestError(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, createWebResourceRequestData(webResourceRequest), createWebResourceErrorData(webResourceError), reply);
    }

    public void onReceivedRequestError(WebViewClient webViewClient, WebView webView, WebResourceRequest webResourceRequest, WebResourceErrorCompat webResourceErrorCompat, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        onReceivedRequestError(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, createWebResourceRequestData(webResourceRequest), createWebResourceErrorData(webResourceErrorCompat), reply);
    }

    public void onReceivedError(WebViewClient webViewClient, WebView webView, Long l, String str, String str2, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        onReceivedError(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, l, str, str2, reply);
    }

    public void requestLoading(WebViewClient webViewClient, WebView webView, WebResourceRequest webResourceRequest, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        requestLoading(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, createWebResourceRequestData(webResourceRequest), reply);
    }

    public void urlLoading(WebViewClient webViewClient, WebView webView, String str, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebView.");
        }
        urlLoading(Long.valueOf(getIdentifierForClient(webViewClient)), identifierForStrongReference, str, reply);
    }

    public void dispose(WebViewClient webViewClient, GeneratedAndroidWebView.WebViewClientFlutterApi.Reply<Void> reply) {
        if (this.instanceManager.containsInstance(webViewClient)) {
            dispose(Long.valueOf(getIdentifierForClient(webViewClient)), reply);
        } else {
            reply.reply(null);
        }
    }

    private long getIdentifierForClient(WebViewClient webViewClient) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webViewClient);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebViewClient.");
        }
        return identifierForStrongReference.longValue();
    }
}
