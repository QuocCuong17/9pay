package com.google.firebase.firestore.proto;

import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteOrBuilder;
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
public final class WriteBatch extends GeneratedMessageLite<WriteBatch, Builder> implements WriteBatchOrBuilder {
    public static final int BASE_WRITES_FIELD_NUMBER = 4;
    public static final int BATCH_ID_FIELD_NUMBER = 1;
    private static final WriteBatch DEFAULT_INSTANCE;
    public static final int LOCAL_WRITE_TIME_FIELD_NUMBER = 3;
    private static volatile Parser<WriteBatch> PARSER = null;
    public static final int WRITES_FIELD_NUMBER = 2;
    private int batchId_;
    private Timestamp localWriteTime_;
    private Internal.ProtobufList<Write> writes_ = emptyProtobufList();
    private Internal.ProtobufList<Write> baseWrites_ = emptyProtobufList();

    private WriteBatch() {
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getBatchId() {
        return this.batchId_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBatchId(int i) {
        this.batchId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBatchId() {
        this.batchId_ = 0;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
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

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public boolean hasLocalWriteTime() {
        return this.localWriteTime_ != null;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Timestamp getLocalWriteTime() {
        Timestamp timestamp = this.localWriteTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalWriteTime(Timestamp timestamp) {
        timestamp.getClass();
        this.localWriteTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLocalWriteTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.localWriteTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.localWriteTime_ = Timestamp.newBuilder(this.localWriteTime_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.localWriteTime_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocalWriteTime() {
        this.localWriteTime_ = null;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public List<Write> getBaseWritesList() {
        return this.baseWrites_;
    }

    public List<? extends WriteOrBuilder> getBaseWritesOrBuilderList() {
        return this.baseWrites_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getBaseWritesCount() {
        return this.baseWrites_.size();
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Write getBaseWrites(int i) {
        return this.baseWrites_.get(i);
    }

    public WriteOrBuilder getBaseWritesOrBuilder(int i) {
        return this.baseWrites_.get(i);
    }

    private void ensureBaseWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.baseWrites_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.baseWrites_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBaseWrites(int i, Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.set(i, write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBaseWrites(Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBaseWrites(int i, Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(i, write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllBaseWrites(Iterable<? extends Write> iterable) {
        ensureBaseWritesIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.baseWrites_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBaseWrites() {
        this.baseWrites_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeBaseWrites(int i) {
        ensureBaseWritesIsMutable();
        this.baseWrites_.remove(i);
    }

    public static WriteBatch parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static WriteBatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WriteBatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteBatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(InputStream inputStream) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteBatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteBatch parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WriteBatch) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteBatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteBatch) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteBatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(WriteBatch writeBatch) {
        return DEFAULT_INSTANCE.createBuilder(writeBatch);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<WriteBatch, Builder> implements WriteBatchOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(WriteBatch.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getBatchId() {
            return ((WriteBatch) this.instance).getBatchId();
        }

        public Builder setBatchId(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBatchId(i);
            return this;
        }

        public Builder clearBatchId() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBatchId();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getWritesList());
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getWritesCount() {
            return ((WriteBatch) this.instance).getWritesCount();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Write getWrites(int i) {
            return ((WriteBatch) this.instance).getWrites(i);
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(i, write);
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(i, builder.build());
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(write);
            return this;
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(i, write);
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(i, builder.build());
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            ((WriteBatch) this.instance).addAllWrites(iterable);
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearWrites();
            return this;
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).removeWrites(i);
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public boolean hasLocalWriteTime() {
            return ((WriteBatch) this.instance).hasLocalWriteTime();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Timestamp getLocalWriteTime() {
            return ((WriteBatch) this.instance).getLocalWriteTime();
        }

        public Builder setLocalWriteTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(timestamp);
            return this;
        }

        public Builder setLocalWriteTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(builder.build());
            return this;
        }

        public Builder mergeLocalWriteTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteBatch) this.instance).mergeLocalWriteTime(timestamp);
            return this;
        }

        public Builder clearLocalWriteTime() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearLocalWriteTime();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public List<Write> getBaseWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getBaseWritesList());
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getBaseWritesCount() {
            return ((WriteBatch) this.instance).getBaseWritesCount();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Write getBaseWrites(int i) {
            return ((WriteBatch) this.instance).getBaseWrites(i);
        }

        public Builder setBaseWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(i, write);
            return this;
        }

        public Builder setBaseWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(i, builder.build());
            return this;
        }

        public Builder addBaseWrites(Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(write);
            return this;
        }

        public Builder addBaseWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(i, write);
            return this;
        }

        public Builder addBaseWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(builder.build());
            return this;
        }

        public Builder addBaseWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(i, builder.build());
            return this;
        }

        public Builder addAllBaseWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            ((WriteBatch) this.instance).addAllBaseWrites(iterable);
            return this;
        }

        public Builder clearBaseWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBaseWrites();
            return this;
        }

        public Builder removeBaseWrites(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).removeBaseWrites(i);
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.WriteBatch$1, reason: invalid class name */
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
                return new WriteBatch();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u0004\u0002\u001b\u0003\t\u0004\u001b", new Object[]{"batchId_", "writes_", Write.class, "localWriteTime_", "baseWrites_", Write.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<WriteBatch> parser = PARSER;
                if (parser == null) {
                    synchronized (WriteBatch.class) {
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
        WriteBatch writeBatch = new WriteBatch();
        DEFAULT_INSTANCE = writeBatch;
        GeneratedMessageLite.registerDefaultInstance(WriteBatch.class, writeBatch);
    }

    public static WriteBatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WriteBatch> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
