package co.hyperverge.hyperkyc.utils;

import android.os.Build;
import kotlin.Metadata;

/* compiled from: SessionRecorderUtils.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0007¨\u0006\u0003"}, d2 = {"getIsAndroid7Plus", "", "getIsAndroid8Plus", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SessionRecorderUtilsKt {
    public static final boolean getIsAndroid7Plus() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static final boolean getIsAndroid8Plus() {
        return Build.VERSION.SDK_INT >= 26;
    }
}
