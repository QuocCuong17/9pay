package co.hyperverge.hvnfcsdk.helpers;

import android.app.Application;
import android.util.Log;
import co.hyperverge.hyperlogger.HyperLogger;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public class LogUtils {
    private static final Pattern ANON_CLASS_PATTERN = Pattern.compile("(\\$\\d+)+$");
    private static final int MAX_TAG_LENGTH = 23;
    private static final String NULL_VALUE = "null ";

    private static boolean isDebug() {
        return false;
    }

    public static void v(String str, String str2) {
        log(2, str, str2, null);
    }

    public static void d(String str, String str2) {
        log(3, str, str2, null);
    }

    public static void i(String str, String str2) {
        log(4, str, str2, null);
    }

    public static void w(String str, String str2) {
        log(5, str, str2, null);
    }

    public static void w(String str, String str2, Throwable th) {
        log(5, str, str2, th);
    }

    public static void e(String str, String str2) {
        log(6, str, str2, null);
    }

    public static void e(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }

    private static void log(int i, String str, String str2, Throwable th) {
        HyperLogger.Level level;
        String str3;
        String str4;
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            level = HyperLogger.Level.DEBUG;
        } else {
            level = HyperLogger.Level.ERROR;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" - ");
        sb.append(str2 != null ? str2 : NULL_VALUE);
        String str5 = "";
        if (th != null) {
            str3 = IOUtils.LINE_SEPARATOR_UNIX + th.getLocalizedMessage();
        } else {
            str3 = "";
        }
        sb.append(str3);
        HyperLogger.getInstance().log(level, sb.toString());
        if (!isRelease() || i >= 6) {
            try {
                str4 = ((Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0])).getPackageName();
            } catch (Exception unused) {
                str4 = "";
            }
            if (isDebug() && str4.contains("co.hyperverge")) {
                StringBuilder sb2 = new StringBuilder();
                if (str2 == null) {
                    str2 = NULL_VALUE;
                }
                sb2.append(str2);
                if (th != null) {
                    str5 = IOUtils.LINE_SEPARATOR_UNIX + th.getLocalizedMessage();
                }
                sb2.append(str5);
                Log.println(i, str, sb2.toString());
            }
        }
    }

    private static boolean isRelease() {
        return !isDebug();
    }
}
