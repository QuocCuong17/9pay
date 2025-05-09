package com.tekartik.sqflite;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class DatabaseWorker {
    private Handler handler;
    private HandlerThread handlerThread;
    private DatabaseTask lastTask;
    private final String name;
    protected Runnable onIdle;
    private final int priority;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DatabaseWorker(String str, int i) {
        this.name = str;
        this.priority = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void start(Runnable runnable) {
        HandlerThread handlerThread = new HandlerThread(this.name, this.priority);
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
        this.onIdle = runnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void quit() {
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.handlerThread = null;
            this.handler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLastTaskInTransaction() {
        DatabaseTask databaseTask = this.lastTask;
        return databaseTask != null && databaseTask.isInTransaction();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer lastTaskDatabaseId() {
        DatabaseTask databaseTask = this.lastTask;
        if (databaseTask != null) {
            return databaseTask.getDatabaseId();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postTask(final DatabaseTask databaseTask) {
        this.handler.post(new Runnable() { // from class: com.tekartik.sqflite.DatabaseWorker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DatabaseWorker.this.m990lambda$postTask$0$comtekartiksqfliteDatabaseWorker(databaseTask);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: work, reason: merged with bridge method [inline-methods] */
    public void m990lambda$postTask$0$comtekartiksqfliteDatabaseWorker(DatabaseTask databaseTask) {
        databaseTask.runnable.run();
        this.lastTask = databaseTask;
        this.onIdle.run();
    }
}
