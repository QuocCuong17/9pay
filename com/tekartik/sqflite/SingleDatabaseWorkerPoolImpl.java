package com.tekartik.sqflite;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DatabaseWorkerPool.java */
/* loaded from: classes5.dex */
public class SingleDatabaseWorkerPoolImpl implements DatabaseWorkerPool {
    private Handler handler;
    private HandlerThread handlerThread;
    final String name;
    final int priority;

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public /* synthetic */ void post(Database database, Runnable runnable) {
        post(new DatabaseTask(r2 == null ? null : new DatabaseDelegate() { // from class: com.tekartik.sqflite.DatabaseWorkerPool.1
            final /* synthetic */ Database val$database;

            AnonymousClass1(Database database2) {
                r2 = database2;
            }

            @Override // com.tekartik.sqflite.DatabaseDelegate
            public int getDatabaseId() {
                return r2.f113id;
            }

            @Override // com.tekartik.sqflite.DatabaseDelegate
            public boolean isInTransaction() {
                return r2.isInTransaction();
            }
        }, runnable));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SingleDatabaseWorkerPoolImpl(String str, int i) {
        this.name = str;
        this.priority = i;
    }

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public void start() {
        HandlerThread handlerThread = new HandlerThread(this.name, this.priority);
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
    }

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public void quit() {
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.handlerThread = null;
            this.handler = null;
        }
    }

    @Override // com.tekartik.sqflite.DatabaseWorkerPool
    public void post(DatabaseTask databaseTask) {
        this.handler.post(databaseTask.runnable);
    }
}
