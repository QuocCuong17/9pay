package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewButtonSecondaryBinding implements ViewBinding {
    private final MaterialButton rootView;

    private HkViewButtonSecondaryBinding(MaterialButton materialButton) {
        this.rootView = materialButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static HkViewButtonSecondaryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewButtonSecondaryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_button_secondary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewButtonSecondaryBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewButtonSecondaryBinding((MaterialButton) view);
    }
}
