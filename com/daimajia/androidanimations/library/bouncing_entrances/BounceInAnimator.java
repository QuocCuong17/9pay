package com.daimajia.androidanimations.library.bouncing_entrances;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class BounceInAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 1.0f, 1.0f), ObjectAnimator.ofFloat(view, "scaleX", 0.3f, 1.05f, 0.9f, 1.0f), ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 1.05f, 0.9f, 1.0f));
    }
}
