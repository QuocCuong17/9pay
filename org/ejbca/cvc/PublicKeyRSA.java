package org.ejbca.cvc;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes6.dex */
public class PublicKeyRSA extends CVCPublicKey implements RSAPublicKey {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.OID, CVCTagEnum.MODULUS, CVCTagEnum.EXPONENT};
    private static final long serialVersionUID = 1;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "RSA";
    }

    @Override // java.security.Key
    public String getFormat() {
        return "CVC";
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKeyRSA(GenericPublicKeyField genericPublicKeyField) throws ConstructionException, NoSuchFieldException {
        ByteField byteField = (ByteField) genericPublicKeyField.getSubfield(CVCTagEnum.MODULUS);
        byteField.setShowBitLength(true);
        addSubfield(genericPublicKeyField.getSubfield(CVCTagEnum.OID));
        addSubfield(byteField);
        addSubfield(genericPublicKeyField.getSubfield(CVCTagEnum.EXPONENT));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicKeyRSA(OIDField oIDField, RSAPublicKey rSAPublicKey) throws ConstructionException {
        addSubfield(oIDField);
        addSubfield(new ByteField(CVCTagEnum.MODULUS, trimByteArray(rSAPublicKey.getModulus().toByteArray()), true));
        addSubfield(new ByteField(CVCTagEnum.EXPONENT, trimByteArray(rSAPublicKey.getPublicExponent().toByteArray())));
    }

    @Override // java.security.interfaces.RSAPublicKey
    public BigInteger getPublicExponent() {
        try {
            return new BigInteger(1, ((ByteField) getSubfield(CVCTagEnum.EXPONENT)).getData());
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // java.security.interfaces.RSAKey
    public BigInteger getModulus() {
        try {
            return new BigInteger(1, ((ByteField) getSubfield(CVCTagEnum.MODULUS)).getData());
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }
}
