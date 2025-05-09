package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import org.apache.commons.io.IOUtils;

/* loaded from: classes5.dex */
public class ProxyCollectionWriter extends FilterCollectionWriter {
    protected void afterWrite(int i) throws IOException {
    }

    protected void beforeWrite(int i) throws IOException {
    }

    public ProxyCollectionWriter(Collection<Writer> collection) {
        super(collection);
    }

    public ProxyCollectionWriter(Writer... writerArr) {
        super(writerArr);
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        try {
            beforeWrite(1);
            super.append(c);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) throws IOException {
        try {
            int length = IOUtils.length(charSequence);
            beforeWrite(length);
            super.append(charSequence);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) throws IOException {
        int i3 = i2 - i;
        try {
            beforeWrite(i3);
            super.append(charSequence, i, i2);
            afterWrite(i3);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        try {
            super.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    protected void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer
    public void write(char[] cArr) throws IOException {
        try {
            int length = IOUtils.length(cArr);
            beforeWrite(length);
            super.write(cArr);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            super.write(cArr, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer
    public void write(int i) throws IOException {
        try {
            beforeWrite(1);
            super.write(i);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer
    public void write(String str) throws IOException {
        try {
            int length = IOUtils.length(str);
            beforeWrite(length);
            super.write(str);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // org.apache.commons.io.output.FilterCollectionWriter, java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            super.write(str, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }
}
