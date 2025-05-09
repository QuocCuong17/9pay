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
public final class NoDocument extends GeneratedMessageLite<NoDocument, Builder> implements NoDocumentOrBuilder {
    private static final NoDocument DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<NoDocument> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 2;
    private String name_ = "";
    private Timestamp readTime_;

    private NoDocument() {
    }

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
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

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
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

    public static NoDocument parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static NoDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static NoDocument parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static NoDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static NoDocument parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static NoDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static NoDocument parseFrom(InputStream inputStream) throws IOException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NoDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NoDocument parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (NoDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NoDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NoDocument) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NoDocument parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static NoDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(NoDocument noDocument) {
        return DEFAULT_INSTANCE.createBuilder(noDocument);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<NoDocument, Builder> implements NoDocumentOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(NoDocument.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public String getName() {
            return ((NoDocument) this.instance).getName();
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public ByteString getNameBytes() {
            return ((NoDocument) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((NoDocument) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((NoDocument) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((NoDocument) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public boolean hasReadTime() {
            return ((NoDocument) this.instance).hasReadTime();
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public Timestamp getReadTime() {
            return ((NoDocument) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NoDocument) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((NoDocument) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NoDocument) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((NoDocument) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.NoDocument$1, reason: invalid class name */
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
                return new NoDocument();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "readTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<NoDocument> parser = PARSER;
                if (parser == null) {
                    synchronized (NoDocument.class) {
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
        NoDocument noDocument = new NoDocument();
        DEFAULT_INSTANCE = noDocument;
        GeneratedMessageLite.registerDefaultInstance(NoDocument.class, noDocument);
    }

    public static NoDocument getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NoDocument> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
