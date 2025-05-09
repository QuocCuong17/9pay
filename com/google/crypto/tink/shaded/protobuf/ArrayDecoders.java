package com.google.crypto.tink.shaded.protobuf;

import com.google.common.base.Ascii;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
@CheckReturnValue
/* loaded from: classes4.dex */
public final class ArrayDecoders {
    private ArrayDecoders() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        Registers() {
            this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Registers(ExtensionRegistryLite extensionRegistry) {
            Objects.requireNonNull(extensionRegistry);
            this.extensionRegistry = extensionRegistry;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint32(byte[] data, int position, Registers registers) {
        int i = position + 1;
        byte b = data[position];
        if (b >= 0) {
            registers.int1 = b;
            return i;
        }
        return decodeVarint32(b, data, i, registers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint32(int firstByte, byte[] data, int position, Registers registers) {
        int i = firstByte & 127;
        int i2 = position + 1;
        byte b = data[position];
        if (b >= 0) {
            registers.int1 = i | (b << 7);
            return i2;
        }
        int i3 = i | ((b & Byte.MAX_VALUE) << 7);
        int i4 = i2 + 1;
        byte b2 = data[i2];
        if (b2 >= 0) {
            registers.int1 = i3 | (b2 << 14);
            return i4;
        }
        int i5 = i3 | ((b2 & Byte.MAX_VALUE) << 14);
        int i6 = i4 + 1;
        byte b3 = data[i4];
        if (b3 >= 0) {
            registers.int1 = i5 | (b3 << Ascii.NAK);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 21);
        int i8 = i6 + 1;
        byte b4 = data[i6];
        if (b4 >= 0) {
            registers.int1 = i7 | (b4 << 28);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i10 = i8 + 1;
            if (data[i8] >= 0) {
                registers.int1 = i9;
                return i10;
            }
            i8 = i10;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint64(byte[] data, int position, Registers registers) {
        int i = position + 1;
        long j = data[position];
        if (j >= 0) {
            registers.long1 = j;
            return i;
        }
        return decodeVarint64(j, data, i, registers);
    }

    static int decodeVarint64(long firstByte, byte[] data, int position, Registers registers) {
        int i = position + 1;
        byte b = data[position];
        long j = (firstByte & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i2 = 7;
        while (b < 0) {
            int i3 = i + 1;
            byte b2 = data[i];
            i2 += 7;
            j |= (b2 & Byte.MAX_VALUE) << i2;
            i = i3;
            b = b2;
        }
        registers.long1 = j;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeFixed32(byte[] data, int position) {
        return ((data[position + 3] & 255) << 24) | (data[position] & 255) | ((data[position + 1] & 255) << 8) | ((data[position + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long decodeFixed64(byte[] data, int position) {
        return ((data[position + 7] & 255) << 56) | (data[position] & 255) | ((data[position + 1] & 255) << 8) | ((data[position + 2] & 255) << 16) | ((data[position + 3] & 255) << 24) | ((data[position + 4] & 255) << 32) | ((data[position + 5] & 255) << 40) | ((data[position + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double decodeDouble(byte[] data, int position) {
        return Double.longBitsToDouble(decodeFixed64(data, position));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float decodeFloat(byte[] data, int position) {
        return Float.intBitsToFloat(decodeFixed32(data, position));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeString(byte[] data, int position, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i == 0) {
            registers.object1 = "";
            return decodeVarint32;
        }
        registers.object1 = new String(data, decodeVarint32, i, Internal.UTF_8);
        return decodeVarint32 + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeStringRequireUtf8(byte[] data, int position, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i == 0) {
            registers.object1 = "";
            return decodeVarint32;
        }
        registers.object1 = Utf8.decodeUtf8(data, decodeVarint32, i);
        return decodeVarint32 + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeBytes(byte[] data, int position, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i > data.length - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if (i == 0) {
            registers.object1 = ByteString.EMPTY;
            return decodeVarint32;
        }
        registers.object1 = ByteString.copyFrom(data, decodeVarint32, i);
        return decodeVarint32 + i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeMessageField(Schema schema, byte[] data, int position, int limit, Registers registers) throws IOException {
        Object newInstance = schema.newInstance();
        int mergeMessageField = mergeMessageField(newInstance, schema, data, position, limit, registers);
        schema.makeImmutable(newInstance);
        registers.object1 = newInstance;
        return mergeMessageField;
    }

    static int decodeGroupField(Schema schema, byte[] data, int position, int limit, int endGroup, Registers registers) throws IOException {
        Object newInstance = schema.newInstance();
        int mergeGroupField = mergeGroupField(newInstance, schema, data, position, limit, endGroup, registers);
        schema.makeImmutable(newInstance);
        registers.object1 = newInstance;
        return mergeGroupField;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int mergeMessageField(Object msg, Schema schema, byte[] data, int position, int limit, Registers registers) throws IOException {
        int i = position + 1;
        int i2 = data[position];
        if (i2 < 0) {
            i = decodeVarint32(i2, data, i, registers);
            i2 = registers.int1;
        }
        int i3 = i;
        if (i2 < 0 || i2 > limit - i3) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i4 = i2 + i3;
        schema.mergeFrom(msg, data, i3, i4, registers);
        registers.object1 = msg;
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int mergeGroupField(Object msg, Schema schema, byte[] data, int position, int limit, int endGroup, Registers registers) throws IOException {
        int parseProto2Message = ((MessageSchema) schema).parseProto2Message(msg, data, position, limit, endGroup, registers);
        registers.object1 = msg;
        return parseProto2Message;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint32List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        intArrayList.addInt(registers.int1);
        while (decodeVarint32 < limit) {
            int decodeVarint322 = decodeVarint32(data, decodeVarint32, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(data, decodeVarint322, registers);
            intArrayList.addInt(registers.int1);
        }
        return decodeVarint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint64List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) list;
        int decodeVarint64 = decodeVarint64(data, position, registers);
        longArrayList.addLong(registers.long1);
        while (decodeVarint64 < limit) {
            int decodeVarint32 = decodeVarint32(data, decodeVarint64, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(data, decodeVarint32, registers);
            longArrayList.addLong(registers.long1);
        }
        return decodeVarint64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeFixed32List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) list;
        intArrayList.addInt(decodeFixed32(data, position));
        int i = position + 4;
        while (i < limit) {
            int decodeVarint32 = decodeVarint32(data, i, registers);
            if (tag != registers.int1) {
                break;
            }
            intArrayList.addInt(decodeFixed32(data, decodeVarint32));
            i = decodeVarint32 + 4;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeFixed64List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) list;
        longArrayList.addLong(decodeFixed64(data, position));
        int i = position + 8;
        while (i < limit) {
            int decodeVarint32 = decodeVarint32(data, i, registers);
            if (tag != registers.int1) {
                break;
            }
            longArrayList.addLong(decodeFixed64(data, decodeVarint32));
            i = decodeVarint32 + 8;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeFloatList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        FloatArrayList floatArrayList = (FloatArrayList) list;
        floatArrayList.addFloat(decodeFloat(data, position));
        int i = position + 4;
        while (i < limit) {
            int decodeVarint32 = decodeVarint32(data, i, registers);
            if (tag != registers.int1) {
                break;
            }
            floatArrayList.addFloat(decodeFloat(data, decodeVarint32));
            i = decodeVarint32 + 4;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeDoubleList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        DoubleArrayList doubleArrayList = (DoubleArrayList) list;
        doubleArrayList.addDouble(decodeDouble(data, position));
        int i = position + 8;
        while (i < limit) {
            int decodeVarint32 = decodeVarint32(data, i, registers);
            if (tag != registers.int1) {
                break;
            }
            doubleArrayList.addDouble(decodeDouble(data, decodeVarint32));
            i = decodeVarint32 + 8;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeBoolList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        BooleanArrayList booleanArrayList = (BooleanArrayList) list;
        int decodeVarint64 = decodeVarint64(data, position, registers);
        booleanArrayList.addBoolean(registers.long1 != 0);
        while (decodeVarint64 < limit) {
            int decodeVarint32 = decodeVarint32(data, decodeVarint64, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(data, decodeVarint32, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        return decodeVarint64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeSInt32List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        while (decodeVarint32 < limit) {
            int decodeVarint322 = decodeVarint32(data, decodeVarint32, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(data, decodeVarint322, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        return decodeVarint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeSInt64List(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) list;
        int decodeVarint64 = decodeVarint64(data, position, registers);
        longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        while (decodeVarint64 < limit) {
            int decodeVarint32 = decodeVarint32(data, decodeVarint64, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(data, decodeVarint32, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        return decodeVarint64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedVarint32List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        IntArrayList intArrayList = (IntArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            decodeVarint32 = decodeVarint32(data, decodeVarint32, registers);
            intArrayList.addInt(registers.int1);
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedVarint64List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        LongArrayList longArrayList = (LongArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            decodeVarint32 = decodeVarint64(data, decodeVarint32, registers);
            longArrayList.addLong(registers.long1);
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedFixed32List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        IntArrayList intArrayList = (IntArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            intArrayList.addInt(decodeFixed32(data, decodeVarint32));
            decodeVarint32 += 4;
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedFixed64List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        LongArrayList longArrayList = (LongArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            longArrayList.addLong(decodeFixed64(data, decodeVarint32));
            decodeVarint32 += 8;
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedFloatList(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        FloatArrayList floatArrayList = (FloatArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            floatArrayList.addFloat(decodeFloat(data, decodeVarint32));
            decodeVarint32 += 4;
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedDoubleList(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        DoubleArrayList doubleArrayList = (DoubleArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            doubleArrayList.addDouble(decodeDouble(data, decodeVarint32));
            decodeVarint32 += 8;
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedBoolList(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        BooleanArrayList booleanArrayList = (BooleanArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            decodeVarint32 = decodeVarint64(data, decodeVarint32, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedSInt32List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        IntArrayList intArrayList = (IntArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            decodeVarint32 = decodeVarint32(data, decodeVarint32, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedSInt64List(byte[] data, int position, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        LongArrayList longArrayList = (LongArrayList) list;
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i) {
            decodeVarint32 = decodeVarint64(data, decodeVarint32, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        if (decodeVarint32 == i) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeStringList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i == 0) {
            list.add("");
        } else {
            list.add(new String(data, decodeVarint32, i, Internal.UTF_8));
            decodeVarint32 += i;
        }
        while (decodeVarint32 < limit) {
            int decodeVarint322 = decodeVarint32(data, decodeVarint32, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(data, decodeVarint322, registers);
            int i2 = registers.int1;
            if (i2 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i2 == 0) {
                list.add("");
            } else {
                list.add(new String(data, decodeVarint32, i2, Internal.UTF_8));
                decodeVarint32 += i2;
            }
        }
        return decodeVarint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeStringListRequireUtf8(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i == 0) {
            list.add("");
        } else {
            int i2 = decodeVarint32 + i;
            if (!Utf8.isValidUtf8(data, decodeVarint32, i2)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            list.add(new String(data, decodeVarint32, i, Internal.UTF_8));
            decodeVarint32 = i2;
        }
        while (decodeVarint32 < limit) {
            int decodeVarint322 = decodeVarint32(data, decodeVarint32, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(data, decodeVarint322, registers);
            int i3 = registers.int1;
            if (i3 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i3 == 0) {
                list.add("");
            } else {
                int i4 = decodeVarint32 + i3;
                if (!Utf8.isValidUtf8(data, decodeVarint32, i4)) {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
                list.add(new String(data, decodeVarint32, i3, Internal.UTF_8));
                decodeVarint32 = i4;
            }
        }
        return decodeVarint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeBytesList(int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if (i > data.length - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if (i == 0) {
            list.add(ByteString.EMPTY);
        } else {
            list.add(ByteString.copyFrom(data, decodeVarint32, i));
            decodeVarint32 += i;
        }
        while (decodeVarint32 < limit) {
            int decodeVarint322 = decodeVarint32(data, decodeVarint32, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(data, decodeVarint322, registers);
            int i2 = registers.int1;
            if (i2 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i2 > data.length - decodeVarint32) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i2 == 0) {
                list.add(ByteString.EMPTY);
            } else {
                list.add(ByteString.copyFrom(data, decodeVarint32, i2));
                decodeVarint32 += i2;
            }
        }
        return decodeVarint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeMessageList(Schema<?> schema, int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        int decodeMessageField = decodeMessageField(schema, data, position, limit, registers);
        list.add(registers.object1);
        while (decodeMessageField < limit) {
            int decodeVarint32 = decodeVarint32(data, decodeMessageField, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeMessageField = decodeMessageField(schema, data, decodeVarint32, limit, registers);
            list.add(registers.object1);
        }
        return decodeMessageField;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeGroupList(Schema schema, int tag, byte[] data, int position, int limit, Internal.ProtobufList<?> list, Registers registers) throws IOException {
        int i = (tag & (-8)) | 4;
        int decodeGroupField = decodeGroupField(schema, data, position, limit, i, registers);
        list.add(registers.object1);
        while (decodeGroupField < limit) {
            int decodeVarint32 = decodeVarint32(data, decodeGroupField, registers);
            if (tag != registers.int1) {
                break;
            }
            decodeGroupField = decodeGroupField(schema, data, decodeVarint32, limit, i, registers);
            list.add(registers.object1);
        }
        return decodeGroupField;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeExtensionOrUnknownField(int tag, byte[] data, int position, int limit, Object message, MessageLite defaultInstance, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) throws IOException {
        GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber = registers.extensionRegistry.findLiteExtensionByNumber(defaultInstance, tag >>> 3);
        if (findLiteExtensionByNumber == null) {
            return decodeUnknownField(tag, data, position, limit, MessageSchema.getMutableUnknownFields(message), registers);
        }
        GeneratedMessageLite.ExtendableMessage extendableMessage = (GeneratedMessageLite.ExtendableMessage) message;
        extendableMessage.ensureExtensionsAreMutable();
        return decodeExtension(tag, data, position, limit, extendableMessage, findLiteExtensionByNumber, unknownFieldSchema, registers);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:44:0x0122. Please report as an issue. */
    static int decodeExtension(int tag, byte[] data, int position, int limit, GeneratedMessageLite.ExtendableMessage<?, ?> message, GeneratedMessageLite.GeneratedExtension<?, ?> extension, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) throws IOException {
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet = message.extensions;
        int i = tag >>> 3;
        if (extension.descriptor.isRepeated() && extension.descriptor.isPacked()) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extension.getLiteType().ordinal()]) {
                case 1:
                    DoubleArrayList doubleArrayList = new DoubleArrayList();
                    int decodePackedDoubleList = decodePackedDoubleList(data, position, doubleArrayList, registers);
                    fieldSet.setField(extension.descriptor, doubleArrayList);
                    return decodePackedDoubleList;
                case 2:
                    FloatArrayList floatArrayList = new FloatArrayList();
                    int decodePackedFloatList = decodePackedFloatList(data, position, floatArrayList, registers);
                    fieldSet.setField(extension.descriptor, floatArrayList);
                    return decodePackedFloatList;
                case 3:
                case 4:
                    LongArrayList longArrayList = new LongArrayList();
                    int decodePackedVarint64List = decodePackedVarint64List(data, position, longArrayList, registers);
                    fieldSet.setField(extension.descriptor, longArrayList);
                    return decodePackedVarint64List;
                case 5:
                case 6:
                    IntArrayList intArrayList = new IntArrayList();
                    int decodePackedVarint32List = decodePackedVarint32List(data, position, intArrayList, registers);
                    fieldSet.setField(extension.descriptor, intArrayList);
                    return decodePackedVarint32List;
                case 7:
                case 8:
                    LongArrayList longArrayList2 = new LongArrayList();
                    int decodePackedFixed64List = decodePackedFixed64List(data, position, longArrayList2, registers);
                    fieldSet.setField(extension.descriptor, longArrayList2);
                    return decodePackedFixed64List;
                case 9:
                case 10:
                    IntArrayList intArrayList2 = new IntArrayList();
                    int decodePackedFixed32List = decodePackedFixed32List(data, position, intArrayList2, registers);
                    fieldSet.setField(extension.descriptor, intArrayList2);
                    return decodePackedFixed32List;
                case 11:
                    BooleanArrayList booleanArrayList = new BooleanArrayList();
                    int decodePackedBoolList = decodePackedBoolList(data, position, booleanArrayList, registers);
                    fieldSet.setField(extension.descriptor, booleanArrayList);
                    return decodePackedBoolList;
                case 12:
                    IntArrayList intArrayList3 = new IntArrayList();
                    int decodePackedSInt32List = decodePackedSInt32List(data, position, intArrayList3, registers);
                    fieldSet.setField(extension.descriptor, intArrayList3);
                    return decodePackedSInt32List;
                case 13:
                    LongArrayList longArrayList3 = new LongArrayList();
                    int decodePackedSInt64List = decodePackedSInt64List(data, position, longArrayList3, registers);
                    fieldSet.setField(extension.descriptor, longArrayList3);
                    return decodePackedSInt64List;
                case 14:
                    IntArrayList intArrayList4 = new IntArrayList();
                    int decodePackedVarint32List2 = decodePackedVarint32List(data, position, intArrayList4, registers);
                    SchemaUtil.filterUnknownEnumList((Object) message, i, (List<Integer>) intArrayList4, extension.descriptor.getEnumType(), (Object) null, (UnknownFieldSchema<UT, Object>) unknownFieldSchema);
                    fieldSet.setField(extension.descriptor, intArrayList4);
                    return decodePackedVarint32List2;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + extension.descriptor.getLiteType());
            }
        }
        Object obj = null;
        if (extension.getLiteType() == WireFormat.FieldType.ENUM) {
            position = decodeVarint32(data, position, registers);
            if (extension.descriptor.getEnumType().findValueByNumber(registers.int1) == null) {
                SchemaUtil.storeUnknownEnum(message, i, registers.int1, null, unknownFieldSchema);
                return position;
            }
            obj = Integer.valueOf(registers.int1);
        } else {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[extension.getLiteType().ordinal()]) {
                case 1:
                    obj = Double.valueOf(decodeDouble(data, position));
                    position += 8;
                    break;
                case 2:
                    obj = Float.valueOf(decodeFloat(data, position));
                    position += 4;
                    break;
                case 3:
                case 4:
                    position = decodeVarint64(data, position, registers);
                    obj = Long.valueOf(registers.long1);
                    break;
                case 5:
                case 6:
                    position = decodeVarint32(data, position, registers);
                    obj = Integer.valueOf(registers.int1);
                    break;
                case 7:
                case 8:
                    obj = Long.valueOf(decodeFixed64(data, position));
                    position += 8;
                    break;
                case 9:
                case 10:
                    obj = Integer.valueOf(decodeFixed32(data, position));
                    position += 4;
                    break;
                case 11:
                    position = decodeVarint64(data, position, registers);
                    obj = Boolean.valueOf(registers.long1 != 0);
                    break;
                case 12:
                    position = decodeVarint32(data, position, registers);
                    obj = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                    break;
                case 13:
                    position = decodeVarint64(data, position, registers);
                    obj = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                    break;
                case 14:
                    throw new IllegalStateException("Shouldn't reach here.");
                case 15:
                    position = decodeBytes(data, position, registers);
                    obj = registers.object1;
                    break;
                case 16:
                    position = decodeString(data, position, registers);
                    obj = registers.object1;
                    break;
                case 17:
                    int i2 = (i << 3) | 4;
                    Schema schemaFor = Protobuf.getInstance().schemaFor((Class) extension.getMessageDefaultInstance().getClass());
                    if (extension.isRepeated()) {
                        int decodeGroupField = decodeGroupField(schemaFor, data, position, limit, i2, registers);
                        fieldSet.addRepeatedField(extension.descriptor, registers.object1);
                        return decodeGroupField;
                    }
                    Object field = fieldSet.getField(extension.descriptor);
                    if (field == null) {
                        field = schemaFor.newInstance();
                        fieldSet.setField(extension.descriptor, field);
                    }
                    return mergeGroupField(field, schemaFor, data, position, limit, i2, registers);
                case 18:
                    Schema schemaFor2 = Protobuf.getInstance().schemaFor((Class) extension.getMessageDefaultInstance().getClass());
                    if (extension.isRepeated()) {
                        int decodeMessageField = decodeMessageField(schemaFor2, data, position, limit, registers);
                        fieldSet.addRepeatedField(extension.descriptor, registers.object1);
                        return decodeMessageField;
                    }
                    Object field2 = fieldSet.getField(extension.descriptor);
                    if (field2 == null) {
                        field2 = schemaFor2.newInstance();
                        fieldSet.setField(extension.descriptor, field2);
                    }
                    return mergeMessageField(field2, schemaFor2, data, position, limit, registers);
            }
        }
        if (extension.isRepeated()) {
            fieldSet.addRepeatedField(extension.descriptor, obj);
        } else {
            fieldSet.setField(extension.descriptor, obj);
        }
        return position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.ArrayDecoders$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeUnknownField(int tag, byte[] data, int position, int limit, UnknownFieldSetLite unknownFields, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(tag) == 0) {
            throw InvalidProtocolBufferException.invalidTag();
        }
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            int decodeVarint64 = decodeVarint64(data, position, registers);
            unknownFields.storeField(tag, Long.valueOf(registers.long1));
            return decodeVarint64;
        }
        if (tagWireType == 1) {
            unknownFields.storeField(tag, Long.valueOf(decodeFixed64(data, position)));
            return position + 8;
        }
        if (tagWireType == 2) {
            int decodeVarint32 = decodeVarint32(data, position, registers);
            int i = registers.int1;
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            if (i > data.length - decodeVarint32) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i == 0) {
                unknownFields.storeField(tag, ByteString.EMPTY);
            } else {
                unknownFields.storeField(tag, ByteString.copyFrom(data, decodeVarint32, i));
            }
            return decodeVarint32 + i;
        }
        if (tagWireType != 3) {
            if (tagWireType == 5) {
                unknownFields.storeField(tag, Integer.valueOf(decodeFixed32(data, position)));
                return position + 4;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        int i2 = (tag & (-8)) | 4;
        int i3 = 0;
        while (true) {
            if (position >= limit) {
                break;
            }
            int decodeVarint322 = decodeVarint32(data, position, registers);
            int i4 = registers.int1;
            if (i4 == i2) {
                i3 = i4;
                position = decodeVarint322;
                break;
            }
            i3 = i4;
            position = decodeUnknownField(i4, data, decodeVarint322, limit, newInstance, registers);
        }
        if (position > limit || i3 != i2) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        unknownFields.storeField(tag, newInstance);
        return position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int skipField(int tag, byte[] data, int position, int limit, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(tag) == 0) {
            throw InvalidProtocolBufferException.invalidTag();
        }
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            return decodeVarint64(data, position, registers);
        }
        if (tagWireType == 1) {
            return position + 8;
        }
        if (tagWireType == 2) {
            return decodeVarint32(data, position, registers) + registers.int1;
        }
        if (tagWireType != 3) {
            if (tagWireType == 5) {
                return position + 4;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }
        int i = (tag & (-8)) | 4;
        int i2 = 0;
        while (position < limit) {
            position = decodeVarint32(data, position, registers);
            i2 = registers.int1;
            if (i2 == i) {
                break;
            }
            position = skipField(i2, data, position, limit, registers);
        }
        if (position > limit || i2 != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position;
    }
}
