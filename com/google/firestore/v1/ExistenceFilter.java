package com.google.firestore.v1;

import com.google.firestore.v1.BloomFilter;
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
public final class ExistenceFilter extends GeneratedMessageLite<ExistenceFilter, Builder> implements ExistenceFilterOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 2;
    private static final ExistenceFilter DEFAULT_INSTANCE;
    private static volatile Parser<ExistenceFilter> PARSER = null;
    public static final int TARGET_ID_FIELD_NUMBER = 1;
    public static final int UNCHANGED_NAMES_FIELD_NUMBER = 3;
    private int count_;
    private int targetId_;
    private BloomFilter unchangedNames_;

    private ExistenceFilter() {
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
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

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public int getCount() {
        return this.count_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCount(int i) {
        this.count_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCount() {
        this.count_ = 0;
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public boolean hasUnchangedNames() {
        return this.unchangedNames_ != null;
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public BloomFilter getUnchangedNames() {
        BloomFilter bloomFilter = this.unchangedNames_;
        return bloomFilter == null ? BloomFilter.getDefaultInstance() : bloomFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnchangedNames(BloomFilter bloomFilter) {
        bloomFilter.getClass();
        this.unchangedNames_ = bloomFilter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUnchangedNames(BloomFilter bloomFilter) {
        bloomFilter.getClass();
        BloomFilter bloomFilter2 = this.unchangedNames_;
        if (bloomFilter2 != null && bloomFilter2 != BloomFilter.getDefaultInstance()) {
            this.unchangedNames_ = BloomFilter.newBuilder(this.unchangedNames_).mergeFrom((BloomFilter.Builder) bloomFilter).buildPartial();
        } else {
            this.unchangedNames_ = bloomFilter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUnchangedNames() {
        this.unchangedNames_ = null;
    }

    public static ExistenceFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ExistenceFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ExistenceFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ExistenceFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(InputStream inputStream) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExistenceFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExistenceFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ExistenceFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExistenceFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExistenceFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ExistenceFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ExistenceFilter existenceFilter) {
        return DEFAULT_INSTANCE.createBuilder(existenceFilter);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ExistenceFilter, Builder> implements ExistenceFilterOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ExistenceFilter.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public int getTargetId() {
            return ((ExistenceFilter) this.instance).getTargetId();
        }

        public Builder setTargetId(int i) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setTargetId(i);
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).clearTargetId();
            return this;
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public int getCount() {
            return ((ExistenceFilter) this.instance).getCount();
        }

        public Builder setCount(int i) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setCount(i);
            return this;
        }

        public Builder clearCount() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).clearCount();
            return this;
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public boolean hasUnchangedNames() {
            return ((ExistenceFilter) this.instance).hasUnchangedNames();
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public BloomFilter getUnchangedNames() {
            return ((ExistenceFilter) this.instance).getUnchangedNames();
        }

        public Builder setUnchangedNames(BloomFilter bloomFilter) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setUnchangedNames(bloomFilter);
            return this;
        }

        public Builder setUnchangedNames(BloomFilter.Builder builder) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).setUnchangedNames(builder.build());
            return this;
        }

        public Builder mergeUnchangedNames(BloomFilter bloomFilter) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).mergeUnchangedNames(bloomFilter);
            return this;
        }

        public Builder clearUnchangedNames() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).clearUnchangedNames();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ExistenceFilter$1, reason: invalid class name */
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
                return new ExistenceFilter();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002\u0004\u0003\t", new Object[]{"targetId_", "count_", "unchangedNames_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ExistenceFilter> parser = PARSER;
                if (parser == null) {
                    synchronized (ExistenceFilter.class) {
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
        ExistenceFilter existenceFilter = new ExistenceFilter();
        DEFAULT_INSTANCE = existenceFilter;
        GeneratedMessageLite.registerDefaultInstance(ExistenceFilter.class, existenceFilter);
    }

    public static ExistenceFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExistenceFilter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
