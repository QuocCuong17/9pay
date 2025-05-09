package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class ClientCertResponse {
    private Integer action;
    private String certificatePassword;
    private String certificatePath;
    private String keyStoreType;

    public ClientCertResponse(String str, String str2, String str3, Integer num) {
        this.certificatePath = str;
        this.certificatePassword = str2;
        this.keyStoreType = str3;
        this.action = num;
    }

    public static ClientCertResponse fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new ClientCertResponse((String) map.get("certificatePath"), (String) map.get("certificatePassword"), (String) map.get("keyStoreType"), (Integer) map.get("action"));
    }

    public String getCertificatePath() {
        return this.certificatePath;
    }

    public void setCertificatePath(String str) {
        this.certificatePath = str;
    }

    public String getCertificatePassword() {
        return this.certificatePassword;
    }

    public void setCertificatePassword(String str) {
        this.certificatePassword = str;
    }

    public String getKeyStoreType() {
        return this.keyStoreType;
    }

    public void setKeyStoreType(String str) {
        this.keyStoreType = str;
    }

    public Integer getAction() {
        return this.action;
    }

    public void setAction(Integer num) {
        this.action = num;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClientCertResponse clientCertResponse = (ClientCertResponse) obj;
        if (!this.certificatePath.equals(clientCertResponse.certificatePath)) {
            return false;
        }
        String str = this.certificatePassword;
        if (str == null ? clientCertResponse.certificatePassword != null : !str.equals(clientCertResponse.certificatePassword)) {
            return false;
        }
        if (!this.keyStoreType.equals(clientCertResponse.keyStoreType)) {
            return false;
        }
        Integer num = this.action;
        Integer num2 = clientCertResponse.action;
        return num != null ? num.equals(num2) : num2 == null;
    }

    public int hashCode() {
        int hashCode = this.certificatePath.hashCode() * 31;
        String str = this.certificatePassword;
        int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.keyStoreType.hashCode()) * 31;
        Integer num = this.action;
        return hashCode2 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "ClientCertResponse{certificatePath='" + this.certificatePath + "', certificatePassword='" + this.certificatePassword + "', keyStoreType='" + this.keyStoreType + "', action=" + this.action + '}';
    }
}
