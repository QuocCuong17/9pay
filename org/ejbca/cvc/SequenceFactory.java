package org.ejbca.cvc;

/* loaded from: classes6.dex */
public class SequenceFactory {
    public static AbstractSequence createSequence(CVCTagEnum cVCTagEnum) {
        if (!cVCTagEnum.isSequence()) {
            throw new IllegalArgumentException("Tag " + cVCTagEnum + " is not a sequence");
        }
        int i = AnonymousClass1.$SwitchMap$org$ejbca$cvc$CVCTagEnum[cVCTagEnum.ordinal()];
        if (i == 1) {
            return new CVCertificate();
        }
        if (i == 2) {
            return new CVCertificateBody();
        }
        if (i == 3) {
            return new GenericPublicKeyField();
        }
        if (i == 4) {
            return new CVCAuthorizationTemplate();
        }
        if (i == 5) {
            return new CVCAuthenticatedRequest();
        }
        throw new IllegalArgumentException("Unsupported type " + cVCTagEnum);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.ejbca.cvc.SequenceFactory$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$ejbca$cvc$CVCTagEnum;

        static {
            int[] iArr = new int[CVCTagEnum.values().length];
            $SwitchMap$org$ejbca$cvc$CVCTagEnum = iArr;
            try {
                iArr[CVCTagEnum.CV_CERTIFICATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.CERTIFICATE_BODY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.PUBLIC_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.HOLDER_AUTH_TEMPLATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$ejbca$cvc$CVCTagEnum[CVCTagEnum.REQ_AUTHENTICATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
