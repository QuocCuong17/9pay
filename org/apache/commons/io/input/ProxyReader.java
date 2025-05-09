package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import org.apache.commons.io.IOUtils;

/* loaded from: classes5.dex */
public abstract class ProxyReader extends FilterReader {
    protected void afterRead(int i) throws IOException {
    }

    protected void beforeRead(int i) throws IOException {
    }

    public ProxyReader(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int i = 1;
        try {
            beforeRead(1);
            int read = this.in.read();
            if (read == -1) {
                i = -1;
            }
            afterRead(i);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr) throws IOException {
        try {
            beforeRead(IOUtils.length(cArr));
            int read = this.in.read(cArr);
            afterRead(read);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        try {
            beforeRead(i2);
            int read = this.in.read(cArr, i, i2);
            afterRead(read);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.Reader, java.lang.Readable
    public int read(CharBuffer charBuffer) throws IOException {
        try {
            beforeRead(IOUtils.length(charBuffer));
            int read = this.in.read(charBuffer);
            afterRead(read);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public long skip(long j) throws IOException {
        try {
            return this.in.skip(j);
        } catch (IOException e) {
            handleIOException(e);
            return 0L;
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean ready() throws IOException {
        try {
            return this.in.ready();
        } catch (IOException e) {
            handleIOException(e);
            return false;
        }
    }

    @Override // java.io.FilterReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.in.close();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public synchronized void mark(int i) throws IOException {
        try {
            this.in.mark(i);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public synchronized void reset() throws IOException {
        try {
            this.in.reset();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean markSupported() {
        return this.in.markSupported();
    }

    protected void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }
}
