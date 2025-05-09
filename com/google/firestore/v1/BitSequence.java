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
public final class BitSequence extends GeneratedMessageLite<BitSequence, Builder> implements BitSequenceOrBuilder {
    public static final int BITMAP_FIELD_NUMBER = 1;
    private static final BitSequence DEFAULT_INSTANCE;
    public static final int PADDING_FIELD_NUMBER = 2;
    private static volatile Parser<BitSequence> PARSER;
    private ByteString bitmap_ = ByteString.EMPTY;
    private int padding_;

    private BitSequence() {
    }

    @Override // com.google.firestore.v1.BitSequenceOrBuilder
    public ByteString getBitmap() {
        return this.bitmap_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBitmap(ByteString byteString) {
        byteString.getClass();
        this.bitmap_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBitmap() {
        this.bitmap_ = getDefaultInstance().getBitmap();
    }

    @Override // com.google.firestore.v1.BitSequenceOrBuilder
    public int getPadding() {
        return this.padding_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPadding(int i) {
        this.padding_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPadding() {
        this.padding_ = 0;
    }

    public static BitSequence parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BitSequence parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BitSequence parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BitSequence parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BitSequence parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BitSequence parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BitSequence parseFrom(InputStream inputStream) throws IOException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BitSequence parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BitSequence parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BitSequence) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BitSequence parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BitSequence) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BitSequence parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BitSequence parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BitSequence) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BitSequence bitSequence) {
        return DEFAULT_INSTANCE.createBuilder(bitSequence);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BitSequence, Builder> implements BitSequenceOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BitSequence.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.BitSequenceOrBuilder
        public ByteString getBitmap() {
            return ((BitSequence) this.instance).getBitmap();
        }

        public Builder setBitmap(ByteString byteString) {
            copyOnWrite();
            ((BitSequence) this.instance).setBitmap(byteString);
            return this;
        }

        public Builder clearBitmap() {
            copyOnWrite();
            ((BitSequence) this.instance).clearBitmap();
            return this;
        }

        @Override // com.google.firestore.v1.BitSequenceOrBuilder
        public int getPadding() {
            return ((BitSequence) this.instance).getPadding();
        }

        public Builder setPadding(int i) {
            copyOnWrite();
            ((BitSequence) this.instance).setPadding(i);
            return this;
        }

        public Builder clearPadding() {
            copyOnWrite();
            ((BitSequence) this.instance).clearPadding();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.BitSequence$1, reason: invalid class name */
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
                return new BitSequence();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\n\u0002\u0004", new Object[]{"bitmap_", "padding_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BitSequence> parser = PARSER;
                if (parser == null) {
                    synchronized (BitSequence.class) {
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
        BitSequence bitSequence = new BitSequence();
        DEFAULT_INSTANCE = bitSequence;
        GeneratedMessageLite.registerDefaultInstance(BitSequence.class, bitSequence);
    }

    public static BitSequence getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BitSequence> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
