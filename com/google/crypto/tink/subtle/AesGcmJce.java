package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class AesGcmJce implements Aead {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final InsecureNonceAesGcmJce insecureNonceAesGcmJce;

    public AesGcmJce(final byte[] key) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.insecureNonceAesGcmJce = new InsecureNonceAesGcmJce(key, true);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        return this.insecureNonceAesGcmJce.encrypt(Random.randBytes(12), plaintext, associatedData);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        return this.insecureNonceAesGcmJce.decrypt(Arrays.copyOf(ciphertext, 12), ciphertext, associatedData);
    }
}
