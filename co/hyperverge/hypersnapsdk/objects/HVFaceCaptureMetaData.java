package co.hyperverge.hypersnapsdk.objects;

/* loaded from: classes2.dex */
public class HVFaceCaptureMetaData {
    boolean isFaceDetected;

    protected boolean canEqual(Object obj) {
        return obj instanceof HVFaceCaptureMetaData;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVFaceCaptureMetaData)) {
            return false;
        }
        HVFaceCaptureMetaData hVFaceCaptureMetaData = (HVFaceCaptureMetaData) obj;
        return hVFaceCaptureMetaData.canEqual(this) && isFaceDetected() == hVFaceCaptureMetaData.isFaceDetected();
    }

    public int hashCode() {
        return 59 + (isFaceDetected() ? 79 : 97);
    }

    public void setFaceDetected(boolean z) {
        this.isFaceDetected = z;
    }

    public String toString() {
        return "HVFaceCaptureMetaData(isFaceDetected=" + isFaceDetected() + ")";
    }

    public boolean isFaceDetected() {
        return this.isFaceDetected;
    }
}
