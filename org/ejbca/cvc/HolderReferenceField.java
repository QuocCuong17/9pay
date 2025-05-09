package org.ejbca.cvc;

/* loaded from: classes6.dex */
public class HolderReferenceField extends ReferenceField {
    private static final long serialVersionUID = 1;

    public HolderReferenceField(String str, String str2, String str3) {
        super(CVCTagEnum.HOLDER_REFERENCE, str, str2, str3);
    }

    public HolderReferenceField(byte[] bArr) {
        super(CVCTagEnum.HOLDER_REFERENCE, bArr);
    }
}
