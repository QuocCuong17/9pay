package org.ejbca.cvc;

import java.io.IOException;
import java.security.PublicKey;

/* loaded from: classes6.dex */
public abstract class CVCPublicKey extends AbstractSequence implements PublicKey {
    private static final long serialVersionUID = 5330644668163139836L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CVCPublicKey() {
        super(CVCTagEnum.PUBLIC_KEY);
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return getDEREncoded();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OIDField getObjectIdentifier() throws NoSuchFieldException {
        return (OIDField) getSubfield(CVCTagEnum.OID);
    }
}
