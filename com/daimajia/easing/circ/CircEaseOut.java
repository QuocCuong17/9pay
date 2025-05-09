package com.daimajia.easing.circ;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class CircEaseOut extends BaseEasingMethod {
    public CircEaseOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = (f / f4) - 1.0f;
        return Float.valueOf((f3 * ((float) Math.sqrt(1.0f - (f5 * f5)))) + f2);
    }
}
