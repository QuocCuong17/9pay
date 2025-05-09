package co.hyperverge.hyperkyc.ui.custom.blocks;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlocksView.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewConfig;", "", "blocksContainerConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainerConfig;", "errorTextViewConfig", "Lco/hyperverge/hyperkyc/ui/custom/blocks/ErrorTextViewConfig;", "blocksViewListener", "Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;", "(Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainerConfig;Lco/hyperverge/hyperkyc/ui/custom/blocks/ErrorTextViewConfig;Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;)V", "getBlocksContainerConfig", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksContainerConfig;", "getBlocksViewListener", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/BlocksViewListener;", "getErrorTextViewConfig", "()Lco/hyperverge/hyperkyc/ui/custom/blocks/ErrorTextViewConfig;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BlocksViewConfig {
    private final BlocksContainerConfig blocksContainerConfig;
    private final BlocksViewListener blocksViewListener;
    private final ErrorTextViewConfig errorTextViewConfig;

    public static /* synthetic */ BlocksViewConfig copy$default(BlocksViewConfig blocksViewConfig, BlocksContainerConfig blocksContainerConfig, ErrorTextViewConfig errorTextViewConfig, BlocksViewListener blocksViewListener, int i, Object obj) {
        if ((i & 1) != 0) {
            blocksContainerConfig = blocksViewConfig.blocksContainerConfig;
        }
        if ((i & 2) != 0) {
            errorTextViewConfig = blocksViewConfig.errorTextViewConfig;
        }
        if ((i & 4) != 0) {
            blocksViewListener = blocksViewConfig.blocksViewListener;
        }
        return blocksViewConfig.copy(blocksContainerConfig, errorTextViewConfig, blocksViewListener);
    }

    /* renamed from: component1, reason: from getter */
    public final BlocksContainerConfig getBlocksContainerConfig() {
        return this.blocksContainerConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final ErrorTextViewConfig getErrorTextViewConfig() {
        return this.errorTextViewConfig;
    }

    /* renamed from: component3, reason: from getter */
    public final BlocksViewListener getBlocksViewListener() {
        return this.blocksViewListener;
    }

    public final BlocksViewConfig copy(BlocksContainerConfig blocksContainerConfig, ErrorTextViewConfig errorTextViewConfig, BlocksViewListener blocksViewListener) {
        Intrinsics.checkNotNullParameter(blocksContainerConfig, "blocksContainerConfig");
        Intrinsics.checkNotNullParameter(errorTextViewConfig, "errorTextViewConfig");
        Intrinsics.checkNotNullParameter(blocksViewListener, "blocksViewListener");
        return new BlocksViewConfig(blocksContainerConfig, errorTextViewConfig, blocksViewListener);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlocksViewConfig)) {
            return false;
        }
        BlocksViewConfig blocksViewConfig = (BlocksViewConfig) other;
        return Intrinsics.areEqual(this.blocksContainerConfig, blocksViewConfig.blocksContainerConfig) && Intrinsics.areEqual(this.errorTextViewConfig, blocksViewConfig.errorTextViewConfig) && Intrinsics.areEqual(this.blocksViewListener, blocksViewConfig.blocksViewListener);
    }

    public int hashCode() {
        return (((this.blocksContainerConfig.hashCode() * 31) + this.errorTextViewConfig.hashCode()) * 31) + this.blocksViewListener.hashCode();
    }

    public String toString() {
        return "BlocksViewConfig(blocksContainerConfig=" + this.blocksContainerConfig + ", errorTextViewConfig=" + this.errorTextViewConfig + ", blocksViewListener=" + this.blocksViewListener + ')';
    }

    public BlocksViewConfig(BlocksContainerConfig blocksContainerConfig, ErrorTextViewConfig errorTextViewConfig, BlocksViewListener blocksViewListener) {
        Intrinsics.checkNotNullParameter(blocksContainerConfig, "blocksContainerConfig");
        Intrinsics.checkNotNullParameter(errorTextViewConfig, "errorTextViewConfig");
        Intrinsics.checkNotNullParameter(blocksViewListener, "blocksViewListener");
        this.blocksContainerConfig = blocksContainerConfig;
        this.errorTextViewConfig = errorTextViewConfig;
        this.blocksViewListener = blocksViewListener;
    }

    public final BlocksContainerConfig getBlocksContainerConfig() {
        return this.blocksContainerConfig;
    }

    public final ErrorTextViewConfig getErrorTextViewConfig() {
        return this.errorTextViewConfig;
    }

    public final BlocksViewListener getBlocksViewListener() {
        return this.blocksViewListener;
    }
}
