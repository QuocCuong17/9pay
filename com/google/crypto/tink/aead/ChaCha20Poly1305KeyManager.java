package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.ChaCha20Poly1305;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ChaCha20Poly1305KeyManager extends KeyTypeManager<com.google.crypto.tink.proto.ChaCha20Poly1305Key> {
    private static final int KEY_SIZE_IN_BYTES = 32;

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChaCha20Poly1305KeyManager() {
        super(com.google.crypto.tink.proto.ChaCha20Poly1305Key.class, new PrimitiveFactory<Aead, com.google.crypto.tink.proto.ChaCha20Poly1305Key>(Aead.class) { // from class: com.google.crypto.tink.aead.ChaCha20Poly1305KeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(com.google.crypto.tink.proto.ChaCha20Poly1305Key key) throws GeneralSecurityException {
                return new ChaCha20Poly1305(key.getKeyValue().toByteArray());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.ChaCha20Poly1305Key key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.ChaCha20Poly1305Key parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.ChaCha20Poly1305Key.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.ChaCha20Poly1305Key> keyFactory() {
        return new KeyTypeManager.KeyFactory<ChaCha20Poly1305KeyFormat, com.google.crypto.tink.proto.ChaCha20Poly1305Key>(ChaCha20Poly1305KeyFormat.class) { // from class: com.google.crypto.tink.aead.ChaCha20Poly1305KeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(ChaCha20Poly1305KeyFormat format) throws GeneralSecurityException {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public ChaCha20Poly1305KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return ChaCha20Poly1305KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.ChaCha20Poly1305Key createKey(ChaCha20Poly1305KeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.ChaCha20Poly1305Key) com.google.crypto.tink.proto.ChaCha20Poly1305Key.newBuilder().setVersion(ChaCha20Poly1305KeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(Random.randBytes(32))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<ChaCha20Poly1305KeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("CHACHA20_POLY1305", new KeyTypeManager.KeyFactory.KeyFormat(ChaCha20Poly1305KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("CHACHA20_POLY1305_RAW", new KeyTypeManager.KeyFactory.KeyFormat(ChaCha20Poly1305KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new ChaCha20Poly1305KeyManager(), newKeyAllowed);
        ChaCha20Poly1305ProtoSerialization.register();
    }

    public static final KeyTemplate chaCha20Poly1305Template() {
        return KeyTemplate.create(new ChaCha20Poly1305KeyManager().getKeyType(), ChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawChaCha20Poly1305Template() {
        return KeyTemplate.create(new ChaCha20Poly1305KeyManager().getKeyType(), ChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
