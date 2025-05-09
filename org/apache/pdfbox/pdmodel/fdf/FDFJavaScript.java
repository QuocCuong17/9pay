package org.apache.pdfbox.pdmodel.fdf;

import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDNamedTextStream;
import org.apache.pdfbox.pdmodel.common.PDTextStream;

/* loaded from: classes5.dex */
public class FDFJavaScript implements COSObjectable {
    private COSDictionary js;

    public FDFJavaScript() {
        this.js = new COSDictionary();
    }

    public FDFJavaScript(COSDictionary cOSDictionary) {
        this.js = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.js;
    }

    public COSDictionary getCOSDictionary() {
        return this.js;
    }

    public PDTextStream getBefore() {
        return PDTextStream.createTextStream(this.js.getDictionaryObject(COSName.BEFORE));
    }

    public void setBefore(PDTextStream pDTextStream) {
        this.js.setItem(COSName.BEFORE, pDTextStream);
    }

    public PDTextStream getAfter() {
        return PDTextStream.createTextStream(this.js.getDictionaryObject(COSName.AFTER));
    }

    public void setAfter(PDTextStream pDTextStream) {
        this.js.setItem(COSName.AFTER, pDTextStream);
    }

    public List<PDNamedTextStream> getNamedJavaScripts() {
        COSArray cOSArray = (COSArray) this.js.getDictionaryObject(COSName.DOC);
        ArrayList arrayList = new ArrayList();
        if (cOSArray == null) {
            cOSArray = new COSArray();
            this.js.setItem(COSName.DOC, (COSBase) cOSArray);
        }
        int i = 0;
        while (i < cOSArray.size()) {
            COSName cOSName = (COSName) cOSArray.get(i);
            int i2 = i + 1;
            arrayList.add(new PDNamedTextStream(cOSName, cOSArray.get(i2)));
            i = i2 + 1;
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setNamedJavaScripts(List<PDTextStream> list) {
        this.js.setItem(COSName.DOC, (COSBase) COSArrayList.converterToCOSArray(list));
    }
}
