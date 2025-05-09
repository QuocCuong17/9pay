package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes5.dex */
public class SequenceReader extends Reader {
    private Reader reader;
    private Iterator<? extends Reader> readers;

    public SequenceReader(Iterable<? extends Reader> iterable) {
        Objects.requireNonNull(iterable, "readers");
        this.readers = iterable.iterator();
        this.reader = nextReader();
    }

    public SequenceReader(Reader... readerArr) {
        this(Arrays.asList(readerArr));
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.readers = null;
        this.reader = null;
    }

    private Reader nextReader() {
        if (this.readers.hasNext()) {
            return this.readers.next();
        }
        return null;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i = -1;
        while (true) {
            Reader reader = this.reader;
            if (reader == null || (i = reader.read()) != -1) {
                break;
            }
            this.reader = nextReader();
        }
        return i;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        Objects.requireNonNull(cArr, "cbuf");
        if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
        }
        int i3 = 0;
        while (true) {
            Reader reader = this.reader;
            if (reader == null) {
                break;
            }
            int read = reader.read(cArr, i, i2);
            if (read == -1) {
                this.reader = nextReader();
            } else {
                i3 += read;
                i += read;
                i2 -= read;
                if (i2 <= 0) {
                    break;
                }
            }
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }
}
