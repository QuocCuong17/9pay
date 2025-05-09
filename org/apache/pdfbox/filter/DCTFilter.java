package org.apache.pdfbox.filter;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: classes5.dex */
final class DCTFilter extends Filter {
    private static int clamp(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 255.0f) {
            f = 255.0f;
        }
        return (int) f;
    }

    @Override // org.apache.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        IOUtils.copy(inputStream, outputStream);
        return new DecodeResult(cOSDictionary);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        Log.w("PdfBoxAndroid", "DCTFilter#encode is not implemented yet, skipping this stream.");
    }
}
