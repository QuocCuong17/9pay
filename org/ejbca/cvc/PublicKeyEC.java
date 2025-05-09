package org.ejbca.cvc;

import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.jce.ECPointUtil;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes6.dex */
public class PublicKeyEC extends CVCPublicKey implements ECPublicKey {
    public static final byte UNCOMPRESSED_POINT_TAG = 4;
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.OID, CVCTagEnum.MODULUS, CVCTagEnum.COEFFICIENT_A, CVCTagEnum.COEFFICIENT_B, CVCTagEnum.BASE_POINT_G, CVCTagEnum.BASE_POINT_R_ORDER, CVCTagEnum.PUBLIC_POINT_Y, CVCTagEnum.COFACTOR_F};
    static final long serialVersionUID = 1;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ECDSA";
    }

    @Override // java.security.Key
    public String getFormat() {
        return "CVC";
    }

    @Override // org.ejbca.cvc.AbstractSequence
    protected CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    public PublicKeyEC(GenericPublicKeyField genericPublicKeyField) throws ConstructionException, NoSuchFieldException {
        addSubfield(genericPublicKeyField.getSubfield(CVCTagEnum.OID));
        addSubfield(genericPublicKeyField.getOptionalSubfield(CVCTagEnum.MODULUS));
        addSubfield(genericPublicKeyField.getOptionalSubfield(CVCTagEnum.COEFFICIENT_A));
        addSubfield(genericPublicKeyField.getOptionalSubfield(CVCTagEnum.COEFFICIENT_B));
        addSubfield(genericPublicKeyField.getOptionalSubfield(CVCTagEnum.BASE_POINT_G));
        addSubfield(genericPublicKeyField.getOptionalSubfield(CVCTagEnum.BASE_POINT_R_ORDER));
        addSubfield(genericPublicKeyField.getSubfield(CVCTagEnum.PUBLIC_POINT_Y));
        addSubfield(genericPublicKeyField.getOptionalSubfield(CVCTagEnum.COFACTOR_F));
    }

    public PublicKeyEC(OIDField oIDField, ECPublicKey eCPublicKey, AuthorizationRole authorizationRole) throws ConstructionException {
        addSubfield(oIDField);
        ECParameterSpec params = eCPublicKey.getParams();
        boolean z = authorizationRole == null || authorizationRole.isCVCA();
        if (z) {
            ECField field = params.getCurve().getField();
            if (field instanceof ECFieldFp) {
                addSubfield(new ByteField(CVCTagEnum.MODULUS, trimByteArray(((ECFieldFp) field).getP().toByteArray())));
            }
            addSubfield(new ByteField(CVCTagEnum.COEFFICIENT_A, trimByteArray(params.getCurve().getA().toByteArray())));
            addSubfield(new ByteField(CVCTagEnum.COEFFICIENT_B, trimByteArray(params.getCurve().getB().toByteArray())));
            addSubfield(new ByteField(CVCTagEnum.BASE_POINT_G, encodePoint(params.getGenerator(), params.getCurve())));
            addSubfield(new ByteField(CVCTagEnum.BASE_POINT_R_ORDER, trimByteArray(params.getOrder().toByteArray())));
        }
        addSubfield(new ByteField(CVCTagEnum.PUBLIC_POINT_Y, encodePoint(eCPublicKey.getW(), params.getCurve())));
        if (z) {
            addSubfield(new IntegerField(CVCTagEnum.COFACTOR_F, params.getCofactor()));
        }
    }

    public PublicKeyEC(OIDField oIDField, ECPublicKey eCPublicKey, AuthorizationRoleEnum authorizationRoleEnum) throws ConstructionException {
        this(oIDField, eCPublicKey, (AuthorizationRole) authorizationRoleEnum);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003f, code lost:
    
        if (r4.getAuthRole().isCVCA() != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        if (r4 == null) goto L17;
     */
    @Override // org.ejbca.cvc.AbstractSequence
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected List<CVCObject> getEncodableFields() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSubfield(CVCTagEnum.OID));
            ECParameterSpec params = getParams();
            boolean z = false;
            if (params != null) {
                AbstractSequence parent = getParent();
                if (parent != null && parent.getTag() == CVCTagEnum.CERTIFICATE_BODY) {
                    try {
                        CVCObject optionalSubfield = ((CVCertificateBody) parent).getOptionalSubfield(CVCTagEnum.HOLDER_AUTH_TEMPLATE);
                        if (optionalSubfield != null) {
                            AuthorizationField authorizationField = ((CVCAuthorizationTemplate) optionalSubfield).getAuthorizationField();
                            if (authorizationField != null) {
                            }
                        }
                    } catch (NoSuchFieldException unused) {
                    }
                }
                z = true;
            }
            if (z) {
                if (params.getCurve().getField() instanceof ECFieldFp) {
                    arrayList.add(getSubfield(CVCTagEnum.MODULUS));
                }
                arrayList.add(getSubfield(CVCTagEnum.COEFFICIENT_A));
                arrayList.add(getSubfield(CVCTagEnum.COEFFICIENT_B));
                arrayList.add(getSubfield(CVCTagEnum.BASE_POINT_G));
                arrayList.add(getSubfield(CVCTagEnum.BASE_POINT_R_ORDER));
            }
            arrayList.add(getSubfield(CVCTagEnum.PUBLIC_POINT_Y));
            if (z) {
                arrayList.add(getSubfield(CVCTagEnum.COFACTOR_F));
            }
            return arrayList;
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        ByteField byteField = (ByteField) getOptionalSubfield(CVCTagEnum.MODULUS);
        ByteField byteField2 = (ByteField) getOptionalSubfield(CVCTagEnum.COEFFICIENT_A);
        ByteField byteField3 = (ByteField) getOptionalSubfield(CVCTagEnum.COEFFICIENT_B);
        ByteField byteField4 = (ByteField) getOptionalSubfield(CVCTagEnum.BASE_POINT_G);
        ByteField byteField5 = (ByteField) getOptionalSubfield(CVCTagEnum.BASE_POINT_R_ORDER);
        IntegerField integerField = (IntegerField) getOptionalSubfield(CVCTagEnum.COFACTOR_F);
        if (byteField == null) {
            return null;
        }
        EllipticCurve ellipticCurve = new EllipticCurve(new ECFieldFp(new BigInteger(1, byteField.getData())), new BigInteger(1, byteField2.getData()), new BigInteger(1, byteField3.getData()));
        return new ECParameterSpec(ellipticCurve, ECPointUtil.decodePoint(ellipticCurve, byteField4.getData()), new BigInteger(1, byteField5.getData()), integerField.getValue());
    }

    @Override // java.security.interfaces.ECPublicKey
    public ECPoint getW() {
        try {
            return decodePoint(((ByteField) getSubfield(CVCTagEnum.PUBLIC_POINT_Y)).getData());
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException(e);
        }
    }

    public static byte[] encodePoint(ECPoint eCPoint, EllipticCurve ellipticCurve) {
        int length;
        byte[] trimByteArray = trimByteArray(eCPoint.getAffineX().toByteArray());
        byte[] trimByteArray2 = trimByteArray(eCPoint.getAffineY().toByteArray());
        if (ellipticCurve != null) {
            length = (ellipticCurve.getField().getFieldSize() + 7) >> 3;
        } else {
            length = trimByteArray.length > trimByteArray2.length ? trimByteArray.length : trimByteArray2.length;
        }
        int length2 = trimByteArray.length < length ? length - trimByteArray.length : 0;
        int length3 = trimByteArray2.length < length ? length - trimByteArray2.length : 0;
        byte[] bArr = new byte[(length << 1) + 1];
        Arrays.fill(bArr, (byte) 0);
        bArr[0] = 4;
        System.arraycopy(trimByteArray, 0, bArr, length2 + 1, length - length2);
        System.arraycopy(trimByteArray2, 0, bArr, length + 1 + length3, length - length3);
        return bArr;
    }

    public static ECPoint decodePoint(byte[] bArr) {
        if (bArr[0] != 4) {
            throw new IllegalArgumentException("First byte must be 0x4");
        }
        int length = (bArr.length - 1) / 2;
        byte[] bArr2 = new byte[length];
        int length2 = (bArr.length - 1) / 2;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        System.arraycopy(bArr, length + 1, bArr3, 0, length2);
        return new ECPoint(new BigInteger(1, bArr2), new BigInteger(1, bArr3));
    }
}
