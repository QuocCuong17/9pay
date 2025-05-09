package co.hyperverge.hvcamera;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import androidx.constraintlayout.motion.widget.Key;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* loaded from: classes2.dex */
public class HVCamUtils {
    private static final String TAG = "HVCamUtils";
    public static int currentRotation = 0;
    public static int lastRotation = -1;
    public static Camera.Size newChosensize;

    public static void setCameraDisplayOrientation(int i, Camera camera) {
        int i2;
        HVLog.d(TAG, "setCameraDisplayOrientation() called with: cameraId = [" + i + "], camera = [" + camera + "]");
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((cameraInfo.orientation + 0) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation + 0) + 360) % 360;
        }
        camera.setDisplayOrientation(i2);
    }

    public static Camera getCameraInstance(int i) {
        HVLog.d(TAG, "getCameraInstance() called with: id = [" + i + "]");
        try {
            return Camera.open(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void sortSizes(List<Camera.Size> list) {
        HVLog.d(TAG, "sortSizes() called with: sizes = [" + list + "]");
        Collections.sort(list, new Comparator<Camera.Size>() { // from class: co.hyperverge.hvcamera.HVCamUtils.1
            @Override // java.util.Comparator
            public int compare(Camera.Size size, Camera.Size size2) {
                return (size2.width * size2.height) - (size.width * size.height);
            }
        });
    }

    public static void sortSizeByClosestArea(List<Camera.Size> list, final int i) {
        HVLog.d(TAG, "sortSizeByClosestArea() called with: sizes = [" + list + "], area = [" + i + "]");
        Collections.sort(list, new Comparator<Camera.Size>() { // from class: co.hyperverge.hvcamera.HVCamUtils.2
            @Override // java.util.Comparator
            public int compare(Camera.Size size, Camera.Size size2) {
                return Math.abs((size.width * size.height) - i) - Math.abs((size2.width * size2.height) - i);
            }
        });
    }

    public static void applyBestPreviewSize(Camera camera, int i, int i2, float f) {
        HVLog.d(TAG, "applyBestPreviewSize() called with: mCamera = [" + camera + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "], megapixels = [" + f + "]");
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size bestPreviewSize = getBestPreviewSize(camera, i, i2, f);
        parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
        camera.setParameters(parameters);
    }

    public static Camera.Size getBestPreviewSize(Camera camera, int i, int i2, float f) {
        HVLog.d(TAG, "getBestPreviewSize() called with: mCamera = [" + camera + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "], megapixels = [" + f + "]");
        if (!CameraEngine.getCaptureMode() && Build.VERSION.SDK_INT < 21) {
            return getBestPreviewKitkat(camera, i, i2, f);
        }
        List<Camera.Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
        sortSizeByClosestArea(supportedPreviewSizes, (int) (f * 1000000.0f));
        for (Camera.Size size : supportedPreviewSizes) {
            if (size.width * i == size.height * i2) {
                return size;
            }
        }
        return null;
    }

    public static Camera.Size getBestPreviewKitkat(Camera camera, int i, int i2, float f) {
        HVLog.d(TAG, "getBestPreviewKitkat() called with: mCamera = [" + camera + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "], megapixels = [" + f + "]");
        List<Camera.Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
        sortSizeByClosestArea(supportedPreviewSizes, 0);
        for (Camera.Size size : supportedPreviewSizes) {
            if ((Math.abs(size.width - 480) < 10 && Math.abs(size.height - 360) < 10) || (size.width == 1280 && size.height == 720)) {
                return size;
            }
        }
        return null;
    }

    public static Camera.Size getBestPictureSize(Camera camera, int i, int i2, float f, boolean z) {
        HVLog.d(TAG, "getBestPictureSize() called with: mCamera = [" + camera + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "], megapixels = [" + f + "], shouldCaptureHighResolutionImage = [" + z + "]");
        List<Camera.Size> supportedPictureSizes = camera.getParameters().getSupportedPictureSizes();
        convertToString(supportedPictureSizes);
        sortSizes(supportedPictureSizes);
        if (z && !supportedPictureSizes.isEmpty()) {
            newChosensize = supportedPictureSizes.get(0);
        }
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : supportedPictureSizes) {
            if (size.width * i == size.height * i2 && ((int) (1000000.0f * f)) > size.width * size.height && size.width >= 640 && size.height >= 480) {
                arrayList.add(size);
            }
        }
        if (CameraEngine.shouldRandomize && arrayList.size() > 1) {
            newChosensize = getRandomResolution(arrayList);
        } else if (!arrayList.isEmpty()) {
            newChosensize = (Camera.Size) arrayList.get(0);
        }
        return checkIfSizeExists(supportedPictureSizes, newChosensize, f, i, i2);
    }

    public static Camera.Size checkIfSizeExists(List<Camera.Size> list, Camera.Size size, float f, int i, int i2) {
        HVLog.d(TAG, "checkIfSizeExists() called with: captures = [" + list + "], chosenSize = [" + size + "], megapixels = [" + f + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "]");
        if (size == null || !list.contains(size)) {
            Iterator<Camera.Size> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size next = it.next();
                if (next.width * i == next.height * i2) {
                    newChosensize = next;
                    break;
                }
            }
        }
        return newChosensize;
    }

    private static void convertToString(List<Camera.Size> list) {
        HVLog.d(TAG, "convertToString() called with: captures = [" + list + "]");
        try {
            String str = "";
            for (Camera.Size size : list) {
                str = str + size.width + "X" + size.height;
            }
            CameraEngine.hardwareInfo.put("resolutions", str);
        } catch (Exception e) {
            HVLog.e(TAG, e.getLocalizedMessage());
        }
    }

    public static Camera.Size getRandomResolution(List<Camera.Size> list) {
        HVLog.d(TAG, "getRandomResolution() called with: resolutions = [" + list + "]");
        Camera.Size size = newChosensize;
        if (size != null) {
            return size;
        }
        int size2 = (list.size() - 1) + 0 + 1;
        newChosensize = list.get(new Random().nextInt(size2) + 0);
        while (newChosensize.width == CameraEngine.lastUsedWidth && newChosensize.height == CameraEngine.lastUsedHeight && list.size() > 1) {
            newChosensize = list.get(new Random().nextInt(size2) + 0);
        }
        return newChosensize;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0074, code lost:
    
        if (r5.get(androidx.constraintlayout.motion.widget.Key.ROTATION).equals("" + r3) == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void setPictureOrientation(int i, Camera camera, int i2) {
        int i3;
        HVLog.d(TAG, "setPictureOrientation() called with: cameraId = [" + i + "], mCamera = [" + camera + "], orientation = [" + i2 + "]");
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        int i4 = ((i2 + 45) / 90) * 90;
        if (cameraInfo.facing == 1) {
            i3 = ((cameraInfo.orientation - i4) + 360) % 360;
        } else {
            i3 = (cameraInfo.orientation + i4) % 360;
        }
        if (lastRotation != i3) {
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.get(Key.ROTATION) != null) {
            }
            parameters.setRotation(i3);
            camera.setParameters(parameters);
            currentRotation = i3;
            lastRotation = i3;
        }
    }

    public static void scanMediaFile(Context context, String str) {
        HVLog.d(TAG, "scanMediaFile() called with: context = [" + context + "], file = [" + str + "]");
        scanMediaFile(context, new File(str));
    }

    public static void scanMediaFile(Context context, File file) {
        HVLog.d(TAG, "scanMediaFile() called with: context = [" + context + "], file = [" + file + "]");
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
    }

    public static boolean hasSupport_16_9(Camera camera) {
        HVLog.d(TAG, "hasSupport_16_9() called with: camera = [" + camera + "]");
        if (!CameraEngine.getCaptureMode() && Build.VERSION.SDK_INT < 21) {
            return hasSupport_16_9KitKat(camera);
        }
        for (Camera.Size size : camera.getParameters().getSupportedPreviewSizes()) {
            if (size.width * 9 == size.height * 16) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSupport_16_9KitKat(Camera camera) {
        HVLog.d(TAG, "hasSupport_16_9KitKat() called with: camera = [" + camera + "]");
        List<Camera.Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
        sortSizeByClosestArea(supportedPreviewSizes, 0);
        for (Camera.Size size : supportedPreviewSizes) {
            if (Math.abs(size.width - 480) < 10 && Math.abs(size.height - 360) < 10) {
                return false;
            }
            if (size.width == 1280 && size.height == 720) {
                return true;
            }
        }
        return false;
    }
}
