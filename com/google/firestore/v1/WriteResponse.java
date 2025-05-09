package com.google.firestore.v1;

import com.google.firestore.v1.WriteResult;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class WriteResponse extends GeneratedMessageLite<WriteResponse, Builder> implements WriteResponseOrBuilder {
    public static final int COMMIT_TIME_FIELD_NUMBER = 4;
    private static final WriteResponse DEFAULT_INSTANCE;
    private static volatile Parser<WriteResponse> PARSER = null;
    public static final int STREAM_ID_FIELD_NUMBER = 1;
    public static final int STREAM_TOKEN_FIELD_NUMBER = 2;
    public static final int WRITE_RESULTS_FIELD_NUMBER = 3;
    private Timestamp commitTime_;
    private String streamId_ = "";
    private ByteString streamToken_ = ByteString.EMPTY;
    private Internal.ProtobufList<WriteResult> writeResults_ = emptyProtobufList();

    private WriteResponse() {
    }

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
    public String getStreamId() {
        return this.streamId_;
    }

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
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

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
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

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
    public List<WriteResult> getWriteResultsList() {
        return this.writeResults_;
    }

    public List<? extends WriteResultOrBuilder> getWriteResultsOrBuilderList() {
        return this.writeResults_;
    }

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
    public int getWriteResultsCount() {
        return this.writeResults_.size();
    }

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
    public WriteResult getWriteResults(int i) {
        return this.writeResults_.get(i);
    }

    public WriteResultOrBuilder getWriteResultsOrBuilder(int i) {
        return this.writeResults_.get(i);
    }

    private void ensureWriteResultsIsMutable() {
        Internal.ProtobufList<WriteResult> protobufList = this.writeResults_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.writeResults_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWriteResults(int i, WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.set(i, writeResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWriteResults(WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(writeResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWriteResults(int i, WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(i, writeResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWriteResults(Iterable<? extends WriteResult> iterable) {
        ensureWriteResultsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.writeResults_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWriteResults() {
        this.writeResults_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeWriteResults(int i) {
        ensureWriteResultsIsMutable();
        this.writeResults_.remove(i);
    }

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
    public boolean hasCommitTime() {
        return this.commitTime_ != null;
    }

    @Override // com.google.firestore.v1.WriteResponseOrBuilder
    public Timestamp getCommitTime() {
        Timestamp timestamp = this.commitTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCommitTime(Timestamp timestamp) {
        timestamp.getClass();
        this.commitTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCommitTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.commitTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.commitTime_ = Timestamp.newBuilder(this.commitTime_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.commitTime_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCommitTime() {
        this.commitTime_ = null;
    }

    public static WriteResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static WriteResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WriteResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(InputStream inputStream) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WriteResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteResponse writeResponse) {
        return DEFAULT_INSTANCE.createBuilder(writeResponse);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<WriteResponse, Builder> implements WriteResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(WriteResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public String getStreamId() {
            return ((WriteResponse) this.instance).getStreamId();
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public ByteString getStreamIdBytes() {
            return ((WriteResponse) this.instance).getStreamIdBytes();
        }

        public Builder setStreamId(String str) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamId(str);
            return this;
        }

        public Builder clearStreamId() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearStreamId();
            return this;
        }

        public Builder setStreamIdBytes(ByteString byteString) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamIdBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public ByteString getStreamToken() {
            return ((WriteResponse) this.instance).getStreamToken();
        }

        public Builder setStreamToken(ByteString byteString) {
            copyOnWrite();
            ((WriteResponse) this.instance).setStreamToken(byteString);
            return this;
        }

        public Builder clearStreamToken() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearStreamToken();
            return this;
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public List<WriteResult> getWriteResultsList() {
            return Collections.unmodifiableList(((WriteResponse) this.instance).getWriteResultsList());
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public int getWriteResultsCount() {
            return ((WriteResponse) this.instance).getWriteResultsCount();
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public WriteResult getWriteResults(int i) {
            return ((WriteResponse) this.instance).getWriteResults(i);
        }

        public Builder setWriteResults(int i, WriteResult writeResult) {
            copyOnWrite();
            ((WriteResponse) this.instance).setWriteResults(i, writeResult);
            return this;
        }

        public Builder setWriteResults(int i, WriteResult.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).setWriteResults(i, builder.build());
            return this;
        }

        public Builder addWriteResults(WriteResult writeResult) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(writeResult);
            return this;
        }

        public Builder addWriteResults(int i, WriteResult writeResult) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(i, writeResult);
            return this;
        }

        public Builder addWriteResults(WriteResult.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(builder.build());
            return this;
        }

        public Builder addWriteResults(int i, WriteResult.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).addWriteResults(i, builder.build());
            return this;
        }

        public Builder addAllWriteResults(Iterable<? extends WriteResult> iterable) {
            copyOnWrite();
            ((WriteResponse) this.instance).addAllWriteResults(iterable);
            return this;
        }

        public Builder clearWriteResults() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearWriteResults();
            return this;
        }

        public Builder removeWriteResults(int i) {
            copyOnWrite();
            ((WriteResponse) this.instance).removeWriteResults(i);
            return this;
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public boolean hasCommitTime() {
            return ((WriteResponse) this.instance).hasCommitTime();
        }

        @Override // com.google.firestore.v1.WriteResponseOrBuilder
        public Timestamp getCommitTime() {
            return ((WriteResponse) this.instance).getCommitTime();
        }

        public Builder setCommitTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResponse) this.instance).setCommitTime(timestamp);
            return this;
        }

        public Builder setCommitTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteResponse) this.instance).setCommitTime(builder.build());
            return this;
        }

        public Builder mergeCommitTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResponse) this.instance).mergeCommitTime(timestamp);
            return this;
        }

        public Builder clearCommitTime() {
            copyOnWrite();
            ((WriteResponse) this.instance).clearCommitTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.WriteResponse$1, reason: invalid class name */
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
                return new WriteResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\n\u0003\u001b\u0004\t", new Object[]{"streamId_", "streamToken_", "writeResults_", WriteResult.class, "commitTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteResponse.class) {
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
        WriteResponse writeResponse = new WriteResponse();
        DEFAULT_INSTANCE = writeResponse;
        GeneratedMessageLite.registerDefaultInstance(WriteResponse.class, writeResponse);
    }

    public static WriteResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
