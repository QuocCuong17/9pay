package com.github.jaiimageio.impl.plugins.clib;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;

/* loaded from: classes3.dex */
public abstract class CLibImageReader extends ImageReader {
    private int currIndex;
    private long highWaterMark;
    private ArrayList imageStartPosition;
    private int mlibImageIndex;
    private int numImages;

    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    private static boolean subBandsMatch(int[] iArr, int[] iArr2) {
        if (iArr == null && iArr2 == null) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static final void subsample(Raster raster, int i, int i2, WritableRaster writableRaster) {
        int minX = raster.getMinX();
        int minY = raster.getMinY();
        int width = raster.getWidth();
        int height = minY + raster.getHeight();
        int minX2 = writableRaster.getMinX();
        int minY2 = writableRaster.getMinY();
        int width2 = writableRaster.getWidth();
        int numBands = raster.getSampleModel().getNumBands();
        int dataType = raster.getSampleModel().getDataType();
        int i3 = ((width + i) - 1) / i;
        if (dataType == 4 || dataType == 5) {
            float[] fArr = new float[width];
            float[] fArr2 = new float[i3];
            int i4 = 0;
            while (i4 < numBands) {
                int i5 = minY;
                int i6 = minY2;
                while (i5 < height) {
                    int i7 = i5;
                    int i8 = i4;
                    float[] fArr3 = fArr2;
                    float[] fArr4 = fArr;
                    raster.getSamples(minX, i5, width, 1, i8, fArr);
                    int i9 = 0;
                    int i10 = 0;
                    while (i9 < width) {
                        fArr3[i10] = fArr4[i9];
                        i10++;
                        i9 += i;
                    }
                    writableRaster.setSamples(minX2, i6, width2, 1, i8, fArr3);
                    i5 = i7 + i2;
                    i6++;
                    fArr2 = fArr3;
                    i4 = i8;
                    fArr = fArr4;
                }
                i4++;
            }
            return;
        }
        int[] iArr = new int[width];
        int[] iArr2 = new int[i3];
        int i11 = 0;
        while (i11 < numBands) {
            int i12 = minY;
            int i13 = minY2;
            while (i12 < height) {
                int i14 = i12;
                int i15 = i11;
                int[] iArr3 = iArr2;
                int[] iArr4 = iArr;
                raster.getSamples(minX, i12, width, 1, i15, iArr);
                int i16 = 0;
                int i17 = 0;
                while (i16 < width) {
                    iArr3[i17] = iArr4[i16];
                    i17++;
                    i16 += i;
                }
                writableRaster.setSamples(minX2, i13, width2, 1, i15, iArr3);
                i12 = i14 + i2;
                i13++;
                iArr2 = iArr3;
                i11 = i15;
                iArr = iArr4;
            }
            i11++;
        }
    }

    protected CLibImageReader(ImageReaderSpi imageReaderSpi) {
        super(imageReaderSpi);
        this.currIndex = -1;
        this.highWaterMark = Long.MIN_VALUE;
        this.imageStartPosition = new ArrayList();
        this.numImages = -1;
        this.mlibImageIndex = -1;
    }

    /* loaded from: classes3.dex */
    private class SoloIterator implements Iterator {
        Object theObject;

        SoloIterator(Object obj) {
            if (obj == null) {
                new IllegalArgumentException(I18N.getString("CLibImageReader0"));
            }
            this.theObject = obj;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.theObject != null;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object obj = this.theObject;
            if (obj == null) {
                throw new NoSuchElementException();
            }
            this.theObject = null;
            return obj;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    protected int getImageIndex() {
        return this.mlibImageIndex;
    }
}
