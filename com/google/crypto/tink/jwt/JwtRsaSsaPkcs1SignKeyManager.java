package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
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
public final class JwtRsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPkcs1PrivateKey, JwtRsaSsaPkcs1PublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey privateKey, JwtRsaSsaPkcs1PrivateKey keyProto) throws GeneralSecurityException {
        SelfKeyTestValidators.validateRsaSsaPkcs1(privateKey, (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray()))), JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(keyProto.getPublicKey().getAlgorithm()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPkcs1PrivateKey keyProto) throws GeneralSecurityException {
        return (RSAPrivateCrtKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, keyProto.getPublicKey().getN().toByteArray()), new BigInteger(1, keyProto.getPublicKey().getE().toByteArray()), new BigInteger(1, keyProto.getD().toByteArray()), new BigInteger(1, keyProto.getP().toByteArray()), new BigInteger(1, keyProto.getQ().toByteArray()), new BigInteger(1, keyProto.getDp().toByteArray()), new BigInteger(1, keyProto.getDq().toByteArray()), new BigInteger(1, keyProto.getCrt().toByteArray())));
    }

    /* loaded from: classes4.dex */
    private static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPkcs1PrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPkcs1PrivateKey keyProto) throws GeneralSecurityException {
            final Optional empty;
            RSAPrivateCrtKey createPrivateKey = JwtRsaSsaPkcs1SignKeyManager.createPrivateKey(keyProto);
            JwtRsaSsaPkcs1SignKeyManager.selfTestKey(createPrivateKey, keyProto);
            JwtRsaSsaPkcs1Algorithm algorithm = keyProto.getPublicKey().getAlgorithm();
            final RsaSsaPkcs1SignJce rsaSsaPkcs1SignJce = new RsaSsaPkcs1SignJce(createPrivateKey, JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(algorithm));
            final String name = algorithm.name();
            if (keyProto.getPublicKey().hasCustomKid()) {
                empty = Optional.of(keyProto.getPublicKey().getCustomKid().getValue());
            } else {
                empty = Optional.empty();
            }
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1SignKeyManager.JwtPublicKeySignFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> kid) throws GeneralSecurityException {
                    if (empty.isPresent()) {
                        if (kid.isPresent()) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        kid = empty;
                    }
                    String createUnsignedCompact = JwtFormat.createUnsignedCompact(name, kid, rawJwt);
                    return JwtFormat.createSignedCompact(createUnsignedCompact, rsaSsaPkcs1SignJce.sign(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JwtRsaSsaPkcs1SignKeyManager() {
        super(JwtRsaSsaPkcs1PrivateKey.class, JwtRsaSsaPkcs1PublicKey.class, new JwtPublicKeySignFactory());
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtRsaSsaPkcs1PublicKey getPublicKey(JwtRsaSsaPkcs1PrivateKey privKeyProto) {
        return privKeyProto.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPkcs1PrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPkcs1PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPkcs1PrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, privKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, privKey.getPublicKey().getE().toByteArray()));
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtRsaSsaPkcs1KeyFormat, JwtRsaSsaPkcs1PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPkcs1KeyFormat, JwtRsaSsaPkcs1PrivateKey>(JwtRsaSsaPkcs1KeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1SignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtRsaSsaPkcs1KeyFormat keyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(keyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, keyFormat.getPublicExponent().toByteArray()));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtRsaSsaPkcs1KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPkcs1PrivateKey deriveKey(JwtRsaSsaPkcs1KeyFormat format, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPkcs1PrivateKey createKey(JwtRsaSsaPkcs1KeyFormat format) throws GeneralSecurityException {
                JwtRsaSsaPkcs1Algorithm algorithm = format.getAlgorithm();
                KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                engineFactory.initialize(new RSAKeyGenParameterSpec(format.getModulusSizeInBits(), new BigInteger(1, format.getPublicExponent().toByteArray())));
                KeyPair generateKeyPair = engineFactory.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
                return (JwtRsaSsaPkcs1PrivateKey) JwtRsaSsaPkcs1PrivateKey.newBuilder().setVersion(JwtRsaSsaPkcs1SignKeyManager.this.getVersion()).setPublicKey((JwtRsaSsaPkcs1PublicKey) JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(JwtRsaSsaPkcs1SignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("JWT_RS256_2048_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_RS256_2048_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 2048, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_RS256_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_RS256_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_RS384_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_RS384_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS384, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_RS512_4096_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_RS512_4096_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(JwtRsaSsaPkcs1Algorithm.RS512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPkcs1SignKeyManager(), new JwtRsaSsaPkcs1VerifyKeyManager(), newKeyAllowed);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat> createKeyFormat(JwtRsaSsaPkcs1Algorithm algorithm, int modulusSize, BigInteger publicExponent, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtRsaSsaPkcs1KeyFormat) JwtRsaSsaPkcs1KeyFormat.newBuilder().setAlgorithm(algorithm).setModulusSizeInBits(modulusSize).setPublicExponent(ByteString.copyFrom(publicExponent.toByteArray())).build(), prefixType);
    }
}
