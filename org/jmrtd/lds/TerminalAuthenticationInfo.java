package org.jmrtd.lds;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DLSequence;

/* loaded from: classes6.dex */
public class TerminalAuthenticationInfo extends SecurityInfo {
    public static final int VERSION_1 = 1;
    private static final int VERSION_2 = 2;
    private static final long serialVersionUID = 6220506985707094044L;
    private transient ASN1Sequence efCVCA;
    private String oid;
    private int version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TerminalAuthenticationInfo(String str, int i, ASN1Sequence aSN1Sequence) {
        this.oid = str;
        this.version = i;
        this.efCVCA = aSN1Sequence;
        checkFields();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TerminalAuthenticationInfo(String str, int i) {
        this(str, i, null);
    }

    public TerminalAuthenticationInfo() {
        this(ID_TA, 1);
    }

    public TerminalAuthenticationInfo(short s, byte b) {
        this(ID_TA, 1, constructEFCVCA(s, b));
    }

    public int getVersion() {
        return this.version;
    }

    @Override // org.jmrtd.lds.SecurityInfo
    @Deprecated
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(this.oid));
        aSN1EncodableVector.add(new ASN1Integer(this.version));
        ASN1Sequence aSN1Sequence = this.efCVCA;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        return new DLSequence(aSN1EncodableVector);
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getObjectIdentifier() {
        return this.oid;
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getProtocolOIDString() {
        return toProtocolOIDString(this.oid);
    }

    public int getFileId() {
        return getFID(this.efCVCA);
    }

    public byte getShortFileId() {
        return getSFI(this.efCVCA);
    }

    public String toString() {
        return "TerminalAuthenticationInfo [protocol: " + toProtocolOIDString(this.oid) + ", version: " + this.version + ", fileID: " + getFileId() + ", shortFileID: " + ((int) getShortFileId()) + "]";
    }

    public int hashCode() {
        String str = this.oid;
        int hashCode = ((str == null ? 0 : str.hashCode()) * 7) + 123 + (this.version * 5);
        ASN1Sequence aSN1Sequence = this.efCVCA;
        return hashCode + ((aSN1Sequence == null ? 1 : aSN1Sequence.hashCode()) * 3);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!TerminalAuthenticationInfo.class.equals(obj.getClass())) {
            return false;
        }
        TerminalAuthenticationInfo terminalAuthenticationInfo = (TerminalAuthenticationInfo) obj;
        ASN1Sequence aSN1Sequence = this.efCVCA;
        if (aSN1Sequence == null && terminalAuthenticationInfo.efCVCA != null) {
            return false;
        }
        if (aSN1Sequence == null || terminalAuthenticationInfo.efCVCA != null) {
            return getDERObject().equals(terminalAuthenticationInfo.getDERObject());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkRequiredIdentifier(String str) {
        return ID_TA.equals(str);
    }

    private void checkFields() {
        try {
            if (!checkRequiredIdentifier(this.oid)) {
                throw new IllegalArgumentException("Wrong identifier: " + this.oid);
            }
            int i = this.version;
            if (i != 1 && i != 2) {
                throw new IllegalArgumentException("Wrong version. Was expecting 1 or 2, found " + this.version);
            }
            ASN1Sequence aSN1Sequence = this.efCVCA;
            if (aSN1Sequence != null) {
                if (((DEROctetString) aSN1Sequence.getObjectAt(0)).getOctets().length != 2) {
                    throw new IllegalArgumentException("Malformed FID.");
                }
                if (this.efCVCA.size() == 2 && ((DEROctetString) this.efCVCA.getObjectAt(1)).getOctets().length != 1) {
                    throw new IllegalArgumentException("Malformed SFI.");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Malformed TerminalAuthenticationInfo", e);
        }
    }

    private static ASN1Sequence constructEFCVCA(short s, byte b) {
        if (b != -1) {
            return new DLSequence(new ASN1Encodable[]{new DEROctetString(new byte[]{(byte) ((65280 & s) >> 8), (byte) (s & 255)}), new DEROctetString(new byte[]{(byte) (b & 255)})});
        }
        return new DLSequence(new ASN1Encodable[]{new DEROctetString(new byte[]{(byte) ((65280 & s) >> 8), (byte) (s & 255)})});
    }

    private static short getFID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence == null) {
            return (short) -1;
        }
        byte[] octets = ((DEROctetString) aSN1Sequence.getObjectAt(0)).getOctets();
        return (short) ((octets[1] & 255) | ((octets[0] & 255) << 8));
    }

    private static byte getSFI(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence != null && aSN1Sequence.size() == 2) {
            return ((DEROctetString) aSN1Sequence.getObjectAt(1)).getOctets()[0];
        }
        return (byte) -1;
    }

    private String toProtocolOIDString(String str) {
        return ID_TA.equals(str) ? "id-TA" : ID_TA_RSA.equals(str) ? "id-TA-RSA" : ID_TA_RSA_V1_5_SHA_1.equals(str) ? "id-TA-RSA-v1-5-SHA-1" : ID_TA_RSA_V1_5_SHA_256.equals(str) ? "id-TA-RSA-v1-5-SHA-256" : ID_TA_RSA_PSS_SHA_1.equals(str) ? "id-TA-RSA-PSS-SHA-1" : ID_TA_RSA_PSS_SHA_256.equals(str) ? "id-TA-RSA-PSS-SHA-256" : ID_TA_ECDSA.equals(str) ? "id-TA-ECDSA" : ID_TA_ECDSA_SHA_1.equals(str) ? "id-TA-ECDSA-SHA-1" : ID_TA_ECDSA_SHA_224.equals(str) ? "id-TA-ECDSA-SHA-224" : ID_TA_ECDSA_SHA_256.equals(str) ? "id-TA-ECDSA-SHA-256" : str;
    }
}
