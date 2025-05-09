package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes5.dex */
public class RandomAccessFile implements RandomAccess, Closeable {
    private boolean isClosed;
    private final java.io.RandomAccessFile ras;

    public RandomAccessFile(File file, String str) throws FileNotFoundException {
        this.ras = new java.io.RandomAccessFile(file, str);
    }

    @Override // org.apache.pdfbox.io.SequentialRead, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ras.close();
        this.isClosed = true;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        this.ras.seek(j);
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        return this.ras.getFilePointer();
    }

    @Override // org.apache.pdfbox.io.SequentialRead
    public int read() throws IOException {
        return this.ras.read();
    }

    @Override // org.apache.pdfbox.io.SequentialRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.ras.read(bArr, i, i2);
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        return this.ras.length();
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override // org.apache.pdfbox.io.RandomAccess
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.ras.write(bArr, i, i2);
    }

    @Override // org.apache.pdfbox.io.RandomAccess
    public void write(int i) throws IOException {
        this.ras.write(i);
    }
}
