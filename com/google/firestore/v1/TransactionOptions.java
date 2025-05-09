package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class TransactionOptions extends GeneratedMessageLite<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
    private static final TransactionOptions DEFAULT_INSTANCE;
    private static volatile Parser<TransactionOptions> PARSER = null;
    public static final int READ_ONLY_FIELD_NUMBER = 2;
    public static final int READ_WRITE_FIELD_NUMBER = 3;
    private int modeCase_ = 0;
    private Object mode_;

    /* loaded from: classes4.dex */
    public interface ReadOnlyOrBuilder extends MessageLiteOrBuilder {
        ReadOnly.ConsistencySelectorCase getConsistencySelectorCase();

        Timestamp getReadTime();

        boolean hasReadTime();
    }

    /* loaded from: classes4.dex */
    public interface ReadWriteOrBuilder extends MessageLiteOrBuilder {
        ByteString getRetryTransaction();
    }

    private TransactionOptions() {
    }

    /* loaded from: classes4.dex */
    public static final class ReadWrite extends GeneratedMessageLite<ReadWrite, Builder> implements ReadWriteOrBuilder {
        private static final ReadWrite DEFAULT_INSTANCE;
        private static volatile Parser<ReadWrite> PARSER = null;
        public static final int RETRY_TRANSACTION_FIELD_NUMBER = 1;
        private ByteString retryTransaction_ = ByteString.EMPTY;

        private ReadWrite() {
        }

        @Override // com.google.firestore.v1.TransactionOptions.ReadWriteOrBuilder
        public ByteString getRetryTransaction() {
            return this.retryTransaction_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRetryTransaction(ByteString byteString) {
            byteString.getClass();
            this.retryTransaction_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRetryTransaction() {
            this.retryTransaction_ = getDefaultInstance().getRetryTransaction();
        }

        public static ReadWrite parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ReadWrite parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReadWrite parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReadWrite parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(InputStream inputStream) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadWrite parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadWrite parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReadWrite) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadWrite parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadWrite) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReadWrite parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ReadWrite readWrite) {
            return DEFAULT_INSTANCE.createBuilder(readWrite);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ReadWrite, Builder> implements ReadWriteOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(ReadWrite.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadWriteOrBuilder
            public ByteString getRetryTransaction() {
                return ((ReadWrite) this.instance).getRetryTransaction();
            }

            public Builder setRetryTransaction(ByteString byteString) {
                copyOnWrite();
                ((ReadWrite) this.instance).setRetryTransaction(byteString);
                return this;
            }

            public Builder clearRetryTransaction() {
                copyOnWrite();
                ((ReadWrite) this.instance).clearRetryTransaction();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ReadWrite();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"retryTransaction_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ReadWrite> parser = PARSER;
                    if (parser == null) {
                        synchronized (ReadWrite.class) {
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
            ReadWrite readWrite = new ReadWrite();
            DEFAULT_INSTANCE = readWrite;
            GeneratedMessageLite.registerDefaultInstance(ReadWrite.class, readWrite);
        }

        public static ReadWrite getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReadWrite> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.TransactionOptions$1, reason: invalid class name */
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

    /* loaded from: classes4.dex */
    public static final class ReadOnly extends GeneratedMessageLite<ReadOnly, Builder> implements ReadOnlyOrBuilder {
        private static final ReadOnly DEFAULT_INSTANCE;
        private static volatile Parser<ReadOnly> PARSER = null;
        public static final int READ_TIME_FIELD_NUMBER = 2;
        private int consistencySelectorCase_ = 0;
        private Object consistencySelector_;

        private ReadOnly() {
        }

        /* loaded from: classes4.dex */
        public enum ConsistencySelectorCase {
            READ_TIME(2),
            CONSISTENCYSELECTOR_NOT_SET(0);

            private final int value;

            ConsistencySelectorCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ConsistencySelectorCase valueOf(int i) {
                return forNumber(i);
            }

            public static ConsistencySelectorCase forNumber(int i) {
                if (i == 0) {
                    return CONSISTENCYSELECTOR_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return READ_TIME;
            }

            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConsistencySelector() {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }

        @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
        public boolean hasReadTime() {
            return this.consistencySelectorCase_ == 2;
        }

        @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
        public Timestamp getReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                return (Timestamp) this.consistencySelector_;
            }
            return Timestamp.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReadTime(Timestamp timestamp) {
            timestamp.getClass();
            this.consistencySelector_ = timestamp;
            this.consistencySelectorCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeReadTime(Timestamp timestamp) {
            timestamp.getClass();
            if (this.consistencySelectorCase_ == 2 && this.consistencySelector_ != Timestamp.getDefaultInstance()) {
                this.consistencySelector_ = Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
            } else {
                this.consistencySelector_ = timestamp;
            }
            this.consistencySelectorCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                this.consistencySelectorCase_ = 0;
                this.consistencySelector_ = null;
            }
        }

        public static ReadOnly parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ReadOnly parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReadOnly parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReadOnly parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(InputStream inputStream) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadOnly parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadOnly parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReadOnly) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadOnly parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadOnly) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReadOnly parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(ReadOnly readOnly) {
            return DEFAULT_INSTANCE.createBuilder(readOnly);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<ReadOnly, Builder> implements ReadOnlyOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(ReadOnly.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
            public ConsistencySelectorCase getConsistencySelectorCase() {
                return ((ReadOnly) this.instance).getConsistencySelectorCase();
            }

            public Builder clearConsistencySelector() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearConsistencySelector();
                return this;
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
            public boolean hasReadTime() {
                return ((ReadOnly) this.instance).hasReadTime();
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
            public Timestamp getReadTime() {
                return ((ReadOnly) this.instance).getReadTime();
            }

            public Builder setReadTime(Timestamp timestamp) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime(timestamp);
                return this;
            }

            public Builder setReadTime(Timestamp.Builder builder) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime(builder.build());
                return this;
            }

            public Builder mergeReadTime(Timestamp timestamp) {
                copyOnWrite();
                ((ReadOnly) this.instance).mergeReadTime(timestamp);
                return this;
            }

            public Builder clearReadTime() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearReadTime();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ReadOnly();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0001\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", Timestamp.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<ReadOnly> parser = PARSER;
                    if (parser == null) {
                        synchronized (ReadOnly.class) {
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
            ReadOnly readOnly = new ReadOnly();
            DEFAULT_INSTANCE = readOnly;
            GeneratedMessageLite.registerDefaultInstance(ReadOnly.class, readOnly);
        }

        public static ReadOnly getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReadOnly> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public enum ModeCase {
        READ_ONLY(2),
        READ_WRITE(3),
        MODE_NOT_SET(0);

        private final int value;

        ModeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ModeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ModeCase forNumber(int i) {
            if (i == 0) {
                return MODE_NOT_SET;
            }
            if (i == 2) {
                return READ_ONLY;
            }
            if (i != 3) {
                return null;
            }
            return READ_WRITE;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public ModeCase getModeCase() {
        return ModeCase.forNumber(this.modeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMode() {
        this.modeCase_ = 0;
        this.mode_ = null;
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public boolean hasReadOnly() {
        return this.modeCase_ == 2;
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public ReadOnly getReadOnly() {
        if (this.modeCase_ == 2) {
            return (ReadOnly) this.mode_;
        }
        return ReadOnly.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadOnly(ReadOnly readOnly) {
        readOnly.getClass();
        this.mode_ = readOnly;
        this.modeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadOnly(ReadOnly readOnly) {
        readOnly.getClass();
        if (this.modeCase_ == 2 && this.mode_ != ReadOnly.getDefaultInstance()) {
            this.mode_ = ReadOnly.newBuilder((ReadOnly) this.mode_).mergeFrom((ReadOnly.Builder) readOnly).buildPartial();
        } else {
            this.mode_ = readOnly;
        }
        this.modeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadOnly() {
        if (this.modeCase_ == 2) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public boolean hasReadWrite() {
        return this.modeCase_ == 3;
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public ReadWrite getReadWrite() {
        if (this.modeCase_ == 3) {
            return (ReadWrite) this.mode_;
        }
        return ReadWrite.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadWrite(ReadWrite readWrite) {
        readWrite.getClass();
        this.mode_ = readWrite;
        this.modeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadWrite(ReadWrite readWrite) {
        readWrite.getClass();
        if (this.modeCase_ == 3 && this.mode_ != ReadWrite.getDefaultInstance()) {
            this.mode_ = ReadWrite.newBuilder((ReadWrite) this.mode_).mergeFrom((ReadWrite.Builder) readWrite).buildPartial();
        } else {
            this.mode_ = readWrite;
        }
        this.modeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadWrite() {
        if (this.modeCase_ == 3) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    public static TransactionOptions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static TransactionOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TransactionOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TransactionOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(InputStream inputStream) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TransactionOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TransactionOptions) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TransactionOptions) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TransactionOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TransactionOptions transactionOptions) {
        return DEFAULT_INSTANCE.createBuilder(transactionOptions);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(TransactionOptions.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public ModeCase getModeCase() {
            return ((TransactionOptions) this.instance).getModeCase();
        }

        public Builder clearMode() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearMode();
            return this;
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public boolean hasReadOnly() {
            return ((TransactionOptions) this.instance).hasReadOnly();
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public ReadOnly getReadOnly() {
            return ((TransactionOptions) this.instance).getReadOnly();
        }

        public Builder setReadOnly(ReadOnly readOnly) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly(readOnly);
            return this;
        }

        public Builder setReadOnly(ReadOnly.Builder builder) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly(builder.build());
            return this;
        }

        public Builder mergeReadOnly(ReadOnly readOnly) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadOnly(readOnly);
            return this;
        }

        public Builder clearReadOnly() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadOnly();
            return this;
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public boolean hasReadWrite() {
            return ((TransactionOptions) this.instance).hasReadWrite();
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public ReadWrite getReadWrite() {
            return ((TransactionOptions) this.instance).getReadWrite();
        }

        public Builder setReadWrite(ReadWrite readWrite) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite(readWrite);
            return this;
        }

        public Builder setReadWrite(ReadWrite.Builder builder) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite(builder.build());
            return this;
        }

        public Builder mergeReadWrite(ReadWrite readWrite) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadWrite(readWrite);
            return this;
        }

        public Builder clearReadWrite() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadWrite();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new TransactionOptions();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"mode_", "modeCase_", ReadOnly.class, ReadWrite.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<TransactionOptions> parser = PARSER;
                if (parser == null) {
                    synchronized (TransactionOptions.class) {
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
        TransactionOptions transactionOptions = new TransactionOptions();
        DEFAULT_INSTANCE = transactionOptions;
        GeneratedMessageLite.registerDefaultInstance(TransactionOptions.class, transactionOptions);
    }

    public static TransactionOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TransactionOptions> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
