package com.google.firestore.bundle;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class BundledDocumentMetadata extends GeneratedMessageLite<BundledDocumentMetadata, Builder> implements BundledDocumentMetadataOrBuilder {
    private static final BundledDocumentMetadata DEFAULT_INSTANCE;
    public static final int EXISTS_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<BundledDocumentMetadata> PARSER = null;
    public static final int QUERIES_FIELD_NUMBER = 4;
    public static final int READ_TIME_FIELD_NUMBER = 2;
    private boolean exists_;
    private String name_ = "";
    private Internal.ProtobufList<String> queries_ = GeneratedMessageLite.emptyProtobufList();
    private Timestamp readTime_;

    private BundledDocumentMetadata() {
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
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

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
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

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public boolean getExists() {
        return this.exists_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExists(boolean z) {
        this.exists_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExists() {
        this.exists_ = false;
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public List<String> getQueriesList() {
        return this.queries_;
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public int getQueriesCount() {
        return this.queries_.size();
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public String getQueries(int i) {
        return this.queries_.get(i);
    }

    @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
    public ByteString getQueriesBytes(int i) {
        return ByteString.copyFromUtf8(this.queries_.get(i));
    }

    private void ensureQueriesIsMutable() {
        Internal.ProtobufList<String> protobufList = this.queries_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.queries_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueries(int i, String str) {
        str.getClass();
        ensureQueriesIsMutable();
        this.queries_.set(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addQueries(String str) {
        str.getClass();
        ensureQueriesIsMutable();
        this.queries_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllQueries(Iterable<String> iterable) {
        ensureQueriesIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.queries_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueries() {
        this.queries_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addQueriesBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        ensureQueriesIsMutable();
        this.queries_.add(byteString.toStringUtf8());
    }

    public static BundledDocumentMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BundledDocumentMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundledDocumentMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundledDocumentMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(InputStream inputStream) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundledDocumentMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BundledDocumentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundledDocumentMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledDocumentMetadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundledDocumentMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundledDocumentMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledDocumentMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundledDocumentMetadata bundledDocumentMetadata) {
        return DEFAULT_INSTANCE.createBuilder(bundledDocumentMetadata);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BundledDocumentMetadata, Builder> implements BundledDocumentMetadataOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BundledDocumentMetadata.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public String getName() {
            return ((BundledDocumentMetadata) this.instance).getName();
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public ByteString getNameBytes() {
            return ((BundledDocumentMetadata) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setNameBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public boolean hasReadTime() {
            return ((BundledDocumentMetadata) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public Timestamp getReadTime() {
            return ((BundledDocumentMetadata) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearReadTime();
            return this;
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public boolean getExists() {
            return ((BundledDocumentMetadata) this.instance).getExists();
        }

        public Builder setExists(boolean z) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setExists(z);
            return this;
        }

        public Builder clearExists() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearExists();
            return this;
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public List<String> getQueriesList() {
            return Collections.unmodifiableList(((BundledDocumentMetadata) this.instance).getQueriesList());
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public int getQueriesCount() {
            return ((BundledDocumentMetadata) this.instance).getQueriesCount();
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public String getQueries(int i) {
            return ((BundledDocumentMetadata) this.instance).getQueries(i);
        }

        @Override // com.google.firestore.bundle.BundledDocumentMetadataOrBuilder
        public ByteString getQueriesBytes(int i) {
            return ((BundledDocumentMetadata) this.instance).getQueriesBytes(i);
        }

        public Builder setQueries(int i, String str) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).setQueries(i, str);
            return this;
        }

        public Builder addQueries(String str) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addQueries(str);
            return this;
        }

        public Builder addAllQueries(Iterable<String> iterable) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addAllQueries(iterable);
            return this;
        }

        public Builder clearQueries() {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).clearQueries();
            return this;
        }

        public Builder addQueriesBytes(ByteString byteString) {
            copyOnWrite();
            ((BundledDocumentMetadata) this.instance).addQueriesBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundledDocumentMetadata$1, reason: invalid class name */
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
                return new BundledDocumentMetadata();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\t\u0003\u0007\u0004Ț", new Object[]{"name_", "readTime_", "exists_", "queries_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundledDocumentMetadata> parser = PARSER;
                if (parser == null) {
                    synchronized (BundledDocumentMetadata.class) {
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
        BundledDocumentMetadata bundledDocumentMetadata = new BundledDocumentMetadata();
        DEFAULT_INSTANCE = bundledDocumentMetadata;
        GeneratedMessageLite.registerDefaultInstance(BundledDocumentMetadata.class, bundledDocumentMetadata);
    }

    public static BundledDocumentMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundledDocumentMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
