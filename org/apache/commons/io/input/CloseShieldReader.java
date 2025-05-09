package org.apache.commons.io.input;

import java.io.Reader;

/* loaded from: classes5.dex */
public class CloseShieldReader extends ProxyReader {
    public CloseShieldReader(Reader reader) {
        super(reader);
    }

    @Override // org.apache.commons.io.input.ProxyReader, java.io.FilterReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.in = ClosedReader.CLOSED_READER;
    }
}
