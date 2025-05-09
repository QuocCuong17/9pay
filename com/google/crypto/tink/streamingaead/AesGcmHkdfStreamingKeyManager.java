package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKey;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class AesGcmHkdfStreamingKeyManager extends KeyTypeManager<AesGcmHkdfStreamingKey> {
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int TAG_SIZE_IN_BYTES = 16;

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AesGcmHkdfStreamingKeyManager() {
        super(AesGcmHkdfStreamingKey.class, new PrimitiveFactory<StreamingAead, AesGcmHkdfStreamingKey>(StreamingAead.class) { // from class: com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public StreamingAead getPrimitive(AesGcmHkdfStreamingKey key) throws GeneralSecurityException {
                return new AesGcmHkdfStreaming(key.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(key.getParams().getHkdfHashType()), key.getParams().getDerivedKeySize(), key.getParams().getCiphertextSegmentSize(), 0);
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesGcmHkdfStreamingKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        validateParams(key.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesGcmHkdfStreamingKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmHkdfStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, AesGcmHkdfStreamingKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey>(AesGcmHkdfStreamingKeyFormat.class) { // from class: com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesGcmHkdfStreamingKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() >= 16) {
                    AesGcmHkdfStreamingKeyManager.validateParams(format.getParams());
                    return;
                }
                throw new GeneralSecurityException("key_size must be at least 16 bytes");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesGcmHkdfStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKey createKey(AesGcmHkdfStreamingKeyFormat format) throws GeneralSecurityException {
                return (AesGcmHkdfStreamingKey) AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).setParams(format.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKey deriveKey(AesGcmHkdfStreamingKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), AesGcmHkdfStreamingKeyManager.this.getVersion());
                byte[] bArr = new byte[format.getKeySize()];
                try {
                    readFully(inputStream, bArr);
                    return (AesGcmHkdfStreamingKey) AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setParams(format.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesGcmHkdfStreamingKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_GCM_HKDF_4KB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(16, HashType.SHA256, 16, 4096), KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES128_GCM_HKDF_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(16, HashType.SHA256, 16, 1048576), KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_GCM_HKDF_4KB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(32, HashType.SHA256, 32, 4096), KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_GCM_HKDF_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(32, HashType.SHA256, 32, 1048576), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesGcmHkdfStreamingParams params) throws GeneralSecurityException {
        Validators.validateAesKeySize(params.getDerivedKeySize());
        if (params.getHkdfHashType() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HKDF hash type");
        }
        if (params.getCiphertextSegmentSize() < params.getDerivedKeySize() + 7 + 16 + 2) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + NONCE_PREFIX_IN_BYTES + TAG_SIZE_IN_BYTES + 2)");
        }
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesGcmHkdfStreamingKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 4096);
    }

    public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 1048576);
    }

    public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 4096);
    }

    public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 1048576);
    }

    private static KeyTemplate createKeyTemplate(int mainKeySize, HashType hkdfHashType, int derivedKeySize, int ciphertextSegmentSize) {
        return KeyTemplate.create(new AesGcmHkdfStreamingKeyManager().getKeyType(), createKeyFormat(mainKeySize, hkdfHashType, derivedKeySize, ciphertextSegmentSize).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesGcmHkdfStreamingKeyFormat createKeyFormat(int mainKeySize, HashType hkdfHashType, int derivedKeySize, int ciphertextSegmentSize) {
        return (AesGcmHkdfStreamingKeyFormat) AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(mainKeySize).setParams((AesGcmHkdfStreamingParams) AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(ciphertextSegmentSize).setDerivedKeySize(derivedKeySize).setHkdfHashType(hkdfHashType).build()).build();
    }
}
