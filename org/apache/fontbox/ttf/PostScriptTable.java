package org.apache.fontbox.ttf;

import java.io.IOException;

/* loaded from: classes5.dex */
public class PostScriptTable extends TTFTable {
    public static final String TAG = "post";
    private float formatType;
    private String[] glyphNames = null;
    private long isFixedPitch;
    private float italicAngle;
    private long maxMemType1;
    private long maxMemType42;
    private long minMemType1;
    private long minMemType42;
    private short underlinePosition;
    private short underlineThickness;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.formatType = tTFDataStream.read32Fixed();
        this.italicAngle = tTFDataStream.read32Fixed();
        this.underlinePosition = tTFDataStream.readSignedShort();
        this.underlineThickness = tTFDataStream.readSignedShort();
        this.isFixedPitch = tTFDataStream.readUnsignedInt();
        this.minMemType42 = tTFDataStream.readUnsignedInt();
        this.maxMemType42 = tTFDataStream.readUnsignedInt();
        this.minMemType1 = tTFDataStream.readUnsignedInt();
        this.maxMemType1 = tTFDataStream.readUnsignedInt();
        float f = this.formatType;
        int i = 0;
        if (f == 1.0f) {
            this.glyphNames = new String[258];
            System.arraycopy(WGL4Names.MAC_GLYPH_NAMES, 0, this.glyphNames, 0, 258);
        } else if (f == 2.0f) {
            int readUnsignedShort = tTFDataStream.readUnsignedShort();
            int[] iArr = new int[readUnsignedShort];
            this.glyphNames = new String[readUnsignedShort];
            int i2 = Integer.MIN_VALUE;
            for (int i3 = 0; i3 < readUnsignedShort; i3++) {
                int readUnsignedShort2 = tTFDataStream.readUnsignedShort();
                iArr[i3] = readUnsignedShort2;
                if (readUnsignedShort2 <= 32767) {
                    i2 = Math.max(i2, readUnsignedShort2);
                }
            }
            String[] strArr = null;
            if (i2 >= 258) {
                int i4 = (i2 - 258) + 1;
                strArr = new String[i4];
                for (int i5 = 0; i5 < i4; i5++) {
                    strArr[i5] = tTFDataStream.readString(tTFDataStream.readUnsignedByte());
                }
            }
            while (i < readUnsignedShort) {
                int i6 = iArr[i];
                if (i6 < 258) {
                    this.glyphNames[i] = WGL4Names.MAC_GLYPH_NAMES[i6];
                } else if (i6 >= 258 && i6 <= 32767) {
                    this.glyphNames[i] = strArr[i6 - 258];
                } else {
                    this.glyphNames[i] = ".undefined";
                }
                i++;
            }
        } else if (f == 2.5f) {
            int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
            int[] iArr2 = new int[numberOfGlyphs];
            int i7 = 0;
            while (i7 < numberOfGlyphs) {
                int i8 = i7 + 1;
                iArr2[i7] = tTFDataStream.readSignedByte() + i8;
                i7 = i8;
            }
            this.glyphNames = new String[numberOfGlyphs];
            while (i < this.glyphNames.length) {
                String str = WGL4Names.MAC_GLYPH_NAMES[iArr2[i]];
                if (str != null) {
                    this.glyphNames[i] = str;
                }
                i++;
            }
        }
        this.initialized = true;
    }

    public float getFormatType() {
        return this.formatType;
    }

    public void setFormatType(float f) {
        this.formatType = f;
    }

    public long getIsFixedPitch() {
        return this.isFixedPitch;
    }

    public void setIsFixedPitch(long j) {
        this.isFixedPitch = j;
    }

    public float getItalicAngle() {
        return this.italicAngle;
    }

    public void setItalicAngle(float f) {
        this.italicAngle = f;
    }

    public long getMaxMemType1() {
        return this.maxMemType1;
    }

    public void setMaxMemType1(long j) {
        this.maxMemType1 = j;
    }

    public long getMaxMemType42() {
        return this.maxMemType42;
    }

    public void setMaxMemType42(long j) {
        this.maxMemType42 = j;
    }

    public long getMinMemType1() {
        return this.minMemType1;
    }

    public void setMimMemType1(long j) {
        this.minMemType1 = j;
    }

    public long getMinMemType42() {
        return this.minMemType42;
    }

    public void setMinMemType42(long j) {
        this.minMemType42 = j;
    }

    public short getUnderlinePosition() {
        return this.underlinePosition;
    }

    public void setUnderlinePosition(short s) {
        this.underlinePosition = s;
    }

    public short getUnderlineThickness() {
        return this.underlineThickness;
    }

    public void setUnderlineThickness(short s) {
        this.underlineThickness = s;
    }

    public String[] getGlyphNames() {
        return this.glyphNames;
    }

    public void setGlyphNames(String[] strArr) {
        this.glyphNames = strArr;
    }

    public String getName(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this.glyphNames) == null || i > strArr.length) {
            return null;
        }
        return strArr[i];
    }
}
