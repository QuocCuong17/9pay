package com.google.firebase.firestore.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public class ByteBufferInputStream extends InputStream {
    ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.buffer.remaining();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.buffer.hasRemaining()) {
            return this.buffer.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.buffer.hasRemaining()) {
            return -1;
        }
        int min = Math.min(i2, this.buffer.remaining());
        this.buffer.get(bArr, i, min);
        return min;
    }
}
