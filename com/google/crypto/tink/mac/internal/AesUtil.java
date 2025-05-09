package com.google.crypto.tink.mac.internal;

import java.util.Arrays;

/* loaded from: classes4.dex */
public final class AesUtil {
    public static final int BLOCK_SIZE = 16;

    public static byte[] dbl(final byte[] value) {
        if (value.length != 16) {
            throw new IllegalArgumentException("value must be a block.");
        }
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((value[i] << 1) & 254);
            if (i < 15) {
                bArr[i] = (byte) (bArr[i] | ((byte) ((value[i + 1] >> 7) & 1)));
            }
        }
        bArr[15] = (byte) (((byte) ((value[0] >> 7) & 135)) ^ bArr[15]);
        return bArr;
    }

    public static byte[] cmacPad(final byte[] x) {
        if (x.length >= 16) {
            throw new IllegalArgumentException("x must be smaller than a block.");
        }
        byte[] copyOf = Arrays.copyOf(x, 16);
        copyOf[x.length] = Byte.MIN_VALUE;
        return copyOf;
    }

    private AesUtil() {
    }
}
