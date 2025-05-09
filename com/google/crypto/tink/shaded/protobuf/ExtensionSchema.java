package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.FieldSet.FieldDescriptorLite;
import java.io.IOException;
import java.util.Map;

@CheckReturnValue
/* loaded from: classes4.dex */
abstract class ExtensionSchema<T extends FieldSet.FieldDescriptorLite<T>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int extensionNumber(Map.Entry<?, ?> extension);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object findExtensionByNumber(ExtensionRegistryLite extensionRegistry, MessageLite defaultInstance, int number);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract FieldSet<T> getExtensions(Object message);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract FieldSet<T> getMutableExtensions(Object message);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean hasExtensions(MessageLite prototype);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void makeImmutable(Object message);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <UT, UB> UB parseExtension(Object containerMessage, Reader reader, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet<T> extensions, UB unknownFields, UnknownFieldSchema<UT, UB> unknownFieldSchema) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void parseLengthPrefixedMessageSetItem(Reader reader, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet<T> extensions) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void parseMessageSetItem(ByteString data, Object extension, ExtensionRegistryLite extensionRegistry, FieldSet<T> extensions) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void serializeExtension(Writer writer, Map.Entry<?, ?> extension) throws IOException;

    abstract void setExtensions(Object message, FieldSet<T> extensions);
}
