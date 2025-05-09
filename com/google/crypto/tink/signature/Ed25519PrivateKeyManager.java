package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.Ed25519KeyFormat;
import com.google.crypto.tink.proto.Ed25519PrivateKey;
import com.google.crypto.tink.proto.Ed25519PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Ed25519Sign;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class Ed25519PrivateKeyManager extends PrivateKeyTypeManager<Ed25519PrivateKey, Ed25519PublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ed25519PrivateKeyManager() {
        super(Ed25519PrivateKey.class, Ed25519PublicKey.class, new PrimitiveFactory<PublicKeySign, Ed25519PrivateKey>(PublicKeySign.class) { // from class: com.google.crypto.tink.signature.Ed25519PrivateKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public PublicKeySign getPrimitive(Ed25519PrivateKey keyProto) throws GeneralSecurityException {
                return new Ed25519Sign(keyProto.getKeyValue().toByteArray());
            }
        });
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public Ed25519PublicKey getPublicKey(Ed25519PrivateKey key) throws GeneralSecurityException {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public Ed25519PrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return Ed25519PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(Ed25519PrivateKey keyProto) throws GeneralSecurityException {
        Validators.validateVersion(keyProto.getVersion(), getVersion());
        new Ed25519PublicKeyManager().validateKey(keyProto.getPublicKey());
        if (keyProto.getKeyValue().size() != 32) {
            throw new GeneralSecurityException("invalid Ed25519 private key: incorrect key length");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<Ed25519KeyFormat, Ed25519PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<Ed25519KeyFormat, Ed25519PrivateKey>(Ed25519KeyFormat.class) { // from class: com.google.crypto.tink.signature.Ed25519PrivateKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(Ed25519KeyFormat format) throws GeneralSecurityException {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Ed25519KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return Ed25519KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Ed25519PrivateKey createKey(Ed25519KeyFormat format) throws GeneralSecurityException {
                Ed25519Sign.KeyPair newKeyPair = Ed25519Sign.KeyPair.newKeyPair();
                return (Ed25519PrivateKey) Ed25519PrivateKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPair.getPrivateKey())).setPublicKey((Ed25519PublicKey) Ed25519PublicKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPair.getPublicKey())).build()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Ed25519PrivateKey deriveKey(Ed25519KeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(format.getVersion(), Ed25519PrivateKeyManager.this.getVersion());
                byte[] bArr = new byte[32];
                try {
                    readFully(inputStream, bArr);
                    Ed25519Sign.KeyPair newKeyPairFromSeed = Ed25519Sign.KeyPair.newKeyPairFromSeed(bArr);
                    return (Ed25519PrivateKey) Ed25519PrivateKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPairFromSeed.getPrivateKey())).setPublicKey((Ed25519PublicKey) Ed25519PublicKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPairFromSeed.getPublicKey())).build()).build();
                } catch (IOException e) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<Ed25519KeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("ED25519", new KeyTypeManager.KeyFactory.KeyFormat(Ed25519KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("ED25519_RAW", new KeyTypeManager.KeyFactory.KeyFormat(Ed25519KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("ED25519WithRawOutput", new KeyTypeManager.KeyFactory.KeyFormat(Ed25519KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new Ed25519PrivateKeyManager(), new Ed25519PublicKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate ed25519Template() {
        return KeyTemplate.create(new Ed25519PrivateKeyManager().getKeyType(), new byte[0], KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawEd25519Template() {
        return KeyTemplate.create(new Ed25519PrivateKeyManager().getKeyType(), new byte[0], KeyTemplate.OutputPrefixType.RAW);
    }
}
