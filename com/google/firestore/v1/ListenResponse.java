package com.google.firestore.v1;

import com.google.firestore.v1.DocumentChange;
import com.google.firestore.v1.DocumentDelete;
import com.google.firestore.v1.DocumentRemove;
import com.google.firestore.v1.ExistenceFilter;
import com.google.firestore.v1.TargetChange;
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
public final class ListenResponse extends GeneratedMessageLite<ListenResponse, Builder> implements ListenResponseOrBuilder {
    private static final ListenResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_CHANGE_FIELD_NUMBER = 3;
    public static final int DOCUMENT_DELETE_FIELD_NUMBER = 4;
    public static final int DOCUMENT_REMOVE_FIELD_NUMBER = 6;
    public static final int FILTER_FIELD_NUMBER = 5;
    private static volatile Parser<ListenResponse> PARSER = null;
    public static final int TARGET_CHANGE_FIELD_NUMBER = 2;
    private int responseTypeCase_ = 0;
    private Object responseType_;

    private ListenResponse() {
    }

    /* loaded from: classes4.dex */
    public enum ResponseTypeCase {
        TARGET_CHANGE(2),
        DOCUMENT_CHANGE(3),
        DOCUMENT_DELETE(4),
        DOCUMENT_REMOVE(6),
        FILTER(5),
        RESPONSETYPE_NOT_SET(0);

        private final int value;

        ResponseTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ResponseTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ResponseTypeCase forNumber(int i) {
            if (i == 0) {
                return RESPONSETYPE_NOT_SET;
            }
            if (i == 2) {
                return TARGET_CHANGE;
            }
            if (i == 3) {
                return DOCUMENT_CHANGE;
            }
            if (i == 4) {
                return DOCUMENT_DELETE;
            }
            if (i == 5) {
                return FILTER;
            }
            if (i != 6) {
                return null;
            }
            return DOCUMENT_REMOVE;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public ResponseTypeCase getResponseTypeCase() {
        return ResponseTypeCase.forNumber(this.responseTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponseType() {
        this.responseTypeCase_ = 0;
        this.responseType_ = null;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasTargetChange() {
        return this.responseTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public TargetChange getTargetChange() {
        if (this.responseTypeCase_ == 2) {
            return (TargetChange) this.responseType_;
        }
        return TargetChange.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetChange(TargetChange targetChange) {
        targetChange.getClass();
        this.responseType_ = targetChange;
        this.responseTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTargetChange(TargetChange targetChange) {
        targetChange.getClass();
        if (this.responseTypeCase_ == 2 && this.responseType_ != TargetChange.getDefaultInstance()) {
            this.responseType_ = TargetChange.newBuilder((TargetChange) this.responseType_).mergeFrom((TargetChange.Builder) targetChange).buildPartial();
        } else {
            this.responseType_ = targetChange;
        }
        this.responseTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetChange() {
        if (this.responseTypeCase_ == 2) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentChange() {
        return this.responseTypeCase_ == 3;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentChange getDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            return (DocumentChange) this.responseType_;
        }
        return DocumentChange.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentChange(DocumentChange documentChange) {
        documentChange.getClass();
        this.responseType_ = documentChange;
        this.responseTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentChange(DocumentChange documentChange) {
        documentChange.getClass();
        if (this.responseTypeCase_ == 3 && this.responseType_ != DocumentChange.getDefaultInstance()) {
            this.responseType_ = DocumentChange.newBuilder((DocumentChange) this.responseType_).mergeFrom((DocumentChange.Builder) documentChange).buildPartial();
        } else {
            this.responseType_ = documentChange;
        }
        this.responseTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentDelete() {
        return this.responseTypeCase_ == 4;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentDelete getDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            return (DocumentDelete) this.responseType_;
        }
        return DocumentDelete.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentDelete(DocumentDelete documentDelete) {
        documentDelete.getClass();
        this.responseType_ = documentDelete;
        this.responseTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentDelete(DocumentDelete documentDelete) {
        documentDelete.getClass();
        if (this.responseTypeCase_ == 4 && this.responseType_ != DocumentDelete.getDefaultInstance()) {
            this.responseType_ = DocumentDelete.newBuilder((DocumentDelete) this.responseType_).mergeFrom((DocumentDelete.Builder) documentDelete).buildPartial();
        } else {
            this.responseType_ = documentDelete;
        }
        this.responseTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentRemove() {
        return this.responseTypeCase_ == 6;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentRemove getDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            return (DocumentRemove) this.responseType_;
        }
        return DocumentRemove.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentRemove(DocumentRemove documentRemove) {
        documentRemove.getClass();
        this.responseType_ = documentRemove;
        this.responseTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentRemove(DocumentRemove documentRemove) {
        documentRemove.getClass();
        if (this.responseTypeCase_ == 6 && this.responseType_ != DocumentRemove.getDefaultInstance()) {
            this.responseType_ = DocumentRemove.newBuilder((DocumentRemove) this.responseType_).mergeFrom((DocumentRemove.Builder) documentRemove).buildPartial();
        } else {
            this.responseType_ = documentRemove;
        }
        this.responseTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasFilter() {
        return this.responseTypeCase_ == 5;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public ExistenceFilter getFilter() {
        if (this.responseTypeCase_ == 5) {
            return (ExistenceFilter) this.responseType_;
        }
        return ExistenceFilter.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFilter(ExistenceFilter existenceFilter) {
        existenceFilter.getClass();
        this.responseType_ = existenceFilter;
        this.responseTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeFilter(ExistenceFilter existenceFilter) {
        existenceFilter.getClass();
        if (this.responseTypeCase_ == 5 && this.responseType_ != ExistenceFilter.getDefaultInstance()) {
            this.responseType_ = ExistenceFilter.newBuilder((ExistenceFilter) this.responseType_).mergeFrom((ExistenceFilter.Builder) existenceFilter).buildPartial();
        } else {
            this.responseType_ = existenceFilter;
        }
        this.responseTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFilter() {
        if (this.responseTypeCase_ == 5) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public static ListenResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ListenResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListenResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListenResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListenResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListenResponse listenResponse) {
        return DEFAULT_INSTANCE.createBuilder(listenResponse);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ListenResponse, Builder> implements ListenResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ListenResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public ResponseTypeCase getResponseTypeCase() {
            return ((ListenResponse) this.instance).getResponseTypeCase();
        }

        public Builder clearResponseType() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearResponseType();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasTargetChange() {
            return ((ListenResponse) this.instance).hasTargetChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public TargetChange getTargetChange() {
            return ((ListenResponse) this.instance).getTargetChange();
        }

        public Builder setTargetChange(TargetChange targetChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(targetChange);
            return this;
        }

        public Builder setTargetChange(TargetChange.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(builder.build());
            return this;
        }

        public Builder mergeTargetChange(TargetChange targetChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeTargetChange(targetChange);
            return this;
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearTargetChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentChange() {
            return ((ListenResponse) this.instance).hasDocumentChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentChange getDocumentChange() {
            return ((ListenResponse) this.instance).getDocumentChange();
        }

        public Builder setDocumentChange(DocumentChange documentChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(documentChange);
            return this;
        }

        public Builder setDocumentChange(DocumentChange.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(builder.build());
            return this;
        }

        public Builder mergeDocumentChange(DocumentChange documentChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentChange(documentChange);
            return this;
        }

        public Builder clearDocumentChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentDelete() {
            return ((ListenResponse) this.instance).hasDocumentDelete();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentDelete getDocumentDelete() {
            return ((ListenResponse) this.instance).getDocumentDelete();
        }

        public Builder setDocumentDelete(DocumentDelete documentDelete) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(documentDelete);
            return this;
        }

        public Builder setDocumentDelete(DocumentDelete.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(builder.build());
            return this;
        }

        public Builder mergeDocumentDelete(DocumentDelete documentDelete) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentDelete(documentDelete);
            return this;
        }

        public Builder clearDocumentDelete() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentDelete();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentRemove() {
            return ((ListenResponse) this.instance).hasDocumentRemove();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentRemove getDocumentRemove() {
            return ((ListenResponse) this.instance).getDocumentRemove();
        }

        public Builder setDocumentRemove(DocumentRemove documentRemove) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(documentRemove);
            return this;
        }

        public Builder setDocumentRemove(DocumentRemove.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(builder.build());
            return this;
        }

        public Builder mergeDocumentRemove(DocumentRemove documentRemove) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentRemove(documentRemove);
            return this;
        }

        public Builder clearDocumentRemove() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentRemove();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasFilter() {
            return ((ListenResponse) this.instance).hasFilter();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public ExistenceFilter getFilter() {
            return ((ListenResponse) this.instance).getFilter();
        }

        public Builder setFilter(ExistenceFilter existenceFilter) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(existenceFilter);
            return this;
        }

        public Builder setFilter(ExistenceFilter.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(builder.build());
            return this;
        }

        public Builder mergeFilter(ExistenceFilter existenceFilter) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeFilter(existenceFilter);
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearFilter();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListenResponse$1, reason: invalid class name */
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
                return new ListenResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0002\u0006\u0005\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"responseType_", "responseTypeCase_", TargetChange.class, DocumentChange.class, DocumentDelete.class, ExistenceFilter.class, DocumentRemove.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListenResponse> parser = PARSER;
                if (parser == null) {
                    synchronized (ListenResponse.class) {
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
        ListenResponse listenResponse = new ListenResponse();
        DEFAULT_INSTANCE = listenResponse;
        GeneratedMessageLite.registerDefaultInstance(ListenResponse.class, listenResponse);
    }

    public static ListenResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
