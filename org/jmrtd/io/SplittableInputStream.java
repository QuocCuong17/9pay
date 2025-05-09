package org.jmrtd.io;

import java.io.IOException;
import java.io.InputStream;
import org.jmrtd.io.InputStreamBuffer;

/* loaded from: classes6.dex */
public class SplittableInputStream extends InputStream {
    private InputStreamBuffer.SubInputStream carrier;
    private InputStreamBuffer inputStreamBuffer;

    public SplittableInputStream(InputStream inputStream, int i) {
        InputStreamBuffer inputStreamBuffer = new InputStreamBuffer(inputStream, i);
        this.inputStreamBuffer = inputStreamBuffer;
        this.carrier = inputStreamBuffer.getInputStream();
    }

    public void updateFrom(SplittableInputStream splittableInputStream) {
        this.inputStreamBuffer.updateFrom(splittableInputStream.inputStreamBuffer);
    }

    public InputStream getInputStream(int i) {
        try {
            InputStreamBuffer.SubInputStream inputStream = this.inputStreamBuffer.getInputStream();
            long j = 0;
            while (true) {
                long j2 = i;
                if (j >= j2) {
                    return inputStream;
                }
                j += inputStream.skip(j2 - j);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public int getPosition() {
        return this.carrier.getPosition();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.carrier.read();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.carrier.skip(j);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.carrier.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.carrier.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.carrier.mark(i);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.carrier.reset();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.carrier.markSupported();
    }

    public int getLength() {
        return this.inputStreamBuffer.getLength();
    }

    public int getBytesBuffered() {
        return this.inputStreamBuffer.getBytesBuffered();
    }
}
