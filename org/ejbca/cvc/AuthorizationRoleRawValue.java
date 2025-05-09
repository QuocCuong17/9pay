package org.ejbca.cvc;

/* loaded from: classes6.dex */
public class AuthorizationRoleRawValue implements AuthorizationRole {
    private static final String EXCEPTION_MSG = "Authorization Role object does not know its type/OID yet. This is a bug.";
    private final byte value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizationRoleRawValue(byte b) {
        this.value = b;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCVCA() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDomesticDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isForeignDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAccreditationBodyDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCertificationServiceProviderDV() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isIS() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAuthenticationTerminal() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isSignatureTerminal() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public byte getValue() {
        return this.value;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public String name() {
        throw new IllegalStateException(EXCEPTION_MSG);
    }
}
