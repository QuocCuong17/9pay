package co.hyperverge.hypersnapsdk.service.sensorbiometrics;

/* loaded from: classes2.dex */
public class SensorData {
    private float azimuth;
    private float pitch;
    private float roll;

    protected boolean canEqual(Object obj) {
        return obj instanceof SensorData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SensorData)) {
            return false;
        }
        SensorData sensorData = (SensorData) obj;
        return sensorData.canEqual(this) && Float.compare(getAzimuth(), sensorData.getAzimuth()) == 0 && Float.compare(getPitch(), sensorData.getPitch()) == 0 && Float.compare(getRoll(), sensorData.getRoll()) == 0;
    }

    public int hashCode() {
        return ((((Float.floatToIntBits(getAzimuth()) + 59) * 59) + Float.floatToIntBits(getPitch())) * 59) + Float.floatToIntBits(getRoll());
    }

    public void setAzimuth(float f) {
        this.azimuth = f;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public void setRoll(float f) {
        this.roll = f;
    }

    public String toString() {
        return "SensorData(azimuth=" + getAzimuth() + ", pitch=" + getPitch() + ", roll=" + getRoll() + ")";
    }

    public SensorData(float f, float f2, float f3) {
        this.azimuth = f;
        this.pitch = f2;
        this.roll = f3;
    }

    public float getAzimuth() {
        return this.azimuth;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getRoll() {
        return this.roll;
    }
}
