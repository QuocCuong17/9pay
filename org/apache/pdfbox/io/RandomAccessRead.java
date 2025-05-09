package org.apache.pdfbox.io;

import java.io.IOException;

/* loaded from: classes5.dex */
public interface RandomAccessRead extends SequentialRead {
    long getPosition() throws IOException;

    boolean isClosed();

    long length() throws IOException;

    void seek(long j) throws IOException;
}
