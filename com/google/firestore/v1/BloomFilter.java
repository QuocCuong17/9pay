package com.google.firestore.v1;

import com.google.firestore.v1.BitSequence;
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
public final class BloomFilter extends GeneratedMessageLite<BloomFilter, Builder> implements BloomFilterOrBuilder {
    public static final int BITS_FIELD_NUMBER = 1;
    private static final BloomFilter DEFAULT_INSTANCE;
    public static final int HASH_COUNT_FIELD_NUMBER = 2;
    private static volatile Parser<BloomFilter> PARSER;
    private BitSequence bits_;
    private int hashCount_;

    private BloomFilter() {
    }

    @Override // com.google.firestore.v1.BloomFilterOrBuilder
    public boolean hasBits() {
        return this.bits_ != null;
    }

    @Override // com.google.firestore.v1.BloomFilterOrBuilder
    public BitSequence getBits() {
        BitSequence bitSequence = this.bits_;
        return bitSequence == null ? BitSequence.getDefaultInstance() : bitSequence;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBits(BitSequence bitSequence) {
        bitSequence.getClass();
        this.bits_ = bitSequence;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBits(BitSequence bitSequence) {
        bitSequence.getClass();
        BitSequence bitSequence2 = this.bits_;
        if (bitSequence2 != null && bitSequence2 != BitSequence.getDefaultInstance()) {
            this.bits_ = BitSequence.newBuilder(this.bits_).mergeFrom((BitSequence.Builder) bitSequence).buildPartial();
        } else {
            this.bits_ = bitSequence;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBits() {
        this.bits_ = null;
    }

    @Override // com.google.firestore.v1.BloomFilterOrBuilder
    public int getHashCount() {
        return this.hashCount_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashCount(int i) {
        this.hashCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHashCount() {
        this.hashCount_ = 0;
    }

    public static BloomFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BloomFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BloomFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BloomFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(InputStream inputStream) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BloomFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BloomFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BloomFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BloomFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BloomFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BloomFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BloomFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BloomFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BloomFilter bloomFilter) {
        return DEFAULT_INSTANCE.createBuilder(bloomFilter);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BloomFilter, Builder> implements BloomFilterOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BloomFilter.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.BloomFilterOrBuilder
        public boolean hasBits() {
            return ((BloomFilter) this.instance).hasBits();
        }

        @Override // com.google.firestore.v1.BloomFilterOrBuilder
        public BitSequence getBits() {
            return ((BloomFilter) this.instance).getBits();
        }

        public Builder setBits(BitSequence bitSequence) {
            copyOnWrite();
            ((BloomFilter) this.instance).setBits(bitSequence);
            return this;
        }

        public Builder setBits(BitSequence.Builder builder) {
            copyOnWrite();
            ((BloomFilter) this.instance).setBits(builder.build());
            return this;
        }

        public Builder mergeBits(BitSequence bitSequence) {
            copyOnWrite();
            ((BloomFilter) this.instance).mergeBits(bitSequence);
            return this;
        }

        public Builder clearBits() {
            copyOnWrite();
            ((BloomFilter) this.instance).clearBits();
            return this;
        }

        @Override // com.google.firestore.v1.BloomFilterOrBuilder
        public int getHashCount() {
            return ((BloomFilter) this.instance).getHashCount();
        }

        public Builder setHashCount(int i) {
            copyOnWrite();
            ((BloomFilter) this.instance).setHashCount(i);
            return this;
        }

        public Builder clearHashCount() {
            copyOnWrite();
            ((BloomFilter) this.instance).clearHashCount();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.BloomFilter$1, reason: invalid class name */
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
                return new BloomFilter();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u0004", new Object[]{"bits_", "hashCount_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BloomFilter> parser = PARSER;
                if (parser == null) {
                    synchronized (BloomFilter.class) {
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
        BloomFilter bloomFilter = new BloomFilter();
        DEFAULT_INSTANCE = bloomFilter;
        GeneratedMessageLite.registerDefaultInstance(BloomFilter.class, bloomFilter);
    }

    public static BloomFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BloomFilter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
