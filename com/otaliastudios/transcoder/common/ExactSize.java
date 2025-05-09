package com.otaliastudios.transcoder.common;

/* loaded from: classes4.dex */
public class ExactSize extends Size {
    private final int mHeight;
    private final int mWidth;

    public ExactSize(int i, int i2) {
        super(i, i2);
        this.mWidth = i;
        this.mHeight = i2;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
