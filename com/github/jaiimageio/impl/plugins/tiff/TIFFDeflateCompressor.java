package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import javax.imageio.ImageWriteParam;

/* loaded from: classes3.dex */
public class TIFFDeflateCompressor extends TIFFDeflater {
    public TIFFDeflateCompressor(ImageWriteParam imageWriteParam, int i) {
        super("Deflate", BaselineTIFFTagSet.COMPRESSION_DEFLATE, imageWriteParam, i);
    }
}
