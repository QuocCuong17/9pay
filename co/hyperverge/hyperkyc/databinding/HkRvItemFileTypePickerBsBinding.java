package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkRvItemFileTypePickerBsBinding implements ViewBinding {
    public final AppCompatImageView ivIcon;
    private final ConstraintLayout rootView;
    public final MaterialTextView tvTitle;

    private HkRvItemFileTypePickerBsBinding(ConstraintLayout constraintLayout, AppCompatImageView appCompatImageView, MaterialTextView materialTextView) {
        this.rootView = constraintLayout;
        this.ivIcon = appCompatImageView;
        this.tvTitle = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkRvItemFileTypePickerBsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemFileTypePickerBsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_file_type_picker_bs, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemFileTypePickerBsBinding bind(View view) {
        int i = R.id.ivIcon;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.tvTitle;
            MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
            if (materialTextView != null) {
                return new HkRvItemFileTypePickerBsBinding((ConstraintLayout) view, appCompatImageView, materialTextView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
