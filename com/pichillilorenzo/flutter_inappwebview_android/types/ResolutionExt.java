package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.print.PrintAttributes;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ResolutionExt {
    private int horizontalDpi;

    /* renamed from: id, reason: collision with root package name */
    private String f105id;
    private String label;
    private int verticalDpi;

    public ResolutionExt(String str, String str2, int i, int i2) {
        this.f105id = str;
        this.label = str2;
        this.verticalDpi = i;
        this.horizontalDpi = i2;
    }

    public static ResolutionExt fromResolution(PrintAttributes.Resolution resolution) {
        if (resolution == null) {
            return null;
        }
        return new ResolutionExt(resolution.getId(), resolution.getLabel(), resolution.getVerticalDpi(), resolution.getHorizontalDpi());
    }

    public static ResolutionExt fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new ResolutionExt((String) map.get("id"), (String) map.get("label"), ((Integer) map.get("verticalDpi")).intValue(), ((Integer) map.get("horizontalDpi")).intValue());
    }

    public PrintAttributes.Resolution toResolution() {
        return new PrintAttributes.Resolution(this.f105id, this.label, this.horizontalDpi, this.verticalDpi);
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.f105id);
        hashMap.put("label", this.label);
        hashMap.put("verticalDpi", Integer.valueOf(this.verticalDpi));
        hashMap.put("horizontalDpi", Integer.valueOf(this.horizontalDpi));
        return hashMap;
    }

    public String getId() {
        return this.f105id;
    }

    public void setId(String str) {
        this.f105id = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public int getVerticalDpi() {
        return this.verticalDpi;
    }

    public void setVerticalDpi(int i) {
        this.verticalDpi = i;
    }

    public int getHorizontalDpi() {
        return this.horizontalDpi;
    }

    public void setHorizontalDpi(int i) {
        this.horizontalDpi = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ResolutionExt resolutionExt = (ResolutionExt) obj;
        if (this.verticalDpi == resolutionExt.verticalDpi && this.horizontalDpi == resolutionExt.horizontalDpi && this.f105id.equals(resolutionExt.f105id)) {
            return this.label.equals(resolutionExt.label);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.f105id.hashCode() * 31) + this.label.hashCode()) * 31) + this.verticalDpi) * 31) + this.horizontalDpi;
    }

    public String toString() {
        return "ResolutionExt{id='" + this.f105id + "', label='" + this.label + "', verticalDpi=" + this.verticalDpi + ", horizontalDpi=" + this.horizontalDpi + '}';
    }
}
