package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class GeolocationPermissionShowPromptResponse {
    boolean allow;
    private String origin;
    boolean retain;

    public GeolocationPermissionShowPromptResponse(String str, boolean z, boolean z2) {
        this.origin = str;
        this.allow = z;
        this.retain = z2;
    }

    public static GeolocationPermissionShowPromptResponse fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new GeolocationPermissionShowPromptResponse((String) map.get("origin"), ((Boolean) map.get("allow")).booleanValue(), ((Boolean) map.get("retain")).booleanValue());
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public boolean isAllow() {
        return this.allow;
    }

    public void setAllow(boolean z) {
        this.allow = z;
    }

    public boolean isRetain() {
        return this.retain;
    }

    public void setRetain(boolean z) {
        this.retain = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeolocationPermissionShowPromptResponse geolocationPermissionShowPromptResponse = (GeolocationPermissionShowPromptResponse) obj;
        if (this.allow == geolocationPermissionShowPromptResponse.allow && this.retain == geolocationPermissionShowPromptResponse.retain) {
            return this.origin.equals(geolocationPermissionShowPromptResponse.origin);
        }
        return false;
    }

    public int hashCode() {
        return (((this.origin.hashCode() * 31) + (this.allow ? 1 : 0)) * 31) + (this.retain ? 1 : 0);
    }

    public String toString() {
        return "GeolocationPermissionShowPromptResponse{origin='" + this.origin + "', allow=" + this.allow + ", retain=" + this.retain + '}';
    }
}
