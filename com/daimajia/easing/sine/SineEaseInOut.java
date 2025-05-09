package com.daimajia.easing.sine;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class SineEaseInOut extends BaseEasingMethod {
    public SineEaseInOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        return Float.valueOf((((-f3) / 2.0f) * (((float) Math.cos((f * 3.141592653589793d) / f4)) - 1.0f)) + f2);
    }
}
