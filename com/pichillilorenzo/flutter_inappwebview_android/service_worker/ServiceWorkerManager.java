package com.pichillilorenzo.flutter_inappwebview_android.service_worker;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.webkit.ServiceWorkerClientCompat;
import androidx.webkit.ServiceWorkerControllerCompat;
import androidx.webkit.WebViewFeature;
import com.pichillilorenzo.flutter_inappwebview_android.InAppWebViewFlutterPlugin;
import com.pichillilorenzo.flutter_inappwebview_android.types.Disposable;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceRequestExt;
import com.pichillilorenzo.flutter_inappwebview_android.types.WebResourceResponseExt;
import io.flutter.plugin.common.MethodChannel;
import java.io.ByteArrayInputStream;
import java.util.Map;

/* loaded from: classes2.dex */
public class ServiceWorkerManager implements Disposable {
    protected static final String LOG_TAG = "ServiceWorkerManager";
    public static final String METHOD_CHANNEL_NAME = "com.pichillilorenzo/flutter_inappwebview_serviceworkercontroller";
    public static ServiceWorkerControllerCompat serviceWorkerController;
    public ServiceWorkerChannelDelegate channelDelegate;
    public InAppWebViewFlutterPlugin plugin;

    public ServiceWorkerManager(InAppWebViewFlutterPlugin inAppWebViewFlutterPlugin) {
        this.plugin = inAppWebViewFlutterPlugin;
        this.channelDelegate = new ServiceWorkerChannelDelegate(this, new MethodChannel(inAppWebViewFlutterPlugin.messenger, METHOD_CHANNEL_NAME));
    }

    public static void init() {
        if (serviceWorkerController == null && WebViewFeature.isFeatureSupported("SERVICE_WORKER_BASIC_USAGE")) {
            serviceWorkerController = ServiceWorkerControllerCompat.getInstance();
        }
    }

    public void setServiceWorkerClient(Boolean bool) {
        ServiceWorkerControllerCompat serviceWorkerControllerCompat = serviceWorkerController;
        if (serviceWorkerControllerCompat != null) {
            serviceWorkerControllerCompat.setServiceWorkerClient(bool.booleanValue() ? dummyServiceWorkerClientCompat() : new ServiceWorkerClientCompat() { // from class: com.pichillilorenzo.flutter_inappwebview_android.service_worker.ServiceWorkerManager.1
                @Override // androidx.webkit.ServiceWorkerClientCompat
                public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
                    WebResourceResponseExt shouldInterceptRequest;
                    WebResourceRequestExt fromWebResourceRequest = WebResourceRequestExt.fromWebResourceRequest(webResourceRequest);
                    if (ServiceWorkerManager.this.channelDelegate != null) {
                        try {
                            shouldInterceptRequest = ServiceWorkerManager.this.channelDelegate.shouldInterceptRequest(fromWebResourceRequest);
                        } catch (InterruptedException e) {
                            Log.e(ServiceWorkerManager.LOG_TAG, "", e);
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
                    if (statusCode != null && reasonPhrase != null) {
                        return new WebResourceResponse(contentType, contentEncoding, statusCode.intValue(), reasonPhrase, headers, byteArrayInputStream);
                    }
                    return new WebResourceResponse(contentType, contentEncoding, byteArrayInputStream);
                }
            });
        }
    }

    private ServiceWorkerClientCompat dummyServiceWorkerClientCompat() {
        return DummyServiceWorkerClientCompat.INSTANCE;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.Disposable
    public void dispose() {
        ServiceWorkerChannelDelegate serviceWorkerChannelDelegate = this.channelDelegate;
        if (serviceWorkerChannelDelegate != null) {
            serviceWorkerChannelDelegate.dispose();
            this.channelDelegate = null;
        }
        this.plugin = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class DummyServiceWorkerClientCompat extends ServiceWorkerClientCompat {
        static final ServiceWorkerClientCompat INSTANCE = new DummyServiceWorkerClientCompat();

        @Override // androidx.webkit.ServiceWorkerClientCompat
        public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
            return null;
        }

        private DummyServiceWorkerClientCompat() {
        }
    }
}
