package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkActivityMainBinding implements ViewBinding {
    public final ConstraintLayout HKMainActivityContent;
    public final MaterialButton btnCancel;
    public final MaterialButton btnRetry;
    public final FrameLayout flContent;
    public final LottieAnimationView lavLoader;
    private final ConstraintLayout rootView;
    public final MaterialTextView tvRetryTitle;

    private HkActivityMainBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, MaterialButton materialButton, MaterialButton materialButton2, FrameLayout frameLayout, LottieAnimationView lottieAnimationView, MaterialTextView materialTextView) {
        this.rootView = constraintLayout;
        this.HKMainActivityContent = constraintLayout2;
        this.btnCancel = materialButton;
        this.btnRetry = materialButton2;
        this.flContent = frameLayout;
        this.lavLoader = lottieAnimationView;
        this.tvRetryTitle = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkActivityMainBinding bind(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.btnCancel;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btnRetry;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.flContent;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                if (frameLayout != null) {
                    i = R.id.lavLoader;
                    LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                    if (lottieAnimationView != null) {
                        i = R.id.tvRetryTitle;
                        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView != null) {
                            return new HkActivityMainBinding(constraintLayout, constraintLayout, materialButton, materialButton2, frameLayout, lottieAnimationView, materialTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
