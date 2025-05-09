package com.google.firestore.v1;

import com.google.firestore.v1.DocumentMask;
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
public final class GetDocumentRequest extends GeneratedMessageLite<GetDocumentRequest, Builder> implements GetDocumentRequestOrBuilder {
    private static final GetDocumentRequest DEFAULT_INSTANCE;
    public static final int MASK_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<GetDocumentRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 5;
    public static final int TRANSACTION_FIELD_NUMBER = 3;
    private Object consistencySelector_;
    private DocumentMask mask_;
    private int consistencySelectorCase_ = 0;
    private String name_ = "";

    private GetDocumentRequest() {
    }

    /* loaded from: classes4.dex */
    public enum ConsistencySelectorCase {
        TRANSACTION(3),
        READ_TIME(5),
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
            if (i == 3) {
                return TRANSACTION;
            }
            if (i != 5) {
                return null;
            }
            return READ_TIME;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
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

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public boolean hasMask() {
        return this.mask_ != null;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
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

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public boolean hasTransaction() {
        return this.consistencySelectorCase_ == 3;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public ByteString getTransaction() {
        if (this.consistencySelectorCase_ == 3) {
            return (ByteString) this.consistencySelector_;
        }
        return ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 3;
        this.consistencySelector_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 3) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 5;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public Timestamp getReadTime() {
        if (this.consistencySelectorCase_ == 5) {
            return (Timestamp) this.consistencySelector_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.consistencySelector_ = timestamp;
        this.consistencySelectorCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        if (this.consistencySelectorCase_ == 5 && this.consistencySelector_ != Timestamp.getDefaultInstance()) {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.consistencySelector_ = timestamp;
        }
        this.consistencySelectorCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 5) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static GetDocumentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static GetDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetDocumentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetDocumentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetDocumentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(GetDocumentRequest getDocumentRequest) {
        return DEFAULT_INSTANCE.createBuilder(getDocumentRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<GetDocumentRequest, Builder> implements GetDocumentRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(GetDocumentRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((GetDocumentRequest) this.instance).getConsistencySelectorCase();
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearConsistencySelector();
            return this;
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public String getName() {
            return ((GetDocumentRequest) this.instance).getName();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public ByteString getNameBytes() {
            return ((GetDocumentRequest) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public boolean hasMask() {
            return ((GetDocumentRequest) this.instance).hasMask();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public DocumentMask getMask() {
            return ((GetDocumentRequest) this.instance).getMask();
        }

        public Builder setMask(DocumentMask documentMask) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setMask(documentMask);
            return this;
        }

        public Builder setMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setMask(builder.build());
            return this;
        }

        public Builder mergeMask(DocumentMask documentMask) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).mergeMask(documentMask);
            return this;
        }

        public Builder clearMask() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearMask();
            return this;
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public boolean hasTransaction() {
            return ((GetDocumentRequest) this.instance).hasTransaction();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public ByteString getTransaction() {
            return ((GetDocumentRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public boolean hasReadTime() {
            return ((GetDocumentRequest) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public Timestamp getReadTime() {
            return ((GetDocumentRequest) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.GetDocumentRequest$1, reason: invalid class name */
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
                return new GetDocumentRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0005\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003=\u0000\u0005<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", "name_", "mask_", Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<GetDocumentRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (GetDocumentRequest.class) {
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
        GetDocumentRequest getDocumentRequest = new GetDocumentRequest();
        DEFAULT_INSTANCE = getDocumentRequest;
        GeneratedMessageLite.registerDefaultInstance(GetDocumentRequest.class, getDocumentRequest);
    }

    public static GetDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetDocumentRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
