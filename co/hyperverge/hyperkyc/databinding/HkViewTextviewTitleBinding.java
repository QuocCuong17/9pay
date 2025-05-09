package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewTextviewTitleBinding implements ViewBinding {
    private final MaterialTextView rootView;

    private HkViewTextviewTitleBinding(MaterialTextView materialTextView) {
        this.rootView = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialTextView getRoot() {
        return this.rootView;
    }

    public static HkViewTextviewTitleBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTextviewTitleBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_textview_title, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTextviewTitleBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewTextviewTitleBinding((MaterialTextView) view);
    }
}
