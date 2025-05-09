package com.github.jaiimageio.impl.common;

import java.awt.color.ColorSpace;

/* loaded from: classes3.dex */
public class BogusColorSpace extends ColorSpace {
    private static int getType(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("numComponents < 1!");
        }
        if (i != 1) {
            return i + 10;
        }
        return 6;
    }

    public BogusColorSpace(int i) {
        super(getType(i), i);
    }

    public float[] toRGB(float[] fArr) {
        if (fArr.length < getNumComponents()) {
            throw new ArrayIndexOutOfBoundsException("colorvalue.length < getNumComponents()");
        }
        System.arraycopy(fArr, 0, new float[3], 0, Math.min(3, getNumComponents()));
        return fArr;
    }

    public float[] fromRGB(float[] fArr) {
        if (fArr.length < 3) {
            throw new ArrayIndexOutOfBoundsException("rgbvalue.length < 3");
        }
        int numComponents = getNumComponents();
        System.arraycopy(fArr, 0, new float[numComponents], 0, Math.min(3, numComponents));
        return fArr;
    }

    public float[] toCIEXYZ(float[] fArr) {
        if (fArr.length < getNumComponents()) {
            throw new ArrayIndexOutOfBoundsException("colorvalue.length < getNumComponents()");
        }
        System.arraycopy(fArr, 0, new float[3], 0, Math.min(3, getNumComponents()));
        return fArr;
    }

    public float[] fromCIEXYZ(float[] fArr) {
        if (fArr.length < 3) {
            throw new ArrayIndexOutOfBoundsException("xyzvalue.length < 3");
        }
        int numComponents = getNumComponents();
        System.arraycopy(fArr, 0, new float[numComponents], 0, Math.min(3, numComponents));
        return fArr;
    }
}
