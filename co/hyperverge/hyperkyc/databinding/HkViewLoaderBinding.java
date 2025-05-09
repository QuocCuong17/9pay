package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewLoaderBinding implements ViewBinding {
    public final ProgressBar hkFormLoader;
    private final ProgressBar rootView;

    private HkViewLoaderBinding(ProgressBar progressBar, ProgressBar progressBar2) {
        this.rootView = progressBar;
        this.hkFormLoader = progressBar2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ProgressBar getRoot() {
        return this.rootView;
    }

    public static HkViewLoaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewLoaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_loader, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewLoaderBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        ProgressBar progressBar = (ProgressBar) view;
        return new HkViewLoaderBinding(progressBar, progressBar);
    }
}
