package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class PDPageXYZDestination extends PDPageDestination {
    protected static final String TYPE = "XYZ";

    public PDPageXYZDestination() {
        this.array.growToSize(5);
        this.array.setName(1, TYPE);
    }

    public PDPageXYZDestination(COSArray cOSArray) {
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

    public int getTop() {
        return this.array.getInt(3);
    }

    public void setTop(int i) {
        this.array.growToSize(4);
        if (i == -1) {
            this.array.set(3, (COSBase) null);
        } else {
            this.array.setInt(3, i);
        }
    }

    public int getZoom() {
        return this.array.getInt(4);
    }

    public void setZoom(int i) {
        this.array.growToSize(5);
        if (i == -1) {
            this.array.set(4, (COSBase) null);
        } else {
            this.array.setInt(4, i);
        }
    }
}
