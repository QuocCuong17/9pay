package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.subtle.Bytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* loaded from: classes4.dex */
abstract class InsecureNonceChaCha20Base {
    private final int initialCounter;
    int[] key;

    abstract int[] createInitialState(final int[] nonce, int counter);

    abstract int nonceSizeInBytes();

    public InsecureNonceChaCha20Base(final byte[] key, int initialCounter) throws InvalidKeyException {
        if (key.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.key = ChaCha20Util.toIntArray(key);
        this.initialCounter = initialCounter;
    }

    public byte[] encrypt(final byte[] nonce, final byte[] plaintext) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(plaintext.length);
        encrypt(allocate, nonce, plaintext);
        return allocate.array();
    }

    public void encrypt(ByteBuffer output, final byte[] nonce, final byte[] plaintext) throws GeneralSecurityException {
        if (output.remaining() < plaintext.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        process(nonce, output, ByteBuffer.wrap(plaintext));
    }

    public byte[] decrypt(final byte[] nonce, final byte[] ciphertext) throws GeneralSecurityException {
        return decrypt(nonce, ByteBuffer.wrap(ciphertext));
    }

    public byte[] decrypt(final byte[] nonce, ByteBuffer ciphertext) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(ciphertext.remaining());
        process(nonce, allocate, ciphertext);
        return allocate.array();
    }

    private void process(final byte[] nonce, ByteBuffer output, ByteBuffer input) throws GeneralSecurityException {
        if (nonce.length != nonceSizeInBytes()) {
            throw new GeneralSecurityException("The nonce length (in bytes) must be " + nonceSizeInBytes());
        }
        int remaining = input.remaining();
        int i = (remaining / 64) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer chacha20Block = chacha20Block(nonce, this.initialCounter + i2);
            if (i2 == i - 1) {
                Bytes.xor(output, input, chacha20Block, remaining % 64);
            } else {
                Bytes.xor(output, input, chacha20Block, 64);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer chacha20Block(final byte[] nonce, int counter) {
        int[] createInitialState = createInitialState(ChaCha20Util.toIntArray(nonce), counter);
        int[] iArr = (int[]) createInitialState.clone();
        ChaCha20Util.shuffleState(iArr);
        for (int i = 0; i < createInitialState.length; i++) {
            createInitialState[i] = createInitialState[i] + iArr[i];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(createInitialState, 0, 16);
        return order;
    }
}
