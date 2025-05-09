package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class HpkePrimitiveFactory {
    static HpkeKem createKem(byte[] kemId) throws GeneralSecurityException {
        if (Arrays.equals(kemId, HpkeUtil.X25519_HKDF_SHA256_KEM_ID)) {
            return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
        }
        if (Arrays.equals(kemId, HpkeUtil.P256_HKDF_SHA256_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P256);
        }
        if (Arrays.equals(kemId, HpkeUtil.P384_HKDF_SHA384_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P384);
        }
        if (Arrays.equals(kemId, HpkeUtil.P521_HKDF_SHA512_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P521);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HpkeKem createKem(HpkeParams params) throws GeneralSecurityException {
        if (params.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256) {
            return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
        }
        if (params.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P256);
        }
        if (params.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P384);
        }
        if (params.getKem() == com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P521);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }

    static HpkeKdf createKdf(byte[] kdfId) {
        if (Arrays.equals(kdfId, HpkeUtil.HKDF_SHA256_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha256");
        }
        if (Arrays.equals(kdfId, HpkeUtil.HKDF_SHA384_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha384");
        }
        if (Arrays.equals(kdfId, HpkeUtil.HKDF_SHA512_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HpkeKdf createKdf(HpkeParams params) {
        if (params.getKdf() == com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256) {
            return new HkdfHpkeKdf("HmacSha256");
        }
        if (params.getKdf() == com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384) {
            return new HkdfHpkeKdf("HmacSha384");
        }
        if (params.getKdf() == com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512) {
            return new HkdfHpkeKdf("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }

    static HpkeAead createAead(byte[] aeadId) throws GeneralSecurityException {
        if (Arrays.equals(aeadId, HpkeUtil.AES_128_GCM_AEAD_ID)) {
            return new AesGcmHpkeAead(16);
        }
        if (Arrays.equals(aeadId, HpkeUtil.AES_256_GCM_AEAD_ID)) {
            return new AesGcmHpkeAead(32);
        }
        if (Arrays.equals(aeadId, HpkeUtil.CHACHA20_POLY1305_AEAD_ID)) {
            return new ChaCha20Poly1305HpkeAead();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HpkeAead createAead(HpkeParams params) throws GeneralSecurityException {
        if (params.getAead() == com.google.crypto.tink.proto.HpkeAead.AES_128_GCM) {
            return new AesGcmHpkeAead(16);
        }
        if (params.getAead() == com.google.crypto.tink.proto.HpkeAead.AES_256_GCM) {
            return new AesGcmHpkeAead(32);
        }
        if (params.getAead() == com.google.crypto.tink.proto.HpkeAead.CHACHA20_POLY1305) {
            return new ChaCha20Poly1305HpkeAead();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    private HpkePrimitiveFactory() {
    }
}
