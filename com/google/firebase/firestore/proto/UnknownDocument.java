package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class UnknownDocument extends GeneratedMessageLite<UnknownDocument, Builder> implements UnknownDocumentOrBuilder {
    private static final UnknownDocument DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<UnknownDocument> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 2;
    private String name_ = "";
    private Timestamp version_;

    private UnknownDocument() {
    }

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
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

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public boolean hasVersion() {
        return this.version_ != null;
    }

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public Timestamp getVersion() {
        Timestamp timestamp = this.version_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.version_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.version_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.version_ = Timestamp.newBuilder(this.version_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.version_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVersion() {
        this.version_ = null;
    }

    public static UnknownDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static UnknownDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UnknownDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UnknownDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(InputStream inputStream) throws IOException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UnknownDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UnknownDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UnknownDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UnknownDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UnknownDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UnknownDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(UnknownDocument unknownDocument) {
        return DEFAULT_INSTANCE.createBuilder(unknownDocument);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<UnknownDocument, Builder> implements UnknownDocumentOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(UnknownDocument.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public String getName() {
            return ((UnknownDocument) this.instance).getName();
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public ByteString getNameBytes() {
            return ((UnknownDocument) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((UnknownDocument) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public boolean hasVersion() {
            return ((UnknownDocument) this.instance).hasVersion();
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public Timestamp getVersion() {
            return ((UnknownDocument) this.instance).getVersion();
        }

        public Builder setVersion(Timestamp timestamp) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setVersion(timestamp);
            return this;
        }

        public Builder setVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setVersion(builder.build());
            return this;
        }

        public Builder mergeVersion(Timestamp timestamp) {
            copyOnWrite();
            ((UnknownDocument) this.instance).mergeVersion(timestamp);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((UnknownDocument) this.instance).clearVersion();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.UnknownDocument$1, reason: invalid class name */
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
                return new UnknownDocument();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "version_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<UnknownDocument> parser = PARSER;
                if (parser == null) {
                    synchronized (UnknownDocument.class) {
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
        UnknownDocument unknownDocument = new UnknownDocument();
        DEFAULT_INSTANCE = unknownDocument;
        GeneratedMessageLite.registerDefaultInstance(UnknownDocument.class, unknownDocument);
    }

    public static UnknownDocument getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UnknownDocument> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
