package com.google.firebase.firestore.proto;

import com.google.firebase.firestore.proto.NoDocument;
import com.google.firebase.firestore.proto.UnknownDocument;
import com.google.firestore.v1.Document;
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
public final class MaybeDocument extends GeneratedMessageLite<MaybeDocument, Builder> implements MaybeDocumentOrBuilder {
    private static final MaybeDocument DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 2;
    public static final int HAS_COMMITTED_MUTATIONS_FIELD_NUMBER = 4;
    public static final int NO_DOCUMENT_FIELD_NUMBER = 1;
    private static volatile Parser<MaybeDocument> PARSER = null;
    public static final int UNKNOWN_DOCUMENT_FIELD_NUMBER = 3;
    private int documentTypeCase_ = 0;
    private Object documentType_;
    private boolean hasCommittedMutations_;

    private MaybeDocument() {
    }

    /* loaded from: classes4.dex */
    public enum DocumentTypeCase {
        NO_DOCUMENT(1),
        DOCUMENT(2),
        UNKNOWN_DOCUMENT(3),
        DOCUMENTTYPE_NOT_SET(0);

        private final int value;

        DocumentTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static DocumentTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static DocumentTypeCase forNumber(int i) {
            if (i == 0) {
                return DOCUMENTTYPE_NOT_SET;
            }
            if (i == 1) {
                return NO_DOCUMENT;
            }
            if (i == 2) {
                return DOCUMENT;
            }
            if (i != 3) {
                return null;
            }
            return UNKNOWN_DOCUMENT;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public DocumentTypeCase getDocumentTypeCase() {
        return DocumentTypeCase.forNumber(this.documentTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentType() {
        this.documentTypeCase_ = 0;
        this.documentType_ = null;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean hasNoDocument() {
        return this.documentTypeCase_ == 1;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public NoDocument getNoDocument() {
        if (this.documentTypeCase_ == 1) {
            return (NoDocument) this.documentType_;
        }
        return NoDocument.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNoDocument(NoDocument noDocument) {
        noDocument.getClass();
        this.documentType_ = noDocument;
        this.documentTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNoDocument(NoDocument noDocument) {
        noDocument.getClass();
        if (this.documentTypeCase_ == 1 && this.documentType_ != NoDocument.getDefaultInstance()) {
            this.documentType_ = NoDocument.newBuilder((NoDocument) this.documentType_).mergeFrom((NoDocument.Builder) noDocument).buildPartial();
        } else {
            this.documentType_ = noDocument;
        }
        this.documentTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNoDocument() {
        if (this.documentTypeCase_ == 1) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean hasDocument() {
        return this.documentTypeCase_ == 2;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public Document getDocument() {
        if (this.documentTypeCase_ == 2) {
            return (Document) this.documentType_;
        }
        return Document.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.documentType_ = document;
        this.documentTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document document) {
        document.getClass();
        if (this.documentTypeCase_ == 2 && this.documentType_ != Document.getDefaultInstance()) {
            this.documentType_ = Document.newBuilder((Document) this.documentType_).mergeFrom((Document.Builder) document).buildPartial();
        } else {
            this.documentType_ = document;
        }
        this.documentTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        if (this.documentTypeCase_ == 2) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean hasUnknownDocument() {
        return this.documentTypeCase_ == 3;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public UnknownDocument getUnknownDocument() {
        if (this.documentTypeCase_ == 3) {
            return (UnknownDocument) this.documentType_;
        }
        return UnknownDocument.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnknownDocument(UnknownDocument unknownDocument) {
        unknownDocument.getClass();
        this.documentType_ = unknownDocument;
        this.documentTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUnknownDocument(UnknownDocument unknownDocument) {
        unknownDocument.getClass();
        if (this.documentTypeCase_ == 3 && this.documentType_ != UnknownDocument.getDefaultInstance()) {
            this.documentType_ = UnknownDocument.newBuilder((UnknownDocument) this.documentType_).mergeFrom((UnknownDocument.Builder) unknownDocument).buildPartial();
        } else {
            this.documentType_ = unknownDocument;
        }
        this.documentTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUnknownDocument() {
        if (this.documentTypeCase_ == 3) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean getHasCommittedMutations() {
        return this.hasCommittedMutations_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHasCommittedMutations(boolean z) {
        this.hasCommittedMutations_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHasCommittedMutations() {
        this.hasCommittedMutations_ = false;
    }

    public static MaybeDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static MaybeDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MaybeDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MaybeDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(InputStream inputStream) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MaybeDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MaybeDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MaybeDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MaybeDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MaybeDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MaybeDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(MaybeDocument maybeDocument) {
        return DEFAULT_INSTANCE.createBuilder(maybeDocument);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<MaybeDocument, Builder> implements MaybeDocumentOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(MaybeDocument.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public DocumentTypeCase getDocumentTypeCase() {
            return ((MaybeDocument) this.instance).getDocumentTypeCase();
        }

        public Builder clearDocumentType() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearDocumentType();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean hasNoDocument() {
            return ((MaybeDocument) this.instance).hasNoDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public NoDocument getNoDocument() {
            return ((MaybeDocument) this.instance).getNoDocument();
        }

        public Builder setNoDocument(NoDocument noDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setNoDocument(noDocument);
            return this;
        }

        public Builder setNoDocument(NoDocument.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setNoDocument(builder.build());
            return this;
        }

        public Builder mergeNoDocument(NoDocument noDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeNoDocument(noDocument);
            return this;
        }

        public Builder clearNoDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearNoDocument();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean hasDocument() {
            return ((MaybeDocument) this.instance).hasDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public Document getDocument() {
            return ((MaybeDocument) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearDocument();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean hasUnknownDocument() {
            return ((MaybeDocument) this.instance).hasUnknownDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public UnknownDocument getUnknownDocument() {
            return ((MaybeDocument) this.instance).getUnknownDocument();
        }

        public Builder setUnknownDocument(UnknownDocument unknownDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setUnknownDocument(unknownDocument);
            return this;
        }

        public Builder setUnknownDocument(UnknownDocument.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setUnknownDocument(builder.build());
            return this;
        }

        public Builder mergeUnknownDocument(UnknownDocument unknownDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeUnknownDocument(unknownDocument);
            return this;
        }

        public Builder clearUnknownDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearUnknownDocument();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean getHasCommittedMutations() {
            return ((MaybeDocument) this.instance).getHasCommittedMutations();
        }

        public Builder setHasCommittedMutations(boolean z) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setHasCommittedMutations(z);
            return this;
        }

        public Builder clearHasCommittedMutations() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearHasCommittedMutations();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.MaybeDocument$1, reason: invalid class name */
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
                return new MaybeDocument();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004\u0007", new Object[]{"documentType_", "documentTypeCase_", NoDocument.class, Document.class, UnknownDocument.class, "hasCommittedMutations_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<MaybeDocument> parser = PARSER;
                if (parser == null) {
                    synchronized (MaybeDocument.class) {
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
        MaybeDocument maybeDocument = new MaybeDocument();
        DEFAULT_INSTANCE = maybeDocument;
        GeneratedMessageLite.registerDefaultInstance(MaybeDocument.class, maybeDocument);
    }

    public static MaybeDocument getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MaybeDocument> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
