package org.ejbca.cvc;

/* loaded from: classes6.dex */
public enum AccessRightEnum implements AccessRights {
    READ_ACCESS_NONE(0),
    READ_ACCESS_DG3(1),
    READ_ACCESS_DG4(2),
    READ_ACCESS_DG3_AND_DG4(3);

    private byte value;

    AccessRightEnum(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return this.value;
    }

    public boolean hasDG3() {
        return (this.value & READ_ACCESS_DG3.value) != 0;
    }

    public boolean hasDG4() {
        return (this.value & READ_ACCESS_DG4.value) != 0;
    }

    @Override // org.ejbca.cvc.AccessRights
    public byte[] getEncoded() {
        return new byte[]{this.value};
    }

    /* renamed from: org.ejbca.cvc.AccessRightEnum$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$AccessRightEnum;

        static {
            int[] iArr = new int[AccessRightEnum.values().length];
            $SwitchMap$org$ejbca$cvc$AccessRightEnum = iArr;
            try {
                iArr[AccessRightEnum.READ_ACCESS_DG3_AND_DG4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightEnum[AccessRightEnum.READ_ACCESS_DG4.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightEnum[AccessRightEnum.READ_ACCESS_DG3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$AccessRightEnum[AccessRightEnum.READ_ACCESS_NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$AccessRightEnum[ordinal()];
        if (i == 1) {
            return "DG3+DG4";
        }
        if (i == 2) {
            return "DG4";
        }
        if (i == 3) {
            return "DG3";
        }
        if (i == 4) {
            return "none";
        }
        throw new IllegalStateException("Enum case not handled");
    }
}
