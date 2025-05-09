package com.google.firestore.v1;

import com.google.firestore.v1.Write;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class CommitRequest extends GeneratedMessageLite<CommitRequest, Builder> implements CommitRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    private static final CommitRequest DEFAULT_INSTANCE;
    private static volatile Parser<CommitRequest> PARSER = null;
    public static final int TRANSACTION_FIELD_NUMBER = 3;
    public static final int WRITES_FIELD_NUMBER = 2;
    private String database_ = "";
    private Internal.ProtobufList<Write> writes_ = emptyProtobufList();
    private ByteString transaction_ = ByteString.EMPTY;

    private CommitRequest() {
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
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

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
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

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.transaction_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = getDefaultInstance().getTransaction();
    }

    public static CommitRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static CommitRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CommitRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CommitRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(InputStream inputStream) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CommitRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CommitRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CommitRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CommitRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommitRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CommitRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(CommitRequest commitRequest) {
        return DEFAULT_INSTANCE.createBuilder(commitRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<CommitRequest, Builder> implements CommitRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(CommitRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public String getDatabase() {
            return ((CommitRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((CommitRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((CommitRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((CommitRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((CommitRequest) this.instance).getWritesList());
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public int getWritesCount() {
            return ((CommitRequest) this.instance).getWritesCount();
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public Write getWrites(int i) {
            return ((CommitRequest) this.instance).getWrites(i);
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((CommitRequest) this.instance).setWrites(i, write);
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((CommitRequest) this.instance).setWrites(i, builder.build());
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(write);
            return this;
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(i, write);
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(i, builder.build());
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            ((CommitRequest) this.instance).addAllWrites(iterable);
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearWrites();
            return this;
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            ((CommitRequest) this.instance).removeWrites(i);
            return this;
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public ByteString getTransaction() {
            return ((CommitRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((CommitRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearTransaction();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.CommitRequest$1, reason: invalid class name */
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
                return new CommitRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003\n", new Object[]{"database_", "writes_", Write.class, "transaction_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<CommitRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (CommitRequest.class) {
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
        CommitRequest commitRequest = new CommitRequest();
        DEFAULT_INSTANCE = commitRequest;
        GeneratedMessageLite.registerDefaultInstance(CommitRequest.class, commitRequest);
    }

    public static CommitRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CommitRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
