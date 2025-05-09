package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HpkeKeyFormat;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.X25519;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class HpkePrivateKeyManager extends PrivateKeyTypeManager<HpkePrivateKey, HpkePublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    public HpkePrivateKeyManager() {
        super(HpkePrivateKey.class, HpkePublicKey.class, new PrimitiveFactory<HybridDecrypt, HpkePrivateKey>(HybridDecrypt.class) { // from class: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public HybridDecrypt getPrimitive(HpkePrivateKey recipientPrivateKey) throws GeneralSecurityException {
                return HpkeDecrypt.createHpkeDecrypt(recipientPrivateKey);
            }
        });
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new HpkePrivateKeyManager(), new HpkePublicKeyManager(), newKeyAllowed);
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public HpkePublicKey getPublicKey(HpkePrivateKey key) {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public HpkePrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HpkePrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(HpkePrivateKey key) throws GeneralSecurityException {
        if (key.getPrivateKey().isEmpty()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if (!key.hasPublicKey()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        Validators.validateVersion(key.getVersion(), getVersion());
        HpkeUtil.validateParams(key.getPublicKey().getParams());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<HpkeKeyFormat, HpkePrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HpkeKeyFormat, HpkePrivateKey>(HpkeKeyFormat.class) { // from class: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HpkeKeyFormat keyFormat) throws GeneralSecurityException {
                HpkeUtil.validateParams(keyFormat.getParams());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HpkeKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HpkeKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HpkePrivateKey createKey(HpkeKeyFormat keyFormat) throws GeneralSecurityException {
                byte[] generatePrivateKey;
                byte[] publicFromPrivate;
                int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[keyFormat.getParams().getKem().ordinal()];
                if (i == 1) {
                    generatePrivateKey = X25519.generatePrivateKey();
                    publicFromPrivate = X25519.publicFromPrivate(generatePrivateKey);
                } else if (i == 2 || i == 3 || i == 4) {
                    EllipticCurves.CurveType nistHpkeKemToCurve = HpkeUtil.nistHpkeKemToCurve(keyFormat.getParams().getKem());
                    KeyPair generateKeyPair = EllipticCurves.generateKeyPair(nistHpkeKemToCurve);
                    publicFromPrivate = EllipticCurves.pointEncode(nistHpkeKemToCurve, EllipticCurves.PointFormatType.UNCOMPRESSED, ((ECPublicKey) generateKeyPair.getPublic()).getW());
                    generatePrivateKey = ((ECPrivateKey) generateKeyPair.getPrivate()).getS().toByteArray();
                } else {
                    throw new GeneralSecurityException("Invalid KEM");
                }
                return (HpkePrivateKey) HpkePrivateKey.newBuilder().setVersion(HpkePrivateKeyManager.this.getVersion()).setPublicKey((HpkePublicKey) HpkePublicKey.newBuilder().setVersion(HpkePrivateKeyManager.this.getVersion()).setParams(keyFormat.getParams()).setPublicKey(ByteString.copyFrom(publicFromPrivate)).build()).setPrivateKey(ByteString.copyFrom(generatePrivateKey)).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HpkeKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.CHACHA20_POLY1305, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.CHACHA20_POLY1305, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512, com.google.crypto.tink.proto.HpkeAead.AES_128_GCM, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512, com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512, com.google.crypto.tink.proto.HpkeAead.AES_256_GCM, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] iArr = new int[com.google.crypto.tink.proto.HpkeKem.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HpkeKem = iArr;
            try {
                iArr[com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<HpkeKeyFormat> createKeyFormat(com.google.crypto.tink.proto.HpkeKem kem, com.google.crypto.tink.proto.HpkeKdf kdf, com.google.crypto.tink.proto.HpkeAead aead, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((HpkeKeyFormat) HpkeKeyFormat.newBuilder().setParams((HpkeParams) HpkeParams.newBuilder().setKem(kem).setKdf(kdf).setAead(aead).build()).build(), prefixType);
    }
}
