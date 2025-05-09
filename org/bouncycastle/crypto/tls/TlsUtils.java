package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Shorts;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;

/* loaded from: classes6.dex */
public class TlsUtils {
    public static final byte[] EMPTY_BYTES = new byte[0];
    public static final short[] EMPTY_SHORTS = new short[0];
    public static final int[] EMPTY_INTS = new int[0];
    public static final long[] EMPTY_LONGS = new long[0];
    public static final Integer EXT_signature_algorithms = Integers.valueOf(13);
    static final byte[] SSL_CLIENT = {67, 76, 78, 84};
    static final byte[] SSL_SERVER = {83, 82, 86, 82};
    static final byte[][] SSL3_CONST = genSSL3Const();

    public static byte[] PRF(TlsContext tlsContext, byte[] bArr, String str, byte[] bArr2, int i) {
        if (tlsContext.getServerVersion().isSSL()) {
            throw new IllegalStateException("No PRF available for SSLv3 session");
        }
        byte[] byteArray = Strings.toByteArray(str);
        byte[] concat = concat(byteArray, bArr2);
        int prfAlgorithm = tlsContext.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            return PRF_legacy(bArr, byteArray, concat, i);
        }
        byte[] bArr3 = new byte[i];
        hmac_hash(createPRFHash(prfAlgorithm), bArr, concat, bArr3);
        return bArr3;
    }

    public static byte[] PRF_legacy(byte[] bArr, String str, byte[] bArr2, int i) {
        byte[] byteArray = Strings.toByteArray(str);
        return PRF_legacy(bArr, byteArray, concat(byteArray, bArr2), i);
    }

    static byte[] PRF_legacy(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int length = (bArr.length + 1) / 2;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr, bArr.length - length, bArr5, 0, length);
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        hmac_hash(createHash((short) 1), bArr4, bArr3, bArr6);
        hmac_hash(createHash((short) 2), bArr5, bArr3, bArr7);
        for (int i2 = 0; i2 < i; i2++) {
            bArr6[i2] = (byte) (bArr6[i2] ^ bArr7[i2]);
        }
        return bArr6;
    }

    public static void addSignatureAlgorithmsExtension(Hashtable hashtable, Vector vector) throws IOException {
        hashtable.put(EXT_signature_algorithms, createSignatureAlgorithmsExtension(vector));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateKeyBlock(TlsContext tlsContext, int i) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] masterSecret = securityParameters.getMasterSecret();
        byte[] concat = concat(securityParameters.getServerRandom(), securityParameters.getClientRandom());
        return isSSL(tlsContext) ? calculateKeyBlock_SSL(masterSecret, concat, i) : PRF(tlsContext, masterSecret, "key expansion", concat, i);
    }

    static byte[] calculateKeyBlock_SSL(byte[] bArr, byte[] bArr2, int i) {
        Digest createHash = createHash((short) 1);
        Digest createHash2 = createHash((short) 2);
        int digestSize = createHash.getDigestSize();
        int digestSize2 = createHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[i + digestSize];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            byte[] bArr5 = SSL3_CONST[i3];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, digestSize2);
            createHash.doFinal(bArr4, i2);
            i2 += digestSize;
            i3++;
        }
        return Arrays.copyOfRange(bArr4, 0, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateMasterSecret(TlsContext tlsContext, byte[] bArr) {
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        byte[] sessionHash = securityParameters.isExtendedMasterSecret() ? securityParameters.getSessionHash() : concat(securityParameters.getClientRandom(), securityParameters.getServerRandom());
        if (isSSL(tlsContext)) {
            return calculateMasterSecret_SSL(bArr, sessionHash);
        }
        return PRF(tlsContext, bArr, securityParameters.isExtendedMasterSecret() ? ExporterLabel.extended_master_secret : "master secret", sessionHash, 48);
    }

    static byte[] calculateMasterSecret_SSL(byte[] bArr, byte[] bArr2) {
        Digest createHash = createHash((short) 1);
        Digest createHash2 = createHash((short) 2);
        int digestSize = createHash.getDigestSize();
        int digestSize2 = createHash2.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        byte[] bArr4 = new byte[digestSize * 3];
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            byte[] bArr5 = SSL3_CONST[i2];
            createHash2.update(bArr5, 0, bArr5.length);
            createHash2.update(bArr, 0, bArr.length);
            createHash2.update(bArr2, 0, bArr2.length);
            createHash2.doFinal(bArr3, 0);
            createHash.update(bArr, 0, bArr.length);
            createHash.update(bArr3, 0, digestSize2);
            createHash.doFinal(bArr4, i);
            i += digestSize;
        }
        return bArr4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] calculateVerifyData(TlsContext tlsContext, String str, byte[] bArr) {
        if (isSSL(tlsContext)) {
            return bArr;
        }
        SecurityParameters securityParameters = tlsContext.getSecurityParameters();
        return PRF(tlsContext, securityParameters.getMasterSecret(), str, bArr, securityParameters.getVerifyDataLength());
    }

    public static void checkUint16(int i) throws IOException {
        if (!isValidUint16(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint16(long j) throws IOException {
        if (!isValidUint16(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(int i) throws IOException {
        if (!isValidUint24(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint24(long j) throws IOException {
        if (!isValidUint24(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint32(long j) throws IOException {
        if (!isValidUint32(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint48(long j) throws IOException {
        if (!isValidUint48(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint64(long j) throws IOException {
        if (!isValidUint64(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(int i) throws IOException {
        if (!isValidUint8(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(long j) throws IOException {
        if (!isValidUint8(j)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void checkUint8(short s) throws IOException {
        if (!isValidUint8(s)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static Digest cloneHash(short s, Digest digest) {
        switch (s) {
            case 1:
                return new MD5Digest((MD5Digest) digest);
            case 2:
                return new SHA1Digest((SHA1Digest) digest);
            case 3:
                return new SHA224Digest((SHA224Digest) digest);
            case 4:
                return new SHA256Digest((SHA256Digest) digest);
            case 5:
                return new SHA384Digest((SHA384Digest) digest);
            case 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest clonePRFHash(int i, Digest digest) {
        return i != 0 ? cloneHash(getHashAlgorithmForPRFAlgorithm(i), digest) : new CombinedHash((CombinedHash) digest);
    }

    static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static Digest createHash(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return signatureAndHashAlgorithm == null ? new CombinedHash() : createHash(signatureAndHashAlgorithm.getHash());
    }

    public static Digest createHash(short s) {
        switch (s) {
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA224Digest();
            case 4:
                return new SHA256Digest();
            case 5:
                return new SHA384Digest();
            case 6:
                return new SHA512Digest();
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Digest createPRFHash(int i) {
        return i != 0 ? createHash(getHashAlgorithmForPRFAlgorithm(i)) : new CombinedHash();
    }

    public static byte[] createSignatureAlgorithmsExtension(Vector vector) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encodeSupportedSignatureAlgorithms(vector, false, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static TlsSigner createTlsSigner(short s) {
        if (s == 1) {
            return new TlsRSASigner();
        }
        if (s == 2) {
            return new TlsDSSSigner();
        }
        if (s == 64) {
            return new TlsECDSASigner();
        }
        throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
    }

    public static byte[] encodeOpaque8(byte[] bArr) throws IOException {
        checkUint8(bArr.length);
        return Arrays.prepend(bArr, (byte) bArr.length);
    }

    public static void encodeSupportedSignatureAlgorithms(Vector vector, boolean z, OutputStream outputStream) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        checkUint16(size);
        writeUint16(size, outputStream);
        for (int i = 0; i < vector.size(); i++) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (!z && signatureAndHashAlgorithm.getSignature() == 0) {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
            signatureAndHashAlgorithm.encode(outputStream);
        }
    }

    public static byte[] encodeUint16ArrayWithUint16Length(int[] iArr) throws IOException {
        byte[] bArr = new byte[(iArr.length * 2) + 2];
        writeUint16ArrayWithUint16Length(iArr, bArr, 0);
        return bArr;
    }

    public static byte[] encodeUint8ArrayWithUint8Length(short[] sArr) throws IOException {
        byte[] bArr = new byte[sArr.length + 1];
        writeUint8ArrayWithUint8Length(sArr, bArr, 0);
        return bArr;
    }

    private static byte[][] genSSL3Const() {
        byte[][] bArr = new byte[10];
        int i = 0;
        while (i < 10) {
            int i2 = i + 1;
            byte[] bArr2 = new byte[i2];
            Arrays.fill(bArr2, (byte) (i + 65));
            bArr[i] = bArr2;
            i = i2;
        }
        return bArr;
    }

    public static Vector getAllSignatureAlgorithms() {
        Vector vector = new Vector(4);
        vector.addElement(Shorts.valueOf((short) 0));
        vector.addElement(Shorts.valueOf((short) 1));
        vector.addElement(Shorts.valueOf((short) 2));
        vector.addElement(Shorts.valueOf((short) 3));
        return vector;
    }

    public static int getCipherType(int i) throws IOException {
        int encryptionAlgorithm = getEncryptionAlgorithm(i);
        if (encryptionAlgorithm == 103 || encryptionAlgorithm == 104) {
            return 2;
        }
        switch (encryptionAlgorithm) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 12:
            case 13:
            case 14:
                return 1;
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 2;
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short getClientCertificateType(Certificate certificate, Certificate certificate2) throws IOException {
        if (certificate.isEmpty()) {
            return (short) -1;
        }
        org.bouncycastle.asn1.x509.Certificate certificateAt = certificate.getCertificateAt(0);
        try {
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(certificateAt.getSubjectPublicKeyInfo());
            if (createKey.isPrivate()) {
                throw new TlsFatalAlert((short) 80);
            }
            if (createKey instanceof RSAKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return (short) 1;
            }
            if (createKey instanceof DSAPublicKeyParameters) {
                validateKeyUsage(certificateAt, 128);
                return (short) 2;
            }
            if (!(createKey instanceof ECPublicKeyParameters)) {
                throw new TlsFatalAlert((short) 43);
            }
            validateKeyUsage(certificateAt, 128);
            return (short) 64;
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 43, e);
        }
    }

    public static Vector getDefaultDSSSignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm((short) 2, (short) 2));
    }

    public static Vector getDefaultECDSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm((short) 2, (short) 3));
    }

    public static Vector getDefaultRSASignatureAlgorithms() {
        return vectorOfOne(new SignatureAndHashAlgorithm((short) 2, (short) 1));
    }

    public static Vector getDefaultSupportedSignatureAlgorithms() {
        short[] sArr = {2, 3, 4, 5, 6};
        short[] sArr2 = {1, 2, 3};
        Vector vector = new Vector();
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                vector.addElement(new SignatureAndHashAlgorithm(sArr[i2], sArr2[i]));
            }
        }
        return vector;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0010. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0013. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x000d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getEncryptionAlgorithm(int i) throws IOException {
        if (i != 1 && i != 2) {
            if (i != 4 && i != 5) {
                switch (i) {
                    case 10:
                    case 13:
                    case 16:
                    case 19:
                    case 22:
                    case 27:
                    case 139:
                    case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /* 143 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA /* 147 */:
                        return 7;
                    case 24:
                    case 138:
                    case 142:
                    case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /* 146 */:
                        break;
                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                    case 133:
                    case 134:
                    case 135:
                    case 136:
                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                    case 192:
                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                        return 13;
                    case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /* 140 */:
                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /* 144 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA /* 148 */:
                    case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /* 174 */:
                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /* 178 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256 /* 182 */:
                        return 8;
                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA /* 141 */:
                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA /* 145 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA /* 149 */:
                    case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /* 175 */:
                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /* 179 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /* 183 */:
                        return 9;
                    case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA /* 150 */:
                    case 151:
                    case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /* 152 */:
                    case 153:
                    case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /* 154 */:
                    case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /* 155 */:
                        return 14;
                    case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
                    case 158:
                    case 160:
                    case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
                    case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
                    case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
                    case 168:
                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                    case 172:
                        return 10;
                    case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
                    case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
                    case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
                    case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
                    case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
                    case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                        return 11;
                    case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /* 176 */:
                    case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /* 177 */:
                    case 180:
                    case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /* 181 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256 /* 184 */:
                    case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /* 185 */:
                        return 0;
                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
                    case 188:
                    case 189:
                    case 190:
                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
                        return 12;
                    default:
                        switch (i) {
                            case 44:
                            case 45:
                            case 46:
                                break;
                            case 47:
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 60:
                            case 62:
                            case 63:
                            case 64:
                                return 8;
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                            case 58:
                            case 61:
                                return 9;
                            case 59:
                                break;
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                                return 12;
                            default:
                                switch (i) {
                                    case 103:
                                    case 108:
                                        return 8;
                                    case 104:
                                    case 105:
                                    case 106:
                                    case 107:
                                    case 109:
                                        return 9;
                                    default:
                                        switch (i) {
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
                                                break;
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA /* 49154 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA /* 49159 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA /* 49164 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA /* 49169 */:
                                            case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA /* 49174 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA /* 49203 */:
                                                break;
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
                                            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
                                            case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
                                            case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
                                            case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
                                                return 7;
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
                                            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
                                            case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
                                            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
                                            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
                                                return 8;
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
                                            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
                                            case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
                                            case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
                                            case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
                                                return 9;
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                                return 10;
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                                return 11;
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
                                                break;
                                            default:
                                                switch (i) {
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
                                                        return 12;
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                                                        return 13;
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                        return 19;
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                        return 20;
                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                        return 15;
                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                        return 17;
                                                    case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                    case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                        return 16;
                                                    case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                    case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                    case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                        return 18;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                            case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                            case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                return 21;
                                                            default:
                                                                switch (i) {
                                                                    case 65280:
                                                                    case 65282:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                        return 103;
                                                                    case 65281:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                        return 104;
                                                                    default:
                                                                        switch (i) {
                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB /* 65296 */:
                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB /* 65298 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                                return 103;
                                                                            case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB /* 65297 */:
                                                                            case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB /* 65299 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                                return 104;
                                                                            default:
                                                                                throw new TlsFatalAlert((short) 80);
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
            }
            return 2;
        }
        return 0;
    }

    public static byte[] getExtensionData(Hashtable hashtable, Integer num) {
        if (hashtable == null) {
            return null;
        }
        return (byte[]) hashtable.get(num);
    }

    public static short getHashAlgorithmForPRFAlgorithm(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("legacy PRF not a valid algorithm");
        }
        if (i == 1) {
            return (short) 4;
        }
        if (i == 2) {
            return (short) 5;
        }
        throw new IllegalArgumentException("unknown PRFAlgorithm");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x000f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0012. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0015. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0053 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getKeyExchangeAlgorithm(int i) throws IOException {
        if (i != 1 && i != 2 && i != 4 && i != 5) {
            switch (i) {
                case 10:
                case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA /* 150 */:
                case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
                case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
                case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
                case 192:
                    break;
                case 13:
                case 133:
                case 151:
                case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
                case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
                case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
                case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
                    return 7;
                case 16:
                case 134:
                case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /* 152 */:
                case 160:
                case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
                case 188:
                case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
                    return 9;
                case 19:
                case 135:
                case 153:
                case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
                case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
                case 189:
                case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
                    return 3;
                case 22:
                case 136:
                case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /* 154 */:
                case 158:
                case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
                case 190:
                case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                    return 5;
                case 24:
                case 27:
                case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /* 155 */:
                case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
                case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
                case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
                case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                    return 11;
                case 138:
                case 139:
                case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /* 140 */:
                case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA /* 141 */:
                case 168:
                case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
                case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /* 174 */:
                case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /* 175 */:
                case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /* 176 */:
                case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /* 177 */:
                    return 13;
                case 142:
                case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /* 143 */:
                case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /* 144 */:
                case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA /* 145 */:
                case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /* 178 */:
                case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /* 179 */:
                case 180:
                case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /* 181 */:
                    return 14;
                case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /* 146 */:
                case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA /* 147 */:
                case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA /* 148 */:
                case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA /* 149 */:
                case 172:
                case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256 /* 182 */:
                case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /* 183 */:
                case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256 /* 184 */:
                case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /* 185 */:
                    return 15;
                default:
                    switch (i) {
                        case 44:
                            return 13;
                        case 45:
                            return 14;
                        case 46:
                            return 15;
                        case 47:
                        case 53:
                        case 59:
                        case 60:
                        case 61:
                        case 65:
                            break;
                        case 48:
                        case 54:
                        case 62:
                        case 66:
                            return 7;
                        case 49:
                        case 55:
                        case 63:
                        case 67:
                            return 9;
                        case 50:
                        case 56:
                        case 64:
                        case 68:
                            return 3;
                        case 51:
                        case 57:
                        case 69:
                            break;
                        case 52:
                        case 58:
                        case 70:
                            return 11;
                        default:
                            switch (i) {
                                case 103:
                                case 107:
                                    break;
                                case 104:
                                    return 7;
                                case 105:
                                    return 9;
                                case 106:
                                    return 3;
                                case 108:
                                case 109:
                                    return 11;
                                default:
                                    switch (i) {
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA /* 49154 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                        case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                            return 16;
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA /* 49159 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                        case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                            return 17;
                                        case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA /* 49164 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                        case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                            return 18;
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA /* 49169 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                        case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                            return 19;
                                        case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
                                        case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA /* 49174 */:
                                        case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
                                        case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
                                        case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
                                            return 20;
                                        case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
                                        case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
                                        case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
                                            return 21;
                                        case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
                                        case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
                                        case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
                                            return 23;
                                        case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
                                        case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
                                        case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
                                            return 22;
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA /* 49203 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
                                        case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
                                            return 24;
                                        default:
                                            switch (i) {
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                    return 17;
                                                case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                                case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                                case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                    return 16;
                                                case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                                case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                                case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                    return 19;
                                                case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                                case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                                case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                    return 18;
                                                case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                    break;
                                                case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                    break;
                                                case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                    return 9;
                                                case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                    return 3;
                                                case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                    return 7;
                                                case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                    return 11;
                                                case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
                                                case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
                                                case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                    return 13;
                                                case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
                                                case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
                                                case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                    return 14;
                                                case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
                                                case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
                                                    return 15;
                                                case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
                                                case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                                                    return 24;
                                                default:
                                                    switch (i) {
                                                        case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                            return 19;
                                                        case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                            return 17;
                                                        case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                            break;
                                                        case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                            return 13;
                                                        case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                            return 24;
                                                        case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                            return 14;
                                                        case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                            return 15;
                                                        default:
                                                            switch (i) {
                                                                case 65280:
                                                                case 65281:
                                                                    break;
                                                                case 65282:
                                                                case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                    return 19;
                                                                case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                    return 17;
                                                                default:
                                                                    switch (i) {
                                                                        case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB /* 65296 */:
                                                                        case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB /* 65297 */:
                                                                            return 13;
                                                                        case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB /* 65298 */:
                                                                        case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB /* 65299 */:
                                                                            return 14;
                                                                        case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                        case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                            return 24;
                                                                        default:
                                                                            throw new TlsFatalAlert((short) 80);
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x000f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0012. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:29:0x002c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int getMACAlgorithm(int i) throws IOException {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 5) {
                        switch (i) {
                            case 10:
                            case 13:
                            case 16:
                            case 19:
                            case 22:
                            case 27:
                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA /* 132 */:
                            case 133:
                            case 134:
                            case 135:
                            case 136:
                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA /* 137 */:
                            case 138:
                            case 139:
                            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA /* 140 */:
                            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA /* 141 */:
                            case 142:
                            case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /* 143 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /* 144 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA /* 145 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /* 146 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA /* 147 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA /* 148 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA /* 149 */:
                            case CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA /* 150 */:
                            case 151:
                            case CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA /* 152 */:
                            case 153:
                            case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /* 154 */:
                            case CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA /* 155 */:
                                break;
                            case 24:
                                break;
                            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
                            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
                            case 158:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
                            case 160:
                            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
                            case 168:
                            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                            case 172:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                                return 0;
                            case CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256 /* 174 */:
                            case CipherSuite.TLS_PSK_WITH_NULL_SHA256 /* 176 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /* 178 */:
                            case 180:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256 /* 182 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256 /* 184 */:
                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
                            case 188:
                            case 189:
                            case 190:
                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
                            case 192:
                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                                return 3;
                            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /* 175 */:
                            case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /* 177 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /* 179 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /* 181 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /* 183 */:
                            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /* 185 */:
                                return 4;
                            default:
                                switch (i) {
                                    case 44:
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                    case 50:
                                    case 51:
                                    case 52:
                                    case 53:
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                    case 58:
                                    case 65:
                                    case 66:
                                    case 67:
                                    case 68:
                                    case 69:
                                    case 70:
                                        break;
                                    case 59:
                                    case 60:
                                    case 61:
                                    case 62:
                                    case 63:
                                    case 64:
                                        return 3;
                                    default:
                                        switch (i) {
                                            case 103:
                                            case 104:
                                            case 105:
                                            case 106:
                                            case 107:
                                            case 108:
                                            case 109:
                                                return 3;
                                            default:
                                                switch (i) {
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /* 49153 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA /* 49154 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49155 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /* 49156 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /* 49157 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA /* 49159 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /* 49163 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA /* 49164 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /* 49165 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /* 49166 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /* 49167 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA /* 49169 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
                                                    case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /* 49173 */:
                                                    case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA /* 49174 */:
                                                    case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /* 49175 */:
                                                    case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /* 49176 */:
                                                    case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /* 49177 */:
                                                    case CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA /* 49178 */:
                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA /* 49179 */:
                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA /* 49180 */:
                                                    case CipherSuite.TLS_SRP_SHA_WITH_AES_128_CBC_SHA /* 49181 */:
                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA /* 49182 */:
                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA /* 49183 */:
                                                    case CipherSuite.TLS_SRP_SHA_WITH_AES_256_CBC_SHA /* 49184 */:
                                                    case CipherSuite.TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA /* 49185 */:
                                                    case CipherSuite.TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA /* 49186 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA /* 49203 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /* 49204 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /* 49205 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /* 49206 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /* 49209 */:
                                                        break;
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /* 49207 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /* 49210 */:
                                                        return 3;
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /* 49208 */:
                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /* 49211 */:
                                                        break;
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                                        return 0;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49300 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49302 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49304 */:
                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /* 49306 */:
                                                                return 3;
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49301 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49303 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49305 */:
                                                            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /* 49307 */:
                                                                break;
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                                return 0;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                                    case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                        return 0;
                                                                    default:
                                                                        switch (i) {
                                                                            case 65280:
                                                                            case 65281:
                                                                            case 65282:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                                return 0;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB /* 65296 */:
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB /* 65297 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB /* 65298 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB /* 65299 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                                        return 0;
                                                                                    default:
                                                                                        throw new TlsFatalAlert((short) 80);
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                    }
                }
            }
            return 2;
        }
        return 1;
    }

    public static ProtocolVersion getMinimumVersion(int i) {
        switch (i) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                break;
            default:
                switch (i) {
                    case 103:
                    case 104:
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                        break;
                    default:
                        switch (i) {
                            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /* 156 */:
                            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /* 157 */:
                            case 158:
                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /* 159 */:
                            case 160:
                            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /* 161 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /* 162 */:
                            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /* 163 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /* 164 */:
                            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /* 165 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 /* 166 */:
                            case CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 /* 167 */:
                            case 168:
                            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /* 169 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /* 170 */:
                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /* 171 */:
                            case 172:
                            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /* 173 */:
                                break;
                            default:
                                switch (i) {
                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 186 */:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 187 */:
                                    case 188:
                                    case 189:
                                    case 190:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /* 191 */:
                                    case 192:
                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 193 */:
                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 194 */:
                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /* 195 */:
                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                                        break;
                                    default:
                                        switch (i) {
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /* 49189 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /* 49190 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /* 49193 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /* 49194 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /* 49197 */:
                                            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /* 49198 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
                                            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /* 49201 */:
                                            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /* 49202 */:
                                                break;
                                            default:
                                                switch (i) {
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49268 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49269 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49272 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49273 */:
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
                                                    case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
                                                    case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49278 */:
                                                    case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49279 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
                                                    case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49282 */:
                                                    case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49283 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /* 49284 */:
                                                    case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /* 49285 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49288 */:
                                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49289 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49292 */:
                                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49293 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49294 */:
                                                    case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49295 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49296 */:
                                                    case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49297 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /* 49298 */:
                                                    case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /* 49299 */:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
                                                            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
                                                            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /* 49316 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /* 49317 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /* 49318 */:
                                                            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /* 49319 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /* 49320 */:
                                                            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /* 49321 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /* 49322 */:
                                                            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /* 49323 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52395 */:
                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52396 */:
                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52397 */:
                                                                    case CipherSuite.DRAFT_TLS_RSA_PSK_WITH_CHACHA20_POLY1305_SHA256 /* 52398 */:
                                                                        break;
                                                                    default:
                                                                        switch (i) {
                                                                            case 65280:
                                                                            case 65281:
                                                                            case 65282:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB /* 65283 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_128_OCB /* 65284 */:
                                                                            case CipherSuite.DRAFT_TLS_ECDHE_ECDSA_WITH_AES_256_OCB /* 65285 */:
                                                                                break;
                                                                            default:
                                                                                switch (i) {
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_128_OCB /* 65296 */:
                                                                                    case CipherSuite.DRAFT_TLS_PSK_WITH_AES_256_OCB /* 65297 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_128_OCB /* 65298 */:
                                                                                    case CipherSuite.DRAFT_TLS_DHE_PSK_WITH_AES_256_OCB /* 65299 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_128_OCB /* 65300 */:
                                                                                    case CipherSuite.DRAFT_TLS_ECDHE_PSK_WITH_AES_256_OCB /* 65301 */:
                                                                                        break;
                                                                                    default:
                                                                                        return ProtocolVersion.SSLv3;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        return ProtocolVersion.TLSv12;
    }

    public static ASN1ObjectIdentifier getOIDForHashAlgorithm(short s) {
        switch (s) {
            case 1:
                return PKCSObjectIdentifiers.md5;
            case 2:
                return X509ObjectIdentifiers.id_SHA1;
            case 3:
                return NISTObjectIdentifiers.id_sha224;
            case 4:
                return NISTObjectIdentifiers.id_sha256;
            case 5:
                return NISTObjectIdentifiers.id_sha384;
            case 6:
                return NISTObjectIdentifiers.id_sha512;
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static Vector getSignatureAlgorithmsExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, EXT_signature_algorithms);
        if (extensionData == null) {
            return null;
        }
        return readSignatureAlgorithmsExtension(extensionData);
    }

    public static SignatureAndHashAlgorithm getSignatureAndHashAlgorithm(TlsContext tlsContext, TlsSignerCredentials tlsSignerCredentials) throws IOException {
        if (!isTLSv12(tlsContext)) {
            return null;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = tlsSignerCredentials.getSignatureAndHashAlgorithm();
        if (signatureAndHashAlgorithm != null) {
            return signatureAndHashAlgorithm;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public static Vector getUsableSignatureAlgorithms(Vector vector) {
        if (vector == null) {
            return getAllSignatureAlgorithms();
        }
        Vector vector2 = new Vector(4);
        vector2.addElement(Shorts.valueOf((short) 0));
        for (int i = 0; i < vector.size(); i++) {
            Short valueOf = Shorts.valueOf(((SignatureAndHashAlgorithm) vector.elementAt(i)).getSignature());
            if (!vector2.contains(valueOf)) {
                vector2.addElement(valueOf);
            }
        }
        return vector2;
    }

    public static boolean hasExpectedEmptyExtensionData(Hashtable hashtable, Integer num, short s) throws IOException {
        byte[] extensionData = getExtensionData(hashtable, num);
        if (extensionData == null) {
            return false;
        }
        if (extensionData.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s);
    }

    public static boolean hasSigningCapability(short s) {
        return s == 1 || s == 2 || s == 64;
    }

    static void hmac_hash(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(bArr));
        int digestSize = digest.getDigestSize();
        int length = ((bArr3.length + digestSize) - 1) / digestSize;
        int macSize = hMac.getMacSize();
        byte[] bArr4 = new byte[macSize];
        byte[] bArr5 = new byte[hMac.getMacSize()];
        byte[] bArr6 = bArr2;
        int i = 0;
        while (i < length) {
            hMac.update(bArr6, 0, bArr6.length);
            hMac.doFinal(bArr4, 0);
            hMac.update(bArr4, 0, macSize);
            hMac.update(bArr2, 0, bArr2.length);
            hMac.doFinal(bArr5, 0);
            int i2 = digestSize * i;
            System.arraycopy(bArr5, 0, bArr3, i2, Math.min(digestSize, bArr3.length - i2));
            i++;
            bArr6 = bArr4;
        }
    }

    public static TlsSession importSession(byte[] bArr, SessionParameters sessionParameters) {
        return new TlsSessionImpl(bArr, sessionParameters);
    }

    public static boolean isAEADCipherSuite(int i) throws IOException {
        return 2 == getCipherType(i);
    }

    public static boolean isBlockCipherSuite(int i) throws IOException {
        return 1 == getCipherType(i);
    }

    public static boolean isSSL(TlsContext tlsContext) {
        return tlsContext.getServerVersion().isSSL();
    }

    public static boolean isSignatureAlgorithmsExtensionAllowed(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isStreamCipherSuite(int i) throws IOException {
        return getCipherType(i) == 0;
    }

    public static boolean isTLSv11(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv11.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv11(TlsContext tlsContext) {
        return isTLSv11(tlsContext.getServerVersion());
    }

    public static boolean isTLSv12(ProtocolVersion protocolVersion) {
        return ProtocolVersion.TLSv12.isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isTLSv12(TlsContext tlsContext) {
        return isTLSv12(tlsContext.getServerVersion());
    }

    public static boolean isValidCipherSuiteForSignatureAlgorithms(int i, Vector vector) {
        short s;
        Short valueOf;
        try {
            int keyExchangeAlgorithm = getKeyExchangeAlgorithm(i);
            if (keyExchangeAlgorithm != 3 && keyExchangeAlgorithm != 4) {
                if (keyExchangeAlgorithm != 5 && keyExchangeAlgorithm != 6) {
                    if (keyExchangeAlgorithm != 11 && keyExchangeAlgorithm != 12) {
                        if (keyExchangeAlgorithm == 17) {
                            valueOf = Shorts.valueOf((short) 3);
                            return vector.contains(valueOf);
                        }
                        if (keyExchangeAlgorithm != 19) {
                            if (keyExchangeAlgorithm != 20) {
                                if (keyExchangeAlgorithm != 22) {
                                    if (keyExchangeAlgorithm != 23) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    s = 0;
                    valueOf = Shorts.valueOf(s);
                    return vector.contains(valueOf);
                }
                valueOf = Shorts.valueOf((short) 1);
                return vector.contains(valueOf);
            }
            s = 2;
            valueOf = Shorts.valueOf(s);
            return vector.contains(valueOf);
        } catch (IOException unused) {
            return true;
        }
    }

    public static boolean isValidCipherSuiteForVersion(int i, ProtocolVersion protocolVersion) {
        return getMinimumVersion(i).isEqualOrEarlierVersionOf(protocolVersion.getEquivalentTLSVersion());
    }

    public static boolean isValidUint16(int i) {
        return (65535 & i) == i;
    }

    public static boolean isValidUint16(long j) {
        return (WebSocketProtocol.PAYLOAD_SHORT_MAX & j) == j;
    }

    public static boolean isValidUint24(int i) {
        return (16777215 & i) == i;
    }

    public static boolean isValidUint24(long j) {
        return (16777215 & j) == j;
    }

    public static boolean isValidUint32(long j) {
        return (4294967295L & j) == j;
    }

    public static boolean isValidUint48(long j) {
        return (281474976710655L & j) == j;
    }

    public static boolean isValidUint64(long j) {
        return true;
    }

    public static boolean isValidUint8(int i) {
        return (i & 255) == i;
    }

    public static boolean isValidUint8(long j) {
        return (255 & j) == j;
    }

    public static boolean isValidUint8(short s) {
        return (s & 255) == s;
    }

    public static Vector parseSupportedSignatureAlgorithms(boolean z, InputStream inputStream) throws IOException {
        int readUint16 = readUint16(inputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i = readUint16 / 2;
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 < i; i2++) {
            SignatureAndHashAlgorithm parse = SignatureAndHashAlgorithm.parse(inputStream);
            if (!z && parse.getSignature() == 0) {
                throw new TlsFatalAlert((short) 47);
            }
            vector.addElement(parse);
        }
        return vector;
    }

    public static ASN1Primitive readASN1Object(byte[] bArr) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive readObject = aSN1InputStream.readObject();
        if (readObject == null) {
            throw new TlsFatalAlert((short) 50);
        }
        if (aSN1InputStream.readObject() == null) {
            return readObject;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static byte[] readAllOrNothing(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        int readFully = Streams.readFully(inputStream, bArr);
        if (readFully == 0) {
            return null;
        }
        if (readFully == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public static ASN1Primitive readDERObject(byte[] bArr) throws IOException {
        ASN1Primitive readASN1Object = readASN1Object(bArr);
        if (Arrays.areEqual(readASN1Object.getEncoded(ASN1Encoding.DER), bArr)) {
            return readASN1Object;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static void readFully(byte[] bArr, InputStream inputStream) throws IOException {
        int length = bArr.length;
        if (length > 0 && length != Streams.readFully(inputStream, bArr)) {
            throw new EOFException();
        }
    }

    public static byte[] readFully(int i, InputStream inputStream) throws IOException {
        if (i < 1) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i];
        if (i == Streams.readFully(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] readOpaque16(InputStream inputStream) throws IOException {
        return readFully(readUint16(inputStream), inputStream);
    }

    public static byte[] readOpaque24(InputStream inputStream) throws IOException {
        return readFully(readUint24(inputStream), inputStream);
    }

    public static byte[] readOpaque8(InputStream inputStream) throws IOException {
        return readFully(readUint8(inputStream), inputStream);
    }

    public static Vector readSignatureAlgorithmsExtension(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Vector parseSupportedSignatureAlgorithms = parseSupportedSignatureAlgorithms(false, byteArrayInputStream);
        TlsProtocol.assertEmpty(byteArrayInputStream);
        return parseSupportedSignatureAlgorithms;
    }

    public static int readUint16(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readUint16(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public static int[] readUint16Array(int i, InputStream inputStream) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readUint16(inputStream);
        }
        return iArr;
    }

    public static int readUint24(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return read3 | (read << 16) | (read2 << 8);
        }
        throw new EOFException();
    }

    public static int readUint24(byte[] bArr, int i) {
        int i2 = (bArr[i] & 255) << 16;
        int i3 = i + 1;
        return (bArr[i3 + 1] & 255) | i2 | ((bArr[i3] & 255) << 8);
    }

    public static long readUint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (inputStream.read() >= 0) {
            return (r4 | (read << 24) | (read2 << 16) | (read3 << 8)) & 4294967295L;
        }
        throw new EOFException();
    }

    public static long readUint32(byte[] bArr, int i) {
        int i2 = (bArr[i] & 255) << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        return ((bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8)) & 4294967295L;
    }

    public static long readUint48(InputStream inputStream) throws IOException {
        return ((readUint24(inputStream) & 4294967295L) << 24) | (4294967295L & readUint24(inputStream));
    }

    public static long readUint48(byte[] bArr, int i) {
        return (readUint24(bArr, i + 3) & 4294967295L) | ((readUint24(bArr, i) & 4294967295L) << 24);
    }

    public static short readUint8(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short readUint8(byte[] bArr, int i) {
        return (short) (bArr[i] & 255);
    }

    public static short[] readUint8Array(int i, InputStream inputStream) throws IOException {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = readUint8(inputStream);
        }
        return sArr;
    }

    public static ProtocolVersion readVersion(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.get(read, read2);
        }
        throw new EOFException();
    }

    public static ProtocolVersion readVersion(byte[] bArr, int i) throws IOException {
        return ProtocolVersion.get(bArr[i] & 255, bArr[i + 1] & 255);
    }

    public static int readVersionRaw(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return read2 | (read << 8);
        }
        throw new EOFException();
    }

    public static int readVersionRaw(byte[] bArr, int i) throws IOException {
        return bArr[i + 1] | (bArr[i] << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void trackHashAlgorithms(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                short hash = ((SignatureAndHashAlgorithm) vector.elementAt(i)).getHash();
                if (HashAlgorithm.isRecognized(hash)) {
                    tlsHandshakeHash.trackHashAlgorithm(hash);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateKeyUsage(org.bouncycastle.asn1.x509.Certificate certificate, int i) throws IOException {
        KeyUsage fromExtensions;
        Extensions extensions = certificate.getTBSCertificate().getExtensions();
        if (extensions != null && (fromExtensions = KeyUsage.fromExtensions(extensions)) != null && (fromExtensions.getBytes()[0] & 255 & i) != i) {
            throw new TlsFatalAlert((short) 46);
        }
    }

    private static Vector vectorOfOne(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }

    public static void verifySupportedSignatureAlgorithm(Vector vector, SignatureAndHashAlgorithm signatureAndHashAlgorithm) throws IOException {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        if (signatureAndHashAlgorithm == null) {
            throw new IllegalArgumentException("'signatureAlgorithm' cannot be null");
        }
        if (signatureAndHashAlgorithm.getSignature() != 0) {
            for (int i = 0; i < vector.size(); i++) {
                SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = (SignatureAndHashAlgorithm) vector.elementAt(i);
                if (signatureAndHashAlgorithm2.getHash() == signatureAndHashAlgorithm.getHash() && signatureAndHashAlgorithm2.getSignature() == signatureAndHashAlgorithm.getSignature()) {
                    return;
                }
            }
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static void writeGMTUnixTime(byte[] bArr, int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        bArr[i] = (byte) (currentTimeMillis >>> 24);
        bArr[i + 1] = (byte) (currentTimeMillis >>> 16);
        bArr[i + 2] = (byte) (currentTimeMillis >>> 8);
        bArr[i + 3] = (byte) currentTimeMillis;
    }

    public static void writeOpaque16(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint16(bArr.length);
        writeUint16(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque24(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint24(bArr.length);
        writeUint24(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeOpaque8(byte[] bArr, OutputStream outputStream) throws IOException {
        checkUint8(bArr.length);
        writeUint8(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void writeUint16(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i >>> 8);
        outputStream.write(i);
    }

    public static void writeUint16(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    public static void writeUint16Array(int[] iArr, OutputStream outputStream) throws IOException {
        for (int i : iArr) {
            writeUint16(i, outputStream);
        }
    }

    public static void writeUint16Array(int[] iArr, byte[] bArr, int i) throws IOException {
        for (int i2 : iArr) {
            writeUint16(i2, bArr, i);
            i += 2;
        }
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, OutputStream outputStream) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, outputStream);
        writeUint16Array(iArr, outputStream);
    }

    public static void writeUint16ArrayWithUint16Length(int[] iArr, byte[] bArr, int i) throws IOException {
        int length = iArr.length * 2;
        checkUint16(length);
        writeUint16(length, bArr, i);
        writeUint16Array(iArr, bArr, i + 2);
    }

    public static void writeUint24(int i, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (i >>> 16));
        outputStream.write((byte) (i >>> 8));
        outputStream.write((byte) i);
    }

    public static void writeUint24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void writeUint32(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint32(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 24);
        bArr[i + 1] = (byte) (j >>> 16);
        bArr[i + 2] = (byte) (j >>> 8);
        bArr[i + 3] = (byte) j;
    }

    public static void writeUint48(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint48(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 40);
        bArr[i + 1] = (byte) (j >>> 32);
        bArr[i + 2] = (byte) (j >>> 24);
        bArr[i + 3] = (byte) (j >>> 16);
        bArr[i + 4] = (byte) (j >>> 8);
        bArr[i + 5] = (byte) j;
    }

    public static void writeUint64(long j, OutputStream outputStream) throws IOException {
        outputStream.write((byte) (j >>> 56));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) j);
    }

    public static void writeUint64(long j, byte[] bArr, int i) {
        bArr[i] = (byte) (j >>> 56);
        bArr[i + 1] = (byte) (j >>> 48);
        bArr[i + 2] = (byte) (j >>> 40);
        bArr[i + 3] = (byte) (j >>> 32);
        bArr[i + 4] = (byte) (j >>> 24);
        bArr[i + 5] = (byte) (j >>> 16);
        bArr[i + 6] = (byte) (j >>> 8);
        bArr[i + 7] = (byte) j;
    }

    public static void writeUint8(int i, OutputStream outputStream) throws IOException {
        outputStream.write(i);
    }

    public static void writeUint8(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    public static void writeUint8(short s, OutputStream outputStream) throws IOException {
        outputStream.write(s);
    }

    public static void writeUint8(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
    }

    public static void writeUint8Array(short[] sArr, OutputStream outputStream) throws IOException {
        for (short s : sArr) {
            writeUint8(s, outputStream);
        }
    }

    public static void writeUint8Array(short[] sArr, byte[] bArr, int i) throws IOException {
        for (short s : sArr) {
            writeUint8(s, bArr, i);
            i++;
        }
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, OutputStream outputStream) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, outputStream);
        writeUint8Array(sArr, outputStream);
    }

    public static void writeUint8ArrayWithUint8Length(short[] sArr, byte[] bArr, int i) throws IOException {
        checkUint8(sArr.length);
        writeUint8(sArr.length, bArr, i);
        writeUint8Array(sArr, bArr, i + 1);
    }

    public static void writeVersion(ProtocolVersion protocolVersion, OutputStream outputStream) throws IOException {
        outputStream.write(protocolVersion.getMajorVersion());
        outputStream.write(protocolVersion.getMinorVersion());
    }

    public static void writeVersion(ProtocolVersion protocolVersion, byte[] bArr, int i) {
        bArr[i] = (byte) protocolVersion.getMajorVersion();
        bArr[i + 1] = (byte) protocolVersion.getMinorVersion();
    }
}
