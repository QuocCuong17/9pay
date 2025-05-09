package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class ServerTrustAuthResponse {
    private Integer action;

    public ServerTrustAuthResponse(Integer num) {
        this.action = num;
    }

    public static ServerTrustAuthResponse fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new ServerTrustAuthResponse((Integer) map.get("action"));
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
        Integer num = this.action;
        Integer num2 = ((ServerTrustAuthResponse) obj).action;
        return num != null ? num.equals(num2) : num2 == null;
    }

    public int hashCode() {
        Integer num = this.action;
        if (num != null) {
            return num.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ServerTrustAuthResponse{action=" + this.action + '}';
    }
}
