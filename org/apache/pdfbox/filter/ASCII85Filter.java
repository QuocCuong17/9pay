package org.apache.pdfbox.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: classes5.dex */
final class ASCII85Filter extends Filter {
    @Override // org.apache.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        ASCII85InputStream aSCII85InputStream = null;
        try {
            ASCII85InputStream aSCII85InputStream2 = new ASCII85InputStream(inputStream);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = aSCII85InputStream2.read(bArr, 0, 1024);
                    if (read != -1) {
                        outputStream.write(bArr, 0, read);
                    } else {
                        outputStream.flush();
                        IOUtils.closeQuietly(aSCII85InputStream2);
                        return new DecodeResult(cOSDictionary);
                    }
                }
            } catch (Throwable th) {
                th = th;
                aSCII85InputStream = aSCII85InputStream2;
                IOUtils.closeQuietly(aSCII85InputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        ASCII85OutputStream aSCII85OutputStream = new ASCII85OutputStream(outputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read != -1) {
                aSCII85OutputStream.write(bArr, 0, read);
            } else {
                aSCII85OutputStream.close();
                outputStream.flush();
                return;
            }
        }
    }
}
