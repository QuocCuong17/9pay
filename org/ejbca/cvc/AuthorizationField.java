package org.ejbca.cvc;

import com.google.firebase.sessions.settings.RemoteSettings;
import net.sf.scuba.smartcards.ISO7816;
import org.ejbca.cvc.util.StringConverter;

/* loaded from: classes6.dex */
public class AuthorizationField extends AbstractDataField {
    private static final long serialVersionUID = -5478250843535697147L;
    private AccessRights rights;
    private AuthorizationRole role;

    AuthorizationField() {
        super(CVCTagEnum.ROLE_AND_ACCESS_RIGHTS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizationField(AuthorizationRole authorizationRole, AccessRights accessRights) {
        this();
        this.role = authorizationRole;
        this.rights = accessRights;
    }

    AuthorizationField(AuthorizationRoleEnum authorizationRoleEnum, AccessRightEnum accessRightEnum) {
        this((AuthorizationRole) authorizationRoleEnum, (AccessRights) accessRightEnum);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizationField(byte[] bArr) {
        this();
        if (bArr.length < 1) {
            throw new IllegalArgumentException("byte array length must be at least 1");
        }
        this.role = new AuthorizationRoleRawValue(bArr[0]);
        this.rights = new AccessRightsRawValue(bArr);
    }

    @Deprecated
    public AuthorizationRoleEnum getRole() {
        AuthorizationRole authorizationRole = this.role;
        if (!(authorizationRole instanceof AuthorizationRoleEnum)) {
            throw new UnsupportedOperationException("Attempted to use deprecated getRole method with in an AT or ST certificate chain. It handles IS only.");
        }
        return (AuthorizationRoleEnum) authorizationRole;
    }

    public AuthorizationRole getAuthRole() {
        return this.role;
    }

    @Deprecated
    public AccessRightEnum getAccessRight() {
        AccessRights accessRights = this.rights;
        if (!(accessRights instanceof AccessRightEnum)) {
            throw new UnsupportedOperationException("Attempted to use deprecated getAccessRight method with an AT or ST certificate chain. It handles IS only.");
        }
        return (AccessRightEnum) accessRights;
    }

    public AccessRights getAccessRights() {
        return this.rights;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected byte[] getEncoded() {
        byte[] encoded = this.rights.getEncoded();
        encoded[0] = (byte) (encoded[0] | this.role.getValue());
        return encoded;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected String valueAsText() {
        return StringConverter.byteToHex(getEncoded()) + ": " + this.role + RemoteSettings.FORWARD_SLASH_STRING + this.rights;
    }

    private static AuthorizationRole getRoleFromByte(OIDField oIDField, byte b) {
        AuthorizationRole[] values;
        byte b2 = (byte) (b & ISO7816.INS_GET_RESPONSE);
        if (CVCObjectIdentifiers.id_EAC_ePassport.equals(oIDField)) {
            values = AuthorizationRoleEnum.values();
        } else if (CVCObjectIdentifiers.id_EAC_roles_ST.equals(oIDField)) {
            values = AuthorizationRoleSignTermEnum.values();
        } else if (CVCObjectIdentifiers.id_EAC_roles_AT.equals(oIDField)) {
            values = AuthorizationRoleAuthTermEnum.values();
        } else {
            throw new IllegalArgumentException("incorrect or unsupported OID");
        }
        for (AuthorizationRole authorizationRole : values) {
            if (b2 == authorizationRole.getValue()) {
                return authorizationRole;
            }
        }
        return null;
    }

    private static AccessRights getRightsFromBytes(OIDField oIDField, byte[] bArr) {
        int i = 0;
        if (CVCObjectIdentifiers.id_EAC_ePassport.equals(oIDField)) {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("byte array length must be 1, was " + bArr.length);
            }
            byte b = (byte) (bArr[0] & 3);
            AccessRightEnum[] values = AccessRightEnum.values();
            int length = values.length;
            while (i < length) {
                AccessRightEnum accessRightEnum = values[i];
                if (b == accessRightEnum.getValue()) {
                    return accessRightEnum;
                }
                i++;
            }
            return null;
        }
        if (CVCObjectIdentifiers.id_EAC_roles_ST.equals(oIDField)) {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("byte array length must be 1, was " + bArr.length);
            }
            byte b2 = (byte) (bArr[0] & 3);
            AccessRightSignTermEnum[] values2 = AccessRightSignTermEnum.values();
            int length2 = values2.length;
            while (i < length2) {
                AccessRightSignTermEnum accessRightSignTermEnum = values2[i];
                if (b2 == accessRightSignTermEnum.getValue()) {
                    return accessRightSignTermEnum;
                }
                i++;
            }
            return null;
        }
        if (CVCObjectIdentifiers.id_EAC_roles_AT.equals(oIDField)) {
            if (bArr.length != 5) {
                throw new IllegalArgumentException("byte array length must be 5, was " + bArr.length);
            }
            return new AccessRightAuthTerm(bArr);
        }
        throw new IllegalArgumentException("incorrect or unsupported OID");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fixEnumTypes(OIDField oIDField) {
        this.role = getRoleFromByte(oIDField, this.role.getValue());
        this.rights = getRightsFromBytes(oIDField, this.rights.getEncoded());
    }
}
