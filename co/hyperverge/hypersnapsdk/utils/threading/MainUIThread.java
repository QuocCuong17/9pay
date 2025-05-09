package co.hyperverge.hypersnapsdk.utils.threading;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes2.dex */
public class MainUIThread {
    private static MainUIThread mainUiThread;
    private Handler handler = new Handler(Looper.getMainLooper());

    private MainUIThread() {
    }

    public static synchronized MainUIThread getInstance() {
        MainUIThread mainUIThread;
        synchronized (MainUIThread.class) {
            if (mainUiThread == null) {
                mainUiThread = new MainUIThread();
            }
            mainUIThread = mainUiThread;
        }
        return mainUIThread;
    }

    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }
}
