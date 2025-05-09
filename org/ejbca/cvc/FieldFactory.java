package org.ejbca.cvc;

import java.io.IOException;

/* loaded from: classes6.dex */
public class FieldFactory {
    public static AbstractDataField decodeField(CVCTagEnum cVCTagEnum, byte[] bArr) throws IOException {
        if (cVCTagEnum.isSequence()) {
            throw new IllegalArgumentException("Tag " + cVCTagEnum + " is a sequence");
        }
        switch (AnonymousClass1.$SwitchMap$org$ejbca$cvc$CVCTagEnum[cVCTagEnum.ordinal()]) {
            case 1:
                return new DateField(cVCTagEnum, bArr);
            case 2:
                return new DateField(cVCTagEnum, bArr);
            case 3:
                return new CAReferenceField(bArr);
            case 4:
                return new HolderReferenceField(bArr);
            case 5:
                return new OIDField(bArr);
            case 6:
                return new AuthorizationField(bArr);
            case 7:
                return new IntegerField(cVCTagEnum, bArr);
            case 8:
                return new IntegerField(cVCTagEnum, bArr);
            default:
                return new ByteField(cVCTagEnum, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.ejbca.cvc.FieldFactory$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$CVCTagEnum;

        static {
            int[] iArr = new int[CVCTagEnum.values().length];
            $SwitchMap$org$ejbca$cvc$CVCTagEnum = iArr;
            try {
                iArr[CVCTagEnum.EFFECTIVE_DATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.EXPIRATION_DATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.CA_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.HOLDER_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.OID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.ROLE_AND_ACCESS_RIGHTS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.PROFILE_IDENTIFIER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.COFACTOR_F.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }
}
