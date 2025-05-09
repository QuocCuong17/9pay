package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewImageBinding implements ViewBinding {
    public final ImageView hkFormImageView;
    private final ImageView rootView;

    private HkViewImageBinding(ImageView imageView, ImageView imageView2) {
        this.rootView = imageView;
        this.hkFormImageView = imageView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ImageView getRoot() {
        return this.rootView;
    }

    public static HkViewImageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewImageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_image, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewImageBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        ImageView imageView = (ImageView) view;
        return new HkViewImageBinding(imageView, imageView);
    }
}
