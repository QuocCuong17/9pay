package com.daimajia.androidanimations.library.flippers;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class FlipInXAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "rotationX", 90.0f, -15.0f, 15.0f, 0.0f), ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1.0f));
    }
}
