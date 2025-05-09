package org.bouncycastle.crypto;

import java.math.BigInteger;

/* loaded from: classes2.dex */
public interface DSA {
    BigInteger[] generateSignature(byte[] bArr);

    void init(boolean z, CipherParameters cipherParameters);

    boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2);
}
