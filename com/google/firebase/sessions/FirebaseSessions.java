package com.google.firebase.sessions;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.ktx.FirebaseKt;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import com.google.firebase.sessions.api.SessionSubscriber;
import com.google.firebase.sessions.settings.SessionsSettings;
import io.sentry.protocol.App;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.apache.commons.io.FilenameUtils;

/* compiled from: FirebaseSessions.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 #2\u00020\u0001:\u0001#B5\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessions;", "", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "firebaseInstallations", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "backgroundDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "blockingDispatcher", "transportFactoryProvider", "Lcom/google/firebase/inject/Provider;", "Lcom/google/android/datatransport/TransportFactory;", "(Lcom/google/firebase/FirebaseApp;Lcom/google/firebase/installations/FirebaseInstallationsApi;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lcom/google/firebase/inject/Provider;)V", "applicationInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "eventGDTLogger", "Lcom/google/firebase/sessions/EventGDTLogger;", "sessionCoordinator", "Lcom/google/firebase/sessions/SessionCoordinator;", "sessionGenerator", "Lcom/google/firebase/sessions/SessionGenerator;", "sessionSettings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "timeProvider", "Lcom/google/firebase/sessions/TimeProvider;", "initiateSessionStart", "", "sessionDetails", "Lcom/google/firebase/sessions/SessionDetails;", "(Lcom/google/firebase/sessions/SessionDetails;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "subscriber", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "shouldCollectEvents", "", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class FirebaseSessions {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "FirebaseSessions";
    private final ApplicationInfo applicationInfo;
    private final EventGDTLogger eventGDTLogger;
    private final FirebaseApp firebaseApp;
    private final SessionCoordinator sessionCoordinator;
    private final SessionGenerator sessionGenerator;
    private final SessionsSettings sessionSettings;
    private final TimeProvider timeProvider;

    public static final FirebaseSessions getInstance() {
        return INSTANCE.getInstance();
    }

    @JvmStatic
    public static final FirebaseSessions getInstance(FirebaseApp firebaseApp) {
        return INSTANCE.getInstance(firebaseApp);
    }

    public FirebaseSessions(FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallations, CoroutineDispatcher backgroundDispatcher, CoroutineDispatcher blockingDispatcher, Provider<TransportFactory> transportFactoryProvider) {
        Intrinsics.checkNotNullParameter(firebaseApp, "firebaseApp");
        Intrinsics.checkNotNullParameter(firebaseInstallations, "firebaseInstallations");
        Intrinsics.checkNotNullParameter(backgroundDispatcher, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(blockingDispatcher, "blockingDispatcher");
        Intrinsics.checkNotNullParameter(transportFactoryProvider, "transportFactoryProvider");
        this.firebaseApp = firebaseApp;
        ApplicationInfo applicationInfo = SessionEvents.INSTANCE.getApplicationInfo(firebaseApp);
        this.applicationInfo = applicationInfo;
        Context applicationContext = firebaseApp.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "firebaseApp.applicationContext");
        CoroutineDispatcher coroutineDispatcher = backgroundDispatcher;
        SessionsSettings sessionsSettings = new SessionsSettings(applicationContext, blockingDispatcher, coroutineDispatcher, firebaseInstallations, applicationInfo);
        this.sessionSettings = sessionsSettings;
        Time time = new Time();
        this.timeProvider = time;
        EventGDTLogger eventGDTLogger = new EventGDTLogger(transportFactoryProvider);
        this.eventGDTLogger = eventGDTLogger;
        this.sessionCoordinator = new SessionCoordinator(firebaseInstallations, eventGDTLogger);
        SessionGenerator sessionGenerator = new SessionGenerator(shouldCollectEvents(), time, null, 4, null);
        this.sessionGenerator = sessionGenerator;
        final SessionInitiator sessionInitiator = new SessionInitiator(time, coroutineDispatcher, new SessionInitiateListener() { // from class: com.google.firebase.sessions.FirebaseSessions$sessionInitiateListener$1
            @Override // com.google.firebase.sessions.SessionInitiateListener
            public Object onInitiateSession(SessionDetails sessionDetails, Continuation<? super Unit> continuation) {
                Object initiateSessionStart;
                initiateSessionStart = FirebaseSessions.this.initiateSessionStart(sessionDetails, continuation);
                return initiateSessionStart == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? initiateSessionStart : Unit.INSTANCE;
            }
        }, sessionsSettings, sessionGenerator);
        final Context applicationContext2 = firebaseApp.getApplicationContext().getApplicationContext();
        if (applicationContext2 instanceof Application) {
            ((Application) applicationContext2).registerActivityLifecycleCallbacks(sessionInitiator.getActivityLifecycleCallbacks());
            firebaseApp.addLifecycleEventListener(new FirebaseAppLifecycleListener() { // from class: com.google.firebase.sessions.FirebaseSessions$$ExternalSyntheticLambda0
                @Override // com.google.firebase.FirebaseAppLifecycleListener
                public final void onDeleted(String str, FirebaseOptions firebaseOptions) {
                    FirebaseSessions.m906_init_$lambda0(applicationContext2, sessionInitiator, str, firebaseOptions);
                }
            });
            return;
        }
        Log.e(TAG, "Failed to register lifecycle callbacks, unexpected context " + applicationContext2.getClass() + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m906_init_$lambda0(Context context, SessionInitiator sessionInitiator, String str, FirebaseOptions firebaseOptions) {
        Intrinsics.checkNotNullParameter(sessionInitiator, "$sessionInitiator");
        Log.w(TAG, "FirebaseApp instance deleted. Sessions library will not collect session data.");
        ((Application) context).unregisterActivityLifecycleCallbacks(sessionInitiator.getActivityLifecycleCallbacks());
    }

    public final void register(SessionSubscriber subscriber) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        FirebaseSessionsDependencies.INSTANCE.register$com_google_firebase_firebase_sessions(subscriber);
        Log.d(TAG, "Registering Sessions SDK subscriber with name: " + subscriber.getSessionSubscriberName() + ", data collection enabled: " + subscriber.isDataCollectionEnabled());
        if (this.sessionGenerator.getHasGenerateSession()) {
            subscriber.onSessionChanged(new SessionSubscriber.SessionDetails(this.sessionGenerator.getCurrentSession().getSessionId()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:1|(2:3|(6:5|6|7|(1:(1:(1:(4:12|13|14|15)(2:18|19))(3:20|21|(2:23|24)(2:25|(2:27|28)(5:29|(1:31)|13|14|15))))(1:32))(2:62|(1:64)(1:65))|33|(2:35|36)(6:37|(2:40|38)|41|42|(2:53|(2:54|(1:61)(2:56|(2:58|59)(1:60))))(0)|(2:47|48)(2:49|(1:51)(3:52|21|(0)(0))))))|68|6|7|(0)(0)|33|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0033, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x012b, code lost:
    
        android.util.Log.w(com.google.firebase.sessions.FirebaseSessions.TAG, "FirebaseApp is not initialized. Sessions library will not collect session data.", r12);
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initiateSessionStart(SessionDetails sessionDetails, Continuation<? super Unit> continuation) {
        FirebaseSessions$initiateSessionStart$1 firebaseSessions$initiateSessionStart$1;
        int i;
        FirebaseSessions firebaseSessions;
        Map<SessionSubscriber.Name, ? extends SessionSubscriber> map;
        FirebaseSessions firebaseSessions2;
        SessionDetails sessionDetails2;
        Map<SessionSubscriber.Name, ? extends SessionSubscriber> map2;
        if (continuation instanceof FirebaseSessions$initiateSessionStart$1) {
            firebaseSessions$initiateSessionStart$1 = (FirebaseSessions$initiateSessionStart$1) continuation;
            if ((firebaseSessions$initiateSessionStart$1.label & Integer.MIN_VALUE) != 0) {
                firebaseSessions$initiateSessionStart$1.label -= Integer.MIN_VALUE;
                Object obj = firebaseSessions$initiateSessionStart$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = firebaseSessions$initiateSessionStart$1.label;
                boolean z = true;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    FirebaseSessionsDependencies firebaseSessionsDependencies = FirebaseSessionsDependencies.INSTANCE;
                    firebaseSessions$initiateSessionStart$1.L$0 = this;
                    firebaseSessions$initiateSessionStart$1.L$1 = sessionDetails;
                    firebaseSessions$initiateSessionStart$1.label = 1;
                    obj = firebaseSessionsDependencies.getRegisteredSubscribers$com_google_firebase_firebase_sessions(firebaseSessions$initiateSessionStart$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    firebaseSessions = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        map2 = (Map) firebaseSessions$initiateSessionStart$1.L$2;
                        sessionDetails2 = (SessionDetails) firebaseSessions$initiateSessionStart$1.L$1;
                        firebaseSessions2 = (FirebaseSessions) firebaseSessions$initiateSessionStart$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        if (firebaseSessions2.sessionSettings.getSessionsEnabled()) {
                            Log.d(TAG, "Sessions SDK disabled. Events will not be sent.");
                            return Unit.INSTANCE;
                        }
                        if (!firebaseSessions2.sessionGenerator.getCollectEvents()) {
                            Log.d(TAG, "Sessions SDK has dropped this session due to sampling.");
                            return Unit.INSTANCE;
                        }
                        SessionEvent startSession = SessionEvents.INSTANCE.startSession(firebaseSessions2.firebaseApp, sessionDetails2, firebaseSessions2.sessionSettings, map2);
                        SessionCoordinator sessionCoordinator = firebaseSessions2.sessionCoordinator;
                        firebaseSessions$initiateSessionStart$1.L$0 = null;
                        firebaseSessions$initiateSessionStart$1.L$1 = null;
                        firebaseSessions$initiateSessionStart$1.L$2 = null;
                        firebaseSessions$initiateSessionStart$1.label = 3;
                        if (sessionCoordinator.attemptLoggingSessionEvent(startSession, firebaseSessions$initiateSessionStart$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    sessionDetails = (SessionDetails) firebaseSessions$initiateSessionStart$1.L$1;
                    firebaseSessions = (FirebaseSessions) firebaseSessions$initiateSessionStart$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                map = (Map) obj;
                if (!map.isEmpty()) {
                    Log.d(TAG, "Sessions SDK did not have any dependent SDKs register as dependencies. Events will not be sent.");
                    return Unit.INSTANCE;
                }
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    ((SessionSubscriber) it.next()).onSessionChanged(new SessionSubscriber.SessionDetails(sessionDetails.getSessionId()));
                }
                Collection<? extends SessionSubscriber> values = map.values();
                if (!(values instanceof Collection) || !values.isEmpty()) {
                    Iterator it2 = values.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        if (((SessionSubscriber) it2.next()).isDataCollectionEnabled()) {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    Log.d(TAG, "Data Collection is disabled for all subscribers. Skipping this Session Event");
                    return Unit.INSTANCE;
                }
                Log.d(TAG, "Data Collection is enabled for at least one Subscriber");
                SessionsSettings sessionsSettings = firebaseSessions.sessionSettings;
                firebaseSessions$initiateSessionStart$1.L$0 = firebaseSessions;
                firebaseSessions$initiateSessionStart$1.L$1 = sessionDetails;
                firebaseSessions$initiateSessionStart$1.L$2 = map;
                firebaseSessions$initiateSessionStart$1.label = 2;
                if (sessionsSettings.updateSettings(firebaseSessions$initiateSessionStart$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                firebaseSessions2 = firebaseSessions;
                sessionDetails2 = sessionDetails;
                map2 = map;
                if (firebaseSessions2.sessionSettings.getSessionsEnabled()) {
                }
            }
        }
        firebaseSessions$initiateSessionStart$1 = new FirebaseSessions$initiateSessionStart$1(this, continuation);
        Object obj2 = firebaseSessions$initiateSessionStart$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = firebaseSessions$initiateSessionStart$1.label;
        boolean z2 = true;
        if (i != 0) {
        }
        map = (Map) obj2;
        if (!map.isEmpty()) {
        }
    }

    private final boolean shouldCollectEvents() {
        return Math.random() <= this.sessionSettings.getSamplingRate();
    }

    /* compiled from: FirebaseSessions.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessions$Companion;", "", "()V", "TAG", "", "instance", "Lcom/google/firebase/sessions/FirebaseSessions;", "getInstance$annotations", "getInstance", "()Lcom/google/firebase/sessions/FirebaseSessions;", App.TYPE, "Lcom/google/firebase/FirebaseApp;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final FirebaseSessions getInstance() {
            return getInstance(FirebaseKt.getApp(Firebase.INSTANCE));
        }

        @JvmStatic
        public final FirebaseSessions getInstance(FirebaseApp app) {
            Intrinsics.checkNotNullParameter(app, "app");
            Object obj = app.get(FirebaseSessions.class);
            Intrinsics.checkNotNullExpressionValue(obj, "app.get(FirebaseSessions::class.java)");
            return (FirebaseSessions) obj;
        }
    }
}
