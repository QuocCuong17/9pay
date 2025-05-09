package org.apache.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.common.PDMemoryStream;

/* loaded from: classes5.dex */
final class SampledImageReader {
    private SampledImageReader() {
    }

    public static Bitmap getRGBImage(PDImage pDImage, COSArray cOSArray) throws IOException {
        Log.e("PdfBoxAndroid", "getting image");
        if (pDImage.getStream() instanceof PDMemoryStream) {
            if (pDImage.getStream().getLength() == 0) {
                throw new IOException("Image stream is empty");
            }
        } else if (pDImage.getStream().getStream().getFilteredLength() == 0) {
            throw new IOException("Image stream is empty");
        }
        return BitmapFactory.decodeStream(pDImage.getStream().createInputStream());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static float[] getDecodeArray(PDImage pDImage) throws IOException {
        float[] fArr;
        COSArray decode = pDImage.getDecode();
        if (decode != null) {
            if (decode.size() != 16) {
                if (pDImage.isStencil() && decode.size() >= 2 && (decode.get(0) instanceof COSNumber) && (decode.get(1) instanceof COSNumber)) {
                    float floatValue = ((COSNumber) decode.get(0)).floatValue();
                    float floatValue2 = ((COSNumber) decode.get(1)).floatValue();
                    if (floatValue >= 0.0f && floatValue <= 1.0f && floatValue2 >= 0.0f && floatValue2 <= 1.0f) {
                        Log.w("PdfBoxAndroid", "decode array " + decode + " not compatible with color space, using the first two entries");
                        return new float[]{floatValue, floatValue2};
                    }
                }
                Log.e("PdfBoxAndroid", "decode array " + decode + " not compatible with color space, using default");
            } else {
                fArr = decode.toFloatArray();
                return fArr != null ? new float[]{0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f} : fArr;
            }
        }
        fArr = null;
        if (fArr != null) {
        }
    }
}
