package com.google.firestore.v1;

import com.google.firestore.v1.StructuredAggregationQuery;
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
public final class RunAggregationQueryRequest extends GeneratedMessageLite<RunAggregationQueryRequest, Builder> implements RunAggregationQueryRequestOrBuilder {
    private static final RunAggregationQueryRequest DEFAULT_INSTANCE;
    public static final int NEW_TRANSACTION_FIELD_NUMBER = 5;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<RunAggregationQueryRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 6;
    public static final int STRUCTURED_AGGREGATION_QUERY_FIELD_NUMBER = 2;
    public static final int TRANSACTION_FIELD_NUMBER = 4;
    private Object consistencySelector_;
    private Object queryType_;
    private int queryTypeCase_ = 0;
    private int consistencySelectorCase_ = 0;
    private String parent_ = "";

    private RunAggregationQueryRequest() {
    }

    /* loaded from: classes4.dex */
    public enum QueryTypeCase {
        STRUCTURED_AGGREGATION_QUERY(2),
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
            return STRUCTURED_AGGREGATION_QUERY;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
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
        TRANSACTION(4),
        NEW_TRANSACTION(5),
        READ_TIME(6),
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
            if (i == 4) {
                return TRANSACTION;
            }
            if (i == 5) {
                return NEW_TRANSACTION;
            }
            if (i != 6) {
                return null;
            }
            return READ_TIME;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
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

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public boolean hasStructuredAggregationQuery() {
        return this.queryTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public StructuredAggregationQuery getStructuredAggregationQuery() {
        if (this.queryTypeCase_ == 2) {
            return (StructuredAggregationQuery) this.queryType_;
        }
        return StructuredAggregationQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredAggregationQuery(StructuredAggregationQuery structuredAggregationQuery) {
        structuredAggregationQuery.getClass();
        this.queryType_ = structuredAggregationQuery;
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredAggregationQuery(StructuredAggregationQuery structuredAggregationQuery) {
        structuredAggregationQuery.getClass();
        if (this.queryTypeCase_ == 2 && this.queryType_ != StructuredAggregationQuery.getDefaultInstance()) {
            this.queryType_ = StructuredAggregationQuery.newBuilder((StructuredAggregationQuery) this.queryType_).mergeFrom((StructuredAggregationQuery.Builder) structuredAggregationQuery).buildPartial();
        } else {
            this.queryType_ = structuredAggregationQuery;
        }
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredAggregationQuery() {
        if (this.queryTypeCase_ == 2) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public boolean hasTransaction() {
        return this.consistencySelectorCase_ == 4;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public ByteString getTransaction() {
        if (this.consistencySelectorCase_ == 4) {
            return (ByteString) this.consistencySelector_;
        }
        return ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 4;
        this.consistencySelector_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 4) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public boolean hasNewTransaction() {
        return this.consistencySelectorCase_ == 5;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public TransactionOptions getNewTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            return (TransactionOptions) this.consistencySelector_;
        }
        return TransactionOptions.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        this.consistencySelector_ = transactionOptions;
        this.consistencySelectorCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        if (this.consistencySelectorCase_ == 5 && this.consistencySelector_ != TransactionOptions.getDefaultInstance()) {
            this.consistencySelector_ = TransactionOptions.newBuilder((TransactionOptions) this.consistencySelector_).mergeFrom((TransactionOptions.Builder) transactionOptions).buildPartial();
        } else {
            this.consistencySelector_ = transactionOptions;
        }
        this.consistencySelectorCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNewTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 6;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
    public Timestamp getReadTime() {
        if (this.consistencySelectorCase_ == 6) {
            return (Timestamp) this.consistencySelector_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.consistencySelector_ = timestamp;
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        if (this.consistencySelectorCase_ == 6 && this.consistencySelector_ != Timestamp.getDefaultInstance()) {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.consistencySelector_ = timestamp;
        }
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 6) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static RunAggregationQueryRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static RunAggregationQueryRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RunAggregationQueryRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RunAggregationQueryRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RunAggregationQueryRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RunAggregationQueryRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RunAggregationQueryRequest parseFrom(InputStream inputStream) throws IOException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunAggregationQueryRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunAggregationQueryRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RunAggregationQueryRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunAggregationQueryRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunAggregationQueryRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunAggregationQueryRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RunAggregationQueryRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunAggregationQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RunAggregationQueryRequest runAggregationQueryRequest) {
        return DEFAULT_INSTANCE.createBuilder(runAggregationQueryRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<RunAggregationQueryRequest, Builder> implements RunAggregationQueryRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(RunAggregationQueryRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return ((RunAggregationQueryRequest) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearQueryType();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((RunAggregationQueryRequest) this.instance).getConsistencySelectorCase();
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearConsistencySelector();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public String getParent() {
            return ((RunAggregationQueryRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public ByteString getParentBytes() {
            return ((RunAggregationQueryRequest) this.instance).getParentBytes();
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setParent(str);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public boolean hasStructuredAggregationQuery() {
            return ((RunAggregationQueryRequest) this.instance).hasStructuredAggregationQuery();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public StructuredAggregationQuery getStructuredAggregationQuery() {
            return ((RunAggregationQueryRequest) this.instance).getStructuredAggregationQuery();
        }

        public Builder setStructuredAggregationQuery(StructuredAggregationQuery structuredAggregationQuery) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setStructuredAggregationQuery(structuredAggregationQuery);
            return this;
        }

        public Builder setStructuredAggregationQuery(StructuredAggregationQuery.Builder builder) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setStructuredAggregationQuery(builder.build());
            return this;
        }

        public Builder mergeStructuredAggregationQuery(StructuredAggregationQuery structuredAggregationQuery) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).mergeStructuredAggregationQuery(structuredAggregationQuery);
            return this;
        }

        public Builder clearStructuredAggregationQuery() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearStructuredAggregationQuery();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public boolean hasTransaction() {
            return ((RunAggregationQueryRequest) this.instance).hasTransaction();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public ByteString getTransaction() {
            return ((RunAggregationQueryRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public boolean hasNewTransaction() {
            return ((RunAggregationQueryRequest) this.instance).hasNewTransaction();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public TransactionOptions getNewTransaction() {
            return ((RunAggregationQueryRequest) this.instance).getNewTransaction();
        }

        public Builder setNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setNewTransaction(transactionOptions);
            return this;
        }

        public Builder setNewTransaction(TransactionOptions.Builder builder) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setNewTransaction(builder.build());
            return this;
        }

        public Builder mergeNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).mergeNewTransaction(transactionOptions);
            return this;
        }

        public Builder clearNewTransaction() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearNewTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public boolean hasReadTime() {
            return ((RunAggregationQueryRequest) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryRequestOrBuilder
        public Timestamp getReadTime() {
            return ((RunAggregationQueryRequest) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunAggregationQueryRequest) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.RunAggregationQueryRequest$1, reason: invalid class name */
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
                return new RunAggregationQueryRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0002\u0000\u0001\u0006\u0005\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0004=\u0001\u0005<\u0001\u0006<\u0001", new Object[]{"queryType_", "queryTypeCase_", "consistencySelector_", "consistencySelectorCase_", "parent_", StructuredAggregationQuery.class, TransactionOptions.class, Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RunAggregationQueryRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (RunAggregationQueryRequest.class) {
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
        RunAggregationQueryRequest runAggregationQueryRequest = new RunAggregationQueryRequest();
        DEFAULT_INSTANCE = runAggregationQueryRequest;
        GeneratedMessageLite.registerDefaultInstance(RunAggregationQueryRequest.class, runAggregationQueryRequest);
    }

    public static RunAggregationQueryRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RunAggregationQueryRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
