package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes3.dex */
public final class zzh {
    private static Logger zzbd = new Logger("GoogleSignInCommon", new String[0]);

    public static Intent zzc(Context context, GoogleSignInOptions googleSignInOptions) {
        zzbd.d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(HVRetakeActivity.CONFIG_TAG, signInConfiguration);
        intent.putExtra(HVRetakeActivity.CONFIG_TAG, bundle);
        return intent;
    }

    public static Intent zzd(Context context, GoogleSignInOptions googleSignInOptions) {
        zzbd.d("getFallbackSignInIntent()", new Object[0]);
        Intent zzc = zzc(context, googleSignInOptions);
        zzc.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return zzc;
    }

    public static Intent zze(Context context, GoogleSignInOptions googleSignInOptions) {
        zzbd.d("getNoImplementationSignInIntent()", new Object[0]);
        Intent zzc = zzc(context, googleSignInOptions);
        zzc.setAction("com.google.android.gms.auth.NO_IMPL");
        return zzc;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static OptionalPendingResult<GoogleSignInResult> zzc(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions, boolean z) {
        GoogleSignInResult googleSignInResult;
        boolean equals;
        GoogleSignInAccount zzh;
        zzbd.d("silentSignIn()", new Object[0]);
        zzbd.d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull(googleSignInOptions);
        GoogleSignInOptions zzi = zzp.zzd(context).zzi();
        if (zzi != null) {
            Account account = zzi.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            if (account == null) {
                equals = account2 == null;
            } else {
                equals = account.equals(account2);
            }
            if (equals && !googleSignInOptions.isServerAuthCodeRequested() && ((!googleSignInOptions.isIdTokenRequested() || (zzi.isIdTokenRequested() && googleSignInOptions.getServerClientId().equals(zzi.getServerClientId()))) && new HashSet(zzi.getScopes()).containsAll(new HashSet(googleSignInOptions.getScopes())) && (zzh = zzp.zzd(context).zzh()) != null && !zzh.isExpired())) {
                googleSignInResult = new GoogleSignInResult(zzh, Status.RESULT_SUCCESS);
                if (googleSignInResult == null) {
                    zzbd.d("Eligible saved sign in result found", new Object[0]);
                    return PendingResults.immediatePendingResult(googleSignInResult, googleApiClient);
                }
                if (z) {
                    return PendingResults.immediatePendingResult(new GoogleSignInResult(null, new Status(4)), googleApiClient);
                }
                zzbd.d("trySilentSignIn()", new Object[0]);
                return new OptionalPendingResultImpl(googleApiClient.enqueue(new zzi(googleApiClient, context, googleSignInOptions)));
            }
        }
        googleSignInResult = null;
        if (googleSignInResult == null) {
        }
    }

    public static PendingResult<Status> zzc(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzbd.d("Signing out", new Object[0]);
        zzc(context);
        if (z) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return googleApiClient.execute(new zzk(googleApiClient));
    }

    public static PendingResult<Status> zzd(GoogleApiClient googleApiClient, Context context, boolean z) {
        zzbd.d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zzc(context);
        if (z) {
            return zzd.zzc(savedRefreshToken);
        }
        return googleApiClient.execute(new zzm(googleApiClient));
    }

    private static void zzc(Context context) {
        zzp.zzd(context).clear();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }

    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        if (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount")) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.RESULT_SUCCESS;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }
}
