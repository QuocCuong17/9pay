package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import org.apache.pdfbox.cos.COSArray;

/* loaded from: classes5.dex */
public class PDPageFitDestination extends PDPageDestination {
    protected static final String TYPE = "Fit";
    protected static final String TYPE_BOUNDED = "FitB";

    public PDPageFitDestination() {
        this.array.growToSize(2);
        this.array.setName(1, TYPE);
    }

    public PDPageFitDestination(COSArray cOSArray) {
        super(cOSArray);
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
