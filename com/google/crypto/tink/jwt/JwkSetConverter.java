package com.google.crypto.tink.jwt;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Optional;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class JwkSetConverter {
    private static final String JWT_ECDSA_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    private static final String JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    private static final String JWT_RSA_SSA_PSS_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";

    /* JADX WARN: Failed to find 'out' block for switch in B:19:0x0058. Please report as an issue. */
    public static String fromPublicKeysetHandle(KeysetHandle handle) throws IOException, GeneralSecurityException {
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < handle.size(); i++) {
            KeysetHandle.Entry at = handle.getAt(i);
            if (at.getStatus() == KeyStatus.ENABLED) {
                Key key = at.getKey();
                if (!(key instanceof LegacyProtoKey)) {
                    throw new GeneralSecurityException("only LegacyProtoKey is currently supported");
                }
                ProtoKeySerialization serialization = ((LegacyProtoKey) key).getSerialization(null);
                if (serialization.getOutputPrefixType() != OutputPrefixType.RAW && serialization.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("only OutputPrefixType RAW and TINK are supported");
                }
                if (serialization.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    throw new GeneralSecurityException("only public keys can be converted");
                }
                String typeUrl = serialization.getTypeUrl();
                typeUrl.hashCode();
                char c = 65535;
                switch (typeUrl.hashCode()) {
                    case -1204668709:
                        if (typeUrl.equals(JWT_ECDSA_PUBLIC_KEY_URL)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 516334794:
                        if (typeUrl.equals(JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1174255008:
                        if (typeUrl.equals(JWT_RSA_SSA_PSS_PUBLIC_KEY_URL)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        jsonArray.add(convertJwtEcdsaKey(serialization));
                        break;
                    case 1:
                        jsonArray.add(convertJwtRsaSsaPkcs1(serialization));
                        break;
                    case 2:
                        jsonArray.add(convertJwtRsaSsaPss(serialization));
                        break;
                    default:
                        throw new GeneralSecurityException(String.format("key type %s is not supported", serialization.getTypeUrl()));
                }
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(UserMetadata.KEYDATA_FILENAME, jsonArray);
        return jsonObject.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0056, code lost:
    
        if (r4.equals("RS") == false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static KeysetHandle toPublicKeysetHandle(String jwkSet) throws IOException, GeneralSecurityException {
        ProtoKeySerialization convertToEcdsaKey;
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(jwkSet));
            jsonReader.setLenient(false);
            JsonObject asJsonObject = Streams.parse(jsonReader).getAsJsonObject();
            KeysetHandle.Builder newBuilder = KeysetHandle.newBuilder();
            Iterator<JsonElement> it = asJsonObject.get(UserMetadata.KEYDATA_FILENAME).getAsJsonArray().iterator();
            while (it.hasNext()) {
                JsonObject asJsonObject2 = it.next().getAsJsonObject();
                char c = 2;
                String substring = getStringItem(asJsonObject2, "alg").substring(0, 2);
                substring.hashCode();
                switch (substring.hashCode()) {
                    case 2222:
                        if (substring.equals("ES")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 2563:
                        if (substring.equals("PS")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 2625:
                        break;
                }
                c = 65535;
                switch (c) {
                    case 0:
                        convertToEcdsaKey = convertToEcdsaKey(asJsonObject2);
                        break;
                    case 1:
                        convertToEcdsaKey = convertToRsaSsaPssKey(asJsonObject2);
                        break;
                    case 2:
                        convertToEcdsaKey = convertToRsaSsaPkcs1Key(asJsonObject2);
                        break;
                    default:
                        throw new GeneralSecurityException("unexpected alg value: " + getStringItem(asJsonObject2, "alg"));
                }
                newBuilder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(convertToEcdsaKey, null)).withRandomId());
            }
            if (newBuilder.size() <= 0) {
                throw new GeneralSecurityException("empty keyset");
            }
            newBuilder.getAt(0).makePrimary();
            return newBuilder.build();
        } catch (JsonParseException | IllegalStateException | StackOverflowError e) {
            throw new GeneralSecurityException("JWK set is invalid JSON", e);
        }
    }

    private static Optional<String> getKid(@Nullable Integer idRequirement) {
        if (idRequirement == null) {
            return Optional.empty();
        }
        return Optional.of(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(idRequirement.intValue()).array()));
    }

    private static JsonObject convertJwtEcdsaKey(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        String str2;
        try {
            JwtEcdsaPublicKey parseFrom = JwtEcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[parseFrom.getAlgorithm().ordinal()];
            if (i == 1) {
                str = "ES256";
                str2 = "P-256";
            } else if (i == 2) {
                str = "ES384";
                str2 = "P-384";
            } else {
                if (i != 3) {
                    throw new GeneralSecurityException("unknown algorithm");
                }
                str = "ES512";
                str2 = "P-521";
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kty", "EC");
            jsonObject.addProperty("crv", str2);
            jsonObject.addProperty(ViewHierarchyNode.JsonKeys.X, Base64.urlSafeEncode(parseFrom.getX().toByteArray()));
            jsonObject.addProperty(ViewHierarchyNode.JsonKeys.Y, Base64.urlSafeEncode(parseFrom.getY().toByteArray()));
            jsonObject.addProperty("use", "sig");
            jsonObject.addProperty("alg", str);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add("verify");
            jsonObject.add("key_ops", jsonArray);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonObject.addProperty("kid", kid.get());
            } else if (parseFrom.hasCustomKid()) {
                jsonObject.addProperty("kid", parseFrom.getCustomKid().getValue());
            }
            return jsonObject;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtEcdsaPublicKey proto", e);
        }
    }

    private static JsonObject convertJwtRsaSsaPkcs1(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        try {
            JwtRsaSsaPkcs1PublicKey parseFrom = JwtRsaSsaPkcs1PublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[parseFrom.getAlgorithm().ordinal()];
            if (i == 1) {
                str = "RS256";
            } else if (i == 2) {
                str = "RS384";
            } else {
                if (i != 3) {
                    throw new GeneralSecurityException("unknown algorithm");
                }
                str = "RS512";
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kty", "RSA");
            jsonObject.addProperty("n", Base64.urlSafeEncode(parseFrom.getN().toByteArray()));
            jsonObject.addProperty("e", Base64.urlSafeEncode(parseFrom.getE().toByteArray()));
            jsonObject.addProperty("use", "sig");
            jsonObject.addProperty("alg", str);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add("verify");
            jsonObject.add("key_ops", jsonArray);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonObject.addProperty("kid", kid.get());
            } else if (parseFrom.hasCustomKid()) {
                jsonObject.addProperty("kid", parseFrom.getCustomKid().getValue());
            }
            return jsonObject;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPkcs1PublicKey proto", e);
        }
    }

    private static JsonObject convertJwtRsaSsaPss(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        try {
            JwtRsaSsaPssPublicKey parseFrom = JwtRsaSsaPssPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[parseFrom.getAlgorithm().ordinal()];
            if (i == 1) {
                str = "PS256";
            } else if (i == 2) {
                str = "PS384";
            } else {
                if (i != 3) {
                    throw new GeneralSecurityException("unknown algorithm");
                }
                str = "PS512";
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kty", "RSA");
            jsonObject.addProperty("n", Base64.urlSafeEncode(parseFrom.getN().toByteArray()));
            jsonObject.addProperty("e", Base64.urlSafeEncode(parseFrom.getE().toByteArray()));
            jsonObject.addProperty("use", "sig");
            jsonObject.addProperty("alg", str);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add("verify");
            jsonObject.add("key_ops", jsonArray);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonObject.addProperty("kid", kid.get());
            } else if (parseFrom.hasCustomKid()) {
                jsonObject.addProperty("kid", parseFrom.getCustomKid().getValue());
            }
            return jsonObject;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPssPublicKey proto", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.jwt.JwkSetConverter$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm;

        static {
            int[] iArr = new int[JwtRsaSsaPssAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm = iArr;
            try {
                iArr[JwtRsaSsaPssAlgorithm.PS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[JwtRsaSsaPkcs1Algorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = iArr2;
            try {
                iArr2[JwtRsaSsaPkcs1Algorithm.RS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[JwtEcdsaAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = iArr3;
            try {
                iArr3[JwtEcdsaAlgorithm.ES256.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES384.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES512.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private static String getStringItem(JsonObject obj, String name) throws GeneralSecurityException {
        if (!obj.has(name)) {
            throw new GeneralSecurityException(name + " not found");
        }
        if (!obj.get(name).isJsonPrimitive() || !obj.get(name).getAsJsonPrimitive().isString()) {
            throw new GeneralSecurityException(name + " is not a string");
        }
        return obj.get(name).getAsString();
    }

    private static void expectStringItem(JsonObject obj, String name, String expectedValue) throws GeneralSecurityException {
        String stringItem = getStringItem(obj, name);
        if (stringItem.equals(expectedValue)) {
            return;
        }
        throw new GeneralSecurityException("unexpected " + name + " value: " + stringItem);
    }

    private static void validateUseIsSig(JsonObject jsonKey) throws GeneralSecurityException {
        if (jsonKey.has("use")) {
            expectStringItem(jsonKey, "use", "sig");
        }
    }

    private static void validateKeyOpsIsVerify(JsonObject jsonKey) throws GeneralSecurityException {
        if (jsonKey.has("key_ops")) {
            if (!jsonKey.get("key_ops").isJsonArray()) {
                throw new GeneralSecurityException("key_ops is not an array");
            }
            JsonArray asJsonArray = jsonKey.get("key_ops").getAsJsonArray();
            if (asJsonArray.size() != 1) {
                throw new GeneralSecurityException("key_ops must contain exactly one element");
            }
            if (!asJsonArray.get(0).isJsonPrimitive() || !asJsonArray.get(0).getAsJsonPrimitive().isString()) {
                throw new GeneralSecurityException("key_ops is not a string");
            }
            if (asJsonArray.get(0).getAsString().equals("verify")) {
                return;
            }
            throw new GeneralSecurityException("unexpected keyOps value: " + asJsonArray.get(0).getAsString());
        }
    }

    private static ProtoKeySerialization convertToRsaSsaPkcs1Key(JsonObject jsonKey) throws GeneralSecurityException {
        JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm;
        String stringItem = getStringItem(jsonKey, "alg");
        stringItem.hashCode();
        char c = 65535;
        switch (stringItem.hashCode()) {
            case 78251122:
                if (stringItem.equals("RS256")) {
                    c = 0;
                    break;
                }
                break;
            case 78252174:
                if (stringItem.equals("RS384")) {
                    c = 1;
                    break;
                }
                break;
            case 78253877:
                if (stringItem.equals("RS512")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS256;
                break;
            case 1:
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS384;
                break;
            case 2:
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(jsonKey, "alg"));
        }
        if (jsonKey.has(TtmlNode.TAG_P) || jsonKey.has("q") || jsonKey.has("dp") || jsonKey.has("dq") || jsonKey.has("d") || jsonKey.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(jsonKey, "kty", "RSA");
        validateUseIsSig(jsonKey);
        validateKeyOpsIsVerify(jsonKey);
        JwtRsaSsaPkcs1PublicKey.Builder n = JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPkcs1Algorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "n"))));
        if (jsonKey.has("kid")) {
            n.setCustomKid((JwtRsaSsaPkcs1PublicKey.CustomKid) JwtRsaSsaPkcs1PublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonKey, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL, ((JwtRsaSsaPkcs1PublicKey) n.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static ProtoKeySerialization convertToRsaSsaPssKey(JsonObject jsonKey) throws GeneralSecurityException {
        JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm;
        String stringItem = getStringItem(jsonKey, "alg");
        stringItem.hashCode();
        char c = 65535;
        switch (stringItem.hashCode()) {
            case 76404080:
                if (stringItem.equals("PS256")) {
                    c = 0;
                    break;
                }
                break;
            case 76405132:
                if (stringItem.equals("PS384")) {
                    c = 1;
                    break;
                }
                break;
            case 76406835:
                if (stringItem.equals("PS512")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS256;
                break;
            case 1:
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS384;
                break;
            case 2:
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(jsonKey, "alg"));
        }
        if (jsonKey.has(TtmlNode.TAG_P) || jsonKey.has("q") || jsonKey.has("dq") || jsonKey.has("dq") || jsonKey.has("d") || jsonKey.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(jsonKey, "kty", "RSA");
        validateUseIsSig(jsonKey);
        validateKeyOpsIsVerify(jsonKey);
        JwtRsaSsaPssPublicKey.Builder n = JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPssAlgorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "n"))));
        if (jsonKey.has("kid")) {
            n.setCustomKid((JwtRsaSsaPssPublicKey.CustomKid) JwtRsaSsaPssPublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonKey, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PSS_PUBLIC_KEY_URL, ((JwtRsaSsaPssPublicKey) n.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static ProtoKeySerialization convertToEcdsaKey(JsonObject jsonKey) throws GeneralSecurityException {
        JwtEcdsaAlgorithm jwtEcdsaAlgorithm;
        String stringItem = getStringItem(jsonKey, "alg");
        stringItem.hashCode();
        char c = 65535;
        switch (stringItem.hashCode()) {
            case 66245349:
                if (stringItem.equals("ES256")) {
                    c = 0;
                    break;
                }
                break;
            case 66246401:
                if (stringItem.equals("ES384")) {
                    c = 1;
                    break;
                }
                break;
            case 66248104:
                if (stringItem.equals("ES512")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                expectStringItem(jsonKey, "crv", "P-256");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES256;
                break;
            case 1:
                expectStringItem(jsonKey, "crv", "P-384");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES384;
                break;
            case 2:
                expectStringItem(jsonKey, "crv", "P-521");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Ecdsa Algorithm: " + getStringItem(jsonKey, "alg"));
        }
        if (jsonKey.has("d")) {
            throw new UnsupportedOperationException("importing ECDSA private keys is not implemented");
        }
        expectStringItem(jsonKey, "kty", "EC");
        validateUseIsSig(jsonKey);
        validateKeyOpsIsVerify(jsonKey);
        JwtEcdsaPublicKey.Builder y = JwtEcdsaPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtEcdsaAlgorithm).setX(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, ViewHierarchyNode.JsonKeys.X)))).setY(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, ViewHierarchyNode.JsonKeys.Y))));
        if (jsonKey.has("kid")) {
            y.setCustomKid((JwtEcdsaPublicKey.CustomKid) JwtEcdsaPublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonKey, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_ECDSA_PUBLIC_KEY_URL, ((JwtEcdsaPublicKey) y.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    @Deprecated
    public static String fromKeysetHandle(KeysetHandle handle, KeyAccess keyAccess) throws IOException, GeneralSecurityException {
        return fromPublicKeysetHandle(handle);
    }

    @Deprecated
    public static KeysetHandle toKeysetHandle(String jwkSet, KeyAccess keyAccess) throws IOException, GeneralSecurityException {
        return toPublicKeysetHandle(jwkSet);
    }

    private JwkSetConverter() {
    }
}
