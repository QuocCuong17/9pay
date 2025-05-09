package co.hyperverge.facedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.Log;
import co.hyperverge.facedetection.Detectors.HVFaceDetector;
import co.hyperverge.facedetection.Detectors.MediaDetector;
import co.hyperverge.facedetection.Detectors.NDPDetector;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class FaceDetectorApi {
    public static final int DETECTOR_HV = 2;
    public static final int DETECTOR_MEDIA = 0;
    public static final int DETECTOR_MOBILE_VISION = 1;
    private static final String LOG_TAG = "co.hyperverge.facedetection.FaceDetectorApi";
    private static HVFaceDetector mDetector;

    public static void initialize(Context context, int i) {
        release();
        if (i == 0) {
            MediaDetector mediaDetector = new MediaDetector();
            mDetector = mediaDetector;
            mediaDetector.initialize(context);
        } else {
            if (i != 2) {
                return;
            }
            NDPDetector nDPDetector = new NDPDetector();
            mDetector = nDPDetector;
            nDPDetector.initialize(context);
        }
    }

    public static void release() {
        HVFaceDetector hVFaceDetector = mDetector;
        if (hVFaceDetector != null) {
            hVFaceDetector.release();
            mDetector = null;
        }
    }

    public static List<HVFace> detectFacesFromFile(FileInterface fileInterface) {
        if (mDetector != null) {
            Log.i("ABCDE", "mDetector is not null");
            return mDetector.detectFaces(fileInterface);
        }
        Log.i("ABCDE", "mDetector is null");
        Log.e(LOG_TAG, "Detector not initialized");
        Log.i("ABCDE", "Returning null");
        return null;
    }

    public static ArrayList<ArrayList<Float>> detectFacesFromByteArray(byte[] bArr, int i, int i2, int i3) {
        HVFaceDetector hVFaceDetector = mDetector;
        if (hVFaceDetector != null) {
            return ((NDPDetector) hVFaceDetector).detectFacesFromData(bArr, i, i2, i3);
        }
        Log.e(LOG_TAG, "Detector not initialized");
        return null;
    }

    public static ArrayList<ArrayList<Float>> detectFacesFromBitmap(Bitmap bitmap) {
        HVFaceDetector hVFaceDetector = mDetector;
        if (hVFaceDetector != null) {
            return ((NDPDetector) hVFaceDetector).detectFacesFromBitmap(bitmap);
        }
        Log.e(LOG_TAG, "Detector not initialized");
        return null;
    }

    public static List<RectF> detectFacesFromByteArray2(byte[] bArr, int i, int i2, int i3) {
        HVFaceDetector hVFaceDetector = mDetector;
        if (hVFaceDetector != null) {
            return ((NDPDetector) hVFaceDetector).detectFacesFromData2(bArr, i, i2, i3);
        }
        Log.e(LOG_TAG, "Detector not initialized");
        return null;
    }

    public static int getAvgIntensity(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        Log.i("touchh", i3 + "," + i4 + "," + i5 + "," + i6);
        HVFaceDetector hVFaceDetector = mDetector;
        if (hVFaceDetector != null) {
            return ((NDPDetector) hVFaceDetector).getAvgIntensity(bArr, i, i2, i3, i4, i5, i6, z);
        }
        Log.e(LOG_TAG, "Detector not initialized");
        return -1;
    }
}
