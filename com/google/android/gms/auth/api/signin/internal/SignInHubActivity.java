package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import co.hyperverge.hypersnapsdk.activities.HVRetakeActivity;
import com.github.jaiimageio.plugins.tiff.EXIFTIFFTagSet;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* loaded from: classes3.dex */
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzbt = false;
    private boolean zzbu = false;
    private SignInConfiguration zzbv;
    private boolean zzbw;
    private int zzbx;
    private Intent zzby;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class zzc implements LoaderManager.LoaderCallbacks<Void> {
        private zzc() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public final void onLoaderReset(Loader<Void> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zze(SignInHubActivity.this, GoogleApiClient.getAllClients());
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public final /* synthetic */ void onLoadFinished(Loader<Void> loader, Void r3) {
            SignInHubActivity signInHubActivity = SignInHubActivity.this;
            signInHubActivity.setResult(signInHubActivity.zzbx, SignInHubActivity.this.zzby);
            SignInHubActivity.this.finish();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zzc(GoogleSignInStatusCodes.SIGN_IN_FAILED);
            return;
        }
        if (!action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") && !action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            String valueOf = String.valueOf(intent.getAction());
            Log.e("AuthSignInClient", valueOf.length() != 0 ? "Unknown action: ".concat(valueOf) : new String("Unknown action: "));
            finish();
            return;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) intent.getBundleExtra(HVRetakeActivity.CONFIG_TAG).getParcelable(HVRetakeActivity.CONFIG_TAG);
        this.zzbv = signInConfiguration;
        if (signInConfiguration == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
            return;
        }
        if (bundle == null) {
            if (zzbt) {
                setResult(0);
                zzc(GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
                return;
            }
            zzbt = true;
            Intent intent2 = new Intent(action);
            if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
                intent2.setPackage("com.google.android.gms");
            } else {
                intent2.setPackage(getPackageName());
            }
            intent2.putExtra(HVRetakeActivity.CONFIG_TAG, this.zzbv);
            try {
                startActivityForResult(intent2, EXIFTIFFTagSet.TAG_PIXEL_X_DIMENSION);
                return;
            } catch (ActivityNotFoundException unused) {
                this.zzbu = true;
                Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                zzc(17);
                return;
            }
        }
        boolean z = bundle.getBoolean("signingInGoogleApiClients");
        this.zzbw = z;
        if (z) {
            this.zzbx = bundle.getInt("signInResultCode");
            this.zzby = (Intent) bundle.getParcelable("signInResultData");
            zzn();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzbw);
        if (this.zzbw) {
            bundle.putInt("signInResultCode", this.zzbx);
            bundle.putParcelable("signInResultData", this.zzby);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.zzbu) {
            return;
        }
        setResult(0);
        if (i != 40962) {
            return;
        }
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
            if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                zzp.zzd(this).zzc(this.zzbv.zzm(), googleSignInAccount);
                intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                intent.putExtra("googleSignInAccount", googleSignInAccount);
                this.zzbw = true;
                this.zzbx = i2;
                this.zzby = intent;
                zzn();
                return;
            }
            if (intent.hasExtra("errorCode")) {
                int intExtra = intent.getIntExtra("errorCode", 8);
                if (intExtra == 13) {
                    intExtra = GoogleSignInStatusCodes.SIGN_IN_CANCELLED;
                }
                zzc(intExtra);
                return;
            }
        }
        zzc(8);
    }

    private final void zzn() {
        getSupportLoaderManager().initLoader(0, null, new zzc());
        zzbt = false;
    }

    private final void zzc(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzbt = false;
    }
}
