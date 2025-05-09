package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.mac.HmacParameters;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class HmacProtoSerialization {
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER;
    private static final KeySerializer<HmacKey, ProtoKeySerialization> KEY_SERIALIZER;
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER;
    private static final ParametersSerializer<HmacParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HmacKey";
    private static final Bytes TYPE_URL_BYTES;

    static {
        Bytes bytesFromPrintableAscii = Util.toBytesFromPrintableAscii(TYPE_URL);
        TYPE_URL_BYTES = bytesFromPrintableAscii;
        PARAMETERS_SERIALIZER = ParametersSerializer.create(new ParametersSerializer.ParametersSerializationFunction() { // from class: com.google.crypto.tink.mac.HmacProtoSerialization$$ExternalSyntheticLambda3
            @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
            public final Serialization serializeParameters(Parameters parameters) {
                ProtoParametersSerialization serializeParameters;
                serializeParameters = HmacProtoSerialization.serializeParameters((HmacParameters) parameters);
                return serializeParameters;
            }
        }, HmacParameters.class, ProtoParametersSerialization.class);
        PARAMETERS_PARSER = ParametersParser.create(new ParametersParser.ParametersParsingFunction() { // from class: com.google.crypto.tink.mac.HmacProtoSerialization$$ExternalSyntheticLambda2
            @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
            public final Parameters parseParameters(Serialization serialization) {
                HmacParameters parseParameters;
                parseParameters = HmacProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
                return parseParameters;
            }
        }, bytesFromPrintableAscii, ProtoParametersSerialization.class);
        KEY_SERIALIZER = KeySerializer.create(new KeySerializer.KeySerializationFunction() { // from class: com.google.crypto.tink.mac.HmacProtoSerialization$$ExternalSyntheticLambda1
            @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
            public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
                ProtoKeySerialization serializeKey;
                serializeKey = HmacProtoSerialization.serializeKey((HmacKey) key, secretKeyAccess);
                return serializeKey;
            }
        }, HmacKey.class, ProtoKeySerialization.class);
        KEY_PARSER = KeyParser.create(new KeyParser.KeyParsingFunction() { // from class: com.google.crypto.tink.mac.HmacProtoSerialization$$ExternalSyntheticLambda0
            @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
            public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
                HmacKey parseKey;
                parseKey = HmacProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
                return parseKey;
            }
        }, bytesFromPrintableAscii, ProtoKeySerialization.class);
    }

    private static OutputPrefixType toProtoOutputPrefixType(HmacParameters.Variant variant) throws GeneralSecurityException {
        if (HmacParameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (HmacParameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (HmacParameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        if (HmacParameters.Variant.LEGACY.equals(variant)) {
            return OutputPrefixType.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static HashType toProtoHashType(HmacParameters.HashType hashType) throws GeneralSecurityException {
        if (HmacParameters.HashType.SHA1.equals(hashType)) {
            return HashType.SHA1;
        }
        if (HmacParameters.HashType.SHA224.equals(hashType)) {
            return HashType.SHA224;
        }
        if (HmacParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (HmacParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (HmacParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    private static HmacParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i == 1) {
            return HmacParameters.HashType.SHA1;
        }
        if (i == 2) {
            return HmacParameters.HashType.SHA224;
        }
        if (i == 3) {
            return HmacParameters.HashType.SHA256;
        }
        if (i == 4) {
            return HmacParameters.HashType.SHA384;
        }
        if (i == 5) {
            return HmacParameters.HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.mac.HmacProtoSerialization$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr2;
            try {
                iArr2[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private static HmacParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i == 1) {
            return HmacParameters.Variant.TINK;
        }
        if (i == 2) {
            return HmacParameters.Variant.CRUNCHY;
        }
        if (i == 3) {
            return HmacParameters.Variant.LEGACY;
        }
        if (i == 4) {
            return HmacParameters.Variant.NO_PREFIX;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
    }

    private static HmacParams getProtoParams(HmacParameters parameters) throws GeneralSecurityException {
        return (HmacParams) HmacParams.newBuilder().setTagSize(parameters.getCryptographicTagSizeBytes()).setHash(toProtoHashType(parameters.getHashType())).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(HmacParameters parameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(((HmacKeyFormat) HmacKeyFormat.newBuilder().setParams(getProtoParams(parameters)).setKeySize(parameters.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(toProtoOutputPrefixType(parameters.getVariant())).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(HmacKey key, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        return ProtoKeySerialization.create(TYPE_URL, ((com.google.crypto.tink.proto.HmacKey) com.google.crypto.tink.proto.HmacKey.newBuilder().setParams(getProtoParams(key.getParameters())).setKeyValue(ByteString.copyFrom(key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(access)))).build()).toByteString(), KeyData.KeyMaterialType.SYMMETRIC, toProtoOutputPrefixType(key.getParameters().getVariant()), key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HmacParameters parseParameters(ProtoParametersSerialization serialization) throws GeneralSecurityException {
        if (!serialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: " + serialization.getKeyTemplate().getTypeUrl());
        }
        try {
            HmacKeyFormat parseFrom = HmacKeyFormat.parseFrom(serialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (parseFrom.getVersion() != 0) {
                throw new GeneralSecurityException("Parsing HmacParameters failed: unknown Version " + parseFrom.getVersion());
            }
            return HmacParameters.builder().setKeySizeBytes(parseFrom.getKeySize()).setTagSizeBytes(parseFrom.getParams().getTagSize()).setHashType(toHashType(parseFrom.getParams().getHash())).setVariant(toVariant(serialization.getKeyTemplate().getOutputPrefixType())).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("Parsing HmacParameters failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HmacKey parseKey(ProtoKeySerialization serialization, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        if (!serialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
        }
        try {
            com.google.crypto.tink.proto.HmacKey parseFrom = com.google.crypto.tink.proto.HmacKey.parseFrom(serialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (parseFrom.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return HmacKey.builder().setParameters(HmacParameters.builder().setKeySizeBytes(parseFrom.getKeyValue().size()).setTagSizeBytes(parseFrom.getParams().getTagSize()).setHashType(toHashType(parseFrom.getParams().getHash())).setVariant(toVariant(serialization.getOutputPrefixType())).build()).setKeyBytes(SecretBytes.copyFrom(parseFrom.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(access))).setIdRequirement(serialization.getIdRequirementOrNull()).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing HmacKey failed");
        }
    }

    public static void register() throws GeneralSecurityException {
        register(MutableSerializationRegistry.globalInstance());
    }

    public static void register(MutableSerializationRegistry registry) throws GeneralSecurityException {
        registry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        registry.registerParametersParser(PARAMETERS_PARSER);
        registry.registerKeySerializer(KEY_SERIALIZER);
        registry.registerKeyParser(KEY_PARSER);
    }

    private HmacProtoSerialization() {
    }
}
