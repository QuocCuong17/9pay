package com.google.firestore.v1;

import com.google.firestore.v1.StructuredQuery;
import com.google.firestore.v1.TransactionOptions;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class RunQueryRequest extends GeneratedMessageLite<RunQueryRequest, Builder> implements RunQueryRequestOrBuilder {
    private static final RunQueryRequest DEFAULT_INSTANCE;
    public static final int NEW_TRANSACTION_FIELD_NUMBER = 6;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<RunQueryRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 7;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
    public static final int TRANSACTION_FIELD_NUMBER = 5;
    private Object consistencySelector_;
    private Object queryType_;
    private int queryTypeCase_ = 0;
    private int consistencySelectorCase_ = 0;
    private String parent_ = "";

    private RunQueryRequest() {
    }

    /* loaded from: classes4.dex */
    public enum QueryTypeCase {
        STRUCTURED_QUERY(2),
        QUERYTYPE_NOT_SET(0);

        private final int value;

        QueryTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static QueryTypeCase forNumber(int i) {
            if (i == 0) {
                return QUERYTYPE_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return STRUCTURED_QUERY;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    /* loaded from: classes4.dex */
    public enum ConsistencySelectorCase {
        TRANSACTION(5),
        NEW_TRANSACTION(6),
        READ_TIME(7),
        CONSISTENCYSELECTOR_NOT_SET(0);

        private final int value;

        ConsistencySelectorCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ConsistencySelectorCase valueOf(int i) {
            return forNumber(i);
        }

        public static ConsistencySelectorCase forNumber(int i) {
            if (i == 0) {
                return CONSISTENCYSELECTOR_NOT_SET;
            }
            if (i == 5) {
                return TRANSACTION;
            }
            if (i == 6) {
                return NEW_TRANSACTION;
            }
            if (i != 7) {
                return null;
            }
            return READ_TIME;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParent(String str) {
        str.getClass();
        this.parent_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = getDefaultInstance().getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParentBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.parent_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public StructuredQuery getStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            return (StructuredQuery) this.queryType_;
        }
        return StructuredQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        this.queryType_ = structuredQuery;
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        if (this.queryTypeCase_ == 2 && this.queryType_ != StructuredQuery.getDefaultInstance()) {
            this.queryType_ = StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom((StructuredQuery.Builder) structuredQuery).buildPartial();
        } else {
            this.queryType_ = structuredQuery;
        }
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasTransaction() {
        return this.consistencySelectorCase_ == 5;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public ByteString getTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            return (ByteString) this.consistencySelector_;
        }
        return ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 5;
        this.consistencySelector_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasNewTransaction() {
        return this.consistencySelectorCase_ == 6;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public TransactionOptions getNewTransaction() {
        if (this.consistencySelectorCase_ == 6) {
            return (TransactionOptions) this.consistencySelector_;
        }
        return TransactionOptions.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        this.consistencySelector_ = transactionOptions;
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        if (this.consistencySelectorCase_ == 6 && this.consistencySelector_ != TransactionOptions.getDefaultInstance()) {
            this.consistencySelector_ = TransactionOptions.newBuilder((TransactionOptions) this.consistencySelector_).mergeFrom((TransactionOptions.Builder) transactionOptions).buildPartial();
        } else {
            this.consistencySelector_ = transactionOptions;
        }
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNewTransaction() {
        if (this.consistencySelectorCase_ == 6) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 7;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public Timestamp getReadTime() {
        if (this.consistencySelectorCase_ == 7) {
            return (Timestamp) this.consistencySelector_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.consistencySelector_ = timestamp;
        this.consistencySelectorCase_ = 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        if (this.consistencySelectorCase_ == 7 && this.consistencySelector_ != Timestamp.getDefaultInstance()) {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.consistencySelector_ = timestamp;
        }
        this.consistencySelectorCase_ = 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 7) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static RunQueryRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static RunQueryRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RunQueryRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RunQueryRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(InputStream inputStream) throws IOException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RunQueryRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunQueryRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RunQueryRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RunQueryRequest runQueryRequest) {
        return DEFAULT_INSTANCE.createBuilder(runQueryRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<RunQueryRequest, Builder> implements RunQueryRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(RunQueryRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return ((RunQueryRequest) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearQueryType();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((RunQueryRequest) this.instance).getConsistencySelectorCase();
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearConsistencySelector();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public String getParent() {
            return ((RunQueryRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public ByteString getParentBytes() {
            return ((RunQueryRequest) this.instance).getParentBytes();
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setParent(str);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasStructuredQuery() {
            return ((RunQueryRequest) this.instance).hasStructuredQuery();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public StructuredQuery getStructuredQuery() {
            return ((RunQueryRequest) this.instance).getStructuredQuery();
        }

        public Builder setStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setStructuredQuery(structuredQuery);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builder) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setStructuredQuery(builder.build());
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).mergeStructuredQuery(structuredQuery);
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearStructuredQuery();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasTransaction() {
            return ((RunQueryRequest) this.instance).hasTransaction();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public ByteString getTransaction() {
            return ((RunQueryRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasNewTransaction() {
            return ((RunQueryRequest) this.instance).hasNewTransaction();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public TransactionOptions getNewTransaction() {
            return ((RunQueryRequest) this.instance).getNewTransaction();
        }

        public Builder setNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setNewTransaction(transactionOptions);
            return this;
        }

        public Builder setNewTransaction(TransactionOptions.Builder builder) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setNewTransaction(builder.build());
            return this;
        }

        public Builder mergeNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).mergeNewTransaction(transactionOptions);
            return this;
        }

        public Builder clearNewTransaction() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearNewTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasReadTime() {
            return ((RunQueryRequest) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public Timestamp getReadTime() {
            return ((RunQueryRequest) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.RunQueryRequest$1, reason: invalid class name */
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
                return new RunQueryRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0002\u0000\u0001\u0007\u0005\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0005=\u0001\u0006<\u0001\u0007<\u0001", new Object[]{"queryType_", "queryTypeCase_", "consistencySelector_", "consistencySelectorCase_", "parent_", StructuredQuery.class, TransactionOptions.class, Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RunQueryRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (RunQueryRequest.class) {
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
        RunQueryRequest runQueryRequest = new RunQueryRequest();
        DEFAULT_INSTANCE = runQueryRequest;
        GeneratedMessageLite.registerDefaultInstance(RunQueryRequest.class, runQueryRequest);
    }

    public static RunQueryRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RunQueryRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
