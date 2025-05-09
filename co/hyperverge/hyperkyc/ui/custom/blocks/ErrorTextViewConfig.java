package co.hyperverge.hyperkyc.ui.custom.blocks;

import com.facebook.appevents.iap.InAppPurchaseConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlocksView.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/ErrorTextViewConfig;", "", "color", "", "textSize", "", "(Ljava/lang/String;I)V", "getColor", "()Ljava/lang/String;", "getTextSize", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", InAppPurchaseConstants.METHOD_TO_STRING, "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ErrorTextViewConfig {
    private final String color;
    private final int textSize;

    public static /* synthetic */ ErrorTextViewConfig copy$default(ErrorTextViewConfig errorTextViewConfig, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = errorTextViewConfig.color;
        }
        if ((i2 & 2) != 0) {
            i = errorTextViewConfig.textSize;
        }
        return errorTextViewConfig.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getColor() {
        return this.color;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTextSize() {
        return this.textSize;
    }

    public final ErrorTextViewConfig copy(String color, int textSize) {
        Intrinsics.checkNotNullParameter(color, "color");
        return new ErrorTextViewConfig(color, textSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ErrorTextViewConfig)) {
            return false;
        }
        ErrorTextViewConfig errorTextViewConfig = (ErrorTextViewConfig) other;
        return Intrinsics.areEqual(this.color, errorTextViewConfig.color) && this.textSize == errorTextViewConfig.textSize;
    }

    public int hashCode() {
        return (this.color.hashCode() * 31) + this.textSize;
    }

    public String toString() {
        return "ErrorTextViewConfig(color=" + this.color + ", textSize=" + this.textSize + ')';
    }

    public ErrorTextViewConfig(String color, int i) {
        Intrinsics.checkNotNullParameter(color, "color");
        this.color = color;
        this.textSize = i;
    }

    public final String getColor() {
        return this.color;
    }

    public final int getTextSize() {
        return this.textSize;
    }
}
