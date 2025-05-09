package org.apache.fontbox.cff;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.PlaybackException;
import com.beust.jcommander.Parameters;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.cff.CFFOperator;
import org.apache.fontbox.ttf.CFFTable;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* loaded from: classes5.dex */
public class CFFParser {
    private static final String TAG_OTTO = "OTTO";
    private static final String TAG_TTCF = "ttcf";
    private static final String TAG_TTFONLY = "\u0000\u0001\u0000\u0000";
    private String debugFontName;
    private CFFDataInput input = null;
    private Header header = null;
    private IndexData nameIndex = null;
    private IndexData topDictIndex = null;
    private IndexData stringIndex = null;

    public List<CFFFont> parse(byte[] bArr) throws IOException {
        boolean z;
        CFFDataInput cFFDataInput = new CFFDataInput(bArr);
        this.input = cFFDataInput;
        String readTagName = readTagName(cFFDataInput);
        if (TAG_OTTO.equals(readTagName)) {
            short readShort = this.input.readShort();
            this.input.readShort();
            this.input.readShort();
            this.input.readShort();
            int i = 0;
            while (true) {
                if (i >= readShort) {
                    z = false;
                    break;
                }
                String readTagName2 = readTagName(this.input);
                readLong(this.input);
                long readLong = readLong(this.input);
                long readLong2 = readLong(this.input);
                if (readTagName2.equals(CFFTable.TAG)) {
                    int i2 = (int) readLong2;
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, (int) readLong, bArr2, 0, i2);
                    this.input = new CFFDataInput(bArr2);
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                throw new IOException("CFF tag not found in this OpenType font.");
            }
        } else {
            if (TAG_TTCF.equals(readTagName)) {
                throw new IOException("True Type Collection fonts are not supported.");
            }
            if (TAG_TTFONLY.equals(readTagName)) {
                throw new IOException("OpenType fonts containing a true type font are not supported.");
            }
            this.input.setPosition(0);
        }
        this.header = readHeader(this.input);
        this.nameIndex = readIndexData(this.input);
        this.topDictIndex = readIndexData(this.input);
        this.stringIndex = readIndexData(this.input);
        IndexData readIndexData = readIndexData(this.input);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.nameIndex.getCount(); i3++) {
            CFFFont parseFont = parseFont(i3);
            parseFont.setGlobalSubrIndex(readIndexData);
            arrayList.add(parseFont);
        }
        return arrayList;
    }

    private static String readTagName(CFFDataInput cFFDataInput) throws IOException {
        return new String(cFFDataInput.readBytes(4), "ISO-8859-1");
    }

    private static long readLong(CFFDataInput cFFDataInput) throws IOException {
        return cFFDataInput.readCard16() | (cFFDataInput.readCard16() << 16);
    }

    private static Header readHeader(CFFDataInput cFFDataInput) throws IOException {
        Header header = new Header();
        header.major = cFFDataInput.readCard8();
        header.minor = cFFDataInput.readCard8();
        header.hdrSize = cFFDataInput.readCard8();
        header.offSize = cFFDataInput.readOffSize();
        return header;
    }

    private static IndexData readIndexData(CFFDataInput cFFDataInput) throws IOException {
        int readCard16 = cFFDataInput.readCard16();
        IndexData indexData = new IndexData(readCard16);
        if (readCard16 == 0) {
            return indexData;
        }
        int readOffSize = cFFDataInput.readOffSize();
        for (int i = 0; i <= readCard16; i++) {
            int readOffset = cFFDataInput.readOffset(readOffSize);
            if (readOffset > cFFDataInput.length()) {
                throw new IOException("illegal offset value " + readOffset + " in CFF font");
            }
            indexData.setOffset(i, readOffset);
        }
        int offset = indexData.getOffset(readCard16) - indexData.getOffset(0);
        indexData.initData(offset);
        for (int i2 = 0; i2 < offset; i2++) {
            indexData.setData(i2, cFFDataInput.readCard8());
        }
        return indexData;
    }

    private static DictData readDictData(CFFDataInput cFFDataInput) throws IOException {
        DictData dictData = new DictData();
        dictData.entries = new ArrayList();
        while (cFFDataInput.hasRemaining()) {
            dictData.entries.add(readEntry(cFFDataInput));
        }
        return dictData;
    }

    private static DictData.Entry readEntry(CFFDataInput cFFDataInput) throws IOException {
        DictData.Entry entry = new DictData.Entry();
        while (true) {
            int readUnsignedByte = cFFDataInput.readUnsignedByte();
            if (readUnsignedByte >= 0 && readUnsignedByte <= 21) {
                entry.operator = readOperator(cFFDataInput, readUnsignedByte);
                return entry;
            }
            if (readUnsignedByte == 28 || readUnsignedByte == 29) {
                entry.operands.add(readIntegerNumber(cFFDataInput, readUnsignedByte));
            } else if (readUnsignedByte == 30) {
                entry.operands.add(readRealNumber(cFFDataInput, readUnsignedByte));
            } else {
                if (readUnsignedByte < 32 || readUnsignedByte > 254) {
                    break;
                }
                entry.operands.add(readIntegerNumber(cFFDataInput, readUnsignedByte));
            }
        }
        throw new IllegalArgumentException();
    }

    private static CFFOperator readOperator(CFFDataInput cFFDataInput, int i) throws IOException {
        return CFFOperator.getOperator(readOperatorKey(cFFDataInput, i));
    }

    private static CFFOperator.Key readOperatorKey(CFFDataInput cFFDataInput, int i) throws IOException {
        if (i == 12) {
            return new CFFOperator.Key(i, cFFDataInput.readUnsignedByte());
        }
        return new CFFOperator.Key(i);
    }

    private static Integer readIntegerNumber(CFFDataInput cFFDataInput, int i) throws IOException {
        if (i == 28) {
            return Integer.valueOf((short) (cFFDataInput.readUnsignedByte() | (cFFDataInput.readUnsignedByte() << 8)));
        }
        if (i == 29) {
            return Integer.valueOf(cFFDataInput.readUnsignedByte() | (cFFDataInput.readUnsignedByte() << 24) | (cFFDataInput.readUnsignedByte() << 16) | (cFFDataInput.readUnsignedByte() << 8));
        }
        if (i >= 32 && i <= 246) {
            return Integer.valueOf(i - 139);
        }
        if (i >= 247 && i <= 250) {
            return Integer.valueOf(((i - 247) * 256) + cFFDataInput.readUnsignedByte() + 108);
        }
        if (i >= 251 && i <= 254) {
            return Integer.valueOf((((-(i - 251)) * 256) - cFFDataInput.readUnsignedByte()) + PlaybackException.ERROR_CODE_SETUP_REQUIRED);
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x001f. Please report as an issue. */
    private static Double readRealNumber(CFFDataInput cFFDataInput, int i) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            int readUnsignedByte = cFFDataInput.readUnsignedByte();
            int[] iArr = {readUnsignedByte / 16, readUnsignedByte % 16};
            for (int i2 = 0; i2 < 2; i2++) {
                int i3 = iArr[i2];
                switch (i3) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        stringBuffer.append(i3);
                        z2 = false;
                    case 10:
                        stringBuffer.append(".");
                    case 11:
                        stringBuffer.append("E");
                        z2 = true;
                    case 12:
                        stringBuffer.append("E-");
                        z2 = true;
                    case 13:
                    case 14:
                        stringBuffer.append(Parameters.DEFAULT_OPTION_PREFIXES);
                    case 15:
                        z = true;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        if (z2) {
            stringBuffer.append("0");
        }
        return Double.valueOf(stringBuffer.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CFFFont parseFont(int i) throws IOException {
        CFFType1Font cFFType1Font;
        CFFCharset cFFISOAdobeCharset;
        String string = new DataInput(this.nameIndex.getBytes(i)).getString();
        DictData readDictData = readDictData(new CFFDataInput(this.topDictIndex.getBytes(i)));
        if (readDictData.getEntry("SyntheticBase") != null) {
            throw new IOException("Synthetic Fonts are not supported");
        }
        boolean z = readDictData.getEntry("ROS") != null;
        if (z) {
            CFFCIDFont cFFCIDFont = new CFFCIDFont();
            DictData.Entry entry = readDictData.getEntry("ROS");
            cFFCIDFont.setRegistry(readString(entry.getNumber(0).intValue()));
            cFFCIDFont.setOrdering(readString(entry.getNumber(1).intValue()));
            cFFCIDFont.setSupplement(entry.getNumber(2).intValue());
            cFFType1Font = cFFCIDFont;
        } else {
            cFFType1Font = new CFFType1Font();
        }
        this.debugFontName = string;
        cFFType1Font.setName(string);
        cFFType1Font.addValueToTopDict("version", getString(readDictData, "version"));
        cFFType1Font.addValueToTopDict(AFMParser.NOTICE, getString(readDictData, AFMParser.NOTICE));
        cFFType1Font.addValueToTopDict(ExifInterface.TAG_COPYRIGHT, getString(readDictData, ExifInterface.TAG_COPYRIGHT));
        cFFType1Font.addValueToTopDict(AFMParser.FULL_NAME, getString(readDictData, AFMParser.FULL_NAME));
        cFFType1Font.addValueToTopDict(AFMParser.FAMILY_NAME, getString(readDictData, AFMParser.FAMILY_NAME));
        cFFType1Font.addValueToTopDict(AFMParser.WEIGHT, getString(readDictData, AFMParser.WEIGHT));
        cFFType1Font.addValueToTopDict("isFixedPitch", getBoolean(readDictData, "isFixedPitch", false));
        cFFType1Font.addValueToTopDict(AFMParser.ITALIC_ANGLE, getNumber(readDictData, AFMParser.ITALIC_ANGLE, 0));
        cFFType1Font.addValueToTopDict(AFMParser.UNDERLINE_POSITION, getNumber(readDictData, AFMParser.UNDERLINE_POSITION, -100));
        cFFType1Font.addValueToTopDict(AFMParser.UNDERLINE_THICKNESS, getNumber(readDictData, AFMParser.UNDERLINE_THICKNESS, 50));
        cFFType1Font.addValueToTopDict("PaintType", getNumber(readDictData, "PaintType", 0));
        cFFType1Font.addValueToTopDict("CharstringType", getNumber(readDictData, "CharstringType", 2));
        cFFType1Font.addValueToTopDict("FontMatrix", getArray(readDictData, "FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
        cFFType1Font.addValueToTopDict("UniqueID", getNumber(readDictData, "UniqueID", null));
        cFFType1Font.addValueToTopDict(AFMParser.FONT_BBOX, getArray(readDictData, AFMParser.FONT_BBOX, Arrays.asList(0, 0, 0, 0)));
        cFFType1Font.addValueToTopDict("StrokeWidth", getNumber(readDictData, "StrokeWidth", 0));
        cFFType1Font.addValueToTopDict("XUID", getArray(readDictData, "XUID", null));
        this.input.setPosition(readDictData.getEntry("CharStrings").getNumber(0).intValue());
        IndexData readIndexData = readIndexData(this.input);
        DictData.Entry entry2 = readDictData.getEntry("charset");
        if (entry2 != null) {
            int intValue = entry2.getNumber(0).intValue();
            if (!z && intValue == 0) {
                cFFISOAdobeCharset = CFFISOAdobeCharset.getInstance();
            } else if (!z && intValue == 1) {
                cFFISOAdobeCharset = CFFExpertCharset.getInstance();
            } else if (!z && intValue == 2) {
                cFFISOAdobeCharset = CFFExpertSubsetCharset.getInstance();
            } else {
                this.input.setPosition(intValue);
                cFFISOAdobeCharset = readCharset(this.input, readIndexData.getCount(), z);
            }
        } else if (z) {
            cFFISOAdobeCharset = new EmptyCharset(readIndexData.getCount());
        } else {
            cFFISOAdobeCharset = CFFISOAdobeCharset.getInstance();
        }
        cFFType1Font.setCharset(cFFISOAdobeCharset);
        cFFType1Font.getCharStringBytes().add(readIndexData.getBytes(0));
        for (int i2 = 1; i2 < readIndexData.getCount(); i2++) {
            cFFType1Font.getCharStringBytes().add(readIndexData.getBytes(i2));
        }
        if (z) {
            CFFCIDFont cFFCIDFont2 = cFFType1Font;
            parseCIDFontDicts(readDictData, cFFCIDFont2, readIndexData);
            if (readDictData.getEntry("FontMatrix") == null) {
                List<Map<String, Object>> fontDicts = cFFCIDFont2.getFontDicts();
                if (fontDicts.size() > 0 && fontDicts.get(0).containsKey("FontMatrix")) {
                    cFFType1Font.addValueToTopDict("FontMatrix", (List) fontDicts.get(0).get("FontMatrix"));
                } else {
                    cFFType1Font.addValueToTopDict("FontMatrix", getArray(readDictData, "FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
                }
            }
        } else {
            parseType1Dicts(readDictData, cFFType1Font, cFFISOAdobeCharset);
        }
        return cFFType1Font;
    }

    private void parseCIDFontDicts(DictData dictData, CFFCIDFont cFFCIDFont, IndexData indexData) throws IOException {
        DictData.Entry entry = dictData.getEntry("FDArray");
        if (entry == null) {
            throw new IOException("FDArray is missing for a CIDKeyed Font.");
        }
        this.input.setPosition(entry.getNumber(0).intValue());
        IndexData readIndexData = readIndexData(this.input);
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        for (int i = 0; i < readIndexData.getCount(); i++) {
            DictData readDictData = readDictData(new CFFDataInput(readIndexData.getBytes(i)));
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(AFMParser.FONT_NAME, getString(readDictData, AFMParser.FONT_NAME));
            linkedHashMap.put("FontType", getNumber(readDictData, "FontType", 0));
            linkedHashMap.put(AFMParser.FONT_BBOX, getDelta(readDictData, AFMParser.FONT_BBOX, null));
            linkedHashMap.put("FontMatrix", getDelta(readDictData, "FontMatrix", null));
            linkedList2.add(linkedHashMap);
            DictData.Entry entry2 = readDictData.getEntry(StandardStructureTypes.PRIVATE);
            if (entry2 == null) {
                throw new IOException("Font DICT invalid without \"Private\" entry");
            }
            int intValue = entry2.getNumber(1).intValue();
            this.input.setPosition(intValue);
            DictData readDictData2 = readDictData(new CFFDataInput(this.input.readBytes(entry2.getNumber(0).intValue())));
            Map<String, Object> readPrivateDict = readPrivateDict(readDictData2);
            linkedList.add(readPrivateDict);
            int intValue2 = ((Integer) getNumber(readDictData2, "Subrs", 0)).intValue();
            if (intValue2 == 0) {
                readPrivateDict.put("Subrs", new IndexData(0));
            } else {
                this.input.setPosition(intValue + intValue2);
                readPrivateDict.put("Subrs", readIndexData(this.input));
            }
        }
        this.input.setPosition(dictData.getEntry("FDSelect").getNumber(0).intValue());
        FDSelect readFDSelect = readFDSelect(this.input, indexData.getCount(), cFFCIDFont);
        cFFCIDFont.setFontDict(linkedList2);
        cFFCIDFont.setPrivDict(linkedList);
        cFFCIDFont.setFdSelect(readFDSelect);
    }

    private Map<String, Object> readPrivateDict(DictData dictData) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BlueValues", getDelta(dictData, "BlueValues", null));
        linkedHashMap.put("OtherBlues", getDelta(dictData, "OtherBlues", null));
        linkedHashMap.put("FamilyBlues", getDelta(dictData, "FamilyBlues", null));
        linkedHashMap.put("FamilyOtherBlues", getDelta(dictData, "FamilyOtherBlues", null));
        linkedHashMap.put("BlueScale", getNumber(dictData, "BlueScale", Double.valueOf(0.039625d)));
        linkedHashMap.put("BlueShift", getNumber(dictData, "BlueShift", 7));
        linkedHashMap.put("BlueFuzz", getNumber(dictData, "BlueFuzz", 1));
        linkedHashMap.put(AFMParser.STD_HW, getNumber(dictData, AFMParser.STD_HW, null));
        linkedHashMap.put(AFMParser.STD_VW, getNumber(dictData, AFMParser.STD_VW, null));
        linkedHashMap.put("StemSnapH", getDelta(dictData, "StemSnapH", null));
        linkedHashMap.put("StemSnapV", getDelta(dictData, "StemSnapV", null));
        linkedHashMap.put("ForceBold", getBoolean(dictData, "ForceBold", false));
        linkedHashMap.put("LanguageGroup", getNumber(dictData, "LanguageGroup", 0));
        linkedHashMap.put("ExpansionFactor", getNumber(dictData, "ExpansionFactor", Double.valueOf(0.06d)));
        linkedHashMap.put("initialRandomSeed", getNumber(dictData, "initialRandomSeed", 0));
        linkedHashMap.put("defaultWidthX", getNumber(dictData, "defaultWidthX", 0));
        linkedHashMap.put("nominalWidthX", getNumber(dictData, "nominalWidthX", 0));
        return linkedHashMap;
    }

    private void parseType1Dicts(DictData dictData, CFFType1Font cFFType1Font, CFFCharset cFFCharset) throws IOException {
        CFFEncoding readEncoding;
        DictData.Entry entry = dictData.getEntry("Encoding");
        int intValue = entry != null ? entry.getNumber(0).intValue() : 0;
        if (intValue == 0) {
            readEncoding = CFFStandardEncoding.getInstance();
        } else if (intValue == 1) {
            readEncoding = CFFExpertEncoding.getInstance();
        } else {
            this.input.setPosition(intValue);
            readEncoding = readEncoding(this.input, cFFCharset);
        }
        cFFType1Font.setEncoding(readEncoding);
        DictData.Entry entry2 = dictData.getEntry(StandardStructureTypes.PRIVATE);
        int intValue2 = entry2.getNumber(1).intValue();
        this.input.setPosition(intValue2);
        DictData readDictData = readDictData(new CFFDataInput(this.input.readBytes(entry2.getNumber(0).intValue())));
        for (Map.Entry<String, Object> entry3 : readPrivateDict(readDictData).entrySet()) {
            cFFType1Font.addToPrivateDict(entry3.getKey(), entry3.getValue());
        }
        int intValue3 = ((Integer) getNumber(readDictData, "Subrs", 0)).intValue();
        if (intValue3 == 0) {
            cFFType1Font.addToPrivateDict("Subrs", new IndexData(0));
        } else {
            this.input.setPosition(intValue2 + intValue3);
            cFFType1Font.addToPrivateDict("Subrs", readIndexData(this.input));
        }
    }

    private String readString(int i) throws IOException {
        if (i >= 0 && i <= 390) {
            return CFFStandardString.getName(i);
        }
        int i2 = i - 391;
        if (i2 < this.stringIndex.getCount()) {
            return new DataInput(this.stringIndex.getBytes(i2)).getString();
        }
        return "SID" + i;
    }

    private String getString(DictData dictData, String str) throws IOException {
        DictData.Entry entry = dictData.getEntry(str);
        if (entry != null) {
            return readString(entry.getNumber(0).intValue());
        }
        return null;
    }

    private static Boolean getBoolean(DictData dictData, String str, boolean z) {
        DictData.Entry entry = dictData.getEntry(str);
        if (entry != null) {
            z = entry.getBoolean(0).booleanValue();
        }
        return Boolean.valueOf(z);
    }

    private static Number getNumber(DictData dictData, String str, Number number) {
        DictData.Entry entry = dictData.getEntry(str);
        return entry != null ? entry.getNumber(0) : number;
    }

    private static List<Number> getArray(DictData dictData, String str, List<Number> list) {
        DictData.Entry entry = dictData.getEntry(str);
        return entry != null ? entry.getArray() : list;
    }

    private static List<Number> getDelta(DictData dictData, String str, List<Number> list) {
        DictData.Entry entry = dictData.getEntry(str);
        return entry != null ? entry.getArray() : list;
    }

    private CFFEncoding readEncoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset) throws IOException {
        int readCard8 = cFFDataInput.readCard8();
        int i = readCard8 & 127;
        if (i == 0) {
            return readFormat0Encoding(cFFDataInput, cFFCharset, readCard8);
        }
        if (i == 1) {
            return readFormat1Encoding(cFFDataInput, cFFCharset, readCard8);
        }
        throw new IllegalArgumentException();
    }

    private Format0Encoding readFormat0Encoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset, int i) throws IOException {
        Format0Encoding format0Encoding = new Format0Encoding();
        format0Encoding.format = i;
        format0Encoding.nCodes = cFFDataInput.readCard8();
        format0Encoding.code = new int[format0Encoding.nCodes];
        format0Encoding.add(0, 0, ".notdef");
        for (int i2 = 1; i2 <= format0Encoding.nCodes; i2++) {
            int readCard8 = cFFDataInput.readCard8();
            format0Encoding.code[i2 - 1] = readCard8;
            int sIDForGID = cFFCharset.getSIDForGID(i2);
            format0Encoding.add(readCard8, sIDForGID, readString(sIDForGID));
        }
        if ((i & 128) != 0) {
            readSupplement(cFFDataInput, format0Encoding);
        }
        return format0Encoding;
    }

    private Format1Encoding readFormat1Encoding(CFFDataInput cFFDataInput, CFFCharset cFFCharset, int i) throws IOException {
        Format1Encoding format1Encoding = new Format1Encoding();
        format1Encoding.format = i;
        format1Encoding.nRanges = cFFDataInput.readCard8();
        format1Encoding.range = new Format1Encoding.Range1[format1Encoding.nRanges];
        format1Encoding.add(0, 0, ".notdef");
        int i2 = 1;
        for (int i3 = 0; i3 < format1Encoding.range.length; i3++) {
            Format1Encoding.Range1 range1 = new Format1Encoding.Range1();
            range1.first = cFFDataInput.readCard8();
            range1.nLeft = cFFDataInput.readCard8();
            format1Encoding.range[i3] = range1;
            for (int i4 = 0; i4 < range1.nLeft + 1; i4++) {
                int sIDForGID = cFFCharset.getSIDForGID(i2);
                format1Encoding.add(range1.first + i4, sIDForGID, readString(sIDForGID));
                i2++;
            }
        }
        if ((i & 128) != 0) {
            readSupplement(cFFDataInput, format1Encoding);
        }
        return format1Encoding;
    }

    private void readSupplement(CFFDataInput cFFDataInput, EmbeddedEncoding embeddedEncoding) throws IOException {
        embeddedEncoding.nSups = cFFDataInput.readCard8();
        embeddedEncoding.supplement = new EmbeddedEncoding.Supplement[embeddedEncoding.nSups];
        for (int i = 0; i < embeddedEncoding.supplement.length; i++) {
            EmbeddedEncoding.Supplement supplement = new EmbeddedEncoding.Supplement();
            supplement.code = cFFDataInput.readCard8();
            supplement.sid = cFFDataInput.readSID();
            supplement.name = readString(supplement.sid);
            embeddedEncoding.supplement[i] = supplement;
            embeddedEncoding.add(supplement.code, supplement.sid, readString(supplement.sid));
        }
    }

    private static FDSelect readFDSelect(CFFDataInput cFFDataInput, int i, CFFCIDFont cFFCIDFont) throws IOException {
        int readCard8 = cFFDataInput.readCard8();
        if (readCard8 == 0) {
            return readFormat0FDSelect(cFFDataInput, readCard8, i, cFFCIDFont);
        }
        if (readCard8 == 3) {
            return readFormat3FDSelect(cFFDataInput, readCard8, i, cFFCIDFont);
        }
        throw new IllegalArgumentException();
    }

    private static Format0FDSelect readFormat0FDSelect(CFFDataInput cFFDataInput, int i, int i2, CFFCIDFont cFFCIDFont) throws IOException {
        Format0FDSelect format0FDSelect = new Format0FDSelect(cFFCIDFont);
        format0FDSelect.format = i;
        format0FDSelect.fds = new int[i2];
        for (int i3 = 0; i3 < format0FDSelect.fds.length; i3++) {
            format0FDSelect.fds[i3] = cFFDataInput.readCard8();
        }
        return format0FDSelect;
    }

    private static Format3FDSelect readFormat3FDSelect(CFFDataInput cFFDataInput, int i, int i2, CFFCIDFont cFFCIDFont) throws IOException {
        Format3FDSelect format3FDSelect = new Format3FDSelect(cFFCIDFont);
        format3FDSelect.format = i;
        format3FDSelect.nbRanges = cFFDataInput.readCard16();
        format3FDSelect.range3 = new Range3[format3FDSelect.nbRanges];
        for (int i3 = 0; i3 < format3FDSelect.nbRanges; i3++) {
            Range3 range3 = new Range3();
            range3.first = cFFDataInput.readCard16();
            range3.fd = cFFDataInput.readCard8();
            format3FDSelect.range3[i3] = range3;
        }
        format3FDSelect.sentinel = cFFDataInput.readCard16();
        return format3FDSelect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class Format3FDSelect extends FDSelect {
        private int format;
        private int nbRanges;
        private Range3[] range3;
        private int sentinel;

        private Format3FDSelect(CFFCIDFont cFFCIDFont) {
            super(cFFCIDFont);
        }

        @Override // org.apache.fontbox.cff.FDSelect
        public int getFDIndex(int i) {
            for (int i2 = 0; i2 < this.nbRanges; i2++) {
                if (this.range3[i2].first <= i) {
                    int i3 = i2 + 1;
                    if (i3 >= this.nbRanges) {
                        if (this.sentinel > i) {
                            return this.range3[i2].fd;
                        }
                        return -1;
                    }
                    if (this.range3[i3].first > i) {
                        return this.range3[i2].fd;
                    }
                }
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + " nbRanges=" + this.nbRanges + ", range3=" + Arrays.toString(this.range3) + " sentinel=" + this.sentinel + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static final class Range3 {
        private int fd;
        private int first;

        private Range3() {
        }

        public String toString() {
            return getClass().getName() + "[first=" + this.first + ", fd=" + this.fd + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Format0FDSelect extends FDSelect {
        private int[] fds;
        private int format;

        private Format0FDSelect(CFFCIDFont cFFCIDFont) {
            super(cFFCIDFont);
        }

        @Override // org.apache.fontbox.cff.FDSelect
        public int getFDIndex(int i) {
            int[] iArr = this.fds;
            if (i < iArr.length) {
                return iArr[i];
            }
            return 0;
        }

        public String toString() {
            return getClass().getName() + "[fds=" + Arrays.toString(this.fds) + "]";
        }
    }

    private CFFCharset readCharset(CFFDataInput cFFDataInput, int i, boolean z) throws IOException {
        int readCard8 = cFFDataInput.readCard8();
        if (readCard8 == 0) {
            return readFormat0Charset(cFFDataInput, readCard8, i, z);
        }
        if (readCard8 == 1) {
            return readFormat1Charset(cFFDataInput, readCard8, i, z);
        }
        if (readCard8 == 2) {
            return readFormat2Charset(cFFDataInput, readCard8, i, z);
        }
        throw new IllegalArgumentException();
    }

    private Format0Charset readFormat0Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format0Charset format0Charset = new Format0Charset(z);
        format0Charset.format = i;
        format0Charset.glyph = new int[i2];
        format0Charset.glyph[0] = 0;
        if (z) {
            format0Charset.addCID(0, 0);
        } else {
            format0Charset.addSID(0, 0, ".notdef");
        }
        for (int i3 = 1; i3 < format0Charset.glyph.length; i3++) {
            int readSID = cFFDataInput.readSID();
            format0Charset.glyph[i3] = readSID;
            if (z) {
                format0Charset.addCID(i3, readSID);
            } else {
                format0Charset.addSID(i3, readSID, readString(readSID));
            }
        }
        return format0Charset;
    }

    private Format1Charset readFormat1Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format1Charset format1Charset = new Format1Charset(z);
        format1Charset.format = i;
        ArrayList arrayList = new ArrayList();
        if (z) {
            format1Charset.addCID(0, 0);
        } else {
            format1Charset.addSID(0, 0, ".notdef");
        }
        int i3 = 1;
        while (i3 < i2) {
            Format1Charset.Range1 range1 = new Format1Charset.Range1();
            range1.first = cFFDataInput.readSID();
            range1.nLeft = cFFDataInput.readCard8();
            arrayList.add(range1);
            for (int i4 = 0; i4 < range1.nLeft + 1; i4++) {
                int i5 = range1.first + i4;
                if (z) {
                    format1Charset.addCID(i3 + i4, i5);
                } else {
                    format1Charset.addSID(i3 + i4, i5, readString(i5));
                }
            }
            i3 = i3 + range1.nLeft + 1;
        }
        format1Charset.range = (Format1Charset.Range1[]) arrayList.toArray(new Format1Charset.Range1[0]);
        return format1Charset;
    }

    private Format2Charset readFormat2Charset(CFFDataInput cFFDataInput, int i, int i2, boolean z) throws IOException {
        Format2Charset format2Charset = new Format2Charset(z);
        format2Charset.format = i;
        format2Charset.range = new Format2Charset.Range2[0];
        if (z) {
            format2Charset.addCID(0, 0);
        } else {
            format2Charset.addSID(0, 0, ".notdef");
        }
        int i3 = 1;
        while (i3 < i2) {
            Format2Charset.Range2[] range2Arr = new Format2Charset.Range2[format2Charset.range.length + 1];
            System.arraycopy(format2Charset.range, 0, range2Arr, 0, format2Charset.range.length);
            format2Charset.range = range2Arr;
            Format2Charset.Range2 range2 = new Format2Charset.Range2();
            range2.first = cFFDataInput.readSID();
            range2.nLeft = cFFDataInput.readCard16();
            format2Charset.range[format2Charset.range.length - 1] = range2;
            for (int i4 = 0; i4 < range2.nLeft + 1; i4++) {
                int i5 = range2.first + i4;
                if (z) {
                    format2Charset.addCID(i3 + i4, i5);
                } else {
                    format2Charset.addSID(i3 + i4, i5, readString(i5));
                }
            }
            i3 = i3 + range2.nLeft + 1;
        }
        return format2Charset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Header {
        private int hdrSize;
        private int major;
        private int minor;
        private int offSize;

        private Header() {
        }

        public String toString() {
            return getClass().getName() + "[major=" + this.major + ", minor=" + this.minor + ", hdrSize=" + this.hdrSize + ", offSize=" + this.offSize + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class DictData {
        private List<Entry> entries;

        private DictData() {
            this.entries = null;
        }

        public Entry getEntry(CFFOperator.Key key) {
            return getEntry(CFFOperator.getOperator(key));
        }

        public Entry getEntry(String str) {
            return getEntry(CFFOperator.getOperator(str));
        }

        private Entry getEntry(CFFOperator cFFOperator) {
            for (Entry entry : this.entries) {
                if (entry != null && entry.operator != null && entry.operator.equals(cFFOperator)) {
                    return entry;
                }
            }
            return null;
        }

        public String toString() {
            return getClass().getName() + "[entries=" + this.entries + "]";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public static class Entry {
            private List<Number> operands;
            private CFFOperator operator;

            private Entry() {
                this.operands = new ArrayList();
                this.operator = null;
            }

            public Number getNumber(int i) {
                return this.operands.get(i);
            }

            public Boolean getBoolean(int i) {
                Number number = this.operands.get(i);
                if (number instanceof Integer) {
                    int intValue = number.intValue();
                    if (intValue == 0) {
                        return Boolean.FALSE;
                    }
                    if (intValue == 1) {
                        return Boolean.TRUE;
                    }
                }
                throw new IllegalArgumentException();
            }

            public Integer getSID(int i) {
                Number number = this.operands.get(i);
                if (number instanceof Integer) {
                    return (Integer) number;
                }
                throw new IllegalArgumentException();
            }

            public List<Number> getArray() {
                return this.operands;
            }

            public List<Number> getDelta() {
                return this.operands;
            }

            public String toString() {
                return getClass().getName() + "[operands=" + this.operands + ", operator=" + this.operator + "]";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static abstract class EmbeddedEncoding extends CFFEncoding {
        private int nSups;
        private Supplement[] supplement;

        EmbeddedEncoding() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes5.dex */
        public static class Supplement {
            private int code;
            private String name;
            private int sid;

            Supplement() {
            }

            public int getCode() {
                return this.code;
            }

            public int getSID() {
                return this.sid;
            }

            public String getName() {
                return this.name;
            }

            public String toString() {
                return getClass().getName() + "[code=" + this.code + ", sid=" + this.sid + "]";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Format0Encoding extends EmbeddedEncoding {
        private int[] code;
        private int format;
        private int nCodes;

        private Format0Encoding() {
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", nCodes=" + this.nCodes + ", code=" + Arrays.toString(this.code) + ", supplement=" + Arrays.toString(((EmbeddedEncoding) this).supplement) + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Format1Encoding extends EmbeddedEncoding {
        private int format;
        private int nRanges;
        private Range1[] range;

        private Format1Encoding() {
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", nRanges=" + this.nRanges + ", range=" + Arrays.toString(this.range) + ", supplement=" + Arrays.toString(((EmbeddedEncoding) this).supplement) + "]";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public static class Range1 {
            private int first;
            private int nLeft;

            private Range1() {
            }

            public String toString() {
                return getClass().getName() + "[first=" + this.first + ", nLeft=" + this.nLeft + "]";
            }
        }
    }

    /* loaded from: classes5.dex */
    static abstract class EmbeddedCharset extends CFFCharset {
        protected EmbeddedCharset(boolean z) {
            super(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class EmptyCharset extends EmbeddedCharset {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        protected EmptyCharset(int i) {
            super(true);
            addCID(0, 0);
            for (int i2 = 1; i2 <= i; i2++) {
                addCID(i2, i2);
            }
        }

        public String toString() {
            return getClass().getName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Format0Charset extends EmbeddedCharset {
        private int format;
        private int[] glyph;

        protected Format0Charset(boolean z) {
            super(z);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", glyph=" + Arrays.toString(this.glyph) + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Format1Charset extends EmbeddedCharset {
        private int format;
        private Range1[] range;

        protected Format1Charset(boolean z) {
            super(z);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", range=" + Arrays.toString(this.range) + "]";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public static class Range1 {
            private int first;
            private int nLeft;

            private Range1() {
            }

            public String toString() {
                return getClass().getName() + "[first=" + this.first + ", nLeft=" + this.nLeft + "]";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class Format2Charset extends EmbeddedCharset {
        private int format;
        private Range2[] range;

        protected Format2Charset(boolean z) {
            super(z);
        }

        public String toString() {
            return getClass().getName() + "[format=" + this.format + ", range=" + Arrays.toString(this.range) + "]";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes5.dex */
        public static class Range2 {
            private int first;
            private int nLeft;

            private Range2() {
            }

            public String toString() {
                return getClass().getName() + "[first=" + this.first + ", nLeft=" + this.nLeft + "]";
            }
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.debugFontName + "]";
    }
}
