package org.jmrtd.protocol;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import net.sf.scuba.util.Hex;
import org.jmrtd.Util;

/* loaded from: classes6.dex */
public class EACCAResult implements Serializable {
    private static final long serialVersionUID = 4431711176589761513L;
    private byte[] keyHash;
    private BigInteger keyId;
    private PrivateKey pcdPrivateKey;
    private PublicKey pcdPublicKey;
    private PublicKey piccPublicKey;
    private SecureMessagingWrapper wrapper;

    public EACCAResult(BigInteger bigInteger, PublicKey publicKey, byte[] bArr, PublicKey publicKey2, PrivateKey privateKey, SecureMessagingWrapper secureMessagingWrapper) {
        this.keyId = bigInteger;
        this.piccPublicKey = publicKey;
        this.keyHash = bArr;
        this.pcdPublicKey = publicKey2;
        this.pcdPrivateKey = privateKey;
        this.wrapper = secureMessagingWrapper;
    }

    public BigInteger getKeyId() {
        return this.keyId;
    }

    public PublicKey getPublicKey() {
        return this.piccPublicKey;
    }

    public SecureMessagingWrapper getWrapper() {
        return this.wrapper;
    }

    public String toString() {
        return "EACCAResult [keyId: " + this.keyId + ", PICC public key: " + this.piccPublicKey + ", wrapper: " + this.wrapper + ", key hash: " + Hex.bytesToHexString(this.keyHash) + ", PCD public key: " + Util.getDetailedPublicKeyAlgorithm(this.pcdPublicKey) + ", PCD private key: " + Util.getDetailedPrivateKeyAlgorithm(this.pcdPrivateKey) + "]";
    }

    public int hashCode() {
        int hashCode = (Arrays.hashCode(this.keyHash) + 31) * 31;
        BigInteger bigInteger = this.keyId;
        int hashCode2 = (hashCode + (bigInteger == null ? 0 : bigInteger.hashCode())) * 31;
        PublicKey publicKey = this.piccPublicKey;
        int hashCode3 = (hashCode2 + (publicKey == null ? 0 : publicKey.hashCode())) * 31;
        PublicKey publicKey2 = this.pcdPublicKey;
        int hashCode4 = (hashCode3 + (publicKey2 == null ? 0 : publicKey2.hashCode())) * 31;
        PrivateKey privateKey = this.pcdPrivateKey;
        int hashCode5 = (hashCode4 + (privateKey == null ? 0 : privateKey.hashCode())) * 31;
        SecureMessagingWrapper secureMessagingWrapper = this.wrapper;
        return hashCode5 + (secureMessagingWrapper != null ? secureMessagingWrapper.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EACCAResult eACCAResult = (EACCAResult) obj;
        if (!Arrays.equals(this.keyHash, eACCAResult.keyHash)) {
            return false;
        }
        BigInteger bigInteger = this.keyId;
        if (bigInteger == null) {
            if (eACCAResult.keyId != null) {
                return false;
            }
        } else if (!bigInteger.equals(eACCAResult.keyId)) {
            return false;
        }
        PrivateKey privateKey = this.pcdPrivateKey;
        if (privateKey == null) {
            if (eACCAResult.pcdPrivateKey != null) {
                return false;
            }
        } else if (!privateKey.equals(eACCAResult.pcdPrivateKey)) {
            return false;
        }
        PublicKey publicKey = this.pcdPublicKey;
        if (publicKey == null) {
            if (eACCAResult.pcdPublicKey != null) {
                return false;
            }
        } else if (!publicKey.equals(eACCAResult.pcdPublicKey)) {
            return false;
        }
        PublicKey publicKey2 = this.piccPublicKey;
        if (publicKey2 == null) {
            if (eACCAResult.piccPublicKey != null) {
                return false;
            }
        } else if (!publicKey2.equals(eACCAResult.piccPublicKey)) {
            return false;
        }
        SecureMessagingWrapper secureMessagingWrapper = this.wrapper;
        if (secureMessagingWrapper == null) {
            if (eACCAResult.wrapper != null) {
                return false;
            }
        } else if (!secureMessagingWrapper.equals(eACCAResult.wrapper)) {
            return false;
        }
        return true;
    }

    public byte[] getKeyHash() {
        return this.keyHash;
    }

    public PublicKey getPCDPublicKey() {
        return this.pcdPublicKey;
    }

    public PrivateKey getPCDPrivateKey() {
        return this.pcdPrivateKey;
    }
}
