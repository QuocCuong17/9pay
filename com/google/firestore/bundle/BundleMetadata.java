package com.google.firestore.bundle;

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
public final class BundleMetadata extends GeneratedMessageLite<BundleMetadata, Builder> implements BundleMetadataOrBuilder {
    public static final int CREATE_TIME_FIELD_NUMBER = 2;
    private static final BundleMetadata DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    private static volatile Parser<BundleMetadata> PARSER = null;
    public static final int TOTAL_BYTES_FIELD_NUMBER = 5;
    public static final int TOTAL_DOCUMENTS_FIELD_NUMBER = 4;
    public static final int VERSION_FIELD_NUMBER = 3;
    private Timestamp createTime_;
    private String id_ = "";
    private long totalBytes_;
    private int totalDocuments_;
    private int version_;

    private BundleMetadata() {
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setId(String str) {
        str.getClass();
        this.id_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearId() {
        this.id_ = getDefaultInstance().getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.id_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public boolean hasCreateTime() {
        return this.createTime_ != null;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public Timestamp getCreateTime() {
        Timestamp timestamp = this.createTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.createTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.createTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.createTime_ = Timestamp.newBuilder(this.createTime_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.createTime_ = timestamp;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCreateTime() {
        this.createTime_ = null;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public int getVersion() {
        return this.version_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersion(int i) {
        this.version_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVersion() {
        this.version_ = 0;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public int getTotalDocuments() {
        return this.totalDocuments_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalDocuments(int i) {
        this.totalDocuments_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTotalDocuments() {
        this.totalDocuments_ = 0;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public long getTotalBytes() {
        return this.totalBytes_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalBytes(long j) {
        this.totalBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTotalBytes() {
        this.totalBytes_ = 0L;
    }

    public static BundleMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BundleMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundleMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundleMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(InputStream inputStream) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BundleMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundleMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundleMetadata bundleMetadata) {
        return DEFAULT_INSTANCE.createBuilder(bundleMetadata);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BundleMetadata, Builder> implements BundleMetadataOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BundleMetadata.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public String getId() {
            return ((BundleMetadata) this.instance).getId();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public ByteString getIdBytes() {
            return ((BundleMetadata) this.instance).getIdBytes();
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setId(str);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setIdBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public boolean hasCreateTime() {
            return ((BundleMetadata) this.instance).hasCreateTime();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public Timestamp getCreateTime() {
            return ((BundleMetadata) this.instance).getCreateTime();
        }

        public Builder setCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setCreateTime(timestamp);
            return this;
        }

        public Builder setCreateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setCreateTime(builder.build());
            return this;
        }

        public Builder mergeCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundleMetadata) this.instance).mergeCreateTime(timestamp);
            return this;
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearCreateTime();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public int getVersion() {
            return ((BundleMetadata) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearVersion();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public int getTotalDocuments() {
            return ((BundleMetadata) this.instance).getTotalDocuments();
        }

        public Builder setTotalDocuments(int i) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setTotalDocuments(i);
            return this;
        }

        public Builder clearTotalDocuments() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearTotalDocuments();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public long getTotalBytes() {
            return ((BundleMetadata) this.instance).getTotalBytes();
        }

        public Builder setTotalBytes(long j) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setTotalBytes(j);
            return this;
        }

        public Builder clearTotalBytes() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearTotalBytes();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundleMetadata$1, reason: invalid class name */
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
                return new BundleMetadata();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\u000b\u0004\u000b\u0005\u0003", new Object[]{"id_", "createTime_", "version_", "totalDocuments_", "totalBytes_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundleMetadata> parser = PARSER;
                if (parser == null) {
                    synchronized (BundleMetadata.class) {
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
        BundleMetadata bundleMetadata = new BundleMetadata();
        DEFAULT_INSTANCE = bundleMetadata;
        GeneratedMessageLite.registerDefaultInstance(BundleMetadata.class, bundleMetadata);
    }

    public static BundleMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundleMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
