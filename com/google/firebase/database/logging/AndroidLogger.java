package com.google.firebase.database.logging;

import android.util.Log;
import com.google.firebase.database.logging.Logger;
import java.util.List;

/* loaded from: classes4.dex */
public class AndroidLogger extends DefaultLogger {
    @Override // com.google.firebase.database.logging.DefaultLogger
    protected String buildLogMessage(Logger.Level level, String str, String str2, long j) {
        return str2;
    }

    public AndroidLogger(Logger.Level level, List<String> list) {
        super(level, list);
    }

    @Override // com.google.firebase.database.logging.DefaultLogger
    protected void error(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.google.firebase.database.logging.DefaultLogger
    protected void warn(String str, String str2) {
        Log.w(str, str2);
    }

    @Override // com.google.firebase.database.logging.DefaultLogger
    protected void info(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.google.firebase.database.logging.DefaultLogger
    protected void debug(String str, String str2) {
        Log.d(str, str2);
    }
}
