package co.hyperverge.hypersnapsdk.components.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import co.hyperverge.hvcamera.HVCamHost;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hypersnapsdk.components.camera.model.FaceStateUIFlow;
import co.hyperverge.hypersnapsdk.components.camera.model.HVCamConfig;
import co.hyperverge.hypersnapsdk.helpers.SaveBitmapAsync;
import co.hyperverge.hypersnapsdk.model.CameraProperties;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import co.hyperverge.hypersnapsdk.utils.threading.ThreadExecutor;
import co.hyperverge.hypersnapsdk.views.CrossHairView;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: HVFacePreview.kt */
@Metadata(d1 = {"\u0000O\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0019*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u001f\u0010\u0015\u001a\u00020\u00032\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0017\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u001b\u001a\u00020\u0003H\u0016J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u0003H\u0016J\b\u0010\u001e\u001a\u00020\u0003H\u0016J\b\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010 \u001a\u00020\u0003H\u0016J<\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010#H\u0016J\b\u0010)\u001a\u00020\u0003H\u0016J\u0012\u0010*\u001a\u00020\u00032\b\u0010+\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010,\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010-\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0005H\u0016J\b\u0010.\u001a\u00020\u0003H\u0016J\b\u0010/\u001a\u00020\u0003H\u0016J\u0012\u00100\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u00101\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0005H\u0016J\b\u00104\u001a\u00020\u0003H\u0016J\b\u00105\u001a\u00020\u0003H\u0016J \u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u0012H\u0016J\u0010\u0010:\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u0005H\u0016¨\u0006<"}, d2 = {"co/hyperverge/hypersnapsdk/components/camera/HVFacePreview$hvCamHost$1", "Lco/hyperverge/hvcamera/HVCamHost;", "flashScreen", "", "getAspectRatio", "", "getCurrentVideoLength", "p0", "", "getPhotoDirectory", "Ljava/io/File;", "getPhotoFilename", "", "getPictureMegapixels", "", "getPreviewMegapixels", "getVideoFilename", "isShouldCaptureHighResolutionImage", "", "onCameraFlipCallback", "onCamerasFound", "onFaceDetection", "", "Landroid/hardware/Camera$Face;", "([Landroid/hardware/Camera$Face;)V", "onFilterMode", "p1", "onFlashAuto", "onFlashNull", "onFlashOff", "onFlashOn", "onFlashTorchOn", "onLayoutChange", "onNewPreviewFrame", "yuvData", "", "camWidth", "camHeight", "camOrientation", "camRotation", "rgbData", "onPictureFailed", "onPictureReady", "imageData", "onPictureSaved", "onPictureSizeSet", "onPictureTaken", "onReady", "onVideoSaved", "onViewDimensionChange", "width", "height", "setScreenFlashOff", "setScreenFlashOn", "showCrossHair", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "b", "zoomMaxLevel", "zoomMax", "hypersnapsdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class HVFacePreview$hvCamHost$1 extends HVCamHost {
    final /* synthetic */ HVFacePreview this$0;

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void flashScreen() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public int getAspectRatio() {
        return 1;
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void getCurrentVideoLength(long p0) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public boolean isShouldCaptureHighResolutionImage() {
        return false;
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onCameraFlipCallback() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onCamerasFound(int p0) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFaceDetection(Camera.Face[] p0) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFilterMode(int p0, String p1) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFlashAuto() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFlashNull() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFlashOff() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFlashOn() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onFlashTorchOn() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onLayoutChange() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onPictureSaved(File p0) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onPictureSizeSet(int p0, int p1) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onPictureTaken() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onVideoSaved(File p0) {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void setScreenFlashOff() {
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void setScreenFlashOn() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HVFacePreview$hvCamHost$1(HVFacePreview hVFacePreview) {
        this.this$0 = hVFacePreview;
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onPictureFailed() {
        String str;
        HVMagicView hVMagicView;
        str = this.this$0.TAG;
        HVLogUtils.d(str, "onPictureFailed() called");
        this.this$0.safeToTakePicture = true;
        hVMagicView = this.this$0.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onResume();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0068  */
    @Override // co.hyperverge.hvcamera.HVCamHost
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public File getPhotoDirectory() {
        String str;
        File file;
        Exception e;
        String str2;
        String str3;
        String str4;
        str = this.this$0.TAG;
        HVLogUtils.d(str, "getPhotoDirectory() called");
        try {
            file = new File(this.this$0.getContext().getFilesDir(), "hv");
        } catch (Exception e2) {
            file = null;
            e = e2;
        }
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e3) {
            e = e3;
            str2 = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("getPhotoDirectory(): exception = [");
            Exception exc = e;
            sb.append((Object) Utils.getErrorMessage(exc));
            sb.append(']');
            HVLogUtils.e(str2, sb.toString(), exc);
            if (file == null) {
            }
            Intrinsics.checkNotNull(file);
            return file;
        }
        if (file == null) {
            str4 = this.this$0.TAG;
            HVLogUtils.d(str4, Intrinsics.stringPlus("getPhotoDirectory() returned with folder path: ", file.getPath()));
        } else {
            str3 = this.this$0.TAG;
            HVLogUtils.d(str3, "getPhotoDirectory() folder is null");
        }
        Intrinsics.checkNotNull(file);
        return file;
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public String getPhotoFilename() {
        return "FD_" + System.currentTimeMillis() + ".jpg";
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public String getVideoFilename() {
        return "FD_" + System.currentTimeMillis() + ".mp4";
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public float getPreviewMegapixels() {
        HVCamConfig hVCamConfig;
        hVCamConfig = this.this$0.camConfig;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        return hVCamConfig.getPreviewMegaPixel();
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public float getPictureMegapixels() {
        HVCamConfig hVCamConfig;
        hVCamConfig = this.this$0.camConfig;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        return hVCamConfig.getPictureMegaPixel();
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onReady() {
        HVMagicView hVMagicView;
        hVMagicView = this.this$0.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.startAccelerometer();
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void showCrossHair(final float x, final float y, final boolean b) {
        CrossHairView crossHairView;
        crossHairView = this.this$0.crossHairView;
        if (crossHairView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
        }
        final HVFacePreview hVFacePreview = this.this$0;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hypersnapsdk.components.camera.HVFacePreview$hvCamHost$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HVFacePreview$hvCamHost$1.m513showCrossHair$lambda1$lambda0(x, y, hVFacePreview, b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showCrossHair$lambda-1$lambda-0, reason: not valid java name */
    public static final void m513showCrossHair$lambda1$lambda0(float f, float f2, HVFacePreview this$0, boolean z) {
        CrossHairView crossHairView;
        int i;
        int i2;
        CrossHairView crossHairView2;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CrossHairView crossHairView3 = null;
        if (f > 0.0f || f2 > 0.0f) {
            crossHairView = this$0.crossHairView;
            if (crossHairView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
            } else {
                crossHairView3 = crossHairView;
            }
            i = this$0.camViewWidth;
            i2 = this$0.camViewHeight;
            crossHairView3.showCrosshair(f * i, f2 * i2, z);
            return;
        }
        crossHairView2 = this$0.crossHairView;
        if (crossHairView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crossHairView");
        } else {
            crossHairView3 = crossHairView2;
        }
        i3 = this$0.camViewWidth;
        i4 = this$0.camViewHeight;
        crossHairView3.showCrosshair(i3 / 2, i4 / 2, z);
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onPictureReady(byte[] imageData) {
        HVMagicView hVMagicView;
        final String photoFilename = getPhotoFilename();
        hVMagicView = this.this$0.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onPause();
        ThreadExecutor threadExecutor = ThreadExecutor.getInstance();
        String absolutePath = getPhotoDirectory().getAbsolutePath();
        HVFaceConfig hVFaceConfig = new HVFaceConfig();
        final HVFacePreview hVFacePreview = this.this$0;
        threadExecutor.execute(new SaveBitmapAsync(imageData, null, absolutePath, photoFilename, hVFaceConfig, "", new SaveBitmapAsync.SaveCallBack() { // from class: co.hyperverge.hypersnapsdk.components.camera.HVFacePreview$hvCamHost$1$$ExternalSyntheticLambda0
            @Override // co.hyperverge.hypersnapsdk.helpers.SaveBitmapAsync.SaveCallBack
            public final void onImageSaved(String str, List list, boolean z) {
                HVFacePreview$hvCamHost$1.m512onPictureReady$lambda3(HVFacePreview.this, this, photoFilename, str, list, z);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onPictureReady$lambda-3, reason: not valid java name */
    public static final void m512onPictureReady$lambda3(HVFacePreview this$0, HVFacePreview$hvCamHost$1 this$1, String fileName, String str, List list, boolean z) {
        String str2;
        HVMagicView hVMagicView;
        MutableStateFlow mutableStateFlow;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        str2 = this$0.TAG;
        Log.d(str2, Intrinsics.stringPlus("onPictureReady: onImageSaved with filePath : ", str));
        if (str != null) {
            mutableStateFlow = this$0.faceUIStateFlow;
            mutableStateFlow.tryEmit(new FaceStateUIFlow.FaceCapture(this$1.getPhotoDirectory().getAbsolutePath() + '/' + fileName, str));
        }
        hVMagicView = this$0.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.onResume();
        this$0.safeToTakePicture = true;
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onNewPreviewFrame(byte[] yuvData, int camWidth, int camHeight, int camOrientation, int camRotation, byte[] rgbData) {
        HVCamConfig hVCamConfig;
        HVCamConfig hVCamConfig2;
        HVCamConfig hVCamConfig3;
        CameraProperties cameraProperties = new CameraProperties();
        HVFacePreview hVFacePreview = this.this$0;
        cameraProperties.width = camWidth;
        cameraProperties.height = camHeight;
        hVCamConfig = hVFacePreview.camConfig;
        HVCamConfig hVCamConfig4 = null;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        cameraProperties.viewWidth = hVCamConfig.getDiameter();
        hVCamConfig2 = hVFacePreview.camConfig;
        if (hVCamConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig2 = null;
        }
        cameraProperties.viewHeight = hVCamConfig2.getDiameter();
        cameraProperties.orientation = camOrientation;
        cameraProperties.rotation = camRotation;
        cameraProperties.data = yuvData;
        hVCamConfig3 = hVFacePreview.camConfig;
        if (hVCamConfig3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
        } else {
            hVCamConfig4 = hVCamConfig3;
        }
        cameraProperties.isFrontFacingCam = !hVCamConfig4.getUseBackCamera();
        this.this$0.processFrame(cameraProperties);
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void onViewDimensionChange(int width, int height) {
        String str;
        HVMagicView hVMagicView;
        str = this.this$0.TAG;
        HVLogUtils.d(str, "onViewDimensionChange() called with: width = " + width + ", height = " + height);
        this.this$0.camViewHeight = height;
        this.this$0.camViewWidth = width;
        this.this$0.adjustCrossHairView();
        hVMagicView = this.this$0.cameraView;
        if (hVMagicView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cameraView");
            hVMagicView = null;
        }
        hVMagicView.invalidate();
    }

    @Override // co.hyperverge.hvcamera.HVCamHost
    public void zoomMaxLevel(int zoomMax) {
        String str;
        HVCamConfig hVCamConfig;
        HVCamConfig hVCamConfig2;
        str = this.this$0.TAG;
        HVLogUtils.d(str, Intrinsics.stringPlus("zoomMaxLevel() called with: zoomMax = ", Integer.valueOf(zoomMax)));
        hVCamConfig = this.this$0.camConfig;
        HVCamConfig hVCamConfig3 = null;
        if (hVCamConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            hVCamConfig = null;
        }
        if (hVCamConfig.getShouldZoomPreview()) {
            hVCamConfig2 = this.this$0.camConfig;
            if (hVCamConfig2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("camConfig");
            } else {
                hVCamConfig3 = hVCamConfig2;
            }
            if (hVCamConfig3.getUseBackCamera()) {
                CameraEngine.setDefaultZoom(AppConstants.ZOOM_FACTOR_BACK_CAM);
                return;
            } else {
                CameraEngine.setDefaultZoom(AppConstants.ZOOM_FACTOR_FRONT_CAM);
                return;
            }
        }
        CameraEngine.setShouldUseDefaultZoom(false);
    }
}
