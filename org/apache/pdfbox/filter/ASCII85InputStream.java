package org.apache.pdfbox.filter;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
final class ASCII85InputStream extends FilterInputStream {
    private static final char NEWLINE = '\n';
    private static final char OFFSET = '!';
    private static final char PADDING_U = 'u';
    private static final char RETURN = '\r';
    private static final char SPACE = ' ';
    private static final char TERMINATOR = '~';
    private static final char Z = 'z';
    private byte[] ascii;
    private byte[] b;
    private boolean eof;
    private int index;
    private int n;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASCII85InputStream(InputStream inputStream) {
        super(inputStream);
        this.index = 0;
        this.n = 0;
        this.eof = false;
        this.ascii = new byte[5];
        this.b = new byte[4];
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0069, code lost:
    
        r13 = r14.ascii;
        r13[r2] = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
    
        if (r12 != 126) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006f, code lost:
    
        r13[r2] = 117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0075, code lost:
    
        r4 = r2 - 1;
        r14.n = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
    
        if (r4 != 0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007b, code lost:
    
        r14.eof = true;
        r14.ascii = null;
        r14.b = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
    
        if (r2 >= 5) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0084, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0085, code lost:
    
        if (r2 >= 5) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0087, code lost:
    
        r14.ascii[r2] = 117;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
    
        r14.eof = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0090, code lost:
    
        r1 = 0;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
    
        if (r4 >= 5) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
    
        r5 = (byte) (r14.ascii[r4] - 33);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009c, code lost:
    
        if (r5 < 0) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a0, code lost:
    
        if (r5 > 93) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a2, code lost:
    
        r1 = (r1 * 85) + r5;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00aa, code lost:
    
        r14.n = 0;
        r14.eof = true;
        r14.ascii = null;
        r14.b = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00b9, code lost:
    
        throw new java.io.IOException("Invalid data in Ascii85 stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ba, code lost:
    
        if (r10 < 0) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00bc, code lost:
    
        r14.b[r10] = (byte) (255 & r1);
        r1 = r1 >>> 8;
        r10 = r10 - 1;
     */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int read() throws IOException {
        if (this.index >= this.n) {
            if (this.eof) {
                return -1;
            }
            this.index = 0;
            while (true) {
                byte read = (byte) this.in.read();
                if (read == -1) {
                    this.eof = true;
                    return -1;
                }
                byte b = read;
                if (b != 10 && b != 13 && b != 32) {
                    if (b == 126) {
                        this.eof = true;
                        this.b = null;
                        this.ascii = null;
                        this.n = 0;
                        return -1;
                    }
                    int i = 3;
                    if (b == 122) {
                        byte[] bArr = this.b;
                        bArr[3] = 0;
                        bArr[2] = 0;
                        bArr[1] = 0;
                        bArr[0] = 0;
                        this.n = 4;
                    } else {
                        this.ascii[0] = b;
                        int i2 = 1;
                        while (true) {
                            if (i2 >= 5) {
                                break;
                            }
                            while (true) {
                                byte read2 = (byte) this.in.read();
                                if (read2 != -1) {
                                    byte b2 = read2;
                                    if (b2 != 10 && b2 != 13 && b2 != 32) {
                                        break;
                                    }
                                } else {
                                    this.eof = true;
                                    return -1;
                                }
                            }
                            i2++;
                        }
                    }
                }
            }
        }
        byte[] bArr2 = this.b;
        int i3 = this.index;
        this.index = i3 + 1;
        return bArr2[i3] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.eof && this.index >= this.n) {
            return -1;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.index;
            if (i4 < this.n) {
                byte[] bArr2 = this.b;
                this.index = i4 + 1;
                bArr[i3 + i] = bArr2[i4];
            } else {
                int read = read();
                if (read == -1) {
                    return i3;
                }
                bArr[i3 + i] = (byte) read;
            }
        }
        return i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ascii = null;
        this.eof = true;
        this.b = null;
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new IOException("Reset is not supported");
    }
}
