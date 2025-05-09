package com.pichillilorenzo.flutter_inappwebview_android.types;

import android.print.PrintAttributes;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class MediaSizeExt {
    private int heightMils;

    /* renamed from: id, reason: collision with root package name */
    private String f104id;
    private String label;
    private int widthMils;

    public MediaSizeExt(String str, String str2, int i, int i2) {
        this.f104id = str;
        this.label = str2;
        this.widthMils = i;
        this.heightMils = i2;
    }

    public static MediaSizeExt fromMediaSize(PrintAttributes.MediaSize mediaSize) {
        if (mediaSize == null) {
            return null;
        }
        return new MediaSizeExt(mediaSize.getId(), null, mediaSize.getHeightMils(), mediaSize.getWidthMils());
    }

    public static MediaSizeExt fromMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return new MediaSizeExt((String) map.get("id"), (String) map.get("label"), ((Integer) map.get("widthMils")).intValue(), ((Integer) map.get("heightMils")).intValue());
    }

    public PrintAttributes.MediaSize toMediaSize() {
        return new PrintAttributes.MediaSize(this.f104id, TypedValues.Custom.NAME, this.widthMils, this.heightMils);
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.f104id);
        hashMap.put("label", this.label);
        hashMap.put("heightMils", Integer.valueOf(this.heightMils));
        hashMap.put("widthMils", Integer.valueOf(this.widthMils));
        return hashMap;
    }

    public String getId() {
        return this.f104id;
    }

    public void setId(String str) {
        this.f104id = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public int getWidthMils() {
        return this.widthMils;
    }

    public void setWidthMils(int i) {
        this.widthMils = i;
    }

    public int getHeightMils() {
        return this.heightMils;
    }

    public void setHeightMils(int i) {
        this.heightMils = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaSizeExt mediaSizeExt = (MediaSizeExt) obj;
        if (this.widthMils != mediaSizeExt.widthMils || this.heightMils != mediaSizeExt.heightMils || !this.f104id.equals(mediaSizeExt.f104id)) {
            return false;
        }
        String str = this.label;
        String str2 = mediaSizeExt.label;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        int hashCode = this.f104id.hashCode() * 31;
        String str = this.label;
        return ((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.widthMils) * 31) + this.heightMils;
    }

    public String toString() {
        return "MediaSizeExt{id='" + this.f104id + "', label='" + this.label + "', widthMils=" + this.widthMils + ", heightMils=" + this.heightMils + '}';
    }
}
