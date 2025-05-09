package co.hyperverge.hvcamera.magicfilter.display;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import co.hyperverge.hvcamera.GLTextureView;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine1;
import co.hyperverge.hvcamera.magicfilter.filter.base.MagicCameraInputFilter;
import co.hyperverge.hvcamera.magicfilter.utils.OpenGLUtils;
import co.hyperverge.hvcamera.magicfilter.utils.Rotation;
import co.hyperverge.hvcamera.magicfilter.utils.SaveTask2;
import co.hyperverge.hvcamera.magicfilter.utils.TextureRotationUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes2.dex */
public class MagicCameraDisplay extends MagicDisplay {
    private static final String TAG = "co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay";
    private CameraHandlerThread camHandler;
    private CameraEngine1 cameraEngine1;
    private boolean isSurfaceViewCreated;
    private final MagicCameraInputFilter mCameraInputFilter;
    SaveTask2.onPictureSaveListener mListener;
    private SurfaceTexture.OnFrameAvailableListener mOnFrameAvailableListener;
    private MyPictureCallback mPictureCallback;
    private SurfaceTexture mSurfaceTexture;
    private float[] mtx;

    public MagicCameraDisplay(GLTextureView gLTextureView) {
        super(gLTextureView);
        this.mOnFrameAvailableListener = new SurfaceTexture.OnFrameAvailableListener() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.2
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                MagicCameraDisplay.this.mGLTextureView.requestRender();
            }
        };
        this.mListener = new SaveTask2.onPictureSaveListener() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.4
            @Override // co.hyperverge.hvcamera.magicfilter.utils.SaveTask2.onPictureSaveListener
            public void onSaved(String str) {
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.onPictureSaved(new File(str));
                }
            }
        };
        this.mPictureCallback = new MyPictureCallback();
        this.mCameraInputFilter = new MagicCameraInputFilter();
        this.mtx = new float[16];
        this.cameraEngine1 = CameraEngine1.getInstance();
        this.camHandler = new CameraHandlerThread(this, this.cameraEngine1);
        currentFiltertype = 0;
    }

    @Override // co.hyperverge.hvcamera.GLTextureView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glDisable(3024);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glEnable(2884);
        GLES20.glEnable(2929);
        this.mCameraInputFilter.init();
        setFilter(currentFiltertype);
        this.isSurfaceViewCreated = true;
    }

    @Override // co.hyperverge.hvcamera.GLTextureView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glViewport(0, 0, i, i2);
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
        onFilterChanged();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.1
            @Override // java.lang.Runnable
            public void run() {
                if (HVMagicView.camHost != null) {
                    HVMagicView.camHost.onLayoutChange();
                }
            }
        });
    }

    @Override // co.hyperverge.hvcamera.GLTextureView.Renderer
    public void onDrawFrame(GL10 gl10) {
        CameraEngine1 cameraEngine1;
        if (this.mSurfaceTexture == null || (cameraEngine1 = this.cameraEngine1) == null || cameraEngine1.getCamera() == null) {
            return;
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16640);
        try {
            this.mSurfaceTexture.updateTexImage();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
        this.mSurfaceTexture.getTransformMatrix(this.mtx);
        this.mCameraInputFilter.setTextureTransformMatrix(this.mtx);
        int i = this.mTextureId;
        if (this.mFilters == null) {
            this.mCameraInputFilter.onDrawFrame(this.mTextureId, this.mGLCubeBuffer, this.mGLTextureBuffer);
        } else {
            this.mFilters.onDrawFrame(this.mCameraInputFilter.onDrawToTexture(this.mTextureId), this.mGLCubeBuffer, this.mGLTextureBuffer);
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void clearHandlers() {
        super.clearHandlers();
        this.camHandler = null;
        this.cameraEngine1.destroyEngine();
        this.cameraEngine1 = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpCamera() {
        this.mGLTextureView.removeCallbacks(null);
        this.mGLTextureView.queueEvent(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.3
            /* JADX WARN: Removed duplicated region for block: B:17:0x008b A[Catch: Exception -> 0x00c5, TryCatch #0 {Exception -> 0x00c5, blocks: (B:2:0x0000, B:4:0x0007, B:5:0x002c, B:9:0x0039, B:14:0x004c, B:15:0x0065, B:17:0x008b, B:19:0x009b, B:20:0x00a2, B:22:0x00a8, B:25:0x00b7, B:29:0x0059), top: B:1:0x0000 }] */
            /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                try {
                    if (MagicCameraDisplay.this.mTextureId == -1) {
                        MagicCameraDisplay.this.mTextureId = OpenGLUtils.getExternalOESTextureID();
                        MagicCameraDisplay.this.mSurfaceTexture = new SurfaceTexture(MagicCameraDisplay.this.mTextureId);
                        MagicCameraDisplay.this.mSurfaceTexture.setOnFrameAvailableListener(MagicCameraDisplay.this.mOnFrameAvailableListener);
                    }
                    Camera.Size previewSize = MagicCameraDisplay.this.cameraEngine1.getPreviewSize();
                    if (previewSize == null) {
                        return;
                    }
                    int orientation = MagicCameraDisplay.this.cameraEngine1.getOrientation();
                    if (orientation != 90 && orientation != 270) {
                        MagicCameraDisplay.this.mImageWidth = previewSize.width;
                        MagicCameraDisplay.this.mImageHeight = previewSize.height;
                        MagicCameraDisplay.this.mCameraInputFilter.onOutputSizeChanged(MagicCameraDisplay.this.mImageWidth, MagicCameraDisplay.this.mImageHeight);
                        MagicCameraDisplay.this.cameraEngine1.startPreview(MagicCameraDisplay.this.mSurfaceTexture);
                        if (CameraEngine.getCaptureMode()) {
                            return;
                        }
                        Camera.Size bestPictureSize = MagicCameraDisplay.this.cameraEngine1.getBestPictureSize();
                        if (MagicCameraDisplay.this.ib != null) {
                            MagicCameraDisplay.this.ib.clear();
                        }
                        if (MagicCameraDisplay.this.ib == null || MagicCameraDisplay.this.ib.capacity() < bestPictureSize.height * bestPictureSize.width) {
                            MagicCameraDisplay.this.ib = IntBuffer.allocate(bestPictureSize.height * bestPictureSize.width);
                            return;
                        }
                        return;
                    }
                    MagicCameraDisplay.this.mImageWidth = previewSize.height;
                    MagicCameraDisplay.this.mImageHeight = previewSize.width;
                    MagicCameraDisplay.this.mCameraInputFilter.onOutputSizeChanged(MagicCameraDisplay.this.mImageWidth, MagicCameraDisplay.this.mImageHeight);
                    MagicCameraDisplay.this.cameraEngine1.startPreview(MagicCameraDisplay.this.mSurfaceTexture);
                    if (CameraEngine.getCaptureMode()) {
                    }
                } catch (Exception e) {
                    if (TextUtils.isEmpty(e.getMessage())) {
                        return;
                    }
                    Log.d(MagicCameraDisplay.TAG, e.getMessage());
                }
            }
        });
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onCameraFlip() {
        CameraHandlerThread cameraHandlerThread = this.camHandler;
        if (cameraHandlerThread != null) {
            cameraHandlerThread.triggerSwitchCamera();
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onCameraChanged() {
        CameraHandlerThread cameraHandlerThread = this.camHandler;
        if (cameraHandlerThread != null) {
            cameraHandlerThread.triggerChange();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onFilterChanged() {
        super.onFilterChanged();
        this.mCameraInputFilter.onDisplaySizeChanged(this.mSurfaceWidth, this.mSurfaceHeight);
        if (this.mFilters != null) {
            this.mCameraInputFilter.initCameraFrameBuffer(this.mImageWidth, this.mImageHeight);
        } else {
            this.mCameraInputFilter.destroyFramebuffers();
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onResume() {
        super.onResume();
        CameraHandlerThread cameraHandlerThread = this.camHandler;
        if (cameraHandlerThread != null) {
            cameraHandlerThread.triggerOpen();
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onPause() {
        super.onPause();
        CameraHandlerThread cameraHandlerThread = this.camHandler;
        if (cameraHandlerThread != null) {
            cameraHandlerThread.triggerClose();
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onDestroy() {
        if (this.ib != null) {
            destroyBuffer(this.ib);
            this.ib = null;
        }
        super.onDestroy();
    }

    /* loaded from: classes2.dex */
    private static class CameraHandlerThread extends HandlerThread {
        private final Handler backHandler;
        WeakReference<CameraEngine1> cameraEngineWeakReference;
        private Runnable changeCamera;
        WeakReference<MagicCameraDisplay> magicCameraDisplayWeakReference;
        private Runnable openCamera;
        private Runnable releaseCamera;
        private Runnable switchCamera;
        private final Handler uiHandler;
        private Runnable uiSetupRunnable;

        public CameraHandlerThread(MagicCameraDisplay magicCameraDisplay, CameraEngine1 cameraEngine1) {
            super("CameraHandler");
            this.releaseCamera = new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.CameraHandlerThread.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MagicCameraDisplay magicCameraDisplay2 = CameraHandlerThread.this.magicCameraDisplayWeakReference.get();
                        CameraEngine1 cameraEngine12 = CameraHandlerThread.this.cameraEngineWeakReference.get();
                        CameraHandlerThread.this.uiHandler.removeCallbacksAndMessages(null);
                        magicCameraDisplay2.mGLTextureView.removeCallbacks(null);
                        cameraEngine12.releaseCamera();
                    } catch (Exception e) {
                        Log.e(MagicCameraDisplay.TAG, e.getMessage());
                    }
                }
            };
            this.openCamera = new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.CameraHandlerThread.2
                @Override // java.lang.Runnable
                public void run() {
                    MagicCameraDisplay magicCameraDisplay2 = CameraHandlerThread.this.magicCameraDisplayWeakReference.get();
                    CameraEngine1 cameraEngine12 = CameraHandlerThread.this.cameraEngineWeakReference.get();
                    CameraHandlerThread.this.uiHandler.removeCallbacksAndMessages(null);
                    if (magicCameraDisplay2 == null || cameraEngine12 == null) {
                        return;
                    }
                    try {
                        cameraEngine12.openCamera();
                        if (cameraEngine12.getCamera() != null) {
                            boolean isFrontFacingCamera = cameraEngine12.isFrontFacingCamera();
                            magicCameraDisplay2.adjustPosition(cameraEngine12.getOrientation(), isFrontFacingCamera, !isFrontFacingCamera);
                            CameraHandlerThread.this.uiHandler.post(CameraHandlerThread.this.uiSetupRunnable);
                        }
                    } catch (Exception e) {
                        Log.e(MagicCameraDisplay.TAG, e.getMessage());
                    }
                }
            };
            this.switchCamera = new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.CameraHandlerThread.3
                @Override // java.lang.Runnable
                public void run() {
                    MagicCameraDisplay magicCameraDisplay2 = CameraHandlerThread.this.magicCameraDisplayWeakReference.get();
                    CameraEngine1 cameraEngine12 = CameraHandlerThread.this.cameraEngineWeakReference.get();
                    CameraHandlerThread.this.uiHandler.removeCallbacksAndMessages(null);
                    if (magicCameraDisplay2 == null || cameraEngine12 == null) {
                        return;
                    }
                    try {
                        magicCameraDisplay2.mGLTextureView.removeCallbacks(null);
                        cameraEngine12.releaseCamera();
                        cameraEngine12.switchCamera();
                        CameraHandlerThread.this.uiHandler.post(CameraHandlerThread.this.uiSetupRunnable);
                        if (HVMagicView.camHost != null) {
                            HVMagicView.camHost.onCameraFlipCallback();
                        }
                    } catch (Exception e) {
                        Log.e(MagicCameraDisplay.TAG, e.getMessage());
                    }
                }
            };
            this.changeCamera = new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.CameraHandlerThread.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        CameraHandlerThread.this.uiHandler.removeCallbacksAndMessages(null);
                        MagicCameraDisplay magicCameraDisplay2 = CameraHandlerThread.this.magicCameraDisplayWeakReference.get();
                        CameraEngine1 cameraEngine12 = CameraHandlerThread.this.cameraEngineWeakReference.get();
                        magicCameraDisplay2.mGLTextureView.removeCallbacks(null);
                        cameraEngine12.releaseCamera();
                        cameraEngine12.changeCamera();
                        CameraHandlerThread.this.uiHandler.post(CameraHandlerThread.this.uiSetupRunnable);
                    } catch (Exception e) {
                        Log.e(MagicCameraDisplay.TAG, e.getMessage());
                    }
                }
            };
            this.uiSetupRunnable = new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.CameraHandlerThread.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        MagicCameraDisplay magicCameraDisplay2 = CameraHandlerThread.this.magicCameraDisplayWeakReference.get();
                        Log.i("camera", "setup called");
                        magicCameraDisplay2.setUpCamera();
                        if (magicCameraDisplay2.isSurfaceViewCreated) {
                            magicCameraDisplay2.setFilter(MagicDisplay.currentFiltertype);
                        }
                        magicCameraDisplay2.mGLTextureView.requestLayout();
                        HVMagicView.reLayout();
                    } catch (Exception e) {
                        Log.e(MagicCameraDisplay.TAG, e.getMessage());
                    }
                }
            };
            this.magicCameraDisplayWeakReference = new WeakReference<>(magicCameraDisplay);
            this.cameraEngineWeakReference = new WeakReference<>(cameraEngine1);
            start();
            this.backHandler = new Handler(getLooper());
            this.uiHandler = new Handler(Looper.getMainLooper());
        }

        public void triggerOpen() {
            this.backHandler.removeCallbacksAndMessages(null);
            this.backHandler.post(this.openCamera);
        }

        public void triggerClose() {
            this.backHandler.removeCallbacksAndMessages(null);
            this.backHandler.post(this.releaseCamera);
        }

        public void triggerSwitchCamera() {
            this.backHandler.removeCallbacksAndMessages(null);
            this.backHandler.post(this.switchCamera);
        }

        public void triggerChange() {
            this.backHandler.removeCallbacksAndMessages(null);
            this.backHandler.post(this.changeCamera);
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void getBitmapfromgl(Bitmap bitmap, File file, boolean z, boolean z2, int i) {
        if (this.mFilters != null && currentFiltertype != 0) {
            getBitmapFromGL(bitmap, true, z, z2, file, i);
        } else {
            new SaveTask2(file, this.mListener, i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, bitmap);
        }
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void onTakePicture(File file, SaveTask2.onPictureSaveListener onpicturesavelistener, Camera.ShutterCallback shutterCallback) {
        this.mPictureCallback.setPhotoFile(file);
        this.mPictureCallback.setCameraDisplay(this);
        this.cameraEngine1.takePicture(shutterCallback, null, this.mPictureCallback);
    }

    @Override // co.hyperverge.hvcamera.magicfilter.display.MagicDisplay
    public void setTouchEvent(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
        CameraEngine1 cameraEngine1 = this.cameraEngine1;
        if (cameraEngine1 != null) {
            cameraEngine1.setTouchEvent(f, f2, autoFocusCallback);
        }
    }

    /* loaded from: classes2.dex */
    private class MyPictureCallback implements Camera.PictureCallback {
        MagicCameraDisplay mCameraDisplay;
        public File photoFile;

        private MyPictureCallback() {
        }

        public void setPhotoFile(File file) {
            this.photoFile = file;
        }

        public void setCameraDisplay(MagicCameraDisplay magicCameraDisplay) {
            this.mCameraDisplay = magicCameraDisplay;
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            try {
                if (HVMagicView.camHost == null || MagicCameraDisplay.this.cameraEngine1 == null) {
                    return;
                }
                MagicCameraDisplay.this.cameraEngine1.startPreview();
                HVMagicView.camHost.onReady();
                HVMagicView.camHost.onPictureReady(bArr);
            } catch (Exception e) {
                Log.d(MagicCameraDisplay.TAG, e.getMessage());
            }
        }
    }

    protected void onGetBitmapFromGL(Bitmap bitmap, File file, int i) {
        new SaveTask2(file, this.mListener, i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustPosition(int i, boolean z, boolean z2) {
        float[] rotation = TextureRotationUtil.getRotation(Rotation.fromInt(i), z, z2);
        this.mGLTextureBuffer.clear();
        this.mGLTextureBuffer.put(rotation).position(0);
    }

    public void autoFocus() {
        CameraEngine1.getInstance().autoFocus();
    }

    private void drawBox(int i) {
        GLES20.glEnable(3089);
        GLES20.glScissor(50, 200, 100, 100);
        GLES20.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
        GLES20.glClear(16384);
        GLES20.glDisable(3089);
    }

    public static void destroyBuffer(Buffer buffer) {
        if (buffer.isDirect()) {
            try {
                if (!buffer.getClass().getName().equals("java.nio.DirectByteBuffer")) {
                    Field declaredField = buffer.getClass().getDeclaredField("att");
                    declaredField.setAccessible(true);
                    buffer = (Buffer) declaredField.get(buffer);
                }
                Method method = buffer.getClass().getMethod("cleaner", new Class[0]);
                method.setAccessible(true);
                Object invoke = method.invoke(buffer, new Object[0]);
                Method method2 = invoke.getClass().getMethod("clean", new Class[0]);
                method2.setAccessible(true);
                method2.invoke(invoke, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("Could not destroy direct buffer " + buffer, e);
            }
        }
    }

    protected void getBitmapFromGL(final Bitmap bitmap, final boolean z, final boolean z2, final boolean z3, final File file, final int i) {
        this.mGLTextureView.queueEvent(new Runnable() { // from class: co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay.5
            @Override // java.lang.Runnable
            public void run() {
                int i2;
                int i3;
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                GLES20.glGenFramebuffers(1, iArr, 0);
                GLES20.glGenTextures(1, iArr2, 0);
                GLES20.glBindTexture(3553, iArr2[0]);
                GLES20.glTexImage2D(3553, 0, 6408, width, height, 0, 6408, 5121, null);
                GLES20.glTexParameterf(3553, 10240, 9729.0f);
                GLES20.glTexParameterf(3553, 10241, 9729.0f);
                GLES20.glTexParameterf(3553, 10242, 33071.0f);
                GLES20.glTexParameterf(3553, 10243, 33071.0f);
                GLES20.glBindFramebuffer(36160, iArr[0]);
                GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[0], 0);
                GLES20.glViewport(0, 0, width, height);
                MagicCameraDisplay.this.mFilters.onOutputSizeChanged(width, height);
                MagicCameraDisplay.this.mFilters.onDisplaySizeChanged(MagicCameraDisplay.this.mImageWidth, MagicCameraDisplay.this.mImageHeight);
                if (z) {
                    i2 = OpenGLUtils.loadTexture(bitmap, -1, true);
                } else {
                    i2 = MagicCameraDisplay.this.mTextureId;
                }
                int i4 = i2;
                MagicCameraDisplay.this.mFilters.onDrawFrame(i4, z2, z3);
                if (MagicCameraDisplay.this.ib != null) {
                    MagicCameraDisplay.this.ib.clear();
                }
                if (MagicCameraDisplay.this.ib == null || MagicCameraDisplay.this.ib.capacity() < height * width) {
                    MagicCameraDisplay.this.ib = IntBuffer.allocate(height * width);
                }
                GLES20.glReadPixels(0, 0, width, height, 6408, 5121, MagicCameraDisplay.this.ib);
                Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                createBitmap.copyPixelsFromBuffer(IntBuffer.wrap(MagicCameraDisplay.this.ib.array()));
                if (CameraEngine1.getInstance().getCamera() != null) {
                    i3 = 1;
                    MagicCameraDisplay.this.adjustPosition(CameraEngine1.getInstance().getOrientation(), true, false);
                } else {
                    i3 = 1;
                }
                if (z) {
                    int[] iArr3 = new int[i3];
                    iArr3[0] = i4;
                    GLES20.glDeleteTextures(i3, iArr3, 0);
                }
                GLES20.glDeleteFramebuffers(i3, iArr, 0);
                GLES20.glDeleteTextures(i3, iArr2, 0);
                GLES20.glViewport(0, 0, MagicCameraDisplay.this.mSurfaceWidth, MagicCameraDisplay.this.mSurfaceHeight);
                MagicCameraDisplay.this.mFilters.onOutputSizeChanged(MagicCameraDisplay.this.mImageWidth, MagicCameraDisplay.this.mImageHeight);
                MagicCameraDisplay.this.onGetBitmapFromGL(createBitmap, file, i);
            }
        });
    }
}
