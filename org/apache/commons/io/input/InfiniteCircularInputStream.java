package org.apache.commons.io.input;

/* loaded from: classes5.dex */
public class InfiniteCircularInputStream extends CircularInputStream {
    public InfiniteCircularInputStream(byte[] bArr) {
        super(bArr, -1L);
    }
}
