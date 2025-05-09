package vn.ai.faceauth.sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;

/* loaded from: classes6.dex */
public final class FacesdkCameraxActivityBinding implements ViewBinding {
    public final FrameLayout fragmentContainer;
    private final RelativeLayout rootView;

    private FacesdkCameraxActivityBinding(RelativeLayout relativeLayout, FrameLayout frameLayout) {
        this.rootView = relativeLayout;
        this.fragmentContainer = frameLayout;
    }

    public static FacesdkCameraxActivityBinding bind(View view) {
        int i = R.id.fragmentContainer;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            return new FacesdkCameraxActivityBinding((RelativeLayout) view, frameLayout);
        }
        throw new NullPointerException(tfwhgw.rnigpa(BaselineTIFFTagSet.TAG_FREE_BYTE_COUNTS).concat(view.getResources().getResourceName(i)));
    }

    public static FacesdkCameraxActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FacesdkCameraxActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.facesdk_camerax_activity, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
