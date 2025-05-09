package com.google.firebase.storage.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTaskScheduler;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class SmartHandler {
    static boolean testMode = false;
    private final Executor executor;

    public SmartHandler(Executor executor) {
        if (executor == null) {
            if (!testMode) {
                this.executor = StorageTaskScheduler.getInstance().getMainThreadExecutor();
                return;
            } else {
                this.executor = null;
                return;
            }
        }
        this.executor = executor;
    }

    public void callBack(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        Executor executor = this.executor;
        if (executor != null) {
            executor.execute(runnable);
        } else {
            StorageTaskScheduler.getInstance().scheduleCallback(runnable);
        }
    }
}
