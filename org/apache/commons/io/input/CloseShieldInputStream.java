package org.apache.commons.io.input;

import java.io.InputStream;

/* loaded from: classes5.dex */
public class CloseShieldInputStream extends ProxyInputStream {
    public CloseShieldInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.in = ClosedInputStream.CLOSED_INPUT_STREAM;
    }
}
