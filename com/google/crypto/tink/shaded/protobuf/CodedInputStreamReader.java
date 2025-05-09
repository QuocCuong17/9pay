package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
@CheckReturnValue
/* loaded from: classes4.dex */
public final class CodedInputStreamReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;
    private static final int NEXT_TAG_UNSET = 0;
    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag = 0;
    private int tag;

    public static CodedInputStreamReader forCodedInput(CodedInputStream input) {
        if (input.wrapper != null) {
            return input.wrapper;
        }
        return new CodedInputStreamReader(input);
    }

    private CodedInputStreamReader(CodedInputStream input) {
        CodedInputStream codedInputStream = (CodedInputStream) Internal.checkNotNull(input, "input");
        this.input = codedInputStream;
        codedInputStream.wrapper = this;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean shouldDiscardUnknownFields() {
        return this.input.shouldDiscardUnknownFields();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int getFieldNumber() throws IOException {
        int i = this.nextTag;
        if (i != 0) {
            this.tag = i;
            this.nextTag = 0;
        } else {
            this.tag = this.input.readTag();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.endGroupTag) {
            return Integer.MAX_VALUE;
        }
        return WireFormat.getTagFieldNumber(i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int getTag() {
        return this.tag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean skipField() throws IOException {
        int i;
        if (this.input.isAtEnd() || (i = this.tag) == this.endGroupTag) {
            return false;
        }
        return this.input.skipField(i);
    }

    private void requireWireType(int requiredWireType) throws IOException {
        if (WireFormat.getTagWireType(this.tag) != requiredWireType) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public double readDouble() throws IOException {
        requireWireType(1);
        return this.input.readDouble();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public float readFloat() throws IOException {
        requireWireType(5);
        return this.input.readFloat();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readUInt64() throws IOException {
        requireWireType(0);
        return this.input.readUInt64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readInt64() throws IOException {
        requireWireType(0);
        return this.input.readInt64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readInt32() throws IOException {
        requireWireType(0);
        return this.input.readInt32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readFixed64() throws IOException {
        requireWireType(1);
        return this.input.readFixed64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readFixed32() throws IOException {
        requireWireType(5);
        return this.input.readFixed32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean readBool() throws IOException {
        requireWireType(0);
        return this.input.readBool();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public String readString() throws IOException {
        requireWireType(2);
        return this.input.readString();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public String readStringRequireUtf8() throws IOException {
        requireWireType(2);
        return this.input.readStringRequireUtf8();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> T readMessage(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(2);
        return (T) readMessage(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> T readMessageBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(2);
        return (T) readMessage(schema, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    @Deprecated
    public <T> T readGroup(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(3);
        return (T) readGroup(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    @Deprecated
    public <T> T readGroupBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(3);
        return (T) readGroup(schema, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void mergeMessageField(T target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(2);
        mergeMessageFieldInternal(target, schema, extensionRegistry);
    }

    private <T> void mergeMessageFieldInternal(T target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int readUInt32 = this.input.readUInt32();
        if (this.input.recursionDepth >= this.input.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int pushLimit = this.input.pushLimit(readUInt32);
        this.input.recursionDepth++;
        schema.mergeFrom(target, this, extensionRegistry);
        this.input.checkLastTagWas(0);
        CodedInputStream codedInputStream = this.input;
        codedInputStream.recursionDepth--;
        this.input.popLimit(pushLimit);
    }

    private <T> T readMessage(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        T newInstance = schema.newInstance();
        mergeMessageFieldInternal(newInstance, schema, extensionRegistry);
        schema.makeImmutable(newInstance);
        return newInstance;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void mergeGroupField(T target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(3);
        mergeGroupFieldInternal(target, schema, extensionRegistry);
    }

    private <T> void mergeGroupFieldInternal(T target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int i = this.endGroupTag;
        this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
        try {
            schema.mergeFrom(target, this, extensionRegistry);
            if (this.tag == this.endGroupTag) {
            } else {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } finally {
            this.endGroupTag = i;
        }
    }

    private <T> T readGroup(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        T newInstance = schema.newInstance();
        mergeGroupFieldInternal(newInstance, schema, extensionRegistry);
        schema.makeImmutable(newInstance);
        return newInstance;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public ByteString readBytes() throws IOException {
        requireWireType(2);
        return this.input.readBytes();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readUInt32() throws IOException {
        requireWireType(0);
        return this.input.readUInt32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readEnum() throws IOException {
        requireWireType(0);
        return this.input.readEnum();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readSFixed32() throws IOException {
        requireWireType(5);
        return this.input.readSFixed32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readSFixed64() throws IOException {
        requireWireType(1);
        return this.input.readSFixed64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readSInt32() throws IOException {
        requireWireType(0);
        return this.input.readSInt32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readSInt64() throws IOException {
        requireWireType(0);
        return this.input.readSInt64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readDoubleList(List<Double> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof DoubleArrayList) {
            DoubleArrayList doubleArrayList = (DoubleArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 1) {
                if (tagWireType == 2) {
                    int readUInt32 = this.input.readUInt32();
                    verifyPackedFixed64Length(readUInt32);
                    int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                    do {
                        doubleArrayList.addDouble(this.input.readDouble());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                doubleArrayList.addDouble(this.input.readDouble());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 1) {
            if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    target.add(Double.valueOf(this.input.readDouble()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Double.valueOf(this.input.readDouble()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFloatList(List<Float> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof FloatArrayList) {
            FloatArrayList floatArrayList = (FloatArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    floatArrayList.addFloat(this.input.readFloat());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                return;
            }
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                floatArrayList.addFloat(this.input.readFloat());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 2) {
            int readUInt322 = this.input.readUInt32();
            verifyPackedFixed32Length(readUInt322);
            int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
            do {
                target.add(Float.valueOf(this.input.readFloat()));
            } while (this.input.getTotalBytesRead() < totalBytesRead2);
            return;
        }
        if (tagWireType2 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Float.valueOf(this.input.readFloat()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readUInt64List(List<Long> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        longArrayList.addLong(this.input.readUInt64());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                longArrayList.addLong(this.input.readUInt64());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Long.valueOf(this.input.readUInt64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readUInt64()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readInt64List(List<Long> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        longArrayList.addLong(this.input.readInt64());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                longArrayList.addLong(this.input.readInt64());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Long.valueOf(this.input.readInt64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readInt64()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readInt32List(List<Integer> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        intArrayList.addInt(this.input.readInt32());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(this.input.readInt32());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readInt32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readInt32()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFixed64List(List<Long> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 1) {
                if (tagWireType == 2) {
                    int readUInt32 = this.input.readUInt32();
                    verifyPackedFixed64Length(readUInt32);
                    int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                    do {
                        longArrayList.addLong(this.input.readFixed64());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                longArrayList.addLong(this.input.readFixed64());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 1) {
            if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    target.add(Long.valueOf(this.input.readFixed64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readFixed64()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFixed32List(List<Integer> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    intArrayList.addInt(this.input.readFixed32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                return;
            }
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(this.input.readFixed32());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 2) {
            int readUInt322 = this.input.readUInt32();
            verifyPackedFixed32Length(readUInt322);
            int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
            do {
                target.add(Integer.valueOf(this.input.readFixed32()));
            } while (this.input.getTotalBytesRead() < totalBytesRead2);
            return;
        }
        if (tagWireType2 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readFixed32()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readBoolList(List<Boolean> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof BooleanArrayList) {
            BooleanArrayList booleanArrayList = (BooleanArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        booleanArrayList.addBoolean(this.input.readBool());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                booleanArrayList.addBoolean(this.input.readBool());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Boolean.valueOf(this.input.readBool()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Boolean.valueOf(this.input.readBool()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readStringList(List<String> target) throws IOException {
        readStringListInternal(target, false);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readStringListRequireUtf8(List<String> target) throws IOException {
        readStringListInternal(target, true);
    }

    public void readStringListInternal(List<String> target, boolean requireUtf8) throws IOException {
        int readTag;
        int readTag2;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        if ((target instanceof LazyStringList) && !requireUtf8) {
            LazyStringList lazyStringList = (LazyStringList) target;
            do {
                lazyStringList.add(readBytes());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        do {
            target.add(requireUtf8 ? readStringRequireUtf8() : readString());
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void readMessageList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
        readMessageList(target, Protobuf.getInstance().schemaFor((Class) targetType), extensionRegistry);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void readMessageList(List<T> target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int readTag;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int i = this.tag;
        do {
            target.add(readMessage(schema, extensionRegistry));
            if (this.input.isAtEnd() || this.nextTag != 0) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == i);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    @Deprecated
    public <T> void readGroupList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
        readGroupList(target, Protobuf.getInstance().schemaFor((Class) targetType), extensionRegistry);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    @Deprecated
    public <T> void readGroupList(List<T> target, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int readTag;
        if (WireFormat.getTagWireType(this.tag) != 3) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int i = this.tag;
        do {
            target.add(readGroup(schema, extensionRegistry));
            if (this.input.isAtEnd() || this.nextTag != 0) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == i);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readBytesList(List<ByteString> target) throws IOException {
        int readTag;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(readBytes());
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readUInt32List(List<Integer> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        intArrayList.addInt(this.input.readUInt32());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(this.input.readUInt32());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readUInt32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readUInt32()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readEnumList(List<Integer> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        intArrayList.addInt(this.input.readEnum());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(this.input.readEnum());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readEnum()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readEnum()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSFixed32List(List<Integer> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int readUInt32 = this.input.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                do {
                    intArrayList.addInt(this.input.readSFixed32());
                } while (this.input.getTotalBytesRead() < totalBytesRead);
                return;
            }
            if (tagWireType != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(this.input.readSFixed32());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 2) {
            int readUInt322 = this.input.readUInt32();
            verifyPackedFixed32Length(readUInt322);
            int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
            do {
                target.add(Integer.valueOf(this.input.readSFixed32()));
            } while (this.input.getTotalBytesRead() < totalBytesRead2);
            return;
        }
        if (tagWireType2 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readSFixed32()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSFixed64List(List<Long> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 1) {
                if (tagWireType == 2) {
                    int readUInt32 = this.input.readUInt32();
                    verifyPackedFixed64Length(readUInt32);
                    int totalBytesRead = this.input.getTotalBytesRead() + readUInt32;
                    do {
                        longArrayList.addLong(this.input.readSFixed64());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                longArrayList.addLong(this.input.readSFixed64());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 1) {
            if (tagWireType2 == 2) {
                int readUInt322 = this.input.readUInt32();
                verifyPackedFixed64Length(readUInt322);
                int totalBytesRead2 = this.input.getTotalBytesRead() + readUInt322;
                do {
                    target.add(Long.valueOf(this.input.readSFixed64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readSFixed64()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSInt32List(List<Integer> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        intArrayList.addInt(this.input.readSInt32());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                intArrayList.addInt(this.input.readSInt32());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Integer.valueOf(this.input.readSInt32()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Integer.valueOf(this.input.readSInt32()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSInt64List(List<Long> target) throws IOException {
        int readTag;
        int readTag2;
        if (target instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    int totalBytesRead = this.input.getTotalBytesRead() + this.input.readUInt32();
                    do {
                        longArrayList.addLong(this.input.readSInt64());
                    } while (this.input.getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                longArrayList.addLong(this.input.readSInt64());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    readTag2 = this.input.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 != 0) {
            if (tagWireType2 == 2) {
                int totalBytesRead2 = this.input.getTotalBytesRead() + this.input.readUInt32();
                do {
                    target.add(Long.valueOf(this.input.readSInt64()));
                } while (this.input.getTotalBytesRead() < totalBytesRead2);
                requirePosition(totalBytesRead2);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(Long.valueOf(this.input.readSInt64()));
            if (this.input.isAtEnd()) {
                return;
            } else {
                readTag = this.input.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    private void verifyPackedFixed64Length(int bytes) throws IOException {
        if ((bytes & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x005c, code lost:
    
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0064, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <K, V> void readMap(Map<K, V> target, MapEntryLite.Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistry) throws IOException {
        requireWireType(2);
        int pushLimit = this.input.pushLimit(this.input.readUInt32());
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (true) {
            try {
                int fieldNumber = getFieldNumber();
                if (fieldNumber == Integer.MAX_VALUE || this.input.isAtEnd()) {
                    break;
                }
                if (fieldNumber == 1) {
                    obj = readField(metadata.keyType, null, null);
                } else if (fieldNumber == 2) {
                    obj2 = readField(metadata.valueType, metadata.defaultValue.getClass(), extensionRegistry);
                } else {
                    try {
                        if (!skipField()) {
                            throw new InvalidProtocolBufferException("Unable to parse map entry.");
                            break;
                        }
                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                        if (!skipField()) {
                            throw new InvalidProtocolBufferException("Unable to parse map entry.");
                        }
                    }
                }
            } finally {
                this.input.popLimit(pushLimit);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private Object readField(WireFormat.FieldType fieldType, Class<?> messageType, ExtensionRegistryLite extensionRegistry) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                return Boolean.valueOf(readBool());
            case 2:
                return readBytes();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(readEnum());
            case 5:
                return Integer.valueOf(readFixed32());
            case 6:
                return Long.valueOf(readFixed64());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(readInt32());
            case 9:
                return Long.valueOf(readInt64());
            case 10:
                return readMessage(messageType, extensionRegistry);
            case 11:
                return Integer.valueOf(readSFixed32());
            case 12:
                return Long.valueOf(readSFixed64());
            case 13:
                return Integer.valueOf(readSInt32());
            case 14:
                return Long.valueOf(readSInt64());
            case 15:
                return readStringRequireUtf8();
            case 16:
                return Integer.valueOf(readUInt32());
            case 17:
                return Long.valueOf(readUInt64());
            default:
                throw new IllegalArgumentException("unsupported field type.");
        }
    }

    private void verifyPackedFixed32Length(int bytes) throws IOException {
        if ((bytes & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    private void requirePosition(int expectedPosition) throws IOException {
        if (this.input.getTotalBytesRead() != expectedPosition) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
