package org.jmrtd.protocol;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;
import net.sf.scuba.util.Hex;
import org.jmrtd.AccessKeySpec;
import org.jmrtd.lds.PACEInfo;

/* loaded from: classes6.dex */
public class PACECAMResult extends PACEResult {
    private static final long serialVersionUID = -4288710920347109329L;
    private byte[] chipAuthenticationData;
    private byte[] encryptedChipAuthenticationData;

    public PACECAMResult(AccessKeySpec accessKeySpec, String str, String str2, String str3, int i, PACEMappingResult pACEMappingResult, KeyPair keyPair, PublicKey publicKey, byte[] bArr, byte[] bArr2, SecureMessagingWrapper secureMessagingWrapper) {
        super(accessKeySpec, PACEInfo.MappingType.CAM, str, str2, str3, i, pACEMappingResult, keyPair, publicKey, secureMessagingWrapper);
        this.encryptedChipAuthenticationData = null;
        if (bArr != null) {
            byte[] bArr3 = new byte[bArr.length];
            this.encryptedChipAuthenticationData = bArr3;
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        }
        this.chipAuthenticationData = null;
        if (bArr2 != null) {
            byte[] bArr4 = new byte[bArr2.length];
            this.chipAuthenticationData = bArr4;
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        }
    }

    public byte[] getEncryptedChipAuthenticationData() {
        byte[] bArr = this.encryptedChipAuthenticationData;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public byte[] getChipAuthenticationData() {
        byte[] bArr = this.chipAuthenticationData;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    @Override // org.jmrtd.protocol.PACEResult
    public int hashCode() {
        return (((super.hashCode() * 31) + Arrays.hashCode(this.chipAuthenticationData)) * 31) + Arrays.hashCode(this.encryptedChipAuthenticationData);
    }

    @Override // org.jmrtd.protocol.PACEResult
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        PACECAMResult pACECAMResult = (PACECAMResult) obj;
        return Arrays.equals(this.chipAuthenticationData, pACECAMResult.chipAuthenticationData) && Arrays.equals(this.encryptedChipAuthenticationData, pACECAMResult.encryptedChipAuthenticationData);
    }

    @Override // org.jmrtd.protocol.PACEResult
    public String toString() {
        return "PACECAMResult [paceKey: " + getPACEKey() + ", mappingType: " + getMappingType() + ", agreementAlg: " + getAgreementAlg() + ", cipherAlg: " + getCipherAlg() + ", digestAlg: " + getDigestAlg() + ", keyLength: " + getKeyLength() + ", mappingResult: " + getMappingResult() + ", pcdKeyPair: " + getPCDKeyPair() + ", piccPublicKey: " + getPICCPublicKey() + ", encryptedChipAuthenticationData: " + Hex.bytesToHexString(this.encryptedChipAuthenticationData) + ", wrapper: " + getWrapper() + ", chipAuthenticationData: " + Hex.bytesToHexString(this.chipAuthenticationData) + "]";
    }
}
