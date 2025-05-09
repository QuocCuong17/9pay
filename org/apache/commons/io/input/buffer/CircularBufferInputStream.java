package org.apache.commons.io.input.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* loaded from: classes5.dex */
public class CircularBufferInputStream extends InputStream {
    protected final CircularByteBuffer buffer;
    protected final int bufferSize;
    private boolean eofSeen;
    protected final InputStream in;

    public CircularBufferInputStream(InputStream inputStream, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Invalid bufferSize: " + i);
        }
        Objects.requireNonNull(inputStream, "inputStream");
        this.in = inputStream;
        this.buffer = new CircularByteBuffer(i);
        this.bufferSize = i;
        this.eofSeen = false;
    }

    public CircularBufferInputStream(InputStream inputStream) {
        this(inputStream, 8192);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fillBuffer() throws IOException {
        if (this.eofSeen) {
            return;
        }
        int space = this.buffer.getSpace();
        byte[] bArr = new byte[space];
        while (space > 0) {
            int read = this.in.read(bArr, 0, space);
            if (read == -1) {
                this.eofSeen = true;
                return;
            } else if (read > 0) {
                this.buffer.add(bArr, 0, read);
                space -= read;
            }
        }
    }

    protected boolean haveBytes(int i) throws IOException {
        if (this.buffer.getCurrentNumberOfBytes() < i) {
            fillBuffer();
        }
        return this.buffer.hasBytes();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (haveBytes(1)) {
            return this.buffer.read() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr, "Buffer");
        if (i < 0) {
            throw new IllegalArgumentException("Offset must not be negative");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Length must not be negative");
        }
        if (!haveBytes(i2)) {
            return -1;
        }
        int min = Math.min(i2, this.buffer.getCurrentNumberOfBytes());
        for (int i3 = 0; i3 < min; i3++) {
            bArr[i + i3] = this.buffer.read();
        }
        return min;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
        this.eofSeen = true;
        this.buffer.clear();
    }
}
