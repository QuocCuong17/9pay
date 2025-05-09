package com.google.firestore.v1;

import com.google.firestore.v1.Document;
import com.google.firestore.v1.DocumentMask;
import com.google.firestore.v1.DocumentTransform;
import com.google.firestore.v1.Precondition;
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
public final class Write extends GeneratedMessageLite<Write, Builder> implements WriteOrBuilder {
    public static final int CURRENT_DOCUMENT_FIELD_NUMBER = 4;
    private static final Write DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 2;
    private static volatile Parser<Write> PARSER = null;
    public static final int TRANSFORM_FIELD_NUMBER = 6;
    public static final int UPDATE_FIELD_NUMBER = 1;
    public static final int UPDATE_MASK_FIELD_NUMBER = 3;
    public static final int UPDATE_TRANSFORMS_FIELD_NUMBER = 7;
    public static final int VERIFY_FIELD_NUMBER = 5;
    private Precondition currentDocument_;
    private Object operation_;
    private DocumentMask updateMask_;
    private int operationCase_ = 0;
    private Internal.ProtobufList<DocumentTransform.FieldTransform> updateTransforms_ = emptyProtobufList();

    private Write() {
    }

    /* loaded from: classes4.dex */
    public enum OperationCase {
        UPDATE(1),
        DELETE(2),
        VERIFY(5),
        TRANSFORM(6),
        OPERATION_NOT_SET(0);

        private final int value;

        OperationCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static OperationCase valueOf(int i) {
            return forNumber(i);
        }

        public static OperationCase forNumber(int i) {
            if (i == 0) {
                return OPERATION_NOT_SET;
            }
            if (i == 1) {
                return UPDATE;
            }
            if (i == 2) {
                return DELETE;
            }
            if (i == 5) {
                return VERIFY;
            }
            if (i != 6) {
                return null;
            }
            return TRANSFORM;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public OperationCase getOperationCase() {
        return OperationCase.forNumber(this.operationCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOperation() {
        this.operationCase_ = 0;
        this.operation_ = null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasUpdate() {
        return this.operationCase_ == 1;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public Document getUpdate() {
        if (this.operationCase_ == 1) {
            return (Document) this.operation_;
        }
        return Document.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdate(Document document) {
        document.getClass();
        this.operation_ = document;
        this.operationCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdate(Document document) {
        document.getClass();
        if (this.operationCase_ == 1 && this.operation_ != Document.getDefaultInstance()) {
            this.operation_ = Document.newBuilder((Document) this.operation_).mergeFrom((Document.Builder) document).buildPartial();
        } else {
            this.operation_ = document;
        }
        this.operationCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdate() {
        if (this.operationCase_ == 1) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasDelete() {
        return this.operationCase_ == 2;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public String getDelete() {
        return this.operationCase_ == 2 ? (String) this.operation_ : "";
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public ByteString getDeleteBytes() {
        return ByteString.copyFromUtf8(this.operationCase_ == 2 ? (String) this.operation_ : "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDelete(String str) {
        str.getClass();
        this.operationCase_ = 2;
        this.operation_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDelete() {
        if (this.operationCase_ == 2) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeleteBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.operation_ = byteString.toStringUtf8();
        this.operationCase_ = 2;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasVerify() {
        return this.operationCase_ == 5;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public String getVerify() {
        return this.operationCase_ == 5 ? (String) this.operation_ : "";
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public ByteString getVerifyBytes() {
        return ByteString.copyFromUtf8(this.operationCase_ == 5 ? (String) this.operation_ : "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVerify(String str) {
        str.getClass();
        this.operationCase_ = 5;
        this.operation_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVerify() {
        if (this.operationCase_ == 5) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVerifyBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.operation_ = byteString.toStringUtf8();
        this.operationCase_ = 5;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasTransform() {
        return this.operationCase_ == 6;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public DocumentTransform getTransform() {
        if (this.operationCase_ == 6) {
            return (DocumentTransform) this.operation_;
        }
        return DocumentTransform.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransform(DocumentTransform documentTransform) {
        documentTransform.getClass();
        this.operation_ = documentTransform;
        this.operationCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTransform(DocumentTransform documentTransform) {
        documentTransform.getClass();
        if (this.operationCase_ == 6 && this.operation_ != DocumentTransform.getDefaultInstance()) {
            this.operation_ = DocumentTransform.newBuilder((DocumentTransform) this.operation_).mergeFrom((DocumentTransform.Builder) documentTransform).buildPartial();
        } else {
            this.operation_ = documentTransform;
        }
        this.operationCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransform() {
        if (this.operationCase_ == 6) {
            this.operationCase_ = 0;
            this.operation_ = null;
        }
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasUpdateMask() {
        return this.updateMask_ != null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public DocumentMask getUpdateMask() {
        DocumentMask documentMask = this.updateMask_;
        return documentMask == null ? DocumentMask.getDefaultInstance() : documentMask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateMask(DocumentMask documentMask) {
        documentMask.getClass();
        this.updateMask_ = documentMask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateMask(DocumentMask documentMask) {
        documentMask.getClass();
        DocumentMask documentMask2 = this.updateMask_;
        if (documentMask2 != null && documentMask2 != DocumentMask.getDefaultInstance()) {
            this.updateMask_ = DocumentMask.newBuilder(this.updateMask_).mergeFrom((DocumentMask.Builder) documentMask).buildPartial();
        } else {
            this.updateMask_ = documentMask;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateMask() {
        this.updateMask_ = null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public List<DocumentTransform.FieldTransform> getUpdateTransformsList() {
        return this.updateTransforms_;
    }

    public List<? extends DocumentTransform.FieldTransformOrBuilder> getUpdateTransformsOrBuilderList() {
        return this.updateTransforms_;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public int getUpdateTransformsCount() {
        return this.updateTransforms_.size();
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public DocumentTransform.FieldTransform getUpdateTransforms(int i) {
        return this.updateTransforms_.get(i);
    }

    public DocumentTransform.FieldTransformOrBuilder getUpdateTransformsOrBuilder(int i) {
        return this.updateTransforms_.get(i);
    }

    private void ensureUpdateTransformsIsMutable() {
        Internal.ProtobufList<DocumentTransform.FieldTransform> protobufList = this.updateTransforms_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.updateTransforms_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTransforms(int i, DocumentTransform.FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.set(i, fieldTransform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdateTransforms(DocumentTransform.FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.add(fieldTransform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdateTransforms(int i, DocumentTransform.FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.add(i, fieldTransform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllUpdateTransforms(Iterable<? extends DocumentTransform.FieldTransform> iterable) {
        ensureUpdateTransformsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.updateTransforms_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateTransforms() {
        this.updateTransforms_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUpdateTransforms(int i) {
        ensureUpdateTransformsIsMutable();
        this.updateTransforms_.remove(i);
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public boolean hasCurrentDocument() {
        return this.currentDocument_ != null;
    }

    @Override // com.google.firestore.v1.WriteOrBuilder
    public Precondition getCurrentDocument() {
        Precondition precondition = this.currentDocument_;
        return precondition == null ? Precondition.getDefaultInstance() : precondition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentDocument(Precondition precondition) {
        precondition.getClass();
        this.currentDocument_ = precondition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCurrentDocument(Precondition precondition) {
        precondition.getClass();
        Precondition precondition2 = this.currentDocument_;
        if (precondition2 != null && precondition2 != Precondition.getDefaultInstance()) {
            this.currentDocument_ = Precondition.newBuilder(this.currentDocument_).mergeFrom((Precondition.Builder) precondition).buildPartial();
        } else {
            this.currentDocument_ = precondition;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCurrentDocument() {
        this.currentDocument_ = null;
    }

    public static Write parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Write parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Write parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Write parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Write parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Write parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Write parseFrom(InputStream inputStream) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Write parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Write parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Write parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Write) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Write parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Write parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Write) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Write write) {
        return DEFAULT_INSTANCE.createBuilder(write);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<Write, Builder> implements WriteOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Write.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public OperationCase getOperationCase() {
            return ((Write) this.instance).getOperationCase();
        }

        public Builder clearOperation() {
            copyOnWrite();
            ((Write) this.instance).clearOperation();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasUpdate() {
            return ((Write) this.instance).hasUpdate();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public Document getUpdate() {
            return ((Write) this.instance).getUpdate();
        }

        public Builder setUpdate(Document document) {
            copyOnWrite();
            ((Write) this.instance).setUpdate(document);
            return this;
        }

        public Builder setUpdate(Document.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setUpdate(builder.build());
            return this;
        }

        public Builder mergeUpdate(Document document) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdate(document);
            return this;
        }

        public Builder clearUpdate() {
            copyOnWrite();
            ((Write) this.instance).clearUpdate();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasDelete() {
            return ((Write) this.instance).hasDelete();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public String getDelete() {
            return ((Write) this.instance).getDelete();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public ByteString getDeleteBytes() {
            return ((Write) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String str) {
            copyOnWrite();
            ((Write) this.instance).setDelete(str);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((Write) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            copyOnWrite();
            ((Write) this.instance).setDeleteBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasVerify() {
            return ((Write) this.instance).hasVerify();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public String getVerify() {
            return ((Write) this.instance).getVerify();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public ByteString getVerifyBytes() {
            return ((Write) this.instance).getVerifyBytes();
        }

        public Builder setVerify(String str) {
            copyOnWrite();
            ((Write) this.instance).setVerify(str);
            return this;
        }

        public Builder clearVerify() {
            copyOnWrite();
            ((Write) this.instance).clearVerify();
            return this;
        }

        public Builder setVerifyBytes(ByteString byteString) {
            copyOnWrite();
            ((Write) this.instance).setVerifyBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasTransform() {
            return ((Write) this.instance).hasTransform();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public DocumentTransform getTransform() {
            return ((Write) this.instance).getTransform();
        }

        public Builder setTransform(DocumentTransform documentTransform) {
            copyOnWrite();
            ((Write) this.instance).setTransform(documentTransform);
            return this;
        }

        public Builder setTransform(DocumentTransform.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setTransform(builder.build());
            return this;
        }

        public Builder mergeTransform(DocumentTransform documentTransform) {
            copyOnWrite();
            ((Write) this.instance).mergeTransform(documentTransform);
            return this;
        }

        public Builder clearTransform() {
            copyOnWrite();
            ((Write) this.instance).clearTransform();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasUpdateMask() {
            return ((Write) this.instance).hasUpdateMask();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public DocumentMask getUpdateMask() {
            return ((Write) this.instance).getUpdateMask();
        }

        public Builder setUpdateMask(DocumentMask documentMask) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask(documentMask);
            return this;
        }

        public Builder setUpdateMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setUpdateMask(builder.build());
            return this;
        }

        public Builder mergeUpdateMask(DocumentMask documentMask) {
            copyOnWrite();
            ((Write) this.instance).mergeUpdateMask(documentMask);
            return this;
        }

        public Builder clearUpdateMask() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateMask();
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public List<DocumentTransform.FieldTransform> getUpdateTransformsList() {
            return Collections.unmodifiableList(((Write) this.instance).getUpdateTransformsList());
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public int getUpdateTransformsCount() {
            return ((Write) this.instance).getUpdateTransformsCount();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public DocumentTransform.FieldTransform getUpdateTransforms(int i) {
            return ((Write) this.instance).getUpdateTransforms(i);
        }

        public Builder setUpdateTransforms(int i, DocumentTransform.FieldTransform fieldTransform) {
            copyOnWrite();
            ((Write) this.instance).setUpdateTransforms(i, fieldTransform);
            return this;
        }

        public Builder setUpdateTransforms(int i, DocumentTransform.FieldTransform.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setUpdateTransforms(i, builder.build());
            return this;
        }

        public Builder addUpdateTransforms(DocumentTransform.FieldTransform fieldTransform) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(fieldTransform);
            return this;
        }

        public Builder addUpdateTransforms(int i, DocumentTransform.FieldTransform fieldTransform) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(i, fieldTransform);
            return this;
        }

        public Builder addUpdateTransforms(DocumentTransform.FieldTransform.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(builder.build());
            return this;
        }

        public Builder addUpdateTransforms(int i, DocumentTransform.FieldTransform.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).addUpdateTransforms(i, builder.build());
            return this;
        }

        public Builder addAllUpdateTransforms(Iterable<? extends DocumentTransform.FieldTransform> iterable) {
            copyOnWrite();
            ((Write) this.instance).addAllUpdateTransforms(iterable);
            return this;
        }

        public Builder clearUpdateTransforms() {
            copyOnWrite();
            ((Write) this.instance).clearUpdateTransforms();
            return this;
        }

        public Builder removeUpdateTransforms(int i) {
            copyOnWrite();
            ((Write) this.instance).removeUpdateTransforms(i);
            return this;
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public boolean hasCurrentDocument() {
            return ((Write) this.instance).hasCurrentDocument();
        }

        @Override // com.google.firestore.v1.WriteOrBuilder
        public Precondition getCurrentDocument() {
            return ((Write) this.instance).getCurrentDocument();
        }

        public Builder setCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument(precondition);
            return this;
        }

        public Builder setCurrentDocument(Precondition.Builder builder) {
            copyOnWrite();
            ((Write) this.instance).setCurrentDocument(builder.build());
            return this;
        }

        public Builder mergeCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((Write) this.instance).mergeCurrentDocument(precondition);
            return this;
        }

        public Builder clearCurrentDocument() {
            copyOnWrite();
            ((Write) this.instance).clearCurrentDocument();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Write$1, reason: invalid class name */
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
                return new Write();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001<\u0000\u0002Ȼ\u0000\u0003\t\u0004\t\u0005Ȼ\u0000\u0006<\u0000\u0007\u001b", new Object[]{"operation_", "operationCase_", Document.class, "updateMask_", "currentDocument_", DocumentTransform.class, "updateTransforms_", DocumentTransform.FieldTransform.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Write> parser = PARSER;
                if (parser == null) {
                    synchronized (Write.class) {
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
        Write write = new Write();
        DEFAULT_INSTANCE = write;
        GeneratedMessageLite.registerDefaultInstance(Write.class, write);
    }

    public static Write getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Write> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
