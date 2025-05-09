package com.google.firestore.bundle;

import com.google.firestore.bundle.BundleMetadata;
import com.google.firestore.bundle.BundledDocumentMetadata;
import com.google.firestore.bundle.NamedQuery;
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
public final class BundleElement extends GeneratedMessageLite<BundleElement, Builder> implements BundleElementOrBuilder {
    private static final BundleElement DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 4;
    public static final int DOCUMENT_METADATA_FIELD_NUMBER = 3;
    public static final int METADATA_FIELD_NUMBER = 1;
    public static final int NAMED_QUERY_FIELD_NUMBER = 2;
    private static volatile Parser<BundleElement> PARSER;
    private int elementTypeCase_ = 0;
    private Object elementType_;

    private BundleElement() {
    }

    /* loaded from: classes4.dex */
    public enum ElementTypeCase {
        METADATA(1),
        NAMED_QUERY(2),
        DOCUMENT_METADATA(3),
        DOCUMENT(4),
        ELEMENTTYPE_NOT_SET(0);

        private final int value;

        ElementTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ElementTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ElementTypeCase forNumber(int i) {
            if (i == 0) {
                return ELEMENTTYPE_NOT_SET;
            }
            if (i == 1) {
                return METADATA;
            }
            if (i == 2) {
                return NAMED_QUERY;
            }
            if (i == 3) {
                return DOCUMENT_METADATA;
            }
            if (i != 4) {
                return null;
            }
            return DOCUMENT;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public ElementTypeCase getElementTypeCase() {
        return ElementTypeCase.forNumber(this.elementTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearElementType() {
        this.elementTypeCase_ = 0;
        this.elementType_ = null;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasMetadata() {
        return this.elementTypeCase_ == 1;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public BundleMetadata getMetadata() {
        if (this.elementTypeCase_ == 1) {
            return (BundleMetadata) this.elementType_;
        }
        return BundleMetadata.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(BundleMetadata bundleMetadata) {
        bundleMetadata.getClass();
        this.elementType_ = bundleMetadata;
        this.elementTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMetadata(BundleMetadata bundleMetadata) {
        bundleMetadata.getClass();
        if (this.elementTypeCase_ == 1 && this.elementType_ != BundleMetadata.getDefaultInstance()) {
            this.elementType_ = BundleMetadata.newBuilder((BundleMetadata) this.elementType_).mergeFrom((BundleMetadata.Builder) bundleMetadata).buildPartial();
        } else {
            this.elementType_ = bundleMetadata;
        }
        this.elementTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        if (this.elementTypeCase_ == 1) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasNamedQuery() {
        return this.elementTypeCase_ == 2;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public NamedQuery getNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            return (NamedQuery) this.elementType_;
        }
        return NamedQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNamedQuery(NamedQuery namedQuery) {
        namedQuery.getClass();
        this.elementType_ = namedQuery;
        this.elementTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNamedQuery(NamedQuery namedQuery) {
        namedQuery.getClass();
        if (this.elementTypeCase_ == 2 && this.elementType_ != NamedQuery.getDefaultInstance()) {
            this.elementType_ = NamedQuery.newBuilder((NamedQuery) this.elementType_).mergeFrom((NamedQuery.Builder) namedQuery).buildPartial();
        } else {
            this.elementType_ = namedQuery;
        }
        this.elementTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasDocumentMetadata() {
        return this.elementTypeCase_ == 3;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public BundledDocumentMetadata getDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            return (BundledDocumentMetadata) this.elementType_;
        }
        return BundledDocumentMetadata.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
        bundledDocumentMetadata.getClass();
        this.elementType_ = bundledDocumentMetadata;
        this.elementTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
        bundledDocumentMetadata.getClass();
        if (this.elementTypeCase_ == 3 && this.elementType_ != BundledDocumentMetadata.getDefaultInstance()) {
            this.elementType_ = BundledDocumentMetadata.newBuilder((BundledDocumentMetadata) this.elementType_).mergeFrom((BundledDocumentMetadata.Builder) bundledDocumentMetadata).buildPartial();
        } else {
            this.elementType_ = bundledDocumentMetadata;
        }
        this.elementTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasDocument() {
        return this.elementTypeCase_ == 4;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public Document getDocument() {
        if (this.elementTypeCase_ == 4) {
            return (Document) this.elementType_;
        }
        return Document.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.elementType_ = document;
        this.elementTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document document) {
        document.getClass();
        if (this.elementTypeCase_ == 4 && this.elementType_ != Document.getDefaultInstance()) {
            this.elementType_ = Document.newBuilder((Document) this.elementType_).mergeFrom((Document.Builder) document).buildPartial();
        } else {
            this.elementType_ = document;
        }
        this.elementTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        if (this.elementTypeCase_ == 4) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public static BundleElement parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BundleElement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundleElement parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundleElement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundleElement parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundleElement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundleElement parseFrom(InputStream inputStream) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleElement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleElement parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BundleElement) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleElement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleElement) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleElement parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundleElement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundleElement bundleElement) {
        return DEFAULT_INSTANCE.createBuilder(bundleElement);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BundleElement, Builder> implements BundleElementOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BundleElement.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public ElementTypeCase getElementTypeCase() {
            return ((BundleElement) this.instance).getElementTypeCase();
        }

        public Builder clearElementType() {
            copyOnWrite();
            ((BundleElement) this.instance).clearElementType();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasMetadata() {
            return ((BundleElement) this.instance).hasMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public BundleMetadata getMetadata() {
            return ((BundleElement) this.instance).getMetadata();
        }

        public Builder setMetadata(BundleMetadata bundleMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(bundleMetadata);
            return this;
        }

        public Builder setMetadata(BundleMetadata.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(builder.build());
            return this;
        }

        public Builder mergeMetadata(BundleMetadata bundleMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeMetadata(bundleMetadata);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearMetadata();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasNamedQuery() {
            return ((BundleElement) this.instance).hasNamedQuery();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public NamedQuery getNamedQuery() {
            return ((BundleElement) this.instance).getNamedQuery();
        }

        public Builder setNamedQuery(NamedQuery namedQuery) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(namedQuery);
            return this;
        }

        public Builder setNamedQuery(NamedQuery.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(builder.build());
            return this;
        }

        public Builder mergeNamedQuery(NamedQuery namedQuery) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeNamedQuery(namedQuery);
            return this;
        }

        public Builder clearNamedQuery() {
            copyOnWrite();
            ((BundleElement) this.instance).clearNamedQuery();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasDocumentMetadata() {
            return ((BundleElement) this.instance).hasDocumentMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public BundledDocumentMetadata getDocumentMetadata() {
            return ((BundleElement) this.instance).getDocumentMetadata();
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(bundledDocumentMetadata);
            return this;
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(builder.build());
            return this;
        }

        public Builder mergeDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocumentMetadata(bundledDocumentMetadata);
            return this;
        }

        public Builder clearDocumentMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocumentMetadata();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasDocument() {
            return ((BundleElement) this.instance).hasDocument();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public Document getDocument() {
            return ((BundleElement) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocument();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundleElement$1, reason: invalid class name */
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
                return new BundleElement();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000", new Object[]{"elementType_", "elementTypeCase_", BundleMetadata.class, NamedQuery.class, BundledDocumentMetadata.class, Document.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundleElement> parser = PARSER;
                if (parser == null) {
                    synchronized (BundleElement.class) {
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
        BundleElement bundleElement = new BundleElement();
        DEFAULT_INSTANCE = bundleElement;
        GeneratedMessageLite.registerDefaultInstance(BundleElement.class, bundleElement);
    }

    public static BundleElement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundleElement> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
