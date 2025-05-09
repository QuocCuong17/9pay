package com.google.firebase.sessions.api;

import android.util.Log;
import com.facebook.appevents.iap.InAppPurchaseConstants;
import com.google.firebase.sessions.api.SessionSubscriber;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: FirebaseSessionsDependencies.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0007H\u0002J\u001f\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\nH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\fH\u0001¢\u0006\u0002\b\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000RN\u0010\u0005\u001aB\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0012\f\u0012\n \b*\u0004\u0018\u00010\t0\t \b* \u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u0007\u0012\f\u0012\n \b*\u0004\u0018\u00010\t0\t\u0018\u00010\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies;", "", "()V", "TAG", "", "dependencies", "", "Lcom/google/firebase/sessions/api/SessionSubscriber$Name;", "kotlin.jvm.PlatformType", "Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies$Dependency;", "", "addDependency", "", "subscriberName", "getDependency", "getRegisteredSubscribers", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "getRegisteredSubscribers$com_google_firebase_firebase_sessions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubscriber", "getSubscriber$com_google_firebase_firebase_sessions", "register", "subscriber", "register$com_google_firebase_firebase_sessions", "reset", "reset$com_google_firebase_firebase_sessions", "Dependency", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class FirebaseSessionsDependencies {
    private static final String TAG = "SessionsDependencies";
    public static final FirebaseSessionsDependencies INSTANCE = new FirebaseSessionsDependencies();
    private static final Map<SessionSubscriber.Name, Dependency> dependencies = Collections.synchronizedMap(new LinkedHashMap());

    private FirebaseSessionsDependencies() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void addDependency(SessionSubscriber.Name subscriberName) {
        Intrinsics.checkNotNullParameter(subscriberName, "subscriberName");
        Map<SessionSubscriber.Name, Dependency> dependencies2 = dependencies;
        if (dependencies2.containsKey(subscriberName)) {
            Log.d(TAG, "Dependency " + subscriberName + " already added.");
            return;
        }
        Intrinsics.checkNotNullExpressionValue(dependencies2, "dependencies");
        dependencies2.put(subscriberName, new Dependency(MutexKt.Mutex(true), null, 2, 0 == true ? 1 : 0));
    }

    public final void register$com_google_firebase_firebase_sessions(SessionSubscriber subscriber) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        SessionSubscriber.Name sessionSubscriberName = subscriber.getSessionSubscriberName();
        Dependency dependency = getDependency(sessionSubscriberName);
        if (dependency.getSubscriber() != null) {
            Log.d(TAG, "Subscriber " + sessionSubscriberName + " already registered.");
            return;
        }
        dependency.setSubscriber(subscriber);
        Mutex.DefaultImpls.unlock$default(dependency.getMutex(), null, 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00a4 -> B:10:0x00a5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object getRegisteredSubscribers$com_google_firebase_firebase_sessions(Continuation<? super Map<SessionSubscriber.Name, ? extends SessionSubscriber>> continuation) {
        FirebaseSessionsDependencies$getRegisteredSubscribers$1 firebaseSessionsDependencies$getRegisteredSubscribers$1;
        int i;
        Iterator it;
        Map map;
        if (continuation instanceof FirebaseSessionsDependencies$getRegisteredSubscribers$1) {
            firebaseSessionsDependencies$getRegisteredSubscribers$1 = (FirebaseSessionsDependencies$getRegisteredSubscribers$1) continuation;
            if ((firebaseSessionsDependencies$getRegisteredSubscribers$1.label & Integer.MIN_VALUE) != 0) {
                firebaseSessionsDependencies$getRegisteredSubscribers$1.label -= Integer.MIN_VALUE;
                Object obj = firebaseSessionsDependencies$getRegisteredSubscribers$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = firebaseSessionsDependencies$getRegisteredSubscribers$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Map<SessionSubscriber.Name, Dependency> dependencies2 = dependencies;
                    Intrinsics.checkNotNullExpressionValue(dependencies2, "dependencies");
                    LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(dependencies2.size()));
                    it = dependencies2.entrySet().iterator();
                    map = linkedHashMap;
                    if (it.hasNext()) {
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object key = firebaseSessionsDependencies$getRegisteredSubscribers$1.L$5;
                    map = (Map) firebaseSessionsDependencies$getRegisteredSubscribers$1.L$4;
                    Mutex mutex = (Mutex) firebaseSessionsDependencies$getRegisteredSubscribers$1.L$3;
                    SessionSubscriber.Name name = (SessionSubscriber.Name) firebaseSessionsDependencies$getRegisteredSubscribers$1.L$2;
                    it = (Iterator) firebaseSessionsDependencies$getRegisteredSubscribers$1.L$1;
                    Map map2 = (Map) firebaseSessionsDependencies$getRegisteredSubscribers$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    try {
                        SessionSubscriber subscriber$com_google_firebase_firebase_sessions = INSTANCE.getSubscriber$com_google_firebase_firebase_sessions(name);
                        mutex.unlock(null);
                        map.put(key, subscriber$com_google_firebase_firebase_sessions);
                        map = map2;
                        if (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            key = entry.getKey();
                            name = (SessionSubscriber.Name) entry.getKey();
                            mutex = ((Dependency) entry.getValue()).getMutex();
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.L$0 = map;
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.L$1 = it;
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.L$2 = name;
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.L$3 = mutex;
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.L$4 = map;
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.L$5 = key;
                            firebaseSessionsDependencies$getRegisteredSubscribers$1.label = 1;
                            if (mutex.lock(null, firebaseSessionsDependencies$getRegisteredSubscribers$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            map2 = map;
                            SessionSubscriber subscriber$com_google_firebase_firebase_sessions2 = INSTANCE.getSubscriber$com_google_firebase_firebase_sessions(name);
                            mutex.unlock(null);
                            map.put(key, subscriber$com_google_firebase_firebase_sessions2);
                            map = map2;
                            if (it.hasNext()) {
                                return map;
                            }
                        }
                    } catch (Throwable th) {
                        mutex.unlock(null);
                        throw th;
                    }
                }
            }
        }
        firebaseSessionsDependencies$getRegisteredSubscribers$1 = new FirebaseSessionsDependencies$getRegisteredSubscribers$1(this, continuation);
        Object obj2 = firebaseSessionsDependencies$getRegisteredSubscribers$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = firebaseSessionsDependencies$getRegisteredSubscribers$1.label;
        if (i != 0) {
        }
    }

    public final SessionSubscriber getSubscriber$com_google_firebase_firebase_sessions(SessionSubscriber.Name subscriberName) {
        Intrinsics.checkNotNullParameter(subscriberName, "subscriberName");
        SessionSubscriber subscriber = getDependency(subscriberName).getSubscriber();
        if (subscriber != null) {
            return subscriber;
        }
        throw new IllegalStateException("Subscriber " + subscriberName + " has not been registered.");
    }

    public final void reset$com_google_firebase_firebase_sessions() {
        dependencies.clear();
    }

    private final Dependency getDependency(SessionSubscriber.Name subscriberName) {
        Map<SessionSubscriber.Name, Dependency> dependencies2 = dependencies;
        Intrinsics.checkNotNullExpressionValue(dependencies2, "dependencies");
        Dependency dependency = dependencies2.get(subscriberName);
        if (dependency != null) {
            Intrinsics.checkNotNullExpressionValue(dependency, "dependencies.getOrElse(s…load time.\"\n      )\n    }");
            return dependency;
        }
        throw new IllegalStateException("Cannot get dependency " + subscriberName + ". Dependencies should be added at class load time.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FirebaseSessionsDependencies.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies$Dependency;", "", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "subscriber", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "(Lkotlinx/coroutines/sync/Mutex;Lcom/google/firebase/sessions/api/SessionSubscriber;)V", "getMutex", "()Lkotlinx/coroutines/sync/Mutex;", "getSubscriber", "()Lcom/google/firebase/sessions/api/SessionSubscriber;", "setSubscriber", "(Lcom/google/firebase/sessions/api/SessionSubscriber;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", InAppPurchaseConstants.METHOD_TO_STRING, "", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes4.dex */
    public static final /* data */ class Dependency {
        private final Mutex mutex;
        private SessionSubscriber subscriber;

        public static /* synthetic */ Dependency copy$default(Dependency dependency, Mutex mutex, SessionSubscriber sessionSubscriber, int i, Object obj) {
            if ((i & 1) != 0) {
                mutex = dependency.mutex;
            }
            if ((i & 2) != 0) {
                sessionSubscriber = dependency.subscriber;
            }
            return dependency.copy(mutex, sessionSubscriber);
        }

        /* renamed from: component1, reason: from getter */
        public final Mutex getMutex() {
            return this.mutex;
        }

        /* renamed from: component2, reason: from getter */
        public final SessionSubscriber getSubscriber() {
            return this.subscriber;
        }

        public final Dependency copy(Mutex mutex, SessionSubscriber subscriber) {
            Intrinsics.checkNotNullParameter(mutex, "mutex");
            return new Dependency(mutex, subscriber);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Dependency)) {
                return false;
            }
            Dependency dependency = (Dependency) other;
            return Intrinsics.areEqual(this.mutex, dependency.mutex) && Intrinsics.areEqual(this.subscriber, dependency.subscriber);
        }

        public int hashCode() {
            int hashCode = this.mutex.hashCode() * 31;
            SessionSubscriber sessionSubscriber = this.subscriber;
            return hashCode + (sessionSubscriber == null ? 0 : sessionSubscriber.hashCode());
        }

        public String toString() {
            return "Dependency(mutex=" + this.mutex + ", subscriber=" + this.subscriber + ')';
        }

        public Dependency(Mutex mutex, SessionSubscriber sessionSubscriber) {
            Intrinsics.checkNotNullParameter(mutex, "mutex");
            this.mutex = mutex;
            this.subscriber = sessionSubscriber;
        }

        public /* synthetic */ Dependency(Mutex mutex, SessionSubscriber sessionSubscriber, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(mutex, (i & 2) != 0 ? null : sessionSubscriber);
        }

        public final Mutex getMutex() {
            return this.mutex;
        }

        public final SessionSubscriber getSubscriber() {
            return this.subscriber;
        }

        public final void setSubscriber(SessionSubscriber sessionSubscriber) {
            this.subscriber = sessionSubscriber;
        }
    }
}
