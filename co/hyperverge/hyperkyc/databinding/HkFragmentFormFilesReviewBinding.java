package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkFragmentFormFilesReviewBinding implements ViewBinding {
    public final AppCompatImageView ivClose;
    public final AppCompatImageView ivRemove;
    private final ConstraintLayout rootView;
    public final RecyclerView rvFiles;
    public final MaterialTextView tvError;
    public final MaterialTextView tvSubtitle;
    public final MaterialTextView tvTitle;
    public final View vBottomDivider;
    public final View vTopDivider;

    private HkFragmentFormFilesReviewBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, RecyclerView recyclerView, MaterialTextView materialTextView, MaterialTextView materialTextView2, MaterialTextView materialTextView3, View view, View view2) {
        this.rootView = constraintLayout;
        this.ivClose = appCompatImageView;
        this.ivRemove = appCompatImageView2;
        this.rvFiles = recyclerView;
        this.tvError = materialTextView;
        this.tvSubtitle = materialTextView2;
        this.tvTitle = materialTextView3;
        this.vBottomDivider = view;
        this.vTopDivider = view2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentFormFilesReviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentFormFilesReviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_form_files_review, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentFormFilesReviewBinding bind(View view) {
        View findChildViewById;
        View findChildViewById2;
        int i = R.id.ivClose;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.ivRemove;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.rvFiles;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.tvError;
                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView != null) {
                        i = R.id.tvSubtitle;
                        MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView2 != null) {
                            i = R.id.tvTitle;
                            MaterialTextView materialTextView3 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                            if (materialTextView3 != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.vBottomDivider))) != null && (findChildViewById2 = ViewBindings.findChildViewById(view, (i = R.id.vTopDivider))) != null) {
                                return new HkFragmentFormFilesReviewBinding((ConstraintLayout) view, appCompatImageView, appCompatImageView2, recyclerView, materialTextView, materialTextView2, materialTextView3, findChildViewById, findChildViewById2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
