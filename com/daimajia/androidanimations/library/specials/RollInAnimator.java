package com.daimajia.androidanimations.library.specials;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class RollInAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f), ObjectAnimator.ofFloat(view, "translationX", -((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()), 0.0f), ObjectAnimator.ofFloat(view, Key.ROTATION, -120.0f, 0.0f));
    }
}
