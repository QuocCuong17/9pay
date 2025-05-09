package org.jmrtd.cert;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Date;
import org.ejbca.cvc.AccessRightEnum;
import org.ejbca.cvc.AuthorizationRoleEnum;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CertificateGenerator;
import org.ejbca.cvc.HolderReferenceField;
import org.ejbca.cvc.exception.ConstructionException;
import org.jmrtd.cert.CVCAuthorizationTemplate;

/* loaded from: classes6.dex */
public class CVCertificateBuilder {
    private CVCertificateBuilder() {
    }

    public static CardVerifiableCertificate createCertificate(PublicKey publicKey, PrivateKey privateKey, String str, CVCPrincipal cVCPrincipal, CVCPrincipal cVCPrincipal2, CVCAuthorizationTemplate cVCAuthorizationTemplate, Date date, Date date2, String str2) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, ConstructionException {
        return new CardVerifiableCertificate(CertificateGenerator.createCertificate(publicKey, privateKey, str, new CAReferenceField(cVCPrincipal.getCountry().toAlpha2Code(), cVCPrincipal.getMnemonic(), cVCPrincipal.getSeqNumber()), new HolderReferenceField(cVCPrincipal2.getCountry().toAlpha2Code(), cVCPrincipal2.getMnemonic(), cVCPrincipal2.getSeqNumber()), getRole(cVCAuthorizationTemplate.getRole()), getAccessRight(cVCAuthorizationTemplate.getAccessRight()), date, date2, str2));
    }

    private static AuthorizationRoleEnum getRole(CVCAuthorizationTemplate.Role role) {
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
        throw new NumberFormatException("Cannot decode role " + role);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jmrtd.cert.CVCertificateBuilder$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission;
        static final /* synthetic */ int[] $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role;

        static {
            int[] iArr = new int[CVCAuthorizationTemplate.Permission.values().length];
            $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission = iArr;
            try {
                iArr[CVCAuthorizationTemplate.Permission.READ_ACCESS_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[CVCAuthorizationTemplate.Permission.READ_ACCESS_DG3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[CVCAuthorizationTemplate.Permission.READ_ACCESS_DG4.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Permission[CVCAuthorizationTemplate.Permission.READ_ACCESS_DG3_AND_DG4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[CVCAuthorizationTemplate.Role.values().length];
            $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role = iArr2;
            try {
                iArr2[CVCAuthorizationTemplate.Role.CVCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[CVCAuthorizationTemplate.Role.DV_D.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[CVCAuthorizationTemplate.Role.DV_F.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$jmrtd$cert$CVCAuthorizationTemplate$Role[CVCAuthorizationTemplate.Role.IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private static AccessRightEnum getAccessRight(CVCAuthorizationTemplate.Permission permission) {
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
        throw new NumberFormatException("Cannot decode access right " + permission);
    }
}
