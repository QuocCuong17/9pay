package org.jmrtd.protocol;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.PublicKey;
import org.jmrtd.AccessKeySpec;
import org.jmrtd.Util;
import org.jmrtd.lds.PACEInfo;

/* loaded from: classes6.dex */
public class PACEResult implements Serializable {
    private static final long serialVersionUID = -6819675856205885052L;
    private String agreementAlg;
    private String cipherAlg;
    private String digestAlg;
    private int keyLength;
    private PACEMappingResult mappingResult;
    private PACEInfo.MappingType mappingType;
    private AccessKeySpec paceKey;
    private KeyPair pcdKeyPair;
    private PublicKey piccPublicKey;
    private SecureMessagingWrapper wrapper;

    public PACEResult(AccessKeySpec accessKeySpec, PACEInfo.MappingType mappingType, String str, String str2, String str3, int i, PACEMappingResult pACEMappingResult, KeyPair keyPair, PublicKey publicKey, SecureMessagingWrapper secureMessagingWrapper) {
        this.paceKey = accessKeySpec;
        this.mappingType = mappingType;
        this.agreementAlg = str;
        this.cipherAlg = str2;
        this.digestAlg = str3;
        this.keyLength = i;
        this.mappingResult = pACEMappingResult;
        this.pcdKeyPair = keyPair;
        this.piccPublicKey = publicKey;
        this.wrapper = secureMessagingWrapper;
    }

    public AccessKeySpec getPACEKey() {
        return this.paceKey;
    }

    public PACEMappingResult getMappingResult() {
        return this.mappingResult;
    }

    public SecureMessagingWrapper getWrapper() {
        return this.wrapper;
    }

    public PACEInfo.MappingType getMappingType() {
        return this.mappingType;
    }

    public String getAgreementAlg() {
        return this.agreementAlg;
    }

    public String getCipherAlg() {
        return this.cipherAlg;
    }

    public String getDigestAlg() {
        return this.digestAlg;
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    public KeyPair getPCDKeyPair() {
        return this.pcdKeyPair;
    }

    public PublicKey getPICCPublicKey() {
        return this.piccPublicKey;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PACEResult [");
        sb.append("paceKey: ");
        sb.append(this.paceKey);
        sb.append(", mappingType: ");
        sb.append(this.mappingType);
        sb.append(", agreementAlg: " + this.agreementAlg);
        sb.append(", cipherAlg: " + this.cipherAlg);
        sb.append(", digestAlg: " + this.digestAlg);
        sb.append(", keyLength: " + this.keyLength);
        sb.append(", mappingResult: " + this.mappingResult);
        sb.append(", piccPublicKey: " + Util.getDetailedPublicKeyAlgorithm(this.piccPublicKey));
        sb.append(", pcdPrivateKey: " + Util.getDetailedPrivateKeyAlgorithm(this.pcdKeyPair.getPrivate()));
        sb.append(", pcdPublicKey: " + Util.getDetailedPublicKeyAlgorithm(this.pcdKeyPair.getPublic()));
        return sb.toString();
    }

    public int hashCode() {
        AccessKeySpec accessKeySpec = this.paceKey;
        int hashCode = (21901 + (accessKeySpec == null ? 0 : accessKeySpec.hashCode())) * 1991;
        String str = this.agreementAlg;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 1991;
        String str2 = this.cipherAlg;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 1991;
        String str3 = this.digestAlg;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 1991;
        PACEMappingResult pACEMappingResult = this.mappingResult;
        int hashCode5 = (((hashCode4 + (pACEMappingResult == null ? 0 : pACEMappingResult.hashCode())) * 1991) + this.keyLength) * 1991;
        PACEInfo.MappingType mappingType = this.mappingType;
        int hashCode6 = (hashCode5 + (mappingType == null ? 0 : mappingType.hashCode())) * 1991;
        KeyPair keyPair = this.pcdKeyPair;
        int hashCode7 = (hashCode6 + (keyPair == null ? 0 : keyPair.hashCode())) * 1991;
        PublicKey publicKey = this.piccPublicKey;
        int hashCode8 = (hashCode7 + (publicKey == null ? 0 : publicKey.hashCode())) * 1991;
        SecureMessagingWrapper secureMessagingWrapper = this.wrapper;
        return hashCode8 + (secureMessagingWrapper != null ? secureMessagingWrapper.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PACEResult pACEResult = (PACEResult) obj;
        AccessKeySpec accessKeySpec = this.paceKey;
        if (accessKeySpec == null) {
            if (pACEResult.paceKey != null) {
                return false;
            }
        } else if (!accessKeySpec.equals(pACEResult.paceKey)) {
            return false;
        }
        String str = this.agreementAlg;
        if (str == null) {
            if (pACEResult.agreementAlg != null) {
                return false;
            }
        } else if (!str.equals(pACEResult.agreementAlg)) {
            return false;
        }
        String str2 = this.cipherAlg;
        if (str2 == null) {
            if (pACEResult.cipherAlg != null) {
                return false;
            }
        } else if (!str2.equals(pACEResult.cipherAlg)) {
            return false;
        }
        String str3 = this.digestAlg;
        if (str3 == null) {
            if (pACEResult.digestAlg != null) {
                return false;
            }
        } else if (!str3.equals(pACEResult.digestAlg)) {
            return false;
        }
        PACEMappingResult pACEMappingResult = this.mappingResult;
        if (pACEMappingResult == null) {
            if (pACEResult.mappingResult != null) {
                return false;
            }
        } else if (!pACEMappingResult.equals(pACEResult.mappingResult)) {
            return false;
        }
        if (this.keyLength != pACEResult.keyLength || this.mappingType != pACEResult.mappingType) {
            return false;
        }
        KeyPair keyPair = this.pcdKeyPair;
        if (keyPair == null) {
            if (pACEResult.pcdKeyPair != null) {
                return false;
            }
        } else if (!keyPair.equals(pACEResult.pcdKeyPair)) {
            return false;
        }
        PublicKey publicKey = this.piccPublicKey;
        if (publicKey == null) {
            if (pACEResult.piccPublicKey != null) {
                return false;
            }
        } else if (!publicKey.equals(pACEResult.piccPublicKey)) {
            return false;
        }
        SecureMessagingWrapper secureMessagingWrapper = this.wrapper;
        if (secureMessagingWrapper == null) {
            if (pACEResult.wrapper != null) {
                return false;
            }
        } else if (!secureMessagingWrapper.equals(pACEResult.wrapper)) {
            return false;
        }
        return true;
    }
}
