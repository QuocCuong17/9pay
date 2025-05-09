package com.daimajia.easing.back;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class BackEaseIn extends BaseEasingMethod {
    private float s;

    public BackEaseIn(float f) {
        super(f);
        this.s = 1.70158f;
    }

    public BackEaseIn(float f, float f2) {
        this(f);
        this.s = f2;
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        float f6 = this.s;
        return Float.valueOf((f3 * f5 * f5 * (((1.0f + f6) * f5) - f6)) + f2);
    }
}
