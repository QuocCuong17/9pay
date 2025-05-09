package com.google.crypto.tink.integration.android;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

/* loaded from: classes4.dex */
public final class AndroidKeystoreKmsClient implements KmsClient {
    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 40;
    public static final String PREFIX = "android-keystore://";
    private static final String TAG = "AndroidKeystoreKmsClient";
    private static final Object keyCreationLock = new Object();
    private KeyStore keyStore;
    private final String keyUri;

    static /* synthetic */ boolean access$000() {
        return isAtLeastM();
    }

    public AndroidKeystoreKmsClient() throws GeneralSecurityException {
        this(new Builder());
    }

    @Deprecated
    public AndroidKeystoreKmsClient(String uri) {
        this(new Builder().setKeyUri(uri));
    }

    private AndroidKeystoreKmsClient(Builder builder) {
        this.keyUri = builder.keyUri;
        this.keyStore = builder.keyStore;
    }

    /* loaded from: classes4.dex */
    public static final class Builder {
        KeyStore keyStore;
        String keyUri = null;

        public Builder() {
            this.keyStore = null;
            if (!AndroidKeystoreKmsClient.access$000()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }

        public Builder setKeyUri(String val) {
            if (val == null || !val.toLowerCase(Locale.US).startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("val must start with android-keystore://");
            }
            this.keyUri = val;
            return this;
        }

        public Builder setKeyStore(KeyStore val) {
            if (val == null) {
                throw new IllegalArgumentException("val cannot be null");
            }
            this.keyStore = val;
            return this;
        }

        public AndroidKeystoreKmsClient build() {
            return new AndroidKeystoreKmsClient(this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001e, code lost:
    
        if (r3.toLowerCase(java.util.Locale.US).startsWith(com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient.PREFIX) != false) goto L15;
     */
    @Override // com.google.crypto.tink.KmsClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean doesSupport(String uri) {
        String str = this.keyUri;
        boolean z = true;
        if (str != null && str.equals(uri)) {
            return true;
        }
        if (this.keyUri == null) {
        }
        z = false;
        return z;
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withCredentials(String unused) throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withDefaultCredentials() throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public synchronized Aead getAead(String uri) throws GeneralSecurityException {
        String str = this.keyUri;
        if (str != null && !str.equals(uri)) {
            throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.keyUri, uri));
        }
        return validateAead(new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, uri), this.keyStore));
    }

    public synchronized void deleteKey(String keyUri) throws GeneralSecurityException {
        this.keyStore.deleteEntry(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri));
    }

    synchronized boolean hasKey(String keyUri) throws GeneralSecurityException {
        String validateKmsKeyUriAndRemovePrefix;
        validateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        try {
        } catch (NullPointerException unused) {
            Log.w(TAG, "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
            try {
                sleepRandomAmount();
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
                return this.keyStore.containsAlias(validateKmsKeyUriAndRemovePrefix);
            } catch (IOException e) {
                throw new GeneralSecurityException(e);
            }
        }
        return this.keyStore.containsAlias(validateKmsKeyUriAndRemovePrefix);
    }

    private static void sleepRandomAmount() {
        try {
            Thread.sleep((int) (Math.random() * 40.0d));
        } catch (InterruptedException unused) {
        }
    }

    public static Aead getOrGenerateNewAeadKey(String keyUri) throws GeneralSecurityException, IOException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            if (!androidKeystoreKmsClient.hasKey(keyUri)) {
                generateNewAesGcmKeyWithoutExistenceCheck(keyUri);
            }
        }
        return androidKeystoreKmsClient.getAead(keyUri);
    }

    public static void generateNewAeadKey(String keyUri) throws GeneralSecurityException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            if (androidKeystoreKmsClient.hasKey(keyUri)) {
                throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", keyUri));
            }
            generateNewAesGcmKeyWithoutExistenceCheck(keyUri);
        }
    }

    static void generateNewAesGcmKeyWithoutExistenceCheck(String keyUri) throws GeneralSecurityException {
        String validateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(validateKmsKeyUriAndRemovePrefix, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
        keyGenerator.generateKey();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean generateKeyIfNotExist(String keyUri) throws GeneralSecurityException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            if (androidKeystoreKmsClient.hasKey(keyUri)) {
                return false;
            }
            generateNewAesGcmKeyWithoutExistenceCheck(keyUri);
            return true;
        }
    }

    private static Aead validateAead(Aead aead) throws GeneralSecurityException {
        byte[] randBytes = Random.randBytes(10);
        byte[] bArr = new byte[0];
        if (Arrays.equals(randBytes, aead.decrypt(aead.encrypt(randBytes, bArr), bArr))) {
            return aead;
        }
        throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
    }

    private static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
