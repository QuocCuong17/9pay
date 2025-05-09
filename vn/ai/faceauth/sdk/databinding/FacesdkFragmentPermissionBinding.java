package vn.ai.faceauth.sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;

/* loaded from: classes6.dex */
public final class FacesdkFragmentPermissionBinding implements ViewBinding {
    public final RelativeLayout bgLinearLayout;
    public final Button btnAccept;
    public final ImageView btnBack;
    private final RelativeLayout rootView;
    public final TextView toolbarText;
    public final TextView txtRequestAccess;
    public final TextView txtSub;

    private FacesdkFragmentPermissionBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, Button button, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.bgLinearLayout = relativeLayout2;
        this.btnAccept = button;
        this.btnBack = imageView;
        this.toolbarText = textView;
        this.txtRequestAccess = textView2;
        this.txtSub = textView3;
    }

    public static FacesdkFragmentPermissionBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i = R.id.btnAccept;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.btnBack;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.toolbarText;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    i = R.id.txtRequestAccess;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                    if (textView2 != null) {
                        i = R.id.txtSub;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                        if (textView3 != null) {
                            return new FacesdkFragmentPermissionBinding(relativeLayout, relativeLayout, button, imageView, textView, textView2, textView3);
                        }
                    }
                }
            }
        }
        throw new NullPointerException(tfwhgw.rnigpa(374).concat(view.getResources().getResourceName(i)));
    }

    public static FacesdkFragmentPermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FacesdkFragmentPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.facesdk_fragment_permission, viewGroup, false);
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
