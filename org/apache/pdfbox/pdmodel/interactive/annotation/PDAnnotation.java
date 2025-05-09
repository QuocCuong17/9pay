package org.apache.pdfbox.pdmodel.interactive.annotation;

import android.util.Log;
import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

/* loaded from: classes5.dex */
public abstract class PDAnnotation implements COSObjectable {
    public static final int FLAG_HIDDEN = 2;
    public static final int FLAG_INVISIBLE = 1;
    public static final int FLAG_LOCKED = 128;
    public static final int FLAG_NO_ROTATE = 16;
    public static final int FLAG_NO_VIEW = 32;
    public static final int FLAG_NO_ZOOM = 8;
    public static final int FLAG_PRINTED = 4;
    public static final int FLAG_READ_ONLY = 64;
    public static final int FLAG_TOGGLE_NO_VIEW = 256;
    private final COSDictionary dictionary;

    public static PDAnnotation createAnnotation(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSDictionary) {
            COSDictionary cOSDictionary = (COSDictionary) cOSBase;
            String nameAsString = cOSDictionary.getNameAsString(COSName.SUBTYPE);
            if ("FileAttachment".equals(nameAsString)) {
                return new PDAnnotationFileAttachment(cOSDictionary);
            }
            if ("Line".equals(nameAsString)) {
                return new PDAnnotationLine(cOSDictionary);
            }
            if ("Link".equals(nameAsString)) {
                return new PDAnnotationLink(cOSDictionary);
            }
            if (PDAnnotationPopup.SUB_TYPE.equals(nameAsString)) {
                return new PDAnnotationPopup(cOSDictionary);
            }
            if ("Stamp".equals(nameAsString)) {
                return new PDAnnotationRubberStamp(cOSDictionary);
            }
            if ("Square".equals(nameAsString) || "Circle".equals(nameAsString)) {
                return new PDAnnotationSquareCircle(cOSDictionary);
            }
            if ("Text".equals(nameAsString)) {
                return new PDAnnotationText(cOSDictionary);
            }
            if ("Highlight".equals(nameAsString) || "Underline".equals(nameAsString) || "Squiggly".equals(nameAsString) || "StrikeOut".equals(nameAsString)) {
                return new PDAnnotationTextMarkup(cOSDictionary);
            }
            if ("Link".equals(nameAsString)) {
                return new PDAnnotationLink(cOSDictionary);
            }
            if (PDAnnotationWidget.SUB_TYPE.equals(nameAsString)) {
                return new PDAnnotationWidget(cOSDictionary);
            }
            if ("FreeText".equals(nameAsString) || "Polygon".equals(nameAsString) || PDAnnotationMarkup.SUB_TYPE_POLYLINE.equals(nameAsString) || "Caret".equals(nameAsString) || "Ink".equals(nameAsString) || "Sound".equals(nameAsString)) {
                return new PDAnnotationMarkup(cOSDictionary);
            }
            PDAnnotationUnknown pDAnnotationUnknown = new PDAnnotationUnknown(cOSDictionary);
            Log.d("PdfBoxAndroid", "Unknown or unsupported annotation subtype " + nameAsString);
            return pDAnnotationUnknown;
        }
        throw new IOException("Error: Unknown annotation type " + cOSBase);
    }

    public PDAnnotation() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public PDAnnotation(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.RECT);
        if (cOSArray != null) {
            if (cOSArray.size() == 4 && (cOSArray.get(0) instanceof COSNumber) && (cOSArray.get(1) instanceof COSNumber) && (cOSArray.get(2) instanceof COSNumber) && (cOSArray.get(3) instanceof COSNumber)) {
                return new PDRectangle(cOSArray);
            }
            Log.w("PdfBoxAndroid", cOSArray + " is not a rectangle array, returning null");
        }
        return null;
    }

    public void setRectangle(PDRectangle pDRectangle) {
        this.dictionary.setItem(COSName.RECT, (COSBase) pDRectangle.getCOSArray());
    }

    public int getAnnotationFlags() {
        return getDictionary().getInt(COSName.F, 0);
    }

    public void setAnnotationFlags(int i) {
        getDictionary().setInt(COSName.F, i);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return getDictionary();
    }

    public COSName getAppearanceState() {
        COSName cOSName = (COSName) getDictionary().getDictionaryObject(COSName.AS);
        if (cOSName != null) {
            return cOSName;
        }
        return null;
    }

    public void setAppearanceState(String str) {
        if (str == null) {
            getDictionary().removeItem(COSName.AS);
        } else {
            getDictionary().setItem(COSName.AS, (COSBase) COSName.getPDFName(str));
        }
    }

    public PDAppearanceDictionary getAppearance() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.AP);
        if (cOSDictionary != null) {
            return new PDAppearanceDictionary(cOSDictionary);
        }
        return null;
    }

    public void setAppearance(PDAppearanceDictionary pDAppearanceDictionary) {
        this.dictionary.setItem(COSName.AP, (COSBase) (pDAppearanceDictionary != null ? pDAppearanceDictionary.getCOSObject() : null));
    }

    public PDAppearanceStream getNormalAppearanceStream() {
        PDAppearanceEntry normalAppearance;
        PDAppearanceDictionary appearance = getAppearance();
        if (appearance == null || (normalAppearance = appearance.getNormalAppearance()) == null) {
            return null;
        }
        if (normalAppearance.isSubDictionary()) {
            return normalAppearance.getSubDictionary().get(getAppearanceState());
        }
        return normalAppearance.getAppearanceStream();
    }

    public boolean isInvisible() {
        return getDictionary().getFlag(COSName.F, 1);
    }

    public void setInvisible(boolean z) {
        getDictionary().setFlag(COSName.F, 1, z);
    }

    public boolean isHidden() {
        return getDictionary().getFlag(COSName.F, 2);
    }

    public void setHidden(boolean z) {
        getDictionary().setFlag(COSName.F, 2, z);
    }

    public boolean isPrinted() {
        return getDictionary().getFlag(COSName.F, 4);
    }

    public void setPrinted(boolean z) {
        getDictionary().setFlag(COSName.F, 4, z);
    }

    public boolean isNoZoom() {
        return getDictionary().getFlag(COSName.F, 8);
    }

    public void setNoZoom(boolean z) {
        getDictionary().setFlag(COSName.F, 8, z);
    }

    public boolean isNoRotate() {
        return getDictionary().getFlag(COSName.F, 16);
    }

    public void setNoRotate(boolean z) {
        getDictionary().setFlag(COSName.F, 16, z);
    }

    public boolean isNoView() {
        return getDictionary().getFlag(COSName.F, 32);
    }

    public void setNoView(boolean z) {
        getDictionary().setFlag(COSName.F, 32, z);
    }

    public boolean isReadOnly() {
        return getDictionary().getFlag(COSName.F, 64);
    }

    public void setReadOnly(boolean z) {
        getDictionary().setFlag(COSName.F, 64, z);
    }

    public boolean isLocked() {
        return getDictionary().getFlag(COSName.F, 128);
    }

    public void setLocked(boolean z) {
        getDictionary().setFlag(COSName.F, 128, z);
    }

    public boolean isToggleNoView() {
        return getDictionary().getFlag(COSName.F, 256);
    }

    public void setToggleNoView(boolean z) {
        getDictionary().setFlag(COSName.F, 256, z);
    }

    public String getContents() {
        return this.dictionary.getString(COSName.CONTENTS);
    }

    public void setContents(String str) {
        this.dictionary.setString(COSName.CONTENTS, str);
    }

    public String getModifiedDate() {
        return getDictionary().getString(COSName.M);
    }

    public void setModifiedDate(String str) {
        getDictionary().setString(COSName.M, str);
    }

    public String getAnnotationName() {
        return getDictionary().getString(COSName.NM);
    }

    public void setAnnotationName(String str) {
        getDictionary().setString(COSName.NM, str);
    }

    public int getStructParent() {
        return getDictionary().getInt(COSName.STRUCT_PARENT, 0);
    }

    public void setStructParent(int i) {
        getDictionary().setInt(COSName.STRUCT_PARENT, i);
    }

    public void setColor(PDColor pDColor) {
        getDictionary().setItem(COSName.C, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getColor() {
        return getColor(COSName.C);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDColor getColor(COSName cOSName) {
        COSBase item = getDictionary().getItem(cOSName);
        PDColorSpace pDColorSpace = null;
        if (!(item instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) item;
        int size = cOSArray.size();
        if (size == 1) {
            pDColorSpace = PDDeviceGray.INSTANCE;
        } else if (size == 3) {
            pDColorSpace = PDDeviceRGB.INSTANCE;
        }
        return new PDColor(cOSArray, pDColorSpace);
    }

    public String getSubtype() {
        return getDictionary().getNameAsString(COSName.SUBTYPE);
    }

    public void setPage(PDPage pDPage) {
        getDictionary().setItem(COSName.P, pDPage);
    }

    public PDPage getPage() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject(COSName.P);
        if (cOSDictionary != null) {
            return new PDPage(cOSDictionary);
        }
        return null;
    }
}
