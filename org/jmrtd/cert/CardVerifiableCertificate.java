package org.jmrtd.cert;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.data.Country;
import org.ejbca.cvc.AlgorithmUtil;
import org.ejbca.cvc.AuthorizationRoleEnum;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CVCPublicKey;
import org.ejbca.cvc.CVCertificate;
import org.ejbca.cvc.CVCertificateBody;
import org.ejbca.cvc.HolderReferenceField;
import org.ejbca.cvc.exception.ConstructionException;
import org.jmrtd.cert.CVCAuthorizationTemplate;

/* loaded from: classes6.dex */
public class CardVerifiableCertificate extends Certificate {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -3585440601605666288L;
    private CVCertificate cvCertificate;
    private transient KeyFactory rsaKeyFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public CardVerifiableCertificate(CVCertificate cVCertificate) {
        super("CVC");
        try {
            this.rsaKeyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
        }
        this.cvCertificate = cVCertificate;
    }

    public CardVerifiableCertificate(CVCPrincipal cVCPrincipal, CVCPrincipal cVCPrincipal2, PublicKey publicKey, String str, Date date, Date date2, CVCAuthorizationTemplate.Role role, CVCAuthorizationTemplate.Permission permission, byte[] bArr) {
        this(null);
        try {
            CAReferenceField cAReferenceField = new CAReferenceField(cVCPrincipal.getCountry().toAlpha2Code(), cVCPrincipal.getMnemonic(), cVCPrincipal.getSeqNumber());
            HolderReferenceField holderReferenceField = new HolderReferenceField(cVCPrincipal2.getCountry().toAlpha2Code(), cVCPrincipal2.getMnemonic(), cVCPrincipal2.getSeqNumber());
            AuthorizationRoleEnum fromRole = CVCAuthorizationTemplate.fromRole(role);
            CVCertificate cVCertificate = new CVCertificate(new CVCertificateBody(cAReferenceField, org.ejbca.cvc.KeyFactory.createInstance(publicKey, str, fromRole), holderReferenceField, fromRole, CVCAuthorizationTemplate.fromPermission(permission), date, date2));
            this.cvCertificate = cVCertificate;
            cVCertificate.setSignature(bArr);
            this.cvCertificate.getTBS();
        } catch (ConstructionException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getSigAlgName() {
        try {
            return AlgorithmUtil.getAlgorithmName(this.cvCertificate.getCertificateBody().getPublicKey().getObjectIdentifier());
        } catch (NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, "No such field", (Throwable) e);
            return null;
        }
    }

    public String getSigAlgOID() {
        try {
            return this.cvCertificate.getCertificateBody().getPublicKey().getObjectIdentifier().getAsText();
        } catch (NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, "No such field", (Throwable) e);
            return null;
        }
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.cvCertificate.getDEREncoded();
        } catch (IOException e) {
            throw new CertificateEncodingException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        try {
            CVCPublicKey publicKey = this.cvCertificate.getCertificateBody().getPublicKey();
            if ("RSA".equals(publicKey.getAlgorithm())) {
                RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
                try {
                    return this.rsaKeyFactory.generatePublic(new RSAPublicKeySpec(rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent()));
                } catch (GeneralSecurityException e) {
                    LOGGER.log(Level.WARNING, "Exception", (Throwable) e);
                }
            }
            return publicKey;
        } catch (NoSuchFieldException e2) {
            LOGGER.log(Level.WARNING, "No such field", (Throwable) e2);
            return null;
        }
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        return this.cvCertificate.toString();
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            try {
                this.cvCertificate.verify(publicKey, providers[i].getName());
                z = true;
                break;
            } catch (NoSuchAlgorithmException e) {
                LOGGER.log(Level.FINE, "Trying next provider", (Throwable) e);
                i++;
            }
        }
        if (!z) {
            throw new NoSuchAlgorithmException("Tried all security providers: None was able to provide this signature algorithm.");
        }
    }

    @Override // java.security.cert.Certificate
    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.cvCertificate.verify(publicKey, str);
    }

    public byte[] getCertBodyData() throws CertificateException, IOException {
        try {
            return this.cvCertificate.getCertificateBody().getDEREncoded();
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    public Date getNotBefore() throws CertificateException {
        try {
            return this.cvCertificate.getCertificateBody().getValidFrom();
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    public Date getNotAfter() throws CertificateException {
        try {
            return this.cvCertificate.getCertificateBody().getValidTo();
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    public CVCPrincipal getAuthorityReference() throws CertificateException {
        try {
            CAReferenceField authorityReference = this.cvCertificate.getCertificateBody().getAuthorityReference();
            return new CVCPrincipal(Country.getInstance(authorityReference.getCountry().toUpperCase()), authorityReference.getMnemonic(), authorityReference.getSequence());
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    public CVCPrincipal getHolderReference() throws CertificateException {
        try {
            HolderReferenceField holderReference = this.cvCertificate.getCertificateBody().getHolderReference();
            return new CVCPrincipal(Country.getInstance(holderReference.getCountry().toUpperCase()), holderReference.getMnemonic(), holderReference.getSequence());
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    public CVCAuthorizationTemplate getAuthorizationTemplate() throws CertificateException {
        try {
            return new CVCAuthorizationTemplate(this.cvCertificate.getCertificateBody().getAuthorizationTemplate());
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    public byte[] getSignature() throws CertificateException {
        try {
            return this.cvCertificate.getSignature();
        } catch (NoSuchFieldException e) {
            throw new CertificateException("No such field", e);
        }
    }

    @Override // java.security.cert.Certificate
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass().equals(obj.getClass())) {
            return this.cvCertificate.equals(((CardVerifiableCertificate) obj).cvCertificate);
        }
        return false;
    }

    @Override // java.security.cert.Certificate
    public int hashCode() {
        return (this.cvCertificate.hashCode() * 2) - 1030507011;
    }
}
