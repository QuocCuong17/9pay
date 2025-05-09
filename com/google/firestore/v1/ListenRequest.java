package com.google.firestore.v1;

import com.google.firestore.v1.Target;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes4.dex */
public final class ListenRequest extends GeneratedMessageLite<ListenRequest, Builder> implements ListenRequestOrBuilder {
    public static final int ADD_TARGET_FIELD_NUMBER = 2;
    public static final int DATABASE_FIELD_NUMBER = 1;
    private static final ListenRequest DEFAULT_INSTANCE;
    public static final int LABELS_FIELD_NUMBER = 4;
    private static volatile Parser<ListenRequest> PARSER = null;
    public static final int REMOVE_TARGET_FIELD_NUMBER = 3;
    private Object targetChange_;
    private int targetChangeCase_ = 0;
    private MapFieldLite<String, String> labels_ = MapFieldLite.emptyMapField();
    private String database_ = "";

    private ListenRequest() {
    }

    /* loaded from: classes4.dex */
    public enum TargetChangeCase {
        ADD_TARGET(2),
        REMOVE_TARGET(3),
        TARGETCHANGE_NOT_SET(0);

        private final int value;

        TargetChangeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static TargetChangeCase valueOf(int i) {
            return forNumber(i);
        }

        public static TargetChangeCase forNumber(int i) {
            if (i == 0) {
                return TARGETCHANGE_NOT_SET;
            }
            if (i == 2) {
                return ADD_TARGET;
            }
            if (i != 3) {
                return null;
            }
            return REMOVE_TARGET;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public TargetChangeCase getTargetChangeCase() {
        return TargetChangeCase.forNumber(this.targetChangeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetChange() {
        this.targetChangeCase_ = 0;
        this.targetChange_ = null;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = getDefaultInstance().getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public boolean hasAddTarget() {
        return this.targetChangeCase_ == 2;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public Target getAddTarget() {
        if (this.targetChangeCase_ == 2) {
            return (Target) this.targetChange_;
        }
        return Target.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAddTarget(Target target) {
        target.getClass();
        this.targetChange_ = target;
        this.targetChangeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAddTarget(Target target) {
        target.getClass();
        if (this.targetChangeCase_ == 2 && this.targetChange_ != Target.getDefaultInstance()) {
            this.targetChange_ = Target.newBuilder((Target) this.targetChange_).mergeFrom((Target.Builder) target).buildPartial();
        } else {
            this.targetChange_ = target;
        }
        this.targetChangeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAddTarget() {
        if (this.targetChangeCase_ == 2) {
            this.targetChangeCase_ = 0;
            this.targetChange_ = null;
        }
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public boolean hasRemoveTarget() {
        return this.targetChangeCase_ == 3;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public int getRemoveTarget() {
        if (this.targetChangeCase_ == 3) {
            return ((Integer) this.targetChange_).intValue();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemoveTarget(int i) {
        this.targetChangeCase_ = 3;
        this.targetChange_ = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRemoveTarget() {
        if (this.targetChangeCase_ == 3) {
            this.targetChangeCase_ = 0;
            this.targetChange_ = null;
        }
    }

    /* loaded from: classes4.dex */
    private static final class LabelsDefaultEntryHolder {
        static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, String> internalGetLabels() {
        return this.labels_;
    }

    private MapFieldLite<String, String> internalGetMutableLabels() {
        if (!this.labels_.isMutable()) {
            this.labels_ = this.labels_.mutableCopy();
        }
        return this.labels_;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public int getLabelsCount() {
        return internalGetLabels().size();
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public boolean containsLabels(String str) {
        str.getClass();
        return internalGetLabels().containsKey(str);
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public Map<String, String> getLabelsMap() {
        return Collections.unmodifiableMap(internalGetLabels());
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public String getLabelsOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> internalGetLabels = internalGetLabels();
        return internalGetLabels.containsKey(str) ? internalGetLabels.get(str) : str2;
    }

    @Override // com.google.firestore.v1.ListenRequestOrBuilder
    public String getLabelsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> internalGetLabels = internalGetLabels();
        if (!internalGetLabels.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return internalGetLabels.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> getMutableLabelsMap() {
        return internalGetMutableLabels();
    }

    public static ListenRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ListenRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListenRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListenRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(InputStream inputStream) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListenRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListenRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListenRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ListenRequest listenRequest) {
        return DEFAULT_INSTANCE.createBuilder(listenRequest);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<ListenRequest, Builder> implements ListenRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ListenRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public TargetChangeCase getTargetChangeCase() {
            return ((ListenRequest) this.instance).getTargetChangeCase();
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearTargetChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public String getDatabase() {
            return ((ListenRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((ListenRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((ListenRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((ListenRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public boolean hasAddTarget() {
            return ((ListenRequest) this.instance).hasAddTarget();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public Target getAddTarget() {
            return ((ListenRequest) this.instance).getAddTarget();
        }

        public Builder setAddTarget(Target target) {
            copyOnWrite();
            ((ListenRequest) this.instance).setAddTarget(target);
            return this;
        }

        public Builder setAddTarget(Target.Builder builder) {
            copyOnWrite();
            ((ListenRequest) this.instance).setAddTarget(builder.build());
            return this;
        }

        public Builder mergeAddTarget(Target target) {
            copyOnWrite();
            ((ListenRequest) this.instance).mergeAddTarget(target);
            return this;
        }

        public Builder clearAddTarget() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearAddTarget();
            return this;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public boolean hasRemoveTarget() {
            return ((ListenRequest) this.instance).hasRemoveTarget();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public int getRemoveTarget() {
            return ((ListenRequest) this.instance).getRemoveTarget();
        }

        public Builder setRemoveTarget(int i) {
            copyOnWrite();
            ((ListenRequest) this.instance).setRemoveTarget(i);
            return this;
        }

        public Builder clearRemoveTarget() {
            copyOnWrite();
            ((ListenRequest) this.instance).clearRemoveTarget();
            return this;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public int getLabelsCount() {
            return ((ListenRequest) this.instance).getLabelsMap().size();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public boolean containsLabels(String str) {
            str.getClass();
            return ((ListenRequest) this.instance).getLabelsMap().containsKey(str);
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().clear();
            return this;
        }

        public Builder removeLabels(String str) {
            str.getClass();
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().remove(str);
            return this;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public Map<String, String> getLabelsMap() {
            return Collections.unmodifiableMap(((ListenRequest) this.instance).getLabelsMap());
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public String getLabelsOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> labelsMap = ((ListenRequest) this.instance).getLabelsMap();
            return labelsMap.containsKey(str) ? labelsMap.get(str) : str2;
        }

        @Override // com.google.firestore.v1.ListenRequestOrBuilder
        public String getLabelsOrThrow(String str) {
            str.getClass();
            Map<String, String> labelsMap = ((ListenRequest) this.instance).getLabelsMap();
            if (!labelsMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return labelsMap.get(str);
        }

        public Builder putLabels(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().put(str, str2);
            return this;
        }

        public Builder putAllLabels(Map<String, String> map) {
            copyOnWrite();
            ((ListenRequest) this.instance).getMutableLabelsMap().putAll(map);
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.ListenRequest$1, reason: invalid class name */
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
                return new ListenRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0001\u0000\u0000\u0001Ȉ\u0002<\u0000\u00037\u0000\u00042", new Object[]{"targetChange_", "targetChangeCase_", "database_", Target.class, "labels_", LabelsDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ListenRequest> parser = PARSER;
                if (parser == null) {
                    synchronized (ListenRequest.class) {
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
        ListenRequest listenRequest = new ListenRequest();
        DEFAULT_INSTANCE = listenRequest;
        GeneratedMessageLite.registerDefaultInstance(ListenRequest.class, listenRequest);
    }

    public static ListenRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListenRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
