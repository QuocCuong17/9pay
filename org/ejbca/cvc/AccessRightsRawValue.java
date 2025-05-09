package org.ejbca.cvc;

/* loaded from: classes6.dex */
public class AccessRightsRawValue implements AccessRights {
    private static final String EXCEPTION_MSG = "Access Right object does not know its type/OID yet. This is a bug.";
    private final byte[] bytes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessRightsRawValue(byte[] bArr) {
        this.bytes = bArr;
    }

    @Override // org.ejbca.cvc.AccessRights
    public byte[] getEncoded() {
        return this.bytes;
    }

    @Override // org.ejbca.cvc.AccessRights
    public String name() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }
}
