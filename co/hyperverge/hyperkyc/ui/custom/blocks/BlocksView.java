package co.hyperverge.hyperkyc.ui.custom.blocks;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import com.google.android.material.textview.MaterialTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BlocksView.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\r\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001dJ\b\u0010\u001e\u001a\u00020\u0013H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "blocksContainer", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainer;", "value", "", "error", "getError", "()Ljava/lang/String;", "setError", "(Ljava/lang/String;)V", "errorTextView", "Lcom/google/android/material/textview/MaterialTextView;", "clearError", "", "clearError$hyperkyc_release", "generateBlocksView", "layoutConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewConfig;", "generateBlocksView$hyperkyc_release", "setText", "text", "setText$hyperkyc_release", "showSuccess", "showSuccess$hyperkyc_release", "updateError", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlocksView extends LinearLayout {
    private BlocksContainer blocksContainer;
    private String error;
    private MaterialTextView errorTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlocksView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
    }

    public final String getError() {
        return this.error;
    }

    public final void setError(String str) {
        this.error = str;
        updateError();
    }

    public final void generateBlocksView$hyperkyc_release(BlocksViewConfig layoutConfig) {
        Intrinsics.checkNotNullParameter(layoutConfig, "layoutConfig");
        MaterialTextView materialTextView = new MaterialTextView(getContext());
        materialTextView.setTextColor(Color.parseColor(layoutConfig.getErrorTextViewConfig().getColor()));
        materialTextView.setTextSize(2, layoutConfig.getErrorTextViewConfig().getTextSize());
        materialTextView.setGravity(GravityCompat.END);
        materialTextView.setText("");
        materialTextView.setVisibility(8);
        this.errorTextView = materialTextView;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.blocksContainer = new BlocksContainer(context, layoutConfig.getBlocksContainerConfig());
        removeAllViews();
        BlocksContainer blocksContainer = this.blocksContainer;
        BlocksContainer blocksContainer2 = null;
        if (blocksContainer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blocksContainer");
            blocksContainer = null;
        }
        addView(blocksContainer);
        MaterialTextView materialTextView2 = this.errorTextView;
        if (materialTextView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorTextView");
            materialTextView2 = null;
        }
        addView(materialTextView2);
        BlocksContainer blocksContainer3 = this.blocksContainer;
        if (blocksContainer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blocksContainer");
        } else {
            blocksContainer2 = blocksContainer3;
        }
        blocksContainer2.setBlocksViewListener(layoutConfig.getBlocksViewListener());
        String blockText = layoutConfig.getBlocksContainerConfig().getBlockText();
        setText$hyperkyc_release(blockText != null ? blockText : "");
    }

    public final void setText$hyperkyc_release(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        BlocksContainer blocksContainer = this.blocksContainer;
        if (blocksContainer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blocksContainer");
            blocksContainer = null;
        }
        blocksContainer.setText$hyperkyc_release(text, true);
    }

    private final void updateError() {
        String str = this.error;
        if (!(str == null || StringsKt.isBlank(str))) {
            BlocksContainer blocksContainer = this.blocksContainer;
            MaterialTextView materialTextView = null;
            if (blocksContainer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("blocksContainer");
                blocksContainer = null;
            }
            blocksContainer.updateBlocksState$hyperkyc_release(-1);
            MaterialTextView materialTextView2 = this.errorTextView;
            if (materialTextView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorTextView");
                materialTextView2 = null;
            }
            materialTextView2.setText(this.error);
            MaterialTextView materialTextView3 = this.errorTextView;
            if (materialTextView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("errorTextView");
            } else {
                materialTextView = materialTextView3;
            }
            materialTextView.setVisibility(0);
            return;
        }
        clearError$hyperkyc_release();
    }

    public final void clearError$hyperkyc_release() {
        MaterialTextView materialTextView = this.errorTextView;
        MaterialTextView materialTextView2 = null;
        if (materialTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorTextView");
            materialTextView = null;
        }
        materialTextView.setText(this.error);
        MaterialTextView materialTextView3 = this.errorTextView;
        if (materialTextView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorTextView");
        } else {
            materialTextView2 = materialTextView3;
        }
        materialTextView2.setVisibility(8);
    }

    public final void showSuccess$hyperkyc_release() {
        clearError$hyperkyc_release();
        BlocksContainer blocksContainer = this.blocksContainer;
        if (blocksContainer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blocksContainer");
            blocksContainer = null;
        }
        blocksContainer.updateBlocksState$hyperkyc_release(0);
    }
}
