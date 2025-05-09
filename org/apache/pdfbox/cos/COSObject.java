package org.apache.pdfbox.cos;

import java.io.IOException;

/* loaded from: classes5.dex */
public class COSObject extends COSBase implements COSUpdateInfo {
    private COSBase baseObject;
    private int generationNumber;
    private boolean needToBeUpdated;
    private long objectNumber;

    public COSObject(COSBase cOSBase) throws IOException {
        setObject(cOSBase);
    }

    public COSBase getDictionaryObject(COSName cOSName) {
        COSBase cOSBase = this.baseObject;
        if (cOSBase instanceof COSDictionary) {
            return ((COSDictionary) cOSBase).getDictionaryObject(cOSName);
        }
        return null;
    }

    public COSBase getItem(COSName cOSName) {
        COSBase cOSBase = this.baseObject;
        if (cOSBase instanceof COSDictionary) {
            return ((COSDictionary) cOSBase).getItem(cOSName);
        }
        return null;
    }

    public COSBase getObject() {
        return this.baseObject;
    }

    public final void setObject(COSBase cOSBase) throws IOException {
        this.baseObject = cOSBase;
    }

    public String toString() {
        return "COSObject{" + Long.toString(this.objectNumber) + ", " + Integer.toString(this.generationNumber) + "}";
    }

    public long getObjectNumber() {
        return this.objectNumber;
    }

    public void setObjectNumber(long j) {
        this.objectNumber = j;
    }

    public int getGenerationNumber() {
        return this.generationNumber;
    }

    public void setGenerationNumber(int i) {
        this.generationNumber = i;
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return getObject() != null ? getObject().accept(iCOSVisitor) : COSNull.NULL.accept(iCOSVisitor);
    }

    @Override // org.apache.pdfbox.cos.COSUpdateInfo
    public boolean isNeedToBeUpdated() {
        return this.needToBeUpdated;
    }

    @Override // org.apache.pdfbox.cos.COSUpdateInfo
    public void setNeedToBeUpdated(boolean z) {
        this.needToBeUpdated = z;
    }
}
