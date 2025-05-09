package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFColorConverter;
import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.github.jaiimageio.plugins.tiff.TIFFImageReadParam;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

/* loaded from: classes3.dex */
public class TIFFImageReader extends ImageReader {
    private static final boolean DEBUG = false;
    int[] bitsPerSample;
    char[] colorMap;
    int compression;
    int currIndex;
    private TIFFDecompressor decompressor;
    private int[] destinationBands;
    int dstHeight;
    int dstMinX;
    int dstMinY;
    int dstWidth;
    int dstXOffset;
    int dstYOffset;
    int[] extraSamples;
    boolean gotHeader;
    int height;
    TIFFImageMetadata imageMetadata;
    ImageReadParam imageReadParam;
    List imageStartPosition;
    HashMap imageTypeMap;
    int numBands;
    int numImages;
    int photometricInterpretation;
    int pixelsRead;
    int pixelsToRead;
    int planarConfiguration;
    int rowsDone;
    int[] sampleFormat;
    int samplesPerPixel;
    private int[] sourceBands;
    int sourceXOffset;
    int sourceYOffset;
    int srcXSubsampling;
    int srcYSubsampling;
    ImageInputStream stream;
    TIFFStreamMetadata streamMetadata;
    BufferedImage theImage;
    int tileOrStripHeight;
    int tileOrStripWidth;
    int tilesAcross;
    int tilesDown;
    int width;

    public boolean canReadRaster() {
        return false;
    }

    public int getNumThumbnails(int i) throws IOException {
        return 0;
    }

    public boolean hasThumbnails(int i) {
        return false;
    }

    public boolean readSupportsThumbnails() {
        return false;
    }

    public TIFFImageReader(ImageReaderSpi imageReaderSpi) {
        super(imageReaderSpi);
        this.stream = null;
        this.gotHeader = false;
        this.imageReadParam = getDefaultReadParam();
        this.streamMetadata = null;
        this.currIndex = -1;
        this.imageMetadata = null;
        this.imageStartPosition = new ArrayList();
        this.numImages = -1;
        this.imageTypeMap = new HashMap();
        this.theImage = null;
        this.width = -1;
        this.height = -1;
        this.numBands = -1;
        this.tileOrStripWidth = -1;
        this.tileOrStripHeight = -1;
        this.planarConfiguration = 1;
        this.rowsDone = 0;
    }

    public void setInput(Object obj, boolean z, boolean z2) {
        super.setInput(obj, z, z2);
        resetLocal();
        if (obj != null) {
            if (!(obj instanceof ImageInputStream)) {
                throw new IllegalArgumentException("input not an ImageInputStream!");
            }
            this.stream = (ImageInputStream) obj;
            return;
        }
        this.stream = null;
    }

    private void readHeader() throws IIOException {
        if (this.gotHeader) {
            return;
        }
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        this.streamMetadata = new TIFFStreamMetadata();
        try {
            int readUnsignedShort = this.stream.readUnsignedShort();
            if (readUnsignedShort == 19789) {
                this.streamMetadata.byteOrder = ByteOrder.BIG_ENDIAN;
                this.stream.setByteOrder(ByteOrder.BIG_ENDIAN);
            } else if (readUnsignedShort == 18761) {
                this.streamMetadata.byteOrder = ByteOrder.LITTLE_ENDIAN;
                this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
            } else {
                processWarningOccurred("Bad byte order in header, assuming little-endian");
                this.streamMetadata.byteOrder = ByteOrder.LITTLE_ENDIAN;
                this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
            }
            if (this.stream.readUnsignedShort() != 42) {
                processWarningOccurred("Bad magic number in header, continuing");
            }
            long readUnsignedInt = this.stream.readUnsignedInt();
            this.imageStartPosition.add(new Long(readUnsignedInt));
            this.stream.seek(readUnsignedInt);
            this.gotHeader = true;
        } catch (IOException e) {
            throw new IIOException("I/O error reading header!", e);
        }
    }

    private int locateImage(int i) throws IIOException {
        readHeader();
        try {
            int min = Math.min(i, this.imageStartPosition.size() - 1);
            this.stream.seek(((Long) this.imageStartPosition.get(min)).longValue());
            while (min < i) {
                this.stream.skipBytes(this.stream.readUnsignedShort() * 12);
                long readUnsignedInt = this.stream.readUnsignedInt();
                if (readUnsignedInt == 0) {
                    return min;
                }
                this.imageStartPosition.add(new Long(readUnsignedInt));
                this.stream.seek(readUnsignedInt);
                min++;
            }
            if (this.currIndex != i) {
                this.imageMetadata = null;
            }
            this.currIndex = i;
            return i;
        } catch (IOException e) {
            throw new IIOException("Couldn't seek!", e);
        }
    }

    public int getNumImages(boolean z) throws IOException {
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        if (this.seekForwardOnly && z) {
            throw new IllegalStateException("seekForwardOnly and allowSearch can't both be true!");
        }
        int i = this.numImages;
        if (i > 0) {
            return i;
        }
        if (z) {
            this.numImages = locateImage(Integer.MAX_VALUE) + 1;
        }
        return this.numImages;
    }

    public IIOMetadata getStreamMetadata() throws IIOException {
        readHeader();
        return this.streamMetadata;
    }

    private void checkIndex(int i) {
        if (i < this.minIndex) {
            throw new IndexOutOfBoundsException("imageIndex < minIndex!");
        }
        if (this.seekForwardOnly) {
            this.minIndex = i;
        }
    }

    private void seekToImage(int i) throws IIOException {
        checkIndex(i);
        if (locateImage(i) != i) {
            throw new IndexOutOfBoundsException("imageIndex out of bounds!");
        }
        readMetadata();
        initializeFromMetadata();
    }

    private void readMetadata() throws IIOException {
        List arrayList;
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        if (this.imageMetadata != null) {
            return;
        }
        try {
            ImageReadParam imageReadParam = this.imageReadParam;
            if (imageReadParam instanceof TIFFImageReadParam) {
                arrayList = ((TIFFImageReadParam) imageReadParam).getAllowedTagSets();
            } else {
                arrayList = new ArrayList(1);
                arrayList.add(BaselineTIFFTagSet.getInstance());
            }
            TIFFImageMetadata tIFFImageMetadata = new TIFFImageMetadata(arrayList);
            this.imageMetadata = tIFFImageMetadata;
            tIFFImageMetadata.initializeFromStream(this.stream, this.ignoreMetadata);
        } catch (IIOException e) {
            throw e;
        } catch (IOException e2) {
            throw new IIOException("I/O error reading image metadata!", e2);
        }
    }

    private int getWidth() {
        return this.width;
    }

    private int getHeight() {
        return this.height;
    }

    private int getNumBands() {
        return this.numBands;
    }

    private int getTileOrStripWidth() {
        TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_WIDTH);
        return tIFFField == null ? getWidth() : tIFFField.getAsInt(0);
    }

    private int getTileOrStripHeight() {
        TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_LENGTH);
        if (tIFFField != null) {
            return tIFFField.getAsInt(0);
        }
        TIFFField tIFFField2 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
        int asInt = tIFFField2 == null ? -1 : tIFFField2.getAsInt(0);
        return asInt == -1 ? getHeight() : asInt;
    }

    private int getPlanarConfiguration() {
        TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION);
        if (tIFFField == null) {
            return 1;
        }
        int asInt = tIFFField.getAsInt(0);
        if (asInt == 2) {
            if (getCompression() == 6 && this.imageMetadata.getTIFFField(513) != null) {
                processWarningOccurred("PlanarConfiguration \"Planar\" value inconsistent with JPEGInterchangeFormat; resetting to \"Chunky\".");
                return 1;
            }
            TIFFField tIFFField2 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_OFFSETS);
            if (tIFFField2 == null) {
                TIFFField tIFFField3 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_STRIP_OFFSETS);
                int tileOrStripWidth = getTileOrStripWidth();
                int tileOrStripHeight = getTileOrStripHeight();
                int width = (((getWidth() + tileOrStripWidth) - 1) / tileOrStripWidth) * (((getHeight() + tileOrStripHeight) - 1) / tileOrStripHeight);
                long[] asLongs = tIFFField3.getAsLongs();
                if (asLongs != null && asLongs.length == width) {
                    processWarningOccurred("PlanarConfiguration \"Planar\" value inconsistent with TileOffsets field value count; resetting to \"Chunky\".");
                    return 1;
                }
            } else {
                int tileOrStripHeight2 = getTileOrStripHeight();
                int height = ((getHeight() + tileOrStripHeight2) - 1) / tileOrStripHeight2;
                long[] asLongs2 = tIFFField2.getAsLongs();
                if (asLongs2 != null && asLongs2.length == height) {
                    processWarningOccurred("PlanarConfiguration \"Planar\" value inconsistent with StripOffsets field value count; resetting to \"Chunky\".");
                    return 1;
                }
            }
        }
        return asInt;
    }

    private long getTileOrStripOffset(int i) throws IIOException {
        TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_OFFSETS);
        if (tIFFField == null) {
            tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_STRIP_OFFSETS);
        }
        if (tIFFField == null) {
            tIFFField = this.imageMetadata.getTIFFField(513);
        }
        if (tIFFField == null) {
            throw new IIOException("Missing required strip or tile offsets field.");
        }
        return tIFFField.getAsLong(i);
    }

    private long getTileOrStripByteCount(int i) throws IOException {
        TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_BYTE_COUNTS);
        if (tIFFField == null) {
            tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS);
        }
        if (tIFFField == null) {
            tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        }
        if (tIFFField != null) {
            return tIFFField.getAsLong(i);
        }
        processWarningOccurred("TIFF directory contains neither StripByteCounts nor TileByteCounts field: attempting to calculate from strip or tile width and height.");
        int i2 = this.bitsPerSample[0];
        for (int i3 = 1; i3 < this.samplesPerPixel; i3++) {
            i2 += this.bitsPerSample[i3];
        }
        long tileOrStripWidth = (((getTileOrStripWidth() * i2) + 7) / 8) * getTileOrStripHeight();
        long length = this.stream.length();
        if (length != -1) {
            return Math.min(tileOrStripWidth, length - getTileOrStripOffset(i));
        }
        processWarningOccurred("Stream length is unknown: cannot clamp estimated strip or tile byte count to EOF.");
        return tileOrStripWidth;
    }

    private int getCompression() {
        TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION);
        if (tIFFField == null) {
            return 1;
        }
        return tIFFField.getAsInt(0);
    }

    public int getWidth(int i) throws IOException {
        seekToImage(i);
        return getWidth();
    }

    public int getHeight(int i) throws IOException {
        seekToImage(i);
        return getHeight();
    }

    private void initializeFromMetadata() {
        boolean z;
        int i;
        boolean z2;
        TIFFField tIFFField;
        Iterator imageReadersByFormatName;
        TIFFField tIFFField2 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION);
        boolean z3 = true;
        if (tIFFField2 == null) {
            processWarningOccurred("Compression field is missing; assuming no compression");
            this.compression = 1;
        } else {
            this.compression = tIFFField2.getAsInt(0);
        }
        TIFFField tIFFField3 = this.imageMetadata.getTIFFField(256);
        if (tIFFField3 != null) {
            this.width = tIFFField3.getAsInt(0);
            z = false;
        } else {
            processWarningOccurred("ImageWidth field is missing.");
            z = true;
        }
        TIFFField tIFFField4 = this.imageMetadata.getTIFFField(257);
        if (tIFFField4 != null) {
            this.height = tIFFField4.getAsInt(0);
        } else {
            processWarningOccurred("ImageLength field is missing.");
            z = true;
        }
        TIFFField tIFFField5 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL);
        if (tIFFField5 != null) {
            this.samplesPerPixel = tIFFField5.getAsInt(0);
        } else {
            this.samplesPerPixel = 1;
            z = true;
        }
        if (!z || (tIFFField = this.imageMetadata.getTIFFField(513)) == null || (imageReadersByFormatName = ImageIO.getImageReadersByFormatName("JPEG")) == null || !imageReadersByFormatName.hasNext()) {
            i = 1;
        } else {
            ImageReader imageReader = (ImageReader) imageReadersByFormatName.next();
            try {
                this.stream.mark();
                this.stream.seek(tIFFField.getAsLong(0));
                imageReader.setInput(this.stream);
                if (this.imageMetadata.getTIFFField(256) == null) {
                    this.width = imageReader.getWidth(0);
                }
                if (this.imageMetadata.getTIFFField(257) == null) {
                    this.height = imageReader.getHeight(0);
                }
                ImageTypeSpecifier rawImageType = imageReader.getRawImageType(0);
                if (this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL) == null) {
                    this.samplesPerPixel = rawImageType.getSampleModel().getNumBands();
                }
                this.stream.reset();
                i = rawImageType.getColorModel().getComponentSize(0);
            } catch (IOException unused) {
                i = 1;
            }
            imageReader.dispose();
        }
        if (this.samplesPerPixel < 1) {
            processWarningOccurred("Samples per pixel < 1!");
        }
        this.numBands = this.samplesPerPixel;
        this.colorMap = null;
        TIFFField tIFFField6 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
        if (tIFFField6 != null) {
            this.colorMap = tIFFField6.getAsChars();
        }
        TIFFField tIFFField7 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
        if (tIFFField7 == null) {
            int i2 = this.compression;
            if (i2 == 2 || i2 == 3 || i2 == 4) {
                processWarningOccurred("PhotometricInterpretation field is missing; assuming WhiteIsZero");
                this.photometricInterpretation = 0;
            } else if (this.colorMap != null) {
                this.photometricInterpretation = 3;
            } else {
                int i3 = this.samplesPerPixel;
                if (i3 == 3 || i3 == 4) {
                    this.photometricInterpretation = 2;
                } else {
                    processWarningOccurred("PhotometricInterpretation field is missing; assuming BlackIsZero");
                    this.photometricInterpretation = 1;
                }
            }
        } else {
            this.photometricInterpretation = tIFFField7.getAsInt(0);
        }
        int i4 = -1;
        TIFFField tIFFField8 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
        this.sampleFormat = new int[this.samplesPerPixel];
        if (tIFFField8 == null) {
            z2 = true;
            i4 = 4;
        } else if (tIFFField8.getCount() != this.samplesPerPixel) {
            i4 = tIFFField8.getAsInt(0);
            z2 = true;
        } else {
            z2 = false;
        }
        for (int i5 = 0; i5 < this.samplesPerPixel; i5++) {
            this.sampleFormat[i5] = z2 ? i4 : tIFFField8.getAsInt(i5);
            int[] iArr = this.sampleFormat;
            if (iArr[i5] != 1 && iArr[i5] != 2 && iArr[i5] != 3 && iArr[i5] != 4) {
                processWarningOccurred("Illegal value for SAMPLE_FORMAT, assuming SAMPLE_FORMAT_UNDEFINED");
                this.sampleFormat[i5] = 4;
            }
        }
        TIFFField tIFFField9 = this.imageMetadata.getTIFFField(258);
        this.bitsPerSample = new int[this.samplesPerPixel];
        if (tIFFField9 != null) {
            if (tIFFField9.getCount() != this.samplesPerPixel) {
                i = tIFFField9.getAsInt(0);
            } else {
                z3 = false;
                i = i4;
            }
        }
        for (int i6 = 0; i6 < this.samplesPerPixel; i6++) {
            this.bitsPerSample[i6] = z3 ? i : tIFFField9.getAsInt(i6);
        }
        this.extraSamples = null;
        TIFFField tIFFField10 = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES);
        if (tIFFField10 != null) {
            this.extraSamples = tIFFField10.getAsInts();
        }
    }

    public Iterator getImageTypes(int i) throws IIOException {
        List list;
        Integer num = new Integer(i);
        if (this.imageTypeMap.containsKey(num)) {
            list = (List) this.imageTypeMap.get(num);
        } else {
            ArrayList arrayList = new ArrayList(1);
            seekToImage(i);
            ImageTypeSpecifier rawImageTypeSpecifier = TIFFDecompressor.getRawImageTypeSpecifier(this.photometricInterpretation, this.compression, this.samplesPerPixel, this.bitsPerSample, this.sampleFormat, this.extraSamples, this.colorMap);
            TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_ICC_PROFILE);
            if (tIFFField != null && (rawImageTypeSpecifier.getColorModel() instanceof ComponentColorModel)) {
                ICC_ColorSpace iCC_ColorSpace = new ICC_ColorSpace(ICC_Profile.getInstance(tIFFField.getAsBytes()));
                ColorModel colorModel = rawImageTypeSpecifier.getColorModel();
                ColorSpace colorSpace = colorModel.getColorSpace();
                SampleModel sampleModel = rawImageTypeSpecifier.getSampleModel();
                int numBands = sampleModel.getNumBands();
                int numComponents = iCC_ColorSpace.getNumComponents();
                if (numBands == numComponents || numBands == numComponents + 1) {
                    boolean z = numComponents != numBands;
                    arrayList.add(new ImageTypeSpecifier(new ComponentColorModel(iCC_ColorSpace, colorModel.getComponentSize(), z, z && colorModel.isAlphaPremultiplied(), colorModel.getTransparency(), colorModel.getTransferType()), sampleModel));
                    if (colorSpace.getType() == iCC_ColorSpace.getType() && colorSpace.getNumComponents() == iCC_ColorSpace.getNumComponents()) {
                        arrayList.add(rawImageTypeSpecifier);
                    }
                } else {
                    arrayList.add(rawImageTypeSpecifier);
                }
            } else {
                arrayList.add(rawImageTypeSpecifier);
            }
            this.imageTypeMap.put(num, arrayList);
            list = arrayList;
        }
        return list.iterator();
    }

    public IIOMetadata getImageMetadata(int i) throws IIOException {
        seekToImage(i);
        TIFFImageMetadata tIFFImageMetadata = new TIFFImageMetadata(this.imageMetadata.getRootIFD().getTagSetList());
        tIFFImageMetadata.setFromTree(TIFFImageMetadata.nativeMetadataFormatName, this.imageMetadata.getAsTree(TIFFImageMetadata.nativeMetadataFormatName));
        return tIFFImageMetadata;
    }

    public IIOMetadata getStreamMetadata(int i) throws IIOException {
        readHeader();
        TIFFStreamMetadata tIFFStreamMetadata = new TIFFStreamMetadata();
        tIFFStreamMetadata.setFromTree("com_sun_media_imageio_plugins_tiff_stream_1.0", tIFFStreamMetadata.getAsTree("com_sun_media_imageio_plugins_tiff_stream_1.0"));
        return tIFFStreamMetadata;
    }

    public boolean isRandomAccessEasy(int i) throws IOException {
        int i2 = this.currIndex;
        if (i2 == -1) {
            return false;
        }
        seekToImage(i2);
        return getCompression() == 1;
    }

    public ImageReadParam getDefaultReadParam() {
        return new TIFFImageReadParam();
    }

    public boolean isImageTiled(int i) throws IOException {
        seekToImage(i);
        return this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_TILE_WIDTH) != null;
    }

    public int getTileWidth(int i) throws IOException {
        seekToImage(i);
        return getTileOrStripWidth();
    }

    public int getTileHeight(int i) throws IOException {
        seekToImage(i);
        return getTileOrStripHeight();
    }

    public BufferedImage readTile(int i, int i2, int i3) throws IOException {
        int width = getWidth(i);
        int height = getHeight(i);
        int tileWidth = getTileWidth(i);
        int tileHeight = getTileHeight(i);
        int i4 = tileWidth * i2;
        int i5 = tileHeight * i3;
        if (i2 < 0 || i3 < 0 || i4 >= width || i5 >= height) {
            throw new IllegalArgumentException("Tile indices are out of bounds!");
        }
        if (i4 + tileWidth > width) {
            tileWidth = width - i4;
        }
        if (i5 + tileHeight > height) {
            tileHeight = height - i5;
        }
        ImageReadParam defaultReadParam = getDefaultReadParam();
        defaultReadParam.setSourceRegion(new Rectangle(i4, i5, tileWidth, tileHeight));
        return read(i, defaultReadParam);
    }

    public Raster readRaster(int i, ImageReadParam imageReadParam) throws IOException {
        throw new UnsupportedOperationException();
    }

    private static int ifloor(int i, int i2) {
        if (i < 0) {
            i -= i2 - 1;
        }
        return i / i2;
    }

    private static int iceil(int i, int i2) {
        if (i > 0) {
            i += i2 - 1;
        }
        return i / i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0089, code lost:
    
        throw new java.lang.IllegalArgumentException("Destination band out of range!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
    
        throw new java.lang.IllegalArgumentException("Source band out of range!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void prepareRead(int i, ImageReadParam imageReadParam) throws IOException {
        if (this.stream == null) {
            throw new IllegalStateException("Input not set!");
        }
        if (imageReadParam == null) {
            imageReadParam = getDefaultReadParam();
        }
        this.imageReadParam = imageReadParam;
        seekToImage(i);
        this.tileOrStripWidth = getTileOrStripWidth();
        this.tileOrStripHeight = getTileOrStripHeight();
        this.planarConfiguration = getPlanarConfiguration();
        int[] sourceBands = imageReadParam.getSourceBands();
        this.sourceBands = sourceBands;
        int i2 = 0;
        if (sourceBands == null) {
            this.sourceBands = new int[this.numBands];
            for (int i3 = 0; i3 < this.numBands; i3++) {
                this.sourceBands[i3] = i3;
            }
        }
        int numBands = ImageUtil.getDestinationType(imageReadParam, getImageTypes(i)).getSampleModel().getNumBands();
        int[] destinationBands = imageReadParam.getDestinationBands();
        this.destinationBands = destinationBands;
        if (destinationBands == null) {
            this.destinationBands = new int[numBands];
            for (int i4 = 0; i4 < numBands; i4++) {
                this.destinationBands[i4] = i4;
            }
        }
        if (this.sourceBands.length != this.destinationBands.length) {
            throw new IllegalArgumentException("sourceBands.length != destinationBands.length");
        }
        while (true) {
            int[] iArr = this.sourceBands;
            if (i2 >= iArr.length) {
                return;
            }
            int i5 = iArr[i2];
            if (i5 < 0 || i5 >= this.numBands) {
                break;
            }
            int i6 = this.destinationBands[i2];
            if (i6 < 0 || i6 >= numBands) {
                break;
            } else {
                i2++;
            }
        }
    }

    public RenderedImage readAsRenderedImage(int i, ImageReadParam imageReadParam) throws IOException {
        prepareRead(i, imageReadParam);
        return new TIFFRenderedImage(this, i, this.imageReadParam, this.width, this.height);
    }

    private void decodeTile(int i, int i2, int i3) throws IOException {
        int i4 = this.tileOrStripWidth;
        int i5 = this.tileOrStripHeight;
        Rectangle rectangle = new Rectangle(i * i4, i2 * i5, i4, i5);
        if (!isImageTiled(this.currIndex)) {
            rectangle = rectangle.intersection(new Rectangle(0, 0, this.width, this.height));
        }
        if (rectangle.width <= 0 || rectangle.height <= 0) {
            return;
        }
        int i6 = rectangle.x;
        int i7 = rectangle.y;
        int i8 = rectangle.width;
        int i9 = rectangle.height;
        this.dstMinX = iceil(i6 - this.sourceXOffset, this.srcXSubsampling);
        int ifloor = ifloor(((i6 + i8) - 1) - this.sourceXOffset, this.srcXSubsampling);
        this.dstMinY = iceil(i7 - this.sourceYOffset, this.srcYSubsampling);
        int ifloor2 = ifloor(((i7 + i9) - 1) - this.sourceYOffset, this.srcYSubsampling);
        int i10 = this.dstMinX;
        this.dstWidth = (ifloor - i10) + 1;
        int i11 = this.dstMinY;
        this.dstHeight = (ifloor2 - i11) + 1;
        this.dstMinX = i10 + this.dstXOffset;
        this.dstMinY = i11 + this.dstYOffset;
        Rectangle intersection = new Rectangle(this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight).intersection(this.theImage.getRaster().getBounds());
        this.dstMinX = intersection.x;
        this.dstMinY = intersection.y;
        this.dstWidth = intersection.width;
        int i12 = intersection.height;
        this.dstHeight = i12;
        if (this.dstWidth <= 0 || i12 <= 0) {
            return;
        }
        int i13 = this.dstMinX;
        int i14 = this.dstXOffset;
        int i15 = this.srcXSubsampling;
        int i16 = this.sourceXOffset;
        int i17 = ((i13 - i14) * i15) + i16;
        int i18 = ((((((i13 + r5) - 1) - i14) * i15) + i16) - i17) + 1;
        int i19 = this.dstMinY;
        int i20 = this.dstYOffset;
        int i21 = this.srcYSubsampling;
        int i22 = this.sourceYOffset;
        int i23 = ((i19 - i20) * i21) + i22;
        this.decompressor.setSrcMinX(i6);
        this.decompressor.setSrcMinY(i7);
        this.decompressor.setSrcWidth(i8);
        this.decompressor.setSrcHeight(i9);
        this.decompressor.setDstMinX(this.dstMinX);
        this.decompressor.setDstMinY(this.dstMinY);
        this.decompressor.setDstWidth(this.dstWidth);
        this.decompressor.setDstHeight(this.dstHeight);
        this.decompressor.setActiveSrcMinX(i17);
        this.decompressor.setActiveSrcMinY(i23);
        this.decompressor.setActiveSrcWidth(i18);
        this.decompressor.setActiveSrcHeight(((((((i19 + i12) - 1) - i20) * i21) + i22) - i23) + 1);
        int i24 = this.tilesAcross;
        int i25 = (i2 * i24) + i;
        if (this.planarConfiguration == 2) {
            i25 += i3 * i24 * this.tilesDown;
        }
        long tileOrStripOffset = getTileOrStripOffset(i25);
        long tileOrStripByteCount = getTileOrStripByteCount(i25);
        long length = this.stream.length();
        if (length > 0 && tileOrStripOffset + tileOrStripByteCount > length) {
            processWarningOccurred("Attempting to process truncated stream.");
            tileOrStripByteCount = length - tileOrStripOffset;
            if (Math.max(tileOrStripByteCount, 0L) == 0) {
                processWarningOccurred("No bytes in strip/tile: skipping.");
                return;
            }
        }
        this.decompressor.setStream(this.stream);
        this.decompressor.setOffset(tileOrStripOffset);
        this.decompressor.setByteCount((int) tileOrStripByteCount);
        this.decompressor.beginDecoding();
        this.stream.mark();
        this.decompressor.decode();
        this.stream.reset();
    }

    private void reportProgress() {
        int i = this.pixelsRead + (this.dstWidth * this.dstHeight);
        this.pixelsRead = i;
        processImageProgress((i * 100.0f) / this.pixelsToRead);
        processImageUpdate(this.theImage, this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight, 1, 1, this.destinationBands);
    }

    public BufferedImage read(int i, ImageReadParam imageReadParam) throws IOException {
        TIFFColorConverter tIFFColorConverter;
        boolean z;
        prepareRead(i, imageReadParam);
        this.theImage = ImageReader.getDestination(imageReadParam, getImageTypes(i), this.width, this.height);
        this.srcXSubsampling = this.imageReadParam.getSourceXSubsampling();
        this.srcYSubsampling = this.imageReadParam.getSourceYSubsampling();
        Point destinationOffset = this.imageReadParam.getDestinationOffset();
        this.dstXOffset = destinationOffset.x;
        this.dstYOffset = destinationOffset.y;
        boolean z2 = false;
        Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        Rectangle rectangle2 = new Rectangle(0, 0, 0, 0);
        computeRegions(this.imageReadParam, this.width, this.height, this.theImage, rectangle, rectangle2);
        this.sourceXOffset = rectangle.x;
        this.sourceYOffset = rectangle.y;
        this.pixelsToRead = rectangle2.width * rectangle2.height;
        this.pixelsRead = 0;
        processImageStarted(i);
        processImageProgress(0.0f);
        int i2 = this.width;
        int i3 = this.tileOrStripWidth;
        this.tilesAcross = ((i2 + i3) - 1) / i3;
        int i4 = this.height;
        int i5 = this.tileOrStripHeight;
        this.tilesDown = ((i4 + i5) - 1) / i5;
        int compression = getCompression();
        ImageReadParam imageReadParam2 = this.imageReadParam;
        if (imageReadParam2 instanceof TIFFImageReadParam) {
            TIFFImageReadParam tIFFImageReadParam = (TIFFImageReadParam) imageReadParam2;
            this.decompressor = tIFFImageReadParam.getTIFFDecompressor();
            tIFFColorConverter = tIFFImageReadParam.getColorConverter();
        } else {
            tIFFColorConverter = null;
        }
        TIFFDecompressor tIFFDecompressor = this.decompressor;
        if (tIFFDecompressor == null) {
            if (compression == 1) {
                TIFFField tIFFField = this.imageMetadata.getTIFFField(BaselineTIFFTagSet.TAG_FILL_ORDER);
                if (tIFFField != null && tIFFField.getAsInt(0) == 2) {
                    this.decompressor = new TIFFLSBDecompressor();
                } else {
                    this.decompressor = new TIFFNullDecompressor();
                }
            } else if (compression == 4) {
                if (tIFFDecompressor == null) {
                    this.decompressor = new TIFFFaxDecompressor();
                }
            } else if (compression == 3) {
                if (tIFFDecompressor == null) {
                    this.decompressor = new TIFFFaxDecompressor();
                }
            } else if (compression == 2) {
                this.decompressor = new TIFFFaxDecompressor();
            } else if (compression == 32773) {
                this.decompressor = new TIFFPackBitsDecompressor();
            } else if (compression == 5) {
                TIFFField tIFFField2 = this.imageMetadata.getTIFFField(317);
                this.decompressor = new TIFFLZWDecompressor(tIFFField2 == null ? 1 : tIFFField2.getAsInt(0));
            } else if (compression == 7) {
                this.decompressor = new TIFFJPEGDecompressor();
            } else if (compression == 8 || compression == 32946) {
                TIFFField tIFFField3 = this.imageMetadata.getTIFFField(317);
                this.decompressor = new TIFFDeflateDecompressor(tIFFField3 == null ? 1 : tIFFField3.getAsInt(0));
            } else if (compression == 6) {
                TIFFField tIFFField4 = this.imageMetadata.getTIFFField(512);
                if (tIFFField4 == null) {
                    processWarningOccurred("JPEGProc field missing; assuming baseline sequential JPEG process.");
                } else if (tIFFField4.getAsInt(0) != 1) {
                    throw new IIOException("Old-style JPEG supported for baseline sequential JPEG process only!");
                }
                this.decompressor = new TIFFOldJPEGDecompressor();
            } else {
                throw new IIOException("Unsupported compression type (tag number = " + compression + ")!");
            }
            if (this.photometricInterpretation == 6 && compression != 7 && compression != 6) {
                boolean z3 = this.theImage.getColorModel().getColorSpace().getType() == 5;
                TIFFDecompressor tIFFDecompressor2 = this.decompressor;
                this.decompressor = new TIFFYCbCrDecompressor(tIFFDecompressor2 instanceof TIFFNullDecompressor ? null : tIFFDecompressor2, z3);
            }
        }
        if (tIFFColorConverter == null) {
            if (this.photometricInterpretation == 8 && this.theImage.getColorModel().getColorSpace().getType() == 5) {
                tIFFColorConverter = new TIFFCIELabColorConverter();
            } else if (this.photometricInterpretation == 6 && !(this.decompressor instanceof TIFFYCbCrDecompressor) && compression != 7 && compression != 6) {
                tIFFColorConverter = new TIFFYCbCrColorConverter(this.imageMetadata);
            }
        }
        this.decompressor.setReader(this);
        this.decompressor.setMetadata(this.imageMetadata);
        this.decompressor.setImage(this.theImage);
        this.decompressor.setPhotometricInterpretation(this.photometricInterpretation);
        this.decompressor.setCompression(compression);
        this.decompressor.setSamplesPerPixel(this.samplesPerPixel);
        this.decompressor.setBitsPerSample(this.bitsPerSample);
        this.decompressor.setSampleFormat(this.sampleFormat);
        this.decompressor.setExtraSamples(this.extraSamples);
        this.decompressor.setColorMap(this.colorMap);
        this.decompressor.setColorConverter(tIFFColorConverter);
        this.decompressor.setSourceXOffset(this.sourceXOffset);
        this.decompressor.setSourceYOffset(this.sourceYOffset);
        this.decompressor.setSubsampleX(this.srcXSubsampling);
        this.decompressor.setSubsampleY(this.srcYSubsampling);
        this.decompressor.setDstXOffset(this.dstXOffset);
        this.decompressor.setDstYOffset(this.dstYOffset);
        this.decompressor.setSourceBands(this.sourceBands);
        this.decompressor.setDestinationBands(this.destinationBands);
        int XToTileX = TIFFImageWriter.XToTileX(rectangle.x, 0, this.tileOrStripWidth);
        int YToTileY = TIFFImageWriter.YToTileY(rectangle.y, 0, this.tileOrStripHeight);
        int XToTileX2 = TIFFImageWriter.XToTileX((rectangle.x + rectangle.width) - 1, 0, this.tileOrStripWidth);
        int YToTileY2 = TIFFImageWriter.YToTileY((rectangle.y + rectangle.height) - 1, 0, this.tileOrStripHeight);
        if (this.planarConfiguration == 2) {
            this.decompressor.setPlanar(true);
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            z = false;
            while (YToTileY <= YToTileY2) {
                for (int i6 = XToTileX; i6 <= XToTileX2; i6++) {
                    int i7 = 0;
                    while (true) {
                        if (i7 >= this.numBands) {
                            break;
                        }
                        iArr[0] = this.sourceBands[i7];
                        this.decompressor.setSourceBands(iArr);
                        iArr2[0] = this.destinationBands[i7];
                        this.decompressor.setDestinationBands(iArr2);
                        if (abortRequested()) {
                            z = true;
                            break;
                        }
                        decodeTile(i6, YToTileY, i7);
                        i7++;
                    }
                    if (z) {
                        break;
                    }
                    reportProgress();
                }
                if (z) {
                    break;
                }
                YToTileY++;
            }
        } else {
            while (YToTileY <= YToTileY2) {
                int i8 = XToTileX;
                while (true) {
                    if (i8 > XToTileX2) {
                        break;
                    }
                    if (abortRequested()) {
                        z2 = true;
                        break;
                    }
                    decodeTile(i8, YToTileY, -1);
                    reportProgress();
                    i8++;
                }
                if (z2) {
                    break;
                }
                YToTileY++;
            }
            z = z2;
        }
        if (z) {
            processReadAborted();
        } else {
            processImageComplete();
        }
        return this.theImage;
    }

    public void reset() {
        super.reset();
        resetLocal();
    }

    protected void resetLocal() {
        this.stream = null;
        this.gotHeader = false;
        this.imageReadParam = getDefaultReadParam();
        this.streamMetadata = null;
        this.currIndex = -1;
        this.imageMetadata = null;
        this.imageStartPosition = new ArrayList();
        this.numImages = -1;
        this.imageTypeMap = new HashMap();
        this.width = -1;
        this.height = -1;
        this.numBands = -1;
        this.tileOrStripWidth = -1;
        this.tileOrStripHeight = -1;
        this.planarConfiguration = 1;
        this.rowsDone = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void forwardWarningMessage(String str) {
        processWarningOccurred(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected static BufferedImage getDestination(ImageReadParam imageReadParam, Iterator it, int i, int i2) throws IIOException {
        boolean z;
        int i3;
        int i4;
        if (it == null || !it.hasNext()) {
            throw new IllegalArgumentException("imageTypes null or empty!");
        }
        ImageTypeSpecifier imageTypeSpecifier = null;
        if (imageReadParam != null) {
            BufferedImage destination = imageReadParam.getDestination();
            if (destination != null) {
                return destination;
            }
            imageTypeSpecifier = imageReadParam.getDestinationType();
        }
        if (imageTypeSpecifier == null) {
            Object next = it.next();
            if (!(next instanceof ImageTypeSpecifier)) {
                throw new IllegalArgumentException("Non-ImageTypeSpecifier retrieved from imageTypes!");
            }
            imageTypeSpecifier = (ImageTypeSpecifier) next;
            Rectangle rectangle = new Rectangle(0, 0, 0, 0);
            Rectangle rectangle2 = new Rectangle(0, 0, 0, 0);
            computeRegions(imageReadParam, i, i2, null, rectangle, rectangle2);
            i3 = rectangle2.x + rectangle2.width;
            i4 = rectangle2.y + rectangle2.height;
            if (i3 * i4 <= 2147483647L) {
                throw new IllegalArgumentException("width*height > Integer.MAX_VALUE!");
            }
            return imageTypeSpecifier.createBufferedImage(i3, i4);
        }
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (((ImageTypeSpecifier) it.next()).equals(imageTypeSpecifier)) {
                z = true;
                break;
            }
        }
        if (!z) {
            throw new IIOException("Destination type from ImageReadParam does not match!");
        }
        Rectangle rectangle3 = new Rectangle(0, 0, 0, 0);
        Rectangle rectangle22 = new Rectangle(0, 0, 0, 0);
        computeRegions(imageReadParam, i, i2, null, rectangle3, rectangle22);
        i3 = rectangle22.x + rectangle22.width;
        i4 = rectangle22.y + rectangle22.height;
        if (i3 * i4 <= 2147483647L) {
        }
    }
}
