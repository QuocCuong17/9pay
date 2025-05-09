package com.google.firebase.database;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.Logger;
import com.google.firebase.database.core.DatabaseConfig;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.Repo;
import com.google.firebase.database.core.RepoInfo;
import com.google.firebase.database.core.RepoManager;
import com.google.firebase.database.core.utilities.ParsedUrl;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.emulators.EmulatedServiceSettings;
import java.util.Objects;

/* loaded from: classes4.dex */
public class FirebaseDatabase {
    private static final String SDK_VERSION = "20.2.2";
    private final FirebaseApp app;
    private final DatabaseConfig config;
    private EmulatedServiceSettings emulatorSettings;
    private Repo repo;
    private final RepoInfo repoInfo;

    public static String getSdkVersion() {
        return "20.2.2";
    }

    public static FirebaseDatabase getInstance() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        if (firebaseApp == null) {
            throw new DatabaseException("You must call FirebaseApp.initialize() first.");
        }
        return getInstance(firebaseApp);
    }

    public static FirebaseDatabase getInstance(String str) {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        if (firebaseApp == null) {
            throw new DatabaseException("You must call FirebaseApp.initialize() first.");
        }
        return getInstance(firebaseApp, str);
    }

    public static FirebaseDatabase getInstance(FirebaseApp firebaseApp) {
        String databaseUrl = firebaseApp.getOptions().getDatabaseUrl();
        if (databaseUrl == null) {
            if (firebaseApp.getOptions().getProjectId() == null) {
                throw new DatabaseException("Failed to get FirebaseDatabase instance: Can't determine Firebase Database URL. Be sure to include a Project ID in your configuration.");
            }
            databaseUrl = "https://" + firebaseApp.getOptions().getProjectId() + "-default-rtdb.firebaseio.com";
        }
        return getInstance(firebaseApp, databaseUrl);
    }

    public static synchronized FirebaseDatabase getInstance(FirebaseApp firebaseApp, String str) {
        FirebaseDatabase firebaseDatabase;
        synchronized (FirebaseDatabase.class) {
            if (TextUtils.isEmpty(str)) {
                throw new DatabaseException("Failed to get FirebaseDatabase instance: Specify DatabaseURL within FirebaseApp or from your getInstance() call.");
            }
            Preconditions.checkNotNull(firebaseApp, "Provided FirebaseApp must not be null.");
            FirebaseDatabaseComponent firebaseDatabaseComponent = (FirebaseDatabaseComponent) firebaseApp.get(FirebaseDatabaseComponent.class);
            Preconditions.checkNotNull(firebaseDatabaseComponent, "Firebase Database component is not present.");
            ParsedUrl parseUrl = Utilities.parseUrl(str);
            if (!parseUrl.path.isEmpty()) {
                throw new DatabaseException("Specified Database URL '" + str + "' is invalid. It should point to the root of a Firebase Database but it includes a path: " + parseUrl.path.toString());
            }
            firebaseDatabase = firebaseDatabaseComponent.get(parseUrl.repoInfo);
        }
        return firebaseDatabase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FirebaseDatabase createForTests(FirebaseApp firebaseApp, RepoInfo repoInfo, DatabaseConfig databaseConfig) {
        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(firebaseApp, repoInfo, databaseConfig);
        firebaseDatabase.ensureRepo();
        return firebaseDatabase;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseDatabase(FirebaseApp firebaseApp, RepoInfo repoInfo, DatabaseConfig databaseConfig) {
        this.app = firebaseApp;
        this.repoInfo = repoInfo;
        this.config = databaseConfig;
    }

    public FirebaseApp getApp() {
        return this.app;
    }

    public DatabaseReference getReference() {
        ensureRepo();
        return new DatabaseReference(this.repo, Path.getEmptyPath());
    }

    public DatabaseReference getReference(String str) {
        ensureRepo();
        Objects.requireNonNull(str, "Can't pass null for argument 'pathString' in FirebaseDatabase.getReference()");
        Validation.validateRootPathString(str);
        return new DatabaseReference(this.repo, new Path(str));
    }

    public DatabaseReference getReferenceFromUrl(String str) {
        ensureRepo();
        Objects.requireNonNull(str, "Can't pass null for argument 'url' in FirebaseDatabase.getReferenceFromUrl()");
        ParsedUrl parseUrl = Utilities.parseUrl(str);
        parseUrl.repoInfo.applyEmulatorSettings(this.emulatorSettings);
        if (!parseUrl.repoInfo.host.equals(this.repo.getRepoInfo().host)) {
            throw new DatabaseException("Invalid URL (" + str + ") passed to getReference().  URL was expected to match configured Database URL: " + getReference());
        }
        return new DatabaseReference(this.repo, parseUrl.path);
    }

    public void purgeOutstandingWrites() {
        ensureRepo();
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.FirebaseDatabase.1
            @Override // java.lang.Runnable
            public void run() {
                FirebaseDatabase.this.repo.purgeOutstandingWrites();
            }
        });
    }

    public void goOnline() {
        ensureRepo();
        RepoManager.resume(this.repo);
    }

    public void goOffline() {
        ensureRepo();
        RepoManager.interrupt(this.repo);
    }

    public synchronized void setLogLevel(Logger.Level level) {
        assertUnfrozen("setLogLevel");
        this.config.setLogLevel(level);
    }

    public synchronized void setPersistenceEnabled(boolean z) {
        assertUnfrozen("setPersistenceEnabled");
        this.config.setPersistenceEnabled(z);
    }

    public synchronized void setPersistenceCacheSizeBytes(long j) {
        assertUnfrozen("setPersistenceCacheSizeBytes");
        this.config.setPersistenceCacheSizeBytes(j);
    }

    public void useEmulator(String str, int i) {
        if (this.repo != null) {
            throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
        }
        this.emulatorSettings = new EmulatedServiceSettings(str, i);
    }

    private void assertUnfrozen(String str) {
        if (this.repo == null) {
            return;
        }
        throw new DatabaseException("Calls to " + str + "() must be made before any other usage of FirebaseDatabase instance.");
    }

    private synchronized void ensureRepo() {
        if (this.repo == null) {
            this.repoInfo.applyEmulatorSettings(this.emulatorSettings);
            this.repo = RepoManager.createRepo(this.config, this.repoInfo, this);
        }
    }

    DatabaseConfig getConfig() {
        return this.config;
    }
}
