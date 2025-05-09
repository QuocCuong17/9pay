package co.hyperverge.hvcamera.magicfilter.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.WindowManager;
import co.hyperverge.hvcamera.HVCamHost;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.utils.AutoFocusHelper;
import co.hyperverge.hvcamera.magicfilter.utils.BitmapUtil;
import co.hyperverge.hvcamera.magicfilter.utils.Camera2Utils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class CameraEngine2 {
    private static final String HVLog_TAG = "co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2";
    private static final SparseIntArray ORIENTATIONS;
    public static CameraEngine2 cameraEngine2;
    private static int orientation;
    private static final List<Integer> supportedModes;
    private static final List<Integer> supportedModesVideo;
    private List<Integer> availableFlashmodes;
    private Context context;
    private int flashMode;
    private boolean isFocused;
    private boolean isStillImageMode;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    private CameraDevice mCameraDevice;
    private String mCameraId;
    private CameraCaptureSession mCaptureSession;
    private Size mCaptureSize;
    private ImageReader mImageReader;
    private ImageReader mImageReaderNew;
    private CaptureRequest mPreviewRequest;
    private CaptureRequest.Builder mPreviewRequestBuilder;
    private Size mPreviewSize;
    private int mSensorOrientation;
    private int mState;
    private Surface mSurface;
    private CameraManager manager;
    private PictureListener pictureListener;
    private int screenHeight;
    private int screenWidth;
    private boolean useFrontCam;
    private float zoomCurrent;
    private float zoomMax;
    private Rect zoomRect;
    private final int STATE_PREVIEW = 0;
    private final int STATE_WAITING_LOCK = 1;
    private final int STATE_WAITING_PRECAPTURE = 2;
    private final int STATE_WAITING_NON_PRECAPTURE = 3;
    private final int STATE_PICTURE_TAKEN = 4;
    private boolean isCameraOpened = false;
    private float currentExposure = -1.0f;
    private Matrix matrix = new Matrix();
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);
    private int ANTIBANDING_MODE = -1;
    private int OIS_MODE = -1;
    private boolean isReleased = true;
    private boolean captureTriggered = false;
    private final CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.1
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            CameraEngine2.this.mCameraOpenCloseLock.release();
            CameraEngine2.this.mCameraDevice = cameraDevice;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            CameraEngine2.this.mCameraOpenCloseLock.release();
            cameraDevice.close();
            CameraEngine2.this.mCameraDevice = null;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int i) {
            CameraEngine2.this.mCameraOpenCloseLock.release();
            cameraDevice.close();
            CameraEngine2.this.mCameraDevice = null;
        }
    };
    private boolean isInitialized = false;
    private boolean isLegacy = false;
    private float[] focusRegion = {-1.0f, -1.0f};
    private boolean isZoomSupported = false;
    private float zoomBase = 0.0f;
    private boolean useEnhancedCamera = false;
    private int counter = 0;
    private int data2counter = 0;
    private byte[][] data2 = new byte[5];
    private byte[][] byteBuffer = new byte[5];
    public boolean willNeedNextFrame = false;
    private final String TAG = "CameraEngine2";
    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.2
        private void process(CaptureResult captureResult) {
            int i = CameraEngine2.this.mState;
            if (i == 0) {
                try {
                    CameraEngine2.this.currentExposure = ((Integer) captureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION)).intValue();
                } catch (Exception e) {
                    HVLog.d(CameraEngine2.HVLog_TAG, e.getMessage());
                }
                try {
                    int intValue = ((Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE)).intValue();
                    if (intValue == 0) {
                        CameraEngine2.this.isFocused = false;
                        return;
                    }
                    if (intValue != 1) {
                        if (intValue != 2) {
                            if (intValue != 3) {
                                if (intValue != 4) {
                                    CameraEngine2.this.isFocused = false;
                                    return;
                                }
                            }
                        }
                        if (!CameraEngine2.this.isFocused && HVMagicView.camHost != null) {
                            HVMagicView.camHost.showCrossHair(CameraEngine2.this.focusRegion[0], CameraEngine2.this.focusRegion[1], true);
                        }
                        CameraEngine2.this.isFocused = true;
                        return;
                    }
                    if (HVMagicView.camHost != null) {
                        HVMagicView.camHost.showCrossHair(CameraEngine2.this.focusRegion[0], CameraEngine2.this.focusRegion[1], false);
                    }
                    CameraEngine2.this.isFocused = false;
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            if (i != 1) {
                if (i == 2) {
                    Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                    if (num == null || num.intValue() == 5 || num.intValue() == 4 || num.intValue() == 2) {
                        CameraEngine2.this.mState = 3;
                        return;
                    }
                    return;
                }
                if (i != 3) {
                    return;
                }
                Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num2 == null || num2.intValue() != 5) {
                    CameraEngine2.this.mState = 4;
                    CameraEngine2.this.captureStillPicture();
                    return;
                }
                return;
            }
            Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
            if (num3 == null) {
                CameraEngine2.this.mState = 4;
                CameraEngine2.this.captureStillPicture();
                return;
            }
            if (4 == num3.intValue() || 5 == num3.intValue()) {
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.showCrossHair(CameraEngine2.this.focusRegion[0], CameraEngine2.this.focusRegion[1], 4 == num3.intValue());
                }
                CameraEngine2.this.isFocused = false;
                Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                if (num4 == null || num4.intValue() == 2) {
                    CameraEngine2.this.mState = 4;
                    CameraEngine2.this.captureStillPicture();
                    return;
                } else {
                    CameraEngine2.this.runPrecaptureSequence();
                    return;
                }
            }
            if (num3.intValue() != 3) {
                CameraEngine2.this.mState = 4;
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.showCrossHair(CameraEngine2.this.focusRegion[0], CameraEngine2.this.focusRegion[1], true);
                }
                CameraEngine2.this.captureStillPicture();
                return;
            }
            if (HVMagicView.camHost != null) {
                HVMagicView.camHost.showCrossHair(CameraEngine2.this.focusRegion[0], CameraEngine2.this.focusRegion[1], false);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            process(captureResult);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            try {
                process(totalCaptureResult);
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.onReady();
                }
            } catch (Exception e) {
                HVLog.d(CameraEngine2.HVLog_TAG, e.getMessage());
            }
        }
    };
    private CameraCaptureSession.CaptureCallback mCaptureCallback2 = new CameraCaptureSession.CaptureCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.3
        private void process(CaptureResult captureResult) {
            CameraEngine2.this.mState = 1;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            process(totalCaptureResult);
        }
    };
    public boolean returnNextFrame = false;

    /* loaded from: classes2.dex */
    public interface PictureListener {
        void onNewPreviewFrame(byte[] bArr, int i, int i2, int i3, int i4, byte[] bArr2);

        void onPictureTaken(byte[] bArr);
    }

    private static void cancelAutoFocus() {
    }

    public void stopZoom() {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        ORIENTATIONS = sparseIntArray;
        sparseIntArray.append(0, 90);
        sparseIntArray.append(1, 0);
        sparseIntArray.append(2, 270);
        sparseIntArray.append(3, 180);
        ArrayList arrayList = new ArrayList();
        supportedModes = arrayList;
        arrayList.add(3);
        arrayList.add(1);
        ArrayList arrayList2 = new ArrayList();
        supportedModesVideo = arrayList2;
        arrayList2.add(3);
        arrayList2.add(1);
    }

    public void setPictureListener(PictureListener pictureListener) {
        this.pictureListener = pictureListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setScreenDisplaySize(Point point) {
        this.screenWidth = point.x;
        this.screenHeight = point.y;
    }

    public void setUseEnhancedCamera(boolean z) {
        this.useEnhancedCamera = z;
    }

    public void init(Context context, boolean z) {
        setContext(context);
        this.isInitialized = true;
        this.isReleased = false;
        this.manager = (CameraManager) this.context.getSystemService("camera");
        this.useFrontCam = z;
        this.flashMode = 1;
    }

    public static CameraEngine2 getInstance() {
        if (cameraEngine2 == null) {
            cameraEngine2 = new CameraEngine2();
        }
        return cameraEngine2;
    }

    public void destroyEngine() {
        cameraEngine2 = null;
    }

    public void openCamera() {
        if (this.useFrontCam) {
            this.mCameraId = getCameraId(0);
        }
        if (this.mCameraId == null || !this.useFrontCam) {
            this.mCameraId = getCameraId(1);
        }
        startBackgroundThread();
        try {
            try {
                if (!this.mCameraOpenCloseLock.tryAcquire(2500L, TimeUnit.MILLISECONDS)) {
                    throw new RuntimeException("Time out waiting to lock camera opening.");
                }
                this.manager.openCamera(this.mCameraId, this.mStateCallback, this.mBackgroundHandler);
                CameraCharacteristics cameraCharacteristics = this.manager.getCameraCharacteristics(this.mCameraId);
                StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                HVMagicView.updateRatios();
                updateFlash((int[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES));
                float floatValue = ((Float) cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue();
                this.zoomMax = floatValue;
                if (floatValue > 1.0f) {
                    this.isZoomSupported = true;
                }
                float f = CameraEngine.zoomFactor;
                this.zoomCurrent = f;
                this.zoomRect = getZoomRect(f);
                this.zoomBase = this.zoomMax;
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.zoomMaxLevel((int) this.zoomMax);
                }
                updateCamHostFlashMode();
                float[] fArr = this.focusRegion;
                fArr[0] = -1.0f;
                fArr[1] = -1.0f;
                int[] iArr = (int[]) cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
                if (iArr != null) {
                    this.OIS_MODE = -1;
                    for (int i : iArr) {
                        if (i == 1) {
                            this.OIS_MODE = 1;
                            break;
                        }
                    }
                }
                try {
                    CameraEngine.hardwareInfo.put("focalCalibration", cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION));
                    CameraEngine.hardwareInfo.put("cameraIds", CameraEngine2$$ExternalSyntheticBackport0.m(",", this.manager.getCameraIdList()));
                } catch (Exception e) {
                    HVLog.d(HVLog_TAG, e.getMessage());
                }
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                    this.isLegacy = true;
                }
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.getAspectRatio();
                }
                if (HVMagicView.camHost != null) {
                    this.mPreviewSize = Camera2Utils.getBestPreviewSize(streamConfigurationMap, HVMagicView.getmRatioWidth(), HVMagicView.getmRatioHeight(), HVMagicView.camHost.getPreviewMegapixels());
                    this.mCaptureSize = Camera2Utils.getBestCaptureSize(streamConfigurationMap, HVMagicView.getmRatioWidth(), HVMagicView.getmRatioHeight(), HVMagicView.camHost.getPictureMegapixels(), HVMagicView.camHost.isShouldCaptureHighResolutionImage());
                    HVMagicView.camHost.onPictureSizeSet(this.mCaptureSize.getWidth(), this.mCaptureSize.getHeight());
                }
                this.mImageReader = ImageReader.newInstance(this.mCaptureSize.getWidth(), this.mCaptureSize.getHeight(), 256, 2);
                if (CameraEngine.isSetPreviewCallback()) {
                    this.mImageReaderNew = ImageReader.newInstance(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight(), 35, 5);
                    for (int i2 = 0; i2 < 5; i2++) {
                        this.data2[i2] = new byte[getPreviewSize().getWidth() * getPreviewSize().getHeight()];
                    }
                    this.mImageReaderNew.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.4
                        /* JADX WARN: Removed duplicated region for block: B:20:0x00cb A[Catch: IllegalArgumentException -> 0x00f3, Exception -> 0x0100, TRY_LEAVE, TryCatch #1 {IllegalArgumentException -> 0x00f3, blocks: (B:18:0x00a5, B:20:0x00cb), top: B:17:0x00a5, outer: #0 }] */
                        /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
                        @Override // android.media.ImageReader.OnImageAvailableListener
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void onImageAvailable(ImageReader imageReader) {
                            int i3;
                            int height;
                            int width;
                            try {
                                Image acquireLatestImage = imageReader.acquireLatestImage();
                                int i4 = ((((CameraEngine2.orientation + 45) / 90) * 90) % 360) + (CameraEngine2.this.mSensorOrientation - 270);
                                if (CameraEngine.isFrontFacingCamera()) {
                                    if (Build.MODEL.toLowerCase().contains("rk3399-all")) {
                                        i3 = ((90 - i4) + 360) % 360;
                                    } else {
                                        i3 = ((270 - i4) + 360) % 360;
                                    }
                                } else if (Build.MODEL.toLowerCase().contains("comio x1")) {
                                    i3 = (i4 + 90) % 360;
                                } else {
                                    i3 = ((270 - i4) + 360) % 360;
                                }
                                int i5 = i3;
                                byte[] YUV_420_888toNV21 = CameraEngine2.this.YUV_420_888toNV21(acquireLatestImage);
                                acquireLatestImage.close();
                                System.currentTimeMillis();
                                CameraEngine2 cameraEngine22 = CameraEngine2.this;
                                cameraEngine22.counter = (cameraEngine22.counter + 1) % 3;
                                if (YUV_420_888toNV21 == null) {
                                    return;
                                }
                                int width2 = CameraEngine2.this.getPreviewSize().getWidth();
                                int height2 = CameraEngine2.this.getPreviewSize().getHeight();
                                try {
                                    if (i5 != 90 && i5 != 270) {
                                        height = width2;
                                        width = height2;
                                        CameraEngine2 cameraEngine23 = CameraEngine2.this;
                                        cameraEngine23.data2counter = (cameraEngine23.data2counter + 1) % 5;
                                        CameraEngine.rotateNV21a(YUV_420_888toNV21, width, height, i5, CameraEngine2.this.data2[CameraEngine2.this.data2counter]);
                                        if (CameraEngine2.this.pictureListener == null) {
                                            CameraEngine2.this.pictureListener.onNewPreviewFrame(CameraEngine2.this.data2[CameraEngine2.this.data2counter], height, width, ((i4 + 360) + (CameraEngine2.this.mSensorOrientation - 270)) % 360, i5, BitmapUtil.createByteArrayForFrame(YUV_420_888toNV21, width, height, i5));
                                            return;
                                        }
                                        return;
                                    }
                                    CameraEngine2 cameraEngine232 = CameraEngine2.this;
                                    cameraEngine232.data2counter = (cameraEngine232.data2counter + 1) % 5;
                                    CameraEngine.rotateNV21a(YUV_420_888toNV21, width, height, i5, CameraEngine2.this.data2[CameraEngine2.this.data2counter]);
                                    if (CameraEngine2.this.pictureListener == null) {
                                    }
                                } catch (IllegalArgumentException e2) {
                                    HVLog.d(CameraEngine2.HVLog_TAG, e2.getMessage());
                                    return;
                                }
                                height = CameraEngine2.this.getPreviewSize().getHeight();
                                width = CameraEngine2.this.getPreviewSize().getWidth();
                            } catch (Exception e3) {
                                HVLog.d(CameraEngine2.HVLog_TAG, e3.getMessage());
                            }
                        }
                    }, this.mBackgroundHandler);
                }
                this.mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.5
                    @Override // android.media.ImageReader.OnImageAvailableListener
                    public void onImageAvailable(ImageReader imageReader) {
                        try {
                            Image acquireLatestImage = imageReader.acquireLatestImage();
                            ByteBuffer buffer = acquireLatestImage.getPlanes()[0].getBuffer();
                            byte[] bArr = new byte[buffer.remaining()];
                            buffer.get(bArr);
                            if (CameraEngine2.this.pictureListener != null) {
                                CameraEngine2.this.pictureListener.onPictureTaken(bArr);
                            }
                            acquireLatestImage.close();
                        } catch (Exception e2) {
                            HVLog.d(CameraEngine2.HVLog_TAG, e2.getMessage());
                        }
                    }
                }, this.mBackgroundHandler);
                this.mSensorOrientation = ((Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
                getJpegOrientation(cameraCharacteristics, orientation);
                this.isCameraOpened = true;
            } catch (Exception e2) {
                HVLog.d(HVLog_TAG, e2.getMessage());
            }
        } catch (CameraAccessException e3) {
            HVLog.d(HVLog_TAG, e3.getMessage());
        } catch (InterruptedException e4) {
            throw new RuntimeException("Interrupted while trying to lock camera opening.", e4);
        } catch (RuntimeException unused) {
            openCamera();
        }
    }

    public byte[] YUV_420_888toNV21(Image image) {
        int i;
        Rect cropRect = image.getCropRect();
        int format = image.getFormat();
        int width = cropRect.width();
        int height = cropRect.height();
        Image.Plane[] planes = image.getPlanes();
        int i2 = width * height;
        byte[] bArr = new byte[(ImageFormat.getBitsPerPixel(format) * i2) / 8];
        int i3 = 0;
        byte[] bArr2 = new byte[planes[0].getRowStride()];
        int i4 = 1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 1;
        while (i5 < planes.length) {
            if (i5 != 0) {
                if (i5 == i4) {
                    i6 = i2 + 1;
                } else if (i5 == 2) {
                    i6 = i2;
                }
                i7 = 2;
            } else {
                i6 = i3;
                i7 = i4;
            }
            ByteBuffer buffer = planes[i5].getBuffer();
            int rowStride = planes[i5].getRowStride();
            int pixelStride = planes[i5].getPixelStride();
            int i8 = i5 == 0 ? i3 : i4;
            int i9 = width >> i8;
            int i10 = height >> i8;
            int i11 = width;
            int i12 = height;
            buffer.position(((cropRect.top >> i8) * rowStride) + ((cropRect.left >> i8) * pixelStride));
            for (int i13 = 0; i13 < i10; i13++) {
                if (pixelStride == 1 && i7 == 1) {
                    buffer.get(bArr, i6, i9);
                    i6 += i9;
                    i = i9;
                } else {
                    i = ((i9 - 1) * pixelStride) + 1;
                    buffer.get(bArr2, 0, i);
                    for (int i14 = 0; i14 < i9; i14++) {
                        bArr[i6] = bArr2[i14 * pixelStride];
                        i6 += i7;
                    }
                }
                if (i13 < i10 - 1) {
                    buffer.position((buffer.position() + rowStride) - i);
                }
            }
            i5++;
            width = i11;
            height = i12;
            i3 = 0;
            i4 = 1;
        }
        return bArr;
    }

    private void updateFlash(int[] iArr) {
        if (iArr != null) {
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                Integer valueOf = Integer.valueOf(i);
                if (this.isStillImageMode) {
                    if (supportedModes.contains(valueOf)) {
                        arrayList.add(valueOf);
                    }
                } else if (supportedModesVideo.contains(valueOf)) {
                    arrayList.add(valueOf);
                }
            }
            this.availableFlashmodes = arrayList;
            if (arrayList.size() > 0) {
                this.flashMode = this.availableFlashmodes.get(0).intValue();
                return;
            } else {
                this.flashMode = -1;
                return;
            }
        }
        this.availableFlashmodes = null;
        this.flashMode = -1;
    }

    public void createCameraPreviewSession(final SurfaceTexture surfaceTexture) {
        if (this.mCameraDevice == null || !this.isCameraOpened) {
            return;
        }
        this.mBackgroundHandler.post(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (surfaceTexture != null) {
                        CameraEngine2.this.mSurface = new Surface(surfaceTexture);
                        surfaceTexture.setDefaultBufferSize(CameraEngine2.this.mPreviewSize.getWidth(), CameraEngine2.this.mPreviewSize.getHeight());
                        CameraEngine2 cameraEngine22 = CameraEngine2.this;
                        cameraEngine22.mPreviewRequestBuilder = cameraEngine22.mCameraDevice.createCaptureRequest(1);
                        CameraEngine2.this.mPreviewRequestBuilder.addTarget(CameraEngine2.this.mSurface);
                        if (CameraEngine.isSetPreviewCallback()) {
                            CameraEngine2.this.mPreviewRequestBuilder.addTarget(CameraEngine2.this.mImageReaderNew.getSurface());
                        }
                        CameraEngine2.this.mState = 0;
                        CameraEngine2.this.mCameraDevice.createCaptureSession(CameraEngine.isSetPreviewCallback() ? Arrays.asList(CameraEngine2.this.mSurface, CameraEngine2.this.mImageReader.getSurface(), CameraEngine2.this.mImageReaderNew.getSurface()) : Arrays.asList(CameraEngine2.this.mSurface, CameraEngine2.this.mImageReader.getSurface()), new CameraCaptureSession.StateCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.6.1
                            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                            public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                                if (CameraEngine2.this.mCameraDevice == null || !CameraEngine2.this.isCameraOpened) {
                                    return;
                                }
                                try {
                                    CameraEngine2.this.mCaptureSession = cameraCaptureSession;
                                    CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
                                    CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_MODE, 1);
                                    if (CameraEngine.isFacePriority()) {
                                        CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_SCENE_MODE, 1);
                                    }
                                    if (CameraEngine2.this.OIS_MODE == 1) {
                                        CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 1);
                                        CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0);
                                    } else {
                                        CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, 0);
                                        CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 1);
                                    }
                                    if (CameraEngine.shouldUseDefaultZoom) {
                                        CameraEngine2.this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, CameraEngine2.this.getZoomRect(CameraEngine2.this.zoomCurrent));
                                    }
                                    CameraEngine2.this.setCurrentFlash(CameraEngine2.this.mPreviewRequestBuilder);
                                    CameraEngine2.this.mPreviewRequest = CameraEngine2.this.mPreviewRequestBuilder.build();
                                    try {
                                        CameraEngine2.this.mCaptureSession.setRepeatingRequest(CameraEngine2.this.mPreviewRequest, CameraEngine2.this.mCaptureCallback, CameraEngine2.this.mBackgroundHandler);
                                    } catch (Exception e) {
                                        HVLog.d(CameraEngine2.HVLog_TAG, e.getMessage());
                                    }
                                } catch (Exception e2) {
                                    HVLog.d(CameraEngine2.HVLog_TAG, e2.getMessage());
                                }
                            }

                            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
                            public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                                HVLog.i("camera", "config failed");
                            }
                        }, null);
                    }
                } catch (CameraAccessException e) {
                    HVLog.d(CameraEngine2.HVLog_TAG, e.getMessage());
                } catch (IllegalStateException e2) {
                    HVLog.d(CameraEngine2.HVLog_TAG, e2.getMessage());
                } catch (Exception e3) {
                    HVLog.d(CameraEngine2.HVLog_TAG, e3.getMessage());
                }
            }
        });
    }

    private int getJpegOrientation(CameraCharacteristics cameraCharacteristics, int i) {
        if (i == -1) {
            return 0;
        }
        int intValue = ((Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        int i2 = ((i + 45) / 90) * 90;
        if (((Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING)).intValue() == 0) {
            i2 = -i2;
        }
        return ((intValue + i2) + 360) % 360;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void captureStillPicture() {
        CameraDevice cameraDevice;
        try {
            if (this.context != null && (cameraDevice = this.mCameraDevice) != null && !this.captureTriggered) {
                this.captureTriggered = true;
                CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(2);
                createCaptureRequest.addTarget(this.mImageReader.getSurface());
                createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 4);
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                if (this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AE_REGIONS) != null) {
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_REGIONS, (MeteringRectangle[]) this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AE_REGIONS));
                }
                if (this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_REGIONS) != null) {
                    createCaptureRequest.set(CaptureRequest.CONTROL_AF_REGIONS, (MeteringRectangle[]) this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_REGIONS));
                }
                if (CameraEngine.isFacePriority()) {
                    createCaptureRequest.set(CaptureRequest.CONTROL_SCENE_MODE, 1);
                }
                try {
                    if (this.useEnhancedCamera) {
                        createCaptureRequest.set(CaptureRequest.CONTROL_AE_ANTIBANDING_MODE, 0);
                    }
                } catch (Exception e) {
                    HVLog.e(HVLog_TAG, e.getMessage());
                }
                setCurrentFlash(createCaptureRequest);
                CameraCharacteristics cameraCharacteristics = this.manager.getCameraCharacteristics(this.mCameraId);
                if (CameraEngine.shouldUseDefaultZoom) {
                    createCaptureRequest.set(CaptureRequest.SCALER_CROP_REGION, getZoomRect(1.0f));
                } else if (this.zoomRect != null) {
                    createCaptureRequest.set(CaptureRequest.SCALER_CROP_REGION, this.zoomRect);
                }
                createCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(getJpegOrientation(cameraCharacteristics, orientation)));
                CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.7
                    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
                    public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
                        super.onCaptureStarted(cameraCaptureSession, captureRequest, j, j2);
                        if (HVMagicView.camHost != null) {
                            HVMagicView.camHost.flashScreen();
                        }
                    }

                    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
                    public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                        CameraEngine2.this.unlockFocus();
                    }
                };
                CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.stopRepeating();
                    this.mCaptureSession.capture(createCaptureRequest.build(), captureCallback, null);
                }
            }
        } catch (Exception e2) {
            HVLog.d(HVLog_TAG, e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlockFocus() {
        try {
            if (this.mCaptureSession != null) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
                this.mState = 0;
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequest, this.mCaptureCallback, this.mBackgroundHandler);
            }
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runPrecaptureSequence() {
        try {
            if (this.mCaptureSession != null) {
                setCurrentFlash(this.mPreviewRequestBuilder);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                this.mState = 2;
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
            }
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentFlash(CaptureRequest.Builder builder) {
        List<Integer> list = this.availableFlashmodes;
        if (list == null || !list.contains(Integer.valueOf(this.flashMode))) {
            return;
        }
        try {
            int i = this.flashMode;
            if (i == 1) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                builder.set(CaptureRequest.FLASH_MODE, 0);
            } else if (i == 2) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, 2);
            } else if (i != 3) {
                if (i == 4) {
                    builder.set(CaptureRequest.FLASH_MODE, 4);
                } else {
                    builder.set(CaptureRequest.CONTROL_AE_MODE, 2);
                }
            } else if (this.isStillImageMode) {
                builder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                builder.set(CaptureRequest.FLASH_MODE, 2);
            } else {
                builder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                builder.set(CaptureRequest.FLASH_MODE, 2);
            }
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }

    public void nextFlashMode() {
        List<Integer> list = this.availableFlashmodes;
        if (list == null || !list.contains(Integer.valueOf(this.flashMode))) {
            return;
        }
        int indexOf = this.availableFlashmodes.indexOf(Integer.valueOf(this.flashMode));
        int intValue = this.availableFlashmodes.get(indexOf == this.availableFlashmodes.size() + (-1) ? 0 : indexOf + 1).intValue();
        this.flashMode = intValue;
        setFlashMode(intValue);
    }

    private String getCameraId(int i) {
        try {
            for (String str : this.manager.getCameraIdList()) {
                Integer num = (Integer) this.manager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING);
                if (num != null && num.intValue() == i) {
                    return str;
                }
            }
            return null;
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
            return null;
        }
    }

    public void takePicture() {
        lockFocus();
    }

    private void lockFocus() {
        try {
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            this.mState = 1;
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback2, this.mBackgroundHandler);
            if (HVMagicView.camHost != null) {
                HVCamHost hVCamHost = HVMagicView.camHost;
                float[] fArr = this.focusRegion;
                hVCamHost.showCrossHair(fArr[0], fArr[1], false);
            }
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }

    public int getOrientation() {
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getRotation();
        return (!Build.MODEL.toLowerCase().contains("comio x1") || this.useFrontCam) ? 180 : 0;
    }

    public void setOrientation(int i) {
        orientation = i % 360;
    }

    public int getVideoRotation() {
        try {
            return getJpegOrientation(this.manager.getCameraCharacteristics(this.mCameraId), orientation);
        } catch (CameraAccessException e) {
            HVLog.d(HVLog_TAG, e.getMessage());
            return 0;
        }
    }

    private void startBackgroundThread() {
        try {
            HandlerThread handlerThread = new HandlerThread("CameraBackground");
            this.mBackgroundThread = handlerThread;
            handlerThread.start();
            this.mBackgroundHandler = new Handler(this.mBackgroundThread.getLooper());
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }

    public Size getPreviewSize() {
        return this.mPreviewSize;
    }

    private void stopBackgroundThread() {
        try {
            this.mBackgroundThread.quitSafely();
            try {
                this.mBackgroundThread.join();
                this.mBackgroundThread = null;
                this.mBackgroundHandler = null;
            } catch (InterruptedException e) {
                HVLog.d(HVLog_TAG, e.getMessage());
            }
        } catch (Exception e2) {
            HVLog.d(HVLog_TAG, e2.getMessage());
        }
    }

    public void pause() {
        stopBackgroundThread();
    }

    public void resume() {
        startBackgroundThread();
    }

    public void closeCamera() {
        Camera2Utils.newChosensize = null;
        try {
            try {
                try {
                    HVLog.e("CameraEngine2", "closing  camera");
                    this.mCameraOpenCloseLock.acquire();
                    CameraCaptureSession cameraCaptureSession = this.mCaptureSession;
                    if (cameraCaptureSession != null) {
                        cameraCaptureSession.abortCaptures();
                        this.mCaptureSession.close();
                        this.mCaptureSession = null;
                    }
                    CameraDevice cameraDevice = this.mCameraDevice;
                    if (cameraDevice != null) {
                        cameraDevice.close();
                        this.mCameraDevice = null;
                    }
                    ImageReader imageReader = this.mImageReader;
                    if (imageReader != null) {
                        try {
                            imageReader.close();
                        } catch (Exception unused) {
                            HVLog.e("CameraEngine2", "This image was not produced by this ImageReader");
                        }
                        this.mImageReader = null;
                    }
                    ImageReader imageReader2 = this.mImageReaderNew;
                    if (imageReader2 != null) {
                        try {
                            imageReader2.close();
                        } catch (Exception unused2) {
                            HVLog.e("CameraEngine2", "This image was not produced by this ImageReader");
                        }
                        this.mImageReaderNew = null;
                    }
                    stopBackgroundThread();
                } catch (IllegalStateException unused3) {
                    HVLog.e("CameraEngine2", "Capture session is no longer active");
                }
            } catch (CameraAccessException unused4) {
                HVLog.e("CameraEngine2", "Camera abort captures exception");
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted while trying to lock camera closing.", e);
            }
        } finally {
            this.mCameraOpenCloseLock.release();
            this.captureTriggered = false;
            this.isCameraOpened = false;
            this.isReleased = true;
        }
    }

    public boolean isReleased() {
        return this.isReleased;
    }

    public void setFlashMode(int i) {
        this.flashMode = i;
        setCurrentFlash(this.mPreviewRequestBuilder);
        CaptureRequest build = this.mPreviewRequestBuilder.build();
        this.mPreviewRequest = build;
        try {
            this.mCaptureSession.setRepeatingRequest(build, this.mCaptureCallback, this.mBackgroundHandler);
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
        updateCamHostFlashMode();
    }

    private void updateCamHostFlashMode() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.camera.CameraEngine2.8
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0057 -> B:19:0x0068). Please report as a decompilation issue!!! */
            @Override // java.lang.Runnable
            public void run() {
                if (HVMagicView.camHost != null) {
                    if (CameraEngine2.this.flashMode > 0 && (CameraEngine2.this.availableFlashmodes == null || CameraEngine2.this.availableFlashmodes.size() != 1 || ((Integer) CameraEngine2.this.availableFlashmodes.get(0)).intValue() != 1)) {
                        try {
                            int i = CameraEngine2.this.flashMode;
                            if (i == 1) {
                                HVMagicView.camHost.onFlashOff();
                            } else if (i == 2) {
                                HVMagicView.camHost.onFlashAuto();
                            } else if (i == 3) {
                                HVMagicView.camHost.onFlashOn();
                            }
                        } catch (Exception e) {
                            HVLog.d(CameraEngine2.HVLog_TAG, e.getMessage());
                        }
                        return;
                    }
                    HVMagicView.camHost.onFlashNull();
                }
            }
        });
    }

    public void switchCamera() {
        this.useFrontCam = !this.useFrontCam;
    }

    public Size getCaptureSize() {
        return this.mCaptureSize;
    }

    public boolean isFrontFacingCamera() {
        return this.useFrontCam;
    }

    public boolean getIsStillImageMode() {
        return this.isStillImageMode;
    }

    public void setIsStillImageMode(boolean z) {
        this.isStillImageMode = z;
    }

    public Rect getZoomRect(float f) {
        try {
            return AutoFocusHelper.cropRegionForZoom(this.manager.getCameraCharacteristics(this.mCameraId), f, this.zoomMax);
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
            return new Rect(0, 0, 0, 0);
        }
    }

    public void setZoom(float f) {
        if (this.isZoomSupported) {
            zoomModify(f);
        }
    }

    private void zoomModify(float f) {
        if (!this.isZoomSupported || this.mCameraId == null || f <= 0.0f) {
            return;
        }
        try {
            float f2 = this.zoomMax;
            if (f <= f2) {
                this.zoomCurrent = f;
                if (f <= f2) {
                    this.zoomRect = getZoomRect(f);
                    this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, getZoomRect(this.zoomCurrent));
                    CaptureRequest build = this.mPreviewRequestBuilder.build();
                    this.mPreviewRequest = build;
                    try {
                        this.mCaptureSession.setRepeatingRequest(build, this.mCaptureCallback, this.mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        HVLog.d(HVLog_TAG, e.getMessage());
                    }
                }
            }
        } catch (Exception e2) {
            HVLog.d(HVLog_TAG, e2.getMessage());
        }
    }

    public void setTouchEvent(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
        if (this.mCameraId != null) {
            cancelAutoFocus();
            float[] fArr = this.focusRegion;
            fArr[0] = f;
            fArr[1] = f2;
            if (this.useFrontCam) {
                f = 1.0f - f;
            }
            MeteringRectangle[] afRegionsForNormalizedCoord = AutoFocusHelper.afRegionsForNormalizedCoord(f, f2, this.zoomRect, this.mSensorOrientation);
            MeteringRectangle[] aeRegionsForNormalizedCoord = AutoFocusHelper.aeRegionsForNormalizedCoord(f, f2, this.zoomRect, this.mSensorOrientation);
            try {
                CameraCharacteristics cameraCharacteristics = this.manager.getCameraCharacteristics(this.mCameraId);
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 1);
                    if (afRegionsForNormalizedCoord != null) {
                        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_REGIONS, afRegionsForNormalizedCoord);
                    }
                } else {
                    HVLog.e(HVLog_TAG, "No focusing supported");
                }
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)).intValue() > 0) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                    if (aeRegionsForNormalizedCoord != null) {
                        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_REGIONS, aeRegionsForNormalizedCoord);
                    }
                } else {
                    HVLog.e(HVLog_TAG, "No metering supported");
                }
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
                }
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
            } catch (Exception e) {
                HVLog.d(HVLog_TAG, e.getMessage());
            }
        }
    }

    public void setFocusRectangle(float f, float f2, float f3, float f4, Camera.AutoFocusCallback autoFocusCallback) {
        float f5;
        float f6;
        if (this.mCameraId != null) {
            cancelAutoFocus();
            if (this.useFrontCam) {
                f6 = 1.0f - f3;
                f5 = 1.0f - f4;
            } else {
                f5 = f3;
                f6 = f4;
            }
            float[] fArr = this.focusRegion;
            fArr[0] = (f5 + f6) / 2.0f;
            fArr[1] = (f + f2) / 2.0f;
            MeteringRectangle[] afRegionsForRectangle = AutoFocusHelper.afRegionsForRectangle(f, f2, f5, f6, this.zoomRect, this.mSensorOrientation);
            try {
                CameraCharacteristics cameraCharacteristics = this.manager.getCameraCharacteristics(this.mCameraId);
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 1);
                    if (afRegionsForRectangle != null) {
                        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_REGIONS, afRegionsForRectangle);
                    }
                } else {
                    HVLog.e(HVLog_TAG, "No focusing supported");
                }
                if (((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)).intValue() > 0) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_REGIONS, afRegionsForRectangle);
                } else {
                    HVLog.e(HVLog_TAG, "No metering supported");
                }
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
                }
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
                this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
            } catch (Exception e) {
                HVLog.d(HVLog_TAG, e.getMessage());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setExposure(double d) {
        int round;
        int round2;
        try {
            CameraCharacteristics cameraCharacteristics = this.manager.getCameraCharacteristics(this.mCameraId);
            Range range = (Range) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
            float floatValue = ((Rational) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP)).floatValue();
            float f = this.currentExposure;
            int intValue = ((Integer) range.getUpper()).intValue();
            int intValue2 = ((Integer) range.getLower()).intValue();
            int ceil = (int) Math.ceil(((f * floatValue) + ((float) d)) / floatValue);
            float f2 = ceil;
            float f3 = f2 - f;
            if (f3 > 1.0f) {
                round = Math.round(f);
                round2 = Math.round(f3 / 2.0f);
            } else {
                if (f - f2 > 1.0f) {
                    round = Math.round(f);
                    round2 = Math.round(f3 / 2.0f);
                }
                if (ceil >= intValue2) {
                    intValue = intValue2;
                } else if (ceil <= intValue) {
                    intValue = ceil;
                }
                CaptureRequest.Builder createCaptureRequest = this.mCameraDevice.createCaptureRequest(2);
                createCaptureRequest.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(intValue));
                this.mCaptureSession.capture(createCaptureRequest.build(), null, this.mBackgroundHandler);
            }
            ceil = round + round2;
            if (ceil >= intValue2) {
            }
            CaptureRequest.Builder createCaptureRequest2 = this.mCameraDevice.createCaptureRequest(2);
            createCaptureRequest2.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(intValue));
            this.mCaptureSession.capture(createCaptureRequest2.build(), null, this.mBackgroundHandler);
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }

    private void destroy() {
        this.context = null;
    }

    public void clearEvent(Object obj) {
        try {
            float[] fArr = this.focusRegion;
            fArr[0] = -1.0f;
            fArr[1] = -1.0f;
            CameraCharacteristics cameraCharacteristics = this.manager.getCameraCharacteristics(this.mCameraId);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_REGIONS, null);
            } else {
                HVLog.e(HVLog_TAG, "No focusing supported");
            }
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE)).intValue() > 0) {
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_REGIONS, null);
            } else {
                HVLog.e(HVLog_TAG, "No metering supported");
            }
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, this.mBackgroundHandler);
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
            CaptureRequest build = this.mPreviewRequestBuilder.build();
            this.mPreviewRequest = build;
            this.mCaptureSession.setRepeatingRequest(build, this.mCaptureCallback, this.mBackgroundHandler);
        } catch (Exception e) {
            HVLog.d(HVLog_TAG, e.getMessage());
        }
    }
}
