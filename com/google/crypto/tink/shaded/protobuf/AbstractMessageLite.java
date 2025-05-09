package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    protected int memoizedHashCode = 0;

    /* loaded from: classes4.dex */
    protected interface InternalOneOfEnum {
        int getNumber();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(newCodedBuilder.getCodedOutput());
            return newCodedBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException(getSerializingExceptionMessage("ByteString"), e);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream newInstance = CodedOutputStream.newInstance(bArr);
            writeTo(newInstance);
            newInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(getSerializingExceptionMessage("byte array"), e);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageLite
    public void writeTo(final OutputStream output) throws IOException {
        CodedOutputStream newInstance = CodedOutputStream.newInstance(output, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(newInstance);
        newInstance.flush();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageLite
    public void writeDelimitedTo(final OutputStream output) throws IOException {
        int serializedSize = getSerializedSize();
        CodedOutputStream newInstance = CodedOutputStream.newInstance(output, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeUInt32SizeNoTag(serializedSize) + serializedSize));
        newInstance.writeUInt32NoTag(serializedSize);
        writeTo(newInstance);
        newInstance.flush();
    }

    int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    void setMemoizedSerializedSize(int size) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSerializedSize(Schema schema) {
        int memoizedSerializedSize = getMemoizedSerializedSize();
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int serializedSize = schema.getSerializedSize(this);
        setMemoizedSerializedSize(serializedSize);
        return serializedSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    private String getSerializingExceptionMessage(String target) {
        return "Serializing " + getClass().getName() + " to a " + target + " threw an IOException (should never happen).";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void checkByteStringIsUtf8(ByteString byteString) throws IllegalArgumentException {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    @Deprecated
    protected static <T> void addAll(final Iterable<T> values, final Collection<? super T> list) {
        Builder.addAll((Iterable) values, (List) list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T> void addAll(final Iterable<T> values, final List<? super T> list) {
        Builder.addAll((Iterable) values, (List) list);
    }

    /* loaded from: classes4.dex */
    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {
        @Override // 
        /* renamed from: clone */
        public abstract BuilderType mo707clone();

        protected abstract BuilderType internalMergeFrom(MessageType message);

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public abstract BuilderType mergeFrom(final CodedInputStream input, final ExtensionRegistryLite extensionRegistry) throws IOException;

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final CodedInputStream input) throws IOException {
            return mergeFrom(input, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final ByteString data) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newCodedInput = data.newCodedInput();
                mergeFrom(newCodedInput);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("ByteString"), e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final ByteString data, final ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newCodedInput = data.newCodedInput();
                mergeFrom(newCodedInput, extensionRegistry);
                newCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("ByteString"), e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final byte[] data) throws InvalidProtocolBufferException {
            return mergeFrom(data, 0, data.length);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final byte[] data, final int off, final int len) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(data, off, len);
                mergeFrom(newInstance);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("byte array"), e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final byte[] data, final ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return mergeFrom(data, 0, data.length, extensionRegistry);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final byte[] data, final int off, final int len, final ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            try {
                CodedInputStream newInstance = CodedInputStream.newInstance(data, off, len);
                mergeFrom(newInstance, extensionRegistry);
                newInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException(getReadingExceptionMessage("byte array"), e2);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final InputStream input) throws IOException {
            CodedInputStream newInstance = CodedInputStream.newInstance(input);
            mergeFrom(newInstance);
            newInstance.checkLastTagWas(0);
            return this;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(final InputStream input, final ExtensionRegistryLite extensionRegistry) throws IOException {
            CodedInputStream newInstance = CodedInputStream.newInstance(input);
            mergeFrom(newInstance, extensionRegistry);
            newInstance.checkLastTagWas(0);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public static final class LimitedInputStream extends FilterInputStream {
            private int limit;

            /* JADX INFO: Access modifiers changed from: package-private */
            public LimitedInputStream(InputStream in, int limit) {
                super(in);
                this.limit = limit;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() throws IOException {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int read = super.read();
                if (read >= 0) {
                    this.limit--;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(final byte[] b, final int off, int len) throws IOException {
                int i = this.limit;
                if (i <= 0) {
                    return -1;
                }
                int read = super.read(b, off, Math.min(len, i));
                if (read >= 0) {
                    this.limit -= read;
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(final long n) throws IOException {
                int skip = (int) super.skip(Math.min(n, this.limit));
                if (skip >= 0) {
                    this.limit -= skip;
                }
                return skip;
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public boolean mergeDelimitedFrom(final InputStream input, final ExtensionRegistryLite extensionRegistry) throws IOException {
            int read = input.read();
            if (read == -1) {
                return false;
            }
            mergeFrom((InputStream) new LimitedInputStream(input, CodedInputStream.readRawVarint32(read, input)), extensionRegistry);
            return true;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public boolean mergeDelimitedFrom(final InputStream input) throws IOException {
            return mergeDelimitedFrom(input, ExtensionRegistryLite.getEmptyRegistry());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(MessageLite messageLite) {
            if (!getDefaultInstanceForType().getClass().isInstance(messageLite)) {
                throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
            }
            return (BuilderType) internalMergeFrom((AbstractMessageLite) messageLite);
        }

        private String getReadingExceptionMessage(String target) {
            return "Reading " + getClass().getName() + " from a " + target + " threw an IOException (should never happen).";
        }

        private static <T> void addAllCheckingNulls(Iterable<T> values, List<? super T> list) {
            if ((list instanceof ArrayList) && (values instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) values).size());
            }
            int size = list.size();
            for (T t : values) {
                if (t == null) {
                    String str = "Element at index " + (list.size() - size) + " is null.";
                    for (int size2 = list.size() - 1; size2 >= size; size2--) {
                        list.remove(size2);
                    }
                    throw new NullPointerException(str);
                }
                list.add(t);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static UninitializedMessageException newUninitializedMessageException(MessageLite message) {
            return new UninitializedMessageException(message);
        }

        @Deprecated
        protected static <T> void addAll(final Iterable<T> values, final Collection<? super T> list) {
            addAll((Iterable) values, (List) list);
        }

        protected static <T> void addAll(final Iterable<T> values, final List<? super T> list) {
            Internal.checkNotNull(values);
            if (values instanceof LazyStringList) {
                List<?> underlyingElements = ((LazyStringList) values).getUnderlyingElements();
                LazyStringList lazyStringList = (LazyStringList) list;
                int size = list.size();
                for (Object obj : underlyingElements) {
                    if (obj == null) {
                        String str = "Element at index " + (lazyStringList.size() - size) + " is null.";
                        for (int size2 = lazyStringList.size() - 1; size2 >= size; size2--) {
                            lazyStringList.remove(size2);
                        }
                        throw new NullPointerException(str);
                    }
                    if (obj instanceof ByteString) {
                        lazyStringList.add((ByteString) obj);
                    } else {
                        lazyStringList.add((String) obj);
                    }
                }
                return;
            }
            if (values instanceof PrimitiveNonBoxingCollection) {
                list.addAll((Collection) values);
            } else {
                addAllCheckingNulls(values, list);
            }
        }
    }
}
