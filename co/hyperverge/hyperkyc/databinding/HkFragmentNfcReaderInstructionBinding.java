package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;

/* loaded from: classes2.dex */
public final class HkFragmentNfcReaderInstructionBinding implements ViewBinding {
    public final MaterialButton btnProceed;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    public final LottieAnimationView lavNFCInstructions;
    private final ConstraintLayout rootView;
    public final TextView tvSubtitle;
    public final TextView tvTitle;

    private HkFragmentNfcReaderInstructionBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, LottieAnimationView lottieAnimationView, TextView textView, TextView textView2) {
        this.rootView = constraintLayout;
        this.btnProceed = materialButton;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.lavNFCInstructions = lottieAnimationView;
        this.tvSubtitle = textView;
        this.tvTitle = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentNfcReaderInstructionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentNfcReaderInstructionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_nfc_reader_instruction, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentNfcReaderInstructionBinding bind(View view) {
        View findChildViewById;
        int i = R.id.btnProceed;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
            HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
            i = R.id.includeBranding;
            View findChildViewById2 = ViewBindings.findChildViewById(view, i);
            if (findChildViewById2 != null) {
                HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                i = R.id.lavNFCInstructions;
                LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
                if (lottieAnimationView != null) {
                    i = R.id.tvSubtitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        i = R.id.tvTitle;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView2 != null) {
                            return new HkFragmentNfcReaderInstructionBinding((ConstraintLayout) view, materialButton, bind, bind2, lottieAnimationView, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
