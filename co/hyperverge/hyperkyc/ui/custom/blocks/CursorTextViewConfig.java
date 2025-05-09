package co.hyperverge.hyperkyc.ui.custom.blocks;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CursorTextView.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lco/hyperverge/hyperkyc/ui/custom/blocks/CursorTextViewConfig;", "", "cursorColor", "", "cursorHeight", "", "(Ljava/lang/String;I)V", "getCursorColor", "()Ljava/lang/String;", "getCursorHeight", "()I", "hyperkyc_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CursorTextViewConfig {
    private final String cursorColor;
    private final int cursorHeight;

    public CursorTextViewConfig(String cursorColor, int i) {
        Intrinsics.checkNotNullParameter(cursorColor, "cursorColor");
        this.cursorColor = cursorColor;
        this.cursorHeight = i;
    }

    public final String getCursorColor() {
        return this.cursorColor;
    }

    public final int getCursorHeight() {
        return this.cursorHeight;
    }
}
