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
public final class ListCollectionIdsRequest extends GeneratedMessageLite<ListCollectionIdsRequest, Builder> implements ListCollectionIdsRequestOrBuilder {
    private static final ListCollectionIdsRequest DEFAULT_INSTANCE;
    public static final int PAGE_SIZE_FIELD_NUMBER = 2;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<ListCollectionIdsRequest> PARSER;
    private int pageSize_;
    private String parent_ = "";
    private String pageToken_ = "";

    private ListCollectionIdsRequest() {
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParent(String str) {
        str.getClass();
        this.parent_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = getDefaultInstance().getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParentBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.parent_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public int getPageSize() {
        return this.pageSize_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageSize(int i) {
        this.pageSize_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPageSize() {
        this.pageSize_ = 0;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public String getPageToken() {
        return this.pageToken_;
    }

    @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
    public ByteString getPageTokenBytes() {
        return ByteString.copyFromUtf8(this.pageToken_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageToken(String str) {
        str.getClass();
        this.pageToken_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPageToken() {
        this.pageToken_ = getDefaultInstance().getPageToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageTokenBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.pageToken_ = byteString.toStringUtf8();
    }

    public static ListCollectionIdsRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ListCollectionIdsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListCollectionIdsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListCollectionIdsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(InputStream inputStream) throws IOException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListCollectionIdsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListCollectionIdsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListCollectionIdsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListCollectionIdsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListCollectionIdsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListCollectionIdsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListCollectionIdsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListCollectionIdsRequest listCollectionIdsRequest) {
        return DEFAULT_INSTANCE.createBuilder(listCollectionIdsRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ListCollectionIdsRequest, Builder> implements ListCollectionIdsRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ListCollectionIdsRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public String getParent() {
            return ((ListCollectionIdsRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public ByteString getParentBytes() {
            return ((ListCollectionIdsRequest) this.instance).getParentBytes();
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setParent(str);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public int getPageSize() {
            return ((ListCollectionIdsRequest) this.instance).getPageSize();
        }

        public Builder setPageSize(int i) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setPageSize(i);
            return this;
        }

        public Builder clearPageSize() {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).clearPageSize();
            return this;
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public String getPageToken() {
            return ((ListCollectionIdsRequest) this.instance).getPageToken();
        }

        @Override // com.google.firestore.v1.ListCollectionIdsRequestOrBuilder
        public ByteString getPageTokenBytes() {
            return ((ListCollectionIdsRequest) this.instance).getPageTokenBytes();
        }

        public Builder setPageToken(String str) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setPageToken(str);
            return this;
        }

        public Builder clearPageToken() {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).clearPageToken();
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListCollectionIdsRequest) this.instance).setPageTokenBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListCollectionIdsRequest$1, reason: invalid class name */
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
                return new ListCollectionIdsRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ", new Object[]{"parent_", "pageSize_", "pageToken_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListCollectionIdsRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (ListCollectionIdsRequest.class) {
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
        ListCollectionIdsRequest listCollectionIdsRequest = new ListCollectionIdsRequest();
        DEFAULT_INSTANCE = listCollectionIdsRequest;
        GeneratedMessageLite.registerDefaultInstance(ListCollectionIdsRequest.class, listCollectionIdsRequest);
    }

    public static ListCollectionIdsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListCollectionIdsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
