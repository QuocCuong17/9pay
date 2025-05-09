package org.apache.pdfbox.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class CryptFilter extends Filter {
    @Override // org.apache.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        COSName cOSName = (COSName) cOSDictionary.getDictionaryObject(COSName.NAME);
        if (cOSName == null || cOSName.equals(COSName.IDENTITY)) {
            new IdentityFilter().decode(inputStream, outputStream, cOSDictionary, i);
            return new DecodeResult(cOSDictionary);
        }
        throw new IOException("Unsupported crypt filter " + cOSName.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        COSName cOSName = (COSName) cOSDictionary.getDictionaryObject(COSName.NAME);
        if (cOSName == null || cOSName.equals(COSName.IDENTITY)) {
            new IdentityFilter().encode(inputStream, outputStream, cOSDictionary);
            return;
        }
        throw new IOException("Unsupported crypt filter " + cOSName.getName());
    }
}
