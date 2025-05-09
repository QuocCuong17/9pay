package org.apache.fontbox.ttf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes5.dex */
public class RAFDataStream extends TTFDataStream {
    private RandomAccessFile raf;
    private File ttfFile;

    public RAFDataStream(String str, String str2) throws FileNotFoundException {
        this(new File(str), str2);
    }

    public RAFDataStream(File file, String str) throws FileNotFoundException {
        this.raf = null;
        this.ttfFile = null;
        this.raf = new RandomAccessFile(file, str);
        this.ttfFile = file;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public short readSignedShort() throws IOException {
        return this.raf.readShort();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long getCurrentPosition() throws IOException {
        return this.raf.getFilePointer();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.raf.close();
        this.raf = null;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read() throws IOException {
        return this.raf.read();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int readUnsignedShort() throws IOException {
        return this.raf.readUnsignedShort();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long readLong() throws IOException {
        return this.raf.readLong();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.raf.read(bArr, i, i2);
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public InputStream getOriginalData() throws IOException {
        return new FileInputStream(this.ttfFile);
    }
}
