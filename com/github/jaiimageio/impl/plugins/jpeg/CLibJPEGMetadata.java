package com.github.jaiimageio.impl.plugins.jpeg;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.ts.PsExtractor;
import co.hyperverge.hvnfcsdk.helpers.DataGroupConstants;
import com.facebook.internal.ServerProtocol;
import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.EXIFParentTIFFTagSet;
import com.github.jaiimageio.plugins.tiff.TIFFDirectory;
import com.github.jaiimageio.plugins.tiff.TIFFField;
import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
import com.google.firebase.database.core.ValidationPath;
import io.flutter.plugins.firebase.crashlytics.Constants;
import io.sentry.ProfilingTraceData;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.imageio.plugins.jpeg.JPEGQTable;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import net.sf.scuba.smartcards.ISO7816;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes3.dex */
public class CLibJPEGMetadata extends IIOMetadata {
    static final int ADOBE_TRANSFORM_UNKNOWN = 0;
    static final int ADOBE_TRANSFORM_YCC = 1;
    static final int ADOBE_TRANSFORM_YCCK = 2;
    static final int APP0 = 224;
    static final int APP0_JFIF = 57344;
    static final int APP0_JFXX = 57345;
    static final int APP1 = 225;
    static final int APP10 = 234;
    static final int APP11 = 235;
    static final int APP12 = 236;
    static final int APP13 = 237;
    static final int APP14 = 238;
    static final int APP14_ADOBE = 60928;
    static final int APP15 = 239;
    static final int APP1_EXIF = 57600;
    static final int APP2 = 226;
    static final int APP2_ICC = 57856;
    static final int APP3 = 227;
    static final int APP4 = 228;
    static final int APP5 = 229;
    static final int APP6 = 230;
    static final int APP7 = 231;
    static final int APP8 = 232;
    static final int APP9 = 233;
    static final int APPN_MAX = 239;
    static final int APPN_MIN = 224;
    static final int COM = 254;
    static final int DAC = 204;
    static final int DHP = 222;
    static final int DHT = 196;
    static final int DNL = 220;
    static final int DQT = 219;
    static final int DRI = 221;
    static final int EOI = 217;
    static final int EXP = 223;
    static final int JFIF_RESUNITS_ASPECT = 0;
    static final int JFIF_RESUNITS_DPC = 2;
    static final int JFIF_RESUNITS_DPI = 1;
    static final int JPG = 200;
    static final int LSE = 242;
    static final String NATIVE_FORMAT = "javax_imageio_jpeg_image_1.0";
    static final String NATIVE_FORMAT_CLASS = "com.sun.imageio.plugins.jpeg.JPEGImageMetadataFormat";
    static final int RESTART_RANGE = 8;
    static final int RST0 = 208;
    static final int RST1 = 209;
    static final int RST2 = 210;
    static final int RST3 = 211;
    static final int RST4 = 212;
    static final int RST5 = 213;
    static final int RST6 = 214;
    static final int RST7 = 215;
    static final int RST_MAX = 215;
    static final int RST_MIN = 208;
    static final int SOF0 = 192;
    static final int SOF1 = 193;
    static final int SOF10 = 202;
    static final int SOF11 = 203;
    static final int SOF13 = 205;
    static final int SOF14 = 206;
    static final int SOF15 = 207;
    static final int SOF2 = 194;
    static final int SOF3 = 195;
    static final int SOF5 = 197;
    static final int SOF55 = 247;
    static final int SOF6 = 198;
    static final int SOF7 = 199;
    static final int SOF9 = 201;
    static final int SOFN_MAX = 207;
    static final int SOFN_MIN = 192;
    static final int SOF_MARKER = 49152;
    static final int SOI = 216;
    static final int SOS = 218;
    static final int TEM = 1;
    static final int THUMBNAIL_JPEG = 16;
    static final int THUMBNAIL_PALETTE = 17;
    static final int THUMBNAIL_RGB = 18;
    static final String TIFF_FORMAT = "com_sun_media_imageio_plugins_tiff_image_1.0";
    static final String TIFF_FORMAT_CLASS = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat";
    static final int UNKNOWN_MARKER = 65535;
    static final int[] zigzag = {0, 1, 5, 6, 14, 15, 27, 28, 2, 4, 7, 13, 16, 26, 29, 42, 3, 8, 12, 17, 25, 30, 41, 43, 9, 11, 18, 24, 31, 40, 44, 53, 10, 19, 23, 32, 39, 45, 52, 54, 20, 22, 33, 38, 46, 51, 55, 60, 21, 34, 37, 47, 50, 56, 59, 61, 35, 36, 48, 49, 57, 58, 62, 63};
    int Xdensity;
    int Ydensity;
    int[] acHuffTable;
    boolean app0JFIFPresent;
    boolean app0JFXXPresent;
    boolean app14AdobePresent;
    boolean app2ICCPresent;
    int approxHigh;
    int approxLow;
    boolean comPresent;
    List comments;
    int[] componentId;
    int[] componentSelector;
    int[] dcHuffTable;
    boolean dhtPresent;
    boolean dqtPresent;
    int driInterval;
    boolean driPresent;
    int endSpectralSelection;
    byte[] exifData;
    List extensionCodes;
    int flags0;
    int flags1;
    int[] hSamplingFactor;
    private boolean hasAlpha;
    List htables;
    private boolean isReadOnly;
    BufferedImage jfifThumbnail;
    List jfxxThumbnails;
    int majorVersion;
    List markerTags;
    private List markers;
    int minorVersion;
    int numFrameComponents;
    int numLines;
    int numScanComponents;
    ICC_Profile profile;
    int[] qtableSelector;
    List qtables;
    int resUnits;
    int samplePrecision;
    int samplesPerLine;
    boolean sofPresent;
    int sofProcess;
    boolean sosPresent;
    int startSpectralSelection;
    int thumbHeight;
    int thumbWidth;
    private List thumbnails;
    private boolean thumbnailsInitialized;
    int transform;
    List unknownData;
    boolean unknownPresent;
    int[] vSamplingFactor;
    int version;

    private static IIOImage getThumbnail(ImageInputStream imageInputStream, int i, int i2, int i3, int i4) throws IOException {
        IndexColorModel componentColorModel;
        int i5;
        IIOImage iIOImage;
        IIOMetadata iIOMetadata;
        long streamPosition = imageInputStream.getStreamPosition();
        if (i2 == 16) {
            Iterator imageReaders = ImageIO.getImageReaders(imageInputStream);
            if (imageReaders == null || !imageReaders.hasNext()) {
                return null;
            }
            ImageReader imageReader = (ImageReader) imageReaders.next();
            imageReader.setInput(imageInputStream);
            BufferedImage read = imageReader.read(0, (ImageReadParam) null);
            try {
                iIOMetadata = imageReader.getImageMetadata(0);
            } catch (Exception unused) {
                iIOMetadata = null;
            }
            iIOImage = new IIOImage(read, (List) null, iIOMetadata);
        } else {
            if (i2 == 17) {
                if (i < (i3 * i4) + ValidationPath.MAX_PATH_LENGTH_BYTES) {
                    return null;
                }
                byte[] bArr = new byte[ValidationPath.MAX_PATH_LENGTH_BYTES];
                imageInputStream.readFully(bArr);
                byte[] bArr2 = new byte[256];
                byte[] bArr3 = new byte[256];
                byte[] bArr4 = new byte[256];
                int i6 = 0;
                int i7 = 0;
                while (i6 < 256) {
                    int i8 = i7 + 1;
                    bArr2[i6] = bArr[i7];
                    int i9 = i8 + 1;
                    bArr3[i6] = bArr[i8];
                    bArr4[i6] = bArr[i9];
                    i6++;
                    i7 = i9 + 1;
                }
                componentColorModel = new IndexColorModel(8, 256, bArr2, bArr3, bArr4);
                i5 = 1;
            } else {
                if (i < i3 * 3 * i4) {
                    return null;
                }
                componentColorModel = new ComponentColorModel(ColorSpace.getInstance(1000), false, false, 1, 0);
                i5 = 3;
            }
            int i10 = i3 * i4 * i5;
            byte[] bArr5 = new byte[i10];
            imageInputStream.readFully(bArr5);
            iIOImage = new IIOImage(new BufferedImage(componentColorModel, Raster.createInterleavedRaster(new DataBufferByte(bArr5, i10), i3, i4, i3 * i5, i5, new int[]{0, 1, 2}, (Point) null), false, (Hashtable) null), (List) null, (IIOMetadata) null);
        }
        imageInputStream.seek(streamPosition + i);
        return iIOImage;
    }

    CLibJPEGMetadata() {
        super(true, NATIVE_FORMAT, NATIVE_FORMAT_CLASS, new String[]{"com_sun_media_imageio_plugins_tiff_image_1.0"}, new String[]{"com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat"});
        this.isReadOnly = true;
        this.majorVersion = 1;
        this.minorVersion = 2;
        this.Xdensity = 1;
        this.Ydensity = 1;
        this.thumbWidth = 0;
        this.thumbHeight = 0;
        this.profile = null;
        this.version = 100;
        this.flags0 = 0;
        this.flags1 = 0;
        this.samplePrecision = 8;
        this.exifData = null;
        this.markers = null;
        this.hasAlpha = false;
        this.thumbnailsInitialized = false;
        this.thumbnails = new ArrayList();
        this.isReadOnly = this.isReadOnly;
    }

    CLibJPEGMetadata(ImageInputStream imageInputStream) throws IIOException {
        this();
        try {
            initializeFromStream(imageInputStream);
        } catch (IOException e) {
            throw new IIOException("Cannot initialize JPEG metadata!", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class QTable {
        private static final int QTABLE_SIZE = 64;
        int elementPrecision;
        int length;
        JPEGQTable table;
        int tableID;

        QTable(ImageInputStream imageInputStream) throws IOException {
            this.elementPrecision = (int) imageInputStream.readBits(4);
            this.tableID = (int) imageInputStream.readBits(4);
            byte[] bArr = new byte[64];
            imageInputStream.readFully(bArr);
            int[] iArr = new int[64];
            for (int i = 0; i < 64; i++) {
                iArr[i] = bArr[CLibJPEGMetadata.zigzag[i]] & 255;
            }
            this.table = new JPEGQTable(iArr);
            this.length = 65;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class HuffmanTable {
        private static final int NUM_LENGTHS = 16;
        int length;
        JPEGHuffmanTable table;
        int tableClass;
        int tableID;

        HuffmanTable(ImageInputStream imageInputStream) throws IOException {
            this.tableClass = (int) imageInputStream.readBits(4);
            this.tableID = (int) imageInputStream.readBits(4);
            short[] sArr = new short[16];
            for (int i = 0; i < 16; i++) {
                sArr[i] = (short) imageInputStream.read();
            }
            int i2 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                i2 += sArr[i3];
            }
            short[] sArr2 = new short[i2];
            for (int i4 = 0; i4 < i2; i4++) {
                sArr2[i4] = (short) imageInputStream.read();
            }
            this.table = new JPEGHuffmanTable(sArr, sArr2);
            this.length = i2 + 17;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:33:0x0058. Please report as an issue. */
    private synchronized void initializeFromStream(ImageInputStream imageInputStream) throws IOException {
        int read;
        int i;
        int i2;
        IIOImage thumbnail;
        boolean z;
        imageInputStream.mark();
        imageInputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        this.markers = new ArrayList();
        long[] jArr = null;
        char c = 0;
        int[] iArr = null;
        int i3 = 0;
        boolean z2 = true;
        while (true) {
            try {
                if (imageInputStream.read() == 255 && (read = imageInputStream.read()) != 0 && read != 255 && read != SOI && read != 1 && (read < 208 || read > 215)) {
                    if (read != EOI) {
                        int readUnsignedShort = imageInputStream.readUnsignedShort() - 2;
                        if (224 <= read && read <= 239) {
                            long streamPosition = imageInputStream.getStreamPosition();
                            if (read != APP14) {
                                int i4 = 6;
                                switch (read) {
                                    case 224:
                                        if (readUnsignedShort >= 5) {
                                            byte[] bArr = new byte[5];
                                            imageInputStream.readFully(bArr);
                                            String str = new String(bArr);
                                            if (str.startsWith("JFIF") && !this.app0JFIFPresent) {
                                                this.app0JFIFPresent = true;
                                                this.markers.add(new Integer(APP0_JFIF));
                                                this.majorVersion = imageInputStream.read();
                                                this.minorVersion = imageInputStream.read();
                                                this.resUnits = imageInputStream.read();
                                                this.Xdensity = imageInputStream.readUnsignedShort();
                                                this.Ydensity = imageInputStream.readUnsignedShort();
                                                this.thumbWidth = imageInputStream.read();
                                                int read2 = imageInputStream.read();
                                                this.thumbHeight = read2;
                                                int i5 = this.thumbWidth;
                                                if (i5 > 0 && read2 > 0 && (thumbnail = getThumbnail(imageInputStream, readUnsignedShort - 14, 18, i5, read2)) != null) {
                                                    this.jfifThumbnail = thumbnail.getRenderedImage();
                                                }
                                            } else if (str.startsWith("JFXX")) {
                                                if (!this.app0JFXXPresent) {
                                                    this.extensionCodes = new ArrayList(1);
                                                    this.jfxxThumbnails = new ArrayList(1);
                                                    this.app0JFXXPresent = true;
                                                }
                                                this.markers.add(new Integer(APP0_JFXX));
                                                int read3 = imageInputStream.read();
                                                this.extensionCodes.add(new Integer(read3));
                                                if (read3 != 16) {
                                                    i = imageInputStream.read();
                                                    i2 = imageInputStream.read();
                                                    i4 = 8;
                                                } else {
                                                    i = 0;
                                                    i2 = 0;
                                                }
                                                IIOImage thumbnail2 = getThumbnail(imageInputStream, readUnsignedShort - i4, read3, i, i2);
                                                if (thumbnail2 != null) {
                                                    this.jfxxThumbnails.add(thumbnail2);
                                                }
                                            }
                                            c = 1;
                                            break;
                                        }
                                        c = 0;
                                        break;
                                    case APP1 /* 225 */:
                                        if (readUnsignedShort >= 6) {
                                            byte[] bArr2 = new byte[6];
                                            imageInputStream.readFully(bArr2);
                                            if (bArr2[c] == 69 && bArr2[1] == 120 && bArr2[2] == 105 && bArr2[3] == 102 && bArr2[4] == 0 && bArr2[5] == 0) {
                                                byte[] bArr3 = new byte[readUnsignedShort - 6];
                                                this.exifData = bArr3;
                                                imageInputStream.readFully(bArr3);
                                            }
                                        }
                                        break;
                                    case APP2 /* 226 */:
                                        if (readUnsignedShort >= 12) {
                                            byte[] bArr4 = new byte[12];
                                            imageInputStream.readFully(bArr4);
                                            if (new String(bArr4).startsWith("ICC_PROFILE")) {
                                                if (!z2) {
                                                    imageInputStream.skipBytes(readUnsignedShort - 12);
                                                    break;
                                                } else {
                                                    int read4 = imageInputStream.read();
                                                    int read5 = imageInputStream.read();
                                                    if (read5 != 0 && read4 != 0 && read4 <= read5 && (!(z = this.app2ICCPresent) || (read5 == i3 && jArr[read4] == 0))) {
                                                        if (!z) {
                                                            this.app2ICCPresent = true;
                                                            this.markers.add(new Integer(APP2_ICC));
                                                            if (read5 == 1) {
                                                                try {
                                                                    byte[] bArr5 = new byte[readUnsignedShort - 14];
                                                                    imageInputStream.readFully(bArr5);
                                                                    this.profile = ICC_Profile.getInstance(bArr5);
                                                                } catch (EOFException unused) {
                                                                    i3 = read5;
                                                                    break;
                                                                }
                                                            } else {
                                                                int i6 = read5 + 1;
                                                                jArr = new long[i6];
                                                                iArr = new int[i6];
                                                                jArr[read4] = imageInputStream.getStreamPosition();
                                                                int i7 = readUnsignedShort - 14;
                                                                iArr[read4] = i7;
                                                                imageInputStream.skipBytes(i7);
                                                            }
                                                            i3 = read5;
                                                        } else {
                                                            jArr[read4] = imageInputStream.getStreamPosition();
                                                            int i8 = readUnsignedShort - 14;
                                                            iArr[read4] = i8;
                                                            imageInputStream.skipBytes(i8);
                                                        }
                                                        c = 1;
                                                        break;
                                                    }
                                                    try {
                                                        imageInputStream.skipBytes(readUnsignedShort - 14);
                                                        c = 0;
                                                        z2 = false;
                                                    } catch (EOFException unused2) {
                                                        z2 = false;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        c = 0;
                                        break;
                                }
                            } else {
                                if (readUnsignedShort >= 5) {
                                    byte[] bArr6 = new byte[5];
                                    imageInputStream.readFully(bArr6);
                                    if (new String(bArr6).startsWith("Adobe") && !this.app14AdobePresent) {
                                        this.app14AdobePresent = true;
                                        this.markers.add(new Integer(APP14_ADOBE));
                                        this.version = imageInputStream.readUnsignedShort();
                                        this.flags0 = imageInputStream.readUnsignedShort();
                                        this.flags1 = imageInputStream.readUnsignedShort();
                                        this.transform = imageInputStream.read();
                                        imageInputStream.skipBytes(readUnsignedShort - 12);
                                        c = 1;
                                    }
                                }
                                c = 0;
                            }
                            if (c == 0) {
                                imageInputStream.seek(streamPosition);
                                addUnknownMarkerSegment(imageInputStream, read, readUnsignedShort);
                            }
                        } else if (read == DQT) {
                            if (!this.dqtPresent) {
                                this.dqtPresent = true;
                                this.qtables = new ArrayList(1);
                            }
                            this.markers.add(new Integer(DQT));
                            ArrayList arrayList = new ArrayList(1);
                            do {
                                QTable qTable = new QTable(imageInputStream);
                                arrayList.add(qTable);
                                readUnsignedShort -= qTable.length;
                            } while (readUnsignedShort > 0);
                            this.qtables.add(arrayList);
                        } else if (read == 196) {
                            if (!this.dhtPresent) {
                                this.dhtPresent = true;
                                this.htables = new ArrayList(1);
                            }
                            this.markers.add(new Integer(196));
                            ArrayList arrayList2 = new ArrayList(1);
                            do {
                                HuffmanTable huffmanTable = new HuffmanTable(imageInputStream);
                                arrayList2.add(huffmanTable);
                                readUnsignedShort -= huffmanTable.length;
                            } while (readUnsignedShort > 0);
                            this.htables.add(arrayList2);
                        } else if (read == DRI) {
                            if (!this.driPresent) {
                                this.driPresent = true;
                            }
                            this.markers.add(new Integer(DRI));
                            this.driInterval = imageInputStream.readUnsignedShort();
                        } else if (read == 254) {
                            if (!this.comPresent) {
                                this.comPresent = true;
                                this.comments = new ArrayList(1);
                            }
                            this.markers.add(new Integer(254));
                            byte[] bArr7 = new byte[readUnsignedShort];
                            imageInputStream.readFully(bArr7);
                            this.comments.add(bArr7);
                        } else if ((read < 192 || read > 207) && read != SOF55) {
                            if (read == SOS) {
                                if (!this.sosPresent) {
                                    this.sosPresent = true;
                                    int read6 = imageInputStream.read();
                                    this.numScanComponents = read6;
                                    this.componentSelector = new int[read6];
                                    this.dcHuffTable = new int[read6];
                                    this.acHuffTable = new int[read6];
                                    for (int i9 = 0; i9 < this.numScanComponents; i9++) {
                                        this.componentSelector[i9] = imageInputStream.read();
                                        this.dcHuffTable[i9] = (int) imageInputStream.readBits(4);
                                        this.acHuffTable[i9] = (int) imageInputStream.readBits(4);
                                    }
                                    this.startSpectralSelection = imageInputStream.read();
                                    this.endSpectralSelection = imageInputStream.read();
                                    this.approxHigh = (int) imageInputStream.readBits(4);
                                    this.approxLow = (int) imageInputStream.readBits(4);
                                    this.markers.add(new Integer(SOS));
                                }
                            } else {
                                addUnknownMarkerSegment(imageInputStream, read, readUnsignedShort);
                            }
                        } else if (!this.sofPresent) {
                            this.sofPresent = true;
                            this.sofProcess = read - 192;
                            this.samplePrecision = imageInputStream.read();
                            this.numLines = imageInputStream.readUnsignedShort();
                            this.samplesPerLine = imageInputStream.readUnsignedShort();
                            int read7 = imageInputStream.read();
                            this.numFrameComponents = read7;
                            this.componentId = new int[read7];
                            this.hSamplingFactor = new int[read7];
                            this.vSamplingFactor = new int[read7];
                            this.qtableSelector = new int[read7];
                            for (int i10 = 0; i10 < this.numFrameComponents; i10++) {
                                this.componentId[i10] = imageInputStream.read();
                                this.hSamplingFactor[i10] = (int) imageInputStream.readBits(4);
                                this.vSamplingFactor[i10] = (int) imageInputStream.readBits(4);
                                this.qtableSelector[i10] = imageInputStream.read();
                            }
                            this.markers.add(new Integer(SOF_MARKER));
                        }
                    }
                }
                c = 0;
            } catch (EOFException unused3) {
            }
        }
        if (this.app2ICCPresent && z2 && this.profile == null) {
            int i11 = 1;
            int i12 = 0;
            while (true) {
                if (i11 <= i3) {
                    if (jArr[i11] == 0) {
                        z2 = false;
                    } else {
                        i12 += iArr[i11];
                        i11++;
                    }
                }
            }
            if (z2) {
                byte[] bArr8 = new byte[i12];
                int i13 = 0;
                for (int i14 = 1; i14 <= i3; i14++) {
                    imageInputStream.seek(jArr[i14]);
                    imageInputStream.read(bArr8, i13, iArr[i14]);
                    i13 += iArr[i14];
                }
                this.profile = ICC_Profile.getInstance(bArr8);
            }
        }
        imageInputStream.reset();
    }

    private void addUnknownMarkerSegment(ImageInputStream imageInputStream, int i, int i2) throws IOException {
        if (!this.unknownPresent) {
            this.unknownPresent = true;
            this.markerTags = new ArrayList(1);
            this.unknownData = new ArrayList(1);
        }
        this.markerTags.add(new Integer(i));
        byte[] bArr = new byte[i2];
        imageInputStream.readFully(bArr);
        this.unknownData.add(bArr);
        this.markers.add(new Integer(65535));
    }

    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    public Node getAsTree(String str) {
        if (str.equals(this.nativeMetadataFormatName)) {
            return getNativeTree();
        }
        if (str.equals("javax_imageio_1.0")) {
            return getStandardTree();
        }
        if (str.equals("com_sun_media_imageio_plugins_tiff_image_1.0")) {
            return getTIFFTree();
        }
        throw new IllegalArgumentException("Not a recognized format!");
    }

    public void mergeTree(String str, Node node) throws IIOInvalidTreeException {
        if (this.isReadOnly) {
            throw new IllegalStateException("isReadOnly() == true!");
        }
    }

    public void reset() {
        if (this.isReadOnly) {
            throw new IllegalStateException("isReadOnly() == true!");
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x00ae. Please report as an issue. */
    private Node getNativeTree() {
        IIOMetadataNode iIOMetadataNode;
        IIOMetadataNode iIOMetadataNode2;
        IIOMetadataNode iIOMetadataNode3;
        String str;
        IIOMetadataNode iIOMetadataNode4;
        String str2;
        Iterator it;
        IIOMetadataNode iIOMetadataNode5;
        CLibJPEGMetadata cLibJPEGMetadata = this;
        IIOMetadataNode iIOMetadataNode6 = new IIOMetadataNode(cLibJPEGMetadata.nativeMetadataFormatName);
        IIOMetadataNode iIOMetadataNode7 = new IIOMetadataNode("JPEGvariety");
        iIOMetadataNode6.appendChild(iIOMetadataNode7);
        String str3 = "markerSequence";
        IIOMetadataNode iIOMetadataNode8 = new IIOMetadataNode("markerSequence");
        iIOMetadataNode6.appendChild(iIOMetadataNode8);
        String str4 = "thumbHeight";
        if (cLibJPEGMetadata.app0JFIFPresent || cLibJPEGMetadata.app0JFXXPresent || cLibJPEGMetadata.app2ICCPresent) {
            iIOMetadataNode = new IIOMetadataNode("app0JFIF");
            iIOMetadataNode.setAttribute("majorVersion", Integer.toString(cLibJPEGMetadata.majorVersion));
            iIOMetadataNode.setAttribute("minorVersion", Integer.toString(cLibJPEGMetadata.minorVersion));
            iIOMetadataNode.setAttribute("resUnits", Integer.toString(cLibJPEGMetadata.resUnits));
            iIOMetadataNode.setAttribute("Xdensity", Integer.toString(cLibJPEGMetadata.Xdensity));
            iIOMetadataNode.setAttribute("Ydensity", Integer.toString(cLibJPEGMetadata.Ydensity));
            iIOMetadataNode.setAttribute("thumbWidth", Integer.toString(cLibJPEGMetadata.thumbWidth));
            iIOMetadataNode.setAttribute("thumbHeight", Integer.toString(cLibJPEGMetadata.thumbHeight));
            iIOMetadataNode7.appendChild(iIOMetadataNode);
        } else {
            iIOMetadataNode = null;
        }
        if (cLibJPEGMetadata.app0JFXXPresent) {
            iIOMetadataNode2 = new IIOMetadataNode("JFXX");
            iIOMetadataNode.appendChild(iIOMetadataNode2);
        } else {
            iIOMetadataNode2 = null;
        }
        Iterator it2 = cLibJPEGMetadata.markers.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (it2.hasNext()) {
            switch (((Integer) it2.next()).intValue()) {
                case 196:
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    iIOMetadataNode4 = iIOMetadataNode;
                    str2 = str4;
                    it = it2;
                    IIOMetadataNode iIOMetadataNode9 = new IIOMetadataNode("dht");
                    int i6 = i + 1;
                    List list = (List) cLibJPEGMetadata.htables.get(i);
                    int size = list.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        IIOMetadataNode iIOMetadataNode10 = new IIOMetadataNode("dhtable");
                        HuffmanTable huffmanTable = (HuffmanTable) list.get(i7);
                        iIOMetadataNode10.setAttribute(Constants.CLASS, Integer.toString(huffmanTable.tableClass));
                        iIOMetadataNode10.setAttribute("htableId", Integer.toString(huffmanTable.tableID));
                        iIOMetadataNode10.setUserObject(huffmanTable.table);
                        iIOMetadataNode9.appendChild(iIOMetadataNode10);
                    }
                    iIOMetadataNode8.appendChild(iIOMetadataNode9);
                    i = i6;
                    break;
                case SOS /* 218 */:
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    iIOMetadataNode4 = iIOMetadataNode;
                    str2 = str4;
                    it = it2;
                    IIOMetadataNode iIOMetadataNode11 = new IIOMetadataNode("sos");
                    iIOMetadataNode11.setAttribute("numScanComponents", Integer.toString(cLibJPEGMetadata.numScanComponents));
                    iIOMetadataNode11.setAttribute("startSpectralSelection", Integer.toString(cLibJPEGMetadata.startSpectralSelection));
                    iIOMetadataNode11.setAttribute("endSpectralSelection", Integer.toString(cLibJPEGMetadata.endSpectralSelection));
                    iIOMetadataNode11.setAttribute("approxHigh", Integer.toString(cLibJPEGMetadata.approxHigh));
                    iIOMetadataNode11.setAttribute("approxLow", Integer.toString(cLibJPEGMetadata.approxLow));
                    for (int i8 = 0; i8 < cLibJPEGMetadata.numScanComponents; i8++) {
                        IIOMetadataNode iIOMetadataNode12 = new IIOMetadataNode("scanComponentSpec");
                        iIOMetadataNode12.setAttribute("componentSelector", Integer.toString(cLibJPEGMetadata.componentSelector[i8]));
                        iIOMetadataNode12.setAttribute("dcHuffTable", Integer.toString(cLibJPEGMetadata.dcHuffTable[i8]));
                        iIOMetadataNode12.setAttribute("acHuffTable", Integer.toString(cLibJPEGMetadata.acHuffTable[i8]));
                        iIOMetadataNode11.appendChild(iIOMetadataNode12);
                    }
                    iIOMetadataNode8.appendChild(iIOMetadataNode11);
                    break;
                case DQT /* 219 */:
                    iIOMetadataNode3 = iIOMetadataNode2;
                    iIOMetadataNode4 = iIOMetadataNode;
                    it = it2;
                    IIOMetadataNode iIOMetadataNode13 = new IIOMetadataNode("dqt");
                    int i9 = i2 + 1;
                    List list2 = (List) cLibJPEGMetadata.qtables.get(i2);
                    int size2 = list2.size();
                    int i10 = 0;
                    while (i10 < size2) {
                        String str5 = str3;
                        IIOMetadataNode iIOMetadataNode14 = new IIOMetadataNode("dqtable");
                        QTable qTable = (QTable) list2.get(i10);
                        iIOMetadataNode14.setAttribute("elementPrecision", Integer.toString(qTable.elementPrecision));
                        iIOMetadataNode14.setAttribute("qtableId", Integer.toString(qTable.tableID));
                        iIOMetadataNode14.setUserObject(qTable.table);
                        iIOMetadataNode13.appendChild(iIOMetadataNode14);
                        i10++;
                        str3 = str5;
                        list2 = list2;
                        str4 = str4;
                    }
                    str = str3;
                    str2 = str4;
                    iIOMetadataNode8.appendChild(iIOMetadataNode13);
                    i2 = i9;
                    break;
                case DRI /* 221 */:
                    iIOMetadataNode3 = iIOMetadataNode2;
                    iIOMetadataNode4 = iIOMetadataNode;
                    it = it2;
                    IIOMetadataNode iIOMetadataNode15 = new IIOMetadataNode("dri");
                    iIOMetadataNode15.setAttribute("interval", Integer.toString(cLibJPEGMetadata.driInterval));
                    iIOMetadataNode8.appendChild(iIOMetadataNode15);
                    str = str3;
                    str2 = str4;
                    break;
                case 254:
                    iIOMetadataNode3 = iIOMetadataNode2;
                    iIOMetadataNode4 = iIOMetadataNode;
                    it = it2;
                    IIOMetadataNode iIOMetadataNode16 = new IIOMetadataNode(DataGroupConstants.COM);
                    iIOMetadataNode16.setUserObject(cLibJPEGMetadata.comments.get(i3));
                    iIOMetadataNode8.appendChild(iIOMetadataNode16);
                    str = str3;
                    str2 = str4;
                    i3++;
                    break;
                case SOF_MARKER /* 49152 */:
                    iIOMetadataNode4 = iIOMetadataNode;
                    it = it2;
                    IIOMetadataNode iIOMetadataNode17 = new IIOMetadataNode("sof");
                    iIOMetadataNode17.setAttribute("process", Integer.toString(cLibJPEGMetadata.sofProcess));
                    iIOMetadataNode17.setAttribute("samplePrecision", Integer.toString(cLibJPEGMetadata.samplePrecision));
                    iIOMetadataNode17.setAttribute("numLines", Integer.toString(cLibJPEGMetadata.numLines));
                    iIOMetadataNode17.setAttribute("samplesPerLine", Integer.toString(cLibJPEGMetadata.samplesPerLine));
                    iIOMetadataNode17.setAttribute("numFrameComponents", Integer.toString(cLibJPEGMetadata.numFrameComponents));
                    int i11 = 0;
                    while (i11 < cLibJPEGMetadata.numFrameComponents) {
                        IIOMetadataNode iIOMetadataNode18 = new IIOMetadataNode("componentSpec");
                        iIOMetadataNode18.setAttribute("componentId", Integer.toString(cLibJPEGMetadata.componentId[i11]));
                        iIOMetadataNode18.setAttribute("HsamplingFactor", Integer.toString(cLibJPEGMetadata.hSamplingFactor[i11]));
                        iIOMetadataNode18.setAttribute("VsamplingFactor", Integer.toString(cLibJPEGMetadata.vSamplingFactor[i11]));
                        iIOMetadataNode18.setAttribute("QtableSelector", Integer.toString(cLibJPEGMetadata.qtableSelector[i11]));
                        iIOMetadataNode17.appendChild(iIOMetadataNode18);
                        i11++;
                        iIOMetadataNode2 = iIOMetadataNode2;
                    }
                    iIOMetadataNode3 = iIOMetadataNode2;
                    iIOMetadataNode8.appendChild(iIOMetadataNode17);
                    str = str3;
                    str2 = str4;
                    break;
                case APP0_JFXX /* 57345 */:
                    it = it2;
                    IIOMetadataNode iIOMetadataNode19 = new IIOMetadataNode("app0JFXX");
                    Integer num = (Integer) cLibJPEGMetadata.extensionCodes.get(i4);
                    iIOMetadataNode4 = iIOMetadataNode;
                    iIOMetadataNode19.setAttribute("extensionCode", num.toString());
                    switch (num.intValue()) {
                        case 16:
                            iIOMetadataNode5 = new IIOMetadataNode("JFIFthumbJPEG");
                            break;
                        case 17:
                            iIOMetadataNode5 = new IIOMetadataNode("JFIFthumbPalette");
                            break;
                        case 18:
                            iIOMetadataNode5 = new IIOMetadataNode("JFIFthumbRGB");
                            break;
                        default:
                            iIOMetadataNode5 = null;
                            break;
                    }
                    if (iIOMetadataNode5 != null) {
                        int i12 = i4 + 1;
                        IIOImage iIOImage = (IIOImage) cLibJPEGMetadata.jfxxThumbnails.get(i4);
                        if (num.intValue() == 16) {
                            IIOMetadata metadata = iIOImage.getMetadata();
                            if (metadata != null) {
                                IIOMetadataNode asTree = metadata.getAsTree(cLibJPEGMetadata.nativeMetadataFormatName);
                                if (asTree instanceof IIOMetadataNode) {
                                    NodeList elementsByTagName = asTree.getElementsByTagName(str3);
                                    if (elementsByTagName.getLength() > 0) {
                                        iIOMetadataNode5.appendChild(elementsByTagName.item(0));
                                    }
                                }
                            }
                        } else {
                            BufferedImage renderedImage = iIOImage.getRenderedImage();
                            iIOMetadataNode5.setAttribute("thumbWidth", Integer.toString(renderedImage.getWidth()));
                            iIOMetadataNode5.setAttribute(str4, Integer.toString(renderedImage.getHeight()));
                        }
                        iIOMetadataNode5.setUserObject(iIOImage);
                        iIOMetadataNode19.appendChild(iIOMetadataNode5);
                        i4 = i12;
                    }
                    iIOMetadataNode2.appendChild(iIOMetadataNode19);
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    str2 = str4;
                    break;
                case APP2_ICC /* 57856 */:
                    it = it2;
                    IIOMetadataNode iIOMetadataNode20 = new IIOMetadataNode("app2ICC");
                    iIOMetadataNode20.setUserObject(cLibJPEGMetadata.profile);
                    iIOMetadataNode.appendChild(iIOMetadataNode20);
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    iIOMetadataNode4 = iIOMetadataNode;
                    str2 = str4;
                    break;
                case APP14_ADOBE /* 60928 */:
                    it = it2;
                    IIOMetadataNode iIOMetadataNode21 = new IIOMetadataNode("app14Adobe");
                    iIOMetadataNode21.setAttribute("version", Integer.toString(cLibJPEGMetadata.version));
                    iIOMetadataNode21.setAttribute("flags0", Integer.toString(cLibJPEGMetadata.flags0));
                    iIOMetadataNode21.setAttribute("flags1", Integer.toString(cLibJPEGMetadata.flags1));
                    iIOMetadataNode21.setAttribute("transform", Integer.toString(cLibJPEGMetadata.transform));
                    iIOMetadataNode8.appendChild(iIOMetadataNode21);
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    iIOMetadataNode4 = iIOMetadataNode;
                    str2 = str4;
                    break;
                case 65535:
                    IIOMetadataNode iIOMetadataNode22 = new IIOMetadataNode("unknown");
                    it = it2;
                    iIOMetadataNode22.setAttribute("MarkerTag", ((Integer) cLibJPEGMetadata.markerTags.get(i5)).toString());
                    iIOMetadataNode22.setUserObject(cLibJPEGMetadata.unknownData.get(i5));
                    iIOMetadataNode8.appendChild(iIOMetadataNode22);
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    iIOMetadataNode4 = iIOMetadataNode;
                    str2 = str4;
                    i5++;
                    break;
                default:
                    iIOMetadataNode3 = iIOMetadataNode2;
                    str = str3;
                    iIOMetadataNode4 = iIOMetadataNode;
                    str2 = str4;
                    it = it2;
                    break;
            }
            cLibJPEGMetadata = this;
            it2 = it;
            iIOMetadataNode = iIOMetadataNode4;
            iIOMetadataNode2 = iIOMetadataNode3;
            str3 = str;
            str4 = str2;
        }
        return iIOMetadataNode6;
    }

    protected IIOMetadataNode getStandardChromaNode() {
        int[] iArr;
        if (!this.sofPresent) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Chroma");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ColorSpaceType");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("NumChannels");
        iIOMetadataNode.appendChild(iIOMetadataNode3);
        iIOMetadataNode3.setAttribute("value", Integer.toString(this.numFrameComponents));
        if (this.app0JFIFPresent) {
            if (this.numFrameComponents == 1) {
                iIOMetadataNode2.setAttribute("name", "GRAY");
            } else {
                iIOMetadataNode2.setAttribute("name", "YCbCr");
            }
            return iIOMetadataNode;
        }
        if (this.app14AdobePresent) {
            int i = this.transform;
            if (i == 0) {
                int i2 = this.numFrameComponents;
                if (i2 == 3) {
                    iIOMetadataNode2.setAttribute("name", "RGB");
                } else if (i2 == 4) {
                    iIOMetadataNode2.setAttribute("name", "CMYK");
                }
            } else if (i == 1) {
                iIOMetadataNode2.setAttribute("name", "YCbCr");
            } else if (i == 2) {
                iIOMetadataNode2.setAttribute("name", "YCCK");
            }
            return iIOMetadataNode;
        }
        boolean z = false;
        this.hasAlpha = false;
        if (this.numFrameComponents < 3) {
            iIOMetadataNode2.setAttribute("name", "GRAY");
            if (this.numFrameComponents == 2) {
                this.hasAlpha = true;
            }
            return iIOMetadataNode;
        }
        int i3 = 0;
        boolean z2 = true;
        while (true) {
            iArr = this.componentId;
            if (i3 >= iArr.length) {
                break;
            }
            int i4 = iArr[i3];
            if (i4 < 1 || i4 >= iArr.length) {
                z2 = false;
            }
            i3++;
        }
        if (z2) {
            iIOMetadataNode2.setAttribute("name", "YCbCr");
            if (this.numFrameComponents == 4) {
                this.hasAlpha = true;
            }
            return iIOMetadataNode;
        }
        if (iArr[0] == 82 && iArr[1] == 71 && iArr[2] == 66) {
            iIOMetadataNode2.setAttribute("name", "RGB");
            if (this.numFrameComponents == 4 && this.componentId[3] == 65) {
                this.hasAlpha = true;
            }
            return iIOMetadataNode;
        }
        if (iArr[0] == 89 && iArr[1] == 67 && iArr[2] == 99) {
            iIOMetadataNode2.setAttribute("name", "PhotoYCC");
            if (this.numFrameComponents == 4 && this.componentId[3] == 65) {
                this.hasAlpha = true;
            }
            return iIOMetadataNode;
        }
        int i5 = this.hSamplingFactor[0];
        int i6 = this.vSamplingFactor[0];
        for (int i7 = 1; i7 < this.componentId.length; i7++) {
            if (this.hSamplingFactor[i7] != i5 || this.vSamplingFactor[i7] != i6) {
                z = true;
                break;
            }
        }
        if (z) {
            iIOMetadataNode2.setAttribute("name", "YCbCr");
            if (this.numFrameComponents == 4) {
                this.hasAlpha = true;
            }
            return iIOMetadataNode;
        }
        if (this.numFrameComponents == 3) {
            iIOMetadataNode2.setAttribute("name", "RGB");
        } else {
            iIOMetadataNode2.setAttribute("name", "CMYK");
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardCompressionNode() {
        String str;
        if (!this.sofPresent && !this.sosPresent) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(ExifInterface.TAG_COMPRESSION);
        if (this.sofPresent) {
            int i = this.sofProcess;
            boolean z = i == 3 || i == 7 || i == 11 || i == 15 || i == 55;
            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("CompressionTypeName");
            if (z) {
                str = this.sofProcess == 55 ? "JPEG-LS" : "JPEG-LOSSLESS";
            } else {
                str = "JPEG";
            }
            iIOMetadataNode2.setAttribute("value", str);
            iIOMetadataNode.appendChild(iIOMetadataNode2);
            IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("Lossless");
            iIOMetadataNode3.setAttribute("value", z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
            iIOMetadataNode.appendChild(iIOMetadataNode3);
        }
        if (!this.sosPresent) {
            return iIOMetadataNode;
        }
        IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("NumProgressiveScans");
        iIOMetadataNode4.setAttribute("value", "1");
        iIOMetadataNode.appendChild(iIOMetadataNode4);
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardDimensionNode() {
        float f;
        int i;
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Dimension");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("ImageOrientation");
        iIOMetadataNode2.setAttribute("value", ProfilingTraceData.TRUNCATION_REASON_NORMAL);
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        if (this.app0JFIFPresent) {
            if (this.resUnits == 0) {
                f = this.Xdensity;
                i = this.Ydensity;
            } else {
                f = this.Ydensity;
                i = this.Xdensity;
            }
            float f2 = f / i;
            IIOMetadataNode iIOMetadataNode3 = new IIOMetadataNode("PixelAspectRatio");
            iIOMetadataNode3.setAttribute("value", Float.toString(f2));
            iIOMetadataNode.insertBefore(iIOMetadataNode3, iIOMetadataNode2);
            int i2 = this.resUnits;
            if (i2 != 0) {
                float f3 = i2 == 1 ? 25.4f : 10.0f;
                IIOMetadataNode iIOMetadataNode4 = new IIOMetadataNode("HorizontalPixelSize");
                iIOMetadataNode4.setAttribute("value", Float.toString(f3 / this.Xdensity));
                iIOMetadataNode.appendChild(iIOMetadataNode4);
                IIOMetadataNode iIOMetadataNode5 = new IIOMetadataNode("VerticalPixelSize");
                iIOMetadataNode5.setAttribute("value", Float.toString(f3 / this.Ydensity));
                iIOMetadataNode.appendChild(iIOMetadataNode5);
            }
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardTextNode() {
        if (!this.comPresent) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Text");
        for (byte[] bArr : this.comments) {
            IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("TextEntry");
            iIOMetadataNode2.setAttribute("keyword", "comment");
            try {
                iIOMetadataNode2.setAttribute("value", new String(bArr, "ISO-8859-1"));
            } catch (UnsupportedEncodingException unused) {
                iIOMetadataNode2.setAttribute("value", new String(bArr));
            }
            iIOMetadataNode.appendChild(iIOMetadataNode2);
        }
        return iIOMetadataNode;
    }

    protected IIOMetadataNode getStandardTransparencyNode() {
        if (!this.hasAlpha) {
            return null;
        }
        IIOMetadataNode iIOMetadataNode = new IIOMetadataNode("Transparency");
        IIOMetadataNode iIOMetadataNode2 = new IIOMetadataNode("Alpha");
        iIOMetadataNode2.setAttribute("value", "nonpremultiplied");
        iIOMetadataNode.appendChild(iIOMetadataNode2);
        return iIOMetadataNode;
    }

    private Node getTIFFTree() {
        int i;
        int i2;
        int i3;
        BaselineTIFFTagSet baselineTIFFTagSet = BaselineTIFFTagSet.getInstance();
        int i4 = 2;
        byte[] bArr = null;
        TIFFDirectory tIFFDirectory = new TIFFDirectory(new TIFFTagSet[]{baselineTIFFTagSet, EXIFParentTIFFTagSet.getInstance()}, null);
        if (this.sofPresent) {
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_COMPRESSION), 7));
            int i5 = this.numFrameComponents;
            char[] cArr = new char[i5];
            Arrays.fill(cArr, (char) (this.samplePrecision & 255));
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(258), 3, i5, cArr));
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(257), this.numLines));
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(256), this.samplesPerLine));
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL), this.numFrameComponents));
            IIOMetadataNode standardChromaNode = getStandardChromaNode();
            if (standardChromaNode != null) {
                String attribute = standardChromaNode.getElementsByTagName("ColorSpaceType").item(0).getAttribute("name");
                if (attribute.equals("GRAY")) {
                    i3 = 1;
                } else if (attribute.equals("YCbCr") || attribute.equals("PhotoYCC")) {
                    i3 = 6;
                } else if (attribute.equals("RGB")) {
                    i3 = 2;
                } else {
                    i3 = (attribute.equals("CMYK") || attribute.equals("YCCK")) ? 5 : -1;
                }
                if (i3 != -1) {
                    tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION), i3));
                }
                if (this.hasAlpha) {
                    tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_EXTRA_SAMPLES), 3, 1, new char[]{1}));
                }
            }
        }
        if (this.app0JFIFPresent) {
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_X_RESOLUTION), 5, 1, new long[][]{new long[]{this.Xdensity, 1}}));
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_Y_RESOLUTION), 5, 1, new long[][]{new long[]{this.Ydensity, 1}}));
            int i6 = this.resUnits;
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_RESOLUTION_UNIT), (i6 == 0 || i6 == 1) ? 2 : i6 != 2 ? 1 : 3));
        }
        boolean z = this.dqtPresent;
        if (z || z) {
            if (z) {
                Iterator it = this.qtables.iterator();
                i = 2;
                while (it.hasNext()) {
                    Iterator it2 = ((List) it.next()).iterator();
                    while (it2.hasNext()) {
                        i += ((QTable) it2.next()).length + 4;
                    }
                }
            } else {
                i = 2;
            }
            if (this.dhtPresent) {
                Iterator it3 = this.htables.iterator();
                while (it3.hasNext()) {
                    Iterator it4 = ((List) it3.next()).iterator();
                    while (it4.hasNext()) {
                        i += ((HuffmanTable) it4.next()).length + 4;
                    }
                }
            }
            bArr = new byte[i + 2];
            bArr[0] = -1;
            bArr[1] = ISO7816.INS_LOAD_KEY_FILE;
            if (this.dqtPresent) {
                Iterator it5 = this.qtables.iterator();
                i2 = 2;
                while (it5.hasNext()) {
                    for (QTable qTable : (List) it5.next()) {
                        int i7 = i2 + 1;
                        bArr[i2] = -1;
                        int i8 = i7 + 1;
                        bArr[i7] = -37;
                        int i9 = qTable.length + 2;
                        int i10 = i8 + 1;
                        bArr[i8] = (byte) ((i9 & 65280) >> 8);
                        int i11 = i10 + 1;
                        bArr[i10] = (byte) (i9 & 255);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((qTable.elementPrecision & PsExtractor.VIDEO_STREAM_MASK) << 4) | (qTable.tableID & 15));
                        int[] table = qTable.table.getTable();
                        int length = table.length;
                        for (int i13 = 0; i13 < length; i13++) {
                            bArr[zigzag[i13] + i12] = (byte) table[i13];
                        }
                        i2 = i12 + length;
                    }
                }
            } else {
                i2 = 2;
            }
            if (this.dhtPresent) {
                Iterator it6 = this.htables.iterator();
                while (it6.hasNext()) {
                    for (HuffmanTable huffmanTable : (List) it6.next()) {
                        int i14 = i2 + 1;
                        bArr[i2] = -1;
                        int i15 = i14 + 1;
                        bArr[i14] = -60;
                        int i16 = huffmanTable.length + i4;
                        int i17 = i15 + 1;
                        bArr[i15] = (byte) ((i16 & 65280) >> 8);
                        int i18 = i17 + 1;
                        bArr[i17] = (byte) (i16 & 255);
                        int i19 = i18 + 1;
                        bArr[i18] = (byte) (((huffmanTable.tableClass & 15) << 4) | (huffmanTable.tableID & 15));
                        short[] lengths = huffmanTable.table.getLengths();
                        int length2 = lengths.length;
                        int i20 = 0;
                        while (i20 < length2) {
                            bArr[i19] = (byte) lengths[i20];
                            i20++;
                            i19++;
                        }
                        short[] values = huffmanTable.table.getValues();
                        int length3 = values.length;
                        int i21 = 0;
                        while (i21 < length3) {
                            bArr[i19] = (byte) values[i21];
                            i21++;
                            i19++;
                        }
                        i2 = i19;
                        i4 = 2;
                    }
                }
            }
            bArr[i2] = -1;
            bArr[i2 + 1] = -39;
        }
        if (bArr != null) {
            tIFFDirectory.addTIFFField(new TIFFField(baselineTIFFTagSet.getTag(BaselineTIFFTagSet.TAG_JPEG_TABLES), 7, bArr.length, bArr));
        }
        IIOMetadata asMetadata = tIFFDirectory.getAsMetadata();
        if (this.exifData != null) {
            try {
                Iterator imageReadersByFormatName = ImageIO.getImageReadersByFormatName("TIFF");
                if (imageReadersByFormatName != null && imageReadersByFormatName.hasNext()) {
                    ImageReader imageReader = (ImageReader) imageReadersByFormatName.next();
                    imageReader.setInput(new MemoryCacheImageInputStream(new ByteArrayInputStream(this.exifData)));
                    asMetadata.mergeTree("com_sun_media_imageio_plugins_tiff_image_1.0", imageReader.getImageMetadata(0).getAsTree("com_sun_media_imageio_plugins_tiff_image_1.0"));
                    imageReader.reset();
                }
            } catch (IOException unused) {
            }
        }
        return asMetadata.getAsTree("com_sun_media_imageio_plugins_tiff_image_1.0");
    }

    private void initializeThumbnails() {
        List list;
        BufferedImage bufferedImage;
        synchronized (this.thumbnails) {
            if (!this.thumbnailsInitialized) {
                if (this.app0JFIFPresent && (bufferedImage = this.jfifThumbnail) != null) {
                    this.thumbnails.add(bufferedImage);
                }
                if (this.app0JFXXPresent && (list = this.jfxxThumbnails) != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        this.thumbnails.add(((IIOImage) this.jfxxThumbnails.get(i)).getRenderedImage());
                    }
                }
                if (this.exifData != null) {
                    try {
                        Iterator imageReadersByFormatName = ImageIO.getImageReadersByFormatName("TIFF");
                        if (imageReadersByFormatName != null && imageReadersByFormatName.hasNext()) {
                            ImageReader imageReader = (ImageReader) imageReadersByFormatName.next();
                            imageReader.setInput(new MemoryCacheImageInputStream(new ByteArrayInputStream(this.exifData)));
                            if (imageReader.getNumImages(true) > 1) {
                                this.thumbnails.add(imageReader.read(1, (ImageReadParam) null));
                            }
                            imageReader.reset();
                        }
                    } catch (IOException unused) {
                    }
                }
                this.thumbnailsInitialized = true;
            }
        }
    }

    int getNumThumbnails() throws IOException {
        initializeThumbnails();
        return this.thumbnails.size();
    }

    BufferedImage getThumbnail(int i) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("thumbnailIndex < 0!");
        }
        initializeThumbnails();
        if (i >= this.thumbnails.size()) {
            throw new IndexOutOfBoundsException("thumbnailIndex > getNumThumbnails()");
        }
        return (BufferedImage) this.thumbnails.get(i);
    }
}
