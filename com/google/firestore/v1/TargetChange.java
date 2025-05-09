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
import com.google.rpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class TargetChange extends GeneratedMessageLite<TargetChange, Builder> implements TargetChangeOrBuilder {
    public static final int CAUSE_FIELD_NUMBER = 3;
    private static final TargetChange DEFAULT_INSTANCE;
    private static volatile Parser<TargetChange> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 6;
    public static final int RESUME_TOKEN_FIELD_NUMBER = 4;
    public static final int TARGET_CHANGE_TYPE_FIELD_NUMBER = 1;
    public static final int TARGET_IDS_FIELD_NUMBER = 2;
    private Status cause_;
    private Timestamp readTime_;
    private int targetChangeType_;
    private int targetIdsMemoizedSerializedSize = -1;
    private Internal.IntList targetIds_ = emptyIntList();
    private ByteString resumeToken_ = ByteString.EMPTY;

    private TargetChange() {
    }

    /* loaded from: classes4.dex */
    public enum TargetChangeType implements Internal.EnumLite {
        NO_CHANGE(0),
        ADD(1),
        REMOVE(2),
        CURRENT(3),
        RESET(4),
        UNRECOGNIZED(-1);

        public static final int ADD_VALUE = 1;
        public static final int CURRENT_VALUE = 3;
        public static final int NO_CHANGE_VALUE = 0;
        public static final int REMOVE_VALUE = 2;
        public static final int RESET_VALUE = 4;
        private static final Internal.EnumLiteMap<TargetChangeType> internalValueMap = new Internal.EnumLiteMap<TargetChangeType>() { // from class: com.google.firestore.v1.TargetChange.TargetChangeType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TargetChangeType findValueByNumber(int i) {
                return TargetChangeType.forNumber(i);
            }
        };
        private final int value;

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static TargetChangeType valueOf(int i) {
            return forNumber(i);
        }

        public static TargetChangeType forNumber(int i) {
            if (i == 0) {
                return NO_CHANGE;
            }
            if (i == 1) {
                return ADD;
            }
            if (i == 2) {
                return REMOVE;
            }
            if (i == 3) {
                return CURRENT;
            }
            if (i != 4) {
                return null;
            }
            return RESET;
        }

        public static Internal.EnumLiteMap<TargetChangeType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return TargetChangeTypeVerifier.INSTANCE;
        }

        /* loaded from: classes4.dex */
        private static final class TargetChangeTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new TargetChangeTypeVerifier();

            private TargetChangeTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return TargetChangeType.forNumber(i) != null;
            }
        }

        TargetChangeType(int i) {
            this.value = i;
        }
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public int getTargetChangeTypeValue() {
        return this.targetChangeType_;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public TargetChangeType getTargetChangeType() {
        TargetChangeType forNumber = TargetChangeType.forNumber(this.targetChangeType_);
        return forNumber == null ? TargetChangeType.UNRECOGNIZED : forNumber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetChangeTypeValue(int i) {
        this.targetChangeType_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetChangeType(TargetChangeType targetChangeType) {
        this.targetChangeType_ = targetChangeType.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetChangeType() {
        this.targetChangeType_ = 0;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public List<Integer> getTargetIdsList() {
        return this.targetIds_;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public int getTargetIdsCount() {
        return this.targetIds_.size();
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public int getTargetIds(int i) {
        return this.targetIds_.getInt(i);
    }

    private void ensureTargetIdsIsMutable() {
        Internal.IntList intList = this.targetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.targetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetIds(int i, int i2) {
        ensureTargetIdsIsMutable();
        this.targetIds_.setInt(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTargetIds(int i) {
        ensureTargetIdsIsMutable();
        this.targetIds_.addInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllTargetIds(Iterable<? extends Integer> iterable) {
        ensureTargetIdsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.targetIds_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetIds() {
        this.targetIds_ = emptyIntList();
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public boolean hasCause() {
        return this.cause_ != null;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public Status getCause() {
        Status status = this.cause_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCause(Status status) {
        status.getClass();
        this.cause_ = status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCause(Status status) {
        status.getClass();
        Status status2 = this.cause_;
        if (status2 != null && status2 != Status.getDefaultInstance()) {
            this.cause_ = Status.newBuilder(this.cause_).mergeFrom((Status.Builder) status).buildPartial();
        } else {
            this.cause_ = status;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCause() {
        this.cause_ = null;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
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

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
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

    public static TargetChange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static TargetChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TargetChange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TargetChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TargetChange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TargetChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TargetChange parseFrom(InputStream inputStream) throws IOException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetChange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TargetChange) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetChange) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetChange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TargetChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(TargetChange targetChange) {
        return DEFAULT_INSTANCE.createBuilder(targetChange);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<TargetChange, Builder> implements TargetChangeOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(TargetChange.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public int getTargetChangeTypeValue() {
            return ((TargetChange) this.instance).getTargetChangeTypeValue();
        }

        public Builder setTargetChangeTypeValue(int i) {
            copyOnWrite();
            ((TargetChange) this.instance).setTargetChangeTypeValue(i);
            return this;
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public TargetChangeType getTargetChangeType() {
            return ((TargetChange) this.instance).getTargetChangeType();
        }

        public Builder setTargetChangeType(TargetChangeType targetChangeType) {
            copyOnWrite();
            ((TargetChange) this.instance).setTargetChangeType(targetChangeType);
            return this;
        }

        public Builder clearTargetChangeType() {
            copyOnWrite();
            ((TargetChange) this.instance).clearTargetChangeType();
            return this;
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public List<Integer> getTargetIdsList() {
            return Collections.unmodifiableList(((TargetChange) this.instance).getTargetIdsList());
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public int getTargetIdsCount() {
            return ((TargetChange) this.instance).getTargetIdsCount();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public int getTargetIds(int i) {
            return ((TargetChange) this.instance).getTargetIds(i);
        }

        public Builder setTargetIds(int i, int i2) {
            copyOnWrite();
            ((TargetChange) this.instance).setTargetIds(i, i2);
            return this;
        }

        public Builder addTargetIds(int i) {
            copyOnWrite();
            ((TargetChange) this.instance).addTargetIds(i);
            return this;
        }

        public Builder addAllTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            ((TargetChange) this.instance).addAllTargetIds(iterable);
            return this;
        }

        public Builder clearTargetIds() {
            copyOnWrite();
            ((TargetChange) this.instance).clearTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public boolean hasCause() {
            return ((TargetChange) this.instance).hasCause();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public Status getCause() {
            return ((TargetChange) this.instance).getCause();
        }

        public Builder setCause(Status status) {
            copyOnWrite();
            ((TargetChange) this.instance).setCause(status);
            return this;
        }

        public Builder setCause(Status.Builder builder) {
            copyOnWrite();
            ((TargetChange) this.instance).setCause(builder.build());
            return this;
        }

        public Builder mergeCause(Status status) {
            copyOnWrite();
            ((TargetChange) this.instance).mergeCause(status);
            return this;
        }

        public Builder clearCause() {
            copyOnWrite();
            ((TargetChange) this.instance).clearCause();
            return this;
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public ByteString getResumeToken() {
            return ((TargetChange) this.instance).getResumeToken();
        }

        public Builder setResumeToken(ByteString byteString) {
            copyOnWrite();
            ((TargetChange) this.instance).setResumeToken(byteString);
            return this;
        }

        public Builder clearResumeToken() {
            copyOnWrite();
            ((TargetChange) this.instance).clearResumeToken();
            return this;
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public boolean hasReadTime() {
            return ((TargetChange) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public Timestamp getReadTime() {
            return ((TargetChange) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((TargetChange) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((TargetChange) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((TargetChange) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((TargetChange) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.TargetChange$1, reason: invalid class name */
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
                return new TargetChange();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0006\u0005\u0000\u0001\u0000\u0001\f\u0002'\u0003\t\u0004\n\u0006\t", new Object[]{"targetChangeType_", "targetIds_", "cause_", "resumeToken_", "readTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<TargetChange> parser = PARSER;
                if (parser == null) {
                    synchronized (TargetChange.class) {
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
        TargetChange targetChange = new TargetChange();
        DEFAULT_INSTANCE = targetChange;
        GeneratedMessageLite.registerDefaultInstance(TargetChange.class, targetChange);
    }

    public static TargetChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TargetChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
