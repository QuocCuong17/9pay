package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkViewFileUploadClBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final RecyclerView rvFiles;
    public final AppCompatTextView tvInfo;
    public final MaterialTextView tvSubTitle;
    public final MaterialTextView tvTitle;

    private HkViewFileUploadClBinding(ConstraintLayout constraintLayout, RecyclerView recyclerView, AppCompatTextView appCompatTextView, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = constraintLayout;
        this.rvFiles = recyclerView;
        this.tvInfo = appCompatTextView;
        this.tvSubTitle = materialTextView;
        this.tvTitle = materialTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkViewFileUploadClBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewFileUploadClBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_file_upload_cl, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewFileUploadClBinding bind(View view) {
        int i = R.id.rvFiles;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
        if (recyclerView != null) {
            i = R.id.tvInfo;
            AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, i);
            if (appCompatTextView != null) {
                i = R.id.tvSubTitle;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    i = R.id.tvTitle;
                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView2 != null) {
                        return new HkViewFileUploadClBinding((ConstraintLayout) view, recyclerView, appCompatTextView, materialTextView, materialTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
