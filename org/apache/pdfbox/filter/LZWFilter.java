package org.apache.pdfbox.filter;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.javax.imageio.stream.MemoryCacheImageInputStream;
import org.apache.javax.imageio.stream.MemoryCacheImageOutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class LZWFilter extends Filter {
    public static final long CLEAR_TABLE = 256;
    public static final long EOD = 257;

    private static int calculateChunk(int i, int i2) {
        if (i >= 2048 - i2) {
            return 12;
        }
        if (i >= 1024 - i2) {
            return 11;
        }
        return i >= 512 - i2 ? 10 : 9;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // org.apache.pdfbox.filter.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public DecodeResult decode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws IOException {
        int i2;
        int i3;
        int i4;
        COSDictionary decodeParams = getDecodeParams(cOSDictionary, i);
        if (decodeParams != null) {
            i2 = decodeParams.getInt(COSName.PREDICTOR);
            int i5 = decodeParams.getInt(COSName.EARLY_CHANGE, 1);
            if (i5 == 0 || i5 == 1) {
                i3 = i2;
                i4 = i5;
                if (i3 <= 1) {
                    int min = Math.min(decodeParams.getInt(COSName.COLORS, 1), 32);
                    int i6 = decodeParams.getInt(COSName.BITS_PER_COMPONENT, 8);
                    int i7 = decodeParams.getInt(COSName.COLUMNS, 1);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    doLZWDecode(inputStream, byteArrayOutputStream, i4);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    Predictor.decodePredictor(i3, min, i6, i7, byteArrayInputStream, outputStream);
                    outputStream.flush();
                    byteArrayOutputStream.reset();
                    byteArrayInputStream.reset();
                } else {
                    doLZWDecode(inputStream, outputStream, i4);
                }
                return new DecodeResult(cOSDictionary);
            }
        } else {
            i2 = -1;
        }
        i3 = i2;
        i4 = 1;
        if (i3 <= 1) {
        }
        return new DecodeResult(cOSDictionary);
    }

    private static void doLZWDecode(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        List arrayList = new ArrayList();
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(inputStream);
        loop0: while (true) {
            int i2 = 9;
            long j = -1;
            while (true) {
                try {
                    long readBits = memoryCacheImageInputStream.readBits(i2);
                    if (readBits == 257) {
                        break loop0;
                    }
                    if (readBits == 256) {
                        break;
                    }
                    if (readBits < arrayList.size()) {
                        byte[] bArr = (byte[]) arrayList.get((int) readBits);
                        byte b = bArr[0];
                        outputStream.write(bArr);
                        if (j != -1) {
                            byte[] bArr2 = (byte[]) arrayList.get((int) j);
                            byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length + 1);
                            copyOf[bArr2.length] = b;
                            arrayList.add(copyOf);
                        }
                    } else {
                        byte[] bArr3 = (byte[]) arrayList.get((int) j);
                        byte[] copyOf2 = Arrays.copyOf(bArr3, bArr3.length + 1);
                        copyOf2[bArr3.length] = bArr3[0];
                        outputStream.write(copyOf2);
                        arrayList.add(copyOf2);
                    }
                    i2 = calculateChunk(arrayList.size(), i);
                    j = readBits;
                } catch (EOFException unused) {
                    Log.w("PdfBoxAndroid", "Premature EOF in LZW stream, EOD code missing");
                }
            }
            arrayList = createCodeTable();
        }
        outputStream.flush();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.pdfbox.filter.Filter
    public void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        List<byte[]> createCodeTable = createCodeTable();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(outputStream);
        memoryCacheImageOutputStream.writeBits(256L, 9);
        byte[] bArr = null;
        int i = -1;
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            }
            byte b = (byte) read;
            if (bArr == null) {
                bArr = new byte[]{b};
            } else {
                bArr = Arrays.copyOf(bArr, bArr.length + 1);
                bArr[bArr.length - 1] = b;
                int findPatternCode = findPatternCode(createCodeTable, bArr);
                if (findPatternCode == -1) {
                    int calculateChunk = calculateChunk(createCodeTable.size() - 1, 1);
                    memoryCacheImageOutputStream.writeBits(i, calculateChunk);
                    createCodeTable.add(bArr);
                    if (createCodeTable.size() == 4096) {
                        memoryCacheImageOutputStream.writeBits(256L, calculateChunk);
                        createCodeTable = createCodeTable();
                    }
                    bArr = new byte[]{b};
                } else {
                    i = findPatternCode;
                }
            }
            i = b & 255;
        }
        if (i != -1) {
            memoryCacheImageOutputStream.writeBits(i, calculateChunk(createCodeTable.size() - 1, 1));
        }
        memoryCacheImageOutputStream.writeBits(257L, calculateChunk(createCodeTable.size(), 1));
        memoryCacheImageOutputStream.writeBits(0L, 7);
        memoryCacheImageOutputStream.flush();
    }

    private static int findPatternCode(List<byte[]> list, byte[] bArr) {
        int i = 0;
        int i2 = -1;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (size <= 257) {
                if (i2 != -1) {
                    return i2;
                }
                if (bArr.length > 1) {
                    return -1;
                }
            }
            byte[] bArr2 = list.get(size);
            if ((i2 != -1 || bArr2.length > i) && Arrays.equals(bArr2, bArr)) {
                i = bArr2.length;
                i2 = size;
            }
        }
        return i2;
    }

    private static List<byte[]> createCodeTable() {
        ArrayList arrayList = new ArrayList(4096);
        for (int i = 0; i < 256; i++) {
            arrayList.add(new byte[]{(byte) (i & 255)});
        }
        arrayList.add(null);
        arrayList.add(null);
        return arrayList;
    }
}
