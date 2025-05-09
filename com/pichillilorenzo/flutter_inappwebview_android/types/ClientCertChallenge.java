package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes5.dex */
public class ClientCertChallenge extends URLAuthenticationChallenge {
    private String[] keyTypes;
    private Principal[] principals;

    public ClientCertChallenge(URLProtectionSpace uRLProtectionSpace, Principal[] principalArr, String[] strArr) {
        super(uRLProtectionSpace);
        this.principals = principalArr;
        this.keyTypes = strArr;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.URLAuthenticationChallenge
    public Map<String, Object> toMap() {
        ArrayList arrayList;
        if (this.principals != null) {
            arrayList = new ArrayList();
            for (Principal principal : this.principals) {
                arrayList.add(principal.getName());
            }
        } else {
            arrayList = null;
        }
        Map<String, Object> map = super.toMap();
        map.put("principals", arrayList);
        String[] strArr = this.keyTypes;
        map.put("keyTypes", strArr != null ? Arrays.asList(strArr) : null);
        return map;
    }

    public Principal[] getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(Principal[] principalArr) {
        this.principals = principalArr;
    }

    public String[] getKeyTypes() {
        return this.keyTypes;
    }

    public void setKeyTypes(String[] strArr) {
        this.keyTypes = strArr;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.URLAuthenticationChallenge
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        ClientCertChallenge clientCertChallenge = (ClientCertChallenge) obj;
        if (Arrays.equals(this.principals, clientCertChallenge.principals)) {
            return Arrays.equals(this.keyTypes, clientCertChallenge.keyTypes);
        }
        return false;
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.URLAuthenticationChallenge
    public int hashCode() {
        return (((super.hashCode() * 31) + Arrays.hashCode(this.principals)) * 31) + Arrays.hashCode(this.keyTypes);
    }

    @Override // com.pichillilorenzo.flutter_inappwebview_android.types.URLAuthenticationChallenge
    public String toString() {
        return "ClientCertChallenge{principals=" + Arrays.toString(this.principals) + ", keyTypes=" + Arrays.toString(this.keyTypes) + "} " + super.toString();
    }
}
