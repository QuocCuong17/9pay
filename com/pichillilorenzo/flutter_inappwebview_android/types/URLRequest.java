package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class URLRequest {
    private byte[] body;
    private Map<String, String> headers;
    private String method;
    private String url;

    public URLRequest(String str, String str2, byte[] bArr, Map<String, String> map) {
        this.url = str;
        this.method = str2;
        this.body = bArr;
        this.headers = map;
    }

    public static URLRequest fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        String str = (String) map.get("url");
        if (str == null) {
            str = "about:blank";
        }
        return new URLRequest(str, (String) map.get("method"), (byte[]) map.get("body"), (Map) map.get("headers"));
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("url", this.url);
        hashMap.put("method", this.method);
        hashMap.put("headers", this.headers);
        hashMap.put("body", this.body);
        hashMap.put("allowsCellularAccess", null);
        hashMap.put("allowsConstrainedNetworkAccess", null);
        hashMap.put("allowsExpensiveNetworkAccess", null);
        hashMap.put("cachePolicy", null);
        hashMap.put("httpShouldHandleCookies", null);
        hashMap.put("httpShouldUsePipelining", null);
        hashMap.put("networkServiceType", null);
        hashMap.put("timeoutInterval", null);
        hashMap.put("mainDocumentURL", null);
        hashMap.put("assumesHTTP3Capable", null);
        hashMap.put("attribution", null);
        return hashMap;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public byte[] getBody() {
        return this.body;
    }

    public void setBody(byte[] bArr) {
        this.body = bArr;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URLRequest uRLRequest = (URLRequest) obj;
        String str = this.url;
        if (str == null ? uRLRequest.url != null : !str.equals(uRLRequest.url)) {
            return false;
        }
        String str2 = this.method;
        if (str2 == null ? uRLRequest.method != null : !str2.equals(uRLRequest.method)) {
            return false;
        }
        if (!Arrays.equals(this.body, uRLRequest.body)) {
            return false;
        }
        Map<String, String> map = this.headers;
        Map<String, String> map2 = uRLRequest.headers;
        return map != null ? map.equals(map2) : map2 == null;
    }

    public int hashCode() {
        String str = this.url;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.method;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Arrays.hashCode(this.body)) * 31;
        Map<String, String> map = this.headers;
        return hashCode2 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "URLRequest{url='" + this.url + "', method='" + this.method + "', body=" + Arrays.toString(this.body) + ", headers=" + this.headers + '}';
    }
}
