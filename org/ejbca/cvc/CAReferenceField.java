package org.ejbca.cvc;

/* loaded from: classes6.dex */
public class CAReferenceField extends ReferenceField {
    private static final long serialVersionUID = 6311109644238677669L;

    public CAReferenceField(String str, String str2, String str3) {
        super(CVCTagEnum.CA_REFERENCE, str, str2, str3);
    }

    public CAReferenceField(byte[] bArr) {
        super(CVCTagEnum.CA_REFERENCE, bArr);
    }
}
