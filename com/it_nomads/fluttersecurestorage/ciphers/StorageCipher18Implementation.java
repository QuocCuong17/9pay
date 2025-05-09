package com.it_nomads.fluttersecurestorage.ciphers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public class StorageCipher18Implementation implements StorageCipher {
    private static final String KEY_ALGORITHM = "AES";
    private static final String SHARED_PREFERENCES_NAME = "FlutterSecureKeyStorage";
    private static final int keySize = 16;
    private final Cipher cipher;
    private Key secretKey;
    private final SecureRandom secureRandom = new SecureRandom();

    protected String getAESPreferencesKey() {
        return "VGhpcyBpcyB0aGUga2V5IGZvciBhIHNlY3VyZSBzdG9yYWdlIEFFUyBLZXkK";
    }

    protected int getIvSize() {
        return 16;
    }

    public StorageCipher18Implementation(Context context, KeyCipher keyCipher) throws Exception {
        String aESPreferencesKey = getAESPreferencesKey();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String string = sharedPreferences.getString(aESPreferencesKey, null);
        this.cipher = getCipher();
        if (string != null) {
            try {
                this.secretKey = keyCipher.unwrap(Base64.decode(string, 0), KEY_ALGORITHM);
                return;
            } catch (Exception e) {
                Log.e("StorageCipher18Impl", "unwrap key failed", e);
            }
        }
        byte[] bArr = new byte[16];
        this.secureRandom.nextBytes(bArr);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, KEY_ALGORITHM);
        this.secretKey = secretKeySpec;
        edit.putString(aESPreferencesKey, Base64.encodeToString(keyCipher.wrap(secretKeySpec), 0));
        edit.apply();
    }

    protected Cipher getCipher() throws Exception {
        return Cipher.getInstance("AES/CBC/PKCS7Padding");
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipher
    public byte[] encrypt(byte[] bArr) throws Exception {
        int ivSize = getIvSize();
        byte[] bArr2 = new byte[ivSize];
        this.secureRandom.nextBytes(bArr2);
        this.cipher.init(1, this.secretKey, getParameterSpec(bArr2));
        byte[] doFinal = this.cipher.doFinal(bArr);
        byte[] bArr3 = new byte[doFinal.length + ivSize];
        System.arraycopy(bArr2, 0, bArr3, 0, ivSize);
        System.arraycopy(doFinal, 0, bArr3, ivSize, doFinal.length);
        return bArr3;
    }

    @Override // com.it_nomads.fluttersecurestorage.ciphers.StorageCipher
    public byte[] decrypt(byte[] bArr) throws Exception {
        int ivSize = getIvSize();
        byte[] bArr2 = new byte[ivSize];
        System.arraycopy(bArr, 0, bArr2, 0, ivSize);
        AlgorithmParameterSpec parameterSpec = getParameterSpec(bArr2);
        int length = bArr.length - getIvSize();
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, ivSize, bArr3, 0, length);
        this.cipher.init(2, this.secretKey, parameterSpec);
        return this.cipher.doFinal(bArr3);
    }

    protected AlgorithmParameterSpec getParameterSpec(byte[] bArr) {
        return new IvParameterSpec(bArr);
    }
}
