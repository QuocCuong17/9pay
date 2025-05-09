package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes2.dex */
public class SwingAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, Key.ROTATION, 0.0f, 10.0f, -10.0f, 6.0f, -6.0f, 3.0f, -3.0f, 0.0f));
    }
}
