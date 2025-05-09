package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class BounceAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "translationY", 0.0f, 0.0f, -30.0f, 0.0f, -15.0f, 0.0f, 0.0f));
    }
}
