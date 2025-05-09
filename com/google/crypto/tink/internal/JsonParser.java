package com.google.crypto.tink.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class JsonParser {
    private static final JsonElementTypeAdapter JSON_ELEMENT = new JsonElementTypeAdapter(null);

    public static boolean isValidString(String s) {
        int length = s.length();
        int i = 0;
        while (i != length) {
            char charAt = s.charAt(i);
            i++;
            if (Character.isSurrogate(charAt)) {
                if (Character.isLowSurrogate(charAt) || i == length || !Character.isLowSurrogate(s.charAt(i))) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class LazilyParsedNumber extends Number {
        private final String value;

        public LazilyParsedNumber(String value) {
            this.value = value;
        }

        @Override // java.lang.Number
        public int intValue() {
            try {
                try {
                    return Integer.parseInt(this.value);
                } catch (NumberFormatException unused) {
                    return (int) Long.parseLong(this.value);
                }
            } catch (NumberFormatException unused2) {
                return new BigDecimal(this.value).intValue();
            }
        }

        @Override // java.lang.Number
        public long longValue() {
            try {
                return Long.parseLong(this.value);
            } catch (NumberFormatException unused) {
                return new BigDecimal(this.value).longValue();
            }
        }

        @Override // java.lang.Number
        public float floatValue() {
            return Float.parseFloat(this.value);
        }

        @Override // java.lang.Number
        public double doubleValue() {
            return Double.parseDouble(this.value);
        }

        public String toString() {
            return this.value;
        }

        private Object writeReplace() throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        private void readObject(ObjectInputStream in) throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LazilyParsedNumber) {
                return this.value.equals(((LazilyParsedNumber) obj).value);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class JsonElementTypeAdapter extends TypeAdapter<JsonElement> {
        private static final int RECURSION_LIMIT = 100;

        private JsonElementTypeAdapter() {
        }

        /* synthetic */ JsonElementTypeAdapter(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Nullable
        private JsonElement tryBeginNesting(JsonReader in, JsonToken peeked) throws IOException {
            int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[peeked.ordinal()];
            if (i == 1) {
                in.beginArray();
                return new JsonArray();
            }
            if (i != 2) {
                return null;
            }
            in.beginObject();
            return new JsonObject();
        }

        private JsonElement readTerminal(JsonReader in, JsonToken peeked) throws IOException {
            int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[peeked.ordinal()];
            if (i == 3) {
                String nextString = in.nextString();
                if (!JsonParser.isValidString(nextString)) {
                    throw new IOException("illegal characters in string");
                }
                return new JsonPrimitive(nextString);
            }
            if (i == 4) {
                return new JsonPrimitive(new LazilyParsedNumber(in.nextString()));
            }
            if (i == 5) {
                return new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
            }
            if (i == 6) {
                in.nextNull();
                return JsonNull.INSTANCE;
            }
            throw new IllegalStateException("Unexpected token: " + peeked);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public JsonElement read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            JsonElement tryBeginNesting = tryBeginNesting(in, peek);
            if (tryBeginNesting == null) {
                return readTerminal(in, peek);
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            while (true) {
                if (in.hasNext()) {
                    String str = null;
                    if (tryBeginNesting instanceof JsonObject) {
                        str = in.nextName();
                        if (!JsonParser.isValidString(str)) {
                            throw new IOException("illegal characters in string");
                        }
                    }
                    JsonToken peek2 = in.peek();
                    JsonElement tryBeginNesting2 = tryBeginNesting(in, peek2);
                    boolean z = tryBeginNesting2 != null;
                    if (tryBeginNesting2 == null) {
                        tryBeginNesting2 = readTerminal(in, peek2);
                    }
                    if (tryBeginNesting instanceof JsonArray) {
                        ((JsonArray) tryBeginNesting).add(tryBeginNesting2);
                    } else {
                        JsonObject jsonObject = (JsonObject) tryBeginNesting;
                        if (jsonObject.has(str)) {
                            throw new IOException("duplicate key: " + str);
                        }
                        jsonObject.add(str, tryBeginNesting2);
                    }
                    if (z) {
                        arrayDeque.addLast(tryBeginNesting);
                        if (arrayDeque.size() > 100) {
                            throw new IOException("too many recursions");
                        }
                        tryBeginNesting = tryBeginNesting2;
                    } else {
                        continue;
                    }
                } else {
                    if (tryBeginNesting instanceof JsonArray) {
                        in.endArray();
                    } else {
                        in.endObject();
                    }
                    if (arrayDeque.isEmpty()) {
                        return tryBeginNesting;
                    }
                    tryBeginNesting = (JsonElement) arrayDeque.removeLast();
                }
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, JsonElement value) {
            throw new UnsupportedOperationException("write is not supported");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.internal.JsonParser$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static JsonElement parse(String json) throws IOException {
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(json));
            jsonReader.setLenient(false);
            return JSON_ELEMENT.read(jsonReader);
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
    }

    public static long getParsedNumberAsLongOrThrow(JsonElement element) {
        if (!(element.getAsNumber() instanceof LazilyParsedNumber)) {
            throw new IllegalArgumentException("does not contain a parsed number.");
        }
        return Long.parseLong(element.getAsNumber().toString());
    }

    private JsonParser() {
    }
}
