package org.ejbca.cvc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.util.BCECUtil;

/* loaded from: classes6.dex */
public class CVCertificate extends AbstractSequence implements Signable {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.CERTIFICATE_BODY, CVCTagEnum.SIGNATURE};
    private static final long serialVersionUID = 1;

    @Override // org.ejbca.cvc.AbstractSequence
    protected CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CVCertificate() {
        super(CVCTagEnum.CV_CERTIFICATE);
    }

    public CVCertificate(CVCertificateBody cVCertificateBody) throws ConstructionException {
        this();
        if (cVCertificateBody == null) {
            throw new IllegalArgumentException("body is null");
        }
        addSubfield(cVCertificateBody);
    }

    public void setSignature(byte[] bArr) throws ConstructionException {
        addSubfield(new ByteField(CVCTagEnum.SIGNATURE, bArr));
    }

    public CVCertificateBody getCertificateBody() throws NoSuchFieldException {
        return (CVCertificateBody) getSubfield(CVCTagEnum.CERTIFICATE_BODY);
    }

    public byte[] getSignature() throws NoSuchFieldException {
        return ((ByteField) getSubfield(CVCTagEnum.SIGNATURE)).getData();
    }

    @Override // org.ejbca.cvc.Signable
    public byte[] getTBS() throws ConstructionException {
        try {
            return getCertificateBody().getDEREncoded();
        } catch (IOException e) {
            throw new ConstructionException(e);
        } catch (NoSuchFieldException e2) {
            throw new ConstructionException(e2);
        }
    }

    public String toString() {
        return getAsText("");
    }

    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        try {
            String algorithmName = AlgorithmUtil.getAlgorithmName(getCertificateBody().getPublicKey().getObjectIdentifier());
            Signature signature = Signature.getInstance(algorithmName, str);
            signature.initVerify(publicKey);
            signature.update(getTBS());
            if (signature.verify(BCECUtil.convertCVCSigToX962(algorithmName, getSignature()))) {
            } else {
                throw new SignatureException("Signature verification failed!");
            }
        } catch (NoSuchFieldException e) {
            throw new CertificateException("CV-Certificate is corrupt", e);
        } catch (ConstructionException e2) {
            throw new CertificateException("CV-Certificate is corrupt", e2);
        }
    }
}
