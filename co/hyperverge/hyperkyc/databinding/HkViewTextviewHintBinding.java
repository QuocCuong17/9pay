package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkViewTextviewHintBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final MaterialTextView tvHintLabel;

    private HkViewTextviewHintBinding(LinearLayout linearLayout, MaterialTextView materialTextView) {
        this.rootView = linearLayout;
        this.tvHintLabel = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static HkViewTextviewHintBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTextviewHintBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_textview_hint, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTextviewHintBinding bind(View view) {
        int i = R.id.tvHintLabel;
        MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
        if (materialTextView != null) {
            return new HkViewTextviewHintBinding((LinearLayout) view, materialTextView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
