package co.hyperverge.hvcamera;

import android.hardware.Camera;
import java.io.File;

/* loaded from: classes2.dex */
public abstract class HVCamHost {
    public static final int ASPECT_16_9 = 2;
    public static final int ASPECT_4_3 = 1;
    private static final String TAG = "HVCamHost";

    public abstract void flashScreen();

    public abstract int getAspectRatio();

    public abstract void getCurrentVideoLength(long j);

    public abstract File getPhotoDirectory();

    public abstract String getPhotoFilename();

    public abstract float getPictureMegapixels();

    public abstract float getPreviewMegapixels();

    public abstract String getVideoFilename();

    public abstract boolean isShouldCaptureHighResolutionImage();

    public abstract void onCameraFlipCallback();

    public abstract void onCamerasFound(int i);

    public abstract void onFaceDetection(Camera.Face[] faceArr);

    public abstract void onFilterMode(int i, String str);

    public abstract void onFlashAuto();

    public abstract void onFlashNull();

    public abstract void onFlashOff();

    public abstract void onFlashOn();

    public abstract void onFlashTorchOn();

    public abstract void onLayoutChange();

    public abstract void onNewPreviewFrame(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2);

    public abstract void onPictureFailed();

    public abstract void onPictureReady(byte[] bArr);

    public abstract void onPictureSaved(File file);

    public abstract void onPictureSizeSet(int i, int i2);

    public abstract void onPictureTaken();

    public abstract void onReady();

    public abstract void onVideoSaved(File file);

    public abstract void onViewDimensionChange(int i, int i2);

    public abstract void setScreenFlashOff();

    public abstract void setScreenFlashOn();

    public abstract void showCrossHair(float f, float f2, boolean z);

    public abstract void zoomMaxLevel(int i);

    public File getPhotoPath() {
        HVLog.d(TAG, "getPhotoPath() called");
        File photoDirectory = getPhotoDirectory();
        photoDirectory.mkdirs();
        return new File(photoDirectory, getPhotoFilename());
    }

    public File getVideoPath() {
        HVLog.d(TAG, "getVideoPath() called");
        File photoDirectory = getPhotoDirectory();
        photoDirectory.mkdirs();
        return new File(photoDirectory, getVideoFilename());
    }
}
