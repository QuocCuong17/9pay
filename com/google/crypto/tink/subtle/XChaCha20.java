package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* loaded from: classes4.dex */
class XChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 24;
    private final InsecureNonceXChaCha20 cipher;

    XChaCha20(byte[] key, int initialCounter) throws InvalidKeyException {
        this.cipher = new InsecureNonceXChaCha20(key, initialCounter);
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(plaintext.length + 24);
        byte[] randBytes = Random.randBytes(24);
        allocate.put(randBytes);
        this.cipher.encrypt(allocate, randBytes, plaintext);
        return allocate.array();
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException {
        if (ciphertext.length < 24) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        return this.cipher.decrypt(Arrays.copyOf(ciphertext, 24), ByteBuffer.wrap(ciphertext, 24, ciphertext.length - 24));
    }
}
