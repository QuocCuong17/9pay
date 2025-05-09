package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;

/* loaded from: classes4.dex */
public class AtMostResizer implements Resizer {
    private final int atMostMajor;
    private final int atMostMinor;

    public AtMostResizer(int i) {
        this.atMostMinor = i;
        this.atMostMajor = Integer.MAX_VALUE;
    }

    public AtMostResizer(int i, int i2) {
        this.atMostMinor = i;
        this.atMostMajor = i2;
    }

    @Override // com.otaliastudios.transcoder.resize.Resizer
    public Size getOutputSize(Size size) {
        int i;
        int i2;
        if (size.getMinor() <= this.atMostMinor && size.getMajor() <= this.atMostMajor) {
            return size;
        }
        float minor = size.getMinor() / size.getMajor();
        if (size.getMajor() / this.atMostMajor >= size.getMinor() / this.atMostMinor) {
            i2 = this.atMostMajor;
            i = (int) (i2 * minor);
        } else {
            i = this.atMostMinor;
            i2 = (int) (i / minor);
        }
        if (i % 2 != 0) {
            i--;
        }
        if (i2 % 2 != 0) {
            i2--;
        }
        return new Size(i, i2);
    }
}
