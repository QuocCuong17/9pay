package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class AesGcmKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.AesGcmKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesGcmKeyManager() {
        super(com.google.crypto.tink.proto.AesGcmKey.class, new PrimitiveFactory<Aead, com.google.crypto.tink.proto.AesGcmKey>(Aead.class) { // from class: com.google.crypto.tink.aead.AesGcmKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(com.google.crypto.tink.proto.AesGcmKey key) throws GeneralSecurityException {
                return new AesGcmJce(key.getKeyValue().toByteArray());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.AesGcmKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        Validators.validateAesKeySize(key.getKeyValue().size());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.AesGcmKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.AesGcmKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.AesGcmKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmKeyFormat, com.google.crypto.tink.proto.AesGcmKey>(AesGcmKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesGcmKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesGcmKeyFormat format) throws GeneralSecurityException {
                Validators.validateAesKeySize(format.getKeySize());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesGcmKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.AesGcmKey createKey(AesGcmKeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.AesGcmKey) com.google.crypto.tink.proto.AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).setVersion(AesGcmKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.AesGcmKey deriveKey(AesGcmKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), AesGcmKeyManager.this.getVersion());
                byte[] bArr = new byte[format.getKeySize()];
                try {
                    readFully(inputStream, bArr);
                    return (com.google.crypto.tink.proto.AesGcmKey) com.google.crypto.tink.proto.AesGcmKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(AesGcmKeyManager.this.getVersion()).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesGcmKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_GCM", AesGcmKeyManager.createKeyFormat(16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES128_GCM_RAW", AesGcmKeyManager.createKeyFormat(16, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_GCM", AesGcmKeyManager.createKeyFormat(32, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_GCM_RAW", AesGcmKeyManager.createKeyFormat(32, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmKeyManager(), newKeyAllowed);
        AesGcmProtoSerialization.register();
    }

    public static final KeyTemplate aes128GcmTemplate() {
        return createKeyTemplate(16, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes128GcmTemplate() {
        return createKeyTemplate(16, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate aes256GcmTemplate() {
        return createKeyTemplate(32, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256GcmTemplate() {
        return createKeyTemplate(32, KeyTemplate.OutputPrefixType.RAW);
    }

    private static KeyTemplate createKeyTemplate(int keySize, KeyTemplate.OutputPrefixType prefixType) {
        return KeyTemplate.create(new AesGcmKeyManager().getKeyType(), ((AesGcmKeyFormat) AesGcmKeyFormat.newBuilder().setKeySize(keySize).build()).toByteArray(), prefixType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<AesGcmKeyFormat> createKeyFormat(int keySize, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((AesGcmKeyFormat) AesGcmKeyFormat.newBuilder().setKeySize(keySize).build(), prefixType);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }
}
