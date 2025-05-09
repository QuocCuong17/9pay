package com.google.firebase.firestore.proto;

import com.google.firestore.v1.Target;
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
public final class Target extends GeneratedMessageLite<Target, Builder> implements TargetOrBuilder {
    private static final Target DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 6;
    public static final int LAST_LIMBO_FREE_SNAPSHOT_VERSION_FIELD_NUMBER = 7;
    public static final int LAST_LISTEN_SEQUENCE_NUMBER_FIELD_NUMBER = 4;
    private static volatile Parser<Target> PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 5;
    public static final int RESUME_TOKEN_FIELD_NUMBER = 3;
    public static final int SNAPSHOT_VERSION_FIELD_NUMBER = 2;
    public static final int TARGET_ID_FIELD_NUMBER = 1;
    private Timestamp lastLimboFreeSnapshotVersion_;
    private long lastListenSequenceNumber_;
    private Timestamp snapshotVersion_;
    private int targetId_;
    private Object targetType_;
    private int targetTypeCase_ = 0;
    private ByteString resumeToken_ = ByteString.EMPTY;

    private Target() {
    }

    /* loaded from: classes4.dex */
    public enum TargetTypeCase {
        QUERY(5),
        DOCUMENTS(6),
        TARGETTYPE_NOT_SET(0);

        private final int value;

        TargetTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static TargetTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static TargetTypeCase forNumber(int i) {
            if (i == 0) {
                return TARGETTYPE_NOT_SET;
            }
            if (i == 5) {
                return QUERY;
            }
            if (i != 6) {
                return null;
            }
            return DOCUMENTS;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public TargetTypeCase getTargetTypeCase() {
        return TargetTypeCase.forNumber(this.targetTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetType() {
        this.targetTypeCase_ = 0;
        this.targetType_ = null;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public int getTargetId() {
        return this.targetId_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetId(int i) {
        this.targetId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetId() {
        this.targetId_ = 0;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasSnapshotVersion() {
        return this.snapshotVersion_ != null;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public Timestamp getSnapshotVersion() {
        Timestamp timestamp = this.snapshotVersion_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.snapshotVersion_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.snapshotVersion_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.snapshotVersion_ = Timestamp.newBuilder(this.snapshotVersion_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.snapshotVersion_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSnapshotVersion() {
        this.snapshotVersion_ = null;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public ByteString getResumeToken() {
        return this.resumeToken_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeToken(ByteString byteString) {
        byteString.getClass();
        this.resumeToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeToken() {
        this.resumeToken_ = getDefaultInstance().getResumeToken();
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public long getLastListenSequenceNumber() {
        return this.lastListenSequenceNumber_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastListenSequenceNumber(long j) {
        this.lastListenSequenceNumber_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastListenSequenceNumber() {
        this.lastListenSequenceNumber_ = 0L;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasQuery() {
        return this.targetTypeCase_ == 5;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public Target.QueryTarget getQuery() {
        if (this.targetTypeCase_ == 5) {
            return (Target.QueryTarget) this.targetType_;
        }
        return Target.QueryTarget.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuery(Target.QueryTarget queryTarget) {
        queryTarget.getClass();
        this.targetType_ = queryTarget;
        this.targetTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeQuery(Target.QueryTarget queryTarget) {
        queryTarget.getClass();
        if (this.targetTypeCase_ == 5 && this.targetType_ != Target.QueryTarget.getDefaultInstance()) {
            this.targetType_ = Target.QueryTarget.newBuilder((Target.QueryTarget) this.targetType_).mergeFrom((Target.QueryTarget.Builder) queryTarget).buildPartial();
        } else {
            this.targetType_ = queryTarget;
        }
        this.targetTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQuery() {
        if (this.targetTypeCase_ == 5) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasDocuments() {
        return this.targetTypeCase_ == 6;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public Target.DocumentsTarget getDocuments() {
        if (this.targetTypeCase_ == 6) {
            return (Target.DocumentsTarget) this.targetType_;
        }
        return Target.DocumentsTarget.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocuments(Target.DocumentsTarget documentsTarget) {
        documentsTarget.getClass();
        this.targetType_ = documentsTarget;
        this.targetTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocuments(Target.DocumentsTarget documentsTarget) {
        documentsTarget.getClass();
        if (this.targetTypeCase_ == 6 && this.targetType_ != Target.DocumentsTarget.getDefaultInstance()) {
            this.targetType_ = Target.DocumentsTarget.newBuilder((Target.DocumentsTarget) this.targetType_).mergeFrom((Target.DocumentsTarget.Builder) documentsTarget).buildPartial();
        } else {
            this.targetType_ = documentsTarget;
        }
        this.targetTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocuments() {
        if (this.targetTypeCase_ == 6) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasLastLimboFreeSnapshotVersion() {
        return this.lastLimboFreeSnapshotVersion_ != null;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public Timestamp getLastLimboFreeSnapshotVersion() {
        Timestamp timestamp = this.lastLimboFreeSnapshotVersion_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastLimboFreeSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.lastLimboFreeSnapshotVersion_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLastLimboFreeSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.lastLimboFreeSnapshotVersion_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.lastLimboFreeSnapshotVersion_ = Timestamp.newBuilder(this.lastLimboFreeSnapshotVersion_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.lastLimboFreeSnapshotVersion_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastLimboFreeSnapshotVersion() {
        this.lastLimboFreeSnapshotVersion_ = null;
    }

    public static Target parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Target parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Target parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Target parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Target parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Target parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Target parseFrom(InputStream inputStream) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Target) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Target) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Target parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Target target) {
        return DEFAULT_INSTANCE.createBuilder(target);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<Target, Builder> implements TargetOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Target.DEFAULT_INSTANCE);
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public TargetTypeCase getTargetTypeCase() {
            return ((Target) this.instance).getTargetTypeCase();
        }

        public Builder clearTargetType() {
            copyOnWrite();
            ((Target) this.instance).clearTargetType();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public int getTargetId() {
            return ((Target) this.instance).getTargetId();
        }

        public Builder setTargetId(int i) {
            copyOnWrite();
            ((Target) this.instance).setTargetId(i);
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((Target) this.instance).clearTargetId();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasSnapshotVersion() {
            return ((Target) this.instance).hasSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public Timestamp getSnapshotVersion() {
            return ((Target) this.instance).getSnapshotVersion();
        }

        public Builder setSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).setSnapshotVersion(timestamp);
            return this;
        }

        public Builder setSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setSnapshotVersion(builder.build());
            return this;
        }

        public Builder mergeSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).mergeSnapshotVersion(timestamp);
            return this;
        }

        public Builder clearSnapshotVersion() {
            copyOnWrite();
            ((Target) this.instance).clearSnapshotVersion();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public ByteString getResumeToken() {
            return ((Target) this.instance).getResumeToken();
        }

        public Builder setResumeToken(ByteString byteString) {
            copyOnWrite();
            ((Target) this.instance).setResumeToken(byteString);
            return this;
        }

        public Builder clearResumeToken() {
            copyOnWrite();
            ((Target) this.instance).clearResumeToken();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public long getLastListenSequenceNumber() {
            return ((Target) this.instance).getLastListenSequenceNumber();
        }

        public Builder setLastListenSequenceNumber(long j) {
            copyOnWrite();
            ((Target) this.instance).setLastListenSequenceNumber(j);
            return this;
        }

        public Builder clearLastListenSequenceNumber() {
            copyOnWrite();
            ((Target) this.instance).clearLastListenSequenceNumber();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasQuery() {
            return ((Target) this.instance).hasQuery();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public Target.QueryTarget getQuery() {
            return ((Target) this.instance).getQuery();
        }

        public Builder setQuery(Target.QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).setQuery(queryTarget);
            return this;
        }

        public Builder setQuery(Target.QueryTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setQuery(builder.build());
            return this;
        }

        public Builder mergeQuery(Target.QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeQuery(queryTarget);
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((Target) this.instance).clearQuery();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasDocuments() {
            return ((Target) this.instance).hasDocuments();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public Target.DocumentsTarget getDocuments() {
            return ((Target) this.instance).getDocuments();
        }

        public Builder setDocuments(Target.DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(documentsTarget);
            return this;
        }

        public Builder setDocuments(Target.DocumentsTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(builder.build());
            return this;
        }

        public Builder mergeDocuments(Target.DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeDocuments(documentsTarget);
            return this;
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((Target) this.instance).clearDocuments();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasLastLimboFreeSnapshotVersion() {
            return ((Target) this.instance).hasLastLimboFreeSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public Timestamp getLastLimboFreeSnapshotVersion() {
            return ((Target) this.instance).getLastLimboFreeSnapshotVersion();
        }

        public Builder setLastLimboFreeSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).setLastLimboFreeSnapshotVersion(timestamp);
            return this;
        }

        public Builder setLastLimboFreeSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setLastLimboFreeSnapshotVersion(builder.build());
            return this;
        }

        public Builder mergeLastLimboFreeSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).mergeLastLimboFreeSnapshotVersion(timestamp);
            return this;
        }

        public Builder clearLastLimboFreeSnapshotVersion() {
            copyOnWrite();
            ((Target) this.instance).clearLastLimboFreeSnapshotVersion();
            return this;
        }
    }

    /* renamed from: com.google.firebase.firestore.proto.Target$1, reason: invalid class name */
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
                return new Target();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0004\u0002\t\u0003\n\u0004\u0002\u0005<\u0000\u0006<\u0000\u0007\t", new Object[]{"targetType_", "targetTypeCase_", "targetId_", "snapshotVersion_", "resumeToken_", "lastListenSequenceNumber_", Target.QueryTarget.class, Target.DocumentsTarget.class, "lastLimboFreeSnapshotVersion_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Target> parser = PARSER;
                if (parser == null) {
                    synchronized (Target.class) {
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
        Target target = new Target();
        DEFAULT_INSTANCE = target;
        GeneratedMessageLite.registerDefaultInstance(Target.class, target);
    }

    public static Target getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Target> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
