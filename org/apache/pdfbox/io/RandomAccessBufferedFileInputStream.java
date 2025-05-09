package org.apache.pdfbox.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: classes5.dex */
public class RandomAccessBufferedFileInputStream extends InputStream implements RandomAccessRead {
    private final long fileLength;
    private boolean isClosed;
    private final java.io.RandomAccessFile raFile;
    private int pageSizeShift = 12;
    private int pageSize = 1 << 12;
    private long pageOffsetMask = (-1) << 12;
    private int maxCachedPages = 1000;
    private byte[] lastRemovedCachePage = null;
    private final Map<Long, byte[]> pageCache = new LinkedHashMap<Long, byte[]>(this.maxCachedPages, 0.75f, true) { // from class: org.apache.pdfbox.io.RandomAccessBufferedFileInputStream.1
        private static final long serialVersionUID = -6302488539257741101L;

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<Long, byte[]> entry) {
            boolean z = size() > RandomAccessBufferedFileInputStream.this.maxCachedPages;
            if (z) {
                RandomAccessBufferedFileInputStream.this.lastRemovedCachePage = entry.getValue();
            }
            return z;
        }
    };
    private long curPageOffset = -1;
    private byte[] curPage = new byte[this.pageSize];
    private int offsetWithinPage = 0;
    private long fileOffset = 0;

    public RandomAccessBufferedFileInputStream(File file) throws IOException {
        this.raFile = new java.io.RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        this.fileLength = file.length();
        seek(0L);
    }

    public long getFilePointer() {
        return this.fileOffset;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() {
        return this.fileOffset;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long j) throws IOException {
        long j2 = this.pageOffsetMask & j;
        if (j2 != this.curPageOffset) {
            byte[] bArr = this.pageCache.get(Long.valueOf(j2));
            if (bArr == null) {
                this.raFile.seek(j2);
                bArr = readPage();
                this.pageCache.put(Long.valueOf(j2), bArr);
            }
            this.curPageOffset = j2;
            this.curPage = bArr;
        }
        this.offsetWithinPage = (int) (j - this.curPageOffset);
        this.fileOffset = j;
    }

    private byte[] readPage() throws IOException {
        int read;
        byte[] bArr = this.lastRemovedCachePage;
        if (bArr != null) {
            this.lastRemovedCachePage = null;
        } else {
            bArr = new byte[this.pageSize];
        }
        int i = 0;
        while (true) {
            int i2 = this.pageSize;
            if (i >= i2 || (read = this.raFile.read(bArr, i, i2 - i)) < 0) {
                break;
            }
            i += read;
        }
        return bArr;
    }

    @Override // java.io.InputStream, org.apache.pdfbox.io.SequentialRead
    public int read() throws IOException {
        long j = this.fileOffset;
        if (j >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == this.pageSize) {
            seek(j);
        }
        this.fileOffset++;
        byte[] bArr = this.curPage;
        int i = this.offsetWithinPage;
        this.offsetWithinPage = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream, org.apache.pdfbox.io.SequentialRead
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.fileOffset;
        if (j >= this.fileLength) {
            return -1;
        }
        if (this.offsetWithinPage == this.pageSize) {
            seek(j);
        }
        int min = Math.min(this.pageSize - this.offsetWithinPage, i2);
        long j2 = this.fileLength;
        long j3 = this.fileOffset;
        if (j2 - j3 < this.pageSize) {
            min = Math.min(min, (int) (j2 - j3));
        }
        System.arraycopy(this.curPage, this.offsetWithinPage, bArr, i, min);
        this.offsetWithinPage += min;
        this.fileOffset += min;
        return min;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return (int) Math.min(this.fileLength - this.fileOffset, 2147483647L);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = this.fileLength;
        long j3 = this.fileOffset;
        if (j2 - j3 < j) {
            j = j2 - j3;
        }
        int i = this.pageSize;
        if (j < i) {
            int i2 = this.offsetWithinPage;
            if (i2 + j <= i) {
                this.offsetWithinPage = (int) (i2 + j);
                this.fileOffset = j3 + j;
                return j;
            }
        }
        seek(j3 + j);
        return j;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        return this.fileLength;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable, org.apache.pdfbox.io.SequentialRead
    public void close() throws IOException {
        this.raFile.close();
        this.pageCache.clear();
        this.isClosed = true;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.isClosed;
    }
}
