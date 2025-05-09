package org.apache.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

/* loaded from: classes5.dex */
public final class JPEGFactory extends ImageFactory {
    private JPEGFactory() {
    }

    public static PDImageXObject createFromStream(PDDocument pDDocument, InputStream inputStream) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
        Bitmap readJPEG = readJPEG(byteArrayInputStream);
        byteArrayInputStream.reset();
        PDImageXObject pDImageXObject = new PDImageXObject(pDDocument, byteArrayInputStream, COSName.DCT_DECODE, readJPEG.getWidth(), readJPEG.getHeight(), 8, PDDeviceRGB.INSTANCE);
        if (readJPEG.hasAlpha()) {
            throw new UnsupportedOperationException("alpha channel not implemented");
        }
        return pDImageXObject;
    }

    private static Bitmap readJPEG(InputStream inputStream) throws IOException {
        return BitmapFactory.decodeStream(inputStream);
    }
}
