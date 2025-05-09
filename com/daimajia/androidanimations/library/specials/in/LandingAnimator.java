package com.daimajia.androidanimations.library.specials.in;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

/* loaded from: classes2.dex */
public class LandingAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    protected void prepare(View view) {
        getAnimatorAgent().playTogether(Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1.0f)), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1.0f)), Glider.glide(Skill.QuintEaseOut, (float) getDuration(), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f)));
    }
}
