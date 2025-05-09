package com.github.jaiimageio.impl.plugins.clib;

import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.stream.ImageOutputStream;

/* loaded from: classes3.dex */
public final class OutputStreamAdapter extends OutputStream {
    ImageOutputStream stream;

    public OutputStreamAdapter(ImageOutputStream imageOutputStream) {
        this.stream = imageOutputStream;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.stream.close();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.stream.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.stream.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.stream.write(i);
    }
}
