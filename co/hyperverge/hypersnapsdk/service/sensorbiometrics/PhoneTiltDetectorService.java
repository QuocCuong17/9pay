package co.hyperverge.hypersnapsdk.service.sensorbiometrics;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;

/* loaded from: classes2.dex */
public class PhoneTiltDetectorService {
    private static final String TAG = "co.hyperverge.hypersnapsdk.service.sensorbiometrics.PhoneTiltDetectorService";
    private boolean isPhoneHeldFlat;
    private boolean isPhoneHeldStraight;
    private Sensor mSensorAccelerometer;
    private final PhoneTiltDetectedEventListener mSensorEventListener;
    private Sensor mSensorGeoMagnetic;
    private final SensorManager sensorManager;

    public PhoneTiltDetectorService(Context context, PhoneTiltListener phoneTiltListener) {
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
        this.mSensorEventListener = new PhoneTiltDetectedEventListener(phoneTiltListener);
        initialiseSensors();
    }

    private void initialiseSensors() {
        HVLogUtils.d(TAG, "initialiseSensors() called");
        this.mSensorAccelerometer = this.sensorManager.getDefaultSensor(1);
        this.mSensorGeoMagnetic = this.sensorManager.getDefaultSensor(2);
    }

    public void registerSensors() {
        HVLogUtils.d(TAG, "registerSensors() called");
        this.sensorManager.registerListener(this.mSensorEventListener, this.mSensorAccelerometer, 3);
        this.sensorManager.registerListener(this.mSensorEventListener, this.mSensorGeoMagnetic, 3);
    }

    public void pauseSensors() {
        String str = TAG;
        HVLogUtils.d(str, "pauseSensors() called");
        SensorManager sensorManager = this.sensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.mSensorEventListener);
        } else {
            Log.d(str, "sensorManager is null");
        }
    }

    public void resumeSensors() {
        HVLogUtils.d(TAG, "resumeSensors() called");
        registerSensors();
    }
}
