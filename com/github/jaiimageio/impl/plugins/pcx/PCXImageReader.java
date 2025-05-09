package com.github.jaiimageio.impl.plugins.pcx;

import com.google.firebase.database.core.ValidationPath;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class PCXImageReader extends ImageReader implements PCXConstants {
    private BufferedImage bi;
    private short bytesPerLine;
    private ColorModel colorModel;
    private byte colorPlanes;
    private int[] destBands;
    private Rectangle destinationRegion;
    private byte encoding;
    private boolean gotHeader;
    private int height;
    private ImageInputStream iis;
    private byte[] largePalette;
    private byte manufacturer;
    private PCXMetadata metadata;
    private boolean noTransform;
    private ColorModel originalColorModel;
    private SampleModel originalSampleModel;
    private short paletteType;
    private SampleModel sampleModel;
    private int scaleX;
    private int scaleY;
    private boolean seleBand;
    private byte[] smallPalette;
    private int[] sourceBands;
    private Rectangle sourceRegion;
    private int width;
    private short xmax;
    private short ymax;

    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    public PCXImageReader(PCXImageReaderSpi pCXImageReaderSpi) {
        super(pCXImageReaderSpi);
        this.gotHeader = false;
        this.smallPalette = new byte[48];
        this.largePalette = new byte[ValidationPath.MAX_PATH_LENGTH_BYTES];
        this.noTransform = true;
        this.seleBand = false;
    }

    public void setInput(Object obj, boolean z, boolean z2) {
        super.setInput(obj, z, z2);
        ImageInputStream imageInputStream = (ImageInputStream) obj;
        this.iis = imageInputStream;
        if (imageInputStream != null) {
            imageInputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        }
        this.gotHeader = false;
    }

    public int getHeight(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.height;
    }

    public IIOMetadata getImageMetadata(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.metadata;
    }

    public Iterator getImageTypes(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return Collections.singletonList(new ImageTypeSpecifier(this.originalColorModel, this.originalSampleModel)).iterator();
    }

    public int getNumImages(boolean z) throws IOException {
        if (this.iis == null) {
            throw new IllegalStateException("input is null");
        }
        if (this.seekForwardOnly && z) {
            throw new IllegalStateException("cannot search with forward only input");
        }
        return 1;
    }

    public int getWidth(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.width;
    }

    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
        WritableRaster writableTile;
        checkIndex(i);
        readHeader();
        if (this.iis == null) {
            throw new IllegalStateException("input is null");
        }
        clearAbortRequest();
        processImageStarted(i);
        if (imageReadParam == null) {
            imageReadParam = getDefaultReadParam();
        }
        this.sourceRegion = new Rectangle(0, 0, 0, 0);
        this.destinationRegion = new Rectangle(0, 0, 0, 0);
        computeRegions(imageReadParam, this.width, this.height, imageReadParam.getDestination(), this.sourceRegion, this.destinationRegion);
        this.scaleX = imageReadParam.getSourceXSubsampling();
        this.scaleY = imageReadParam.getSourceYSubsampling();
        this.sourceBands = imageReadParam.getSourceBands();
        int[] destinationBands = imageReadParam.getDestinationBands();
        this.destBands = destinationBands;
        boolean z = true;
        this.seleBand = (this.sourceBands == null || destinationBands == null) ? false : true;
        if (!this.destinationRegion.equals(new Rectangle(0, 0, this.width, this.height)) && !this.seleBand) {
            z = false;
        }
        this.noTransform = z;
        if (!this.seleBand) {
            int i2 = this.colorPlanes;
            this.sourceBands = new int[i2];
            this.destBands = new int[i2];
            for (int i3 = 0; i3 < this.colorPlanes; i3++) {
                int[] iArr = this.destBands;
                this.sourceBands[i3] = i3;
                iArr[i3] = i3;
            }
        }
        BufferedImage destination = imageReadParam.getDestination();
        this.bi = destination;
        if (destination == null) {
            SampleModel sampleModel = this.sampleModel;
            if (sampleModel == null || this.colorModel == null) {
                writableTile = null;
            } else {
                SampleModel createCompatibleSampleModel = sampleModel.createCompatibleSampleModel(this.destinationRegion.width + this.destinationRegion.x, this.destinationRegion.height + this.destinationRegion.y);
                this.sampleModel = createCompatibleSampleModel;
                if (this.seleBand) {
                    this.sampleModel = createCompatibleSampleModel.createSubsetSampleModel(this.sourceBands);
                }
                writableTile = Raster.createWritableRaster(this.sampleModel, new Point(0, 0));
                this.bi = new BufferedImage(this.colorModel, writableTile, false, (Hashtable) null);
            }
        } else {
            writableTile = destination.getWritableTile(0, 0);
            this.sampleModel = this.bi.getSampleModel();
            this.colorModel = this.bi.getColorModel();
            this.noTransform &= this.destinationRegion.equals(writableTile.getBounds());
        }
        readImage(this.sampleModel.getDataType() == 0 ? writableTile.getDataBuffer().getData() : null);
        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }
        return this.bi;
    }

    private void readImage(byte[] bArr) throws IOException {
        byte[] bArr2 = new byte[this.bytesPerLine * this.colorPlanes];
        if (this.noTransform) {
            try {
                int i = (((this.width * this.metadata.bitsPerPixel) + 8) - this.metadata.bitsPerPixel) / 8;
                int i2 = 0;
                for (int i3 = 0; i3 < this.height; i3++) {
                    readScanLine(bArr2);
                    for (int i4 = 0; i4 < this.colorPlanes; i4++) {
                        System.arraycopy(bArr2, this.bytesPerLine * i4, bArr, i2, i);
                        i2 += i;
                    }
                    processImageProgress((i3 * 100.0f) / this.height);
                }
                return;
            } catch (EOFException unused) {
                return;
            }
        }
        if (this.metadata.bitsPerPixel == 1) {
            read1Bit(bArr);
        } else if (this.metadata.bitsPerPixel == 4) {
            read4Bit(bArr);
        } else {
            read8Bit(bArr);
        }
    }

    private void read1Bit(byte[] bArr) throws IOException {
        byte[] bArr2 = new byte[this.bytesPerLine];
        for (int i = 0; i < this.sourceRegion.y; i++) {
            try {
                readScanLine(bArr2);
            } catch (EOFException unused) {
                return;
            }
        }
        int scanlineStride = this.sampleModel.getScanlineStride();
        int[] iArr = new int[this.destinationRegion.width];
        int[] iArr2 = new int[this.destinationRegion.width];
        int[] iArr3 = new int[this.destinationRegion.width];
        int[] iArr4 = new int[this.destinationRegion.width];
        int i2 = this.destinationRegion.x;
        int i3 = this.sourceRegion.x;
        int i4 = 0;
        while (i2 < this.destinationRegion.x + this.destinationRegion.width) {
            iArr3[i4] = i3 >> 3;
            iArr[i4] = 7 - (i3 & 7);
            iArr4[i4] = i2 >> 3;
            iArr2[i4] = 7 - (i2 & 7);
            i2++;
            i4++;
            i3 += this.scaleX;
        }
        int i5 = this.destinationRegion.y * scanlineStride;
        for (int i6 = 0; i6 < this.sourceRegion.height; i6++) {
            readScanLine(bArr2);
            if (i6 % this.scaleY == 0) {
                for (int i7 = 0; i7 < this.destinationRegion.width; i7++) {
                    int i8 = (bArr2[iArr3[i7]] >> iArr[i7]) & 1;
                    int i9 = iArr4[i7] + i5;
                    bArr[i9] = (byte) ((i8 << iArr2[i7]) | bArr[i9]);
                }
                i5 += scanlineStride;
            }
            processImageProgress((i6 * 100.0f) / this.sourceRegion.height);
        }
    }

    private void read4Bit(byte[] bArr) throws IOException {
        byte[] bArr2 = new byte[this.bytesPerLine];
        try {
            int scanlineStride = this.sampleModel.getScanlineStride();
            int[] iArr = new int[this.destinationRegion.width];
            int[] iArr2 = new int[this.destinationRegion.width];
            int[] iArr3 = new int[this.destinationRegion.width];
            int[] iArr4 = new int[this.destinationRegion.width];
            int i = this.destinationRegion.x;
            int i2 = this.sourceRegion.x;
            int i3 = 0;
            while (i < this.destinationRegion.x + this.destinationRegion.width) {
                iArr3[i3] = i2 >> 1;
                iArr[i3] = (1 - (i2 & 1)) << 2;
                iArr4[i3] = i >> 1;
                iArr2[i3] = (1 - (i & 1)) << 2;
                i++;
                i3++;
                i2 += this.scaleX;
            }
            int i4 = this.destinationRegion.y * scanlineStride;
            for (int i5 = 0; i5 < this.sourceRegion.height; i5++) {
                readScanLine(bArr2);
                if (abortRequested()) {
                    return;
                }
                if (i5 % this.scaleY == 0) {
                    for (int i6 = 0; i6 < this.destinationRegion.width; i6++) {
                        int i7 = (bArr2[iArr3[i6]] >> iArr[i6]) & 15;
                        int i8 = iArr4[i6] + i4;
                        bArr[i8] = (byte) ((i7 << iArr2[i6]) | bArr[i8]);
                    }
                    i4 += scanlineStride;
                }
                processImageProgress((i5 * 100.0f) / this.sourceRegion.height);
            }
        } catch (EOFException unused) {
        }
    }

    private void read8Bit(byte[] bArr) throws IOException {
        byte[] bArr2 = new byte[this.colorPlanes * this.bytesPerLine];
        for (int i = 0; i < this.sourceRegion.y; i++) {
            try {
                readScanLine(bArr2);
            } catch (EOFException unused) {
                return;
            }
        }
        int i2 = this.destinationRegion.y * (this.destinationRegion.x + this.destinationRegion.width) * this.colorPlanes;
        for (int i3 = 0; i3 < this.sourceRegion.height; i3++) {
            readScanLine(bArr2);
            if (i3 % this.scaleY == 0) {
                int i4 = this.sourceRegion.x;
                for (int i5 = 0; i5 < this.colorPlanes; i5++) {
                    i2 += this.destinationRegion.x;
                    int i6 = 0;
                    while (i6 < this.destinationRegion.width) {
                        bArr[i2] = bArr2[i4 + i6];
                        i6 += this.scaleX;
                        i2++;
                    }
                    i4 += this.bytesPerLine;
                }
            }
            processImageProgress((i3 * 100.0f) / this.sourceRegion.height);
        }
    }

    private void readScanLine(byte[] bArr) throws IOException {
        int i = this.bytesPerLine * this.colorPlanes;
        int i2 = 0;
        while (i2 < i) {
            int readUnsignedByte = this.iis.readUnsignedByte();
            if ((readUnsignedByte & 192) == 192) {
                int i3 = readUnsignedByte & (-193);
                int readUnsignedByte2 = this.iis.readUnsignedByte();
                int i4 = 0;
                while (i4 < i3 && i2 < i) {
                    bArr[i2] = (byte) (readUnsignedByte2 & 255);
                    i4++;
                    i2++;
                }
            } else {
                bArr[i2] = (byte) (readUnsignedByte & 255);
                i2++;
            }
        }
    }

    private void checkIndex(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException("only one image exists in the stream");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0150  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readHeader() throws IOException {
        int read;
        if (this.gotHeader) {
            this.iis.seek(128L);
            return;
        }
        this.metadata = new PCXMetadata();
        byte readByte = this.iis.readByte();
        this.manufacturer = readByte;
        if (readByte != 10) {
            throw new IllegalStateException("image is not a PCX file");
        }
        this.metadata.version = this.iis.readByte();
        byte readByte2 = this.iis.readByte();
        this.encoding = readByte2;
        if (readByte2 != 1) {
            throw new IllegalStateException("image is not a PCX file, invalid encoding " + ((int) this.encoding));
        }
        this.metadata.bitsPerPixel = this.iis.readByte();
        this.metadata.xmin = this.iis.readShort();
        this.metadata.ymin = this.iis.readShort();
        this.xmax = this.iis.readShort();
        this.ymax = this.iis.readShort();
        this.metadata.hdpi = this.iis.readShort();
        this.metadata.vdpi = this.iis.readShort();
        this.iis.readFully(this.smallPalette);
        this.iis.readByte();
        this.colorPlanes = this.iis.readByte();
        this.bytesPerLine = this.iis.readShort();
        this.paletteType = this.iis.readShort();
        this.metadata.hsize = this.iis.readShort();
        this.metadata.vsize = this.iis.readShort();
        this.iis.skipBytes(54);
        this.width = (this.xmax - this.metadata.xmin) + 1;
        this.height = (this.ymax - this.metadata.ymin) + 1;
        if (this.colorPlanes == 1) {
            if (this.paletteType == 2) {
                this.colorModel = new ComponentColorModel(ColorSpace.getInstance(1003), new int[]{8}, false, false, 1, 0);
                int i = this.width;
                this.sampleModel = new ComponentSampleModel(0, i, this.height, 1, i, new int[]{0});
            } else if (this.metadata.bitsPerPixel == 8) {
                this.iis.mark();
                if (this.iis.length() != -1) {
                    ImageInputStream imageInputStream = this.iis;
                    imageInputStream.seek((imageInputStream.length() - 768) - 1);
                    read = this.iis.read();
                    if (read != 12) {
                        processWarningOccurred("Expected palette magic number 12; instead read " + read + " from this image.");
                    }
                    this.iis.readFully(this.largePalette);
                    this.iis.reset();
                    IndexColorModel indexColorModel = new IndexColorModel(this.metadata.bitsPerPixel, 256, this.largePalette, 0, false);
                    this.colorModel = indexColorModel;
                    this.sampleModel = indexColorModel.createCompatibleSampleModel(this.width, this.height);
                }
                do {
                } while (this.iis.read() != -1);
                ImageInputStream imageInputStream2 = this.iis;
                imageInputStream2.seek((imageInputStream2.getStreamPosition() - 768) - 1);
                read = this.iis.read();
                if (read != 12) {
                }
                this.iis.readFully(this.largePalette);
                this.iis.reset();
                IndexColorModel indexColorModel2 = new IndexColorModel(this.metadata.bitsPerPixel, 256, this.largePalette, 0, false);
                this.colorModel = indexColorModel2;
                this.sampleModel = indexColorModel2.createCompatibleSampleModel(this.width, this.height);
            } else {
                IndexColorModel indexColorModel3 = new IndexColorModel(this.metadata.bitsPerPixel, this.metadata.bitsPerPixel != 1 ? 16 : 2, this.smallPalette, 0, false);
                this.colorModel = indexColorModel3;
                this.sampleModel = indexColorModel3.createCompatibleSampleModel(this.width, this.height);
            }
        } else {
            this.colorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[]{8, 8, 8}, false, false, 1, 0);
            int i2 = this.width;
            this.sampleModel = new ComponentSampleModel(0, i2, this.height, 1, i2 * this.colorPlanes, new int[]{0, i2, i2 * 2});
        }
        this.originalSampleModel = this.sampleModel;
        this.originalColorModel = this.colorModel;
        this.gotHeader = true;
    }
}
