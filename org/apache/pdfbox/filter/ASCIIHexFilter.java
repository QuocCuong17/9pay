package org.apache.pdfbox.filter;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.util.Hex;

/* loaded from: classes5.dex */
final class ASCIIHexFilter extends Filter {
    private static final int[] REVERSE_HEX = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15};

    private static boolean isEOD(int i) {
        return i == 62;
    }

    private static boolean isWhitespace(int i) {
        return i == 0 || i == 9 || i == 10 || i == 12 || i == 13 || i == 32;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0077, code lost:
    
        r9.write(r11);
     */
    @Override // org.apache.pdfbox.filter.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            }
            while (isWhitespace(read)) {
                read = inputStream.read();
            }
            if (read == -1 || isEOD(read)) {
                break;
            }
            int[] iArr = REVERSE_HEX;
            if (iArr[read] == -1) {
                Log.e("PdfBoxAndroid", "Invalid hex, int: " + read + " char: " + ((char) read));
            }
            int i2 = iArr[read] * 16;
            int read2 = inputStream.read();
            if (read2 == -1 || isEOD(read2)) {
                break;
            }
            if (read2 >= 0) {
                if (iArr[read2] == -1) {
                    Log.e("PdfBoxAndroid", "Invalid hex, int: " + read2 + " char: " + ((char) read2));
                }
                i2 += iArr[read2];
            }
            outputStream.write(i2);
        }
        outputStream.flush();
        return new DecodeResult(cOSDictionary);
    }

    @Override // org.apache.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                outputStream.write(Hex.getBytes((byte) read));
            } else {
                outputStream.flush();
                return;
            }
        }
    }
}
