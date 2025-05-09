package org.apache.commons.io.input;

import java.io.InputStream;
import java.util.Objects;

/* loaded from: classes5.dex */
public class CircularInputStream extends InputStream {
    private long byteCount;
    private int position = -1;
    private final byte[] repeatedContent;
    private final long targetByteCount;

    private static byte[] validate(byte[] bArr) {
        Objects.requireNonNull(bArr, "repeatContent");
        for (byte b : bArr) {
            if (b == -1) {
                throw new IllegalArgumentException("repeatContent contains the end-of-stream marker -1");
            }
        }
        return bArr;
    }

    public CircularInputStream(byte[] bArr, long j) {
        this.repeatedContent = validate(bArr);
        if (bArr.length == 0) {
            throw new IllegalArgumentException("repeatContent is empty.");
        }
        this.targetByteCount = j;
    }

    @Override // java.io.InputStream
    public int read() {
        long j = this.targetByteCount;
        if (j >= 0) {
            long j2 = this.byteCount;
            if (j2 == j) {
                return -1;
            }
            this.byteCount = j2 + 1;
        }
        int i = this.position + 1;
        byte[] bArr = this.repeatedContent;
        int length = i % bArr.length;
        this.position = length;
        return bArr[length] & 255;
    }
}
