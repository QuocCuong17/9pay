package com.daimajia.easing.elastic;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class ElasticEaseOut extends BaseEasingMethod {
    public ElasticEaseOut(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        if (f == 0.0f) {
            return Float.valueOf(f2);
        }
        if (f / f4 == 1.0f) {
            return Float.valueOf(f2 + f3);
        }
        float f5 = 0.3f * f4;
        return Float.valueOf((((float) Math.pow(2.0d, (-10.0f) * r7)) * f3 * ((float) Math.sin((((r7 * f4) - (f5 / 4.0f)) * 6.2831855f) / f5))) + f3 + f2);
    }
}
