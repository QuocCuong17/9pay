package com.daimajia.androidanimations.library.specials.out;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

/* loaded from: classes2.dex */
public class TakingOffAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View view) {
        getAnimatorAgent().playTogether(Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.5f)), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.5f)), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f)));
    }
}
