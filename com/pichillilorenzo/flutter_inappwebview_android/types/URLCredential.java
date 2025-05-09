package com.pichillilorenzo.flutter_inappwebview_android.types;

import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLCredentialContract;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class URLCredential {

    /* renamed from: id, reason: collision with root package name */
    private Long f106id;
    private String password;
    private Long protectionSpaceId;
    private String username;

    public URLCredential(String str, String str2) {
        this.username = str;
        this.password = str2;
    }

    public URLCredential(Long l, String str, String str2, Long l2) {
        this.f106id = l;
        this.username = str;
        this.password = str2;
        this.protectionSpaceId = l2;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("username", this.username);
        hashMap.put(URLCredentialContract.FeedEntry.COLUMN_NAME_PASSWORD, this.password);
        hashMap.put("certificates", null);
        hashMap.put("persistence", null);
        return hashMap;
    }

    public Long getId() {
        return this.f106id;
    }

    public void setId(Long l) {
        this.f106id = l;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public Long getProtectionSpaceId() {
        return this.protectionSpaceId;
    }

    public void setProtectionSpaceId(Long l) {
        this.protectionSpaceId = l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URLCredential uRLCredential = (URLCredential) obj;
        String str = this.username;
        if (str == null ? uRLCredential.username != null : !str.equals(uRLCredential.username)) {
            return false;
        }
        String str2 = this.password;
        String str3 = uRLCredential.password;
        return str2 != null ? str2.equals(str3) : str3 == null;
    }

    public int hashCode() {
        String str = this.username;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.password;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "URLCredential{username='" + this.username + "', password='" + this.password + "'}";
    }
}
