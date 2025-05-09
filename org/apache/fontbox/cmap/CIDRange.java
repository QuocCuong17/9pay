package org.apache.fontbox.cmap;

/* loaded from: classes5.dex */
class CIDRange {
    private final int cid;
    private final char from;
    private final char to;

    public CIDRange(char c, char c2, int i) {
        this.from = c;
        this.to = c2;
        this.cid = i;
    }

    public int map(char c) {
        char c2 = this.from;
        if (c2 > c || c > this.to) {
            return -1;
        }
        return this.cid + (c - c2);
    }

    public int unmap(int i) {
        int i2 = this.cid;
        if (i2 > i) {
            return -1;
        }
        char c = this.to;
        char c2 = this.from;
        if (i <= (c - c2) + i2) {
            return c2 + (i - i2);
        }
        return -1;
    }
}
