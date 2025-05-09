package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;

/* loaded from: classes5.dex */
public abstract class PDPageDestination extends PDDestination {
    protected COSArray array;

    /* JADX INFO: Access modifiers changed from: protected */
    public PDPageDestination() {
        this.array = new COSArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDPageDestination(COSArray cOSArray) {
        this.array = cOSArray;
    }

    public PDPage getPage() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSDictionary) {
                return new PDPage((COSDictionary) object);
            }
        }
        return null;
    }

    public void setPage(PDPage pDPage) {
        this.array.set(0, pDPage);
    }

    public int getPageNumber() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSNumber) {
                return ((COSNumber) object).intValue();
            }
        }
        return -1;
    }

    public int findPageNumber() {
        if (this.array.size() > 0) {
            COSBase object = this.array.getObject(0);
            if (object instanceof COSNumber) {
                return ((COSNumber) object).intValue();
            }
            if (object instanceof COSDictionary) {
                COSBase cOSBase = object;
                while (true) {
                    COSDictionary cOSDictionary = (COSDictionary) cOSBase;
                    if (cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P) != null) {
                        cOSBase = cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P);
                    } else {
                        return new PDPageTree(cOSDictionary).indexOf(new PDPage((COSDictionary) object)) + 1;
                    }
                }
            }
        }
        return -1;
    }

    public void setPageNumber(int i) {
        this.array.set(0, i);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.array;
    }

    public COSArray getCOSArray() {
        return this.array;
    }
}
