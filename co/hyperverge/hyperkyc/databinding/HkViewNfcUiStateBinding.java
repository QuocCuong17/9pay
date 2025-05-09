package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.nfc.HKNFCProgressWheel;

/* loaded from: classes2.dex */
public final class HkViewNfcUiStateBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ImageView statusImage;
    public final HKNFCProgressWheel statusProgress;
    public final TextView statusText;

    private HkViewNfcUiStateBinding(LinearLayout linearLayout, ImageView imageView, HKNFCProgressWheel hKNFCProgressWheel, TextView textView) {
        this.rootView = linearLayout;
        this.statusImage = imageView;
        this.statusProgress = hKNFCProgressWheel;
        this.statusText = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static HkViewNfcUiStateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewNfcUiStateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_nfc_ui_state, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewNfcUiStateBinding bind(View view) {
        int i = R.id.status_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.status_progress;
            HKNFCProgressWheel hKNFCProgressWheel = (HKNFCProgressWheel) ViewBindings.findChildViewById(view, i);
            if (hKNFCProgressWheel != null) {
                i = R.id.status_text;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                if (textView != null) {
                    return new HkViewNfcUiStateBinding((LinearLayout) view, imageView, hKNFCProgressWheel, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
