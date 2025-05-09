package co.hyperverge.hypersnapsdk.service.sensorbiometrics;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.lang.reflect.Array;

/* loaded from: classes2.dex */
class PhoneTiltDetectedEventListener implements SensorEventListener {
    private static final String TAG = "PhoneTiltDetectedEventL";
    private final PhoneTiltListener mPhoneTiltListener;
    private final float[][] gravity = (float[][]) Array.newInstance((Class<?>) float.class, 1, 1);
    private final float[][] magnetic = (float[][]) Array.newInstance((Class<?>) float.class, 1, 1);
    private final float[][] accels = {new float[3]};
    private final float[][] mags = {new float[3]};
    private final float[] values = new float[3];
    private final float[] azimuth = new float[1];
    private final float[] pitch = new float[1];
    private final float[] roll = new float[1];

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public PhoneTiltDetectedEventListener(PhoneTiltListener phoneTiltListener) {
        this.mPhoneTiltListener = phoneTiltListener;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type == 1) {
            this.accels[0] = (float[]) sensorEvent.values.clone();
        } else if (type == 2) {
            this.mags[0] = (float[]) sensorEvent.values.clone();
        }
        float[][] fArr = this.mags;
        if (fArr[0] != null) {
            float[][] fArr2 = this.accels;
            if (fArr2[0] != null) {
                float[][] fArr3 = this.gravity;
                fArr3[0] = new float[9];
                float[][] fArr4 = this.magnetic;
                fArr4[0] = new float[9];
                SensorManager.getRotationMatrix(fArr3[0], fArr4[0], fArr2[0], fArr[0]);
                SensorManager.getOrientation(this.gravity[0], this.values);
                float[] fArr5 = this.azimuth;
                float[] fArr6 = this.values;
                fArr5[0] = fArr6[0] * 57.29578f;
                float[] fArr7 = this.pitch;
                fArr7[0] = fArr6[1] * 57.29578f;
                float[] fArr8 = this.roll;
                fArr8[0] = fArr6[2] * 57.29578f;
                this.mags[0] = null;
                this.accels[0] = null;
                this.mPhoneTiltListener.onSensorDataChanged(new SensorData(fArr5[0], fArr7[0], fArr8[0]));
            }
        }
    }
}
