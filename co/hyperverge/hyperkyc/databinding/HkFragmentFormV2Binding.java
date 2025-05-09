package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkFragmentFormV2Binding implements ViewBinding {
    public final ConstraintLayout hkWebFormContainer;
    private final ConstraintLayout rootView;

    private HkFragmentFormV2Binding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.hkWebFormContainer = constraintLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentFormV2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentFormV2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_form_v2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentFormV2Binding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        ConstraintLayout constraintLayout = (ConstraintLayout) view;
        return new HkFragmentFormV2Binding(constraintLayout, constraintLayout);
    }
}
