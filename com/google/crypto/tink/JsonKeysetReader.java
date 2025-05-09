package com.google.crypto.tink;

import com.google.crypto.tink.internal.JsonParser;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

/* loaded from: classes4.dex */
public final class JsonKeysetReader implements KeysetReader {
    private static final long MAX_KEY_ID = 4294967295L;
    private static final long MIN_KEY_ID = -2147483648L;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final InputStream inputStream;
    private boolean urlSafeBase64 = false;

    private JsonKeysetReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static JsonKeysetReader withInputStream(InputStream input) throws IOException {
        return new JsonKeysetReader(input);
    }

    @Deprecated
    public static JsonKeysetReader withJsonObject(Object input) {
        return withString(input.toString());
    }

    public static JsonKeysetReader withString(String input) {
        return new JsonKeysetReader(new ByteArrayInputStream(input.getBytes(UTF_8)));
    }

    public static JsonKeysetReader withBytes(final byte[] bytes) {
        return new JsonKeysetReader(new ByteArrayInputStream(bytes));
    }

    @Deprecated
    public static JsonKeysetReader withFile(File file) throws IOException {
        return withInputStream(new FileInputStream(file));
    }

    @Deprecated
    public static JsonKeysetReader withPath(String path) throws IOException {
        return withInputStream(new FileInputStream(new File(path)));
    }

    @Deprecated
    public static JsonKeysetReader withPath(Path path) throws IOException {
        return withInputStream(new FileInputStream(path.toFile()));
    }

    public JsonKeysetReader withUrlSafeBase64() {
        this.urlSafeBase64 = true;
        return this;
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            try {
                return keysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), UTF_8)).getAsJsonObject());
            } finally {
                InputStream inputStream = this.inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (JsonParseException | IllegalStateException e) {
            throw new IOException(e);
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            try {
                return encryptedKeysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), UTF_8)).getAsJsonObject());
            } finally {
                InputStream inputStream = this.inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (JsonParseException | IllegalStateException e) {
            throw new IOException(e);
        }
    }

    private static int getKeyId(JsonElement element) throws IOException {
        try {
            long parsedNumberAsLongOrThrow = JsonParser.getParsedNumberAsLongOrThrow(element);
            if (parsedNumberAsLongOrThrow > 4294967295L || parsedNumberAsLongOrThrow < MIN_KEY_ID) {
                throw new IOException("invalid key id");
            }
            return (int) element.getAsLong();
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
    }

    private Keyset keysetFromJson(JsonObject json) throws IOException {
        validateKeyset(json);
        Keyset.Builder newBuilder = Keyset.newBuilder();
        if (json.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(getKeyId(json.get("primaryKeyId")));
        }
        JsonArray asJsonArray = json.getAsJsonArray("key");
        for (int i = 0; i < asJsonArray.size(); i++) {
            newBuilder.addKey(keyFromJson(asJsonArray.get(i).getAsJsonObject()));
        }
        return (Keyset) newBuilder.build();
    }

    private EncryptedKeyset encryptedKeysetFromJson(JsonObject json) throws IOException {
        byte[] decode;
        validateEncryptedKeyset(json);
        if (this.urlSafeBase64) {
            decode = Base64.urlSafeDecode(json.get("encryptedKeyset").getAsString());
        } else {
            decode = Base64.decode(json.get("encryptedKeyset").getAsString());
        }
        if (json.has("keysetInfo")) {
            return (EncryptedKeyset) EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(decode)).setKeysetInfo(keysetInfoFromJson(json.getAsJsonObject("keysetInfo"))).build();
        }
        return (EncryptedKeyset) EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(decode)).build();
    }

    private Keyset.Key keyFromJson(JsonObject json) throws IOException {
        validateKey(json);
        return (Keyset.Key) Keyset.Key.newBuilder().setStatus(getStatus(json.get("status").getAsString())).setKeyId(getKeyId(json.get("keyId"))).setOutputPrefixType(getOutputPrefixType(json.get("outputPrefixType").getAsString())).setKeyData(keyDataFromJson(json.getAsJsonObject("keyData"))).build();
    }

    private static KeysetInfo keysetInfoFromJson(JsonObject json) throws IOException {
        KeysetInfo.Builder newBuilder = KeysetInfo.newBuilder();
        if (json.has("primaryKeyId")) {
            newBuilder.setPrimaryKeyId(getKeyId(json.get("primaryKeyId")));
        }
        if (json.has("keyInfo")) {
            JsonArray asJsonArray = json.getAsJsonArray("keyInfo");
            for (int i = 0; i < asJsonArray.size(); i++) {
                newBuilder.addKeyInfo(keyInfoFromJson(asJsonArray.get(i).getAsJsonObject()));
            }
        }
        return (KeysetInfo) newBuilder.build();
    }

    private static KeysetInfo.KeyInfo keyInfoFromJson(JsonObject json) throws IOException {
        return (KeysetInfo.KeyInfo) KeysetInfo.KeyInfo.newBuilder().setStatus(getStatus(json.get("status").getAsString())).setKeyId(getKeyId(json.get("keyId"))).setOutputPrefixType(getOutputPrefixType(json.get("outputPrefixType").getAsString())).setTypeUrl(json.get("typeUrl").getAsString()).build();
    }

    private KeyData keyDataFromJson(JsonObject json) {
        byte[] decode;
        validateKeyData(json);
        if (this.urlSafeBase64) {
            decode = Base64.urlSafeDecode(json.get("value").getAsString());
        } else {
            decode = Base64.decode(json.get("value").getAsString());
        }
        return (KeyData) KeyData.newBuilder().setTypeUrl(json.get("typeUrl").getAsString()).setValue(ByteString.copyFrom(decode)).setKeyMaterialType(getKeyMaterialType(json.get("keyMaterialType").getAsString())).build();
    }

    private static KeyStatusType getStatus(String status) {
        status.hashCode();
        char c = 65535;
        switch (status.hashCode()) {
            case -891611359:
                if (status.equals("ENABLED")) {
                    c = 0;
                    break;
                }
                break;
            case 478389753:
                if (status.equals("DESTROYED")) {
                    c = 1;
                    break;
                }
                break;
            case 1053567612:
                if (status.equals("DISABLED")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return KeyStatusType.ENABLED;
            case 1:
                return KeyStatusType.DESTROYED;
            case 2:
                return KeyStatusType.DISABLED;
            default:
                throw new JsonParseException("unknown status: " + status);
        }
    }

    private static OutputPrefixType getOutputPrefixType(String type) {
        type.hashCode();
        char c = 65535;
        switch (type.hashCode()) {
            case -2053249079:
                if (type.equals("LEGACY")) {
                    c = 0;
                    break;
                }
                break;
            case 80904:
                if (type.equals("RAW")) {
                    c = 1;
                    break;
                }
                break;
            case 2575090:
                if (type.equals("TINK")) {
                    c = 2;
                    break;
                }
                break;
            case 1761684556:
                if (type.equals("CRUNCHY")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return OutputPrefixType.LEGACY;
            case 1:
                return OutputPrefixType.RAW;
            case 2:
                return OutputPrefixType.TINK;
            case 3:
                return OutputPrefixType.CRUNCHY;
            default:
                throw new JsonParseException("unknown output prefix type: " + type);
        }
    }

    private static KeyData.KeyMaterialType getKeyMaterialType(String type) {
        type.hashCode();
        char c = 65535;
        switch (type.hashCode()) {
            case -1881281466:
                if (type.equals("REMOTE")) {
                    c = 0;
                    break;
                }
                break;
            case -1609477353:
                if (type.equals("SYMMETRIC")) {
                    c = 1;
                    break;
                }
                break;
            case 249237018:
                if (type.equals("ASYMMETRIC_PRIVATE")) {
                    c = 2;
                    break;
                }
                break;
            case 1534613202:
                if (type.equals("ASYMMETRIC_PUBLIC")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return KeyData.KeyMaterialType.REMOTE;
            case 1:
                return KeyData.KeyMaterialType.SYMMETRIC;
            case 2:
                return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
            case 3:
                return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
            default:
                throw new JsonParseException("unknown key material type: " + type);
        }
    }

    private static void validateKeyset(JsonObject json) {
        if (!json.has("key") || json.getAsJsonArray("key").size() == 0) {
            throw new JsonParseException("invalid keyset");
        }
    }

    private static void validateEncryptedKeyset(JsonObject json) {
        if (!json.has("encryptedKeyset")) {
            throw new JsonParseException("invalid encrypted keyset");
        }
    }

    private static void validateKey(JsonObject json) {
        if (!json.has("keyData") || !json.has("status") || !json.has("keyId") || !json.has("outputPrefixType")) {
            throw new JsonParseException("invalid key");
        }
    }

    private static void validateKeyData(JsonObject json) {
        if (!json.has("typeUrl") || !json.has("value") || !json.has("keyMaterialType")) {
            throw new JsonParseException("invalid keyData");
        }
    }
}
