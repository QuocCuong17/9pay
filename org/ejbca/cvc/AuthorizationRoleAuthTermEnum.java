package org.ejbca.cvc;

/* loaded from: classes6.dex */
public enum AuthorizationRoleAuthTermEnum implements AuthorizationRole {
    CVCA(192),
    DV_D(128),
    DV_F(64),
    AUTHTERM(0);

    private byte value;

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAccreditationBodyDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCertificationServiceProviderDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isIS() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isSignatureTerminal() {
        return false;
    }

    AuthorizationRoleAuthTermEnum(int i) {
        this.value = (byte) i;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public byte getValue() {
        return this.value;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCVCA() {
        return this == CVCA;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDV() {
        return this == DV_D || this == DV_F;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDomesticDV() {
        return this == DV_D;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isForeignDV() {
        return this == DV_F;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAuthenticationTerminal() {
        return this == AUTHTERM;
    }

    /* renamed from: org.ejbca.cvc.AuthorizationRoleAuthTermEnum$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$AuthorizationRoleAuthTermEnum;

        static {
            int[] iArr = new int[AuthorizationRoleAuthTermEnum.values().length];
            $SwitchMap$org$ejbca$cvc$AuthorizationRoleAuthTermEnum = iArr;
            try {
                iArr[AuthorizationRoleAuthTermEnum.CVCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleAuthTermEnum[AuthorizationRoleAuthTermEnum.DV_D.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleAuthTermEnum[AuthorizationRoleAuthTermEnum.DV_F.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleAuthTermEnum[AuthorizationRoleAuthTermEnum.AUTHTERM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$AuthorizationRoleAuthTermEnum[ordinal()];
        if (i == 1) {
            return "CVCA";
        }
        if (i == 2) {
            return "DV-domestic";
        }
        if (i == 3) {
            return "DV-foreign";
        }
        if (i == 4) {
            return "Authentication-Terminal";
        }
        throw new IllegalStateException("Enum case not handled");
    }
}
