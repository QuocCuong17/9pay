package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.airbnb.lottie.LottieAnimationView;

/* loaded from: classes2.dex */
public final class HkActivityBrowserBinding implements ViewBinding {
    public final ConstraintLayout browserContent;
    public final LottieAnimationView browserLoader;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    private final ConstraintLayout rootView;

    private HkActivityBrowserBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, LottieAnimationView lottieAnimationView, HkLayoutTopBarBinding hkLayoutTopBarBinding) {
        this.rootView = constraintLayout;
        this.browserContent = constraintLayout2;
        this.browserLoader = lottieAnimationView;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkActivityBrowserBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkActivityBrowserBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_activity_browser, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkActivityBrowserBinding bind(View view) {
        View findChildViewById;
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        int i = R.id.browserLoader;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, i);
        if (lottieAnimationView != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
            return new HkActivityBrowserBinding(constraintLayout, constraintLayout, lottieAnimationView, HkLayoutTopBarBinding.bind(findChildViewById));
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
