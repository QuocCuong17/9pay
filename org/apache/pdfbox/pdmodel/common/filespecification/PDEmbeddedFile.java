package org.apache.pdfbox.pdmodel.common.filespecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;

/* loaded from: classes5.dex */
public class PDEmbeddedFile extends PDStream {
    public PDEmbeddedFile(PDDocument pDDocument) {
        super(pDDocument);
        getStream().setName(COSName.TYPE, "EmbeddedFile");
    }

    public PDEmbeddedFile(COSStream cOSStream) {
        super(cOSStream);
    }

    public PDEmbeddedFile(PDDocument pDDocument, InputStream inputStream) throws IOException {
        super(pDDocument, inputStream);
        getStream().setName(COSName.TYPE, "EmbeddedFile");
    }

    public PDEmbeddedFile(PDDocument pDDocument, InputStream inputStream, boolean z) throws IOException {
        super(pDDocument, inputStream, z);
        getStream().setName(COSName.TYPE, "EmbeddedFile");
    }

    public void setSubtype(String str) {
        getStream().setName(COSName.SUBTYPE, str);
    }

    public String getSubtype() {
        return getStream().getNameAsString(COSName.SUBTYPE);
    }

    public int getSize() {
        return getStream().getEmbeddedInt("Params", "Size");
    }

    public void setSize(int i) {
        getStream().setEmbeddedInt("Params", "Size", i);
    }

    public Calendar getCreationDate() throws IOException {
        return getStream().getEmbeddedDate("Params", "CreationDate");
    }

    public void setCreationDate(Calendar calendar) {
        getStream().setEmbeddedDate("Params", "CreationDate", calendar);
    }

    public Calendar getModDate() throws IOException {
        return getStream().getEmbeddedDate("Params", "ModDate");
    }

    public void setModDate(Calendar calendar) {
        getStream().setEmbeddedDate("Params", "ModDate", calendar);
    }

    public String getCheckSum() {
        return getStream().getEmbeddedString("Params", "CheckSum");
    }

    public void setCheckSum(String str) {
        getStream().setEmbeddedString("Params", "CheckSum", str);
    }

    public String getMacSubtype() {
        COSDictionary cOSDictionary = (COSDictionary) getStream().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary != null) {
            return cOSDictionary.getEmbeddedString("Mac", "Subtype");
        }
        return null;
    }

    public void setMacSubtype(String str) {
        COSDictionary cOSDictionary = (COSDictionary) getStream().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary == null && str != null) {
            cOSDictionary = new COSDictionary();
            getStream().setItem(COSName.PARAMS, (COSBase) cOSDictionary);
        }
        if (cOSDictionary != null) {
            cOSDictionary.setEmbeddedString("Mac", "Subtype", str);
        }
    }

    public String getMacCreator() {
        COSDictionary cOSDictionary = (COSDictionary) getStream().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary != null) {
            return cOSDictionary.getEmbeddedString("Mac", "Creator");
        }
        return null;
    }

    public void setMacCreator(String str) {
        COSDictionary cOSDictionary = (COSDictionary) getStream().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary == null && str != null) {
            cOSDictionary = new COSDictionary();
            getStream().setItem(COSName.PARAMS, (COSBase) cOSDictionary);
        }
        if (cOSDictionary != null) {
            cOSDictionary.setEmbeddedString("Mac", "Creator", str);
        }
    }

    public String getMacResFork() {
        COSDictionary cOSDictionary = (COSDictionary) getStream().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary != null) {
            return cOSDictionary.getEmbeddedString("Mac", "ResFork");
        }
        return null;
    }

    public void setMacResFork(String str) {
        COSDictionary cOSDictionary = (COSDictionary) getStream().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary == null && str != null) {
            cOSDictionary = new COSDictionary();
            getStream().setItem(COSName.PARAMS, (COSBase) cOSDictionary);
        }
        if (cOSDictionary != null) {
            cOSDictionary.setEmbeddedString("Mac", "ResFork", str);
        }
    }
}
