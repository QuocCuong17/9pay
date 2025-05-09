package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKey;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public class KmsEnvelopeAeadKeyManager extends KeyTypeManager<KmsEnvelopeAeadKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KmsEnvelopeAeadKeyManager() {
        super(KmsEnvelopeAeadKey.class, new PrimitiveFactory<Aead, KmsEnvelopeAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(KmsEnvelopeAeadKey keyProto) throws GeneralSecurityException {
                String kekUri = keyProto.getParams().getKekUri();
                return new KmsEnvelopeAead(keyProto.getParams().getDekTemplate(), KmsClients.get(kekUri).getAead(kekUri));
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.REMOTE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(KmsEnvelopeAeadKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KmsEnvelopeAeadKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return KmsEnvelopeAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, KmsEnvelopeAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsEnvelopeAeadKeyFormat, KmsEnvelopeAeadKey>(KmsEnvelopeAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(KmsEnvelopeAeadKeyFormat format) throws GeneralSecurityException {
                if (format.getKekUri().isEmpty() || !format.hasDekTemplate()) {
                    throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public KmsEnvelopeAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return KmsEnvelopeAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public KmsEnvelopeAeadKey createKey(KmsEnvelopeAeadKeyFormat format) throws GeneralSecurityException {
                return (KmsEnvelopeAeadKey) KmsEnvelopeAeadKey.newBuilder().setParams(format).setVersion(KmsEnvelopeAeadKeyManager.this.getVersion()).build();
            }
        };
    }

    public static KeyTemplate createKeyTemplate(String kekUri, KeyTemplate dekTemplate) {
        return KeyTemplate.create(new KmsEnvelopeAeadKeyManager().getKeyType(), createKeyFormat(kekUri, dekTemplate).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsEnvelopeAeadKeyManager(), newKeyAllowed);
    }

    static KmsEnvelopeAeadKeyFormat createKeyFormat(String kekUri, KeyTemplate dekTemplate) {
        return (KmsEnvelopeAeadKeyFormat) KmsEnvelopeAeadKeyFormat.newBuilder().setDekTemplate((com.google.crypto.tink.proto.KeyTemplate) com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(dekTemplate.getTypeUrl()).setValue(ByteString.copyFrom(dekTemplate.getValue())).build()).setKekUri(kekUri).build();
    }
}
