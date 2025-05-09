package co.hyperverge.hypersnapsdk.model;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.tekartik.sqflite.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LivenessResponse extends BaseResponse {
    private static final String TAG = "co.hyperverge.hypersnapsdk.model.LivenessResponse";
    private JSONObject headers;
    private String livenessError = null;
    private String requestID;
    private JSONObject response;

    @Override // co.hyperverge.hypersnapsdk.model.BaseResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof LivenessResponse;
    }

    @Override // co.hyperverge.hypersnapsdk.model.BaseResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LivenessResponse)) {
            return false;
        }
        LivenessResponse livenessResponse = (LivenessResponse) obj;
        if (!livenessResponse.canEqual(this)) {
            return false;
        }
        JSONObject response = getResponse();
        JSONObject response2 = livenessResponse.getResponse();
        if (response != null ? !response.equals(response2) : response2 != null) {
            return false;
        }
        JSONObject headers = getHeaders();
        JSONObject headers2 = livenessResponse.getHeaders();
        if (headers != null ? !headers.equals(headers2) : headers2 != null) {
            return false;
        }
        String livenessError = getLivenessError();
        String livenessError2 = livenessResponse.getLivenessError();
        if (livenessError != null ? !livenessError.equals(livenessError2) : livenessError2 != null) {
            return false;
        }
        String requestID = getRequestID();
        String requestID2 = livenessResponse.getRequestID();
        return requestID != null ? requestID.equals(requestID2) : requestID2 == null;
    }

    @Override // co.hyperverge.hypersnapsdk.model.BaseResponse
    public int hashCode() {
        JSONObject response = getResponse();
        int hashCode = response == null ? 43 : response.hashCode();
        JSONObject headers = getHeaders();
        int hashCode2 = ((hashCode + 59) * 59) + (headers == null ? 43 : headers.hashCode());
        String livenessError = getLivenessError();
        int hashCode3 = (hashCode2 * 59) + (livenessError == null ? 43 : livenessError.hashCode());
        String requestID = getRequestID();
        return (hashCode3 * 59) + (requestID != null ? requestID.hashCode() : 43);
    }

    public void setHeaders(JSONObject jSONObject) {
        this.headers = jSONObject;
    }

    public void setLivenessError(String str) {
        this.livenessError = str;
    }

    public void setRequestID(String str) {
        this.requestID = str;
    }

    public void setResponse(JSONObject jSONObject) {
        this.response = jSONObject;
    }

    @Override // co.hyperverge.hypersnapsdk.model.BaseResponse
    public String toString() {
        return "LivenessResponse(response=" + getResponse() + ", headers=" + getHeaders() + ", livenessError=" + getLivenessError() + ", requestID=" + getRequestID() + ")";
    }

    public JSONObject getResponse() {
        return this.response;
    }

    public JSONObject getHeaders() {
        return this.headers;
    }

    public String getLivenessError() {
        return this.livenessError;
    }

    public String getRequestID() {
        return this.requestID;
    }

    public String getError() {
        JSONObject jSONObject = this.response;
        if (jSONObject == null || !jSONObject.has(Constant.PARAM_RESULT)) {
            return null;
        }
        try {
            JSONObject jSONObject2 = this.response.getJSONObject(Constant.PARAM_RESULT);
            if (jSONObject2.has("error")) {
                return jSONObject2.getString("error");
            }
            return null;
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return null;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return null;
        }
    }

    public boolean toBeReviewed() {
        JSONObject jSONObject = this.response;
        if (jSONObject == null || !jSONObject.has(Constant.PARAM_RESULT)) {
            return false;
        }
        try {
            JSONObject jSONObject2 = this.response.getJSONObject(Constant.PARAM_RESULT);
            if (jSONObject2.has("to-be-reviewed")) {
                return jSONObject2.getString("to-be-reviewed").equals("yes");
            }
            return false;
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return false;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return false;
        }
    }
}
