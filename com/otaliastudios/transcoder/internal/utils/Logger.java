package com.otaliastudios.transcoder.internal.utils;

import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public class Logger {
    public static final int LEVEL_ERROR = 3;
    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 2;
    private static int sLevel;
    private final String mTag;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface LogLevel {
    }

    public Logger(String str) {
        this.mTag = str;
    }

    public static void setLogLevel(int i) {
        sLevel = i;
    }

    private boolean should(int i) {
        return sLevel <= i;
    }

    public void v(String str) {
        v(str, null);
    }

    public void i(String str) {
        i(str, null);
    }

    public void w(String str) {
        w(str, null);
    }

    public void e(String str) {
        e(str, null);
    }

    public void v(String str, Throwable th) {
        log(0, str, th);
    }

    public void i(String str, Throwable th) {
        log(1, str, th);
    }

    public void w(String str, Throwable th) {
        log(2, str, th);
    }

    public void e(String str, Throwable th) {
        log(3, str, th);
    }

    private void log(int i, String str, Throwable th) {
        if (should(i)) {
            if (i == 0) {
                Log.v(this.mTag, str, th);
                return;
            }
            if (i == 1) {
                Log.i(this.mTag, str, th);
            } else if (i == 2) {
                Log.w(this.mTag, str, th);
            } else {
                if (i != 3) {
                    return;
                }
                Log.e(this.mTag, str, th);
            }
        }
    }
}
