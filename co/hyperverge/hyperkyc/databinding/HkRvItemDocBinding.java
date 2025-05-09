package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;

/* loaded from: classes2.dex */
public final class HkRvItemDocBinding implements ViewBinding {
    public final MaterialButton btnDocument;
    public final LinearLayout documentItemConstraintLayout;
    private final LinearLayout rootView;

    private HkRvItemDocBinding(LinearLayout linearLayout, MaterialButton materialButton, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.btnDocument = materialButton;
        this.documentItemConstraintLayout = linearLayout2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static HkRvItemDocBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemDocBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_doc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemDocBinding bind(View view) {
        int i = R.id.btnDocument;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            return new HkRvItemDocBinding(linearLayout, materialButton, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
