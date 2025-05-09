package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* loaded from: classes4.dex */
public final class JwtRsaSsaPssSignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPssPrivateKey, JwtRsaSsaPssPublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey privateKey, JwtRsaSsaPssPrivateKey keyProto) throws GeneralSecurityException {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray())));
        JwtRsaSsaPssAlgorithm algorithm = keyProto.getPublicKey().getAlgorithm();
        Enums.HashType hashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
        SelfKeyTestValidators.validateRsaSsaPss(privateKey, rSAPublicKey, hashForPssAlgorithm, hashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPssPrivateKey keyProto) throws GeneralSecurityException {
        return (RSAPrivateCrtKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray()), new BigInteger(1, keyProto.getD().toByteArray()), new BigInteger(1, keyProto.getP().toByteArray()), new BigInteger(1, keyProto.getQ().toByteArray()), new BigInteger(1, keyProto.getDp().toByteArray()), new BigInteger(1, keyProto.getDq().toByteArray()), new BigInteger(1, keyProto.getCrt().toByteArray())));
    }

    /* loaded from: classes4.dex */
    private static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPssPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPssPrivateKey keyProto) throws GeneralSecurityException {
            final Optional empty;
            RSAPrivateCrtKey createPrivateKey = JwtRsaSsaPssSignKeyManager.createPrivateKey(keyProto);
            JwtRsaSsaPssSignKeyManager.selfTestKey(createPrivateKey, keyProto);
            JwtRsaSsaPssAlgorithm algorithm = keyProto.getPublicKey().getAlgorithm();
            Enums.HashType hashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
            final RsaSsaPssSignJce rsaSsaPssSignJce = new RsaSsaPssSignJce(createPrivateKey, hashForPssAlgorithm, hashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm));
            final String name = algorithm.name();
            if (keyProto.getPublicKey().hasCustomKid()) {
                empty = Optional.of(keyProto.getPublicKey().getCustomKid().getValue());
            } else {
                empty = Optional.empty();
            }
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssSignKeyManager.JwtPublicKeySignFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> kid) throws GeneralSecurityException {
                    if (empty.isPresent()) {
                        if (kid.isPresent()) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        kid = empty;
                    }
                    String createUnsignedCompact = JwtFormat.createUnsignedCompact(name, kid, rawJwt);
                    return JwtFormat.createSignedCompact(createUnsignedCompact, rsaSsaPssSignJce.sign(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JwtRsaSsaPssSignKeyManager() {
        super(JwtRsaSsaPssPrivateKey.class, JwtRsaSsaPssPublicKey.class, new JwtPublicKeySignFactory());
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtRsaSsaPssPublicKey getPublicKey(JwtRsaSsaPssPrivateKey privKeyProto) {
        return privKeyProto.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPssPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPssPrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, privKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, privKey.getPublicKey().getE().toByteArray()));
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey>(JwtRsaSsaPssKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssSignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtRsaSsaPssKeyFormat keyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(keyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, keyFormat.getPublicExponent().toByteArray()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtRsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssPrivateKey deriveKey(JwtRsaSsaPssKeyFormat format, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssPrivateKey createKey(JwtRsaSsaPssKeyFormat format) throws GeneralSecurityException {
                JwtRsaSsaPssAlgorithm algorithm = format.getAlgorithm();
                KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                engineFactory.initialize(new RSAKeyGenParameterSpec(format.getModulusSizeInBits(), new BigInteger(1, format.getPublicExponent().toByteArray())));
                KeyPair generateKeyPair = engineFactory.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
                return (JwtRsaSsaPssPrivateKey) JwtRsaSsaPssPrivateKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setPublicKey((JwtRsaSsaPssPublicKey) JwtRsaSsaPssPublicKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("JWT_PS256_2048_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_PS256_2048_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_PS256_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_PS256_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_PS384_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_PS384_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_PS512_4096_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_PS512_4096_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(JwtRsaSsaPssAlgorithm.PS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPssSignKeyManager(), new JwtRsaSsaPssVerifyKeyManager(), newKeyAllowed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat> createKeyFormat(JwtRsaSsaPssAlgorithm algorithm, int modulusSize, BigInteger publicExponent, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtRsaSsaPssKeyFormat) JwtRsaSsaPssKeyFormat.newBuilder().setAlgorithm(algorithm).setModulusSizeInBits(modulusSize).setPublicExponent(ByteString.copyFrom(publicExponent.toByteArray())).build(), prefixType);
    }
}
