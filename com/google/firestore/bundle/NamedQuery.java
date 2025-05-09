package com.google.firestore.bundle;

import com.google.firestore.bundle.BundledQuery;
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
public final class NamedQuery extends GeneratedMessageLite<NamedQuery, Builder> implements NamedQueryOrBuilder {
    public static final int BUNDLED_QUERY_FIELD_NUMBER = 2;
    private static final NamedQuery DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<NamedQuery> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    private BundledQuery bundledQuery_;
    private String name_ = "";
    private Timestamp readTime_;

    private NamedQuery() {
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public boolean hasBundledQuery() {
        return this.bundledQuery_ != null;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public BundledQuery getBundledQuery() {
        BundledQuery bundledQuery = this.bundledQuery_;
        return bundledQuery == null ? BundledQuery.getDefaultInstance() : bundledQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBundledQuery(BundledQuery bundledQuery) {
        bundledQuery.getClass();
        this.bundledQuery_ = bundledQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBundledQuery(BundledQuery bundledQuery) {
        bundledQuery.getClass();
        BundledQuery bundledQuery2 = this.bundledQuery_;
        if (bundledQuery2 != null && bundledQuery2 != BundledQuery.getDefaultInstance()) {
            this.bundledQuery_ = BundledQuery.newBuilder(this.bundledQuery_).mergeFrom((BundledQuery.Builder) bundledQuery).buildPartial();
        } else {
            this.bundledQuery_ = bundledQuery;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBundledQuery() {
        this.bundledQuery_ = null;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
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

    public static NamedQuery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static NamedQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static NamedQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static NamedQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(InputStream inputStream) throws IOException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NamedQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NamedQuery parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (NamedQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NamedQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NamedQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static NamedQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(NamedQuery namedQuery) {
        return DEFAULT_INSTANCE.createBuilder(namedQuery);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<NamedQuery, Builder> implements NamedQueryOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(NamedQuery.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public String getName() {
            return ((NamedQuery) this.instance).getName();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public ByteString getNameBytes() {
            return ((NamedQuery) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((NamedQuery) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((NamedQuery) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((NamedQuery) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public boolean hasBundledQuery() {
            return ((NamedQuery) this.instance).hasBundledQuery();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public BundledQuery getBundledQuery() {
            return ((NamedQuery) this.instance).getBundledQuery();
        }

        public Builder setBundledQuery(BundledQuery bundledQuery) {
            copyOnWrite();
            ((NamedQuery) this.instance).setBundledQuery(bundledQuery);
            return this;
        }

        public Builder setBundledQuery(BundledQuery.Builder builder) {
            copyOnWrite();
            ((NamedQuery) this.instance).setBundledQuery(builder.build());
            return this;
        }

        public Builder mergeBundledQuery(BundledQuery bundledQuery) {
            copyOnWrite();
            ((NamedQuery) this.instance).mergeBundledQuery(bundledQuery);
            return this;
        }

        public Builder clearBundledQuery() {
            copyOnWrite();
            ((NamedQuery) this.instance).clearBundledQuery();
            return this;
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public boolean hasReadTime() {
            return ((NamedQuery) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public Timestamp getReadTime() {
            return ((NamedQuery) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NamedQuery) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((NamedQuery) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NamedQuery) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((NamedQuery) this.instance).clearReadTime();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.NamedQuery$1, reason: invalid class name */
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
                return new NamedQuery();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\t", new Object[]{"name_", "bundledQuery_", "readTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<NamedQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (NamedQuery.class) {
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
        NamedQuery namedQuery = new NamedQuery();
        DEFAULT_INSTANCE = namedQuery;
        GeneratedMessageLite.registerDefaultInstance(NamedQuery.class, namedQuery);
    }

    public static NamedQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<NamedQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
