package org.apache.fontbox.ttf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class MemoryTTFDataStream extends TTFDataStream {
    private int currentPosition = 0;
    private byte[] data;

    public MemoryTTFDataStream(InputStream inputStream) throws IOException {
        this.data = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            }
            this.data = byteArrayOutputStream.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long readLong() throws IOException {
        return (readSignedInt() << 32) + (readSignedInt() & 4294967295L);
    }

    public int readSignedInt() throws IOException {
        int read = read();
        int read2 = read();
        int read3 = read();
        int read4 = read();
        if ((read | read2 | read3 | read4) >= 0) {
            return (read << 24) + (read2 << 16) + (read3 << 8) + (read4 << 0);
        }
        throw new EOFException();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read() throws IOException {
        int i = this.currentPosition;
        byte[] bArr = this.data;
        if (i >= bArr.length) {
            return -1;
        }
        byte b = bArr[i];
        this.currentPosition = i + 1;
        return (b + 256) % 256;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int readUnsignedShort() throws IOException {
        int read = read();
        int read2 = read();
        if ((read | read2) >= 0) {
            return (read << 8) + (read2 << 0);
        }
        throw new EOFException();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public short readSignedShort() throws IOException {
        int read = read();
        int read2 = read();
        if ((read | read2) >= 0) {
            return (short) ((read << 8) + (read2 << 0));
        }
        throw new EOFException();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.data = null;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public void seek(long j) throws IOException {
        this.currentPosition = (int) j;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.currentPosition;
        byte[] bArr2 = this.data;
        if (i3 >= bArr2.length) {
            return -1;
        }
        int min = Math.min(i2, bArr2.length - i3);
        System.arraycopy(this.data, this.currentPosition, bArr, i, min);
        this.currentPosition += min;
        return min;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long getCurrentPosition() throws IOException {
        return this.currentPosition;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public InputStream getOriginalData() throws IOException {
        return new ByteArrayInputStream(this.data);
    }
}
