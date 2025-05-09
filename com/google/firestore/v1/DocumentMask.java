package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class DocumentMask extends GeneratedMessageLite<DocumentMask, Builder> implements DocumentMaskOrBuilder {
    private static final DocumentMask DEFAULT_INSTANCE;
    public static final int FIELD_PATHS_FIELD_NUMBER = 1;
    private static volatile Parser<DocumentMask> PARSER;
    private Internal.ProtobufList<String> fieldPaths_ = GeneratedMessageLite.emptyProtobufList();

    private DocumentMask() {
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public List<String> getFieldPathsList() {
        return this.fieldPaths_;
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public int getFieldPathsCount() {
        return this.fieldPaths_.size();
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public String getFieldPaths(int i) {
        return this.fieldPaths_.get(i);
    }

    @Override // com.google.firestore.v1.DocumentMaskOrBuilder
    public ByteString getFieldPathsBytes(int i) {
        return ByteString.copyFromUtf8(this.fieldPaths_.get(i));
    }

    private void ensureFieldPathsIsMutable() {
        Internal.ProtobufList<String> protobufList = this.fieldPaths_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.fieldPaths_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFieldPaths(int i, String str) {
        str.getClass();
        ensureFieldPathsIsMutable();
        this.fieldPaths_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldPaths(String str) {
        str.getClass();
        ensureFieldPathsIsMutable();
        this.fieldPaths_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllFieldPaths(Iterable<String> iterable) {
        ensureFieldPathsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.fieldPaths_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldPaths() {
        this.fieldPaths_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldPathsBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        ensureFieldPathsIsMutable();
        this.fieldPaths_.add(byteString.toStringUtf8());
    }

    public static DocumentMask parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DocumentMask parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentMask parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentMask parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(InputStream inputStream) throws IOException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentMask parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentMask parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentMask) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentMask parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentMask) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentMask parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentMask parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentMask) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentMask documentMask) {
        return DEFAULT_INSTANCE.createBuilder(documentMask);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentMask, Builder> implements DocumentMaskOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DocumentMask.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public List<String> getFieldPathsList() {
            return Collections.unmodifiableList(((DocumentMask) this.instance).getFieldPathsList());
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public int getFieldPathsCount() {
            return ((DocumentMask) this.instance).getFieldPathsCount();
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public String getFieldPaths(int i) {
            return ((DocumentMask) this.instance).getFieldPaths(i);
        }

        @Override // com.google.firestore.v1.DocumentMaskOrBuilder
        public ByteString getFieldPathsBytes(int i) {
            return ((DocumentMask) this.instance).getFieldPathsBytes(i);
        }

        public Builder setFieldPaths(int i, String str) {
            copyOnWrite();
            ((DocumentMask) this.instance).setFieldPaths(i, str);
            return this;
        }

        public Builder addFieldPaths(String str) {
            copyOnWrite();
            ((DocumentMask) this.instance).addFieldPaths(str);
            return this;
        }

        public Builder addAllFieldPaths(Iterable<String> iterable) {
            copyOnWrite();
            ((DocumentMask) this.instance).addAllFieldPaths(iterable);
            return this;
        }

        public Builder clearFieldPaths() {
            copyOnWrite();
            ((DocumentMask) this.instance).clearFieldPaths();
            return this;
        }

        public Builder addFieldPathsBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentMask) this.instance).addFieldPathsBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentMask$1, reason: invalid class name */
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
                return new DocumentMask();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"fieldPaths_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentMask> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentMask.class) {
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
        DocumentMask documentMask = new DocumentMask();
        DEFAULT_INSTANCE = documentMask;
        GeneratedMessageLite.registerDefaultInstance(DocumentMask.class, documentMask);
    }

    public static DocumentMask getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentMask> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
