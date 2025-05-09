package org.ejbca.cvc;

/* loaded from: classes6.dex */
public enum AccessRightSignTermEnum implements AccessRights {
    ACCESS_NONE(0),
    ACCESS_SIGN(1),
    ACCESS_QUALSIGN(2),
    ACCESS_SIGN_AND_QUALSIGN(3);

    private byte value;

    AccessRightSignTermEnum(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public boolean allowsSignature() {
        return (this.value & ACCESS_SIGN.value) != 0;
    }

    public boolean allowsQualifiedSignature() {
        return (this.value & ACCESS_QUALSIGN.value) != 0;
    }

    @Override // org.ejbca.cvc.AccessRights
    public byte[] getEncoded() {
        return new byte[]{this.value};
    }

    /* renamed from: org.ejbca.cvc.AccessRightSignTermEnum$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$AccessRightSignTermEnum;

        static {
            int[] iArr = new int[AccessRightSignTermEnum.values().length];
            $SwitchMap$org$ejbca$cvc$AccessRightSignTermEnum = iArr;
            try {
                iArr[AccessRightSignTermEnum.ACCESS_SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightSignTermEnum[AccessRightSignTermEnum.ACCESS_QUALSIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightSignTermEnum[AccessRightSignTermEnum.ACCESS_SIGN_AND_QUALSIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightSignTermEnum[AccessRightSignTermEnum.ACCESS_NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$AccessRightSignTermEnum[ordinal()];
        if (i == 1) {
            return "Signature";
        }
        if (i == 2) {
            return "Qualified_Signature";
        }
        if (i == 3) {
            return "Signature_and_Qualified_Signature";
        }
        if (i == 4) {
            return "none";
        }
        throw new IllegalStateException("Enum case not handled");
    }
}
