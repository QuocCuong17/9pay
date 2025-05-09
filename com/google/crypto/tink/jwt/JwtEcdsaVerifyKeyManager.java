package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.jwt.JwtFormat;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Validators;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Optional;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class JwtEcdsaVerifyKeyManager extends KeyTypeManager<JwtEcdsaPublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;

        static {
            int[] iArr = new int[JwtEcdsaAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = iArr;
            try {
                iArr[JwtEcdsaAlgorithm.ES256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final EllipticCurves.CurveType getCurve(JwtEcdsaAlgorithm algorithm) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[algorithm.ordinal()];
        if (i == 1) {
            return EllipticCurves.CurveType.NIST_P256;
        }
        if (i == 2) {
            return EllipticCurves.CurveType.NIST_P384;
        }
        if (i == 3) {
            return EllipticCurves.CurveType.NIST_P521;
        }
        throw new GeneralSecurityException("unknown algorithm " + algorithm.name());
    }

    public static Enums.HashType hashForEcdsaAlgorithm(JwtEcdsaAlgorithm algorithm) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[algorithm.ordinal()];
        if (i == 1) {
            return Enums.HashType.SHA256;
        }
        if (i == 2) {
            return Enums.HashType.SHA384;
        }
        if (i == 3) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("unknown algorithm " + algorithm.name());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void validateEcdsaAlgorithm(JwtEcdsaAlgorithm algorithm) throws GeneralSecurityException {
        hashForEcdsaAlgorithm(algorithm);
    }

    /* loaded from: classes4.dex */
    private static class JwtPublicKeyVerifyFactory extends PrimitiveFactory<JwtPublicKeyVerifyInternal, JwtEcdsaPublicKey> {
        public JwtPublicKeyVerifyFactory() {
            super(JwtPublicKeyVerifyInternal.class);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeyVerifyInternal getPrimitive(JwtEcdsaPublicKey keyProto) throws GeneralSecurityException {
            final Optional empty;
            final EcdsaVerifyJce ecdsaVerifyJce = new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(keyProto.getAlgorithm()), keyProto.getX().toByteArray(), keyProto.getY().toByteArray()), JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(keyProto.getAlgorithm()), EllipticCurves.EcdsaEncoding.IEEE_P1363);
            final String name = keyProto.getAlgorithm().name();
            if (keyProto.hasCustomKid()) {
                empty = Optional.of(keyProto.getCustomKid().getValue());
            } else {
                empty = Optional.empty();
            }
            return new JwtPublicKeyVerifyInternal() { // from class: com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.JwtPublicKeyVerifyFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                public VerifiedJwt verifyAndDecodeWithKid(String compact, JwtValidator validator, Optional<String> kid) throws GeneralSecurityException {
                    JwtFormat.Parts splitSignedCompact = JwtFormat.splitSignedCompact(compact);
                    ecdsaVerifyJce.verify(splitSignedCompact.signatureOrMac, splitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                    JsonObject parseJson = JsonUtil.parseJson(splitSignedCompact.header);
                    JwtFormat.validateHeader(name, kid, empty, parseJson);
                    return validator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parseJson), splitSignedCompact.payload));
                }
            };
        }
    }

    public JwtEcdsaVerifyKeyManager() {
        super(JwtEcdsaPublicKey.class, new JwtPublicKeyVerifyFactory());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtEcdsaPublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtEcdsaPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtEcdsaPublicKey pubKey) throws GeneralSecurityException {
        Validators.validateVersion(pubKey.getVersion(), getVersion());
        validateEcdsaAlgorithm(pubKey.getAlgorithm());
    }
}
