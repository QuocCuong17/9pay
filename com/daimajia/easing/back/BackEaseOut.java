package com.daimajia.easing.back;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class BackEaseOut extends BaseEasingMethod {
    private float s;

    public BackEaseOut(float f) {
        super(f);
        this.s = 1.70158f;
    }

    public BackEaseOut(float f, float f2) {
        this(f);
        this.s = f2;
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = (f / f4) - 1.0f;
        float f6 = this.s;
        return Float.valueOf((f3 * ((f5 * f5 * (((f6 + 1.0f) * f5) + f6)) + 1.0f)) + f2);
    }
}
