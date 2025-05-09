package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes2.dex */
public final class HkViewTilDateBinding implements ViewBinding {
    public final TextInputEditText etDate;
    private final TextInputLayout rootView;

    private HkViewTilDateBinding(TextInputLayout textInputLayout, TextInputEditText textInputEditText) {
        this.rootView = textInputLayout;
        this.etDate = textInputEditText;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextInputLayout getRoot() {
        return this.rootView;
    }

    public static HkViewTilDateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTilDateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_til_date, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTilDateBinding bind(View view) {
        int i = R.id.etDate;
        TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, i);
        if (textInputEditText != null) {
            return new HkViewTilDateBinding((TextInputLayout) view, textInputEditText);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
