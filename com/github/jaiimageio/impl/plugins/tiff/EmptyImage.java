package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.impl.common.SimpleRenderedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;

/* compiled from: TIFFImageWriter.java */
/* loaded from: classes3.dex */
class EmptyImage extends SimpleRenderedImage {
    public Raster getTile(int i, int i2) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmptyImage(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, SampleModel sampleModel, ColorModel colorModel) {
        this.minX = i;
        this.minY = i2;
        this.width = i3;
        this.height = i4;
        this.tileGridXOffset = i5;
        this.tileGridYOffset = i6;
        this.tileWidth = i7;
        this.tileHeight = i8;
        this.sampleModel = sampleModel;
        this.colorModel = colorModel;
    }
}
