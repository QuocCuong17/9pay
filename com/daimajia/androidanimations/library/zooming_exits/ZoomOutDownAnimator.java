package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class ZoomOutDownAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "alpha", 1.0f, 1.0f, 0.0f), ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.475f, 0.1f), ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.475f, 0.1f), ObjectAnimator.ofFloat(view, "translationY", 0.0f, -60.0f, ((ViewGroup) view.getParent()).getHeight() - view.getTop()));
    }
}
