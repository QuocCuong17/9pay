package com.squareup.tape2;

import com.squareup.tape2.ObjectQueue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
final class FileObjectQueue<T> extends ObjectQueue<T> {
    private final DirectByteArrayOutputStream bytes = new DirectByteArrayOutputStream();
    final ObjectQueue.Converter<T> converter;
    private final QueueFile queueFile;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileObjectQueue(QueueFile queueFile, ObjectQueue.Converter<T> converter) {
        this.queueFile = queueFile;
        this.converter = converter;
    }

    @Override // com.squareup.tape2.ObjectQueue
    @Nonnull
    public QueueFile file() {
        return this.queueFile;
    }

    @Override // com.squareup.tape2.ObjectQueue
    public int size() {
        return this.queueFile.size();
    }

    @Override // com.squareup.tape2.ObjectQueue
    public boolean isEmpty() {
        return this.queueFile.isEmpty();
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void add(T t) throws IOException {
        this.bytes.reset();
        this.converter.toStream(t, this.bytes);
        this.queueFile.add(this.bytes.getArray(), 0, this.bytes.size());
    }

    @Override // com.squareup.tape2.ObjectQueue
    @Nullable
    public T peek() throws IOException {
        byte[] peek = this.queueFile.peek();
        if (peek == null) {
            return null;
        }
        return this.converter.from(peek);
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void remove() throws IOException {
        this.queueFile.remove();
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void remove(int i) throws IOException {
        this.queueFile.remove(i);
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void clear() throws IOException {
        this.queueFile.clear();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.queueFile.close();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new QueueFileIterator(this.queueFile.iterator());
    }

    public String toString() {
        return "FileObjectQueue{queueFile=" + this.queueFile + '}';
    }

    /* loaded from: classes5.dex */
    private final class QueueFileIterator implements Iterator<T> {
        final Iterator<byte[]> iterator;

        QueueFileIterator(Iterator<byte[]> it) {
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            try {
                return FileObjectQueue.this.converter.from(this.iterator.next());
            } catch (IOException e) {
                throw ((Error) QueueFile.getSneakyThrowable(e));
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }

    /* loaded from: classes5.dex */
    private static final class DirectByteArrayOutputStream extends ByteArrayOutputStream {
        DirectByteArrayOutputStream() {
        }

        byte[] getArray() {
            return this.buf;
        }
    }
}
