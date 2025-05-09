package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes2.dex */
public final class HkViewTilDropdownBinding implements ViewBinding {
    public final AutoCompleteTextView actvDropdown;
    private final TextInputLayout rootView;

    private HkViewTilDropdownBinding(TextInputLayout textInputLayout, AutoCompleteTextView autoCompleteTextView) {
        this.rootView = textInputLayout;
        this.actvDropdown = autoCompleteTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public TextInputLayout getRoot() {
        return this.rootView;
    }

    public static HkViewTilDropdownBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTilDropdownBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_til_dropdown, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTilDropdownBinding bind(View view) {
        int i = R.id.actvDropdown;
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) ViewBindings.findChildViewById(view, i);
        if (autoCompleteTextView != null) {
            return new HkViewTilDropdownBinding((TextInputLayout) view, autoCompleteTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
