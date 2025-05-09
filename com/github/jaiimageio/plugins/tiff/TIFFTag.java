package com.github.jaiimageio.plugins.tiff;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class TIFFTag {
    public static final int MAX_DATATYPE = 13;
    public static final int MIN_DATATYPE = 1;
    public static final int TIFF_ASCII = 2;
    public static final int TIFF_BYTE = 1;
    public static final int TIFF_DOUBLE = 12;
    public static final int TIFF_FLOAT = 11;
    public static final int TIFF_IFD_POINTER = 13;
    public static final int TIFF_LONG = 4;
    public static final int TIFF_RATIONAL = 5;
    public static final int TIFF_SBYTE = 6;
    public static final int TIFF_SHORT = 3;
    public static final int TIFF_SLONG = 9;
    public static final int TIFF_SRATIONAL = 10;
    public static final int TIFF_SSHORT = 8;
    public static final int TIFF_UNDEFINED = 7;
    private static final int[] sizeOfType = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 4};
    int dataTypes;
    String name;
    int number;
    TIFFTagSet tagSet;
    Map valueNames;

    public TIFFTag(String str, int i, int i2, TIFFTagSet tIFFTagSet) {
        this.tagSet = null;
        this.valueNames = null;
        this.name = str;
        this.number = i;
        this.dataTypes = i2;
        this.tagSet = tIFFTagSet;
    }

    public TIFFTag(String str, int i, int i2) {
        this(str, i, i2, null);
    }

    public static int getSizeOfType(int i) {
        if (i < 1 || i > 13) {
            throw new IllegalArgumentException("dataType out of range!");
        }
        return sizeOfType[i];
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }

    public int getDataTypes() {
        return this.dataTypes;
    }

    public boolean isDataTypeOK(int i) {
        if (i < 1 || i > 13) {
            throw new IllegalArgumentException("datatype not in range!");
        }
        return ((1 << i) & this.dataTypes) != 0;
    }

    public TIFFTagSet getTagSet() {
        return this.tagSet;
    }

    public boolean isIFDPointer() {
        return (this.tagSet == null && (this.dataTypes & 8192) == 0) ? false : true;
    }

    public boolean hasValueNames() {
        return this.valueNames != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addValueName(int i, String str) {
        if (this.valueNames == null) {
            this.valueNames = new HashMap();
        }
        this.valueNames.put(new Integer(i), str);
    }

    public String getValueName(int i) {
        Map map = this.valueNames;
        if (map == null) {
            return null;
        }
        return (String) map.get(new Integer(i));
    }
}
