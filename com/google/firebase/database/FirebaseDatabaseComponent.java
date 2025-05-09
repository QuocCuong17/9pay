package com.google.firebase.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.android.AndroidAppCheckTokenProvider;
import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.DatabaseConfig;
import com.google.firebase.database.core.RepoInfo;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class FirebaseDatabaseComponent {
    private final FirebaseApp app;
    private final TokenProvider appCheckProvider;
    private final TokenProvider authProvider;
    private final Map<RepoInfo, FirebaseDatabase> instances = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseDatabaseComponent(FirebaseApp firebaseApp, Deferred<InternalAuthProvider> deferred, Deferred<InteropAppCheckTokenProvider> deferred2) {
        this.app = firebaseApp;
        this.authProvider = new AndroidAuthTokenProvider(deferred);
        this.appCheckProvider = new AndroidAppCheckTokenProvider(deferred2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized FirebaseDatabase get(RepoInfo repoInfo) {
        FirebaseDatabase firebaseDatabase;
        firebaseDatabase = this.instances.get(repoInfo);
        if (firebaseDatabase == null) {
            DatabaseConfig databaseConfig = new DatabaseConfig();
            if (!this.app.isDefaultApp()) {
                databaseConfig.setSessionPersistenceKey(this.app.getName());
            }
            databaseConfig.setFirebaseApp(this.app);
            databaseConfig.setAuthTokenProvider(this.authProvider);
            databaseConfig.setAppCheckTokenProvider(this.appCheckProvider);
            FirebaseDatabase firebaseDatabase2 = new FirebaseDatabase(this.app, repoInfo, databaseConfig);
            this.instances.put(repoInfo, firebaseDatabase2);
            firebaseDatabase = firebaseDatabase2;
        }
        return firebaseDatabase;
    }
}
