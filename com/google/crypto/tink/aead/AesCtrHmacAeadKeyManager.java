package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.mac.HmacKeyManager;
import com.google.crypto.tink.proto.AesCtrHmacAeadKey;
import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EncryptThenAuthenticate;
import com.google.crypto.tink.subtle.IndCpaCipher;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class AesCtrHmacAeadKeyManager extends KeyTypeManager<AesCtrHmacAeadKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesCtrHmacAeadKeyManager() {
        super(AesCtrHmacAeadKey.class, new PrimitiveFactory<Aead, AesCtrHmacAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(AesCtrHmacAeadKey key) throws GeneralSecurityException {
                return new EncryptThenAuthenticate((IndCpaCipher) new AesCtrKeyManager().getPrimitive(key.getAesCtrKey(), IndCpaCipher.class), (Mac) new HmacKeyManager().getPrimitive(key.getHmacKey(), Mac.class), key.getHmacKey().getParams().getTagSize());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesCtrHmacAeadKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        new AesCtrKeyManager().validateKey(key.getAesCtrKey());
        new HmacKeyManager().validateKey(key.getHmacKey());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesCtrHmacAeadKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCtrHmacAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesCtrHmacAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCtrHmacAeadKeyFormat, AesCtrHmacAeadKey>(AesCtrHmacAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCtrHmacAeadKeyFormat format) throws GeneralSecurityException {
                new AesCtrKeyManager().keyFactory().validateKeyFormat(format.getAesCtrKeyFormat());
                new HmacKeyManager().keyFactory().validateKeyFormat(format.getHmacKeyFormat());
                Validators.validateAesKeySize(format.getAesCtrKeyFormat().getKeySize());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCtrHmacAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCtrHmacAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCtrHmacAeadKey createKey(AesCtrHmacAeadKeyFormat format) throws GeneralSecurityException {
                AesCtrKey createKey = new AesCtrKeyManager().keyFactory().createKey(format.getAesCtrKeyFormat());
                return (AesCtrHmacAeadKey) AesCtrHmacAeadKey.newBuilder().setAesCtrKey(createKey).setHmacKey(new HmacKeyManager().keyFactory().createKey(format.getHmacKeyFormat())).setVersion(AesCtrHmacAeadKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCtrHmacAeadKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_CTR_HMAC_SHA256", AesCtrHmacAeadKeyManager.createKeyFormat(16, 16, 32, 16, HashType.SHA256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES128_CTR_HMAC_SHA256_RAW", AesCtrHmacAeadKeyManager.createKeyFormat(16, 16, 32, 16, HashType.SHA256, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_CTR_HMAC_SHA256", AesCtrHmacAeadKeyManager.createKeyFormat(32, 16, 32, 32, HashType.SHA256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_CTR_HMAC_SHA256_RAW", AesCtrHmacAeadKeyManager.createKeyFormat(32, 16, 32, 32, HashType.SHA256, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrHmacAeadKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate aes128CtrHmacSha256Template() {
        return createKeyTemplate(16, 16, 32, 16, HashType.SHA256);
    }

    public static final KeyTemplate aes256CtrHmacSha256Template() {
        return createKeyTemplate(32, 16, 32, 32, HashType.SHA256);
    }

    private static KeyTemplate createKeyTemplate(int aesKeySize, int ivSize, int hmacKeySize, int tagSize, HashType hashType) {
        return KeyTemplate.create(new AesCtrHmacAeadKeyManager().getKeyType(), createKeyFormat(aesKeySize, ivSize, hmacKeySize, tagSize, hashType).toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<AesCtrHmacAeadKeyFormat> createKeyFormat(int aesKeySize, int ivSize, int hmacKeySize, int tagSize, HashType hashType, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>(createKeyFormat(aesKeySize, ivSize, hmacKeySize, tagSize, hashType), prefixType);
    }

    private static AesCtrHmacAeadKeyFormat createKeyFormat(int aesKeySize, int ivSize, int hmacKeySize, int tagSize, HashType hashType) {
        AesCtrKeyFormat aesCtrKeyFormat = (AesCtrKeyFormat) AesCtrKeyFormat.newBuilder().setParams((AesCtrParams) AesCtrParams.newBuilder().setIvSize(ivSize).build()).setKeySize(aesKeySize).build();
        return (AesCtrHmacAeadKeyFormat) AesCtrHmacAeadKeyFormat.newBuilder().setAesCtrKeyFormat(aesCtrKeyFormat).setHmacKeyFormat((HmacKeyFormat) HmacKeyFormat.newBuilder().setParams((HmacParams) HmacParams.newBuilder().setHash(hashType).setTagSize(tagSize).build()).setKeySize(hmacKeySize).build()).build();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }
}
