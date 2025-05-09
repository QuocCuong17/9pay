package org.apache.pdfbox.filter;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.filter.ccitt.CCITTFaxG31DDecodeInputStream;
import org.apache.pdfbox.filter.ccitt.FillOrderChangeInputStream;
import org.apache.pdfbox.filter.ccitt.TIFFFaxDecoder;
import org.apache.pdfbox.io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class CCITTFaxFilter extends Filter {
    @Override // org.apache.pdfbox.filter.Filter
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        int max;
        byte[] bArr;
        DecodeResult decodeResult = new DecodeResult(new COSDictionary());
        decodeResult.getParameters().addAll(cOSDictionary);
        COSDictionary decodeParams = getDecodeParams(cOSDictionary, i);
        int i2 = decodeParams.getInt(COSName.COLUMNS, 1728);
        int i3 = decodeParams.getInt(COSName.ROWS, 0);
        int i4 = cOSDictionary.getInt(COSName.HEIGHT, COSName.H, 0);
        if (i3 > 0 && i4 > 0) {
            max = Math.min(i3, i4);
        } else {
            max = Math.max(i3, i4);
        }
        int i5 = max;
        int i6 = decodeParams.getInt(COSName.K, 0);
        boolean z = decodeParams.getBoolean(COSName.ENCODED_BYTE_ALIGN, false);
        int i7 = ((i2 + 7) / 8) * i5;
        TIFFFaxDecoder tIFFFaxDecoder = new TIFFFaxDecoder(1, i2, i5);
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        byte[] bArr2 = null;
        if (i6 == 0) {
            FillOrderChangeInputStream fillOrderChangeInputStream = new FillOrderChangeInputStream(new CCITTFaxG31DDecodeInputStream(new ByteArrayInputStream(byteArray), i2, z));
            bArr2 = IOUtils.toByteArray(fillOrderChangeInputStream);
            fillOrderChangeInputStream.close();
        } else {
            if (i6 > 0) {
                bArr = new byte[i7];
                tIFFFaxDecoder.decode2D(bArr, byteArray, 0, i5, 0L);
            } else if (i6 < 0) {
                bArr = new byte[i7];
                tIFFFaxDecoder.decodeT6(bArr, byteArray, 0, i5, 0L, z);
            }
            bArr2 = bArr;
        }
        if (!decodeParams.getBoolean(COSName.BLACK_IS_1, false)) {
            invertBitmap(bArr2);
        }
        if (!cOSDictionary.containsKey(COSName.COLORSPACE)) {
            decodeResult.getParameters().setName(COSName.COLORSPACE, COSName.DEVICEGRAY.getName());
        }
        outputStream.write(bArr2);
        return new DecodeResult(cOSDictionary);
    }

    private static void invertBitmap(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) ((~bArr[i]) & 255);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        Log.w("PdfBoxAndroid", "CCITTFaxDecode.encode is not implemented yet, skipping this stream.");
    }
}
