package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public class AndroidAuthTokenProvider implements TokenProvider {
    private final Deferred<InternalAuthProvider> deferredAuthProvider;
    private final AtomicReference<InternalAuthProvider> internalAuth = new AtomicReference<>();

    @Override // com.google.firebase.database.core.TokenProvider
    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenChangeListener) {
    }

    public AndroidAuthTokenProvider(Deferred<InternalAuthProvider> deferred) {
        this.deferredAuthProvider = deferred;
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.AndroidAuthTokenProvider$$ExternalSyntheticLambda3
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                AndroidAuthTokenProvider.this.m740x1833a710(provider);
            }
        });
    }

    /* renamed from: lambda$new$0$com-google-firebase-database-android-AndroidAuthTokenProvider, reason: not valid java name */
    public /* synthetic */ void m740x1833a710(Provider provider) {
        this.internalAuth.set((InternalAuthProvider) provider.get());
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void getToken(boolean z, final TokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        InternalAuthProvider internalAuthProvider = this.internalAuth.get();
        if (internalAuthProvider != null) {
            internalAuthProvider.getAccessToken(z).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.database.android.AndroidAuthTokenProvider$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    TokenProvider.GetTokenCompletionListener.this.onSuccess(((GetTokenResult) obj).getToken());
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.database.android.AndroidAuthTokenProvider$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    AndroidAuthTokenProvider.lambda$getToken$2(TokenProvider.GetTokenCompletionListener.this, exc);
                }
            });
        } else {
            getTokenCompletionListener.onSuccess(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getToken$2(TokenProvider.GetTokenCompletionListener getTokenCompletionListener, Exception exc) {
        if (isUnauthenticatedUsage(exc)) {
            getTokenCompletionListener.onSuccess(null);
        } else {
            getTokenCompletionListener.onError(exc.getMessage());
        }
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void addTokenChangeListener(final ExecutorService executorService, final TokenProvider.TokenChangeListener tokenChangeListener) {
        this.deferredAuthProvider.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.AndroidAuthTokenProvider$$ExternalSyntheticLambda4
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                ((InternalAuthProvider) provider.get()).addIdTokenListener(new IdTokenListener() { // from class: com.google.firebase.database.android.AndroidAuthTokenProvider$$ExternalSyntheticLambda2
                    @Override // com.google.firebase.auth.internal.IdTokenListener
                    public final void onIdTokenChanged(InternalTokenResult internalTokenResult) {
                        r1.execute(new Runnable() { // from class: com.google.firebase.database.android.AndroidAuthTokenProvider$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                TokenProvider.TokenChangeListener.this.onTokenChange(internalTokenResult.getToken());
                            }
                        });
                    }
                });
            }
        });
    }

    private static boolean isUnauthenticatedUsage(Exception exc) {
        return (exc instanceof FirebaseApiNotAvailableException) || (exc instanceof FirebaseNoSignedInUserException);
    }
}
