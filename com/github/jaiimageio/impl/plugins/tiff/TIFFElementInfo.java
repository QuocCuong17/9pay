package com.github.jaiimageio.impl.plugins.tiff;

/* loaded from: classes3.dex */
public class TIFFElementInfo {
    String[] attributeNames;
    String[] childNames;
    int childPolicy;
    int minChildren = 0;
    int maxChildren = Integer.MAX_VALUE;
    int objectValueType = 0;
    Class objectClass = null;
    Object objectDefaultValue = null;
    Object[] objectEnumerations = null;
    Comparable objectMinValue = null;
    Comparable objectMaxValue = null;
    int objectArrayMinLength = 0;
    int objectArrayMaxLength = 0;

    public TIFFElementInfo(String[] strArr, String[] strArr2, int i) {
        this.childNames = strArr;
        this.attributeNames = strArr2;
        this.childPolicy = i;
    }
}
