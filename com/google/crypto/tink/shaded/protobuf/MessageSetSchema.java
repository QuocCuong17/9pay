package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.LazyField;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@CheckReturnValue
/* loaded from: classes4.dex */
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite defaultInstance) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.hasExtensions(defaultInstance);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite defaultInstance) {
        return new MessageSetSchema<>(unknownFieldSchema, extensionSchema, defaultInstance);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        MessageLite messageLite = this.defaultInstance;
        if (messageLite instanceof GeneratedMessageLite) {
            return (T) ((GeneratedMessageLite) messageLite).newMutableInstance();
        }
        return (T) messageLite.newBuilderForType().buildPartial();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T message, T other) {
        if (!this.unknownFieldSchema.getFromMessage(message).equals(this.unknownFieldSchema.getFromMessage(other))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = this.unknownFieldSchema.getFromMessage(message).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(message).hashCode() : hashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, T other) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.extensionSchema.getExtensions(message).iterator();
        while (it.hasNext()) {
            Map.Entry<?, Object> next = it.next();
            FieldSet.FieldDescriptorLite fieldDescriptorLite = (FieldSet.FieldDescriptorLite) next.getKey();
            if (fieldDescriptorLite.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof LazyField.LazyEntry) {
                writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), ((LazyField.LazyEntry) next).getField().toByteString());
            } else {
                writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), next.getValue());
            }
        }
        writeUnknownFieldsHelper(this.unknownFieldSchema, message, writer);
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T message, Writer writer) throws IOException {
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(message), writer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cb A[EDGE_INSN: B:24:0x00cb->B:25:0x00cb BREAK  A[LOOP:1: B:10:0x006d->B:18:0x006d], SYNTHETIC] */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLite = UnknownFieldSetLite.newInstance();
            generatedMessageLite.unknownFields = unknownFieldSetLite;
        }
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> ensureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) message).ensureExtensionsAreMutable();
        GeneratedMessageLite.GeneratedExtension generatedExtension = null;
        while (position < limit) {
            int decodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
            int i = registers.int1;
            if (i == WireFormat.MESSAGE_SET_ITEM_TAG) {
                int i2 = 0;
                ByteString byteString = null;
                while (decodeVarint32 < limit) {
                    decodeVarint32 = ArrayDecoders.decodeVarint32(data, decodeVarint32, registers);
                    int i3 = registers.int1;
                    int tagFieldNumber = WireFormat.getTagFieldNumber(i3);
                    int tagWireType = WireFormat.getTagWireType(i3);
                    if (tagFieldNumber != 2) {
                        if (tagFieldNumber == 3) {
                            if (generatedExtension != null) {
                                decodeVarint32 = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), data, decodeVarint32, limit, registers);
                                ensureExtensionsAreMutable.setField(generatedExtension.descriptor, registers.object1);
                            } else if (tagWireType == 2) {
                                decodeVarint32 = ArrayDecoders.decodeBytes(data, decodeVarint32, registers);
                                byteString = (ByteString) registers.object1;
                            }
                        }
                        if (i3 != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                        } else {
                            decodeVarint32 = ArrayDecoders.skipField(i3, data, decodeVarint32, limit, registers);
                        }
                    } else if (tagWireType == 0) {
                        decodeVarint32 = ArrayDecoders.decodeVarint32(data, decodeVarint32, registers);
                        i2 = registers.int1;
                        generatedExtension = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, i2);
                    } else if (i3 != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                    }
                }
                if (byteString != null) {
                    unknownFieldSetLite.storeField(WireFormat.makeTag(i2, 2), byteString);
                }
                position = decodeVarint32;
            } else if (WireFormat.getTagWireType(i) == 2) {
                GeneratedMessageLite.GeneratedExtension generatedExtension2 = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, WireFormat.getTagFieldNumber(i));
                if (generatedExtension2 != null) {
                    position = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension2.getMessageDefaultInstance().getClass()), data, decodeVarint32, limit, registers);
                    ensureExtensionsAreMutable.setField(generatedExtension2.descriptor, registers.object1);
                } else {
                    position = ArrayDecoders.decodeUnknownField(i, data, decodeVarint32, limit, unknownFieldSetLite, registers);
                }
                generatedExtension = generatedExtension2;
            } else {
                position = ArrayDecoders.skipField(i, data, decodeVarint32, limit, registers);
            }
        }
        if (position != limit) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        UB builderFromMessage = unknownFieldSchema.getBuilderFromMessage(message);
        FieldSet<ET> mutableExtensions = extensionSchema.getMutableExtensions(message);
        do {
            try {
                if (reader.getFieldNumber() == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                unknownFieldSchema.setBuilderToMessage(message, builderFromMessage);
            }
        } while (parseMessageSetItemOrUnknownField(reader, extensionRegistry, extensionSchema, mutableExtensions, unknownFieldSchema, builderFromMessage));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T message) {
        this.unknownFieldSchema.makeImmutable(message);
        this.extensionSchema.makeImmutable(message);
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(Reader reader, ExtensionRegistryLite extensionRegistry, ExtensionSchema<ET> extensionSchema, FieldSet<ET> extensions, UnknownFieldSchema<UT, UB> unknownFieldSchema, UB unknownFields) throws IOException {
        int tag = reader.getTag();
        if (tag != WireFormat.MESSAGE_SET_ITEM_TAG) {
            if (WireFormat.getTagWireType(tag) == 2) {
                Object findExtensionByNumber = extensionSchema.findExtensionByNumber(extensionRegistry, this.defaultInstance, WireFormat.getTagFieldNumber(tag));
                if (findExtensionByNumber != null) {
                    extensionSchema.parseLengthPrefixedMessageSetItem(reader, findExtensionByNumber, extensionRegistry, extensions);
                    return true;
                }
                return unknownFieldSchema.mergeOneFieldFrom(unknownFields, reader);
            }
            return reader.skipField();
        }
        int i = 0;
        Object obj = null;
        ByteString byteString = null;
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            int tag2 = reader.getTag();
            if (tag2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                i = reader.readUInt32();
                obj = extensionSchema.findExtensionByNumber(extensionRegistry, this.defaultInstance, i);
            } else if (tag2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (obj != null) {
                    extensionSchema.parseLengthPrefixedMessageSetItem(reader, obj, extensionRegistry, extensions);
                } else {
                    byteString = reader.readBytes();
                }
            } else if (!reader.skipField()) {
                break;
            }
        }
        if (reader.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        if (byteString != null) {
            if (obj != null) {
                extensionSchema.parseMessageSetItem(byteString, obj, extensionRegistry, extensions);
            } else {
                unknownFieldSchema.addLengthDelimited(unknownFields, i, byteString);
            }
        }
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T message) {
        return this.extensionSchema.getExtensions(message).isInitialized();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T message) {
        int unknownFieldsSerializedSize = getUnknownFieldsSerializedSize(this.unknownFieldSchema, message) + 0;
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(message).getMessageSetSerializedSize() : unknownFieldsSerializedSize;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        return schema.getSerializedSizeAsMessageSet(schema.getFromMessage(message));
    }
}
