package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class HVCameraHelper {
    private static final String TAG = "HVCameraHelper";

    public static void enableCameraParameters(Context context, boolean z) {
        HVLogUtils.d(TAG, "enableCameraParameters() called with: context = [" + context + "], useBackCamera = [" + z + "]");
        String infoHardwareLevel = Utils.getInfoHardwareLevel(context, z);
        StringBuilder sb = new StringBuilder();
        sb.append("enableCameraParameters(): level = [");
        sb.append(infoHardwareLevel);
        sb.append("]");
        HVLogUtils.d(TAG, sb.toString());
        if (SDKInternalConfig.getInstance() != null) {
            HashMap hashMap = new HashMap();
            boolean shouldUseCamera2 = SDKInternalConfig.getInstance().shouldUseCamera2();
            HVLogUtils.d(TAG, "enableCameraParameters(): shouldUseCamera2 = [" + shouldUseCamera2 + "]");
            if (SDKInternalConfig.getInstance().isAutoCamSelectionEnabled(infoHardwareLevel)) {
                shouldUseCamera2 = true;
            }
            hashMap.put("camera2", Boolean.valueOf(shouldUseCamera2));
            CameraEngine.setFeatureConfig(hashMap);
        }
        AppConstants.cameraType = CameraEngine.isCamera2(null) ? "camera2" : "camera1";
    }
}
