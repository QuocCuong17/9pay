package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsAeadKey;
import com.google.crypto.tink.proto.KmsAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public class KmsAeadKeyManager extends KeyTypeManager<KmsAeadKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KmsAeadKeyManager() {
        super(KmsAeadKey.class, new PrimitiveFactory<Aead, KmsAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(KmsAeadKey keyProto) throws GeneralSecurityException {
                String keyUri = keyProto.getParams().getKeyUri();
                return KmsClients.get(keyUri).getAead(keyUri);
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.REMOTE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(KmsAeadKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KmsAeadKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return KmsAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, KmsAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsAeadKeyFormat, KmsAeadKey>(KmsAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(KmsAeadKeyFormat format) throws GeneralSecurityException {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public KmsAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return KmsAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public KmsAeadKey createKey(KmsAeadKeyFormat format) throws GeneralSecurityException {
                return (KmsAeadKey) KmsAeadKey.newBuilder().setParams(format).setVersion(KmsAeadKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsAeadKeyManager(), newKeyAllowed);
    }

    public static KeyTemplate createKeyTemplate(String kekUri) {
        return KeyTemplate.create(new KmsAeadKeyManager().getKeyType(), createKeyFormat(kekUri).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    static KmsAeadKeyFormat createKeyFormat(String keyUri) {
        return (KmsAeadKeyFormat) KmsAeadKeyFormat.newBuilder().setKeyUri(keyUri).build();
    }
}
