package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.ClickableLinearLayout;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkRvItemFormListBinding implements ViewBinding {
    private final ClickableLinearLayout rootView;

    private HkRvItemFormListBinding(ClickableLinearLayout clickableLinearLayout) {
        this.rootView = clickableLinearLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ClickableLinearLayout getRoot() {
        return this.rootView;
    }

    public static HkRvItemFormListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemFormListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_form_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemFormListBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkRvItemFormListBinding((ClickableLinearLayout) view);
    }
}
