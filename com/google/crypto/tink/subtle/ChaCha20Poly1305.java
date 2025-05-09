package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class ChaCha20Poly1305 implements Aead {
    private final InsecureNonceChaCha20Poly1305 cipher;

    public ChaCha20Poly1305(final byte[] key) throws GeneralSecurityException {
        this.cipher = new InsecureNonceChaCha20Poly1305(key);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(plaintext.length + 12 + 16);
        byte[] randBytes = Random.randBytes(12);
        allocate.put(randBytes);
        this.cipher.encrypt(allocate, randBytes, plaintext, associatedData);
        return allocate.array();
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] copyOf = Arrays.copyOf(ciphertext, 12);
        return this.cipher.decrypt(ByteBuffer.wrap(ciphertext, 12, ciphertext.length - 12), copyOf, associatedData);
    }
}
