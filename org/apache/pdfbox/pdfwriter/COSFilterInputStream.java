package org.apache.pdfbox.pdfwriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class COSFilterInputStream extends FilterInputStream {
    private final int[] byteRange;
    private long position;

    public COSFilterInputStream(InputStream inputStream, int[] iArr) {
        super(inputStream);
        this.position = 0L;
        this.byteRange = iArr;
    }

    public COSFilterInputStream(byte[] bArr, int[] iArr) {
        super(new ByteArrayInputStream(bArr));
        this.position = 0L;
        this.byteRange = iArr;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        nextAvailable();
        int read = super.read();
        if (read > -1) {
            this.position++;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        bArr[i] = (byte) read;
        int i3 = 1;
        while (i3 < i2) {
            try {
                int read2 = read();
                if (read2 == -1) {
                    break;
                }
                bArr[i + i3] = (byte) read2;
                i3++;
            } catch (IOException unused) {
            }
        }
        return i3;
    }

    private boolean inRange() throws IOException {
        long j = this.position;
        int i = 0;
        while (true) {
            if (i >= this.byteRange.length / 2) {
                return false;
            }
            int i2 = i * 2;
            if (r4[i2] <= j && r4[i2] + r4[i2 + 1] > j) {
                return true;
            }
            i++;
        }
    }

    private void nextAvailable() throws IOException {
        while (!inRange()) {
            this.position++;
            if (super.read() < 0) {
                return;
            }
        }
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
