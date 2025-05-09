package com.github.jaiimageio.impl.plugins.clib;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferUShort;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;

/* loaded from: classes3.dex */
public abstract class CLibImageWriter extends ImageWriter {
    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        return null;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        return null;
    }

    private static final Object getDataBufferData(DataBuffer dataBuffer) {
        int dataType = dataBuffer.getDataType();
        if (dataType == 0) {
            return ((DataBufferByte) dataBuffer).getData();
        }
        if (dataType == 1) {
            return ((DataBufferUShort) dataBuffer).getData();
        }
        throw new IllegalArgumentException(I18N.getString("Generic0") + " " + dataType);
    }

    private static final Raster getContiguousData(RenderedImage renderedImage, Rectangle rectangle) {
        if (renderedImage == null) {
            throw new IllegalArgumentException("im == null");
        }
        if (rectangle == null) {
            throw new IllegalArgumentException("region == null");
        }
        if (renderedImage.getNumXTiles() == 1 && renderedImage.getNumYTiles() == 1) {
            Raster tile = renderedImage.getTile(renderedImage.getMinTileX(), renderedImage.getMinTileY());
            return !tile.getBounds().equals(rectangle) ? tile.createChild(rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle.x, rectangle.y, (int[]) null) : tile;
        }
        SampleModel sampleModel = renderedImage.getSampleModel();
        return renderedImage.copyData(sampleModel.getSampleSize(0) == 8 ? Raster.createInterleavedRaster(0, renderedImage.getWidth(), renderedImage.getHeight(), sampleModel.getNumBands(), new Point(renderedImage.getMinX(), renderedImage.getMinY())) : null);
    }

    private static void reformat(Raster raster, int[] iArr, int i, int i2, WritableRaster writableRaster) {
        boolean z;
        if (raster == null) {
            throw new IllegalArgumentException("source == null!");
        }
        if (writableRaster == null) {
            throw new IllegalArgumentException("dst == null!");
        }
        Rectangle bounds = raster.getBounds();
        if (bounds.isEmpty()) {
            throw new IllegalArgumentException("source.getBounds().isEmpty()!");
        }
        int numBands = raster.getSampleModel().getNumBands();
        if (iArr == null) {
            z = false;
        } else {
            if (iArr.length > numBands) {
                throw new IllegalArgumentException("sourceBands.length > numSourceBands!");
            }
            boolean z2 = iArr.length == numBands;
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if (iArr[i3] < 0 || iArr[i3] >= numBands) {
                    throw new IllegalArgumentException("sourceBands[i] < 0 || sourceBands[i] >= numSourceBands!");
                }
                if (iArr[i3] != i3) {
                    z2 = false;
                }
            }
            z = !z2;
        }
        int i4 = bounds.width;
        int[] iArr2 = new int[i4 * numBands];
        int i5 = bounds.x;
        int i6 = bounds.y;
        int length = iArr != null ? iArr.length : numBands;
        int width = writableRaster.getWidth();
        int height = writableRaster.getHeight() - 1;
        int i7 = numBands * i;
        int i8 = i6;
        int i9 = 0;
        while (i9 <= height) {
            int i10 = i9;
            int i11 = height;
            raster.getPixels(i5, i8, i4, 1, iArr2);
            if (z) {
                int i12 = 0;
                int i13 = 0;
                for (int i14 = 0; i14 < width; i14++) {
                    int i15 = 0;
                    while (i15 < length) {
                        iArr2[i13] = iArr2[iArr[i15] + i12];
                        i15++;
                        i13++;
                    }
                    i12 += i7;
                }
            } else {
                int i16 = numBands;
                int i17 = i7;
                for (int i18 = 1; i18 < width; i18++) {
                    int i19 = i17;
                    int i20 = 0;
                    while (i20 < numBands) {
                        iArr2[i16] = iArr2[i19];
                        i20++;
                        i16++;
                        i19++;
                    }
                    i17 += i7;
                }
            }
            writableRaster.setPixels(0, i10, width, 1, iArr2);
            i8 += i2;
            i9 = i10 + 1;
            height = i11;
        }
    }

    protected CLibImageWriter(ImageWriterSpi imageWriterSpi) {
        super(imageWriterSpi);
    }

    private static final Rectangle getSourceRegion(ImageWriteParam imageWriteParam, int i, int i2, int i3, int i4) {
        Rectangle rectangle = new Rectangle(i, i2, i3, i4);
        if (imageWriteParam != null) {
            Rectangle sourceRegion = imageWriteParam.getSourceRegion();
            if (sourceRegion != null) {
                rectangle = rectangle.intersection(sourceRegion);
            }
            int subsamplingXOffset = imageWriteParam.getSubsamplingXOffset();
            int subsamplingYOffset = imageWriteParam.getSubsamplingYOffset();
            rectangle.x += subsamplingXOffset;
            rectangle.y += subsamplingYOffset;
            rectangle.width -= subsamplingXOffset;
            rectangle.height -= subsamplingYOffset;
        }
        return rectangle;
    }
}
