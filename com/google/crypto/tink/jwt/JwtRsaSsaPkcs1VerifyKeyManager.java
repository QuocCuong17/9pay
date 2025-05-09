package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.jwt.JwtFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce;
import com.google.crypto.tink.subtle.Validators;
import com.google.gson.JsonObject;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Optional;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class JwtRsaSsaPkcs1VerifyKeyManager extends KeyTypeManager<JwtRsaSsaPkcs1PublicKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;

        static {
            int[] iArr = new int[JwtRsaSsaPkcs1Algorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = iArr;
            try {
                iArr[JwtRsaSsaPkcs1Algorithm.RS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static Enums.HashType hashForPkcs1Algorithm(JwtRsaSsaPkcs1Algorithm algorithm) throws GeneralSecurityException {
        int i = AnonymousClass2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[algorithm.ordinal()];
        if (i == 1) {
            return Enums.HashType.SHA256;
        }
        if (i == 2) {
            return Enums.HashType.SHA384;
        }
        if (i == 3) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("unknown algorithm " + algorithm.name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPublicKey createPublicKey(JwtRsaSsaPkcs1PublicKey keyProto) throws GeneralSecurityException {
        return (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, keyProto.getN().toByteArray()), new BigInteger(1, keyProto.getE().toByteArray())));
    }

    public JwtRsaSsaPkcs1VerifyKeyManager() {
        super(JwtRsaSsaPkcs1PublicKey.class, new PrimitiveFactory<JwtPublicKeyVerifyInternal, JwtRsaSsaPkcs1PublicKey>(JwtPublicKeyVerifyInternal.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public JwtPublicKeyVerifyInternal getPrimitive(JwtRsaSsaPkcs1PublicKey keyProto) throws GeneralSecurityException {
                final Optional empty;
                final RsaSsaPkcs1VerifyJce rsaSsaPkcs1VerifyJce = new RsaSsaPkcs1VerifyJce(JwtRsaSsaPkcs1VerifyKeyManager.createPublicKey(keyProto), JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(keyProto.getAlgorithm()));
                final String name = keyProto.getAlgorithm().name();
                if (keyProto.hasCustomKid()) {
                    empty = Optional.of(keyProto.getCustomKid().getValue());
                } else {
                    empty = Optional.empty();
                }
                return new JwtPublicKeyVerifyInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.1.1
                    @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String compact, JwtValidator validator, Optional<String> kid) throws GeneralSecurityException {
                        JwtFormat.Parts splitSignedCompact = JwtFormat.splitSignedCompact(compact);
                        rsaSsaPkcs1VerifyJce.verify(splitSignedCompact.signatureOrMac, splitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        JsonObject parseJson = JsonUtil.parseJson(splitSignedCompact.header);
                        JwtFormat.validateHeader(name, kid, empty, parseJson);
                        return validator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parseJson), splitSignedCompact.payload));
                    }
                };
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPkcs1PublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPkcs1PublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPkcs1PublicKey pubKey) throws GeneralSecurityException {
        Validators.validateVersion(pubKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, pubKey.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, pubKey.getE().toByteArray()));
    }
}
