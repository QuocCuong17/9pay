package co.hyperverge.hyperkyc.ui.custom.blocks;

import android.content.Context;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.ui.custom.IMEAwareTextInputEditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlockEditText.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditText;", "Lco/hyperverge/hyperkyc/ui/custom/IMEAwareTextInputEditText;", "context", "Landroid/content/Context;", "blockEditTextConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditTextConfig;", "(Landroid/content/Context;Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditTextConfig;)V", "blocksViewListener", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;", "getBlocksViewListener", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;", "setBlocksViewListener", "(Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;)V", "initialiseBlockEditText", "", "onSelectionChanged", "start", "", TtmlNode.END, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlockEditText extends IMEAwareTextInputEditText {
    private final BlockEditTextConfig blockEditTextConfig;
    private BlocksViewListener blocksViewListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BlockEditText(Context context, BlockEditTextConfig blockEditTextConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(blockEditTextConfig, "blockEditTextConfig");
        this.blockEditTextConfig = blockEditTextConfig;
        initialiseBlockEditText();
    }

    public final BlocksViewListener getBlocksViewListener() {
        return this.blocksViewListener;
    }

    public final void setBlocksViewListener(BlocksViewListener blocksViewListener) {
        this.blocksViewListener = blocksViewListener;
    }

    private final void initialiseBlockEditText() {
        setCursorVisible(false);
        setTextColor(0);
        setBackground(null);
        setEnabled(this.blockEditTextConfig.getEnable());
        setInputType(this.blockEditTextConfig.getTextInputKeyboard());
        setSelectAllOnFocus(false);
        setTextIsSelectable(false);
        setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: co.hyperverge.hyperkyc.ui.custom.blocks.BlockEditText$$ExternalSyntheticLambda0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                BlockEditText.initialiseBlockEditText$lambda$0(BlockEditText.this, view, z);
            }
        });
        setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: co.hyperverge.hyperkyc.ui.custom.blocks.BlockEditText$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean initialiseBlockEditText$lambda$1;
                initialiseBlockEditText$lambda$1 = BlockEditText.initialiseBlockEditText$lambda$1(BlockEditText.this, textView, i, keyEvent);
                return initialiseBlockEditText$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialiseBlockEditText$lambda$0(BlockEditText this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BlocksViewListener blocksViewListener = this$0.blocksViewListener;
        if (blocksViewListener != null) {
            blocksViewListener.onFocusChanged(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initialiseBlockEditText$lambda$1(BlockEditText this$0, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 6) {
            return false;
        }
        this$0.clearFocus();
        return false;
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int start, int end) {
        Editable text = getText();
        if (text != null && (start != text.length() || end != text.length())) {
            setSelection(text.length(), text.length());
        } else {
            super.onSelectionChanged(start, end);
        }
    }
}
