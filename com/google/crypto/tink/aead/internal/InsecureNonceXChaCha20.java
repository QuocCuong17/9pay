package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class InsecureNonceXChaCha20 extends InsecureNonceChaCha20Base {
    public static final int NONCE_SIZE_IN_BYTES = 24;

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    int nonceSizeInBytes() {
        return 24;
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] nonce, ByteBuffer ciphertext) throws GeneralSecurityException {
        return super.decrypt(nonce, ciphertext);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] nonce, byte[] ciphertext) throws GeneralSecurityException {
        return super.decrypt(nonce, ciphertext);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ void encrypt(ByteBuffer output, byte[] nonce, byte[] plaintext) throws GeneralSecurityException {
        super.encrypt(output, nonce, plaintext);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    public /* bridge */ /* synthetic */ byte[] encrypt(byte[] nonce, byte[] plaintext) throws GeneralSecurityException {
        return super.encrypt(nonce, plaintext);
    }

    public InsecureNonceXChaCha20(byte[] key, int initialCounter) throws InvalidKeyException {
        super(key, initialCounter);
    }

    @Override // com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Base
    int[] createInitialState(final int[] nonce, int counter) {
        if (nonce.length != nonceSizeInBytes() / 4) {
            throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(nonce.length * 32)));
        }
        int[] iArr = new int[16];
        ChaCha20Util.setSigmaAndKey(iArr, hChaCha20(this.key, nonce));
        iArr[12] = counter;
        iArr[13] = 0;
        iArr[14] = nonce[4];
        iArr[15] = nonce[5];
        return iArr;
    }

    static int[] hChaCha20(final int[] key, final int[] nonce) {
        ChaCha20Util.setSigmaAndKey(r0, key);
        int[] iArr = {0, 0, 0, 0, iArr[12], iArr[13], iArr[14], iArr[15], 0, 0, 0, 0, nonce[0], nonce[1], nonce[2], nonce[3]};
        ChaCha20Util.shuffleState(iArr);
        return Arrays.copyOf(iArr, 8);
    }
}
