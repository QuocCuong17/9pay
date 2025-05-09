package com.daimajia.androidanimations.library.rotating_exits;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class RotateOutUpLeftAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        float paddingLeft = view.getPaddingLeft();
        float height = view.getHeight() - view.getPaddingBottom();
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f), ObjectAnimator.ofFloat(view, Key.ROTATION, 0.0f, -90.0f), ObjectAnimator.ofFloat(view, "pivotX", paddingLeft, paddingLeft), ObjectAnimator.ofFloat(view, "pivotY", height, height));
    }
}
