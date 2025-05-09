package lmlf.ayxnhy;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import androidx.media3.common.C;
import java.lang.Thread;
import java.util.Iterator;
import ywehnqt.bflwukrx;

/* loaded from: classes5.dex */
public class jbkj {
    public static void izb(String str, String str2) {
        try {
            Context context = (Context) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, null);
            if (str == null || str.isEmpty()) {
                if (str2 == null || Looper.myLooper() != Looper.getMainLooper()) {
                    return;
                }
                try {
                    Toast.makeText(context, str2, 1).show();
                    Thread.sleep(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                    return;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            ujxyn(context, str);
            if (Build.VERSION.SDK_INT >= 34) {
                Iterator<ActivityManager.AppTask> it = ((ActivityManager) context.getSystemService("activity")).getAppTasks().iterator();
                while (it.hasNext()) {
                    it.next().finishAndRemoveTask();
                }
                ujxyn(context, str);
            }
            Process.killProcess(Process.myPid());
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void stnyz() {
        RuntimeException runtimeException = new RuntimeException();
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), runtimeException);
        }
        Process.killProcess(Process.myPid());
    }

    public static void tknf() {
    }

    public static void ujxyn(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) bflwukrx.class);
        intent.putExtra("c", str);
        intent.setFlags(335544320);
        context.startActivity(intent);
    }
}
