package com.daimajia.androidanimations.library.fading_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class FadeOutRightAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f), ObjectAnimator.ofFloat(view, "translationX", 0.0f, view.getWidth() / 4));
    }
}
