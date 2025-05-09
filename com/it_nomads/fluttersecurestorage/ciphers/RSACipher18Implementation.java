package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes4.dex */
class RSACipher18Implementation implements KeyCipher {
    private static final String KEYSTORE_PROVIDER_ANDROID = "AndroidKeyStore";
    private static final String TYPE_RSA = "RSA";
    protected final Context context;
    protected final String keyAlias = createKeyAlias();

    protected AlgorithmParameterSpec getAlgorithmParameterSpec() {
        return null;
    }

    public RSACipher18Implementation(Context context) throws Exception {
        this.context = context;
        createRSAKeysIfNeeded(context);
    }

    protected String createKeyAlias() {
        return this.context.getPackageName() + ".FlutterSecureStoragePluginKey";
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.KeyCipher
    public byte[] wrap(Key key) throws Exception {
        PublicKey publicKey = getPublicKey();
        Cipher rSACipher = getRSACipher();
        rSACipher.init(3, publicKey, getAlgorithmParameterSpec());
        return rSACipher.wrap(key);
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.KeyCipher
    public Key unwrap(byte[] bArr, String str) throws Exception {
        PrivateKey privateKey = getPrivateKey();
        Cipher rSACipher = getRSACipher();
        rSACipher.init(4, privateKey, getAlgorithmParameterSpec());
        return rSACipher.unwrap(bArr, str, 3);
    }

    private PrivateKey getPrivateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID);
        keyStore.load(null);
        Key key = keyStore.getKey(this.keyAlias, null);
        if (key == null) {
            throw new Exception("No key found under alias: " + this.keyAlias);
        }
        if (!(key instanceof PrivateKey)) {
            throw new Exception("Not an instance of a PrivateKey");
        }
        return (PrivateKey) key;
    }

    private PublicKey getPublicKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID);
        keyStore.load(null);
        Certificate certificate = keyStore.getCertificate(this.keyAlias);
        if (certificate == null) {
            throw new Exception("No certificate found under alias: " + this.keyAlias);
        }
        PublicKey publicKey = certificate.getPublicKey();
        if (publicKey != null) {
            return publicKey;
        }
        throw new Exception("No key found under alias: " + this.keyAlias);
    }

    protected Cipher getRSACipher() throws Exception {
        if (Build.VERSION.SDK_INT < 23) {
            return Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
        }
        return Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidKeyStoreBCWorkaround");
    }

    private void createRSAKeysIfNeeded(Context context) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER_ANDROID);
        keyStore.load(null);
        if (keyStore.getKey(this.keyAlias, null) == null) {
            createKeys(context);
        }
    }

    private void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = this.context.getResources().getConfiguration();
        configuration.setLocale(locale);
        this.context.createConfigurationContext(configuration);
    }

    private void createKeys(Context context) throws Exception {
        AlgorithmParameterSpec makeAlgorithmParameterSpec;
        Locale locale = Locale.getDefault();
        try {
            setLocale(Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 25);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(TYPE_RSA, KEYSTORE_PROVIDER_ANDROID);
            if (Build.VERSION.SDK_INT < 23) {
                makeAlgorithmParameterSpec = makeAlgorithmParameterSpecLegacy(context, calendar, calendar2);
            } else {
                makeAlgorithmParameterSpec = makeAlgorithmParameterSpec(context, calendar, calendar2);
            }
            keyPairGenerator.initialize(makeAlgorithmParameterSpec);
            keyPairGenerator.generateKeyPair();
        } finally {
            setLocale(locale);
        }
    }

    private AlgorithmParameterSpec makeAlgorithmParameterSpecLegacy(Context context, Calendar calendar, Calendar calendar2) {
        return new KeyPairGeneratorSpec.Builder(context).setAlias(this.keyAlias).setSubject(new X500Principal("CN=" + this.keyAlias)).setSerialNumber(BigInteger.valueOf(1L)).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
    }

    protected AlgorithmParameterSpec makeAlgorithmParameterSpec(Context context, Calendar calendar, Calendar calendar2) {
        return new KeyGenParameterSpec.Builder(this.keyAlias, 3).setCertificateSubject(new X500Principal("CN=" + this.keyAlias)).setDigests("SHA-256").setBlockModes("ECB").setEncryptionPaddings("PKCS1Padding").setCertificateSerialNumber(BigInteger.valueOf(1L)).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
    }
}
