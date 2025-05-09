package lmlf.ayxnhy;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class jrvye implements AccessibilityManager.AccessibilityStateChangeListener {
    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean z) {
        try {
            Context context = (Context) Class.forName(tfwhgw.rnigpa(30)).getMethod(tfwhgw.rnigpa(31), new Class[0]).invoke(null, null);
            if (Build.VERSION.SDK_INT >= 34) {
                Iterator<ActivityManager.AppTask> it = ((ActivityManager) context.getSystemService(tfwhgw.rnigpa(32))).getAppTasks().iterator();
                while (it.hasNext()) {
                    it.next().finishAndRemoveTask();
                }
            }
            Process.killProcess(Process.myPid());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
