package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.mac.internal.ChunkedAesCmacImpl;
import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class AesCmacKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.AesCmacKey> {
    private static final PrimitiveConstructor<AesCmacKey, ChunkedMac> CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(new PrimitiveConstructor.PrimitiveConstructionFunction() { // from class: com.google.crypto.tink.mac.AesCmacKeyManager$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.PrimitiveConstructor.PrimitiveConstructionFunction
        public final Object constructPrimitive(Key key) {
            return new ChunkedAesCmacImpl((AesCmacKey) key);
        }
    }, AesCmacKey.class, ChunkedMac.class);
    private static final int KEY_SIZE_IN_BYTES = 32;
    private static final int MAX_TAG_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int VERSION = 0;

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesCmacKeyManager() {
        super(com.google.crypto.tink.proto.AesCmacKey.class, new PrimitiveFactory<Mac, com.google.crypto.tink.proto.AesCmacKey>(Mac.class) { // from class: com.google.crypto.tink.mac.AesCmacKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Mac getPrimitive(com.google.crypto.tink.proto.AesCmacKey key) throws GeneralSecurityException {
                return new PrfMac(new PrfAesCmac(key.getKeyValue().toByteArray()), key.getParams().getTagSize());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.AesCmacKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        validateSize(key.getKeyValue().size());
        validateParams(key.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.AesCmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.AesCmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesCmacParams params) throws GeneralSecurityException {
        if (params.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if (params.getTagSize() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateSize(int size) throws GeneralSecurityException {
        if (size != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.AesCmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCmacKeyFormat, com.google.crypto.tink.proto.AesCmacKey>(AesCmacKeyFormat.class) { // from class: com.google.crypto.tink.mac.AesCmacKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCmacKeyFormat format) throws GeneralSecurityException {
                AesCmacKeyManager.validateParams(format.getParams());
                AesCmacKeyManager.validateSize(format.getKeySize());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.AesCmacKey createKey(AesCmacKeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.AesCmacKey) com.google.crypto.tink.proto.AesCmacKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).setParams(format.getParams()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCmacKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("AES_CMAC", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build(), KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_CMAC", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build(), KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_CMAC_RAW", new KeyTypeManager.KeyFactory.KeyFormat((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCmacKeyManager(), newKeyAllowed);
        AesCmacProtoSerialization.register();
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR);
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), ((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build()).toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), ((AesCmacKeyFormat) AesCmacKeyFormat.newBuilder().setKeySize(32).setParams((AesCmacParams) AesCmacParams.newBuilder().setTagSize(16).build()).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
