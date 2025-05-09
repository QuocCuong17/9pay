package com.w3conext.jailbreak_root_detection.frida;

import android.util.Log;
import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import java.io.File;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;

/* compiled from: AntiFridaUtility.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\n\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0002J\u0006\u0010\r\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/frida/AntiFridaUtility;", "", "()V", "TAG", "", "checkBeingDebugged", "", "useCustomizedSyscall", "checkFridaByPort", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT, "", "checkFridaByProcMaps", "readProcMaps", "scanModulesForSignatureDetected", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AntiFridaUtility {
    public static final AntiFridaUtility INSTANCE = new AntiFridaUtility();
    private static final String TAG = "AntiFridaUtility";

    private AntiFridaUtility() {
    }

    public final boolean checkFridaByProcMaps() {
        return AntiFridaBlocklist.INSTANCE.check(readProcMaps()) || AntiFridaBlocklist.INSTANCE.check(AntiFridaNativeLoader.nativeReadProcMaps$default(AntiFridaNativeLoader.INSTANCE, false, 1, null)) || AntiFridaBlocklist.INSTANCE.check(AntiFridaNativeLoader.INSTANCE.nativeReadProcMaps(true));
    }

    public final boolean checkFridaByPort(int port) {
        return AntiFridaNativeLoader.INSTANCE.checkFridaByPort(port);
    }

    public final boolean scanModulesForSignatureDetected() {
        int i = 0;
        for (String str : CollectionsKt.listOf((Object[]) new String[]{"frida:rpc", "LIBFRIDA"})) {
            if (AntiFridaNativeLoader.INSTANCE.scanModulesForSignature(str, true)) {
                i++;
            }
            if (AntiFridaNativeLoader.INSTANCE.scanModulesForSignature(str, false)) {
                i++;
            }
        }
        return i > 0;
    }

    public final boolean checkBeingDebugged(boolean useCustomizedSyscall) {
        return AntiFridaNativeLoader.INSTANCE.checkBeingDebugged(useCustomizedSyscall);
    }

    private final String readProcMaps() {
        try {
            return FilesKt.readText$default(new File("/proc/self/maps"), null, 1, null);
        } catch (Exception e) {
            Log.e(TAG, ExceptionsKt.stackTraceToString(e));
            return null;
        }
    }
}
