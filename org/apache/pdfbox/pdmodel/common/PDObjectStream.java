package org.apache.pdfbox.pdmodel.common;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;

/* loaded from: classes5.dex */
public class PDObjectStream extends PDStream {
    public PDObjectStream(COSStream cOSStream) {
        super(cOSStream);
    }

    public static PDObjectStream createStream(PDDocument pDDocument) {
        PDObjectStream pDObjectStream = new PDObjectStream(pDDocument.getDocument().createCOSStream());
        pDObjectStream.getStream().setItem(COSName.TYPE, (COSBase) COSName.OBJ_STM);
        return pDObjectStream;
    }

    public String getType() {
        return getStream().getNameAsString(COSName.TYPE);
    }

    public int getNumberOfObjects() {
        return getStream().getInt(COSName.N, 0);
    }

    public void setNumberOfObjects(int i) {
        getStream().setInt(COSName.N, i);
    }

    public int getFirstByteOffset() {
        return getStream().getInt(COSName.FIRST, 0);
    }

    public void setFirstByteOffset(int i) {
        getStream().setInt(COSName.FIRST, i);
    }

    public PDObjectStream getExtends() {
        COSStream cOSStream = (COSStream) getStream().getDictionaryObject(COSName.EXTENDS);
        if (cOSStream != null) {
            return new PDObjectStream(cOSStream);
        }
        return null;
    }

    public void setExtends(PDObjectStream pDObjectStream) {
        getStream().setItem(COSName.EXTENDS, pDObjectStream);
    }
}
