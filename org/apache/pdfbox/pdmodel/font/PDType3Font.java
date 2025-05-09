package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

/* loaded from: classes5.dex */
public class PDType3Font extends PDSimpleFont {
    private COSDictionary charProcs;
    private Matrix fontMatrix;
    private PDResources resources;

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return false;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return true;
    }

    public PDType3Font(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        readEncoding();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
        return this.dict.getNameAsString(COSName.NAME);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDSimpleFont
    protected Encoding readEncodingFromFont() throws IOException {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDSimpleFont
    protected Boolean isFontSymbolic() {
        return false;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public Vector getDisplacement(int i) throws IOException {
        return getFontMatrix().transform(new Vector(getWidth(i), 0.0f));
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int i) throws IOException {
        int i2 = this.dict.getInt(COSName.FIRST_CHAR, -1);
        int i3 = this.dict.getInt(COSName.LAST_CHAR, -1);
        if (getWidths().size() > 0 && i >= i2 && i <= i3) {
            return getWidths().get(i - i2).floatValue();
        }
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor != null) {
            return fontDescriptor.getMissingWidth();
        }
        Log.e("PdfBoxAndroid", "No width for glyph " + i + " in font " + getName());
        return 0.0f;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) {
        throw new UnsupportedOperationException("not suppported");
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor == null) {
            return 0.0f;
        }
        PDRectangle fontBoundingBox = fontDescriptor.getFontBoundingBox();
        float height = fontBoundingBox != null ? fontBoundingBox.getHeight() / 2.0f : 0.0f;
        if (height == 0.0f) {
            height = fontDescriptor.getCapHeight();
        }
        if (height == 0.0f) {
            height = fontDescriptor.getAscent();
        }
        if (height != 0.0f) {
            return height;
        }
        float xHeight = fontDescriptor.getXHeight();
        return xHeight > 0.0f ? xHeight - fontDescriptor.getDescent() : xHeight;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        throw new UnsupportedOperationException("Not implemented: Type3");
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.FONT_MATRIX);
            if (cOSArray != null) {
                this.fontMatrix = new Matrix(cOSArray);
            } else {
                return super.getFontMatrix();
            }
        }
        return this.fontMatrix;
    }

    public PDResources getResources() {
        COSDictionary cOSDictionary;
        if (this.resources == null && (cOSDictionary = (COSDictionary) this.dict.getDictionaryObject(COSName.RESOURCES)) != null) {
            this.resources = new PDResources(cOSDictionary);
        }
        return this.resources;
    }

    public PDRectangle getFontBBox() {
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.FONT_BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() {
        PDRectangle fontBBox = getFontBBox();
        return new BoundingBox(fontBBox.getLowerLeftX(), fontBBox.getLowerLeftY(), fontBBox.getWidth(), fontBBox.getHeight());
    }

    public COSDictionary getCharProcs() {
        if (this.charProcs == null) {
            this.charProcs = (COSDictionary) this.dict.getDictionaryObject(COSName.CHAR_PROCS);
        }
        return this.charProcs;
    }

    public PDType3CharProc getCharProc(int i) {
        String name = getEncoding().getName(i);
        if (name != null) {
            return new PDType3CharProc(this, (COSStream) getCharProcs().getDictionaryObject(COSName.getPDFName(name)));
        }
        return null;
    }
}
