package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* loaded from: classes2.dex */
public final class HkRvItemFormFileReviewDocumentBinding implements ViewBinding {
    public final SubsamplingScaleImageView ivDocImage;
    private final ConstraintLayout rootView;

    private HkRvItemFormFileReviewDocumentBinding(ConstraintLayout constraintLayout, SubsamplingScaleImageView subsamplingScaleImageView) {
        this.rootView = constraintLayout;
        this.ivDocImage = subsamplingScaleImageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkRvItemFormFileReviewDocumentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemFormFileReviewDocumentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_form_file_review_document, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemFormFileReviewDocumentBinding bind(View view) {
        int i = R.id.ivDocImage;
        SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) ViewBindings.findChildViewById(view, i);
        if (subsamplingScaleImageView != null) {
            return new HkRvItemFormFileReviewDocumentBinding((ConstraintLayout) view, subsamplingScaleImageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
