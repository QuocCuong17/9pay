package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* loaded from: classes5.dex */
public class LineIterator implements Iterator<String>, Closeable {
    private final BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished = false;

    protected boolean isValidLine(String str) {
        return true;
    }

    public LineIterator(Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        if (reader instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader) reader;
        } else {
            this.bufferedReader = new BufferedReader(reader);
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        String readLine;
        if (this.cachedLine != null) {
            return true;
        }
        if (this.finished) {
            return false;
        }
        do {
            try {
                readLine = this.bufferedReader.readLine();
                if (readLine == null) {
                    this.finished = true;
                    return false;
                }
            } catch (IOException e) {
                IOUtils.closeQuietly(this, new Consumer() { // from class: org.apache.commons.io.LineIterator$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        e.addSuppressed((IOException) obj);
                    }
                });
                throw new IllegalStateException(e);
            }
        } while (!isValidLine(readLine));
        this.cachedLine = readLine;
        return true;
    }

    @Override // java.util.Iterator
    public String next() {
        return nextLine();
    }

    public String nextLine() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        String str = this.cachedLine;
        this.cachedLine = null;
        return str;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.finished = true;
        this.cachedLine = null;
        IOUtils.close(this.bufferedReader);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }

    @Deprecated
    public static void closeQuietly(LineIterator lineIterator) {
        IOUtils.closeQuietly(lineIterator);
    }
}
