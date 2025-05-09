package com.pichillilorenzo.flutter_inappwebview_android.types;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class WebMessagePortCompatExt {
    private int index;
    private String webMessageChannelId;

    public WebMessagePortCompatExt(int i, String str) {
        this.index = i;
        this.webMessageChannelId = str;
    }

    public static WebMessagePortCompatExt fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Integer num = (Integer) map.get(FirebaseAnalytics.Param.INDEX);
        return new WebMessagePortCompatExt(num.intValue(), (String) map.get("webMessageChannelId"));
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(FirebaseAnalytics.Param.INDEX, Integer.valueOf(this.index));
        hashMap.put("webMessageChannelId", this.webMessageChannelId);
        return hashMap;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String getWebMessageChannelId() {
        return this.webMessageChannelId;
    }

    public void setWebMessageChannelId(String str) {
        this.webMessageChannelId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebMessagePortCompatExt webMessagePortCompatExt = (WebMessagePortCompatExt) obj;
        if (this.index != webMessagePortCompatExt.index) {
            return false;
        }
        return this.webMessageChannelId.equals(webMessagePortCompatExt.webMessageChannelId);
    }

    public int hashCode() {
        return (this.index * 31) + this.webMessageChannelId.hashCode();
    }

    public String toString() {
        return "WebMessagePortCompatExt{index=" + this.index + ", webMessageChannelId='" + this.webMessageChannelId + "'}";
    }
}
