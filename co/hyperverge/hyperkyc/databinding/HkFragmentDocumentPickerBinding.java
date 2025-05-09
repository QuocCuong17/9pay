package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkFragmentDocumentPickerBinding implements ViewBinding {
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    private final ConstraintLayout rootView;
    public final RecyclerView rvDocuments;
    public final MaterialTextView tvSubTitle;
    public final MaterialTextView tvTitle;

    private HkFragmentDocumentPickerBinding(ConstraintLayout constraintLayout, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, RecyclerView recyclerView, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = constraintLayout;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.rvDocuments = recyclerView;
        this.tvSubTitle = materialTextView;
        this.tvTitle = materialTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentDocumentPickerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentDocumentPickerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_document_picker, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentDocumentPickerBinding bind(View view) {
        int i = R.id.hk_layout_top_bar;
        View findChildViewById = ViewBindings.findChildViewById(view, i);
        if (findChildViewById != null) {
            HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
            i = R.id.includeBranding;
            View findChildViewById2 = ViewBindings.findChildViewById(view, i);
            if (findChildViewById2 != null) {
                HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                i = R.id.rvDocuments;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i);
                if (recyclerView != null) {
                    i = R.id.tvSubTitle;
                    MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                    if (materialTextView != null) {
                        i = R.id.tvTitle;
                        MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                        if (materialTextView2 != null) {
                            return new HkFragmentDocumentPickerBinding((ConstraintLayout) view, bind, bind2, recyclerView, materialTextView, materialTextView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
