package co.hyperverge.hvcamera.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Handler;
import co.hyperverge.hvcamera.HVLog;
import co.hyperverge.hvcamera.HVMagicView;
import co.hyperverge.hvcamera.magicfilter.camera.CameraEngine;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AccelerometerEngine extends SensorEnginev2 {
    private static final String TAG = "AccelerometerEngine";
    private final int MAX_WINDOWSIZE;
    private ArrayList<ArrayList<Float>> accValues;
    private final long burnInPeriod;
    private HVMagicView.SensorCallback callback;
    private Handler h;
    private boolean hasAccelSensing;
    private boolean isAccelSensing;
    private boolean isReferenceSet;
    private boolean isRunning;
    private boolean isTouchEvent;
    private Sensor mAccelerometerSensor;
    private ArrayList<Float> meanValues;
    private Runnable motionFocusRunnable;
    private Long refSensorTimestamp;
    private Long refTimestamp;
    private final float threshold;
    private ArrayList<Long> timestamps;
    private Runnable touchEventRunnable;
    private int windowSize;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public AccelerometerEngine(Context context, int i) {
        super(context);
        this.MAX_WINDOWSIZE = 15;
        this.isTouchEvent = false;
        this.burnInPeriod = 1000L;
        this.threshold = 0.325f;
        init(i);
    }

    public void init(int i) {
        HVLog.d(TAG, "init() called with: wSize = [" + i + "]");
        this.hasAccelSensing = false;
        this.isAccelSensing = false;
        this.isReferenceSet = false;
        this.isRunning = false;
        this.h = new Handler();
        this.motionFocusRunnable = new Runnable() { // from class: co.hyperverge.hvcamera.sensor.AccelerometerEngine.1
            @Override // java.lang.Runnable
            public void run() {
                AccelerometerEngine.this.isRunning = true;
                CameraEngine.clearEvent(null);
                AccelerometerEngine.this.isTouchEvent = false;
                if (AccelerometerEngine.this.callback != null) {
                    AccelerometerEngine.this.callback.onSensorCallback();
                }
                AccelerometerEngine.this.isRunning = false;
                AccelerometerEngine.this.h.removeMessages(0);
            }
        };
        this.touchEventRunnable = new Runnable() { // from class: co.hyperverge.hvcamera.sensor.AccelerometerEngine.2
            @Override // java.lang.Runnable
            public void run() {
                AccelerometerEngine.this.isRunning = false;
            }
        };
        if (this.mSensorManager != null) {
            this.mAccelerometerSensor = this.mSensorManager.getDefaultSensor(1);
        } else {
            this.hasAccelSensing = false;
        }
        if (this.mAccelerometerSensor != null) {
            this.hasAccelSensing = true;
        }
        if (i > 15) {
            this.windowSize = 15;
        } else {
            this.windowSize = i;
        }
        this.accValues = new ArrayList<>(3);
        this.meanValues = new ArrayList<>(3);
        for (int i2 = 0; i2 < 3; i2++) {
            this.accValues.add(i2, new ArrayList<>(this.windowSize));
            for (int i3 = 0; i3 < this.windowSize; i3++) {
                this.accValues.get(i2).add(i3, Float.valueOf(0.0f));
            }
            this.meanValues.add(i2, Float.valueOf(0.0f));
        }
        this.timestamps = new ArrayList<>();
    }

    public void touchEventRunning() {
        this.isTouchEvent = true;
        if (!this.isRunning && this.h.hasMessages(0)) {
            this.h.removeCallbacks(this.motionFocusRunnable);
            this.h.removeMessages(0);
        }
        this.isRunning = true;
        this.h.postDelayed(this.touchEventRunnable, 2500L);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (!this.isReferenceSet) {
            this.refSensorTimestamp = Long.valueOf(sensorEvent.timestamp);
            this.refTimestamp = Long.valueOf(System.currentTimeMillis());
            this.isReferenceSet = true;
        }
        Long valueOf = Long.valueOf(this.refTimestamp.longValue() + Math.round((float) ((sensorEvent.timestamp - this.refSensorTimestamp.longValue()) / 1000000)));
        if (type == 1) {
            for (int i = 0; i < this.meanValues.size(); i++) {
                ArrayList<Float> arrayList = this.meanValues;
                arrayList.set(i, Float.valueOf(computeMean(arrayList.get(i).floatValue(), this.accValues.get(i).get(0).floatValue(), sensorEvent.values[i], this.windowSize)));
            }
            updateAccQueue(sensorEvent.values);
            updateTStampQueue(valueOf);
            if (this.timestamps.size() == this.windowSize && valueOf.longValue() - this.refTimestamp.longValue() >= 1000 && this.isTouchEvent) {
                isZeroCrossingAnyAxis();
            }
        }
    }

    @Override // co.hyperverge.hvcamera.sensor.SensorEnginev2
    public void stop() {
        HVLog.d(TAG, "stop() called");
        if (this.isAccelSensing && this.hasAccelSensing) {
            this.mSensorManager.unregisterListener(this, this.mAccelerometerSensor);
            this.isAccelSensing = false;
            this.isReferenceSet = false;
            this.h.removeCallbacksAndMessages(null);
            HVLog.d(TAG, "Accelerometer sensing stopped");
        }
    }

    @Override // co.hyperverge.hvcamera.sensor.SensorEnginev2
    public void start() {
        if (this.hasAccelSensing) {
            if (this.isAccelSensing) {
                return;
            }
            this.mSensorManager.registerListener(this, this.mAccelerometerSensor, 3);
            this.isAccelSensing = true;
            HVLog.d(TAG, "Acceleration Sensing started");
            return;
        }
        HVLog.d("Sensing", "Acceleration is unavailable");
    }

    public void setCallback(HVMagicView.SensorCallback sensorCallback) {
        this.callback = sensorCallback;
    }

    private void updateAccQueue(float[] fArr) {
        for (int i = 0; i < fArr.length; i++) {
            this.accValues.get(i).add(Float.valueOf(fArr[i]));
            if (this.accValues.get(i).size() > this.windowSize) {
                this.accValues.get(i).remove(0);
            }
        }
    }

    private void updateTStampQueue(Long l) {
        this.timestamps.add(l);
        if (this.timestamps.size() > this.windowSize) {
            this.timestamps.remove(0);
        }
    }

    private boolean isZeroCrossingAnyAxis() {
        Long.valueOf(System.currentTimeMillis());
        int i = this.windowSize;
        int i2 = (i / 2) - 1;
        int i3 = i / 2;
        if (this.timestamps.get(i3).longValue() - this.timestamps.get(i2).longValue() < 500) {
            for (int i4 = 0; i4 < this.accValues.size(); i4++) {
                Float valueOf = Float.valueOf(this.accValues.get(i4).get(i3).floatValue() - this.meanValues.get(i4).floatValue());
                Float valueOf2 = Float.valueOf(this.accValues.get(i4).get(i2).floatValue() - this.meanValues.get(i4).floatValue());
                if ((Math.abs(valueOf.floatValue()) > Math.max(0.325f, this.meanValues.get(i4).floatValue() / 4.0f) || Math.abs(valueOf2.floatValue()) > Math.max(0.325f, this.meanValues.get(i4).floatValue() / 4.0f)) && valueOf.floatValue() * valueOf2.floatValue() < 0.0f) {
                    if (!this.isRunning) {
                        if (this.h.hasMessages(0)) {
                            this.h.removeCallbacks(this.motionFocusRunnable);
                            this.h.removeMessages(0);
                        }
                        this.h.postDelayed(this.motionFocusRunnable, 300L);
                        this.h.sendEmptyMessage(0);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
