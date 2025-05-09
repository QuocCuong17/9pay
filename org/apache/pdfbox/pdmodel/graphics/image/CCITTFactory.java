package org.apache.pdfbox.pdmodel.graphics.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccess;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;

/* loaded from: classes5.dex */
public final class CCITTFactory {
    private CCITTFactory() {
    }

    @Deprecated
    public static PDImageXObject createFromRandomAccess(PDDocument pDDocument, RandomAccess randomAccess) throws IOException {
        return createFromRandomAccessImpl(pDDocument, randomAccess, 0);
    }

    @Deprecated
    public static PDImageXObject createFromRandomAccess(PDDocument pDDocument, RandomAccess randomAccess, int i) throws IOException {
        return createFromRandomAccessImpl(pDDocument, randomAccess, i);
    }

    public static PDImageXObject createFromFile(PDDocument pDDocument, File file) throws IOException {
        return createFromRandomAccessImpl(pDDocument, new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER), 0);
    }

    public static PDImageXObject createFromFile(PDDocument pDDocument, File file, int i) throws IOException {
        return createFromRandomAccessImpl(pDDocument, new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER), i);
    }

    private static PDImageXObject createFromRandomAccessImpl(PDDocument pDDocument, RandomAccess randomAccess, int i) throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        extractFromTiff(randomAccess, byteArrayOutputStream, cOSDictionary, i);
        if (byteArrayOutputStream.size() == 0) {
            return null;
        }
        PDImageXObject pDImageXObject = new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.CCITTFAX_DECODE, cOSDictionary.getInt(COSName.COLUMNS), cOSDictionary.getInt(COSName.ROWS), 1, PDDeviceGray.INSTANCE);
        pDImageXObject.getCOSStream().setItem(COSName.DECODE_PARMS, (COSBase) cOSDictionary);
        return pDImageXObject;
    }

    private static void extractFromTiff(RandomAccess randomAccess, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        try {
            randomAccess.seek(0L);
            char read = (char) randomAccess.read();
            try {
                if (((char) randomAccess.read()) != read) {
                    throw new IOException("Not a valid tiff file");
                }
                if (read != 'M' && read != 'I') {
                    throw new IOException("Not a valid tiff file");
                }
                if (readshort(read, randomAccess) != 42) {
                    throw new IOException("Not a valid tiff file");
                }
                int readlong = readlong(read, randomAccess);
                randomAccess.seek(readlong);
                for (int i2 = 0; i2 < i; i2++) {
                    if (readshort(read, randomAccess) > 50) {
                        throw new IOException("Not a valid tiff file");
                    }
                    randomAccess.seek(readlong + 2 + (r10 * 12));
                    readlong = readlong(read, randomAccess);
                    if (readlong != 0) {
                        randomAccess.seek(readlong);
                    } else {
                        outputStream.close();
                        return;
                    }
                }
                int readshort = readshort(read, randomAccess);
                if (readshort > 50) {
                    throw new IOException("Not a valid tiff file");
                }
                int i3 = -1000;
                int i4 = 0;
                int i5 = 0;
                for (int i6 = 0; i6 < readshort; i6++) {
                    int readshort2 = readshort(read, randomAccess);
                    int readshort3 = readshort(read, randomAccess);
                    int readlong2 = readlong(read, randomAccess);
                    int readlong3 = readlong(read, randomAccess);
                    if (read == 'M') {
                        if (readshort3 == 1) {
                            readlong3 >>= 24;
                        } else if (readshort3 == 3) {
                            readlong3 >>= 16;
                        }
                    }
                    if (readshort2 == 256) {
                        cOSDictionary.setInt(COSName.COLUMNS, readlong3);
                    } else if (readshort2 == 257) {
                        cOSDictionary.setInt(COSName.ROWS, readlong3);
                    } else if (readshort2 == 259) {
                        if (readlong3 == 4) {
                            i3 = -1;
                        }
                        if (readlong3 == 3) {
                            i3 = 0;
                        }
                    } else if (readshort2 != 262) {
                        if (readshort2 == 273) {
                            if (readlong2 != 1) {
                            }
                            i4 = readlong3;
                        } else if (readshort2 == 279) {
                            if (readlong2 != 1) {
                            }
                            i5 = readlong3;
                        } else if (readshort2 == 292) {
                            if ((readlong3 & 1) != 0) {
                                i3 = 50;
                            }
                            if ((readlong3 & 4) != 0) {
                                throw new IOException("CCITT Group 3 'uncompressed mode' is not supported");
                            }
                            if ((readlong3 & 2) != 0) {
                                throw new IOException("CCITT Group 3 'fill bits before EOL' is not supported");
                            }
                        } else if (readshort2 != 324) {
                            if (readshort2 == 325 && readlong2 == 1) {
                                i5 = readlong3;
                            }
                        } else if (readlong2 == 1) {
                            i4 = readlong3;
                        }
                    } else if (readlong3 == 1) {
                        cOSDictionary.setBoolean(COSName.BLACK_IS_1, true);
                    }
                }
                if (i3 == -1000) {
                    throw new IOException("First image in tiff is not CCITT T4 or T6 compressed");
                }
                if (i4 == 0) {
                    throw new IOException("First image in tiff is not a single tile/strip");
                }
                cOSDictionary.setInt(COSName.K, i3);
                randomAccess.seek(i4);
                byte[] bArr = new byte[8192];
                while (true) {
                    int read2 = randomAccess.read(bArr, 0, Math.min(8192, i5));
                    if (read2 > 0) {
                        i5 -= read2;
                        outputStream.write(bArr, 0, read2);
                    } else {
                        outputStream.close();
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                outputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static int readshort(char c, RandomAccess randomAccess) throws IOException {
        int read;
        int read2;
        if (c == 'I') {
            read = randomAccess.read();
            read2 = randomAccess.read() << 8;
        } else {
            read = randomAccess.read() << 8;
            read2 = randomAccess.read();
        }
        return read | read2;
    }

    private static int readlong(char c, RandomAccess randomAccess) throws IOException {
        int read;
        int read2;
        if (c == 'I') {
            read = randomAccess.read() | (randomAccess.read() << 8) | (randomAccess.read() << 16);
            read2 = randomAccess.read() << 24;
        } else {
            read = (randomAccess.read() << 24) | (randomAccess.read() << 16) | (randomAccess.read() << 8);
            read2 = randomAccess.read();
        }
        return read | read2;
    }
}
