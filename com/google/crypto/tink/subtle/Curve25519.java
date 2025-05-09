package com.google.crypto.tink.subtle;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.otaliastudios.transcoder.internal.utils.AvcSpsUtils;
import java.security.InvalidKeyException;
import java.util.Arrays;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.bouncycastle.crypto.signers.PSSSigner;

/* loaded from: classes4.dex */
final class Curve25519 {
    static final byte[][] BANNED_PUBLIC_KEYS = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{ISO7816.INS_CREATE_FILE, -21, 122, 124, 59, 65, -72, -82, Ascii.SYN, 86, -29, -6, -15, -97, -60, 106, ISO7816.INS_PUT_DATA, 9, ISOFileInfo.ENV_TEMP_EF, -21, -100, ISO7816.INS_INCREASE, ISO7816.INS_READ_BINARY2, -3, -122, ISOFileInfo.FCP_BYTE, 5, Ascii.SYN, 95, 73, -72, 0}, new byte[]{95, -100, -107, PSSSigner.TRAILER_IMPLICIT, -93, 80, ISOFileInfo.SECURITY_ATTR_COMPACT, ISO7816.INS_CHANGE_CHV, ISO7816.INS_READ_BINARY2, ISO7816.INS_WRITE_BINARY, ISO7816.INS_READ_BINARY2, 85, -100, ISOFileInfo.FILE_IDENTIFIER, ByteSourceJsonBootstrapper.UTF8_BOM_1, 91, 4, ISO7816.INS_REHABILITATE_CHV, 92, -60, AvcSpsUtils.PROFILE_IDC_EXTENDED, 28, ISOFileInfo.CHANNEL_SECURITY, -122, ISO7816.INS_LOAD_KEY_FILE, ISO7816.INS_MSE, 78, -35, ISO7816.INS_WRITE_BINARY, -97, 17, 87}, new byte[]{-20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}};

    private static void monty(long[] x2, long[] z2, long[] x3, long[] z3, long[] x, long[] z, long[] xprime, long[] zprime, long[] qmqp) {
        long[] copyOf = Arrays.copyOf(x, 10);
        long[] jArr = new long[19];
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        long[] jArr4 = new long[19];
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        Field25519.sum(x, z);
        Field25519.sub(z, copyOf);
        long[] copyOf2 = Arrays.copyOf(xprime, 10);
        Field25519.sum(xprime, zprime);
        Field25519.sub(zprime, copyOf2);
        Field25519.product(jArr4, xprime, z);
        Field25519.product(jArr5, x, zprime);
        Field25519.reduceSizeByModularReduction(jArr4);
        Field25519.reduceCoefficients(jArr4);
        Field25519.reduceSizeByModularReduction(jArr5);
        Field25519.reduceCoefficients(jArr5);
        System.arraycopy(jArr4, 0, copyOf2, 0, 10);
        Field25519.sum(jArr4, jArr5);
        Field25519.sub(jArr5, copyOf2);
        Field25519.square(jArr7, jArr4);
        Field25519.square(jArr6, jArr5);
        Field25519.product(jArr5, jArr6, qmqp);
        Field25519.reduceSizeByModularReduction(jArr5);
        Field25519.reduceCoefficients(jArr5);
        System.arraycopy(jArr7, 0, x3, 0, 10);
        System.arraycopy(jArr5, 0, z3, 0, 10);
        Field25519.square(jArr2, x);
        Field25519.square(jArr3, z);
        Field25519.product(x2, jArr2, jArr3);
        Field25519.reduceSizeByModularReduction(x2);
        Field25519.reduceCoefficients(x2);
        Field25519.sub(jArr3, jArr2);
        Arrays.fill(jArr, 10, 18, 0L);
        Field25519.scalarProduct(jArr, jArr3, 121665L);
        Field25519.reduceCoefficients(jArr);
        Field25519.sum(jArr, jArr2);
        Field25519.product(z2, jArr3, jArr);
        Field25519.reduceSizeByModularReduction(z2);
        Field25519.reduceCoefficients(z2);
    }

    static void swapConditional(long[] a, long[] b, int iswap) {
        int i = -iswap;
        for (int i2 = 0; i2 < 10; i2++) {
            int i3 = (((int) a[i2]) ^ ((int) b[i2])) & i;
            a[i2] = ((int) a[i2]) ^ i3;
            b[i2] = i3 ^ ((int) b[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyConditional(long[] a, long[] b, int icopy) {
        int i = -icopy;
        for (int i2 = 0; i2 < 10; i2++) {
            a[i2] = ((((int) a[i2]) ^ ((int) b[i2])) & i) ^ ((int) a[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void curveMult(long[] resultx, byte[] n, byte[] qBytes) throws InvalidKeyException {
        long[] expand = Field25519.expand(validatePubKeyAndClearMsb(qBytes));
        long[] jArr = new long[19];
        long[] jArr2 = new long[19];
        int i = 0;
        jArr2[0] = 1;
        long[] jArr3 = new long[19];
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        jArr6[0] = 1;
        long[] jArr7 = new long[19];
        long[] jArr8 = new long[19];
        jArr8[0] = 1;
        int i2 = 10;
        System.arraycopy(expand, 0, jArr, 0, 10);
        int i3 = 0;
        while (i3 < 32) {
            int i4 = n[(32 - i3) - 1] & 255;
            long[] jArr9 = jArr5;
            long[] jArr10 = jArr6;
            long[] jArr11 = jArr;
            long[] jArr12 = jArr2;
            int i5 = i;
            long[] jArr13 = jArr7;
            long[] jArr14 = jArr8;
            long[] jArr15 = jArr4;
            long[] jArr16 = jArr3;
            long[] jArr17 = jArr15;
            while (i5 < 8) {
                int i6 = (i4 >> (7 - i5)) & 1;
                swapConditional(jArr16, jArr11, i6);
                swapConditional(jArr17, jArr12, i6);
                long[] jArr18 = jArr13;
                long[] jArr19 = jArr10;
                int i7 = i4;
                long[] jArr20 = jArr9;
                long[] jArr21 = jArr17;
                long[] jArr22 = jArr16;
                long[] jArr23 = jArr12;
                long[] jArr24 = jArr11;
                monty(jArr13, jArr14, jArr9, jArr10, jArr16, jArr17, jArr11, jArr12, expand);
                swapConditional(jArr18, jArr20, i6);
                swapConditional(jArr14, jArr19, i6);
                i5++;
                jArr12 = jArr19;
                jArr17 = jArr14;
                jArr16 = jArr18;
                jArr11 = jArr20;
                i4 = i7;
                jArr14 = jArr21;
                jArr13 = jArr22;
                jArr10 = jArr23;
                jArr9 = jArr24;
            }
            long[] jArr25 = jArr16;
            long[] jArr26 = jArr12;
            long[] jArr27 = jArr11;
            jArr6 = jArr10;
            i3++;
            jArr8 = jArr14;
            jArr7 = jArr13;
            jArr5 = jArr9;
            jArr4 = jArr17;
            jArr3 = jArr25;
            jArr2 = jArr26;
            jArr = jArr27;
            i = 0;
            i2 = 10;
        }
        long[] jArr28 = new long[i2];
        Field25519.inverse(jArr28, jArr4);
        Field25519.mult(resultx, jArr3, jArr28);
        if (isCollinear(expand, resultx, jArr, jArr2)) {
            return;
        }
        throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: " + Hex.encode(qBytes));
    }

    private static byte[] validatePubKeyAndClearMsb(byte[] pubKey) throws InvalidKeyException {
        if (pubKey.length != 32) {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        byte[] copyOf = Arrays.copyOf(pubKey, pubKey.length);
        copyOf[31] = (byte) (copyOf[31] & Byte.MAX_VALUE);
        int i = 0;
        while (true) {
            byte[][] bArr = BANNED_PUBLIC_KEYS;
            if (i >= bArr.length) {
                return copyOf;
            }
            if (Bytes.equal(bArr[i], copyOf)) {
                throw new InvalidKeyException("Banned public key: " + Hex.encode(bArr[i]));
            }
            i++;
        }
    }

    private static boolean isCollinear(long[] x1, long[] x2, long[] x3, long[] z3) {
        long[] jArr = new long[10];
        long[] jArr2 = new long[10];
        long[] jArr3 = new long[11];
        long[] jArr4 = new long[11];
        long[] jArr5 = new long[11];
        Field25519.mult(jArr, x1, x2);
        Field25519.sum(jArr2, x1, x2);
        long[] jArr6 = new long[10];
        jArr6[0] = 486662;
        Field25519.sum(jArr4, jArr2, jArr6);
        Field25519.mult(jArr4, jArr4, z3);
        Field25519.sum(jArr4, x3);
        Field25519.mult(jArr4, jArr4, jArr);
        Field25519.mult(jArr4, jArr4, x3);
        Field25519.scalarProduct(jArr3, jArr4, 4L);
        Field25519.reduceCoefficients(jArr3);
        Field25519.mult(jArr4, jArr, z3);
        Field25519.sub(jArr4, jArr4, z3);
        Field25519.mult(jArr5, jArr2, x3);
        Field25519.sum(jArr4, jArr4, jArr5);
        Field25519.square(jArr4, jArr4);
        return Bytes.equal(Field25519.contract(jArr3), Field25519.contract(jArr4));
    }

    private Curve25519() {
    }
}
