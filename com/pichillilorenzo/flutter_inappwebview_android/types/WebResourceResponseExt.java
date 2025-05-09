package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.os.Build;
import android.webkit.WebResourceResponse;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import com.pichillilorenzo.flutter_inappwebview_android.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebResourceResponseExt {
    private String contentEncoding;
    private String contentType;
    private byte[] data;
    private Map<String, String> headers;
    private String reasonPhrase;
    private Integer statusCode;

    public WebResourceResponseExt(String str, String str2, Integer num, String str3, Map<String, String> map, byte[] bArr) {
        this.contentType = str;
        this.contentEncoding = str2;
        this.statusCode = num;
        this.reasonPhrase = str3;
        this.headers = map;
        this.data = bArr;
    }

    public static WebResourceResponseExt fromWebResourceResponse(WebResourceResponse webResourceResponse) {
        Integer num;
        String str;
        Map<String, String> map;
        if (Build.VERSION.SDK_INT >= 21) {
            Integer valueOf = Integer.valueOf(webResourceResponse.getStatusCode());
            str = webResourceResponse.getReasonPhrase();
            num = valueOf;
            map = webResourceResponse.getResponseHeaders();
        } else {
            num = null;
            str = null;
            map = null;
        }
        return new WebResourceResponseExt(webResourceResponse.getMimeType(), webResourceResponse.getEncoding(), num, str, map, Util.readAllBytes(webResourceResponse.getData()));
    }

    public static WebResourceResponseExt fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new WebResourceResponseExt((String) map.get("contentType"), (String) map.get("contentEncoding"), (Integer) map.get(Keys.STATUS_CODE), (String) map.get("reasonPhrase"), (Map) map.get("headers"), (byte[]) map.get("data"));
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("contentType", this.contentType);
        hashMap.put("contentEncoding", this.contentEncoding);
        hashMap.put(Keys.STATUS_CODE, this.statusCode);
        hashMap.put("reasonPhrase", this.reasonPhrase);
        hashMap.put("headers", this.headers);
        hashMap.put("data", this.data);
        return hashMap;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(String str) {
        this.contentEncoding = str;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public void setReasonPhrase(String str) {
        this.reasonPhrase = str;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebResourceResponseExt webResourceResponseExt = (WebResourceResponseExt) obj;
        String str = this.contentType;
        if (str == null ? webResourceResponseExt.contentType != null : !str.equals(webResourceResponseExt.contentType)) {
            return false;
        }
        String str2 = this.contentEncoding;
        if (str2 == null ? webResourceResponseExt.contentEncoding != null : !str2.equals(webResourceResponseExt.contentEncoding)) {
            return false;
        }
        Integer num = this.statusCode;
        if (num == null ? webResourceResponseExt.statusCode != null : !num.equals(webResourceResponseExt.statusCode)) {
            return false;
        }
        String str3 = this.reasonPhrase;
        if (str3 == null ? webResourceResponseExt.reasonPhrase != null : !str3.equals(webResourceResponseExt.reasonPhrase)) {
            return false;
        }
        Map<String, String> map = this.headers;
        if (map == null ? webResourceResponseExt.headers == null : map.equals(webResourceResponseExt.headers)) {
            return Arrays.equals(this.data, webResourceResponseExt.data);
        }
        return false;
    }

    public int hashCode() {
        String str = this.contentType;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.contentEncoding;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.statusCode;
        int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        String str3 = this.reasonPhrase;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Map<String, String> map = this.headers;
        return ((hashCode4 + (map != null ? map.hashCode() : 0)) * 31) + Arrays.hashCode(this.data);
    }

    public String toString() {
        return "WebResourceResponseExt{contentType='" + this.contentType + "', contentEncoding='" + this.contentEncoding + "', statusCode=" + this.statusCode + ", reasonPhrase='" + this.reasonPhrase + "', headers=" + this.headers + ", data=" + Arrays.toString(this.data) + '}';
    }
}
