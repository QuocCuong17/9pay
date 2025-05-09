package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkFragmentBsFormFileBinding implements ViewBinding {
    public final AppCompatImageView ivDragHandle;
    private final LinearLayout rootView;
    public final RecyclerView rvFileTypePicker;
    public final MaterialTextView tvTitle;

    private HkFragmentBsFormFileBinding(LinearLayout linearLayout, AppCompatImageView appCompatImageView, RecyclerView recyclerView, MaterialTextView materialTextView) {
        this.rootView = linearLayout;
        this.ivDragHandle = appCompatImageView;
        this.rvFileTypePicker = recyclerView;
        this.tvTitle = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentBsFormFileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentBsFormFileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_bs_form_file, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentBsFormFileBinding bind(View view) {
        int i = R.id.ivDragHandle;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            i = R.id.rvFileTypePicker;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
            if (recyclerView != null) {
                i = R.id.tvTitle;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    return new HkFragmentBsFormFileBinding((LinearLayout) view, appCompatImageView, recyclerView, materialTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
