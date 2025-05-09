package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkFragmentFormBinding implements ViewBinding {
    public final MaterialButton btnNegative;
    public final MaterialButton btnPositive;
    public final ConstraintLayout clLegacyUI;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    public final LinearLayout llContent;
    public final LinearLayout llFooter;
    private final ConstraintLayout rootView;
    public final MaterialTextView tvSubTitle;
    public final MaterialTextView tvTitle;

    private HkFragmentFormBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, MaterialButton materialButton2, ConstraintLayout constraintLayout2, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, LinearLayout linearLayout, LinearLayout linearLayout2, MaterialTextView materialTextView, MaterialTextView materialTextView2) {
        this.rootView = constraintLayout;
        this.btnNegative = materialButton;
        this.btnPositive = materialButton2;
        this.clLegacyUI = constraintLayout2;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.llContent = linearLayout;
        this.llFooter = linearLayout2;
        this.tvSubTitle = materialTextView;
        this.tvTitle = materialTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentFormBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentFormBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_form, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentFormBinding bind(View view) {
        View findChildViewById;
        int i = R.id.btnNegative;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            i = R.id.btnPositive;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton2 != null) {
                i = R.id.clLegacyUI;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i);
                if (constraintLayout != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
                    HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
                    i = R.id.includeBranding;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, i);
                    if (findChildViewById2 != null) {
                        HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                        i = R.id.llContent;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                        if (linearLayout != null) {
                            i = R.id.llFooter;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout2 != null) {
                                i = R.id.tvSubTitle;
                                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                if (materialTextView != null) {
                                    i = R.id.tvTitle;
                                    MaterialTextView materialTextView2 = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                                    if (materialTextView2 != null) {
                                        return new HkFragmentFormBinding((ConstraintLayout) view, materialButton, materialButton2, constraintLayout, bind, bind2, linearLayout, linearLayout2, materialTextView, materialTextView2);
                                    }
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
