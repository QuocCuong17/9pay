package androidx.security.crypto;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Objects;

/* loaded from: classes2.dex */
public final class MasterKey {
    public static final int DEFAULT_AES_GCM_MASTER_KEY_SIZE = 256;
    private static final int DEFAULT_AUTHENTICATION_VALIDITY_DURATION_SECONDS = 300;
    public static final String DEFAULT_MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    static final String KEYSTORE_PATH_URI = "android-keystore://";
    private final String mKeyAlias;
    private final KeyGenParameterSpec mKeyGenParameterSpec;

    /* loaded from: classes2.dex */
    public enum KeyScheme {
        AES256_GCM
    }

    public static int getDefaultAuthenticationValidityDurationSeconds() {
        return 300;
    }

    MasterKey(String str, Object obj) {
        this.mKeyAlias = str;
        if (Build.VERSION.SDK_INT >= 23) {
            this.mKeyGenParameterSpec = (KeyGenParameterSpec) obj;
        } else {
            this.mKeyGenParameterSpec = null;
        }
    }

    public boolean isKeyStoreBacked() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(this.mKeyAlias);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            return false;
        }
    }

    public boolean isUserAuthenticationRequired() {
        KeyGenParameterSpec keyGenParameterSpec;
        return Build.VERSION.SDK_INT >= 23 && (keyGenParameterSpec = this.mKeyGenParameterSpec) != null && Api23Impl.isUserAuthenticationRequired(keyGenParameterSpec);
    }

    public int getUserAuthenticationValidityDurationSeconds() {
        KeyGenParameterSpec keyGenParameterSpec;
        if (Build.VERSION.SDK_INT >= 23 && (keyGenParameterSpec = this.mKeyGenParameterSpec) != null) {
            return Api23Impl.getUserAuthenticationValidityDurationSeconds(keyGenParameterSpec);
        }
        return 0;
    }

    public boolean isStrongBoxBacked() {
        KeyGenParameterSpec keyGenParameterSpec;
        if (Build.VERSION.SDK_INT < 28 || (keyGenParameterSpec = this.mKeyGenParameterSpec) == null) {
            return false;
        }
        return Api28Impl.isStrongBoxBacked(keyGenParameterSpec);
    }

    public String toString() {
        return "MasterKey{keyAlias=" + this.mKeyAlias + ", isKeyStoreBacked=" + isKeyStoreBacked() + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKeyAlias() {
        return this.mKeyAlias;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        boolean mAuthenticationRequired;
        final Context mContext;
        final String mKeyAlias;
        KeyGenParameterSpec mKeyGenParameterSpec;
        KeyScheme mKeyScheme;
        boolean mRequestStrongBoxBacked;
        int mUserAuthenticationValidityDurationSeconds;

        public Builder(Context context) {
            this(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS);
        }

        public Builder(Context context, String str) {
            this.mContext = context.getApplicationContext();
            this.mKeyAlias = str;
        }

        public Builder setKeyScheme(KeyScheme keyScheme) {
            if (AnonymousClass1.$SwitchMap$androidx$security$crypto$MasterKey$KeyScheme[keyScheme.ordinal()] == 1) {
                if (Build.VERSION.SDK_INT >= 23 && this.mKeyGenParameterSpec != null) {
                    throw new IllegalArgumentException("KeyScheme set after setting a KeyGenParamSpec");
                }
                this.mKeyScheme = keyScheme;
                return this;
            }
            throw new IllegalArgumentException("Unsupported scheme: " + keyScheme);
        }

        public Builder setUserAuthenticationRequired(boolean z) {
            return setUserAuthenticationRequired(z, MasterKey.getDefaultAuthenticationValidityDurationSeconds());
        }

        public Builder setUserAuthenticationRequired(boolean z, int i) {
            this.mAuthenticationRequired = z;
            this.mUserAuthenticationValidityDurationSeconds = i;
            return this;
        }

        public Builder setRequestStrongBoxBacked(boolean z) {
            this.mRequestStrongBoxBacked = z;
            return this;
        }

        public Builder setKeyGenParameterSpec(KeyGenParameterSpec keyGenParameterSpec) {
            if (this.mKeyScheme != null) {
                throw new IllegalArgumentException("KeyGenParamSpec set after setting a KeyScheme");
            }
            if (!this.mKeyAlias.equals(Api23Impl.getKeystoreAlias(keyGenParameterSpec))) {
                throw new IllegalArgumentException("KeyGenParamSpec's key alias does not match provided alias (" + this.mKeyAlias + " vs " + Api23Impl.getKeystoreAlias(keyGenParameterSpec));
            }
            this.mKeyGenParameterSpec = keyGenParameterSpec;
            return this;
        }

        public MasterKey build() throws GeneralSecurityException, IOException {
            if (Build.VERSION.SDK_INT >= 23) {
                return Api23Impl.build(this);
            }
            return new MasterKey(this.mKeyAlias, null);
        }

        /* loaded from: classes2.dex */
        static class Api23Impl {
            private Api23Impl() {
            }

            static String getKeystoreAlias(KeyGenParameterSpec keyGenParameterSpec) {
                return keyGenParameterSpec.getKeystoreAlias();
            }

            static MasterKey build(Builder builder) throws GeneralSecurityException, IOException {
                if (builder.mKeyScheme == null && builder.mKeyGenParameterSpec == null) {
                    throw new IllegalArgumentException("build() called before setKeyGenParameterSpec or setKeyScheme.");
                }
                if (builder.mKeyScheme == KeyScheme.AES256_GCM) {
                    KeyGenParameterSpec.Builder keySize = new KeyGenParameterSpec.Builder(builder.mKeyAlias, 3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setKeySize(256);
                    if (builder.mAuthenticationRequired) {
                        keySize.setUserAuthenticationRequired(true);
                        if (Build.VERSION.SDK_INT >= 30) {
                            Api30Impl.setUserAuthenticationParameters(keySize, builder.mUserAuthenticationValidityDurationSeconds, 3);
                        } else {
                            keySize.setUserAuthenticationValidityDurationSeconds(builder.mUserAuthenticationValidityDurationSeconds);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 28 && builder.mRequestStrongBoxBacked && builder.mContext.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore")) {
                        Api28Impl.setIsStrongBoxBacked(keySize);
                    }
                    builder.mKeyGenParameterSpec = keySize.build();
                }
                Objects.requireNonNull(builder.mKeyGenParameterSpec, "KeyGenParameterSpec was null after build() check");
                return new MasterKey(MasterKeys.getOrCreate(builder.mKeyGenParameterSpec), builder.mKeyGenParameterSpec);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: classes2.dex */
            public static class Api28Impl {
                private Api28Impl() {
                }

                static void setIsStrongBoxBacked(KeyGenParameterSpec.Builder builder) {
                    builder.setIsStrongBoxBacked(true);
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: classes2.dex */
            public static class Api30Impl {
                private Api30Impl() {
                }

                static void setUserAuthenticationParameters(KeyGenParameterSpec.Builder builder, int i, int i2) {
                    builder.setUserAuthenticationParameters(i, i2);
                }
            }
        }
    }

    /* renamed from: androidx.security.crypto.MasterKey$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme;

        static {
            int[] iArr = new int[KeyScheme.values().length];
            $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme = iArr;
            try {
                iArr[KeyScheme.AES256_GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes2.dex */
    static class Api23Impl {
        private Api23Impl() {
        }

        static boolean isUserAuthenticationRequired(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isUserAuthenticationRequired();
        }

        static int getUserAuthenticationValidityDurationSeconds(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.getUserAuthenticationValidityDurationSeconds();
        }
    }

    /* loaded from: classes2.dex */
    static class Api28Impl {
        private Api28Impl() {
        }

        static boolean isStrongBoxBacked(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isStrongBoxBacked();
        }
    }
}
