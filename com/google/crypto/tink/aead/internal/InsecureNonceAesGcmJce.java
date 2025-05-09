package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.SubtleUtil;
import com.google.crypto.tink.subtle.Validators;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public final class InsecureNonceAesGcmJce {
    public static final int IV_SIZE_IN_BYTES = 12;
    public static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey keySpec;
    private final boolean prependIv;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    };

    public InsecureNonceAesGcmJce(final byte[] key, boolean prependIv) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, "AES");
        this.prependIv = prependIv;
    }

    public byte[] encrypt(final byte[] iv, final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        int length;
        if (iv.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        if (plaintext.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        boolean z = this.prependIv;
        if (z) {
            length = plaintext.length + 12;
        } else {
            length = plaintext.length;
        }
        byte[] bArr = new byte[length + 16];
        if (z) {
            System.arraycopy(iv, 0, bArr, 0, 12);
        }
        AlgorithmParameterSpec params = getParams(iv);
        ThreadLocal<Cipher> threadLocal = localCipher;
        threadLocal.get().init(1, this.keySpec, params);
        if (associatedData != null && associatedData.length != 0) {
            threadLocal.get().updateAAD(associatedData);
        }
        int doFinal = threadLocal.get().doFinal(plaintext, 0, plaintext.length, bArr, this.prependIv ? 12 : 0);
        if (doFinal == plaintext.length + 16) {
            return bArr;
        }
        throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(doFinal - plaintext.length)));
    }

    public byte[] decrypt(final byte[] iv, final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (iv.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        boolean z = this.prependIv;
        if (ciphertext.length < (z ? 28 : 16)) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (z && !ByteBuffer.wrap(iv).equals(ByteBuffer.wrap(ciphertext, 0, 12))) {
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        AlgorithmParameterSpec params = getParams(iv);
        ThreadLocal<Cipher> threadLocal = localCipher;
        threadLocal.get().init(2, this.keySpec, params);
        if (associatedData != null && associatedData.length != 0) {
            threadLocal.get().updateAAD(associatedData);
        }
        boolean z2 = this.prependIv;
        return threadLocal.get().doFinal(ciphertext, z2 ? 12 : 0, z2 ? ciphertext.length - 12 : ciphertext.length);
    }

    private static AlgorithmParameterSpec getParams(final byte[] iv) throws GeneralSecurityException {
        return getParams(iv, 0, iv.length);
    }

    private static AlgorithmParameterSpec getParams(final byte[] buf, int offset, int len) throws GeneralSecurityException {
        if (SubtleUtil.isAndroid() && SubtleUtil.androidApiLevel() <= 19) {
            return new IvParameterSpec(buf, offset, len);
        }
        return new GCMParameterSpec(128, buf, offset, len);
    }
}
