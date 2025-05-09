package com.anish.trust_fall.rooted;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import kotlin.Metadata;

/* compiled from: GreaterThan23.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Lcom/anish/trust_fall/rooted/GreaterThan23;", "Lcom/anish/trust_fall/rooted/CheckApiVersion;", "()V", "checkRootMethod1", "", "checkRootMethod2", "checkRooted", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GreaterThan23 implements CheckApiVersion {
    @Override // com.anish.trust_fall.rooted.CheckApiVersion
    public boolean checkRooted() {
        return checkRootMethod1() || checkRootMethod2();
    }

    private final boolean checkRootMethod1() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
        for (int i = 0; i < 9; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
    
        if (r1 != null) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0029, code lost:
    
        if (r1 != null) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x002b, code lost:
    
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0032, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean checkRootMethod2() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", CmcdConfiguration.KEY_STARTUP});
            r0 = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
        } catch (Throwable unused) {
        }
    }
}
