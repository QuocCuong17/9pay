package com.tekartik.sqflite;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class DatabaseTask {
    private final DatabaseDelegate database;
    final Runnable runnable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DatabaseTask(DatabaseDelegate databaseDelegate, Runnable runnable) {
        this.database = databaseDelegate;
        this.runnable = runnable;
    }

    public boolean isInTransaction() {
        DatabaseDelegate databaseDelegate = this.database;
        return databaseDelegate != null && databaseDelegate.isInTransaction();
    }

    public Integer getDatabaseId() {
        DatabaseDelegate databaseDelegate = this.database;
        if (databaseDelegate != null) {
            return Integer.valueOf(databaseDelegate.getDatabaseId());
        }
        return null;
    }
}
