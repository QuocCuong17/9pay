package com.daimajia.easing.circ;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class CircEaseInOut extends BaseEasingMethod {
    public CircEaseInOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            return Float.valueOf((((-f3) / 2.0f) * (((float) Math.sqrt(1.0f - (f5 * f5))) - 1.0f)) + f2);
        }
        float f6 = f5 - 2.0f;
        return Float.valueOf(((f3 / 2.0f) * (((float) Math.sqrt(1.0f - (f6 * f6))) + 1.0f)) + f2);
    }
}
