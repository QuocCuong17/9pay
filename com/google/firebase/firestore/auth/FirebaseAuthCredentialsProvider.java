package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;

/* loaded from: classes4.dex */
public final class FirebaseAuthCredentialsProvider extends CredentialsProvider<User> {
    private static final String LOG_TAG = "FirebaseAuthCredentialsProvider";
    private Listener<User> changeListener;
    private boolean forceRefresh;
    private final IdTokenListener idTokenListener = new IdTokenListener() { // from class: com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda1
        @Override // com.google.firebase.auth.internal.IdTokenListener
        public final void onIdTokenChanged(InternalTokenResult internalTokenResult) {
            FirebaseAuthCredentialsProvider.this.m763x85cee08e(internalTokenResult);
        }
    };
    private InternalAuthProvider internalAuthProvider;
    private int tokenCounter;

    /* renamed from: lambda$new$0$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider, reason: not valid java name */
    public /* synthetic */ void m763x85cee08e(InternalTokenResult internalTokenResult) {
        onIdTokenChanged();
    }

    public FirebaseAuthCredentialsProvider(Deferred<InternalAuthProvider> deferred) {
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda2
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                FirebaseAuthCredentialsProvider.this.m764x223cdced(provider);
            }
        });
    }

    /* renamed from: lambda$new$1$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider, reason: not valid java name */
    public /* synthetic */ void m764x223cdced(Provider provider) {
        synchronized (this) {
            this.internalAuthProvider = (InternalAuthProvider) provider.get();
            onIdTokenChanged();
            this.internalAuthProvider.addIdTokenListener(this.idTokenListener);
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized Task<String> getToken() {
        InternalAuthProvider internalAuthProvider = this.internalAuthProvider;
        if (internalAuthProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("auth is not available"));
        }
        Task<GetTokenResult> accessToken = internalAuthProvider.getAccessToken(this.forceRefresh);
        this.forceRefresh = false;
        final int i = this.tokenCounter;
        return accessToken.continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseAuthCredentialsProvider.this.m762x41a0a62f(i, task);
            }
        });
    }

    /* renamed from: lambda$getToken$2$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider, reason: not valid java name */
    public /* synthetic */ Task m762x41a0a62f(int i, Task task) throws Exception {
        synchronized (this) {
            if (i != this.tokenCounter) {
                Logger.debug(LOG_TAG, "getToken aborted due to token change", new Object[0]);
                return getToken();
            }
            if (task.isSuccessful()) {
                return Tasks.forResult(((GetTokenResult) task.getResult()).getToken());
            }
            return Tasks.forException(task.getException());
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void setChangeListener(Listener<User> listener) {
        this.changeListener = listener;
        listener.onValue(getUser());
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void removeChangeListener() {
        this.changeListener = null;
        InternalAuthProvider internalAuthProvider = this.internalAuthProvider;
        if (internalAuthProvider != null) {
            internalAuthProvider.removeIdTokenListener(this.idTokenListener);
        }
    }

    private synchronized void onIdTokenChanged() {
        this.tokenCounter++;
        Listener<User> listener = this.changeListener;
        if (listener != null) {
            listener.onValue(getUser());
        }
    }

    private synchronized User getUser() {
        String uid;
        InternalAuthProvider internalAuthProvider = this.internalAuthProvider;
        uid = internalAuthProvider == null ? null : internalAuthProvider.getUid();
        return uid != null ? new User(uid) : User.UNAUTHENTICATED;
    }
}
