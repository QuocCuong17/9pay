package org.apache.pdfbox.io;

import java.io.IOException;

/* loaded from: classes5.dex */
public interface RandomAccess extends RandomAccessRead {
    void write(int i) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
