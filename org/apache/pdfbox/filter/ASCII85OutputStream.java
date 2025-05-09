package org.apache.pdfbox.filter;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
final class ASCII85OutputStream extends FilterOutputStream {
    private static final char NEWLINE = '\n';
    private static final char OFFSET = '!';
    private static final char Z = 'z';
    private int count;
    private boolean flushed;
    private byte[] indata;
    private int lineBreak;
    private int maxline;
    private byte[] outdata;
    private char terminator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASCII85OutputStream(OutputStream outputStream) {
        super(outputStream);
        this.lineBreak = 72;
        this.maxline = 72;
        this.count = 0;
        this.indata = new byte[4];
        this.outdata = new byte[5];
        this.flushed = true;
        this.terminator = '~';
    }

    public void setTerminator(char c) {
        if (c < 'v' || c > '~' || c == 'z') {
            throw new IllegalArgumentException("Terminator must be 118-126 excluding z");
        }
        this.terminator = c;
    }

    public char getTerminator() {
        return this.terminator;
    }

    public void setLineLength(int i) {
        if (this.lineBreak > i) {
            this.lineBreak = i;
        }
        this.maxline = i;
    }

    public int getLineLength() {
        return this.maxline;
    }

    private void transformASCII85() {
        byte[] bArr = this.indata;
        long j = ((bArr[3] & 255) | (((bArr[0] << 8) | (bArr[1] & 255)) << 16) | ((bArr[2] & 255) << 8)) & 4294967295L;
        if (j == 0) {
            byte[] bArr2 = this.outdata;
            bArr2[0] = 122;
            bArr2[1] = 0;
            return;
        }
        byte[] bArr3 = this.outdata;
        bArr3[0] = (byte) (r8 + 33);
        long j2 = j - (((((j / 52200625) * 85) * 85) * 85) * 85);
        bArr3[1] = (byte) (r8 + 33);
        long j3 = j2 - ((((j2 / 614125) * 85) * 85) * 85);
        bArr3[2] = (byte) (r8 + 33);
        long j4 = j3 - (((j3 / 7225) * 85) * 85);
        bArr3[3] = (byte) ((j4 / 85) + 33);
        bArr3[4] = (byte) ((j4 % 85) + 33);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        this.flushed = false;
        byte[] bArr = this.indata;
        int i2 = this.count;
        int i3 = i2 + 1;
        this.count = i3;
        bArr[i2] = (byte) i;
        if (i3 < 4) {
            return;
        }
        transformASCII85();
        for (int i4 = 0; i4 < 5 && this.outdata[i4] != 0; i4++) {
            this.out.write(this.outdata[i4]);
            int i5 = this.lineBreak - 1;
            this.lineBreak = i5;
            if (i5 == 0) {
                this.out.write(10);
                this.lineBreak = this.maxline;
            }
        }
        this.count = 0;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.flushed) {
            return;
        }
        int i = this.count;
        if (i > 0) {
            while (i < 4) {
                this.indata[i] = 0;
                i++;
            }
            transformASCII85();
            if (this.outdata[0] == 122) {
                for (int i2 = 0; i2 < 5; i2++) {
                    this.outdata[i2] = 33;
                }
            }
            for (int i3 = 0; i3 < this.count + 1; i3++) {
                this.out.write(this.outdata[i3]);
                int i4 = this.lineBreak - 1;
                this.lineBreak = i4;
                if (i4 == 0) {
                    this.out.write(10);
                    this.lineBreak = this.maxline;
                }
            }
        }
        int i5 = this.lineBreak - 1;
        this.lineBreak = i5;
        if (i5 == 0) {
            this.out.write(10);
        }
        this.out.write(this.terminator);
        this.out.write(10);
        this.count = 0;
        this.lineBreak = this.maxline;
        this.flushed = true;
        super.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            super.close();
        } finally {
            this.outdata = null;
            this.indata = null;
        }
    }
}
