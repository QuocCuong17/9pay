package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import org.apache.fontbox.afm.FontMetrics;
import org.apache.fontbox.cmap.CMap;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

/* loaded from: classes5.dex */
public abstract class PDFont implements COSObjectable, PDFontLike {
    protected static final Matrix DEFAULT_FONT_MATRIX = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
    private final FontMetrics afmStandard14;
    private float avgFontWidth;
    protected final COSDictionary dict;
    private PDFontDescriptor fontDescriptor;
    private float fontWidthOfSpace;
    private final CMap toUnicodeCMap;
    private List<Integer> widths;

    public abstract void addToSubset(int i);

    protected abstract byte[] encode(int i) throws IOException;

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public abstract BoundingBox getBoundingBox() throws IOException;

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public abstract String getName();

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public abstract boolean isDamaged();

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public abstract boolean isEmbedded();

    public abstract boolean isVertical();

    public abstract int readCode(InputStream inputStream) throws IOException;

    public abstract void subset() throws IOException;

    public abstract boolean willBeSubset();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont() {
        this.fontWidthOfSpace = -1.0f;
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        this.fontDescriptor = null;
        this.afmStandard14 = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDFont(String str) {
        this.fontWidthOfSpace = -1.0f;
        this.dict = new COSDictionary();
        this.toUnicodeCMap = null;
        FontMetrics afm = Standard14Fonts.getAFM(str);
        this.afmStandard14 = afm;
        if (afm == null) {
            throw new IllegalArgumentException("No AFM for font " + str);
        }
        this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(afm);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDFont(COSDictionary cOSDictionary) throws IOException {
        this.fontWidthOfSpace = -1.0f;
        this.dict = cOSDictionary;
        FontMetrics afm = Standard14Fonts.getAFM(getName());
        this.afmStandard14 = afm;
        COSDictionary cOSDictionary2 = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.FONT_DESC);
        if (cOSDictionary2 != null) {
            this.fontDescriptor = new PDFontDescriptor(cOSDictionary2);
        } else if (afm != null) {
            this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(afm);
        } else {
            this.fontDescriptor = null;
        }
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TO_UNICODE);
        if (dictionaryObject != null) {
            CMap readCMap = readCMap(dictionaryObject);
            this.toUnicodeCMap = readCMap;
            if (readCMap == null || readCMap.hasUnicodeMappings()) {
                return;
            }
            Log.w("PdfBoxAndroid", "Invalid ToUnicode CMap in font " + getName());
            return;
        }
        this.toUnicodeCMap = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final FontMetrics getStandard14AFM() {
        return this.afmStandard14;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setFontDescriptor(PDFontDescriptor pDFontDescriptor) {
        this.fontDescriptor = pDFontDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final CMap readCMap(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSName) {
            return CMapManager.getPredefinedCMap(((COSName) cOSBase).getName());
        }
        if (cOSBase instanceof COSStream) {
            InputStream inputStream = null;
            try {
                inputStream = ((COSStream) cOSBase).getUnfilteredStream();
                return CMapManager.parseCMap(inputStream);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
        throw new IOException("Expected Name or Stream");
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public Vector getPositionVector(int i) {
        throw new UnsupportedOperationException("Horizontal fonts have no position vector");
    }

    public Vector getDisplacement(int i) throws IOException {
        return new Vector(getWidth(i) / 1000.0f, 0.0f);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int i) throws IOException {
        if (this.dict.containsKey(COSName.WIDTHS) || this.dict.containsKey(COSName.MISSING_WIDTH)) {
            int i2 = this.dict.getInt(COSName.FIRST_CHAR, -1);
            int i3 = this.dict.getInt(COSName.LAST_CHAR, -1);
            if (getWidths().size() > 0 && i >= i2 && i <= i3) {
                return getWidths().get(i - i2).floatValue();
            }
            PDFontDescriptor fontDescriptor = getFontDescriptor();
            if (fontDescriptor != null) {
                return fontDescriptor.getMissingWidth();
            }
        }
        return getWidthFromFont(i);
    }

    public final byte[] encode(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < str.length()) {
            int codePointAt = str.codePointAt(i);
            byteArrayOutputStream.write(encode(codePointAt));
            i += Character.charCount(codePointAt);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public float getStringWidth(String str) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encode(str));
        float f = 0.0f;
        while (byteArrayInputStream.available() > 0) {
            f += getWidth(readCode(byteArrayInputStream));
        }
        return f;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        float f;
        float f2;
        float f3 = this.avgFontWidth;
        if (f3 == 0.0f) {
            COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.WIDTHS);
            if (cOSArray != null) {
                f = 0.0f;
                f2 = 0.0f;
                for (int i = 0; i < cOSArray.size(); i++) {
                    COSNumber cOSNumber = (COSNumber) cOSArray.getObject(i);
                    if (cOSNumber.floatValue() > 0.0f) {
                        f += cOSNumber.floatValue();
                        f2 += 1.0f;
                    }
                }
            } else {
                f = 0.0f;
                f2 = 0.0f;
            }
            f3 = f > 0.0f ? f / f2 : 0.0f;
            this.avgFontWidth = f3;
        }
        return f3;
    }

    public String toUnicode(int i, GlyphList glyphList) throws IOException {
        return toUnicode(i);
    }

    public String toUnicode(int i) throws IOException {
        CMap cMap = this.toUnicodeCMap;
        if (cMap == null) {
            return null;
        }
        if (cMap.getName() != null && this.toUnicodeCMap.getName().startsWith("Identity-")) {
            return new String(new char[]{(char) i});
        }
        return this.toUnicodeCMap.toUnicode(i);
    }

    public String getType() {
        return this.dict.getNameAsString(COSName.TYPE);
    }

    public String getSubType() {
        return this.dict.getNameAsString(COSName.SUBTYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<Integer> getWidths() {
        if (this.widths == null) {
            COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.WIDTHS);
            if (cOSArray != null) {
                this.widths = COSArrayList.convertIntegerCOSArrayToList(cOSArray);
            } else {
                this.widths = Collections.emptyList();
            }
        }
        return this.widths;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        return DEFAULT_FONT_MATRIX;
    }

    public float getSpaceWidth() {
        if (this.fontWidthOfSpace == -1.0f) {
            try {
                if (this.dict.getDictionaryObject(COSName.TO_UNICODE) != null) {
                    int spaceMapping = this.toUnicodeCMap.getSpaceMapping();
                    if (spaceMapping > -1) {
                        this.fontWidthOfSpace = getWidth(spaceMapping);
                    }
                } else {
                    this.fontWidthOfSpace = getWidth(32);
                }
                if (this.fontWidthOfSpace <= 0.0f) {
                    this.fontWidthOfSpace = getAverageFontWidth();
                }
            } catch (Exception e) {
                Log.e("PdfBoxAndroid", "Can't determine the width of the space character, assuming 250", e);
                this.fontWidthOfSpace = 250.0f;
            }
        }
        return this.fontWidthOfSpace;
    }

    public boolean isStandard14() {
        if (isEmbedded()) {
            return false;
        }
        return Standard14Fonts.containsName(getName());
    }

    public boolean equals(Object obj) {
        return (obj instanceof PDFont) && ((PDFont) obj).getCOSObject() == getCOSObject();
    }

    public int hashCode() {
        return getCOSObject().hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }
}
