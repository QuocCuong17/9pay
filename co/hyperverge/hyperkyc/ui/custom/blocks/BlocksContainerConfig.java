package co.hyperverge.hyperkyc.ui.custom.blocks;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlocksContainer.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\fHÆ\u0003JQ\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0007HÖ\u0001J\t\u0010%\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006&"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainerConfig;", "", "blockEditTextConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditTextConfig;", "blockItemViewConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemViewConfig;", "blockCount", "", "blockLength", "blockWidth", "blockHeight", "blockText", "", "(Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditTextConfig;Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemViewConfig;IIIILjava/lang/String;)V", "getBlockCount", "()I", "getBlockEditTextConfig", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockEditTextConfig;", "getBlockHeight", "getBlockItemViewConfig", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemViewConfig;", "getBlockLength", "getBlockText", "()Ljava/lang/String;", "getBlockWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BlocksContainerConfig {
    private final int blockCount;
    private final BlockEditTextConfig blockEditTextConfig;
    private final int blockHeight;
    private final BlockItemViewConfig blockItemViewConfig;
    private final int blockLength;
    private final String blockText;
    private final int blockWidth;

    public static /* synthetic */ BlocksContainerConfig copy$default(BlocksContainerConfig blocksContainerConfig, BlockEditTextConfig blockEditTextConfig, BlockItemViewConfig blockItemViewConfig, int i, int i2, int i3, int i4, String str, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            blockEditTextConfig = blocksContainerConfig.blockEditTextConfig;
        }
        if ((i5 & 2) != 0) {
            blockItemViewConfig = blocksContainerConfig.blockItemViewConfig;
        }
        BlockItemViewConfig blockItemViewConfig2 = blockItemViewConfig;
        if ((i5 & 4) != 0) {
            i = blocksContainerConfig.blockCount;
        }
        int i6 = i;
        if ((i5 & 8) != 0) {
            i2 = blocksContainerConfig.blockLength;
        }
        int i7 = i2;
        if ((i5 & 16) != 0) {
            i3 = blocksContainerConfig.blockWidth;
        }
        int i8 = i3;
        if ((i5 & 32) != 0) {
            i4 = blocksContainerConfig.blockHeight;
        }
        int i9 = i4;
        if ((i5 & 64) != 0) {
            str = blocksContainerConfig.blockText;
        }
        return blocksContainerConfig.copy(blockEditTextConfig, blockItemViewConfig2, i6, i7, i8, i9, str);
    }

    /* renamed from: component1, reason: from getter */
    public final BlockEditTextConfig getBlockEditTextConfig() {
        return this.blockEditTextConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final BlockItemViewConfig getBlockItemViewConfig() {
        return this.blockItemViewConfig;
    }

    /* renamed from: component3, reason: from getter */
    public final int getBlockCount() {
        return this.blockCount;
    }

    /* renamed from: component4, reason: from getter */
    public final int getBlockLength() {
        return this.blockLength;
    }

    /* renamed from: component5, reason: from getter */
    public final int getBlockWidth() {
        return this.blockWidth;
    }

    /* renamed from: component6, reason: from getter */
    public final int getBlockHeight() {
        return this.blockHeight;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBlockText() {
        return this.blockText;
    }

    public final BlocksContainerConfig copy(BlockEditTextConfig blockEditTextConfig, BlockItemViewConfig blockItemViewConfig, int blockCount, int blockLength, int blockWidth, int blockHeight, String blockText) {
        Intrinsics.checkNotNullParameter(blockEditTextConfig, "blockEditTextConfig");
        Intrinsics.checkNotNullParameter(blockItemViewConfig, "blockItemViewConfig");
        return new BlocksContainerConfig(blockEditTextConfig, blockItemViewConfig, blockCount, blockLength, blockWidth, blockHeight, blockText);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlocksContainerConfig)) {
            return false;
        }
        BlocksContainerConfig blocksContainerConfig = (BlocksContainerConfig) other;
        return Intrinsics.areEqual(this.blockEditTextConfig, blocksContainerConfig.blockEditTextConfig) && Intrinsics.areEqual(this.blockItemViewConfig, blocksContainerConfig.blockItemViewConfig) && this.blockCount == blocksContainerConfig.blockCount && this.blockLength == blocksContainerConfig.blockLength && this.blockWidth == blocksContainerConfig.blockWidth && this.blockHeight == blocksContainerConfig.blockHeight && Intrinsics.areEqual(this.blockText, blocksContainerConfig.blockText);
    }

    public int hashCode() {
        int hashCode = ((((((((((this.blockEditTextConfig.hashCode() * 31) + this.blockItemViewConfig.hashCode()) * 31) + this.blockCount) * 31) + this.blockLength) * 31) + this.blockWidth) * 31) + this.blockHeight) * 31;
        String str = this.blockText;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "BlocksContainerConfig(blockEditTextConfig=" + this.blockEditTextConfig + ", blockItemViewConfig=" + this.blockItemViewConfig + ", blockCount=" + this.blockCount + ", blockLength=" + this.blockLength + ", blockWidth=" + this.blockWidth + ", blockHeight=" + this.blockHeight + ", blockText=" + this.blockText + ')';
    }

    public BlocksContainerConfig(BlockEditTextConfig blockEditTextConfig, BlockItemViewConfig blockItemViewConfig, int i, int i2, int i3, int i4, String str) {
        Intrinsics.checkNotNullParameter(blockEditTextConfig, "blockEditTextConfig");
        Intrinsics.checkNotNullParameter(blockItemViewConfig, "blockItemViewConfig");
        this.blockEditTextConfig = blockEditTextConfig;
        this.blockItemViewConfig = blockItemViewConfig;
        this.blockCount = i;
        this.blockLength = i2;
        this.blockWidth = i3;
        this.blockHeight = i4;
        this.blockText = str;
    }

    public final BlockEditTextConfig getBlockEditTextConfig() {
        return this.blockEditTextConfig;
    }

    public final BlockItemViewConfig getBlockItemViewConfig() {
        return this.blockItemViewConfig;
    }

    public final int getBlockCount() {
        return this.blockCount;
    }

    public final int getBlockLength() {
        return this.blockLength;
    }

    public final int getBlockWidth() {
        return this.blockWidth;
    }

    public final int getBlockHeight() {
        return this.blockHeight;
    }

    public final String getBlockText() {
        return this.blockText;
    }
}
