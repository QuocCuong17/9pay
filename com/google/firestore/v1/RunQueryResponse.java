package com.google.firestore.v1;

import com.google.firestore.v1.Document;
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
public final class RunQueryResponse extends GeneratedMessageLite<RunQueryResponse, Builder> implements RunQueryResponseOrBuilder {
    private static final RunQueryResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    private static volatile Parser<RunQueryResponse> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    public static final int SKIPPED_RESULTS_FIELD_NUMBER = 4;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    private Document document_;
    private Timestamp readTime_;
    private int skippedResults_;
    private ByteString transaction_ = ByteString.EMPTY;

    private RunQueryResponse() {
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
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

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public Document getDocument() {
        Document document = this.document_;
        return document == null ? Document.getDefaultInstance() : document;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.document_ = document;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document document) {
        document.getClass();
        Document document2 = this.document_;
        if (document2 != null && document2 != Document.getDefaultInstance()) {
            this.document_ = Document.newBuilder(this.document_).mergeFrom((Document.Builder) document).buildPartial();
        } else {
            this.document_ = document;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        this.document_ = null;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
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

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public int getSkippedResults() {
        return this.skippedResults_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSkippedResults(int i) {
        this.skippedResults_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSkippedResults() {
        this.skippedResults_ = 0;
    }

    public static RunQueryResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static RunQueryResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RunQueryResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RunQueryResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(InputStream inputStream) throws IOException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RunQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunQueryResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RunQueryResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RunQueryResponse runQueryResponse) {
        return DEFAULT_INSTANCE.createBuilder(runQueryResponse);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<RunQueryResponse, Builder> implements RunQueryResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(RunQueryResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public ByteString getTransaction() {
            return ((RunQueryResponse) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public boolean hasDocument() {
            return ((RunQueryResponse) this.instance).hasDocument();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public Document getDocument() {
            return ((RunQueryResponse) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).clearDocument();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public boolean hasReadTime() {
            return ((RunQueryResponse) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public Timestamp getReadTime() {
            return ((RunQueryResponse) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).clearReadTime();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public int getSkippedResults() {
            return ((RunQueryResponse) this.instance).getSkippedResults();
        }

        public Builder setSkippedResults(int i) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setSkippedResults(i);
            return this;
        }

        public Builder clearSkippedResults() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).clearSkippedResults();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.RunQueryResponse$1, reason: invalid class name */
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
                return new RunQueryResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\n\u0003\t\u0004\u0004", new Object[]{"document_", "transaction_", "readTime_", "skippedResults_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RunQueryResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (RunQueryResponse.class) {
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
        RunQueryResponse runQueryResponse = new RunQueryResponse();
        DEFAULT_INSTANCE = runQueryResponse;
        GeneratedMessageLite.registerDefaultInstance(RunQueryResponse.class, runQueryResponse);
    }

    public static RunQueryResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RunQueryResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
