package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkRvItemFormFileBinding implements ViewBinding {
    public final AppCompatImageView ivPreview;
    public final AppCompatImageView ivRemove;
    public final ProgressBar progressBar;
    private final MaterialCardView rootView;
    public final MaterialTextView tvFileName;
    public final MaterialTextView tvFileSize;

    private HkRvItemFormFileBinding(MaterialCardView materialCardView, AppCompatImageView appCompatImageView, AppCompatImageView appCompatImageView2, ProgressBar progressBar, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = materialCardView;
        this.ivPreview = appCompatImageView;
        this.ivRemove = appCompatImageView2;
        this.progressBar = progressBar;
        this.tvFileName = materialTextView;
        this.tvFileSize = materialTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static HkRvItemFormFileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemFormFileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_form_file, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemFormFileBinding bind(View view) {
        int i = R.id.ivPreview;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.ivRemove;
            AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
            if (appCompatImageView2 != null) {
                i = R.id.progressBar;
                ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
                if (progressBar != null) {
                    i = R.id.tvFileName;
                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView != null) {
                        i = R.id.tvFileSize;
                        MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView2 != null) {
                            return new HkRvItemFormFileBinding((MaterialCardView) view, appCompatImageView, appCompatImageView2, progressBar, materialTextView, materialTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
