package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.os.Build;
import android.webkit.WebResourceRequest;
import androidx.webkit.WebResourceRequestCompat;
import androidx.webkit.WebViewFeature;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebResourceRequestExt {
    private boolean hasGesture;
    private Map<String, String> headers;
    private boolean isForMainFrame;
    private boolean isRedirect;
    private String method;
    private String url;

    public WebResourceRequestExt(String str, Map<String, String> map, boolean z, boolean z2, boolean z3, String str2) {
        this.url = str;
        this.headers = map;
        this.isRedirect = z;
        this.hasGesture = z2;
        this.isForMainFrame = z3;
        this.method = str2;
    }

    public static WebResourceRequestExt fromWebResourceRequest(WebResourceRequest webResourceRequest) {
        boolean isRedirect;
        if (WebViewFeature.isFeatureSupported("WEB_RESOURCE_REQUEST_IS_REDIRECT")) {
            isRedirect = WebResourceRequestCompat.isRedirect(webResourceRequest);
        } else {
            isRedirect = Build.VERSION.SDK_INT >= 24 ? webResourceRequest.isRedirect() : false;
        }
        return new WebResourceRequestExt(webResourceRequest.getUrl().toString(), webResourceRequest.getRequestHeaders(), isRedirect, webResourceRequest.hasGesture(), webResourceRequest.isForMainFrame(), webResourceRequest.getMethod());
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("url", this.url);
        hashMap.put("headers", this.headers);
        hashMap.put("isRedirect", Boolean.valueOf(this.isRedirect));
        hashMap.put("hasGesture", Boolean.valueOf(this.hasGesture));
        hashMap.put("isForMainFrame", Boolean.valueOf(this.isForMainFrame));
        hashMap.put("method", this.method);
        return hashMap;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public boolean isRedirect() {
        return this.isRedirect;
    }

    public void setRedirect(boolean z) {
        this.isRedirect = z;
    }

    public boolean isHasGesture() {
        return this.hasGesture;
    }

    public void setHasGesture(boolean z) {
        this.hasGesture = z;
    }

    public boolean isForMainFrame() {
        return this.isForMainFrame;
    }

    public void setForMainFrame(boolean z) {
        this.isForMainFrame = z;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebResourceRequestExt webResourceRequestExt = (WebResourceRequestExt) obj;
        if (this.isRedirect != webResourceRequestExt.isRedirect || this.hasGesture != webResourceRequestExt.hasGesture || this.isForMainFrame != webResourceRequestExt.isForMainFrame || !this.url.equals(webResourceRequestExt.url)) {
            return false;
        }
        Map<String, String> map = this.headers;
        if (map == null ? webResourceRequestExt.headers != null : !map.equals(webResourceRequestExt.headers)) {
            return false;
        }
        String str = this.method;
        String str2 = webResourceRequestExt.method;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int hashCode = this.url.hashCode() * 31;
        Map<String, String> map = this.headers;
        int hashCode2 = (((((((hashCode + (map != null ? map.hashCode() : 0)) * 31) + (this.isRedirect ? 1 : 0)) * 31) + (this.hasGesture ? 1 : 0)) * 31) + (this.isForMainFrame ? 1 : 0)) * 31;
        String str = this.method;
        return hashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "WebResourceRequestExt{url=" + this.url + ", headers=" + this.headers + ", isRedirect=" + this.isRedirect + ", hasGesture=" + this.hasGesture + ", isForMainFrame=" + this.isForMainFrame + ", method='" + this.method + "'}";
    }
}
