package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public class AeadOrDaead {
    private final Aead aead;
    private final DeterministicAead deterministicAead;

    public AeadOrDaead(Aead aead) {
        this.aead = aead;
        this.deterministicAead = null;
    }

    public AeadOrDaead(DeterministicAead deterministicAead) {
        this.aead = null;
        this.deterministicAead = deterministicAead;
    }

    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        Aead aead = this.aead;
        if (aead != null) {
            return aead.encrypt(plaintext, associatedData);
        }
        return this.deterministicAead.encryptDeterministically(plaintext, associatedData);
    }

    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        Aead aead = this.aead;
        if (aead != null) {
            return aead.decrypt(ciphertext, associatedData);
        }
        return this.deterministicAead.decryptDeterministically(ciphertext, associatedData);
    }
}
