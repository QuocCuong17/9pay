package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes4.dex */
public final class Document extends GeneratedMessageLite<Document, Builder> implements DocumentOrBuilder {
    public static final int CREATE_TIME_FIELD_NUMBER = 3;
    private static final Document DEFAULT_INSTANCE;
    public static final int FIELDS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<Document> PARSER = null;
    public static final int UPDATE_TIME_FIELD_NUMBER = 4;
    private Timestamp createTime_;
    private MapFieldLite<String, Value> fields_ = MapFieldLite.emptyMapField();
    private String name_ = "";
    private Timestamp updateTime_;

    private Document() {
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
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

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public int getFieldsCount() {
        return internalGetFields().size();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean containsFields(String str) {
        str.getClass();
        return internalGetFields().containsKey(str);
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    @Deprecated
    public Map<String, Value> getFields() {
        return getFieldsMap();
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Map<String, Value> getFieldsMap() {
        return Collections.unmodifiableMap(internalGetFields());
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Value getFieldsOrDefault(String str, Value value) {
        str.getClass();
        MapFieldLite<String, Value> internalGetFields = internalGetFields();
        return internalGetFields.containsKey(str) ? internalGetFields.get(str) : value;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
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

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean hasCreateTime() {
        return this.createTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Timestamp getCreateTime() {
        Timestamp timestamp = this.createTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.createTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.createTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.createTime_ = Timestamp.newBuilder(this.createTime_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.createTime_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCreateTime() {
        this.createTime_ = null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public boolean hasUpdateTime() {
        return this.updateTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentOrBuilder
    public Timestamp getUpdateTime() {
        Timestamp timestamp = this.updateTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.updateTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.updateTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.updateTime_ = Timestamp.newBuilder(this.updateTime_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.updateTime_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateTime() {
        this.updateTime_ = null;
    }

    public static Document parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Document parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Document parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Document parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Document parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Document parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Document parseFrom(InputStream inputStream) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Document parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Document parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Document) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Document parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Document) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Document parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Document parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Document) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Document document) {
        return DEFAULT_INSTANCE.createBuilder(document);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<Document, Builder> implements DocumentOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Document.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public String getName() {
            return ((Document) this.instance).getName();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public ByteString getNameBytes() {
            return ((Document) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Document) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Document) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Document) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public int getFieldsCount() {
            return ((Document) this.instance).getFieldsMap().size();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean containsFields(String str) {
            str.getClass();
            return ((Document) this.instance).getFieldsMap().containsKey(str);
        }

        public Builder clearFields() {
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().clear();
            return this;
        }

        public Builder removeFields(String str) {
            str.getClass();
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().remove(str);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        @Deprecated
        public Map<String, Value> getFields() {
            return getFieldsMap();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Map<String, Value> getFieldsMap() {
            return Collections.unmodifiableMap(((Document) this.instance).getFieldsMap());
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Value getFieldsOrDefault(String str, Value value) {
            str.getClass();
            Map<String, Value> fieldsMap = ((Document) this.instance).getFieldsMap();
            return fieldsMap.containsKey(str) ? fieldsMap.get(str) : value;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Value getFieldsOrThrow(String str) {
            str.getClass();
            Map<String, Value> fieldsMap = ((Document) this.instance).getFieldsMap();
            if (!fieldsMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return fieldsMap.get(str);
        }

        public Builder putFields(String str, Value value) {
            str.getClass();
            value.getClass();
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().put(str, value);
            return this;
        }

        public Builder putAllFields(Map<String, Value> map) {
            copyOnWrite();
            ((Document) this.instance).getMutableFieldsMap().putAll(map);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean hasCreateTime() {
            return ((Document) this.instance).hasCreateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Timestamp getCreateTime() {
            return ((Document) this.instance).getCreateTime();
        }

        public Builder setCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).setCreateTime(timestamp);
            return this;
        }

        public Builder setCreateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Document) this.instance).setCreateTime(builder.build());
            return this;
        }

        public Builder mergeCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).mergeCreateTime(timestamp);
            return this;
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((Document) this.instance).clearCreateTime();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public boolean hasUpdateTime() {
            return ((Document) this.instance).hasUpdateTime();
        }

        @Override // com.google.firestore.v1.DocumentOrBuilder
        public Timestamp getUpdateTime() {
            return ((Document) this.instance).getUpdateTime();
        }

        public Builder setUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).setUpdateTime(timestamp);
            return this;
        }

        public Builder setUpdateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Document) this.instance).setUpdateTime(builder.build());
            return this;
        }

        public Builder mergeUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Document) this.instance).mergeUpdateTime(timestamp);
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((Document) this.instance).clearUpdateTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Document$1, reason: invalid class name */
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
                return new Document();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u00022\u0003\t\u0004\t", new Object[]{"name_", "fields_", FieldsDefaultEntryHolder.defaultEntry, "createTime_", "updateTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Document> parser = PARSER;
                if (parser == null) {
                    synchronized (Document.class) {
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
        Document document = new Document();
        DEFAULT_INSTANCE = document;
        GeneratedMessageLite.registerDefaultInstance(Document.class, document);
    }

    public static Document getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Document> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
