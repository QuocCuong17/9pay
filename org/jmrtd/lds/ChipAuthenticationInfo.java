package org.jmrtd.lds;

import com.beust.jcommander.Parameters;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes6.dex */
public class ChipAuthenticationInfo extends SecurityInfo {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int VERSION_1 = 1;
    public static final int VERSION_2 = 2;
    private static final long serialVersionUID = 5591988305059068535L;
    private BigInteger keyId;
    private String oid;
    private int version;

    public ChipAuthenticationInfo(String str, int i) {
        this(str, i, null);
    }

    public ChipAuthenticationInfo(String str, int i, BigInteger bigInteger) {
        this.oid = str;
        this.version = i;
        this.keyId = bigInteger;
        checkFields();
    }

    @Override // org.jmrtd.lds.SecurityInfo
    @Deprecated
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
        aSN1EncodableVector.add(new ASN1Integer(this.version));
        if (this.keyId != null) {
            aSN1EncodableVector.add(new ASN1Integer(this.keyId));
        }
        return new DLSequence(aSN1EncodableVector);
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getObjectIdentifier() {
        return this.oid;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getProtocolOIDString() {
        return toProtocolOIDString(this.oid);
    }

    public BigInteger getKeyId() {
        return this.keyId;
    }

    protected void checkFields() {
        try {
            if (!checkRequiredIdentifier(this.oid)) {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            }
            int i = this.version;
            if (i != 1 && i != 2) {
                throw new IllegalArgumentException("Wrong version. Was expecting 1 or 2, found " + this.version);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Unexpected exception", (Throwable) e);
            throw new IllegalArgumentException("Malformed ChipAuthenticationInfo.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkRequiredIdentifier(String str) {
        return ID_CA_DH_3DES_CBC_CBC.equals(str) || ID_CA_ECDH_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256.equals(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChipAuthenticationInfo [protocol: ");
        sb.append(toProtocolOIDString(this.oid));
        sb.append(", version: ");
        sb.append(this.version);
        sb.append(", keyId: ");
        Object obj = this.keyId;
        if (obj == null) {
            obj = Parameters.DEFAULT_OPTION_PREFIXES;
        }
        sb.append(obj);
        sb.append("]");
        return sb.toString();
    }

    public int hashCode() {
        String str = this.oid;
        int hashCode = ((str == null ? 0 : str.hashCode()) * 11) + 3 + (this.version * 61);
        BigInteger bigInteger = this.keyId;
        return hashCode + ((bigInteger == null ? 111 : bigInteger.hashCode()) * 1991);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!ChipAuthenticationInfo.class.equals(obj.getClass())) {
            return false;
        }
        ChipAuthenticationInfo chipAuthenticationInfo = (ChipAuthenticationInfo) obj;
        if (!this.oid.equals(chipAuthenticationInfo.oid) || this.version != chipAuthenticationInfo.version) {
            return false;
        }
        BigInteger bigInteger = this.keyId;
        return (bigInteger == null && chipAuthenticationInfo.keyId == null) || (bigInteger != null && bigInteger.equals(chipAuthenticationInfo.keyId));
    }

    public static String toKeyAgreementAlgorithm(String str) {
        if (str == null) {
            throw new NumberFormatException("Unknown OID: null");
        }
        if (ID_CA_DH_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256.equals(str)) {
            return "DH";
        }
        if (ID_CA_ECDH_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256.equals(str)) {
            return "ECDH";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static String toCipherAlgorithm(String str) {
        if (ID_CA_DH_3DES_CBC_CBC.equals(str) || ID_CA_ECDH_3DES_CBC_CBC.equals(str)) {
            return "DESede";
        }
        if (SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256.equals(str)) {
            return "AES";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static String toDigestAlgorithm(String str) {
        if (ID_CA_DH_3DES_CBC_CBC.equals(str) || ID_CA_ECDH_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128.equals(str)) {
            return McElieceCCA2KeyGenParameterSpec.SHA1;
        }
        if (SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256.equals(str)) {
            return "SHA-256";
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    public static int toKeyLength(String str) {
        if (ID_CA_DH_3DES_CBC_CBC.equals(str) || ID_CA_ECDH_3DES_CBC_CBC.equals(str) || SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128.equals(str)) {
            return 128;
        }
        if (SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192.equals(str)) {
            return 192;
        }
        if (SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256.equals(str) || SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256.equals(str)) {
            return 256;
        }
        throw new NumberFormatException("Unknown OID: \"" + str + "\"");
    }

    private static String toProtocolOIDString(String str) {
        return ID_CA_DH_3DES_CBC_CBC.equals(str) ? "id-CA-DH-3DES-CBC-CBC" : SecurityInfo.ID_CA_DH_AES_CBC_CMAC_128.equals(str) ? "id-CA-DH-AES-CBC-CMAC-128" : SecurityInfo.ID_CA_DH_AES_CBC_CMAC_192.equals(str) ? "id-CA-DH-AES-CBC-CMAC-192" : SecurityInfo.ID_CA_DH_AES_CBC_CMAC_256.equals(str) ? "id-CA-DH-AES-CBC-CMAC-256" : ID_CA_ECDH_3DES_CBC_CBC.equals(str) ? "id-CA-ECDH-3DES-CBC-CBC" : SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_128.equals(str) ? "id-CA-ECDH-AES-CBC-CMAC-128" : SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_192.equals(str) ? "id-CA-ECDH-AES-CBC-CMAC-192" : SecurityInfo.ID_CA_ECDH_AES_CBC_CMAC_256.equals(str) ? "id-CA-ECDH-AES-CBC-CMAC-256" : str;
    }
}
