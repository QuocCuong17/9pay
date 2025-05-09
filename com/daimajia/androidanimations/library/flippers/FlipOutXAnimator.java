package com.daimajia.androidanimations.library.flippers;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class FlipOutXAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "rotationX", 0.0f, 90.0f), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f));
    }
}
