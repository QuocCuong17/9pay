package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public class SQLiteOverlayMigrationManager implements OverlayMigrationManager {
    private final SQLitePersistence db;

    public SQLiteOverlayMigrationManager(SQLitePersistence sQLitePersistence) {
        this.db = sQLitePersistence;
    }

    @Override // com.google.firebase.firestore.local.OverlayMigrationManager
    public void run() {
        buildOverlays();
    }

    private void buildOverlays() {
        this.db.runTransaction("build overlays", new Runnable() { // from class: com.google.firebase.firestore.local.SQLiteOverlayMigrationManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SQLiteOverlayMigrationManager.this.m833xe3799d5d();
            }
        });
    }

    /* renamed from: lambda$buildOverlays$0$com-google-firebase-firestore-local-SQLiteOverlayMigrationManager, reason: not valid java name */
    public /* synthetic */ void m833xe3799d5d() {
        if (hasPendingOverlayMigration()) {
            Set<String> allUserIds = getAllUserIds();
            RemoteDocumentCache remoteDocumentCache = this.db.getRemoteDocumentCache();
            Iterator<String> it = allUserIds.iterator();
            while (it.hasNext()) {
                User user = new User(it.next());
                SQLitePersistence sQLitePersistence = this.db;
                MutationQueue mutationQueue = sQLitePersistence.getMutationQueue(user, sQLitePersistence.getIndexManager(user));
                HashSet hashSet = new HashSet();
                Iterator<MutationBatch> it2 = mutationQueue.getAllMutationBatches().iterator();
                while (it2.hasNext()) {
                    hashSet.addAll(it2.next().getKeys());
                }
                new LocalDocumentsView(remoteDocumentCache, mutationQueue, this.db.getDocumentOverlayCache(user), this.db.getIndexManager(user)).recalculateAndSaveOverlays(hashSet);
            }
            removePendingOverlayMigrations();
        }
    }

    private Set<String> getAllUserIds() {
        final HashSet hashSet = new HashSet();
        this.db.query("SELECT DISTINCT uid FROM mutation_queues").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteOverlayMigrationManager$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                hashSet.add(((Cursor) obj).getString(0));
            }
        });
        return hashSet;
    }

    boolean hasPendingOverlayMigration() {
        final Boolean[] boolArr = {false};
        this.db.query("SELECT migration_name FROM data_migrations").forEach(new Consumer() { // from class: com.google.firebase.firestore.local.SQLiteOverlayMigrationManager$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                SQLiteOverlayMigrationManager.lambda$hasPendingOverlayMigration$2(boolArr, (Cursor) obj);
            }
        });
        return boolArr[0].booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hasPendingOverlayMigration$2(Boolean[] boolArr, Cursor cursor) {
        try {
            if (Persistence.DATA_MIGRATION_BUILD_OVERLAYS.equals(cursor.getString(0))) {
                boolArr[0] = true;
            }
        } catch (IllegalArgumentException e) {
            throw Assert.fail("SQLitePersistence.DataMigration failed to parse: %s", e);
        }
    }

    private void removePendingOverlayMigrations() {
        this.db.execute("DELETE FROM data_migrations WHERE migration_name = ?", Persistence.DATA_MIGRATION_BUILD_OVERLAYS);
    }
}
