package org.apache.pdfbox.filter;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public abstract class Filter {
    public abstract DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException;

    public final void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        encode(inputStream, outputStream, cOSDictionary.asUnmodifiableDictionary());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static COSDictionary getDecodeParams(COSDictionary cOSDictionary, int i) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.DECODE_PARMS, COSName.DP);
        if (dictionaryObject instanceof COSDictionary) {
            return (COSDictionary) dictionaryObject;
        }
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            if (i < cOSArray.size()) {
                return (COSDictionary) cOSArray.getObject(i);
            }
        } else if (dictionaryObject != null) {
            Log.e("PdfBoxAndroid", "Expected DecodeParams to be an Array or Dictionary but found " + dictionaryObject.getClass().getName());
        }
        return new COSDictionary();
    }
}
