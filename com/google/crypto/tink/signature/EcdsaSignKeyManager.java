package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class EcdsaSignKeyManager extends PrivateKeyTypeManager<com.google.crypto.tink.proto.EcdsaPrivateKey, com.google.crypto.tink.proto.EcdsaPublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EcdsaSignKeyManager() {
        super(com.google.crypto.tink.proto.EcdsaPrivateKey.class, com.google.crypto.tink.proto.EcdsaPublicKey.class, new PrimitiveFactory<PublicKeySign, com.google.crypto.tink.proto.EcdsaPrivateKey>(PublicKeySign.class) { // from class: com.google.crypto.tink.signature.EcdsaSignKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public PublicKeySign getPrimitive(com.google.crypto.tink.proto.EcdsaPrivateKey key) throws GeneralSecurityException {
                ECPrivateKey ecPrivateKey = EllipticCurves.getEcPrivateKey(SigUtil.toCurveType(key.getPublicKey().getParams().getCurve()), key.getKeyValue().toByteArray());
                SelfKeyTestValidators.validateEcdsa(ecPrivateKey, EllipticCurves.getEcPublicKey(SigUtil.toCurveType(key.getPublicKey().getParams().getCurve()), key.getPublicKey().getX().toByteArray(), key.getPublicKey().getY().toByteArray()), SigUtil.toHashType(key.getPublicKey().getParams().getHashType()), SigUtil.toEcdsaEncoding(key.getPublicKey().getParams().getEncoding()));
                return new EcdsaSignJce(ecPrivateKey, SigUtil.toHashType(key.getPublicKey().getParams().getHashType()), SigUtil.toEcdsaEncoding(key.getPublicKey().getParams().getEncoding()));
            }
        });
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public com.google.crypto.tink.proto.EcdsaPublicKey getPublicKey(com.google.crypto.tink.proto.EcdsaPrivateKey key) throws GeneralSecurityException {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.EcdsaPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.EcdsaPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.EcdsaPrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        SigUtil.validateEcdsaParams(privKey.getPublicKey().getParams());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<EcdsaKeyFormat, com.google.crypto.tink.proto.EcdsaPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<EcdsaKeyFormat, com.google.crypto.tink.proto.EcdsaPrivateKey>(EcdsaKeyFormat.class) { // from class: com.google.crypto.tink.signature.EcdsaSignKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(EcdsaKeyFormat format) throws GeneralSecurityException {
                SigUtil.validateEcdsaParams(format.getParams());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public EcdsaKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return EcdsaKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.EcdsaPrivateKey createKey(EcdsaKeyFormat format) throws GeneralSecurityException {
                EcdsaParams params = format.getParams();
                KeyPair generateKeyPair = EllipticCurves.generateKeyPair(SigUtil.toCurveType(params.getCurve()));
                ECPublicKey eCPublicKey = (ECPublicKey) generateKeyPair.getPublic();
                ECPrivateKey eCPrivateKey = (ECPrivateKey) generateKeyPair.getPrivate();
                ECPoint w = eCPublicKey.getW();
                return (com.google.crypto.tink.proto.EcdsaPrivateKey) com.google.crypto.tink.proto.EcdsaPrivateKey.newBuilder().setVersion(EcdsaSignKeyManager.this.getVersion()).setPublicKey((com.google.crypto.tink.proto.EcdsaPublicKey) com.google.crypto.tink.proto.EcdsaPublicKey.newBuilder().setVersion(EcdsaSignKeyManager.this.getVersion()).setParams(params).setX(ByteString.copyFrom(w.getAffineX().toByteArray())).setY(ByteString.copyFrom(w.getAffineY().toByteArray())).build()).setKeyValue(ByteString.copyFrom(eCPrivateKey.getS().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<EcdsaKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("ECDSA_P256", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P256_IEEE_P1363", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P256_RAW", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX", EcdsaSignKeyManager.createKeyFormat(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("ECDSA_P384", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P384_IEEE_P1363", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P384_SHA512", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P384_SHA384", EcdsaSignKeyManager.createKeyFormat(HashType.SHA384, EllipticCurveType.NIST_P384, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P521", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P521, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ECDSA_P521_IEEE_P1363", EcdsaSignKeyManager.createKeyFormat(HashType.SHA512, EllipticCurveType.NIST_P521, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EcdsaSignKeyManager(), new EcdsaVerifyKeyManager(), newKeyAllowed);
        EcdsaProtoSerialization.register();
    }

    public static final KeyTemplate ecdsaP256Template() {
        return createKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawEcdsaP256Template() {
        return createKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.RAW);
    }

    public static KeyTemplate createKeyTemplate(HashType hashType, EllipticCurveType curve, EcdsaSignatureEncoding encoding, KeyTemplate.OutputPrefixType prefixType) {
        return KeyTemplate.create(new EcdsaSignKeyManager().getKeyType(), ((EcdsaKeyFormat) EcdsaKeyFormat.newBuilder().setParams((EcdsaParams) EcdsaParams.newBuilder().setHashType(hashType).setCurve(curve).setEncoding(encoding).build()).build()).toByteArray(), prefixType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<EcdsaKeyFormat> createKeyFormat(HashType hashType, EllipticCurveType curve, EcdsaSignatureEncoding encoding, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((EcdsaKeyFormat) EcdsaKeyFormat.newBuilder().setParams((EcdsaParams) EcdsaParams.newBuilder().setHashType(hashType).setCurve(curve).setEncoding(encoding).build()).build(), prefixType);
    }
}
