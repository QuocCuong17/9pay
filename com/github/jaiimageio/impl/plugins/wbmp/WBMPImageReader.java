package com.github.jaiimageio.impl.plugins.wbmp;

import com.github.jaiimageio.impl.common.ImageUtil;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.IIOException;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class WBMPImageReader extends ImageReader {
    private boolean gotHeader;
    private int height;
    private ImageInputStream iis;
    private long imageDataOffset;
    private WBMPMetadata metadata;
    private int wbmpType;
    private int width;

    public boolean canReadRaster() {
        return true;
    }

    public IIOMetadata getStreamMetadata() throws IOException {
        return null;
    }

    boolean isValidWbmpType(int i) {
        return i == 0;
    }

    public WBMPImageReader(ImageReaderSpi imageReaderSpi) {
        super(imageReaderSpi);
        this.iis = null;
        this.gotHeader = false;
    }

    public void setInput(Object obj, boolean z, boolean z2) {
        super.setInput(obj, z, z2);
        this.iis = (ImageInputStream) obj;
        this.gotHeader = false;
    }

    public int getNumImages(boolean z) throws IOException {
        if (this.iis == null) {
            throw new IllegalStateException(I18N.getString("GetNumImages0"));
        }
        if (this.seekForwardOnly && z) {
            throw new IllegalStateException(I18N.getString("GetNumImages1"));
        }
        return 1;
    }

    public int getWidth(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.width;
    }

    public int getHeight(int i) throws IOException {
        checkIndex(i);
        readHeader();
        return this.height;
    }

    public boolean isRandomAccessEasy(int i) throws IOException {
        checkIndex(i);
        return true;
    }

    private void checkIndex(int i) {
        if (i != 0) {
            throw new IndexOutOfBoundsException(I18N.getString("WBMPImageReader0"));
        }
    }

    public void readHeader() throws IOException {
        if (this.gotHeader) {
            this.iis.seek(this.imageDataOffset);
            return;
        }
        if (this.iis == null) {
            throw new IllegalStateException(I18N.getString("WBMPImageReader1"));
        }
        this.metadata = new WBMPMetadata();
        this.wbmpType = this.iis.readByte();
        if (this.iis.readByte() != 0 || !isValidWbmpType(this.wbmpType)) {
            throw new IIOException(I18N.getString("WBMPImageReader2"));
        }
        this.metadata.wbmpType = this.wbmpType;
        int readMultiByteInteger = ImageUtil.readMultiByteInteger(this.iis);
        this.width = readMultiByteInteger;
        this.metadata.width = readMultiByteInteger;
        int readMultiByteInteger2 = ImageUtil.readMultiByteInteger(this.iis);
        this.height = readMultiByteInteger2;
        this.metadata.height = readMultiByteInteger2;
        this.gotHeader = true;
        this.imageDataOffset = this.iis.getStreamPosition();
    }

    public Iterator getImageTypes(int i) throws IOException {
        checkIndex(i);
        readHeader();
        BufferedImage bufferedImage = new BufferedImage(1, 1, 12);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new ImageTypeSpecifier(bufferedImage));
        return arrayList.iterator();
    }

    public ImageReadParam getDefaultReadParam() {
        return new ImageReadParam();
    }

    public IIOMetadata getImageMetadata(int i) throws IOException {
        checkIndex(i);
        if (this.metadata == null) {
            readHeader();
        }
        return this.metadata;
    }

    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
        if (this.iis == null) {
            throw new IllegalStateException(I18N.getString("WBMPImageReader1"));
        }
        checkIndex(i);
        clearAbortRequest();
        processImageStarted(i);
        ImageReadParam defaultReadParam = imageReadParam == null ? getDefaultReadParam() : imageReadParam;
        readHeader();
        Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        Rectangle rectangle2 = new Rectangle(0, 0, 0, 0);
        computeRegions(defaultReadParam, this.width, this.height, defaultReadParam.getDestination(), rectangle, rectangle2);
        int sourceXSubsampling = defaultReadParam.getSourceXSubsampling();
        int sourceYSubsampling = defaultReadParam.getSourceYSubsampling();
        defaultReadParam.getSubsamplingXOffset();
        defaultReadParam.getSubsamplingYOffset();
        BufferedImage destination = defaultReadParam.getDestination();
        if (destination == null) {
            destination = new BufferedImage(rectangle2.x + rectangle2.width, rectangle2.y + rectangle2.height, 12);
        }
        BufferedImage bufferedImage = destination;
        boolean z = rectangle2.equals(new Rectangle(0, 0, this.width, this.height)) && rectangle2.equals(new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
        WritableRaster writableTile = bufferedImage.getWritableTile(0, 0);
        MultiPixelPackedSampleModel sampleModel = bufferedImage.getSampleModel();
        if (z) {
            if (abortRequested()) {
                processReadAborted();
                return bufferedImage;
            }
            this.iis.read(writableTile.getDataBuffer().getData(), 0, this.height * sampleModel.getScanlineStride());
            processImageUpdate(bufferedImage, 0, 0, this.width, this.height, 1, 1, new int[]{0});
            processImageProgress(100.0f);
        } else {
            int i2 = (this.width + 7) / 8;
            byte[] bArr = new byte[i2];
            byte[] data = writableTile.getDataBuffer().getData();
            int scanlineStride = sampleModel.getScanlineStride();
            this.iis.skipBytes(rectangle.y * i2);
            int i3 = i2 * (sourceYSubsampling - 1);
            int[] iArr = new int[rectangle2.width];
            int[] iArr2 = new int[rectangle2.width];
            int[] iArr3 = new int[rectangle2.width];
            int[] iArr4 = new int[rectangle2.width];
            int i4 = rectangle2.x;
            int i5 = rectangle.x;
            int i6 = 0;
            while (i4 < rectangle2.x + rectangle2.width) {
                iArr3[i6] = i5 >> 3;
                iArr[i6] = 7 - (i5 & 7);
                iArr4[i6] = i4 >> 3;
                iArr2[i6] = 7 - (i4 & 7);
                i4++;
                i6++;
                i5 += sourceXSubsampling;
            }
            int i7 = rectangle.y;
            int i8 = rectangle2.y * scanlineStride;
            int i9 = 0;
            while (i9 < rectangle2.height && !abortRequested()) {
                this.iis.read(bArr, 0, i2);
                for (int i10 = 0; i10 < rectangle2.width; i10++) {
                    int i11 = (bArr[iArr3[i10]] >> iArr[i10]) & 1;
                    int i12 = iArr4[i10] + i8;
                    data[i12] = (byte) ((i11 << iArr2[i10]) | data[i12]);
                }
                this.iis.skipBytes(i3);
                processImageUpdate(bufferedImage, 0, i9, rectangle2.width, 1, 1, 1, new int[]{0});
                processImageProgress((i9 * 100.0f) / rectangle2.height);
                i9++;
                i8 += scanlineStride;
                iArr = iArr;
                i3 = i3;
                bArr = bArr;
                iArr4 = iArr4;
                iArr3 = iArr3;
                iArr2 = iArr2;
            }
        }
        if (abortRequested()) {
            processReadAborted();
        } else {
            processImageComplete();
        }
        return bufferedImage;
    }

    public Raster readRaster(int i, ImageReadParam imageReadParam) throws IOException {
        return read(i, imageReadParam).getData();
    }

    public void reset() {
        super.reset();
        this.iis = null;
        this.gotHeader = false;
    }
}
