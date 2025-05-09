package com.google.firestore.v1;

import com.google.firestore.v1.Write;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class WriteRequest extends GeneratedMessageLite<WriteRequest, Builder> implements WriteRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    private static final WriteRequest DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 5;
    private static volatile Parser<WriteRequest> PARSER = null;
    public static final int STREAM_ID_FIELD_NUMBER = 2;
    public static final int STREAM_TOKEN_FIELD_NUMBER = 4;
    public static final int WRITES_FIELD_NUMBER = 3;
    private MapFieldLite<String, String> labels_ = MapFieldLite.emptyMapField();
    private String database_ = "";
    private String streamId_ = "";
    private Internal.ProtobufList<Write> writes_ = emptyProtobufList();
    private ByteString streamToken_ = ByteString.EMPTY;

    private WriteRequest() {
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = getDefaultInstance().getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getStreamId() {
        return this.streamId_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public ByteString getStreamIdBytes() {
        return ByteString.copyFromUtf8(this.streamId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamId(String str) {
        str.getClass();
        this.streamId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStreamId() {
        this.streamId_ = getDefaultInstance().getStreamId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.streamId_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public Write getWrites(int i) {
        return this.writes_.get(i);
    }

    public WriteOrBuilder getWritesOrBuilder(int i) {
        return this.writes_.get(i);
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.writes_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.writes_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.set(i, write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(i, write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWrites(Iterable<? extends Write> iterable) {
        ensureWritesIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.writes_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeWrites(int i) {
        ensureWritesIsMutable();
        this.writes_.remove(i);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public ByteString getStreamToken() {
        return this.streamToken_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStreamToken(ByteString byteString) {
        byteString.getClass();
        this.streamToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStreamToken() {
        this.streamToken_ = getDefaultInstance().getStreamToken();
    }

    /* loaded from: classes4.dex */
    private static final class LabelsDefaultEntryHolder {
        static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, String> internalGetLabels() {
        return this.labels_;
    }

    private MapFieldLite<String, String> internalGetMutableLabels() {
        if (!this.labels_.isMutable()) {
            this.labels_ = this.labels_.mutableCopy();
        }
        return this.labels_;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public int getLabelsCount() {
        return internalGetLabels().size();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public boolean containsLabels(String str) {
        str.getClass();
        return internalGetLabels().containsKey(str);
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(internalGetLabels());
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> internalGetLabels = internalGetLabels();
        return internalGetLabels.containsKey(str) ? internalGetLabels.get(str) : str2;
    }

    @Override // com.google.firestore.v1.WriteRequestOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> internalGetLabels = internalGetLabels();
        if (!internalGetLabels.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return internalGetLabels.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> getMutableLabelsMap() {
        return internalGetMutableLabels();
    }

    public static WriteRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static WriteRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WriteRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(InputStream inputStream) throws IOException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WriteRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteRequest writeRequest) {
        return DEFAULT_INSTANCE.createBuilder(writeRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<WriteRequest, Builder> implements WriteRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(WriteRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getDatabase() {
            return ((WriteRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((WriteRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((WriteRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((WriteRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getStreamId() {
            return ((WriteRequest) this.instance).getStreamId();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public ByteString getStreamIdBytes() {
            return ((WriteRequest) this.instance).getStreamIdBytes();
        }

        public Builder setStreamId(String str) {
            copyOnWrite();
            ((WriteRequest) this.instance).setStreamId(str);
            return this;
        }

        public Builder clearStreamId() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearStreamId();
            return this;
        }

        public Builder setStreamIdBytes(ByteString byteString) {
            copyOnWrite();
            ((WriteRequest) this.instance).setStreamIdBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((WriteRequest) this.instance).getWritesList());
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public int getWritesCount() {
            return ((WriteRequest) this.instance).getWritesCount();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public Write getWrites(int i) {
            return ((WriteRequest) this.instance).getWrites(i);
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((WriteRequest) this.instance).setWrites(i, write);
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteRequest) this.instance).setWrites(i, builder.build());
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(write);
            return this;
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(i, write);
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteRequest) this.instance).addWrites(i, builder.build());
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            ((WriteRequest) this.instance).addAllWrites(iterable);
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearWrites();
            return this;
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            ((WriteRequest) this.instance).removeWrites(i);
            return this;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public ByteString getStreamToken() {
            return ((WriteRequest) this.instance).getStreamToken();
        }

        public Builder setStreamToken(ByteString byteString) {
            copyOnWrite();
            ((WriteRequest) this.instance).setStreamToken(byteString);
            return this;
        }

        public Builder clearStreamToken() {
            copyOnWrite();
            ((WriteRequest) this.instance).clearStreamToken();
            return this;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public int getLabelsCount() {
            return ((WriteRequest) this.instance).getLabelsMap().size();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return ((WriteRequest) this.instance).getLabelsMap().containsKey(str);
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((WriteRequest) this.instance).getMutableLabelsMap().clear();
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((WriteRequest) this.instance).getMutableLabelsMap().remove(str);
            return this;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((WriteRequest) this.instance).getLabelsMap());
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> labelsMap = ((WriteRequest) this.instance).getLabelsMap();
            return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
        }

        @Override // com.google.firestore.v1.WriteRequestOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> labelsMap = ((WriteRequest) this.instance).getLabelsMap();
            if (!labelsMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return labelsMap.get(str);
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((WriteRequest) this.instance).getMutableLabelsMap().put(str, str2);
            return this;
        }

        public Builder putAllLabels(Map<String, String> map) {
            copyOnWrite();
            ((WriteRequest) this.instance).getMutableLabelsMap().putAll(map);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.WriteRequest$1, reason: invalid class name */
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
                return new WriteRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0001\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003\u001b\u0004\n\u00052", new Object[]{"database_", "streamId_", "writes_", Write.class, "streamToken_", "labels_", LabelsDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteRequest.class) {
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
        WriteRequest writeRequest = new WriteRequest();
        DEFAULT_INSTANCE = writeRequest;
        GeneratedMessageLite.registerDefaultInstance(WriteRequest.class, writeRequest);
    }

    public static WriteRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
