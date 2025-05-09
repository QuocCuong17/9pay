package org.ejbca.cvc;

/* loaded from: classes6.dex */
public enum AuthorizationRoleSignTermEnum implements AuthorizationRole {
    CVCA(192),
    DV_AB(128),
    DV_CSP(64),
    SIGNTERM(0);

    private byte value;

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAuthenticationTerminal() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isDomesticDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isForeignDV() {
        return false;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isIS() {
        return false;
    }

    AuthorizationRoleSignTermEnum(int i) {
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
        return this == DV_AB || this == DV_CSP;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isAccreditationBodyDV() {
        return this == DV_AB;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isCertificationServiceProviderDV() {
        return this == DV_CSP;
    }

    @Override // org.ejbca.cvc.AuthorizationRole
    public boolean isSignatureTerminal() {
        return this == SIGNTERM;
    }

    /* renamed from: org.ejbca.cvc.AuthorizationRoleSignTermEnum$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$AuthorizationRoleSignTermEnum;

        static {
            int[] iArr = new int[AuthorizationRoleSignTermEnum.values().length];
            $SwitchMap$org$ejbca$cvc$AuthorizationRoleSignTermEnum = iArr;
            try {
                iArr[AuthorizationRoleSignTermEnum.CVCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleSignTermEnum[AuthorizationRoleSignTermEnum.DV_AB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleSignTermEnum[AuthorizationRoleSignTermEnum.DV_CSP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleSignTermEnum[AuthorizationRoleSignTermEnum.SIGNTERM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$AuthorizationRoleSignTermEnum[ordinal()];
        if (i == 1) {
            return "CVCA";
        }
        if (i == 2) {
            return "DV-Accreditation-Body";
        }
        if (i == 3) {
            return "DV-Certification-Service-Provider";
        }
        if (i == 4) {
            return "Signature-Terminal";
        }
        throw new IllegalStateException("Enum case not handled");
    }
}
