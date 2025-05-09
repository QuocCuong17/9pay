package com.daimajia.easing;

import android.animation.TypeEvaluator;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class BaseEasingMethod implements TypeEvaluator<Number> {
    protected float mDuration;
    private ArrayList<EasingListener> mListeners = new ArrayList<>();

    /* loaded from: classes2.dex */
    public interface EasingListener {
        void on(float f, float f2, float f3, float f4, float f5);
    }

    public abstract Float calculate(float f, float f2, float f3, float f4);

    public void addEasingListener(EasingListener easingListener) {
        this.mListeners.add(easingListener);
    }

    public void addEasingListeners(EasingListener... easingListenerArr) {
        for (EasingListener easingListener : easingListenerArr) {
            this.mListeners.add(easingListener);
        }
    }

    public void removeEasingListener(EasingListener easingListener) {
        this.mListeners.remove(easingListener);
    }

    public void clearEasingListeners() {
        this.mListeners.clear();
    }

    public BaseEasingMethod(float f) {
        this.mDuration = f;
    }

    public void setDuration(float f) {
        this.mDuration = f;
    }

    @Override // android.animation.TypeEvaluator
    public final Float evaluate(float f, Number number, Number number2) {
        float f2 = this.mDuration * f;
        float floatValue = number.floatValue();
        float floatValue2 = number2.floatValue() - number.floatValue();
        float f3 = this.mDuration;
        float floatValue3 = calculate(f2, floatValue, floatValue2, f3).floatValue();
        Iterator<EasingListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().on(f2, floatValue3, floatValue, floatValue2, f3);
        }
        return Float.valueOf(floatValue3);
    }
}
