package org.ejbca.cvc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes6.dex */
public class CardVerifiableCertificate extends Certificate {
    private CVCertificate cvc;

    public CardVerifiableCertificate(CVCertificate cVCertificate) {
        super("CVC");
        this.cvc = cVCertificate;
    }

    public CVCertificate getCVCertificate() {
        return this.cvc;
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.cvc.getDEREncoded();
        } catch (IOException e) {
            throw new CertificateEncodingException(e);
        }
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        try {
            return this.cvc.getCertificateBody().getPublicKey();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        verify(publicKey, BouncyCastleProvider.PROVIDER_NAME);
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.cvc.verify(publicKey, str);
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        return this.cvc.toString();
    }
}
