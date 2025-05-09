package org.apache.pdfbox.pdfparser;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
class EndstreamOutputStream extends BufferedOutputStream {
    private boolean hasCR;
    private boolean hasLF;
    private boolean mustFilter;
    private int pos;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EndstreamOutputStream(OutputStream outputStream) {
        super(outputStream);
        this.hasCR = false;
        this.hasLF = false;
        this.pos = 0;
        this.mustFilter = true;
    }

    @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.pos == 0 && i2 > 10) {
            this.mustFilter = false;
            for (int i3 = 0; i3 < 10; i3++) {
                if (bArr[i3] < 9 || (bArr[i3] > 10 && bArr[i3] < 32 && bArr[i3] != 13)) {
                    this.mustFilter = true;
                    break;
                }
            }
        }
        if (this.mustFilter) {
            if (this.hasCR) {
                if (!this.hasLF && i2 == 1 && bArr[i] == 10) {
                    this.hasCR = false;
                    return;
                } else {
                    super.write(13);
                    this.hasCR = false;
                }
            }
            if (this.hasLF) {
                super.write(10);
                this.hasLF = false;
            }
            if (i2 > 0) {
                int i4 = (i + i2) - 1;
                if (bArr[i4] == 13) {
                    this.hasCR = true;
                } else if (bArr[i4] == 10) {
                    this.hasLF = true;
                    i2--;
                    if (i2 > 0 && bArr[(i + i2) - 1] == 13) {
                        this.hasCR = true;
                    }
                }
                i2--;
            }
        }
        super.write(bArr, i, i2);
        this.pos += i2;
    }

    @Override // java.io.BufferedOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.hasCR && !this.hasLF) {
            super.write(13);
            this.pos++;
        }
        this.hasCR = false;
        this.hasLF = false;
        super.flush();
    }
}
