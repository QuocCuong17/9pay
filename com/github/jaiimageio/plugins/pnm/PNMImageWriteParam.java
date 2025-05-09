package com.github.jaiimageio.plugins.pnm;

import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class PNMImageWriteParam extends ImageWriteParam {
    private boolean raw = true;

    public void setRaw(boolean z) {
        this.raw = z;
    }

    public boolean getRaw() {
        return this.raw;
    }
}
