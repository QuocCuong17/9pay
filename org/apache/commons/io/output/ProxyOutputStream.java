package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;

/* loaded from: classes5.dex */
public class ProxyOutputStream extends FilterOutputStream {
    protected void afterWrite(int i) throws IOException {
    }

    protected void beforeWrite(int i) throws IOException {
    }

    public ProxyOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(i);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        try {
            int length = IOUtils.length(bArr);
            beforeWrite(length);
            this.out.write(bArr);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            this.out.write(bArr, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.out, new IOConsumer() { // from class: org.apache.commons.io.output.ProxyOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ProxyOutputStream.this.handleIOException((IOException) obj);
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
