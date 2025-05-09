package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;

/* loaded from: classes2.dex */
public final class HkActivityWebCoreBinding implements ViewBinding {
    public final FrameLayout flContent;
    private final ConstraintLayout rootView;
    public final WebView webCoreWebView;

    private HkActivityWebCoreBinding(ConstraintLayout constraintLayout, FrameLayout frameLayout, WebView webView) {
        this.rootView = constraintLayout;
        this.flContent = frameLayout;
        this.webCoreWebView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkActivityWebCoreBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkActivityWebCoreBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_activity_web_core, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkActivityWebCoreBinding bind(View view) {
        int i = R.id.flContent;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
        if (frameLayout != null) {
            i = R.id.webCoreWebView;
            WebView webView = (WebView) ViewBindings.findChildViewById(view, i);
            if (webView != null) {
                return new HkActivityWebCoreBinding((ConstraintLayout) view, frameLayout, webView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
