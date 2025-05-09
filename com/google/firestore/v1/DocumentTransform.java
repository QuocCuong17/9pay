package com.google.firestore.v1;

import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
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
public final class DocumentTransform extends GeneratedMessageLite<DocumentTransform, Builder> implements DocumentTransformOrBuilder {
    private static final DocumentTransform DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static final int FIELD_TRANSFORMS_FIELD_NUMBER = 2;
    private static volatile Parser<DocumentTransform> PARSER;
    private String document_ = "";
    private Internal.ProtobufList<FieldTransform> fieldTransforms_ = emptyProtobufList();

    /* loaded from: classes4.dex */
    public interface FieldTransformOrBuilder extends MessageLiteOrBuilder {
        ArrayValue getAppendMissingElements();

        String getFieldPath();

        ByteString getFieldPathBytes();

        Value getIncrement();

        Value getMaximum();

        Value getMinimum();

        ArrayValue getRemoveAllFromArray();

        FieldTransform.ServerValue getSetToServerValue();

        int getSetToServerValueValue();

        FieldTransform.TransformTypeCase getTransformTypeCase();

        boolean hasAppendMissingElements();

        boolean hasIncrement();

        boolean hasMaximum();

        boolean hasMinimum();

        boolean hasRemoveAllFromArray();

        boolean hasSetToServerValue();
    }

    private DocumentTransform() {
    }

    /* loaded from: classes4.dex */
    public static final class FieldTransform extends GeneratedMessageLite<FieldTransform, Builder> implements FieldTransformOrBuilder {
        public static final int APPEND_MISSING_ELEMENTS_FIELD_NUMBER = 6;
        private static final FieldTransform DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 1;
        public static final int INCREMENT_FIELD_NUMBER = 3;
        public static final int MAXIMUM_FIELD_NUMBER = 4;
        public static final int MINIMUM_FIELD_NUMBER = 5;
        private static volatile Parser<FieldTransform> PARSER = null;
        public static final int REMOVE_ALL_FROM_ARRAY_FIELD_NUMBER = 7;
        public static final int SET_TO_SERVER_VALUE_FIELD_NUMBER = 2;
        private Object transformType_;
        private int transformTypeCase_ = 0;
        private String fieldPath_ = "";

        private FieldTransform() {
        }

        /* loaded from: classes4.dex */
        public enum ServerValue implements Internal.EnumLite {
            SERVER_VALUE_UNSPECIFIED(0),
            REQUEST_TIME(1),
            UNRECOGNIZED(-1);

            public static final int REQUEST_TIME_VALUE = 1;
            public static final int SERVER_VALUE_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<ServerValue> internalValueMap = new Internal.EnumLiteMap<ServerValue>() { // from class: com.google.firestore.v1.DocumentTransform.FieldTransform.ServerValue.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public ServerValue findValueByNumber(int i) {
                    return ServerValue.forNumber(i);
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
            public static ServerValue valueOf(int i) {
                return forNumber(i);
            }

            public static ServerValue forNumber(int i) {
                if (i == 0) {
                    return SERVER_VALUE_UNSPECIFIED;
                }
                if (i != 1) {
                    return null;
                }
                return REQUEST_TIME;
            }

            public static Internal.EnumLiteMap<ServerValue> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return ServerValueVerifier.INSTANCE;
            }

            /* loaded from: classes4.dex */
            private static final class ServerValueVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new ServerValueVerifier();

                private ServerValueVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return ServerValue.forNumber(i) != null;
                }
            }

            ServerValue(int i) {
                this.value = i;
            }
        }

        /* loaded from: classes4.dex */
        public enum TransformTypeCase {
            SET_TO_SERVER_VALUE(2),
            INCREMENT(3),
            MAXIMUM(4),
            MINIMUM(5),
            APPEND_MISSING_ELEMENTS(6),
            REMOVE_ALL_FROM_ARRAY(7),
            TRANSFORMTYPE_NOT_SET(0);

            private final int value;

            TransformTypeCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static TransformTypeCase valueOf(int i) {
                return forNumber(i);
            }

            public static TransformTypeCase forNumber(int i) {
                if (i != 0) {
                    switch (i) {
                        case 2:
                            return SET_TO_SERVER_VALUE;
                        case 3:
                            return INCREMENT;
                        case 4:
                            return MAXIMUM;
                        case 5:
                            return MINIMUM;
                        case 6:
                            return APPEND_MISSING_ELEMENTS;
                        case 7:
                            return REMOVE_ALL_FROM_ARRAY;
                        default:
                            return null;
                    }
                }
                return TRANSFORMTYPE_NOT_SET;
            }

            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public TransformTypeCase getTransformTypeCase() {
            return TransformTypeCase.forNumber(this.transformTypeCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTransformType() {
            this.transformTypeCase_ = 0;
            this.transformType_ = null;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public String getFieldPath() {
            return this.fieldPath_;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
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

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public boolean hasSetToServerValue() {
            return this.transformTypeCase_ == 2;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public int getSetToServerValueValue() {
            if (this.transformTypeCase_ == 2) {
                return ((Integer) this.transformType_).intValue();
            }
            return 0;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public ServerValue getSetToServerValue() {
            if (this.transformTypeCase_ == 2) {
                ServerValue forNumber = ServerValue.forNumber(((Integer) this.transformType_).intValue());
                return forNumber == null ? ServerValue.UNRECOGNIZED : forNumber;
            }
            return ServerValue.SERVER_VALUE_UNSPECIFIED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetToServerValueValue(int i) {
            this.transformTypeCase_ = 2;
            this.transformType_ = Integer.valueOf(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSetToServerValue(ServerValue serverValue) {
            this.transformType_ = Integer.valueOf(serverValue.getNumber());
            this.transformTypeCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSetToServerValue() {
            if (this.transformTypeCase_ == 2) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public boolean hasIncrement() {
            return this.transformTypeCase_ == 3;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public Value getIncrement() {
            if (this.transformTypeCase_ == 3) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIncrement(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIncrement(Value value) {
            value.getClass();
            if (this.transformTypeCase_ == 3 && this.transformType_ != Value.getDefaultInstance()) {
                this.transformType_ = Value.newBuilder((Value) this.transformType_).mergeFrom((Value.Builder) value).buildPartial();
            } else {
                this.transformType_ = value;
            }
            this.transformTypeCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIncrement() {
            if (this.transformTypeCase_ == 3) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public boolean hasMaximum() {
            return this.transformTypeCase_ == 4;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public Value getMaximum() {
            if (this.transformTypeCase_ == 4) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaximum(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeMaximum(Value value) {
            value.getClass();
            if (this.transformTypeCase_ == 4 && this.transformType_ != Value.getDefaultInstance()) {
                this.transformType_ = Value.newBuilder((Value) this.transformType_).mergeFrom((Value.Builder) value).buildPartial();
            } else {
                this.transformType_ = value;
            }
            this.transformTypeCase_ = 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMaximum() {
            if (this.transformTypeCase_ == 4) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public boolean hasMinimum() {
            return this.transformTypeCase_ == 5;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public Value getMinimum() {
            if (this.transformTypeCase_ == 5) {
                return (Value) this.transformType_;
            }
            return Value.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinimum(Value value) {
            value.getClass();
            this.transformType_ = value;
            this.transformTypeCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeMinimum(Value value) {
            value.getClass();
            if (this.transformTypeCase_ == 5 && this.transformType_ != Value.getDefaultInstance()) {
                this.transformType_ = Value.newBuilder((Value) this.transformType_).mergeFrom((Value.Builder) value).buildPartial();
            } else {
                this.transformType_ = value;
            }
            this.transformTypeCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearMinimum() {
            if (this.transformTypeCase_ == 5) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public boolean hasAppendMissingElements() {
            return this.transformTypeCase_ == 6;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public ArrayValue getAppendMissingElements() {
            if (this.transformTypeCase_ == 6) {
                return (ArrayValue) this.transformType_;
            }
            return ArrayValue.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppendMissingElements(ArrayValue arrayValue) {
            arrayValue.getClass();
            this.transformType_ = arrayValue;
            this.transformTypeCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAppendMissingElements(ArrayValue arrayValue) {
            arrayValue.getClass();
            if (this.transformTypeCase_ == 6 && this.transformType_ != ArrayValue.getDefaultInstance()) {
                this.transformType_ = ArrayValue.newBuilder((ArrayValue) this.transformType_).mergeFrom((ArrayValue.Builder) arrayValue).buildPartial();
            } else {
                this.transformType_ = arrayValue;
            }
            this.transformTypeCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppendMissingElements() {
            if (this.transformTypeCase_ == 6) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public boolean hasRemoveAllFromArray() {
            return this.transformTypeCase_ == 7;
        }

        @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
        public ArrayValue getRemoveAllFromArray() {
            if (this.transformTypeCase_ == 7) {
                return (ArrayValue) this.transformType_;
            }
            return ArrayValue.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRemoveAllFromArray(ArrayValue arrayValue) {
            arrayValue.getClass();
            this.transformType_ = arrayValue;
            this.transformTypeCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeRemoveAllFromArray(ArrayValue arrayValue) {
            arrayValue.getClass();
            if (this.transformTypeCase_ == 7 && this.transformType_ != ArrayValue.getDefaultInstance()) {
                this.transformType_ = ArrayValue.newBuilder((ArrayValue) this.transformType_).mergeFrom((ArrayValue.Builder) arrayValue).buildPartial();
            } else {
                this.transformType_ = arrayValue;
            }
            this.transformTypeCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRemoveAllFromArray() {
            if (this.transformTypeCase_ == 7) {
                this.transformTypeCase_ = 0;
                this.transformType_ = null;
            }
        }

        public static FieldTransform parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static FieldTransform parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldTransform parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldTransform parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(InputStream inputStream) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldTransform parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldTransform parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldTransform parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldTransform parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldTransform parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldTransform fieldTransform) {
            return DEFAULT_INSTANCE.createBuilder(fieldTransform);
        }

        /* loaded from: classes4.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldTransform, Builder> implements FieldTransformOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(FieldTransform.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public TransformTypeCase getTransformTypeCase() {
                return ((FieldTransform) this.instance).getTransformTypeCase();
            }

            public Builder clearTransformType() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearTransformType();
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public String getFieldPath() {
                return ((FieldTransform) this.instance).getFieldPath();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public ByteString getFieldPathBytes() {
                return ((FieldTransform) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String str) {
                copyOnWrite();
                ((FieldTransform) this.instance).setFieldPath(str);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldTransform) this.instance).setFieldPathBytes(byteString);
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public boolean hasSetToServerValue() {
                return ((FieldTransform) this.instance).hasSetToServerValue();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public int getSetToServerValueValue() {
                return ((FieldTransform) this.instance).getSetToServerValueValue();
            }

            public Builder setSetToServerValueValue(int i) {
                copyOnWrite();
                ((FieldTransform) this.instance).setSetToServerValueValue(i);
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public ServerValue getSetToServerValue() {
                return ((FieldTransform) this.instance).getSetToServerValue();
            }

            public Builder setSetToServerValue(ServerValue serverValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setSetToServerValue(serverValue);
                return this;
            }

            public Builder clearSetToServerValue() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearSetToServerValue();
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public boolean hasIncrement() {
                return ((FieldTransform) this.instance).hasIncrement();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public Value getIncrement() {
                return ((FieldTransform) this.instance).getIncrement();
            }

            public Builder setIncrement(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setIncrement(value);
                return this;
            }

            public Builder setIncrement(Value.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setIncrement(builder.build());
                return this;
            }

            public Builder mergeIncrement(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeIncrement(value);
                return this;
            }

            public Builder clearIncrement() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearIncrement();
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public boolean hasMaximum() {
                return ((FieldTransform) this.instance).hasMaximum();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public Value getMaximum() {
                return ((FieldTransform) this.instance).getMaximum();
            }

            public Builder setMaximum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMaximum(value);
                return this;
            }

            public Builder setMaximum(Value.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMaximum(builder.build());
                return this;
            }

            public Builder mergeMaximum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeMaximum(value);
                return this;
            }

            public Builder clearMaximum() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearMaximum();
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public boolean hasMinimum() {
                return ((FieldTransform) this.instance).hasMinimum();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public Value getMinimum() {
                return ((FieldTransform) this.instance).getMinimum();
            }

            public Builder setMinimum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMinimum(value);
                return this;
            }

            public Builder setMinimum(Value.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setMinimum(builder.build());
                return this;
            }

            public Builder mergeMinimum(Value value) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeMinimum(value);
                return this;
            }

            public Builder clearMinimum() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearMinimum();
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public boolean hasAppendMissingElements() {
                return ((FieldTransform) this.instance).hasAppendMissingElements();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public ArrayValue getAppendMissingElements() {
                return ((FieldTransform) this.instance).getAppendMissingElements();
            }

            public Builder setAppendMissingElements(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setAppendMissingElements(arrayValue);
                return this;
            }

            public Builder setAppendMissingElements(ArrayValue.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setAppendMissingElements(builder.build());
                return this;
            }

            public Builder mergeAppendMissingElements(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeAppendMissingElements(arrayValue);
                return this;
            }

            public Builder clearAppendMissingElements() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearAppendMissingElements();
                return this;
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public boolean hasRemoveAllFromArray() {
                return ((FieldTransform) this.instance).hasRemoveAllFromArray();
            }

            @Override // com.google.firestore.v1.DocumentTransform.FieldTransformOrBuilder
            public ArrayValue getRemoveAllFromArray() {
                return ((FieldTransform) this.instance).getRemoveAllFromArray();
            }

            public Builder setRemoveAllFromArray(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).setRemoveAllFromArray(arrayValue);
                return this;
            }

            public Builder setRemoveAllFromArray(ArrayValue.Builder builder) {
                copyOnWrite();
                ((FieldTransform) this.instance).setRemoveAllFromArray(builder.build());
                return this;
            }

            public Builder mergeRemoveAllFromArray(ArrayValue arrayValue) {
                copyOnWrite();
                ((FieldTransform) this.instance).mergeRemoveAllFromArray(arrayValue);
                return this;
            }

            public Builder clearRemoveAllFromArray() {
                copyOnWrite();
                ((FieldTransform) this.instance).clearRemoveAllFromArray();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldTransform();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002?\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000", new Object[]{"transformType_", "transformTypeCase_", "fieldPath_", Value.class, Value.class, Value.class, ArrayValue.class, ArrayValue.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldTransform> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldTransform.class) {
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
            FieldTransform fieldTransform = new FieldTransform();
            DEFAULT_INSTANCE = fieldTransform;
            GeneratedMessageLite.registerDefaultInstance(FieldTransform.class, fieldTransform);
        }

        public static FieldTransform getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldTransform> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.DocumentTransform$1, reason: invalid class name */
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

    @Override // com.google.firestore.v1.DocumentTransformOrBuilder
    public String getDocument() {
        return this.document_;
    }

    @Override // com.google.firestore.v1.DocumentTransformOrBuilder
    public ByteString getDocumentBytes() {
        return ByteString.copyFromUtf8(this.document_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(String str) {
        str.getClass();
        this.document_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        this.document_ = getDefaultInstance().getDocument();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.document_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.DocumentTransformOrBuilder
    public List<FieldTransform> getFieldTransformsList() {
        return this.fieldTransforms_;
    }

    public List<? extends FieldTransformOrBuilder> getFieldTransformsOrBuilderList() {
        return this.fieldTransforms_;
    }

    @Override // com.google.firestore.v1.DocumentTransformOrBuilder
    public int getFieldTransformsCount() {
        return this.fieldTransforms_.size();
    }

    @Override // com.google.firestore.v1.DocumentTransformOrBuilder
    public FieldTransform getFieldTransforms(int i) {
        return this.fieldTransforms_.get(i);
    }

    public FieldTransformOrBuilder getFieldTransformsOrBuilder(int i) {
        return this.fieldTransforms_.get(i);
    }

    private void ensureFieldTransformsIsMutable() {
        Internal.ProtobufList<FieldTransform> protobufList = this.fieldTransforms_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.fieldTransforms_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFieldTransforms(int i, FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.set(i, fieldTransform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldTransforms(FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.add(fieldTransform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldTransforms(int i, FieldTransform fieldTransform) {
        fieldTransform.getClass();
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.add(i, fieldTransform);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllFieldTransforms(Iterable<? extends FieldTransform> iterable) {
        ensureFieldTransformsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.fieldTransforms_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFieldTransforms() {
        this.fieldTransforms_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFieldTransforms(int i) {
        ensureFieldTransformsIsMutable();
        this.fieldTransforms_.remove(i);
    }

    public static DocumentTransform parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DocumentTransform parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentTransform parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentTransform parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(InputStream inputStream) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentTransform parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentTransform parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DocumentTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentTransform parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentTransform) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentTransform parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentTransform parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DocumentTransform) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DocumentTransform documentTransform) {
        return DEFAULT_INSTANCE.createBuilder(documentTransform);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<DocumentTransform, Builder> implements DocumentTransformOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DocumentTransform.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.DocumentTransformOrBuilder
        public String getDocument() {
            return ((DocumentTransform) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.DocumentTransformOrBuilder
        public ByteString getDocumentBytes() {
            return ((DocumentTransform) this.instance).getDocumentBytes();
        }

        public Builder setDocument(String str) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setDocument(str);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentTransform) this.instance).clearDocument();
            return this;
        }

        public Builder setDocumentBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setDocumentBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.DocumentTransformOrBuilder
        public List<FieldTransform> getFieldTransformsList() {
            return Collections.unmodifiableList(((DocumentTransform) this.instance).getFieldTransformsList());
        }

        @Override // com.google.firestore.v1.DocumentTransformOrBuilder
        public int getFieldTransformsCount() {
            return ((DocumentTransform) this.instance).getFieldTransformsCount();
        }

        @Override // com.google.firestore.v1.DocumentTransformOrBuilder
        public FieldTransform getFieldTransforms(int i) {
            return ((DocumentTransform) this.instance).getFieldTransforms(i);
        }

        public Builder setFieldTransforms(int i, FieldTransform fieldTransform) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setFieldTransforms(i, fieldTransform);
            return this;
        }

        public Builder setFieldTransforms(int i, FieldTransform.Builder builder) {
            copyOnWrite();
            ((DocumentTransform) this.instance).setFieldTransforms(i, builder.build());
            return this;
        }

        public Builder addFieldTransforms(FieldTransform fieldTransform) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(fieldTransform);
            return this;
        }

        public Builder addFieldTransforms(int i, FieldTransform fieldTransform) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(i, fieldTransform);
            return this;
        }

        public Builder addFieldTransforms(FieldTransform.Builder builder) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(builder.build());
            return this;
        }

        public Builder addFieldTransforms(int i, FieldTransform.Builder builder) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addFieldTransforms(i, builder.build());
            return this;
        }

        public Builder addAllFieldTransforms(Iterable<? extends FieldTransform> iterable) {
            copyOnWrite();
            ((DocumentTransform) this.instance).addAllFieldTransforms(iterable);
            return this;
        }

        public Builder clearFieldTransforms() {
            copyOnWrite();
            ((DocumentTransform) this.instance).clearFieldTransforms();
            return this;
        }

        public Builder removeFieldTransforms(int i) {
            copyOnWrite();
            ((DocumentTransform) this.instance).removeFieldTransforms(i);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new DocumentTransform();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"document_", "fieldTransforms_", FieldTransform.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DocumentTransform> parser = PARSER;
                if (parser == null) {
                    synchronized (DocumentTransform.class) {
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
        DocumentTransform documentTransform = new DocumentTransform();
        DEFAULT_INSTANCE = documentTransform;
        GeneratedMessageLite.registerDefaultInstance(DocumentTransform.class, documentTransform);
    }

    public static DocumentTransform getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DocumentTransform> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
