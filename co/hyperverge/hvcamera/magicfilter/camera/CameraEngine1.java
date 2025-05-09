package co.hyperverge.hvcamera.magicfilter.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import co.hyperverge.hvcamera.HVCamUtils;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.utils.BitmapUtil;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class CameraEngine1 {
    private static final String TAG = "CameraEngine1";
    private static List<String> availableFlashmodes;
    private static final List<String> cancelAutoFocusList;
    static String currentMode;
    public static CameraEngine1 instance;
    static Matrix matrix;
    private static final List<String> stopAutoFocusList;
    private static final List<String> supportedFlashModesStillImage;
    private static final List<String> supportedFlashModesVideo;
    private Camera.Parameters currParameters;
    private int data2counter;
    private String focusMode;
    private boolean isStillImageMode;
    private Camera.PictureCallback pictureCallback;
    private ScheduledExecutorService scheduler;
    private int screenHeight;
    private int screenWidth;
    private Camera.ShutterCallback shutterCallback;
    private boolean useEnhancedCamera;
    private float zoomCurrent;
    private int zoomMax;
    private List<Integer> zooms;
    private final String LOG_TAG = CameraEngine1.class.getCanonicalName();
    public boolean returnNextFrame = false;
    private Camera mCamera = null;
    private int mCameraID = getFrontCameraId();
    private boolean isDetectingFace = false;
    private boolean isZoomSupported = false;
    private float zoomBase = 0.0f;
    private boolean isReleased = true;
    private final byte[][] data2 = new byte[5];
    private final byte[][] byteBuffer = new byte[5];
    private int currentOrientation = 0;
    private boolean isScreenFlashOn = false;
    private boolean isCameraInitialised = false;

    private static int clamp(int i) {
        if (i > 1000) {
            return 1000;
        }
        if (i < -1000) {
            return -1000;
        }
        return i;
    }

    static {
        ArrayList arrayList = new ArrayList();
        supportedFlashModesStillImage = arrayList;
        ArrayList arrayList2 = new ArrayList();
        supportedFlashModesVideo = arrayList2;
        matrix = new Matrix();
        ArrayList arrayList3 = new ArrayList();
        cancelAutoFocusList = arrayList3;
        ArrayList arrayList4 = new ArrayList();
        stopAutoFocusList = arrayList4;
        arrayList3.add("SM-J200G");
        arrayList3.add("SM-J120G");
        arrayList3.add("SM-T285");
        arrayList4.add("LLD-AL10");
        arrayList4.add("vivo 1814");
        arrayList.add("off");
        arrayList.add("on");
        arrayList.add("torch");
        arrayList2.add("off");
        arrayList2.add("torch");
    }

    public static CameraEngine1 getInstance() {
        if (instance == null) {
            instance = new CameraEngine1();
        }
        return instance;
    }

    public void destroyEngine() {
        HVLog.d(TAG, "destroyEngine() called");
        instance = null;
    }

    public void setInitialFrontCam(boolean z) {
        this.scheduler = Executors.newScheduledThreadPool(1);
        if (z) {
            this.mCameraID = getFrontCameraId();
        } else {
            this.mCameraID = 0;
        }
    }

    public void switchCamera() {
        HVLog.d(TAG, "switchCamera() called");
        releaseCamera();
        if (this.mCameraID == 0) {
            this.mCameraID = 1;
        } else {
            this.mCameraID = 0;
        }
        openCamera();
        startPreview();
    }

    public void setZoomLevel(int i) {
        HVLog.d(TAG, "setZoomLevel() called with: scale = [" + i + "]");
        List<Integer> list = this.zooms;
        if (list == null || list.isEmpty()) {
            return;
        }
        int intValue = this.zooms.get(0).intValue();
        for (int i2 = 0; i2 < this.zooms.size(); i2++) {
            if (intValue > 0 && intValue * i < this.zooms.get(i2).intValue()) {
                float f = i2;
                this.zoomCurrent = f;
                zoomModify((int) f);
                return;
            }
        }
    }

    public void changeCamera() {
        HVLog.d(TAG, "changeCamera() called");
        openCamera();
        startPreview();
    }

    public void setScreenDisplaySize(Point point) {
        HVLog.d(TAG, "setScreenDisplaySize() called with: size = [" + point + "]");
        this.screenWidth = point.x;
        this.screenHeight = point.y;
    }

    public Camera getCamera() {
        return this.mCamera;
    }

    public void addPreviewCallback(byte[] bArr) {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.addCallbackBuffer(bArr);
        }
    }

    public void setPreviewCallbackWithBuffer(Camera.PreviewCallback previewCallback) {
        HVLog.d(TAG, "setPreviewCallbackWithBuffer() called with: previewCallback = [" + previewCallback + "]");
        this.mCamera.setPreviewCallbackWithBuffer(previewCallback);
    }

    public boolean openCamera() {
        HVLog.d(TAG, "openCamera() called");
        try {
            this.isScreenFlashOn = false;
            try {
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.setScreenFlashOff();
                }
            } catch (Exception e) {
                Log.d(this.LOG_TAG, e.getMessage());
            }
            this.mCamera.getParameters();
            this.isReleased = false;
        } catch (Throwable th) {
            HVLog.e(TAG, "openCamera: exception -" + th.getMessage());
            this.isReleased = true;
            this.mCamera = null;
        }
        Camera camera = this.mCamera;
        if (camera == null) {
            try {
                this.mCamera = Camera.open(this.mCameraID);
                this.isReleased = false;
                HVMagicView.updateRatios();
                setDefaultParameters();
                List<String> supportedFlashModes = this.mCamera.getParameters().getSupportedFlashModes();
                if (supportedFlashModes != null) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : supportedFlashModes) {
                        if (supportedFlashModesStillImage.contains(str)) {
                            arrayList.add(str);
                        }
                    }
                    availableFlashmodes = arrayList;
                } else {
                    availableFlashmodes = null;
                }
                currentMode = this.mCamera.getParameters().getFlashMode();
                updateCamHostFlashMode();
                if (CameraEngine.isFacePriority()) {
                    this.mCamera.setFaceDetectionListener(new Camera.FaceDetectionListener() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.1
                        @Override // android.hardware.Camera.FaceDetectionListener
                        public void onFaceDetection(Camera.Face[] faceArr, Camera camera2) {
                        }
                    });
                    if (this.mCamera.getParameters().getMaxNumDetectedFaces() > 0 && !this.isDetectingFace) {
                        try {
                            this.isDetectingFace = true;
                            this.mCamera.startFaceDetection();
                        } catch (RuntimeException e2) {
                            HVLog.e(TAG, e2.getMessage());
                        }
                    }
                }
                Camera.Parameters parameters = this.mCamera.getParameters();
                this.currParameters = parameters;
                this.isZoomSupported = parameters.isZoomSupported();
                this.zoomMax = this.currParameters.getMaxZoom();
                this.zooms = this.currParameters.getZoomRatios();
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.zoomMaxLevel(this.zoomMax);
                    HVMagicView.camHost.onPictureSizeSet(HVCamUtils.newChosensize.width, HVCamUtils.newChosensize.height);
                }
                for (int i = 0; i < 5; i++) {
                    this.byteBuffer[i] = new byte[((getPreviewSize().width * getPreviewSize().height) * ImageFormat.getBitsPerPixel(17)) / 8];
                    addPreviewCallback(this.byteBuffer[i]);
                }
                setPreviewCallbackWithBuffer(getPreviewCallBack());
                return true;
            } catch (RuntimeException e3) {
                if (!TextUtils.isEmpty(e3.getMessage())) {
                    HVLog.e(TAG, "openCamera: " + e3.getMessage());
                }
                return false;
            }
        }
        try {
            Camera.Parameters parameters2 = camera.getParameters();
            this.currParameters = parameters2;
            this.isZoomSupported = parameters2.isZoomSupported();
            HVLog.d("scale", "supported ?? ::" + this.isZoomSupported);
            this.zoomMax = this.currParameters.getMaxZoom();
            if (HVMagicView.camHost != null) {
                HVMagicView.camHost.zoomMaxLevel(this.zoomMax);
            }
            return false;
        } catch (Throwable th2) {
            HVLog.e(TAG, "openCamera: " + th2.getMessage());
            this.isReleased = true;
            this.mCamera = null;
            return openCamera();
        }
    }

    public void releaseCamera() {
        HVLog.d(TAG, "releaseCamera() called");
        if (this.mCamera != null) {
            HVCamUtils.newChosensize = null;
            if (this.isDetectingFace && CameraEngine.isFacePriority()) {
                try {
                    this.isDetectingFace = false;
                    this.mCamera.stopFaceDetection();
                } catch (RuntimeException e) {
                    HVLog.e(TAG, "releaseCamera: " + e.getMessage());
                }
            }
            this.currParameters = null;
            try {
                this.mCamera.setPreviewCallback(null);
            } catch (Exception e2) {
                HVLog.e(TAG, "releaseCamera: " + e2.getMessage());
            }
            try {
                this.mCamera.stopPreview();
            } catch (Exception e3) {
                HVLog.e(TAG, "releaseCamera: " + e3.getMessage());
            }
            try {
                this.mCamera.release();
            } catch (Exception e4) {
                HVLog.e(TAG, "releaseCamera: " + e4.getMessage());
            }
            this.mCamera = null;
            this.isReleased = true;
        }
    }

    public boolean isReleased() {
        return this.isReleased;
    }

    public void setFocusMode(String str) {
        Camera.Parameters parameters;
        Camera camera = this.mCamera;
        if (camera == null || (parameters = camera.getParameters()) == null) {
            return;
        }
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (!supportedFocusModes.contains(str)) {
            str = "auto";
            if (!supportedFocusModes.contains("auto")) {
                return;
            }
        }
        parameters.setFocusMode(str);
        this.focusMode = str;
        Camera camera2 = this.mCamera;
        if (camera2 != null) {
            camera2.setParameters(parameters);
        }
    }

    public Camera.Size getPreviewSize() {
        if (this.isReleased) {
            return null;
        }
        return this.mCamera.getParameters().getPreviewSize();
    }

    public void startPreview(SurfaceTexture surfaceTexture) {
        HVLog.d(TAG, "startPreview() called with: surfaceTexture = [" + surfaceTexture + "]");
        try {
            this.mCamera.setPreviewTexture(surfaceTexture);
            this.mCamera.startPreview();
            setOrientation(0);
        } catch (Exception e) {
            HVLog.e(TAG, "startPreview: " + e.getMessage());
        }
    }

    public void startPreview() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.startPreview();
            this.mCamera.cancelAutoFocus();
        }
    }

    public Camera.CameraInfo getCameraInfo() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.mCameraID, cameraInfo);
        return cameraInfo;
    }

    public int getOrientation() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.mCameraID, cameraInfo);
        return cameraInfo.orientation;
    }

    public void setOrientation(int i) {
        HVLog.d(TAG, "setOrientation() called with: orientation = [" + i + "]");
        this.currentOrientation = i % 360;
        try {
            Camera camera = this.mCamera;
            if (camera == null || this.isReleased) {
                return;
            }
            HVCamUtils.setPictureOrientation(this.mCameraID, camera, i % 360);
        } catch (Exception e) {
            HVLog.e(TAG, "setOrientation: " + e.getMessage());
        }
    }

    public boolean isFrontFacingCamera() {
        try {
            return getCameraInfo().facing == 1;
        } catch (Exception e) {
            HVLog.e(TAG, "isFrontFacingCamera: " + e.getMessage());
            return false;
        }
    }

    public void takePicture(Camera.ShutterCallback shutterCallback, Camera.PictureCallback pictureCallback, Camera.PictureCallback pictureCallback2) {
        HVLog.d(TAG, "takePicture() called with: shuttercallback = [" + shutterCallback + "], rawCallback = [" + pictureCallback + "], jpegCallback = [" + pictureCallback2 + "]");
        try {
            Camera.Parameters parameters = this.mCamera.getParameters();
            this.shutterCallback = shutterCallback;
            this.pictureCallback = pictureCallback2;
            if (parameters.getMaxNumFocusAreas() > 0 && this.mCameraID == 0) {
                this.mCamera.cancelAutoFocus();
                final ScheduledFuture<?> schedule = this.scheduler.schedule(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!CameraEngine1.cancelAutoFocusList.contains(Build.MODEL) && !CameraEngine1.stopAutoFocusList.contains(Build.MODEL)) {
                            CameraEngine1.this.mCamera.cancelAutoFocus();
                        }
                        CameraEngine1.this.takePictureInternal();
                    }
                }, 1000L, TimeUnit.MILLISECONDS);
                if (!stopAutoFocusList.contains(Build.MODEL)) {
                    this.mCamera.autoFocus(new Camera.AutoFocusCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.3
                        @Override // android.hardware.Camera.AutoFocusCallback
                        public void onAutoFocus(boolean z, Camera camera) {
                            if (schedule.cancel(false)) {
                                CameraEngine1.this.takePictureInternal();
                            }
                        }
                    });
                }
            } else {
                takePictureInternal();
            }
        } catch (Exception e) {
            HVLog.e(TAG, "takePicture: " + e.getMessage());
            takePictureInternal();
        }
    }

    public void takePictureInternal() {
        HVLog.d(TAG, "takePictureInternal() called");
        if (this.mCamera != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.4
                @Override // java.lang.Runnable
                public void run() {
                    if (!CameraEngine.isScreenFlashSet()) {
                        try {
                            CameraEngine1.this.mCamera.takePicture(CameraEngine1.this.shutterCallback, null, CameraEngine1.this.pictureCallback);
                            return;
                        } catch (Exception e) {
                            HVLog.e(CameraEngine1.TAG, "takePictureInternal: " + e.getMessage());
                            try {
                                if (HVMagicView.camHost != null) {
                                    HVMagicView.camHost.onPictureFailed();
                                    return;
                                }
                                return;
                            } catch (Exception e2) {
                                HVLog.e(CameraEngine1.TAG, "takePictureInternal: " + e2.getMessage());
                                return;
                            }
                        }
                    }
                    if (HVMagicView.camHost != null) {
                        HVMagicView.camHost.setScreenFlashOn();
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CameraEngine1.this.mCamera.takePicture(CameraEngine1.this.shutterCallback, null, CameraEngine1.this.pictureCallback);
                        }
                    }, 500L);
                }
            });
        }
    }

    public void resetZoom() {
        HVLog.d(TAG, "resetZoom() called");
        if (CameraEngine.shouldUseDefaultZoom) {
            Camera.Parameters parameters = this.mCamera.getParameters();
            parameters.setZoom(1);
            this.mCamera.setParameters(parameters);
        }
    }

    public void autoFocus() {
        HVLog.d(TAG, "autoFocus() called");
        try {
            Camera camera = this.mCamera;
            if (camera != null) {
                camera.autoFocus(null);
            }
        } catch (Exception e) {
            HVLog.d(TAG, "autoFocus: " + e.getMessage());
        }
    }

    public void nextFlashMode() {
        String str;
        HVLog.d(TAG, "nextFlashMode() called");
        try {
            List<String> list = availableFlashmodes;
            if (list == null || (str = currentMode) == null) {
                return;
            }
            int indexOf = list.indexOf(str);
            String str2 = availableFlashmodes.get(indexOf == availableFlashmodes.size() + (-1) ? 0 : indexOf + 1);
            currentMode = str2;
            setFlashMode(str2);
        } catch (Exception e) {
            HVLog.e(TAG, "nextFlashMode: " + e.getMessage());
        }
    }

    public void setFlashMode(String str) {
        HVLog.d(TAG, "setFlashMode() called with: flashMode = [" + str + "]");
        try {
            currentMode = str;
            Camera.Parameters parameters = this.mCamera.getParameters();
            parameters.setFlashMode(str);
            this.mCamera.setParameters(parameters);
            updateCamHostFlashMode();
        } catch (Exception e) {
            HVLog.e(TAG, "setFlashMode: " + e.getMessage());
        }
    }

    public void clearEvent(Camera.AutoFocusCallback autoFocusCallback) {
        Camera.Parameters parameters;
        try {
            Camera camera = this.mCamera;
            if (camera != null) {
                try {
                    camera.cancelAutoFocus();
                    parameters = this.mCamera.getParameters();
                } catch (Throwable th) {
                    HVLog.e(TAG, "clearEvent: " + th.getMessage());
                    parameters = null;
                }
                if (parameters != null) {
                    if (parameters.getMaxNumMeteringAreas() > 0) {
                        parameters.setMeteringAreas(null);
                    }
                    if (parameters.getMaxNumFocusAreas() > 0) {
                        parameters.setFocusAreas(null);
                    }
                    String str = this.focusMode;
                    if (str != null) {
                        parameters.setFocusMode(str);
                    }
                    try {
                        this.mCamera.cancelAutoFocus();
                        this.mCamera.setParameters(parameters);
                    } catch (Exception e) {
                        HVLog.e(TAG, "clearEvent: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e2) {
            HVLog.e(TAG, "clearEvent: " + e2.getMessage());
        }
    }

    public void setEffect(String str) {
        HVLog.d(TAG, "setEffect() called with: effect = [" + str + "]");
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setColorEffect(str);
                this.mCamera.setParameters(parameters);
            } catch (Exception e) {
                HVLog.e(TAG, "setEffect: " + e.getMessage());
            }
        }
    }

    public Camera.Size getBestPictureSize() {
        if (this.isReleased) {
            return null;
        }
        return HVCamUtils.newChosensize;
    }

    public void setZoomBase() {
        HVLog.d(TAG, "setZoomBase() called");
        if (this.mCamera != null) {
            this.zoomBase = this.zoomMax;
        }
    }

    public void setZoom(float f) {
        HVLog.d(TAG, "setZoom() called with: delta = [" + f + "]");
        if (this.isZoomSupported) {
            zoomModify((int) f);
        }
    }

    public void stopZoom() {
        HVLog.d(TAG, "stopZoom() called");
    }

    public void setUseEnhancedCamera(boolean z) {
        HVLog.d(TAG, "setUseEnhancedCamera() called with: enhancedCamera = [" + z + "]");
        this.useEnhancedCamera = z;
    }

    public boolean getIsStillImageMode() {
        HVLog.d(TAG, "getIsStillImageMode() called");
        return this.isStillImageMode;
    }

    public void setIsStillImageMode(boolean z) {
        HVLog.d(TAG, "setIsStillImageMode() called with: isStillImageMode = [" + z + "]");
        this.isStillImageMode = z;
    }

    public void setTouchEvent(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
        HVLog.d(TAG, "setTouchEvent() called with: x = [" + f + "], y = [" + f2 + "], callback = [" + autoFocusCallback + "]");
        if (this.mCamera != null) {
            Rect calculateTapArea = calculateTapArea(f, f2);
            Camera.Parameters parameters = null;
            try {
                this.mCamera.cancelAutoFocus();
                parameters = this.mCamera.getParameters();
            } catch (Exception e) {
                HVLog.e(TAG, "setTouchEvent: " + e.getMessage());
            }
            if (parameters != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Camera.Area(calculateTapArea, 1000));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new Camera.Area(calculateTapArea, 400));
                if (parameters.getMaxNumFocusAreas() > 0) {
                    parameters.setFocusMode("auto");
                    parameters.setFocusAreas(arrayList);
                } else {
                    Log.e(this.LOG_TAG, "No focusing supported");
                }
                if (parameters.getMaxNumMeteringAreas() > 0) {
                    parameters.setMeteringAreas(arrayList2);
                } else {
                    HVLog.d(TAG, "setTouchEvent: No metering supported");
                }
                try {
                    this.mCamera.setParameters(parameters);
                    this.mCamera.autoFocus(autoFocusCallback);
                } catch (Exception e2) {
                    HVLog.e(TAG, "setTouchEvent: " + e2.getMessage());
                }
            }
        }
    }

    public void setFocusArea(float f, float f2, float f3, float f4, Camera.AutoFocusCallback autoFocusCallback) {
        HVLog.d(TAG, "setFocusArea() called with: top = [" + f + "], bottom = [" + f2 + "], left = [" + f3 + "], right = [" + f4 + "], callback = [" + autoFocusCallback + "]");
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.cancelAutoFocus();
            Rect focusRect = getFocusRect(f, f2, f3, f4);
            Camera.Parameters parameters = null;
            try {
                parameters = this.mCamera.getParameters();
            } catch (Exception e) {
                HVLog.e(TAG, "setFocusArea: " + e.getMessage());
            }
            if (parameters != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Camera.Area(focusRect, 1000));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new Camera.Area(focusRect, 400));
                if (parameters.getMaxNumFocusAreas() > 0) {
                    parameters.setFocusMode("auto");
                    parameters.setFocusAreas(arrayList);
                } else {
                    HVLog.d(TAG, "setFocusArea: No focusing supported");
                }
                if (parameters.getMaxNumMeteringAreas() > 0) {
                    parameters.setMeteringAreas(arrayList2);
                } else {
                    HVLog.d(TAG, "setFocusArea: No metering supported");
                }
                try {
                    this.mCamera.setParameters(parameters);
                    this.mCamera.autoFocus(autoFocusCallback);
                } catch (Exception e2) {
                    HVLog.e(TAG, "setFocusArea: " + e2.getMessage());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setExposure(double d) {
        int round;
        int round2;
        HVLog.d(TAG, "setExposure() called with: exposure = [" + d + "]");
        try {
            Camera.Parameters parameters = this.mCamera.getParameters();
            float exposureCompensationStep = parameters.getExposureCompensationStep();
            float exposureCompensation = parameters.getExposureCompensation();
            int maxExposureCompensation = parameters.getMaxExposureCompensation();
            int minExposureCompensation = parameters.getMinExposureCompensation();
            int ceil = (int) Math.ceil(((exposureCompensation * exposureCompensationStep) + ((float) d)) / exposureCompensationStep);
            float f = ceil;
            float f2 = f - exposureCompensation;
            if (f2 > 1.0f) {
                round = Math.round(exposureCompensation);
                round2 = Math.round(f2 / 2.0f);
            } else {
                if (exposureCompensation - f > 1.0f) {
                    round = Math.round(exposureCompensation);
                    round2 = Math.round(f2 / 2.0f);
                }
                if (ceil >= minExposureCompensation) {
                    maxExposureCompensation = minExposureCompensation;
                } else if (ceil <= maxExposureCompensation) {
                    maxExposureCompensation = ceil;
                }
                parameters.setExposureCompensation(maxExposureCompensation);
                this.mCamera.setParameters(parameters);
            }
            ceil = round + round2;
            if (ceil >= minExposureCompensation) {
            }
            parameters.setExposureCompensation(maxExposureCompensation);
            this.mCamera.setParameters(parameters);
        } catch (Exception e) {
            HVLog.e(this.LOG_TAG, "setExposure : " + e.getMessage());
        }
    }

    private Camera.PreviewCallback getPreviewCallBack() {
        HVLog.d(TAG, "getPreviewCallBack() called");
        this.data2counter = 0;
        for (int i = 0; i < 5; i++) {
            this.data2[i] = new byte[getPreviewSize().width * getPreviewSize().height];
        }
        return new Camera.PreviewCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.5
            /* JADX WARN: Removed duplicated region for block: B:30:0x0150 A[Catch: Exception -> 0x0170, TRY_LEAVE, TryCatch #0 {Exception -> 0x0170, blocks: (B:17:0x0036, B:19:0x005a, B:20:0x0065, B:28:0x011e, B:30:0x0150, B:32:0x010e, B:45:0x00e0, B:49:0x0061), top: B:16:0x0036 }] */
            @Override // android.hardware.Camera.PreviewCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onPreviewFrame(byte[] bArr, Camera camera) {
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                int i7;
                Bitmap decodeByteArray;
                ByteArrayOutputStream byteArrayOutputStream;
                Matrix matrix2;
                int i8;
                int i9;
                System.currentTimeMillis();
                if (!CameraEngine1.this.isCameraInitialised) {
                    if (HVMagicView.camHost != null) {
                        HVMagicView.camHost.onReady();
                    }
                    CameraEngine1.this.isCameraInitialised = true;
                }
                if (!CameraEngine.isSetPreviewCallback()) {
                    CameraEngine1.this.setPreviewCallbackWithBuffer(null);
                    return;
                }
                if (bArr == null) {
                    HVLog.d("NPD", "WTF");
                } else {
                    try {
                        int i10 = CameraEngine1.this.getPreviewSize().width;
                        int i11 = CameraEngine1.this.getPreviewSize().height;
                        int i12 = (((CameraEngine1.this.currentOrientation + 45) / 90) * 90) % 360;
                        if (CameraEngine.isFrontFacingCamera()) {
                            i2 = ((270 - i12) + 360) % 360;
                        } else {
                            i2 = (i12 + 90) % 360;
                        }
                        int i13 = i2;
                        if (CameraEngine1.this.returnNextFrame) {
                            try {
                                if (HVMagicView.camHost != null) {
                                    HVMagicView.camHost.setScreenFlashOff();
                                }
                                CameraEngine1.this.isScreenFlashOn = false;
                                CameraEngine1.this.returnNextFrame = false;
                                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                new YuvImage(bArr, 17, i10, i11, null).compressToJpeg(new Rect(0, 0, i10, i11), 90, byteArrayOutputStream2);
                                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                                decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                matrix2 = new Matrix();
                                matrix2.postRotate(i13);
                                i3 = i13;
                                i4 = i12;
                                i5 = 90;
                                i6 = i11;
                                i7 = i10;
                            } catch (Exception e) {
                                e = e;
                                i3 = i13;
                                i4 = i12;
                                i5 = 90;
                                i6 = i11;
                                i7 = i10;
                            }
                            try {
                                Bitmap.createBitmap(decodeByteArray, 0, 0, i10, i11, matrix2, false).compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                                if (HVMagicView.camHost != null) {
                                    HVMagicView.camHost.onPictureReady(byteArrayOutputStream.toByteArray());
                                }
                            } catch (Exception e2) {
                                e = e2;
                                HVLog.e(CameraEngine1.this.LOG_TAG, "getPreviewCallBack() returnNextFrame : " + e.getMessage());
                                if (i3 != i5) {
                                    i9 = i6;
                                    i8 = i7;
                                    CameraEngine1 cameraEngine1 = CameraEngine1.this;
                                    cameraEngine1.data2counter = (cameraEngine1.data2counter + 1) % 5;
                                    CameraEngine.rotateNV21a(bArr, CameraEngine1.this.getPreviewSize().width, CameraEngine1.this.getPreviewSize().height, i3, CameraEngine1.this.data2[CameraEngine1.this.data2counter]);
                                    if (HVMagicView.camHost != null) {
                                    }
                                    CameraEngine1.this.addPreviewCallback(bArr);
                                }
                                i8 = CameraEngine1.this.getPreviewSize().height;
                                i9 = CameraEngine1.this.getPreviewSize().width;
                                CameraEngine1 cameraEngine12 = CameraEngine1.this;
                                cameraEngine12.data2counter = (cameraEngine12.data2counter + 1) % 5;
                                CameraEngine.rotateNV21a(bArr, CameraEngine1.this.getPreviewSize().width, CameraEngine1.this.getPreviewSize().height, i3, CameraEngine1.this.data2[CameraEngine1.this.data2counter]);
                                if (HVMagicView.camHost != null) {
                                }
                                CameraEngine1.this.addPreviewCallback(bArr);
                            }
                        } else {
                            i3 = i13;
                            i4 = i12;
                            i5 = 90;
                            i6 = i11;
                            i7 = i10;
                        }
                        if (i3 != i5 && i3 != 270) {
                            i9 = i6;
                            i8 = i7;
                            CameraEngine1 cameraEngine122 = CameraEngine1.this;
                            cameraEngine122.data2counter = (cameraEngine122.data2counter + 1) % 5;
                            CameraEngine.rotateNV21a(bArr, CameraEngine1.this.getPreviewSize().width, CameraEngine1.this.getPreviewSize().height, i3, CameraEngine1.this.data2[CameraEngine1.this.data2counter]);
                            if (HVMagicView.camHost != null) {
                                HVMagicView.camHost.onNewPreviewFrame(CameraEngine1.this.data2[CameraEngine1.this.data2counter], i8, i9, i4, i3, BitmapUtil.createByteArrayForFrame(bArr, i9, i8, i3));
                            }
                        }
                        i8 = CameraEngine1.this.getPreviewSize().height;
                        i9 = CameraEngine1.this.getPreviewSize().width;
                        CameraEngine1 cameraEngine1222 = CameraEngine1.this;
                        cameraEngine1222.data2counter = (cameraEngine1222.data2counter + 1) % 5;
                        CameraEngine.rotateNV21a(bArr, CameraEngine1.this.getPreviewSize().width, CameraEngine1.this.getPreviewSize().height, i3, CameraEngine1.this.data2[CameraEngine1.this.data2counter]);
                        if (HVMagicView.camHost != null) {
                        }
                    } catch (Exception e3) {
                        HVLog.e(CameraEngine1.this.LOG_TAG, "getPreviewCallBack : " + e3.getMessage());
                    }
                }
                CameraEngine1.this.addPreviewCallback(bArr);
            }
        };
    }

    private void setDefaultParameters() {
        HVLog.d(TAG, "setDefaultParameters() called");
        Camera.Parameters parameters = this.mCamera.getParameters();
        if (HVMagicView.camHost != null) {
            Camera.Size bestPreviewSize = HVCamUtils.getBestPreviewSize(this.mCamera, HVMagicView.getmRatioWidth(), HVMagicView.getmRatioHeight(), HVMagicView.camHost.getPreviewMegapixels());
            parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
            Camera.Size bestPictureSize = HVCamUtils.getBestPictureSize(this.mCamera, HVMagicView.getmRatioWidth(), HVMagicView.getmRatioHeight(), HVMagicView.camHost.getPictureMegapixels(), HVMagicView.camHost.isShouldCaptureHighResolutionImage());
            parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);
        }
        HVCamUtils.lastRotation = -1;
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes.contains("continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
            this.focusMode = "continuous-picture";
        } else if (supportedFocusModes.contains("auto")) {
            parameters.setFocusMode("auto");
            this.focusMode = "auto";
        }
        int[] iArr = new int[2];
        parameters.getPreviewFpsRange(iArr);
        if (iArr[0] == iArr[1]) {
            for (int[] iArr2 : parameters.getSupportedPreviewFpsRange()) {
                if (iArr2[0] != iArr2[1]) {
                    parameters.setPreviewFpsRange(iArr2[0], iArr2[1]);
                    break;
                }
            }
        }
        try {
            if (this.useEnhancedCamera) {
                parameters.setAntibanding("off");
            }
        } catch (Exception e) {
            HVLog.e(this.LOG_TAG, "setDefaultParameters() useEnhancedCamera :" + e.getMessage());
        }
        if (parameters.isAutoExposureLockSupported()) {
            parameters.setAutoExposureLock(false);
        }
        try {
            this.mCamera.cancelAutoFocus();
            this.mCamera.setParameters(parameters);
        } catch (Exception e2) {
            HVLog.e(this.LOG_TAG, "setDefaultParameters() :" + e2.getMessage());
        }
    }

    private void updateCamHostFlashMode() {
        HVLog.d(TAG, "updateCamHostFlashMode() called");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1.6
            /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
            
                if (r1 == 1) goto L35;
             */
            /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
            
                if (r1 == 2) goto L34;
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x005a, code lost:
            
                if (r1 == 3) goto L33;
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x005d, code lost:
            
                co.hyperverge.hvcamera.HVMagicView.camHost.onFlashOn();
             */
            /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
            
                co.hyperverge.hvcamera.HVMagicView.camHost.onFlashTorchOn();
             */
            /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x0069, code lost:
            
                co.hyperverge.hvcamera.HVMagicView.camHost.onFlashAuto();
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
            
                return;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                try {
                    if (HVMagicView.camHost != null) {
                        if (CameraEngine1.currentMode == null) {
                            HVMagicView.camHost.onFlashNull();
                            return;
                        }
                        String str = CameraEngine1.currentMode;
                        char c = 65535;
                        int hashCode = str.hashCode();
                        if (hashCode != 3551) {
                            if (hashCode != 109935) {
                                if (hashCode != 3005871) {
                                    if (hashCode == 110547964 && str.equals("torch")) {
                                        c = 2;
                                    }
                                } else if (str.equals("auto")) {
                                    c = 1;
                                }
                            } else if (str.equals("off")) {
                                c = 0;
                            }
                        } else if (str.equals("on")) {
                            c = 3;
                        }
                        HVMagicView.camHost.onFlashOff();
                    }
                } catch (Exception e) {
                    HVLog.e(CameraEngine1.this.LOG_TAG, "updateCamHostFlashMode() :" + e.getMessage());
                }
            }
        });
    }

    private Rect calculateTapArea(float f, float f2) {
        HVLog.d(TAG, "calculateTapArea() called with: x = [" + f + "], y = [" + f2 + "]");
        if (isFrontFacingCamera()) {
            f = 1.0f - f;
        }
        int i = ((int) ((f - 0.5f) * 2000.0f)) - 100;
        int i2 = i + 200;
        int i3 = ((int) ((f2 - 0.5f) * 2000.0f)) - 100;
        int i4 = i3 + 200;
        int clamp = clamp(i);
        int clamp2 = clamp(i2);
        int clamp3 = clamp(i3);
        int clamp4 = clamp(i4);
        matrix.reset();
        matrix.postRotate(90.0f);
        Matrix matrix2 = matrix;
        matrix2.invert(matrix2);
        RectF rectF = new RectF(clamp, clamp3, clamp2, clamp4);
        matrix.mapRect(rectF);
        Rect rect = new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        HVLog.d(TAG, "calculateTapArea() returned: " + rect);
        return rect;
    }

    private void zoomModify(int i) {
        Camera camera;
        HVLog.d(TAG, "zoomModify() called with: zoomValue = [" + i + "]");
        if (!this.isZoomSupported || (camera = this.mCamera) == null) {
            return;
        }
        float f = i;
        try {
            this.zoomCurrent = f;
            if (f <= this.zoomMax) {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setZoom((int) this.zoomCurrent);
                this.mCamera.setParameters(parameters);
            }
        } catch (Exception e) {
            HVLog.e(this.LOG_TAG, "zoomModify() :" + e.getMessage());
        }
    }

    private Rect getFocusRect(float f, float f2, float f3, float f4) {
        HVLog.d(TAG, "getFocusRect() called with: t = [" + f + "], b = [" + f2 + "], l = [" + f3 + "], r = [" + f4 + "]");
        if (isFrontFacingCamera()) {
            float f5 = 1.0f - f4;
            float f6 = 1.0f - f3;
            float max = Math.max(f5, f6);
            float min = Math.min(f5, f6);
            f4 = max;
            f3 = min;
        }
        int clamp = clamp((int) ((f3 - 0.5f) * 2000.0f));
        int clamp2 = clamp((int) ((f4 - 0.5f) * 2000.0f));
        int clamp3 = clamp((int) ((f - 0.5f) * 2000.0f));
        int clamp4 = clamp((int) ((f2 - 0.5f) * 2000.0f));
        matrix.reset();
        matrix.postRotate(90.0f);
        Matrix matrix2 = matrix;
        matrix2.invert(matrix2);
        RectF rectF = new RectF(clamp, clamp3, clamp2, clamp4);
        matrix.mapRect(rectF);
        Rect rect = new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        HVLog.d(TAG, "getFocusRect() returned: " + rect);
        return rect;
    }

    int getFrontCameraId() {
        HVLog.d(TAG, "getFrontCameraId() called");
        try {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == 1) {
                    return i;
                }
            }
        } catch (Exception e) {
            HVLog.e(TAG, "setExposure: " + e.getMessage());
        }
        return 0;
    }
}
