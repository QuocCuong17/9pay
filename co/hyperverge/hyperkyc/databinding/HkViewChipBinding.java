package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import com.google.android.material.chip.Chip;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewChipBinding implements ViewBinding {
    private final Chip rootView;

    private HkViewChipBinding(Chip chip) {
        this.rootView = chip;
    }

    @Override // androidx.viewbinding.ViewBinding
    public Chip getRoot() {
        return this.rootView;
    }

    public static HkViewChipBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewChipBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_chip, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewChipBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewChipBinding((Chip) view);
    }
}
