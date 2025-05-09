package com.google.firestore.v1;

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
public final class DocumentRemove extends GeneratedMessageLite<DocumentRemove, Builder> implements DocumentRemoveOrBuilder {
    private static final DocumentRemove DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    private static volatile Parser<DocumentRemove> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 4;
    public static final int REMOVED_TARGET_IDS_FIELD_NUMBER = 2;
    private Timestamp readTime_;
    private int removedTargetIdsMemoizedSerializedSize = -1;
    private String document_ = "";
    private Internal.IntList removedTargetIds_ = emptyIntList();

    private DocumentRemove() {
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public String getDocument() {
        return this.document_;
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public ByteString getDocumentBytes() {
        return ByteString.copyFromUtf8(this.document_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(String str) {
        str.getClass();
        this.document_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        this.document_ = getDefaultInstance().getDocument();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.document_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public List<Integer> getRemovedTargetIdsList() {
        return this.removedTargetIds_;
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public int getRemovedTargetIdsCount() {
        return this.removedTargetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
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

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
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

    public static DocumentRemove parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DocumentRemove parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentRemove parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentRemove parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(InputStream inputStream) throws IOException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentRemove parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentRemove parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentRemove) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentRemove parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentRemove) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentRemove parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentRemove documentRemove) {
        return DEFAULT_INSTANCE.createBuilder(documentRemove);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentRemove, Builder> implements DocumentRemoveOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DocumentRemove.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public String getDocument() {
            return ((DocumentRemove) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public ByteString getDocumentBytes() {
            return ((DocumentRemove) this.instance).getDocumentBytes();
        }

        public Builder setDocument(String str) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setDocument(str);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentRemove) this.instance).clearDocument();
            return this;
        }

        public Builder setDocumentBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setDocumentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public List<Integer> getRemovedTargetIdsList() {
            return Collections.unmodifiableList(((DocumentRemove) this.instance).getRemovedTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public int getRemovedTargetIdsCount() {
            return ((DocumentRemove) this.instance).getRemovedTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public int getRemovedTargetIds(int i) {
            return ((DocumentRemove) this.instance).getRemovedTargetIds(i);
        }

        public Builder setRemovedTargetIds(int i, int i2) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setRemovedTargetIds(i, i2);
            return this;
        }

        public Builder addRemovedTargetIds(int i) {
            copyOnWrite();
            ((DocumentRemove) this.instance).addRemovedTargetIds(i);
            return this;
        }

        public Builder addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            ((DocumentRemove) this.instance).addAllRemovedTargetIds(iterable);
            return this;
        }

        public Builder clearRemovedTargetIds() {
            copyOnWrite();
            ((DocumentRemove) this.instance).clearRemovedTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public boolean hasReadTime() {
            return ((DocumentRemove) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public Timestamp getReadTime() {
            return ((DocumentRemove) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentRemove) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((DocumentRemove) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentRemove$1, reason: invalid class name */
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
                return new DocumentRemove();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0004\u0003\u0000\u0001\u0000\u0001Ȉ\u0002'\u0004\t", new Object[]{"document_", "removedTargetIds_", "readTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentRemove> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentRemove.class) {
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
        DocumentRemove documentRemove = new DocumentRemove();
        DEFAULT_INSTANCE = documentRemove;
        GeneratedMessageLite.registerDefaultInstance(DocumentRemove.class, documentRemove);
    }

    public static DocumentRemove getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentRemove> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
