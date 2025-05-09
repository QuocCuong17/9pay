package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDPropBuild implements COSObjectable {
    private COSDictionary dictionary;

    public PDPropBuild() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public PDPropBuild(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return getDictionary();
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    public PDPropBuildDataDict getFilter() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.FILTER);
        if (cOSDictionary != null) {
            return new PDPropBuildDataDict(cOSDictionary);
        }
        return null;
    }

    public void setPDPropBuildFilter(PDPropBuildDataDict pDPropBuildDataDict) {
        this.dictionary.setItem(COSName.FILTER, pDPropBuildDataDict);
    }

    public PDPropBuildDataDict getPubSec() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.PUB_SEC);
        if (cOSDictionary != null) {
            return new PDPropBuildDataDict(cOSDictionary);
        }
        return null;
    }

    public void setPDPropBuildPubSec(PDPropBuildDataDict pDPropBuildDataDict) {
        this.dictionary.setItem(COSName.PUB_SEC, pDPropBuildDataDict);
    }

    public PDPropBuildDataDict getApp() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.APP);
        if (cOSDictionary != null) {
            return new PDPropBuildDataDict(cOSDictionary);
        }
        return null;
    }

    public void setPDPropBuildApp(PDPropBuildDataDict pDPropBuildDataDict) {
        this.dictionary.setItem(COSName.APP, pDPropBuildDataDict);
    }
}
