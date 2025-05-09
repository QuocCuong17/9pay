package com.google.firestore.v1;

import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentMask;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class CreateDocumentRequest extends GeneratedMessageLite<CreateDocumentRequest, Builder> implements CreateDocumentRequestOrBuilder {
    public static final int COLLECTION_ID_FIELD_NUMBER = 2;
    private static final CreateDocumentRequest DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 4;
    public static final int DOCUMENT_ID_FIELD_NUMBER = 3;
    public static final int MASK_FIELD_NUMBER = 5;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<CreateDocumentRequest> PARSER;
    private Document document_;
    private DocumentMask mask_;
    private String parent_ = "";
    private String collectionId_ = "";
    private String documentId_ = "";

    private CreateDocumentRequest() {
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
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

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public String getCollectionId() {
        return this.collectionId_;
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public ByteString getCollectionIdBytes() {
        return ByteString.copyFromUtf8(this.collectionId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionId(String str) {
        str.getClass();
        this.collectionId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionId() {
        this.collectionId_ = getDefaultInstance().getCollectionId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.collectionId_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public String getDocumentId() {
        return this.documentId_;
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public ByteString getDocumentIdBytes() {
        return ByteString.copyFromUtf8(this.documentId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentId(String str) {
        str.getClass();
        this.documentId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentId() {
        this.documentId_ = getDefaultInstance().getDocumentId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.documentId_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
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

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
    public boolean hasMask() {
        return this.mask_ != null;
    }

    @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
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

    public static CreateDocumentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static CreateDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateDocumentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateDocumentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateDocumentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(CreateDocumentRequest createDocumentRequest) {
        return DEFAULT_INSTANCE.createBuilder(createDocumentRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<CreateDocumentRequest, Builder> implements CreateDocumentRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(CreateDocumentRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public String getParent() {
            return ((CreateDocumentRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public ByteString getParentBytes() {
            return ((CreateDocumentRequest) this.instance).getParentBytes();
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setParent(str);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public String getCollectionId() {
            return ((CreateDocumentRequest) this.instance).getCollectionId();
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public ByteString getCollectionIdBytes() {
            return ((CreateDocumentRequest) this.instance).getCollectionIdBytes();
        }

        public Builder setCollectionId(String str) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setCollectionId(str);
            return this;
        }

        public Builder clearCollectionId() {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).clearCollectionId();
            return this;
        }

        public Builder setCollectionIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setCollectionIdBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public String getDocumentId() {
            return ((CreateDocumentRequest) this.instance).getDocumentId();
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public ByteString getDocumentIdBytes() {
            return ((CreateDocumentRequest) this.instance).getDocumentIdBytes();
        }

        public Builder setDocumentId(String str) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setDocumentId(str);
            return this;
        }

        public Builder clearDocumentId() {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).clearDocumentId();
            return this;
        }

        public Builder setDocumentIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setDocumentIdBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public boolean hasDocument() {
            return ((CreateDocumentRequest) this.instance).hasDocument();
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public Document getDocument() {
            return ((CreateDocumentRequest) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).clearDocument();
            return this;
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public boolean hasMask() {
            return ((CreateDocumentRequest) this.instance).hasMask();
        }

        @Override // com.google.firestore.v1.CreateDocumentRequestOrBuilder
        public DocumentMask getMask() {
            return ((CreateDocumentRequest) this.instance).getMask();
        }

        public Builder setMask(DocumentMask documentMask) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setMask(documentMask);
            return this;
        }

        public Builder setMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).setMask(builder.build());
            return this;
        }

        public Builder mergeMask(DocumentMask documentMask) {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).mergeMask(documentMask);
            return this;
        }

        public Builder clearMask() {
            copyOnWrite();
            ((CreateDocumentRequest) this.instance).clearMask();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.CreateDocumentRequest$1, reason: invalid class name */
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
                return new CreateDocumentRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\t\u0005\t", new Object[]{"parent_", "collectionId_", "documentId_", "document_", "mask_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<CreateDocumentRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (CreateDocumentRequest.class) {
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
        CreateDocumentRequest createDocumentRequest = new CreateDocumentRequest();
        DEFAULT_INSTANCE = createDocumentRequest;
        GeneratedMessageLite.registerDefaultInstance(CreateDocumentRequest.class, createDocumentRequest);
    }

    public static CreateDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateDocumentRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
