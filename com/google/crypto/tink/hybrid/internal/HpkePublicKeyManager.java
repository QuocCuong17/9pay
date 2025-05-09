package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: classes4.dex */
public final class HpkePublicKeyManager extends KeyTypeManager<HpkePublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    public HpkePublicKeyManager() {
        super(HpkePublicKey.class, new PrimitiveFactory<HybridEncrypt, HpkePublicKey>(HybridEncrypt.class) { // from class: com.google.crypto.tink.hybrid.internal.HpkePublicKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public HybridEncrypt getPrimitive(HpkePublicKey recipientPublicKey) throws GeneralSecurityException {
                return HpkeEncrypt.createHpkeEncrypt(recipientPublicKey);
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public HpkePublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HpkePublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(HpkePublicKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (!key.hasParams()) {
            throw new GeneralSecurityException("Missing HPKE key params.");
        }
        HpkeUtil.validateParams(key.getParams());
    }
}
