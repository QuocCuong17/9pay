package co.hyperverge.hvcamera.magicfilter.utils;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.MeteringRectangle;
import co.hyperverge.hvcamera.HVLog;

/* loaded from: classes2.dex */
public class AutoFocusHelper {
    private static final int CAMERA2_REGION_WEIGHT = 1000;
    private static final String TAG = "AutoFocusHelper";
    private static final MeteringRectangle[] ZERO_WEIGHT_3A_REGION = {new MeteringRectangle(0, 0, 0, 0, 0)};

    public static MeteringRectangle[] getZeroWeightRegion() {
        return ZERO_WEIGHT_3A_REGION;
    }

    private static MeteringRectangle[] regionsForNormalizedCoord(float f, float f2, float f3, Rect rect, int i) {
        HVLog.d(TAG, "regionsForNormalizedCoord() called with: nx = [" + f + "], ny = [" + f2 + "], fraction = [" + f3 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        if (rect != null && rect.width() >= 0 && rect.height() >= 0) {
            int min = (int) (f3 * 0.5f * Math.min(rect.width(), rect.height()));
            PointF normalizedSensorCoordsForNormalizedDisplayCoords = normalizedSensorCoordsForNormalizedDisplayCoords(f, f2, i);
            if (normalizedSensorCoordsForNormalizedDisplayCoords != null && normalizedSensorCoordsForNormalizedDisplayCoords.x >= 0.0f && normalizedSensorCoordsForNormalizedDisplayCoords.y >= 0.0f) {
                int width = (int) (rect.left + (normalizedSensorCoordsForNormalizedDisplayCoords.x * rect.width()));
                int height = (int) (rect.top + (normalizedSensorCoordsForNormalizedDisplayCoords.y * rect.height()));
                Rect rect2 = new Rect(width - min, height - min, width + min, height + min);
                rect2.left = clamp(rect2.left, rect.left, rect.right);
                rect2.top = clamp(rect2.top, rect.top, rect.bottom);
                rect2.right = clamp(rect2.right, rect.left, rect.right);
                rect2.bottom = clamp(rect2.bottom, rect.top, rect.bottom);
                return new MeteringRectangle[]{new MeteringRectangle(rect2, 1000)};
            }
        }
        return null;
    }

    private static MeteringRectangle[] regionsForRectangle(float f, float f2, float f3, float f4, Rect rect, int i) {
        PointF normalizedSensorCoordsForNormalizedDisplayCoords;
        PointF normalizedSensorCoordsForNormalizedDisplayCoords2;
        HVLog.d(TAG, "regionsForRectangle() called with: t = [" + f + "], b = [" + f2 + "], l = [" + f3 + "], r = [" + f4 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        if (rect == null || (normalizedSensorCoordsForNormalizedDisplayCoords = normalizedSensorCoordsForNormalizedDisplayCoords(f3, f, i)) == null || (normalizedSensorCoordsForNormalizedDisplayCoords2 = normalizedSensorCoordsForNormalizedDisplayCoords(f4, f2, i)) == null) {
            return null;
        }
        Rect rect2 = new Rect((int) Math.min(normalizedSensorCoordsForNormalizedDisplayCoords2.x * rect.width(), normalizedSensorCoordsForNormalizedDisplayCoords.x * rect.width()), (int) Math.min(normalizedSensorCoordsForNormalizedDisplayCoords2.y * rect.height(), normalizedSensorCoordsForNormalizedDisplayCoords.y * rect.height()), (int) Math.max(normalizedSensorCoordsForNormalizedDisplayCoords2.x * rect.width(), normalizedSensorCoordsForNormalizedDisplayCoords.x * rect.width()), (int) Math.max(normalizedSensorCoordsForNormalizedDisplayCoords2.y * rect.height(), normalizedSensorCoordsForNormalizedDisplayCoords.y * rect.height()));
        rect2.left = clamp(rect2.left, rect.left, rect.right);
        rect2.top = clamp(rect2.top, rect.top, rect.bottom);
        rect2.right = clamp(rect2.right, rect.left, rect.right);
        rect2.bottom = clamp(rect2.bottom, rect.top, rect.bottom);
        return new MeteringRectangle[]{new MeteringRectangle(rect2, 1000)};
    }

    public static int clamp(int i, int i2, int i3) {
        HVLog.d(TAG, "clamp() called with: x = [" + i + "], min = [" + i2 + "], max = [" + i3 + "]");
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    public static PointF normalizedSensorCoordsForNormalizedDisplayCoords(float f, float f2, int i) {
        HVLog.d(TAG, "normalizedSensorCoordsForNormalizedDisplayCoords() called with: nx = [" + f + "], ny = [" + f2 + "], sensorOrientation = [" + i + "]");
        if (i == 0) {
            return new PointF(f, f2);
        }
        if (i == 90) {
            return new PointF(f2, 1.0f - f);
        }
        if (i == 180) {
            return new PointF(1.0f - f, 1.0f - f2);
        }
        if (i != 270) {
            return null;
        }
        return new PointF(1.0f - f2, f);
    }

    public static MeteringRectangle[] afRegionsForNormalizedCoord(float f, float f2, Rect rect, int i) {
        HVLog.d(TAG, "afRegionsForNormalizedCoord() called with: nx = [" + f + "], ny = [" + f2 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        return regionsForNormalizedCoord(f, f2, Settings3A.getAutoFocusRegionWidth(), rect, i);
    }

    public static MeteringRectangle[] aeRegionsForNormalizedCoord(float f, float f2, Rect rect, int i) {
        HVLog.d(TAG, "aeRegionsForNormalizedCoord() called with: nx = [" + f + "], ny = [" + f2 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        return regionsForNormalizedCoord(f, f2, Settings3A.getMeteringRegionWidth(), rect, i);
    }

    public static MeteringRectangle[] afRegionsForRectangle(float f, float f2, float f3, float f4, Rect rect, int i) {
        HVLog.d(TAG, "afRegionsForRectangle() called with: top = [" + f + "], bottom = [" + f2 + "], left = [" + f3 + "], right = [" + f4 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        return regionsForRectangle(f, f2, f3, f4, rect, i);
    }

    public static MeteringRectangle[] aeRegionsForRectangle(float f, float f2, float f3, float f4, Rect rect, int i) {
        HVLog.d(TAG, "aeRegionsForRectangle() called with: top = [" + f + "], bottom = [" + f2 + "], left = [" + f3 + "], right = [" + f4 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        return regionsForRectangle(f, f2, f3, f4, rect, i);
    }

    public static MeteringRectangle[] gcamAERegionsForNormalizedCoord(float f, float f2, Rect rect, int i) {
        HVLog.d(TAG, "gcamAERegionsForNormalizedCoord() called with: nx = [" + f + "], ny = [" + f2 + "], cropRegion = [" + rect + "], sensorOrientation = [" + i + "]");
        return regionsForNormalizedCoord(f, f2, Settings3A.getGcamMeteringRegionFraction(), rect, i);
    }

    public static Rect cropRegionForZoom(CameraCharacteristics cameraCharacteristics, float f, float f2) throws Exception {
        HVLog.d(TAG, "cropRegionForZoom() called with: characteristics = [" + cameraCharacteristics + "], zoomLevel = [" + f + "], maxZoom = [" + f2 + "]");
        Rect rect = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (rect == null) {
            throw new Exception("sensor rect is null");
        }
        int width = rect.width() / 2;
        int height = rect.height() / 2;
        int width2 = (int) ((rect.width() * 0.5f) / f);
        int height2 = (int) ((rect.height() * 0.5f) / f);
        return new Rect(width - width2, height - height2, width + width2, height + height2);
    }

    private static String controlAFStateToString(int i) {
        HVLog.d(TAG, "controlAFStateToString() called with: controlAFState = [" + i + "]");
        switch (i) {
            case 0:
                return "inactive";
            case 1:
                return "passive_scan";
            case 2:
                return "passive_focused";
            case 3:
                return "active_scan";
            case 4:
                return "focus_locked";
            case 5:
                return "not_focus_locked";
            case 6:
                return "passive_unfocused";
            default:
                return "unknown";
        }
    }

    private static String lensStateToString(int i) {
        HVLog.d(TAG, "lensStateToString() called with: lensState = [" + i + "]");
        return i != 0 ? i != 1 ? "unknown" : "moving" : "stationary";
    }
}
