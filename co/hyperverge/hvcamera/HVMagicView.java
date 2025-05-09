package co.hyperverge.hvcamera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay;
import co.hyperverge.hvcamera.magicfilter.display.MagicCameraDisplay2;
import co.hyperverge.hvcamera.magicfilter.display.MagicDisplay;
import co.hyperverge.hvcamera.magicfilter.filter.helper.MagicFilterType;
import co.hyperverge.hvcamera.sensor.AccelerometerEngine;
import java.io.File;

/* loaded from: classes2.dex */
public class HVMagicView extends GLTextureView {
    private static final String LOG_TAG = "co.hyperverge.hvcamera.HVMagicView";
    public static HVCamHost camHost = null;
    private static HVMagicView instance = null;
    private static int mRatioHeight = 4;
    private static int mRatioWidth = 3;
    private AccelerometerEngine accelerometerEngine;
    int filterType;
    private MagicDisplay magicDisplay;
    private OnOrientationChange orientationChange;
    private boolean rotationDisabled;
    Camera.ShutterCallback s;

    /* loaded from: classes2.dex */
    public interface SensorCallback {
        void onSensorCallback();
    }

    public void autoFocusOnly() {
    }

    public static HVMagicView getInstance(Context context, HVCamHost hVCamHost, boolean z) {
        HVMagicView hVMagicView = new HVMagicView(context, hVCamHost, z);
        instance = hVMagicView;
        return hVMagicView;
    }

    private HVMagicView(Context context, HVCamHost hVCamHost, boolean z) {
        super(context);
        this.rotationDisabled = false;
        this.s = new Camera.ShutterCallback() { // from class: co.hyperverge.hvcamera.HVMagicView.3
            @Override // android.hardware.Camera.ShutterCallback
            public void onShutter() {
                try {
                    if (HVMagicView.camHost != null) {
                        HVMagicView.camHost.flashScreen();
                    }
                } catch (Exception e) {
                    Log.d(HVMagicView.LOG_TAG, e.getMessage());
                }
            }
        };
        this.filterType = 0;
        camHost = hVCamHost;
        hVCamHost.onCamerasFound(Camera.getNumberOfCameras());
        CameraEngine.init(context, z);
        this.accelerometerEngine = new AccelerometerEngine(context, 9);
        if (!CameraEngine.isCamera2(context)) {
            this.magicDisplay = new MagicCameraDisplay(this);
        } else {
            this.magicDisplay = new MagicCameraDisplay2(this);
        }
        updateRatios();
        OnOrientationChange onOrientationChange = new OnOrientationChange(context);
        this.orientationChange = onOrientationChange;
        onOrientationChange.enable();
    }

    public void setCamHost(HVCamHost hVCamHost) {
        camHost = hVCamHost;
    }

    public HVCamHost getCamHost() {
        return camHost;
    }

    public static int getAspectRatio() {
        return mRatioHeight == 4 ? 1 : 2;
    }

    public void disableRotation() {
        this.orientationChange.disable();
        this.rotationDisabled = true;
        CameraEngine.setOrientation(0);
    }

    public static void updateRatios() {
        HVCamHost hVCamHost = camHost;
        if (hVCamHost != null) {
            if (hVCamHost.getAspectRatio() == 1) {
                mRatioWidth = 3;
                mRatioHeight = 4;
            } else if (camHost.getAspectRatio() == 2) {
                mRatioWidth = 9;
                mRatioHeight = 16;
            }
        }
    }

    public static void reLayout() {
        HVMagicView hVMagicView = instance;
        if (hVMagicView != null) {
            hVMagicView.requestLayout();
        }
    }

    public static void fallBackAspectRatio() {
        mRatioWidth = 3;
        mRatioHeight = 4;
        if (instance != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: co.hyperverge.hvcamera.HVMagicView.1
                @Override // java.lang.Runnable
                public void run() {
                    HVMagicView.instance.requestLayout();
                }
            });
        }
    }

    public static int getmRatioWidth() {
        return mRatioWidth;
    }

    public static int getmRatioHeight() {
        return mRatioHeight;
    }

    @Override // co.hyperverge.hvcamera.GLTextureView
    public void onPause() {
        MagicDisplay magicDisplay = this.magicDisplay;
        if (magicDisplay != null) {
            magicDisplay.onPause();
        }
        removeCallbacks(null);
        this.orientationChange.disable();
        this.accelerometerEngine.stop();
    }

    public void destroyGL() {
        super.onPause();
    }

    public void resumeGL() {
        super.onResume();
    }

    @Override // co.hyperverge.hvcamera.GLTextureView
    public void onResume() {
        super.onResume();
        if (!this.rotationDisabled) {
            this.orientationChange.enable();
        }
        this.accelerometerEngine.start();
        MagicDisplay magicDisplay = this.magicDisplay;
        if (magicDisplay != null) {
            magicDisplay.onResume();
        }
    }

    public void onDestroy() {
        queueEvent(new Runnable() { // from class: co.hyperverge.hvcamera.HVMagicView.2
            @Override // java.lang.Runnable
            public void run() {
                if (HVMagicView.this.magicDisplay != null) {
                    HVMagicView.this.magicDisplay.onDestroy();
                }
            }
        });
        destroyGL();
        instance = null;
        camHost = null;
    }

    public void clearHandlers() {
        MagicDisplay magicDisplay = this.magicDisplay;
        if (magicDisplay != null) {
            magicDisplay.clearHandlers();
        }
    }

    public void takePicture(String str) {
        HVCamHost hVCamHost;
        this.accelerometerEngine.stop();
        if (str == null && (hVCamHost = camHost) != null) {
            this.magicDisplay.onTakePicture(hVCamHost.getPhotoPath(), null, this.s);
        } else {
            this.magicDisplay.onTakePicture(new File(str), null, this.s);
        }
    }

    public void rotateCamera() {
        this.magicDisplay.onCameraFlip();
    }

    public void onTouchToFocus(float f, float f2, Camera.AutoFocusCallback autoFocusCallback) {
        this.magicDisplay.setTouchEvent(f, f2, autoFocusCallback);
        this.accelerometerEngine.touchEventRunning();
    }

    /* loaded from: classes2.dex */
    private class OnOrientationChange extends OrientationEventListener {
        public OnOrientationChange(Context context) {
            super(context);
            disable();
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            if (i == -1) {
                i = 0;
            }
            try {
                CameraEngine.setOrientation(i);
            } catch (Throwable th) {
                Log.d(HVMagicView.LOG_TAG, th.getMessage());
            }
        }

        @Override // android.view.OrientationEventListener
        public void enable() {
            super.enable();
        }

        @Override // android.view.OrientationEventListener
        public void disable() {
            super.disable();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i4 = mRatioWidth;
        if (i4 == 0 || (i3 = mRatioHeight) == 0) {
            setMeasuredDimension(size, size2);
            HVCamHost hVCamHost = camHost;
            if (hVCamHost != null) {
                hVCamHost.onViewDimensionChange(size, size2);
                return;
            }
            return;
        }
        setMeasuredDimension(size, (i3 * size) / i4);
        HVCamHost hVCamHost2 = camHost;
        if (hVCamHost2 != null) {
            hVCamHost2.onViewDimensionChange(size, (mRatioHeight * size) / mRatioWidth);
        }
    }

    public void setFilter(int i) {
        this.filterType = i;
        this.magicDisplay.setFilter(i);
        HVCamHost hVCamHost = camHost;
        if (hVCamHost != null) {
            hVCamHost.onFilterMode(i, MagicFilterType.filterTypeToNameString(getContext(), i));
        }
    }

    public void nextFlashMode() {
        CameraEngine.nextFlashMode();
    }

    public void setSensorCallback(SensorCallback sensorCallback) {
        this.accelerometerEngine.setCallback(sensorCallback);
    }

    public void onCameraChanged() {
        this.magicDisplay.onCameraChanged();
    }

    public void startAccelerometer() {
        this.accelerometerEngine.start();
    }

    public void stopAccelerometer() {
        this.accelerometerEngine.stop();
    }

    @Override // co.hyperverge.hvcamera.GLTextureView
    public void surfaceDestroyed(SurfaceTexture surfaceTexture) {
        super.surfaceDestroyed(surfaceTexture);
        Log.i(LOG_TAG, "destroyed");
        this.magicDisplay.onDestroy();
    }
}
