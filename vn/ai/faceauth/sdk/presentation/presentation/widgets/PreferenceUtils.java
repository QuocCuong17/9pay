package vn.ai.faceauth.sdk.presentation.presentation.widgets;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import lmlf.ayxnhy.tfwhgw;
import vn.ai.faceauth.sdk.presentation.presentation.widgets.CameraSource;

/* loaded from: classes6.dex */
public class PreferenceUtils {
    private static int POSE_DETECTOR_PERFORMANCE_MODE_FAST;

    static {
        tfwhgw.rnl(PreferenceUtils.class, TypedValues.PositionType.TYPE_PERCENT_WIDTH, TypedValues.PositionType.TYPE_PERCENT_WIDTH);
    }

    private PreferenceUtils() {
    }

    public static CameraSource.SizePair getCameraPreviewSizePair(Context context, int i) {
        boolean z = true;
        if (i != 0 && i != 1) {
            z = false;
        }
        Preconditions.checkArgument(z);
        String rnigpa = tfwhgw.rnigpa(26);
        String rnigpa2 = tfwhgw.rnigpa(27);
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return new CameraSource.SizePair(Size.parseSize(defaultSharedPreferences.getString(rnigpa, null)), Size.parseSize(defaultSharedPreferences.getString(rnigpa2, null)));
        } catch (Exception unused) {
            return null;
        }
    }

    public static FaceDetectorOptions getFaceDetectorOptions() {
        return new FaceDetectorOptions.Builder().setLandmarkMode(1).setContourMode(2).setClassificationMode(1).setPerformanceMode(1).setMinFaceSize(0.1f).build();
    }

    public static boolean isCameraLiveViewportEnabled(Context context) {
        return false;
    }

    public static boolean shouldHideDetectionInfo(Context context) {
        return false;
    }
}
