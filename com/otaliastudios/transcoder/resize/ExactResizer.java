package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;

/* loaded from: classes4.dex */
public class ExactResizer implements Resizer {
    private final Size output;

    public ExactResizer(int i, int i2) {
        this.output = new Size(i, i2);
    }

    public ExactResizer(Size size) {
        this.output = size;
    }

    @Override // com.otaliastudios.transcoder.resize.Resizer
    public Size getOutputSize(Size size) {
        return this.output;
    }
}
