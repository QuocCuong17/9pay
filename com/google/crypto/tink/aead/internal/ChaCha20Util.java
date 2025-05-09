package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;

/* loaded from: classes4.dex */
final class ChaCha20Util {
    static final int BLOCK_SIZE_IN_BYTES = 64;
    static final int BLOCK_SIZE_IN_INTS = 16;
    static final int KEY_SIZE_IN_BYTES = 32;
    static final int KEY_SIZE_IN_INTS = 8;
    private static final int[] SIGMA = toIntArray(new byte[]{101, 120, ISO7816.INS_MANAGE_CHANNEL, 97, 110, 100, 32, 51, ISO7816.INS_INCREASE, 45, ISOFileInfo.FCP_BYTE, 121, 116, 101, 32, 107});

    private static int rotateLeft(int x, int y) {
        return (x >>> (-y)) | (x << y);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSigmaAndKey(int[] state, final int[] key) {
        int[] iArr = SIGMA;
        System.arraycopy(iArr, 0, state, 0, iArr.length);
        System.arraycopy(key, 0, state, iArr.length, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void shuffleState(final int[] state) {
        for (int i = 0; i < 10; i++) {
            quarterRound(state, 0, 4, 8, 12);
            quarterRound(state, 1, 5, 9, 13);
            quarterRound(state, 2, 6, 10, 14);
            quarterRound(state, 3, 7, 11, 15);
            quarterRound(state, 0, 5, 10, 15);
            quarterRound(state, 1, 6, 11, 12);
            quarterRound(state, 2, 7, 8, 13);
            quarterRound(state, 3, 4, 9, 14);
        }
    }

    static void quarterRound(int[] x, int a, int b, int c, int d) {
        x[a] = x[a] + x[b];
        x[d] = rotateLeft(x[d] ^ x[a], 16);
        x[c] = x[c] + x[d];
        x[b] = rotateLeft(x[b] ^ x[c], 12);
        x[a] = x[a] + x[b];
        x[d] = rotateLeft(x[a] ^ x[d], 8);
        x[c] = x[c] + x[d];
        x[b] = rotateLeft(x[b] ^ x[c], 7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] toIntArray(final byte[] input) {
        IntBuffer asIntBuffer = ByteBuffer.wrap(input).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        asIntBuffer.get(iArr);
        return iArr;
    }

    private ChaCha20Util() {
    }
}
