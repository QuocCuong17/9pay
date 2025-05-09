package com.google.firestore.bundle;

import com.google.firestore.v1.StructuredQuery;
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

/* loaded from: classes4.dex */
public final class BundledQuery extends GeneratedMessageLite<BundledQuery, Builder> implements BundledQueryOrBuilder {
    private static final BundledQuery DEFAULT_INSTANCE;
    public static final int LIMIT_TYPE_FIELD_NUMBER = 3;
    public static final int PARENT_FIELD_NUMBER = 1;
    private static volatile Parser<BundledQuery> PARSER = null;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
    private int limitType_;
    private Object queryType_;
    private int queryTypeCase_ = 0;
    private String parent_ = "";

    private BundledQuery() {
    }

    /* loaded from: classes4.dex */
    public enum LimitType implements Internal.EnumLite {
        FIRST(0),
        LAST(1),
        UNRECOGNIZED(-1);

        public static final int FIRST_VALUE = 0;
        public static final int LAST_VALUE = 1;
        private static final Internal.EnumLiteMap<LimitType> internalValueMap = new Internal.EnumLiteMap<LimitType>() { // from class: com.google.firestore.bundle.BundledQuery.LimitType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LimitType findValueByNumber(int i) {
                return LimitType.forNumber(i);
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
        public static LimitType valueOf(int i) {
            return forNumber(i);
        }

        public static LimitType forNumber(int i) {
            if (i == 0) {
                return FIRST;
            }
            if (i != 1) {
                return null;
            }
            return LAST;
        }

        public static Internal.EnumLiteMap<LimitType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return LimitTypeVerifier.INSTANCE;
        }

        /* loaded from: classes4.dex */
        private static final class LimitTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new LimitTypeVerifier();

            private LimitTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return LimitType.forNumber(i) != null;
            }
        }

        LimitType(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes4.dex */
    public enum QueryTypeCase {
        STRUCTURED_QUERY(2),
        QUERYTYPE_NOT_SET(0);

        private final int value;

        QueryTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static QueryTypeCase forNumber(int i) {
            if (i == 0) {
                return QUERYTYPE_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return STRUCTURED_QUERY;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
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

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 2;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public StructuredQuery getStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            return (StructuredQuery) this.queryType_;
        }
        return StructuredQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        this.queryType_ = structuredQuery;
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        if (this.queryTypeCase_ == 2 && this.queryType_ != StructuredQuery.getDefaultInstance()) {
            this.queryType_ = StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom((StructuredQuery.Builder) structuredQuery).buildPartial();
        } else {
            this.queryType_ = structuredQuery;
        }
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public int getLimitTypeValue() {
        return this.limitType_;
    }

    @Override // com.google.firestore.bundle.BundledQueryOrBuilder
    public LimitType getLimitType() {
        LimitType forNumber = LimitType.forNumber(this.limitType_);
        return forNumber == null ? LimitType.UNRECOGNIZED : forNumber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLimitTypeValue(int i) {
        this.limitType_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLimitType(LimitType limitType) {
        this.limitType_ = limitType.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLimitType() {
        this.limitType_ = 0;
    }

    public static BundledQuery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static BundledQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundledQuery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundledQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundledQuery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundledQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundledQuery parseFrom(InputStream inputStream) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundledQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundledQuery parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BundledQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundledQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundledQuery parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundledQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BundledQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(BundledQuery bundledQuery) {
        return DEFAULT_INSTANCE.createBuilder(bundledQuery);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<BundledQuery, Builder> implements BundledQueryOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(BundledQuery.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return ((BundledQuery) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearQueryType();
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public String getParent() {
            return ((BundledQuery) this.instance).getParent();
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public ByteString getParentBytes() {
            return ((BundledQuery) this.instance).getParentBytes();
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((BundledQuery) this.instance).setParent(str);
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearParent();
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((BundledQuery) this.instance).setParentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public boolean hasStructuredQuery() {
            return ((BundledQuery) this.instance).hasStructuredQuery();
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public StructuredQuery getStructuredQuery() {
            return ((BundledQuery) this.instance).getStructuredQuery();
        }

        public Builder setStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((BundledQuery) this.instance).setStructuredQuery(structuredQuery);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builder) {
            copyOnWrite();
            ((BundledQuery) this.instance).setStructuredQuery(builder.build());
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((BundledQuery) this.instance).mergeStructuredQuery(structuredQuery);
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearStructuredQuery();
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public int getLimitTypeValue() {
            return ((BundledQuery) this.instance).getLimitTypeValue();
        }

        public Builder setLimitTypeValue(int i) {
            copyOnWrite();
            ((BundledQuery) this.instance).setLimitTypeValue(i);
            return this;
        }

        @Override // com.google.firestore.bundle.BundledQueryOrBuilder
        public LimitType getLimitType() {
            return ((BundledQuery) this.instance).getLimitType();
        }

        public Builder setLimitType(LimitType limitType) {
            copyOnWrite();
            ((BundledQuery) this.instance).setLimitType(limitType);
            return this;
        }

        public Builder clearLimitType() {
            copyOnWrite();
            ((BundledQuery) this.instance).clearLimitType();
            return this;
        }
    }

    /* renamed from: com.google.firestore.bundle.BundledQuery$1, reason: invalid class name */
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
                return new BundledQuery();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0003\f", new Object[]{"queryType_", "queryTypeCase_", "parent_", StructuredQuery.class, "limitType_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<BundledQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (BundledQuery.class) {
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
        BundledQuery bundledQuery = new BundledQuery();
        DEFAULT_INSTANCE = bundledQuery;
        GeneratedMessageLite.registerDefaultInstance(BundledQuery.class, bundledQuery);
    }

    public static BundledQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BundledQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
