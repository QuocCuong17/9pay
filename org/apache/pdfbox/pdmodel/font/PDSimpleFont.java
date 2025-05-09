package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.pdfbox.pdmodel.font.encoding.GlyphList;
import org.apache.pdfbox.pdmodel.font.encoding.MacRomanEncoding;
import org.apache.pdfbox.pdmodel.font.encoding.StandardEncoding;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

/* loaded from: classes5.dex */
public abstract class PDSimpleFont extends PDFont {
    protected Encoding encoding;
    protected GlyphList glyphList;
    private Boolean isSymbolic;
    private final Set<Integer> noUnicode;

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public boolean isVertical() {
        return false;
    }

    protected abstract Encoding readEncodingFromFont() throws IOException;

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public boolean willBeSubset() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDSimpleFont() {
        this.noUnicode = new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDSimpleFont(String str) {
        super(str);
        this.noUnicode = new HashSet();
        this.encoding = WinAnsiEncoding.INSTANCE;
        if ("ZapfDingbats".equals(str)) {
            this.glyphList = GlyphList.getZapfDingbats();
        } else {
            this.glyphList = GlyphList.getAdobeGlyphList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDSimpleFont(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        this.noUnicode = new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void readEncoding() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
        if (dictionaryObject != null) {
            if (dictionaryObject instanceof COSName) {
                COSName cOSName = (COSName) dictionaryObject;
                Encoding encoding = Encoding.getInstance(cOSName);
                this.encoding = encoding;
                if (encoding == null) {
                    Log.w("PdfBoxAndroid", "Unknown encoding: " + cOSName.getName());
                    this.encoding = readEncodingFromFont();
                }
            } else if (dictionaryObject instanceof COSDictionary) {
                COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
                Boolean symbolicFlag = getSymbolicFlag();
                Encoding readEncodingFromFont = (cOSDictionary.containsKey(COSName.BASE_ENCODING) || !(symbolicFlag != null && symbolicFlag.booleanValue())) ? null : readEncodingFromFont();
                if (symbolicFlag == null) {
                    symbolicFlag = false;
                }
                if (readEncodingFromFont == null && !cOSDictionary.containsKey(COSName.BASE_ENCODING) && symbolicFlag.booleanValue()) {
                    this.encoding = null;
                } else {
                    this.encoding = new DictionaryEncoding(cOSDictionary, !symbolicFlag.booleanValue(), readEncodingFromFont);
                }
            }
        } else {
            this.encoding = readEncodingFromFont();
        }
        if (this.encoding == null && getSymbolicFlag() != null && !getSymbolicFlag().booleanValue()) {
            this.encoding = StandardEncoding.INSTANCE;
        }
        if (this.encoding == null && isStandard14() && !getName().equals("Symbol") && !getName().equals("ZapfDingbats")) {
            this.encoding = StandardEncoding.INSTANCE;
        }
        if ("ZapfDingbats".equals(getName())) {
            this.glyphList = GlyphList.getZapfDingbats();
        } else {
            this.glyphList = GlyphList.getAdobeGlyphList();
        }
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public GlyphList getGlyphList() {
        return this.glyphList;
    }

    public final boolean isSymbolic() {
        if (this.isSymbolic == null) {
            Boolean isFontSymbolic = isFontSymbolic();
            if (isFontSymbolic != null) {
                this.isSymbolic = isFontSymbolic;
            } else {
                this.isSymbolic = true;
            }
        }
        return this.isSymbolic.booleanValue();
    }

    protected Boolean isFontSymbolic() {
        Boolean symbolicFlag = getSymbolicFlag();
        if (symbolicFlag != null) {
            return symbolicFlag;
        }
        if (isStandard14()) {
            return Boolean.valueOf(getName().equals("Symbol") || getName().equals("ZapfDingbats"));
        }
        Encoding encoding = this.encoding;
        if (encoding == null) {
            if (!(this instanceof PDTrueTypeFont)) {
                throw new IllegalStateException("PDFBox bug: encoding should not be null!");
            }
            return true;
        }
        if ((encoding instanceof WinAnsiEncoding) || (encoding instanceof MacRomanEncoding) || (encoding instanceof StandardEncoding)) {
            return false;
        }
        if (!(encoding instanceof DictionaryEncoding)) {
            return null;
        }
        for (String str : ((DictionaryEncoding) encoding).getDifferences().values()) {
            if (!str.equals(".notdef") && (!WinAnsiEncoding.INSTANCE.contains(str) || !MacRomanEncoding.INSTANCE.contains(str) || !StandardEncoding.INSTANCE.contains(str))) {
                return true;
            }
        }
        return false;
    }

    protected final Boolean getSymbolicFlag() {
        if (getFontDescriptor() != null) {
            return Boolean.valueOf(getFontDescriptor().isSymbolic());
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public String toUnicode(int i) throws IOException {
        return toUnicode(i, GlyphList.getAdobeGlyphList());
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public String toUnicode(int i, GlyphList glyphList) throws IOException {
        String str;
        if (this.glyphList != GlyphList.getAdobeGlyphList()) {
            glyphList = this.glyphList;
        }
        String unicode = super.toUnicode(i);
        if (unicode != null) {
            return unicode;
        }
        Encoding encoding = this.encoding;
        if (encoding != null) {
            str = encoding.getName(i);
            String unicode2 = glyphList.toUnicode(str);
            if (unicode2 != null) {
                return unicode2;
            }
        } else {
            str = null;
        }
        if (!this.noUnicode.contains(Integer.valueOf(i))) {
            this.noUnicode.add(Integer.valueOf(i));
            if (str != null) {
                Log.w("PdfBoxAndroid", "No Unicode mapping for " + str + " (" + i + ") in font " + getName());
            } else {
                Log.w("PdfBoxAndroid", "No Unicode mapping for character code " + i + " in font " + getName());
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float getStandard14Width(int i) {
        if (getStandard14AFM() != null) {
            String name = getEncoding().getName(i);
            if (name.equals(".notdef")) {
                return 250.0f;
            }
            return getStandard14AFM().getCharacterWidth(name);
        }
        throw new IllegalStateException("No AFM");
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public boolean isStandard14() {
        if (!(getEncoding() instanceof DictionaryEncoding) || ((DictionaryEncoding) getEncoding()).getDifferences().size() <= 0) {
            return super.isStandard14();
        }
        return false;
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public void addToSubset(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.pdmodel.font.PDFont
    public void subset() throws IOException {
        throw new UnsupportedOperationException();
    }
}
