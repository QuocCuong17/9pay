package com.google.firestore.v1;

import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.TransactionOptions;
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
public final class BatchGetDocumentsRequest extends GeneratedMessageLite<BatchGetDocumentsRequest, Builder> implements BatchGetDocumentsRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    private static final BatchGetDocumentsRequest DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 2;
    public static final int MASK_FIELD_NUMBER = 3;
    public static final int NEW_TRANSACTION_FIELD_NUMBER = 5;
    private static volatile Parser<BatchGetDocumentsRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 7;
    public static final int TRANSACTION_FIELD_NUMBER = 4;
    private Object consistencySelector_;
    private DocumentMask mask_;
    private int consistencySelectorCase_ = 0;
    private String database_ = "";
    private Internal.ProtobufList<String> documents_ = GeneratedMessageLite.emptyProtobufList();

    private BatchGetDocumentsRequest() {
    }

    /* loaded from: classes4.dex */
    public enum ConsistencySelectorCase {
        TRANSACTION(4),
        NEW_TRANSACTION(5),
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
            if (i == 7) {
                return READ_TIME;
            }
            if (i == 4) {
                return TRANSACTION;
            }
            if (i != 5) {
                return null;
            }
            return NEW_TRANSACTION;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
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

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public List<String> getDocumentsList() {
        return this.documents_;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public int getDocumentsCount() {
        return this.documents_.size();
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public String getDocuments(int i) {
        return this.documents_.get(i);
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public ByteString getDocumentsBytes(int i) {
        return ByteString.copyFromUtf8(this.documents_.get(i));
    }

    private void ensureDocumentsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.documents_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.documents_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocuments(int i, String str) {
        str.getClass();
        ensureDocumentsIsMutable();
        this.documents_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDocuments(String str) {
        str.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllDocuments(Iterable<String> iterable) {
        ensureDocumentsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.documents_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocuments() {
        this.documents_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDocumentsBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        ensureDocumentsIsMutable();
        this.documents_.add(byteString.toStringUtf8());
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public boolean hasMask() {
        return this.mask_ != null;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public DocumentMask getMask() {
        DocumentMask documentMask = this.mask_;
        return documentMask == null ? DocumentMask.getDefaultInstance() : documentMask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMask(DocumentMask documentMask) {
        documentMask.getClass();
        this.mask_ = documentMask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMask(DocumentMask documentMask) {
        documentMask.getClass();
        DocumentMask documentMask2 = this.mask_;
        if (documentMask2 != null && documentMask2 != DocumentMask.getDefaultInstance()) {
            this.mask_ = DocumentMask.newBuilder(this.mask_).mergeFrom((DocumentMask.Builder) documentMask).buildPartial();
        } else {
            this.mask_ = documentMask;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMask() {
        this.mask_ = null;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public boolean hasTransaction() {
        return this.consistencySelectorCase_ == 4;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
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

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public boolean hasNewTransaction() {
        return this.consistencySelectorCase_ == 5;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
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

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 7;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
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

    public static BatchGetDocumentsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BatchGetDocumentsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BatchGetDocumentsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BatchGetDocumentsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BatchGetDocumentsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BatchGetDocumentsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BatchGetDocumentsRequest parseFrom(InputStream inputStream) throws IOException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchGetDocumentsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BatchGetDocumentsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BatchGetDocumentsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchGetDocumentsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchGetDocumentsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BatchGetDocumentsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BatchGetDocumentsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BatchGetDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BatchGetDocumentsRequest batchGetDocumentsRequest) {
        return DEFAULT_INSTANCE.createBuilder(batchGetDocumentsRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BatchGetDocumentsRequest, Builder> implements BatchGetDocumentsRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BatchGetDocumentsRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((BatchGetDocumentsRequest) this.instance).getConsistencySelectorCase();
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearConsistencySelector();
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public String getDatabase() {
            return ((BatchGetDocumentsRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((BatchGetDocumentsRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public List<String> getDocumentsList() {
            return Collections.unmodifiableList(((BatchGetDocumentsRequest) this.instance).getDocumentsList());
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public int getDocumentsCount() {
            return ((BatchGetDocumentsRequest) this.instance).getDocumentsCount();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public String getDocuments(int i) {
            return ((BatchGetDocumentsRequest) this.instance).getDocuments(i);
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public ByteString getDocumentsBytes(int i) {
            return ((BatchGetDocumentsRequest) this.instance).getDocumentsBytes(i);
        }

        public Builder setDocuments(int i, String str) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setDocuments(i, str);
            return this;
        }

        public Builder addDocuments(String str) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).addDocuments(str);
            return this;
        }

        public Builder addAllDocuments(Iterable<String> iterable) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).addAllDocuments(iterable);
            return this;
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearDocuments();
            return this;
        }

        public Builder addDocumentsBytes(ByteString byteString) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).addDocumentsBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public boolean hasMask() {
            return ((BatchGetDocumentsRequest) this.instance).hasMask();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public DocumentMask getMask() {
            return ((BatchGetDocumentsRequest) this.instance).getMask();
        }

        public Builder setMask(DocumentMask documentMask) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setMask(documentMask);
            return this;
        }

        public Builder setMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setMask(builder.build());
            return this;
        }

        public Builder mergeMask(DocumentMask documentMask) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).mergeMask(documentMask);
            return this;
        }

        public Builder clearMask() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearMask();
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public boolean hasTransaction() {
            return ((BatchGetDocumentsRequest) this.instance).hasTransaction();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public ByteString getTransaction() {
            return ((BatchGetDocumentsRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public boolean hasNewTransaction() {
            return ((BatchGetDocumentsRequest) this.instance).hasNewTransaction();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public TransactionOptions getNewTransaction() {
            return ((BatchGetDocumentsRequest) this.instance).getNewTransaction();
        }

        public Builder setNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setNewTransaction(transactionOptions);
            return this;
        }

        public Builder setNewTransaction(TransactionOptions.Builder builder) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setNewTransaction(builder.build());
            return this;
        }

        public Builder mergeNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).mergeNewTransaction(transactionOptions);
            return this;
        }

        public Builder clearNewTransaction() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearNewTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public boolean hasReadTime() {
            return ((BatchGetDocumentsRequest) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsRequestOrBuilder
        public Timestamp getReadTime() {
            return ((BatchGetDocumentsRequest) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((BatchGetDocumentsRequest) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.BatchGetDocumentsRequest$1, reason: invalid class name */
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
                return new BatchGetDocumentsRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0001\u0000\u0001\u0007\u0006\u0000\u0001\u0000\u0001Ȉ\u0002Ț\u0003\t\u0004=\u0000\u0005<\u0000\u0007<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", "database_", "documents_", "mask_", TransactionOptions.class, Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BatchGetDocumentsRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (BatchGetDocumentsRequest.class) {
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
        BatchGetDocumentsRequest batchGetDocumentsRequest = new BatchGetDocumentsRequest();
        DEFAULT_INSTANCE = batchGetDocumentsRequest;
        GeneratedMessageLite.registerDefaultInstance(BatchGetDocumentsRequest.class, batchGetDocumentsRequest);
    }

    public static BatchGetDocumentsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BatchGetDocumentsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
