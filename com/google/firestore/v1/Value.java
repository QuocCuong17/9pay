package com.google.firestore.v1;

import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.NullValue;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class Value extends GeneratedMessageLite<Value, Builder> implements ValueOrBuilder {
    public static final int ARRAY_VALUE_FIELD_NUMBER = 9;
    public static final int BOOLEAN_VALUE_FIELD_NUMBER = 1;
    public static final int BYTES_VALUE_FIELD_NUMBER = 18;
    private static final Value DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int GEO_POINT_VALUE_FIELD_NUMBER = 8;
    public static final int INTEGER_VALUE_FIELD_NUMBER = 2;
    public static final int MAP_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 11;
    private static volatile Parser<Value> PARSER = null;
    public static final int REFERENCE_VALUE_FIELD_NUMBER = 5;
    public static final int STRING_VALUE_FIELD_NUMBER = 17;
    public static final int TIMESTAMP_VALUE_FIELD_NUMBER = 10;
    private int valueTypeCase_ = 0;
    private Object valueType_;

    private Value() {
    }

    /* loaded from: classes4.dex */
    public enum ValueTypeCase {
        NULL_VALUE(11),
        BOOLEAN_VALUE(1),
        INTEGER_VALUE(2),
        DOUBLE_VALUE(3),
        TIMESTAMP_VALUE(10),
        STRING_VALUE(17),
        BYTES_VALUE(18),
        REFERENCE_VALUE(5),
        GEO_POINT_VALUE(8),
        ARRAY_VALUE(9),
        MAP_VALUE(6),
        VALUETYPE_NOT_SET(0);

        private final int value;

        ValueTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ValueTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static ValueTypeCase forNumber(int i) {
            if (i == 0) {
                return VALUETYPE_NOT_SET;
            }
            if (i == 1) {
                return BOOLEAN_VALUE;
            }
            if (i == 2) {
                return INTEGER_VALUE;
            }
            if (i == 3) {
                return DOUBLE_VALUE;
            }
            if (i == 5) {
                return REFERENCE_VALUE;
            }
            if (i == 6) {
                return MAP_VALUE;
            }
            if (i == 17) {
                return STRING_VALUE;
            }
            if (i == 18) {
                return BYTES_VALUE;
            }
            switch (i) {
                case 8:
                    return GEO_POINT_VALUE;
                case 9:
                    return ARRAY_VALUE;
                case 10:
                    return TIMESTAMP_VALUE;
                case 11:
                    return NULL_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ValueTypeCase getValueTypeCase() {
        return ValueTypeCase.forNumber(this.valueTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValueType() {
        this.valueTypeCase_ = 0;
        this.valueType_ = null;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasNullValue() {
        return this.valueTypeCase_ == 11;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public int getNullValueValue() {
        if (this.valueTypeCase_ == 11) {
            return ((Integer) this.valueType_).intValue();
        }
        return 0;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public NullValue getNullValue() {
        if (this.valueTypeCase_ == 11) {
            NullValue forNumber = NullValue.forNumber(((Integer) this.valueType_).intValue());
            return forNumber == null ? NullValue.UNRECOGNIZED : forNumber;
        }
        return NullValue.NULL_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNullValueValue(int i) {
        this.valueTypeCase_ = 11;
        this.valueType_ = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNullValue(NullValue nullValue) {
        this.valueType_ = Integer.valueOf(nullValue.getNumber());
        this.valueTypeCase_ = 11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNullValue() {
        if (this.valueTypeCase_ == 11) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasBooleanValue() {
        return this.valueTypeCase_ == 1;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean getBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            return ((Boolean) this.valueType_).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBooleanValue(boolean z) {
        this.valueTypeCase_ = 1;
        this.valueType_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasIntegerValue() {
        return this.valueTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public long getIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            return ((Long) this.valueType_).longValue();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIntegerValue(long j) {
        this.valueTypeCase_ = 2;
        this.valueType_ = Long.valueOf(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasDoubleValue() {
        return this.valueTypeCase_ == 3;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public double getDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            return ((Double) this.valueType_).doubleValue();
        }
        return 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDoubleValue(double d) {
        this.valueTypeCase_ = 3;
        this.valueType_ = Double.valueOf(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasTimestampValue() {
        return this.valueTypeCase_ == 10;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public Timestamp getTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            return (Timestamp) this.valueType_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimestampValue(Timestamp timestamp) {
        timestamp.getClass();
        this.valueType_ = timestamp;
        this.valueTypeCase_ = 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTimestampValue(Timestamp timestamp) {
        timestamp.getClass();
        if (this.valueTypeCase_ == 10 && this.valueType_ != Timestamp.getDefaultInstance()) {
            this.valueType_ = Timestamp.newBuilder((Timestamp) this.valueType_).mergeFrom((Timestamp.Builder) timestamp).buildPartial();
        } else {
            this.valueType_ = timestamp;
        }
        this.valueTypeCase_ = 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasStringValue() {
        return this.valueTypeCase_ == 17;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public String getStringValue() {
        return this.valueTypeCase_ == 17 ? (String) this.valueType_ : "";
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getStringValueBytes() {
        return ByteString.copyFromUtf8(this.valueTypeCase_ == 17 ? (String) this.valueType_ : "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValue(String str) {
        str.getClass();
        this.valueTypeCase_ = 17;
        this.valueType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStringValue() {
        if (this.valueTypeCase_ == 17) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValueBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.valueType_ = byteString.toStringUtf8();
        this.valueTypeCase_ = 17;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasBytesValue() {
        return this.valueTypeCase_ == 18;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getBytesValue() {
        if (this.valueTypeCase_ == 18) {
            return (ByteString) this.valueType_;
        }
        return ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBytesValue(ByteString byteString) {
        byteString.getClass();
        this.valueTypeCase_ = 18;
        this.valueType_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBytesValue() {
        if (this.valueTypeCase_ == 18) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasReferenceValue() {
        return this.valueTypeCase_ == 5;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public String getReferenceValue() {
        return this.valueTypeCase_ == 5 ? (String) this.valueType_ : "";
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getReferenceValueBytes() {
        return ByteString.copyFromUtf8(this.valueTypeCase_ == 5 ? (String) this.valueType_ : "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferenceValue(String str) {
        str.getClass();
        this.valueTypeCase_ = 5;
        this.valueType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReferenceValue() {
        if (this.valueTypeCase_ == 5) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferenceValueBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.valueType_ = byteString.toStringUtf8();
        this.valueTypeCase_ = 5;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasGeoPointValue() {
        return this.valueTypeCase_ == 8;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public LatLng getGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            return (LatLng) this.valueType_;
        }
        return LatLng.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGeoPointValue(LatLng latLng) {
        latLng.getClass();
        this.valueType_ = latLng;
        this.valueTypeCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeGeoPointValue(LatLng latLng) {
        latLng.getClass();
        if (this.valueTypeCase_ == 8 && this.valueType_ != LatLng.getDefaultInstance()) {
            this.valueType_ = LatLng.newBuilder((LatLng) this.valueType_).mergeFrom((LatLng.Builder) latLng).buildPartial();
        } else {
            this.valueType_ = latLng;
        }
        this.valueTypeCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasArrayValue() {
        return this.valueTypeCase_ == 9;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ArrayValue getArrayValue() {
        if (this.valueTypeCase_ == 9) {
            return (ArrayValue) this.valueType_;
        }
        return ArrayValue.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArrayValue(ArrayValue arrayValue) {
        arrayValue.getClass();
        this.valueType_ = arrayValue;
        this.valueTypeCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeArrayValue(ArrayValue arrayValue) {
        arrayValue.getClass();
        if (this.valueTypeCase_ == 9 && this.valueType_ != ArrayValue.getDefaultInstance()) {
            this.valueType_ = ArrayValue.newBuilder((ArrayValue) this.valueType_).mergeFrom((ArrayValue.Builder) arrayValue).buildPartial();
        } else {
            this.valueType_ = arrayValue;
        }
        this.valueTypeCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearArrayValue() {
        if (this.valueTypeCase_ == 9) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasMapValue() {
        return this.valueTypeCase_ == 6;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public MapValue getMapValue() {
        if (this.valueTypeCase_ == 6) {
            return (MapValue) this.valueType_;
        }
        return MapValue.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMapValue(MapValue mapValue) {
        mapValue.getClass();
        this.valueType_ = mapValue;
        this.valueTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMapValue(MapValue mapValue) {
        mapValue.getClass();
        if (this.valueTypeCase_ == 6 && this.valueType_ != MapValue.getDefaultInstance()) {
            this.valueType_ = MapValue.newBuilder((MapValue) this.valueType_).mergeFrom((MapValue.Builder) mapValue).buildPartial();
        } else {
            this.valueType_ = mapValue;
        }
        this.valueTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMapValue() {
        if (this.valueTypeCase_ == 6) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    public static Value parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Value value) {
        return DEFAULT_INSTANCE.createBuilder(value);
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Value.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ValueTypeCase getValueTypeCase() {
            return ((Value) this.instance).getValueTypeCase();
        }

        public Builder clearValueType() {
            copyOnWrite();
            ((Value) this.instance).clearValueType();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasNullValue() {
            return ((Value) this.instance).hasNullValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public int getNullValueValue() {
            return ((Value) this.instance).getNullValueValue();
        }

        public Builder setNullValueValue(int i) {
            copyOnWrite();
            ((Value) this.instance).setNullValueValue(i);
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public NullValue getNullValue() {
            return ((Value) this.instance).getNullValue();
        }

        public Builder setNullValue(NullValue nullValue) {
            copyOnWrite();
            ((Value) this.instance).setNullValue(nullValue);
            return this;
        }

        public Builder clearNullValue() {
            copyOnWrite();
            ((Value) this.instance).clearNullValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasBooleanValue() {
            return ((Value) this.instance).hasBooleanValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean getBooleanValue() {
            return ((Value) this.instance).getBooleanValue();
        }

        public Builder setBooleanValue(boolean z) {
            copyOnWrite();
            ((Value) this.instance).setBooleanValue(z);
            return this;
        }

        public Builder clearBooleanValue() {
            copyOnWrite();
            ((Value) this.instance).clearBooleanValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasIntegerValue() {
            return ((Value) this.instance).hasIntegerValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public long getIntegerValue() {
            return ((Value) this.instance).getIntegerValue();
        }

        public Builder setIntegerValue(long j) {
            copyOnWrite();
            ((Value) this.instance).setIntegerValue(j);
            return this;
        }

        public Builder clearIntegerValue() {
            copyOnWrite();
            ((Value) this.instance).clearIntegerValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasDoubleValue() {
            return ((Value) this.instance).hasDoubleValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public double getDoubleValue() {
            return ((Value) this.instance).getDoubleValue();
        }

        public Builder setDoubleValue(double d) {
            copyOnWrite();
            ((Value) this.instance).setDoubleValue(d);
            return this;
        }

        public Builder clearDoubleValue() {
            copyOnWrite();
            ((Value) this.instance).clearDoubleValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasTimestampValue() {
            return ((Value) this.instance).hasTimestampValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public Timestamp getTimestampValue() {
            return ((Value) this.instance).getTimestampValue();
        }

        public Builder setTimestampValue(Timestamp timestamp) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(timestamp);
            return this;
        }

        public Builder setTimestampValue(Timestamp.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(builder.build());
            return this;
        }

        public Builder mergeTimestampValue(Timestamp timestamp) {
            copyOnWrite();
            ((Value) this.instance).mergeTimestampValue(timestamp);
            return this;
        }

        public Builder clearTimestampValue() {
            copyOnWrite();
            ((Value) this.instance).clearTimestampValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasStringValue() {
            return ((Value) this.instance).hasStringValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public String getStringValue() {
            return ((Value) this.instance).getStringValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getStringValueBytes() {
            return ((Value) this.instance).getStringValueBytes();
        }

        public Builder setStringValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setStringValue(str);
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((Value) this.instance).clearStringValue();
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setStringValueBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasBytesValue() {
            return ((Value) this.instance).hasBytesValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getBytesValue() {
            return ((Value) this.instance).getBytesValue();
        }

        public Builder setBytesValue(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setBytesValue(byteString);
            return this;
        }

        public Builder clearBytesValue() {
            copyOnWrite();
            ((Value) this.instance).clearBytesValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasReferenceValue() {
            return ((Value) this.instance).hasReferenceValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public String getReferenceValue() {
            return ((Value) this.instance).getReferenceValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getReferenceValueBytes() {
            return ((Value) this.instance).getReferenceValueBytes();
        }

        public Builder setReferenceValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValue(str);
            return this;
        }

        public Builder clearReferenceValue() {
            copyOnWrite();
            ((Value) this.instance).clearReferenceValue();
            return this;
        }

        public Builder setReferenceValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValueBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasGeoPointValue() {
            return ((Value) this.instance).hasGeoPointValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public LatLng getGeoPointValue() {
            return ((Value) this.instance).getGeoPointValue();
        }

        public Builder setGeoPointValue(LatLng latLng) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(latLng);
            return this;
        }

        public Builder setGeoPointValue(LatLng.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(builder.build());
            return this;
        }

        public Builder mergeGeoPointValue(LatLng latLng) {
            copyOnWrite();
            ((Value) this.instance).mergeGeoPointValue(latLng);
            return this;
        }

        public Builder clearGeoPointValue() {
            copyOnWrite();
            ((Value) this.instance).clearGeoPointValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasArrayValue() {
            return ((Value) this.instance).hasArrayValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ArrayValue getArrayValue() {
            return ((Value) this.instance).getArrayValue();
        }

        public Builder setArrayValue(ArrayValue arrayValue) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(arrayValue);
            return this;
        }

        public Builder setArrayValue(ArrayValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(builder.build());
            return this;
        }

        public Builder mergeArrayValue(ArrayValue arrayValue) {
            copyOnWrite();
            ((Value) this.instance).mergeArrayValue(arrayValue);
            return this;
        }

        public Builder clearArrayValue() {
            copyOnWrite();
            ((Value) this.instance).clearArrayValue();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasMapValue() {
            return ((Value) this.instance).hasMapValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public MapValue getMapValue() {
            return ((Value) this.instance).getMapValue();
        }

        public Builder setMapValue(MapValue mapValue) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(mapValue);
            return this;
        }

        public Builder setMapValue(MapValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(builder.build());
            return this;
        }

        public Builder mergeMapValue(MapValue mapValue) {
            copyOnWrite();
            ((Value) this.instance).mergeMapValue(mapValue);
            return this;
        }

        public Builder clearMapValue() {
            copyOnWrite();
            ((Value) this.instance).clearMapValue();
            return this;
        }
    }

    /* renamed from: com.google.firestore.v1.Value$1, reason: invalid class name */
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
                return new Value();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0001\u0000\u0001\u0012\u000b\u0000\u0000\u0000\u0001:\u0000\u00025\u0000\u00033\u0000\u0005Ȼ\u0000\u0006<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b?\u0000\u0011Ȼ\u0000\u0012=\u0000", new Object[]{"valueType_", "valueTypeCase_", MapValue.class, LatLng.class, ArrayValue.class, Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Value> parser = PARSER;
                if (parser == null) {
                    synchronized (Value.class) {
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
        Value value = new Value();
        DEFAULT_INSTANCE = value;
        GeneratedMessageLite.registerDefaultInstance(Value.class, value);
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
