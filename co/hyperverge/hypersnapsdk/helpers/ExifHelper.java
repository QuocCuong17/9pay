package co.hyperverge.hypersnapsdk.helpers;

import android.location.Location;
import android.media.ExifInterface;
import android.util.Log;
import co.hyperverge.hypersnapsdk.BuildConfig;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.objects.IPAddress;
import co.hyperverge.hypersnapsdk.utils.GPSHelper;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class ExifHelper {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.ExifHelper";
    private String city;
    private String country;
    private String countryCode;
    private String ipAddress;
    private String aperature = null;
    private String datetime = null;
    private String exposureTime = null;
    private String flash = null;
    private String focalLength = null;
    private String gpsAltitude = null;
    private String gpsAltitudeRef = null;
    private String gpsDateStamp = null;
    private String gpsLatitude = null;
    private String gpsLatitudeRef = null;
    private String gpsLongitude = null;
    private String gpsLongitudeRef = null;
    private String gpsProcessingMethod = null;
    private String gpsTimestamp = null;
    private String iso = null;
    private String make = null;
    private String model = null;
    private final String orientation = null;
    private String whiteBalance = null;
    private String userComment = null;
    private final double longitude = 0.0d;
    private final double latitude = 0.0d;

    public void readExif(byte[] bArr, String str, Location location) {
        HVLogUtils.d(TAG, "readExif() called with: data = [" + bArr + "], filePath = [" + str + "], location = [" + location + "]");
        if (StringUtils.isEmptyOrNull(str)) {
            return;
        }
        File file = new File(str);
        try {
            new FileOutputStream(file).write(bArr);
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            this.aperature = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_F_NUMBER);
            this.datetime = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME);
            this.exposureTime = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_EXPOSURE_TIME);
            this.flash = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_FLASH);
            this.focalLength = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_FOCAL_LENGTH);
            this.gpsAltitude = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE);
            this.gpsAltitudeRef = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE_REF);
            this.gpsDateStamp = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_DATESTAMP);
            if (location != null) {
                this.gpsLatitude = GPSHelper.convert(location.getLatitude());
                this.gpsLongitude = GPSHelper.convert(location.getLongitude());
                this.gpsLatitudeRef = GPSHelper.latitudeRef(location.getLatitude());
                this.gpsLongitudeRef = GPSHelper.latitudeRef(location.getLongitude());
            }
            this.gpsProcessingMethod = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_PROCESSING_METHOD);
            this.gpsTimestamp = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_TIMESTAMP);
            this.iso = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ISO_SPEED_RATINGS);
            this.make = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE);
            this.model = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MODEL);
            this.whiteBalance = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_WHITE_BALANCE);
            this.userComment = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_USER_COMMENT);
            exifInterface.getLatLong(new float[2]);
            file.delete();
        } catch (Exception e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "readExif(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void writeExifData(String str, String str2, IPAddress iPAddress) {
        HVLogUtils.d(TAG, "writeExifData() called with: filePath = [" + str + "], transactionID = [" + str2 + "], geoDetails = [" + iPAddress + "]");
        try {
            if (StringUtils.isEmptyOrNull(str)) {
                return;
            }
            ExifInterface exifInterface = new ExifInterface(str);
            String str3 = this.aperature;
            if (str3 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_F_NUMBER, str3);
            }
            String str4 = this.datetime;
            if (str4 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME, str4);
            }
            String str5 = this.exposureTime;
            if (str5 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_EXPOSURE_TIME, str5);
            }
            String str6 = this.flash;
            if (str6 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_FLASH, str6);
            }
            String str7 = this.focalLength;
            if (str7 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_FOCAL_LENGTH, str7);
            }
            String str8 = this.gpsAltitude;
            if (str8 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE, str8);
            }
            String str9 = this.gpsAltitudeRef;
            if (str9 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE_REF, str9);
            }
            String str10 = this.gpsDateStamp;
            if (str10 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_DATESTAMP, str10);
            }
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE, this.gpsLatitude);
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE, this.gpsLongitude);
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE_REF, this.gpsLatitudeRef);
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE_REF, this.gpsLongitudeRef);
            String userCommentString = getUserCommentString(str2, iPAddress);
            this.userComment = userCommentString;
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_USER_COMMENT, userCommentString);
            String str11 = this.gpsProcessingMethod;
            if (str11 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_PROCESSING_METHOD, str11);
            }
            String str12 = this.gpsTimestamp;
            if (str12 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_TIMESTAMP, str12);
            }
            String str13 = this.iso;
            if (str13 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_ISO_SPEED_RATINGS, str13);
            }
            String str14 = this.make;
            if (str14 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE, str14);
            }
            String str15 = this.model;
            if (str15 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_MODEL, str15);
            }
            String str16 = this.whiteBalance;
            if (str16 != null) {
                exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_WHITE_BALANCE, str16);
            }
            exifInterface.saveAttributes();
        } catch (Exception e) {
            String str17 = TAG;
            HVLogUtils.e(str17, "writeExifData(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str17, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public String getUserCommentString(String str, IPAddress iPAddress) {
        HVLogUtils.d(TAG, "getUserCommentString() called with: transactionID = [" + str + "], geoDetails = [" + iPAddress + "]");
        StringBuilder sb = new StringBuilder("hvsdk_android_");
        String appId = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId();
        sb.append(BuildConfig.HYPERSNAP_VERSION_NAME);
        sb.append("_");
        sb.append(appId);
        if (!StringUtils.isEmptyOrNull(str)) {
            sb.append("_");
            sb.append(str);
        } else if (!StringUtils.isEmptyOrNull(SPHelper.getTransactionID())) {
            sb.append("_");
            sb.append(SPHelper.getTransactionID());
        }
        if (iPAddress != null) {
            try {
                String ip = iPAddress.getIp();
                String city = iPAddress.getGeoDetails().getCity();
                String country = iPAddress.getGeoDetails().getCountry();
                String countryCode = iPAddress.getGeoDetails().getCountryCode();
                sb.append("_");
                sb.append(ip);
                sb.append("_");
                sb.append(city);
                sb.append("_");
                sb.append(country);
                sb.append("_");
                sb.append(countryCode);
            } catch (Exception e) {
                String str2 = TAG;
                HVLogUtils.e(str2, "getUserCommentString(): exception = [" + Utils.getErrorMessage(e) + "]", e);
                Log.e(str2, "getUserCommentString: ", e);
            }
        }
        return String.valueOf(sb);
    }

    public void readExifFromFile(File file, Location location) throws IOException {
        HVLogUtils.d(TAG, "readExifFromFile() called with: file = [" + file + "], location = [" + location + "]");
        ExifInterface exifInterface = new ExifInterface(file.getPath());
        this.aperature = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_F_NUMBER);
        this.datetime = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME);
        this.exposureTime = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_EXPOSURE_TIME);
        this.flash = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_FLASH);
        this.focalLength = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_FOCAL_LENGTH);
        this.gpsAltitude = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE);
        this.gpsAltitudeRef = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE_REF);
        this.gpsDateStamp = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_DATESTAMP);
        if (location != null) {
            this.gpsLatitude = GPSHelper.convert(location.getLatitude());
            this.gpsLongitude = GPSHelper.convert(location.getLongitude());
            this.gpsLatitudeRef = GPSHelper.latitudeRef(location.getLatitude());
            this.gpsLongitudeRef = GPSHelper.latitudeRef(location.getLongitude());
        }
        this.gpsProcessingMethod = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_PROCESSING_METHOD);
        this.gpsTimestamp = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_TIMESTAMP);
        this.iso = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ISO_SPEED_RATINGS);
        this.make = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE);
        this.model = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MODEL);
        this.whiteBalance = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_WHITE_BALANCE);
        this.userComment = exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_USER_COMMENT);
        exifInterface.getLatLong(new float[2]);
    }
}
