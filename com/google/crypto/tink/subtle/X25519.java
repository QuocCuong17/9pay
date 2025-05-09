package com.google.crypto.tink.subtle;

import com.google.common.primitives.SignedBytes;
import java.security.InvalidKeyException;
import java.util.Arrays;
import okio.Utf8;

/* loaded from: classes4.dex */
public final class X25519 {
    public static byte[] generatePrivateKey() {
        byte[] randBytes = Random.randBytes(32);
        randBytes[0] = (byte) (randBytes[0] | 7);
        randBytes[31] = (byte) (randBytes[31] & Utf8.REPLACEMENT_BYTE);
        randBytes[31] = (byte) (randBytes[31] | 128);
        return randBytes;
    }

    public static byte[] computeSharedSecret(byte[] privateKey, byte[] peersPublicValue) throws InvalidKeyException {
        if (privateKey.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] jArr = new long[11];
        byte[] copyOf = Arrays.copyOf(privateKey, 32);
        copyOf[0] = (byte) (copyOf[0] & 248);
        copyOf[31] = (byte) (copyOf[31] & Byte.MAX_VALUE);
        copyOf[31] = (byte) (copyOf[31] | SignedBytes.MAX_POWER_OF_TWO);
        Curve25519.curveMult(jArr, copyOf, peersPublicValue);
        return Field25519.contract(jArr);
    }

    public static byte[] publicFromPrivate(byte[] privateKey) throws InvalidKeyException {
        if (privateKey.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] bArr = new byte[32];
        bArr[0] = 9;
        return computeSharedSecret(privateKey, bArr);
    }

    private X25519() {
    }
}
