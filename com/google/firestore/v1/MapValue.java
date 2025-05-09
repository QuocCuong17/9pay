package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes4.dex */
public final class MapValue extends GeneratedMessageLite<MapValue, Builder> implements MapValueOrBuilder {
    private static final MapValue DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 1;
    private static volatile Parser<MapValue> PARSER;
    private MapFieldLite<String, Value> fields_ = MapFieldLite.emptyMapField();

    private MapValue() {
    }

    /* loaded from: classes4.dex */
    private static final class FieldsDefaultEntryHolder {
        static final MapEntryLite<String, Value> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private FieldsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, Value> internalGetFields() {
        return this.fields_;
    }

    private MapFieldLite<String, Value> internalGetMutableFields() {
        if (!this.fields_.isMutable()) {
            this.fields_ = this.fields_.mutableCopy();
        }
        return this.fields_;
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public int getFieldsCount() {
        return internalGetFields().size();
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public boolean containsFields(String str) {
        str.getClass();
        return internalGetFields().containsKey(str);
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    @Deprecated
    public Map<String, Value> getFields() {
        return getFieldsMap();
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public Map<String, Value> getFieldsMap() {
        return Collections.unmodifiableMap(internalGetFields());
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public Value getFieldsOrDefault(String str, Value value) {
        str.getClass();
        MapFieldLite<String, Value> internalGetFields = internalGetFields();
        return internalGetFields.containsKey(str) ? internalGetFields.get(str) : value;
    }

    @Override // com.google.firestore.v1.MapValueOrBuilder
    public Value getFieldsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Value> internalGetFields = internalGetFields();
        if (!internalGetFields.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return internalGetFields.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Value> getMutableFieldsMap() {
        return internalGetMutableFields();
    }

    public static MapValue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static MapValue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MapValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MapValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MapValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MapValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MapValue parseFrom(InputStream inputStream) throws IOException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MapValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MapValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MapValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MapValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MapValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MapValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MapValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MapValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(MapValue mapValue) {
        return DEFAULT_INSTANCE.createBuilder(mapValue);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<MapValue, Builder> implements MapValueOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(MapValue.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public int getFieldsCount() {
            return ((MapValue) this.instance).getFieldsMap().size();
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public boolean containsFields(String str) {
            str.getClass();
            return ((MapValue) this.instance).getFieldsMap().containsKey(str);
        }

        public Builder clearFields() {
            copyOnWrite();
            ((MapValue) this.instance).getMutableFieldsMap().clear();
            return this;
        }

        public Builder removeFields(String str) {
            str.getClass();
            copyOnWrite();
            ((MapValue) this.instance).getMutableFieldsMap().remove(str);
            return this;
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        @Deprecated
        public Map<String, Value> getFields() {
            return getFieldsMap();
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public Map<String, Value> getFieldsMap() {
            return Collections.unmodifiableMap(((MapValue) this.instance).getFieldsMap());
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public Value getFieldsOrDefault(String str, Value value) {
            str.getClass();
            Map<String, Value> fieldsMap = ((MapValue) this.instance).getFieldsMap();
            return fieldsMap.containsKey(str) ? fieldsMap.get(str) : value;
        }

        @Override // com.google.firestore.v1.MapValueOrBuilder
        public Value getFieldsOrThrow(String str) {
            str.getClass();
            Map<String, Value> fieldsMap = ((MapValue) this.instance).getFieldsMap();
            if (!fieldsMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return fieldsMap.get(str);
        }

        public Builder putFields(String str, Value value) {
            str.getClass();
            value.getClass();
            copyOnWrite();
            ((MapValue) this.instance).getMutableFieldsMap().put(str, value);
            return this;
        }

        public Builder putAllFields(Map<String, Value> map) {
            copyOnWrite();
            ((MapValue) this.instance).getMutableFieldsMap().putAll(map);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.MapValue$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MapValue();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"fields_", FieldsDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<MapValue> parser = PARSER;
                if (parser == null) {
                    synchronized (MapValue.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        MapValue mapValue = new MapValue();
        DEFAULT_INSTANCE = mapValue;
        GeneratedMessageLite.registerDefaultInstance(MapValue.class, mapValue);
    }

    public static MapValue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MapValue> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
