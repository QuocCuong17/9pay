package org.apache.commons.io.input;

import java.io.InputStream;
import java.util.Objects;

/* loaded from: classes5.dex */
public class UnsynchronizedByteArrayInputStream extends InputStream {
    public static final int END_OF_STREAM = -1;
    private final byte[] data;
    private final int eod;
    private int markedOffset;
    private int offset;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public UnsynchronizedByteArrayInputStream(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.data = bArr;
        this.offset = 0;
        this.eod = bArr.length;
        this.markedOffset = 0;
    }

    public UnsynchronizedByteArrayInputStream(byte[] bArr, int i) {
        Objects.requireNonNull(bArr);
        if (i < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        this.data = bArr;
        int min = Math.min(i, bArr.length > 0 ? bArr.length : i);
        this.offset = min;
        this.eod = bArr.length;
        this.markedOffset = min;
    }

    public UnsynchronizedByteArrayInputStream(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr);
        if (i < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("length cannot be negative");
        }
        this.data = bArr;
        int min = Math.min(i, bArr.length > 0 ? bArr.length : i);
        this.offset = min;
        this.eod = Math.min(min + i2, bArr.length);
        this.markedOffset = this.offset;
    }

    @Override // java.io.InputStream
    public int available() {
        int i = this.offset;
        int i2 = this.eod;
        if (i < i2) {
            return i2 - i;
        }
        return 0;
    }

    @Override // java.io.InputStream
    public int read() {
        int i = this.offset;
        if (i >= this.eod) {
            return -1;
        }
        byte[] bArr = this.data;
        this.offset = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        Objects.requireNonNull(bArr);
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        Objects.requireNonNull(bArr);
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = this.offset;
        int i4 = this.eod;
        if (i3 >= i4) {
            return -1;
        }
        int i5 = i4 - i3;
        if (i2 >= i5) {
            i2 = i5;
        }
        if (i2 <= 0) {
            return 0;
        }
        System.arraycopy(this.data, i3, bArr, i, i2);
        this.offset += i2;
        return i2;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Skipping backward is not supported");
        }
        int i = this.eod;
        int i2 = this.offset;
        long j2 = i - i2;
        if (j >= j2) {
            j = j2;
        }
        this.offset = (int) (i2 + j);
        return j;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.markedOffset = this.offset;
    }

    @Override // java.io.InputStream
    public void reset() {
        this.offset = this.markedOffset;
    }
}
