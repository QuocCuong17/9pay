package com.github.jaiimageio.impl.common;

import java.awt.color.ColorSpace;

/* loaded from: classes3.dex */
public final class InvertedCMYKColorSpace extends ColorSpace {
    private static final double power1 = 0.4166666666666667d;
    private static ColorSpace theInstance;
    private ColorSpace csRGB;

    public static final synchronized ColorSpace getInstance() {
        ColorSpace colorSpace;
        synchronized (InvertedCMYKColorSpace.class) {
            if (theInstance == null) {
                theInstance = new InvertedCMYKColorSpace();
            }
            colorSpace = theInstance;
        }
        return colorSpace;
    }

    private InvertedCMYKColorSpace() {
        super(9, 4);
        this.csRGB = ColorSpace.getInstance(1004);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof InvertedCMYKColorSpace);
    }

    public float[] toRGB(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float[] fArr2 = new float[3];
        fArr2[0] = f * f4;
        fArr2[1] = f2 * f4;
        fArr2[2] = f4 * f3;
        for (int i = 0; i < 3; i++) {
            float f5 = fArr2[i];
            if (f5 < 0.0f) {
                f5 = 0.0f;
            }
            if (f5 < 0.0031308f) {
                fArr2[i] = f5 * 12.92f;
            } else {
                if (f5 > 1.0f) {
                    f5 = 1.0f;
                }
                fArr2[i] = (float) ((Math.pow(f5, power1) * 1.055d) - 0.055d);
            }
        }
        return fArr2;
    }

    public float[] fromRGB(float[] fArr) {
        float f;
        float f2;
        float f3;
        for (int i = 0; i < 3; i++) {
            if (fArr[i] < 0.040449936f) {
                fArr[i] = fArr[i] / 12.92f;
            } else {
                fArr[i] = (float) Math.pow((fArr[i] + 0.055d) / 1.055d, 2.4d);
            }
        }
        float f4 = fArr[0];
        float f5 = fArr[1];
        float f6 = fArr[2];
        float max = Math.max(f4, Math.max(f5, f6));
        if (max != 0.0f) {
            f = f4 / max;
            f3 = f5 / max;
            f2 = f6 / max;
        } else {
            f = 1.0f;
            f2 = 1.0f;
            f3 = 1.0f;
        }
        return new float[]{f, f3, f2, max};
    }

    public float[] toCIEXYZ(float[] fArr) {
        return this.csRGB.toCIEXYZ(toRGB(fArr));
    }

    public float[] fromCIEXYZ(float[] fArr) {
        return fromRGB(this.csRGB.fromCIEXYZ(fArr));
    }
}
