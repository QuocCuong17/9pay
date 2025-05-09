package com.google.firebase.database.core;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Logger;
import com.google.firebase.database.logging.Logger;
import java.util.List;

/* loaded from: classes4.dex */
public class DatabaseConfig extends Context {
    public synchronized void setLogger(Logger logger) {
        assertUnfrozen();
        this.logger = logger;
    }

    public synchronized void setEventTarget(EventTarget eventTarget) {
        assertUnfrozen();
        this.eventTarget = eventTarget;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.database.core.DatabaseConfig$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$Logger$Level;

        static {
            int[] iArr = new int[Logger.Level.values().length];
            $SwitchMap$com$google$firebase$database$Logger$Level = iArr;
            try {
                iArr[Logger.Level.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.NONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public synchronized void setLogLevel(Logger.Level level) {
        assertUnfrozen();
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$Logger$Level[level.ordinal()];
        if (i == 1) {
            this.logLevel = Logger.Level.DEBUG;
        } else if (i == 2) {
            this.logLevel = Logger.Level.INFO;
        } else if (i == 3) {
            this.logLevel = Logger.Level.WARN;
        } else if (i == 4) {
            this.logLevel = Logger.Level.ERROR;
        } else if (i == 5) {
            this.logLevel = Logger.Level.NONE;
        } else {
            throw new IllegalArgumentException("Unknown log level: " + level);
        }
    }

    public synchronized void setDebugLogComponents(List<String> list) {
        assertUnfrozen();
        setLogLevel(Logger.Level.DEBUG);
        this.loggedComponents = list;
    }

    public void setRunLoop(RunLoop runLoop) {
        this.runLoop = runLoop;
    }

    public void setAuthTokenProvider(TokenProvider tokenProvider) {
        this.authTokenProvider = tokenProvider;
    }

    public void setAppCheckTokenProvider(TokenProvider tokenProvider) {
        this.appCheckTokenProvider = tokenProvider;
    }

    public synchronized void setSessionPersistenceKey(String str) {
        assertUnfrozen();
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
        }
        this.persistenceKey = str;
    }

    public synchronized void setPersistenceEnabled(boolean z) {
        assertUnfrozen();
        this.persistenceEnabled = z;
    }

    public synchronized void setPersistenceCacheSizeBytes(long j) {
        assertUnfrozen();
        if (j < 1048576) {
            throw new DatabaseException("The minimum cache size must be at least 1MB");
        }
        if (j > 104857600) {
            throw new DatabaseException("Firebase Database currently doesn't support a cache size larger than 100MB");
        }
        this.cacheSize = j;
    }

    public synchronized void setFirebaseApp(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
    }
}
