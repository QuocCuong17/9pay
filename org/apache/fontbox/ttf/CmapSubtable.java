package org.apache.fontbox.ttf;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CmapSubtable {
    private static final long LEAD_OFFSET = 55232;
    private static final long SURROGATE_OFFSET = -56613888;
    private Map<Integer, Integer> characterCodeToGlyphId = new HashMap();
    private int[] glyphIdToCharacterCode;
    private int platformEncodingId;
    private int platformId;
    private long subTableOffset;

    public void initData(TTFDataStream tTFDataStream) throws IOException {
        this.platformId = tTFDataStream.readUnsignedShort();
        this.platformEncodingId = tTFDataStream.readUnsignedShort();
        this.subTableOffset = tTFDataStream.readUnsignedInt();
    }

    public void initSubtable(CmapTable cmapTable, int i, TTFDataStream tTFDataStream) throws IOException {
        tTFDataStream.seek(cmapTable.getOffset() + this.subTableOffset);
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        if (readUnsignedShort < 8) {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
        } else {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedInt();
            tTFDataStream.readUnsignedInt();
        }
        if (readUnsignedShort == 0) {
            processSubtype0(tTFDataStream);
            return;
        }
        if (readUnsignedShort == 2) {
            processSubtype2(tTFDataStream, i);
            return;
        }
        if (readUnsignedShort == 4) {
            processSubtype4(tTFDataStream, i);
            return;
        }
        if (readUnsignedShort == 6) {
            processSubtype6(tTFDataStream, i);
            return;
        }
        if (readUnsignedShort == 8) {
            processSubtype8(tTFDataStream, i);
            return;
        }
        if (readUnsignedShort == 10) {
            processSubtype10(tTFDataStream, i);
            return;
        }
        switch (readUnsignedShort) {
            case 12:
                processSubtype12(tTFDataStream, i);
                return;
            case 13:
                processSubtype13(tTFDataStream, i);
                return;
            case 14:
                processSubtype14(tTFDataStream, i);
                return;
            default:
                throw new IOException("Unknown cmap format:" + readUnsignedShort);
        }
    }

    protected void processSubtype8(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] readUnsignedByteArray = tTFDataStream.readUnsignedByteArray(8192);
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        if (readUnsignedInt > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            throw new IOException("CMap ( Subtype8 ) is invalid");
        }
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        long j = 0;
        long j2 = 0;
        while (j2 < readUnsignedInt) {
            long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt3 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt4 = tTFDataStream.readUnsignedInt();
            if (readUnsignedInt2 > readUnsignedInt3 || j > readUnsignedInt2) {
                throw new IOException("Range invalid");
            }
            long j3 = readUnsignedInt2;
            while (j3 <= readUnsignedInt3) {
                if (j3 > 2147483647L) {
                    throw new IOException("[Sub Format 8] Invalid Character code");
                }
                long j4 = readUnsignedInt;
                int i2 = (int) j3;
                if ((readUnsignedByteArray[i2 / 8] & (1 << (i2 % 8))) != 0) {
                    long j5 = (((j3 >> 10) + LEAD_OFFSET) << 10) + (j3 & 1023) + 56320 + SURROGATE_OFFSET;
                    if (j5 > 2147483647L) {
                        throw new IOException("[Sub Format 8] Invalid Character code");
                    }
                    i2 = (int) j5;
                }
                int[] iArr = readUnsignedByteArray;
                long j6 = readUnsignedInt4 + (j3 - readUnsignedInt2);
                long j7 = readUnsignedInt2;
                if (j6 > i || j6 > 2147483647L) {
                    throw new IOException("CMap contains an invalid glyph index");
                }
                int i3 = (int) j6;
                this.glyphIdToCharacterCode[i3] = i2;
                this.characterCodeToGlyphId.put(Integer.valueOf(i2), Integer.valueOf(i3));
                j3++;
                readUnsignedByteArray = iArr;
                readUnsignedInt = j4;
                readUnsignedInt2 = j7;
            }
            j2++;
            readUnsignedInt = readUnsignedInt;
            j = 0;
        }
    }

    protected void processSubtype10(TTFDataStream tTFDataStream, int i) throws IOException {
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
        if (readUnsignedInt2 > 2147483647L) {
            throw new IOException("Invalid number of Characters");
        }
        if (readUnsignedInt >= 0 && readUnsignedInt <= 1114111) {
            long j = readUnsignedInt + readUnsignedInt2;
            if (j <= 1114111 && (j < 55296 || j > 57343)) {
                return;
            }
        }
        throw new IOException("Invalid Characters codes");
    }

    protected void processSubtype12(TTFDataStream tTFDataStream, int i) throws IOException {
        long j;
        int i2 = i;
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i2);
        long j2 = 0;
        long j3 = 0;
        while (j3 < readUnsignedInt) {
            long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt3 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt4 = tTFDataStream.readUnsignedInt();
            if (readUnsignedInt2 < j2 || readUnsignedInt2 > 1114111 || (readUnsignedInt2 >= 55296 && readUnsignedInt2 <= 57343)) {
                throw new IOException("Invalid characters codes");
            }
            if ((readUnsignedInt3 > j2 && readUnsignedInt3 < readUnsignedInt2) || readUnsignedInt3 > 1114111 || (readUnsignedInt3 >= 55296 && readUnsignedInt3 <= 57343)) {
                throw new IOException("Invalid characters codes");
            }
            long j4 = j2;
            while (j4 <= readUnsignedInt3 - readUnsignedInt2) {
                long j5 = readUnsignedInt4 + j4;
                long j6 = readUnsignedInt;
                if (j5 >= i2) {
                    throw new IOException("Character Code greater than Integer.MAX_VALUE");
                }
                long j7 = readUnsignedInt2 + j4;
                if (j7 > 1114111) {
                    j = readUnsignedInt2;
                    Log.w("PdfBoxAndroid", "Format 12 cmap contains character beyond UCS-4");
                } else {
                    j = readUnsignedInt2;
                }
                int i3 = (int) j5;
                int i4 = (int) j7;
                this.glyphIdToCharacterCode[i3] = i4;
                this.characterCodeToGlyphId.put(Integer.valueOf(i4), Integer.valueOf(i3));
                j4++;
                i2 = i;
                readUnsignedInt = j6;
                readUnsignedInt2 = j;
            }
            j3++;
            i2 = i;
            j2 = 0;
        }
    }

    protected void processSubtype13(TTFDataStream tTFDataStream, int i) throws IOException {
        long readUnsignedInt = tTFDataStream.readUnsignedInt();
        long j = 0;
        long j2 = 0;
        while (j2 < readUnsignedInt) {
            long readUnsignedInt2 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt3 = tTFDataStream.readUnsignedInt();
            long readUnsignedInt4 = tTFDataStream.readUnsignedInt();
            if (readUnsignedInt4 > i) {
                Log.w("PdfBoxAndroid", "Format 13 cmap contains an invalid glyph index");
                return;
            }
            if (readUnsignedInt2 < j || readUnsignedInt2 > 1114111 || (readUnsignedInt2 >= 55296 && readUnsignedInt2 <= 57343)) {
                throw new IOException("Invalid Characters codes");
            }
            if ((readUnsignedInt3 > 0 && readUnsignedInt3 < readUnsignedInt2) || readUnsignedInt3 > 1114111 || (readUnsignedInt3 >= 55296 && readUnsignedInt3 <= 57343)) {
                throw new IOException("Invalid Characters codes");
            }
            long j3 = 0;
            while (j3 <= readUnsignedInt3 - readUnsignedInt2) {
                long j4 = readUnsignedInt;
                long j5 = readUnsignedInt2 + j3;
                if (j5 > 2147483647L) {
                    throw new IOException("Character Code greater than Integer.MAX_VALUE");
                }
                if (j5 > 1114111) {
                    Log.w("PdfBoxAndroid", "Format 13 cmap contains character beyond UCS-4");
                }
                long j6 = readUnsignedInt2;
                int i2 = (int) readUnsignedInt4;
                int i3 = (int) j5;
                this.glyphIdToCharacterCode[i2] = i3;
                this.characterCodeToGlyphId.put(Integer.valueOf(i3), Integer.valueOf(i2));
                j3++;
                readUnsignedInt = j4;
                readUnsignedInt2 = j6;
            }
            j2++;
            j = 0;
        }
    }

    protected void processSubtype14(TTFDataStream tTFDataStream, int i) throws IOException {
        Log.w("PdfBoxAndroid", "Format 14 cmap table is not supported and will be ignored");
    }

    protected void processSubtype6(TTFDataStream tTFDataStream, int i) throws IOException {
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        int[] readUnsignedShortArray = tTFDataStream.readUnsignedShortArray(readUnsignedShort2);
        for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
            int i3 = readUnsignedShort + i2;
            this.glyphIdToCharacterCode[readUnsignedShortArray[i2]] = i3;
            this.characterCodeToGlyphId.put(Integer.valueOf(i3), Integer.valueOf(readUnsignedShortArray[i2]));
        }
    }

    protected void processSubtype4(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int readUnsignedShort = tTFDataStream.readUnsignedShort() / 2;
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        int[] readUnsignedShortArray = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        tTFDataStream.readUnsignedShort();
        int[] readUnsignedShortArray2 = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        int[] readUnsignedShortArray3 = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        int[] readUnsignedShortArray4 = tTFDataStream.readUnsignedShortArray(readUnsignedShort);
        HashMap hashMap = new HashMap();
        long currentPosition = tTFDataStream.getCurrentPosition();
        int i2 = 0;
        while (i2 < readUnsignedShort) {
            int i3 = readUnsignedShortArray2[i2];
            int i4 = readUnsignedShortArray[i2];
            int i5 = readUnsignedShortArray3[i2];
            int i6 = readUnsignedShortArray4[i2];
            if (i3 != 65535 && i4 != 65535) {
                int i7 = i3;
                while (i7 <= i4) {
                    if (i6 == 0) {
                        int i8 = (i7 + i5) % 65536;
                        iArr = readUnsignedShortArray;
                        iArr2 = readUnsignedShortArray2;
                        hashMap.put(Integer.valueOf(i8), Integer.valueOf(i7));
                        iArr3 = readUnsignedShortArray3;
                        this.characterCodeToGlyphId.put(Integer.valueOf(i7), Integer.valueOf(i8));
                    } else {
                        iArr = readUnsignedShortArray;
                        iArr2 = readUnsignedShortArray2;
                        iArr3 = readUnsignedShortArray3;
                        tTFDataStream.seek((((i6 / 2) + (i7 - i3) + (i2 - readUnsignedShort)) * 2) + currentPosition);
                        int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
                        if (readUnsignedShort2 != 0) {
                            int i9 = (readUnsignedShort2 + i5) % 65536;
                            if (!hashMap.containsKey(Integer.valueOf(i9))) {
                                hashMap.put(Integer.valueOf(i9), Integer.valueOf(i7));
                                this.characterCodeToGlyphId.put(Integer.valueOf(i7), Integer.valueOf(i9));
                            }
                        }
                    }
                    i7++;
                    readUnsignedShortArray = iArr;
                    readUnsignedShortArray2 = iArr2;
                    readUnsignedShortArray3 = iArr3;
                }
            }
            i2++;
            readUnsignedShortArray = readUnsignedShortArray;
            readUnsignedShortArray2 = readUnsignedShortArray2;
            readUnsignedShortArray3 = readUnsignedShortArray3;
        }
        if (hashMap.isEmpty()) {
            Log.w("PdfBoxAndroid", "cmap format 4 subtable is empty");
            return;
        }
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(((Integer) Collections.max(hashMap.keySet())).intValue() + 1);
        for (Map.Entry entry : hashMap.entrySet()) {
            this.glyphIdToCharacterCode[((Integer) entry.getKey()).intValue()] = ((Integer) entry.getValue()).intValue();
        }
    }

    protected void processSubtype2(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] iArr = new int[256];
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            iArr[i3] = tTFDataStream.readUnsignedShort();
            i2 = Math.max(i2, iArr[i3] / 8);
        }
        SubHeader[] subHeaderArr = new SubHeader[i2 + 1];
        for (int i4 = 0; i4 <= i2; i4++) {
            subHeaderArr[i4] = new SubHeader(tTFDataStream.readUnsignedShort(), tTFDataStream.readUnsignedShort(), tTFDataStream.readSignedShort(), (tTFDataStream.readUnsignedShort() - (((r0 - i4) - 1) * 8)) - 2);
        }
        long currentPosition = tTFDataStream.getCurrentPosition();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        for (int i5 = 0; i5 <= i2; i5++) {
            SubHeader subHeader = subHeaderArr[i5];
            int firstCode = subHeader.getFirstCode();
            int idRangeOffset = subHeader.getIdRangeOffset();
            short idDelta = subHeader.getIdDelta();
            int entryCount = subHeader.getEntryCount();
            tTFDataStream.seek(idRangeOffset + currentPosition);
            for (int i6 = 0; i6 < entryCount; i6++) {
                int i7 = (i5 << 8) + firstCode + i6;
                int readUnsignedShort = tTFDataStream.readUnsignedShort();
                if (readUnsignedShort > 0) {
                    readUnsignedShort = (readUnsignedShort + idDelta) % 65536;
                }
                this.glyphIdToCharacterCode[readUnsignedShort] = i7;
                this.characterCodeToGlyphId.put(Integer.valueOf(i7), Integer.valueOf(readUnsignedShort));
            }
        }
    }

    protected void processSubtype0(TTFDataStream tTFDataStream) throws IOException {
        byte[] read = tTFDataStream.read(256);
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(256);
        for (int i = 0; i < read.length; i++) {
            int i2 = (read[i] + 256) % 256;
            this.glyphIdToCharacterCode[i2] = i;
            this.characterCodeToGlyphId.put(Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    private int[] newGlyphIdToCharacterCode(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public int getPlatformEncodingId() {
        return this.platformEncodingId;
    }

    public void setPlatformEncodingId(int i) {
        this.platformEncodingId = i;
    }

    public int getPlatformId() {
        return this.platformId;
    }

    public void setPlatformId(int i) {
        this.platformId = i;
    }

    public int getGlyphId(int i) {
        Integer num = this.characterCodeToGlyphId.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public Integer getCharacterCode(int i) {
        int i2;
        if (i >= 0) {
            int[] iArr = this.glyphIdToCharacterCode;
            if (i < iArr.length && (i2 = iArr[i]) != -1) {
                return Integer.valueOf(i2);
            }
            return null;
        }
        return null;
    }

    public String toString() {
        return "{" + getPlatformId() + " " + getPlatformEncodingId() + "}";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class SubHeader {
        private final int entryCount;
        private final int firstCode;
        private final short idDelta;
        private final int idRangeOffset;

        private SubHeader(int i, int i2, short s, int i3) {
            this.firstCode = i;
            this.entryCount = i2;
            this.idDelta = s;
            this.idRangeOffset = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getFirstCode() {
            return this.firstCode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getEntryCount() {
            return this.entryCount;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public short getIdDelta() {
            return this.idDelta;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getIdRangeOffset() {
            return this.idRangeOffset;
        }
    }
}
