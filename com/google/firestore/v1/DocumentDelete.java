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
public final class DocumentDelete extends GeneratedMessageLite<DocumentDelete, Builder> implements DocumentDeleteOrBuilder {
    private static final DocumentDelete DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    private static volatile Parser<DocumentDelete> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 4;
    public static final int REMOVED_TARGET_IDS_FIELD_NUMBER = 6;
    private Timestamp readTime_;
    private int removedTargetIdsMemoizedSerializedSize = -1;
    private String document_ = "";
    private Internal.IntList removedTargetIds_ = emptyIntList();

    private DocumentDelete() {
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public String getDocument() {
        return this.document_;
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
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

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public List<Integer> getRemovedTargetIdsList() {
        return this.removedTargetIds_;
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public int getRemovedTargetIdsCount() {
        return this.removedTargetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
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

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
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

    public static DocumentDelete parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DocumentDelete parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentDelete parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentDelete parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(InputStream inputStream) throws IOException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentDelete parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentDelete parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentDelete) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentDelete parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentDelete) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentDelete parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentDelete documentDelete) {
        return DEFAULT_INSTANCE.createBuilder(documentDelete);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentDelete, Builder> implements DocumentDeleteOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DocumentDelete.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public String getDocument() {
            return ((DocumentDelete) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public ByteString getDocumentBytes() {
            return ((DocumentDelete) this.instance).getDocumentBytes();
        }

        public Builder setDocument(String str) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setDocument(str);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentDelete) this.instance).clearDocument();
            return this;
        }

        public Builder setDocumentBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setDocumentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public List<Integer> getRemovedTargetIdsList() {
            return Collections.unmodifiableList(((DocumentDelete) this.instance).getRemovedTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public int getRemovedTargetIdsCount() {
            return ((DocumentDelete) this.instance).getRemovedTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public int getRemovedTargetIds(int i) {
            return ((DocumentDelete) this.instance).getRemovedTargetIds(i);
        }

        public Builder setRemovedTargetIds(int i, int i2) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setRemovedTargetIds(i, i2);
            return this;
        }

        public Builder addRemovedTargetIds(int i) {
            copyOnWrite();
            ((DocumentDelete) this.instance).addRemovedTargetIds(i);
            return this;
        }

        public Builder addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            ((DocumentDelete) this.instance).addAllRemovedTargetIds(iterable);
            return this;
        }

        public Builder clearRemovedTargetIds() {
            copyOnWrite();
            ((DocumentDelete) this.instance).clearRemovedTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public boolean hasReadTime() {
            return ((DocumentDelete) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public Timestamp getReadTime() {
            return ((DocumentDelete) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentDelete) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((DocumentDelete) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentDelete$1, reason: invalid class name */
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
                return new DocumentDelete();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0006\u0003\u0000\u0001\u0000\u0001Ȉ\u0004\t\u0006'", new Object[]{"document_", "readTime_", "removedTargetIds_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentDelete> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentDelete.class) {
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
        DocumentDelete documentDelete = new DocumentDelete();
        DEFAULT_INSTANCE = documentDelete;
        GeneratedMessageLite.registerDefaultInstance(DocumentDelete.class, documentDelete);
    }

    public static DocumentDelete getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentDelete> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
