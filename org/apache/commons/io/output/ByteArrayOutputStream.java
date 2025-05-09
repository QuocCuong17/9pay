package org.apache.commons.io.output;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* loaded from: classes5.dex */
public class ByteArrayOutputStream extends AbstractByteArrayOutputStream {
    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        synchronized (this) {
            needNewBuffer(i);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        synchronized (this) {
            writeImpl(bArr, i, i2);
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public synchronized void write(int i) {
        writeImpl(i);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized int write(InputStream inputStream) throws IOException {
        return writeImpl(inputStream);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized int size() {
        return this.count;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized void reset() {
        resetImpl();
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized void writeTo(OutputStream outputStream) throws IOException {
        writeToImpl(outputStream);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return toBufferedInputStream(inputStream, 1024);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        try {
            byteArrayOutputStream.write(inputStream);
            InputStream inputStream2 = byteArrayOutputStream.toInputStream();
            byteArrayOutputStream.close();
            return inputStream2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized InputStream toInputStream() {
        return toInputStream(new AbstractByteArrayOutputStream.InputStreamConstructor() { // from class: org.apache.commons.io.output.ByteArrayOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream.InputStreamConstructor
            public final InputStream construct(byte[] bArr, int i, int i2) {
                return new ByteArrayInputStream(bArr, i, i2);
            }
        });
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public synchronized byte[] toByteArray() {
        return toByteArrayImpl();
    }
}
