package com.google.firestore.v1;

import com.google.firestore.v1.Cursor;
import com.google.firestore.v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class StructuredQuery extends GeneratedMessageLite<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
    private static final StructuredQuery DEFAULT_INSTANCE;
    public static final int END_AT_FIELD_NUMBER = 8;
    public static final int FROM_FIELD_NUMBER = 2;
    public static final int LIMIT_FIELD_NUMBER = 5;
    public static final int OFFSET_FIELD_NUMBER = 6;
    public static final int ORDER_BY_FIELD_NUMBER = 4;
    private static volatile Parser<StructuredQuery> PARSER = null;
    public static final int SELECT_FIELD_NUMBER = 1;
    public static final int START_AT_FIELD_NUMBER = 7;
    public static final int WHERE_FIELD_NUMBER = 3;
    private Cursor endAt_;
    private Int32Value limit_;
    private int offset_;
    private Projection select_;
    private Cursor startAt_;
    private Filter where_;
    private Internal.ProtobufList<CollectionSelector> from_ = emptyProtobufList();
    private Internal.ProtobufList<Order> orderBy_ = emptyProtobufList();

    /* loaded from: classes4.dex */
    public interface CollectionSelectorOrBuilder extends MessageLiteOrBuilder {
        boolean getAllDescendants();

        String getCollectionId();

        ByteString getCollectionIdBytes();
    }

    /* loaded from: classes4.dex */
    public interface CompositeFilterOrBuilder extends MessageLiteOrBuilder {
        Filter getFilters(int i);

        int getFiltersCount();

        List<Filter> getFiltersList();

        CompositeFilter.Operator getOp();

        int getOpValue();
    }

    /* loaded from: classes4.dex */
    public interface FieldFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        FieldFilter.Operator getOp();

        int getOpValue();

        Value getValue();

        boolean hasField();

        boolean hasValue();
    }

    /* loaded from: classes4.dex */
    public interface FieldReferenceOrBuilder extends MessageLiteOrBuilder {
        String getFieldPath();

        ByteString getFieldPathBytes();
    }

    /* loaded from: classes4.dex */
    public interface FilterOrBuilder extends MessageLiteOrBuilder {
        CompositeFilter getCompositeFilter();

        FieldFilter getFieldFilter();

        Filter.FilterTypeCase getFilterTypeCase();

        UnaryFilter getUnaryFilter();

        boolean hasCompositeFilter();

        boolean hasFieldFilter();

        boolean hasUnaryFilter();
    }

    /* loaded from: classes4.dex */
    public interface OrderOrBuilder extends MessageLiteOrBuilder {
        Direction getDirection();

        int getDirectionValue();

        FieldReference getField();

        boolean hasField();
    }

    /* loaded from: classes4.dex */
    public interface ProjectionOrBuilder extends MessageLiteOrBuilder {
        FieldReference getFields(int i);

        int getFieldsCount();

        List<FieldReference> getFieldsList();
    }

    /* loaded from: classes4.dex */
    public interface UnaryFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        UnaryFilter.Operator getOp();

        int getOpValue();

        UnaryFilter.OperandTypeCase getOperandTypeCase();

        boolean hasField();
    }

    private StructuredQuery() {
    }

    /* loaded from: classes4.dex */
    public enum Direction implements Internal.EnumLite {
        DIRECTION_UNSPECIFIED(0),
        ASCENDING(1),
        DESCENDING(2),
        UNRECOGNIZED(-1);

        public static final int ASCENDING_VALUE = 1;
        public static final int DESCENDING_VALUE = 2;
        public static final int DIRECTION_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<Direction> internalValueMap = new Internal.EnumLiteMap<Direction>() { // from class: com.google.firestore.v1.StructuredQuery.Direction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Direction findValueByNumber(int i) {
                return Direction.forNumber(i);
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
        public static Direction valueOf(int i) {
            return forNumber(i);
        }

        public static Direction forNumber(int i) {
            if (i == 0) {
                return DIRECTION_UNSPECIFIED;
            }
            if (i == 1) {
                return ASCENDING;
            }
            if (i != 2) {
                return null;
            }
            return DESCENDING;
        }

        public static Internal.EnumLiteMap<Direction> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DirectionVerifier.INSTANCE;
        }

        /* loaded from: classes4.dex */
        private static final class DirectionVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new DirectionVerifier();

            private DirectionVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return Direction.forNumber(i) != null;
            }
        }

        Direction(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes4.dex */
    public static final class CollectionSelector extends GeneratedMessageLite<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
        public static final int ALL_DESCENDANTS_FIELD_NUMBER = 3;
        public static final int COLLECTION_ID_FIELD_NUMBER = 2;
        private static final CollectionSelector DEFAULT_INSTANCE;
        private static volatile Parser<CollectionSelector> PARSER;
        private boolean allDescendants_;
        private String collectionId_ = "";

        private CollectionSelector() {
        }

        @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
        public String getCollectionId() {
            return this.collectionId_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
        public ByteString getCollectionIdBytes() {
            return ByteString.copyFromUtf8(this.collectionId_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCollectionId(String str) {
            str.getClass();
            this.collectionId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCollectionId() {
            this.collectionId_ = getDefaultInstance().getCollectionId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCollectionIdBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.collectionId_ = byteString.toStringUtf8();
        }

        @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
        public boolean getAllDescendants() {
            return this.allDescendants_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAllDescendants(boolean z) {
            this.allDescendants_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAllDescendants() {
            this.allDescendants_ = false;
        }

        public static CollectionSelector parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CollectionSelector parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CollectionSelector parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CollectionSelector parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(InputStream inputStream) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CollectionSelector parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CollectionSelector) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CollectionSelector) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CollectionSelector parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CollectionSelector parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CollectionSelector collectionSelector) {
            return DEFAULT_INSTANCE.createBuilder(collectionSelector);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(CollectionSelector.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
            public String getCollectionId() {
                return ((CollectionSelector) this.instance).getCollectionId();
            }

            @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
            public ByteString getCollectionIdBytes() {
                return ((CollectionSelector) this.instance).getCollectionIdBytes();
            }

            public Builder setCollectionId(String str) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionId(str);
                return this;
            }

            public Builder clearCollectionId() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearCollectionId();
                return this;
            }

            public Builder setCollectionIdBytes(ByteString byteString) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionIdBytes(byteString);
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.CollectionSelectorOrBuilder
            public boolean getAllDescendants() {
                return ((CollectionSelector) this.instance).getAllDescendants();
            }

            public Builder setAllDescendants(boolean z) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setAllDescendants(z);
                return this;
            }

            public Builder clearAllDescendants() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearAllDescendants();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new CollectionSelector();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002Ȉ\u0003\u0007", new Object[]{"collectionId_", "allDescendants_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CollectionSelector> parser = PARSER;
                    if (parser == null) {
                        synchronized (CollectionSelector.class) {
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
            CollectionSelector collectionSelector = new CollectionSelector();
            DEFAULT_INSTANCE = collectionSelector;
            GeneratedMessageLite.registerDefaultInstance(CollectionSelector.class, collectionSelector);
        }

        public static CollectionSelector getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CollectionSelector> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$1, reason: invalid class name */
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

    /* loaded from: classes4.dex */
    public static final class Filter extends GeneratedMessageLite<Filter, Builder> implements FilterOrBuilder {
        public static final int COMPOSITE_FILTER_FIELD_NUMBER = 1;
        private static final Filter DEFAULT_INSTANCE;
        public static final int FIELD_FILTER_FIELD_NUMBER = 2;
        private static volatile Parser<Filter> PARSER = null;
        public static final int UNARY_FILTER_FIELD_NUMBER = 3;
        private int filterTypeCase_ = 0;
        private Object filterType_;

        private Filter() {
        }

        /* loaded from: classes4.dex */
        public enum FilterTypeCase {
            COMPOSITE_FILTER(1),
            FIELD_FILTER(2),
            UNARY_FILTER(3),
            FILTERTYPE_NOT_SET(0);

            private final int value;

            FilterTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static FilterTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static FilterTypeCase forNumber(int i) {
                if (i == 0) {
                    return FILTERTYPE_NOT_SET;
                }
                if (i == 1) {
                    return COMPOSITE_FILTER;
                }
                if (i == 2) {
                    return FIELD_FILTER;
                }
                if (i != 3) {
                    return null;
                }
                return UNARY_FILTER;
            }

            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public FilterTypeCase getFilterTypeCase() {
            return FilterTypeCase.forNumber(this.filterTypeCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFilterType() {
            this.filterTypeCase_ = 0;
            this.filterType_ = null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public boolean hasCompositeFilter() {
            return this.filterTypeCase_ == 1;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public CompositeFilter getCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                return (CompositeFilter) this.filterType_;
            }
            return CompositeFilter.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCompositeFilter(CompositeFilter compositeFilter) {
            compositeFilter.getClass();
            this.filterType_ = compositeFilter;
            this.filterTypeCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCompositeFilter(CompositeFilter compositeFilter) {
            compositeFilter.getClass();
            if (this.filterTypeCase_ == 1 && this.filterType_ != CompositeFilter.getDefaultInstance()) {
                this.filterType_ = CompositeFilter.newBuilder((CompositeFilter) this.filterType_).mergeFrom((CompositeFilter.Builder) compositeFilter).buildPartial();
            } else {
                this.filterType_ = compositeFilter;
            }
            this.filterTypeCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public boolean hasFieldFilter() {
            return this.filterTypeCase_ == 2;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public FieldFilter getFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                return (FieldFilter) this.filterType_;
            }
            return FieldFilter.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldFilter(FieldFilter fieldFilter) {
            fieldFilter.getClass();
            this.filterType_ = fieldFilter;
            this.filterTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFieldFilter(FieldFilter fieldFilter) {
            fieldFilter.getClass();
            if (this.filterTypeCase_ == 2 && this.filterType_ != FieldFilter.getDefaultInstance()) {
                this.filterType_ = FieldFilter.newBuilder((FieldFilter) this.filterType_).mergeFrom((FieldFilter.Builder) fieldFilter).buildPartial();
            } else {
                this.filterType_ = fieldFilter;
            }
            this.filterTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public boolean hasUnaryFilter() {
            return this.filterTypeCase_ == 3;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
        public UnaryFilter getUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                return (UnaryFilter) this.filterType_;
            }
            return UnaryFilter.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnaryFilter(UnaryFilter unaryFilter) {
            unaryFilter.getClass();
            this.filterType_ = unaryFilter;
            this.filterTypeCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnaryFilter(UnaryFilter unaryFilter) {
            unaryFilter.getClass();
            if (this.filterTypeCase_ == 3 && this.filterType_ != UnaryFilter.getDefaultInstance()) {
                this.filterType_ = UnaryFilter.newBuilder((UnaryFilter) this.filterType_).mergeFrom((UnaryFilter.Builder) unaryFilter).buildPartial();
            } else {
                this.filterType_ = unaryFilter;
            }
            this.filterTypeCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public static Filter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Filter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Filter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Filter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Filter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Filter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Filter parseFrom(InputStream inputStream) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Filter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Filter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Filter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Filter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Filter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Filter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Filter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Filter filter) {
            return DEFAULT_INSTANCE.createBuilder(filter);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Filter, Builder> implements FilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Filter.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public FilterTypeCase getFilterTypeCase() {
                return ((Filter) this.instance).getFilterTypeCase();
            }

            public Builder clearFilterType() {
                copyOnWrite();
                ((Filter) this.instance).clearFilterType();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public boolean hasCompositeFilter() {
                return ((Filter) this.instance).hasCompositeFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public CompositeFilter getCompositeFilter() {
                return ((Filter) this.instance).getCompositeFilter();
            }

            public Builder setCompositeFilter(CompositeFilter compositeFilter) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter(compositeFilter);
                return this;
            }

            public Builder setCompositeFilter(CompositeFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter(builder.build());
                return this;
            }

            public Builder mergeCompositeFilter(CompositeFilter compositeFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeCompositeFilter(compositeFilter);
                return this;
            }

            public Builder clearCompositeFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearCompositeFilter();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public boolean hasFieldFilter() {
                return ((Filter) this.instance).hasFieldFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public FieldFilter getFieldFilter() {
                return ((Filter) this.instance).getFieldFilter();
            }

            public Builder setFieldFilter(FieldFilter fieldFilter) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter(fieldFilter);
                return this;
            }

            public Builder setFieldFilter(FieldFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter(builder.build());
                return this;
            }

            public Builder mergeFieldFilter(FieldFilter fieldFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeFieldFilter(fieldFilter);
                return this;
            }

            public Builder clearFieldFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearFieldFilter();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public boolean hasUnaryFilter() {
                return ((Filter) this.instance).hasUnaryFilter();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FilterOrBuilder
            public UnaryFilter getUnaryFilter() {
                return ((Filter) this.instance).getUnaryFilter();
            }

            public Builder setUnaryFilter(UnaryFilter unaryFilter) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter(unaryFilter);
                return this;
            }

            public Builder setUnaryFilter(UnaryFilter.Builder builder) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter(builder.build());
                return this;
            }

            public Builder mergeUnaryFilter(UnaryFilter unaryFilter) {
                copyOnWrite();
                ((Filter) this.instance).mergeUnaryFilter(unaryFilter);
                return this;
            }

            public Builder clearUnaryFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearUnaryFilter();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Filter();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"filterType_", "filterTypeCase_", CompositeFilter.class, FieldFilter.class, UnaryFilter.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Filter> parser = PARSER;
                    if (parser == null) {
                        synchronized (Filter.class) {
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
            Filter filter = new Filter();
            DEFAULT_INSTANCE = filter;
            GeneratedMessageLite.registerDefaultInstance(Filter.class, filter);
        }

        public static Filter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Filter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public static final class CompositeFilter extends GeneratedMessageLite<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
        private static final CompositeFilter DEFAULT_INSTANCE;
        public static final int FILTERS_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        private static volatile Parser<CompositeFilter> PARSER;
        private Internal.ProtobufList<Filter> filters_ = emptyProtobufList();
        private int op_;

        private CompositeFilter() {
        }

        /* loaded from: classes4.dex */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            AND(1),
            OR(2),
            UNRECOGNIZED(-1);

            public static final int AND_VALUE = 1;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            public static final int OR_VALUE = 2;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = new Internal.EnumLiteMap<Operator>() { // from class: com.google.firestore.v1.StructuredQuery.CompositeFilter.Operator.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Operator findValueByNumber(int i) {
                    return Operator.forNumber(i);
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
            public static Operator valueOf(int i) {
                return forNumber(i);
            }

            public static Operator forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_UNSPECIFIED;
                }
                if (i == 1) {
                    return AND;
                }
                if (i != 2) {
                    return null;
                }
                return OR;
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* loaded from: classes4.dex */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new OperatorVerifier();

                private OperatorVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            Operator(int i) {
                this.value = i;
            }
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public int getOpValue() {
            return this.op_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public Operator getOp() {
            Operator forNumber = Operator.forNumber(this.op_);
            return forNumber == null ? Operator.UNRECOGNIZED : forNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOpValue(int i) {
            this.op_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public List<Filter> getFiltersList() {
            return this.filters_;
        }

        public List<? extends FilterOrBuilder> getFiltersOrBuilderList() {
            return this.filters_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public int getFiltersCount() {
            return this.filters_.size();
        }

        @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
        public Filter getFilters(int i) {
            return this.filters_.get(i);
        }

        public FilterOrBuilder getFiltersOrBuilder(int i) {
            return this.filters_.get(i);
        }

        private void ensureFiltersIsMutable() {
            Internal.ProtobufList<Filter> protobufList = this.filters_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.filters_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFilters(int i, Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.set(i, filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFilters(Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFilters(int i, Filter filter) {
            filter.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(i, filter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFilters(Iterable<? extends Filter> iterable) {
            ensureFiltersIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.filters_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFilters() {
            this.filters_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeFilters(int i) {
            ensureFiltersIsMutable();
            this.filters_.remove(i);
        }

        public static CompositeFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static CompositeFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CompositeFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CompositeFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(InputStream inputStream) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompositeFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CompositeFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CompositeFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CompositeFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CompositeFilter compositeFilter) {
            return DEFAULT_INSTANCE.createBuilder(compositeFilter);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(CompositeFilter.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public int getOpValue() {
                return ((CompositeFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOpValue(i);
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public Operator getOp() {
                return ((CompositeFilter) this.instance).getOp();
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearOp();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public List<Filter> getFiltersList() {
                return Collections.unmodifiableList(((CompositeFilter) this.instance).getFiltersList());
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public int getFiltersCount() {
                return ((CompositeFilter) this.instance).getFiltersCount();
            }

            @Override // com.google.firestore.v1.StructuredQuery.CompositeFilterOrBuilder
            public Filter getFilters(int i) {
                return ((CompositeFilter) this.instance).getFilters(i);
            }

            public Builder setFilters(int i, Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(i, filter);
                return this;
            }

            public Builder setFilters(int i, Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(i, builder.build());
                return this;
            }

            public Builder addFilters(Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(filter);
                return this;
            }

            public Builder addFilters(int i, Filter filter) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(i, filter);
                return this;
            }

            public Builder addFilters(Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(builder.build());
                return this;
            }

            public Builder addFilters(int i, Filter.Builder builder) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(i, builder.build());
                return this;
            }

            public Builder addAllFilters(Iterable<? extends Filter> iterable) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addAllFilters(iterable);
                return this;
            }

            public Builder clearFilters() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearFilters();
                return this;
            }

            public Builder removeFilters(int i) {
                copyOnWrite();
                ((CompositeFilter) this.instance).removeFilters(i);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new CompositeFilter();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0002\u001b", new Object[]{"op_", "filters_", Filter.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CompositeFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (CompositeFilter.class) {
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
            CompositeFilter compositeFilter = new CompositeFilter();
            DEFAULT_INSTANCE = compositeFilter;
            GeneratedMessageLite.registerDefaultInstance(CompositeFilter.class, compositeFilter);
        }

        public static CompositeFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CompositeFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public static final class FieldFilter extends GeneratedMessageLite<FieldFilter, Builder> implements FieldFilterOrBuilder {
        private static final FieldFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static final int OP_FIELD_NUMBER = 2;
        private static volatile Parser<FieldFilter> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 3;
        private FieldReference field_;
        private int op_;
        private Value value_;

        private FieldFilter() {
        }

        /* loaded from: classes4.dex */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            LESS_THAN(1),
            LESS_THAN_OR_EQUAL(2),
            GREATER_THAN(3),
            GREATER_THAN_OR_EQUAL(4),
            EQUAL(5),
            NOT_EQUAL(6),
            ARRAY_CONTAINS(7),
            IN(8),
            ARRAY_CONTAINS_ANY(9),
            NOT_IN(10),
            UNRECOGNIZED(-1);

            public static final int ARRAY_CONTAINS_ANY_VALUE = 9;
            public static final int ARRAY_CONTAINS_VALUE = 7;
            public static final int EQUAL_VALUE = 5;
            public static final int GREATER_THAN_OR_EQUAL_VALUE = 4;
            public static final int GREATER_THAN_VALUE = 3;
            public static final int IN_VALUE = 8;
            public static final int LESS_THAN_OR_EQUAL_VALUE = 2;
            public static final int LESS_THAN_VALUE = 1;
            public static final int NOT_EQUAL_VALUE = 6;
            public static final int NOT_IN_VALUE = 10;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = new Internal.EnumLiteMap<Operator>() { // from class: com.google.firestore.v1.StructuredQuery.FieldFilter.Operator.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Operator findValueByNumber(int i) {
                    return Operator.forNumber(i);
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
            public static Operator valueOf(int i) {
                return forNumber(i);
            }

            public static Operator forNumber(int i) {
                switch (i) {
                    case 0:
                        return OPERATOR_UNSPECIFIED;
                    case 1:
                        return LESS_THAN;
                    case 2:
                        return LESS_THAN_OR_EQUAL;
                    case 3:
                        return GREATER_THAN;
                    case 4:
                        return GREATER_THAN_OR_EQUAL;
                    case 5:
                        return EQUAL;
                    case 6:
                        return NOT_EQUAL;
                    case 7:
                        return ARRAY_CONTAINS;
                    case 8:
                        return IN;
                    case 9:
                        return ARRAY_CONTAINS_ANY;
                    case 10:
                        return NOT_IN;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* loaded from: classes4.dex */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new OperatorVerifier();

                private OperatorVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            Operator(int i) {
                this.value = i;
            }
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public boolean hasField() {
            return this.field_ != null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.getDefaultInstance() : fieldReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.field_ = fieldReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            FieldReference fieldReference2 = this.field_;
            if (fieldReference2 != null && fieldReference2 != FieldReference.getDefaultInstance()) {
                this.field_ = FieldReference.newBuilder(this.field_).mergeFrom((FieldReference.Builder) fieldReference).buildPartial();
            } else {
                this.field_ = fieldReference;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearField() {
            this.field_ = null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public int getOpValue() {
            return this.op_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public Operator getOp() {
            Operator forNumber = Operator.forNumber(this.op_);
            return forNumber == null ? Operator.UNRECOGNIZED : forNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOpValue(int i) {
            this.op_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public boolean hasValue() {
            return this.value_ != null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
        public Value getValue() {
            Value value = this.value_;
            return value == null ? Value.getDefaultInstance() : value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setValue(Value value) {
            value.getClass();
            this.value_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeValue(Value value) {
            value.getClass();
            Value value2 = this.value_;
            if (value2 != null && value2 != Value.getDefaultInstance()) {
                this.value_ = Value.newBuilder(this.value_).mergeFrom((Value.Builder) value).buildPartial();
            } else {
                this.value_ = value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearValue() {
            this.value_ = null;
        }

        public static FieldFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FieldFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(InputStream inputStream) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldFilter fieldFilter) {
            return DEFAULT_INSTANCE.createBuilder(fieldFilter);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldFilter, Builder> implements FieldFilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(FieldFilter.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public boolean hasField() {
                return ((FieldFilter) this.instance).hasField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public FieldReference getField() {
                return ((FieldFilter) this.instance).getField();
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField(builder.build());
                return this;
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearField();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public int getOpValue() {
                return ((FieldFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOpValue(i);
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public Operator getOp() {
                return ((FieldFilter) this.instance).getOp();
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearOp();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public boolean hasValue() {
                return ((FieldFilter) this.instance).hasValue();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldFilterOrBuilder
            public Value getValue() {
                return ((FieldFilter) this.instance).getValue();
            }

            public Builder setValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue(value);
                return this;
            }

            public Builder setValue(Value.Builder builder) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue(builder.build());
                return this;
            }

            public Builder mergeValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeValue(value);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearValue();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldFilter();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\f\u0003\t", new Object[]{"field_", "op_", "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldFilter.class) {
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
            FieldFilter fieldFilter = new FieldFilter();
            DEFAULT_INSTANCE = fieldFilter;
            GeneratedMessageLite.registerDefaultInstance(FieldFilter.class, fieldFilter);
        }

        public static FieldFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public static final class UnaryFilter extends GeneratedMessageLite<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
        private static final UnaryFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        private static volatile Parser<UnaryFilter> PARSER;
        private int op_;
        private int operandTypeCase_ = 0;
        private Object operandType_;

        private UnaryFilter() {
        }

        /* loaded from: classes4.dex */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            IS_NAN(2),
            IS_NULL(3),
            IS_NOT_NAN(4),
            IS_NOT_NULL(5),
            UNRECOGNIZED(-1);

            public static final int IS_NAN_VALUE = 2;
            public static final int IS_NOT_NAN_VALUE = 4;
            public static final int IS_NOT_NULL_VALUE = 5;
            public static final int IS_NULL_VALUE = 3;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = new Internal.EnumLiteMap<Operator>() { // from class: com.google.firestore.v1.StructuredQuery.UnaryFilter.Operator.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Operator findValueByNumber(int i) {
                    return Operator.forNumber(i);
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
            public static Operator valueOf(int i) {
                return forNumber(i);
            }

            public static Operator forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_UNSPECIFIED;
                }
                if (i == 2) {
                    return IS_NAN;
                }
                if (i == 3) {
                    return IS_NULL;
                }
                if (i == 4) {
                    return IS_NOT_NAN;
                }
                if (i != 5) {
                    return null;
                }
                return IS_NOT_NULL;
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* loaded from: classes4.dex */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new OperatorVerifier();

                private OperatorVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return Operator.forNumber(i) != null;
                }
            }

            Operator(int i) {
                this.value = i;
            }
        }

        /* loaded from: classes4.dex */
        public enum OperandTypeCase {
            FIELD(2),
            OPERANDTYPE_NOT_SET(0);

            private final int value;

            OperandTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static OperandTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static OperandTypeCase forNumber(int i) {
                if (i == 0) {
                    return OPERANDTYPE_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return FIELD;
            }

            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public OperandTypeCase getOperandTypeCase() {
            return OperandTypeCase.forNumber(this.operandTypeCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOperandType() {
            this.operandTypeCase_ = 0;
            this.operandType_ = null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public int getOpValue() {
            return this.op_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public Operator getOp() {
            Operator forNumber = Operator.forNumber(this.op_);
            return forNumber == null ? Operator.UNRECOGNIZED : forNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOpValue(int i) {
            this.op_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOp(Operator operator) {
            this.op_ = operator.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public boolean hasField() {
            return this.operandTypeCase_ == 2;
        }

        @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
        public FieldReference getField() {
            if (this.operandTypeCase_ == 2) {
                return (FieldReference) this.operandType_;
            }
            return FieldReference.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.operandType_ = fieldReference;
            this.operandTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            if (this.operandTypeCase_ == 2 && this.operandType_ != FieldReference.getDefaultInstance()) {
                this.operandType_ = FieldReference.newBuilder((FieldReference) this.operandType_).mergeFrom((FieldReference.Builder) fieldReference).buildPartial();
            } else {
                this.operandType_ = fieldReference;
            }
            this.operandTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearField() {
            if (this.operandTypeCase_ == 2) {
                this.operandTypeCase_ = 0;
                this.operandType_ = null;
            }
        }

        public static UnaryFilter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static UnaryFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UnaryFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UnaryFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(InputStream inputStream) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnaryFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UnaryFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnaryFilter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UnaryFilter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UnaryFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(UnaryFilter unaryFilter) {
            return DEFAULT_INSTANCE.createBuilder(unaryFilter);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(UnaryFilter.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public OperandTypeCase getOperandTypeCase() {
                return ((UnaryFilter) this.instance).getOperandTypeCase();
            }

            public Builder clearOperandType() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOperandType();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public int getOpValue() {
                return ((UnaryFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int i) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOpValue(i);
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public Operator getOp() {
                return ((UnaryFilter) this.instance).getOp();
            }

            public Builder setOp(Operator operator) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOp(operator);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOp();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public boolean hasField() {
                return ((UnaryFilter) this.instance).hasField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.UnaryFilterOrBuilder
            public FieldReference getField() {
                return ((UnaryFilter) this.instance).getField();
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField(builder.build());
                return this;
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((UnaryFilter) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearField();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new UnaryFilter();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002<\u0000", new Object[]{"operandType_", "operandTypeCase_", "op_", FieldReference.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<UnaryFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (UnaryFilter.class) {
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
            UnaryFilter unaryFilter = new UnaryFilter();
            DEFAULT_INSTANCE = unaryFilter;
            GeneratedMessageLite.registerDefaultInstance(UnaryFilter.class, unaryFilter);
        }

        public static UnaryFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnaryFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public static final class Order extends GeneratedMessageLite<Order, Builder> implements OrderOrBuilder {
        private static final Order DEFAULT_INSTANCE;
        public static final int DIRECTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        private static volatile Parser<Order> PARSER;
        private int direction_;
        private FieldReference field_;

        private Order() {
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public boolean hasField() {
            return this.field_ != null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.getDefaultInstance() : fieldReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setField(FieldReference fieldReference) {
            fieldReference.getClass();
            this.field_ = fieldReference;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeField(FieldReference fieldReference) {
            fieldReference.getClass();
            FieldReference fieldReference2 = this.field_;
            if (fieldReference2 != null && fieldReference2 != FieldReference.getDefaultInstance()) {
                this.field_ = FieldReference.newBuilder(this.field_).mergeFrom((FieldReference.Builder) fieldReference).buildPartial();
            } else {
                this.field_ = fieldReference;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearField() {
            this.field_ = null;
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public int getDirectionValue() {
            return this.direction_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
        public Direction getDirection() {
            Direction forNumber = Direction.forNumber(this.direction_);
            return forNumber == null ? Direction.UNRECOGNIZED : forNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDirectionValue(int i) {
            this.direction_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDirection(Direction direction) {
            this.direction_ = direction.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDirection() {
            this.direction_ = 0;
        }

        public static Order parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Order parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Order parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Order parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Order parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Order parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Order parseFrom(InputStream inputStream) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Order parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Order parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Order) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Order parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Order) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Order parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Order parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Order order) {
            return DEFAULT_INSTANCE.createBuilder(order);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Order, Builder> implements OrderOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Order.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public boolean hasField() {
                return ((Order) this.instance).hasField();
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public FieldReference getField() {
                return ((Order) this.instance).getField();
            }

            public Builder setField(FieldReference fieldReference) {
                copyOnWrite();
                ((Order) this.instance).setField(fieldReference);
                return this;
            }

            public Builder setField(FieldReference.Builder builder) {
                copyOnWrite();
                ((Order) this.instance).setField(builder.build());
                return this;
            }

            public Builder mergeField(FieldReference fieldReference) {
                copyOnWrite();
                ((Order) this.instance).mergeField(fieldReference);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((Order) this.instance).clearField();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public int getDirectionValue() {
                return ((Order) this.instance).getDirectionValue();
            }

            public Builder setDirectionValue(int i) {
                copyOnWrite();
                ((Order) this.instance).setDirectionValue(i);
                return this;
            }

            @Override // com.google.firestore.v1.StructuredQuery.OrderOrBuilder
            public Direction getDirection() {
                return ((Order) this.instance).getDirection();
            }

            public Builder setDirection(Direction direction) {
                copyOnWrite();
                ((Order) this.instance).setDirection(direction);
                return this;
            }

            public Builder clearDirection() {
                copyOnWrite();
                ((Order) this.instance).clearDirection();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Order();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\f", new Object[]{"field_", "direction_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Order> parser = PARSER;
                    if (parser == null) {
                        synchronized (Order.class) {
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
            Order order = new Order();
            DEFAULT_INSTANCE = order;
            GeneratedMessageLite.registerDefaultInstance(Order.class, order);
        }

        public static Order getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Order> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public static final class FieldReference extends GeneratedMessageLite<FieldReference, Builder> implements FieldReferenceOrBuilder {
        private static final FieldReference DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 2;
        private static volatile Parser<FieldReference> PARSER;
        private String fieldPath_ = "";

        private FieldReference() {
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
        public String getFieldPath() {
            return this.fieldPath_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
        public ByteString getFieldPathBytes() {
            return ByteString.copyFromUtf8(this.fieldPath_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldPath(String str) {
            str.getClass();
            this.fieldPath_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFieldPath() {
            this.fieldPath_ = getDefaultInstance().getFieldPath();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFieldPathBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.fieldPath_ = byteString.toStringUtf8();
        }

        public static FieldReference parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FieldReference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldReference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldReference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldReference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldReference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldReference parseFrom(InputStream inputStream) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldReference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldReference parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldReference) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldReference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldReference) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldReference parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldReference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldReference fieldReference) {
            return DEFAULT_INSTANCE.createBuilder(fieldReference);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldReference, Builder> implements FieldReferenceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(FieldReference.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
            public String getFieldPath() {
                return ((FieldReference) this.instance).getFieldPath();
            }

            @Override // com.google.firestore.v1.StructuredQuery.FieldReferenceOrBuilder
            public ByteString getFieldPathBytes() {
                return ((FieldReference) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String str) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPath(str);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldReference) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPathBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldReference();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002Ȉ", new Object[]{"fieldPath_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldReference> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldReference.class) {
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
            FieldReference fieldReference = new FieldReference();
            DEFAULT_INSTANCE = fieldReference;
            GeneratedMessageLite.registerDefaultInstance(FieldReference.class, fieldReference);
        }

        public static FieldReference getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldReference> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* loaded from: classes4.dex */
    public static final class Projection extends GeneratedMessageLite<Projection, Builder> implements ProjectionOrBuilder {
        private static final Projection DEFAULT_INSTANCE;
        public static final int FIELDS_FIELD_NUMBER = 2;
        private static volatile Parser<Projection> PARSER;
        private Internal.ProtobufList<FieldReference> fields_ = emptyProtobufList();

        private Projection() {
        }

        @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
        public List<FieldReference> getFieldsList() {
            return this.fields_;
        }

        public List<? extends FieldReferenceOrBuilder> getFieldsOrBuilderList() {
            return this.fields_;
        }

        @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
        public int getFieldsCount() {
            return this.fields_.size();
        }

        @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
        public FieldReference getFields(int i) {
            return this.fields_.get(i);
        }

        public FieldReferenceOrBuilder getFieldsOrBuilder(int i) {
            return this.fields_.get(i);
        }

        private void ensureFieldsIsMutable() {
            Internal.ProtobufList<FieldReference> protobufList = this.fields_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.fields_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFields(int i, FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.set(i, fieldReference);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFields(FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(fieldReference);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addFields(int i, FieldReference fieldReference) {
            fieldReference.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(i, fieldReference);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllFields(Iterable<? extends FieldReference> iterable) {
            ensureFieldsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.fields_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFields() {
            this.fields_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeFields(int i) {
            ensureFieldsIsMutable();
            this.fields_.remove(i);
        }

        public static Projection parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Projection parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Projection parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Projection parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Projection parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Projection parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Projection parseFrom(InputStream inputStream) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Projection parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Projection parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Projection) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Projection parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Projection) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Projection parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Projection parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Projection projection) {
            return DEFAULT_INSTANCE.createBuilder(projection);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Projection, Builder> implements ProjectionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Projection.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
            public List<FieldReference> getFieldsList() {
                return Collections.unmodifiableList(((Projection) this.instance).getFieldsList());
            }

            @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
            public int getFieldsCount() {
                return ((Projection) this.instance).getFieldsCount();
            }

            @Override // com.google.firestore.v1.StructuredQuery.ProjectionOrBuilder
            public FieldReference getFields(int i) {
                return ((Projection) this.instance).getFields(i);
            }

            public Builder setFields(int i, FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).setFields(i, fieldReference);
                return this;
            }

            public Builder setFields(int i, FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).setFields(i, builder.build());
                return this;
            }

            public Builder addFields(FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).addFields(fieldReference);
                return this;
            }

            public Builder addFields(int i, FieldReference fieldReference) {
                copyOnWrite();
                ((Projection) this.instance).addFields(i, fieldReference);
                return this;
            }

            public Builder addFields(FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).addFields(builder.build());
                return this;
            }

            public Builder addFields(int i, FieldReference.Builder builder) {
                copyOnWrite();
                ((Projection) this.instance).addFields(i, builder.build());
                return this;
            }

            public Builder addAllFields(Iterable<? extends FieldReference> iterable) {
                copyOnWrite();
                ((Projection) this.instance).addAllFields(iterable);
                return this;
            }

            public Builder clearFields() {
                copyOnWrite();
                ((Projection) this.instance).clearFields();
                return this;
            }

            public Builder removeFields(int i) {
                copyOnWrite();
                ((Projection) this.instance).removeFields(i);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Projection();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0000\u0002\u001b", new Object[]{"fields_", FieldReference.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Projection> parser = PARSER;
                    if (parser == null) {
                        synchronized (Projection.class) {
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
            Projection projection = new Projection();
            DEFAULT_INSTANCE = projection;
            GeneratedMessageLite.registerDefaultInstance(Projection.class, projection);
        }

        public static Projection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Projection> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasSelect() {
        return this.select_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Projection getSelect() {
        Projection projection = this.select_;
        return projection == null ? Projection.getDefaultInstance() : projection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelect(Projection projection) {
        projection.getClass();
        this.select_ = projection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSelect(Projection projection) {
        projection.getClass();
        Projection projection2 = this.select_;
        if (projection2 != null && projection2 != Projection.getDefaultInstance()) {
            this.select_ = Projection.newBuilder(this.select_).mergeFrom((Projection.Builder) projection).buildPartial();
        } else {
            this.select_ = projection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelect() {
        this.select_ = null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public List<CollectionSelector> getFromList() {
        return this.from_;
    }

    public List<? extends CollectionSelectorOrBuilder> getFromOrBuilderList() {
        return this.from_;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public int getFromCount() {
        return this.from_.size();
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public CollectionSelector getFrom(int i) {
        return this.from_.get(i);
    }

    public CollectionSelectorOrBuilder getFromOrBuilder(int i) {
        return this.from_.get(i);
    }

    private void ensureFromIsMutable() {
        Internal.ProtobufList<CollectionSelector> protobufList = this.from_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.from_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrom(int i, CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.set(i, collectionSelector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFrom(CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.add(collectionSelector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFrom(int i, CollectionSelector collectionSelector) {
        collectionSelector.getClass();
        ensureFromIsMutable();
        this.from_.add(i, collectionSelector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllFrom(Iterable<? extends CollectionSelector> iterable) {
        ensureFromIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.from_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFrom() {
        this.from_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFrom(int i) {
        ensureFromIsMutable();
        this.from_.remove(i);
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasWhere() {
        return this.where_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Filter getWhere() {
        Filter filter = this.where_;
        return filter == null ? Filter.getDefaultInstance() : filter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWhere(Filter filter) {
        filter.getClass();
        this.where_ = filter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeWhere(Filter filter) {
        filter.getClass();
        Filter filter2 = this.where_;
        if (filter2 != null && filter2 != Filter.getDefaultInstance()) {
            this.where_ = Filter.newBuilder(this.where_).mergeFrom((Filter.Builder) filter).buildPartial();
        } else {
            this.where_ = filter;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWhere() {
        this.where_ = null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public List<Order> getOrderByList() {
        return this.orderBy_;
    }

    public List<? extends OrderOrBuilder> getOrderByOrBuilderList() {
        return this.orderBy_;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public int getOrderByCount() {
        return this.orderBy_.size();
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Order getOrderBy(int i) {
        return this.orderBy_.get(i);
    }

    public OrderOrBuilder getOrderByOrBuilder(int i) {
        return this.orderBy_.get(i);
    }

    private void ensureOrderByIsMutable() {
        Internal.ProtobufList<Order> protobufList = this.orderBy_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.orderBy_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrderBy(int i, Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.set(i, order);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOrderBy(Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(order);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addOrderBy(int i, Order order) {
        order.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(i, order);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllOrderBy(Iterable<? extends Order> iterable) {
        ensureOrderByIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.orderBy_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOrderBy() {
        this.orderBy_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeOrderBy(int i) {
        ensureOrderByIsMutable();
        this.orderBy_.remove(i);
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasStartAt() {
        return this.startAt_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Cursor getStartAt() {
        Cursor cursor = this.startAt_;
        return cursor == null ? Cursor.getDefaultInstance() : cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartAt(Cursor cursor) {
        cursor.getClass();
        this.startAt_ = cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStartAt(Cursor cursor) {
        cursor.getClass();
        Cursor cursor2 = this.startAt_;
        if (cursor2 != null && cursor2 != Cursor.getDefaultInstance()) {
            this.startAt_ = Cursor.newBuilder(this.startAt_).mergeFrom((Cursor.Builder) cursor).buildPartial();
        } else {
            this.startAt_ = cursor;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStartAt() {
        this.startAt_ = null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasEndAt() {
        return this.endAt_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Cursor getEndAt() {
        Cursor cursor = this.endAt_;
        return cursor == null ? Cursor.getDefaultInstance() : cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndAt(Cursor cursor) {
        cursor.getClass();
        this.endAt_ = cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeEndAt(Cursor cursor) {
        cursor.getClass();
        Cursor cursor2 = this.endAt_;
        if (cursor2 != null && cursor2 != Cursor.getDefaultInstance()) {
            this.endAt_ = Cursor.newBuilder(this.endAt_).mergeFrom((Cursor.Builder) cursor).buildPartial();
        } else {
            this.endAt_ = cursor;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEndAt() {
        this.endAt_ = null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public int getOffset() {
        return this.offset_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOffset(int i) {
        this.offset_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOffset() {
        this.offset_ = 0;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public boolean hasLimit() {
        return this.limit_ != null;
    }

    @Override // com.google.firestore.v1.StructuredQueryOrBuilder
    public Int32Value getLimit() {
        Int32Value int32Value = this.limit_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLimit(Int32Value int32Value) {
        int32Value.getClass();
        this.limit_ = int32Value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLimit(Int32Value int32Value) {
        int32Value.getClass();
        Int32Value int32Value2 = this.limit_;
        if (int32Value2 != null && int32Value2 != Int32Value.getDefaultInstance()) {
            this.limit_ = Int32Value.newBuilder(this.limit_).mergeFrom((Int32Value.Builder) int32Value).buildPartial();
        } else {
            this.limit_ = int32Value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLimit() {
        this.limit_ = null;
    }

    public static StructuredQuery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static StructuredQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static StructuredQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static StructuredQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(InputStream inputStream) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StructuredQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredQuery parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static StructuredQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StructuredQuery structuredQuery) {
        return DEFAULT_INSTANCE.createBuilder(structuredQuery);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(StructuredQuery.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasSelect() {
            return ((StructuredQuery) this.instance).hasSelect();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Projection getSelect() {
            return ((StructuredQuery) this.instance).getSelect();
        }

        public Builder setSelect(Projection projection) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect(projection);
            return this;
        }

        public Builder setSelect(Projection.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect(builder.build());
            return this;
        }

        public Builder mergeSelect(Projection projection) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeSelect(projection);
            return this;
        }

        public Builder clearSelect() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearSelect();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public List<CollectionSelector> getFromList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getFromList());
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public int getFromCount() {
            return ((StructuredQuery) this.instance).getFromCount();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public CollectionSelector getFrom(int i) {
            return ((StructuredQuery) this.instance).getFrom(i);
        }

        public Builder setFrom(int i, CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(i, collectionSelector);
            return this;
        }

        public Builder setFrom(int i, CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(i, builder.build());
            return this;
        }

        public Builder addFrom(CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(collectionSelector);
            return this;
        }

        public Builder addFrom(int i, CollectionSelector collectionSelector) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(i, collectionSelector);
            return this;
        }

        public Builder addFrom(CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(builder.build());
            return this;
        }

        public Builder addFrom(int i, CollectionSelector.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(i, builder.build());
            return this;
        }

        public Builder addAllFrom(Iterable<? extends CollectionSelector> iterable) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addAllFrom(iterable);
            return this;
        }

        public Builder clearFrom() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearFrom();
            return this;
        }

        public Builder removeFrom(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).removeFrom(i);
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasWhere() {
            return ((StructuredQuery) this.instance).hasWhere();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Filter getWhere() {
            return ((StructuredQuery) this.instance).getWhere();
        }

        public Builder setWhere(Filter filter) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere(filter);
            return this;
        }

        public Builder setWhere(Filter.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere(builder.build());
            return this;
        }

        public Builder mergeWhere(Filter filter) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeWhere(filter);
            return this;
        }

        public Builder clearWhere() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearWhere();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public List<Order> getOrderByList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getOrderByList());
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public int getOrderByCount() {
            return ((StructuredQuery) this.instance).getOrderByCount();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Order getOrderBy(int i) {
            return ((StructuredQuery) this.instance).getOrderBy(i);
        }

        public Builder setOrderBy(int i, Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(i, order);
            return this;
        }

        public Builder setOrderBy(int i, Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(i, builder.build());
            return this;
        }

        public Builder addOrderBy(Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(order);
            return this;
        }

        public Builder addOrderBy(int i, Order order) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(i, order);
            return this;
        }

        public Builder addOrderBy(Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(builder.build());
            return this;
        }

        public Builder addOrderBy(int i, Order.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(i, builder.build());
            return this;
        }

        public Builder addAllOrderBy(Iterable<? extends Order> iterable) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addAllOrderBy(iterable);
            return this;
        }

        public Builder clearOrderBy() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOrderBy();
            return this;
        }

        public Builder removeOrderBy(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).removeOrderBy(i);
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasStartAt() {
            return ((StructuredQuery) this.instance).hasStartAt();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Cursor getStartAt() {
            return ((StructuredQuery) this.instance).getStartAt();
        }

        public Builder setStartAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt(cursor);
            return this;
        }

        public Builder setStartAt(Cursor.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt(builder.build());
            return this;
        }

        public Builder mergeStartAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeStartAt(cursor);
            return this;
        }

        public Builder clearStartAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearStartAt();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasEndAt() {
            return ((StructuredQuery) this.instance).hasEndAt();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Cursor getEndAt() {
            return ((StructuredQuery) this.instance).getEndAt();
        }

        public Builder setEndAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt(cursor);
            return this;
        }

        public Builder setEndAt(Cursor.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt(builder.build());
            return this;
        }

        public Builder mergeEndAt(Cursor cursor) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeEndAt(cursor);
            return this;
        }

        public Builder clearEndAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearEndAt();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public int getOffset() {
            return ((StructuredQuery) this.instance).getOffset();
        }

        public Builder setOffset(int i) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOffset(i);
            return this;
        }

        public Builder clearOffset() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOffset();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public boolean hasLimit() {
            return ((StructuredQuery) this.instance).hasLimit();
        }

        @Override // com.google.firestore.v1.StructuredQueryOrBuilder
        public Int32Value getLimit() {
            return ((StructuredQuery) this.instance).getLimit();
        }

        public Builder setLimit(Int32Value int32Value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit(int32Value);
            return this;
        }

        public Builder setLimit(Int32Value.Builder builder) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit(builder.build());
            return this;
        }

        public Builder mergeLimit(Int32Value int32Value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeLimit(int32Value);
            return this;
        }

        public Builder clearLimit() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearLimit();
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new StructuredQuery();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0002\u0000\u0001\t\u0002\u001b\u0003\t\u0004\u001b\u0005\t\u0006\u0004\u0007\t\b\t", new Object[]{"select_", "from_", CollectionSelector.class, "where_", "orderBy_", Order.class, "limit_", "offset_", "startAt_", "endAt_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<StructuredQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (StructuredQuery.class) {
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
        StructuredQuery structuredQuery = new StructuredQuery();
        DEFAULT_INSTANCE = structuredQuery;
        GeneratedMessageLite.registerDefaultInstance(StructuredQuery.class, structuredQuery);
    }

    public static StructuredQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StructuredQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
