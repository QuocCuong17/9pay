package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.XChaCha20Poly1305;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class XChaCha20Poly1305KeyManager extends KeyTypeManager<com.google.crypto.tink.proto.XChaCha20Poly1305Key> {
    private static final int KEY_SIZE_IN_BYTES = 32;

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XChaCha20Poly1305KeyManager() {
        super(com.google.crypto.tink.proto.XChaCha20Poly1305Key.class, new PrimitiveFactory<Aead, com.google.crypto.tink.proto.XChaCha20Poly1305Key>(Aead.class) { // from class: com.google.crypto.tink.aead.XChaCha20Poly1305KeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(com.google.crypto.tink.proto.XChaCha20Poly1305Key key) throws GeneralSecurityException {
                return new XChaCha20Poly1305(key.getKeyValue().toByteArray());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.XChaCha20Poly1305Key key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.XChaCha20Poly1305Key parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.XChaCha20Poly1305Key.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, com.google.crypto.tink.proto.XChaCha20Poly1305Key> keyFactory() {
        return new KeyTypeManager.KeyFactory<XChaCha20Poly1305KeyFormat, com.google.crypto.tink.proto.XChaCha20Poly1305Key>(XChaCha20Poly1305KeyFormat.class) { // from class: com.google.crypto.tink.aead.XChaCha20Poly1305KeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(XChaCha20Poly1305KeyFormat format) throws GeneralSecurityException {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public XChaCha20Poly1305KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return XChaCha20Poly1305KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.XChaCha20Poly1305Key createKey(XChaCha20Poly1305KeyFormat format) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.XChaCha20Poly1305Key) com.google.crypto.tink.proto.XChaCha20Poly1305Key.newBuilder().setVersion(XChaCha20Poly1305KeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(Random.randBytes(32))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.XChaCha20Poly1305Key deriveKey(XChaCha20Poly1305KeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), XChaCha20Poly1305KeyManager.this.getVersion());
                byte[] bArr = new byte[32];
                try {
                    readFully(inputStream, bArr);
                    return (com.google.crypto.tink.proto.XChaCha20Poly1305Key) com.google.crypto.tink.proto.XChaCha20Poly1305Key.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(XChaCha20Poly1305KeyManager.this.getVersion()).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<XChaCha20Poly1305KeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("XCHACHA20_POLY1305", new KeyTypeManager.KeyFactory.KeyFormat(XChaCha20Poly1305KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("XCHACHA20_POLY1305_RAW", new KeyTypeManager.KeyFactory.KeyFormat(XChaCha20Poly1305KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new XChaCha20Poly1305KeyManager(), newKeyAllowed);
        XChaCha20Poly1305ProtoSerialization.register();
    }

    public static final KeyTemplate xChaCha20Poly1305Template() {
        return KeyTemplate.create(new XChaCha20Poly1305KeyManager().getKeyType(), XChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawXChaCha20Poly1305Template() {
        return KeyTemplate.create(new XChaCha20Poly1305KeyManager().getKeyType(), XChaCha20Poly1305KeyFormat.getDefaultInstance().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }
}
