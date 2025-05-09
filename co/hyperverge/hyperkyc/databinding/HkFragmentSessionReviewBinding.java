package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;

/* loaded from: classes2.dex */
public final class HkFragmentSessionReviewBinding implements ViewBinding {
    public final MaterialButton btnConfirm;
    public final MaterialButton btnRetry;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    public final ImageView progressSpinnerImageView;
    public final ConstraintLayout root;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvTitle;
    public final VideoView vvReview;

    private HkFragmentSessionReviewBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, MaterialButton materialButton2, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, ImageView imageView, ConstraintLayout constraintLayout2, TextView textView, TextView textView2, VideoView videoView) {
        this.rootView = constraintLayout;
        this.btnConfirm = materialButton;
        this.btnRetry = materialButton2;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.progressSpinnerImageView = imageView;
        this.root = constraintLayout2;
        this.tvDesc = textView;
        this.tvTitle = textView2;
        this.vvReview = videoView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentSessionReviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentSessionReviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_session_review, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentSessionReviewBinding bind(View view) {
        View findChildViewById;
        int i = R.id.btnConfirm;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btnRetry;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
                HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
                i = R.id.includeBranding;
                View findChildViewById2 = ViewBindings.findChildViewById(view, i);
                if (findChildViewById2 != null) {
                    HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                    i = R.id.progressSpinnerImageView;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView != null) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                        i = R.id.tvDesc;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            i = R.id.tvTitle;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                            if (textView2 != null) {
                                i = R.id.vvReview;
                                VideoView videoView = (VideoView) ViewBindings.findChildViewById(view, i);
                                if (videoView != null) {
                                    return new HkFragmentSessionReviewBinding(constraintLayout, materialButton, materialButton2, bind, bind2, imageView, constraintLayout, textView, textView2, videoView);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
