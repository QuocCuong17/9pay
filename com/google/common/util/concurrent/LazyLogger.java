package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes4.dex */
public final class LazyLogger {
    private volatile Logger logger;
    private final String loggerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LazyLogger(Class<?> ownerOfLogger) {
        this.loggerName = ownerOfLogger.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Logger get() {
        Logger logger = this.logger;
        if (logger != null) {
            return logger;
        }
        synchronized (this) {
            Logger logger2 = this.logger;
            if (logger2 != null) {
                return logger2;
            }
            Logger logger3 = Logger.getLogger(this.loggerName);
            this.logger = logger3;
            return logger3;
        }
    }
}
