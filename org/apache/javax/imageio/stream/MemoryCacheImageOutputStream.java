package org.apache.javax.imageio.stream;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class MemoryCacheImageOutputStream {
    protected int bitOffset;
    private MemoryCache cache = new MemoryCache();
    protected long flushedPos = 0;
    private OutputStream stream;
    protected long streamPos;

    public MemoryCacheImageOutputStream(OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("stream == null!");
        }
        this.stream = outputStream;
    }

    public void writeBits(long j, int i) throws IOException {
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException("Bad value for numBits!");
        }
        if (i == 0) {
            return;
        }
        if (this.streamPos > 0 || this.bitOffset > 0) {
            int i2 = this.bitOffset;
            int read = read();
            if (read != -1) {
                seek(this.streamPos - 1);
            } else {
                read = 0;
            }
            int i3 = i + i2;
            if (i3 < 8) {
                write((int) (((j & ((-1) >>> (32 - i))) << (8 - i3)) | (read & (~(r13 << r0)))));
                seek(this.streamPos - 1);
                this.bitOffset = i3;
                return;
            }
            int i4 = 8 - i2;
            write((int) (((j >> (i - i4)) & ((-1) >>> (32 - i4))) | (read & (~r0))));
        }
    }

    public int read() throws IOException {
        this.bitOffset = 0;
        int read = this.cache.read(this.streamPos);
        if (read != -1) {
            this.streamPos++;
        }
        return read;
    }

    public void seek(long j) throws IOException {
        if (j < this.flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }
        this.streamPos = j;
        this.bitOffset = 0;
    }

    public void write(int i) throws IOException {
        flushBits();
        this.cache.write(i, this.streamPos);
        this.streamPos++;
    }

    protected final void flushBits() throws IOException {
        int i = this.bitOffset;
        if (i != 0) {
            int read = read();
            int i2 = 0;
            if (read < 0) {
                this.bitOffset = 0;
            } else {
                seek(this.streamPos - 1);
                i2 = read & ((-1) << (8 - i));
            }
            write(i2);
        }
    }

    public void flush() throws IOException {
        flushBefore(this.streamPos);
    }

    public void flushBefore(long j) throws IOException {
        if (j < this.flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }
        if (j > this.streamPos) {
            throw new IndexOutOfBoundsException("pos > getStreamPosition()!");
        }
        this.flushedPos = j;
    }
}
