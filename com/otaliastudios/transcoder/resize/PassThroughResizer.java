package com.otaliastudios.transcoder.resize;

import com.otaliastudios.transcoder.common.Size;

/* loaded from: classes4.dex */
public class PassThroughResizer implements Resizer {
    @Override // com.otaliastudios.transcoder.resize.Resizer
    public Size getOutputSize(Size size) {
        return size;
    }
}
