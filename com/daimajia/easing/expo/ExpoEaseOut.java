package com.daimajia.easing.expo;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class ExpoEaseOut extends BaseEasingMethod {
    public ExpoEaseOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        return Float.valueOf(f == f4 ? f2 + f3 : f2 + (f3 * ((-((float) Math.pow(2.0d, (f * (-10.0f)) / f4))) + 1.0f)));
    }
}
