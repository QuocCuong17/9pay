package com.daimajia.androidanimations.library.bouncing_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class BounceInLeftAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "translationX", -view.getWidth(), 30.0f, -10.0f, 0.0f), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 1.0f, 1.0f));
    }
}
