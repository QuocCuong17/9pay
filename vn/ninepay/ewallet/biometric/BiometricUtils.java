package vn.ninepay.ewallet.biometric;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import androidx.biometric.BiometricManager;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* loaded from: classes6.dex */
public class BiometricUtils {
    private static final String DEFAULT_KEY_NAME = "default_key";
    private static BiometricUtils INSTANCE;
    private static Context activity;
    private static BiometricCallback biometricCallback;
    private static BiometricManager biometricManager;
    private KeyGenerator mKeyGenerator;
    private KeyStore mKeyStore;

    /* loaded from: classes6.dex */
    public interface BiometricCallback {
        void biometricChange();

        void canAuthenticate();

        void hardwareNoSupport();

        void noEnrolled();
    }

    public static BiometricUtils getInstance(Context context) {
        activity = context;
        if (INSTANCE == null) {
            INSTANCE = new BiometricUtils();
        }
        initBiometric();
        return INSTANCE;
    }

    private static void initBiometric() {
        biometricManager = BiometricManager.from(activity);
    }

    public void setCallback(BiometricCallback biometricCallback2) {
        biometricCallback = biometricCallback2;
    }

    public void checkChangeBiometric() {
        try {
            this.mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            try {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mKeyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                }
                try {
                    Cipher cipher = Build.VERSION.SDK_INT >= 23 ? Cipher.getInstance("AES/CBC/PKCS7Padding") : null;
                    if (getCurrentKey() == null) {
                        createKey();
                    }
                    if (Build.VERSION.SDK_INT >= 23) {
                        initCipher(cipher);
                    }
                } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                    throw new RuntimeException("Failed to get an instance of Cipher", e);
                }
            } catch (NoSuchAlgorithmException | NoSuchProviderException e2) {
                throw new RuntimeException("Failed to get an instance of KeyGenerator", e2);
            }
        } catch (KeyStoreException e3) {
            throw new RuntimeException("Failed to get an instance of KeyStore", e3);
        }
    }

    private Key getCurrentKey() {
        try {
            this.mKeyStore.load(null);
            return this.mKeyStore.getKey(DEFAULT_KEY_NAME, null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initCipher(Cipher cipher) {
        try {
            this.mKeyStore.load(null);
            cipher.init(1, (SecretKey) this.mKeyStore.getKey(DEFAULT_KEY_NAME, null));
            BiometricCallback biometricCallback2 = biometricCallback;
            if (biometricCallback2 != null) {
                biometricCallback2.canAuthenticate();
            }
        } catch (KeyPermanentlyInvalidatedException unused) {
            BiometricCallback biometricCallback3 = biometricCallback;
            if (biometricCallback3 != null) {
                biometricCallback3.biometricChange();
            }
            createKey();
        } catch (IOException e) {
            e = e;
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (InvalidKeyException e2) {
            e = e2;
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (KeyStoreException e3) {
            e = e3;
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (UnrecoverableKeyException e5) {
            e = e5;
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (CertificateException e6) {
            e = e6;
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    private void createKey() {
        try {
            this.mKeyStore.load(null);
            KeyGenParameterSpec.Builder encryptionPaddings = Build.VERSION.SDK_INT >= 23 ? new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME, 3).setBlockModes("CBC").setUserAuthenticationRequired(true).setEncryptionPaddings("PKCS7Padding") : null;
            if (Build.VERSION.SDK_INT >= 24) {
                encryptionPaddings.setInvalidatedByBiometricEnrollment(true);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                this.mKeyGenerator.init(encryptionPaddings.build());
            }
            this.mKeyGenerator.generateKey();
        } catch (IOException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | CertificateException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetBiometric() {
        try {
            this.mKeyStore.deleteEntry(DEFAULT_KEY_NAME);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    public void checkStatusBiometric(Activity activity2) {
        BiometricCallback biometricCallback2;
        if (Build.VERSION.SDK_INT >= 23) {
            int canAuthenticate = biometricManager.canAuthenticate();
            if (canAuthenticate == 0) {
                if (!((FingerprintManager) activity2.getSystemService(FingerprintManager.class)).hasEnrolledFingerprints() && (biometricCallback2 = biometricCallback) != null) {
                    biometricCallback2.noEnrolled();
                }
                checkChangeBiometric();
                return;
            }
            if (canAuthenticate != 1) {
                if (canAuthenticate == 11) {
                    BiometricCallback biometricCallback3 = biometricCallback;
                    if (biometricCallback3 != null) {
                        biometricCallback3.noEnrolled();
                        return;
                    }
                    return;
                }
                if (canAuthenticate != 12) {
                    return;
                }
            }
            BiometricCallback biometricCallback4 = biometricCallback;
            if (biometricCallback4 != null) {
                biometricCallback4.hardwareNoSupport();
            }
        }
    }
}
