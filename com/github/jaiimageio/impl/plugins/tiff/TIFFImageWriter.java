package com.github.jaiimageio.impl.plugins.tiff;

import com.github.jaiimageio.impl.common.ImageUtil;
import com.github.jaiimageio.impl.common.SingleTileRenderedImage;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.EXIFParentTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.EXIFTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFColorConverter;
import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.github.jaiimageio.plugins.tiff.TIFFImageWriteParam;
import com.github.jaiimageio.plugins.tiff.TIFFTag;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import net.sf.scuba.smartcards.ISO7816;
import org.w3c.dom.Node;

/* loaded from: classes3.dex */
public class TIFFImageWriter extends ImageWriter {
    private static final boolean DEBUG = false;
    public static final int DEFAULT_BYTES_PER_STRIP = 8192;
    int bitDepth;
    char[] bitsPerSample;
    ByteOrder byteOrder;
    TIFFColorConverter colorConverter;
    int compression;
    TIFFCompressor compressor;
    long headerPosition;
    RenderedImage image;
    TIFFImageMetadata imageMetadata;
    ImageTypeSpecifier imageType;
    private boolean inReplacePixelsNest;
    boolean isBilevel;
    boolean isImageSimple;
    private boolean isInsertingEmpty;
    boolean isInverted;
    boolean isRescaling;
    boolean isTiled;
    private boolean isWritingEmpty;
    boolean isWritingSequence;
    int nativePhotometricInterpretation;
    long nextIFDPointerPos;
    long nextSpace;
    int numBands;
    ImageWriteParam param;
    int periodX;
    int periodY;
    int photometricInterpretation;
    int pixelsDone;
    int predictor;
    private TIFFImageReader reader;
    private long[] replacePixelsByteCounts;
    private long replacePixelsByteCountsPosition;
    private int replacePixelsIndex;
    private Object replacePixelsLock;
    private TIFFImageMetadata replacePixelsMetadata;
    private long replacePixelsOffsetsPosition;
    private Rectangle replacePixelsRegion;
    private long[] replacePixelsTileOffsets;
    int sampleFormat;
    int[] sampleSize;
    byte[][] scale;
    byte[] scale0;
    byte[][] scaleh;
    byte[][] scalel;
    int scalingBitDepth;
    int[] sourceBands;
    int sourceHeight;
    int sourceWidth;
    int sourceXOffset;
    int sourceYOffset;
    ImageOutputStream stream;
    TIFFStreamMetadata streamMetadata;
    int tileLength;
    int tileWidth;
    int tilesAcross;
    int tilesDown;
    int totalPixels;
    static final String EXIF_JPEG_COMPRESSION_TYPE = "EXIF JPEG";
    public static final String[] TIFFCompressionTypes = {"CCITT RLE", "CCITT T.4", "CCITT T.6", "LZW", "JPEG", "ZLib", "PackBits", "Deflate", EXIF_JPEG_COMPRESSION_TYPE};
    public static final String[] compressionTypes = {"CCITT RLE", "CCITT T.4", "CCITT T.6", "LZW", "Old JPEG", "JPEG", "ZLib", "PackBits", "Deflate", EXIF_JPEG_COMPRESSION_TYPE};
    public static final boolean[] isCompressionLossless = {true, true, true, true, false, false, true, true, true, false};
    public static final int[] compressionNumbers = {2, 3, 4, 5, 6, 7, 8, 32773, BaselineTIFFTagSet.COMPRESSION_DEFLATE, 6};

    public boolean canWriteSequence() {
        return true;
    }

    public static int XToTileX(int i, int i2, int i3) {
        int i4 = i - i2;
        if (i4 < 0) {
            i4 += 1 - i3;
        }
        return i4 / i3;
    }

    public static int YToTileY(int i, int i2, int i3) {
        int i4 = i - i2;
        if (i4 < 0) {
            i4 += 1 - i3;
        }
        return i4 / i3;
    }

    public TIFFImageWriter(ImageWriterSpi imageWriterSpi) {
        super(imageWriterSpi);
        this.sampleSize = null;
        this.scalingBitDepth = -1;
        this.isRescaling = false;
        this.sampleFormat = 4;
        this.scale = null;
        this.scale0 = null;
        this.scaleh = null;
        this.scalel = null;
        this.nextSpace = 0L;
        this.isWritingSequence = false;
        this.isInsertingEmpty = false;
        this.isWritingEmpty = false;
        this.replacePixelsLock = new Object();
        this.replacePixelsIndex = -1;
        this.replacePixelsMetadata = null;
        this.replacePixelsTileOffsets = null;
        this.replacePixelsByteCounts = null;
        this.replacePixelsOffsetsPosition = 0L;
        this.replacePixelsByteCountsPosition = 0L;
        this.replacePixelsRegion = null;
        this.inReplacePixelsNest = false;
        this.reader = null;
    }

    public ImageWriteParam getDefaultWriteParam() {
        return new TIFFImageWriteParam(getLocale());
    }

    public void setOutput(Object obj) {
        super.setOutput(obj);
        if (obj != null) {
            if (!(obj instanceof ImageOutputStream)) {
                throw new IllegalArgumentException("output not an ImageOutputStream!");
            }
            ImageOutputStream imageOutputStream = (ImageOutputStream) obj;
            this.stream = imageOutputStream;
            try {
                this.headerPosition = imageOutputStream.getStreamPosition();
                try {
                    byte[] bArr = new byte[4];
                    this.stream.readFully(bArr);
                    if ((bArr[0] == 73 && bArr[1] == 73 && bArr[2] == 42 && bArr[3] == 0) || (bArr[0] == 77 && bArr[1] == 77 && bArr[2] == 0 && bArr[3] == 42)) {
                        this.nextSpace = this.stream.length();
                    } else {
                        this.nextSpace = this.headerPosition;
                    }
                } catch (IOException unused) {
                    this.nextSpace = this.headerPosition;
                }
                this.stream.seek(this.headerPosition);
                return;
            } catch (IOException unused2) {
                this.headerPosition = 0L;
                this.nextSpace = 0L;
                return;
            }
        }
        this.stream = null;
    }

    public IIOMetadata getDefaultStreamMetadata(ImageWriteParam imageWriteParam) {
        return new TIFFStreamMetadata();
    }

    public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        TIFFImageMetadata tIFFImageMetadata;
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(BaselineTIFFTagSet.getInstance());
        TIFFImageMetadata tIFFImageMetadata2 = new TIFFImageMetadata(arrayList);
        return (imageTypeSpecifier == null || (tIFFImageMetadata = (TIFFImageMetadata) convertImageMetadata(tIFFImageMetadata2, imageTypeSpecifier, imageWriteParam)) == null) ? tIFFImageMetadata2 : tIFFImageMetadata;
    }

    public IIOMetadata convertStreamMetadata(IIOMetadata iIOMetadata, ImageWriteParam imageWriteParam) {
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        TIFFStreamMetadata tIFFStreamMetadata = null;
        if (iIOMetadata instanceof TIFFStreamMetadata) {
            TIFFStreamMetadata tIFFStreamMetadata2 = new TIFFStreamMetadata();
            tIFFStreamMetadata2.byteOrder = ((TIFFStreamMetadata) iIOMetadata).byteOrder;
            return tIFFStreamMetadata2;
        }
        if (Arrays.asList(iIOMetadata.getMetadataFormatNames()).contains("com_sun_media_imageio_plugins_tiff_stream_1.0")) {
            tIFFStreamMetadata = new TIFFStreamMetadata();
            try {
                tIFFStreamMetadata.mergeTree("com_sun_media_imageio_plugins_tiff_stream_1.0", iIOMetadata.getAsTree("com_sun_media_imageio_plugins_tiff_stream_1.0"));
            } catch (IIOInvalidTreeException unused) {
            }
        }
        return tIFFStreamMetadata;
    }

    public IIOMetadata convertImageMetadata(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, ImageWriteParam imageWriteParam) {
        TIFFImageMetadata tIFFImageMetadata;
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        if (imageTypeSpecifier == null) {
            throw new IllegalArgumentException("imageType == null!");
        }
        if (iIOMetadata instanceof TIFFImageMetadata) {
            tIFFImageMetadata = new TIFFImageMetadata(((TIFFImageMetadata) iIOMetadata).getRootIFD().getShallowClone());
        } else if (Arrays.asList(iIOMetadata.getMetadataFormatNames()).contains(TIFFImageMetadata.nativeMetadataFormatName)) {
            tIFFImageMetadata = convertNativeImageMetadata(iIOMetadata);
        } else {
            if (iIOMetadata.isStandardMetadataFormatSupported()) {
                tIFFImageMetadata = convertStandardImageMetadata(iIOMetadata);
            }
            tIFFImageMetadata = null;
        }
        if (tIFFImageMetadata == null) {
            return tIFFImageMetadata;
        }
        TIFFImageWriter tIFFImageWriter = new TIFFImageWriter(this.originatingProvider);
        tIFFImageWriter.imageMetadata = tIFFImageMetadata;
        tIFFImageWriter.param = imageWriteParam;
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        try {
            tIFFImageWriter.setupMetadata(imageTypeSpecifier.getColorModel(), sampleModel, sampleModel.getWidth(), sampleModel.getHeight());
            return tIFFImageWriter.imageMetadata;
        } catch (IIOException unused) {
            return null;
        } finally {
            tIFFImageWriter.dispose();
        }
    }

    private TIFFImageMetadata convertStandardImageMetadata(IIOMetadata iIOMetadata) throws IIOInvalidTreeException {
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        if (!iIOMetadata.isStandardMetadataFormatSupported()) {
            throw new IllegalArgumentException("inData does not support standard metadata format!");
        }
        Node asTree = iIOMetadata.getAsTree("javax_imageio_1.0");
        if (asTree == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(BaselineTIFFTagSet.getInstance());
        TIFFImageMetadata tIFFImageMetadata = new TIFFImageMetadata(arrayList);
        tIFFImageMetadata.setFromTree("javax_imageio_1.0", asTree);
        return tIFFImageMetadata;
    }

    private TIFFImageMetadata convertNativeImageMetadata(IIOMetadata iIOMetadata) throws IIOInvalidTreeException {
        if (iIOMetadata == null) {
            throw new IllegalArgumentException("inData == null!");
        }
        if (!Arrays.asList(iIOMetadata.getMetadataFormatNames()).contains(TIFFImageMetadata.nativeMetadataFormatName)) {
            throw new IllegalArgumentException("inData does not support native metadata format!");
        }
        Node asTree = iIOMetadata.getAsTree(TIFFImageMetadata.nativeMetadataFormatName);
        if (asTree == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(BaselineTIFFTagSet.getInstance());
        TIFFImageMetadata tIFFImageMetadata = new TIFFImageMetadata(arrayList);
        tIFFImageMetadata.setFromTree(TIFFImageMetadata.nativeMetadataFormatName, asTree);
        return tIFFImageMetadata;
    }

    /* JADX WARN: Code restructure failed: missing block: B:390:0x0229, code lost:
    
        if (com.github.jaiimageio.impl.plugins.tiff.TIFFImageWriter.EXIF_JPEG_COMPRESSION_TYPE.equals(r26.param.getCompressionType()) != false) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01c9, code lost:
    
        if (r9 != 2) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0214, code lost:
    
        if (r7 != 6) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0218, code lost:
    
        if (r6 == r9) goto L154;
     */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0328 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03b1 A[LOOP:0: B:170:0x03af->B:171:0x03b1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03e8 A[LOOP:1: B:177:0x03e3->B:179:0x03e8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03f0 A[EDGE_INSN: B:180:0x03f0->B:181:0x03f0 BREAK  A[LOOP:1: B:177:0x03e3->B:179:0x03e8], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0559 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x068a  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0694 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x06ec  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x076b  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0809  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:315:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:316:0x07b0  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x05fb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:360:0x061d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x023c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void setupMetadata(ColorModel colorModel, SampleModel sampleModel, int i, int i2) throws IIOException {
        boolean z;
        int numBands;
        int i3;
        char[] cArr;
        int i4;
        ImageWriteParam imageWriteParam;
        int compressionMode;
        TIFFField tIFFField;
        boolean z2;
        boolean z3;
        TIFFCompressor tIFFCompressor;
        boolean z4;
        int i5;
        int i6;
        int i7;
        int i8;
        char[] cArr2;
        TIFFField tIFFField2;
        boolean z5;
        TIFFField tIFFField3;
        int i9;
        TIFFField tIFFField4;
        int i10;
        int max;
        int min;
        int tilingMode;
        boolean z6;
        int i11;
        int i12;
        int i13;
        TIFFIFD tiffifd;
        int i14;
        boolean z7;
        int i15;
        int i16;
        int[] asInts;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        TIFFField tIFFField5;
        TIFFIFD rootIFD = this.imageMetadata.getRootIFD();
        BaselineTIFFTagSet baselineTIFFTagSet = BaselineTIFFTagSet.getInstance();
        TIFFField tIFFField6 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION);
        if (tIFFField6 != null && tIFFField6.getAsInt(0) != 1) {
            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION), 1));
        }
        this.photometricInterpretation = -1;
        TIFFField tIFFField7 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
        if (tIFFField7 != null) {
            int asInt = tIFFField7.getAsInt(0);
            this.photometricInterpretation = asInt;
            if (asInt == 3 && !(colorModel instanceof IndexColorModel)) {
                this.photometricInterpretation = -1;
            } else {
                z = true;
                int[] sampleSize = sampleModel.getSampleSize();
                numBands = sampleModel.getNumBands();
                if (numBands > 1 || colorModel == null || !colorModel.hasAlpha()) {
                    i3 = 0;
                    cArr = null;
                } else {
                    numBands--;
                    cArr = new char[1];
                    if (colorModel.isAlphaPremultiplied()) {
                        cArr[0] = 1;
                    } else {
                        cArr[0] = 2;
                    }
                    i3 = 1;
                }
                if (numBands != 3) {
                    this.nativePhotometricInterpretation = 2;
                    if (this.photometricInterpretation == -1) {
                        this.photometricInterpretation = 2;
                    }
                } else if (sampleModel.getNumBands() == 1 && (colorModel instanceof IndexColorModel)) {
                    IndexColorModel indexColorModel = (IndexColorModel) colorModel;
                    int red = indexColorModel.getRed(0);
                    int red2 = indexColorModel.getRed(1);
                    if (indexColorModel.getMapSize() == 2 && red == indexColorModel.getGreen(0) && red == indexColorModel.getBlue(0) && red2 == indexColorModel.getGreen(1) && red2 == indexColorModel.getBlue(1) && ((red == 0 || red == 255) && ((red2 == 0 || red2 == 255) && red != red2))) {
                        if (red == 0) {
                            i4 = 1;
                            this.nativePhotometricInterpretation = 1;
                        } else {
                            i4 = 1;
                            this.nativePhotometricInterpretation = 0;
                        }
                        int i22 = this.photometricInterpretation;
                        if (i22 != i4 && i22 != 0) {
                            this.photometricInterpretation = red == 0 ? 1 : 0;
                        }
                    } else {
                        this.photometricInterpretation = 3;
                        this.nativePhotometricInterpretation = 3;
                    }
                } else {
                    if (colorModel != null) {
                        int type = colorModel.getColorSpace().getType();
                        if (type == 1) {
                            this.nativePhotometricInterpretation = 8;
                        } else if (type == 3) {
                            this.nativePhotometricInterpretation = 6;
                        } else if (type == 9) {
                            this.nativePhotometricInterpretation = 5;
                        } else {
                            this.nativePhotometricInterpretation = 1;
                        }
                    } else {
                        this.nativePhotometricInterpretation = 1;
                    }
                    if (this.photometricInterpretation == -1) {
                        this.photometricInterpretation = this.nativePhotometricInterpretation;
                    }
                }
                this.compressor = null;
                this.colorConverter = null;
                imageWriteParam = this.param;
                if (imageWriteParam instanceof TIFFImageWriteParam) {
                    TIFFImageWriteParam tIFFImageWriteParam = (TIFFImageWriteParam) imageWriteParam;
                    if (tIFFImageWriteParam.getCompressionMode() == 2) {
                        this.compressor = tIFFImageWriteParam.getTIFFCompressor();
                        String compressionType = this.param.getCompressionType();
                        TIFFCompressor tIFFCompressor2 = this.compressor;
                        if (tIFFCompressor2 != null && !tIFFCompressor2.getCompressionType().equals(compressionType)) {
                            this.compressor = null;
                        }
                    } else {
                        this.compressor = null;
                    }
                    TIFFColorConverter colorConverter = tIFFImageWriteParam.getColorConverter();
                    this.colorConverter = colorConverter;
                    if (colorConverter != null) {
                        this.photometricInterpretation = tIFFImageWriteParam.getPhotometricInterpretation();
                    }
                }
                ImageWriteParam imageWriteParam2 = this.param;
                compressionMode = !(imageWriteParam2 instanceof TIFFImageWriteParam) ? imageWriteParam2.getCompressionMode() : 1;
                if (compressionMode != 2) {
                    String compressionType2 = this.param.getCompressionType();
                    if (compressionType2 == null) {
                        this.compression = 1;
                    } else {
                        int length = compressionTypes.length;
                        for (int i23 = 0; i23 < length; i23++) {
                            if (compressionType2.equals(compressionTypes[i23])) {
                                this.compression = compressionNumbers[i23];
                            }
                        }
                    }
                    TIFFCompressor tIFFCompressor3 = this.compressor;
                    if (tIFFCompressor3 != null && tIFFCompressor3.getCompressionTagValue() != this.compression) {
                        this.compressor = null;
                        tIFFField = rootIFD.getTIFFField(317);
                        if (tIFFField != null) {
                            int asInt2 = tIFFField.getAsInt(0);
                            this.predictor = asInt2;
                            if (sampleSize[0] == 8) {
                                i21 = 1;
                                if (asInt2 != 1) {
                                }
                            } else {
                                i21 = 1;
                            }
                            this.predictor = i21;
                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(317), this.predictor));
                        }
                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_COMPRESSION), this.compression));
                        if (numBands == 3 && sampleSize[0] == 8 && sampleSize[1] == 8 && sampleSize[2] == 8) {
                            if (rootIFD.getTIFFField(EXIFParentTIFFTagSet.TAG_EXIF_IFD_POINTER) == null) {
                                int i24 = this.compression;
                                if (i24 == 1) {
                                    int i25 = this.photometricInterpretation;
                                    i20 = 6;
                                    if (i25 != 2) {
                                    }
                                    z2 = true;
                                } else {
                                    i20 = 6;
                                }
                            } else if (compressionMode == 2) {
                            }
                            z3 = !z2 && this.compression == 6;
                            tIFFCompressor = this.compressor;
                            if (tIFFCompressor == null) {
                                int i26 = this.compression;
                                if (i26 == 2) {
                                    if (tIFFCompressor == null) {
                                        this.compressor = new TIFFRLECompressor();
                                    }
                                    if (!z) {
                                        this.photometricInterpretation = 0;
                                    }
                                } else if (i26 == 3) {
                                    if (tIFFCompressor == null) {
                                        this.compressor = new TIFFT4Compressor();
                                    }
                                    if (!z) {
                                        this.photometricInterpretation = 0;
                                    }
                                } else if (i26 == 4) {
                                    if (tIFFCompressor == null) {
                                        this.compressor = new TIFFT6Compressor();
                                    }
                                    if (!z) {
                                        this.photometricInterpretation = 0;
                                    }
                                } else if (i26 == 5) {
                                    this.compressor = new TIFFLZWCompressor(this.predictor);
                                } else if (i26 == 6) {
                                    if (z2) {
                                        this.compressor = new TIFFEXIFJPEGCompressor(this.param);
                                    } else {
                                        throw new IIOException("Old JPEG compression not supported!");
                                    }
                                } else if (i26 == 7) {
                                    if (numBands == 3) {
                                        i19 = 8;
                                        if (sampleSize[0] == 8) {
                                            i18 = 1;
                                            if (sampleSize[1] == 8 && sampleSize[2] == 8) {
                                                this.photometricInterpretation = 6;
                                                this.compressor = new TIFFJPEGCompressor(this.param);
                                            }
                                        } else {
                                            i18 = 1;
                                        }
                                    } else {
                                        i18 = 1;
                                        i19 = 8;
                                    }
                                    if (numBands == i18 && sampleSize[0] == i19) {
                                        this.photometricInterpretation = i18;
                                        this.compressor = new TIFFJPEGCompressor(this.param);
                                    } else {
                                        throw new IIOException("JPEG compression supported for 1- and 3-band byte images only!");
                                    }
                                } else if (i26 == 8) {
                                    this.compressor = new TIFFZLibCompressor(this.param, this.predictor);
                                } else if (i26 == 32773) {
                                    this.compressor = new TIFFPackBitsCompressor();
                                } else if (i26 == 32946) {
                                    this.compressor = new TIFFDeflateCompressor(this.param, this.predictor);
                                } else {
                                    TIFFField tIFFField8 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_FILL_ORDER);
                                    if (tIFFField8 != null && tIFFField8.getAsInt(0) == 2) {
                                        this.compressor = new TIFFLSBCompressor();
                                    } else {
                                        this.compressor = new TIFFNullCompressor();
                                    }
                                }
                            }
                            if (this.colorConverter == null && colorModel != null && colorModel.getColorSpace().getType() == 5) {
                                i17 = this.photometricInterpretation;
                                if (i17 != 6 && this.compression != 7) {
                                    this.colorConverter = new TIFFYCbCrColorConverter(this.imageMetadata);
                                } else if (i17 == 8) {
                                    this.colorConverter = new TIFFCIELabColorConverter();
                                }
                            }
                            if (this.photometricInterpretation == 6 || this.compression == 7) {
                                z4 = z2;
                            } else {
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING);
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING);
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING), 3, 2, new char[]{1, 1}));
                                z4 = z2;
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING), 3, 1, new char[]{2}));
                            }
                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), this.photometricInterpretation));
                            int i27 = numBands + i3;
                            this.bitsPerSample = new char[i27];
                            this.bitDepth = 0;
                            for (i5 = 0; i5 < numBands; i5++) {
                                this.bitDepth = Math.max(this.bitDepth, sampleSize[i5]);
                            }
                            i6 = this.bitDepth;
                            if (i6 == 3) {
                                this.bitDepth = 4;
                            } else {
                                if (i6 > 4) {
                                    i7 = 8;
                                    if (i6 < 8) {
                                        this.bitDepth = 8;
                                    }
                                } else {
                                    i7 = 8;
                                }
                                if (i6 > i7 && i6 < 16) {
                                    this.bitDepth = 16;
                                } else if (i6 > 16) {
                                    this.bitDepth = 32;
                                }
                            }
                            i8 = 0;
                            while (true) {
                                cArr2 = this.bitsPerSample;
                                if (i8 >= cArr2.length) {
                                    break;
                                }
                                cArr2[i8] = (char) this.bitDepth;
                                i8++;
                            }
                            if (cArr2.length == 1 || cArr2[0] != 1) {
                                TIFFTag tag = baselineTIFFTagSet.getTag(258);
                                char[] cArr3 = this.bitsPerSample;
                                rootIFD.addTIFFField(new TIFFField(tag, 3, cArr3.length, cArr3));
                            } else {
                                TIFFField tIFFField9 = rootIFD.getTIFFField(258);
                                if (tIFFField9 != null && ((asInts = tIFFField9.getAsInts()) == null || asInts.length != 1 || asInts[0] != 1)) {
                                    rootIFD.removeTIFFField(258);
                                }
                            }
                            tIFFField2 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
                            if (tIFFField2 != null && ((i16 = this.bitDepth) == 16 || i16 == 32)) {
                                int dataType = sampleModel.getDataType();
                                int i28 = this.bitDepth;
                                char c = (i28 == 16 && dataType == 1) ? (char) 1 : (i28 == 32 && dataType == 4) ? (char) 3 : (char) 2;
                                this.sampleFormat = c;
                                int length2 = this.bitsPerSample.length;
                                char[] cArr4 = new char[length2];
                                Arrays.fill(cArr4, c);
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT), 3, length2, cArr4));
                            } else if (tIFFField2 != null) {
                                this.sampleFormat = tIFFField2.getAsInt(0);
                            } else {
                                this.sampleFormat = 4;
                            }
                            if (cArr != null) {
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES), 3, cArr.length, cArr));
                            } else {
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES);
                            }
                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), this.bitsPerSample.length));
                            if (this.photometricInterpretation != 3 && (colorModel instanceof IndexColorModel)) {
                                char[] cArr5 = this.bitsPerSample;
                                int i29 = (1 << cArr5[0]) * 3;
                                char[] cArr6 = new char[i29];
                                IndexColorModel indexColorModel2 = (IndexColorModel) colorModel;
                                int i30 = 1 << cArr5[0];
                                int min2 = Math.min(i30, indexColorModel2.getMapSize());
                                int i31 = 0;
                                while (i31 < min2) {
                                    cArr6[i31] = (char) ((indexColorModel2.getRed(i31) * 65535) / 255);
                                    cArr6[i30 + i31] = (char) ((indexColorModel2.getGreen(i31) * 65535) / 255);
                                    cArr6[(i30 * 2) + i31] = (char) ((indexColorModel2.getBlue(i31) * 65535) / 255);
                                    i31++;
                                    min2 = min2;
                                    z3 = z3;
                                }
                                z5 = z3;
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_COLOR_MAP), 3, i29, cArr6));
                            } else {
                                z5 = z3;
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
                            }
                            if (colorModel != null && rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_ICC_PROFILE) == null && ImageUtil.isNonStandardICCColorSpace(colorModel.getColorSpace())) {
                                byte[] data = colorModel.getColorSpace().getProfile().getData();
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_ICC_PROFILE), 7, data.length, data));
                            }
                            tIFFField3 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_X_RESOLUTION);
                            TIFFField tIFFField10 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
                            if (tIFFField3 == null || tIFFField10 != null) {
                                i9 = numBands;
                                if (tIFFField3 != null && tIFFField10 != null) {
                                    rootIFD.addTIFFField(new TIFFField(rootIFD.getTag(BaselineTIFFTagSet.TAG_X_RESOLUTION), 5, 1, (long[]) tIFFField10.getAsRational(0).clone()));
                                } else if (tIFFField3 != null && tIFFField10 == null) {
                                    rootIFD.addTIFFField(new TIFFField(rootIFD.getTag(BaselineTIFFTagSet.TAG_Y_RESOLUTION), 5, 1, (long[]) tIFFField3.getAsRational(0).clone()));
                                }
                            } else {
                                long[][] jArr = (long[][]) Array.newInstance((Class<?>) long.class, 1, 2);
                                jArr[0] = new long[2];
                                TIFFField tIFFField11 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT);
                                if (tIFFField11 == null && rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_X_POSITION) == null && rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_POSITION) == null) {
                                    jArr[0][0] = 1;
                                    jArr[0][1] = 1;
                                    rootIFD.addTIFFField(new TIFFField(rootIFD.getTag(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT), 1));
                                    i9 = numBands;
                                    i15 = 1;
                                } else {
                                    int asInt3 = tIFFField11 != null ? tIFFField11.getAsInt(0) : 2;
                                    int max2 = Math.max(i, i2);
                                    if (asInt3 == 2) {
                                        i9 = numBands;
                                        i15 = 1;
                                        jArr[0][0] = max2;
                                        jArr[0][1] = 4;
                                    } else if (asInt3 == 3) {
                                        i9 = numBands;
                                        jArr[0][0] = max2 * 100;
                                        i15 = 1;
                                        jArr[0][1] = 1016;
                                    } else {
                                        jArr[0][0] = 1;
                                        i15 = 1;
                                        jArr[0][1] = 1;
                                        i9 = numBands;
                                    }
                                }
                                rootIFD.addTIFFField(new TIFFField(rootIFD.getTag(BaselineTIFFTagSet.TAG_X_RESOLUTION), 5, i15, jArr));
                                rootIFD.addTIFFField(new TIFFField(rootIFD.getTag(BaselineTIFFTagSet.TAG_Y_RESOLUTION), 5, i15, jArr));
                            }
                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(256), i));
                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(257), i2));
                            tIFFField4 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
                            if (tIFFField4 != null) {
                                max = tIFFField4.getAsInt(0);
                                if (max < 0) {
                                    max = i2;
                                }
                                i10 = 1;
                            } else {
                                i10 = 1;
                                max = Math.max(Math.max(8192 / ((((this.bitDepth * i27) * i) + 7) / 8), 1), 8);
                            }
                            min = Math.min(max, i2);
                            ImageWriteParam imageWriteParam3 = this.param;
                            tilingMode = imageWriteParam3 instanceof TIFFImageWriteParam ? imageWriteParam3.getTilingMode() : i10;
                            if (tilingMode != 0 || tilingMode == i10) {
                                this.tileWidth = i;
                                this.tileLength = min;
                                z6 = false;
                            } else {
                                if (tilingMode == 2) {
                                    this.tileWidth = this.param.getTileWidth();
                                    this.tileLength = this.param.getTileHeight();
                                } else if (tilingMode == 3) {
                                    TIFFField tIFFField12 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_TILE_WIDTH);
                                    if (tIFFField12 == null) {
                                        this.tileWidth = i;
                                        i14 = 0;
                                        z7 = false;
                                    } else {
                                        i14 = 0;
                                        this.tileWidth = tIFFField12.getAsInt(0);
                                        z7 = true;
                                    }
                                    TIFFField tIFFField13 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_TILE_LENGTH);
                                    if (tIFFField13 == null) {
                                        this.tileLength = min;
                                        z6 = z7;
                                    } else {
                                        this.tileLength = tIFFField13.getAsInt(i14);
                                    }
                                } else {
                                    throw new IIOException("Illegal value of tilingMode!");
                                }
                                z6 = true;
                            }
                            if (this.compression == 7) {
                                int i32 = i9 == 1 ? 1 : 2;
                                if (z6) {
                                    int i33 = i32 * 8;
                                    int i34 = i33 / 2;
                                    this.tileWidth = Math.max(((this.tileWidth + i34) / i33) * i33, i33);
                                    this.tileLength = Math.max(((this.tileLength + i34) / i33) * i33, i33);
                                } else if (min < i2) {
                                    int max3 = Math.max(i32, i32) * 8;
                                    min = Math.max(((this.tileLength + (max3 / 2)) / max3) * max3, max3);
                                    this.tileLength = min;
                                }
                            } else if (z5) {
                                this.tileWidth = i;
                                this.tileLength = i2;
                            } else if (z6) {
                                int i35 = this.tileWidth;
                                if (i35 % 16 != 0) {
                                    i11 = 8;
                                    i12 = 16;
                                    this.tileWidth = Math.max(((i35 + 8) / 16) * 16, 16);
                                } else {
                                    i11 = 8;
                                    i12 = 16;
                                }
                                int i36 = this.tileLength;
                                if (i36 % 16 != 0) {
                                    this.tileLength = Math.max(((i36 + i11) / i12) * i12, i12);
                                }
                            }
                            int i37 = this.tileWidth;
                            this.tilesAcross = ((i + i37) - 1) / i37;
                            int i38 = this.tileLength;
                            this.tilesDown = ((i2 + i38) - 1) / i38;
                            if (!z6) {
                                this.isTiled = false;
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_TILE_WIDTH);
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_TILE_LENGTH);
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_TILE_OFFSETS);
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_TILE_BYTE_COUNTS);
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP), min));
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_STRIP_OFFSETS), 4, this.tilesDown));
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS), 4, this.tilesDown));
                            } else {
                                this.isTiled = true;
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_STRIP_OFFSETS);
                                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS);
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_TILE_WIDTH), this.tileWidth));
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_TILE_LENGTH), this.tileLength));
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_TILE_OFFSETS), 4, this.tilesDown * this.tilesAcross));
                                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_TILE_BYTE_COUNTS), 4, this.tilesDown * this.tilesAcross));
                            }
                            if (z4) {
                                boolean isEncodingEmpty = isEncodingEmpty();
                                if (this.compression == 6) {
                                    rootIFD.removeTIFFField(256);
                                    rootIFD.removeTIFFField(257);
                                    rootIFD.removeTIFFField(258);
                                    if (isEncodingEmpty) {
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION);
                                    }
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION);
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_STRIP_OFFSETS);
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL);
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS);
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION);
                                    if (rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT) == null) {
                                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT), 2));
                                    }
                                    if (isEncodingEmpty) {
                                        rootIFD.removeTIFFField(513);
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING);
                                        if (rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING) == null) {
                                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING), 3, 1, new char[]{1}));
                                        }
                                    } else {
                                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(513), 4, 1));
                                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH), 4, 1));
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING);
                                    }
                                } else {
                                    if (rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT) == null) {
                                        i13 = 2;
                                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT), 2));
                                    } else {
                                        i13 = 2;
                                    }
                                    rootIFD.removeTIFFField(513);
                                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
                                    if (this.photometricInterpretation == i13) {
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_COEFFICIENTS);
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING);
                                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING);
                                    }
                                }
                                EXIFTIFFTagSet eXIFTIFFTagSet = EXIFTIFFTagSet.getInstance();
                                TIFFField tIFFField14 = rootIFD.getTIFFField(EXIFParentTIFFTagSet.TAG_EXIF_IFD_POINTER);
                                if (tIFFField14 != null) {
                                    tiffifd = (TIFFIFD) tIFFField14.getData();
                                } else if (isEncodingEmpty) {
                                    ArrayList arrayList = new ArrayList(1);
                                    arrayList.add(eXIFTIFFTagSet);
                                    tiffifd = new TIFFIFD(arrayList);
                                    rootIFD.addTIFFField(new TIFFField(EXIFParentTIFFTagSet.getInstance().getTag(EXIFParentTIFFTagSet.TAG_EXIF_IFD_POINTER), 4, 1, tiffifd));
                                } else {
                                    tiffifd = null;
                                }
                                if (tiffifd != null) {
                                    if (tiffifd.getTIFFField(EXIFTIFFTagSet.TAG_EXIF_VERSION) == null) {
                                        tiffifd.addTIFFField(new TIFFField(eXIFTIFFTagSet.getTag(EXIFTIFFTagSet.TAG_EXIF_VERSION), 7, 4, EXIFTIFFTagSet.EXIF_VERSION_2_2));
                                    }
                                    if (this.compression == 6) {
                                        if (tiffifd.getTIFFField(EXIFTIFFTagSet.TAG_COMPONENTS_CONFIGURATION) == null) {
                                            tiffifd.addTIFFField(new TIFFField(eXIFTIFFTagSet.getTag(EXIFTIFFTagSet.TAG_COMPONENTS_CONFIGURATION), 7, 4, new byte[]{1, 2, 3, 0}));
                                        }
                                    } else {
                                        tiffifd.removeTIFFField(EXIFTIFFTagSet.TAG_COMPONENTS_CONFIGURATION);
                                        tiffifd.removeTIFFField(EXIFTIFFTagSet.TAG_COMPRESSED_BITS_PER_PIXEL);
                                    }
                                    if (tiffifd.getTIFFField(EXIFTIFFTagSet.TAG_FLASHPIX_VERSION) == null) {
                                        tiffifd.addTIFFField(new TIFFField(eXIFTIFFTagSet.getTag(EXIFTIFFTagSet.TAG_FLASHPIX_VERSION), 7, 4, new byte[]{ISO7816.INS_DECREASE, 49, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE}));
                                    }
                                    if (tiffifd.getTIFFField(EXIFTIFFTagSet.TAG_COLOR_SPACE) == null) {
                                        tiffifd.addTIFFField(new TIFFField(eXIFTIFFTagSet.getTag(EXIFTIFFTagSet.TAG_COLOR_SPACE), 3, 1, new char[]{1}));
                                    }
                                    if (this.compression == 6) {
                                        if (tiffifd.getTIFFField(EXIFTIFFTagSet.TAG_PIXEL_X_DIMENSION) == null) {
                                            tiffifd.addTIFFField(new TIFFField(eXIFTIFFTagSet.getTag(EXIFTIFFTagSet.TAG_PIXEL_X_DIMENSION), i));
                                        }
                                        if (tiffifd.getTIFFField(EXIFTIFFTagSet.TAG_PIXEL_Y_DIMENSION) == null) {
                                            tiffifd.addTIFFField(new TIFFField(eXIFTIFFTagSet.getTag(EXIFTIFFTagSet.TAG_PIXEL_Y_DIMENSION), i2));
                                            return;
                                        }
                                        return;
                                    }
                                    tiffifd.removeTIFFField(EXIFTIFFTagSet.TAG_INTEROPERABILITY_IFD_POINTER);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        z2 = false;
                        if (z2) {
                        }
                        tIFFCompressor = this.compressor;
                        if (tIFFCompressor == null) {
                        }
                        if (this.colorConverter == null) {
                            i17 = this.photometricInterpretation;
                            if (i17 != 6) {
                            }
                            if (i17 == 8) {
                            }
                        }
                        if (this.photometricInterpretation == 6) {
                        }
                        z4 = z2;
                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), this.photometricInterpretation));
                        int i272 = numBands + i3;
                        this.bitsPerSample = new char[i272];
                        this.bitDepth = 0;
                        while (i5 < numBands) {
                        }
                        i6 = this.bitDepth;
                        if (i6 == 3) {
                        }
                        i8 = 0;
                        while (true) {
                            cArr2 = this.bitsPerSample;
                            if (i8 >= cArr2.length) {
                            }
                            cArr2[i8] = (char) this.bitDepth;
                            i8++;
                        }
                        if (cArr2.length == 1) {
                        }
                        TIFFTag tag2 = baselineTIFFTagSet.getTag(258);
                        char[] cArr32 = this.bitsPerSample;
                        rootIFD.addTIFFField(new TIFFField(tag2, 3, cArr32.length, cArr32));
                        tIFFField2 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
                        if (tIFFField2 != null) {
                        }
                        if (tIFFField2 != null) {
                        }
                        if (cArr != null) {
                        }
                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), this.bitsPerSample.length));
                        if (this.photometricInterpretation != 3) {
                        }
                        z5 = z3;
                        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
                        if (colorModel != null) {
                            byte[] data2 = colorModel.getColorSpace().getProfile().getData();
                            rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_ICC_PROFILE), 7, data2.length, data2));
                        }
                        tIFFField3 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_X_RESOLUTION);
                        TIFFField tIFFField102 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
                        if (tIFFField3 == null) {
                        }
                        i9 = numBands;
                        if (tIFFField3 != null) {
                        }
                        if (tIFFField3 != null) {
                            rootIFD.addTIFFField(new TIFFField(rootIFD.getTag(BaselineTIFFTagSet.TAG_Y_RESOLUTION), 5, 1, (long[]) tIFFField3.getAsRational(0).clone()));
                        }
                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(256), i));
                        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(257), i2));
                        tIFFField4 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
                        if (tIFFField4 != null) {
                        }
                        min = Math.min(max, i2);
                        ImageWriteParam imageWriteParam32 = this.param;
                        if (imageWriteParam32 instanceof TIFFImageWriteParam) {
                        }
                        if (tilingMode != 0) {
                        }
                        this.tileWidth = i;
                        this.tileLength = min;
                        z6 = false;
                        if (this.compression == 7) {
                        }
                        int i372 = this.tileWidth;
                        this.tilesAcross = ((i + i372) - 1) / i372;
                        int i382 = this.tileLength;
                        this.tilesDown = ((i2 + i382) - 1) / i382;
                        if (!z6) {
                        }
                        if (z4) {
                        }
                    }
                } else if (compressionMode == 3 && (tIFFField5 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION)) != null) {
                    this.compression = tIFFField5.getAsInt(0);
                } else {
                    this.compression = 1;
                }
                tIFFField = rootIFD.getTIFFField(317);
                if (tIFFField != null) {
                }
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_COMPRESSION), this.compression));
                if (numBands == 3) {
                    if (rootIFD.getTIFFField(EXIFParentTIFFTagSet.TAG_EXIF_IFD_POINTER) == null) {
                    }
                    if (z2) {
                    }
                    tIFFCompressor = this.compressor;
                    if (tIFFCompressor == null) {
                    }
                    if (this.colorConverter == null) {
                    }
                    if (this.photometricInterpretation == 6) {
                    }
                    z4 = z2;
                    rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), this.photometricInterpretation));
                    int i2722 = numBands + i3;
                    this.bitsPerSample = new char[i2722];
                    this.bitDepth = 0;
                    while (i5 < numBands) {
                    }
                    i6 = this.bitDepth;
                    if (i6 == 3) {
                    }
                    i8 = 0;
                    while (true) {
                        cArr2 = this.bitsPerSample;
                        if (i8 >= cArr2.length) {
                        }
                        cArr2[i8] = (char) this.bitDepth;
                        i8++;
                    }
                    if (cArr2.length == 1) {
                    }
                    TIFFTag tag22 = baselineTIFFTagSet.getTag(258);
                    char[] cArr322 = this.bitsPerSample;
                    rootIFD.addTIFFField(new TIFFField(tag22, 3, cArr322.length, cArr322));
                    tIFFField2 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
                    if (tIFFField2 != null) {
                    }
                    if (tIFFField2 != null) {
                    }
                    if (cArr != null) {
                    }
                    rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), this.bitsPerSample.length));
                    if (this.photometricInterpretation != 3) {
                    }
                    z5 = z3;
                    rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
                    if (colorModel != null) {
                    }
                    tIFFField3 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_X_RESOLUTION);
                    TIFFField tIFFField1022 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
                    if (tIFFField3 == null) {
                    }
                    i9 = numBands;
                    if (tIFFField3 != null) {
                    }
                    if (tIFFField3 != null) {
                    }
                    rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(256), i));
                    rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(257), i2));
                    tIFFField4 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
                    if (tIFFField4 != null) {
                    }
                    min = Math.min(max, i2);
                    ImageWriteParam imageWriteParam322 = this.param;
                    if (imageWriteParam322 instanceof TIFFImageWriteParam) {
                    }
                    if (tilingMode != 0) {
                    }
                    this.tileWidth = i;
                    this.tileLength = min;
                    z6 = false;
                    if (this.compression == 7) {
                    }
                    int i3722 = this.tileWidth;
                    this.tilesAcross = ((i + i3722) - 1) / i3722;
                    int i3822 = this.tileLength;
                    this.tilesDown = ((i2 + i3822) - 1) / i3822;
                    if (!z6) {
                    }
                    if (z4) {
                    }
                }
                z2 = false;
                if (z2) {
                }
                tIFFCompressor = this.compressor;
                if (tIFFCompressor == null) {
                }
                if (this.colorConverter == null) {
                }
                if (this.photometricInterpretation == 6) {
                }
                z4 = z2;
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), this.photometricInterpretation));
                int i27222 = numBands + i3;
                this.bitsPerSample = new char[i27222];
                this.bitDepth = 0;
                while (i5 < numBands) {
                }
                i6 = this.bitDepth;
                if (i6 == 3) {
                }
                i8 = 0;
                while (true) {
                    cArr2 = this.bitsPerSample;
                    if (i8 >= cArr2.length) {
                    }
                    cArr2[i8] = (char) this.bitDepth;
                    i8++;
                }
                if (cArr2.length == 1) {
                }
                TIFFTag tag222 = baselineTIFFTagSet.getTag(258);
                char[] cArr3222 = this.bitsPerSample;
                rootIFD.addTIFFField(new TIFFField(tag222, 3, cArr3222.length, cArr3222));
                tIFFField2 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
                if (tIFFField2 != null) {
                }
                if (tIFFField2 != null) {
                }
                if (cArr != null) {
                }
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), this.bitsPerSample.length));
                if (this.photometricInterpretation != 3) {
                }
                z5 = z3;
                rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
                if (colorModel != null) {
                }
                tIFFField3 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_X_RESOLUTION);
                TIFFField tIFFField10222 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
                if (tIFFField3 == null) {
                }
                i9 = numBands;
                if (tIFFField3 != null) {
                }
                if (tIFFField3 != null) {
                }
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(256), i));
                rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(257), i2));
                tIFFField4 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
                if (tIFFField4 != null) {
                }
                min = Math.min(max, i2);
                ImageWriteParam imageWriteParam3222 = this.param;
                if (imageWriteParam3222 instanceof TIFFImageWriteParam) {
                }
                if (tilingMode != 0) {
                }
                this.tileWidth = i;
                this.tileLength = min;
                z6 = false;
                if (this.compression == 7) {
                }
                int i37222 = this.tileWidth;
                this.tilesAcross = ((i + i37222) - 1) / i37222;
                int i38222 = this.tileLength;
                this.tilesDown = ((i2 + i38222) - 1) / i38222;
                if (!z6) {
                }
                if (z4) {
                }
            }
        }
        z = false;
        int[] sampleSize2 = sampleModel.getSampleSize();
        numBands = sampleModel.getNumBands();
        if (numBands > 1) {
        }
        i3 = 0;
        cArr = null;
        if (numBands != 3) {
        }
        this.compressor = null;
        this.colorConverter = null;
        imageWriteParam = this.param;
        if (imageWriteParam instanceof TIFFImageWriteParam) {
        }
        ImageWriteParam imageWriteParam22 = this.param;
        if (!(imageWriteParam22 instanceof TIFFImageWriteParam)) {
        }
        if (compressionMode != 2) {
        }
        tIFFField = rootIFD.getTIFFField(317);
        if (tIFFField != null) {
        }
        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_COMPRESSION), this.compression));
        if (numBands == 3) {
        }
        z2 = false;
        if (z2) {
        }
        tIFFCompressor = this.compressor;
        if (tIFFCompressor == null) {
        }
        if (this.colorConverter == null) {
        }
        if (this.photometricInterpretation == 6) {
        }
        z4 = z2;
        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), this.photometricInterpretation));
        int i272222 = numBands + i3;
        this.bitsPerSample = new char[i272222];
        this.bitDepth = 0;
        while (i5 < numBands) {
        }
        i6 = this.bitDepth;
        if (i6 == 3) {
        }
        i8 = 0;
        while (true) {
            cArr2 = this.bitsPerSample;
            if (i8 >= cArr2.length) {
            }
            cArr2[i8] = (char) this.bitDepth;
            i8++;
        }
        if (cArr2.length == 1) {
        }
        TIFFTag tag2222 = baselineTIFFTagSet.getTag(258);
        char[] cArr32222 = this.bitsPerSample;
        rootIFD.addTIFFField(new TIFFField(tag2222, 3, cArr32222.length, cArr32222));
        tIFFField2 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_SAMPLE_FORMAT);
        if (tIFFField2 != null) {
        }
        if (tIFFField2 != null) {
        }
        if (cArr != null) {
        }
        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), this.bitsPerSample.length));
        if (this.photometricInterpretation != 3) {
        }
        z5 = z3;
        rootIFD.removeTIFFField(BaselineTIFFTagSet.TAG_COLOR_MAP);
        if (colorModel != null) {
        }
        tIFFField3 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_X_RESOLUTION);
        TIFFField tIFFField102222 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_Y_RESOLUTION);
        if (tIFFField3 == null) {
        }
        i9 = numBands;
        if (tIFFField3 != null) {
        }
        if (tIFFField3 != null) {
        }
        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(256), i));
        rootIFD.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(257), i2));
        tIFFField4 = rootIFD.getTIFFField(BaselineTIFFTagSet.TAG_ROWS_PER_STRIP);
        if (tIFFField4 != null) {
        }
        min = Math.min(max, i2);
        ImageWriteParam imageWriteParam32222 = this.param;
        if (imageWriteParam32222 instanceof TIFFImageWriteParam) {
        }
        if (tilingMode != 0) {
        }
        this.tileWidth = i;
        this.tileLength = min;
        z6 = false;
        if (this.compression == 7) {
        }
        int i372222 = this.tileWidth;
        this.tilesAcross = ((i + i372222) - 1) / i372222;
        int i382222 = this.tileLength;
        this.tilesDown = ((i2 + i382222) - 1) / i382222;
        if (!z6) {
        }
        if (z4) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:328:0x0734 A[LOOP:38: B:327:0x0732->B:328:0x0734, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int writeTile(Rectangle rectangle, TIFFCompressor tIFFCompressor) throws IOException {
        boolean z;
        Rectangle rectangle2;
        int i;
        int i2;
        int[] iArr;
        float[] fArr;
        int i3;
        byte[] bArr;
        int i4;
        int i5;
        int i6;
        boolean z2;
        int i7;
        Raster data;
        int i8;
        int i9;
        int i10;
        int i11;
        SampleModel sampleModel;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        SampleModel sampleModel2;
        byte[] bArr2;
        Rectangle rectangle3 = rectangle;
        Rectangle rectangle4 = new Rectangle(this.image.getMinX(), this.image.getMinY(), this.image.getWidth(), this.image.getHeight());
        int i23 = 0;
        if (!this.isTiled) {
            rectangle3 = rectangle3.intersection(rectangle4);
        } else if (!rectangle4.contains(rectangle3)) {
            Rectangle intersection = rectangle4.intersection(rectangle3);
            z = true;
            rectangle2 = rectangle3;
            rectangle3 = intersection;
            if (!rectangle3.isEmpty()) {
                return 0;
            }
            int i24 = rectangle2.x;
            int i25 = rectangle2.y;
            int i26 = rectangle2.width;
            int i27 = rectangle2.height;
            if (this.isImageSimple) {
                SampleModel sampleModel3 = this.image.getSampleModel();
                Raster data2 = this.image.getData(rectangle3);
                if (z) {
                    Raster createCompatibleWritableRaster = data2.createCompatibleWritableRaster(i24, i25, i26, i27);
                    createCompatibleWritableRaster.setRect(data2);
                    data2 = createCompatibleWritableRaster;
                }
                if (this.isBilevel) {
                    byte[] packedBinaryData = ImageUtil.getPackedBinaryData(data2, rectangle2);
                    if (this.isInverted) {
                        DataBufferByte dataBuffer = data2.getDataBuffer();
                        if ((dataBuffer instanceof DataBufferByte) && packedBinaryData == dataBuffer.getData()) {
                            byte[] bArr3 = new byte[packedBinaryData.length];
                            int length = packedBinaryData.length;
                            while (i23 < length) {
                                bArr3[i23] = (byte) (packedBinaryData[i23] ^ 255);
                                i23++;
                            }
                            bArr2 = bArr3;
                            return tIFFCompressor.encode(bArr2, 0, i26, i27, this.sampleSize, (rectangle2.width + 7) / 8);
                        }
                        int length2 = packedBinaryData.length;
                        while (i23 < length2) {
                            packedBinaryData[i23] = (byte) (packedBinaryData[i23] ^ 255);
                            i23++;
                        }
                    }
                    bArr2 = packedBinaryData;
                    return tIFFCompressor.encode(bArr2, 0, i26, i27, this.sampleSize, (rectangle2.width + 7) / 8);
                }
                if (this.bitDepth == 8 && sampleModel3.getDataType() == 0) {
                    ComponentSampleModel sampleModel4 = data2.getSampleModel();
                    return tIFFCompressor.encode(data2.getDataBuffer().getData(), sampleModel4.getOffset(i24 - data2.getSampleModelTranslateX(), i25 - data2.getSampleModelTranslateY()), i26, i27, this.sampleSize, sampleModel4.getScanlineStride());
                }
            }
            int i28 = this.periodX;
            int i29 = this.periodY;
            int i30 = ((i26 + i28) - 1) / i28;
            int i31 = ((i27 + i29) - 1) / i29;
            if (i30 == 0 || i31 == 0) {
                return 0;
            }
            int i32 = this.numBands;
            int i33 = i28 * i32;
            int i34 = this.bitDepth;
            int i35 = 8 / i34;
            int i36 = i26 * i32;
            int i37 = i32 * i30;
            int i38 = i30;
            if (i34 < 8) {
                i2 = ((i37 + i35) - 1) / i35;
            } else {
                if (i34 != 16) {
                    if (i34 == 32) {
                        i37 *= 4;
                    }
                    i = i37;
                    if (this.sampleFormat != 3) {
                        fArr = new float[i36];
                        iArr = null;
                    } else {
                        iArr = new int[i36];
                        fArr = null;
                    }
                    byte[] bArr4 = new byte[i * i31];
                    if (!this.isInverted && !this.isRescaling && this.sourceBands == null && i28 == 1 && i29 == 1 && this.colorConverter == null) {
                        sampleModel2 = this.image.getSampleModel();
                        if (sampleModel2 instanceof ComponentSampleModel) {
                            if (this.bitDepth == 8 && sampleModel2.getDataType() == 0) {
                                Raster data3 = this.image.getData(rectangle3);
                                if (z) {
                                    Raster createCompatibleWritableRaster2 = data3.createCompatibleWritableRaster(i24, i25, i26, i27);
                                    createCompatibleWritableRaster2.setRect(data3);
                                    data3 = createCompatibleWritableRaster2;
                                }
                                ComponentSampleModel sampleModel5 = data3.getSampleModel();
                                int[] bankIndices = sampleModel5.getBankIndices();
                                byte[][] bankData = data3.getDataBuffer().getBankData();
                                int scanlineStride = sampleModel5.getScanlineStride();
                                int pixelStride = sampleModel5.getPixelStride();
                                int i39 = 0;
                                while (true) {
                                    int i40 = this.numBands;
                                    if (i39 < i40) {
                                        byte[] bArr5 = bankData[bankIndices[i39]];
                                        int offset = sampleModel5.getOffset(data3.getMinX() - data3.getSampleModelTranslateX(), data3.getMinY() - data3.getSampleModelTranslateY(), i39);
                                        int i41 = i39;
                                        int i42 = 0;
                                        while (i42 < i31) {
                                            ComponentSampleModel componentSampleModel = sampleModel5;
                                            int i43 = offset;
                                            int i44 = i38;
                                            Raster raster = data3;
                                            int i45 = 0;
                                            while (i45 < i44) {
                                                bArr4[i41] = bArr5[i43];
                                                i41 += this.numBands;
                                                i43 += pixelStride;
                                                i45++;
                                                i44 = i44;
                                            }
                                            int i46 = i44;
                                            offset += scanlineStride;
                                            i42++;
                                            data3 = raster;
                                            sampleModel5 = componentSampleModel;
                                            i38 = i46;
                                        }
                                        i39++;
                                        i38 = i38;
                                    } else {
                                        return tIFFCompressor.encode(bArr4, 0, i26, i27, this.sampleSize, i26 * i40);
                                    }
                                }
                            } else {
                                i3 = i38;
                                bArr = bArr4;
                                int i47 = rectangle3.x;
                                int i48 = rectangle3.y;
                                int i49 = (rectangle3.height + i48) - 1;
                                int i50 = rectangle3.width;
                                SampleModel createCompatibleSampleModel = z ? this.image.getSampleModel().createCompatibleSampleModel(i26, 1) : null;
                                i4 = i25;
                                int i51 = 0;
                                while (i4 < i25 + i27) {
                                    if (z) {
                                        Raster createWritableRaster = Raster.createWritableRaster(createCompatibleSampleModel, new Point(i24, i4));
                                        if (i4 < i48 || i4 > i49) {
                                            z2 = z;
                                            i7 = i48;
                                        } else {
                                            z2 = z;
                                            i7 = i48;
                                            createWritableRaster.setRect(this.image.getData(new Rectangle(i47, i4, i50, 1)));
                                        }
                                        data = createWritableRaster;
                                    } else {
                                        z2 = z;
                                        i7 = i48;
                                        data = this.image.getData(new Rectangle(i24, i4, i26, 1));
                                    }
                                    int[] iArr2 = this.sourceBands;
                                    if (iArr2 != null) {
                                        i9 = i47;
                                        i10 = i4;
                                        i8 = i50;
                                        i11 = i49;
                                        sampleModel = createCompatibleSampleModel;
                                        i14 = 3;
                                        i12 = i27;
                                        i13 = i26;
                                        data = data.createChild(i24, i4, i26, 1, i24, i10, iArr2);
                                    } else {
                                        i8 = i50;
                                        i9 = i47;
                                        i10 = i4;
                                        i11 = i49;
                                        sampleModel = createCompatibleSampleModel;
                                        i12 = i27;
                                        i13 = i26;
                                        i14 = 3;
                                    }
                                    if (this.sampleFormat == i14) {
                                        data.getPixels(i24, i10, i13, 1, fArr);
                                    } else {
                                        data.getPixels(i24, i10, i13, 1, iArr);
                                        int i52 = this.nativePhotometricInterpretation;
                                        if ((i52 == 1 && this.photometricInterpretation == 0) || (i52 == 0 && this.photometricInterpretation == 1)) {
                                            int i53 = (1 << this.bitDepth) - 1;
                                            for (int i54 = 0; i54 < i36; i54++) {
                                                iArr[i54] = iArr[i54] ^ i53;
                                            }
                                        }
                                    }
                                    if (this.colorConverter != null) {
                                        float[] fArr2 = new float[i14];
                                        if (this.sampleFormat == i14) {
                                            i15 = i13;
                                            int i55 = 0;
                                            for (int i56 = 0; i56 < i15; i56++) {
                                                int i57 = i55 + 1;
                                                int i58 = i55 + 2;
                                                this.colorConverter.fromRGB(fArr[i55], fArr[i57], fArr[i58], fArr2);
                                                fArr[i55] = fArr2[0];
                                                fArr[i57] = fArr2[1];
                                                fArr[i58] = fArr2[2];
                                                i55 += 3;
                                            }
                                        } else {
                                            i15 = i13;
                                            int i59 = 0;
                                            for (int i60 = 0; i60 < i15; i60++) {
                                                this.colorConverter.fromRGB(iArr[i59], iArr[r9], iArr[r12], fArr2);
                                                iArr[i59] = (int) fArr2[0];
                                                iArr[i59 + 1] = (int) fArr2[1];
                                                iArr[i59 + 2] = (int) fArr2[2];
                                                i59 += 3;
                                            }
                                        }
                                    } else {
                                        i15 = i13;
                                    }
                                    int i61 = this.bitDepth;
                                    if (i61 == 1) {
                                        i16 = i35;
                                        i17 = i24;
                                        i18 = i15;
                                    } else if (i61 == 2 || i61 == 4) {
                                        i16 = i35;
                                        i17 = i24;
                                        i18 = i15;
                                    } else {
                                        if (i61 != 8) {
                                            if (i61 != 16) {
                                                if (i61 == 32) {
                                                    int i62 = -16777216;
                                                    if (this.sampleFormat == 3) {
                                                        if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
                                                            int i63 = 0;
                                                            while (i63 < i36) {
                                                                int i64 = 0;
                                                                while (i64 < this.numBands) {
                                                                    int floatToIntBits = Float.floatToIntBits(fArr[i63 + i64]);
                                                                    int i65 = i51 + 1;
                                                                    bArr[i51] = (byte) ((floatToIntBits & i62) >> 24);
                                                                    int i66 = i65 + 1;
                                                                    bArr[i65] = (byte) ((floatToIntBits & 16711680) >> 16);
                                                                    int i67 = i66 + 1;
                                                                    bArr[i66] = (byte) ((floatToIntBits & 65280) >> 8);
                                                                    i51 = i67 + 1;
                                                                    bArr[i67] = (byte) (floatToIntBits & 255);
                                                                    i64++;
                                                                    i62 = -16777216;
                                                                }
                                                                i63 += i33;
                                                                i62 = -16777216;
                                                            }
                                                        } else {
                                                            for (int i68 = 0; i68 < i36; i68 += i33) {
                                                                for (int i69 = 0; i69 < this.numBands; i69++) {
                                                                    int floatToIntBits2 = Float.floatToIntBits(fArr[i68 + i69]);
                                                                    int i70 = i51 + 1;
                                                                    bArr[i51] = (byte) (floatToIntBits2 & 255);
                                                                    int i71 = i70 + 1;
                                                                    bArr[i70] = (byte) ((floatToIntBits2 & 65280) >> 8);
                                                                    int i72 = i71 + 1;
                                                                    bArr[i71] = (byte) ((floatToIntBits2 & 16711680) >> 16);
                                                                    i51 = i72 + 1;
                                                                    bArr[i72] = (byte) ((floatToIntBits2 & (-16777216)) >> 24);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (this.isRescaling) {
                                                            int i73 = this.numBands;
                                                            long[] jArr = new long[i73];
                                                            long[] jArr2 = new long[i73];
                                                            long j = (1 << i61) - 1;
                                                            int i74 = 0;
                                                            while (i74 < this.numBands) {
                                                                jArr[i74] = (1 << this.sampleSize[i74]) - 1;
                                                                jArr2[i74] = jArr[i74] / 2;
                                                                i74++;
                                                                i35 = i35;
                                                            }
                                                            i22 = i35;
                                                            if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
                                                                for (int i75 = 0; i75 < i36; i75 += i33) {
                                                                    int i76 = 0;
                                                                    while (i76 < this.numBands) {
                                                                        long j2 = ((iArr[i75 + i76] * j) + jArr2[i76]) / jArr[i76];
                                                                        int i77 = i51 + 1;
                                                                        int i78 = i24;
                                                                        bArr[i51] = (byte) ((j2 & (-16777216)) >> 24);
                                                                        int i79 = i77 + 1;
                                                                        bArr[i77] = (byte) ((j2 & 16711680) >> 16);
                                                                        int i80 = i79 + 1;
                                                                        bArr[i79] = (byte) ((j2 & 65280) >> 8);
                                                                        i51 = i80 + 1;
                                                                        bArr[i80] = (byte) (j2 & 255);
                                                                        i76++;
                                                                        jArr2 = jArr2;
                                                                        i24 = i78;
                                                                        i15 = i15;
                                                                    }
                                                                }
                                                                i17 = i24;
                                                                i18 = i15;
                                                            } else {
                                                                i17 = i24;
                                                                i18 = i15;
                                                                for (int i81 = 0; i81 < i36; i81 += i33) {
                                                                    for (int i82 = 0; i82 < this.numBands; i82++) {
                                                                        long j3 = ((iArr[i81 + i82] * j) + jArr2[i82]) / jArr[i82];
                                                                        int i83 = i51 + 1;
                                                                        bArr[i51] = (byte) (j3 & 255);
                                                                        int i84 = i83 + 1;
                                                                        bArr[i83] = (byte) ((j3 & 65280) >> 8);
                                                                        int i85 = i84 + 1;
                                                                        bArr[i84] = (byte) ((j3 & 16711680) >> 16);
                                                                        i51 = i85 + 1;
                                                                        bArr[i85] = (byte) ((j3 & (-16777216)) >> 24);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            i22 = i35;
                                                            i17 = i24;
                                                            i18 = i15;
                                                            if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
                                                                for (int i86 = 0; i86 < i36; i86 += i33) {
                                                                    for (int i87 = 0; i87 < this.numBands; i87++) {
                                                                        int i88 = iArr[i86 + i87];
                                                                        int i89 = i51 + 1;
                                                                        bArr[i51] = (byte) ((i88 & (-16777216)) >> 24);
                                                                        int i90 = i89 + 1;
                                                                        bArr[i89] = (byte) ((i88 & 16711680) >> 16);
                                                                        int i91 = i90 + 1;
                                                                        bArr[i90] = (byte) ((i88 & 65280) >> 8);
                                                                        i51 = i91 + 1;
                                                                        bArr[i91] = (byte) (i88 & 255);
                                                                    }
                                                                }
                                                            } else {
                                                                for (int i92 = 0; i92 < i36; i92 += i33) {
                                                                    for (int i93 = 0; i93 < this.numBands; i93++) {
                                                                        int i94 = iArr[i92 + i93];
                                                                        int i95 = i51 + 1;
                                                                        bArr[i51] = (byte) (i94 & 255);
                                                                        int i96 = i95 + 1;
                                                                        bArr[i95] = (byte) ((i94 & 65280) >> 8);
                                                                        int i97 = i96 + 1;
                                                                        bArr[i96] = (byte) ((i94 & 16711680) >> 16);
                                                                        i51 = i97 + 1;
                                                                        bArr[i97] = (byte) ((i94 & (-16777216)) >> 24);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        i19 = i22;
                                                        i4 = i10 + i29;
                                                        i35 = i19;
                                                        i49 = i11;
                                                        createCompatibleSampleModel = sampleModel;
                                                        z = z2;
                                                        i48 = i7;
                                                        i47 = i9;
                                                        i27 = i12;
                                                        i24 = i17;
                                                        i26 = i18;
                                                        i50 = i8;
                                                    }
                                                }
                                                i19 = i35;
                                                i17 = i24;
                                                i18 = i15;
                                                i4 = i10 + i29;
                                                i35 = i19;
                                                i49 = i11;
                                                createCompatibleSampleModel = sampleModel;
                                                z = z2;
                                                i48 = i7;
                                                i47 = i9;
                                                i27 = i12;
                                                i24 = i17;
                                                i26 = i18;
                                                i50 = i8;
                                            } else {
                                                i22 = i35;
                                                i17 = i24;
                                                i18 = i15;
                                                if (this.isRescaling) {
                                                    if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
                                                        for (int i98 = 0; i98 < i36; i98 += i33) {
                                                            for (int i99 = 0; i99 < this.numBands; i99++) {
                                                                int i100 = iArr[i98 + i99];
                                                                int i101 = i51 + 1;
                                                                bArr[i51] = this.scaleh[i99][i100];
                                                                i51 = i101 + 1;
                                                                bArr[i101] = this.scalel[i99][i100];
                                                            }
                                                        }
                                                    } else {
                                                        for (int i102 = 0; i102 < i36; i102 += i33) {
                                                            for (int i103 = 0; i103 < this.numBands; i103++) {
                                                                int i104 = iArr[i102 + i103];
                                                                int i105 = i51 + 1;
                                                                bArr[i51] = this.scalel[i103][i104];
                                                                i51 = i105 + 1;
                                                                bArr[i105] = this.scaleh[i103][i104];
                                                            }
                                                        }
                                                    }
                                                } else if (this.stream.getByteOrder() == ByteOrder.BIG_ENDIAN) {
                                                    for (int i106 = 0; i106 < i36; i106 += i33) {
                                                        for (int i107 = 0; i107 < this.numBands; i107++) {
                                                            int i108 = iArr[i106 + i107];
                                                            int i109 = i51 + 1;
                                                            bArr[i51] = (byte) ((i108 >>> 8) & 255);
                                                            i51 = i109 + 1;
                                                            bArr[i109] = (byte) (i108 & 255);
                                                        }
                                                    }
                                                } else {
                                                    for (int i110 = 0; i110 < i36; i110 += i33) {
                                                        for (int i111 = 0; i111 < this.numBands; i111++) {
                                                            int i112 = iArr[i110 + i111];
                                                            int i113 = i51 + 1;
                                                            bArr[i51] = (byte) (i112 & 255);
                                                            i51 = i113 + 1;
                                                            bArr[i113] = (byte) ((i112 >>> 8) & 255);
                                                        }
                                                    }
                                                }
                                            }
                                            i19 = i22;
                                        } else {
                                            int i114 = i35;
                                            i17 = i24;
                                            i18 = i15;
                                            if (this.numBands == 1) {
                                                if (this.isRescaling) {
                                                    int i115 = 0;
                                                    while (i115 < i36) {
                                                        bArr[i51] = this.scale0[iArr[i115]];
                                                        i115 += i33;
                                                        i51++;
                                                    }
                                                } else {
                                                    int i116 = 0;
                                                    while (i116 < i36) {
                                                        bArr[i51] = (byte) iArr[i116];
                                                        i116 += i33;
                                                        i51++;
                                                    }
                                                }
                                            } else if (this.isRescaling) {
                                                for (int i117 = 0; i117 < i36; i117 += i33) {
                                                    int i118 = 0;
                                                    while (i118 < this.numBands) {
                                                        bArr[i51] = this.scale[i118][iArr[i117 + i118]];
                                                        i118++;
                                                        i51++;
                                                    }
                                                }
                                            } else {
                                                for (int i119 = 0; i119 < i36; i119 += i33) {
                                                    int i120 = 0;
                                                    while (i120 < this.numBands) {
                                                        bArr[i51] = (byte) iArr[i119 + i120];
                                                        i120++;
                                                        i51++;
                                                    }
                                                }
                                            }
                                            i19 = i114;
                                        }
                                        i4 = i10 + i29;
                                        i35 = i19;
                                        i49 = i11;
                                        createCompatibleSampleModel = sampleModel;
                                        z = z2;
                                        i48 = i7;
                                        i47 = i9;
                                        i27 = i12;
                                        i24 = i17;
                                        i26 = i18;
                                        i50 = i8;
                                    }
                                    if (this.isRescaling) {
                                        int i121 = 0;
                                        i21 = 0;
                                        i20 = 0;
                                        while (i121 < i36) {
                                            i20 = (i20 << this.bitDepth) | this.scale0[iArr[i121]];
                                            i21++;
                                            int i122 = i16;
                                            if (i21 == i122) {
                                                bArr[i51] = (byte) i20;
                                                i51++;
                                                i21 = 0;
                                                i20 = 0;
                                            }
                                            i121 += i33;
                                            i16 = i122;
                                        }
                                        i19 = i16;
                                    } else {
                                        i19 = i16;
                                        int i123 = 0;
                                        int i124 = 0;
                                        for (int i125 = 0; i125 < i36; i125 += i33) {
                                            i123 = (i123 << this.bitDepth) | ((byte) iArr[i125]);
                                            i124++;
                                            if (i124 == i19) {
                                                bArr[i51] = (byte) i123;
                                                i51++;
                                                i123 = 0;
                                                i124 = 0;
                                            }
                                        }
                                        int i126 = i124;
                                        i20 = i123;
                                        i21 = i126;
                                    }
                                    if (i21 != 0) {
                                        int i127 = this.bitDepth;
                                        bArr[i51] = (byte) (i20 << (((8 / i127) - i21) * i127));
                                        i51++;
                                        i4 = i10 + i29;
                                        i35 = i19;
                                        i49 = i11;
                                        createCompatibleSampleModel = sampleModel;
                                        z = z2;
                                        i48 = i7;
                                        i47 = i9;
                                        i27 = i12;
                                        i24 = i17;
                                        i26 = i18;
                                        i50 = i8;
                                    }
                                    i4 = i10 + i29;
                                    i35 = i19;
                                    i49 = i11;
                                    createCompatibleSampleModel = sampleModel;
                                    z = z2;
                                    i48 = i7;
                                    i47 = i9;
                                    i27 = i12;
                                    i24 = i17;
                                    i26 = i18;
                                    i50 = i8;
                                }
                                i5 = this.numBands;
                                int[] iArr3 = new int[i5];
                                for (i6 = 0; i6 < i5; i6++) {
                                    iArr3[i6] = this.bitDepth;
                                }
                                return tIFFCompressor.encode(bArr, 0, i3, i31, iArr3, i);
                            }
                        }
                    }
                    i3 = i38;
                    bArr = bArr4;
                    int i472 = rectangle3.x;
                    int i482 = rectangle3.y;
                    int i492 = (rectangle3.height + i482) - 1;
                    int i502 = rectangle3.width;
                    if (z) {
                    }
                    i4 = i25;
                    int i512 = 0;
                    while (i4 < i25 + i27) {
                    }
                    i5 = this.numBands;
                    int[] iArr32 = new int[i5];
                    while (i6 < i5) {
                    }
                    return tIFFCompressor.encode(bArr, 0, i3, i31, iArr32, i);
                }
                i2 = i37 * 2;
            }
            i = i2;
            if (this.sampleFormat != 3) {
            }
            byte[] bArr42 = new byte[i * i31];
            if (!this.isInverted) {
                sampleModel2 = this.image.getSampleModel();
                if (sampleModel2 instanceof ComponentSampleModel) {
                }
            }
            i3 = i38;
            bArr = bArr42;
            int i4722 = rectangle3.x;
            int i4822 = rectangle3.y;
            int i4922 = (rectangle3.height + i4822) - 1;
            int i5022 = rectangle3.width;
            if (z) {
            }
            i4 = i25;
            int i5122 = 0;
            while (i4 < i25 + i27) {
            }
            i5 = this.numBands;
            int[] iArr322 = new int[i5];
            while (i6 < i5) {
            }
            return tIFFCompressor.encode(bArr, 0, i3, i31, iArr322, i);
        }
        rectangle2 = rectangle3;
        z = false;
        if (!rectangle3.isEmpty()) {
        }
    }

    private boolean equals(int[] iArr, int[] iArr2) {
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

    private void initializeScaleTables(int[] iArr) {
        if (this.bitDepth == this.scalingBitDepth && equals(iArr, this.sampleSize)) {
            return;
        }
        this.isRescaling = false;
        this.scalingBitDepth = -1;
        this.scaleh = null;
        this.scalel = null;
        this.scale = null;
        this.scale0 = null;
        this.sampleSize = iArr;
        if (this.bitDepth <= 16) {
            int i = 0;
            while (true) {
                if (i >= this.numBands) {
                    break;
                }
                if (iArr[i] != this.bitDepth) {
                    this.isRescaling = true;
                    break;
                }
                i++;
            }
        }
        if (this.isRescaling) {
            int i2 = this.bitDepth;
            this.scalingBitDepth = i2;
            int i3 = (1 << i2) - 1;
            if (i2 <= 8) {
                this.scale = new byte[this.numBands];
                for (int i4 = 0; i4 < this.numBands; i4++) {
                    int i5 = (1 << iArr[i4]) - 1;
                    int i6 = i5 / 2;
                    this.scale[i4] = new byte[i5 + 1];
                    for (int i7 = 0; i7 <= i5; i7++) {
                        this.scale[i4][i7] = (byte) (((i7 * i3) + i6) / i5);
                    }
                }
                this.scale0 = this.scale[0];
                this.scalel = null;
                this.scaleh = null;
                return;
            }
            if (i2 <= 16) {
                int i8 = this.numBands;
                this.scaleh = new byte[i8];
                this.scalel = new byte[i8];
                for (int i9 = 0; i9 < this.numBands; i9++) {
                    int i10 = (1 << iArr[i9]) - 1;
                    int i11 = i10 / 2;
                    int i12 = i10 + 1;
                    this.scaleh[i9] = new byte[i12];
                    this.scalel[i9] = new byte[i12];
                    for (int i13 = 0; i13 <= i10; i13++) {
                        int i14 = ((i13 * i3) + i11) / i10;
                        this.scaleh[i9][i13] = (byte) (i14 >> 8);
                        this.scalel[i9][i13] = (byte) (i14 & 255);
                    }
                }
                this.scale = null;
                this.scale0 = null;
            }
        }
    }

    public void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        write(iIOMetadata, iIOImage, imageWriteParam, true, true);
    }

    private void writeHeader() throws IOException {
        TIFFStreamMetadata tIFFStreamMetadata = this.streamMetadata;
        if (tIFFStreamMetadata != null) {
            this.byteOrder = tIFFStreamMetadata.byteOrder;
        } else {
            this.byteOrder = ByteOrder.BIG_ENDIAN;
        }
        this.stream.setByteOrder(this.byteOrder);
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            this.stream.writeShort(19789);
        } else {
            this.stream.writeShort(18761);
        }
        this.stream.writeShort(42);
        this.stream.writeInt(0);
        long streamPosition = this.stream.getStreamPosition();
        this.nextSpace = streamPosition;
        this.headerPosition = streamPosition - 8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x00e6, code lost:
    
        if (r2.getNumComponents() == r16.numBands) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void write(IIOMetadata iIOMetadata, IIOImage iIOImage, ImageWriteParam imageWriteParam, boolean z, boolean z2) throws IOException {
        ColorModel colorModel;
        if (this.stream == null) {
            throw new IllegalStateException("output == null!");
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException("image == null!");
        }
        if (iIOImage.hasRaster() && !canWriteRasters()) {
            throw new UnsupportedOperationException("TIFF ImageWriter cannot write Rasters!");
        }
        RenderedImage renderedImage = iIOImage.getRenderedImage();
        this.image = renderedImage;
        SampleModel sampleModel = renderedImage.getSampleModel();
        this.sourceXOffset = this.image.getMinX();
        this.sourceYOffset = this.image.getMinY();
        this.sourceWidth = this.image.getWidth();
        this.sourceHeight = this.image.getHeight();
        Rectangle rectangle = new Rectangle(this.sourceXOffset, this.sourceYOffset, this.sourceWidth, this.sourceHeight);
        if (imageWriteParam == null) {
            this.param = getDefaultWriteParam();
            this.sourceBands = null;
            this.periodX = 1;
            this.periodY = 1;
            this.numBands = sampleModel.getNumBands();
            colorModel = this.image.getColorModel();
        } else {
            this.param = imageWriteParam;
            Rectangle sourceRegion = imageWriteParam.getSourceRegion();
            if (sourceRegion != null) {
                Rectangle intersection = sourceRegion.intersection(rectangle);
                this.sourceXOffset = intersection.x;
                this.sourceYOffset = intersection.y;
                this.sourceWidth = intersection.width;
                this.sourceHeight = intersection.height;
            }
            int subsamplingXOffset = this.param.getSubsamplingXOffset();
            int subsamplingYOffset = this.param.getSubsamplingYOffset();
            this.sourceXOffset += subsamplingXOffset;
            this.sourceYOffset += subsamplingYOffset;
            this.sourceWidth -= subsamplingXOffset;
            this.sourceHeight -= subsamplingYOffset;
            this.periodX = this.param.getSourceXSubsampling();
            this.periodY = this.param.getSourceYSubsampling();
            int[] sourceBands = this.param.getSourceBands();
            if (sourceBands != null) {
                this.sourceBands = sourceBands;
                this.numBands = sourceBands.length;
            } else {
                this.numBands = sampleModel.getNumBands();
            }
            ImageTypeSpecifier destinationType = imageWriteParam.getDestinationType();
            if (destinationType != null) {
                colorModel = destinationType.getColorModel();
            }
            colorModel = null;
            if (colorModel == null) {
                colorModel = this.image.getColorModel();
            }
        }
        ImageTypeSpecifier imageTypeSpecifier = new ImageTypeSpecifier(colorModel, sampleModel);
        this.imageType = imageTypeSpecifier;
        ImageUtil.canEncodeImage(this, imageTypeSpecifier);
        int i = this.sourceWidth;
        int i2 = this.periodX;
        int i3 = ((i + i2) - 1) / i2;
        int i4 = this.sourceHeight;
        int i5 = this.periodY;
        int i6 = ((i4 + i5) - 1) / i5;
        if (i3 <= 0 || i6 <= 0) {
            throw new IllegalArgumentException("Empty source region!");
        }
        clearAbortRequest();
        int i7 = 0;
        processImageStarted(0);
        long j = 4;
        if (z) {
            this.streamMetadata = null;
            if (iIOMetadata != null) {
                this.streamMetadata = (TIFFStreamMetadata) convertStreamMetadata(iIOMetadata, this.param);
            }
            if (this.streamMetadata == null) {
                this.streamMetadata = (TIFFStreamMetadata) getDefaultStreamMetadata(this.param);
            }
            writeHeader();
            this.stream.seek(this.headerPosition + 4);
            long j2 = (this.nextSpace + 3) & (-4);
            this.nextSpace = j2;
            this.stream.writeInt((int) j2);
        }
        this.imageMetadata = null;
        IIOMetadata metadata = iIOImage.getMetadata();
        if (metadata != null) {
            if (metadata instanceof TIFFImageMetadata) {
                this.imageMetadata = ((TIFFImageMetadata) metadata).getShallowClone();
            } else if (Arrays.asList(metadata.getMetadataFormatNames()).contains(TIFFImageMetadata.nativeMetadataFormatName)) {
                this.imageMetadata = convertNativeImageMetadata(metadata);
            } else if (metadata.isStandardMetadataFormatSupported()) {
                try {
                    this.imageMetadata = convertStandardImageMetadata(metadata);
                } catch (IIOInvalidTreeException unused) {
                }
            }
        }
        if (this.imageMetadata == null) {
            this.imageMetadata = (TIFFImageMetadata) getDefaultImageMetadata(this.imageType, this.param);
        }
        setupMetadata(colorModel, sampleModel, i3, i6);
        this.compressor.setWriter(this);
        this.compressor.setMetadata(this.imageMetadata);
        this.compressor.setStream(this.stream);
        sampleModel.getSampleSize();
        initializeScaleTables(sampleModel.getSampleSize());
        boolean isBinary = ImageUtil.isBinary(this.image.getSampleModel());
        this.isBilevel = isBinary;
        int i8 = this.nativePhotometricInterpretation;
        boolean z3 = (i8 == 1 && this.photometricInterpretation == 0) || (i8 == 0 && this.photometricInterpretation == 1);
        this.isInverted = z3;
        this.isImageSimple = (isBinary || (!z3 && ImageUtil.imageIsContiguous(this.image))) && !this.isRescaling && this.sourceBands == null && this.periodX == 1 && this.periodY == 1 && this.colorConverter == null;
        TIFFIFD rootIFD = this.imageMetadata.getRootIFD();
        rootIFD.writeToStream(this.stream);
        this.nextIFDPointerPos = this.stream.getStreamPosition();
        this.stream.writeInt(0);
        long lastPosition = rootIFD.getLastPosition();
        this.stream.seek(lastPosition);
        if (lastPosition > this.nextSpace) {
            this.nextSpace = lastPosition;
        }
        if (z2) {
            long stripOrTileByteCountsPosition = rootIFD.getStripOrTileByteCountsPosition();
            long stripOrTileOffsetsPosition = rootIFD.getStripOrTileOffsetsPosition();
            this.totalPixels = this.tileWidth * this.tileLength * this.tilesDown * this.tilesAcross;
            this.pixelsDone = 0;
            int i9 = 0;
            while (i9 < this.tilesDown) {
                int i10 = i7;
                while (i10 < this.tilesAcross) {
                    long streamPosition = this.stream.getStreamPosition();
                    int i11 = this.sourceXOffset;
                    int i12 = this.tileWidth;
                    int i13 = this.periodX;
                    int i14 = this.sourceYOffset;
                    int i15 = this.tileLength;
                    int i16 = i9 * i15;
                    int i17 = i9;
                    int i18 = this.periodY;
                    Rectangle rectangle2 = new Rectangle(i11 + (i10 * i12 * i13), i14 + (i16 * i18), i12 * i13, i15 * i18);
                    try {
                        int writeTile = writeTile(rectangle2, this.compressor);
                        long j3 = writeTile + streamPosition;
                        if (j3 > this.nextSpace) {
                            this.nextSpace = j3;
                        }
                        int i19 = this.pixelsDone + (rectangle2.width * rectangle2.height);
                        this.pixelsDone = i19;
                        processImageProgress((i19 * 100.0f) / this.totalPixels);
                        this.stream.mark();
                        this.stream.seek(stripOrTileOffsetsPosition);
                        this.stream.writeInt((int) streamPosition);
                        stripOrTileOffsetsPosition += 4;
                        this.stream.seek(stripOrTileByteCountsPosition);
                        this.stream.writeInt(writeTile);
                        stripOrTileByteCountsPosition += 4;
                        this.stream.reset();
                        if (abortRequested()) {
                            processWriteAborted();
                            return;
                        } else {
                            i10++;
                            i9 = i17;
                            j = 4;
                        }
                    } catch (IOException e) {
                        throw new IIOException("I/O error writing TIFF file!", e);
                    }
                }
                i9++;
                i7 = 0;
            }
            processImageComplete();
        }
    }

    public void prepareWriteSequence(IIOMetadata iIOMetadata) throws IOException {
        if (getOutput() == null) {
            throw new IllegalStateException("getOutput() == null!");
        }
        if (iIOMetadata != null) {
            iIOMetadata = convertStreamMetadata(iIOMetadata, null);
        }
        if (iIOMetadata == null) {
            iIOMetadata = getDefaultStreamMetadata(null);
        }
        this.streamMetadata = (TIFFStreamMetadata) iIOMetadata;
        writeHeader();
        this.isWritingSequence = true;
    }

    public void writeToSequence(IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        if (!this.isWritingSequence) {
            throw new IllegalStateException("prepareWriteSequence() has not been called!");
        }
        writeInsert(-1, iIOImage, imageWriteParam);
    }

    public void endWriteSequence() throws IOException {
        if (getOutput() == null) {
            throw new IllegalStateException("getOutput() == null!");
        }
        if (!this.isWritingSequence) {
            throw new IllegalStateException("prepareWriteSequence() has not been called!");
        }
        this.isWritingSequence = false;
    }

    public boolean canInsertImage(int i) throws IOException {
        if (getOutput() == null) {
            throw new IllegalStateException("getOutput() == null!");
        }
        this.stream.mark();
        locateIFD(i, new long[1], new long[1]);
        this.stream.reset();
        return true;
    }

    private void locateIFD(int i, long[] jArr, long[] jArr2) throws IOException {
        if (i < -1) {
            throw new IndexOutOfBoundsException("imageIndex < -1!");
        }
        long streamPosition = this.stream.getStreamPosition();
        this.stream.seek(this.headerPosition);
        int readUnsignedShort = this.stream.readUnsignedShort();
        if (readUnsignedShort == 19789) {
            this.stream.setByteOrder(ByteOrder.BIG_ENDIAN);
        } else if (readUnsignedShort == 18761) {
            this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.stream.seek(streamPosition);
            throw new IIOException("Illegal byte order");
        }
        if (this.stream.readUnsignedShort() != 42) {
            this.stream.seek(streamPosition);
            throw new IIOException("Illegal magic number");
        }
        jArr[0] = this.stream.getStreamPosition();
        jArr2[0] = this.stream.readUnsignedInt();
        if (jArr2[0] == 0) {
            if (i <= 0) {
                return;
            }
            this.stream.seek(streamPosition);
            throw new IndexOutOfBoundsException("imageIndex is greater than the largest available index!");
        }
        this.stream.seek(jArr2[0]);
        int i2 = 0;
        while (true) {
            if (i != -1 && i2 >= i) {
                return;
            }
            try {
                this.stream.skipBytes(this.stream.readShort() * 12);
                jArr[0] = this.stream.getStreamPosition();
                jArr2[0] = this.stream.readUnsignedInt();
                if (jArr2[0] == 0) {
                    if (i == -1 || i2 >= i - 1) {
                        return;
                    }
                    this.stream.seek(streamPosition);
                    throw new IndexOutOfBoundsException("imageIndex is greater than the largest available index!");
                }
                this.stream.seek(jArr2[0]);
                i2++;
            } catch (EOFException unused) {
                this.stream.seek(streamPosition);
                jArr2[0] = 0;
                return;
            }
        }
    }

    public void writeInsert(int i, IIOImage iIOImage, ImageWriteParam imageWriteParam) throws IOException {
        insert(i, iIOImage, imageWriteParam, true);
    }

    private void insert(int i, IIOImage iIOImage, ImageWriteParam imageWriteParam, boolean z) throws IOException {
        if (this.stream == null) {
            throw new IllegalStateException("Output not set!");
        }
        if (iIOImage == null) {
            throw new IllegalArgumentException("image == null!");
        }
        long[] jArr = new long[1];
        long[] jArr2 = new long[1];
        locateIFD(i, jArr, jArr2);
        this.stream.seek(jArr[0]);
        if (jArr[0] + 4 > this.nextSpace) {
            this.nextSpace = jArr[0] + 4;
        }
        long j = (this.nextSpace + 3) & (-4);
        this.nextSpace = j;
        this.stream.writeInt((int) j);
        this.stream.seek(this.nextSpace);
        write(null, iIOImage, imageWriteParam, false, z);
        this.stream.seek(this.nextIFDPointerPos);
        this.stream.writeInt((int) jArr2[0]);
    }

    private boolean isEncodingEmpty() {
        return this.isInsertingEmpty || this.isWritingEmpty;
    }

    public boolean canInsertEmpty(int i) throws IOException {
        return canInsertImage(i);
    }

    public boolean canWriteEmpty() throws IOException {
        if (getOutput() != null) {
            return true;
        }
        throw new IllegalStateException("getOutput() == null!");
    }

    private void checkParamsEmpty(ImageTypeSpecifier imageTypeSpecifier, int i, int i2, List list) {
        if (getOutput() == null) {
            throw new IllegalStateException("getOutput() == null!");
        }
        if (imageTypeSpecifier == null) {
            throw new IllegalArgumentException("imageType == null!");
        }
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("width < 1 || height < 1!");
        }
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = list.get(i3);
                if (obj == null || !(obj instanceof BufferedImage)) {
                    throw new IllegalArgumentException("thumbnails contains null references or objects other than BufferedImages!");
                }
            }
        }
        if (this.isInsertingEmpty) {
            throw new IllegalStateException("Previous call to prepareInsertEmpty() without corresponding call to endInsertEmpty()!");
        }
        if (this.isWritingEmpty) {
            throw new IllegalStateException("Previous call to prepareWriteEmpty() without corresponding call to endWriteEmpty()!");
        }
    }

    public void prepareInsertEmpty(int i, ImageTypeSpecifier imageTypeSpecifier, int i2, int i3, IIOMetadata iIOMetadata, List list, ImageWriteParam imageWriteParam) throws IOException {
        checkParamsEmpty(imageTypeSpecifier, i2, i3, list);
        this.isInsertingEmpty = true;
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        insert(i, new IIOImage(new EmptyImage(0, 0, i2, i3, 0, 0, sampleModel.getWidth(), sampleModel.getHeight(), sampleModel, imageTypeSpecifier.getColorModel()), (List) null, iIOMetadata), imageWriteParam, false);
    }

    public void prepareWriteEmpty(IIOMetadata iIOMetadata, ImageTypeSpecifier imageTypeSpecifier, int i, int i2, IIOMetadata iIOMetadata2, List list, ImageWriteParam imageWriteParam) throws IOException {
        checkParamsEmpty(imageTypeSpecifier, i, i2, list);
        this.isWritingEmpty = true;
        SampleModel sampleModel = imageTypeSpecifier.getSampleModel();
        write(iIOMetadata, new IIOImage(new EmptyImage(0, 0, i, i2, 0, 0, sampleModel.getWidth(), sampleModel.getHeight(), sampleModel, imageTypeSpecifier.getColorModel()), (List) null, iIOMetadata2), imageWriteParam, true, false);
    }

    public void endInsertEmpty() throws IOException {
        if (getOutput() == null) {
            throw new IllegalStateException("getOutput() == null!");
        }
        if (!this.isInsertingEmpty) {
            throw new IllegalStateException("No previous call to prepareInsertEmpty()!");
        }
        if (this.isWritingEmpty) {
            throw new IllegalStateException("Previous call to prepareWriteEmpty() without corresponding call to endWriteEmpty()!");
        }
        if (this.inReplacePixelsNest) {
            throw new IllegalStateException("In nested call to prepareReplacePixels!");
        }
        this.isInsertingEmpty = false;
    }

    public void endWriteEmpty() throws IOException {
        if (getOutput() == null) {
            throw new IllegalStateException("getOutput() == null!");
        }
        if (!this.isWritingEmpty) {
            throw new IllegalStateException("No previous call to prepareWriteEmpty()!");
        }
        if (this.isInsertingEmpty) {
            throw new IllegalStateException("Previous call to prepareInsertEmpty() without corresponding call to endInsertEmpty()!");
        }
        if (this.inReplacePixelsNest) {
            throw new IllegalStateException("In nested call to prepareReplacePixels!");
        }
        this.isWritingEmpty = false;
    }

    private TIFFIFD readIFD(int i) throws IOException {
        ImageOutputStream imageOutputStream = this.stream;
        if (imageOutputStream == null) {
            throw new IllegalStateException("Output not set!");
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("imageIndex < 0!");
        }
        imageOutputStream.mark();
        long[] jArr = new long[1];
        locateIFD(i, new long[1], jArr);
        if (jArr[0] == 0) {
            this.stream.reset();
            throw new IndexOutOfBoundsException("imageIndex out of bounds!");
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(BaselineTIFFTagSet.getInstance());
        TIFFIFD tiffifd = new TIFFIFD(arrayList);
        tiffifd.initialize(this.stream, true);
        this.stream.reset();
        return tiffifd;
    }

    public boolean canReplacePixels(int i) throws IOException {
        if (getOutput() != null) {
            return readIFD(i).getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION).getAsInt(0) == 1;
        }
        throw new IllegalStateException("getOutput() == null!");
    }

    public void prepareReplacePixels(int i, Rectangle rectangle) throws IOException {
        synchronized (this.replacePixelsLock) {
            if (this.stream == null) {
                throw new IllegalStateException("Output not set!");
            }
            if (rectangle == null) {
                throw new IllegalArgumentException("region == null!");
            }
            if (rectangle.getWidth() < 1.0d) {
                throw new IllegalArgumentException("region.getWidth() < 1!");
            }
            if (rectangle.getHeight() < 1.0d) {
                throw new IllegalArgumentException("region.getHeight() < 1!");
            }
            if (this.inReplacePixelsNest) {
                throw new IllegalStateException("In nested call to prepareReplacePixels!");
            }
            TIFFIFD readIFD = readIFD(i);
            if (readIFD.getTIFFField(BaselineTIFFTagSet.TAG_COMPRESSION).getAsInt(0) != 1) {
                throw new UnsupportedOperationException("canReplacePixels(imageIndex) == false!");
            }
            TIFFField tIFFField = readIFD.getTIFFField(256);
            if (tIFFField == null) {
                throw new IIOException("Cannot read ImageWidth field.");
            }
            int asInt = tIFFField.getAsInt(0);
            TIFFField tIFFField2 = readIFD.getTIFFField(257);
            if (tIFFField2 == null) {
                throw new IIOException("Cannot read ImageHeight field.");
            }
            Rectangle intersection = rectangle.intersection(new Rectangle(0, 0, asInt, tIFFField2.getAsInt(0)));
            if (intersection.isEmpty()) {
                throw new IIOException("Region does not intersect image bounds");
            }
            this.replacePixelsRegion = intersection;
            TIFFField tIFFField3 = readIFD.getTIFFField(BaselineTIFFTagSet.TAG_TILE_OFFSETS);
            if (tIFFField3 == null) {
                tIFFField3 = readIFD.getTIFFField(BaselineTIFFTagSet.TAG_STRIP_OFFSETS);
            }
            this.replacePixelsTileOffsets = tIFFField3.getAsLongs();
            TIFFField tIFFField4 = readIFD.getTIFFField(BaselineTIFFTagSet.TAG_TILE_BYTE_COUNTS);
            if (tIFFField4 == null) {
                tIFFField4 = readIFD.getTIFFField(BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS);
            }
            this.replacePixelsByteCounts = tIFFField4.getAsLongs();
            this.replacePixelsOffsetsPosition = readIFD.getStripOrTileOffsetsPosition();
            this.replacePixelsByteCountsPosition = readIFD.getStripOrTileByteCountsPosition();
            this.replacePixelsMetadata = new TIFFImageMetadata(readIFD);
            this.replacePixelsIndex = i;
            this.inReplacePixelsNest = true;
        }
    }

    private Raster subsample(Raster raster, int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, Rectangle rectangle) {
        int i7;
        int minX = raster.getMinX();
        int minY = raster.getMinY();
        int width = raster.getWidth();
        int height = raster.getHeight();
        int numBands = raster.getSampleModel().getNumBands();
        int dataType = raster.getSampleModel().getDataType();
        int XToTileX = XToTileX(minX, i, i3) + i5;
        int YToTileY = YToTileY(minY, i2, i4) + i6;
        int XToTileX2 = XToTileX((minX + width) - 1, i, i3) + i5;
        int YToTileY2 = YToTileY((minY + height) - 1, i2, i4) + i6;
        int i8 = (XToTileX2 - XToTileX) + 1;
        int i9 = (YToTileY2 - YToTileY) + 1;
        if (i8 <= 0 || i9 <= 0) {
            return null;
        }
        int i10 = ((XToTileX - i5) * i3) + i;
        int i11 = ((((XToTileX2 - i5) * i3) + i) - i10) + 1;
        int i12 = ((YToTileY - i6) * i4) + i2;
        WritableRaster createCompatibleWritableRaster = raster.createCompatibleWritableRaster(XToTileX, YToTileY, i8, i9);
        int i13 = ((((YToTileY2 - i6) * i4) + i2) - i12) + 1 + i12;
        if (dataType == 4 || dataType == 5) {
            i7 = YToTileY;
            float[] fArr = new float[i11];
            float[] fArr2 = new float[i8];
            int i14 = 0;
            while (i14 < numBands) {
                int i15 = i12;
                int i16 = i7;
                while (i15 < i13) {
                    raster.getSamples(i10, i15, i11, 1, i14, fArr);
                    int i17 = 0;
                    int i18 = 0;
                    while (i17 < i11) {
                        fArr2[i18] = fArr[i17];
                        i17 += i3;
                        i18++;
                    }
                    int i19 = i14;
                    createCompatibleWritableRaster.setSamples(XToTileX, i16, i8, 1, i19, fArr2);
                    i15 += i4;
                    i16++;
                    i14 = i19;
                }
                i14++;
            }
        } else {
            int[] iArr2 = new int[i11];
            int[] iArr3 = new int[i8];
            int i20 = 0;
            while (i20 < numBands) {
                int i21 = i12;
                int i22 = YToTileY;
                while (i21 < i13) {
                    raster.getSamples(i10, i21, i11, 1, i20, iArr2);
                    int i23 = i12;
                    int i24 = 0;
                    int i25 = 0;
                    while (i24 < i11) {
                        iArr3[i25] = iArr2[i24];
                        i24 += i3;
                        i25++;
                    }
                    int i26 = i20;
                    int[] iArr4 = iArr3;
                    createCompatibleWritableRaster.setSamples(XToTileX, i22, i8, 1, i26, iArr4);
                    i21 += i4;
                    i20 = i26;
                    iArr3 = iArr4;
                    YToTileY = YToTileY;
                    i22++;
                    i12 = i23;
                }
                i20++;
            }
            i7 = YToTileY;
        }
        return createCompatibleWritableRaster.createChild(XToTileX, i7, rectangle.width, rectangle.height, rectangle.x, rectangle.y, iArr);
    }

    public void replacePixels(RenderedImage renderedImage, ImageWriteParam imageWriteParam) throws IOException {
        ImageWriteParam defaultWriteParam;
        int subsamplingXOffset;
        int subsamplingYOffset;
        int i;
        int i2;
        int[] iArr;
        int i3;
        WritableRaster raster;
        int i4;
        int i5;
        int i6;
        int[] iArr2;
        int i7;
        SampleModel sampleModel;
        int i8;
        WritableRaster writableRaster;
        TIFFNullCompressor tIFFNullCompressor;
        Point point;
        int i9;
        int i10;
        ColorModel colorModel;
        Rectangle rectangle;
        int i11;
        Raster subsample;
        synchronized (this.replacePixelsLock) {
            if (this.stream == null) {
                throw new IllegalStateException("stream == null!");
            }
            if (renderedImage == null) {
                throw new IllegalArgumentException("image == null!");
            }
            if (!this.inReplacePixelsNest) {
                throw new IllegalStateException("No previous call to prepareReplacePixels!");
            }
            if (imageWriteParam == null) {
                defaultWriteParam = getDefaultWriteParam();
                subsamplingXOffset = 0;
                subsamplingYOffset = 0;
                i2 = 1;
                i = 1;
            } else {
                defaultWriteParam = getDefaultWriteParam();
                defaultWriteParam.setCompressionMode(0);
                defaultWriteParam.setTilingMode(3);
                defaultWriteParam.setDestinationOffset(imageWriteParam.getDestinationOffset());
                defaultWriteParam.setSourceBands(imageWriteParam.getSourceBands());
                defaultWriteParam.setSourceRegion(imageWriteParam.getSourceRegion());
                int sourceXSubsampling = imageWriteParam.getSourceXSubsampling();
                int sourceYSubsampling = imageWriteParam.getSourceYSubsampling();
                subsamplingXOffset = imageWriteParam.getSubsamplingXOffset();
                subsamplingYOffset = imageWriteParam.getSubsamplingYOffset();
                i = sourceXSubsampling;
                i2 = sourceYSubsampling;
            }
            TIFFField tIFFField = this.replacePixelsMetadata.getTIFFField(258);
            if (tIFFField == null) {
                throw new IIOException("Cannot read destination BitsPerSample");
            }
            int[] asInts = tIFFField.getAsInts();
            int[] sampleSize = renderedImage.getSampleModel().getSampleSize();
            int[] sourceBands = defaultWriteParam.getSourceBands();
            if (sourceBands != null) {
                if (sourceBands.length != asInts.length) {
                    throw new IIOException("Source and destination have different SamplesPerPixel");
                }
                for (int i12 = 0; i12 < sourceBands.length; i12++) {
                    if (asInts[i12] != sampleSize[sourceBands[i12]]) {
                        throw new IIOException("Source and destination have different BitsPerSample");
                    }
                }
            } else {
                int numBands = renderedImage.getSampleModel().getNumBands();
                if (numBands != asInts.length) {
                    throw new IIOException("Source and destination have different SamplesPerPixel");
                }
                for (int i13 = 0; i13 < numBands; i13++) {
                    if (asInts[i13] != sampleSize[i13]) {
                        throw new IIOException("Source and destination have different BitsPerSample");
                    }
                }
            }
            Rectangle rectangle2 = new Rectangle(renderedImage.getMinX(), renderedImage.getMinY(), renderedImage.getWidth(), renderedImage.getHeight());
            Rectangle sourceRegion = defaultWriteParam.getSourceRegion();
            if (sourceRegion == null) {
                sourceRegion = rectangle2;
            }
            int i14 = subsamplingXOffset + sourceRegion.x;
            int i15 = subsamplingYOffset + sourceRegion.y;
            if (!sourceRegion.equals(rectangle2)) {
                sourceRegion = sourceRegion.intersection(rectangle2);
                if (sourceRegion.isEmpty()) {
                    throw new IllegalArgumentException("Source region does not intersect source image!");
                }
            }
            Point destinationOffset = defaultWriteParam.getDestinationOffset();
            Rectangle intersection = new Rectangle(destinationOffset.x, destinationOffset.y, (XToTileX(sourceRegion.x + sourceRegion.width, i14, i) + destinationOffset.x) - (XToTileX(sourceRegion.x, i14, i) + destinationOffset.x), (YToTileY(sourceRegion.y + sourceRegion.height, i15, i2) + destinationOffset.y) - (YToTileY(sourceRegion.y, i15, i2) + destinationOffset.y)).intersection(this.replacePixelsRegion);
            if (intersection.isEmpty()) {
                throw new IllegalArgumentException("Forward mapped source region does not intersect destination region!");
            }
            int i16 = ((intersection.x - destinationOffset.x) * i) + i14;
            int i17 = ((((((intersection.x + intersection.width) - 1) - destinationOffset.x) * i) + i14) - i16) + 1;
            int i18 = ((intersection.y - destinationOffset.y) * i2) + i15;
            if (new Rectangle(i16, i18, i17, ((((((intersection.y + intersection.height) - 1) - destinationOffset.y) * i2) + i15) - i18) + 1).intersection(rectangle2).isEmpty()) {
                throw new IllegalArgumentException("Backward mapped destination region does not intersect source image!");
            }
            TIFFImageReader tIFFImageReader = this.reader;
            if (tIFFImageReader == null) {
                this.reader = new TIFFImageReader(new TIFFImageReaderSpi());
            } else {
                tIFFImageReader.reset();
            }
            this.stream.mark();
            try {
                try {
                    this.stream.seek(this.headerPosition);
                    this.reader.setInput(this.stream);
                    this.imageMetadata = this.replacePixelsMetadata;
                    this.param = defaultWriteParam;
                    SampleModel sampleModel2 = renderedImage.getSampleModel();
                    ColorModel colorModel2 = renderedImage.getColorModel();
                    this.numBands = sampleModel2.getNumBands();
                    RenderedImage renderedImage2 = renderedImage;
                    this.imageType = new ImageTypeSpecifier(renderedImage2);
                    this.periodX = defaultWriteParam.getSourceXSubsampling();
                    this.periodY = defaultWriteParam.getSourceYSubsampling();
                    this.sourceBands = null;
                    int[] sourceBands2 = defaultWriteParam.getSourceBands();
                    if (sourceBands2 != null) {
                        this.sourceBands = sourceBands2;
                        iArr = sourceBands;
                        this.numBands = iArr.length;
                    } else {
                        iArr = sourceBands;
                    }
                    setupMetadata(colorModel2, sampleModel2, this.reader.getWidth(this.replacePixelsIndex), this.reader.getHeight(this.replacePixelsIndex));
                    initializeScaleTables(sampleModel2.getSampleSize());
                    boolean isBinary = ImageUtil.isBinary(renderedImage.getSampleModel());
                    this.isBilevel = isBinary;
                    int i19 = this.nativePhotometricInterpretation;
                    boolean z = (i19 == 1 && this.photometricInterpretation == 0) || (i19 == 0 && this.photometricInterpretation == 1);
                    this.isInverted = z;
                    this.isImageSimple = (isBinary || (!z && ImageUtil.imageIsContiguous(renderedImage))) && !this.isRescaling && iArr == null && this.periodX == 1 && this.periodY == 1 && this.colorConverter == null;
                    int XToTileX = XToTileX(intersection.x, 0, this.tileWidth);
                    int YToTileY = YToTileY(intersection.y, 0, this.tileLength);
                    int XToTileX2 = XToTileX((intersection.x + intersection.width) - 1, 0, this.tileWidth);
                    ColorModel colorModel3 = colorModel2;
                    int YToTileY2 = YToTileY((intersection.y + intersection.height) - 1, 0, this.tileLength);
                    TIFFNullCompressor tIFFNullCompressor2 = new TIFFNullCompressor();
                    tIFFNullCompressor2.setWriter(this);
                    tIFFNullCompressor2.setStream(this.stream);
                    tIFFNullCompressor2.setMetadata(this.imageMetadata);
                    Rectangle rectangle3 = new Rectangle();
                    while (YToTileY <= YToTileY2) {
                        int i20 = YToTileY2;
                        int i21 = XToTileX;
                        while (i21 <= XToTileX2) {
                            TIFFNullCompressor tIFFNullCompressor3 = tIFFNullCompressor2;
                            int i22 = (this.tilesAcross * YToTileY) + i21;
                            boolean z2 = this.replacePixelsByteCounts[i22] == 0;
                            if (z2) {
                                i3 = XToTileX2;
                                raster = Raster.createWritableRaster(sampleModel2.createCompatibleSampleModel(this.tileWidth, this.tileLength), (Point) null);
                            } else {
                                i3 = XToTileX2;
                                raster = this.reader.readTile(this.replacePixelsIndex, i21, YToTileY).getRaster();
                            }
                            int i23 = i21;
                            rectangle3.setLocation(this.tileWidth * i21, this.tileLength * YToTileY);
                            rectangle3.setSize(raster.getWidth(), raster.getHeight());
                            WritableRaster createWritableTranslatedChild = raster.createWritableTranslatedChild(rectangle3.x, rectangle3.y);
                            Rectangle intersection2 = rectangle3.intersection(intersection);
                            Rectangle rectangle4 = intersection;
                            int i24 = ((intersection2.x - destinationOffset.x) * i) + i14;
                            int i25 = YToTileY;
                            int i26 = ((((((intersection2.x + intersection2.width) - 1) - destinationOffset.x) * i) + i14) - i24) + 1;
                            int i27 = ((intersection2.y - destinationOffset.y) * i2) + i15;
                            Rectangle rectangle5 = rectangle3;
                            Rectangle rectangle6 = new Rectangle(i24, i27, i26, ((((((intersection2.y + intersection2.height) - 1) - destinationOffset.y) * i2) + i15) - i27) + 1);
                            Raster data = renderedImage2.getData(rectangle6);
                            if (i == 1 && i2 == 1 && i14 == 0 && i15 == 0) {
                                subsample = data.createChild(rectangle6.x, rectangle6.y, rectangle6.width, rectangle6.height, intersection2.x, intersection2.y, iArr);
                                iArr2 = iArr;
                                i7 = i14;
                                sampleModel = sampleModel2;
                                i10 = i;
                                colorModel = colorModel3;
                                i5 = i20;
                                tIFFNullCompressor = tIFFNullCompressor3;
                                i8 = i3;
                                i6 = i23;
                                i4 = i25;
                                writableRaster = createWritableTranslatedChild;
                                rectangle = rectangle5;
                                point = destinationOffset;
                                i9 = i15;
                                i11 = i2;
                            } else {
                                i4 = i25;
                                i5 = i20;
                                i6 = i23;
                                int[] iArr3 = iArr;
                                iArr2 = iArr;
                                int i28 = i14;
                                i7 = i14;
                                sampleModel = sampleModel2;
                                i8 = i3;
                                writableRaster = createWritableTranslatedChild;
                                tIFFNullCompressor = tIFFNullCompressor3;
                                point = destinationOffset;
                                i9 = i15;
                                i10 = i;
                                colorModel = colorModel3;
                                rectangle = rectangle5;
                                i11 = i2;
                                subsample = subsample(data, iArr3, i28, i15, i, i2, destinationOffset.x, destinationOffset.y, intersection2);
                                if (subsample == null) {
                                    i21 = i6 + 1;
                                    renderedImage2 = renderedImage;
                                    colorModel3 = colorModel;
                                    tIFFNullCompressor2 = tIFFNullCompressor;
                                    rectangle3 = rectangle;
                                    YToTileY = i4;
                                    iArr = iArr2;
                                    XToTileX2 = i8;
                                    sampleModel2 = sampleModel;
                                    i = i10;
                                    destinationOffset = point;
                                    i15 = i9;
                                    i2 = i11;
                                    intersection = rectangle4;
                                    i14 = i7;
                                    i20 = i5;
                                }
                            }
                            writableRaster.setRect(subsample);
                            if (z2) {
                                this.stream.seek(this.nextSpace);
                            } else {
                                this.stream.seek(this.replacePixelsTileOffsets[i22]);
                            }
                            this.image = new SingleTileRenderedImage(writableRaster, colorModel);
                            int writeTile = writeTile(rectangle, tIFFNullCompressor);
                            if (z2) {
                                this.stream.mark();
                                long j = i22 * 4;
                                this.stream.seek(this.replacePixelsOffsetsPosition + j);
                                this.stream.writeInt((int) this.nextSpace);
                                this.stream.seek(this.replacePixelsByteCountsPosition + j);
                                this.stream.writeInt(writeTile);
                                this.stream.reset();
                                this.nextSpace += writeTile;
                            }
                            i21 = i6 + 1;
                            renderedImage2 = renderedImage;
                            colorModel3 = colorModel;
                            tIFFNullCompressor2 = tIFFNullCompressor;
                            rectangle3 = rectangle;
                            YToTileY = i4;
                            iArr = iArr2;
                            XToTileX2 = i8;
                            sampleModel2 = sampleModel;
                            i = i10;
                            destinationOffset = point;
                            i15 = i9;
                            i2 = i11;
                            intersection = rectangle4;
                            i14 = i7;
                            i20 = i5;
                        }
                        YToTileY++;
                        renderedImage2 = renderedImage;
                        colorModel3 = colorModel3;
                        YToTileY2 = i20;
                        i = i;
                        intersection = intersection;
                        i14 = i14;
                    }
                } catch (IOException e) {
                    throw e;
                }
            } finally {
                this.stream.reset();
            }
        }
    }

    public void replacePixels(Raster raster, ImageWriteParam imageWriteParam) throws IOException {
        if (raster == null) {
            throw new IllegalArgumentException("raster == null!");
        }
        replacePixels(new SingleTileRenderedImage(raster, this.image.getColorModel()), imageWriteParam);
    }

    public void endReplacePixels() throws IOException {
        synchronized (this.replacePixelsLock) {
            if (!this.inReplacePixelsNest) {
                throw new IllegalStateException("No previous call to prepareReplacePixels()!");
            }
            this.replacePixelsIndex = -1;
            this.replacePixelsMetadata = null;
            this.replacePixelsTileOffsets = null;
            this.replacePixelsByteCounts = null;
            this.replacePixelsOffsetsPosition = 0L;
            this.replacePixelsByteCountsPosition = 0L;
            this.replacePixelsRegion = null;
            this.inReplacePixelsNest = false;
        }
    }

    public void reset() {
        super.reset();
        this.stream = null;
        this.image = null;
        this.imageType = null;
        this.byteOrder = null;
        this.param = null;
        TIFFCompressor tIFFCompressor = this.compressor;
        if (tIFFCompressor != null) {
            tIFFCompressor.dispose();
        }
        this.compressor = null;
        this.colorConverter = null;
        this.streamMetadata = null;
        this.imageMetadata = null;
        this.isWritingSequence = false;
        this.isWritingEmpty = false;
        this.isInsertingEmpty = false;
        this.replacePixelsIndex = -1;
        this.replacePixelsMetadata = null;
        this.replacePixelsTileOffsets = null;
        this.replacePixelsByteCounts = null;
        this.replacePixelsOffsetsPosition = 0L;
        this.replacePixelsByteCountsPosition = 0L;
        this.replacePixelsRegion = null;
        this.inReplacePixelsNest = false;
    }

    public void dispose() {
        reset();
        super.dispose();
    }
}
