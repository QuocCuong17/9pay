package com.tekartik.sqflite.operation;

/* loaded from: classes5.dex */
public class QueuedOperation {
    final Operation operation;
    final Runnable runnable;

    public QueuedOperation(Operation operation, Runnable runnable) {
        this.operation = operation;
        this.runnable = runnable;
    }

    public void run() {
        this.runnable.run();
    }
}
