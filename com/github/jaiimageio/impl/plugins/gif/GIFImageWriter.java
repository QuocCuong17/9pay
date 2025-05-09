package com.github.jaiimageio.impl.plugins.gif;

import com.github.jaiimageio.impl.common.LZWCompressor;
import com.github.jaiimageio.impl.common.PaletteBuilder;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.NodeList;

/* loaded from: classes3.dex */
public class GIFImageWriter extends ImageWriter {
    private static final boolean DEBUG = false;
    static final String IMAGE_METADATA_NAME = "javax_imageio_gif_image_1.0";
    static final String STANDARD_METADATA_NAME = "javax_imageio_1.0";
    static final String STREAM_METADATA_NAME = "javax_imageio_gif_stream_1.0";
    private int imageIndex;
    private boolean isWritingSequence;
    private ImageOutputStream stream;
    private GIFWritableStreamMetadata theStreamMetadata;
    private boolean wroteSequenceHeader;

    private static int getGifPaletteSize(int i) {
        if (i <= 2) {
            return 2;
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    public boolean canWriteSequence() {
        return true;
    }

    private static int getNumBits(int i) throws IOException {
        if (i == 2) {
            return 1;
        }
        if (i == 4) {
            return 2;
        }
        if (i == 8) {
            return 3;
        }
        if (i == 16) {
            return 4;
        }
        if (i == 32) {
            return 5;
        }
        if (i == 64) {
            return 6;
        }
        if (i == 128) {
            return 7;
        }
        if (i == 256) {
            return 8;
        }
        throw new IOException("Bad palette length: " + i + "!");
    }

    private static void computeRegions(Rectangle rectangle, Dimension dimension, ImageWriteParam imageWriteParam) {
        int i;
        int i2;
        if (imageWriteParam != null) {
            int[] sourceBands = imageWriteParam.getSourceBands();
            if (sourceBands != null && (sourceBands.length != 1 || sourceBands[0] != 0)) {
                throw new IllegalArgumentException("Cannot sub-band image!");
            }
            Rectangle sourceRegion = imageWriteParam.getSourceRegion();
            if (sourceRegion != null) {
                rectangle.setBounds(sourceRegion.intersection(rectangle));
            }
            int subsamplingXOffset = imageWriteParam.getSubsamplingXOffset();
            int subsamplingYOffset = imageWriteParam.getSubsamplingYOffset();
            rectangle.x += subsamplingXOffset;
            rectangle.y += subsamplingYOffset;
            rectangle.width -= subsamplingXOffset;
            rectangle.height -= subsamplingYOffset;
            i2 = imageWriteParam.getSourceXSubsampling();
            i = imageWriteParam.getSourceYSubsampling();
        } else {
            i = 1;
            i2 = 1;
        }
        dimension.setSize(((rectangle.width + i2) - 1) / i2, ((rectangle.height + i) - 1) / i);
        if (dimension.width <= 0 || dimension.height <= 0) {
            throw new IllegalArgumentException("Empty source region!");
        }
    }

    private static byte[] createColorTable(ColorModel colorModel, SampleModel sampleModel) {
        int i = 0;
        if (colorModel instanceof IndexColorModel) {
            IndexColorModel indexColorModel = (IndexColorModel) colorModel;
            int mapSize = indexColorModel.getMapSize();
            int gifPaletteSize = getGifPaletteSize(mapSize);
            byte[] bArr = new byte[gifPaletteSize];
            byte[] bArr2 = new byte[gifPaletteSize];
            byte[] bArr3 = new byte[gifPaletteSize];
            indexColorModel.getReds(bArr);
            indexColorModel.getGreens(bArr2);
            indexColorModel.getBlues(bArr3);
            while (mapSize < gifPaletteSize) {
                bArr[mapSize] = bArr[0];
                bArr2[mapSize] = bArr2[0];
                bArr3[mapSize] = bArr3[0];
                mapSize++;
            }
            byte[] bArr4 = new byte[gifPaletteSize * 3];
            int i2 = 0;
            while (i < gifPaletteSize) {
                int i3 = i2 + 1;
                bArr4[i2] = bArr[i];
                int i4 = i3 + 1;
                bArr4[i3] = bArr2[i];
                bArr4[i4] = bArr3[i];
                i++;
                i2 = i4 + 1;
            }
            return bArr4;
        }
        if (sampleModel.getNumBands() != 1) {
            return null;
        }
        int i5 = sampleModel.getSampleSize()[0];
        if (i5 > 8) {
            i5 = 8;
        }
        int i6 = (1 << i5) * 3;
        byte[] bArr5 = new byte[i6];
        while (i < i6) {
            bArr5[i] = (byte) (i / 3);
            i++;
        }
        return bArr5;
    }

    public GIFImageWriter(GIFImageWriterSpi gIFImageWriterSpi) {
        super(gIFImageWriterSpi);
        this.stream = null;
        this.isWritingSequence = false;
        this.wroteSequenceHeader = false;
        this.theStreamMetadata = null;
        this.imageIndex = 0;
    }

    private void convertMetadata(String str, IIOMetadata iIOMetadata, IIOMetadata iIOMetadata2) {
        String nativeMetadataFormatName = iIOMetadata.getNativeMetadataFormatName();
        if (nativeMetadataFormatName == null || !nativeMetadataFormatName.equals(str)) {
            String[] extraMetadataFormatNames = iIOMetadata.getExtraMetadataFormatNames();
            if (extraMetadataFormatNames != null) {
                for (String str2 : extraMetadataFormatNames) {
                    if (str2.equals(str)) {
                        break;
                    }
                }
            }
            str = null;
        }
        if (str == null && iIOMetadata.isStandardMetadataFormatSupported()) {
            str = STANDARD_METADATA_NAME;
        }
        if (str != null) {
            try {
                iIOMetadata2.mergeTree(str, iIOMetadata.getAsTree(str));
            } catch (IIOInvalidTreeException unused) {
            }
        }
    }

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        IIOMetadata defaultStreamMetadata = getDefaultStreamMetadata(imageWriteParam);
        convertMetadata(STREAM_METADATA_NAME, iIOMetadata, defaultStreamMetadata);
        return defaultStreamMetadata;
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        if (imageTypeSpecifier == null) {
            throw new IllegalArgumentException("imageType == null!");
        }
        GIFWritableImageMetadata gIFWritableImageMetadata = (GIFWritableImageMetadata) getDefaultImageMetadata(imageTypeSpecifier, imageWriteParam);
        boolean z = gIFWritableImageMetadata.interlaceFlag;
        convertMetadata(IMAGE_METADATA_NAME, iIOMetadata, gIFWritableImageMetadata);
        if (imageWriteParam != null && imageWriteParam.canWriteProgressive() && imageWriteParam.getProgressiveMode() != 3) {
            gIFWritableImageMetadata.interlaceFlag = z;
        }
        return gIFWritableImageMetadata;
    }

    public void endWriteSequence() throws IOException {
        if (this.stream == null) {
            throw new IllegalStateException("output == null!");
        }
        if (!this.isWritingSequence) {
            throw new IllegalStateException("prepareWriteSequence() was not invoked!");
        }
        writeTrailer();
        resetLocal();
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        int transparentPixel;
        GIFWritableImageMetadata gIFWritableImageMetadata = new GIFWritableImageMetadata();
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        Rectangle rectangle = new Rectangle(sampleModel.getWidth(), sampleModel.getHeight());
        Dimension dimension = new Dimension();
        computeRegions(rectangle, dimension, imageWriteParam);
        gIFWritableImageMetadata.imageWidth = dimension.width;
        gIFWritableImageMetadata.imageHeight = dimension.height;
        if (imageWriteParam != null && imageWriteParam.canWriteProgressive() && imageWriteParam.getProgressiveMode() == 0) {
            gIFWritableImageMetadata.interlaceFlag = false;
        } else {
            gIFWritableImageMetadata.interlaceFlag = true;
        }
        IndexColorModel colorModel = imageTypeSpecifier.getColorModel();
        gIFWritableImageMetadata.localColorTable = createColorTable(colorModel, sampleModel);
        if ((colorModel instanceof IndexColorModel) && (transparentPixel = colorModel.getTransparentPixel()) != -1) {
            gIFWritableImageMetadata.transparentColorFlag = true;
            gIFWritableImageMetadata.transparentColorIndex = transparentPixel;
        }
        return gIFWritableImageMetadata;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        GIFWritableStreamMetadata gIFWritableStreamMetadata = new GIFWritableStreamMetadata();
        gIFWritableStreamMetadata.version = "89a";
        return gIFWritableStreamMetadata;
    }

    public ImageWriteParam getDefaultWriteParam() {
        return new GIFImageWriteParam(getLocale());
    }

    public void prepareWriteSequence(IIOMetadata iIOMetadata) throws IOException {
        if (this.stream == null) {
            throw new IllegalStateException("Output is not set.");
        }
        resetLocal();
        if (iIOMetadata == null) {
            this.theStreamMetadata = (GIFWritableStreamMetadata) getDefaultStreamMetadata(null);
        } else {
            GIFWritableStreamMetadata gIFWritableStreamMetadata = new GIFWritableStreamMetadata();
            this.theStreamMetadata = gIFWritableStreamMetadata;
            convertMetadata(STREAM_METADATA_NAME, iIOMetadata, gIFWritableStreamMetadata);
        }
        this.isWritingSequence = true;
    }

    public void reset() {
        super.reset();
        resetLocal();
    }

    private void resetLocal() {
        this.isWritingSequence = false;
        this.wroteSequenceHeader = false;
        this.theStreamMetadata = null;
        this.imageIndex = 0;
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException("output is not an ImageOutputStream");
            }
            ImageOutputStream imageOutputStream = (ImageOutputStream) obj;
            this.stream = imageOutputStream;
            imageOutputStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
            return;
        }
        this.stream = null;
    }

    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        GIFWritableStreamMetadata gIFWritableStreamMetadata;
        if (this.stream == null) {
            throw new IllegalStateException("output == null!");
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException("iioimage == null!");
        }
        if (iIOImage.hasRaster()) {
            throw new UnsupportedOperationException("canWriteRasters() == false!");
        }
        resetLocal();
        if (iIOMetadata == null) {
            gIFWritableStreamMetadata = (GIFWritableStreamMetadata) getDefaultStreamMetadata(imageWriteParam);
        } else {
            gIFWritableStreamMetadata = (GIFWritableStreamMetadata) convertStreamMetadata(iIOMetadata, imageWriteParam);
        }
        write(true, true, gIFWritableStreamMetadata, iIOImage, imageWriteParam);
    }

    public void writeToSequence(IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        if (this.stream == null) {
            throw new IllegalStateException("output == null!");
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException("image == null!");
        }
        if (iIOImage.hasRaster()) {
            throw new UnsupportedOperationException("canWriteRasters() == false!");
        }
        if (!this.isWritingSequence) {
            throw new IllegalStateException("prepareWriteSequence() was not invoked!");
        }
        write(!this.wroteSequenceHeader, false, this.theStreamMetadata, iIOImage, imageWriteParam);
        if (!this.wroteSequenceHeader) {
            this.wroteSequenceHeader = true;
        }
        this.imageIndex++;
    }

    private boolean needToCreateIndex(RenderedImage renderedImage) {
        SampleModel sampleModel = renderedImage.getSampleModel();
        return sampleModel.getNumBands() != 1 || sampleModel.getSampleSize()[0] > 8 || renderedImage.getColorModel().getComponentSize()[0] > 8;
    }

    private void write(boolean z, boolean z2, IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        byte[] bArr;
        int sampleSize;
        int i;
        clearAbortRequest();
        RenderedImage renderedImage = iIOImage.getRenderedImage();
        if (needToCreateIndex(renderedImage)) {
            renderedImage = PaletteBuilder.createIndexedImage(renderedImage);
            iIOImage.setRenderedImage(renderedImage);
        }
        IndexColorModel colorModel = renderedImage.getColorModel();
        SampleModel sampleModel = renderedImage.getSampleModel();
        Rectangle rectangle = new Rectangle(renderedImage.getMinX(), renderedImage.getMinY(), renderedImage.getWidth(), renderedImage.getHeight());
        Dimension dimension = new Dimension();
        computeRegions(rectangle, dimension, imageWriteParam);
        GIFWritableImageMetadata gIFWritableImageMetadata = null;
        if (iIOImage.getMetadata() != null) {
            gIFWritableImageMetadata = new GIFWritableImageMetadata();
            convertMetadata(IMAGE_METADATA_NAME, iIOImage.getMetadata(), gIFWritableImageMetadata);
            if (gIFWritableImageMetadata.localColorTable == null) {
                gIFWritableImageMetadata.localColorTable = createColorTable(colorModel, sampleModel);
                if (colorModel instanceof IndexColorModel) {
                    int transparentPixel = colorModel.getTransparentPixel();
                    gIFWritableImageMetadata.transparentColorFlag = transparentPixel != -1;
                    if (gIFWritableImageMetadata.transparentColorFlag) {
                        gIFWritableImageMetadata.transparentColorIndex = transparentPixel;
                    }
                }
            }
        }
        if (z) {
            if (iIOMetadata == null) {
                throw new IllegalArgumentException("Cannot write null header!");
            }
            GIFWritableStreamMetadata gIFWritableStreamMetadata = (GIFWritableStreamMetadata) iIOMetadata;
            if (gIFWritableStreamMetadata.version == null) {
                gIFWritableStreamMetadata.version = "89a";
            }
            if (gIFWritableStreamMetadata.logicalScreenWidth == -1) {
                gIFWritableStreamMetadata.logicalScreenWidth = dimension.width;
            }
            if (gIFWritableStreamMetadata.logicalScreenHeight == -1) {
                gIFWritableStreamMetadata.logicalScreenHeight = dimension.height;
            }
            if (gIFWritableStreamMetadata.colorResolution == -1) {
                if (colorModel != null) {
                    i = colorModel.getComponentSize()[0];
                } else {
                    i = sampleModel.getSampleSize()[0];
                }
                gIFWritableStreamMetadata.colorResolution = i;
            }
            if (gIFWritableStreamMetadata.globalColorTable == null) {
                if (this.isWritingSequence && gIFWritableImageMetadata != null && gIFWritableImageMetadata.localColorTable != null) {
                    gIFWritableStreamMetadata.globalColorTable = gIFWritableImageMetadata.localColorTable;
                } else if (gIFWritableImageMetadata == null || gIFWritableImageMetadata.localColorTable == null) {
                    gIFWritableStreamMetadata.globalColorTable = createColorTable(colorModel, sampleModel);
                }
            }
            bArr = gIFWritableStreamMetadata.globalColorTable;
            if (bArr != null) {
                sampleSize = getNumBits(bArr.length / 3);
            } else if (gIFWritableImageMetadata != null && gIFWritableImageMetadata.localColorTable != null) {
                sampleSize = getNumBits(gIFWritableImageMetadata.localColorTable.length / 3);
            } else {
                sampleSize = sampleModel.getSampleSize(0);
            }
            writeHeader(gIFWritableStreamMetadata, sampleSize);
        } else if (this.isWritingSequence) {
            bArr = this.theStreamMetadata.globalColorTable;
        } else {
            throw new IllegalArgumentException("Must write header for single image!");
        }
        writeImage(iIOImage.getRenderedImage(), gIFWritableImageMetadata, imageWriteParam, bArr, rectangle, dimension);
        if (z2) {
            writeTrailer();
        }
    }

    private void writeImage(RenderedImage renderedImage, GIFWritableImageMetadata gIFWritableImageMetadata, ImageWriteParam imageWriteParam, byte[] bArr, Rectangle rectangle, Dimension dimension) throws IOException {
        NodeList nodeList;
        boolean z;
        int length;
        int i;
        renderedImage.getColorModel();
        SampleModel sampleModel = renderedImage.getSampleModel();
        if (gIFWritableImageMetadata == null) {
            gIFWritableImageMetadata = (GIFWritableImageMetadata) getDefaultImageMetadata(new ImageTypeSpecifier(renderedImage), imageWriteParam);
            z = gIFWritableImageMetadata.transparentColorFlag;
        } else {
            try {
                nodeList = gIFWritableImageMetadata.getAsTree(IMAGE_METADATA_NAME).getElementsByTagName("GraphicControlExtension");
            } catch (IllegalArgumentException unused) {
                nodeList = null;
            }
            z = nodeList != null && nodeList.getLength() > 0;
            if (imageWriteParam != null && imageWriteParam.canWriteProgressive()) {
                if (imageWriteParam.getProgressiveMode() == 0) {
                    gIFWritableImageMetadata.interlaceFlag = false;
                } else if (imageWriteParam.getProgressiveMode() == 1) {
                    gIFWritableImageMetadata.interlaceFlag = true;
                }
            }
        }
        if (Arrays.equals(bArr, gIFWritableImageMetadata.localColorTable)) {
            gIFWritableImageMetadata.localColorTable = null;
        }
        gIFWritableImageMetadata.imageWidth = dimension.width;
        gIFWritableImageMetadata.imageHeight = dimension.height;
        if (z) {
            writeGraphicControlExtension(gIFWritableImageMetadata);
        }
        writePlainTextExtension(gIFWritableImageMetadata);
        writeApplicationExtension(gIFWritableImageMetadata);
        writeCommentExtension(gIFWritableImageMetadata);
        if (gIFWritableImageMetadata.localColorTable != null) {
            length = gIFWritableImageMetadata.localColorTable.length;
        } else {
            if (bArr == null) {
                i = sampleModel.getSampleSize(0);
                writeImageDescriptor(gIFWritableImageMetadata, getNumBits(i));
                writeRasterData(renderedImage, rectangle, dimension, imageWriteParam, gIFWritableImageMetadata.interlaceFlag);
            }
            length = bArr.length;
        }
        i = length / 3;
        writeImageDescriptor(gIFWritableImageMetadata, getNumBits(i));
        writeRasterData(renderedImage, rectangle, dimension, imageWriteParam, gIFWritableImageMetadata.interlaceFlag);
    }

    private void writeRows(RenderedImage renderedImage, LZWCompressor lZWCompressor, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) throws IOException {
        int[] iArr = new int[i5];
        byte[] bArr = new byte[i8];
        Raster tile = (renderedImage.getNumXTiles() == 1 && renderedImage.getNumYTiles() == 1) ? renderedImage.getTile(0, 0) : renderedImage.getData();
        int i12 = i3;
        int i13 = i6;
        int i14 = i10;
        while (i13 < i9) {
            if (i14 % i11 == 0) {
                if (abortRequested()) {
                    processWriteAborted();
                    return;
                }
                processImageProgress((i14 * 100.0f) / i9);
            }
            int i15 = i14;
            tile.getSamples(i, i12, i5, 1, 0, iArr);
            int i16 = 0;
            int i17 = 0;
            while (i16 < i8) {
                bArr[i16] = (byte) iArr[i17];
                i16++;
                i17 += i2;
            }
            lZWCompressor.compress(bArr, 0, i8);
            i14 = i15 + 1;
            i12 += i4;
            i13 += i7;
        }
    }

    private void writeRowsOpt(byte[] bArr, int i, int i2, LZWCompressor lZWCompressor, int i3, int i4, int i5, int i6, int i7, int i8) throws IOException {
        int i9 = i + (i3 * i2);
        int i10 = i2 * i4;
        while (i3 < i6) {
            if (i7 % i8 == 0) {
                if (abortRequested()) {
                    processWriteAborted();
                    return;
                }
                processImageProgress((i7 * 100.0f) / i6);
            }
            lZWCompressor.compress(bArr, i9, i5);
            i7++;
            i9 += i10;
            i3 += i4;
        }
    }

    private void writeRasterData(RenderedImage renderedImage, Rectangle rectangle, Dimension dimension, ImageWriteParam imageWriteParam, boolean z) throws IOException {
        int sourceXSubsampling;
        int sourceYSubsampling;
        LZWCompressor lZWCompressor;
        int i;
        int i2 = rectangle.x;
        int i3 = rectangle.y;
        int i4 = rectangle.width;
        int i5 = rectangle.height;
        int i6 = dimension.width;
        int i7 = dimension.height;
        if (imageWriteParam == null) {
            sourceYSubsampling = 1;
            sourceXSubsampling = 1;
        } else {
            sourceXSubsampling = imageWriteParam.getSourceXSubsampling();
            sourceYSubsampling = imageWriteParam.getSourceYSubsampling();
        }
        SampleModel sampleModel = renderedImage.getSampleModel();
        int i8 = sampleModel.getSampleSize()[0];
        if (i8 == 1) {
            i8++;
        }
        this.stream.write(i8);
        LZWCompressor lZWCompressor2 = new LZWCompressor(this.stream, i8, false);
        boolean z2 = sourceXSubsampling == 1 && sourceYSubsampling == 1 && (sampleModel instanceof ComponentSampleModel) && renderedImage.getNumXTiles() == 1 && renderedImage.getNumYTiles() == 1 && (renderedImage.getTile(0, 0).getDataBuffer() instanceof DataBufferByte);
        int max = Math.max(i7 / 20, 1);
        processImageStarted(this.imageIndex);
        if (!z) {
            lZWCompressor = lZWCompressor2;
            i = 0;
            int i9 = sourceYSubsampling;
            int i10 = sourceXSubsampling;
            if (z2) {
                Raster tile = renderedImage.getTile(0, 0);
                byte[] data = tile.getDataBuffer().getData();
                ComponentSampleModel sampleModel2 = tile.getSampleModel();
                writeRowsOpt(data, sampleModel2.getOffset(i2 - tile.getSampleModelTranslateX(), i3 - tile.getSampleModelTranslateY(), 0), sampleModel2.getScanlineStride(), lZWCompressor, 0, 1, i6, i7, 0, max);
            } else {
                writeRows(renderedImage, lZWCompressor, i2, i10, i3, i9, i4, 0, 1, i6, i7, 0, max);
            }
        } else if (z2) {
            Raster tile2 = renderedImage.getTile(0, 0);
            byte[] data2 = tile2.getDataBuffer().getData();
            ComponentSampleModel sampleModel3 = tile2.getSampleModel();
            int offset = sampleModel3.getOffset(i2 - tile2.getSampleModelTranslateX(), i3 - tile2.getSampleModelTranslateY(), 0);
            int scanlineStride = sampleModel3.getScanlineStride();
            lZWCompressor = lZWCompressor2;
            writeRowsOpt(data2, offset, scanlineStride, lZWCompressor2, 0, 8, i6, i7, 0, max);
            if (abortRequested()) {
                return;
            }
            int i11 = (i7 / 8) + 0;
            writeRowsOpt(data2, offset, scanlineStride, lZWCompressor, 4, 8, i6, i7, i11, max);
            if (abortRequested()) {
                return;
            }
            int i12 = i11 + ((i7 - 4) / 8);
            writeRowsOpt(data2, offset, scanlineStride, lZWCompressor, 2, 4, i6, i7, i12, max);
            if (abortRequested()) {
                return;
            }
            writeRowsOpt(data2, offset, scanlineStride, lZWCompressor, 1, 2, i6, i7, i12 + ((i7 - 2) / 4), max);
            i = 0;
        } else {
            lZWCompressor = lZWCompressor2;
            int i13 = sourceYSubsampling * 8;
            int i14 = sourceYSubsampling;
            int i15 = sourceXSubsampling;
            writeRows(renderedImage, lZWCompressor, i2, sourceXSubsampling, i3, i13, i4, 0, 8, i6, i7, 0, max);
            if (abortRequested()) {
                return;
            }
            int i16 = (i7 / 8) + 0;
            int i17 = i14 * 4;
            i = 0;
            writeRows(renderedImage, lZWCompressor, i2, i15, i3 + i17, i13, i4, 4, 8, i6, i7, i16, max);
            if (abortRequested()) {
                return;
            }
            int i18 = i16 + ((i7 - 4) / 8);
            int i19 = i14 * 2;
            writeRows(renderedImage, lZWCompressor, i2, i15, i3 + i19, i17, i4, 2, 4, i6, i7, i18, max);
            if (abortRequested()) {
                return;
            } else {
                writeRows(renderedImage, lZWCompressor, i2, i15, i3 + i14, i19, i4, 1, 2, i6, i7, i18 + ((i7 - 2) / 4), max);
            }
        }
        if (abortRequested()) {
            return;
        }
        processImageProgress(100.0f);
        lZWCompressor.flush();
        this.stream.write(i);
        processImageComplete();
    }

    private void writeHeader(String str, int i, int i2, int i3, int i4, int i5, boolean z, int i6, byte[] bArr) throws IOException {
        try {
            this.stream.writeBytes("GIF" + str);
            this.stream.writeShort((short) i);
            this.stream.writeShort((short) i2);
            int i7 = (bArr != null ? 128 : 0) | (((i3 - 1) & 7) << 4);
            if (z) {
                i7 |= 8;
            }
            this.stream.write(i7 | (i6 - 1));
            this.stream.write(i5);
            this.stream.write(i4);
            if (bArr != null) {
                this.stream.write(bArr);
            }
        } catch (IOException e) {
            throw new IIOException("I/O error writing header!", e);
        }
    }

    private void writeHeader(IIOMetadata iIOMetadata, int i) throws IOException {
        GIFWritableStreamMetadata gIFWritableStreamMetadata;
        if (iIOMetadata instanceof GIFWritableStreamMetadata) {
            gIFWritableStreamMetadata = (GIFWritableStreamMetadata) iIOMetadata;
        } else {
            GIFWritableStreamMetadata gIFWritableStreamMetadata2 = new GIFWritableStreamMetadata();
            gIFWritableStreamMetadata2.setFromTree(STREAM_METADATA_NAME, iIOMetadata.getAsTree(STREAM_METADATA_NAME));
            gIFWritableStreamMetadata = gIFWritableStreamMetadata2;
        }
        writeHeader(gIFWritableStreamMetadata.version, gIFWritableStreamMetadata.logicalScreenWidth, gIFWritableStreamMetadata.logicalScreenHeight, gIFWritableStreamMetadata.colorResolution, gIFWritableStreamMetadata.pixelAspectRatio, gIFWritableStreamMetadata.backgroundColorIndex, gIFWritableStreamMetadata.sortFlag, i, gIFWritableStreamMetadata.globalColorTable);
    }

    private void writeGraphicControlExtension(int i, boolean z, boolean z2, int i2, int i3) throws IOException {
        try {
            this.stream.write(33);
            this.stream.write(249);
            this.stream.write(4);
            int i4 = (i & 3) << 2;
            if (z) {
                i4 |= 2;
            }
            if (z2) {
                i4 |= 1;
            }
            this.stream.write(i4);
            this.stream.writeShort((short) i2);
            this.stream.write(i3);
            this.stream.write(0);
        } catch (IOException e) {
            throw new IIOException("I/O error writing Graphic Control Extension!", e);
        }
    }

    private void writeGraphicControlExtension(GIFWritableImageMetadata gIFWritableImageMetadata) throws IOException {
        writeGraphicControlExtension(gIFWritableImageMetadata.disposalMethod, gIFWritableImageMetadata.userInputFlag, gIFWritableImageMetadata.transparentColorFlag, gIFWritableImageMetadata.delayTime, gIFWritableImageMetadata.transparentColorIndex);
    }

    private void writeBlocks(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        int i = 0;
        while (i < bArr.length) {
            int min = Math.min(bArr.length - i, 255);
            this.stream.write(min);
            this.stream.write(bArr, i, min);
            i += min;
        }
    }

    private void writePlainTextExtension(GIFWritableImageMetadata gIFWritableImageMetadata) throws IOException {
        if (gIFWritableImageMetadata.hasPlainTextExtension) {
            try {
                this.stream.write(33);
                this.stream.write(1);
                this.stream.write(12);
                this.stream.writeShort(gIFWritableImageMetadata.textGridLeft);
                this.stream.writeShort(gIFWritableImageMetadata.textGridTop);
                this.stream.writeShort(gIFWritableImageMetadata.textGridWidth);
                this.stream.writeShort(gIFWritableImageMetadata.textGridHeight);
                this.stream.write(gIFWritableImageMetadata.characterCellWidth);
                this.stream.write(gIFWritableImageMetadata.characterCellHeight);
                this.stream.write(gIFWritableImageMetadata.textForegroundColor);
                this.stream.write(gIFWritableImageMetadata.textBackgroundColor);
                writeBlocks(gIFWritableImageMetadata.text);
                this.stream.write(0);
            } catch (IOException e) {
                throw new IIOException("I/O error writing Plain Text Extension!", e);
            }
        }
    }

    private void writeApplicationExtension(GIFWritableImageMetadata gIFWritableImageMetadata) throws IOException {
        if (gIFWritableImageMetadata.applicationIDs != null) {
            Iterator it = gIFWritableImageMetadata.applicationIDs.iterator();
            Iterator it2 = gIFWritableImageMetadata.authenticationCodes.iterator();
            Iterator it3 = gIFWritableImageMetadata.applicationData.iterator();
            while (it.hasNext()) {
                try {
                    this.stream.write(33);
                    this.stream.write(255);
                    this.stream.write(11);
                    this.stream.write((byte[]) it.next(), 0, 8);
                    this.stream.write((byte[]) it2.next(), 0, 3);
                    writeBlocks((byte[]) it3.next());
                    this.stream.write(0);
                } catch (IOException e) {
                    throw new IIOException("I/O error writing Application Extension!", e);
                }
            }
        }
    }

    private void writeCommentExtension(GIFWritableImageMetadata gIFWritableImageMetadata) throws IOException {
        if (gIFWritableImageMetadata.comments != null) {
            try {
                Iterator it = gIFWritableImageMetadata.comments.iterator();
                while (it.hasNext()) {
                    this.stream.write(33);
                    this.stream.write(254);
                    writeBlocks((byte[]) it.next());
                    this.stream.write(0);
                }
            } catch (IOException e) {
                throw new IIOException("I/O error writing Comment Extension!", e);
            }
        }
    }

    private void writeImageDescriptor(int i, int i2, int i3, int i4, boolean z, boolean z2, int i5, byte[] bArr) throws IOException {
        try {
            this.stream.write(44);
            this.stream.writeShort((short) i);
            this.stream.writeShort((short) i2);
            this.stream.writeShort((short) i3);
            this.stream.writeShort((short) i4);
            int i6 = bArr != null ? 128 : 0;
            if (z) {
                i6 |= 64;
            }
            if (z2) {
                i6 |= 8;
            }
            this.stream.write(i6 | (i5 - 1));
            if (bArr != null) {
                this.stream.write(bArr);
            }
        } catch (IOException e) {
            throw new IIOException("I/O error writing Image Descriptor!", e);
        }
    }

    private void writeImageDescriptor(GIFWritableImageMetadata gIFWritableImageMetadata, int i) throws IOException {
        writeImageDescriptor(gIFWritableImageMetadata.imageLeftPosition, gIFWritableImageMetadata.imageTopPosition, gIFWritableImageMetadata.imageWidth, gIFWritableImageMetadata.imageHeight, gIFWritableImageMetadata.interlaceFlag, gIFWritableImageMetadata.sortFlag, i, gIFWritableImageMetadata.localColorTable);
    }

    private void writeTrailer() throws IOException {
        this.stream.write(59);
    }
}
