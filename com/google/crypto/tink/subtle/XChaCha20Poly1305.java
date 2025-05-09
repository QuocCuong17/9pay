package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20Poly1305;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class XChaCha20Poly1305 implements Aead {
    private final InsecureNonceXChaCha20Poly1305 cipher;

    public XChaCha20Poly1305(final byte[] key) throws GeneralSecurityException {
        this.cipher = new InsecureNonceXChaCha20Poly1305(key);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(plaintext.length + 24 + 16);
        byte[] randBytes = Random.randBytes(24);
        allocate.put(randBytes);
        this.cipher.encrypt(allocate, randBytes, plaintext, associatedData);
        return allocate.array();
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.length < 40) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] copyOf = Arrays.copyOf(ciphertext, 24);
        return this.cipher.decrypt(ByteBuffer.wrap(ciphertext, 24, ciphertext.length - 24), copyOf, associatedData);
    }
}
