package com.github.jaiimageio.plugins.tiff;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes3.dex */
public class EXIFGPSTagSet extends TIFFTagSet {
    public static final int ALTITUDE_REF_SEA_LEVEL = 0;
    public static final int ALTITUDE_REF_SEA_LEVEL_REFERENCE = 1;
    public static final String DEST_DISTANCE_REF_KILOMETERS = "K";
    public static final String DEST_DISTANCE_REF_KNOTS = "N";
    public static final String DEST_DISTANCE_REF_MILES = "M";
    public static final String DIRECTION_REF_MAGNETIC = "M";
    public static final String DIRECTION_REF_TRUE = "T";
    public static final String LATITUDE_REF_NORTH = "N";
    public static final String LATITUDE_REF_SOUTH = "S";
    public static final String LONGITUDE_REF_EAST = "E";
    public static final String LONGITUDE_REF_WEST = "W";
    public static final String MEASURE_MODE_2D = "2";
    public static final String MEASURE_MODE_3D = "3";
    public static final String SPEED_REF_KILOMETERS_PER_HOUR = "K";
    public static final String SPEED_REF_KNOTS = "N";
    public static final String SPEED_REF_MILES_PER_HOUR = "M";
    public static final String STATUS_MEASUREMENT_INTEROPERABILITY = "V";
    public static final String STATUS_MEASUREMENT_IN_PROGRESS = "A";
    public static final int TAG_GPS_ALTITUDE = 6;
    public static final int TAG_GPS_ALTITUDE_REF = 5;
    public static final int TAG_GPS_AREA_INFORMATION = 28;
    public static final int TAG_GPS_DATE_STAMP = 29;
    public static final int TAG_GPS_DEST_BEARING = 24;
    public static final int TAG_GPS_DEST_BEARING_REF = 23;
    public static final int TAG_GPS_DEST_DISTANCE = 26;
    public static final int TAG_GPS_DEST_DISTANCE_REF = 25;
    public static final int TAG_GPS_DEST_LATITUDE = 20;
    public static final int TAG_GPS_DEST_LATITUDE_REF = 19;
    public static final int TAG_GPS_DEST_LONGITUDE = 22;
    public static final int TAG_GPS_DEST_LONGITUDE_REF = 21;
    public static final int TAG_GPS_DIFFERENTIAL = 30;
    public static final int TAG_GPS_DOP = 11;
    public static final int TAG_GPS_IMG_DIRECTION = 17;
    public static final int TAG_GPS_IMG_DIRECTION_REF = 16;
    public static final int TAG_GPS_LATITUDE = 2;
    public static final int TAG_GPS_LATITUDE_REF = 1;
    public static final int TAG_GPS_LONGITUDE = 4;
    public static final int TAG_GPS_LONGITUDE_REF = 3;
    public static final int TAG_GPS_MAP_DATUM = 18;
    public static final int TAG_GPS_MEASURE_MODE = 10;
    public static final int TAG_GPS_PROCESSING_METHOD = 27;
    public static final int TAG_GPS_SATELLITES = 8;
    public static final int TAG_GPS_SPEED = 13;
    public static final int TAG_GPS_SPEED_REF = 12;
    public static final int TAG_GPS_STATUS = 9;
    public static final int TAG_GPS_TIME_STAMP = 7;
    public static final int TAG_GPS_TRACK = 15;
    public static final int TAG_GPS_TRACK_REF = 14;
    public static final int TAG_GPS_VERSION_ID = 0;
    private static EXIFGPSTagSet theInstance;
    public static byte[] GPS_VERSION_2_2 = {ISO7816.INS_INCREASE, ISO7816.INS_INCREASE, ISO7816.INS_DECREASE, ISO7816.INS_DECREASE};
    public static int DIFFERENTIAL_CORRECTION_NONE = 0;
    public static int DIFFERENTIAL_CORRECTION_APPLIED = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSVersionID extends TIFFTag {
        public GPSVersionID() {
            super(ExifInterface.TAG_GPS_VERSION_ID, 0, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSLatitudeRef extends TIFFTag {
        public GPSLatitudeRef() {
            super(ExifInterface.TAG_GPS_LATITUDE_REF, 1, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSLatitude extends TIFFTag {
        public GPSLatitude() {
            super(ExifInterface.TAG_GPS_LATITUDE, 2, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSLongitudeRef extends TIFFTag {
        public GPSLongitudeRef() {
            super(ExifInterface.TAG_GPS_LONGITUDE_REF, 3, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSLongitude extends TIFFTag {
        public GPSLongitude() {
            super(ExifInterface.TAG_GPS_LONGITUDE, 4, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSAltitudeRef extends TIFFTag {
        public GPSAltitudeRef() {
            super(ExifInterface.TAG_GPS_ALTITUDE_REF, 5, 2);
            addValueName(0, "Sea level");
            addValueName(1, "Sea level reference (negative value)");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSAltitude extends TIFFTag {
        public GPSAltitude() {
            super(ExifInterface.TAG_GPS_ALTITUDE, 6, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSTimeStamp extends TIFFTag {
        public GPSTimeStamp() {
            super(ExifInterface.TAG_GPS_TIMESTAMP, 7, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSSatellites extends TIFFTag {
        public GPSSatellites() {
            super(ExifInterface.TAG_GPS_SATELLITES, 8, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSStatus extends TIFFTag {
        public GPSStatus() {
            super(ExifInterface.TAG_GPS_STATUS, 9, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSMeasureMode extends TIFFTag {
        public GPSMeasureMode() {
            super(ExifInterface.TAG_GPS_MEASURE_MODE, 10, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDOP extends TIFFTag {
        public GPSDOP() {
            super(ExifInterface.TAG_GPS_DOP, 11, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSSpeedRef extends TIFFTag {
        public GPSSpeedRef() {
            super(ExifInterface.TAG_GPS_SPEED_REF, 12, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSSpeed extends TIFFTag {
        public GPSSpeed() {
            super(ExifInterface.TAG_GPS_SPEED, 13, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSTrackRef extends TIFFTag {
        public GPSTrackRef() {
            super(ExifInterface.TAG_GPS_TRACK_REF, 14, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSTrack extends TIFFTag {
        public GPSTrack() {
            super(ExifInterface.TAG_GPS_TRACK, 15, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSImgDirectionRef extends TIFFTag {
        public GPSImgDirectionRef() {
            super(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, 16, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSImgDirection extends TIFFTag {
        public GPSImgDirection() {
            super(ExifInterface.TAG_GPS_IMG_DIRECTION, 17, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSMapDatum extends TIFFTag {
        public GPSMapDatum() {
            super(ExifInterface.TAG_GPS_MAP_DATUM, 18, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestLatitudeRef extends TIFFTag {
        public GPSDestLatitudeRef() {
            super(ExifInterface.TAG_GPS_DEST_LATITUDE_REF, 19, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestLatitude extends TIFFTag {
        public GPSDestLatitude() {
            super(ExifInterface.TAG_GPS_DEST_LATITUDE, 20, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestLongitudeRef extends TIFFTag {
        public GPSDestLongitudeRef() {
            super(ExifInterface.TAG_GPS_DEST_LONGITUDE_REF, 21, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestLongitude extends TIFFTag {
        public GPSDestLongitude() {
            super(ExifInterface.TAG_GPS_DEST_LONGITUDE, 22, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestBearingRef extends TIFFTag {
        public GPSDestBearingRef() {
            super(ExifInterface.TAG_GPS_DEST_BEARING_REF, 23, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestBearing extends TIFFTag {
        public GPSDestBearing() {
            super(ExifInterface.TAG_GPS_DEST_BEARING, 24, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestDistanceRef extends TIFFTag {
        public GPSDestDistanceRef() {
            super(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, 25, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDestDistance extends TIFFTag {
        public GPSDestDistance() {
            super(ExifInterface.TAG_GPS_DEST_DISTANCE, 26, 32);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSProcessingMethod extends TIFFTag {
        public GPSProcessingMethod() {
            super(ExifInterface.TAG_GPS_PROCESSING_METHOD, 27, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSAreaInformation extends TIFFTag {
        public GPSAreaInformation() {
            super(ExifInterface.TAG_GPS_AREA_INFORMATION, 28, 128);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDateStamp extends TIFFTag {
        public GPSDateStamp() {
            super(ExifInterface.TAG_GPS_DATESTAMP, 29, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class GPSDifferential extends TIFFTag {
        public GPSDifferential() {
            super(ExifInterface.TAG_GPS_DIFFERENTIAL, 30, 8);
            addValueName(EXIFGPSTagSet.DIFFERENTIAL_CORRECTION_NONE, "Measurement without differential correction");
            addValueName(EXIFGPSTagSet.DIFFERENTIAL_CORRECTION_APPLIED, "Differential correction applied");
        }
    }

    private static List initTags() {
        ArrayList arrayList = new ArrayList(31);
        arrayList.add(new GPSVersionID());
        arrayList.add(new GPSLatitudeRef());
        arrayList.add(new GPSLatitude());
        arrayList.add(new GPSLongitudeRef());
        arrayList.add(new GPSLongitude());
        arrayList.add(new GPSAltitudeRef());
        arrayList.add(new GPSAltitude());
        arrayList.add(new GPSTimeStamp());
        arrayList.add(new GPSSatellites());
        arrayList.add(new GPSStatus());
        arrayList.add(new GPSMeasureMode());
        arrayList.add(new GPSDOP());
        arrayList.add(new GPSSpeedRef());
        arrayList.add(new GPSSpeed());
        arrayList.add(new GPSTrackRef());
        arrayList.add(new GPSTrack());
        arrayList.add(new GPSImgDirectionRef());
        arrayList.add(new GPSImgDirection());
        arrayList.add(new GPSMapDatum());
        arrayList.add(new GPSDestLatitudeRef());
        arrayList.add(new GPSDestLatitude());
        arrayList.add(new GPSDestLongitudeRef());
        arrayList.add(new GPSDestLongitude());
        arrayList.add(new GPSDestBearingRef());
        arrayList.add(new GPSDestBearing());
        arrayList.add(new GPSDestDistanceRef());
        arrayList.add(new GPSDestDistance());
        arrayList.add(new GPSProcessingMethod());
        arrayList.add(new GPSAreaInformation());
        arrayList.add(new GPSDateStamp());
        arrayList.add(new GPSDifferential());
        return arrayList;
    }

    private EXIFGPSTagSet() {
        super(initTags());
    }

    public static synchronized EXIFGPSTagSet getInstance() {
        EXIFGPSTagSet eXIFGPSTagSet;
        synchronized (EXIFGPSTagSet.class) {
            if (theInstance == null) {
                theInstance = new EXIFGPSTagSet();
            }
            eXIFGPSTagSet = theInstance;
        }
        return eXIFGPSTagSet;
    }
}
