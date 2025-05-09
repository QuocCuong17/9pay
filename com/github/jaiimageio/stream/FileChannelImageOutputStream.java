package com.github.jaiimageio.stream;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStreamImpl;

/* loaded from: classes3.dex */
public class FileChannelImageOutputStream extends ImageOutputStreamImpl {
    private static final int DEFAULT_WRITE_BUFFER_SIZE = 1048576;
    private ByteBuffer byteBuffer;
    private FileChannel channel;
    private ImageInputStream readStream;

    public FileChannelImageOutputStream(FileChannel fileChannel) throws IOException {
        this.readStream = null;
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
        this.byteBuffer = ByteBuffer.allocateDirect(1048576);
        this.readStream = new FileChannelImageInputStream(fileChannel);
    }

    private ImageInputStream getImageInputStream() throws IOException {
        flushBuffer();
        this.readStream.setByteOrder(this.byteOrder);
        this.readStream.seek(this.streamPos);
        this.readStream.flushBefore(this.flushedPos);
        this.readStream.setBitOffset(this.bitOffset);
        return this.readStream;
    }

    private void flushBuffer() throws IOException {
        if (this.byteBuffer.position() != 0) {
            ByteBuffer byteBuffer = this.byteBuffer;
            byteBuffer.limit(byteBuffer.position());
            this.byteBuffer.position(0);
            this.channel.write(this.byteBuffer);
            this.byteBuffer.clear();
        }
    }

    public int read() throws IOException {
        checkClosed();
        this.bitOffset = 0;
        ImageInputStream imageInputStream = getImageInputStream();
        this.streamPos++;
        return imageInputStream.read();
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
        int read = getImageInputStream().read(bArr, i, i2);
        this.streamPos += read;
        return read;
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) (i & 255)}, 0, 1);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > b.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        do {
            int min = Math.min(i2 - i3, this.byteBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                this.byteBuffer.put(bArr, i + i3, min);
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2;
    }

    public void readFully(char[] cArr, int i, int i2) throws IOException {
        getImageInputStream().readFully(cArr, i, i2);
        this.streamPos += i2 * 2;
    }

    public void readFully(short[] sArr, int i, int i2) throws IOException {
        getImageInputStream().readFully(sArr, i, i2);
        this.streamPos += i2 * 2;
    }

    public void readFully(int[] iArr, int i, int i2) throws IOException {
        getImageInputStream().readFully(iArr, i, i2);
        this.streamPos += i2 * 4;
    }

    public void readFully(long[] jArr, int i, int i2) throws IOException {
        getImageInputStream().readFully(jArr, i, i2);
        this.streamPos += i2 * 8;
    }

    public void readFully(float[] fArr, int i, int i2) throws IOException {
        getImageInputStream().readFully(fArr, i, i2);
        this.streamPos += i2 * 4;
    }

    public void readFully(double[] dArr, int i, int i2) throws IOException {
        getImageInputStream().readFully(dArr, i, i2);
        this.streamPos += i2 * 8;
    }

    public void writeChars(char[] cArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > c.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        CharBuffer asCharBuffer = this.byteBuffer.asCharBuffer();
        do {
            int min = Math.min(i2 - i3, asCharBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                asCharBuffer.put(cArr, i + i3, min);
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + (min * 2));
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2 * 2;
    }

    public void writeShorts(short[] sArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > sArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > c.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        ShortBuffer asShortBuffer = this.byteBuffer.asShortBuffer();
        do {
            int min = Math.min(i2 - i3, asShortBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                asShortBuffer.put(sArr, i + i3, min);
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + (min * 2));
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2 * 2;
    }

    public void writeInts(int[] iArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > iArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > c.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        IntBuffer asIntBuffer = this.byteBuffer.asIntBuffer();
        do {
            int min = Math.min(i2 - i3, asIntBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                asIntBuffer.put(iArr, i + i3, min);
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + (min * 4));
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2 * 4;
    }

    public void writeLongs(long[] jArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > jArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > c.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        LongBuffer asLongBuffer = this.byteBuffer.asLongBuffer();
        do {
            int min = Math.min(i2 - i3, asLongBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                asLongBuffer.put(jArr, i + i3, min);
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + (min * 8));
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2 * 8;
    }

    public void writeFloats(float[] fArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > fArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > f.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        FloatBuffer asFloatBuffer = this.byteBuffer.asFloatBuffer();
        do {
            int min = Math.min(i2 - i3, asFloatBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                asFloatBuffer.put(fArr, i + i3, min);
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + (min * 4));
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2 * 4;
    }

    public void writeDoubles(double[] dArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > dArr.length) {
            throw new IndexOutOfBoundsException("off < 0 || len < 0 || off + len > d.length");
        }
        if (i2 == 0) {
            return;
        }
        flushBits();
        int i3 = 0;
        DoubleBuffer asDoubleBuffer = this.byteBuffer.asDoubleBuffer();
        do {
            int min = Math.min(i2 - i3, asDoubleBuffer.remaining());
            if (min == 0) {
                flushBuffer();
            } else {
                asDoubleBuffer.put(dArr, i + i3, min);
                ByteBuffer byteBuffer = this.byteBuffer;
                byteBuffer.position(byteBuffer.position() + (min * 8));
                i3 += min;
            }
        } while (i3 < i2);
        this.streamPos += i2 * 8;
    }

    public void close() throws IOException {
        flushBuffer();
        this.readStream.close();
        this.readStream = null;
        this.channel = null;
        this.byteBuffer = null;
        super.close();
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
        flushBuffer();
        this.channel.position(j);
    }

    public void setByteOrder(ByteOrder byteOrder) {
        super.setByteOrder(byteOrder);
        this.byteBuffer.order(byteOrder);
    }
}
