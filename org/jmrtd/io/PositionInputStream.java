package org.jmrtd.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/* loaded from: classes6.dex */
public class PositionInputStream extends InputStream {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long MARK_NOT_SET = -1;
    private InputStream carrier;
    private long position = 0;
    private long markedPosition = -1;

    public PositionInputStream(InputStream inputStream) {
        this.carrier = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.carrier.read();
        if (read >= 0) {
            this.position++;
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.carrier.read(bArr, i, i2);
        this.position += read;
        return read;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long skip = this.carrier.skip(j);
        if (skip <= 0) {
            LOGGER.warning("Carrier (" + this.carrier.getClass().getCanonicalName() + ")'s skip(" + j + ") only skipped " + skip + ", position = " + this.position);
        }
        this.position += skip;
        return skip;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.carrier.mark(i);
        this.markedPosition = this.position;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.carrier.reset();
        this.position = this.markedPosition;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.carrier.markSupported();
    }

    public long getPosition() {
        return this.position;
    }
}
