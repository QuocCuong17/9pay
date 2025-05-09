package org.apache.fontbox.cff;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class IndexData {
    private int count;
    private int[] data;
    private int[] offset;

    public IndexData(int i) {
        this.count = i;
        this.offset = new int[i + 1];
    }

    public byte[] getBytes(int i) {
        int[] iArr = this.offset;
        int i2 = iArr[i + 1] - iArr[i];
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) this.data[(this.offset[i] - 1) + i3];
        }
        return bArr;
    }

    public String toString() {
        return getClass().getName() + "[count=" + this.count + ", offset=" + Arrays.toString(this.offset) + ", data=" + Arrays.toString(this.data) + "]";
    }

    public int getCount() {
        return this.count;
    }

    public void setOffset(int i, int i2) {
        this.offset[i] = i2;
    }

    public int getOffset(int i) {
        return this.offset[i];
    }

    public void initData(int i) {
        this.data = new int[i];
    }

    public void setData(int i, int i2) {
        this.data[i] = i2;
    }
}
