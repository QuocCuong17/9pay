package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;

/* loaded from: classes6.dex */
public final class XMSSParameters {
    private final int height;
    private final int k;
    private final XMSSOid oid;
    private final String treeDigest;
    private final ASN1ObjectIdentifier treeDigestOID;
    private final int treeDigestSize;
    private final int winternitzParameter;
    private final WOTSPlusParameters wotsPlusParams;

    public XMSSParameters(int i, Digest digest) {
        if (i < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        Objects.requireNonNull(digest, "digest == null");
        this.height = i;
        this.k = determineMinK();
        String algorithmName = digest.getAlgorithmName();
        this.treeDigest = algorithmName;
        ASN1ObjectIdentifier digestOID = DigestUtil.getDigestOID(digest.getAlgorithmName());
        this.treeDigestOID = digestOID;
        WOTSPlusParameters wOTSPlusParameters = new WOTSPlusParameters(digestOID);
        this.wotsPlusParams = wOTSPlusParameters;
        int treeDigestSize = wOTSPlusParameters.getTreeDigestSize();
        this.treeDigestSize = treeDigestSize;
        int winternitzParameter = wOTSPlusParameters.getWinternitzParameter();
        this.winternitzParameter = winternitzParameter;
        this.oid = DefaultXMSSOid.lookup(algorithmName, treeDigestSize, winternitzParameter, wOTSPlusParameters.getLen(), i);
    }

    private int determineMinK() {
        int i = 2;
        while (true) {
            int i2 = this.height;
            if (i > i2) {
                throw new IllegalStateException("should never happen...");
            }
            if ((i2 - i) % 2 == 0) {
                return i;
            }
            i++;
        }
    }

    public int getHeight() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getK() {
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLen() {
        return this.wotsPlusParams.getLen();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XMSSOid getOid() {
        return this.oid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTreeDigest() {
        return this.treeDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigestOID;
    }

    public int getTreeDigestSize() {
        return this.treeDigestSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlus getWOTSPlus() {
        return new WOTSPlus(this.wotsPlusParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
