package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.jwt.JwtFormat;
import com.google.crypto.tink.proto.JwtHmacAlgorithm;
import com.google.crypto.tink.proto.JwtHmacKey;
import com.google.crypto.tink.proto.JwtHmacKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.errorprone.annotations.Immutable;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public final class JwtHmacKeyManager extends KeyTypeManager<JwtHmacKey> {
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtHmacKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.jwt.JwtHmacKeyManager$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm;

        static {
            int[] iArr = new int[JwtHmacAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm = iArr;
            try {
                iArr[JwtHmacAlgorithm.HS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[JwtHmacAlgorithm.HS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[JwtHmacAlgorithm.HS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getAlgorithmName(JwtHmacAlgorithm algorithm) throws GeneralSecurityException {
        int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[algorithm.ordinal()];
        if (i == 1) {
            return "HS256";
        }
        if (i == 2) {
            return "HS384";
        }
        if (i == 3) {
            return "HS512";
        }
        throw new GeneralSecurityException("unknown algorithm");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getHmacAlgorithm(JwtHmacAlgorithm algorithm) throws GeneralSecurityException {
        int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[algorithm.ordinal()];
        if (i == 1) {
            return "HMACSHA256";
        }
        if (i == 2) {
            return "HMACSHA384";
        }
        if (i == 3) {
            return "HMACSHA512";
        }
        throw new GeneralSecurityException("unknown algorithm");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getMinimumKeySizeInBytes(JwtHmacAlgorithm algorithm) throws GeneralSecurityException {
        int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[algorithm.ordinal()];
        if (i == 1) {
            return 32;
        }
        if (i == 2) {
            return 48;
        }
        if (i == 3) {
            return 64;
        }
        throw new GeneralSecurityException("unknown algorithm");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes4.dex */
    public static final class JwtHmac implements JwtMacInternal {
        private final String algorithm;
        private final Optional<String> customKidFromHmacKey;
        private final PrfMac prfMac;

        public JwtHmac(String algorithm, Optional<String> customKidFromHmacKey, PrfMac prfMac) {
            this.algorithm = algorithm;
            this.customKidFromHmacKey = customKidFromHmacKey;
            this.prfMac = prfMac;
        }

        @Override // com.google.crypto.tink.jwt.JwtMacInternal
        public String computeMacAndEncodeWithKid(RawJwt rawJwt, Optional<String> kid) throws GeneralSecurityException {
            if (this.customKidFromHmacKey.isPresent()) {
                if (kid.isPresent()) {
                    throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                }
                kid = this.customKidFromHmacKey;
            }
            String createUnsignedCompact = JwtFormat.createUnsignedCompact(this.algorithm, kid, rawJwt);
            return JwtFormat.createSignedCompact(createUnsignedCompact, this.prfMac.computeMac(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
        }

        @Override // com.google.crypto.tink.jwt.JwtMacInternal
        public VerifiedJwt verifyMacAndDecodeWithKid(String compact, JwtValidator validator, Optional<String> kid) throws GeneralSecurityException {
            JwtFormat.Parts splitSignedCompact = JwtFormat.splitSignedCompact(compact);
            this.prfMac.verifyMac(splitSignedCompact.signatureOrMac, splitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
            JsonObject parseJson = JsonUtil.parseJson(splitSignedCompact.header);
            JwtFormat.validateHeader(this.algorithm, kid, this.customKidFromHmacKey, parseJson);
            return validator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parseJson), splitSignedCompact.payload));
        }
    }

    public JwtHmacKeyManager() {
        super(JwtHmacKey.class, new PrimitiveFactory<JwtMacInternal, JwtHmacKey>(JwtMacInternal.class) { // from class: com.google.crypto.tink.jwt.JwtHmacKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public JwtMacInternal getPrimitive(JwtHmacKey key) throws GeneralSecurityException {
                JwtHmacAlgorithm algorithm = key.getAlgorithm();
                PrfHmacJce prfHmacJce = new PrfHmacJce(JwtHmacKeyManager.getHmacAlgorithm(algorithm), new SecretKeySpec(key.getKeyValue().toByteArray(), "HMAC"));
                return new JwtHmac(JwtHmacKeyManager.getAlgorithmName(algorithm), key.hasCustomKid() ? Optional.of(key.getCustomKid().getValue()) : Optional.empty(), new PrfMac(prfHmacJce, prfHmacJce.getMaxOutputLength()));
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtHmacKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() < getMinimumKeySizeInBytes(key.getAlgorithm())) {
            throw new GeneralSecurityException("key too short");
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtHmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtHmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<?, JwtHmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtHmacKeyFormat, JwtHmacKey>(JwtHmacKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtHmacKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtHmacKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() < JwtHmacKeyManager.getMinimumKeySizeInBytes(format.getAlgorithm())) {
                    throw new GeneralSecurityException("key too short");
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtHmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtHmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtHmacKey createKey(JwtHmacKeyFormat format) {
                return (JwtHmacKey) JwtHmacKey.newBuilder().setVersion(JwtHmacKeyManager.this.getVersion()).setAlgorithm(format.getAlgorithm()).setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtHmacKey deriveKey(JwtHmacKeyFormat format, InputStream inputStream) throws GeneralSecurityException {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("JWT_HS256_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS256, 32, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_HS256", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS256, 32, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_HS384_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS384, 48, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_HS384", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS384, 48, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("JWT_HS512_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS512, 64, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("JWT_HS512", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS512, 64, KeyTemplate.OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new JwtHmacKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate hs256Template() {
        return createTemplate(JwtHmacAlgorithm.HS256, 32);
    }

    public static final KeyTemplate hs384Template() {
        return createTemplate(JwtHmacAlgorithm.HS384, 48);
    }

    public static final KeyTemplate hs512Template() {
        return createTemplate(JwtHmacAlgorithm.HS512, 64);
    }

    private static KeyTemplate createTemplate(JwtHmacAlgorithm algorithm, int keySize) {
        return KeyTemplate.create(new JwtHmacKeyManager().getKeyType(), ((JwtHmacKeyFormat) JwtHmacKeyFormat.newBuilder().setAlgorithm(algorithm).setKeySize(keySize).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat> createKeyFormat(JwtHmacAlgorithm algorithm, int keySize, KeyTemplate.OutputPrefixType prefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtHmacKeyFormat) JwtHmacKeyFormat.newBuilder().setAlgorithm(algorithm).setKeySize(keySize).build(), prefixType);
    }
}
