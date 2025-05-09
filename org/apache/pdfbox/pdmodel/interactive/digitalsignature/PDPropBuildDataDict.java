package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDPropBuildDataDict implements COSObjectable {
    private COSDictionary dictionary;

    public PDPropBuildDataDict() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public PDPropBuildDataDict(COSDictionary cOSDictionary) {
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

    public String getName() {
        return this.dictionary.getString(COSName.NAME);
    }

    public void setName(String str) {
        this.dictionary.setName(COSName.NAME, str);
    }

    public String getDate() {
        return this.dictionary.getString(COSName.DATE);
    }

    public void setDate(String str) {
        this.dictionary.setString(COSName.DATE, str);
    }

    public long getRevision() {
        return this.dictionary.getLong(COSName.R);
    }

    public void setRevision(long j) {
        this.dictionary.setLong(COSName.R, j);
    }

    public long getMinimumRevision() {
        return this.dictionary.getLong(COSName.V);
    }

    public void setMinimumRevision(long j) {
        this.dictionary.setLong(COSName.V, j);
    }

    public boolean getPreRelease() {
        return this.dictionary.getBoolean(COSName.PRE_RELEASE, false);
    }

    public void setPreRelease(boolean z) {
        this.dictionary.setBoolean(COSName.PRE_RELEASE, z);
    }

    public String getOS() {
        return this.dictionary.getString(COSName.OS);
    }

    public void setOS(String str) {
        this.dictionary.setString(COSName.OS, str);
    }

    public boolean getNonEFontNoWarn() {
        return this.dictionary.getBoolean(COSName.NON_EFONT_NO_WARN, true);
    }

    public boolean getTrustedMode() {
        return this.dictionary.getBoolean(COSName.TRUSTED_MODE, false);
    }

    public void setTrustedMode(boolean z) {
        this.dictionary.setBoolean(COSName.TRUSTED_MODE, z);
    }
}
