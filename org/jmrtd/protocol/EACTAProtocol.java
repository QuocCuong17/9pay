package org.jmrtd.protocol;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.util.List;
import javax.crypto.interfaces.DHPublicKey;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.tlv.TLVOutputStream;
import net.sf.scuba.tlv.TLVUtil;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.jmrtd.APDULevelEACTACapable;
import org.jmrtd.Util;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.cert.CVCAuthorizationTemplate;
import org.jmrtd.cert.CVCPrincipal;
import org.jmrtd.cert.CardVerifiableCertificate;
import org.jmrtd.lds.icao.MRZInfo;

/* loaded from: classes6.dex */
public class EACTAProtocol {
    private static final Provider BC_PROVIDER = Util.getBouncyCastleProvider();
    private static final int TAG_CVCERTIFICATE_SIGNATURE = 24375;
    private APDULevelEACTACapable service;
    private SecureMessagingWrapper wrapper;

    public EACTAProtocol(APDULevelEACTACapable aPDULevelEACTACapable, SecureMessagingWrapper secureMessagingWrapper) {
        this.service = aPDULevelEACTACapable;
        this.wrapper = secureMessagingWrapper;
    }

    public synchronized EACTAResult doEACTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, String str2) throws CardServiceException {
        return doTA(cVCPrincipal, list, privateKey, str, eACCAResult, deriveIdentifier(str2));
    }

    public synchronized EACTAResult doTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, PACEResult pACEResult) throws CardServiceException {
        try {
        } catch (NoSuchAlgorithmException e) {
            throw new CardServiceException("No such algorithm", e);
        }
        return doTA(cVCPrincipal, list, privateKey, str, eACCAResult, deriveIdentifier(pACEResult));
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
    
        r8 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009d, code lost:
    
        r2 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized EACTAResult doTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, byte[] bArr) throws CardServiceException {
        CVCPrincipal cVCPrincipal2;
        byte[] sendGetChallenge;
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    if (eACCAResult == null) {
                        throw new IllegalArgumentException("Could not get EAC-CA key hash");
                    }
                    byte[] keyHash = eACCAResult.getKeyHash();
                    if (keyHash == null) {
                        throw new IllegalArgumentException("Could nnot get EAC-CA key hash");
                    }
                    CardVerifiableCertificate cardVerifiableCertificate = list.get(0);
                    if (CVCAuthorizationTemplate.Role.CVCA.equals(cardVerifiableCertificate.getAuthorizationTemplate().getRole())) {
                        CVCPrincipal holderReference = cardVerifiableCertificate.getHolderReference();
                        if (cVCPrincipal != null && !cVCPrincipal.equals(holderReference)) {
                            throw new CardServiceException("First certificate holds wrong authority, found \"" + holderReference.getName() + "\", expected \"" + cVCPrincipal.getName() + "\"");
                        }
                        list.remove(0);
                    }
                    CVCPrincipal authorityReference = cardVerifiableCertificate.getAuthorityReference();
                    if (cVCPrincipal != null && !cVCPrincipal.equals(authorityReference)) {
                        throw new CardServiceException("First certificate not signed by expected CA, found " + authorityReference.getName() + ", expected " + cVCPrincipal.getName());
                    }
                    cVCPrincipal2 = cVCPrincipal;
                    CardVerifiableCertificate cardVerifiableCertificate2 = list.get(list.size() - 1);
                    CVCAuthorizationTemplate.Role role = cardVerifiableCertificate2.getAuthorizationTemplate().getRole();
                    if (!CVCAuthorizationTemplate.Role.IS.equals(role)) {
                        throw new CardServiceException("Last certificate in chain (" + cardVerifiableCertificate2.getHolderReference().getName() + ") does not have role IS, but has role " + role);
                    }
                    for (CardVerifiableCertificate cardVerifiableCertificate3 : list) {
                        try {
                            this.service.sendMSESetDST(this.wrapper, TLVUtil.wrapDO(ISO781611.CREATION_DATE_AND_TIME_TAG, cardVerifiableCertificate3.getAuthorityReference().getName().getBytes("ISO-8859-1")));
                            byte[] certBodyData = cardVerifiableCertificate3.getCertBodyData();
                            byte[] signature = cardVerifiableCertificate3.getSignature();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
                            tLVOutputStream.writeTag(TAG_CVCERTIFICATE_SIGNATURE);
                            tLVOutputStream.writeValue(signature);
                            tLVOutputStream.close();
                            this.service.sendPSOExtendedLengthMode(this.wrapper, certBodyData, byteArrayOutputStream.toByteArray());
                        } catch (CardServiceException e) {
                            throw e;
                        } catch (Exception e2) {
                            throw new CardServiceException("Exception", e2);
                        }
                    }
                    if (privateKey == null) {
                        throw new CardServiceException("No terminal key");
                    }
                    this.service.sendMSESetATExtAuth(this.wrapper, TLVUtil.wrapDO(ISO781611.CREATION_DATE_AND_TIME_TAG, cardVerifiableCertificate2.getHolderReference().getName().getBytes("ISO-8859-1")));
                    sendGetChallenge = this.service.sendGetChallenge(this.wrapper);
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    byteArrayOutputStream2.write(bArr);
                    byteArrayOutputStream2.write(sendGetChallenge);
                    byteArrayOutputStream2.write(keyHash);
                    byteArrayOutputStream2.close();
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    String sigAlgName = cardVerifiableCertificate2.getSigAlgName();
                    if (sigAlgName == null) {
                        throw new IllegalStateException("Could not determine signature algorithm for terminal certificate " + cardVerifiableCertificate2.getHolderReference().getName());
                    }
                    Signature signature2 = Signature.getInstance(sigAlgName, BC_PROVIDER);
                    signature2.initSign(privateKey);
                    signature2.update(byteArray);
                    byte[] sign = signature2.sign();
                    if (sigAlgName.toUpperCase().endsWith("ECDSA")) {
                        sign = Util.getRawECDSASignature(sign, (int) Math.ceil(((ECPrivateKey) privateKey).getParameters().getCurve().getFieldSize() / 8.0d));
                    }
                    this.service.sendMutualAuthenticate(this.wrapper, sign);
                }
            } catch (CardServiceException e3) {
                throw e3;
            } catch (Exception e4) {
                throw new CardServiceException("Exception", e4);
            }
        }
        throw new IllegalArgumentException("Need at least 1 certificate to perform TA, found: " + list);
        return new EACTAResult(eACCAResult, cVCPrincipal2, list, privateKey, null, sendGetChallenge);
    }

    private static byte[] deriveIdentifier(String str) {
        int length = str.length();
        byte[] bArr = new byte[length + 1];
        try {
            System.arraycopy(str.getBytes("ISO-8859-1"), 0, bArr, 0, length);
            bArr[length] = (byte) MRZInfo.checkDigit(str);
            return bArr;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unsupported encoding", e);
        }
    }

    private static byte[] deriveIdentifier(PACEResult pACEResult) throws NoSuchAlgorithmException {
        String agreementAlg = pACEResult.getAgreementAlg();
        PublicKey pICCPublicKey = pACEResult.getPICCPublicKey();
        if ("DH".equals(agreementAlg)) {
            return MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(getKeyData(agreementAlg, pICCPublicKey));
        }
        if ("ECDH".equals(agreementAlg)) {
            return Util.alignKeyDataToSize(Util.i2os(((ECPublicKey) pICCPublicKey).getQ().getAffineXCoord().toBigInteger()), (int) Math.ceil(r5.getParameters().getCurve().getFieldSize() / 8.0d));
        }
        throw new NoSuchAlgorithmException("Unsupported agreement algorithm " + agreementAlg);
    }

    private static byte[] getKeyData(String str, PublicKey publicKey) {
        if ("DH".equals(str)) {
            return Util.i2os(((DHPublicKey) publicKey).getY());
        }
        if ("ECDH".equals(str)) {
            return ((ECPublicKey) publicKey).getQ().getEncoded(false);
        }
        throw new IllegalArgumentException("Unsupported agreement algorithm " + str);
    }
}
