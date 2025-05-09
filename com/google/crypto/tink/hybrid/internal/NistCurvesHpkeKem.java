package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

@Immutable
/* loaded from: classes4.dex */
final class NistCurvesHpkeKem implements HpkeKem {
    private final EllipticCurves.CurveType curve;
    private final HkdfHpkeKdf hkdf;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;

        static {
            int[] iArr = new int[EllipticCurves.CurveType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = iArr;
            try {
                iArr[EllipticCurves.CurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[EllipticCurves.CurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[EllipticCurves.CurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NistCurvesHpkeKem fromCurve(EllipticCurves.CurveType curve) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[curve.ordinal()];
        if (i == 1) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha256"), EllipticCurves.CurveType.NIST_P256);
        }
        if (i == 2) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha384"), EllipticCurves.CurveType.NIST_P384);
        }
        if (i == 3) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha512"), EllipticCurves.CurveType.NIST_P521);
        }
        throw new GeneralSecurityException("invalid curve type: " + curve);
    }

    private NistCurvesHpkeKem(HkdfHpkeKdf hkdf, EllipticCurves.CurveType curve) {
        this.hkdf = hkdf;
        this.curve = curve;
    }

    private byte[] deriveKemSharedSecret(byte[] dhSharedSecret, byte[] senderPublicKey, byte[] recipientPublicKey) throws GeneralSecurityException {
        byte[] concat = Bytes.concat(senderPublicKey, recipientPublicKey);
        byte[] kemSuiteId = HpkeUtil.kemSuiteId(getKemId());
        HkdfHpkeKdf hkdfHpkeKdf = this.hkdf;
        return hkdfHpkeKdf.extractAndExpand(null, dhSharedSecret, "eae_prk", concat, "shared_secret", kemSuiteId, hkdfHpkeKdf.getMacLength());
    }

    HpkeKemEncapOutput encapsulate(byte[] recipientPublicKey, KeyPair senderKeyPair) throws GeneralSecurityException {
        byte[] computeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) senderKeyPair.getPrivate(), EllipticCurves.getEcPublicKey(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, recipientPublicKey));
        byte[] pointEncode = EllipticCurves.pointEncode(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, ((ECPublicKey) senderKeyPair.getPublic()).getW());
        return new HpkeKemEncapOutput(deriveKemSharedSecret(computeSharedSecret, pointEncode, recipientPublicKey), pointEncode);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public HpkeKemEncapOutput encapsulate(byte[] recipientPublicKey) throws GeneralSecurityException {
        return encapsulate(recipientPublicKey, EllipticCurves.generateKeyPair(this.curve));
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] decapsulate(byte[] encapsulatedKey, HpkeKemPrivateKey recipientPrivateKey) throws GeneralSecurityException {
        return deriveKemSharedSecret(EllipticCurves.computeSharedSecret(EllipticCurves.getEcPrivateKey(this.curve, recipientPrivateKey.getSerializedPrivate().toByteArray()), EllipticCurves.getEcPublicKey(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, encapsulatedKey)), encapsulatedKey, recipientPrivateKey.getSerializedPublic().toByteArray());
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] getKemId() throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[this.curve.ordinal()];
        if (i == 1) {
            return HpkeUtil.P256_HKDF_SHA256_KEM_ID;
        }
        if (i == 2) {
            return HpkeUtil.P384_HKDF_SHA384_KEM_ID;
        }
        if (i == 3) {
            return HpkeUtil.P521_HKDF_SHA512_KEM_ID;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
