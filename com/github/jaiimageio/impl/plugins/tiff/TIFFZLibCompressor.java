package com.github.jaiimageio.impl.plugins.tiff;

import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class TIFFZLibCompressor extends TIFFDeflater {
    public TIFFZLibCompressor(ImageWriteParam imageWriteParam, int i) {
        super("ZLib", 8, imageWriteParam, i);
    }
}
