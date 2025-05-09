package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class OS2WindowsMetricsTable extends TTFTable {
    public static final int FAMILY_CLASS_CLAREDON_SERIFS = 4;
    public static final int FAMILY_CLASS_FREEFORM_SERIFS = 7;
    public static final int FAMILY_CLASS_MODERN_SERIFS = 3;
    public static final int FAMILY_CLASS_NO_CLASSIFICATION = 0;
    public static final int FAMILY_CLASS_OLDSTYLE_SERIFS = 1;
    public static final int FAMILY_CLASS_ORNAMENTALS = 9;
    public static final int FAMILY_CLASS_SANS_SERIF = 8;
    public static final int FAMILY_CLASS_SCRIPTS = 10;
    public static final int FAMILY_CLASS_SLAB_SERIFS = 5;
    public static final int FAMILY_CLASS_SYMBOLIC = 12;
    public static final int FAMILY_CLASS_TRANSITIONAL_SERIFS = 2;
    public static final short FSTYPE_BITMAP_ONLY = 512;
    public static final short FSTYPE_EDITIBLE = 4;
    public static final short FSTYPE_NO_SUBSETTING = 256;
    public static final short FSTYPE_PREVIEW_AND_PRINT = 4;
    public static final short FSTYPE_RESTRICTED = 1;
    public static final String TAG = "OS/2";
    public static final int WEIGHT_CLASS_BLACK = 900;
    public static final int WEIGHT_CLASS_BOLD = 700;
    public static final int WEIGHT_CLASS_EXTRA_BOLD = 800;
    public static final int WEIGHT_CLASS_LIGHT = 300;
    public static final int WEIGHT_CLASS_MEDIUM = 500;
    public static final int WEIGHT_CLASS_NORMAL = 400;
    public static final int WEIGHT_CLASS_SEMI_BOLD = 600;
    public static final int WEIGHT_CLASS_THIN = 100;
    public static final int WEIGHT_CLASS_ULTRA_LIGHT = 200;
    public static final int WIDTH_CLASS_CONDENSED = 3;
    public static final int WIDTH_CLASS_EXPANDED = 7;
    public static final int WIDTH_CLASS_EXTRA_CONDENSED = 2;
    public static final int WIDTH_CLASS_EXTRA_EXPANDED = 8;
    public static final int WIDTH_CLASS_MEDIUM = 5;
    public static final int WIDTH_CLASS_SEMI_CONDENSED = 4;
    public static final int WIDTH_CLASS_SEMI_EXPANDED = 6;
    public static final int WIDTH_CLASS_ULTRA_CONDENSED = 1;
    public static final int WIDTH_CLASS_ULTRA_EXPANDED = 9;
    private short averageCharWidth;
    private int familyClass;
    private int familySubClass;
    private int firstCharIndex;
    private int fsSelection;
    private short fsType;
    private int lastCharIndex;
    private int sCapHeight;
    private short strikeoutPosition;
    private short strikeoutSize;
    private short subscriptXOffset;
    private short subscriptXSize;
    private short subscriptYOffset;
    private short subscriptYSize;
    private short superscriptXOffset;
    private short superscriptXSize;
    private short superscriptYOffset;
    private short superscriptYSize;
    private int sxHeight;
    private int typoAscender;
    private int typoDescender;
    private int typoLineGap;
    private long unicodeRange1;
    private long unicodeRange2;
    private long unicodeRange3;
    private long unicodeRange4;
    private int usBreakChar;
    private int usDefaultChar;
    private int usMaxContext;
    private int version;
    private int weightClass;
    private int widthClass;
    private int winAscent;
    private int winDescent;
    private byte[] panose = new byte[10];
    private String achVendId = "XXXX";
    private long codePageRange1 = -1;
    private long codePageRange2 = -1;

    public String getAchVendId() {
        return this.achVendId;
    }

    public void setAchVendId(String str) {
        this.achVendId = str;
    }

    public short getAverageCharWidth() {
        return this.averageCharWidth;
    }

    public void setAverageCharWidth(short s) {
        this.averageCharWidth = s;
    }

    public long getCodePageRange1() {
        return this.codePageRange1;
    }

    public void setCodePageRange1(long j) {
        this.codePageRange1 = j;
    }

    public long getCodePageRange2() {
        return this.codePageRange2;
    }

    public void setCodePageRange2(long j) {
        this.codePageRange2 = j;
    }

    public int getFamilyClass() {
        return this.familyClass;
    }

    public void setFamilyClass(int i) {
        this.familyClass = i;
    }

    public int getFamilySubClass() {
        return this.familySubClass;
    }

    public void setFamilySubClass(int i) {
        this.familySubClass = i;
    }

    public int getFirstCharIndex() {
        return this.firstCharIndex;
    }

    public void setFirstCharIndex(int i) {
        this.firstCharIndex = i;
    }

    public int getFsSelection() {
        return this.fsSelection;
    }

    public void setFsSelection(int i) {
        this.fsSelection = i;
    }

    public short getFsType() {
        return this.fsType;
    }

    public void setFsType(short s) {
        this.fsType = s;
    }

    public int getLastCharIndex() {
        return this.lastCharIndex;
    }

    public void setLastCharIndex(int i) {
        this.lastCharIndex = i;
    }

    public byte[] getPanose() {
        return this.panose;
    }

    public void setPanose(byte[] bArr) {
        this.panose = bArr;
    }

    public short getStrikeoutPosition() {
        return this.strikeoutPosition;
    }

    public void setStrikeoutPosition(short s) {
        this.strikeoutPosition = s;
    }

    public short getStrikeoutSize() {
        return this.strikeoutSize;
    }

    public void setStrikeoutSize(short s) {
        this.strikeoutSize = s;
    }

    public short getSubscriptXOffset() {
        return this.subscriptXOffset;
    }

    public void setSubscriptXOffset(short s) {
        this.subscriptXOffset = s;
    }

    public short getSubscriptXSize() {
        return this.subscriptXSize;
    }

    public void setSubscriptXSize(short s) {
        this.subscriptXSize = s;
    }

    public short getSubscriptYOffset() {
        return this.subscriptYOffset;
    }

    public void setSubscriptYOffset(short s) {
        this.subscriptYOffset = s;
    }

    public short getSubscriptYSize() {
        return this.subscriptYSize;
    }

    public void setSubscriptYSize(short s) {
        this.subscriptYSize = s;
    }

    public short getSuperscriptXOffset() {
        return this.superscriptXOffset;
    }

    public void setSuperscriptXOffset(short s) {
        this.superscriptXOffset = s;
    }

    public short getSuperscriptXSize() {
        return this.superscriptXSize;
    }

    public void setSuperscriptXSize(short s) {
        this.superscriptXSize = s;
    }

    public short getSuperscriptYOffset() {
        return this.superscriptYOffset;
    }

    public void setSuperscriptYOffset(short s) {
        this.superscriptYOffset = s;
    }

    public short getSuperscriptYSize() {
        return this.superscriptYSize;
    }

    public void setSuperscriptYSize(short s) {
        this.superscriptYSize = s;
    }

    public int getTypoLineGap() {
        return this.typoLineGap;
    }

    public void setTypeLineGap(int i) {
        this.typoLineGap = i;
    }

    public int getTypoAscender() {
        return this.typoAscender;
    }

    public void setTypoAscender(int i) {
        this.typoAscender = i;
    }

    public int getTypoDescender() {
        return this.typoDescender;
    }

    public void setTypoDescender(int i) {
        this.typoDescender = i;
    }

    public long getUnicodeRange1() {
        return this.unicodeRange1;
    }

    public void setUnicodeRange1(long j) {
        this.unicodeRange1 = j;
    }

    public long getUnicodeRange2() {
        return this.unicodeRange2;
    }

    public void setUnicodeRange2(long j) {
        this.unicodeRange2 = j;
    }

    public long getUnicodeRange3() {
        return this.unicodeRange3;
    }

    public void setUnicodeRange3(long j) {
        this.unicodeRange3 = j;
    }

    public long getUnicodeRange4() {
        return this.unicodeRange4;
    }

    public void setUnicodeRange4(long j) {
        this.unicodeRange4 = j;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public int getWeightClass() {
        return this.weightClass;
    }

    public void setWeightClass(int i) {
        this.weightClass = i;
    }

    public int getWidthClass() {
        return this.widthClass;
    }

    public void setWidthClass(int i) {
        this.widthClass = i;
    }

    public int getWinAscent() {
        return this.winAscent;
    }

    public void setWinAscent(int i) {
        this.winAscent = i;
    }

    public int getWinDescent() {
        return this.winDescent;
    }

    public void setWinDescent(int i) {
        this.winDescent = i;
    }

    public int getHeight() {
        return this.sxHeight;
    }

    public int getCapHeight() {
        return this.sCapHeight;
    }

    public int getDefaultChar() {
        return this.usDefaultChar;
    }

    public int getBreakChar() {
        return this.usBreakChar;
    }

    public int getMaxContext() {
        return this.usMaxContext;
    }

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.version = tTFDataStream.readUnsignedShort();
        this.averageCharWidth = tTFDataStream.readSignedShort();
        this.weightClass = tTFDataStream.readUnsignedShort();
        this.widthClass = tTFDataStream.readUnsignedShort();
        this.fsType = tTFDataStream.readSignedShort();
        this.subscriptXSize = tTFDataStream.readSignedShort();
        this.subscriptYSize = tTFDataStream.readSignedShort();
        this.subscriptXOffset = tTFDataStream.readSignedShort();
        this.subscriptYOffset = tTFDataStream.readSignedShort();
        this.superscriptXSize = tTFDataStream.readSignedShort();
        this.superscriptYSize = tTFDataStream.readSignedShort();
        this.superscriptXOffset = tTFDataStream.readSignedShort();
        this.superscriptYOffset = tTFDataStream.readSignedShort();
        this.strikeoutSize = tTFDataStream.readSignedShort();
        this.strikeoutPosition = tTFDataStream.readSignedShort();
        this.familyClass = tTFDataStream.readUnsignedByte();
        this.familySubClass = tTFDataStream.readUnsignedByte();
        this.panose = tTFDataStream.read(10);
        this.unicodeRange1 = tTFDataStream.readUnsignedInt();
        this.unicodeRange2 = tTFDataStream.readUnsignedInt();
        this.unicodeRange3 = tTFDataStream.readUnsignedInt();
        this.unicodeRange4 = tTFDataStream.readUnsignedInt();
        this.achVendId = tTFDataStream.readString(4);
        this.fsSelection = tTFDataStream.readUnsignedShort();
        this.firstCharIndex = tTFDataStream.readUnsignedShort();
        this.lastCharIndex = tTFDataStream.readUnsignedShort();
        this.typoAscender = tTFDataStream.readSignedShort();
        this.typoDescender = tTFDataStream.readSignedShort();
        this.typoLineGap = tTFDataStream.readSignedShort();
        this.winAscent = tTFDataStream.readUnsignedShort();
        this.winDescent = tTFDataStream.readUnsignedShort();
        if (this.version >= 1) {
            this.codePageRange1 = tTFDataStream.readUnsignedInt();
            this.codePageRange2 = tTFDataStream.readUnsignedInt();
        }
        if (this.version >= 1.2d) {
            this.sxHeight = tTFDataStream.readSignedShort();
            this.sCapHeight = tTFDataStream.readSignedShort();
            this.usDefaultChar = tTFDataStream.readUnsignedShort();
            this.usBreakChar = tTFDataStream.readUnsignedShort();
            this.usMaxContext = tTFDataStream.readUnsignedShort();
        }
        this.initialized = true;
    }
}
