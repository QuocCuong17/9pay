package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* loaded from: classes5.dex */
public final class UnsynchronizedByteArrayOutputStream extends AbstractByteArrayOutputStream {
    public UnsynchronizedByteArrayOutputStream() {
        this(1024);
    }

    public UnsynchronizedByteArrayOutputStream(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        needNewBuffer(i);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException(String.format("offset=%,d, length=%,d", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (i2 == 0) {
            return;
        }
        writeImpl(bArr, i, i2);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream
    public void write(int i) {
        writeImpl(i);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public int write(InputStream inputStream) throws IOException {
        return writeImpl(inputStream);
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public int size() {
        return this.count;
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public void reset() {
        resetImpl();
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public void writeTo(OutputStream outputStream) throws IOException {
        writeToImpl(outputStream);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return toBufferedInputStream(inputStream, 1024);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream, int i) throws IOException {
        UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream(i);
        try {
            unsynchronizedByteArrayOutputStream.write(inputStream);
            InputStream inputStream2 = unsynchronizedByteArrayOutputStream.toInputStream();
            unsynchronizedByteArrayOutputStream.close();
            return inputStream2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    unsynchronizedByteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public InputStream toInputStream() {
        return toInputStream(new AbstractByteArrayOutputStream.InputStreamConstructor() { // from class: org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream.InputStreamConstructor
            public final InputStream construct(byte[] bArr, int i, int i2) {
                return new UnsynchronizedByteArrayInputStream(bArr, i, i2);
            }
        });
    }

    @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream
    public byte[] toByteArray() {
        return toByteArrayImpl();
    }
}
