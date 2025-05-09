package org.apache.pdfbox.pdmodel.interactive.annotation;

import java.io.IOException;
import java.util.Calendar;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.PDTextStream;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;

/* loaded from: classes5.dex */
public class PDAnnotationMarkup extends PDAnnotation {
    public static final String RT_GROUP = "Group";
    public static final String RT_REPLY = "R";
    public static final String SUB_TYPE_CARET = "Caret";
    public static final String SUB_TYPE_FREETEXT = "FreeText";
    public static final String SUB_TYPE_INK = "Ink";
    public static final String SUB_TYPE_POLYGON = "Polygon";
    public static final String SUB_TYPE_POLYLINE = "PolyLine";
    public static final String SUB_TYPE_SOUND = "Sound";

    public PDAnnotationMarkup() {
    }

    public PDAnnotationMarkup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getTitlePopup() {
        return getDictionary().getString("T");
    }

    public void setTitlePopup(String str) {
        getDictionary().setString("T", str);
    }

    public PDAnnotationPopup getPopup() {
        COSDictionary cOSDictionary = (COSDictionary) getDictionary().getDictionaryObject(PDAnnotationPopup.SUB_TYPE);
        if (cOSDictionary != null) {
            return new PDAnnotationPopup(cOSDictionary);
        }
        return null;
    }

    public void setPopup(PDAnnotationPopup pDAnnotationPopup) {
        getDictionary().setItem(PDAnnotationPopup.SUB_TYPE, pDAnnotationPopup);
    }

    public float getConstantOpacity() {
        return getDictionary().getFloat("CA", 1.0f);
    }

    public void setConstantOpacity(float f) {
        getDictionary().setFloat("CA", f);
    }

    public PDTextStream getRichContents() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject("RC");
        if (dictionaryObject != null) {
            return PDTextStream.createTextStream(dictionaryObject);
        }
        return null;
    }

    public void setRichContents(PDTextStream pDTextStream) {
        getDictionary().setItem("RC", pDTextStream);
    }

    public Calendar getCreationDate() throws IOException {
        return getDictionary().getDate("CreationDate");
    }

    public void setCreationDate(Calendar calendar) {
        getDictionary().setDate("CreationDate", calendar);
    }

    public PDAnnotation getInReplyTo() throws IOException {
        return PDAnnotation.createAnnotation(getDictionary().getDictionaryObject("IRT"));
    }

    public void setInReplyTo(PDAnnotation pDAnnotation) {
        getDictionary().setItem("IRT", pDAnnotation);
    }

    public String getSubject() {
        return getDictionary().getString("Subj");
    }

    public void setSubject(String str) {
        getDictionary().setString("Subj", str);
    }

    public String getReplyType() {
        return getDictionary().getNameAsString(StandardStructureTypes.RT, "R");
    }

    public void setReplyType(String str) {
        getDictionary().setName(StandardStructureTypes.RT, str);
    }

    public String getIntent() {
        return getDictionary().getNameAsString("IT");
    }

    public void setIntent(String str) {
        getDictionary().setName("IT", str);
    }

    public PDExternalDataDictionary getExternalData() {
        COSBase dictionaryObject = getDictionary().getDictionaryObject("ExData");
        if (dictionaryObject instanceof COSDictionary) {
            return new PDExternalDataDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setExternalData(PDExternalDataDictionary pDExternalDataDictionary) {
        getDictionary().setItem("ExData", pDExternalDataDictionary);
    }
}
