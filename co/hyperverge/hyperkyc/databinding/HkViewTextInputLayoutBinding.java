package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.IMEAwareTextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes2.dex */
public final class HkViewTextInputLayoutBinding implements ViewBinding {
    private final TextInputLayout rootView;
    public final IMEAwareTextInputEditText textInputEditText;

    private HkViewTextInputLayoutBinding(TextInputLayout textInputLayout, IMEAwareTextInputEditText iMEAwareTextInputEditText) {
        this.rootView = textInputLayout;
        this.textInputEditText = iMEAwareTextInputEditText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextInputLayout getRoot() {
        return this.rootView;
    }

    public static HkViewTextInputLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTextInputLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_text_input_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTextInputLayoutBinding bind(View view) {
        int i = R.id.textInputEditText;
        IMEAwareTextInputEditText iMEAwareTextInputEditText = (IMEAwareTextInputEditText) ViewBindings.findChildViewById(view, i);
        if (iMEAwareTextInputEditText != null) {
            return new HkViewTextInputLayoutBinding((TextInputLayout) view, iMEAwareTextInputEditText);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
