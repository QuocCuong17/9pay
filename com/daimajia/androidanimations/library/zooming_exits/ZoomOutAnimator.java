package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class ZoomOutAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f, 0.0f), ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.3f, 0.0f), ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.3f, 0.0f));
    }
}
