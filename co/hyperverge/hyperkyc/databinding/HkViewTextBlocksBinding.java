package co.hyperverge.hyperkyc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import co.hyperverge.hyperkyc.R;
import co.hyperverge.hyperkyc.ui.custom.blocks.BlocksView;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class HkViewTextBlocksBinding implements ViewBinding {
    private final BlocksView rootView;

    private HkViewTextBlocksBinding(BlocksView blocksView) {
        this.rootView = blocksView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public BlocksView getRoot() {
        return this.rootView;
    }

    public static HkViewTextBlocksBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static HkViewTextBlocksBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.hk_view_text_blocks, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static HkViewTextBlocksBinding bind(View view) {
        Objects.requireNonNull(view, "rootView");
        return new HkViewTextBlocksBinding((BlocksView) view);
    }
}
