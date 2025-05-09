package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewButtonPrimaryBinding implements ViewBinding {
    private final MaterialButton rootView;

    private HkViewButtonPrimaryBinding(MaterialButton materialButton) {
        this.rootView = materialButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static HkViewButtonPrimaryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewButtonPrimaryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_button_primary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewButtonPrimaryBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewButtonPrimaryBinding((MaterialButton) view);
    }
}
