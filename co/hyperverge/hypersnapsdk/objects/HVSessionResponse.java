package co.hyperverge.hypersnapsdk.objects;

/* loaded from: classes2.dex */
public class HVSessionResponse {
    private HVError hvError;

    protected boolean canEqual(Object obj) {
        return obj instanceof HVSessionResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVSessionResponse)) {
            return false;
        }
        HVSessionResponse hVSessionResponse = (HVSessionResponse) obj;
        if (!hVSessionResponse.canEqual(this)) {
            return false;
        }
        HVError hvError = getHvError();
        HVError hvError2 = hVSessionResponse.getHvError();
        return hvError != null ? hvError.equals(hvError2) : hvError2 == null;
    }

    public int hashCode() {
        HVError hvError = getHvError();
        return 59 + (hvError == null ? 43 : hvError.hashCode());
    }

    public void setHvError(HVError hVError) {
        this.hvError = hVError;
    }

    public String toString() {
        return "HVSessionResponse(hvError=" + getHvError() + ")";
    }

    public HVError getHvError() {
        return this.hvError;
    }

    public boolean isSuccess() {
        return this.hvError == null;
    }
}
