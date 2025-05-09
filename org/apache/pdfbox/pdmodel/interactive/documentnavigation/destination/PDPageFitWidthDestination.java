package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class PDPageFitWidthDestination extends PDPageDestination {
    protected static final String TYPE = "FitH";
    protected static final String TYPE_BOUNDED = "FitBH";

    public PDPageFitWidthDestination() {
        this.array.growToSize(3);
        this.array.setName(1, TYPE);
    }

    public PDPageFitWidthDestination(COSArray cOSArray) {
        super(cOSArray);
    }

    public int getTop() {
        return this.array.getInt(2);
    }

    public void setTop(int i) {
        this.array.growToSize(3);
        if (i == -1) {
            this.array.set(2, (COSBase) null);
        } else {
            this.array.setInt(2, i);
        }
    }

    public boolean fitBoundingBox() {
        return TYPE_BOUNDED.equals(this.array.getName(1));
    }

    public void setFitBoundingBox(boolean z) {
        this.array.growToSize(2);
        if (z) {
            this.array.setName(1, TYPE_BOUNDED);
        } else {
            this.array.setName(1, TYPE);
        }
    }
}
