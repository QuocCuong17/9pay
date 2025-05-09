package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesEaxKeyFormat;
import com.google.crypto.tink.proto.AesEaxParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesEaxJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class AesEaxKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.AesEaxKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesEaxKeyManager() {
        super(com.google.crypto.tink.proto.AesEaxKey.class, new PrimitiveFactory<Aead, com.google.crypto.tink.proto.AesEaxKey>(Aead.class) { // from class: com.google.crypto.tink.aead.AesEaxKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(com.google.crypto.tink.proto.AesEaxKey key) throws GeneralSecurityException {
                return new AesEaxJce(key.getKeyValue().toByteArray(), key.getParams().getIvSize());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.AesEaxKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        Validators.validateAesKeySize(key.getKeyValue().size());
        if (key.getParams().getIvSize() != 12 && key.getParams().getIvSize() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.AesEaxKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.AesEaxKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.AesEaxKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesEaxKeyFormat, com.google.crypto.tink.proto.AesEaxKey>(AesEaxKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesEaxKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesEaxKeyFormat format) throws GeneralSecurityException {
                Validators.validateAesKeySize(format.getKeySize());
                if (format.getParams().getIvSize() != 12 && format.getParams().getIvSize() != 16) {
                    throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesEaxKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesEaxKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.AesEaxKey createKey(AesEaxKeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.AesEaxKey) com.google.crypto.tink.proto.AesEaxKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).setParams(format.getParams()).setVersion(AesEaxKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesEaxKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_EAX", AesEaxKeyManager.createKeyFormat(16, 16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES128_EAX_RAW", AesEaxKeyManager.createKeyFormat(16, 16, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_EAX", AesEaxKeyManager.createKeyFormat(32, 16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_EAX_RAW", AesEaxKeyManager.createKeyFormat(32, 16, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesEaxKeyManager(), newKeyAllowed);
        AesEaxProtoSerialization.register();
    }

    public static final KeyTemplate aes128EaxTemplate() {
        return createKeyTemplate(16, 16, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes128EaxTemplate() {
        return createKeyTemplate(16, 16, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate aes256EaxTemplate() {
        return createKeyTemplate(32, 16, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256EaxTemplate() {
        return createKeyTemplate(32, 16, KeyTemplate.OutputPrefixType.RAW);
    }

    private static KeyTemplate createKeyTemplate(int keySize, int ivSize, KeyTemplate.OutputPrefixType prefixType) {
        return KeyTemplate.create(new AesEaxKeyManager().getKeyType(), ((AesEaxKeyFormat) AesEaxKeyFormat.newBuilder().setKeySize(keySize).setParams((AesEaxParams) AesEaxParams.newBuilder().setIvSize(ivSize).build()).build()).toByteArray(), prefixType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<AesEaxKeyFormat> createKeyFormat(int keySize, int ivSize, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((AesEaxKeyFormat) AesEaxKeyFormat.newBuilder().setKeySize(keySize).setParams((AesEaxParams) AesEaxParams.newBuilder().setIvSize(ivSize).build()).build(), prefixType);
    }
}
