package co.hyperverge.hyperkyc.ui.custom.blocks;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;

/* compiled from: BlockEditText.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditTextConfig;", "", "textInputKeyboard", "", "enable", "", "(IZ)V", "getEnable", "()Z", "getTextInputKeyboard", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BlockEditTextConfig {
    private final boolean enable;
    private final int textInputKeyboard;

    public static /* synthetic */ BlockEditTextConfig copy$default(BlockEditTextConfig blockEditTextConfig, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = blockEditTextConfig.textInputKeyboard;
        }
        if ((i2 & 2) != 0) {
            z = blockEditTextConfig.enable;
        }
        return blockEditTextConfig.copy(i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getTextInputKeyboard() {
        return this.textInputKeyboard;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getEnable() {
        return this.enable;
    }

    public final BlockEditTextConfig copy(int textInputKeyboard, boolean enable) {
        return new BlockEditTextConfig(textInputKeyboard, enable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlockEditTextConfig)) {
            return false;
        }
        BlockEditTextConfig blockEditTextConfig = (BlockEditTextConfig) other;
        return this.textInputKeyboard == blockEditTextConfig.textInputKeyboard && this.enable == blockEditTextConfig.enable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int i = this.textInputKeyboard * 31;
        boolean z = this.enable;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return i + i2;
    }

    public String toString() {
        return "BlockEditTextConfig(textInputKeyboard=" + this.textInputKeyboard + ", enable=" + this.enable + ')';
    }

    public BlockEditTextConfig(int i, boolean z) {
        this.textInputKeyboard = i;
        this.enable = z;
    }

    public final int getTextInputKeyboard() {
        return this.textInputKeyboard;
    }

    public final boolean getEnable() {
        return this.enable;
    }
}
