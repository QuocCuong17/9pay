package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.card.MaterialCardView;

/* loaded from: classes2.dex */
public final class HkRvItemFormAddFileBinding implements ViewBinding {
    public final AppCompatImageView ivAdd;
    private final MaterialCardView rootView;

    private HkRvItemFormAddFileBinding(MaterialCardView materialCardView, AppCompatImageView appCompatImageView) {
        this.rootView = materialCardView;
        this.ivAdd = appCompatImageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static HkRvItemFormAddFileBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemFormAddFileBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_form_add_file, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemFormAddFileBinding bind(View view) {
        int i = R.id.ivAdd;
        AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, i);
        if (appCompatImageView != null) {
            return new HkRvItemFormAddFileBinding((MaterialCardView) view, appCompatImageView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
