package com.github.jaiimageio.impl.plugins.tiff;

import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;

/* loaded from: classes3.dex */
public class TIFFEXIFJPEGCompressor extends TIFFBaseJPEGCompressor {
    public TIFFEXIFJPEGCompressor(ImageWriteParam imageWriteParam) {
        super("EXIF JPEG", 6, false, imageWriteParam);
    }

    @Override // com.github.jaiimageio.plugins.tiff.TIFFCompressor
    public void setMetadata(IIOMetadata iIOMetadata) {
        super.setMetadata(iIOMetadata);
        initJPEGWriter(false, true);
    }
}
