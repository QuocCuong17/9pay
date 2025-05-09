package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes5.dex */
public class CustomSchemeResponse {
    private String contentEncoding;
    private String contentType;
    private byte[] data;

    public CustomSchemeResponse(byte[] bArr, String str, String str2) {
        this.data = bArr;
        this.contentType = str;
        this.contentEncoding = str2;
    }

    public static CustomSchemeResponse fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new CustomSchemeResponse((byte[]) map.get("data"), (String) map.get("contentType"), (String) map.get("contentEncoding"));
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomSchemeResponse customSchemeResponse = (CustomSchemeResponse) obj;
        if (Arrays.equals(this.data, customSchemeResponse.data) && this.contentType.equals(customSchemeResponse.contentType)) {
            return this.contentEncoding.equals(customSchemeResponse.contentEncoding);
        }
        return false;
    }

    public int hashCode() {
        return (((Arrays.hashCode(this.data) * 31) + this.contentType.hashCode()) * 31) + this.contentEncoding.hashCode();
    }

    public String toString() {
        return "CustomSchemeResponse{data=" + Arrays.toString(this.data) + ", contentType='" + this.contentType + "', contentEncoding='" + this.contentEncoding + "'}";
    }
}
