package com.w3conext.jailbreak_root_detection.frida;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.w3conext.jailbreak_root_detection.rooted.SuperUserUtility;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* compiled from: AntiFridaChecker.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\u0006R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/frida/AntiFridaChecker;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "checkModuleDetected", "", "checkPortDetected", "checkServerProcessDetected", "checkSignatureDetected", "isDetected", "tryRoot", "Companion", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AntiFridaChecker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "AntiFridaChecker";
    private final Context context;

    public AntiFridaChecker(Context context) {
        this.context = context;
    }

    public final boolean isDetected() {
        return tryRoot() || checkModuleDetected() || checkPortDetected() || checkServerProcessDetected() || checkSignatureDetected();
    }

    public final boolean tryRoot() {
        SuperUserUtility superUserUtility = SuperUserUtility.INSTANCE;
        Context context = this.context;
        String packageCodePath = context != null ? context.getPackageCodePath() : null;
        if (packageCodePath == null) {
            packageCodePath = "";
        }
        boolean tryRoot = superUserUtility.tryRoot(packageCodePath);
        Log.i(TAG, "Rooted: " + tryRoot);
        return tryRoot;
    }

    public final boolean checkModuleDetected() {
        SuperUserUtility superUserUtility = SuperUserUtility.INSTANCE;
        int myPid = Process.myPid();
        StringBuilder sb = new StringBuilder();
        sb.append("pmap ");
        sb.append(myPid);
        boolean z = AntiFridaUtility.INSTANCE.checkFridaByProcMaps() || AntiFridaBlocklist.INSTANCE.checkContain(superUserUtility.execRootCmd(sb.toString()));
        Log.i(TAG, "Check module detected: " + z);
        return z;
    }

    public final boolean checkPortDetected() {
        boolean checkFridaByPort = AntiFridaUtility.INSTANCE.checkFridaByPort(27042);
        Log.i(TAG, "Check port detected: " + checkFridaByPort);
        return checkFridaByPort;
    }

    public final boolean checkServerProcessDetected() {
        boolean contains$default = StringsKt.contains$default((CharSequence) SuperUserUtility.INSTANCE.execRootCmd("ps -ef"), (CharSequence) "frida-server", false, 2, (Object) null);
        Log.i(TAG, "Check frida-server process detected: " + contains$default);
        return contains$default;
    }

    public final boolean checkSignatureDetected() {
        boolean scanModulesForSignatureDetected = AntiFridaUtility.INSTANCE.scanModulesForSignatureDetected();
        Log.i(TAG, "Check signature detected: " + scanModulesForSignatureDetected);
        return scanModulesForSignatureDetected;
    }

    /* compiled from: AntiFridaChecker.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/frida/AntiFridaChecker$Companion;", "", "()V", "TAG", "", "checkFrida", "", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean checkFrida() {
            boolean z;
            boolean z2 = true;
            if (new File("/data/local/tmp/frida-gadget").exists()) {
                Log.d(AntiFridaChecker.TAG, "Frida-gadget found in /data/local/tmp");
                z = true;
            } else {
                z = false;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ps").getInputStream()));
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    if (StringsKt.contains$default((CharSequence) readLine, (CharSequence) "frida-server", false, 2, (Object) null)) {
                        Log.d(AntiFridaChecker.TAG, "Frida-server process found");
                        z = true;
                        break;
                    }
                }
            } catch (Exception unused) {
                Log.d(AntiFridaChecker.TAG, "Error checking for Frida-related processes");
            }
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("lsof").getInputStream()));
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (StringsKt.contains$default((CharSequence) readLine2, (CharSequence) "libfrida-gadget.so", false, 2, (Object) null)) {
                        Log.d(AntiFridaChecker.TAG, "Frida-gadget library found");
                        break;
                    }
                }
            } catch (Exception unused2) {
                Log.d(AntiFridaChecker.TAG, "Error checking for Frida-related libraries");
            }
            z2 = z;
            Log.i(AntiFridaChecker.TAG, "Frida running: " + z2);
            return z2;
        }
    }
}
