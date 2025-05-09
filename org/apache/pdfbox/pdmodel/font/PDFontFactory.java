package org.apache.pdfbox.pdmodel.font;

import android.util.Log;
import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDFontFactory {
    private PDFontFactory() {
    }

    public static PDFont createFont(COSDictionary cOSDictionary) throws IOException {
        COSName cOSName = cOSDictionary.getCOSName(COSName.TYPE, COSName.FONT);
        if (!COSName.FONT.equals(cOSName)) {
            Log.e("PdfBoxAndroid", "Expected 'Font' dictionary but found '" + cOSName.getName() + "'");
        }
        COSName cOSName2 = cOSDictionary.getCOSName(COSName.SUBTYPE);
        if (COSName.TYPE1.equals(cOSName2)) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.FONT_DESC);
            if ((dictionaryObject instanceof COSDictionary) && ((COSDictionary) dictionaryObject).containsKey(COSName.FONT_FILE3)) {
                return new PDType1CFont(cOSDictionary);
            }
            return new PDType1Font(cOSDictionary);
        }
        if (COSName.MM_TYPE1.equals(cOSName2)) {
            COSBase dictionaryObject2 = cOSDictionary.getDictionaryObject(COSName.FONT_DESC);
            if ((dictionaryObject2 instanceof COSDictionary) && ((COSDictionary) dictionaryObject2).containsKey(COSName.FONT_FILE3)) {
                return new PDType1CFont(cOSDictionary);
            }
            return new PDMMType1Font(cOSDictionary);
        }
        if (COSName.TRUE_TYPE.equals(cOSName2)) {
            return new PDTrueTypeFont(cOSDictionary);
        }
        if (COSName.TYPE3.equals(cOSName2)) {
            return new PDType3Font(cOSDictionary);
        }
        if (COSName.TYPE0.equals(cOSName2)) {
            return new PDType0Font(cOSDictionary);
        }
        if (COSName.CID_FONT_TYPE0.equals(cOSName2)) {
            throw new IllegalArgumentException("Type 0 descendant font not allowed");
        }
        if (COSName.CID_FONT_TYPE2.equals(cOSName2)) {
            throw new IllegalArgumentException("Type 2 descendant font not allowed");
        }
        Log.w("PdfBoxAndroid", "Invalid font subtype '" + cOSName2 + "'");
        return new PDType1Font(cOSDictionary);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PDCIDFont createDescendantFont(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        COSName cOSName = cOSDictionary.getCOSName(COSName.TYPE, COSName.FONT);
        if (!COSName.FONT.equals(cOSName)) {
            throw new IllegalArgumentException("Expected 'Font' dictionary but found '" + cOSName.getName() + "'");
        }
        COSName cOSName2 = cOSDictionary.getCOSName(COSName.SUBTYPE);
        if (COSName.CID_FONT_TYPE0.equals(cOSName2)) {
            return new PDCIDFontType0(cOSDictionary, pDType0Font);
        }
        if (COSName.CID_FONT_TYPE2.equals(cOSName2)) {
            return new PDCIDFontType2(cOSDictionary, pDType0Font);
        }
        throw new IOException("Invalid font type: " + cOSName);
    }

    public static PDFont createDefaultFont() throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        cOSDictionary.setItem(COSName.SUBTYPE, (COSBase) COSName.TRUE_TYPE);
        cOSDictionary.setString(COSName.BASE_FONT, "Arial");
        return createFont(cOSDictionary);
    }
}
