package vn.ninepay.ewallet.biometric;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;

/* loaded from: classes6.dex */
public class FingerprintUiHelper extends FingerprintManager.AuthenticationCallback {
    private static final long ERROR_TIMEOUT_MILLIS = 1600;
    private static final long SUCCESS_DELAY_MILLIS = 1300;
    private final Callback mCallback;
    private CancellationSignal mCancellationSignal;
    private final FingerprintManager mFingerprintManager;
    private boolean mSelfCancelled;

    /* loaded from: classes6.dex */
    public interface Callback {
        void onAuthenticated();

        void onCancel();

        void onError();

        void onLimited();
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationHelp(int i, CharSequence charSequence) {
    }

    public FingerprintUiHelper(FingerprintManager fingerprintManager, Callback callback) {
        this.mFingerprintManager = fingerprintManager;
        this.mCallback = callback;
    }

    public void startListening() {
        this.mCancellationSignal = new CancellationSignal();
        this.mSelfCancelled = false;
        if (Build.VERSION.SDK_INT >= 23) {
            this.mFingerprintManager.authenticate(null, this.mCancellationSignal, 0, this, null);
        }
    }

    public void stopListening() {
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            this.mSelfCancelled = true;
            cancellationSignal.cancel();
            this.mCancellationSignal = null;
        }
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (!this.mSelfCancelled) {
            if (i == 7) {
                this.mCallback.onLimited();
            } else {
                this.mCallback.onCancel();
            }
        }
        Log.e("", "onAuthenticationError: " + ((Object) charSequence));
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationFailed() {
        if (this.mSelfCancelled) {
            return;
        }
        this.mCallback.onError();
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        this.mCallback.onAuthenticated();
    }
}
