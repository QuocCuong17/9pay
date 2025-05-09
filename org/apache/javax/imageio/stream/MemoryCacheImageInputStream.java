package org.apache.javax.imageio.stream;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class MemoryCacheImageInputStream {
    protected int bitOffset;
    private InputStream stream;
    protected long streamPos;
    protected long flushedPos = 0;
    private MemoryCache cache = new MemoryCache();

    public MemoryCacheImageInputStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("stream == null!");
        }
        this.stream = inputStream;
    }

    public long readBits(int i) throws IOException {
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException();
        }
        long j = 0;
        if (i == 0) {
            return 0L;
        }
        int i2 = this.bitOffset;
        int i3 = i + i2;
        int i4 = (i2 + i) & 7;
        while (i3 > 0) {
            int read = read();
            if (read == -1) {
                throw new EOFException();
            }
            j = (j << 8) | read;
            i3 -= 8;
        }
        if (i4 != 0) {
            seek(this.streamPos - 1);
        }
        this.bitOffset = i4;
        return (j >>> (-i3)) & ((-1) >>> (64 - i));
    }

    public void seek(long j) throws IOException {
        if (j < this.flushedPos) {
            throw new IndexOutOfBoundsException("pos < flushedPos!");
        }
        this.streamPos = j;
        this.bitOffset = 0;
    }

    public int read() throws IOException {
        this.bitOffset = 0;
        long loadFromStream = this.cache.loadFromStream(this.stream, this.streamPos + 1);
        long j = this.streamPos;
        if (loadFromStream < j + 1) {
            return -1;
        }
        MemoryCache memoryCache = this.cache;
        this.streamPos = 1 + j;
        return memoryCache.read(j);
    }
}
