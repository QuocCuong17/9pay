package org.tensorflow.lite.support.image;

import java.util.Objects;
import org.tensorflow.lite.support.image.MlImageAdapter;

/* loaded from: classes6.dex */
final class AutoValue_MlImageAdapter_ImageFormatProxy extends MlImageAdapter.ImageFormatProxy {
    private final ColorSpaceType colorSpaceType;
    private final int imageFormat;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MlImageAdapter_ImageFormatProxy(ColorSpaceType colorSpaceType, int imageFormat) {
        Objects.requireNonNull(colorSpaceType, "Null colorSpaceType");
        this.colorSpaceType = colorSpaceType;
        this.imageFormat = imageFormat;
    }

    @Override // org.tensorflow.lite.support.image.MlImageAdapter.ImageFormatProxy
    ColorSpaceType getColorSpaceType() {
        return this.colorSpaceType;
    }

    @Override // org.tensorflow.lite.support.image.MlImageAdapter.ImageFormatProxy
    int getImageFormat() {
        return this.imageFormat;
    }

    public String toString() {
        return "ImageFormatProxy{colorSpaceType=" + this.colorSpaceType + ", imageFormat=" + this.imageFormat + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MlImageAdapter.ImageFormatProxy)) {
            return false;
        }
        MlImageAdapter.ImageFormatProxy imageFormatProxy = (MlImageAdapter.ImageFormatProxy) o;
        return this.colorSpaceType.equals(imageFormatProxy.getColorSpaceType()) && this.imageFormat == imageFormatProxy.getImageFormat();
    }

    public int hashCode() {
        return ((this.colorSpaceType.hashCode() ^ 1000003) * 1000003) ^ this.imageFormat;
    }
}
