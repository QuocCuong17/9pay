package org.apache.pdfbox.pdmodel.font;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;

/* loaded from: classes5.dex */
public final class PDFontDescriptor implements COSObjectable {
    private static final int FLAG_ALL_CAP = 65536;
    private static final int FLAG_FIXED_PITCH = 1;
    private static final int FLAG_FORCE_BOLD = 262144;
    private static final int FLAG_ITALIC = 64;
    private static final int FLAG_NON_SYMBOLIC = 32;
    private static final int FLAG_SCRIPT = 8;
    private static final int FLAG_SERIF = 2;
    private static final int FLAG_SMALL_CAP = 131072;
    private static final int FLAG_SYMBOLIC = 4;
    private final COSDictionary dic;
    private float xHeight = Float.NEGATIVE_INFINITY;
    private float capHeight = Float.NEGATIVE_INFINITY;
    private int flags = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFontDescriptor() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dic = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT_DESC);
    }

    public PDFontDescriptor(COSDictionary cOSDictionary) {
        this.dic = cOSDictionary;
    }

    public boolean isFixedPitch() {
        return isFlagBitOn(1);
    }

    public void setFixedPitch(boolean z) {
        setFlagBit(1, z);
    }

    public boolean isSerif() {
        return isFlagBitOn(2);
    }

    public void setSerif(boolean z) {
        setFlagBit(2, z);
    }

    public boolean isSymbolic() {
        return isFlagBitOn(4);
    }

    public void setSymbolic(boolean z) {
        setFlagBit(4, z);
    }

    public boolean isScript() {
        return isFlagBitOn(8);
    }

    public void setScript(boolean z) {
        setFlagBit(8, z);
    }

    public boolean isNonSymbolic() {
        return isFlagBitOn(32);
    }

    public void setNonSymbolic(boolean z) {
        setFlagBit(32, z);
    }

    public boolean isItalic() {
        return isFlagBitOn(64);
    }

    public void setItalic(boolean z) {
        setFlagBit(64, z);
    }

    public boolean isAllCap() {
        return isFlagBitOn(65536);
    }

    public void setAllCap(boolean z) {
        setFlagBit(65536, z);
    }

    public boolean isSmallCap() {
        return isFlagBitOn(131072);
    }

    public void setSmallCap(boolean z) {
        setFlagBit(131072, z);
    }

    public boolean isForceBold() {
        return isFlagBitOn(262144);
    }

    public void setForceBold(boolean z) {
        setFlagBit(262144, z);
    }

    private boolean isFlagBitOn(int i) {
        return (i & getFlags()) != 0;
    }

    private void setFlagBit(int i, boolean z) {
        int flags = getFlags();
        setFlags(z ? i | flags : (i ^ (-1)) & flags);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dic;
    }

    public String getFontName() {
        COSName cOSName = (COSName) this.dic.getDictionaryObject(COSName.FONT_NAME);
        if (cOSName != null) {
            return cOSName.getName();
        }
        return null;
    }

    public void setFontName(String str) {
        this.dic.setItem(COSName.FONT_NAME, (COSBase) (str != null ? COSName.getPDFName(str) : null));
    }

    public String getFontFamily() {
        COSString cOSString = (COSString) this.dic.getDictionaryObject(COSName.FONT_FAMILY);
        if (cOSString != null) {
            return cOSString.getString();
        }
        return null;
    }

    public void setFontFamily(String str) {
        this.dic.setItem(COSName.FONT_FAMILY, (COSBase) (str != null ? new COSString(str) : null));
    }

    public float getFontWeight() {
        return this.dic.getFloat(COSName.FONT_WEIGHT, 0.0f);
    }

    public void setFontWeight(float f) {
        this.dic.setFloat(COSName.FONT_WEIGHT, f);
    }

    public String getFontStretch() {
        COSName cOSName = (COSName) this.dic.getDictionaryObject(COSName.FONT_STRETCH);
        if (cOSName != null) {
            return cOSName.getName();
        }
        return null;
    }

    public void setFontStretch(String str) {
        this.dic.setItem(COSName.FONT_STRETCH, (COSBase) (str != null ? COSName.getPDFName(str) : null));
    }

    public int getFlags() {
        if (this.flags == -1) {
            this.flags = this.dic.getInt(COSName.FLAGS, 0);
        }
        return this.flags;
    }

    public void setFlags(int i) {
        this.dic.setInt(COSName.FLAGS, i);
        this.flags = i;
    }

    public PDRectangle getFontBoundingBox() {
        COSArray cOSArray = (COSArray) this.dic.getDictionaryObject(COSName.FONT_BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setFontBoundingBox(PDRectangle pDRectangle) {
        this.dic.setItem(COSName.FONT_BBOX, (COSBase) (pDRectangle != null ? pDRectangle.getCOSArray() : null));
    }

    public float getItalicAngle() {
        return this.dic.getFloat(COSName.ITALIC_ANGLE, 0.0f);
    }

    public void setItalicAngle(float f) {
        this.dic.setFloat(COSName.ITALIC_ANGLE, f);
    }

    public float getAscent() {
        return this.dic.getFloat(COSName.ASCENT, 0.0f);
    }

    public void setAscent(float f) {
        this.dic.setFloat(COSName.ASCENT, f);
    }

    public float getDescent() {
        return this.dic.getFloat(COSName.DESCENT, 0.0f);
    }

    public void setDescent(float f) {
        this.dic.setFloat(COSName.DESCENT, f);
    }

    public float getLeading() {
        return this.dic.getFloat(COSName.LEADING, 0.0f);
    }

    public void setLeading(float f) {
        this.dic.setFloat(COSName.LEADING, f);
    }

    public float getCapHeight() {
        if (this.capHeight == Float.NEGATIVE_INFINITY) {
            this.capHeight = Math.abs(this.dic.getFloat(COSName.CAP_HEIGHT, 0.0f));
        }
        return this.capHeight;
    }

    public void setCapHeight(float f) {
        this.dic.setFloat(COSName.CAP_HEIGHT, f);
        this.capHeight = f;
    }

    public float getXHeight() {
        if (this.xHeight == Float.NEGATIVE_INFINITY) {
            this.xHeight = Math.abs(this.dic.getFloat(COSName.XHEIGHT, 0.0f));
        }
        return this.xHeight;
    }

    public void setXHeight(float f) {
        this.dic.setFloat(COSName.XHEIGHT, f);
        this.xHeight = f;
    }

    public float getStemV() {
        return this.dic.getFloat(COSName.STEM_V, 0.0f);
    }

    public void setStemV(float f) {
        this.dic.setFloat(COSName.STEM_V, f);
    }

    public float getStemH() {
        return this.dic.getFloat(COSName.STEM_H, 0.0f);
    }

    public void setStemH(float f) {
        this.dic.setFloat(COSName.STEM_H, f);
    }

    public float getAverageWidth() {
        return this.dic.getFloat(COSName.AVG_WIDTH, 0.0f);
    }

    public void setAverageWidth(float f) {
        this.dic.setFloat(COSName.AVG_WIDTH, f);
    }

    public float getMaxWidth() {
        return this.dic.getFloat(COSName.MAX_WIDTH, 0.0f);
    }

    public void setMaxWidth(float f) {
        this.dic.setFloat(COSName.MAX_WIDTH, f);
    }

    public boolean hasWidths() {
        return this.dic.containsKey(COSName.WIDTHS) || this.dic.containsKey(COSName.MISSING_WIDTH);
    }

    public float getMissingWidth() {
        return this.dic.getFloat(COSName.MISSING_WIDTH, 0.0f);
    }

    public void setMissingWidth(float f) {
        this.dic.setFloat(COSName.MISSING_WIDTH, f);
    }

    public String getCharSet() {
        COSString cOSString = (COSString) this.dic.getDictionaryObject(COSName.CHAR_SET);
        if (cOSString != null) {
            return cOSString.getString();
        }
        return null;
    }

    public void setCharacterSet(String str) {
        this.dic.setItem(COSName.CHAR_SET, (COSBase) (str != null ? new COSString(str) : null));
    }

    public PDStream getFontFile() {
        COSBase dictionaryObject = this.dic.getDictionaryObject(COSName.FONT_FILE);
        if (dictionaryObject instanceof COSStream) {
            return new PDStream((COSStream) dictionaryObject);
        }
        return null;
    }

    public void setFontFile(PDStream pDStream) {
        this.dic.setItem(COSName.FONT_FILE, pDStream);
    }

    public PDStream getFontFile2() {
        COSBase dictionaryObject = this.dic.getDictionaryObject(COSName.FONT_FILE2);
        if (dictionaryObject instanceof COSStream) {
            return new PDStream((COSStream) dictionaryObject);
        }
        return null;
    }

    public void setFontFile2(PDStream pDStream) {
        this.dic.setItem(COSName.FONT_FILE2, pDStream);
    }

    public PDStream getFontFile3() {
        COSBase dictionaryObject = this.dic.getDictionaryObject(COSName.FONT_FILE3);
        if (dictionaryObject instanceof COSStream) {
            return new PDStream((COSStream) dictionaryObject);
        }
        return null;
    }

    public void setFontFile3(PDStream pDStream) {
        this.dic.setItem(COSName.FONT_FILE3, pDStream);
    }

    public void setCIDSet(PDStream pDStream) {
        this.dic.setItem(COSName.CID_SET, pDStream);
    }
}
