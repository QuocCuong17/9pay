package co.hyperverge.hvcamera.magicfilter.camera;

import android.content.Context;
import android.graphics.Point;
import co.hyperverge.hvcamera.HVLog;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CameraEngine {
    private static final String TAG = "co.hyperverge.hvcamera.magicfilter.camera.CameraEngine";
    public static float zoomFactor = 1.0f;
    public static JSONObject hardwareInfo = new JSONObject();
    public static boolean shouldUseDefaultZoom = false;
    public static boolean shouldRandomize = true;
    public static int lastUsedWidth = -1;
    public static int lastUsedHeight = -1;
    private static boolean useCamera2 = false;
    private static boolean setPreviewCallback = false;
    private static boolean isScreenFlashSet = false;
    private static boolean facePriority = false;

    public static boolean isCamera2(Context context) {
        HVLog.d(TAG, "isCamera2() called with: context = [" + context + "]");
        return useCamera2;
    }

    public static boolean getCaptureMode() {
        HVLog.d(TAG, "getCaptureMode() called");
        if (useCamera2) {
            return CameraEngine2.getInstance().getIsStillImageMode();
        }
        return CameraEngine1.getInstance().getIsStillImageMode();
    }

    public static void setCaptureMode(boolean z) {
        HVLog.d(TAG, "setCaptureMode() called with: stillCapture = [" + z + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().setIsStillImageMode(z);
        } else {
            CameraEngine1.getInstance().setIsStillImageMode(z);
        }
    }

    public static boolean isFrontFacingCamera() {
        if (useCamera2) {
            return CameraEngine2.getInstance().isFrontFacingCamera();
        }
        return CameraEngine1.getInstance().isFrontFacingCamera();
    }

    public static void clearEvent(Object obj) {
        HVLog.d(TAG, "clearEvent() called with: o = [" + obj + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().clearEvent(null);
        } else {
            CameraEngine1.getInstance().clearEvent(null);
        }
    }

    public static void nextFlashMode() {
        HVLog.d(TAG, "nextFlashMode() called");
        if (useCamera2) {
            CameraEngine2.getInstance().nextFlashMode();
        } else {
            CameraEngine1.getInstance().nextFlashMode();
        }
    }

    public static void setShouldRandomize(boolean z) {
        HVLog.d(TAG, "setShouldRandomize() called with: shouldRandomize = [" + z + "]");
        shouldRandomize = z;
    }

    public static void setLastUsedResolution(int i, int i2) {
        HVLog.d(TAG, "setLastUsedResolution() called with: width = [" + i + "], height = [" + i2 + "]");
        lastUsedWidth = i;
        lastUsedHeight = i2;
    }

    public static JSONObject getHardwareInfo() {
        HVLog.d(TAG, "getHardwareInfo() returned: " + hardwareInfo);
        return hardwareInfo;
    }

    public static int getOrientation() {
        HVLog.d(TAG, "getOrientation() called");
        if (useCamera2) {
            return CameraEngine2.getInstance().getOrientation();
        }
        return CameraEngine1.getInstance().getOrientation();
    }

    public static void setOrientation(int i) {
        HVLog.d(TAG, "setOrientation() called with: orientation = [" + i + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().setOrientation(i);
        } else {
            CameraEngine1.getInstance().setOrientation(i);
        }
    }

    public static void init(Context context, boolean z) {
        HVLog.d(TAG, "init() called with: context = [" + context + "], useFFC = [" + z + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().init(context, z);
        } else {
            CameraEngine1.getInstance().setInitialFrontCam(z);
        }
    }

    public static void setFeatureConfig(Map<String, Boolean> map) {
        HVLog.d(TAG, "setFeatureConfig() called with: remoteConfigMapper = [" + map + "]");
        if (map.containsKey("camera2")) {
            useCamera2 = map.get("camera2").booleanValue();
        }
    }

    public static void setScreenSize(Point point) {
        HVLog.d(TAG, "setScreenSize() called with: screenSize = [" + point + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().setScreenDisplaySize(point);
        } else {
            CameraEngine1.getInstance().setScreenDisplaySize(point);
        }
    }

    public static boolean isCameraReleased() {
        HVLog.d(TAG, "isCameraReleased() called");
        if (useCamera2) {
            return CameraEngine2.getInstance().isReleased();
        }
        return CameraEngine1.getInstance().isReleased();
    }

    public static void setZoomBase() {
        HVLog.d(TAG, "setZoomBase() called");
        if (useCamera2) {
            return;
        }
        CameraEngine1.getInstance().setZoomBase();
    }

    public static void setShouldUseDefaultZoom(boolean z) {
        HVLog.d(TAG, "setShouldUseDefaultZoom() called with: value = [" + z + "]");
        shouldUseDefaultZoom = z;
        if (z) {
            return;
        }
        zoomFactor = 1.0f;
    }

    public static void setDefaultZoom(float f) {
        HVLog.d(TAG, "setDefaultZoom() called with: delta = [" + f + "]");
        zoomFactor = f;
        shouldUseDefaultZoom = true;
        if (useCamera2) {
            CameraEngine2.getInstance().setZoom(f);
        } else {
            CameraEngine1.getInstance().setZoomLevel((int) f);
        }
    }

    public static void setZoom(float f) {
        HVLog.d(TAG, "setZoom() called with: delta = [" + f + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().setZoom(f);
        } else {
            CameraEngine1.getInstance().setZoom(f);
        }
    }

    public static void setUseEnhancedCameraFeatures(boolean z) {
        HVLog.d(TAG, "setUseEnhancedCameraFeatures() called with: enhancedCameraFeatures = [" + z + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().setUseEnhancedCamera(z);
        } else {
            CameraEngine1.getInstance().setUseEnhancedCamera(z);
        }
    }

    public static void resetZoom() {
        HVLog.d(TAG, "resetZoom() called");
        if (useCamera2) {
            return;
        }
        CameraEngine1.getInstance().resetZoom();
    }

    public static boolean isSetPreviewCallback() {
        HVLog.d(TAG, "isSetPreviewCallback() called");
        return setPreviewCallback;
    }

    public static void setPreviewCallback(boolean z) {
        HVLog.d(TAG, "setPreviewCallback() called with: setPreviewCallback = [" + z + "]");
        setPreviewCallback = z;
    }

    public static void rotateNV21a(byte[] bArr, int i, int i2, int i3, byte[] bArr2) {
        int i4;
        int i5;
        if (i3 % 90 != 0 || i3 < 0 || i3 > 270) {
            throw new IllegalArgumentException("0 <= rotation < 360, rotation % 90 == 0");
        }
        boolean z = i3 % 180 != 0;
        boolean z2 = i3 % 270 != 0;
        boolean z3 = i3 >= 180;
        int i6 = z ? i2 : i;
        int i7 = z ? i : i2;
        if (z) {
            i4 = (z2 ? i6 - 1 : 0) + (z3 ? (i7 - 1) * i6 : 0);
            i5 = (i7 * i6) + 1;
        } else {
            i4 = (z2 ? i6 - 1 : 0) + ((z3 ? i7 - 1 : 0) * i6);
            i5 = 0;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < i2; i9++) {
            if (z) {
                int i10 = 0;
                while (i10 < i) {
                    int i11 = i8 + 1;
                    bArr2[i4] = (byte) (bArr[i8] & 255);
                    i4 += z3 ? -i6 : i6;
                    i10++;
                    i8 = i11;
                }
                i4 += z3 ? i5 : -i5;
            } else {
                int i12 = 0;
                while (i12 < i) {
                    int i13 = i8 + 1;
                    bArr2[i4] = (byte) (bArr[i8] & 255);
                    i4 += z2 ? -1 : 1;
                    i12++;
                    i8 = i13;
                }
            }
        }
    }

    public static void getNextPreviewFrame() {
        HVLog.d(TAG, "getNextPreviewFrame() called");
        if (useCamera2) {
            CameraEngine2.getInstance().returnNextFrame = true;
        } else {
            CameraEngine1.getInstance().returnNextFrame = true;
        }
    }

    public static void willNeedNextFrame(boolean z) {
        HVLog.d(TAG, "willNeedNextFrame() called with: nextFramed = [" + z + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().willNeedNextFrame = z;
        }
    }

    public static boolean isFacePriority() {
        HVLog.d(TAG, "isFacePriority() called");
        return facePriority;
    }

    public static void setFacePriority(boolean z) {
        HVLog.d(TAG, "setFacePriority() called with: facePriority = [" + z + "]");
        facePriority = z;
    }

    public static void setExposure(double d) {
        HVLog.d(TAG, "setExposure() called with: exposure = [" + d + "]");
        if (useCamera2) {
            CameraEngine2.getInstance().setExposure(d);
        } else {
            CameraEngine1.getInstance().setExposure(d);
        }
    }

    public static boolean isScreenFlashSet() {
        HVLog.d(TAG, "isScreenFlashSet() called");
        return isScreenFlashSet;
    }

    public static void setScreenFlash(boolean z) {
        HVLog.d(TAG, "setScreenFlash() called with: isScreenFlashSet = [" + z + "]");
        isScreenFlashSet = z;
    }
}
