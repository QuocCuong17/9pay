package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.textview.MaterialTextView;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewTextviewTextblockBinding implements ViewBinding {
    private final MaterialTextView rootView;

    private HkViewTextviewTextblockBinding(MaterialTextView materialTextView) {
        this.rootView = materialTextView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialTextView getRoot() {
        return this.rootView;
    }

    public static HkViewTextviewTextblockBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTextviewTextblockBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_textview_textblock, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTextviewTextblockBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewTextviewTextblockBinding((MaterialTextView) view);
    }
}
