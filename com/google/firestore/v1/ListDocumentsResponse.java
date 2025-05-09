package com.google.firestore.v1;

import com.google.firestore.v1.Document;
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
public final class ListDocumentsResponse extends GeneratedMessageLite<ListDocumentsResponse, Builder> implements ListDocumentsResponseOrBuilder {
    private static final ListDocumentsResponse DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 1;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    private static volatile Parser<ListDocumentsResponse> PARSER;
    private Internal.ProtobufList<Document> documents_ = emptyProtobufList();
    private String nextPageToken_ = "";

    private ListDocumentsResponse() {
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public List<Document> getDocumentsList() {
        return this.documents_;
    }

    public List<? extends DocumentOrBuilder> getDocumentsOrBuilderList() {
        return this.documents_;
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public int getDocumentsCount() {
        return this.documents_.size();
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public Document getDocuments(int i) {
        return this.documents_.get(i);
    }

    public DocumentOrBuilder getDocumentsOrBuilder(int i) {
        return this.documents_.get(i);
    }

    private void ensureDocumentsIsMutable() {
        Internal.ProtobufList<Document> protobufList = this.documents_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.documents_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocuments(int i, Document document) {
        document.getClass();
        ensureDocumentsIsMutable();
        this.documents_.set(i, document);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDocuments(Document document) {
        document.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(document);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDocuments(int i, Document document) {
        document.getClass();
        ensureDocumentsIsMutable();
        this.documents_.add(i, document);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllDocuments(Iterable<? extends Document> iterable) {
        ensureDocumentsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.documents_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocuments() {
        this.documents_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDocuments(int i) {
        ensureDocumentsIsMutable();
        this.documents_.remove(i);
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public String getNextPageToken() {
        return this.nextPageToken_;
    }

    @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
    public ByteString getNextPageTokenBytes() {
        return ByteString.copyFromUtf8(this.nextPageToken_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextPageToken(String str) {
        str.getClass();
        this.nextPageToken_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNextPageToken() {
        this.nextPageToken_ = getDefaultInstance().getNextPageToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNextPageTokenBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.nextPageToken_ = byteString.toStringUtf8();
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ListDocumentsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListDocumentsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListDocumentsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListDocumentsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListDocumentsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListDocumentsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListDocumentsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListDocumentsResponse listDocumentsResponse) {
        return DEFAULT_INSTANCE.createBuilder(listDocumentsResponse);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ListDocumentsResponse, Builder> implements ListDocumentsResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ListDocumentsResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public List<Document> getDocumentsList() {
            return Collections.unmodifiableList(((ListDocumentsResponse) this.instance).getDocumentsList());
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public int getDocumentsCount() {
            return ((ListDocumentsResponse) this.instance).getDocumentsCount();
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public Document getDocuments(int i) {
            return ((ListDocumentsResponse) this.instance).getDocuments(i);
        }

        public Builder setDocuments(int i, Document document) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setDocuments(i, document);
            return this;
        }

        public Builder setDocuments(int i, Document.Builder builder) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setDocuments(i, builder.build());
            return this;
        }

        public Builder addDocuments(Document document) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(document);
            return this;
        }

        public Builder addDocuments(int i, Document document) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(i, document);
            return this;
        }

        public Builder addDocuments(Document.Builder builder) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(builder.build());
            return this;
        }

        public Builder addDocuments(int i, Document.Builder builder) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addDocuments(i, builder.build());
            return this;
        }

        public Builder addAllDocuments(Iterable<? extends Document> iterable) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).addAllDocuments(iterable);
            return this;
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).clearDocuments();
            return this;
        }

        public Builder removeDocuments(int i) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).removeDocuments(i);
            return this;
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public String getNextPageToken() {
            return ((ListDocumentsResponse) this.instance).getNextPageToken();
        }

        @Override // com.google.firestore.v1.ListDocumentsResponseOrBuilder
        public ByteString getNextPageTokenBytes() {
            return ((ListDocumentsResponse) this.instance).getNextPageTokenBytes();
        }

        public Builder setNextPageToken(String str) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setNextPageToken(str);
            return this;
        }

        public Builder clearNextPageToken() {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).clearNextPageToken();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsResponse) this.instance).setNextPageTokenBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListDocumentsResponse$1, reason: invalid class name */
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
                return new ListDocumentsResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002Ȉ", new Object[]{"documents_", Document.class, "nextPageToken_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListDocumentsResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListDocumentsResponse.class) {
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
        ListDocumentsResponse listDocumentsResponse = new ListDocumentsResponse();
        DEFAULT_INSTANCE = listDocumentsResponse;
        GeneratedMessageLite.registerDefaultInstance(ListDocumentsResponse.class, listDocumentsResponse);
    }

    public static ListDocumentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListDocumentsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
