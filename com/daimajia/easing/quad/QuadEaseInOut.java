package com.daimajia.easing.quad;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class QuadEaseInOut extends BaseEasingMethod {
    public QuadEaseInOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            return Float.valueOf(((f3 / 2.0f) * f5 * f5) + f2);
        }
        float f6 = f5 - 1.0f;
        return Float.valueOf((((-f3) / 2.0f) * ((f6 * (f6 - 2.0f)) - 1.0f)) + f2);
    }
}
