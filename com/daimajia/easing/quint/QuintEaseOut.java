package com.daimajia.easing.quint;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class QuintEaseOut extends BaseEasingMethod {
    public QuintEaseOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = (f / f4) - 1.0f;
        return Float.valueOf((f3 * ((f5 * f5 * f5 * f5 * f5) + 1.0f)) + f2);
    }
}
