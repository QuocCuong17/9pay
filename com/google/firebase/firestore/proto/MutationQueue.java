package com.google.firebase.firestore.proto;

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
public final class MutationQueue extends GeneratedMessageLite<MutationQueue, Builder> implements MutationQueueOrBuilder {
    private static final MutationQueue DEFAULT_INSTANCE;
    public static final int LAST_ACKNOWLEDGED_BATCH_ID_FIELD_NUMBER = 1;
    public static final int LAST_STREAM_TOKEN_FIELD_NUMBER = 2;
    private static volatile Parser<MutationQueue> PARSER;
    private int lastAcknowledgedBatchId_;
    private ByteString lastStreamToken_ = ByteString.EMPTY;

    private MutationQueue() {
    }

    @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
    public int getLastAcknowledgedBatchId() {
        return this.lastAcknowledgedBatchId_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastAcknowledgedBatchId(int i) {
        this.lastAcknowledgedBatchId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastAcknowledgedBatchId() {
        this.lastAcknowledgedBatchId_ = 0;
    }

    @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
    public ByteString getLastStreamToken() {
        return this.lastStreamToken_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastStreamToken(ByteString byteString) {
        byteString.getClass();
        this.lastStreamToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastStreamToken() {
        this.lastStreamToken_ = getDefaultInstance().getLastStreamToken();
    }

    public static MutationQueue parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static MutationQueue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MutationQueue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MutationQueue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(InputStream inputStream) throws IOException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MutationQueue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MutationQueue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MutationQueue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MutationQueue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MutationQueue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MutationQueue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(MutationQueue mutationQueue) {
        return DEFAULT_INSTANCE.createBuilder(mutationQueue);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<MutationQueue, Builder> implements MutationQueueOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(MutationQueue.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
        public int getLastAcknowledgedBatchId() {
            return ((MutationQueue) this.instance).getLastAcknowledgedBatchId();
        }

        public Builder setLastAcknowledgedBatchId(int i) {
            copyOnWrite();
            ((MutationQueue) this.instance).setLastAcknowledgedBatchId(i);
            return this;
        }

        public Builder clearLastAcknowledgedBatchId() {
            copyOnWrite();
            ((MutationQueue) this.instance).clearLastAcknowledgedBatchId();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
        public ByteString getLastStreamToken() {
            return ((MutationQueue) this.instance).getLastStreamToken();
        }

        public Builder setLastStreamToken(ByteString byteString) {
            copyOnWrite();
            ((MutationQueue) this.instance).setLastStreamToken(byteString);
            return this;
        }

        public Builder clearLastStreamToken() {
            copyOnWrite();
            ((MutationQueue) this.instance).clearLastStreamToken();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.MutationQueue$1, reason: invalid class name */
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
                return new MutationQueue();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002\n", new Object[]{"lastAcknowledgedBatchId_", "lastStreamToken_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<MutationQueue> parser = PARSER;
                if (parser == null) {
                    synchronized (MutationQueue.class) {
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
        MutationQueue mutationQueue = new MutationQueue();
        DEFAULT_INSTANCE = mutationQueue;
        GeneratedMessageLite.registerDefaultInstance(MutationQueue.class, mutationQueue);
    }

    public static MutationQueue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MutationQueue> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
