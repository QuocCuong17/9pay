package org.apache.pdfbox.io;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class RandomAccessFileInputStream extends InputStream {
    private long currentPosition;
    private final long endPosition;
    private final RandomAccess file;

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public RandomAccessFileInputStream(RandomAccess randomAccess, long j, long j2) {
        this.file = randomAccess;
        this.currentPosition = j;
        this.endPosition = j + j2;
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) (this.endPosition - this.currentPosition);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i;
        synchronized (this.file) {
            i = -1;
            long j = this.currentPosition;
            if (j < this.endPosition) {
                this.file.seek(j);
                this.currentPosition++;
                i = this.file.read();
            }
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > available()) {
            i2 = available();
        }
        int i3 = -1;
        if (available() > 0) {
            synchronized (this.file) {
                this.file.seek(this.currentPosition);
                i3 = this.file.read(bArr, i, i2);
            }
        }
        if (i3 > 0) {
            this.currentPosition += i3;
        }
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        long min = Math.min(j, available());
        this.currentPosition += min;
        return min;
    }
}
