package com.anish.trust_fall.externalstorage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ExternalStorageCheck.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/anish/trust_fall/externalstorage/ExternalStorageCheck;", "", "()V", "isOnExternalStorage", "", "context", "Landroid/content/Context;", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExternalStorageCheck {
    public static final ExternalStorageCheck INSTANCE = new ExternalStorageCheck();

    private ExternalStorageCheck() {
    }

    public final boolean isOnExternalStorage(Context context) {
        String filesDir;
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT > 7) {
            try {
                return (context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.flags & 262144) == 262144;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        try {
            filesDir = context.getFilesDir().getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(filesDir, "filesDir");
        } catch (Throwable unused2) {
        }
        if (StringsKt.startsWith$default(filesDir, "/data/", false, 2, (Object) null)) {
            return false;
        }
        if (!StringsKt.contains$default((CharSequence) filesDir, (CharSequence) "/mnt/", false, 2, (Object) null)) {
            if (!StringsKt.contains$default((CharSequence) filesDir, (CharSequence) "/sdcard/", false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }
}
