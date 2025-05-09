package com.tekartik.sqflite;

/* loaded from: classes5.dex */
public interface DatabaseWorkerPool {
    void post(Database database, Runnable runnable);

    void post(DatabaseTask databaseTask);

    void quit();

    void start();

    /* renamed from: com.tekartik.sqflite.DatabaseWorkerPool$-CC, reason: invalid class name */
    /* loaded from: classes5.dex */
    public final /* synthetic */ class CC {
        public static DatabaseWorkerPool create(String str, int i, int i2) {
            if (i == 1) {
                return new SingleDatabaseWorkerPoolImpl(str, i2);
            }
            return new DatabaseWorkerPoolImpl(str, i, i2);
        }
    }
}
