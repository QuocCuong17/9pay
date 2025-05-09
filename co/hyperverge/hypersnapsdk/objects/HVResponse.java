package co.hyperverge.hypersnapsdk.objects;

import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.network.HVUtils;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVResponse extends HVBaseResponse implements Serializable {
    private ArrayList<HVBaseResponse> retakeAttemptResponses;

    @Override // co.hyperverge.hypersnapsdk.objects.HVBaseResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof HVResponse;
    }

    @Override // co.hyperverge.hypersnapsdk.objects.HVBaseResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVResponse)) {
            return false;
        }
        HVResponse hVResponse = (HVResponse) obj;
        if (!hVResponse.canEqual(this)) {
            return false;
        }
        ArrayList<HVBaseResponse> retakeAttemptResponses = getRetakeAttemptResponses();
        ArrayList<HVBaseResponse> retakeAttemptResponses2 = hVResponse.getRetakeAttemptResponses();
        return retakeAttemptResponses != null ? retakeAttemptResponses.equals(retakeAttemptResponses2) : retakeAttemptResponses2 == null;
    }

    @Override // co.hyperverge.hypersnapsdk.objects.HVBaseResponse
    public int hashCode() {
        ArrayList<HVBaseResponse> retakeAttemptResponses = getRetakeAttemptResponses();
        return 59 + (retakeAttemptResponses == null ? 43 : retakeAttemptResponses.hashCode());
    }

    public void setRetakeAttemptResponses(ArrayList<HVBaseResponse> arrayList) {
        this.retakeAttemptResponses = arrayList;
    }

    @Override // co.hyperverge.hypersnapsdk.objects.HVBaseResponse
    public String toString() {
        return "HVResponse(retakeAttemptResponses=" + getRetakeAttemptResponses() + ")";
    }

    public HVResponse() {
    }

    public ArrayList<HVBaseResponse> getRetakeAttemptResponses() {
        return this.retakeAttemptResponses;
    }

    public HVResponse(JSONObject jSONObject, JSONObject jSONObject2, String str, String str2) {
        super(jSONObject, jSONObject2, str, str2);
    }

    @Override // co.hyperverge.hypersnapsdk.objects.HVBaseResponse
    public String getCompressedImageUri() {
        if (SDKInternalConfig.getInstance() == null || !SDKInternalConfig.getInstance().shouldCompressFinalImage()) {
            return null;
        }
        return HVUtils.compressImageToSize(getImageURI(), 50);
    }
}
