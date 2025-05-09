package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkRvItemCountryBinding implements ViewBinding {
    public final ImageView ivCheck;
    public final ImageView ivFlag;
    private final ConstraintLayout rootView;
    public final MaterialTextView tvName;

    private HkRvItemCountryBinding(ConstraintLayout constraintLayout, ImageView imageView, ImageView imageView2, MaterialTextView materialTextView) {
        this.rootView = constraintLayout;
        this.ivCheck = imageView;
        this.ivFlag = imageView2;
        this.tvName = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkRvItemCountryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkRvItemCountryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_rv_item_country, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkRvItemCountryBinding bind(View view) {
        int i = R.id.ivCheck;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.ivFlag;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.tvName;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    return new HkRvItemCountryBinding((ConstraintLayout) view, imageView, imageView2, materialTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
