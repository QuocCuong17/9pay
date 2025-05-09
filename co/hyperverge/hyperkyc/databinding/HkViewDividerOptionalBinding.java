package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import co.hyperverge.hyperkyc.R;

/* loaded from: classes2.dex */
public final class HkViewDividerOptionalBinding implements ViewBinding {
    public final FrameLayout hkDividerLayout;
    private final FrameLayout rootView;
    public final TextView tvLabel;

    private HkViewDividerOptionalBinding(FrameLayout frameLayout, FrameLayout frameLayout2, TextView textView) {
        this.rootView = frameLayout;
        this.hkDividerLayout = frameLayout2;
        this.tvLabel = textView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static HkViewDividerOptionalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewDividerOptionalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_divider_optional, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewDividerOptionalBinding bind(View view) {
        FrameLayout frameLayout = (FrameLayout) view;
        int i = R.id.tvLabel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            return new HkViewDividerOptionalBinding(frameLayout, frameLayout, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
