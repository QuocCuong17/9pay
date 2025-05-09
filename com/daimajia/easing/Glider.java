package com.daimajia.easing;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes2.dex */
public class Glider {
    public static ValueAnimator glide(Skill skill, float f, ValueAnimator valueAnimator) {
        return glide(skill, f, valueAnimator, null);
    }

    public static ValueAnimator glide(Skill skill, float f, ValueAnimator valueAnimator, BaseEasingMethod.EasingListener... easingListenerArr) {
        BaseEasingMethod method = skill.getMethod(f);
        if (easingListenerArr != null) {
            method.addEasingListeners(easingListenerArr);
        }
        valueAnimator.setEvaluator(method);
        return valueAnimator;
    }

    public static PropertyValuesHolder glide(Skill skill, float f, PropertyValuesHolder propertyValuesHolder) {
        propertyValuesHolder.setEvaluator(skill.getMethod(f));
        return propertyValuesHolder;
    }
}
