package co.hyperverge.encoder.utils.extensions;

import android.content.Context;
import android.os.StatFs;
import java.io.File;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceExts.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u0013\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¢\u0006\u0002\u0010\u0003\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0000¨\u0006\u0006"}, d2 = {"getFreeStorage", "", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Long;", "isLowStorage", "", "hv-bitmaps-to-video_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceExtsKt {
    public static final Long getFreeStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            File externalFilesDir = context.getExternalFilesDir(null);
            StatFs statFs = new StatFs(externalFilesDir == null ? null : externalFilesDir.getPath());
            return Long.valueOf(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Object m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            return (Long) (Result.m1208isFailureimpl(m1202constructorimpl) ? null : m1202constructorimpl);
        }
    }

    public static final boolean isLowStorage(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Long freeStorage = getFreeStorage(context);
        return freeStorage != null && freeStorage.longValue() / ((long) 1048576) <= ((long) 1);
    }
}
