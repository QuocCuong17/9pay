package org.jmrtd.cert;

import org.ejbca.cvc.AccessRightEnum;
import org.ejbca.cvc.AuthorizationRoleEnum;

/* loaded from: classes6.dex */
public class CVCAuthorizationTemplate {
    private Permission accessRight;
    private Role role;

    /* loaded from: classes6.dex */
    public enum Role {
        CVCA(192),
        DV_D(128),
        DV_F(64),
        IS(0);

        private byte value;

        Role(int i) {
            this.value = (byte) i;
        }

        public byte getValue() {
            return this.value;
        }
    }

    /* loaded from: classes6.dex */
    public enum Permission {
        READ_ACCESS_NONE(0),
        READ_ACCESS_DG3(1),
        READ_ACCESS_DG4(2),
        READ_ACCESS_DG3_AND_DG4(3);

        private byte value;

        Permission(int i) {
            this.value = (byte) i;
        }

        public boolean implies(Permission permission) {
            int i = AnonymousClass1.$SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[ordinal()];
            if (i == 1) {
                return permission == READ_ACCESS_NONE;
            }
            if (i == 2) {
                return permission == READ_ACCESS_DG3;
            }
            if (i == 3) {
                return permission == READ_ACCESS_DG4;
            }
            if (i != 4) {
                return false;
            }
            return permission == READ_ACCESS_DG3 || permission == READ_ACCESS_DG4 || permission == READ_ACCESS_DG3_AND_DG4;
        }

        public byte getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CVCAuthorizationTemplate(org.ejbca.cvc.CVCAuthorizationTemplate cVCAuthorizationTemplate) {
        this.role = toRole(cVCAuthorizationTemplate);
        this.accessRight = toPermission(cVCAuthorizationTemplate);
    }

    public CVCAuthorizationTemplate(Role role, Permission permission) {
        this.role = role;
        this.accessRight = permission;
    }

    public Role getRole() {
        return this.role;
    }

    public Permission getAccessRight() {
        return this.accessRight;
    }

    public String toString() {
        return this.role.toString() + this.accessRight.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        CVCAuthorizationTemplate cVCAuthorizationTemplate = (CVCAuthorizationTemplate) obj;
        return this.role == cVCAuthorizationTemplate.role && this.accessRight == cVCAuthorizationTemplate.accessRight;
    }

    public int hashCode() {
        return (this.role.value * 2) + (this.accessRight.value * 3) + 61;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessRightEnum fromPermission(Permission permission) {
        try {
            int i = AnonymousClass1.$SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[permission.ordinal()];
            if (i == 1) {
                return AccessRightEnum.READ_ACCESS_NONE;
            }
            if (i == 2) {
                return AccessRightEnum.READ_ACCESS_DG3;
            }
            if (i == 3) {
                return AccessRightEnum.READ_ACCESS_DG4;
            }
            if (i == 4) {
                return AccessRightEnum.READ_ACCESS_DG3_AND_DG4;
            }
            throw new IllegalArgumentException("Error getting permission for " + permission);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error getting permission from AuthZ template", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AuthorizationRoleEnum fromRole(Role role) {
        try {
            int i = AnonymousClass1.$SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[role.ordinal()];
            if (i == 1) {
                return AuthorizationRoleEnum.CVCA;
            }
            if (i == 2) {
                return AuthorizationRoleEnum.DV_D;
            }
            if (i == 3) {
                return AuthorizationRoleEnum.DV_F;
            }
            if (i == 4) {
                return AuthorizationRoleEnum.IS;
            }
            throw new IllegalArgumentException("Error getting role from AuthZ template " + role);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error getting role from AuthZ template", e);
        }
    }

    private static Role toRole(org.ejbca.cvc.CVCAuthorizationTemplate cVCAuthorizationTemplate) {
        try {
            AuthorizationRoleEnum role = cVCAuthorizationTemplate.getAuthorizationField().getRole();
            int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$AuthorizationRoleEnum[role.ordinal()];
            if (i == 1) {
                return Role.CVCA;
            }
            if (i == 2) {
                return Role.DV_D;
            }
            if (i == 3) {
                return Role.DV_F;
            }
            if (i == 4) {
                return Role.IS;
            }
            throw new IllegalArgumentException("Unsupported role " + role);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Error getting role from AuthZ template", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jmrtd.cert.CVCAuthorizationTemplate$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$AccessRightEnum;
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$AuthorizationRoleEnum;
        static final /* synthetic */ int[] $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission;
        static final /* synthetic */ int[] $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role;

        static {
            int[] iArr = new int[AccessRightEnum.values().length];
            $SwitchMap$org$ejbca$cvc$AccessRightEnum = iArr;
            try {
                iArr[AccessRightEnum.READ_ACCESS_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightEnum[AccessRightEnum.READ_ACCESS_DG3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightEnum[AccessRightEnum.READ_ACCESS_DG4.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightEnum[AccessRightEnum.READ_ACCESS_DG3_AND_DG4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AuthorizationRoleEnum.values().length];
            $SwitchMap$org$ejbca$cvc$AuthorizationRoleEnum = iArr2;
            try {
                iArr2[AuthorizationRoleEnum.CVCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleEnum[AuthorizationRoleEnum.DV_D.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleEnum[AuthorizationRoleEnum.DV_F.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AuthorizationRoleEnum[AuthorizationRoleEnum.IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[Role.values().length];
            $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role = iArr3;
            try {
                iArr3[Role.CVCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[Role.DV_D.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[Role.DV_F.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[Role.IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr4 = new int[Permission.values().length];
            $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission = iArr4;
            try {
                iArr4[Permission.READ_ACCESS_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[Permission.READ_ACCESS_DG3.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[Permission.READ_ACCESS_DG4.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[Permission.READ_ACCESS_DG3_AND_DG4.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    private static Permission toPermission(org.ejbca.cvc.CVCAuthorizationTemplate cVCAuthorizationTemplate) {
        try {
            AccessRightEnum accessRight = cVCAuthorizationTemplate.getAuthorizationField().getAccessRight();
            int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$AccessRightEnum[accessRight.ordinal()];
            if (i == 1) {
                return Permission.READ_ACCESS_NONE;
            }
            if (i == 2) {
                return Permission.READ_ACCESS_DG3;
            }
            if (i == 3) {
                return Permission.READ_ACCESS_DG4;
            }
            if (i == 4) {
                return Permission.READ_ACCESS_DG3_AND_DG4;
            }
            throw new IllegalArgumentException("Unsupported access right " + accessRight);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Unsupported access right", e);
        }
    }
}
