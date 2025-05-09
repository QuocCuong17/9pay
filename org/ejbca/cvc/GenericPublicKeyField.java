package org.ejbca.cvc;

/* loaded from: classes6.dex */
public class GenericPublicKeyField extends AbstractSequence {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.OID, CVCTagEnum.MODULUS, CVCTagEnum.EXPONENT, CVCTagEnum.COEFFICIENT_A, CVCTagEnum.COEFFICIENT_B, CVCTagEnum.BASE_POINT_G, CVCTagEnum.BASE_POINT_R_ORDER, CVCTagEnum.PUBLIC_POINT_Y, CVCTagEnum.COFACTOR_F};
    private static final long serialVersionUID = 1;

    @Override // org.ejbca.cvc.AbstractSequence
    protected CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericPublicKeyField() {
        super(CVCTagEnum.PUBLIC_KEY);
    }
}
