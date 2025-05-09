package org.apache.pdfbox.pdmodel.common;

/* loaded from: classes5.dex */
public class XrefEntry {
    private int byteOffset;
    private int generation;
    private boolean inUse;
    private int objectNumber;

    public XrefEntry() {
        this.objectNumber = 0;
        this.byteOffset = 0;
        this.generation = 0;
        this.inUse = true;
    }

    public XrefEntry(int i, int i2, int i3, String str) {
        this.objectNumber = 0;
        this.byteOffset = 0;
        this.generation = 0;
        this.inUse = true;
        this.objectNumber = i;
        this.byteOffset = i2;
        this.generation = i3;
        this.inUse = "n".equals(str);
    }

    public int getByteOffset() {
        return this.byteOffset;
    }
}
