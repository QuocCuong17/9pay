package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class EcdsaVerifyKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.EcdsaPublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    public EcdsaVerifyKeyManager() {
        super(com.google.crypto.tink.proto.EcdsaPublicKey.class, new PrimitiveFactory<PublicKeyVerify, com.google.crypto.tink.proto.EcdsaPublicKey>(PublicKeyVerify.class) { // from class: com.google.crypto.tink.signature.EcdsaVerifyKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public PublicKeyVerify getPrimitive(com.google.crypto.tink.proto.EcdsaPublicKey keyProto) throws GeneralSecurityException {
                return new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(SigUtil.toCurveType(keyProto.getParams().getCurve()), keyProto.getX().toByteArray(), keyProto.getY().toByteArray()), SigUtil.toHashType(keyProto.getParams().getHashType()), SigUtil.toEcdsaEncoding(keyProto.getParams().getEncoding()));
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.EcdsaPublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.EcdsaPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.EcdsaPublicKey pubKey) throws GeneralSecurityException {
        Validators.validateVersion(pubKey.getVersion(), getVersion());
        SigUtil.validateEcdsaParams(pubKey.getParams());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }
}
