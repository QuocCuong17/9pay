package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/* loaded from: classes5.dex */
public class DemuxInputStream extends InputStream {
    private final InheritableThreadLocal<InputStream> inputStream = new InheritableThreadLocal<>();

    public InputStream bindStream(InputStream inputStream) {
        InputStream inputStream2 = this.inputStream.get();
        this.inputStream.set(inputStream);
        return inputStream2;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.inputStream.get());
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        InputStream inputStream = this.inputStream.get();
        if (inputStream != null) {
            return inputStream.read();
        }
        return -1;
    }
}
