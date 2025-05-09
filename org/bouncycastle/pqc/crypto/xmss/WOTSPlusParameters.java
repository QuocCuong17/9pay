package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class WOTSPlusParameters {
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final ASN1ObjectIdentifier treeDigest;
    private final int winternitzParameter;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Objects.requireNonNull(aSN1ObjectIdentifier, "treeDigest == null");
        this.treeDigest = aSN1ObjectIdentifier;
        Digest digest = DigestUtil.getDigest(aSN1ObjectIdentifier);
        int digestSize = XMSSUtil.getDigestSize(digest);
        this.digestSize = digestSize;
        this.winternitzParameter = 16;
        int ceil = (int) Math.ceil((digestSize * 8) / XMSSUtil.log2(16));
        this.len1 = ceil;
        int floor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * ceil) / XMSSUtil.log2(16))) + 1;
        this.len2 = floor;
        int i = ceil + floor;
        this.len = i;
        WOTSPlusOid lookup = WOTSPlusOid.lookup(digest.getAlgorithmName(), digestSize, 16, i);
        this.oid = lookup;
        if (lookup != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + digest.getAlgorithmName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen() {
        return this.len;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen1() {
        return this.len1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLen2() {
        return this.len2;
    }

    protected XMSSOid getOid() {
        return this.oid;
    }

    public ASN1ObjectIdentifier getTreeDigest() {
        return this.treeDigest;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getTreeDigestSize() {
        return this.digestSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
