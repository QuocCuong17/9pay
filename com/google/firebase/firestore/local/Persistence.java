package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Supplier;

/* loaded from: classes4.dex */
public abstract class Persistence {
    public static String DATA_MIGRATION_BUILD_OVERLAYS = "BUILD_OVERLAYS";
    static final String TAG = "Persistence";

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract BundleCache getBundleCache();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract DocumentOverlayCache getDocumentOverlayCache(User user);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract IndexManager getIndexManager(User user);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract MutationQueue getMutationQueue(User user, IndexManager indexManager);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract OverlayMigrationManager getOverlayMigrationManager();

    public abstract ReferenceDelegate getReferenceDelegate();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract RemoteDocumentCache getRemoteDocumentCache();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract TargetCache getTargetCache();

    public abstract boolean isStarted();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <T> T runTransaction(String str, Supplier<T> supplier);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void runTransaction(String str, Runnable runnable);

    public abstract void shutdown();

    public abstract void start();
}
