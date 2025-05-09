package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Map;

/* loaded from: classes5.dex */
public class CustomTabsMenuItem {

    /* renamed from: id, reason: collision with root package name */
    private int f102id;
    private String label;

    public CustomTabsMenuItem(int i, String str) {
        this.f102id = i;
        this.label = str;
    }

    public static CustomTabsMenuItem fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new CustomTabsMenuItem(((Integer) map.get("id")).intValue(), (String) map.get("label"));
    }

    public int getId() {
        return this.f102id;
    }

    public void setId(int i) {
        this.f102id = i;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomTabsMenuItem customTabsMenuItem = (CustomTabsMenuItem) obj;
        if (this.f102id != customTabsMenuItem.f102id) {
            return false;
        }
        return this.label.equals(customTabsMenuItem.label);
    }

    public int hashCode() {
        return (this.f102id * 31) + this.label.hashCode();
    }

    public String toString() {
        return "CustomTabsMenuItem{id=" + this.f102id + ", label='" + this.label + "'}";
    }
}
