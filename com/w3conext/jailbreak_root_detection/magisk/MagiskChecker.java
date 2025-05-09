package com.w3conext.jailbreak_root_detection.magisk;

import java.io.File;
import kotlin.Metadata;

/* compiled from: MagiskChecker.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/magisk/MagiskChecker;", "", "()V", "isInstalled", "", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MagiskChecker {
    public static final MagiskChecker INSTANCE = new MagiskChecker();

    private MagiskChecker() {
    }

    public final boolean isInstalled() {
        if (new File("/data/adb/magisk.img").exists()) {
            return true;
        }
        File file = new File("/su/bin/su");
        if (!file.exists()) {
            file = new File("/system/xbin/su");
        }
        return file.exists();
    }
}
