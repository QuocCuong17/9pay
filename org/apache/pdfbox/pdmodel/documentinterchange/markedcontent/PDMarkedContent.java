package org.apache.pdfbox.pdmodel.documentinterchange.markedcontent;

import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDArtifactMarkedContent;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.text.TextPosition;

/* loaded from: classes5.dex */
public class PDMarkedContent {
    private final List<Object> contents;
    private final COSDictionary properties;
    private final String tag;

    public static PDMarkedContent create(COSName cOSName, COSDictionary cOSDictionary) {
        if (COSName.ARTIFACT.equals(cOSName)) {
            return new PDArtifactMarkedContent(cOSDictionary);
        }
        return new PDMarkedContent(cOSName, cOSDictionary);
    }

    public PDMarkedContent(COSName cOSName, COSDictionary cOSDictionary) {
        this.tag = cOSName == null ? null : cOSName.getName();
        this.properties = cOSDictionary;
        this.contents = new ArrayList();
    }

    public String getTag() {
        return this.tag;
    }

    public COSDictionary getProperties() {
        return this.properties;
    }

    public int getMCID() {
        return (getProperties() == null ? null : Integer.valueOf(getProperties().getInt(COSName.MCID))).intValue();
    }

    public String getLanguage() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getNameAsString(COSName.LANG);
    }

    public String getActualText() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getString(COSName.ACTUAL_TEXT);
    }

    public String getAlternateDescription() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getString(COSName.ALT);
    }

    public String getExpandedForm() {
        if (getProperties() == null) {
            return null;
        }
        return getProperties().getString(COSName.E);
    }

    public List<Object> getContents() {
        return this.contents;
    }

    public void addText(TextPosition textPosition) {
        getContents().add(textPosition);
    }

    public void addMarkedContent(PDMarkedContent pDMarkedContent) {
        getContents().add(pDMarkedContent);
    }

    public void addXObject(PDXObject pDXObject) {
        getContents().add(pDXObject);
    }

    public String toString() {
        return "tag=" + this.tag + ", properties=" + this.properties + ", contents=" + this.contents;
    }
}
