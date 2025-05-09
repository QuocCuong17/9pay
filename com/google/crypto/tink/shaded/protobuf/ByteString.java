package com.google.crypto.tink.shaded.protobuf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

@CheckReturnValue
/* loaded from: classes4.dex */
public abstract class ByteString implements Iterable<Byte>, Serializable {
    static final int CONCATENATE_BY_COPY_SIZE = 128;
    public static final ByteString EMPTY = new LiteralByteString(Internal.EMPTY_BYTE_ARRAY);
    static final int MAX_READ_FROM_CHUNK_SIZE = 8192;
    static final int MIN_READ_FROM_CHUNK_SIZE = 256;
    private static final int UNSIGNED_BYTE_MASK = 255;
    private static final Comparator<ByteString> UNSIGNED_LEXICOGRAPHICAL_COMPARATOR;
    private static final ByteArrayCopier byteArrayCopier;
    private int hash = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public interface ByteArrayCopier {
        byte[] copyFrom(byte[] bytes, int offset, int size);
    }

    /* loaded from: classes4.dex */
    public interface ByteIterator extends Iterator<Byte> {
        byte nextByte();
    }

    private static int hexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if (c < 'A' || c > 'F') {
            c2 = 'a';
            if (c < 'a' || c > 'f') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int toInt(byte value) {
        return value & 255;
    }

    public abstract ByteBuffer asReadOnlyByteBuffer();

    public abstract List<ByteBuffer> asReadOnlyByteBufferList();

    public abstract byte byteAt(int index);

    public abstract void copyTo(ByteBuffer target);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy);

    public abstract boolean equals(Object o);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getTreeDepth();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte internalByteAt(int index);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBalanced();

    public abstract boolean isValidUtf8();

    public abstract CodedInputStream newCodedInput();

    public abstract InputStream newInput();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int partialHash(int h, int offset, int length);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int partialIsValidUtf8(int state, int offset, int length);

    public abstract int size();

    public abstract ByteString substring(int beginIndex, int endIndex);

    protected abstract String toStringInternal(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeTo(ByteOutput byteOutput) throws IOException;

    public abstract void writeTo(OutputStream out) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeToInternal(OutputStream out, int sourceOffset, int numberToWrite) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeToReverse(ByteOutput byteOutput) throws IOException;

    static {
        byteArrayCopier = Android.isOnAndroidDevice() ? new SystemByteArrayCopier() : new ArraysByteArrayCopier();
        UNSIGNED_LEXICOGRAPHICAL_COMPARATOR = new Comparator<ByteString>() { // from class: com.google.crypto.tink.shaded.protobuf.ByteString.2
            /* JADX WARN: Type inference failed for: r0v0, types: [com.google.crypto.tink.shaded.protobuf.ByteString$ByteIterator] */
            /* JADX WARN: Type inference failed for: r1v0, types: [com.google.crypto.tink.shaded.protobuf.ByteString$ByteIterator] */
            @Override // java.util.Comparator
            public int compare(ByteString former, ByteString latter) {
                ?? iterator2 = former.iterator2();
                ?? iterator22 = latter.iterator2();
                while (iterator2.hasNext() && iterator22.hasNext()) {
                    int compareTo = Integer.valueOf(ByteString.toInt(iterator2.nextByte())).compareTo(Integer.valueOf(ByteString.toInt(iterator22.nextByte())));
                    if (compareTo != 0) {
                        return compareTo;
                    }
                }
                return Integer.valueOf(former.size()).compareTo(Integer.valueOf(latter.size()));
            }
        };
    }

    /* loaded from: classes4.dex */
    private static final class SystemByteArrayCopier implements ByteArrayCopier {
        private SystemByteArrayCopier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.ByteArrayCopier
        public byte[] copyFrom(byte[] bytes, int offset, int size) {
            byte[] bArr = new byte[size];
            System.arraycopy(bytes, offset, bArr, 0, size);
            return bArr;
        }
    }

    /* loaded from: classes4.dex */
    private static final class ArraysByteArrayCopier implements ByteArrayCopier {
        private ArraysByteArrayCopier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.ByteArrayCopier
        public byte[] copyFrom(byte[] bytes, int offset, int size) {
            return Arrays.copyOfRange(bytes, offset, size + offset);
        }
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public Iterator<Byte> iterator2() {
        return new AbstractByteIterator() { // from class: com.google.crypto.tink.shaded.protobuf.ByteString.1
            private final int limit;
            private int position = 0;

            {
                this.limit = ByteString.this.size();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.position < this.limit;
            }

            @Override // com.google.crypto.tink.shaded.protobuf.ByteString.ByteIterator
            public byte nextByte() {
                int i = this.position;
                if (i >= this.limit) {
                    throw new NoSuchElementException();
                }
                this.position = i + 1;
                return ByteString.this.internalByteAt(i);
            }
        };
    }

    /* loaded from: classes4.dex */
    static abstract class AbstractByteIterator implements ByteIterator {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public final Byte next() {
            return Byte.valueOf(nextByte());
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public static final ByteString empty() {
        return EMPTY;
    }

    private static int extractHexDigit(String hexString, int index) {
        int hexDigit = hexDigit(hexString.charAt(index));
        if (hexDigit != -1) {
            return hexDigit;
        }
        throw new NumberFormatException("Invalid hexString " + hexString + " must only contain [0-9a-fA-F] but contained " + hexString.charAt(index) + " at index " + index);
    }

    public static Comparator<ByteString> unsignedLexicographicalComparator() {
        return UNSIGNED_LEXICOGRAPHICAL_COMPARATOR;
    }

    public final ByteString substring(int beginIndex) {
        return substring(beginIndex, size());
    }

    public final boolean startsWith(ByteString prefix) {
        return size() >= prefix.size() && substring(0, prefix.size()).equals(prefix);
    }

    public final boolean endsWith(ByteString suffix) {
        return size() >= suffix.size() && substring(size() - suffix.size()).equals(suffix);
    }

    public static ByteString fromHex(String hexString) {
        if (hexString.length() % 2 != 0) {
            throw new NumberFormatException("Invalid hexString " + hexString + " of length " + hexString.length() + " must be even.");
        }
        int length = hexString.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (extractHexDigit(hexString, i2 + 1) | (extractHexDigit(hexString, i2) << 4));
        }
        return new LiteralByteString(bArr);
    }

    public static ByteString copyFrom(byte[] bytes, int offset, int size) {
        checkRange(offset, offset + size, bytes.length);
        return new LiteralByteString(byteArrayCopier.copyFrom(bytes, offset, size));
    }

    public static ByteString copyFrom(byte[] bytes) {
        return copyFrom(bytes, 0, bytes.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString wrap(ByteBuffer buffer) {
        if (buffer.hasArray()) {
            return wrap(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
        }
        return new NioByteString(buffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString wrap(byte[] bytes) {
        return new LiteralByteString(bytes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString wrap(byte[] bytes, int offset, int length) {
        return new BoundedByteString(bytes, offset, length);
    }

    public static ByteString copyFrom(ByteBuffer bytes, int size) {
        checkRange(0, size, bytes.remaining());
        byte[] bArr = new byte[size];
        bytes.get(bArr);
        return new LiteralByteString(bArr);
    }

    public static ByteString copyFrom(ByteBuffer bytes) {
        return copyFrom(bytes, bytes.remaining());
    }

    public static ByteString copyFrom(String text, String charsetName) throws UnsupportedEncodingException {
        return new LiteralByteString(text.getBytes(charsetName));
    }

    public static ByteString copyFrom(String text, Charset charset) {
        return new LiteralByteString(text.getBytes(charset));
    }

    public static ByteString copyFromUtf8(String text) {
        return new LiteralByteString(text.getBytes(Internal.UTF_8));
    }

    public static ByteString readFrom(InputStream streamToDrain) throws IOException {
        return readFrom(streamToDrain, 256, 8192);
    }

    public static ByteString readFrom(InputStream streamToDrain, int chunkSize) throws IOException {
        return readFrom(streamToDrain, chunkSize, chunkSize);
    }

    public static ByteString readFrom(InputStream streamToDrain, int minChunkSize, int maxChunkSize) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            ByteString readChunk = readChunk(streamToDrain, minChunkSize);
            if (readChunk != null) {
                arrayList.add(readChunk);
                minChunkSize = Math.min(minChunkSize * 2, maxChunkSize);
            } else {
                return copyFrom(arrayList);
            }
        }
    }

    private static ByteString readChunk(InputStream in, final int chunkSize) throws IOException {
        byte[] bArr = new byte[chunkSize];
        int i = 0;
        while (i < chunkSize) {
            int read = in.read(bArr, i, chunkSize - i);
            if (read == -1) {
                break;
            }
            i += read;
        }
        if (i == 0) {
            return null;
        }
        return copyFrom(bArr, 0, i);
    }

    public final ByteString concat(ByteString other) {
        if (Integer.MAX_VALUE - size() < other.size()) {
            throw new IllegalArgumentException("ByteString would be too long: " + size() + "+" + other.size());
        }
        return RopeByteString.concatenate(this, other);
    }

    public static ByteString copyFrom(Iterable<ByteString> byteStrings) {
        int size;
        if (!(byteStrings instanceof Collection)) {
            size = 0;
            Iterator<ByteString> it = byteStrings.iterator();
            while (it.hasNext()) {
                it.next();
                size++;
            }
        } else {
            size = ((Collection) byteStrings).size();
        }
        if (size == 0) {
            return EMPTY;
        }
        return balancedConcat(byteStrings.iterator(), size);
    }

    private static ByteString balancedConcat(Iterator<ByteString> iterator, int length) {
        if (length < 1) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(length)));
        }
        if (length == 1) {
            return iterator.next();
        }
        int i = length >>> 1;
        return balancedConcat(iterator, i).concat(balancedConcat(iterator, length - i));
    }

    public void copyTo(byte[] target, int offset) {
        copyTo(target, 0, offset, size());
    }

    @Deprecated
    public final void copyTo(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
        checkRange(sourceOffset, sourceOffset + numberToCopy, size());
        checkRange(targetOffset, targetOffset + numberToCopy, target.length);
        if (numberToCopy > 0) {
            copyToInternal(target, sourceOffset, targetOffset, numberToCopy);
        }
    }

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[size];
        copyToInternal(bArr, 0, 0, size);
        return bArr;
    }

    final void writeTo(OutputStream out, int sourceOffset, int numberToWrite) throws IOException {
        checkRange(sourceOffset, sourceOffset + numberToWrite, size());
        if (numberToWrite > 0) {
            writeToInternal(out, sourceOffset, numberToWrite);
        }
    }

    public final String toString(String charsetName) throws UnsupportedEncodingException {
        try {
            return toString(Charset.forName(charsetName));
        } catch (UnsupportedCharsetException e) {
            UnsupportedEncodingException unsupportedEncodingException = new UnsupportedEncodingException(charsetName);
            unsupportedEncodingException.initCause(e);
            throw unsupportedEncodingException;
        }
    }

    public final String toString(Charset charset) {
        return size() == 0 ? "" : toStringInternal(charset);
    }

    public final String toStringUtf8() {
        return toString(Internal.UTF_8);
    }

    /* loaded from: classes4.dex */
    static abstract class LeafByteString extends ByteString {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean equalsRange(ByteString other, int offset, int length);

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        protected final int getTreeDepth() {
            return 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        protected final boolean isBalanced() {
            return true;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator<Byte> iterator() {
            return super.iterator2();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        void writeToReverse(ByteOutput byteOutput) throws IOException {
            writeTo(byteOutput);
        }
    }

    public final int hashCode() {
        int i = this.hash;
        if (i == 0) {
            int size = size();
            i = partialHash(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.hash = i;
        }
        return i;
    }

    public static Output newOutput(int initialCapacity) {
        return new Output(initialCapacity);
    }

    public static Output newOutput() {
        return new Output(128);
    }

    /* loaded from: classes4.dex */
    public static final class Output extends OutputStream {
        private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        private byte[] buffer;
        private int bufferPos;
        private final ArrayList<ByteString> flushedBuffers;
        private int flushedBuffersTotalBytes;
        private final int initialCapacity;

        Output(int initialCapacity) {
            if (initialCapacity < 0) {
                throw new IllegalArgumentException("Buffer size < 0");
            }
            this.initialCapacity = initialCapacity;
            this.flushedBuffers = new ArrayList<>();
            this.buffer = new byte[initialCapacity];
        }

        @Override // java.io.OutputStream
        public synchronized void write(int b) {
            if (this.bufferPos == this.buffer.length) {
                flushFullBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.bufferPos;
            this.bufferPos = i + 1;
            bArr[i] = (byte) b;
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] b, int offset, int length) {
            byte[] bArr = this.buffer;
            int length2 = bArr.length;
            int i = this.bufferPos;
            if (length <= length2 - i) {
                System.arraycopy(b, offset, bArr, i, length);
                this.bufferPos += length;
            } else {
                int length3 = bArr.length - i;
                System.arraycopy(b, offset, bArr, i, length3);
                int i2 = length - length3;
                flushFullBuffer(i2);
                System.arraycopy(b, offset + length3, this.buffer, 0, i2);
                this.bufferPos = i2;
            }
        }

        public synchronized ByteString toByteString() {
            flushLastBuffer();
            return ByteString.copyFrom(this.flushedBuffers);
        }

        private byte[] copyArray(byte[] buffer, int length) {
            byte[] bArr = new byte[length];
            System.arraycopy(buffer, 0, bArr, 0, Math.min(buffer.length, length));
            return bArr;
        }

        public void writeTo(OutputStream out) throws IOException {
            ByteString[] byteStringArr;
            byte[] bArr;
            int i;
            synchronized (this) {
                ArrayList<ByteString> arrayList = this.flushedBuffers;
                byteStringArr = (ByteString[]) arrayList.toArray(new ByteString[arrayList.size()]);
                bArr = this.buffer;
                i = this.bufferPos;
            }
            for (ByteString byteString : byteStringArr) {
                byteString.writeTo(out);
            }
            out.write(copyArray(bArr, i));
        }

        public synchronized int size() {
            return this.flushedBuffersTotalBytes + this.bufferPos;
        }

        public synchronized void reset() {
            this.flushedBuffers.clear();
            this.flushedBuffersTotalBytes = 0;
            this.bufferPos = 0;
        }

        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
        }

        private void flushFullBuffer(int minSize) {
            this.flushedBuffers.add(new LiteralByteString(this.buffer));
            int length = this.flushedBuffersTotalBytes + this.buffer.length;
            this.flushedBuffersTotalBytes = length;
            this.buffer = new byte[Math.max(this.initialCapacity, Math.max(minSize, length >>> 1))];
            this.bufferPos = 0;
        }

        private void flushLastBuffer() {
            int i = this.bufferPos;
            byte[] bArr = this.buffer;
            if (i >= bArr.length) {
                this.flushedBuffers.add(new LiteralByteString(this.buffer));
                this.buffer = EMPTY_BYTE_ARRAY;
            } else if (i > 0) {
                this.flushedBuffers.add(new LiteralByteString(copyArray(bArr, i)));
            }
            this.flushedBuffersTotalBytes += this.bufferPos;
            this.bufferPos = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodedBuilder newCodedBuilder(int size) {
        return new CodedBuilder(size);
    }

    /* loaded from: classes4.dex */
    static final class CodedBuilder {
        private final byte[] buffer;
        private final CodedOutputStream output;

        private CodedBuilder(int size) {
            byte[] bArr = new byte[size];
            this.buffer = bArr;
            this.output = CodedOutputStream.newInstance(bArr);
        }

        public ByteString build() {
            this.output.checkNoSpaceLeft();
            return new LiteralByteString(this.buffer);
        }

        public CodedOutputStream getCodedOutput() {
            return this.output;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int peekCachedHashCode() {
        return this.hash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkIndex(int index, int size) {
        if (((size - (index + 1)) | index) < 0) {
            if (index < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + index);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + index + ", " + size);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkRange(int startIndex, int endIndex, int size) {
        int i = endIndex - startIndex;
        if ((startIndex | endIndex | i | (size - endIndex)) >= 0) {
            return i;
        }
        if (startIndex < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + startIndex + " < 0");
        }
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + startIndex + ", " + endIndex);
        }
        throw new IndexOutOfBoundsException("End index: " + endIndex + " >= " + size);
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()), truncateAndEscapeForDisplay());
    }

    private String truncateAndEscapeForDisplay() {
        if (size() <= 50) {
            return TextFormatEscaper.escapeBytes(this);
        }
        return TextFormatEscaper.escapeBytes(substring(0, 47)) + "...";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class LiteralByteString extends LeafByteString {
        private static final long serialVersionUID = 1;
        protected final byte[] bytes;

        protected int getOffsetIntoBytes() {
            return 0;
        }

        LiteralByteString(byte[] bytes) {
            Objects.requireNonNull(bytes);
            this.bytes = bytes;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public byte byteAt(int index) {
            return this.bytes[index];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        byte internalByteAt(int index) {
            return this.bytes[index];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public int size() {
            return this.bytes.length;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final ByteString substring(int beginIndex, int endIndex) {
            int checkRange = checkRange(beginIndex, endIndex, size());
            if (checkRange == 0) {
                return ByteString.EMPTY;
            }
            return new BoundedByteString(this.bytes, getOffsetIntoBytes() + beginIndex, checkRange);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
            System.arraycopy(this.bytes, sourceOffset, target, targetOffset, numberToCopy);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final void copyTo(ByteBuffer target) {
            target.put(this.bytes, getOffsetIntoBytes(), size());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final ByteBuffer asReadOnlyByteBuffer() {
            return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final List<ByteBuffer> asReadOnlyByteBufferList() {
            return Collections.singletonList(asReadOnlyByteBuffer());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(toByteArray());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        final void writeToInternal(OutputStream outputStream, int sourceOffset, int numberToWrite) throws IOException {
            outputStream.write(this.bytes, getOffsetIntoBytes() + sourceOffset, numberToWrite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        final void writeTo(ByteOutput output) throws IOException {
            output.writeLazy(this.bytes, getOffsetIntoBytes(), size());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        protected final String toStringInternal(Charset charset) {
            return new String(this.bytes, getOffsetIntoBytes(), size(), charset);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final boolean isValidUtf8() {
            int offsetIntoBytes = getOffsetIntoBytes();
            return Utf8.isValidUtf8(this.bytes, offsetIntoBytes, size() + offsetIntoBytes);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        protected final int partialIsValidUtf8(int state, int offset, int length) {
            int offsetIntoBytes = getOffsetIntoBytes() + offset;
            return Utf8.partialIsValidUtf8(state, this.bytes, offsetIntoBytes, length + offsetIntoBytes);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof ByteString) || size() != ((ByteString) other).size()) {
                return false;
            }
            if (size() == 0) {
                return true;
            }
            if (other instanceof LiteralByteString) {
                LiteralByteString literalByteString = (LiteralByteString) other;
                int peekCachedHashCode = peekCachedHashCode();
                int peekCachedHashCode2 = literalByteString.peekCachedHashCode();
                if (peekCachedHashCode == 0 || peekCachedHashCode2 == 0 || peekCachedHashCode == peekCachedHashCode2) {
                    return equalsRange(literalByteString, 0, size());
                }
                return false;
            }
            return other.equals(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.LeafByteString
        public final boolean equalsRange(ByteString other, int offset, int length) {
            if (length > other.size()) {
                throw new IllegalArgumentException("Length too large: " + length + size());
            }
            int i = offset + length;
            if (i > other.size()) {
                throw new IllegalArgumentException("Ran off end of other: " + offset + ", " + length + ", " + other.size());
            }
            if (other instanceof LiteralByteString) {
                LiteralByteString literalByteString = (LiteralByteString) other;
                byte[] bArr = this.bytes;
                byte[] bArr2 = literalByteString.bytes;
                int offsetIntoBytes = getOffsetIntoBytes() + length;
                int offsetIntoBytes2 = getOffsetIntoBytes();
                int offsetIntoBytes3 = literalByteString.getOffsetIntoBytes() + offset;
                while (offsetIntoBytes2 < offsetIntoBytes) {
                    if (bArr[offsetIntoBytes2] != bArr2[offsetIntoBytes3]) {
                        return false;
                    }
                    offsetIntoBytes2++;
                    offsetIntoBytes3++;
                }
                return true;
            }
            return other.substring(offset, i).equals(substring(0, length));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        protected final int partialHash(int h, int offset, int length) {
            return Internal.partialHash(h, this.bytes, getOffsetIntoBytes() + offset, length);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final InputStream newInput() {
            return new ByteArrayInputStream(this.bytes, getOffsetIntoBytes(), size());
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString
        public final CodedInputStream newCodedInput() {
            return CodedInputStream.newInstance(this.bytes, getOffsetIntoBytes(), size(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class BoundedByteString extends LiteralByteString {
        private static final long serialVersionUID = 1;
        private final int bytesLength;
        private final int bytesOffset;

        BoundedByteString(byte[] bytes, int offset, int length) {
            super(bytes);
            checkRange(offset, offset + length, bytes.length);
            this.bytesOffset = offset;
            this.bytesLength = length;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.LiteralByteString, com.google.crypto.tink.shaded.protobuf.ByteString
        public byte byteAt(int index) {
            checkIndex(index, size());
            return this.bytes[this.bytesOffset + index];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.LiteralByteString, com.google.crypto.tink.shaded.protobuf.ByteString
        byte internalByteAt(int index) {
            return this.bytes[this.bytesOffset + index];
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.LiteralByteString, com.google.crypto.tink.shaded.protobuf.ByteString
        public int size() {
            return this.bytesLength;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.LiteralByteString
        protected int getOffsetIntoBytes() {
            return this.bytesOffset;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ByteString.LiteralByteString, com.google.crypto.tink.shaded.protobuf.ByteString
        protected void copyToInternal(byte[] target, int sourceOffset, int targetOffset, int numberToCopy) {
            System.arraycopy(this.bytes, getOffsetIntoBytes() + sourceOffset, target, targetOffset, numberToCopy);
        }

        Object writeReplace() {
            return ByteString.wrap(toByteArray());
        }

        private void readObject(ObjectInputStream in) throws IOException {
            throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
        }
    }
}
