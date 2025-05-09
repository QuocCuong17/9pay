package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;

/* loaded from: classes4.dex */
public class LogWrapper {
    private final String component;
    private final Logger logger;
    private final String prefix;

    private static String exceptionStacktrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public LogWrapper(Logger logger, String str) {
        this(logger, str, null);
    }

    public LogWrapper(Logger logger, String str, String str2) {
        this.logger = logger;
        this.component = str;
        this.prefix = str2;
    }

    public void error(String str, Throwable th) {
        this.logger.onLogMessage(Logger.Level.ERROR, this.component, toLog(str, new Object[0]) + IOUtils.LINE_SEPARATOR_UNIX + exceptionStacktrace(th), now());
    }

    public void warn(String str) {
        warn(str, null);
    }

    public void warn(String str, Throwable th) {
        String log = toLog(str, new Object[0]);
        if (th != null) {
            log = log + IOUtils.LINE_SEPARATOR_UNIX + exceptionStacktrace(th);
        }
        this.logger.onLogMessage(Logger.Level.WARN, this.component, log, now());
    }

    public void info(String str) {
        this.logger.onLogMessage(Logger.Level.INFO, this.component, toLog(str, new Object[0]), now());
    }

    public void debug(String str, Object... objArr) {
        debug(str, null, objArr);
    }

    public boolean logsDebug() {
        return this.logger.getLogLevel().ordinal() <= Logger.Level.DEBUG.ordinal();
    }

    public void debug(String str, Throwable th, Object... objArr) {
        if (logsDebug()) {
            String log = toLog(str, objArr);
            if (th != null) {
                log = log + IOUtils.LINE_SEPARATOR_UNIX + exceptionStacktrace(th);
            }
            this.logger.onLogMessage(Logger.Level.DEBUG, this.component, log, now());
        }
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private String toLog(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        if (this.prefix == null) {
            return str;
        }
        return this.prefix + " - " + str;
    }
}
