package co.hyperverge.hypersnapsdk.exceptions;

import android.app.Activity;
import android.os.Process;
import android.util.Log;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class ApplicationCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "co.hyperverge.hypersnapsdk.exceptions.ApplicationCrashHandler";
    static ApplicationCrashHandler crashHandler;
    private Thread.UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    List<String> hvActivities;
    Activity lastActivity;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Activity activity = this.lastActivity;
        if (activity != null && this.hvActivities.contains(activity.getClass().getSimpleName())) {
            this.lastActivity = null;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public void installHandler(Activity activity) {
        this.lastActivity = activity;
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof ApplicationCrashHandler) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    public void removeHandler() {
        this.lastActivity = null;
    }

    private static void killCurrentProcess() {
        Process.killProcess(Process.myPid());
    }

    public static ApplicationCrashHandler getCrashHandler() {
        if (crashHandler == null) {
            crashHandler = new ApplicationCrashHandler();
        }
        return crashHandler;
    }

    private ApplicationCrashHandler() {
        ArrayList arrayList = new ArrayList();
        this.hvActivities = arrayList;
        arrayList.add("HVFaceActivity");
        this.hvActivities.add("HVDocsActivity");
        this.hvActivities.add("HVDocInstructionActivity");
        this.hvActivities.add("HVFaceInstructionActivity");
        this.hvActivities.add("ReviewScreenActivity");
        this.hvActivities.add("HVErrorReviewScreenActivity");
    }

    private String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Log.d(TAG, Utils.getErrorMessage(th));
        String obj = stringWriter.toString();
        printWriter.close();
        return obj;
    }
}
