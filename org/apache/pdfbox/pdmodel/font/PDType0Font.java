package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import com.beust.jcommander.Parameters;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.fontbox.cmap.CMap;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

/* loaded from: classes5.dex */
public class PDType0Font extends PDFont {
    private CMap cMap;
    private CMap cMapUCS2;
    private final PDCIDFont descendantFont;
    private PDCIDFontType2Embedder embedder;
    private boolean isCMapPredefined;

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public boolean isStandard14() {
        return false;
    }

    public static PDType0Font load(PDDocument pDDocument, File file) throws IOException {
        return new PDType0Font(pDDocument, new FileInputStream(file), true);
    }

    public static PDType0Font load(PDDocument pDDocument, InputStream inputStream) throws IOException {
        return new PDType0Font(pDDocument, inputStream, true);
    }

    public static PDType0Font load(PDDocument pDDocument, InputStream inputStream, boolean z) throws IOException {
        return new PDType0Font(pDDocument, inputStream, z);
    }

    public PDType0Font(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        COSDictionary cOSDictionary2 = (COSDictionary) ((COSArray) this.dict.getDictionaryObject(COSName.DESCENDANT_FONTS)).getObject(0);
        if (cOSDictionary2 == null) {
            throw new IOException("Missing descendant font dictionary");
        }
        readEncoding();
        fetchCMapUCS2();
        this.descendantFont = PDFontFactory.createDescendantFont(cOSDictionary2, this);
    }

    private PDType0Font(PDDocument pDDocument, InputStream inputStream, boolean z) throws IOException {
        PDCIDFontType2Embedder pDCIDFontType2Embedder = new PDCIDFontType2Embedder(pDDocument, this.dict, inputStream, z, this);
        this.embedder = pDCIDFontType2Embedder;
        this.descendantFont = pDCIDFontType2Embedder.getCIDFont();
        readEncoding();
        fetchCMapUCS2();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public void addToSubset(int i) {
        if (!willBeSubset()) {
            throw new IllegalStateException("This font was created with subsetting disabled");
        }
        this.embedder.addToSubset(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public void subset() throws IOException {
        if (!willBeSubset()) {
            throw new IllegalStateException("This font was created with subsetting disabled");
        }
        this.embedder.subset();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public boolean willBeSubset() {
        return this.embedder.needsSubset();
    }

    private void readEncoding() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
        if (dictionaryObject instanceof COSName) {
            CMap predefinedCMap = CMapManager.getPredefinedCMap(((COSName) dictionaryObject).getName());
            this.cMap = predefinedCMap;
            if (predefinedCMap != null) {
                this.isCMapPredefined = true;
                return;
            }
            throw new IOException("Missing required CMap");
        }
        if (dictionaryObject != null) {
            CMap readCMap = readCMap(dictionaryObject);
            this.cMap = readCMap;
            if (readCMap == null) {
                throw new IOException("Missing required CMap");
            }
            if (readCMap.hasCIDMappings()) {
                return;
            }
            Log.w("PdfBoxAndroid", "Invalid Encoding CMap in font " + getName());
        }
    }

    private void fetchCMapUCS2() throws IOException {
        CMap predefinedCMap;
        if (this.isCMapPredefined) {
            COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
            String name = dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : null;
            if (name == null || name.equals("Identity-H") || name.equals("Identity-V") || (predefinedCMap = CMapManager.getPredefinedCMap(name)) == null) {
                return;
            }
            CMap predefinedCMap2 = CMapManager.getPredefinedCMap(predefinedCMap.getRegistry() + Parameters.DEFAULT_OPTION_PREFIXES + predefinedCMap.getOrdering() + "-UCS2");
            if (predefinedCMap2 != null) {
                this.cMapUCS2 = predefinedCMap2;
            }
        }
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    public PDCIDFont getDescendantFont() {
        return this.descendantFont;
    }

    public CMap getCMap() {
        return this.cMap;
    }

    public CMap getCMapUCS2() {
        return this.cMapUCS2;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public PDFontDescriptor getFontDescriptor() {
        return this.descendantFont.getFontDescriptor();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Matrix getFontMatrix() {
        return this.descendantFont.getFontMatrix();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public boolean isVertical() {
        return this.cMap.getWMode() == 1;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getHeight(int i) throws IOException {
        return this.descendantFont.getHeight(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    protected byte[] encode(int i) throws IOException {
        return this.descendantFont.encode(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getAverageFontWidth() {
        return this.descendantFont.getAverageFontWidth();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public Vector getPositionVector(int i) {
        return this.descendantFont.getPositionVector(i).scale(-0.001f);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public Vector getDisplacement(int i) throws IOException {
        if (isVertical()) {
            return new Vector(0.0f, this.descendantFont.getVerticalDisplacementVectorY(i) / 1000.0f);
        }
        return super.getDisplacement(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidth(int i) throws IOException {
        return this.descendantFont.getWidth(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFontLike
    public float getWidthFromFont(int i) throws IOException {
        return this.descendantFont.getWidthFromFont(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isEmbedded() {
        return this.descendantFont.isEmbedded();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public String toUnicode(int i) throws IOException {
        String unicode = super.toUnicode(i);
        if (unicode != null) {
            return unicode;
        }
        if (!this.isCMapPredefined || this.cMapUCS2 == null) {
            return null;
        }
        return this.cMapUCS2.toUnicode(codeToCID(i));
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public String getName() {
        return getBaseFont();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public BoundingBox getBoundingBox() throws IOException {
        return this.descendantFont.getBoundingBox();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public int readCode(InputStream inputStream) throws IOException {
        return this.cMap.readCode(inputStream);
    }

    public int codeToCID(int i) {
        return this.descendantFont.codeToCID(i);
    }

    public int codeToGID(int i) throws IOException {
        return this.descendantFont.codeToGID(i);
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont, org.apache.pdfbox.pdmodel.font.PDFontLike
    public boolean isDamaged() {
        return this.descendantFont.isDamaged();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public String toString() {
        return getClass().getSimpleName() + RemoteSettings.FORWARD_SLASH_STRING + (getDescendantFont() != null ? getDescendantFont().getClass().getSimpleName() : null) + " " + getBaseFont();
    }
}
