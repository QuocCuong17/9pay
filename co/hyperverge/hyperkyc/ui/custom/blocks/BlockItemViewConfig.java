package co.hyperverge.hyperkyc.ui.custom.blocks;

import android.graphics.Typeface;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlockItemView.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JG\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006!"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/BlockItemViewConfig;", "", "textColor", "", "blockTextSize", "", "blockTextTypeFace", "Landroid/graphics/Typeface;", "activeBorderColor", "inactiveBorderColor", "errorBorderColor", "(Ljava/lang/String;ILandroid/graphics/Typeface;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActiveBorderColor", "()Ljava/lang/String;", "getBlockTextSize", "()I", "getBlockTextTypeFace", "()Landroid/graphics/Typeface;", "getErrorBorderColor", "getInactiveBorderColor", "getTextColor", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BlockItemViewConfig {
    private final String activeBorderColor;
    private final int blockTextSize;
    private final Typeface blockTextTypeFace;
    private final String errorBorderColor;
    private final String inactiveBorderColor;
    private final String textColor;

    public static /* synthetic */ BlockItemViewConfig copy$default(BlockItemViewConfig blockItemViewConfig, String str, int i, Typeface typeface, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = blockItemViewConfig.textColor;
        }
        if ((i2 & 2) != 0) {
            i = blockItemViewConfig.blockTextSize;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            typeface = blockItemViewConfig.blockTextTypeFace;
        }
        Typeface typeface2 = typeface;
        if ((i2 & 8) != 0) {
            str2 = blockItemViewConfig.activeBorderColor;
        }
        String str5 = str2;
        if ((i2 & 16) != 0) {
            str3 = blockItemViewConfig.inactiveBorderColor;
        }
        String str6 = str3;
        if ((i2 & 32) != 0) {
            str4 = blockItemViewConfig.errorBorderColor;
        }
        return blockItemViewConfig.copy(str, i3, typeface2, str5, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTextColor() {
        return this.textColor;
    }

    /* renamed from: component2, reason: from getter */
    public final int getBlockTextSize() {
        return this.blockTextSize;
    }

    /* renamed from: component3, reason: from getter */
    public final Typeface getBlockTextTypeFace() {
        return this.blockTextTypeFace;
    }

    /* renamed from: component4, reason: from getter */
    public final String getActiveBorderColor() {
        return this.activeBorderColor;
    }

    /* renamed from: component5, reason: from getter */
    public final String getInactiveBorderColor() {
        return this.inactiveBorderColor;
    }

    /* renamed from: component6, reason: from getter */
    public final String getErrorBorderColor() {
        return this.errorBorderColor;
    }

    public final BlockItemViewConfig copy(String textColor, int blockTextSize, Typeface blockTextTypeFace, String activeBorderColor, String inactiveBorderColor, String errorBorderColor) {
        Intrinsics.checkNotNullParameter(textColor, "textColor");
        Intrinsics.checkNotNullParameter(activeBorderColor, "activeBorderColor");
        Intrinsics.checkNotNullParameter(inactiveBorderColor, "inactiveBorderColor");
        Intrinsics.checkNotNullParameter(errorBorderColor, "errorBorderColor");
        return new BlockItemViewConfig(textColor, blockTextSize, blockTextTypeFace, activeBorderColor, inactiveBorderColor, errorBorderColor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BlockItemViewConfig)) {
            return false;
        }
        BlockItemViewConfig blockItemViewConfig = (BlockItemViewConfig) other;
        return Intrinsics.areEqual(this.textColor, blockItemViewConfig.textColor) && this.blockTextSize == blockItemViewConfig.blockTextSize && Intrinsics.areEqual(this.blockTextTypeFace, blockItemViewConfig.blockTextTypeFace) && Intrinsics.areEqual(this.activeBorderColor, blockItemViewConfig.activeBorderColor) && Intrinsics.areEqual(this.inactiveBorderColor, blockItemViewConfig.inactiveBorderColor) && Intrinsics.areEqual(this.errorBorderColor, blockItemViewConfig.errorBorderColor);
    }

    public int hashCode() {
        int hashCode = ((this.textColor.hashCode() * 31) + this.blockTextSize) * 31;
        Typeface typeface = this.blockTextTypeFace;
        return ((((((hashCode + (typeface == null ? 0 : typeface.hashCode())) * 31) + this.activeBorderColor.hashCode()) * 31) + this.inactiveBorderColor.hashCode()) * 31) + this.errorBorderColor.hashCode();
    }

    public String toString() {
        return "BlockItemViewConfig(textColor=" + this.textColor + ", blockTextSize=" + this.blockTextSize + ", blockTextTypeFace=" + this.blockTextTypeFace + ", activeBorderColor=" + this.activeBorderColor + ", inactiveBorderColor=" + this.inactiveBorderColor + ", errorBorderColor=" + this.errorBorderColor + ')';
    }

    public BlockItemViewConfig(String textColor, int i, Typeface typeface, String activeBorderColor, String inactiveBorderColor, String errorBorderColor) {
        Intrinsics.checkNotNullParameter(textColor, "textColor");
        Intrinsics.checkNotNullParameter(activeBorderColor, "activeBorderColor");
        Intrinsics.checkNotNullParameter(inactiveBorderColor, "inactiveBorderColor");
        Intrinsics.checkNotNullParameter(errorBorderColor, "errorBorderColor");
        this.textColor = textColor;
        this.blockTextSize = i;
        this.blockTextTypeFace = typeface;
        this.activeBorderColor = activeBorderColor;
        this.inactiveBorderColor = inactiveBorderColor;
        this.errorBorderColor = errorBorderColor;
    }

    public final String getTextColor() {
        return this.textColor;
    }

    public final int getBlockTextSize() {
        return this.blockTextSize;
    }

    public final Typeface getBlockTextTypeFace() {
        return this.blockTextTypeFace;
    }

    public final String getActiveBorderColor() {
        return this.activeBorderColor;
    }

    public final String getInactiveBorderColor() {
        return this.inactiveBorderColor;
    }

    public final String getErrorBorderColor() {
        return this.errorBorderColor;
    }
}
