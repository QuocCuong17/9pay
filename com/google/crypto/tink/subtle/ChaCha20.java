package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* loaded from: classes4.dex */
class ChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 12;
    private final InsecureNonceChaCha20 cipher;

    ChaCha20(final byte[] key, int initialCounter) throws InvalidKeyException {
        this.cipher = new InsecureNonceChaCha20(key, initialCounter);
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(plaintext.length + 12);
        byte[] randBytes = Random.randBytes(12);
        allocate.put(randBytes);
        this.cipher.encrypt(allocate, randBytes, plaintext);
        return allocate.array();
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException {
        if (ciphertext.length < 12) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        return this.cipher.decrypt(Arrays.copyOf(ciphertext, 12), ByteBuffer.wrap(ciphertext, 12, ciphertext.length - 12));
    }
}
