package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class WOTSPlusSignature {
    private byte[][] signature;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusSignature(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        Objects.requireNonNull(wOTSPlusParameters, "params == null");
        Objects.requireNonNull(bArr, "signature == null");
        if (XMSSUtil.hasNullPointer(bArr)) {
            throw new NullPointerException("signature byte array == null");
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            throw new IllegalArgumentException("wrong signature size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getTreeDigestSize()) {
                throw new IllegalArgumentException("wrong signature format");
            }
        }
        this.signature = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.signature);
    }
}
