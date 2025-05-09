package co.hyperverge.hypersnapsdk.service.exif;

import android.media.ExifInterface;
import android.util.Log;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.objects.HVExifData;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.IOException;

/* loaded from: classes2.dex */
public class HVEXIFExtractor implements HVEXIFService {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.exif.HVEXIFExtractor";

    @Override // co.hyperverge.hypersnapsdk.service.exif.HVEXIFService
    public HVExifData extractExifDataFromImage(String str) {
        HVLogUtils.d(TAG, "extractExifDataFromImage() called with: imageUri = [" + str + "]");
        HVExifData hVExifData = new HVExifData();
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            hVExifData.setAperture(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_F_NUMBER));
            hVExifData.setDatetime(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_DATETIME));
            hVExifData.setExposureTime(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_EXPOSURE_TIME));
            hVExifData.setFlash(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_FLASH));
            hVExifData.setFocalLength(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_FOCAL_LENGTH));
            hVExifData.setIso(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_ISO_SPEED_RATINGS));
            hVExifData.setMake(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MAKE));
            hVExifData.setModel(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_MODEL));
            hVExifData.setWhiteBalance(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_WHITE_BALANCE));
            hVExifData.setUserComment(exifInterface.getAttribute(androidx.exifinterface.media.ExifInterface.TAG_USER_COMMENT));
            float[] fArr = new float[2];
            boolean latLong = exifInterface.getLatLong(fArr);
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseLocation() || latLong) {
                hVExifData.setLatitude(fArr[0]);
                hVExifData.setLongitude(fArr[1]);
            }
        } catch (IOException e) {
            String str2 = TAG;
            HVLogUtils.e(str2, "extractExifDataFromImage() with: imageUri = [" + str + "]: exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str2, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return hVExifData;
    }
}
