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
public final class DocumentChange extends GeneratedMessageLite<DocumentChange, Builder> implements DocumentChangeOrBuilder {
    private static final DocumentChange DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    private static volatile Parser<DocumentChange> PARSER = null;
    public static final int REMOVED_TARGET_IDS_FIELD_NUMBER = 6;
    public static final int TARGET_IDS_FIELD_NUMBER = 5;
    private Document document_;
    private int targetIdsMemoizedSerializedSize = -1;
    private int removedTargetIdsMemoizedSerializedSize = -1;
    private Internal.IntList targetIds_ = emptyIntList();
    private Internal.IntList removedTargetIds_ = emptyIntList();

    private DocumentChange() {
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
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

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public List<Integer> getTargetIdsList() {
        return this.targetIds_;
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getTargetIdsCount() {
        return this.targetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getTargetIds(int i) {
        return this.targetIds_.getInt(i);
    }

    private void ensureTargetIdsIsMutable() {
        Internal.IntList intList = this.targetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.targetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetIds(int i, int i2) {
        ensureTargetIdsIsMutable();
        this.targetIds_.setInt(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTargetIds(int i) {
        ensureTargetIdsIsMutable();
        this.targetIds_.addInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllTargetIds(Iterable<? extends Integer> iterable) {
        ensureTargetIdsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.targetIds_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetIds() {
        this.targetIds_ = emptyIntList();
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public List<Integer> getRemovedTargetIdsList() {
        return this.removedTargetIds_;
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getRemovedTargetIdsCount() {
        return this.removedTargetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getRemovedTargetIds(int i) {
        return this.removedTargetIds_.getInt(i);
    }

    private void ensureRemovedTargetIdsIsMutable() {
        Internal.IntList intList = this.removedTargetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.removedTargetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemovedTargetIds(int i, int i2) {
        ensureRemovedTargetIdsIsMutable();
        this.removedTargetIds_.setInt(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRemovedTargetIds(int i) {
        ensureRemovedTargetIdsIsMutable();
        this.removedTargetIds_.addInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
        ensureRemovedTargetIdsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.removedTargetIds_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRemovedTargetIds() {
        this.removedTargetIds_ = emptyIntList();
    }

    public static DocumentChange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DocumentChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(InputStream inputStream) throws IOException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentChange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentChange) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentChange) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentChange documentChange) {
        return DEFAULT_INSTANCE.createBuilder(documentChange);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentChange, Builder> implements DocumentChangeOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DocumentChange.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public boolean hasDocument() {
            return ((DocumentChange) this.instance).hasDocument();
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public Document getDocument() {
            return ((DocumentChange) this.instance).getDocument();
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((DocumentChange) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((DocumentChange) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((DocumentChange) this.instance).mergeDocument(document);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentChange) this.instance).clearDocument();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public List<Integer> getTargetIdsList() {
            return Collections.unmodifiableList(((DocumentChange) this.instance).getTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getTargetIdsCount() {
            return ((DocumentChange) this.instance).getTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getTargetIds(int i) {
            return ((DocumentChange) this.instance).getTargetIds(i);
        }

        public Builder setTargetIds(int i, int i2) {
            copyOnWrite();
            ((DocumentChange) this.instance).setTargetIds(i, i2);
            return this;
        }

        public Builder addTargetIds(int i) {
            copyOnWrite();
            ((DocumentChange) this.instance).addTargetIds(i);
            return this;
        }

        public Builder addAllTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            ((DocumentChange) this.instance).addAllTargetIds(iterable);
            return this;
        }

        public Builder clearTargetIds() {
            copyOnWrite();
            ((DocumentChange) this.instance).clearTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public List<Integer> getRemovedTargetIdsList() {
            return Collections.unmodifiableList(((DocumentChange) this.instance).getRemovedTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getRemovedTargetIdsCount() {
            return ((DocumentChange) this.instance).getRemovedTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getRemovedTargetIds(int i) {
            return ((DocumentChange) this.instance).getRemovedTargetIds(i);
        }

        public Builder setRemovedTargetIds(int i, int i2) {
            copyOnWrite();
            ((DocumentChange) this.instance).setRemovedTargetIds(i, i2);
            return this;
        }

        public Builder addRemovedTargetIds(int i) {
            copyOnWrite();
            ((DocumentChange) this.instance).addRemovedTargetIds(i);
            return this;
        }

        public Builder addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            ((DocumentChange) this.instance).addAllRemovedTargetIds(iterable);
            return this;
        }

        public Builder clearRemovedTargetIds() {
            copyOnWrite();
            ((DocumentChange) this.instance).clearRemovedTargetIds();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentChange$1, reason: invalid class name */
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
                return new DocumentChange();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0006\u0003\u0000\u0002\u0000\u0001\t\u0005'\u0006'", new Object[]{"document_", "targetIds_", "removedTargetIds_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentChange> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentChange.class) {
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
        DocumentChange documentChange = new DocumentChange();
        DEFAULT_INSTANCE = documentChange;
        GeneratedMessageLite.registerDefaultInstance(DocumentChange.class, documentChange);
    }

    public static DocumentChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
