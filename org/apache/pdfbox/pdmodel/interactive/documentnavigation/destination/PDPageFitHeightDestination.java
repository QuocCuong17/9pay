package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class PDPageFitHeightDestination extends PDPageDestination {
    protected static final String TYPE = "FitV";
    protected static final String TYPE_BOUNDED = "FitBV";

    public PDPageFitHeightDestination() {
        this.array.growToSize(3);
        this.array.setName(1, TYPE);
    }

    public PDPageFitHeightDestination(COSArray cOSArray) {
        super(cOSArray);
    }

    public int getLeft() {
        return this.array.getInt(2);
    }

    public void setLeft(int i) {
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
