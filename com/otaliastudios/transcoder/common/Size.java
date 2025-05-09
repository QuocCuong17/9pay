package com.otaliastudios.transcoder.common;

/* loaded from: classes4.dex */
public class Size {
    private final int mMajor;
    private final int mMinor;

    public Size(int i, int i2) {
        this.mMajor = Math.max(i, i2);
        this.mMinor = Math.min(i, i2);
    }

    public int getMinor() {
        return this.mMinor;
    }

    public int getMajor() {
        return this.mMajor;
    }
}
