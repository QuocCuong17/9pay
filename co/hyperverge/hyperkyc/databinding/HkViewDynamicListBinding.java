package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.DynamicListGridLayout;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewDynamicListBinding implements ViewBinding {
    private final DynamicListGridLayout rootView;

    private HkViewDynamicListBinding(DynamicListGridLayout dynamicListGridLayout) {
        this.rootView = dynamicListGridLayout;
    }

    @Override // androidx.viewbinding.ViewBinding
    public DynamicListGridLayout getRoot() {
        return this.rootView;
    }

    public static HkViewDynamicListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewDynamicListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_dynamic_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewDynamicListBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewDynamicListBinding((DynamicListGridLayout) view);
    }
}
