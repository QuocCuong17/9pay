package com.google.firebase.database.core;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.android.AndroidPlatform;
import com.google.firebase.database.connection.ConnectionContext;
import com.google.firebase.database.connection.ConnectionTokenProvider;
import com.google.firebase.database.connection.HostInfo;
import com.google.firebase.database.connection.PersistentConnection;
import com.google.firebase.database.core.Context;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.database.core.persistence.NoopPersistenceManager;
import com.google.firebase.database.core.persistence.PersistenceManager;
import com.google.firebase.database.core.utilities.DefaultRunLoop;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.logging.Logger;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
public class Context {
    private static final long DEFAULT_CACHE_SIZE = 10485760;
    protected TokenProvider appCheckTokenProvider;
    protected TokenProvider authTokenProvider;
    protected EventTarget eventTarget;
    protected FirebaseApp firebaseApp;
    private PersistenceManager forcedPersistenceManager;
    protected List<String> loggedComponents;
    protected Logger logger;
    protected boolean persistenceEnabled;
    protected String persistenceKey;
    private Platform platform;
    protected RunLoop runLoop;
    protected String userAgent;
    protected Logger.Level logLevel = Logger.Level.INFO;
    protected long cacheSize = DEFAULT_CACHE_SIZE;
    private boolean frozen = false;
    private boolean stopped = false;

    private Platform getPlatform() {
        if (this.platform == null) {
            initializeAndroidPlatform();
        }
        return this.platform;
    }

    private synchronized void initializeAndroidPlatform() {
        this.platform = new AndroidPlatform(this.firebaseApp);
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void freeze() {
        if (!this.frozen) {
            this.frozen = true;
            initServices();
        }
    }

    public void requireStarted() {
        if (this.stopped) {
            restartServices();
            this.stopped = false;
        }
    }

    private void initServices() {
        ensureLogger();
        getPlatform();
        ensureUserAgent();
        ensureEventTarget();
        ensureRunLoop();
        ensureSessionIdentifier();
        ensureAuthTokenProvider();
        ensureAppTokenProvider();
    }

    private void restartServices() {
        this.eventTarget.restart();
        this.runLoop.restart();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        this.stopped = true;
        this.eventTarget.shutdown();
        this.runLoop.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void assertUnfrozen() {
        if (isFrozen()) {
            throw new DatabaseException("Modifications to DatabaseConfig objects must occur before they are in use");
        }
    }

    public List<String> getOptDebugLogComponents() {
        return this.loggedComponents;
    }

    public Logger.Level getLogLevel() {
        return this.logLevel;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public LogWrapper getLogger(String str) {
        return new LogWrapper(this.logger, str);
    }

    public LogWrapper getLogger(String str, String str2) {
        return new LogWrapper(this.logger, str, str2);
    }

    public ConnectionContext getConnectionContext() {
        return new ConnectionContext(getLogger(), wrapTokenProvider(getAuthTokenProvider(), getExecutorService()), wrapTokenProvider(getAppCheckTokenProvider(), getExecutorService()), getExecutorService(), isPersistenceEnabled(), FirebaseDatabase.getSdkVersion(), getUserAgent(), this.firebaseApp.getOptions().getApplicationId(), getSSLCacheDirectory().getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PersistenceManager getPersistenceManager(String str) {
        PersistenceManager persistenceManager = this.forcedPersistenceManager;
        if (persistenceManager != null) {
            return persistenceManager;
        }
        if (this.persistenceEnabled) {
            PersistenceManager createPersistenceManager = this.platform.createPersistenceManager(this, str);
            if (createPersistenceManager != null) {
                return createPersistenceManager;
            }
            throw new IllegalArgumentException("You have enabled persistence, but persistence is not supported on this platform.");
        }
        return new NoopPersistenceManager();
    }

    public boolean isPersistenceEnabled() {
        return this.persistenceEnabled;
    }

    public long getPersistenceCacheSizeBytes() {
        return this.cacheSize;
    }

    void forcePersistenceManager(PersistenceManager persistenceManager) {
        this.forcedPersistenceManager = persistenceManager;
    }

    public EventTarget getEventTarget() {
        return this.eventTarget;
    }

    public RunLoop getRunLoop() {
        return this.runLoop;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getPlatformVersion() {
        return getPlatform().getPlatformVersion();
    }

    public String getSessionPersistenceKey() {
        return this.persistenceKey;
    }

    public TokenProvider getAuthTokenProvider() {
        return this.authTokenProvider;
    }

    public TokenProvider getAppCheckTokenProvider() {
        return this.appCheckTokenProvider;
    }

    public PersistentConnection newPersistentConnection(HostInfo hostInfo, PersistentConnection.Delegate delegate) {
        return getPlatform().newPersistentConnection(this, getConnectionContext(), hostInfo, delegate);
    }

    private ScheduledExecutorService getExecutorService() {
        RunLoop runLoop = getRunLoop();
        if (!(runLoop instanceof DefaultRunLoop)) {
            throw new RuntimeException("Custom run loops are not supported!");
        }
        return ((DefaultRunLoop) runLoop).getExecutorService();
    }

    private void ensureLogger() {
        if (this.logger == null) {
            this.logger = getPlatform().newLogger(this, this.logLevel, this.loggedComponents);
        }
    }

    private void ensureRunLoop() {
        if (this.runLoop == null) {
            this.runLoop = this.platform.newRunLoop(this);
        }
    }

    private void ensureEventTarget() {
        if (this.eventTarget == null) {
            this.eventTarget = getPlatform().newEventTarget(this);
        }
    }

    private void ensureUserAgent() {
        if (this.userAgent == null) {
            this.userAgent = buildUserAgent(getPlatform().getUserAgent(this));
        }
    }

    private void ensureAuthTokenProvider() {
        Preconditions.checkNotNull(this.authTokenProvider, "You must register an authTokenProvider before initializing Context.");
    }

    private void ensureAppTokenProvider() {
        Preconditions.checkNotNull(this.appCheckTokenProvider, "You must register an appCheckTokenProvider before initializing Context.");
    }

    private void ensureSessionIdentifier() {
        if (this.persistenceKey == null) {
            this.persistenceKey = "default";
        }
    }

    private String buildUserAgent(String str) {
        return "Firebase/5" + RemoteSettings.FORWARD_SLASH_STRING + FirebaseDatabase.getSdkVersion() + RemoteSettings.FORWARD_SLASH_STRING + str;
    }

    private static ConnectionTokenProvider wrapTokenProvider(final TokenProvider tokenProvider, final ScheduledExecutorService scheduledExecutorService) {
        return new ConnectionTokenProvider() { // from class: com.google.firebase.database.core.Context$$ExternalSyntheticLambda0
            @Override // com.google.firebase.database.connection.ConnectionTokenProvider
            public final void getToken(boolean z, ConnectionTokenProvider.GetTokenCallback getTokenCallback) {
                TokenProvider.this.getToken(z, new Context.AnonymousClass1(scheduledExecutorService, getTokenCallback));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.database.core.Context$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass1 implements TokenProvider.GetTokenCompletionListener {
        final /* synthetic */ ConnectionTokenProvider.GetTokenCallback val$callback;
        final /* synthetic */ ScheduledExecutorService val$executorService;

        AnonymousClass1(ScheduledExecutorService scheduledExecutorService, ConnectionTokenProvider.GetTokenCallback getTokenCallback) {
            this.val$executorService = scheduledExecutorService;
            this.val$callback = getTokenCallback;
        }

        @Override // com.google.firebase.database.core.TokenProvider.GetTokenCompletionListener
        public void onSuccess(final String str) {
            ScheduledExecutorService scheduledExecutorService = this.val$executorService;
            final ConnectionTokenProvider.GetTokenCallback getTokenCallback = this.val$callback;
            scheduledExecutorService.execute(new Runnable() { // from class: com.google.firebase.database.core.Context$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectionTokenProvider.GetTokenCallback.this.onSuccess(str);
                }
            });
        }

        @Override // com.google.firebase.database.core.TokenProvider.GetTokenCompletionListener
        public void onError(final String str) {
            ScheduledExecutorService scheduledExecutorService = this.val$executorService;
            final ConnectionTokenProvider.GetTokenCallback getTokenCallback = this.val$callback;
            scheduledExecutorService.execute(new Runnable() { // from class: com.google.firebase.database.core.Context$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ConnectionTokenProvider.GetTokenCallback.this.onError(str);
                }
            });
        }
    }

    public File getSSLCacheDirectory() {
        return getPlatform().getSSLCacheDirectory();
    }
}
