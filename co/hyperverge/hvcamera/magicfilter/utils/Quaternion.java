package co.hyperverge.hvcamera.magicfilter.utils;

import co.hyperverge.hvcamera.HVLog;

/* loaded from: classes2.dex */
public class Quaternion {
    private static final String TAG = "Quaternion";
    private float i;
    private float j;
    private float k;
    private float q;

    public Quaternion() {
        setDefaultQuaternion();
    }

    public Quaternion(float f, float f2, float f3) {
        this.i = f;
        this.j = f2;
        this.k = f3;
        checkQuaternion();
    }

    public float dot(Quaternion quaternion) {
        HVLog.d(TAG, "dot() called with: b = [" + quaternion + "]");
        return (this.q * quaternion.q) + (this.i * quaternion.i) + (this.j * quaternion.j) + (this.k * quaternion.k);
    }

    public float getTheta(Quaternion quaternion) {
        HVLog.d(TAG, "getTheta() called with: b = [" + quaternion + "]");
        float dot = dot(quaternion);
        return (float) Math.acos(((2.0f * dot) * dot) - 1.0f);
    }

    private void setDefaultQuaternion() {
        this.q = 1.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
    }

    private float sqn() {
        HVLog.d(TAG, "sqn() called");
        float f = this.i;
        float f2 = this.j;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.k;
        return f3 + (f4 * f4);
    }

    private boolean checkQuaternion() {
        HVLog.d(TAG, "checkQuaternion() called");
        if (sqn() >= 1.0f) {
            HVLog.d(TAG, "Invalid Quaternion");
            setDefaultQuaternion();
            return false;
        }
        this.q = (float) Math.sqrt(1.0f - sqn());
        return true;
    }
}
