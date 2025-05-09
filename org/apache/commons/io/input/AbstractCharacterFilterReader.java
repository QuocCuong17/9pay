package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/* loaded from: classes5.dex */
public abstract class AbstractCharacterFilterReader extends FilterReader {
    protected abstract boolean filter(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCharacterFilterReader(Reader reader) {
        super(reader);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int read;
        do {
            read = this.in.read();
        } while (filter(read));
        return read;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int read = super.read(cArr, i, i2);
        if (read == -1) {
            return -1;
        }
        int i3 = i - 1;
        for (int i4 = i; i4 < i + read; i4++) {
            if (!filter(cArr[i4]) && (i3 = i3 + 1) < i4) {
                cArr[i3] = cArr[i4];
            }
        }
        return (i3 - i) + 1;
    }
}
