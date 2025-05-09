package com.google.firestore.v1;

import com.google.firestore.v1.Precondition;
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
public final class DeleteDocumentRequest extends GeneratedMessageLite<DeleteDocumentRequest, Builder> implements DeleteDocumentRequestOrBuilder {
    public static final int CURRENT_DOCUMENT_FIELD_NUMBER = 2;
    private static final DeleteDocumentRequest DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<DeleteDocumentRequest> PARSER;
    private Precondition currentDocument_;
    private String name_ = "";

    private DeleteDocumentRequest() {
    }

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
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

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
    public boolean hasCurrentDocument() {
        return this.currentDocument_ != null;
    }

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
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

    public static DeleteDocumentRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DeleteDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DeleteDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DeleteDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(InputStream inputStream) throws IOException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DeleteDocumentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteDocumentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DeleteDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DeleteDocumentRequest deleteDocumentRequest) {
        return DEFAULT_INSTANCE.createBuilder(deleteDocumentRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<DeleteDocumentRequest, Builder> implements DeleteDocumentRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DeleteDocumentRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public String getName() {
            return ((DeleteDocumentRequest) this.instance).getName();
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public ByteString getNameBytes() {
            return ((DeleteDocumentRequest) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public boolean hasCurrentDocument() {
            return ((DeleteDocumentRequest) this.instance).hasCurrentDocument();
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public Precondition getCurrentDocument() {
            return ((DeleteDocumentRequest) this.instance).getCurrentDocument();
        }

        public Builder setCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setCurrentDocument(precondition);
            return this;
        }

        public Builder setCurrentDocument(Precondition.Builder builder) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setCurrentDocument(builder.build());
            return this;
        }

        public Builder mergeCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).mergeCurrentDocument(precondition);
            return this;
        }

        public Builder clearCurrentDocument() {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).clearCurrentDocument();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.DeleteDocumentRequest$1, reason: invalid class name */
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
                return new DeleteDocumentRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "currentDocument_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DeleteDocumentRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (DeleteDocumentRequest.class) {
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
        DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest();
        DEFAULT_INSTANCE = deleteDocumentRequest;
        GeneratedMessageLite.registerDefaultInstance(DeleteDocumentRequest.class, deleteDocumentRequest);
    }

    public static DeleteDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeleteDocumentRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
