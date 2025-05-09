package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
/* loaded from: classes4.dex */
final class ChaCha20Poly1305HpkeAead implements HpkeAead {
    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getKeyLength() {
        return 32;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getNonceLength() {
        return 12;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] seal(byte[] key, byte[] nonce, byte[] plaintext, byte[] associatedData) throws GeneralSecurityException {
        if (key.length != getKeyLength()) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + getKeyLength());
        }
        return new InsecureNonceChaCha20Poly1305(key).encrypt(nonce, plaintext, associatedData);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] open(byte[] key, byte[] nonce, byte[] ciphertext, byte[] associatedData) throws GeneralSecurityException {
        if (key.length != getKeyLength()) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + getKeyLength());
        }
        return new InsecureNonceChaCha20Poly1305(key).decrypt(nonce, ciphertext, associatedData);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] getAeadId() {
        return HpkeUtil.CHACHA20_POLY1305_AEAD_ID;
    }
}
