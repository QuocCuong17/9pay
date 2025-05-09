package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

/* loaded from: classes2.dex */
public final class HkFragmentWebviewBinding implements ViewBinding {
    public final MaterialButton btnRetry;
    public final HkLayoutTopBarBinding hkLayoutTopBar;
    public final ProgressBar pbLoading;
    private final ConstraintLayout rootView;
    public final MaterialTextView tvRetryTitle;
    public final WebView webView;

    private HkFragmentWebviewBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, HkLayoutTopBarBinding hkLayoutTopBarBinding, ProgressBar progressBar, MaterialTextView materialTextView, WebView webView) {
        this.rootView = constraintLayout;
        this.btnRetry = materialButton;
        this.hkLayoutTopBar = hkLayoutTopBarBinding;
        this.pbLoading = progressBar;
        this.tvRetryTitle = materialTextView;
        this.webView = webView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static HkFragmentWebviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkFragmentWebviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_fragment_webview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkFragmentWebviewBinding bind(View view) {
        View findChildViewById;
        int i = R.id.btnRetry;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, i);
        if (materialButton != null && (findChildViewById = ViewBindings.findChildViewById(view, (i = R.id.hk_layout_top_bar))) != null) {
            HkLayoutTopBarBinding bind = HkLayoutTopBarBinding.bind(findChildViewById);
            i = R.id.pbLoading;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                i = R.id.tvRetryTitle;
                MaterialTextView materialTextView = (MaterialTextView) ViewBindings.findChildViewById(view, i);
                if (materialTextView != null) {
                    i = R.id.webView;
                    WebView webView = (WebView) ViewBindings.findChildViewById(view, i);
                    if (webView != null) {
                        return new HkFragmentWebviewBinding((ConstraintLayout) view, materialButton, bind, progressBar, materialTextView, webView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
