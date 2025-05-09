package com.google.firebase.database.core;

import java.lang.Thread;

/* loaded from: classes4.dex */
public interface ThreadInitializer {
    public static final ThreadInitializer defaultInstance = new ThreadInitializer() { // from class: com.google.firebase.database.core.ThreadInitializer.1
        @Override // com.google.firebase.database.core.ThreadInitializer
        public void setName(Thread thread, String str) {
            thread.setName(str);
        }

        @Override // com.google.firebase.database.core.ThreadInitializer
        public void setDaemon(Thread thread, boolean z) {
            thread.setDaemon(z);
        }

        @Override // com.google.firebase.database.core.ThreadInitializer
        public void setUncaughtExceptionHandler(Thread thread, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    };

    void setDaemon(Thread thread, boolean z);

    void setName(Thread thread, String str);

    void setUncaughtExceptionHandler(Thread thread, Thread.UncaughtExceptionHandler uncaughtExceptionHandler);
}
