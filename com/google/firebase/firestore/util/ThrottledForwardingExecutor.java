package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class ThrottledForwardingExecutor implements Executor {
    private final Semaphore availableSlots;
    private final Executor executor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThrottledForwardingExecutor(int i, Executor executor) {
        this.availableSlots = new Semaphore(i);
        this.executor = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        if (this.availableSlots.tryAcquire()) {
            try {
                this.executor.execute(new Runnable() { // from class: com.google.firebase.firestore.util.ThrottledForwardingExecutor$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ThrottledForwardingExecutor.this.m882xc0e914ff(runnable);
                    }
                });
                return;
            } catch (RejectedExecutionException unused) {
                runnable.run();
                return;
            }
        }
        runnable.run();
    }

    /* renamed from: lambda$execute$0$com-google-firebase-firestore-util-ThrottledForwardingExecutor, reason: not valid java name */
    public /* synthetic */ void m882xc0e914ff(Runnable runnable) {
        runnable.run();
        this.availableSlots.release();
    }
}
