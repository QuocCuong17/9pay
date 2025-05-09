package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class RandomAccessBuffer implements RandomAccess, Closeable, Cloneable {
    private static final int CHUNK_SIZE = 1024;
    private List<byte[]> bufferList;
    private int bufferListIndex;
    private int bufferListMaxIndex;
    private byte[] currentBuffer;
    private long currentBufferPointer;
    private long pointer;
    private long size;

    public RandomAccessBuffer() {
        this.bufferList = null;
        ArrayList arrayList = new ArrayList();
        this.bufferList = arrayList;
        byte[] bArr = new byte[1024];
        this.currentBuffer = bArr;
        arrayList.add(bArr);
        this.pointer = 0L;
        this.currentBufferPointer = 0L;
        this.size = 0L;
        this.bufferListIndex = 0;
        this.bufferListMaxIndex = 0;
    }

    public RandomAccessBuffer clone() {
        RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer();
        randomAccessBuffer.bufferList = new ArrayList(this.bufferList.size());
        for (byte[] bArr : this.bufferList) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            randomAccessBuffer.bufferList.add(bArr2);
        }
        if (this.currentBuffer != null) {
            randomAccessBuffer.currentBuffer = randomAccessBuffer.bufferList.get(r1.size() - 1);
        } else {
            randomAccessBuffer.currentBuffer = null;
        }
        randomAccessBuffer.pointer = this.pointer;
        randomAccessBuffer.currentBufferPointer = this.currentBufferPointer;
        randomAccessBuffer.size = this.size;
        randomAccessBuffer.bufferListIndex = this.bufferListIndex;
        randomAccessBuffer.bufferListMaxIndex = this.bufferListMaxIndex;
        return randomAccessBuffer;
    }

    @Override // org.apache.pdfbox.io.SequentialRead, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.currentBuffer = null;
        this.bufferList.clear();
        this.pointer = 0L;
        this.currentBufferPointer = 0L;
        this.size = 0L;
        this.bufferListIndex = 0;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        checkClosed();
        this.pointer = j;
        int i = (int) (j / 1024);
        this.bufferListIndex = i;
        this.currentBufferPointer = j % 1024;
        this.currentBuffer = this.bufferList.get(i);
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.pointer;
    }

    @Override // org.apache.pdfbox.io.SequentialRead
    public int read() throws IOException {
        checkClosed();
        if (this.pointer >= this.size) {
            return -1;
        }
        if (this.currentBufferPointer >= 1024) {
            int i = this.bufferListIndex;
            if (i >= this.bufferListMaxIndex) {
                return -1;
            }
            List<byte[]> list = this.bufferList;
            int i2 = i + 1;
            this.bufferListIndex = i2;
            this.currentBuffer = list.get(i2);
            this.currentBufferPointer = 0L;
        }
        this.pointer++;
        byte[] bArr = this.currentBuffer;
        long j = this.currentBufferPointer;
        this.currentBufferPointer = 1 + j;
        return bArr[(int) j] & 255;
    }

    @Override // org.apache.pdfbox.io.SequentialRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        long j = this.pointer;
        long j2 = this.size;
        if (j >= j2) {
            return 0;
        }
        long j3 = i2;
        int min = (int) Math.min(j3, j2 - j);
        long j4 = this.currentBufferPointer;
        long j5 = 1024 - j4;
        long j6 = min;
        if (j6 >= j5) {
            int i3 = (int) j5;
            System.arraycopy(this.currentBuffer, (int) j4, bArr, i, i3);
            int i4 = i + i3;
            long j7 = j3 - j5;
            int i5 = ((int) j7) / 1024;
            for (int i6 = 0; i6 < i5; i6++) {
                nextBuffer();
                System.arraycopy(this.currentBuffer, 0, bArr, i4, 1024);
                i4 += 1024;
            }
            long j8 = j7 % 1024;
            if (j8 > 0) {
                nextBuffer();
                System.arraycopy(this.currentBuffer, 0, bArr, i4, (int) j8);
                this.currentBufferPointer += j8;
            }
        } else {
            System.arraycopy(this.currentBuffer, (int) j4, bArr, i, min);
            this.currentBufferPointer += j6;
        }
        this.pointer += j6;
        return min;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.size;
    }

    @Override // org.apache.pdfbox.io.RandomAccess
    public void write(int i) throws IOException {
        checkClosed();
        if (this.currentBufferPointer >= 1024) {
            if (this.pointer + 1024 >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            expandBuffer();
        }
        byte[] bArr = this.currentBuffer;
        long j = this.currentBufferPointer;
        long j2 = j + 1;
        this.currentBufferPointer = j2;
        bArr[(int) j] = (byte) i;
        long j3 = this.pointer + 1;
        this.pointer = j3;
        if (j3 > this.size) {
            this.size = j3;
        }
        if (j2 >= 1024) {
            if (j3 + 1024 >= 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            expandBuffer();
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccess
    public void write(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        long j = i2;
        long j2 = this.pointer + j;
        long j3 = this.currentBufferPointer;
        long j4 = 1024 - j3;
        if (j >= j4) {
            if (j2 > 2147483647L) {
                throw new IOException("RandomAccessBuffer overflow");
            }
            int i3 = (int) j4;
            System.arraycopy(bArr, i, this.currentBuffer, (int) j3, i3);
            int i4 = i + i3;
            long j5 = j - j4;
            int i5 = ((int) j5) / 1024;
            for (int i6 = 0; i6 < i5; i6++) {
                expandBuffer();
                System.arraycopy(bArr, i4, this.currentBuffer, (int) this.currentBufferPointer, 1024);
                i4 += 1024;
            }
            long j6 = j5 - (i5 * 1024);
            if (j6 >= 0) {
                expandBuffer();
                if (j6 > 0) {
                    System.arraycopy(bArr, i4, this.currentBuffer, (int) this.currentBufferPointer, (int) j6);
                }
                this.currentBufferPointer = j6;
            }
        } else {
            System.arraycopy(bArr, i, this.currentBuffer, (int) j3, i2);
            this.currentBufferPointer += j;
        }
        long j7 = this.pointer + j;
        this.pointer = j7;
        if (j7 > this.size) {
            this.size = j7;
        }
    }

    private void expandBuffer() {
        if (this.bufferListMaxIndex > this.bufferListIndex) {
            nextBuffer();
            return;
        }
        byte[] bArr = new byte[1024];
        this.currentBuffer = bArr;
        this.bufferList.add(bArr);
        this.currentBufferPointer = 0L;
        this.bufferListMaxIndex++;
        this.bufferListIndex++;
    }

    private void nextBuffer() {
        this.currentBufferPointer = 0L;
        List<byte[]> list = this.bufferList;
        int i = this.bufferListIndex + 1;
        this.bufferListIndex = i;
        this.currentBuffer = list.get(i);
    }

    private void checkClosed() throws IOException {
        if (this.currentBuffer == null) {
            throw new IOException("RandomAccessBuffer already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.currentBuffer == null;
    }
}
