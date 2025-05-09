package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;

/* loaded from: classes2.dex */
public final class HkLayoutTopBarBinding implements ViewBinding {
    public final ImageView hkClientLogo;
    public final View hkDummyView;
    public final ImageView ivBack;
    private final ConstraintLayout rootView;

    private HkLayoutTopBarBinding(ConstraintLayout constraintLayout, ImageView imageView, View view, ImageView imageView2) {
        this.rootView = constraintLayout;
        this.hkClientLogo = imageView;
        this.hkDummyView = view;
        this.ivBack = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkLayoutTopBarBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkLayoutTopBarBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_layout_top_bar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkLayoutTopBarBinding bind(View view) {
        View findChildViewById;
        int i = R.id.hkClientLogo;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hkDummyView))) != null) {
            i = R.id.ivBack;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                return new HkLayoutTopBarBinding((ConstraintLayout) view, imageView, findChildViewById, imageView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
