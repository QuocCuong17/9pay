package co.hyperverge.hvcamera.sensor;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class SensorEnginev2 implements SensorEventListener {
    private static final String TAG = "SensorEnginev2";
    protected boolean isInitialized;
    protected final SensorManager mSensorManager;

    /* JADX INFO: Access modifiers changed from: protected */
    public static float computeMean(float f, float f2, float f3, int i) {
        return f + ((f3 - f2) / i);
    }

    public abstract void start();

    public abstract void stop();

    public SensorEnginev2(Context context) {
        this.isInitialized = false;
        this.mSensorManager = (SensorManager) context.getSystemService("sensor");
        this.isInitialized = true;
    }

    private static float computeVectorMagnitudeSquared(ArrayList<Float> arrayList) {
        float f = 0.0f;
        for (int i = 0; i < arrayList.size(); i++) {
            f += arrayList.get(i).floatValue() * arrayList.get(i).floatValue();
        }
        return f;
    }

    protected static float computeMean(ArrayList<Float> arrayList) {
        float f = 0.0f;
        for (int i = 0; i < arrayList.size(); i++) {
            f += arrayList.get(i).floatValue();
        }
        return f / arrayList.size();
    }
}
