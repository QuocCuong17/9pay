package com.github.jaiimageio.plugins.tiff;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.Profile;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class BaselineTIFFTagSet extends TIFFTagSet {
    public static final int COMPRESSION_CCITT_RLE = 2;
    public static final int COMPRESSION_CCITT_T_4 = 3;
    public static final int COMPRESSION_CCITT_T_6 = 4;
    public static final int COMPRESSION_DEFLATE = 32946;
    public static final int COMPRESSION_JPEG = 7;
    public static final int COMPRESSION_LZW = 5;
    public static final int COMPRESSION_NONE = 1;
    public static final int COMPRESSION_OLD_JPEG = 6;
    public static final int COMPRESSION_PACKBITS = 32773;
    public static final int COMPRESSION_ZLIB = 8;
    public static final int EXTRA_SAMPLES_ASSOCIATED_ALPHA = 1;
    public static final int EXTRA_SAMPLES_UNASSOCIATED_ALPHA = 2;
    public static final int EXTRA_SAMPLES_UNSPECIFIED = 0;
    public static final int FILL_ORDER_LEFT_TO_RIGHT = 1;
    public static final int FILL_ORDER_RIGHT_TO_LEFT = 2;
    public static final int GRAY_RESPONSE_UNIT_HUNDREDTHS = 2;
    public static final int GRAY_RESPONSE_UNIT_HUNDRED_THOUSANDTHS = 5;
    public static final int GRAY_RESPONSE_UNIT_TENTHS = 1;
    public static final int GRAY_RESPONSE_UNIT_TEN_THOUSANDTHS = 4;
    public static final int GRAY_RESPONSE_UNIT_THOUSANDTHS = 3;
    public static final int INK_SET_CMYK = 1;
    public static final int INK_SET_NOT_CMYK = 2;
    public static final int JPEG_PROC_BASELINE = 1;
    public static final int JPEG_PROC_LOSSLESS = 14;
    public static final int NEW_SUBFILE_TYPE_REDUCED_RESOLUTION = 1;
    public static final int NEW_SUBFILE_TYPE_SINGLE_PAGE = 2;
    public static final int NEW_SUBFILE_TYPE_TRANSPARENCY = 4;
    public static final int ORIENTATION_ROW_0_BOTTOM_COLUMN_0_LEFT = 4;
    public static final int ORIENTATION_ROW_0_BOTTOM_COLUMN_0_RIGHT = 3;
    public static final int ORIENTATION_ROW_0_LEFT_COLUMN_0_BOTTOM = 8;
    public static final int ORIENTATION_ROW_0_LEFT_COLUMN_0_TOP = 5;
    public static final int ORIENTATION_ROW_0_RIGHT_COLUMN_0_BOTTOM = 7;
    public static final int ORIENTATION_ROW_0_RIGHT_COLUMN_0_TOP = 6;
    public static final int ORIENTATION_ROW_0_TOP_COLUMN_0_LEFT = 1;
    public static final int ORIENTATION_ROW_0_TOP_COLUMN_0_RIGHT = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_CIELAB = 8;
    public static final int PHOTOMETRIC_INTERPRETATION_CMYK = 5;
    public static final int PHOTOMETRIC_INTERPRETATION_ICCLAB = 9;
    public static final int PHOTOMETRIC_INTERPRETATION_PALETTE_COLOR = 3;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_TRANSPARENCY_MASK = 4;
    public static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_Y_CB_CR = 6;
    public static final int PLANAR_CONFIGURATION_CHUNKY = 1;
    public static final int PLANAR_CONFIGURATION_PLANAR = 2;
    public static final int PREDICTOR_HORIZONTAL_DIFFERENCING = 2;
    public static final int PREDICTOR_NONE = 1;
    public static final int RESOLUTION_UNIT_CENTIMETER = 3;
    public static final int RESOLUTION_UNIT_INCH = 2;
    public static final int RESOLUTION_UNIT_NONE = 1;
    public static final int SAMPLE_FORMAT_FLOATING_POINT = 3;
    public static final int SAMPLE_FORMAT_SIGNED_INTEGER = 2;
    public static final int SAMPLE_FORMAT_UNDEFINED = 4;
    public static final int SAMPLE_FORMAT_UNSIGNED_INTEGER = 1;
    public static final int SUBFILE_TYPE_FULL_RESOLUTION = 1;
    public static final int SUBFILE_TYPE_REDUCED_RESOLUTION = 2;
    public static final int SUBFILE_TYPE_SINGLE_PAGE = 3;
    public static final int T4_OPTIONS_2D_CODING = 1;
    public static final int T4_OPTIONS_EOL_BYTE_ALIGNED = 4;
    public static final int T4_OPTIONS_UNCOMPRESSED = 2;
    public static final int T6_OPTIONS_UNCOMPRESSED = 2;
    public static final int TAG_ARTIST = 315;
    public static final int TAG_BITS_PER_SAMPLE = 258;
    public static final int TAG_CELL_LENGTH = 265;
    public static final int TAG_CELL_WIDTH = 264;
    public static final int TAG_COLOR_MAP = 320;
    public static final int TAG_COMPRESSION = 259;
    public static final int TAG_COPYRIGHT = 33432;
    public static final int TAG_DATE_TIME = 306;
    public static final int TAG_DOCUMENT_NAME = 269;
    public static final int TAG_DOT_RANGE = 336;
    public static final int TAG_EXTRA_SAMPLES = 338;
    public static final int TAG_FILL_ORDER = 266;
    public static final int TAG_FREE_BYTE_COUNTS = 289;
    public static final int TAG_FREE_OFFSETS = 288;
    public static final int TAG_GRAY_RESPONSE_CURVE = 291;
    public static final int TAG_GRAY_RESPONSE_UNIT = 290;
    public static final int TAG_HALFTONE_HINTS = 321;
    public static final int TAG_HOST_COMPUTER = 316;
    public static final int TAG_ICC_PROFILE = 34675;
    public static final int TAG_IMAGE_DESCRIPTION = 270;
    public static final int TAG_IMAGE_LENGTH = 257;
    public static final int TAG_IMAGE_WIDTH = 256;
    public static final int TAG_INK_NAMES = 333;
    public static final int TAG_INK_SET = 332;
    public static final int TAG_JPEG_AC_TABLES = 521;
    public static final int TAG_JPEG_DC_TABLES = 520;
    public static final int TAG_JPEG_INTERCHANGE_FORMAT = 513;
    public static final int TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = 514;
    public static final int TAG_JPEG_LOSSLESS_PREDICTORS = 517;
    public static final int TAG_JPEG_POINT_TRANSFORMS = 518;
    public static final int TAG_JPEG_PROC = 512;
    public static final int TAG_JPEG_Q_TABLES = 519;
    public static final int TAG_JPEG_RESTART_INTERVAL = 515;
    public static final int TAG_JPEG_TABLES = 347;
    public static final int TAG_MAKE = 271;
    public static final int TAG_MAX_SAMPLE_VALUE = 281;
    public static final int TAG_MIN_SAMPLE_VALUE = 280;
    public static final int TAG_MODEL = 272;
    public static final int TAG_NEW_SUBFILE_TYPE = 254;
    public static final int TAG_NUMBER_OF_INKS = 334;
    public static final int TAG_ORIENTATION = 274;
    public static final int TAG_PAGE_NAME = 285;
    public static final int TAG_PAGE_NUMBER = 297;
    public static final int TAG_PHOTOMETRIC_INTERPRETATION = 262;
    public static final int TAG_PLANAR_CONFIGURATION = 284;
    public static final int TAG_PREDICTOR = 317;
    public static final int TAG_PRIMARY_CHROMATICITES = 319;
    public static final int TAG_REFERENCE_BLACK_WHITE = 532;
    public static final int TAG_RESOLUTION_UNIT = 296;
    public static final int TAG_ROWS_PER_STRIP = 278;
    public static final int TAG_SAMPLES_PER_PIXEL = 277;
    public static final int TAG_SAMPLE_FORMAT = 339;
    public static final int TAG_SOFTWARE = 305;
    public static final int TAG_STRIP_BYTE_COUNTS = 279;
    public static final int TAG_STRIP_OFFSETS = 273;
    public static final int TAG_SUBFILE_TYPE = 255;
    public static final int TAG_S_MAX_SAMPLE_VALUE = 341;
    public static final int TAG_S_MIN_SAMPLE_VALUE = 340;
    public static final int TAG_T4_OPTIONS = 292;
    public static final int TAG_T6_OPTIONS = 293;
    public static final int TAG_TARGET_PRINTER = 337;
    public static final int TAG_THRESHHOLDING = 263;
    public static final int TAG_TILE_BYTE_COUNTS = 325;
    public static final int TAG_TILE_LENGTH = 323;
    public static final int TAG_TILE_OFFSETS = 324;
    public static final int TAG_TILE_WIDTH = 322;
    public static final int TAG_TRANSFER_FUNCTION = 301;
    public static final int TAG_TRANSFER_RANGE = 342;
    public static final int TAG_WHITE_POINT = 318;
    public static final int TAG_X_POSITION = 286;
    public static final int TAG_X_RESOLUTION = 282;
    public static final int TAG_Y_CB_CR_COEFFICIENTS = 529;
    public static final int TAG_Y_CB_CR_POSITIONING = 531;
    public static final int TAG_Y_CB_CR_SUBSAMPLING = 530;
    public static final int TAG_Y_POSITION = 287;
    public static final int TAG_Y_RESOLUTION = 283;
    public static final int THRESHHOLDING_NONE = 1;
    public static final int THRESHHOLDING_ORDERED_DITHER = 2;
    public static final int THRESHHOLDING_RANDOMIZED_DITHER = 3;
    public static final int Y_CB_CR_POSITIONING_CENTERED = 1;
    public static final int Y_CB_CR_POSITIONING_COSITED = 2;
    private static List tags;
    private static BaselineTIFFTagSet theInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Artist extends TIFFTag {
        public Artist() {
            super(ExifInterface.TAG_ARTIST, 315, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class BitsPerSample extends TIFFTag {
        public BitsPerSample() {
            super(ExifInterface.TAG_BITS_PER_SAMPLE, 258, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class CellLength extends TIFFTag {
        public CellLength() {
            super("CellLength", BaselineTIFFTagSet.TAG_CELL_LENGTH, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class CellWidth extends TIFFTag {
        public CellWidth() {
            super("CellWidth", BaselineTIFFTagSet.TAG_CELL_WIDTH, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ColorMap extends TIFFTag {
        public ColorMap() {
            super("ColorMap", BaselineTIFFTagSet.TAG_COLOR_MAP, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Compression extends TIFFTag {
        public Compression() {
            super(ExifInterface.TAG_COMPRESSION, BaselineTIFFTagSet.TAG_COMPRESSION, 8);
            addValueName(1, "Uncompressed");
            addValueName(2, "CCITT RLE");
            addValueName(3, "CCITT T.4");
            addValueName(4, "CCITT T.6");
            addValueName(5, "LZW");
            addValueName(6, "Old JPEG");
            addValueName(7, "JPEG");
            addValueName(8, "ZLib");
            addValueName(32773, "PackBits");
            addValueName(BaselineTIFFTagSet.COMPRESSION_DEFLATE, "Deflate");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Copyright extends TIFFTag {
        public Copyright() {
            super(ExifInterface.TAG_COPYRIGHT, BaselineTIFFTagSet.TAG_COPYRIGHT, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DateTime extends TIFFTag {
        public DateTime() {
            super(ExifInterface.TAG_DATETIME, 306, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DocumentName extends TIFFTag {
        public DocumentName() {
            super("DocumentName", BaselineTIFFTagSet.TAG_DOCUMENT_NAME, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DotRange extends TIFFTag {
        public DotRange() {
            super("DotRange", BaselineTIFFTagSet.TAG_DOT_RANGE, 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExtraSamples extends TIFFTag {
        public ExtraSamples() {
            super("ExtraSamples", BaselineTIFFTagSet.TAG_EXTRA_SAMPLES, 8);
            addValueName(0, "Unspecified");
            addValueName(1, "Associated Alpha");
            addValueName(2, "Unassociated Alpha");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FillOrder extends TIFFTag {
        public FillOrder() {
            super("FillOrder", BaselineTIFFTagSet.TAG_FILL_ORDER, 8);
            addValueName(1, "LeftToRight");
            addValueName(2, "RightToLeft");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FreeByteCounts extends TIFFTag {
        public FreeByteCounts() {
            super("FreeByteCounts", BaselineTIFFTagSet.TAG_FREE_BYTE_COUNTS, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FreeOffsets extends TIFFTag {
        public FreeOffsets() {
            super("FreeOffsets", BaselineTIFFTagSet.TAG_FREE_OFFSETS, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GrayResponseCurve extends TIFFTag {
        public GrayResponseCurve() {
            super("GrayResponseCurve", BaselineTIFFTagSet.TAG_GRAY_RESPONSE_CURVE, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GrayResponseUnit extends TIFFTag {
        public GrayResponseUnit() {
            super("GrayResponseUnit", BaselineTIFFTagSet.TAG_GRAY_RESPONSE_UNIT, 8);
            addValueName(1, "Tenths");
            addValueName(2, "Hundredths");
            addValueName(3, "Thousandths");
            addValueName(4, "Ten-Thousandths");
            addValueName(5, "Hundred-Thousandths");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class HalftoneHints extends TIFFTag {
        public HalftoneHints() {
            super("HalftoneHints", BaselineTIFFTagSet.TAG_HALFTONE_HINTS, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class HostComputer extends TIFFTag {
        public HostComputer() {
            super("HostComputer", 316, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImageDescription extends TIFFTag {
        public ImageDescription() {
            super(ExifInterface.TAG_IMAGE_DESCRIPTION, 270, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImageLength extends TIFFTag {
        public ImageLength() {
            super(ExifInterface.TAG_IMAGE_LENGTH, 257, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImageWidth extends TIFFTag {
        public ImageWidth() {
            super(ExifInterface.TAG_IMAGE_WIDTH, 256, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class InkNames extends TIFFTag {
        public InkNames() {
            super("InkNames", BaselineTIFFTagSet.TAG_INK_NAMES, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class InkSet extends TIFFTag {
        public InkSet() {
            super("InkSet", BaselineTIFFTagSet.TAG_INK_SET, 8);
            addValueName(1, "CMYK");
            addValueName(2, "Not CMYK");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGTables extends TIFFTag {
        public JPEGTables() {
            super("JPEGTables", BaselineTIFFTagSet.TAG_JPEG_TABLES, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGACTables extends TIFFTag {
        public JPEGACTables() {
            super("JPEGACTables", BaselineTIFFTagSet.TAG_JPEG_AC_TABLES, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGDCTables extends TIFFTag {
        public JPEGDCTables() {
            super("JPEGDCTables", BaselineTIFFTagSet.TAG_JPEG_DC_TABLES, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGInterchangeFormat extends TIFFTag {
        public JPEGInterchangeFormat() {
            super(ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT, 513, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGInterchangeFormatLength extends TIFFTag {
        public JPEGInterchangeFormatLength() {
            super(ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, BaselineTIFFTagSet.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGLosslessPredictors extends TIFFTag {
        public JPEGLosslessPredictors() {
            super("JPEGLosslessPredictors", BaselineTIFFTagSet.TAG_JPEG_LOSSLESS_PREDICTORS, 8);
            addValueName(1, "A");
            addValueName(2, "B");
            addValueName(3, "C");
            addValueName(4, "A+B-C");
            addValueName(5, "A+((B-C)/2)");
            addValueName(6, "B+((A-C)/2)");
            addValueName(7, "(A+B)/2");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGPointTransforms extends TIFFTag {
        public JPEGPointTransforms() {
            super("JPEGPointTransforms", BaselineTIFFTagSet.TAG_JPEG_POINT_TRANSFORMS, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGProc extends TIFFTag {
        public JPEGProc() {
            super("JPEGProc", 512, 8);
            addValueName(1, "Baseline sequential process");
            addValueName(14, "Lossless process with Huffman coding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGQTables extends TIFFTag {
        public JPEGQTables() {
            super("JPEGQTables", BaselineTIFFTagSet.TAG_JPEG_Q_TABLES, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class JPEGRestartInterval extends TIFFTag {
        public JPEGRestartInterval() {
            super("JPEGRestartInterval", BaselineTIFFTagSet.TAG_JPEG_RESTART_INTERVAL, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Make extends TIFFTag {
        public Make() {
            super(ExifInterface.TAG_MAKE, BaselineTIFFTagSet.TAG_MAKE, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MaxSampleValue extends TIFFTag {
        public MaxSampleValue() {
            super("MaxSampleValue", BaselineTIFFTagSet.TAG_MAX_SAMPLE_VALUE, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MinSampleValue extends TIFFTag {
        public MinSampleValue() {
            super("MinSampleValue", BaselineTIFFTagSet.TAG_MIN_SAMPLE_VALUE, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Model extends TIFFTag {
        public Model() {
            super(ExifInterface.TAG_MODEL, BaselineTIFFTagSet.TAG_MODEL, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class NewSubfileType extends TIFFTag {
        public NewSubfileType() {
            super(ExifInterface.TAG_NEW_SUBFILE_TYPE, 254, 16);
            addValueName(0, Profile.DEFAULT_PROFILE_NAME);
            addValueName(1, "ReducedResolution");
            addValueName(2, "SinglePage");
            addValueName(3, "SinglePage+ReducedResolution");
            addValueName(4, "Transparency");
            addValueName(5, "Transparency+ReducedResolution");
            addValueName(6, "Transparency+SinglePage");
            addValueName(7, "Transparency+SinglePage+ReducedResolution");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class NumberOfInks extends TIFFTag {
        public NumberOfInks() {
            super("NumberOfInks", BaselineTIFFTagSet.TAG_NUMBER_OF_INKS, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Orientation extends TIFFTag {
        public Orientation() {
            super(ExifInterface.TAG_ORIENTATION, BaselineTIFFTagSet.TAG_ORIENTATION, 8);
            addValueName(1, "Row 0=Top, Column 0=Left");
            addValueName(2, "Row 0=Top, Column 0=Right");
            addValueName(3, "Row 0=Bottom, Column 0=Right");
            addValueName(4, "Row 0=Bottom, Column 0=Left");
            addValueName(5, "Row 0=Left, Column 0=Top");
            addValueName(6, "Row 0=Right, Column 0=Top");
            addValueName(7, "Row 0=Right, Column 0=Bottom");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PageName extends TIFFTag {
        public PageName() {
            super("PageName", BaselineTIFFTagSet.TAG_PAGE_NAME, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PageNumber extends TIFFTag {
        public PageNumber() {
            super("PageNumber", BaselineTIFFTagSet.TAG_PAGE_NUMBER, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PhotometricInterpretation extends TIFFTag {
        public PhotometricInterpretation() {
            super(ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION, BaselineTIFFTagSet.TAG_PHOTOMETRIC_INTERPRETATION, 8);
            addValueName(0, "WhiteIsZero");
            addValueName(1, "BlackIsZero");
            addValueName(2, "RGB");
            addValueName(3, "Palette Color");
            addValueName(4, "Transparency Mask");
            addValueName(5, "CMYK");
            addValueName(6, "YCbCr");
            addValueName(8, "CIELAB");
            addValueName(9, "ICCLAB");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PlanarConfiguration extends TIFFTag {
        public PlanarConfiguration() {
            super(ExifInterface.TAG_PLANAR_CONFIGURATION, BaselineTIFFTagSet.TAG_PLANAR_CONFIGURATION, 8);
            addValueName(1, "Chunky");
            addValueName(2, "Planar");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Predictor extends TIFFTag {
        public Predictor() {
            super("Predictor", 317, 8);
            addValueName(1, "None");
            addValueName(2, "Horizontal Differencing");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PrimaryChromaticities extends TIFFTag {
        public PrimaryChromaticities() {
            super(ExifInterface.TAG_PRIMARY_CHROMATICITIES, BaselineTIFFTagSet.TAG_PRIMARY_CHROMATICITES, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ReferenceBlackWhite extends TIFFTag {
        public ReferenceBlackWhite() {
            super(ExifInterface.TAG_REFERENCE_BLACK_WHITE, BaselineTIFFTagSet.TAG_REFERENCE_BLACK_WHITE, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ResolutionUnit extends TIFFTag {
        public ResolutionUnit() {
            super(ExifInterface.TAG_RESOLUTION_UNIT, BaselineTIFFTagSet.TAG_RESOLUTION_UNIT, 8);
            addValueName(1, "None");
            addValueName(2, "Inch");
            addValueName(3, "Centimeter");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class RowsPerStrip extends TIFFTag {
        public RowsPerStrip() {
            super(ExifInterface.TAG_ROWS_PER_STRIP, BaselineTIFFTagSet.TAG_ROWS_PER_STRIP, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SampleFormat extends TIFFTag {
        public SampleFormat() {
            super("SampleFormat", BaselineTIFFTagSet.TAG_SAMPLE_FORMAT, 8);
            addValueName(1, "Unsigned Integer");
            addValueName(2, "Signed Integer");
            addValueName(3, "Floating Point");
            addValueName(4, "Undefined");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SamplesPerPixel extends TIFFTag {
        public SamplesPerPixel() {
            super(ExifInterface.TAG_SAMPLES_PER_PIXEL, BaselineTIFFTagSet.TAG_SAMPLES_PER_PIXEL, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SMaxSampleValue extends TIFFTag {
        public SMaxSampleValue() {
            super("SMaxSampleValue", 341, 8058);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SMinSampleValue extends TIFFTag {
        public SMinSampleValue() {
            super("SMinSampleValue", BaselineTIFFTagSet.TAG_S_MIN_SAMPLE_VALUE, 8058);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Software extends TIFFTag {
        public Software() {
            super(ExifInterface.TAG_SOFTWARE, 305, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class StripByteCounts extends TIFFTag {
        public StripByteCounts() {
            super(ExifInterface.TAG_STRIP_BYTE_COUNTS, BaselineTIFFTagSet.TAG_STRIP_BYTE_COUNTS, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class StripOffsets extends TIFFTag {
        public StripOffsets() {
            super(ExifInterface.TAG_STRIP_OFFSETS, BaselineTIFFTagSet.TAG_STRIP_OFFSETS, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubfileType extends TIFFTag {
        public SubfileType() {
            super(ExifInterface.TAG_SUBFILE_TYPE, 255, 8);
            addValueName(1, "FullResolution");
            addValueName(2, "ReducedResolution");
            addValueName(3, "SinglePage");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class T4Options extends TIFFTag {
        public T4Options() {
            super("T4Options", BaselineTIFFTagSet.TAG_T4_OPTIONS, 16);
            addValueName(0, "Default 1DCoding");
            addValueName(1, "2DCoding");
            addValueName(2, "Uncompressed");
            addValueName(3, "2DCoding+Uncompressed");
            addValueName(4, "EOLByteAligned");
            addValueName(5, "2DCoding+EOLByteAligned");
            addValueName(6, "Uncompressed+EOLByteAligned");
            addValueName(7, "2DCoding+Uncompressed+EOLByteAligned");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class T6Options extends TIFFTag {
        public T6Options() {
            super("T6Options", BaselineTIFFTagSet.TAG_T6_OPTIONS, 16);
            addValueName(0, Profile.DEFAULT_PROFILE_NAME);
            addValueName(2, "Uncompressed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TargetPrinter extends TIFFTag {
        public TargetPrinter() {
            super("TargetPrinter", BaselineTIFFTagSet.TAG_TARGET_PRINTER, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Threshholding extends TIFFTag {
        public Threshholding() {
            super("Threshholding", BaselineTIFFTagSet.TAG_THRESHHOLDING, 8);
            addValueName(1, "None");
            addValueName(2, "OrderedDither");
            addValueName(3, "RandomizedDither");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TileByteCounts extends TIFFTag {
        public TileByteCounts() {
            super("TileByteCounts", BaselineTIFFTagSet.TAG_TILE_BYTE_COUNTS, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TileOffsets extends TIFFTag {
        public TileOffsets() {
            super("TileOffsets", BaselineTIFFTagSet.TAG_TILE_OFFSETS, 16);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TileLength extends TIFFTag {
        public TileLength() {
            super("TileLength", BaselineTIFFTagSet.TAG_TILE_LENGTH, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TileWidth extends TIFFTag {
        public TileWidth() {
            super("TileWidth", BaselineTIFFTagSet.TAG_TILE_WIDTH, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TransferFunction extends TIFFTag {
        public TransferFunction() {
            super(ExifInterface.TAG_TRANSFER_FUNCTION, 301, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class TransferRange extends TIFFTag {
        public TransferRange() {
            super("TransferRange", BaselineTIFFTagSet.TAG_TRANSFER_RANGE, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class WhitePoint extends TIFFTag {
        public WhitePoint() {
            super(ExifInterface.TAG_WHITE_POINT, 318, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class XPosition extends TIFFTag {
        public XPosition() {
            super("XPosition", BaselineTIFFTagSet.TAG_X_POSITION, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class XResolution extends TIFFTag {
        public XResolution() {
            super(ExifInterface.TAG_X_RESOLUTION, BaselineTIFFTagSet.TAG_X_RESOLUTION, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class YCbCrCoefficients extends TIFFTag {
        public YCbCrCoefficients() {
            super(ExifInterface.TAG_Y_CB_CR_COEFFICIENTS, BaselineTIFFTagSet.TAG_Y_CB_CR_COEFFICIENTS, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class YCbCrPositioning extends TIFFTag {
        public YCbCrPositioning() {
            super(ExifInterface.TAG_Y_CB_CR_POSITIONING, BaselineTIFFTagSet.TAG_Y_CB_CR_POSITIONING, 8);
            addValueName(1, "Centered");
            addValueName(2, "Cosited");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class YCbCrSubSampling extends TIFFTag {
        public YCbCrSubSampling() {
            super(ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING, BaselineTIFFTagSet.TAG_Y_CB_CR_SUBSAMPLING, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class YPosition extends TIFFTag {
        public YPosition() {
            super("YPosition", BaselineTIFFTagSet.TAG_Y_POSITION, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class YResolution extends TIFFTag {
        public YResolution() {
            super(ExifInterface.TAG_Y_RESOLUTION, BaselineTIFFTagSet.TAG_Y_RESOLUTION, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ICCProfile extends TIFFTag {
        public ICCProfile() {
            super("ICC Profile", BaselineTIFFTagSet.TAG_ICC_PROFILE, 128);
        }
    }

    private static void initTags() {
        ArrayList arrayList = new ArrayList(76);
        tags = arrayList;
        arrayList.add(new Artist());
        tags.add(new BitsPerSample());
        tags.add(new CellLength());
        tags.add(new CellWidth());
        tags.add(new ColorMap());
        tags.add(new Compression());
        tags.add(new Copyright());
        tags.add(new DateTime());
        tags.add(new DocumentName());
        tags.add(new DotRange());
        tags.add(new ExtraSamples());
        tags.add(new FillOrder());
        tags.add(new FreeByteCounts());
        tags.add(new FreeOffsets());
        tags.add(new GrayResponseCurve());
        tags.add(new GrayResponseUnit());
        tags.add(new HalftoneHints());
        tags.add(new HostComputer());
        tags.add(new ImageDescription());
        tags.add(new ICCProfile());
        tags.add(new ImageLength());
        tags.add(new ImageWidth());
        tags.add(new InkNames());
        tags.add(new InkSet());
        tags.add(new JPEGACTables());
        tags.add(new JPEGDCTables());
        tags.add(new JPEGInterchangeFormat());
        tags.add(new JPEGInterchangeFormatLength());
        tags.add(new JPEGLosslessPredictors());
        tags.add(new JPEGPointTransforms());
        tags.add(new JPEGProc());
        tags.add(new JPEGQTables());
        tags.add(new JPEGRestartInterval());
        tags.add(new JPEGTables());
        tags.add(new Make());
        tags.add(new MaxSampleValue());
        tags.add(new MinSampleValue());
        tags.add(new Model());
        tags.add(new NewSubfileType());
        tags.add(new NumberOfInks());
        tags.add(new Orientation());
        tags.add(new PageName());
        tags.add(new PageNumber());
        tags.add(new PhotometricInterpretation());
        tags.add(new PlanarConfiguration());
        tags.add(new Predictor());
        tags.add(new PrimaryChromaticities());
        tags.add(new ReferenceBlackWhite());
        tags.add(new ResolutionUnit());
        tags.add(new RowsPerStrip());
        tags.add(new SampleFormat());
        tags.add(new SamplesPerPixel());
        tags.add(new SMaxSampleValue());
        tags.add(new SMinSampleValue());
        tags.add(new Software());
        tags.add(new StripByteCounts());
        tags.add(new StripOffsets());
        tags.add(new SubfileType());
        tags.add(new T4Options());
        tags.add(new T6Options());
        tags.add(new TargetPrinter());
        tags.add(new Threshholding());
        tags.add(new TileByteCounts());
        tags.add(new TileOffsets());
        tags.add(new TileLength());
        tags.add(new TileWidth());
        tags.add(new TransferFunction());
        tags.add(new TransferRange());
        tags.add(new WhitePoint());
        tags.add(new XPosition());
        tags.add(new XResolution());
        tags.add(new YCbCrCoefficients());
        tags.add(new YCbCrPositioning());
        tags.add(new YCbCrSubSampling());
        tags.add(new YPosition());
        tags.add(new YResolution());
    }

    private BaselineTIFFTagSet() {
        super(tags);
    }

    public static synchronized BaselineTIFFTagSet getInstance() {
        BaselineTIFFTagSet baselineTIFFTagSet;
        synchronized (BaselineTIFFTagSet.class) {
            if (theInstance == null) {
                initTags();
                theInstance = new BaselineTIFFTagSet();
                tags = null;
            }
            baselineTIFFTagSet = theInstance;
        }
        return baselineTIFFTagSet;
    }
}
