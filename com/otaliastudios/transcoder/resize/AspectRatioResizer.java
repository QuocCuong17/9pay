package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;

/* loaded from: classes4.dex */
public class AspectRatioResizer implements Resizer {
    private final float aspectRatio;

    public AspectRatioResizer(float f) {
        this.aspectRatio = f;
    }

    @Override // com.otaliastudios.transcoder.resize.Resizer
    public Size getOutputSize(Size size) {
        float major = size.getMajor() / size.getMinor();
        float f = this.aspectRatio;
        if (f <= 1.0f) {
            f = 1.0f / f;
        }
        if (major > f) {
            return new Size(size.getMinor(), (int) (f * size.getMinor()));
        }
        return major < f ? new Size(size.getMajor(), (int) (size.getMajor() / f)) : size;
    }
}
