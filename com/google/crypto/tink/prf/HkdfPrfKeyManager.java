package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.prf.HkdfStreamingPrf;
import com.google.crypto.tink.subtle.prf.PrfImpl;
import com.google.crypto.tink.subtle.prf.StreamingPrf;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class HkdfPrfKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.HkdfPrfKey> {
    private static final int MIN_KEY_SIZE = 32;

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.prf.HkdfPrfKeyManager$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] iArr = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Enums.HashType convertHash(HashType hashType) throws GeneralSecurityException {
        int i = AnonymousClass4.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i == 1) {
            return Enums.HashType.SHA1;
        }
        if (i == 2) {
            return Enums.HashType.SHA256;
        }
        if (i == 3) {
            return Enums.HashType.SHA384;
        }
        if (i == 4) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("HashType " + hashType.name() + " not known in");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HkdfPrfKeyManager() {
        super(com.google.crypto.tink.proto.HkdfPrfKey.class, new PrimitiveFactory<StreamingPrf, com.google.crypto.tink.proto.HkdfPrfKey>(StreamingPrf.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public StreamingPrf getPrimitive(com.google.crypto.tink.proto.HkdfPrfKey key) throws GeneralSecurityException {
                return new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(key.getParams().getHash()), key.getKeyValue().toByteArray(), key.getParams().getSalt().toByteArray());
            }
        }, new PrimitiveFactory<Prf, com.google.crypto.tink.proto.HkdfPrfKey>(Prf.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.2
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Prf getPrimitive(com.google.crypto.tink.proto.HkdfPrfKey key) throws GeneralSecurityException {
                return PrfImpl.wrap(new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(key.getParams().getHash()), key.getKeyValue().toByteArray(), key.getParams().getSalt().toByteArray()));
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.HkdfPrfKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        validateKeySize(key.getKeyValue().size());
        validateParams(key.getParams());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.HkdfPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.HkdfPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.HkdfPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, com.google.crypto.tink.proto.HkdfPrfKey>(HkdfPrfKeyFormat.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.3
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HkdfPrfKeyFormat format) throws GeneralSecurityException {
                HkdfPrfKeyManager.validateKeySize(format.getKeySize());
                HkdfPrfKeyManager.validateParams(format.getParams());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HkdfPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HkdfPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.HkdfPrfKey createKey(HkdfPrfKeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.HkdfPrfKey) com.google.crypto.tink.proto.HkdfPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).setVersion(HkdfPrfKeyManager.this.getVersion()).setParams(format.getParams()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HkdfPrfKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("HKDF_SHA256", new KeyTypeManager.KeyFactory.KeyFormat((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateKeySize(int keySize) throws GeneralSecurityException {
        if (keySize < 32) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Key size too short");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(HkdfPrfParams params) throws GeneralSecurityException {
        if (params.getHash() != HashType.SHA256 && params.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Unsupported hash");
        }
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new HkdfPrfKeyManager(), newKeyAllowed);
        HkdfPrfProtoSerialization.register();
    }

    public static String staticKeyType() {
        return new HkdfPrfKeyManager().getKeyType();
    }

    public static final KeyTemplate hkdfSha256Template() {
        return KeyTemplate.create(staticKeyType(), ((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
