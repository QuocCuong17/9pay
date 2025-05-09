package com.github.jaiimageio.stream;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import javax.imageio.stream.ImageInputStreamImpl;

/* loaded from: classes3.dex */
public class FileChannelImageInputStream extends ImageInputStreamImpl {
    private FileChannel channel;
    private MappedByteBuffer mappedBuffer;
    private long mappedPos;
    private long mappedUpperBound;

    public FileChannelImageInputStream(FileChannel fileChannel) throws IOException {
        if (fileChannel == null) {
            throw new IllegalArgumentException("channel == null");
        }
        if (!fileChannel.isOpen()) {
            throw new IllegalArgumentException("channel.isOpen() == false");
        }
        this.channel = fileChannel;
        long position = fileChannel.position();
        this.flushedPos = position;
        this.streamPos = position;
        long min = Math.min(fileChannel.size() - position, 2147483647L);
        this.mappedPos = 0L;
        this.mappedUpperBound = 0 + min;
        this.mappedBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, position, min);
    }

    private MappedByteBuffer getMappedBuffer(int i) throws IOException {
        if (this.streamPos < this.mappedPos || this.streamPos + i >= this.mappedUpperBound) {
            this.mappedPos = this.streamPos;
            long min = Math.min(this.channel.size() - this.mappedPos, 2147483647L);
            this.mappedUpperBound = this.mappedPos + min;
            MappedByteBuffer map = this.channel.map(FileChannel.MapMode.READ_ONLY, this.mappedPos, min);
            this.mappedBuffer = map;
            map.order(super.getByteOrder());
        }
        return this.mappedBuffer;
    }

    public int read() throws IOException {
        checkClosed();
        this.bitOffset = 0;
        MappedByteBuffer mappedBuffer = getMappedBuffer(1);
        if (mappedBuffer.remaining() < 1) {
            return -1;
        }
        int i = mappedBuffer.get() & 255;
        this.streamPos++;
        return i;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > b.length");
        }
        if (i2 == 0) {
            return 0;
        }
        checkClosed();
        this.bitOffset = 0;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i2);
        int remaining = mappedBuffer.remaining();
        if (remaining < 1) {
            return -1;
        }
        if (i2 > remaining) {
            i2 = remaining;
        }
        mappedBuffer.get(bArr, i, i2);
        this.streamPos += i2;
        return i2;
    }

    public void close() throws IOException {
        super.close();
        this.channel = null;
    }

    public void readFully(char[] cArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > c.length");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i2 * 2;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i3);
        if (mappedBuffer.remaining() < i3) {
            throw new EOFException();
        }
        mappedBuffer.asCharBuffer().get(cArr, i, i2);
        seek(this.streamPos + i3);
    }

    public void readFully(short[] sArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > sArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > s.length");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i2 * 2;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i3);
        if (mappedBuffer.remaining() < i3) {
            throw new EOFException();
        }
        mappedBuffer.asShortBuffer().get(sArr, i, i2);
        seek(this.streamPos + i3);
    }

    public void readFully(int[] iArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > iArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > i.length");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i2 * 4;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i3);
        if (mappedBuffer.remaining() < i3) {
            throw new EOFException();
        }
        mappedBuffer.asIntBuffer().get(iArr, i, i2);
        seek(this.streamPos + i3);
    }

    public void readFully(long[] jArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > jArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > l.length");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i2 * 8;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i3);
        if (mappedBuffer.remaining() < i3) {
            throw new EOFException();
        }
        mappedBuffer.asLongBuffer().get(jArr, i, i2);
        seek(this.streamPos + i3);
    }

    public void readFully(float[] fArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > fArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > f.length");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i2 * 4;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i3);
        if (mappedBuffer.remaining() < i3) {
            throw new EOFException();
        }
        mappedBuffer.asFloatBuffer().get(fArr, i, i2);
        seek(this.streamPos + i3);
    }

    public void readFully(double[] dArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > dArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > d.length");
        }
        if (i2 == 0) {
            return;
        }
        int i3 = i2 * 8;
        MappedByteBuffer mappedBuffer = getMappedBuffer(i3);
        if (mappedBuffer.remaining() < i3) {
            throw new EOFException();
        }
        mappedBuffer.asDoubleBuffer().get(dArr, i, i2);
        seek(this.streamPos + i3);
    }

    public long length() {
        try {
            return this.channel.size();
        } catch (IOException unused) {
            return -1L;
        }
    }

    public void seek(long j) throws IOException {
        super.seek(j);
        long j2 = this.mappedPos;
        if (j >= j2 && j < this.mappedUpperBound) {
            this.mappedBuffer.position((int) (j - j2));
        } else {
            this.mappedBuffer = getMappedBuffer((int) Math.min(this.channel.size() - j, 2147483647L));
        }
    }

    public void setByteOrder(ByteOrder byteOrder) {
        super.setByteOrder(byteOrder);
        this.mappedBuffer.order(byteOrder);
    }
}
