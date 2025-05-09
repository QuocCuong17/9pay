package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zzh;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

/* loaded from: classes3.dex */
public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    private static final zzc zzar = new zzc(null);
    private static int zzas = zzd.zzau;

    /* loaded from: classes3.dex */
    private static class zzc implements PendingResultUtil.ResultConverter<GoogleSignInResult, GoogleSignInAccount> {
        private zzc() {
        }

        @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
        public final /* synthetic */ GoogleSignInAccount convert(GoogleSignInResult googleSignInResult) {
            return googleSignInResult.getSignInAccount();
        }

        /* synthetic */ zzc(com.google.android.gms.auth.api.signin.zzc zzcVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum zzd {
        public static final int zzau = 1;
        public static final int zzav = 2;
        public static final int zzaw = 3;
        public static final int zzax = 4;
        private static final /* synthetic */ int[] zzay = {1, 2, 3, 4};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM2TBKD0NM2S395TPMIPRED5N2UHRFDTJMOPAJD5JMSIBE8DM6IPBEEGI4IRBGDHIMQPBEEHGN8QBFDOTG____0() {
            return (int[]) zzay.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new ApiExceptionMapper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    private final synchronized int zze() {
        if (zzas == zzd.zzau) {
            Context applicationContext = getApplicationContext();
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(applicationContext, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            if (isGooglePlayServicesAvailable == 0) {
                zzas = zzd.zzax;
            } else if (googleApiAvailability.getErrorResolutionIntent(applicationContext, isGooglePlayServicesAvailable, null) == null && DynamiteModule.getLocalVersion(applicationContext, "com.google.android.gms.auth.api.fallback") != 0) {
                zzas = zzd.zzaw;
            } else {
                zzas = zzd.zzav;
            }
        }
        return zzas;
    }

    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        int i = com.google.android.gms.auth.api.signin.zzc.zzat[zze() - 1];
        if (i == 1) {
            return zzh.zzd(applicationContext, getApiOptions());
        }
        if (i == 2) {
            return zzh.zzc(applicationContext, getApiOptions());
        }
        return zzh.zze(applicationContext, getApiOptions());
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return PendingResultUtil.toTask(zzh.zzc(asGoogleApiClient(), getApplicationContext(), getApiOptions(), zze() == zzd.zzaw), zzar);
    }

    public Task<Void> signOut() {
        return PendingResultUtil.toVoidTask(zzh.zzc(asGoogleApiClient(), getApplicationContext(), zze() == zzd.zzaw));
    }

    public Task<Void> revokeAccess() {
        return PendingResultUtil.toVoidTask(zzh.zzd(asGoogleApiClient(), getApplicationContext(), zze() == zzd.zzaw));
    }
}
