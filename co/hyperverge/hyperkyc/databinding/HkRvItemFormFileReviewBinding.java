package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* loaded from: classes2.dex */
public final class HkRvItemFormFileReviewBinding implements ViewBinding {
    public final SubsamplingScaleImageView ivImage;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final RecyclerView rvDocuments;

    private HkRvItemFormFileReviewBinding(ConstraintLayout constraintLayout, SubsamplingScaleImageView subsamplingScaleImageView, ProgressBar progressBar, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.ivImage = subsamplingScaleImageView;
        this.progressBar = progressBar;
        this.rvDocuments = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkRvItemFormFileReviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemFormFileReviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_form_file_review, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemFormFileReviewBinding bind(View view) {
        int i = R.id.ivImage;
        SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) ViewBindings.findChildViewById(view, i);
        if (subsamplingScaleImageView != null) {
            i = R.id.progressBar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                i = R.id.rvDocuments;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    return new HkRvItemFormFileReviewBinding((ConstraintLayout) view, subsamplingScaleImageView, progressBar, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
