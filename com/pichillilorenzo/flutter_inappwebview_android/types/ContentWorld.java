package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class ContentWorld {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String name;
    public static final ContentWorld PAGE = new ContentWorld("page");
    public static final ContentWorld DEFAULT_CLIENT = new ContentWorld("defaultClient");

    private ContentWorld(String str) {
        this.name = str;
    }

    public static ContentWorld world(String str) {
        return new ContentWorld(str);
    }

    public static ContentWorld fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new ContentWorld((String) map.get("name"));
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.name.equals(((ContentWorld) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "ContentWorld{name='" + this.name + "'}";
    }
}
