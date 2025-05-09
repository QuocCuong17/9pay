package com.google.firestore.v1;

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
public final class RollbackRequest extends GeneratedMessageLite<RollbackRequest, Builder> implements RollbackRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    private static final RollbackRequest DEFAULT_INSTANCE;
    private static volatile Parser<RollbackRequest> PARSER = null;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    private String database_ = "";
    private ByteString transaction_ = ByteString.EMPTY;

    private RollbackRequest() {
    }

    @Override // com.google.firestore.v1.RollbackRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.RollbackRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = getDefaultInstance().getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.RollbackRequestOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.transaction_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = getDefaultInstance().getTransaction();
    }

    public static RollbackRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static RollbackRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RollbackRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RollbackRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(InputStream inputStream) throws IOException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RollbackRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RollbackRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RollbackRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RollbackRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RollbackRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RollbackRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RollbackRequest rollbackRequest) {
        return DEFAULT_INSTANCE.createBuilder(rollbackRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<RollbackRequest, Builder> implements RollbackRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(RollbackRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.RollbackRequestOrBuilder
        public String getDatabase() {
            return ((RollbackRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.RollbackRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((RollbackRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((RollbackRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((RollbackRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((RollbackRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.RollbackRequestOrBuilder
        public ByteString getTransaction() {
            return ((RollbackRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RollbackRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RollbackRequest) this.instance).clearTransaction();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.RollbackRequest$1, reason: invalid class name */
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
                return new RollbackRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"database_", "transaction_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RollbackRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (RollbackRequest.class) {
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
        RollbackRequest rollbackRequest = new RollbackRequest();
        DEFAULT_INSTANCE = rollbackRequest;
        GeneratedMessageLite.registerDefaultInstance(RollbackRequest.class, rollbackRequest);
    }

    public static RollbackRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RollbackRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
