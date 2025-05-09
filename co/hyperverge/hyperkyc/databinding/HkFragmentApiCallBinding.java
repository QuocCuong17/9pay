package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;

/* loaded from: classes2.dex */
public final class HkFragmentApiCallBinding implements ViewBinding {
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final HkLayoutBrandingBinding includeBranding;
    public final ImageView progressSpinnerImageView;
    private final ConstraintLayout rootView;
    public final TextView tvLoadingMsg;

    private HkFragmentApiCallBinding(ConstraintLayout constraintLayout, HkLayoutTopBarBinding hkLayoutTopBarBinding, HkLayoutBrandingBinding hkLayoutBrandingBinding, ImageView imageView, TextView textView) {
        this.rootView = constraintLayout;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.includeBranding = hkLayoutBrandingBinding;
        this.progressSpinnerImageView = imageView;
        this.tvLoadingMsg = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentApiCallBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentApiCallBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_api_call, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentApiCallBinding bind(View view) {
        int i = R.id.hk_layout_top_bar;
        View findChildViewById = ViewBindings.findChildViewById(view, i);
        if (findChildViewById != null) {
            HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
            i = R.id.includeBranding;
            View findChildViewById2 = ViewBindings.findChildViewById(view, i);
            if (findChildViewById2 != null) {
                HkLayoutBrandingBinding bind2 = HkLayoutBrandingBinding.bind(findChildViewById2);
                i = R.id.progressSpinnerImageView;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    i = R.id.tvLoadingMsg;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView != null) {
                        return new HkFragmentApiCallBinding((ConstraintLayout) view, bind, bind2, imageView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
