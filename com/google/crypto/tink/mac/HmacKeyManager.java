package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.mac.internal.ChunkedHmacImpl;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public final class HmacKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.HmacKey> {
    private static final PrimitiveConstructor<HmacKey, ChunkedMac> CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(new PrimitiveConstructor.PrimitiveConstructionFunction() { // from class: com.google.crypto.tink.mac.HmacKeyManager$$ExternalSyntheticLambda0
        @Override // com.google.crypto.tink.internal.PrimitiveConstructor.PrimitiveConstructionFunction
        public final Object constructPrimitive(Key key) {
            return new ChunkedHmacImpl((HmacKey) key);
        }
    }, HmacKey.class, ChunkedMac.class);
    private static final int MIN_KEY_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    public HmacKeyManager() {
        super(com.google.crypto.tink.proto.HmacKey.class, new PrimitiveFactory<Mac, com.google.crypto.tink.proto.HmacKey>(Mac.class) { // from class: com.google.crypto.tink.mac.HmacKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Mac getPrimitive(com.google.crypto.tink.proto.HmacKey key) throws GeneralSecurityException {
                HashType hash = key.getParams().getHash();
                SecretKeySpec secretKeySpec = new SecretKeySpec(key.getKeyValue().toByteArray(), "HMAC");
                int tagSize = key.getParams().getTagSize();
                int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HashType[hash.ordinal()];
                if (i == 1) {
                    return new PrfMac(new PrfHmacJce("HMACSHA1", secretKeySpec), tagSize);
                }
                if (i == 2) {
                    return new PrfMac(new PrfHmacJce("HMACSHA224", secretKeySpec), tagSize);
                }
                if (i == 3) {
                    return new PrfMac(new PrfHmacJce("HMACSHA256", secretKeySpec), tagSize);
                }
                if (i == 4) {
                    return new PrfMac(new PrfHmacJce("HMACSHA384", secretKeySpec), tagSize);
                }
                if (i == 5) {
                    return new PrfMac(new PrfHmacJce("HMACSHA512", secretKeySpec), tagSize);
                }
                throw new GeneralSecurityException("unknown hash");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.mac.HmacKeyManager$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] iArr = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.HmacKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        validateParams(key.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.HmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.HmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(HmacParams params) throws GeneralSecurityException {
        if (params.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HashType[params.getHash().ordinal()];
        if (i == 1) {
            if (params.getTagSize() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
            return;
        }
        if (i == 2) {
            if (params.getTagSize() > 28) {
                throw new GeneralSecurityException("tag size too big");
            }
            return;
        }
        if (i == 3) {
            if (params.getTagSize() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (i == 4) {
            if (params.getTagSize() > 48) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            if (i == 5) {
                if (params.getTagSize() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            }
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.HmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HmacKeyFormat, com.google.crypto.tink.proto.HmacKey>(HmacKeyFormat.class) { // from class: com.google.crypto.tink.mac.HmacKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HmacKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() >= 16) {
                    HmacKeyManager.validateParams(format.getParams());
                    return;
                }
                throw new GeneralSecurityException("key too short");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.HmacKey createKey(HmacKeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.HmacKey) com.google.crypto.tink.proto.HmacKey.newBuilder().setVersion(HmacKeyManager.this.getVersion()).setParams(format.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.HmacKey deriveKey(HmacKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), HmacKeyManager.this.getVersion());
                byte[] bArr = new byte[format.getKeySize()];
                try {
                    readFully(inputStream, bArr);
                    return (com.google.crypto.tink.proto.HmacKey) com.google.crypto.tink.proto.HmacKey.newBuilder().setVersion(HmacKeyManager.this.getVersion()).setParams(format.getParams()).setKeyValue(ByteString.copyFrom(bArr)).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HmacKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("HMAC_SHA256_128BITTAG", HmacKeyManager.createKeyFormat(32, 16, HashType.SHA256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA256_128BITTAG_RAW", HmacKeyManager.createKeyFormat(32, 16, HashType.SHA256, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA256_256BITTAG", HmacKeyManager.createKeyFormat(32, 32, HashType.SHA256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA256_256BITTAG_RAW", HmacKeyManager.createKeyFormat(32, 32, HashType.SHA256, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA512_128BITTAG", HmacKeyManager.createKeyFormat(64, 16, HashType.SHA512, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA512_128BITTAG_RAW", HmacKeyManager.createKeyFormat(64, 16, HashType.SHA512, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA512_256BITTAG", HmacKeyManager.createKeyFormat(64, 32, HashType.SHA512, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA512_256BITTAG_RAW", HmacKeyManager.createKeyFormat(64, 32, HashType.SHA512, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA512_512BITTAG", HmacKeyManager.createKeyFormat(64, 64, HashType.SHA512, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA512_512BITTAG_RAW", HmacKeyManager.createKeyFormat(64, 64, HashType.SHA512, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacKeyManager(), newKeyAllowed);
        HmacProtoSerialization.register();
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR);
    }

    public static final KeyTemplate hmacSha256HalfDigestTemplate() {
        return createTemplate(32, 16, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha256Template() {
        return createTemplate(32, 32, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512HalfDigestTemplate() {
        return createTemplate(64, 32, HashType.SHA512);
    }

    public static final KeyTemplate hmacSha512Template() {
        return createTemplate(64, 64, HashType.SHA512);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    private static KeyTemplate createTemplate(int keySize, int tagSize, HashType hashType) {
        return KeyTemplate.create(new HmacKeyManager().getKeyType(), ((HmacKeyFormat) HmacKeyFormat.newBuilder().setParams((HmacParams) HmacParams.newBuilder().setHash(hashType).setTagSize(tagSize).build()).setKeySize(keySize).build()).toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<HmacKeyFormat> createKeyFormat(int keySize, int tagSize, HashType hashType, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((HmacKeyFormat) HmacKeyFormat.newBuilder().setParams((HmacParams) HmacParams.newBuilder().setHash(hashType).setTagSize(tagSize).build()).setKeySize(keySize).build(), prefixType);
    }
}
