package org.apache.pdfbox.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* loaded from: classes5.dex */
public class PushBackInputStream extends PushbackInputStream {
    private long offset;
    private final RandomAccessRead raInput;

    /* JADX WARN: Multi-variable type inference failed */
    public PushBackInputStream(InputStream inputStream, int i) throws IOException {
        super(inputStream, i);
        this.offset = 0L;
        if (inputStream == 0) {
            throw new IOException("Error: input was null");
        }
        this.raInput = inputStream instanceof RandomAccessRead ? (RandomAccessRead) inputStream : null;
    }

    public int peek() throws IOException {
        int read = read();
        if (read != -1) {
            unread(read);
        }
        return read;
    }

    public long getOffset() {
        return this.offset;
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.offset++;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.PushbackInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.offset += read;
        }
        return read;
    }

    @Override // java.io.PushbackInputStream
    public void unread(int i) throws IOException {
        this.offset--;
        super.unread(i);
    }

    @Override // java.io.PushbackInputStream
    public void unread(byte[] bArr) throws IOException {
        unread(bArr, 0, bArr.length);
    }

    @Override // java.io.PushbackInputStream
    public void unread(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0) {
            this.offset -= i2;
            super.unread(bArr, i, i2);
        }
    }

    public boolean isEOF() throws IOException {
        return peek() == -1;
    }

    public void fillBuffer() throws IOException {
        int length = this.buf.length;
        byte[] bArr = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i != -1 && i2 < length) {
            i = read(bArr, i2, length - i2);
            if (i != -1) {
                i2 += i;
            }
        }
        unread(bArr, 0, i2);
    }

    public byte[] readFully(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = read(bArr, i2, i - i2);
            if (read < 0) {
                throw new EOFException("Premature end of file");
            }
            i2 += read;
        }
        return bArr;
    }

    public void seek(long j) throws IOException {
        if (this.raInput == null) {
            throw new IOException("Provided stream of type " + this.in.getClass().getSimpleName() + " is not seekable.");
        }
        int length = this.buf.length - this.pos;
        if (length > 0) {
            skip(length);
        }
        this.raInput.seek(j);
        this.offset = j;
    }
}
