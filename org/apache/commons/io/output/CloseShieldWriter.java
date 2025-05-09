package org.apache.commons.io.output;

import java.io.Writer;

/* loaded from: classes5.dex */
public class CloseShieldWriter extends ProxyWriter {
    public CloseShieldWriter(Writer writer) {
        super(writer);
    }

    @Override // org.apache.commons.io.output.ProxyWriter, java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out = ClosedWriter.CLOSED_WRITER;
    }
}
