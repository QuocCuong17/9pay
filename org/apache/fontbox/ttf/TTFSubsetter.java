package org.apache.fontbox.ttf;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes5.dex */
public final class TTFSubsetter {
    private static final byte[] PAD_BUF = {0, 0, 0};
    private final SortedSet<Integer> glyphIds;
    private boolean hasAddedCompoundReferences;
    private final List<String> keepTables;
    private String prefix;
    private final TrueTypeFont ttf;
    private final SortedMap<Integer, Integer> uniToGID;
    private final CmapSubtable unicodeCmap;

    private static long toUInt32(int i, int i2) {
        return (i2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) | ((i & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16);
    }

    public TTFSubsetter(TrueTypeFont trueTypeFont) throws IOException {
        this(trueTypeFont, null);
    }

    public TTFSubsetter(TrueTypeFont trueTypeFont, List<String> list) throws IOException {
        this.ttf = trueTypeFont;
        this.keepTables = list;
        this.uniToGID = new TreeMap();
        TreeSet treeSet = new TreeSet();
        this.glyphIds = treeSet;
        this.unicodeCmap = trueTypeFont.getUnicodeCmap();
        treeSet.add(0);
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void add(int i) {
        int glyphId = this.unicodeCmap.getGlyphId(i);
        if (glyphId != 0) {
            this.uniToGID.put(Integer.valueOf(i), Integer.valueOf(glyphId));
            this.glyphIds.add(Integer.valueOf(glyphId));
        }
    }

    public void addAll(Set<Integer> set) {
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            add(it.next().intValue());
        }
    }

    public Map<Integer, Integer> getGIDMap() throws IOException {
        addCompoundReferences();
        HashMap hashMap = new HashMap();
        Iterator<Integer> it = this.glyphIds.iterator();
        int i = 0;
        while (it.hasNext()) {
            hashMap.put(Integer.valueOf(i), Integer.valueOf(it.next().intValue()));
            i++;
        }
        return hashMap;
    }

    private static long writeFileHeader(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeInt(65536);
        dataOutputStream.writeShort(i);
        int highestOneBit = Integer.highestOneBit(i);
        int i2 = highestOneBit * 16;
        dataOutputStream.writeShort(i2);
        int log2 = log2(highestOneBit);
        dataOutputStream.writeShort(log2);
        int i3 = (i * 16) - i2;
        dataOutputStream.writeShort(i3);
        return toUInt32(i, i2) + PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH + toUInt32(log2, i3);
    }

    private static long writeTableHeader(DataOutputStream dataOutputStream, String str, long j, byte[] bArr) throws IOException {
        long j2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            j2 += (bArr[i] & 255) << (24 - ((i % 4) * 8));
        }
        long j3 = j2 & 4294967295L;
        byte[] bytes = str.getBytes("US-ASCII");
        dataOutputStream.write(bytes, 0, 4);
        dataOutputStream.writeInt((int) j3);
        dataOutputStream.writeInt((int) j);
        dataOutputStream.writeInt(bArr.length);
        return toUInt32(bytes) + j3 + j3 + j + bArr.length;
    }

    private static void writeTableBody(OutputStream outputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        outputStream.write(bArr);
        int i = length % 4;
        if (i != 0) {
            outputStream.write(PAD_BUF, 0, 4 - i);
        }
    }

    private byte[] buildHeadTable() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        HeaderTable header = this.ttf.getHeader();
        writeFixed(dataOutputStream, header.getVersion());
        writeFixed(dataOutputStream, header.getFontRevision());
        writeUint32(dataOutputStream, 0L);
        writeUint32(dataOutputStream, header.getMagicNumber());
        writeUint16(dataOutputStream, header.getFlags());
        writeUint16(dataOutputStream, header.getUnitsPerEm());
        writeLongDateTime(dataOutputStream, header.getCreated());
        writeLongDateTime(dataOutputStream, header.getModified());
        writeSInt16(dataOutputStream, header.getXMin());
        writeSInt16(dataOutputStream, header.getYMin());
        writeSInt16(dataOutputStream, header.getXMax());
        writeSInt16(dataOutputStream, header.getYMax());
        writeUint16(dataOutputStream, header.getMacStyle());
        writeUint16(dataOutputStream, header.getLowestRecPPEM());
        writeSInt16(dataOutputStream, header.getFontDirectionHint());
        writeSInt16(dataOutputStream, (short) 1);
        writeSInt16(dataOutputStream, header.getGlyphDataFormat());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildHheaTable() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        HorizontalHeaderTable horizontalHeader = this.ttf.getHorizontalHeader();
        writeFixed(dataOutputStream, horizontalHeader.getVersion());
        writeSInt16(dataOutputStream, horizontalHeader.getAscender());
        writeSInt16(dataOutputStream, horizontalHeader.getDescender());
        writeSInt16(dataOutputStream, horizontalHeader.getLineGap());
        writeUint16(dataOutputStream, horizontalHeader.getAdvanceWidthMax());
        writeSInt16(dataOutputStream, horizontalHeader.getMinLeftSideBearing());
        writeSInt16(dataOutputStream, horizontalHeader.getMinRightSideBearing());
        writeSInt16(dataOutputStream, horizontalHeader.getXMaxExtent());
        writeSInt16(dataOutputStream, horizontalHeader.getCaretSlopeRise());
        writeSInt16(dataOutputStream, horizontalHeader.getCaretSlopeRun());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved1());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved2());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved3());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved4());
        writeSInt16(dataOutputStream, horizontalHeader.getReserved5());
        writeSInt16(dataOutputStream, horizontalHeader.getMetricDataFormat());
        writeUint16(dataOutputStream, this.glyphIds.subSet(0, Integer.valueOf(horizontalHeader.getNumberOfHMetrics())).size());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private static boolean shouldCopyNameRecord(NameRecord nameRecord) {
        return nameRecord.getPlatformId() == 3 && nameRecord.getPlatformEncodingId() == 1 && nameRecord.getLanguageId() == 1033 && nameRecord.getNameId() >= 0 && nameRecord.getNameId() < 7;
    }

    private byte[] buildNameTable() throws IOException {
        List<String> list;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        NamingTable naming = this.ttf.getNaming();
        if (naming == null || !((list = this.keepTables) == null || list.contains("name"))) {
            return null;
        }
        List<NameRecord> nameRecords = naming.getNameRecords();
        Iterator<NameRecord> it = nameRecords.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (shouldCopyNameRecord(it.next())) {
                i++;
            }
        }
        writeUint16(dataOutputStream, 0);
        writeUint16(dataOutputStream, i);
        writeUint16(dataOutputStream, (i * 12) + 6);
        if (i == 0) {
            return null;
        }
        byte[][] bArr = new byte[i];
        int i2 = 0;
        for (NameRecord nameRecord : nameRecords) {
            if (shouldCopyNameRecord(nameRecord)) {
                int platformId = nameRecord.getPlatformId();
                int platformEncodingId = nameRecord.getPlatformEncodingId();
                String str = "ISO-8859-1";
                if (platformId == 3 && platformEncodingId == 1) {
                    str = "UTF-16BE";
                } else if (platformId == 2) {
                    if (platformEncodingId == 0) {
                        str = "US-ASCII";
                    } else if (platformEncodingId == 1) {
                        str = "UTF16-BE";
                    }
                }
                String string = nameRecord.getString();
                if (nameRecord.getNameId() == 6 && this.prefix != null) {
                    string = this.prefix + string;
                }
                bArr[i2] = string.getBytes(str);
                i2++;
            }
        }
        int i3 = 0;
        int i4 = 0;
        for (NameRecord nameRecord2 : nameRecords) {
            if (shouldCopyNameRecord(nameRecord2)) {
                writeUint16(dataOutputStream, nameRecord2.getPlatformId());
                writeUint16(dataOutputStream, nameRecord2.getPlatformEncodingId());
                writeUint16(dataOutputStream, nameRecord2.getLanguageId());
                writeUint16(dataOutputStream, nameRecord2.getNameId());
                writeUint16(dataOutputStream, bArr[i3].length);
                writeUint16(dataOutputStream, i4);
                i4 += bArr[i3].length;
                i3++;
            }
        }
        for (int i5 = 0; i5 < i; i5++) {
            dataOutputStream.write(bArr[i5]);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildMaxpTable() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        MaximumProfileTable maximumProfile = this.ttf.getMaximumProfile();
        writeFixed(dataOutputStream, 1.0d);
        writeUint16(dataOutputStream, this.glyphIds.size());
        writeUint16(dataOutputStream, maximumProfile.getMaxPoints());
        writeUint16(dataOutputStream, maximumProfile.getMaxContours());
        writeUint16(dataOutputStream, maximumProfile.getMaxCompositePoints());
        writeUint16(dataOutputStream, maximumProfile.getMaxCompositeContours());
        writeUint16(dataOutputStream, maximumProfile.getMaxZones());
        writeUint16(dataOutputStream, maximumProfile.getMaxTwilightPoints());
        writeUint16(dataOutputStream, maximumProfile.getMaxStorage());
        writeUint16(dataOutputStream, maximumProfile.getMaxFunctionDefs());
        writeUint16(dataOutputStream, maximumProfile.getMaxInstructionDefs());
        writeUint16(dataOutputStream, maximumProfile.getMaxStackElements());
        writeUint16(dataOutputStream, maximumProfile.getMaxSizeOfInstructions());
        writeUint16(dataOutputStream, maximumProfile.getMaxComponentElements());
        writeUint16(dataOutputStream, maximumProfile.getMaxComponentDepth());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildOS2Table() throws IOException {
        OS2WindowsMetricsTable oS2Windows = this.ttf.getOS2Windows();
        if (oS2Windows == null) {
            return null;
        }
        List<String> list = this.keepTables;
        if (list != null && !list.contains(OS2WindowsMetricsTable.TAG)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        writeUint16(dataOutputStream, oS2Windows.getVersion());
        writeSInt16(dataOutputStream, oS2Windows.getAverageCharWidth());
        writeUint16(dataOutputStream, oS2Windows.getWeightClass());
        writeUint16(dataOutputStream, oS2Windows.getWidthClass());
        writeSInt16(dataOutputStream, oS2Windows.getFsType());
        writeSInt16(dataOutputStream, oS2Windows.getSubscriptXSize());
        writeSInt16(dataOutputStream, oS2Windows.getSubscriptYSize());
        writeSInt16(dataOutputStream, oS2Windows.getSubscriptXOffset());
        writeSInt16(dataOutputStream, oS2Windows.getSubscriptYOffset());
        writeSInt16(dataOutputStream, oS2Windows.getSuperscriptXSize());
        writeSInt16(dataOutputStream, oS2Windows.getSuperscriptYSize());
        writeSInt16(dataOutputStream, oS2Windows.getSuperscriptXOffset());
        writeSInt16(dataOutputStream, oS2Windows.getSuperscriptYOffset());
        writeSInt16(dataOutputStream, oS2Windows.getStrikeoutSize());
        writeSInt16(dataOutputStream, oS2Windows.getStrikeoutPosition());
        writeUint8(dataOutputStream, oS2Windows.getFamilyClass());
        writeUint8(dataOutputStream, oS2Windows.getFamilySubClass());
        dataOutputStream.write(oS2Windows.getPanose());
        writeUint32(dataOutputStream, 0L);
        writeUint32(dataOutputStream, 0L);
        writeUint32(dataOutputStream, 0L);
        writeUint32(dataOutputStream, 0L);
        dataOutputStream.write(oS2Windows.getAchVendId().getBytes("US-ASCII"));
        Iterator<Map.Entry<Integer, Integer>> it = this.uniToGID.entrySet().iterator();
        it.next();
        Map.Entry<Integer, Integer> next = it.next();
        writeUint16(dataOutputStream, oS2Windows.getFsSelection());
        writeUint16(dataOutputStream, next.getKey().intValue());
        writeUint16(dataOutputStream, this.uniToGID.lastKey().intValue());
        writeUint16(dataOutputStream, oS2Windows.getTypoAscender());
        writeUint16(dataOutputStream, oS2Windows.getTypoDescender());
        writeUint16(dataOutputStream, oS2Windows.getTypoLineGap());
        writeUint16(dataOutputStream, oS2Windows.getWinAscent());
        writeUint16(dataOutputStream, oS2Windows.getWinDescent());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] buildLocaTable(long[] jArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (long j : jArr) {
            writeUint32(dataOutputStream, j);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private void addCompoundReferences() throws IOException {
        boolean z;
        int i;
        if (this.hasAddedCompoundReferences) {
            return;
        }
        this.hasAddedCompoundReferences = true;
        do {
            GlyphTable glyph = this.ttf.getGlyph();
            long[] offsets = this.ttf.getIndexToLocation().getOffsets();
            InputStream originalData = this.ttf.getOriginalData();
            TreeSet treeSet = null;
            try {
                originalData.skip(glyph.getOffset());
                long j = 0;
                Iterator<Integer> it = this.glyphIds.iterator();
                while (true) {
                    z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    Integer next = it.next();
                    long j2 = offsets[next.intValue()];
                    long j3 = offsets[next.intValue() + 1] - j2;
                    originalData.skip(j2 - j);
                    int i2 = (int) j3;
                    byte[] bArr = new byte[i2];
                    originalData.read(bArr);
                    if (i2 >= 2 && bArr[0] == -1 && bArr[1] == -1) {
                        int i3 = 10;
                        do {
                            i = ((bArr[i3] & 255) << 8) | (bArr[i3 + 1] & 255);
                            int i4 = i3 + 2;
                            int i5 = ((bArr[i4] & 255) << 8) | (bArr[i4 + 1] & 255);
                            if (!this.glyphIds.contains(Integer.valueOf(i5))) {
                                if (treeSet == null) {
                                    treeSet = new TreeSet();
                                }
                                treeSet.add(Integer.valueOf(i5));
                            }
                            int i6 = i4 + 2;
                            i3 = (i & 1) != 0 ? i6 + 4 : i6 + 2;
                            if ((i & 128) != 0) {
                                i3 += 8;
                            } else if ((i & 64) != 0) {
                                i3 += 4;
                            } else if ((i & 8) != 0) {
                                i3 += 2;
                            }
                        } while ((i & 32) != 0);
                    }
                    j = offsets[next.intValue() + 1];
                }
                if (treeSet != null) {
                    this.glyphIds.addAll(treeSet);
                }
                if (treeSet != null) {
                    z = true;
                }
            } finally {
                originalData.close();
            }
        } while (z);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x010b A[Catch: all -> 0x012e, TryCatch #0 {all -> 0x012e, blocks: (B:3:0x001d, B:4:0x0030, B:6:0x0036, B:8:0x0060, B:10:0x0065, B:13:0x006b, B:15:0x0097, B:16:0x00a0, B:18:0x00b6, B:19:0x00bb, B:21:0x00bf, B:22:0x00cf, B:26:0x00d3, B:28:0x00d9, B:29:0x00e9, B:30:0x0100, B:31:0x0101, B:33:0x010b, B:35:0x011a, B:38:0x00c2, B:40:0x00c6, B:41:0x00c9, B:43:0x00cd, B:44:0x00b9, B:47:0x00fb, B:49:0x0124), top: B:2:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0119  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private byte[] buildGlyfTable(long[] jArr) throws IOException {
        Iterator<Integer> it;
        long[] jArr2;
        long j;
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GlyphTable glyph = this.ttf.getGlyph();
        long[] offsets = this.ttf.getIndexToLocation().getOffsets();
        InputStream originalData = this.ttf.getOriginalData();
        try {
            originalData.skip(glyph.getOffset());
            Iterator<Integer> it2 = this.glyphIds.iterator();
            char c = 0;
            int i2 = 0;
            long j2 = 0;
            long j3 = 0;
            while (it2.hasNext()) {
                Integer next = it2.next();
                long j4 = offsets[next.intValue()];
                long j5 = offsets[next.intValue() + 1] - j4;
                int i3 = i2 + 1;
                jArr[i2] = j2;
                originalData.skip(j4 - j3);
                int i4 = (int) j5;
                byte[] bArr = new byte[i4];
                originalData.read(bArr);
                if (i4 >= 2 && bArr[c] == -1 && bArr[1] == -1) {
                    int i5 = 10;
                    while (true) {
                        i = ((bArr[i5] & 255) << 8) | (bArr[i5 + 1] & 255);
                        int i6 = i5 + 2;
                        int i7 = i6 + 1;
                        it = it2;
                        int i8 = (bArr[i7] & 255) | ((bArr[i6] & 255) << 8);
                        jArr2 = offsets;
                        if (!this.glyphIds.contains(Integer.valueOf(i8))) {
                            this.glyphIds.add(Integer.valueOf(i8));
                        }
                        int newGlyphId = getNewGlyphId(Integer.valueOf(i8));
                        bArr[i6] = (byte) (newGlyphId >>> 8);
                        bArr[i7] = (byte) newGlyphId;
                        int i9 = i6 + 2;
                        i5 = (i & 1) != 0 ? i9 + 4 : i9 + 2;
                        if ((i & 128) != 0) {
                            i5 += 8;
                        } else if ((i & 64) != 0) {
                            i5 += 4;
                        } else if ((i & 8) != 0) {
                            i5 += 2;
                        }
                        if ((i & 32) == 0) {
                            break;
                        }
                        it2 = it;
                        offsets = jArr2;
                    }
                    if ((i & 256) == 256) {
                        i5 = i5 + 2 + (((bArr[i5] & 255) << 8) | (bArr[i5 + 1] & 255));
                    }
                    byteArrayOutputStream.write(bArr, 0, i5);
                    j = i5;
                } else {
                    it = it2;
                    jArr2 = offsets;
                    if (i4 > 0) {
                        byteArrayOutputStream.write(bArr, 0, i4);
                        j = i4;
                    }
                    if (j2 % 4 == 0) {
                        int i10 = 4 - ((int) (j2 % 4));
                        c = 0;
                        byteArrayOutputStream.write(PAD_BUF, 0, i10);
                        j2 += i10;
                    } else {
                        c = 0;
                    }
                    j3 = j4 + j5;
                    i2 = i3;
                    it2 = it;
                    offsets = jArr2;
                }
                j2 += j;
                if (j2 % 4 == 0) {
                }
                j3 = j4 + j5;
                i2 = i3;
                it2 = it;
                offsets = jArr2;
            }
            jArr[i2] = j2;
            originalData.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            originalData.close();
            throw th;
        }
    }

    private int getNewGlyphId(Integer num) {
        return this.glyphIds.headSet(num).size();
    }

    private byte[] buildCmapTable() throws IOException {
        if (this.ttf.getCmap() == null) {
            return null;
        }
        List<String> list = this.keepTables;
        if (list != null && !list.contains(CmapTable.TAG)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        writeUint16(dataOutputStream, 0);
        writeUint16(dataOutputStream, 1);
        writeUint16(dataOutputStream, 3);
        writeUint16(dataOutputStream, 1);
        writeUint32(dataOutputStream, 12L);
        Iterator<Map.Entry<Integer, Integer>> it = this.uniToGID.entrySet().iterator();
        it.next();
        Map.Entry<Integer, Integer> next = it.next();
        int newGlyphId = getNewGlyphId(next.getValue());
        int[] iArr = new int[this.uniToGID.size()];
        int[] iArr2 = new int[this.uniToGID.size()];
        int[] iArr3 = new int[this.uniToGID.size()];
        int i = 0;
        int i2 = newGlyphId;
        Map.Entry<Integer, Integer> entry = next;
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next2 = it.next();
            int newGlyphId2 = getNewGlyphId(next2.getValue());
            if (next2.getKey().intValue() > 65535) {
                throw new UnsupportedOperationException("non-BMP Unicode character");
            }
            if (next2.getKey().intValue() != entry.getKey().intValue() + 1 || newGlyphId2 - i2 != next2.getKey().intValue() - next.getKey().intValue()) {
                if (i2 != 0) {
                    iArr[i] = next.getKey().intValue();
                    iArr2[i] = entry.getKey().intValue();
                    iArr3[i] = i2 - next.getKey().intValue();
                } else {
                    if (!next.getKey().equals(entry.getKey())) {
                        iArr[i] = next.getKey().intValue() + 1;
                        iArr2[i] = entry.getKey().intValue();
                        iArr3[i] = i2 - next.getKey().intValue();
                    }
                    next = next2;
                    i2 = newGlyphId2;
                }
                i++;
                next = next2;
                i2 = newGlyphId2;
            }
            entry = next2;
        }
        iArr[i] = next.getKey().intValue();
        iArr2[i] = entry.getKey().intValue();
        iArr3[i] = i2 - next.getKey().intValue();
        int i3 = i + 1;
        iArr[i3] = 65535;
        iArr2[i3] = 65535;
        iArr3[i3] = 1;
        int i4 = i3 + 1;
        int pow = ((int) Math.pow(2.0d, Math.floor(log2(i4)))) * 2;
        writeUint16(dataOutputStream, 4);
        writeUint16(dataOutputStream, (i4 * 4 * 2) + 16);
        writeUint16(dataOutputStream, 0);
        int i5 = i4 * 2;
        writeUint16(dataOutputStream, i5);
        writeUint16(dataOutputStream, pow);
        writeUint16(dataOutputStream, log2(pow / 2));
        writeUint16(dataOutputStream, i5 - pow);
        for (int i6 = 0; i6 < i4; i6++) {
            writeUint16(dataOutputStream, iArr2[i6]);
        }
        writeUint16(dataOutputStream, 0);
        for (int i7 = 0; i7 < i4; i7++) {
            writeUint16(dataOutputStream, iArr[i7]);
        }
        for (int i8 = 0; i8 < i4; i8++) {
            writeUint16(dataOutputStream, iArr3[i8]);
        }
        for (int i9 = 0; i9 < i4; i9++) {
            writeUint16(dataOutputStream, 0);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildPostTable() throws IOException {
        PostScriptTable postScript = this.ttf.getPostScript();
        if (postScript == null) {
            return null;
        }
        List<String> list = this.keepTables;
        if (list != null && !list.contains("post")) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        writeFixed(dataOutputStream, 2.0d);
        writeFixed(dataOutputStream, postScript.getItalicAngle());
        writeSInt16(dataOutputStream, postScript.getUnderlinePosition());
        writeSInt16(dataOutputStream, postScript.getUnderlineThickness());
        writeUint32(dataOutputStream, postScript.getIsFixedPitch());
        writeUint32(dataOutputStream, postScript.getMinMemType42());
        writeUint32(dataOutputStream, postScript.getMaxMemType42());
        writeUint32(dataOutputStream, postScript.getMinMemType1());
        writeUint32(dataOutputStream, postScript.getMaxMemType1());
        writeUint16(dataOutputStream, this.glyphIds.size());
        TreeMap treeMap = new TreeMap();
        Iterator<Integer> it = this.glyphIds.iterator();
        while (it.hasNext()) {
            String name = postScript.getName(it.next().intValue());
            Integer num = WGL4Names.MAC_GLYPH_NAMES_INDICES.get(name);
            if (num != null) {
                writeUint16(dataOutputStream, num.intValue());
            } else {
                Integer num2 = (Integer) treeMap.get(name);
                if (num2 == null) {
                    num2 = Integer.valueOf(treeMap.size());
                    treeMap.put(name, num2);
                }
                writeUint16(dataOutputStream, num2.intValue() + 258);
            }
        }
        Iterator it2 = treeMap.keySet().iterator();
        while (it2.hasNext()) {
            byte[] bytes = ((String) it2.next()).getBytes(Charset.forName("US-ASCII"));
            writeUint8(dataOutputStream, bytes.length);
            dataOutputStream.write(bytes);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] buildHmtxTable() throws IOException {
        int numberOfHMetrics;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HorizontalHeaderTable horizontalHeader = this.ttf.getHorizontalHeader();
        HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
        byte[] bArr = new byte[4];
        InputStream originalData = this.ttf.getOriginalData();
        try {
            originalData.skip(horizontalMetrics.getOffset());
            long j = 0;
            for (Integer num : this.glyphIds) {
                if (num.intValue() < horizontalHeader.getNumberOfHMetrics()) {
                    numberOfHMetrics = num.intValue() * 4;
                } else {
                    numberOfHMetrics = (horizontalHeader.getNumberOfHMetrics() * 4) + ((num.intValue() - horizontalHeader.getNumberOfHMetrics()) * 2);
                }
                long j2 = numberOfHMetrics;
                if (j2 != j) {
                    long j3 = j2 - j;
                    if (j3 != originalData.skip(j3)) {
                        throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
                    }
                }
                int i = num.intValue() < horizontalHeader.getNumberOfHMetrics() ? 4 : 2;
                if (i != originalData.read(bArr, 0, i)) {
                    throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
                }
                byteArrayOutputStream.write(bArr, 0, i);
                j = i + j2;
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            originalData.close();
        }
    }

    public void writeToStream(OutputStream outputStream) throws IOException {
        List<String> list;
        addCompoundReferences();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            long[] jArr = new long[this.glyphIds.size() + 1];
            byte[] buildHeadTable = buildHeadTable();
            byte[] buildHheaTable = buildHheaTable();
            byte[] buildMaxpTable = buildMaxpTable();
            byte[] buildNameTable = buildNameTable();
            byte[] buildOS2Table = buildOS2Table();
            byte[] buildGlyfTable = buildGlyfTable(jArr);
            byte[] buildLocaTable = buildLocaTable(jArr);
            byte[] buildCmapTable = buildCmapTable();
            byte[] buildHmtxTable = buildHmtxTable();
            byte[] buildPostTable = buildPostTable();
            TreeMap treeMap = new TreeMap();
            if (buildOS2Table != null) {
                treeMap.put(OS2WindowsMetricsTable.TAG, buildOS2Table);
            }
            if (buildCmapTable != null) {
                treeMap.put(CmapTable.TAG, buildCmapTable);
            }
            if (buildGlyfTable != null) {
                treeMap.put(GlyphTable.TAG, buildGlyfTable);
            }
            treeMap.put("head", buildHeadTable);
            treeMap.put(HorizontalHeaderTable.TAG, buildHheaTable);
            treeMap.put(HorizontalMetricsTable.TAG, buildHmtxTable);
            if (buildLocaTable != null) {
                treeMap.put(IndexToLocationTable.TAG, buildLocaTable);
            }
            treeMap.put(MaximumProfileTable.TAG, buildMaxpTable);
            if (buildNameTable != null) {
                treeMap.put("name", buildNameTable);
            }
            if (buildPostTable != null) {
                treeMap.put("post", buildPostTable);
            }
            for (Map.Entry<String, TTFTable> entry : this.ttf.getTableMap().entrySet()) {
                String key = entry.getKey();
                TTFTable value = entry.getValue();
                if (!treeMap.containsKey(key) && ((list = this.keepTables) == null || list.contains(key))) {
                    treeMap.put(key, this.ttf.getTableBytes(value));
                }
            }
            long writeFileHeader = writeFileHeader(dataOutputStream, treeMap.size());
            long size = (treeMap.size() * 16) + 12;
            for (Map.Entry entry2 : treeMap.entrySet()) {
                writeFileHeader += writeTableHeader(dataOutputStream, (String) entry2.getKey(), size, (byte[]) entry2.getValue());
                size += ((((byte[]) entry2.getValue()).length + 3) / 4) * 4;
            }
            buildHeadTable[8] = (byte) (r4 >>> 24);
            buildHeadTable[9] = (byte) (r4 >>> 16);
            buildHeadTable[10] = (byte) (r4 >>> 8);
            buildHeadTable[11] = (byte) (2981146554L - (writeFileHeader & 4294967295L));
            Iterator it = treeMap.values().iterator();
            while (it.hasNext()) {
                writeTableBody(dataOutputStream, (byte[]) it.next());
            }
        } finally {
            dataOutputStream.close();
        }
    }

    private static void writeFixed(DataOutputStream dataOutputStream, double d) throws IOException {
        double floor = Math.floor(d);
        dataOutputStream.writeShort((int) floor);
        dataOutputStream.writeShort((int) ((d - floor) * 65536.0d));
    }

    private static void writeUint32(DataOutputStream dataOutputStream, long j) throws IOException {
        dataOutputStream.writeInt((int) j);
    }

    private static void writeUint16(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeShort(i);
    }

    private static void writeSInt16(DataOutputStream dataOutputStream, short s) throws IOException {
        dataOutputStream.writeShort(s);
    }

    private static void writeUint8(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.writeByte(i);
    }

    private static void writeLongDateTime(DataOutputStream dataOutputStream, Calendar calendar) throws IOException {
        dataOutputStream.writeLong((calendar.getTimeInMillis() - new GregorianCalendar(1904, 0, 1).getTimeInMillis()) / 1000);
    }

    private static long toUInt32(byte[] bArr) {
        return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (255 & bArr[3]);
    }

    private static int log2(int i) {
        return (int) Math.round(Math.log(i) / Math.log(2.0d));
    }
}
