package com.google.crypto.tink.aead.internal;

import com.google.common.base.Ascii;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    private static long load32(byte[] in, int idx) {
        return (((in[idx + 3] & 255) << 24) | (in[idx] & 255) | ((in[idx + 1] & 255) << 8) | ((in[idx + 2] & 255) << 16)) & 4294967295L;
    }

    private static long load26(byte[] in, int idx, int shift) {
        return (load32(in, idx) >> shift) & 67108863;
    }

    private static void toByteArray(byte[] output, long num, int idx) {
        int i = 0;
        while (i < 4) {
            output[idx + i] = (byte) (255 & num);
            i++;
            num >>= 8;
        }
    }

    private static void copyBlockSize(byte[] output, byte[] in, int idx) {
        int min = Math.min(16, in.length - idx);
        System.arraycopy(in, idx, output, 0, min);
        output[min] = 1;
        if (min != 16) {
            Arrays.fill(output, min + 1, output.length, (byte) 0);
        }
    }

    public static byte[] computeMac(final byte[] key, byte[] data) {
        if (key.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        int i = 0;
        long load26 = load26(key, 0, 0) & 67108863;
        int i2 = 3;
        long load262 = load26(key, 3, 2) & 67108611;
        long load263 = load26(key, 6, 4) & 67092735;
        long load264 = load26(key, 9, 6) & 66076671;
        long load265 = load26(key, 12, 8) & 1048575;
        long j = load262 * 5;
        long j2 = load263 * 5;
        long j3 = load264 * 5;
        long j4 = load265 * 5;
        byte[] bArr = new byte[17];
        long j5 = 0;
        int i3 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        while (i3 < data.length) {
            copyBlockSize(bArr, data, i3);
            long load266 = j9 + load26(bArr, i, i);
            long load267 = j5 + load26(bArr, i2, 2);
            long load268 = j6 + load26(bArr, 6, 4);
            long load269 = j7 + load26(bArr, 9, 6);
            long load2610 = j8 + (load26(bArr, 12, 8) | (bArr[16] << Ascii.CAN));
            long j10 = (load266 * load26) + (load267 * j4) + (load268 * j3) + (load269 * j2) + (load2610 * j);
            long j11 = (load266 * load262) + (load267 * load26) + (load268 * j4) + (load269 * j3) + (load2610 * j2);
            long j12 = (load266 * load263) + (load267 * load262) + (load268 * load26) + (load269 * j4) + (load2610 * j3);
            long j13 = (load266 * load264) + (load267 * load263) + (load268 * load262) + (load269 * load26) + (load2610 * j4);
            long j14 = j11 + (j10 >> 26);
            long j15 = j12 + (j14 >> 26);
            long j16 = j13 + (j15 >> 26);
            long j17 = (load266 * load265) + (load267 * load264) + (load268 * load263) + (load269 * load262) + (load2610 * load26) + (j16 >> 26);
            long j18 = (j10 & 67108863) + ((j17 >> 26) * 5);
            j5 = (j14 & 67108863) + (j18 >> 26);
            i3 += 16;
            j6 = j15 & 67108863;
            j7 = j16 & 67108863;
            j8 = j17 & 67108863;
            i2 = 3;
            j9 = j18 & 67108863;
            i = 0;
        }
        long j19 = j6 + (j5 >> 26);
        long j20 = j19 & 67108863;
        long j21 = j7 + (j19 >> 26);
        long j22 = j21 & 67108863;
        long j23 = j8 + (j21 >> 26);
        long j24 = j23 & 67108863;
        long j25 = j9 + ((j23 >> 26) * 5);
        long j26 = j25 & 67108863;
        long j27 = (j5 & 67108863) + (j25 >> 26);
        long j28 = j26 + 5;
        long j29 = j28 & 67108863;
        long j30 = (j28 >> 26) + j27;
        long j31 = j20 + (j30 >> 26);
        long j32 = j22 + (j31 >> 26);
        long j33 = (j24 + (j32 >> 26)) - 67108864;
        long j34 = j33 >> 63;
        long j35 = j26 & j34;
        long j36 = j27 & j34;
        long j37 = j20 & j34;
        long j38 = j22 & j34;
        long j39 = j24 & j34;
        long j40 = ~j34;
        long j41 = (j30 & 67108863 & j40) | j36;
        long j42 = (j31 & 67108863 & j40) | j37;
        long j43 = (j32 & 67108863 & j40) | j38;
        long j44 = (j33 & j40) | j39;
        long j45 = (j35 | (j29 & j40) | (j41 << 26)) & 4294967295L;
        long j46 = ((j41 >> 6) | (j42 << 20)) & 4294967295L;
        long j47 = ((j42 >> 12) | (j43 << 14)) & 4294967295L;
        long j48 = ((j43 >> 18) | (j44 << 8)) & 4294967295L;
        long load32 = j45 + load32(key, 16);
        long j49 = load32 & 4294967295L;
        long load322 = j46 + load32(key, 20) + (load32 >> 32);
        long j50 = load322 & 4294967295L;
        long load323 = j47 + load32(key, 24) + (load322 >> 32);
        long j51 = load323 & 4294967295L;
        long load324 = (j48 + load32(key, 28) + (load323 >> 32)) & 4294967295L;
        byte[] bArr2 = new byte[16];
        toByteArray(bArr2, j49, 0);
        toByteArray(bArr2, j50, 4);
        toByteArray(bArr2, j51, 8);
        toByteArray(bArr2, load324, 12);
        return bArr2;
    }

    public static void verifyMac(final byte[] key, byte[] data, byte[] mac) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(key, data), mac)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
