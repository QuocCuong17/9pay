package com.w3conext.jailbreak_root_detection.frida;

import com.pichillilorenzo.flutter_inappwebview_android.credential_database.URLProtectionSpaceContract;
import kotlin.Metadata;

/* compiled from: AntiFridaNativeLoader.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0086 J\u0011\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0086 J\u0015\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0086 J\u001b\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u0004H\u0086 ¨\u0006\u000e"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/frida/AntiFridaNativeLoader;", "", "()V", "checkBeingDebugged", "", "useCustomizedSyscall", "checkFridaByPort", URLProtectionSpaceContract.FeedEntry.COLUMN_NAME_PORT, "", "nativeReadProcMaps", "", "scanModulesForSignature", "signature", "useCustomizedSysCalls", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AntiFridaNativeLoader {
    public static final AntiFridaNativeLoader INSTANCE = new AntiFridaNativeLoader();

    public final native boolean checkBeingDebugged(boolean useCustomizedSyscall);

    public final native boolean checkFridaByPort(int port);

    public final native String nativeReadProcMaps(boolean useCustomizedSyscall);

    public final native boolean scanModulesForSignature(String signature, boolean useCustomizedSysCalls);

    private AntiFridaNativeLoader() {
    }

    public static /* synthetic */ boolean checkBeingDebugged$default(AntiFridaNativeLoader antiFridaNativeLoader, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return antiFridaNativeLoader.checkBeingDebugged(z);
    }

    public static /* synthetic */ String nativeReadProcMaps$default(AntiFridaNativeLoader antiFridaNativeLoader, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return antiFridaNativeLoader.nativeReadProcMaps(z);
    }

    public static /* synthetic */ boolean scanModulesForSignature$default(AntiFridaNativeLoader antiFridaNativeLoader, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return antiFridaNativeLoader.scanModulesForSignature(str, z);
    }

    static {
        try {
            System.loadLibrary("antifrida");
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
