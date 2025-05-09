package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.ClickableLinearLayout;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewContainerBinding implements ViewBinding {
    private final ClickableLinearLayout rootView;

    private HkViewContainerBinding(ClickableLinearLayout clickableLinearLayout) {
        this.rootView = clickableLinearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ClickableLinearLayout getRoot() {
        return this.rootView;
    }

    public static HkViewContainerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewContainerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_container, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewContainerBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewContainerBinding((ClickableLinearLayout) view);
    }
}
