package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewButtonTertiaryBinding implements ViewBinding {
    private final MaterialButton rootView;

    private HkViewButtonTertiaryBinding(MaterialButton materialButton) {
        this.rootView = materialButton;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialButton getRoot() {
        return this.rootView;
    }

    public static HkViewButtonTertiaryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewButtonTertiaryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_button_tertiary, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewButtonTertiaryBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewButtonTertiaryBinding((MaterialButton) view);
    }
}
