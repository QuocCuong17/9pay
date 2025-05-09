package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaKeyFormat;
import com.google.crypto.tink.proto.JwtEcdsaPrivateKey;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* loaded from: classes4.dex */
public final class JwtEcdsaSignKeyManager extends PrivateKeyTypeManager<JwtEcdsaPrivateKey, JwtEcdsaPublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* loaded from: classes4.dex */
    private static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtEcdsaPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        private static final void selfTestKey(ECPrivateKey privateKey, JwtEcdsaPrivateKey keyProto) throws GeneralSecurityException {
            SelfKeyTestValidators.validateEcdsa(privateKey, EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(keyProto.getPublicKey().getAlgorithm()), keyProto.getPublicKey().getX().toByteArray(), keyProto.getPublicKey().getY().toByteArray()), JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(keyProto.getPublicKey().getAlgorithm()), EllipticCurves.EcdsaEncoding.IEEE_P1363);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtEcdsaPrivateKey keyProto) throws GeneralSecurityException {
            final Optional empty;
            ECPrivateKey ecPrivateKey = EllipticCurves.getEcPrivateKey(JwtEcdsaVerifyKeyManager.getCurve(keyProto.getPublicKey().getAlgorithm()), keyProto.getKeyValue().toByteArray());
            selfTestKey(ecPrivateKey, keyProto);
            JwtEcdsaAlgorithm algorithm = keyProto.getPublicKey().getAlgorithm();
            final EcdsaSignJce ecdsaSignJce = new EcdsaSignJce(ecPrivateKey, JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(algorithm), EllipticCurves.EcdsaEncoding.IEEE_P1363);
            final String name = algorithm.name();
            if (keyProto.getPublicKey().hasCustomKid()) {
                empty = Optional.of(keyProto.getPublicKey().getCustomKid().getValue());
            } else {
                empty = Optional.empty();
            }
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtEcdsaSignKeyManager.JwtPublicKeySignFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> kid) throws GeneralSecurityException {
                    if (empty.isPresent()) {
                        if (kid.isPresent()) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        kid = empty;
                    }
                    String createUnsignedCompact = JwtFormat.createUnsignedCompact(name, kid, rawJwt);
                    return JwtFormat.createSignedCompact(createUnsignedCompact, ecdsaSignJce.sign(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JwtEcdsaSignKeyManager() {
        super(JwtEcdsaPrivateKey.class, JwtEcdsaPublicKey.class, new JwtPublicKeySignFactory());
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtEcdsaPublicKey getPublicKey(JwtEcdsaPrivateKey key) {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtEcdsaPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtEcdsaPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtEcdsaPrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(privKey.getPublicKey().getAlgorithm());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey>(JwtEcdsaKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtEcdsaSignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtEcdsaKeyFormat format) throws GeneralSecurityException {
                JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(format.getAlgorithm());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtEcdsaKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaPrivateKey deriveKey(JwtEcdsaKeyFormat format, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaPrivateKey createKey(JwtEcdsaKeyFormat format) throws GeneralSecurityException {
                JwtEcdsaAlgorithm algorithm = format.getAlgorithm();
                KeyPair generateKeyPair = EllipticCurves.generateKeyPair(JwtEcdsaVerifyKeyManager.getCurve(format.getAlgorithm()));
                ECPublicKey eCPublicKey = (ECPublicKey) generateKeyPair.getPublic();
                ECPrivateKey eCPrivateKey = (ECPrivateKey) generateKeyPair.getPrivate();
                ECPoint w = eCPublicKey.getW();
                return (JwtEcdsaPrivateKey) JwtEcdsaPrivateKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setPublicKey((JwtEcdsaPublicKey) JwtEcdsaPublicKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setX(ByteString.copyFrom(w.getAffineX().toByteArray())).setY(ByteString.copyFrom(w.getAffineY().toByteArray())).build()).setKeyValue(ByteString.copyFrom(eCPrivateKey.getS().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("JWT_ES256_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES256, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_ES256", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_ES384_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES384, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_ES384", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES384, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_ES512_RAW", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES512, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_ES512", JwtEcdsaSignKeyManager.createKeyFormat(JwtEcdsaAlgorithm.ES512, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtEcdsaSignKeyManager(), new JwtEcdsaVerifyKeyManager(), newKeyAllowed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat> createKeyFormat(JwtEcdsaAlgorithm algorithm, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtEcdsaKeyFormat) JwtEcdsaKeyFormat.newBuilder().setAlgorithm(algorithm).build(), prefixType);
    }
}
