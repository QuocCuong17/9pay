package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class SafeBrowsingResponse {
    private Integer action;
    private boolean report;

    public SafeBrowsingResponse(boolean z, Integer num) {
        this.report = z;
        this.action = num;
    }

    public static SafeBrowsingResponse fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new SafeBrowsingResponse(((Boolean) map.get("report")).booleanValue(), (Integer) map.get("action"));
    }

    public boolean isReport() {
        return this.report;
    }

    public void setReport(boolean z) {
        this.report = z;
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
        SafeBrowsingResponse safeBrowsingResponse = (SafeBrowsingResponse) obj;
        if (this.report != safeBrowsingResponse.report) {
            return false;
        }
        Integer num = this.action;
        Integer num2 = safeBrowsingResponse.action;
        return num != null ? num.equals(num2) : num2 == null;
    }

    public int hashCode() {
        int i = (this.report ? 1 : 0) * 31;
        Integer num = this.action;
        return i + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "SafeBrowsingResponse{report=" + this.report + ", action=" + this.action + '}';
    }
}
