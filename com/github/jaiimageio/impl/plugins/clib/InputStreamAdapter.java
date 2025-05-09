package com.github.jaiimageio.impl.plugins.clib;

import java.io.IOException;
import java.io.InputStream;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public final class InputStreamAdapter extends InputStream {
    ImageInputStream stream;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public InputStreamAdapter(ImageInputStream imageInputStream) {
        this.stream = imageInputStream;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stream.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.stream.mark();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.stream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.stream.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.stream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.stream.skipBytes(j);
    }

    public ImageInputStream getWrappedStream() {
        return this.stream;
    }
}
