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
public final class AggregationResult extends GeneratedMessageLite<AggregationResult, Builder> implements AggregationResultOrBuilder {
    public static final int AGGREGATE_FIELDS_FIELD_NUMBER = 2;
    private static final AggregationResult DEFAULT_INSTANCE;
    private static volatile Parser<AggregationResult> PARSER;
    private MapFieldLite<String, Value> aggregateFields_ = MapFieldLite.emptyMapField();

    private AggregationResult() {
    }

    /* loaded from: classes4.dex */
    private static final class AggregateFieldsDefaultEntryHolder {
        static final MapEntryLite<String, Value> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private AggregateFieldsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, Value> internalGetAggregateFields() {
        return this.aggregateFields_;
    }

    private MapFieldLite<String, Value> internalGetMutableAggregateFields() {
        if (!this.aggregateFields_.isMutable()) {
            this.aggregateFields_ = this.aggregateFields_.mutableCopy();
        }
        return this.aggregateFields_;
    }

    @Override // com.google.firestore.v1.AggregationResultOrBuilder
    public int getAggregateFieldsCount() {
        return internalGetAggregateFields().size();
    }

    @Override // com.google.firestore.v1.AggregationResultOrBuilder
    public boolean containsAggregateFields(String str) {
        str.getClass();
        return internalGetAggregateFields().containsKey(str);
    }

    @Override // com.google.firestore.v1.AggregationResultOrBuilder
    @Deprecated
    public Map<String, Value> getAggregateFields() {
        return getAggregateFieldsMap();
    }

    @Override // com.google.firestore.v1.AggregationResultOrBuilder
    public Map<String, Value> getAggregateFieldsMap() {
        return Collections.unmodifiableMap(internalGetAggregateFields());
    }

    @Override // com.google.firestore.v1.AggregationResultOrBuilder
    public Value getAggregateFieldsOrDefault(String str, Value value) {
        str.getClass();
        MapFieldLite<String, Value> internalGetAggregateFields = internalGetAggregateFields();
        return internalGetAggregateFields.containsKey(str) ? internalGetAggregateFields.get(str) : value;
    }

    @Override // com.google.firestore.v1.AggregationResultOrBuilder
    public Value getAggregateFieldsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Value> internalGetAggregateFields = internalGetAggregateFields();
        if (!internalGetAggregateFields.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return internalGetAggregateFields.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Value> getMutableAggregateFieldsMap() {
        return internalGetMutableAggregateFields();
    }

    public static AggregationResult parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static AggregationResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static AggregationResult parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AggregationResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AggregationResult parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AggregationResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AggregationResult parseFrom(InputStream inputStream) throws IOException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AggregationResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AggregationResult parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AggregationResult) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AggregationResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AggregationResult) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AggregationResult parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AggregationResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AggregationResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AggregationResult aggregationResult) {
        return DEFAULT_INSTANCE.createBuilder(aggregationResult);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<AggregationResult, Builder> implements AggregationResultOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(AggregationResult.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.AggregationResultOrBuilder
        public int getAggregateFieldsCount() {
            return ((AggregationResult) this.instance).getAggregateFieldsMap().size();
        }

        @Override // com.google.firestore.v1.AggregationResultOrBuilder
        public boolean containsAggregateFields(String str) {
            str.getClass();
            return ((AggregationResult) this.instance).getAggregateFieldsMap().containsKey(str);
        }

        public Builder clearAggregateFields() {
            copyOnWrite();
            ((AggregationResult) this.instance).getMutableAggregateFieldsMap().clear();
            return this;
        }

        public Builder removeAggregateFields(String str) {
            str.getClass();
            copyOnWrite();
            ((AggregationResult) this.instance).getMutableAggregateFieldsMap().remove(str);
            return this;
        }

        @Override // com.google.firestore.v1.AggregationResultOrBuilder
        @Deprecated
        public Map<String, Value> getAggregateFields() {
            return getAggregateFieldsMap();
        }

        @Override // com.google.firestore.v1.AggregationResultOrBuilder
        public Map<String, Value> getAggregateFieldsMap() {
            return Collections.unmodifiableMap(((AggregationResult) this.instance).getAggregateFieldsMap());
        }

        @Override // com.google.firestore.v1.AggregationResultOrBuilder
        public Value getAggregateFieldsOrDefault(String str, Value value) {
            str.getClass();
            Map<String, Value> aggregateFieldsMap = ((AggregationResult) this.instance).getAggregateFieldsMap();
            return aggregateFieldsMap.containsKey(str) ? aggregateFieldsMap.get(str) : value;
        }

        @Override // com.google.firestore.v1.AggregationResultOrBuilder
        public Value getAggregateFieldsOrThrow(String str) {
            str.getClass();
            Map<String, Value> aggregateFieldsMap = ((AggregationResult) this.instance).getAggregateFieldsMap();
            if (!aggregateFieldsMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return aggregateFieldsMap.get(str);
        }

        public Builder putAggregateFields(String str, Value value) {
            str.getClass();
            value.getClass();
            copyOnWrite();
            ((AggregationResult) this.instance).getMutableAggregateFieldsMap().put(str, value);
            return this;
        }

        public Builder putAllAggregateFields(Map<String, Value> map) {
            copyOnWrite();
            ((AggregationResult) this.instance).getMutableAggregateFieldsMap().putAll(map);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.AggregationResult$1, reason: invalid class name */
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
                return new AggregationResult();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0001\u0000\u0000\u00022", new Object[]{"aggregateFields_", AggregateFieldsDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<AggregationResult> parser = PARSER;
                if (parser == null) {
                    synchronized (AggregationResult.class) {
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
        AggregationResult aggregationResult = new AggregationResult();
        DEFAULT_INSTANCE = aggregationResult;
        GeneratedMessageLite.registerDefaultInstance(AggregationResult.class, aggregationResult);
    }

    public static AggregationResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AggregationResult> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
