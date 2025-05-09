package co.hyperverge.hvcamera.magicfilter.utils;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/* loaded from: classes2.dex */
public class Camera2Utils {
    private static final String TAG = "Camera2Utils";
    public static Size newChosensize;

    public static Size getBestPreviewSize(StreamConfigurationMap streamConfigurationMap, int i, int i2, float f) {
        HVLog.d(TAG, "getBestPreviewSize() called with: sMap = [" + streamConfigurationMap + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "], megaPixels = [" + f + "]");
        Size size = new Size(480, 640);
        try {
            ArrayList<Size> arrayList = new ArrayList(Arrays.asList(streamConfigurationMap.getOutputSizes(SurfaceTexture.class)));
            sortSizeByClosestArea(arrayList, (int) (f * 1000000.0f));
            for (Size size2 : arrayList) {
                if (size2.getWidth() * i == size2.getHeight() * i2) {
                    return size2;
                }
            }
        } catch (AssertionError | Exception e) {
            HVLog.e(TAG, "getBestPreviewSize error: " + e.getLocalizedMessage());
        }
        return size;
    }

    public static Size getBestCaptureSize(StreamConfigurationMap streamConfigurationMap, int i, int i2, float f, boolean z) {
        HVLog.d(TAG, "getBestCaptureSize() called with: sMap = [" + streamConfigurationMap + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "], megaPixels = [" + f + "], shouldCaptureHighResolutionImage = [" + z + "]");
        ArrayList<Size> arrayList = new ArrayList(Arrays.asList(streamConfigurationMap.getOutputSizes(35)));
        convertToString(arrayList, "yuvResolutions");
        int i3 = (int) (1000000.0f * f);
        sortSizeByClosestArea(arrayList, i3);
        if (z && !arrayList.isEmpty()) {
            newChosensize = (Size) arrayList.get(0);
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Size size : arrayList) {
                if (size.getWidth() * i == size.getHeight() * i2 && i3 > size.getWidth() * size.getHeight() && size.getWidth() >= 480 && size.getHeight() >= 640) {
                    arrayList2.add(size);
                }
            }
            if (CameraEngine.shouldRandomize && arrayList2.size() > 1) {
                newChosensize = getRandomResolution(arrayList2);
            } else if (!arrayList2.isEmpty()) {
                newChosensize = (Size) arrayList2.get(0);
            }
        }
        return checkIfSizeExists(streamConfigurationMap, newChosensize, f, i, i2);
    }

    private static void convertToString(List<Size> list, String str) {
        HVLog.d(TAG, "convertToString() called with: captures = [" + list + "], keyName = [" + str + "]");
        try {
            String str2 = "";
            for (Size size : list) {
                str2 = str2 + size.getWidth() + "X" + size.getHeight();
            }
            CameraEngine.hardwareInfo.put(str, str2);
        } catch (Exception e) {
            HVLog.e(TAG, "convertToString: " + e.getLocalizedMessage());
        }
    }

    public static Size checkIfSizeExists(StreamConfigurationMap streamConfigurationMap, Size size, float f, int i, int i2) {
        HVLog.d(TAG, "checkIfSizeExists() called with: sMap = [" + streamConfigurationMap + "], chosenSize = [" + size + "], megaPixels = [" + f + "], mRatioWidth = [" + i + "], mRatioHeight = [" + i2 + "]");
        ArrayList<Size> arrayList = new ArrayList(Arrays.asList(streamConfigurationMap.getOutputSizes(256)));
        convertToString(arrayList, "jpgResolutions");
        sortSizeByClosestArea(arrayList, (int) (f * 1000000.0f));
        if (size == null || !arrayList.contains(size)) {
            for (Size size2 : arrayList) {
                if (size2.getWidth() * i == size2.getHeight() * i2) {
                    newChosensize = size2;
                    return size2;
                }
            }
        }
        return size;
    }

    public static Size getRandomResolution(List<Size> list) {
        HVLog.d(TAG, "getRandomResolution() called with: resolutions = [" + list + "]");
        int size = (list.size() - 1) + 0 + 1;
        newChosensize = list.get(new Random().nextInt(size) + 0);
        while (newChosensize.getWidth() == CameraEngine.lastUsedWidth && newChosensize.getHeight() == CameraEngine.lastUsedHeight && list.size() > 1) {
            newChosensize = list.get(new Random().nextInt(size) + 0);
        }
        return newChosensize;
    }

    public static void sortSizeByClosestArea(List<Size> list, final int i) {
        HVLog.d(TAG, "sortSizeByClosestArea() called with: sizes = [" + list + "], area = [" + i + "]");
        Collections.sort(list, new Comparator<Size>() { // from class: co.hyperverge.hvcamera.magicfilter.utils.Camera2Utils.1
            @Override // java.util.Comparator
            public int compare(Size size, Size size2) {
                return Math.abs((size.getWidth() * size.getHeight()) - i) - Math.abs((size2.getWidth() * size2.getHeight()) - i);
            }
        });
    }
}
