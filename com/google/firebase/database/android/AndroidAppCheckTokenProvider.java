package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public class AndroidAppCheckTokenProvider implements TokenProvider {
    private final Deferred<InteropAppCheckTokenProvider> deferredAppCheckProvider;
    private final AtomicReference<InteropAppCheckTokenProvider> internalAppCheck = new AtomicReference<>();

    @Override // com.google.firebase.database.core.TokenProvider
    public void removeTokenChangeListener(TokenProvider.TokenChangeListener tokenChangeListener) {
    }

    public AndroidAppCheckTokenProvider(Deferred<InteropAppCheckTokenProvider> deferred) {
        this.deferredAppCheckProvider = deferred;
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda3
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                AndroidAppCheckTokenProvider.this.m739x13f0d4b1(provider);
            }
        });
    }

    /* renamed from: lambda$new$0$com-google-firebase-database-android-AndroidAppCheckTokenProvider, reason: not valid java name */
    public /* synthetic */ void m739x13f0d4b1(Provider provider) {
        this.internalAppCheck.set((InteropAppCheckTokenProvider) provider.get());
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void getToken(boolean z, final TokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = this.internalAppCheck.get();
        if (interopAppCheckTokenProvider != null) {
            interopAppCheckTokenProvider.getToken(z).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    TokenProvider.GetTokenCompletionListener.this.onSuccess(((AppCheckTokenResult) obj).getToken());
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    TokenProvider.GetTokenCompletionListener.this.onError(exc.getMessage());
                }
            });
        } else {
            getTokenCompletionListener.onSuccess(null);
        }
    }

    @Override // com.google.firebase.database.core.TokenProvider
    public void addTokenChangeListener(final ExecutorService executorService, final TokenProvider.TokenChangeListener tokenChangeListener) {
        this.deferredAppCheckProvider.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda4
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                ((InteropAppCheckTokenProvider) provider.get()).addAppCheckTokenListener(new AppCheckTokenListener() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda2
                    @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
                    public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
                        r1.execute(new Runnable() { // from class: com.google.firebase.database.android.AndroidAppCheckTokenProvider$$ExternalSyntheticLambda5
                            @Override // java.lang.Runnable
                            public final void run() {
                                TokenProvider.TokenChangeListener.this.onTokenChange(appCheckTokenResult.getToken());
                            }
                        });
                    }
                });
            }
        });
    }
}
