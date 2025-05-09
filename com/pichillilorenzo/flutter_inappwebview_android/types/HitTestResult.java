package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.webkit.WebView;
import io.sentry.SentryBaseEvent;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class HitTestResult {
    private String extra;
    private int type;

    public HitTestResult(int i, String str) {
        this.type = i;
        this.extra = str;
    }

    public static HitTestResult fromWebViewHitTestResult(WebView.HitTestResult hitTestResult) {
        if (hitTestResult == null) {
            return null;
        }
        return new HitTestResult(hitTestResult.getType(), hitTestResult.getExtra());
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", Integer.valueOf(this.type));
        hashMap.put(SentryBaseEvent.JsonKeys.EXTRA, this.extra);
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HitTestResult hitTestResult = (HitTestResult) obj;
        if (this.type != hitTestResult.type) {
            return false;
        }
        String str = this.extra;
        String str2 = hitTestResult.extra;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int i = this.type * 31;
        String str = this.extra;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "HitTestResultMap{type=" + this.type + ", extra='" + this.extra + "'}";
    }
}
