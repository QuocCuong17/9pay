package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DownloadStartRequest {
    private String contentDisposition;
    private long contentLength;
    private String mimeType;
    private String suggestedFilename;
    private String textEncodingName;
    private String url;
    private String userAgent;

    public DownloadStartRequest(String str, String str2, String str3, String str4, long j, String str5, String str6) {
        this.url = str;
        this.userAgent = str2;
        this.contentDisposition = str3;
        this.mimeType = str4;
        this.contentLength = j;
        this.suggestedFilename = str5;
        this.textEncodingName = str6;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("url", this.url);
        hashMap.put("userAgent", this.userAgent);
        hashMap.put("contentDisposition", this.contentDisposition);
        hashMap.put("mimeType", this.mimeType);
        hashMap.put("contentLength", Long.valueOf(this.contentLength));
        hashMap.put("suggestedFilename", this.suggestedFilename);
        hashMap.put("textEncodingName", this.textEncodingName);
        return hashMap;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public String getContentDisposition() {
        return this.contentDisposition;
    }

    public void setContentDisposition(String str) {
        this.contentDisposition = str;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(long j) {
        this.contentLength = j;
    }

    public String getSuggestedFilename() {
        return this.suggestedFilename;
    }

    public void setSuggestedFilename(String str) {
        this.suggestedFilename = str;
    }

    public String getTextEncodingName() {
        return this.textEncodingName;
    }

    public void setTextEncodingName(String str) {
        this.textEncodingName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DownloadStartRequest downloadStartRequest = (DownloadStartRequest) obj;
        if (this.contentLength != downloadStartRequest.contentLength || !this.url.equals(downloadStartRequest.url) || !this.userAgent.equals(downloadStartRequest.userAgent) || !this.contentDisposition.equals(downloadStartRequest.contentDisposition) || !this.mimeType.equals(downloadStartRequest.mimeType)) {
            return false;
        }
        String str = this.suggestedFilename;
        if (str == null ? downloadStartRequest.suggestedFilename != null : !str.equals(downloadStartRequest.suggestedFilename)) {
            return false;
        }
        String str2 = this.textEncodingName;
        String str3 = downloadStartRequest.textEncodingName;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    public int hashCode() {
        int hashCode = ((((((this.url.hashCode() * 31) + this.userAgent.hashCode()) * 31) + this.contentDisposition.hashCode()) * 31) + this.mimeType.hashCode()) * 31;
        long j = this.contentLength;
        int i = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        String str = this.suggestedFilename;
        int hashCode2 = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.textEncodingName;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "DownloadStartRequest{url='" + this.url + "', userAgent='" + this.userAgent + "', contentDisposition='" + this.contentDisposition + "', mimeType='" + this.mimeType + "', contentLength=" + this.contentLength + ", suggestedFilename='" + this.suggestedFilename + "', textEncodingName='" + this.textEncodingName + "'}";
    }
}
