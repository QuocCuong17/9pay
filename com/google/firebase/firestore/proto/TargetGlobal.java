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
public final class TargetGlobal extends GeneratedMessageLite<TargetGlobal, Builder> implements TargetGlobalOrBuilder {
    private static final TargetGlobal DEFAULT_INSTANCE;
    public static final int HIGHEST_LISTEN_SEQUENCE_NUMBER_FIELD_NUMBER = 2;
    public static final int HIGHEST_TARGET_ID_FIELD_NUMBER = 1;
    public static final int LAST_REMOTE_SNAPSHOT_VERSION_FIELD_NUMBER = 3;
    private static volatile Parser<TargetGlobal> PARSER = null;
    public static final int TARGET_COUNT_FIELD_NUMBER = 4;
    private long highestListenSequenceNumber_;
    private int highestTargetId_;
    private Timestamp lastRemoteSnapshotVersion_;
    private int targetCount_;

    private TargetGlobal() {
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public int getHighestTargetId() {
        return this.highestTargetId_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHighestTargetId(int i) {
        this.highestTargetId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHighestTargetId() {
        this.highestTargetId_ = 0;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public long getHighestListenSequenceNumber() {
        return this.highestListenSequenceNumber_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHighestListenSequenceNumber(long j) {
        this.highestListenSequenceNumber_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHighestListenSequenceNumber() {
        this.highestListenSequenceNumber_ = 0L;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public boolean hasLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion_ != null;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public Timestamp getLastRemoteSnapshotVersion() {
        Timestamp timestamp = this.lastRemoteSnapshotVersion_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastRemoteSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.lastRemoteSnapshotVersion_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLastRemoteSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.lastRemoteSnapshotVersion_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.lastRemoteSnapshotVersion_ = Timestamp.newBuilder(this.lastRemoteSnapshotVersion_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.lastRemoteSnapshotVersion_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastRemoteSnapshotVersion() {
        this.lastRemoteSnapshotVersion_ = null;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public int getTargetCount() {
        return this.targetCount_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetCount(int i) {
        this.targetCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetCount() {
        this.targetCount_ = 0;
    }

    public static TargetGlobal parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static TargetGlobal parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TargetGlobal parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TargetGlobal parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(InputStream inputStream) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetGlobal parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetGlobal parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TargetGlobal) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetGlobal parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetGlobal) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TargetGlobal parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TargetGlobal targetGlobal) {
        return DEFAULT_INSTANCE.createBuilder(targetGlobal);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<TargetGlobal, Builder> implements TargetGlobalOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(TargetGlobal.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public int getHighestTargetId() {
            return ((TargetGlobal) this.instance).getHighestTargetId();
        }

        public Builder setHighestTargetId(int i) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setHighestTargetId(i);
            return this;
        }

        public Builder clearHighestTargetId() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearHighestTargetId();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public long getHighestListenSequenceNumber() {
            return ((TargetGlobal) this.instance).getHighestListenSequenceNumber();
        }

        public Builder setHighestListenSequenceNumber(long j) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setHighestListenSequenceNumber(j);
            return this;
        }

        public Builder clearHighestListenSequenceNumber() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearHighestListenSequenceNumber();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public boolean hasLastRemoteSnapshotVersion() {
            return ((TargetGlobal) this.instance).hasLastRemoteSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public Timestamp getLastRemoteSnapshotVersion() {
            return ((TargetGlobal) this.instance).getLastRemoteSnapshotVersion();
        }

        public Builder setLastRemoteSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setLastRemoteSnapshotVersion(timestamp);
            return this;
        }

        public Builder setLastRemoteSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setLastRemoteSnapshotVersion(builder.build());
            return this;
        }

        public Builder mergeLastRemoteSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((TargetGlobal) this.instance).mergeLastRemoteSnapshotVersion(timestamp);
            return this;
        }

        public Builder clearLastRemoteSnapshotVersion() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearLastRemoteSnapshotVersion();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public int getTargetCount() {
            return ((TargetGlobal) this.instance).getTargetCount();
        }

        public Builder setTargetCount(int i) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setTargetCount(i);
            return this;
        }

        public Builder clearTargetCount() {
            copyOnWrite();
            ((TargetGlobal) this.instance).clearTargetCount();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.TargetGlobal$1, reason: invalid class name */
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
                return new TargetGlobal();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0002\u0002\u0003\t\u0004\u0004", new Object[]{"highestTargetId_", "highestListenSequenceNumber_", "lastRemoteSnapshotVersion_", "targetCount_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<TargetGlobal> parser = PARSER;
                if (parser == null) {
                    synchronized (TargetGlobal.class) {
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
        TargetGlobal targetGlobal = new TargetGlobal();
        DEFAULT_INSTANCE = targetGlobal;
        GeneratedMessageLite.registerDefaultInstance(TargetGlobal.class, targetGlobal);
    }

    public static TargetGlobal getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TargetGlobal> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
