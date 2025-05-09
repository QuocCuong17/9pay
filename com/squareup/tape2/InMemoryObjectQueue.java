package com.squareup.tape2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class InMemoryObjectQueue<T> extends ObjectQueue<T> {
    boolean closed;
    int modCount = 0;
    private final Deque<T> entries = new ArrayDeque();

    @Override // com.squareup.tape2.ObjectQueue
    @Nullable
    public QueueFile file() {
        return null;
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void add(T t) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.modCount++;
        this.entries.addLast(t);
    }

    @Override // com.squareup.tape2.ObjectQueue
    @Nullable
    public T peek() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.entries.peekFirst();
    }

    @Override // com.squareup.tape2.ObjectQueue
    public List<T> asList() {
        return Collections.unmodifiableList(new ArrayList(this.entries));
    }

    @Override // com.squareup.tape2.ObjectQueue
    public int size() {
        return this.entries.size();
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void remove() {
        remove(1);
    }

    @Override // com.squareup.tape2.ObjectQueue
    public void remove(int i) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.modCount++;
        for (int i2 = 0; i2 < i; i2++) {
            this.entries.removeFirst();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new EntryIterator(this.entries.iterator());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    public String toString() {
        return "InMemoryObjectQueue{size=" + this.entries.size() + '}';
    }

    /* loaded from: classes5.dex */
    private final class EntryIterator implements Iterator<T> {
        private final Iterator<T> delegate;
        private int expectedModCount;
        private int index = 0;

        EntryIterator(Iterator<T> it) {
            this.expectedModCount = InMemoryObjectQueue.this.modCount;
            this.delegate = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkForComodification();
            return this.delegate.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (InMemoryObjectQueue.this.closed) {
                throw new IllegalStateException("closed");
            }
            checkForComodification();
            T next = this.delegate.next();
            this.index++;
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (InMemoryObjectQueue.this.closed) {
                throw new IllegalStateException("closed");
            }
            checkForComodification();
            if (InMemoryObjectQueue.this.size() == 0) {
                throw new NoSuchElementException();
            }
            if (this.index != 1) {
                throw new UnsupportedOperationException("Removal is only permitted from the head.");
            }
            InMemoryObjectQueue.this.remove();
            this.expectedModCount = InMemoryObjectQueue.this.modCount;
            this.index--;
        }

        private void checkForComodification() {
            if (InMemoryObjectQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
