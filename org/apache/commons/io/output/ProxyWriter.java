package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;

/* loaded from: classes5.dex */
public class ProxyWriter extends FilterWriter {
    protected void afterWrite(int i) throws IOException {
    }

    protected void beforeWrite(int i) throws IOException {
    }

    public ProxyWriter(Writer writer) {
        super(writer);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        try {
            beforeWrite(1);
            this.out.append(c);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) throws IOException {
        int i3 = i2 - i;
        try {
            beforeWrite(i3);
            this.out.append(charSequence, i, i2);
            afterWrite(i3);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) throws IOException {
        try {
            int length = IOUtils.length(charSequence);
            beforeWrite(length);
            this.out.append(charSequence);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(i);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        try {
            int length = IOUtils.length(cArr);
            beforeWrite(length);
            this.out.write(cArr);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            this.out.write(cArr, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        try {
            int length = IOUtils.length(str);
            beforeWrite(length);
            this.out.write(str);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            this.out.write(str, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.out, new IOConsumer() { // from class: org.apache.commons.io.output.ProxyWriter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ProxyWriter.this.handleIOException((IOException) obj);
            }

            @Override // org.apache.commons.io.function.IOConsumer
            public /* synthetic */ IOConsumer andThen(IOConsumer iOConsumer) {
                return IOConsumer.CC.$default$andThen(this, iOConsumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }
}
