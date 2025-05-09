package com.google.firestore.v1;

import com.google.firestore.v1.AggregationResult;
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
public final class RunAggregationQueryResponse extends GeneratedMessageLite<RunAggregationQueryResponse, Builder> implements RunAggregationQueryResponseOrBuilder {
    private static final RunAggregationQueryResponse DEFAULT_INSTANCE;
    private static volatile Parser<RunAggregationQueryResponse> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    public static final int RESULT_FIELD_NUMBER = 1;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    private Timestamp readTime_;
    private AggregationResult result_;
    private ByteString transaction_ = ByteString.EMPTY;

    private RunAggregationQueryResponse() {
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public boolean hasResult() {
        return this.result_ != null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public AggregationResult getResult() {
        AggregationResult aggregationResult = this.result_;
        return aggregationResult == null ? AggregationResult.getDefaultInstance() : aggregationResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResult(AggregationResult aggregationResult) {
        aggregationResult.getClass();
        this.result_ = aggregationResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeResult(AggregationResult aggregationResult) {
        aggregationResult.getClass();
        AggregationResult aggregationResult2 = this.result_;
        if (aggregationResult2 != null && aggregationResult2 != AggregationResult.getDefaultInstance()) {
            this.result_ = AggregationResult.newBuilder(this.result_).mergeFrom((AggregationResult.Builder) aggregationResult).buildPartial();
        } else {
            this.result_ = aggregationResult;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResult() {
        this.result_ = null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
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

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.readTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.readTime_ = Timestamp.newBuilder(this.readTime_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.readTime_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        this.readTime_ = null;
    }

    public static RunAggregationQueryResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static RunAggregationQueryResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RunAggregationQueryResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RunAggregationQueryResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RunAggregationQueryResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RunAggregationQueryResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RunAggregationQueryResponse parseFrom(InputStream inputStream) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunAggregationQueryResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunAggregationQueryResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RunAggregationQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunAggregationQueryResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunAggregationQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunAggregationQueryResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RunAggregationQueryResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunAggregationQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RunAggregationQueryResponse runAggregationQueryResponse) {
        return DEFAULT_INSTANCE.createBuilder(runAggregationQueryResponse);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<RunAggregationQueryResponse, Builder> implements RunAggregationQueryResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(RunAggregationQueryResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public boolean hasResult() {
            return ((RunAggregationQueryResponse) this.instance).hasResult();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public AggregationResult getResult() {
            return ((RunAggregationQueryResponse) this.instance).getResult();
        }

        public Builder setResult(AggregationResult aggregationResult) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setResult(aggregationResult);
            return this;
        }

        public Builder setResult(AggregationResult.Builder builder) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setResult(builder.build());
            return this;
        }

        public Builder mergeResult(AggregationResult aggregationResult) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).mergeResult(aggregationResult);
            return this;
        }

        public Builder clearResult() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearResult();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public ByteString getTransaction() {
            return ((RunAggregationQueryResponse) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public boolean hasReadTime() {
            return ((RunAggregationQueryResponse) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.RunAggregationQueryResponseOrBuilder
        public Timestamp getReadTime() {
            return ((RunAggregationQueryResponse) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunAggregationQueryResponse) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.RunAggregationQueryResponse$1, reason: invalid class name */
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
                return new RunAggregationQueryResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\n\u0003\t", new Object[]{"result_", "transaction_", "readTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RunAggregationQueryResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (RunAggregationQueryResponse.class) {
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
        RunAggregationQueryResponse runAggregationQueryResponse = new RunAggregationQueryResponse();
        DEFAULT_INSTANCE = runAggregationQueryResponse;
        GeneratedMessageLite.registerDefaultInstance(RunAggregationQueryResponse.class, runAggregationQueryResponse);
    }

    public static RunAggregationQueryResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RunAggregationQueryResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
