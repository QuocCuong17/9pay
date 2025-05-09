package com.github.jaiimageio.plugins.tiff;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.internal.AnalyticsEvents;
import java.util.ArrayList;
import java.util.List;
import net.sf.scuba.smartcards.ISO7816;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;

/* loaded from: classes3.dex */
public class EXIFTIFFTagSet extends TIFFTagSet {
    public static final int COLOR_SPACE_SRGB = 1;
    public static final int COLOR_SPACE_UNCALIBRATED = 65535;
    public static final int COMPONENTS_CONFIGURATION_B = 6;
    public static final int COMPONENTS_CONFIGURATION_CB = 2;
    public static final int COMPONENTS_CONFIGURATION_CR = 3;
    public static final int COMPONENTS_CONFIGURATION_DOES_NOT_EXIST = 0;
    public static final int COMPONENTS_CONFIGURATION_G = 5;
    public static final int COMPONENTS_CONFIGURATION_R = 4;
    public static final int COMPONENTS_CONFIGURATION_Y = 1;
    public static final int CONTRAST_HARD = 2;
    public static final int CONTRAST_NORMAL = 0;
    public static final int CONTRAST_SOFT = 1;
    public static final int CUSTOM_RENDERED_CUSTOM = 1;
    public static final int CUSTOM_RENDERED_NORMAL = 0;
    public static byte[] EXIF_VERSION_2_1 = {ISO7816.INS_DECREASE, ISO7816.INS_INCREASE, 49, ISO7816.INS_DECREASE};
    public static byte[] EXIF_VERSION_2_2 = {ISO7816.INS_DECREASE, ISO7816.INS_INCREASE, ISO7816.INS_INCREASE, ISO7816.INS_DECREASE};
    public static final int EXPOSURE_MODE_AUTO_BRACKET = 2;
    public static final int EXPOSURE_MODE_AUTO_EXPOSURE = 0;
    public static final int EXPOSURE_MODE_MANUAL_EXPOSURE = 1;
    public static final int EXPOSURE_PROGRAM_ACTION_PROGRAM = 6;
    public static final int EXPOSURE_PROGRAM_APERTURE_PRIORITY = 3;
    public static final int EXPOSURE_PROGRAM_CREATIVE_PROGRAM = 5;
    public static final int EXPOSURE_PROGRAM_LANDSCAPE_MODE = 8;
    public static final int EXPOSURE_PROGRAM_MANUAL = 1;
    public static final int EXPOSURE_PROGRAM_MAX_RESERVED = 255;
    public static final int EXPOSURE_PROGRAM_NORMAL_PROGRAM = 2;
    public static final int EXPOSURE_PROGRAM_NOT_DEFINED = 0;
    public static final int EXPOSURE_PROGRAM_PORTRAIT_MODE = 7;
    public static final int EXPOSURE_PROGRAM_SHUTTER_PRIORITY = 4;
    public static final int FILE_SOURCE_DSC = 3;
    public static final int FLASH_DID_NOT_FIRE = 0;
    public static final int FLASH_FIRED = 1;
    public static final int FLASH_MASK_FIRED = 1;
    public static final int FLASH_MASK_FUNCTION_NOT_PRESENT = 32;
    public static final int FLASH_MASK_MODE_AUTO = 24;
    public static final int FLASH_MASK_MODE_FLASH_FIRING = 8;
    public static final int FLASH_MASK_MODE_FLASH_SUPPRESSION = 16;
    public static final int FLASH_MASK_RED_EYE_REDUCTION = 64;
    public static final int FLASH_MASK_RETURN_DETECTED = 6;
    public static final int FLASH_MASK_RETURN_NOT_DETECTED = 4;
    public static final int FLASH_STROBE_RETURN_LIGHT_DETECTED = 7;
    public static final int FLASH_STROBE_RETURN_LIGHT_NOT_DETECTED = 5;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_CENTIMETER = 3;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_INCH = 2;
    public static final int FOCAL_PLANE_RESOLUTION_UNIT_NONE = 1;
    public static final int GAIN_CONTROL_HIGH_GAIN_DOWN = 4;
    public static final int GAIN_CONTROL_HIGH_GAIN_UP = 2;
    public static final int GAIN_CONTROL_LOW_GAIN_DOWN = 3;
    public static final int GAIN_CONTROL_LOW_GAIN_UP = 1;
    public static final int GAIN_CONTROL_NONE = 0;
    public static final int LIGHT_SOURCE_CLOUDY_WEATHER = 10;
    public static final int LIGHT_SOURCE_COOL_WHITE_FLUORESCENT = 14;
    public static final int LIGHT_SOURCE_D50 = 23;
    public static final int LIGHT_SOURCE_D55 = 20;
    public static final int LIGHT_SOURCE_D65 = 21;
    public static final int LIGHT_SOURCE_D75 = 22;
    public static final int LIGHT_SOURCE_DAYLIGHT = 1;
    public static final int LIGHT_SOURCE_DAYLIGHT_FLUORESCENT = 12;
    public static final int LIGHT_SOURCE_DAY_WHITE_FLUORESCENT = 13;
    public static final int LIGHT_SOURCE_FINE_WEATHER = 9;
    public static final int LIGHT_SOURCE_FLASH = 4;
    public static final int LIGHT_SOURCE_FLUORESCENT = 2;
    public static final int LIGHT_SOURCE_ISO_STUDIO_TUNGSTEN = 24;
    public static final int LIGHT_SOURCE_OTHER = 255;
    public static final int LIGHT_SOURCE_SHADE = 11;
    public static final int LIGHT_SOURCE_STANDARD_LIGHT_A = 17;
    public static final int LIGHT_SOURCE_STANDARD_LIGHT_B = 18;
    public static final int LIGHT_SOURCE_STANDARD_LIGHT_C = 19;
    public static final int LIGHT_SOURCE_TUNGSTEN = 3;
    public static final int LIGHT_SOURCE_UNKNOWN = 0;
    public static final int LIGHT_SOURCE_WHITE_FLUORESCENT = 15;
    public static final int METERING_MODE_AVERAGE = 1;
    public static final int METERING_MODE_CENTER_WEIGHTED_AVERAGE = 2;
    public static final int METERING_MODE_MAX_RESERVED = 254;
    public static final int METERING_MODE_MIN_RESERVED = 7;
    public static final int METERING_MODE_MULTI_SPOT = 4;
    public static final int METERING_MODE_OTHER = 255;
    public static final int METERING_MODE_PARTIAL = 6;
    public static final int METERING_MODE_PATTERN = 5;
    public static final int METERING_MODE_SPOT = 3;
    public static final int METERING_MODE_UNKNOWN = 0;
    public static final int SATURATION_HIGH = 2;
    public static final int SATURATION_LOW = 1;
    public static final int SATURATION_NORMAL = 0;
    public static final int SCENE_CAPTURE_TYPE_LANDSCAPE = 1;
    public static final int SCENE_CAPTURE_TYPE_NIGHT_SCENE = 3;
    public static final int SCENE_CAPTURE_TYPE_PORTRAIT = 2;
    public static final int SCENE_CAPTURE_TYPE_STANDARD = 0;
    public static final int SCENE_TYPE_DSC = 1;
    public static final int SENSING_METHOD_COLOR_SEQUENTIAL_AREA_SENSOR = 5;
    public static final int SENSING_METHOD_COLOR_SEQUENTIAL_LINEAR_SENSOR = 8;
    public static final int SENSING_METHOD_NOT_DEFINED = 1;
    public static final int SENSING_METHOD_ONE_CHIP_COLOR_AREA_SENSOR = 2;
    public static final int SENSING_METHOD_THREE_CHIP_COLOR_AREA_SENSOR = 4;
    public static final int SENSING_METHOD_TRILINEAR_SENSOR = 7;
    public static final int SENSING_METHOD_TWO_CHIP_COLOR_AREA_SENSOR = 3;
    public static final int SHARPNESS_HARD = 2;
    public static final int SHARPNESS_NORMAL = 0;
    public static final int SHARPNESS_SOFT = 1;
    public static final int SUBJECT_DISTANCE_RANGE_CLOSE_VIEW = 2;
    public static final int SUBJECT_DISTANCE_RANGE_DISTANT_VIEW = 3;
    public static final int SUBJECT_DISTANCE_RANGE_MACRO = 1;
    public static final int SUBJECT_DISTANCE_RANGE_UNKNOWN = 0;
    public static final int TAG_APERTURE_VALUE = 37378;
    public static final int TAG_BRIGHTNESS_VALUE = 37379;
    public static final int TAG_CFA_PATTERN = 41730;
    public static final int TAG_COLOR_SPACE = 40961;
    public static final int TAG_COMPONENTS_CONFIGURATION = 37121;
    public static final int TAG_COMPRESSED_BITS_PER_PIXEL = 37122;
    public static final int TAG_CONTRAST = 41992;
    public static final int TAG_CUSTOM_RENDERED = 41985;
    public static final int TAG_DATE_TIME_DIGITIZED = 36868;
    public static final int TAG_DATE_TIME_ORIGINAL = 36867;
    public static final int TAG_DEVICE_SETTING_DESCRIPTION = 41995;
    public static final int TAG_DIGITAL_ZOOM_RATIO = 41988;
    public static final int TAG_EXIF_VERSION = 36864;
    public static final int TAG_EXPOSURE_BIAS_VALUE = 37380;
    public static final int TAG_EXPOSURE_INDEX = 41493;
    public static final int TAG_EXPOSURE_MODE = 41986;
    public static final int TAG_EXPOSURE_PROGRAM = 34850;
    public static final int TAG_EXPOSURE_TIME = 33434;
    public static final int TAG_FILE_SOURCE = 41728;
    public static final int TAG_FLASH = 37385;
    public static final int TAG_FLASHPIX_VERSION = 40960;
    public static final int TAG_FLASH_ENERGY = 41483;
    public static final int TAG_FOCAL_LENGTH = 37386;
    public static final int TAG_FOCAL_LENGTH_IN_35MM_FILM = 41989;
    public static final int TAG_FOCAL_PLANE_RESOLUTION_UNIT = 41488;
    public static final int TAG_FOCAL_PLANE_X_RESOLUTION = 41486;
    public static final int TAG_FOCAL_PLANE_Y_RESOLUTION = 41487;
    public static final int TAG_F_NUMBER = 33437;
    public static final int TAG_GAIN_CONTROL = 41991;
    public static final int TAG_GPS_INFO_IFD_POINTER = 34853;
    public static final int TAG_IMAGE_UNIQUE_ID = 42016;
    public static final int TAG_INTEROPERABILITY_IFD_POINTER = 40965;
    public static final int TAG_ISO_SPEED_RATINGS = 34855;
    public static final int TAG_LIGHT_SOURCE = 37384;
    public static final int TAG_MAKER_NOTE = 37500;
    public static final int TAG_MARKER_NOTE = 37500;
    public static final int TAG_MAX_APERTURE_VALUE = 37381;
    public static final int TAG_METERING_MODE = 37383;
    public static final int TAG_OECF = 34856;
    public static final int TAG_PIXEL_X_DIMENSION = 40962;
    public static final int TAG_PIXEL_Y_DIMENSION = 40963;
    public static final int TAG_RELATED_SOUND_FILE = 40964;
    public static final int TAG_SATURATION = 41993;
    public static final int TAG_SCENE_CAPTURE_TYPE = 41990;
    public static final int TAG_SCENE_TYPE = 41729;
    public static final int TAG_SENSING_METHOD = 41495;
    public static final int TAG_SHARPNESS = 41994;
    public static final int TAG_SHUTTER_SPEED_VALUE = 37377;
    public static final int TAG_SPATIAL_FREQUENCY_RESPONSE = 41484;
    public static final int TAG_SPECTRAL_SENSITIVITY = 34852;
    public static final int TAG_SUBJECT_AREA = 37396;
    public static final int TAG_SUBJECT_DISTANCE = 37382;
    public static final int TAG_SUBJECT_DISTANCE_RANGE = 41996;
    public static final int TAG_SUBJECT_LOCATION = 41492;
    public static final int TAG_SUB_SEC_TIME = 37520;
    public static final int TAG_SUB_SEC_TIME_DIGITIZED = 37522;
    public static final int TAG_SUB_SEC_TIME_ORIGINAL = 37521;
    public static final int TAG_USER_COMMENT = 37510;
    public static final int TAG_WHITE_BALANCE = 41987;
    public static final int WHITE_BALANCE_AUTO = 0;
    public static final int WHITE_BALANCE_MANUAL = 1;
    private static List tags;
    private static EXIFTIFFTagSet theInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class EXIFVersion extends TIFFTag {
        public EXIFVersion() {
            super("EXIFversion", EXIFTIFFTagSet.TAG_EXIF_VERSION, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FlashPixVersion extends TIFFTag {
        public FlashPixVersion() {
            super("FlashPixVersion", EXIFTIFFTagSet.TAG_FLASHPIX_VERSION, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ColorSpace extends TIFFTag {
        public ColorSpace() {
            super(ExifInterface.TAG_COLOR_SPACE, EXIFTIFFTagSet.TAG_COLOR_SPACE, 8);
            addValueName(1, "sRGB");
            addValueName(65535, "Uncalibrated");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ComponentsConfiguration extends TIFFTag {
        public ComponentsConfiguration() {
            super(ExifInterface.TAG_COMPONENTS_CONFIGURATION, EXIFTIFFTagSet.TAG_COMPONENTS_CONFIGURATION, 128);
            addValueName(0, "DoesNotExist");
            addValueName(1, "Y");
            addValueName(2, "Cb");
            addValueName(3, "Cr");
            addValueName(4, "R");
            addValueName(5, "G");
            addValueName(6, "B");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class CompressedBitsPerPixel extends TIFFTag {
        public CompressedBitsPerPixel() {
            super(ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL, EXIFTIFFTagSet.TAG_COMPRESSED_BITS_PER_PIXEL, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PixelXDimension extends TIFFTag {
        public PixelXDimension() {
            super(ExifInterface.TAG_PIXEL_X_DIMENSION, EXIFTIFFTagSet.TAG_PIXEL_X_DIMENSION, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class PixelYDimension extends TIFFTag {
        public PixelYDimension() {
            super(ExifInterface.TAG_PIXEL_Y_DIMENSION, EXIFTIFFTagSet.TAG_PIXEL_Y_DIMENSION, 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MakerNote extends TIFFTag {
        public MakerNote() {
            super(ExifInterface.TAG_MAKER_NOTE, 37500, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class UserComment extends TIFFTag {
        public UserComment() {
            super(ExifInterface.TAG_USER_COMMENT, EXIFTIFFTagSet.TAG_USER_COMMENT, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class RelatedSoundFile extends TIFFTag {
        public RelatedSoundFile() {
            super(ExifInterface.TAG_RELATED_SOUND_FILE, EXIFTIFFTagSet.TAG_RELATED_SOUND_FILE, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DateTimeOriginal extends TIFFTag {
        public DateTimeOriginal() {
            super(ExifInterface.TAG_DATETIME_ORIGINAL, EXIFTIFFTagSet.TAG_DATE_TIME_ORIGINAL, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DateTimeDigitized extends TIFFTag {
        public DateTimeDigitized() {
            super(ExifInterface.TAG_DATETIME_DIGITIZED, EXIFTIFFTagSet.TAG_DATE_TIME_DIGITIZED, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubSecTime extends TIFFTag {
        public SubSecTime() {
            super(ExifInterface.TAG_SUBSEC_TIME, EXIFTIFFTagSet.TAG_SUB_SEC_TIME, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubSecTimeOriginal extends TIFFTag {
        public SubSecTimeOriginal() {
            super(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, EXIFTIFFTagSet.TAG_SUB_SEC_TIME_ORIGINAL, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubSecTimeDigitized extends TIFFTag {
        public SubSecTimeDigitized() {
            super(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, EXIFTIFFTagSet.TAG_SUB_SEC_TIME_DIGITIZED, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExposureTime extends TIFFTag {
        public ExposureTime() {
            super(ExifInterface.TAG_EXPOSURE_TIME, EXIFTIFFTagSet.TAG_EXPOSURE_TIME, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FNumber extends TIFFTag {
        public FNumber() {
            super(ExifInterface.TAG_F_NUMBER, EXIFTIFFTagSet.TAG_F_NUMBER, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExposureProgram extends TIFFTag {
        public ExposureProgram() {
            super(ExifInterface.TAG_EXPOSURE_PROGRAM, EXIFTIFFTagSet.TAG_EXPOSURE_PROGRAM, 8);
            addValueName(0, "Not Defined");
            addValueName(1, "Manual");
            addValueName(2, "Normal Program");
            addValueName(3, "Aperture Priority");
            addValueName(4, "Shutter Priority");
            addValueName(5, "Creative Program");
            addValueName(6, "Action Program");
            addValueName(7, "Portrait Mode");
            addValueName(8, "Landscape Mode");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SpectralSensitivity extends TIFFTag {
        public SpectralSensitivity() {
            super(ExifInterface.TAG_SPECTRAL_SENSITIVITY, EXIFTIFFTagSet.TAG_SPECTRAL_SENSITIVITY, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ISOSpeedRatings extends TIFFTag {
        public ISOSpeedRatings() {
            super(ExifInterface.TAG_ISO_SPEED_RATINGS, EXIFTIFFTagSet.TAG_ISO_SPEED_RATINGS, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class OECF extends TIFFTag {
        public OECF() {
            super(ExifInterface.TAG_OECF, EXIFTIFFTagSet.TAG_OECF, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ShutterSpeedValue extends TIFFTag {
        public ShutterSpeedValue() {
            super(ExifInterface.TAG_SHUTTER_SPEED_VALUE, EXIFTIFFTagSet.TAG_SHUTTER_SPEED_VALUE, 1024);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ApertureValue extends TIFFTag {
        public ApertureValue() {
            super(ExifInterface.TAG_APERTURE_VALUE, EXIFTIFFTagSet.TAG_APERTURE_VALUE, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class BrightnessValue extends TIFFTag {
        public BrightnessValue() {
            super(ExifInterface.TAG_BRIGHTNESS_VALUE, EXIFTIFFTagSet.TAG_BRIGHTNESS_VALUE, 1024);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExposureBiasValue extends TIFFTag {
        public ExposureBiasValue() {
            super(ExifInterface.TAG_EXPOSURE_BIAS_VALUE, EXIFTIFFTagSet.TAG_EXPOSURE_BIAS_VALUE, 1024);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MaxApertureValue extends TIFFTag {
        public MaxApertureValue() {
            super(ExifInterface.TAG_MAX_APERTURE_VALUE, EXIFTIFFTagSet.TAG_MAX_APERTURE_VALUE, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubjectDistance extends TIFFTag {
        public SubjectDistance() {
            super(ExifInterface.TAG_SUBJECT_DISTANCE, EXIFTIFFTagSet.TAG_SUBJECT_DISTANCE, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MeteringMode extends TIFFTag {
        public MeteringMode() {
            super(ExifInterface.TAG_METERING_MODE, EXIFTIFFTagSet.TAG_METERING_MODE, 8);
            addValueName(0, AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            addValueName(1, "Average");
            addValueName(2, "CenterWeightedAverage");
            addValueName(3, "Spot");
            addValueName(4, "MultiSpot");
            addValueName(5, "Pattern");
            addValueName(6, "Partial");
            addValueName(255, "Other");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class LightSource extends TIFFTag {
        public LightSource() {
            super(ExifInterface.TAG_LIGHT_SOURCE, EXIFTIFFTagSet.TAG_LIGHT_SOURCE, 8);
            addValueName(0, AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            addValueName(1, "Daylight");
            addValueName(2, "Fluorescent");
            addValueName(3, "Tungsten");
            addValueName(17, "Standard Light A");
            addValueName(18, "Standard Light B");
            addValueName(19, "Standard Light C");
            addValueName(20, "D55");
            addValueName(21, "D65");
            addValueName(22, "D75");
            addValueName(255, "Other");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Flash extends TIFFTag {
        public Flash() {
            super(ExifInterface.TAG_FLASH, EXIFTIFFTagSet.TAG_FLASH, 8);
            addValueName(0, "Flash Did Not Fire");
            addValueName(1, "Flash Fired");
            addValueName(5, "Strobe Return Light Not Detected");
            addValueName(7, "Strobe Return Light Detected");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FocalLength extends TIFFTag {
        public FocalLength() {
            super(ExifInterface.TAG_FOCAL_LENGTH, EXIFTIFFTagSet.TAG_FOCAL_LENGTH, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubjectArea extends TIFFTag {
        public SubjectArea() {
            super(ExifInterface.TAG_SUBJECT_AREA, EXIFTIFFTagSet.TAG_SUBJECT_AREA, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FlashEnergy extends TIFFTag {
        public FlashEnergy() {
            super(ExifInterface.TAG_FLASH_ENERGY, EXIFTIFFTagSet.TAG_FLASH_ENERGY, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SpatialFrequencyResponse extends TIFFTag {
        public SpatialFrequencyResponse() {
            super(ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE, EXIFTIFFTagSet.TAG_SPATIAL_FREQUENCY_RESPONSE, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FocalPlaneXResolution extends TIFFTag {
        public FocalPlaneXResolution() {
            super(ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION, EXIFTIFFTagSet.TAG_FOCAL_PLANE_X_RESOLUTION, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FocalPlaneYResolution extends TIFFTag {
        public FocalPlaneYResolution() {
            super(ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION, EXIFTIFFTagSet.TAG_FOCAL_PLANE_Y_RESOLUTION, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FocalPlaneResolutionUnit extends TIFFTag {
        public FocalPlaneResolutionUnit() {
            super(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, EXIFTIFFTagSet.TAG_FOCAL_PLANE_RESOLUTION_UNIT, 8);
            addValueName(1, "None");
            addValueName(2, "Inch");
            addValueName(3, "Centimeter");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubjectLocation extends TIFFTag {
        public SubjectLocation() {
            super(ExifInterface.TAG_SUBJECT_LOCATION, EXIFTIFFTagSet.TAG_SUBJECT_LOCATION, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExposureIndex extends TIFFTag {
        public ExposureIndex() {
            super(ExifInterface.TAG_EXPOSURE_INDEX, EXIFTIFFTagSet.TAG_EXPOSURE_INDEX, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SensingMethod extends TIFFTag {
        public SensingMethod() {
            super(ExifInterface.TAG_SENSING_METHOD, EXIFTIFFTagSet.TAG_SENSING_METHOD, 8);
            addValueName(1, "Not Defined");
            addValueName(2, "One-chip color area sensor");
            addValueName(3, "Two-chip color area sensor");
            addValueName(4, "Three-chip color area sensor");
            addValueName(5, "Color sequential area sensor");
            addValueName(7, "Trilinear sensor");
            addValueName(8, "Color sequential linear sensor");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FileSource extends TIFFTag {
        public FileSource() {
            super(ExifInterface.TAG_FILE_SOURCE, EXIFTIFFTagSet.TAG_FILE_SOURCE, 128);
            addValueName(3, "DSC");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SceneType extends TIFFTag {
        public SceneType() {
            super(ExifInterface.TAG_SCENE_TYPE, EXIFTIFFTagSet.TAG_SCENE_TYPE, 128);
            addValueName(1, "A directly photographed image");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class CFAPattern extends TIFFTag {
        public CFAPattern() {
            super(ExifInterface.TAG_CFA_PATTERN, EXIFTIFFTagSet.TAG_CFA_PATTERN, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class CustomRendered extends TIFFTag {
        public CustomRendered() {
            super(ExifInterface.TAG_CUSTOM_RENDERED, EXIFTIFFTagSet.TAG_CUSTOM_RENDERED, 8);
            addValueName(0, "Normal process");
            addValueName(1, "Custom process");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExposureMode extends TIFFTag {
        public ExposureMode() {
            super(ExifInterface.TAG_EXPOSURE_MODE, EXIFTIFFTagSet.TAG_EXPOSURE_MODE, 8);
            addValueName(0, "Auto exposure");
            addValueName(1, "Manual exposure");
            addValueName(2, "Auto bracket");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class WhiteBalance extends TIFFTag {
        public WhiteBalance() {
            super(ExifInterface.TAG_WHITE_BALANCE, EXIFTIFFTagSet.TAG_WHITE_BALANCE, 8);
            addValueName(0, "Auto white balance");
            addValueName(1, "Manual white balance");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DigitalZoomRatio extends TIFFTag {
        public DigitalZoomRatio() {
            super(ExifInterface.TAG_DIGITAL_ZOOM_RATIO, EXIFTIFFTagSet.TAG_DIGITAL_ZOOM_RATIO, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FocalLengthIn35mmFilm extends TIFFTag {
        public FocalLengthIn35mmFilm() {
            super(ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM, EXIFTIFFTagSet.TAG_FOCAL_LENGTH_IN_35MM_FILM, 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SceneCaptureType extends TIFFTag {
        public SceneCaptureType() {
            super(ExifInterface.TAG_SCENE_CAPTURE_TYPE, EXIFTIFFTagSet.TAG_SCENE_CAPTURE_TYPE, 8);
            addValueName(0, "Standard");
            addValueName(1, "Landscape");
            addValueName(2, "Portrait");
            addValueName(3, "Night scene");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GainControl extends TIFFTag {
        public GainControl() {
            super(ExifInterface.TAG_GAIN_CONTROL, EXIFTIFFTagSet.TAG_GAIN_CONTROL, 8);
            addValueName(0, "None");
            addValueName(1, "Low gain up");
            addValueName(2, "High gain up");
            addValueName(3, "Low gain down");
            addValueName(4, "High gain down");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Contrast extends TIFFTag {
        public Contrast() {
            super(ExifInterface.TAG_CONTRAST, EXIFTIFFTagSet.TAG_CONTRAST, 8);
            addValueName(0, PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
            addValueName(1, "Soft");
            addValueName(2, "Hard");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Saturation extends TIFFTag {
        public Saturation() {
            super(ExifInterface.TAG_SATURATION, EXIFTIFFTagSet.TAG_SATURATION, 8);
            addValueName(0, PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
            addValueName(1, "Low saturation");
            addValueName(2, "High saturation");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Sharpness extends TIFFTag {
        public Sharpness() {
            super(ExifInterface.TAG_SHARPNESS, EXIFTIFFTagSet.TAG_SHARPNESS, 8);
            addValueName(0, PDLayoutAttributeObject.LINE_HEIGHT_NORMAL);
            addValueName(1, "Soft");
            addValueName(2, "Hard");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class DeviceSettingDescription extends TIFFTag {
        public DeviceSettingDescription() {
            super(ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION, EXIFTIFFTagSet.TAG_DEVICE_SETTING_DESCRIPTION, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SubjectDistanceRange extends TIFFTag {
        public SubjectDistanceRange() {
            super(ExifInterface.TAG_SUBJECT_DISTANCE_RANGE, EXIFTIFFTagSet.TAG_SUBJECT_DISTANCE_RANGE, 8);
            addValueName(0, "unknown");
            addValueName(1, "Macro");
            addValueName(2, "Close view");
            addValueName(3, "Distant view");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImageUniqueID extends TIFFTag {
        public ImageUniqueID() {
            super(ExifInterface.TAG_IMAGE_UNIQUE_ID, EXIFTIFFTagSet.TAG_IMAGE_UNIQUE_ID, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class InteroperabilityIFD extends TIFFTag {
        public InteroperabilityIFD() {
            super("InteroperabilityIFD", EXIFTIFFTagSet.TAG_INTEROPERABILITY_IFD_POINTER, 16, EXIFInteroperabilityTagSet.getInstance());
        }
    }

    private static void initTags() {
        ArrayList arrayList = new ArrayList(42);
        tags = arrayList;
        arrayList.add(new EXIFVersion());
        tags.add(new FlashPixVersion());
        tags.add(new ColorSpace());
        tags.add(new ComponentsConfiguration());
        tags.add(new CompressedBitsPerPixel());
        tags.add(new PixelXDimension());
        tags.add(new PixelYDimension());
        tags.add(new MakerNote());
        tags.add(new UserComment());
        tags.add(new RelatedSoundFile());
        tags.add(new DateTimeOriginal());
        tags.add(new DateTimeDigitized());
        tags.add(new SubSecTime());
        tags.add(new SubSecTimeOriginal());
        tags.add(new SubSecTimeDigitized());
        tags.add(new ExposureTime());
        tags.add(new FNumber());
        tags.add(new ExposureProgram());
        tags.add(new SpectralSensitivity());
        tags.add(new ISOSpeedRatings());
        tags.add(new OECF());
        tags.add(new ShutterSpeedValue());
        tags.add(new ApertureValue());
        tags.add(new BrightnessValue());
        tags.add(new ExposureBiasValue());
        tags.add(new MaxApertureValue());
        tags.add(new SubjectDistance());
        tags.add(new MeteringMode());
        tags.add(new LightSource());
        tags.add(new Flash());
        tags.add(new FocalLength());
        tags.add(new SubjectArea());
        tags.add(new FlashEnergy());
        tags.add(new SpatialFrequencyResponse());
        tags.add(new FocalPlaneXResolution());
        tags.add(new FocalPlaneYResolution());
        tags.add(new FocalPlaneResolutionUnit());
        tags.add(new SubjectLocation());
        tags.add(new ExposureIndex());
        tags.add(new SensingMethod());
        tags.add(new FileSource());
        tags.add(new SceneType());
        tags.add(new CFAPattern());
        tags.add(new CustomRendered());
        tags.add(new ExposureMode());
        tags.add(new WhiteBalance());
        tags.add(new DigitalZoomRatio());
        tags.add(new FocalLengthIn35mmFilm());
        tags.add(new SceneCaptureType());
        tags.add(new GainControl());
        tags.add(new Contrast());
        tags.add(new Saturation());
        tags.add(new Sharpness());
        tags.add(new DeviceSettingDescription());
        tags.add(new SubjectDistanceRange());
        tags.add(new ImageUniqueID());
        tags.add(new InteroperabilityIFD());
    }

    private EXIFTIFFTagSet() {
        super(tags);
    }

    public static synchronized EXIFTIFFTagSet getInstance() {
        EXIFTIFFTagSet eXIFTIFFTagSet;
        synchronized (EXIFTIFFTagSet.class) {
            if (theInstance == null) {
                initTags();
                theInstance = new EXIFTIFFTagSet();
                tags = null;
            }
            eXIFTIFFTagSet = theInstance;
        }
        return eXIFTIFFTagSet;
    }
}
