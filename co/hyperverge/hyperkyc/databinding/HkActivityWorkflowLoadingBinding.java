package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkActivityWorkflowLoadingBinding implements ViewBinding {
    public final MaterialButton btnCancel;
    public final MaterialButton btnRetry;
    public final LottieAnimationView lavWorkflowLoader;
    private final ConstraintLayout rootView;
    public final MaterialTextView tvRetryTitle;
    public final ConstraintLayout workflowLoadingContent;

    private HkActivityWorkflowLoadingBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, MaterialButton materialButton2, LottieAnimationView lottieAnimationView, MaterialTextView materialTextView, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.btnCancel = materialButton;
        this.btnRetry = materialButton2;
        this.lavWorkflowLoader = lottieAnimationView;
        this.tvRetryTitle = materialTextView;
        this.workflowLoadingContent = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkActivityWorkflowLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkActivityWorkflowLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_activity_workflow_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkActivityWorkflowLoadingBinding bind(View view) {
        int i = R.id.btnCancel;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btnRetry;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.lavWorkflowLoader;
                LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                if (lottieAnimationView != null) {
                    i = R.id.tvRetryTitle;
                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                        return new HkActivityWorkflowLoadingBinding(constraintLayout, materialButton, materialButton2, lottieAnimationView, materialTextView, constraintLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
