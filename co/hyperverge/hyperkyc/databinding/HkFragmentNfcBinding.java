package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;

/* loaded from: classes2.dex */
public final class HkFragmentNfcBinding implements ViewBinding {
    public final LottieAnimationView animationPlaceholder;
    public final MaterialButton btnRetry;
    public final MaterialButton btnSkip;
    public final LinearLayout buttonLayout;
    public final TextView descText;
    public final ConstraintLayout fragmentContainer;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    private final ConstraintLayout rootView;
    public final TextView titleText;
    public final LinearLayout uiStatesLayout;

    private HkFragmentNfcBinding(ConstraintLayout constraintLayout, LottieAnimationView lottieAnimationView, MaterialButton materialButton, MaterialButton materialButton2, LinearLayout linearLayout, TextView textView, ConstraintLayout constraintLayout2, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, TextView textView2, LinearLayout linearLayout2) {
        this.rootView = constraintLayout;
        this.animationPlaceholder = lottieAnimationView;
        this.btnRetry = materialButton;
        this.btnSkip = materialButton2;
        this.buttonLayout = linearLayout;
        this.descText = textView;
        this.fragmentContainer = constraintLayout2;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.titleText = textView2;
        this.uiStatesLayout = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentNfcBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentNfcBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_nfc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentNfcBinding bind(View view) {
        int i = R.id.animation_placeholder;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
        if (lottieAnimationView != null) {
            i = R.id.btnRetry;
            MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
            if (materialButton != null) {
                i = R.id.btnSkip;
                MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, i);
                if (materialButton2 != null) {
                    i = R.id.button_layout;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                    if (linearLayout != null) {
                        i = R.id.desc_text;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView != null) {
                            ConstraintLayout constraintLayout = (ConstraintLayout) view;
                            i = R.id.hk_layout_top_bar;
                            View findChildViewById = ViewBindings.findChildViewById(view, i);
                            if (findChildViewById != null) {
                                HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
                                i = R.id.includeBranding;
                                View findChildViewById2 = ViewBindings.findChildViewById(view, i);
                                if (findChildViewById2 != null) {
                                    HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                                    i = R.id.title_text;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                    if (textView2 != null) {
                                        i = R.id.ui_states_layout;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                                        if (linearLayout2 != null) {
                                            return new HkFragmentNfcBinding(constraintLayout, lottieAnimationView, materialButton, materialButton2, linearLayout, textView, constraintLayout, bind, bind2, textView2, linearLayout2);
                                        }
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
