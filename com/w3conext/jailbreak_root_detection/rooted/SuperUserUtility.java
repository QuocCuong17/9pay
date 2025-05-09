package com.w3conext.jailbreak_root_detection.rooted;

import android.util.Log;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import io.sentry.protocol.OperatingSystem;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;

/* compiled from: SuperUserUtility.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/w3conext/jailbreak_root_detection/rooted/SuperUserUtility;", "", "()V", "TAG", "", OperatingSystem.JsonKeys.ROOTED, "", "execRootCmd", "cmd", "tryRoot", "pkgCodePath", "jailbreak_root_detection_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SuperUserUtility {
    public static final SuperUserUtility INSTANCE = new SuperUserUtility();
    private static final String TAG = "SuperUserUtility";
    private static boolean rooted;

    private SuperUserUtility() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0049, code lost:
    
        if (r2 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004b, code lost:
    
        r2.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0064, code lost:
    
        return com.w3conext.jailbreak_root_detection.rooted.SuperUserUtility.rooted;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
    
        if (r2 != null) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean tryRoot(String pkgCodePath) {
        Process process;
        DataOutputStream dataOutputStream;
        Intrinsics.checkNotNullParameter(pkgCodePath, "pkgCodePath");
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                process = Runtime.getRuntime().exec(CmcdConfiguration.KEY_STARTUP);
                try {
                    dataOutputStream = new DataOutputStream(process.getOutputStream());
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception unused2) {
            process = null;
        } catch (Throwable th2) {
            th = th2;
            process = null;
        }
        try {
            dataOutputStream.writeBytes("chmod 777 " + pkgCodePath + IOUtils.LINE_SEPARATOR_UNIX);
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            rooted = process.waitFor() == 0;
            dataOutputStream.close();
        } catch (Exception unused3) {
            dataOutputStream2 = dataOutputStream;
            rooted = false;
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream2 = dataOutputStream;
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    public final String execRootCmd(String cmd) {
        Intrinsics.checkNotNullParameter(cmd, "cmd");
        String str = "";
        if (!rooted) {
            return "";
        }
        try {
            Process exec = Runtime.getRuntime().exec(CmcdConfiguration.KEY_STARTUP);
            DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
            InputStream inputStream = exec.getInputStream();
            InputStream errorStream = exec.getErrorStream();
            Log.i(TAG, "execRootCmd: " + cmd);
            dataOutputStream.writeBytes(cmd + IOUtils.LINE_SEPARATOR_UNIX);
            dataOutputStream.flush();
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            dataOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = str + ((Object) readLine);
            }
            bufferedReader.close();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(errorStream));
            while (true) {
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 == null) {
                    break;
                }
                str = str + ((Object) readLine2);
            }
            bufferedReader2.close();
        } catch (Exception e) {
            Log.e(TAG, ExceptionsKt.stackTraceToString(e));
        }
        return str;
    }
}
