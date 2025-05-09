package com.google.firestore.v1;

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
public final class Precondition extends GeneratedMessageLite<Precondition, Builder> implements PreconditionOrBuilder {
    private static final Precondition DEFAULT_INSTANCE;
    public static final int EXISTS_FIELD_NUMBER = 1;
    private static volatile Parser<Precondition> PARSER = null;
    public static final int UPDATE_TIME_FIELD_NUMBER = 2;
    private int conditionTypeCase_ = 0;
    private Object conditionType_;

    private Precondition() {
    }

    /* loaded from: classes4.dex */
    public enum ConditionTypeCase {
        EXISTS(1),
        UPDATE_TIME(2),
        CONDITIONTYPE_NOT_SET(0);

        private final int value;

        ConditionTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ConditionTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ConditionTypeCase forNumber(int i) {
            if (i == 0) {
                return CONDITIONTYPE_NOT_SET;
            }
            if (i == 1) {
                return EXISTS;
            }
            if (i != 2) {
                return null;
            }
            return UPDATE_TIME;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public ConditionTypeCase getConditionTypeCase() {
        return ConditionTypeCase.forNumber(this.conditionTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConditionType() {
        this.conditionTypeCase_ = 0;
        this.conditionType_ = null;
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public boolean hasExists() {
        return this.conditionTypeCase_ == 1;
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public boolean getExists() {
        if (this.conditionTypeCase_ == 1) {
            return ((Boolean) this.conditionType_).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExists(boolean z) {
        this.conditionTypeCase_ = 1;
        this.conditionType_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExists() {
        if (this.conditionTypeCase_ == 1) {
            this.conditionTypeCase_ = 0;
            this.conditionType_ = null;
        }
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public boolean hasUpdateTime() {
        return this.conditionTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.PreconditionOrBuilder
    public Timestamp getUpdateTime() {
        if (this.conditionTypeCase_ == 2) {
            return (Timestamp) this.conditionType_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.conditionType_ = timestamp;
        this.conditionTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        if (this.conditionTypeCase_ == 2 && this.conditionType_ != Timestamp.getDefaultInstance()) {
            this.conditionType_ = Timestamp.newBuilder((Timestamp) this.conditionType_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.conditionType_ = timestamp;
        }
        this.conditionTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUpdateTime() {
        if (this.conditionTypeCase_ == 2) {
            this.conditionTypeCase_ = 0;
            this.conditionType_ = null;
        }
    }

    public static Precondition parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Precondition parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Precondition parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Precondition parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Precondition parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Precondition parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Precondition parseFrom(InputStream inputStream) throws IOException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Precondition parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Precondition parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Precondition) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Precondition parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Precondition) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Precondition parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Precondition parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Precondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Precondition precondition) {
        return DEFAULT_INSTANCE.createBuilder(precondition);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<Precondition, Builder> implements PreconditionOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Precondition.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public ConditionTypeCase getConditionTypeCase() {
            return ((Precondition) this.instance).getConditionTypeCase();
        }

        public Builder clearConditionType() {
            copyOnWrite();
            ((Precondition) this.instance).clearConditionType();
            return this;
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public boolean hasExists() {
            return ((Precondition) this.instance).hasExists();
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public boolean getExists() {
            return ((Precondition) this.instance).getExists();
        }

        public Builder setExists(boolean z) {
            copyOnWrite();
            ((Precondition) this.instance).setExists(z);
            return this;
        }

        public Builder clearExists() {
            copyOnWrite();
            ((Precondition) this.instance).clearExists();
            return this;
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public boolean hasUpdateTime() {
            return ((Precondition) this.instance).hasUpdateTime();
        }

        @Override // com.google.firestore.v1.PreconditionOrBuilder
        public Timestamp getUpdateTime() {
            return ((Precondition) this.instance).getUpdateTime();
        }

        public Builder setUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Precondition) this.instance).setUpdateTime(timestamp);
            return this;
        }

        public Builder setUpdateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Precondition) this.instance).setUpdateTime(builder.build());
            return this;
        }

        public Builder mergeUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((Precondition) this.instance).mergeUpdateTime(timestamp);
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((Precondition) this.instance).clearUpdateTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Precondition$1, reason: invalid class name */
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
                return new Precondition();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001:\u0000\u0002<\u0000", new Object[]{"conditionType_", "conditionTypeCase_", Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Precondition> parser = PARSER;
                if (parser == null) {
                    synchronized (Precondition.class) {
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
        Precondition precondition = new Precondition();
        DEFAULT_INSTANCE = precondition;
        GeneratedMessageLite.registerDefaultInstance(Precondition.class, precondition);
    }

    public static Precondition getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Precondition> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
