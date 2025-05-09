package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.TIFFColorConverter;

/* loaded from: classes3.dex */
public class TIFFCIELabColorConverter extends TIFFColorConverter {
    private static final float THRESHOLD = (float) Math.pow(0.008856d, 0.3333333333333333d);
    private static final float Xn = 95.047f;
    private static final float Yn = 100.0f;
    private static final float Zn = 108.883f;

    private float clamp(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > Yn) {
            return 255.0f;
        }
        return f * 2.55f;
    }

    private float clamp2(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > 255.0f) {
            return 255.0f;
        }
        return f;
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFColorConverter
    public void fromRGB(float f, float f2, float f3, float[] fArr) {
        float f4 = (((0.212671f * f) + (0.71516f * f2)) + (0.072169f * f3)) / Yn;
        float f5 = (((0.412453f * f) + (0.35758f * f2)) + (0.180423f * f3)) / Xn;
        float f6 = (((f * 0.019334f) + (f2 * 0.119193f)) + (f3 * 0.950227f)) / Zn;
        float pow = f4 < 0.008856f ? (f4 * 7.787f) + 0.13793103f : (float) Math.pow(f4, 0.3333333333333333d);
        float f7 = (116.0f * pow) - 16.0f;
        float pow2 = ((f5 < 0.008856f ? (f5 * 7.787f) + 0.13793103f : (float) Math.pow(f5, 0.3333333333333333d)) - pow) * 500.0f;
        float pow3 = (pow - (f6 < 0.008856f ? (f6 * 7.787f) + 0.13793103f : (float) Math.pow(f6, 0.3333333333333333d))) * 200.0f;
        float f8 = f7 * 2.55f;
        if (pow2 < 0.0f) {
            pow2 += 256.0f;
        }
        if (pow3 < 0.0f) {
            pow3 += 256.0f;
        }
        fArr[0] = clamp2(f8);
        fArr[1] = clamp2(pow2);
        fArr[2] = clamp2(pow3);
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFColorConverter
    public void toRGB(float f, float f2, float f3, float[] fArr) {
        float f4;
        float pow;
        float f5 = (f * Yn) / 255.0f;
        if (f2 > 128.0f) {
            f2 -= 256.0f;
        }
        if (f3 > 128.0f) {
            f3 -= 256.0f;
        }
        if (f5 < 8.0f) {
            f4 = f5 / 903.3f;
            pow = (f4 * 7.787f) + 0.13793103f;
        } else {
            float f6 = (f5 + 16.0f) / 116.0f;
            f4 = f6 * f6 * f6;
            pow = (float) Math.pow(f4, 0.3333333333333333d);
        }
        float f7 = f4 * Yn;
        float f8 = (f2 / 500.0f) + pow;
        float f9 = THRESHOLD;
        float f10 = f8 <= f9 ? ((f8 - 0.13793103f) * Xn) / 7.787f : f8 * Xn * f8 * f8;
        float f11 = pow - (f3 / 200.0f);
        float f12 = f11 <= f9 ? ((f11 - 0.13793103f) * Zn) / 7.787f : f11 * Zn * f11 * f11;
        fArr[0] = clamp(((3.240479f * f10) - (1.53715f * f7)) - (0.498535f * f12));
        fArr[1] = clamp(((-0.969256f) * f10) + (1.875992f * f7) + (0.041556f * f12));
        fArr[2] = clamp(((f10 * 0.055648f) - (f7 * 0.204043f)) + (f12 * 1.057311f));
    }
}
