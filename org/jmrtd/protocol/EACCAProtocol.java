package org.jmrtd.protocol;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.tlv.TLVUtil;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.jmrtd.APDULevelEACCACapable;
import org.jmrtd.Util;
import org.jmrtd.lds.ChipAuthenticationInfo;
import org.jmrtd.lds.SecurityInfo;

/* loaded from: classes6.dex */
public class EACCAProtocol {
    private static final int COMMAND_CHAINING_CHUNK_SIZE = 224;
    private int maxTranceiveLength;
    private APDULevelEACCACapable service;
    private boolean shouldCheckMAC;
    private SecureMessagingWrapper wrapper;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final Provider BC_PROVIDER = Util.getBouncyCastleProvider();

    public EACCAProtocol(APDULevelEACCACapable aPDULevelEACCACapable, SecureMessagingWrapper secureMessagingWrapper, int i, boolean z) {
        this.service = aPDULevelEACCACapable;
        this.wrapper = secureMessagingWrapper;
        this.maxTranceiveLength = i;
        this.shouldCheckMAC = z;
    }

    public EACCAResult doCA(BigInteger bigInteger, String str, String str2, PublicKey publicKey) throws CardServiceException {
        if (publicKey == null) {
            throw new IllegalArgumentException("PICC public key is null");
        }
        String keyAgreementAlgorithm = ChipAuthenticationInfo.toKeyAgreementAlgorithm(str);
        if (keyAgreementAlgorithm == null) {
            throw new IllegalArgumentException("Unknown agreement algorithm");
        }
        if (!"ECDH".equals(keyAgreementAlgorithm) && !"DH".equals(keyAgreementAlgorithm)) {
            throw new IllegalArgumentException("Unsupported agreement algorithm, expected ECDH or DH, found " + keyAgreementAlgorithm);
        }
        if (str == null) {
            str = inferChipAuthenticationOIDfromPublicKeyOID(str2);
        }
        AlgorithmParameterSpec algorithmParameterSpec = null;
        try {
            if ("DH".equals(keyAgreementAlgorithm)) {
                algorithmParameterSpec = ((DHPublicKey) publicKey).getParams();
            } else if ("ECDH".equals(keyAgreementAlgorithm)) {
                algorithmParameterSpec = ((ECPublicKey) publicKey).getParams();
            }
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAgreementAlgorithm, BC_PROVIDER);
            keyPairGenerator.initialize(algorithmParameterSpec);
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey2 = generateKeyPair.getPublic();
            PrivateKey privateKey = generateKeyPair.getPrivate();
            sendPublicKey(this.service, this.wrapper, str, bigInteger, publicKey2);
            byte[] keyHash = getKeyHash(keyAgreementAlgorithm, publicKey2);
            SecureMessagingWrapper restartSecureMessaging = restartSecureMessaging(str, computeSharedSecret(keyAgreementAlgorithm, publicKey, privateKey), this.maxTranceiveLength, this.shouldCheckMAC);
            this.wrapper = restartSecureMessaging;
            return new EACCAResult(bigInteger, publicKey, keyHash, publicKey2, privateKey, restartSecureMessaging);
        } catch (GeneralSecurityException e) {
            throw new CardServiceException("Security exception during Chip Authentication", e);
        }
    }

    public static void sendPublicKey(APDULevelEACCACapable aPDULevelEACCACapable, SecureMessagingWrapper secureMessagingWrapper, String str, BigInteger bigInteger, PublicKey publicKey) throws CardServiceException {
        String keyAgreementAlgorithm = ChipAuthenticationInfo.toKeyAgreementAlgorithm(str);
        String cipherAlgorithm = ChipAuthenticationInfo.toCipherAlgorithm(str);
        byte[] keyData = getKeyData(keyAgreementAlgorithm, publicKey);
        if (cipherAlgorithm.startsWith("DESede")) {
            aPDULevelEACCACapable.sendMSEKAT(secureMessagingWrapper, TLVUtil.wrapDO(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, keyData), bigInteger != null ? TLVUtil.wrapDO(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, Util.i2os(bigInteger)) : null);
            return;
        }
        if (cipherAlgorithm.startsWith("AES")) {
            aPDULevelEACCACapable.sendMSESetATIntAuth(secureMessagingWrapper, str, bigInteger);
            byte[] wrapDO = TLVUtil.wrapDO(128, keyData);
            try {
                aPDULevelEACCACapable.sendGeneralAuthenticate(secureMessagingWrapper, wrapDO, true);
                return;
            } catch (CardServiceException e) {
                LOGGER.log(Level.WARNING, "Failed to send GENERAL AUTHENTICATE, falling back to command chaining", (Throwable) e);
                List<byte[]> partition = Util.partition(224, wrapDO);
                Iterator<byte[]> it = partition.iterator();
                int i = 0;
                while (it.hasNext()) {
                    i++;
                    aPDULevelEACCACapable.sendGeneralAuthenticate(secureMessagingWrapper, it.next(), i >= partition.size());
                }
                return;
            }
        }
        throw new IllegalStateException("Cannot set up secure channel with cipher " + cipherAlgorithm);
    }

    public static byte[] computeSharedSecret(String str, PublicKey publicKey, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyAgreement keyAgreement = KeyAgreement.getInstance(str, BC_PROVIDER);
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret();
    }

    public static SecureMessagingWrapper restartSecureMessaging(String str, byte[] bArr, int i, boolean z) throws GeneralSecurityException {
        String cipherAlgorithm = ChipAuthenticationInfo.toCipherAlgorithm(str);
        int keyLength = ChipAuthenticationInfo.toKeyLength(str);
        SecretKey deriveKey = Util.deriveKey(bArr, cipherAlgorithm, keyLength, 1);
        SecretKey deriveKey2 = Util.deriveKey(bArr, cipherAlgorithm, keyLength, 2);
        if (cipherAlgorithm.startsWith("DESede")) {
            return new DESedeSecureMessagingWrapper(deriveKey, deriveKey2, i, z, 0L);
        }
        if (cipherAlgorithm.startsWith("AES")) {
            return new AESSecureMessagingWrapper(deriveKey, deriveKey2, i, z, 0L);
        }
        throw new IllegalStateException("Unsupported cipher algorithm " + cipherAlgorithm);
    }

    public SecureMessagingWrapper getWrapper() {
        return this.wrapper;
    }

    public static byte[] getKeyHash(String str, PublicKey publicKey) throws NoSuchAlgorithmException {
        if ("DH".equals(str)) {
            return MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(getKeyData(str, publicKey));
        }
        if ("ECDH".equals(str)) {
            return Util.alignKeyDataToSize(Util.i2os(((org.bouncycastle.jce.interfaces.ECPublicKey) publicKey).getQ().getAffineXCoord().toBigInteger()), (int) Math.ceil(r5.getParameters().getCurve().getFieldSize() / 8.0d));
        }
        throw new IllegalArgumentException("Unsupported agreement algorithm " + str);
    }

    private static byte[] getKeyData(String str, PublicKey publicKey) {
        if ("DH".equals(str)) {
            return Util.i2os(((DHPublicKey) publicKey).getY());
        }
        if ("ECDH".equals(str)) {
            return ((org.bouncycastle.jce.interfaces.ECPublicKey) publicKey).getQ().getEncoded(false);
        }
        throw new IllegalArgumentException("Unsupported agreement algorithm " + str);
    }

    private static String inferChipAuthenticationOIDfromPublicKeyOID(String str) {
        if (SecurityInfo.ID_PK_ECDH.equals(str)) {
            LOGGER.warning("Could not determine ChipAuthentication algorithm, defaulting to id-CA-ECDH-3DES-CBC-CBC");
            return SecurityInfo.ID_CA_ECDH_3DES_CBC_CBC;
        }
        if (SecurityInfo.ID_PK_DH.equals(str)) {
            LOGGER.warning("Could not determine ChipAuthentication algorithm, defaulting to id-CA-DH-3DES-CBC-CBC");
            return SecurityInfo.ID_CA_DH_3DES_CBC_CBC;
        }
        LOGGER.warning("No ChipAuthenticationInfo and unsupported ChipAuthenticationPublicKeyInfo public key OID " + str);
        return null;
    }
}
