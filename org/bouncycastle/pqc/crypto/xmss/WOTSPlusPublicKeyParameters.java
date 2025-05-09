package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class WOTSPlusPublicKeyParameters {
    private final byte[][] publicKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusPublicKeyParameters(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        Objects.requireNonNull(wOTSPlusParameters, "params == null");
        Objects.requireNonNull(bArr, "publicKey == null");
        if (XMSSUtil.hasNullPointer(bArr)) {
            throw new NullPointerException("publicKey byte array == null");
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            throw new IllegalArgumentException("wrong publicKey size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getTreeDigestSize()) {
                throw new IllegalArgumentException("wrong publicKey format");
            }
        }
        this.publicKey = XMSSUtil.cloneArray(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.publicKey);
    }
}
