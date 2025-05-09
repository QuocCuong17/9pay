package org.apache.pdfbox.pdmodel.fdf;

import co.hyperverge.hyperkyc.data.models.WorkflowModule;
import java.io.IOException;
import java.util.Calendar;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.action.PDWindowsLaunchParams;
import org.apache.pdfbox.util.DateConverter;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public abstract class FDFAnnotation implements COSObjectable {
    protected COSDictionary annot;

    public FDFAnnotation() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.annot = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public FDFAnnotation(COSDictionary cOSDictionary) {
        this.annot = cOSDictionary;
    }

    public FDFAnnotation(Element element) throws IOException {
        this();
        String attribute = element.getAttribute("page");
        if (attribute != null) {
            setPage(Integer.parseInt(attribute));
        }
        String attribute2 = element.getAttribute("color");
        if (attribute2 != null && attribute2.length() == 7 && attribute2.charAt(0) == '#') {
            Integer.parseInt(attribute2.substring(1, 7), 16);
        }
        setDate(element.getAttribute(WorkflowModule.Properties.Section.Component.Type.DATE));
        String attribute3 = element.getAttribute("flags");
        if (attribute3 != null) {
            for (String str : attribute3.split(",")) {
                if (str.equals("invisible")) {
                    setInvisible(true);
                } else if (str.equals("hidden")) {
                    setHidden(true);
                } else if (str.equals(PDWindowsLaunchParams.OPERATION_PRINT)) {
                    setPrinted(true);
                } else if (str.equals("nozoom")) {
                    setNoZoom(true);
                } else if (str.equals("norotate")) {
                    setNoRotate(true);
                } else if (str.equals("noview")) {
                    setNoView(true);
                } else if (str.equals("readonly")) {
                    setReadOnly(true);
                } else if (str.equals("locked")) {
                    setLocked(true);
                } else if (str.equals("togglenoview")) {
                    setToggleNoView(true);
                }
            }
        }
        setName(element.getAttribute("name"));
        String attribute4 = element.getAttribute("rect");
        if (attribute4 != null) {
            String[] split = attribute4.split(",");
            float[] fArr = new float[split.length];
            for (int i = 0; i < split.length; i++) {
                fArr[i] = Float.parseFloat(split[i]);
            }
            COSArray cOSArray = new COSArray();
            cOSArray.setFloatArray(fArr);
            setRectangle(new PDRectangle(cOSArray));
        }
        setName(element.getAttribute("title"));
        setCreationDate(DateConverter.toCalendar(element.getAttribute("creationdate")));
        String attribute5 = element.getAttribute("opacity");
        if (attribute5 != null) {
            setOpacity(Float.parseFloat(attribute5));
        }
        setSubject(element.getAttribute("subject"));
    }

    public static FDFAnnotation create(COSDictionary cOSDictionary) throws IOException {
        if (cOSDictionary == null) {
            return null;
        }
        if ("Text".equals(cOSDictionary.getNameAsString(COSName.SUBTYPE))) {
            return new FDFAnnotationText(cOSDictionary);
        }
        throw new IOException("Unknown annotation type '" + cOSDictionary.getNameAsString(COSName.SUBTYPE) + "'");
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.annot;
    }

    public COSDictionary getCOSDictionary() {
        return this.annot;
    }

    public Integer getPage() {
        COSNumber cOSNumber = (COSNumber) this.annot.getDictionaryObject(COSName.PAGE);
        if (cOSNumber != null) {
            return Integer.valueOf(cOSNumber.intValue());
        }
        return null;
    }

    public void setPage(int i) {
        this.annot.setInt("Page", i);
    }

    public String getDate() {
        return this.annot.getString(COSName.DATE);
    }

    public void setDate(String str) {
        this.annot.setString(COSName.DATE, str);
    }

    public boolean isInvisible() {
        return this.annot.getFlag(COSName.F, 1);
    }

    public void setInvisible(boolean z) {
        this.annot.setFlag(COSName.F, 1, z);
    }

    public boolean isHidden() {
        return this.annot.getFlag(COSName.F, 2);
    }

    public void setHidden(boolean z) {
        this.annot.setFlag(COSName.F, 2, z);
    }

    public boolean isPrinted() {
        return this.annot.getFlag(COSName.F, 4);
    }

    public void setPrinted(boolean z) {
        this.annot.setFlag(COSName.F, 4, z);
    }

    public boolean isNoZoom() {
        return this.annot.getFlag(COSName.F, 8);
    }

    public void setNoZoom(boolean z) {
        this.annot.setFlag(COSName.F, 8, z);
    }

    public boolean isNoRotate() {
        return this.annot.getFlag(COSName.F, 16);
    }

    public void setNoRotate(boolean z) {
        this.annot.setFlag(COSName.F, 16, z);
    }

    public boolean isNoView() {
        return this.annot.getFlag(COSName.F, 32);
    }

    public void setNoView(boolean z) {
        this.annot.setFlag(COSName.F, 32, z);
    }

    public boolean isReadOnly() {
        return this.annot.getFlag(COSName.F, 64);
    }

    public void setReadOnly(boolean z) {
        this.annot.setFlag(COSName.F, 64, z);
    }

    public boolean isLocked() {
        return this.annot.getFlag(COSName.F, 128);
    }

    public void setLocked(boolean z) {
        this.annot.setFlag(COSName.F, 128, z);
    }

    public boolean isToggleNoView() {
        return this.annot.getFlag(COSName.F, 256);
    }

    public void setToggleNoView(boolean z) {
        this.annot.setFlag(COSName.F, 256, z);
    }

    public void setName(String str) {
        this.annot.setString(COSName.NM, str);
    }

    public String getName() {
        return this.annot.getString(COSName.NM);
    }

    public void setRectangle(PDRectangle pDRectangle) {
        this.annot.setItem(COSName.RECT, pDRectangle);
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.annot.getDictionaryObject(COSName.RECT);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setTitle(String str) {
        this.annot.setString(COSName.T, str);
    }

    public String getTitle() {
        return this.annot.getString(COSName.T);
    }

    public Calendar getCreationDate() throws IOException {
        return this.annot.getDate(COSName.CREATION_DATE);
    }

    public void setCreationDate(Calendar calendar) {
        this.annot.setDate(COSName.CREATION_DATE, calendar);
    }

    public void setOpacity(float f) {
        this.annot.setFloat(COSName.CA, f);
    }

    public float getOpacity() {
        return this.annot.getFloat(COSName.CA, 1.0f);
    }

    public void setSubject(String str) {
        this.annot.setString(COSName.SUBJ, str);
    }

    public String getSubject() {
        return this.annot.getString(COSName.SUBJ);
    }
}
