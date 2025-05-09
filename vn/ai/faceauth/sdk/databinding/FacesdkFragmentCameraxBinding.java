package vn.ai.faceauth.sdk.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.camera.view.PreviewView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.R;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.GraphicOverlay;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.OverlayView;

/* loaded from: classes6.dex */
public final class FacesdkFragmentCameraxBinding implements ViewBinding {
    public final Button btnAccept;
    public final ImageView btnBack;
    public final Button btnTryAgain;
    public final RelativeLayout clRoot;
    public final GraphicOverlay graphicOverlay;
    public final ImageView imgSuccess;
    public final LinearLayout layoutStatus;
    public final RelativeLayout layoutSuccess;
    public final OverlayView overlayView;
    private final RelativeLayout rootView;
    public final TextView toolbarText;
    public final TextView tvFailedText;
    public final TextView tvStatusText;
    public final TextView tvSuccessText;
    public final PreviewView viewFinder;

    private FacesdkFragmentCameraxBinding(RelativeLayout relativeLayout, Button button, ImageView imageView, Button button2, RelativeLayout relativeLayout2, GraphicOverlay graphicOverlay, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout3, OverlayView overlayView, TextView textView, TextView textView2, TextView textView3, TextView textView4, PreviewView previewView) {
        this.rootView = relativeLayout;
        this.btnAccept = button;
        this.btnBack = imageView;
        this.btnTryAgain = button2;
        this.clRoot = relativeLayout2;
        this.graphicOverlay = graphicOverlay;
        this.imgSuccess = imageView2;
        this.layoutStatus = linearLayout;
        this.layoutSuccess = relativeLayout3;
        this.overlayView = overlayView;
        this.toolbarText = textView;
        this.tvFailedText = textView2;
        this.tvStatusText = textView3;
        this.tvSuccessText = textView4;
        this.viewFinder = previewView;
    }

    public static FacesdkFragmentCameraxBinding bind(View view) {
        int i = R.id.btnAccept;
        Button button = (Button) ViewBindings.findChildViewById(view, i);
        if (button != null) {
            i = R.id.btnBack;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView != null) {
                i = R.id.btnTryAgain;
                Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                if (button2 != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    i = R.id.graphicOverlay;
                    GraphicOverlay graphicOverlay = (GraphicOverlay) ViewBindings.findChildViewById(view, i);
                    if (graphicOverlay != null) {
                        i = R.id.imgSuccess;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
                        if (imageView2 != null) {
                            i = R.id.layoutStatus;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
                            if (linearLayout != null) {
                                i = R.id.layoutSuccess;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, i);
                                if (relativeLayout2 != null) {
                                    i = R.id.overlayView;
                                    OverlayView overlayView = (OverlayView) ViewBindings.findChildViewById(view, i);
                                    if (overlayView != null) {
                                        i = R.id.toolbarText;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
                                        if (textView != null) {
                                            i = R.id.tvFailedText;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
                                            if (textView2 != null) {
                                                i = R.id.tvStatusText;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i);
                                                if (textView3 != null) {
                                                    i = R.id.tvSuccessText;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, i);
                                                    if (textView4 != null) {
                                                        i = R.id.viewFinder;
                                                        PreviewView previewView = (PreviewView) ViewBindings.findChildViewById(view, i);
                                                        if (previewView != null) {
                                                            return new FacesdkFragmentCameraxBinding(relativeLayout, button, imageView, button2, relativeLayout, graphicOverlay, imageView2, linearLayout, relativeLayout2, overlayView, textView, textView2, textView3, textView4, previewView);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException(tfwhgw.rnigpa(16).concat(view.getResources().getResourceName(i)));
    }

    public static FacesdkFragmentCameraxBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FacesdkFragmentCameraxBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.facesdk_fragment_camerax, viewGroup, false);
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
