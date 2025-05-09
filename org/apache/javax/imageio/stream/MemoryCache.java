package org.apache.javax.imageio.stream;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MemoryCache {
    private static final int BUFFER_LENGTH = 8192;
    private ArrayList cache = new ArrayList();
    private long cacheStart = 0;
    private long length = 0;

    private byte[] getCacheBlock(long j) throws IOException {
        long j2 = j - this.cacheStart;
        if (j2 > 2147483647L) {
            throw new IOException("Cache addressing limit exceeded!");
        }
        return (byte[]) this.cache.get((int) j2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0018, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long loadFromStream(InputStream inputStream, long j) throws IOException {
        byte[] bArr;
        long j2 = this.length;
        if (j < j2) {
            return j;
        }
        int i = (int) (j2 % PlaybackStateCompat.ACTION_PLAY_FROM_URI);
        long j3 = j - j2;
        if (i != 0) {
            bArr = getCacheBlock(j2 / PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            while (j3 > 0) {
                if (bArr == null) {
                    try {
                        bArr = new byte[8192];
                        i = 0;
                    } catch (OutOfMemoryError unused) {
                        throw new IOException("No memory left for cache!");
                    }
                }
                int read = inputStream.read(bArr, i, (int) Math.min(j3, 8192 - i));
                if (read == -1) {
                    return this.length;
                }
                if (i == 0) {
                    this.cache.add(bArr);
                }
                long j4 = read;
                j3 -= j4;
                this.length += j4;
                i += read;
                if (i >= 8192) {
                }
            }
            return j;
        }
        bArr = null;
    }

    public int read(long j) throws IOException {
        byte[] cacheBlock;
        if (j < this.length && (cacheBlock = getCacheBlock(j / PlaybackStateCompat.ACTION_PLAY_FROM_URI)) != null) {
            return cacheBlock[(int) (j % PlaybackStateCompat.ACTION_PLAY_FROM_URI)] & 255;
        }
        return -1;
    }

    public void write(int i, long j) throws IOException {
        if (j < 0) {
            throw new ArrayIndexOutOfBoundsException("pos < 0");
        }
        if (j >= this.length) {
            pad(j);
            this.length = 1 + j;
        }
        getCacheBlock(j / PlaybackStateCompat.ACTION_PLAY_FROM_URI)[(int) (j % PlaybackStateCompat.ACTION_PLAY_FROM_URI)] = (byte) i;
    }

    private void pad(long j) throws IOException {
        long size = (j / PlaybackStateCompat.ACTION_PLAY_FROM_URI) - ((this.cacheStart + this.cache.size()) - 1);
        for (long j2 = 0; j2 < size; j2++) {
            try {
                this.cache.add(new byte[8192]);
            } catch (OutOfMemoryError unused) {
                throw new IOException("No memory left for cache!");
            }
        }
    }
}
