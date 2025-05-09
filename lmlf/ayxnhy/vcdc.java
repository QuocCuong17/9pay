package lmlf.ayxnhy;

import android.app.ActivityManager;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import com.google.mlkit.common.MlKitException;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class vcdc extends ContentObserver {
    public vcdc(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri, int i) {
        try {
            Context context = (Context) Class.forName(tfwhgw.rnigpa(MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD)).getMethod(tfwhgw.rnigpa(208), new Class[0]).invoke(null, null);
            if (Build.VERSION.SDK_INT >= 34) {
                Iterator<ActivityManager.AppTask> it = ((ActivityManager) context.getSystemService(tfwhgw.rnigpa(209))).getAppTasks().iterator();
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
