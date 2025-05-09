package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewRadioButtonBinding implements ViewBinding {
    public final RadioButton radioButton;
    private final RadioButton rootView;

    private HkViewRadioButtonBinding(RadioButton radioButton, RadioButton radioButton2) {
        this.rootView = radioButton;
        this.radioButton = radioButton2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RadioButton getRoot() {
        return this.rootView;
    }

    public static HkViewRadioButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewRadioButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_radio_button, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewRadioButtonBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        RadioButton radioButton = (RadioButton) view;
        return new HkViewRadioButtonBinding(radioButton, radioButton);
    }
}
