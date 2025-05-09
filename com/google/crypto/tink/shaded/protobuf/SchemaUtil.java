package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.Internal;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
@CheckReturnValue
/* loaded from: classes4.dex */
public final class SchemaUtil {
    private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;
    private static final Class<?> GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
    private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
    private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    public static boolean shouldUseTableSwitch(int lo, int hi, int numFields) {
        if (hi < 40) {
            return true;
        }
        long j = numFields;
        return ((((long) hi) - ((long) lo)) + 1) + 9 <= ((2 * j) + 3) + ((j + 3) * 3);
    }

    private SchemaUtil() {
    }

    public static void requireGeneratedMessage(Class<?> messageType) {
        Class<?> cls;
        if (!GeneratedMessageLite.class.isAssignableFrom(messageType) && (cls = GENERATED_MESSAGE_CLASS) != null && !cls.isAssignableFrom(messageType)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
    }

    public static void writeDouble(int fieldNumber, double value, Writer writer) throws IOException {
        if (Double.doubleToRawLongBits(value) != 0) {
            writer.writeDouble(fieldNumber, value);
        }
    }

    public static void writeFloat(int fieldNumber, float value, Writer writer) throws IOException {
        if (Float.floatToRawIntBits(value) != 0) {
            writer.writeFloat(fieldNumber, value);
        }
    }

    public static void writeInt64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeInt64(fieldNumber, value);
        }
    }

    public static void writeUInt64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeUInt64(fieldNumber, value);
        }
    }

    public static void writeSInt64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSInt64(fieldNumber, value);
        }
    }

    public static void writeFixed64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeFixed64(fieldNumber, value);
        }
    }

    public static void writeSFixed64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSFixed64(fieldNumber, value);
        }
    }

    public static void writeInt32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeInt32(fieldNumber, value);
        }
    }

    public static void writeUInt32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeUInt32(fieldNumber, value);
        }
    }

    public static void writeSInt32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSInt32(fieldNumber, value);
        }
    }

    public static void writeFixed32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeFixed32(fieldNumber, value);
        }
    }

    public static void writeSFixed32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSFixed32(fieldNumber, value);
        }
    }

    public static void writeEnum(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeEnum(fieldNumber, value);
        }
    }

    public static void writeBool(int fieldNumber, boolean value, Writer writer) throws IOException {
        if (value) {
            writer.writeBool(fieldNumber, true);
        }
    }

    public static void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writeStringInternal(fieldNumber, (String) value, writer);
        } else {
            writeBytes(fieldNumber, (ByteString) value, writer);
        }
    }

    private static void writeStringInternal(int fieldNumber, String value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeString(fieldNumber, value);
    }

    public static void writeBytes(int fieldNumber, ByteString value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeBytes(fieldNumber, value);
    }

    public static void writeMessage(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value != null) {
            writer.writeMessage(fieldNumber, value);
        }
    }

    public static void writeDoubleList(int fieldNumber, List<Double> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeDoubleList(fieldNumber, value, packed);
    }

    public static void writeFloatList(int fieldNumber, List<Float> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeFloatList(fieldNumber, value, packed);
    }

    public static void writeInt64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeInt64List(fieldNumber, value, packed);
    }

    public static void writeUInt64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeUInt64List(fieldNumber, value, packed);
    }

    public static void writeSInt64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeSInt64List(fieldNumber, value, packed);
    }

    public static void writeFixed64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeFixed64List(fieldNumber, value, packed);
    }

    public static void writeSFixed64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeSFixed64List(fieldNumber, value, packed);
    }

    public static void writeInt32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeInt32List(fieldNumber, value, packed);
    }

    public static void writeUInt32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeUInt32List(fieldNumber, value, packed);
    }

    public static void writeSInt32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeSInt32List(fieldNumber, value, packed);
    }

    public static void writeFixed32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeFixed32List(fieldNumber, value, packed);
    }

    public static void writeSFixed32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeSFixed32List(fieldNumber, value, packed);
    }

    public static void writeEnumList(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeEnumList(fieldNumber, value, packed);
    }

    public static void writeBoolList(int fieldNumber, List<Boolean> value, Writer writer, boolean packed) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeBoolList(fieldNumber, value, packed);
    }

    public static void writeStringList(int fieldNumber, List<String> value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeStringList(fieldNumber, value);
    }

    public static void writeBytesList(int fieldNumber, List<ByteString> value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeBytesList(fieldNumber, value);
    }

    public static void writeMessageList(int fieldNumber, List<?> value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeMessageList(fieldNumber, value);
    }

    public static void writeMessageList(int fieldNumber, List<?> value, Writer writer, Schema schema) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeMessageList(fieldNumber, value, schema);
    }

    public static void writeLazyFieldList(int fieldNumber, List<?> value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        Iterator<?> it = value.iterator();
        while (it.hasNext()) {
            ((LazyFieldLite) it.next()).writeTo(writer, fieldNumber);
        }
    }

    public static void writeGroupList(int fieldNumber, List<?> value, Writer writer) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeGroupList(fieldNumber, value);
    }

    public static void writeGroupList(int fieldNumber, List<?> value, Writer writer, Schema schema) throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writer.writeGroupList(fieldNumber, value, schema);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeInt64ListNoTag(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt64SizeNoTag(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeInt64List(int fieldNumber, List<Long> list, boolean packed) {
        if (list.size() == 0) {
            return 0;
        }
        int computeSizeInt64ListNoTag = computeSizeInt64ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeInt64ListNoTag);
        }
        return computeSizeInt64ListNoTag + (list.size() * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeUInt64ListNoTag(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeUInt64List(int fieldNumber, List<Long> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeUInt64ListNoTag = computeSizeUInt64ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeUInt64ListNoTag);
        }
        return computeSizeUInt64ListNoTag + (size * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeSInt64ListNoTag(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt64SizeNoTag(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeSInt64List(int fieldNumber, List<Long> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeSInt64ListNoTag = computeSizeSInt64ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeSInt64ListNoTag);
        }
        return computeSizeSInt64ListNoTag + (size * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeEnumListNoTag(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeEnumSizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeEnumSizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeEnumList(int fieldNumber, List<Integer> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeEnumListNoTag = computeSizeEnumListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeEnumListNoTag);
        }
        return computeSizeEnumListNoTag + (size * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeInt32ListNoTag(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeInt32List(int fieldNumber, List<Integer> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeInt32ListNoTag = computeSizeInt32ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeInt32ListNoTag);
        }
        return computeSizeInt32ListNoTag + (size * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeUInt32ListNoTag(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt32SizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeUInt32List(int fieldNumber, List<Integer> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeUInt32ListNoTag = computeSizeUInt32ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeUInt32ListNoTag);
        }
        return computeSizeUInt32ListNoTag + (size * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeSInt32ListNoTag(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt32SizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeSInt32List(int fieldNumber, List<Integer> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeSizeSInt32ListNoTag = computeSizeSInt32ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSizeSInt32ListNoTag);
        }
        return computeSizeSInt32ListNoTag + (size * CodedOutputStream.computeTagSize(fieldNumber));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeFixed32ListNoTag(List<?> list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeFixed32List(int fieldNumber, List<?> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size * 4);
        }
        return size * CodedOutputStream.computeFixed32Size(fieldNumber, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeFixed64ListNoTag(List<?> list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeFixed64List(int fieldNumber, List<?> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size * 8);
        }
        return size * CodedOutputStream.computeFixed64Size(fieldNumber, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeBoolListNoTag(List<?> list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeBoolList(int fieldNumber, List<?> list, boolean packed) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return size * CodedOutputStream.computeBoolSize(fieldNumber, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeStringList(int fieldNumber, List<?> list) {
        int computeStringSizeNoTag;
        int computeStringSizeNoTag2;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(fieldNumber) * size;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i < size) {
                Object raw = lazyStringList.getRaw(i);
                if (raw instanceof ByteString) {
                    computeStringSizeNoTag2 = CodedOutputStream.computeBytesSizeNoTag((ByteString) raw);
                } else {
                    computeStringSizeNoTag2 = CodedOutputStream.computeStringSizeNoTag((String) raw);
                }
                computeTagSize += computeStringSizeNoTag2;
                i++;
            }
        } else {
            while (i < size) {
                Object obj = list.get(i);
                if (obj instanceof ByteString) {
                    computeStringSizeNoTag = CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
                } else {
                    computeStringSizeNoTag = CodedOutputStream.computeStringSizeNoTag((String) obj);
                }
                computeTagSize += computeStringSizeNoTag;
                i++;
            }
        }
        return computeTagSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeMessage(int fieldNumber, Object value, Schema schema) {
        if (value instanceof LazyFieldLite) {
            return CodedOutputStream.computeLazyFieldSize(fieldNumber, (LazyFieldLite) value);
        }
        return CodedOutputStream.computeMessageSize(fieldNumber, (MessageLite) value, schema);
    }

    static int computeSizeMessageList(int fieldNumber, List<?> list) {
        int computeMessageSizeNoTag;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(fieldNumber) * size;
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj instanceof LazyFieldLite) {
                computeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj);
            } else {
                computeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj);
            }
            computeTagSize += computeMessageSizeNoTag;
        }
        return computeTagSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeMessageList(int fieldNumber, List<?> list, Schema schema) {
        int computeMessageSizeNoTag;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = CodedOutputStream.computeTagSize(fieldNumber) * size;
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj instanceof LazyFieldLite) {
                computeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj);
            } else {
                computeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj, schema);
            }
            computeTagSize += computeMessageSizeNoTag;
        }
        return computeTagSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeByteStringList(int fieldNumber, List<ByteString> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int computeTagSize = size * CodedOutputStream.computeTagSize(fieldNumber);
        for (int i = 0; i < list.size(); i++) {
            computeTagSize += CodedOutputStream.computeBytesSizeNoTag(list.get(i));
        }
        return computeTagSize;
    }

    static int computeSizeGroupList(int fieldNumber, List<MessageLite> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream.computeGroupSize(fieldNumber, list.get(i2));
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeSizeGroupList(int fieldNumber, List<MessageLite> list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += CodedOutputStream.computeGroupSize(fieldNumber, list.get(i2), schema);
        }
        return i;
    }

    public static boolean shouldUseTableSwitch(FieldInfo[] fields) {
        if (fields.length == 0) {
            return false;
        }
        return shouldUseTableSwitch(fields[0].getFieldNumber(), fields[fields.length - 1].getFieldNumber(), fields.length);
    }

    public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
        return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
        return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean proto3) {
        try {
            Class<?> unknownFieldSetSchemaClass = getUnknownFieldSetSchemaClass();
            if (unknownFieldSetSchemaClass == null) {
                return null;
            }
            return (UnknownFieldSchema) unknownFieldSetSchemaClass.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(proto3));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> getGeneratedMessageClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> getUnknownFieldSetSchemaClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static Object getMapDefaultEntry(Class<?> clazz, String name) {
        try {
            java.lang.reflect.Field[] declaredFields = Class.forName(clazz.getName() + "$" + toCamelCase(name, true) + "DefaultEntryHolder").getDeclaredFields();
            if (declaredFields.length != 1) {
                throw new IllegalStateException("Unable to look up map field default entry holder class for " + name + " in " + clazz.getName());
            }
            return UnsafeUtil.getStaticObject(declaredFields[0]);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    static String toCamelCase(String name, boolean capNext) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char charAt = name.charAt(i);
            if ('a' > charAt || charAt > 'z') {
                if ('A' > charAt || charAt > 'Z') {
                    if ('0' <= charAt && charAt <= '9') {
                        sb.append(charAt);
                    }
                    capNext = true;
                } else if (i == 0 && !capNext) {
                    sb.append((char) (charAt + ' '));
                } else {
                    sb.append(charAt);
                }
            } else if (capNext) {
                sb.append((char) (charAt - ' '));
            } else {
                sb.append(charAt);
            }
            capNext = false;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean safeEquals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void mergeMap(MapFieldSchema mapFieldSchema, T message, T o, long offset) {
        UnsafeUtil.putObject(message, offset, mapFieldSchema.mergeFrom(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(o, offset)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends FieldSet.FieldDescriptorLite<FT>> void mergeExtensions(ExtensionSchema<FT> schema, T message, T other) {
        FieldSet<FT> extensions = schema.getExtensions(other);
        if (extensions.isEmpty()) {
            return;
        }
        schema.getMutableExtensions(message).mergeFrom(extensions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void mergeUnknownFields(UnknownFieldSchema<UT, UB> schema, T message, T other) {
        schema.setToMessage(message, schema.merge(schema.getFromMessage(message), schema.getFromMessage(other)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB filterUnknownEnumList(Object obj, int i, List<Integer> list, Internal.EnumLiteMap<?> enumLiteMap, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumLiteMap == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (enumLiteMap.findValueByNumber(intValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) storeUnknownEnum(obj, i, intValue, ub, unknownFieldSchema);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (enumLiteMap.findValueByNumber(intValue2) == null) {
                    ub = (UB) storeUnknownEnum(obj, i, intValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB filterUnknownEnumList(Object obj, int i, List<Integer> list, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumVerifier == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (enumVerifier.isInRange(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) storeUnknownEnum(obj, i, intValue, ub, unknownFieldSchema);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!enumVerifier.isInRange(intValue2)) {
                    ub = (UB) storeUnknownEnum(obj, i, intValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB storeUnknownEnum(Object obj, int i, int i2, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (ub == null) {
            ub = unknownFieldSchema.getBuilderFromMessage(obj);
        }
        unknownFieldSchema.addVarint(ub, i, i2);
        return ub;
    }
}
