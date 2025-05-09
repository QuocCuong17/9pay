package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;

/* loaded from: classes4.dex */
public class FractionResizer implements Resizer {
    private final float fraction;

    public FractionResizer(float f) {
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Fraction must be > 0 and <= 1");
        }
        this.fraction = f;
    }

    @Override // com.otaliastudios.transcoder.resize.Resizer
    public Size getOutputSize(Size size) {
        int minor = (int) (this.fraction * size.getMinor());
        int major = (int) (this.fraction * size.getMajor());
        if (minor % 2 != 0) {
            minor--;
        }
        if (major % 2 != 0) {
            major--;
        }
        return new Size(minor, major);
    }
}
