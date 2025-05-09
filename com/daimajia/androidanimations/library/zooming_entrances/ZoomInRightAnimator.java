package com.daimajia.androidanimations.library.zooming_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class ZoomInRightAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1.0f), ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1.0f), ObjectAnimator.ofFloat(view, "translationX", view.getWidth() + view.getPaddingRight(), -48.0f, 0.0f), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 1.0f));
    }
}
