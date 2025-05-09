package org.jmrtd.protocol;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECPoint;

/* loaded from: classes6.dex */
public class PACEGMWithECDHMappingResult extends PACEGMMappingResult {
    private static final long serialVersionUID = -3892431861957032423L;
    private BigInteger sharedSecretPointX;
    private BigInteger sharedSecretPointY;

    public PACEGMWithECDHMappingResult(AlgorithmParameterSpec algorithmParameterSpec, byte[] bArr, PublicKey publicKey, KeyPair keyPair, ECPoint eCPoint, AlgorithmParameterSpec algorithmParameterSpec2) {
        super(algorithmParameterSpec, bArr, publicKey, keyPair, algorithmParameterSpec2);
        this.sharedSecretPointX = eCPoint.getAffineX();
        this.sharedSecretPointY = eCPoint.getAffineY();
    }

    public ECPoint getSharedSecretPoint() {
        return new ECPoint(this.sharedSecretPointX, this.sharedSecretPointY);
    }

    @Override // org.jmrtd.protocol.PACEGMMappingResult, org.jmrtd.protocol.PACEMappingResult
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        BigInteger bigInteger = this.sharedSecretPointX;
        int hashCode2 = hashCode + (bigInteger == null ? 0 : bigInteger.hashCode());
        BigInteger bigInteger2 = this.sharedSecretPointY;
        return hashCode2 + (bigInteger2 != null ? bigInteger2.hashCode() : 0);
    }

    @Override // org.jmrtd.protocol.PACEGMMappingResult, org.jmrtd.protocol.PACEMappingResult
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        PACEGMWithECDHMappingResult pACEGMWithECDHMappingResult = (PACEGMWithECDHMappingResult) obj;
        BigInteger bigInteger = this.sharedSecretPointX;
        if (bigInteger == null) {
            if (pACEGMWithECDHMappingResult.sharedSecretPointX != null) {
                return false;
            }
        } else if (!bigInteger.equals(pACEGMWithECDHMappingResult.sharedSecretPointX)) {
            return false;
        }
        BigInteger bigInteger2 = this.sharedSecretPointY;
        if (bigInteger2 == null) {
            if (pACEGMWithECDHMappingResult.sharedSecretPointY != null) {
                return false;
            }
        } else if (!bigInteger2.equals(pACEGMWithECDHMappingResult.sharedSecretPointY)) {
            return false;
        }
        return true;
    }
}
