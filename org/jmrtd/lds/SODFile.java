package org.jmrtd.lds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.icao.DataGroupHash;
import org.bouncycastle.asn1.icao.LDSSecurityObject;
import org.bouncycastle.asn1.icao.LDSVersionInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* loaded from: classes6.dex */
public class SODFile extends AbstractTaggedLDSFile {
    private static final String ICAO_LDS_SOD_ALT_OID = "1.3.27.1.1.1";
    private static final String ICAO_LDS_SOD_OID = "2.23.136.1.1.1";
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final String SDU_LDS_SOD_OID = "1.2.528.1.1006.1.20.1";
    private static final long serialVersionUID = -1081347374739311111L;
    private transient SignedData signedData;

    public SODFile(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate) throws GeneralSecurityException {
        this(str, str2, map, privateKey, x509Certificate, null);
    }

    public SODFile(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate, String str3) throws GeneralSecurityException {
        this(str, str2, map, privateKey, x509Certificate, str3, null, null);
    }

    public SODFile(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate, String str3, String str4, String str5) throws GeneralSecurityException {
        super(119);
        try {
            ContentInfo contentInfo = toContentInfo(ICAO_LDS_SOD_OID, str, map, str4, str5);
            this.signedData = SignedDataUtil.createSignedData(str, str2, ICAO_LDS_SOD_OID, contentInfo, SignedDataUtil.signData(str, str2, ICAO_LDS_SOD_OID, contentInfo, privateKey, str3), x509Certificate);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error creating signedData", e);
        }
    }

    public SODFile(String str, String str2, Map<Integer, byte[]> map, byte[] bArr, X509Certificate x509Certificate) throws GeneralSecurityException {
        super(119);
        if (map == null) {
            throw new IllegalArgumentException("Cannot construct security object from null datagroup hashes");
        }
        try {
            this.signedData = SignedDataUtil.createSignedData(str, str2, ICAO_LDS_SOD_OID, toContentInfo(ICAO_LDS_SOD_OID, str, map, null, null), bArr, x509Certificate);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error creating signedData", e);
        }
    }

    public SODFile(InputStream inputStream) throws IOException {
        super(119, inputStream);
        SignedDataUtil.getSignerInfo(this.signedData);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        this.signedData = SignedDataUtil.readSignedData(inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        SignedDataUtil.writeData(this.signedData, outputStream);
    }

    public Map<Integer, byte[]> getDataGroupHashes() {
        DataGroupHash[] datagroupHash = getLDSSecurityObject(this.signedData).getDatagroupHash();
        TreeMap treeMap = new TreeMap();
        for (DataGroupHash dataGroupHash : datagroupHash) {
            treeMap.put(Integer.valueOf(dataGroupHash.getDataGroupNumber()), dataGroupHash.getDataGroupHashValue().getOctets());
        }
        return treeMap;
    }

    public byte[] getEncryptedDigest() {
        return SignedDataUtil.getEncryptedDigest(this.signedData);
    }

    public AlgorithmParameterSpec getDigestEncryptionAlgorithmParams() {
        return SignedDataUtil.getDigestEncryptionAlgorithmParams(this.signedData);
    }

    public byte[] getEContent() throws SignatureException {
        return SignedDataUtil.getEContent(this.signedData);
    }

    public String getDigestAlgorithm() {
        return getDigestAlgorithm(getLDSSecurityObject(this.signedData));
    }

    private static String getDigestAlgorithm(LDSSecurityObject lDSSecurityObject) {
        try {
            return SignedDataUtil.lookupMnemonicByOID(lDSSecurityObject.getDigestAlgorithmIdentifier().getAlgorithm().getId());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
            return null;
        }
    }

    public String getSignerInfoDigestAlgorithm() {
        return SignedDataUtil.getSignerInfoDigestAlgorithm(this.signedData);
    }

    public String getDigestEncryptionAlgorithm() {
        return SignedDataUtil.getDigestEncryptionAlgorithm(this.signedData);
    }

    public String getLDSVersion() {
        LDSVersionInfo versionInfo = getLDSSecurityObject(this.signedData).getVersionInfo();
        if (versionInfo == null) {
            return null;
        }
        return versionInfo.getLdsVersion();
    }

    public String getUnicodeVersion() {
        LDSVersionInfo versionInfo = getLDSSecurityObject(this.signedData).getVersionInfo();
        if (versionInfo == null) {
            return null;
        }
        return versionInfo.getUnicodeVersion();
    }

    public List<X509Certificate> getDocSigningCertificates() {
        return SignedDataUtil.getCertificates(this.signedData);
    }

    public X509Certificate getDocSigningCertificate() {
        List<X509Certificate> docSigningCertificates = getDocSigningCertificates();
        if (docSigningCertificates == null || docSigningCertificates.isEmpty()) {
            return null;
        }
        return docSigningCertificates.get(docSigningCertificates.size() - 1);
    }

    public X500Principal getIssuerX500Principal() {
        X500Name name;
        try {
            IssuerAndSerialNumber issuerAndSerialNumber = SignedDataUtil.getIssuerAndSerialNumber(this.signedData);
            if (issuerAndSerialNumber == null || (name = issuerAndSerialNumber.getName()) == null) {
                return null;
            }
            return new X500Principal(name.getEncoded(ASN1Encoding.DER));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not get issuer", (Throwable) e);
            return null;
        }
    }

    public BigInteger getSerialNumber() {
        IssuerAndSerialNumber issuerAndSerialNumber = SignedDataUtil.getIssuerAndSerialNumber(this.signedData);
        if (issuerAndSerialNumber == null) {
            return null;
        }
        return issuerAndSerialNumber.getSerialNumber().getValue();
    }

    public byte[] getSubjectKeyIdentifier() {
        return SignedDataUtil.getSubjectKeyIdentifier(this.signedData);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SODFile ");
            Iterator<X509Certificate> it = getDocSigningCertificates().iterator();
            while (it.hasNext()) {
                sb.append(it.next().getIssuerX500Principal().getName());
                sb.append(", ");
            }
            return sb.toString();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Unexpected exception", (Throwable) e);
            return "SODFile";
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass().equals(getClass())) {
            return Arrays.equals(getEncoded(), ((SODFile) obj).getEncoded());
        }
        return false;
    }

    public int hashCode() {
        return (Arrays.hashCode(getEncoded()) * 11) + 111;
    }

    private static ContentInfo toContentInfo(String str, String str2, Map<Integer, byte[]> map, String str3, String str4) throws NoSuchAlgorithmException, IOException {
        LDSSecurityObject lDSSecurityObject;
        DataGroupHash[] dataGroupHashArr = new DataGroupHash[map.size()];
        Iterator<Map.Entry<Integer, byte[]>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            dataGroupHashArr[i] = new DataGroupHash(intValue, new DEROctetString(map.get(Integer.valueOf(intValue))));
            i++;
        }
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(new ASN1ObjectIdentifier(SignedDataUtil.lookupOIDByMnemonic(str2)));
        if (str3 == null) {
            lDSSecurityObject = new LDSSecurityObject(algorithmIdentifier, dataGroupHashArr);
        } else {
            lDSSecurityObject = new LDSSecurityObject(algorithmIdentifier, dataGroupHashArr, new LDSVersionInfo(str3, str4));
        }
        return new ContentInfo(new ASN1ObjectIdentifier(str), new DEROctetString(lDSSecurityObject));
    }

    private static LDSSecurityObject getLDSSecurityObject(SignedData signedData) {
        try {
            ContentInfo encapContentInfo = signedData.getEncapContentInfo();
            String id2 = encapContentInfo.getContentType().getId();
            DEROctetString dEROctetString = (DEROctetString) encapContentInfo.getContent();
            if (!ICAO_LDS_SOD_OID.equals(id2) && !SDU_LDS_SOD_OID.equals(id2) && !ICAO_LDS_SOD_ALT_OID.equals(id2)) {
                LOGGER.warning("SignedData does not appear to contain an LDS SOd. (content type is " + id2 + ", was expecting " + ICAO_LDS_SOD_OID + ")");
            }
            ASN1InputStream aSN1InputStream = new ASN1InputStream(new ByteArrayInputStream(dEROctetString.getOctets()));
            try {
                ASN1Primitive readObject = aSN1InputStream.readObject();
                if (!(readObject instanceof ASN1Sequence)) {
                    throw new IllegalStateException("Expected ASN1Sequence, found " + readObject.getClass().getSimpleName());
                }
                LDSSecurityObject lDSSecurityObject = LDSSecurityObject.getInstance(readObject);
                if (aSN1InputStream.readObject() != null) {
                    LOGGER.warning("Ignoring extra object found after LDSSecurityObject...");
                }
                return lDSSecurityObject;
            } finally {
                aSN1InputStream.close();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not read security object in signedData", e);
        }
    }
}
