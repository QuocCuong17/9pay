package com.pichillilorenzo.flutter_inappwebview_android.types;

import java.util.Arrays;
import java.util.Map;

/* loaded from: classes5.dex */
public class CustomTabsActionButton {
    private String description;
    private byte[] icon;

    /* renamed from: id, reason: collision with root package name */
    private int f101id;
    private boolean shouldTint;

    public CustomTabsActionButton(int i, byte[] bArr, String str, boolean z) {
        this.f101id = i;
        this.icon = bArr;
        this.description = str;
        this.shouldTint = z;
    }

    public static CustomTabsActionButton fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new CustomTabsActionButton(((Integer) map.get("id")).intValue(), (byte[]) map.get("icon"), (String) map.get("description"), ((Boolean) map.get("shouldTint")).booleanValue());
    }

    public int getId() {
        return this.f101id;
    }

    public void setId(int i) {
        this.f101id = i;
    }

    public byte[] getIcon() {
        return this.icon;
    }

    public void setIcon(byte[] bArr) {
        this.icon = bArr;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public boolean isShouldTint() {
        return this.shouldTint;
    }

    public void setShouldTint(boolean z) {
        this.shouldTint = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomTabsActionButton customTabsActionButton = (CustomTabsActionButton) obj;
        if (this.f101id == customTabsActionButton.f101id && this.shouldTint == customTabsActionButton.shouldTint && Arrays.equals(this.icon, customTabsActionButton.icon)) {
            return this.description.equals(customTabsActionButton.description);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.f101id * 31) + Arrays.hashCode(this.icon)) * 31) + this.description.hashCode()) * 31) + (this.shouldTint ? 1 : 0);
    }

    public String toString() {
        return "CustomTabsActionButton{id=" + this.f101id + ", icon=" + Arrays.toString(this.icon) + ", description='" + this.description + "', shouldTint=" + this.shouldTint + '}';
    }
}
