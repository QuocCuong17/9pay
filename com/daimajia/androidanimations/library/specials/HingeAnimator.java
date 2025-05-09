package com.daimajia.androidanimations.library.specials;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

/* loaded from: classes2.dex */
public class HingeAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        float paddingLeft = view.getPaddingLeft();
        float paddingTop = view.getPaddingTop();
        getAnimatorAgent().playTogether(Glider.glide(Skill.SineEaseInOut, 1300.0f, ObjectAnimator.ofFloat(view, Key.ROTATION, 0.0f, 80.0f, 60.0f, 80.0f, 60.0f, 60.0f)), ObjectAnimator.ofFloat(view, "translationY", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 700.0f), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f), ObjectAnimator.ofFloat(view, "pivotX", paddingLeft, paddingLeft, paddingLeft, paddingLeft, paddingLeft, paddingLeft), ObjectAnimator.ofFloat(view, "pivotY", paddingTop, paddingTop, paddingTop, paddingTop, paddingTop, paddingTop));
        setDuration(1300L);
    }
}
