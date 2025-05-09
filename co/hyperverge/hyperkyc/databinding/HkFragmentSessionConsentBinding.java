package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;

/* loaded from: classes2.dex */
public final class HkFragmentSessionConsentBinding implements ViewBinding {
    public final MaterialButton btnConfirm;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    private final ConstraintLayout rootView;
    public final TextView tvDesc;
    public final TextView tvTitle;

    private HkFragmentSessionConsentBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.btnConfirm = materialButton;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.tvDesc = textView;
        this.tvTitle = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentSessionConsentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentSessionConsentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_session_consent, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentSessionConsentBinding bind(View view) {
        View findChildViewById;
        int i = R.id.btnConfirm;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
            HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
            i = R.id.includeBranding;
            View findChildViewById2 = ViewBindings.findChildViewById(view, i);
            if (findChildViewById2 != null) {
                HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                i = R.id.tvDesc;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.tvTitle;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        return new HkFragmentSessionConsentBinding((ConstraintLayout) view, materialButton, bind, bind2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
