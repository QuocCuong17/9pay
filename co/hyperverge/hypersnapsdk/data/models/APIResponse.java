package co.hyperverge.hypersnapsdk.data.models;

import android.util.Log;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class APIResponse {
    private static final String TAG = "co.hyperverge.hypersnapsdk.data.models.APIResponse";
    private JSONObject errorBody;
    private JSONObject responseBody;
    private JSONObject responseHeaders;
    private Integer statusCode;
    private String statusMessage;

    protected boolean canEqual(Object obj) {
        return obj instanceof APIResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof APIResponse)) {
            return false;
        }
        APIResponse aPIResponse = (APIResponse) obj;
        if (!aPIResponse.canEqual(this)) {
            return false;
        }
        Integer statusCode = getStatusCode();
        Integer statusCode2 = aPIResponse.getStatusCode();
        if (statusCode != null ? !statusCode.equals(statusCode2) : statusCode2 != null) {
            return false;
        }
        JSONObject responseBody = getResponseBody();
        JSONObject responseBody2 = aPIResponse.getResponseBody();
        if (responseBody != null ? !responseBody.equals(responseBody2) : responseBody2 != null) {
            return false;
        }
        JSONObject responseHeaders = getResponseHeaders();
        JSONObject responseHeaders2 = aPIResponse.getResponseHeaders();
        if (responseHeaders != null ? !responseHeaders.equals(responseHeaders2) : responseHeaders2 != null) {
            return false;
        }
        JSONObject errorBody = getErrorBody();
        JSONObject errorBody2 = aPIResponse.getErrorBody();
        if (errorBody != null ? !errorBody.equals(errorBody2) : errorBody2 != null) {
            return false;
        }
        String statusMessage = getStatusMessage();
        String statusMessage2 = aPIResponse.getStatusMessage();
        return statusMessage != null ? statusMessage.equals(statusMessage2) : statusMessage2 == null;
    }

    public int hashCode() {
        Integer statusCode = getStatusCode();
        int hashCode = statusCode == null ? 43 : statusCode.hashCode();
        JSONObject responseBody = getResponseBody();
        int hashCode2 = ((hashCode + 59) * 59) + (responseBody == null ? 43 : responseBody.hashCode());
        JSONObject responseHeaders = getResponseHeaders();
        int hashCode3 = (hashCode2 * 59) + (responseHeaders == null ? 43 : responseHeaders.hashCode());
        JSONObject errorBody = getErrorBody();
        int hashCode4 = (hashCode3 * 59) + (errorBody == null ? 43 : errorBody.hashCode());
        String statusMessage = getStatusMessage();
        return (hashCode4 * 59) + (statusMessage != null ? statusMessage.hashCode() : 43);
    }

    public void setErrorBody(JSONObject jSONObject) {
        this.errorBody = jSONObject;
    }

    public void setResponseBody(JSONObject jSONObject) {
        this.responseBody = jSONObject;
    }

    public void setResponseHeaders(JSONObject jSONObject) {
        this.responseHeaders = jSONObject;
    }

    public void setStatusCode(Integer num) {
        this.statusCode = num;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }

    public String toString() {
        return "APIResponse(responseBody=" + getResponseBody() + ", responseHeaders=" + getResponseHeaders() + ", errorBody=" + getErrorBody() + ", statusCode=" + getStatusCode() + ", statusMessage=" + getStatusMessage() + ")";
    }

    public JSONObject getResponseBody() {
        return this.responseBody;
    }

    public JSONObject getResponseHeaders() {
        return this.responseHeaders;
    }

    public JSONObject getErrorBody() {
        return this.errorBody;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public APIResponse(Response<ResponseBody> response) {
        this.statusCode = Integer.valueOf(response.code());
        this.statusMessage = response.message();
        setResponseHeaders(setCustomHeaders(response.headers()));
        try {
            ResponseBody body = response.body();
            try {
                if (body != null) {
                    this.responseBody = new JSONObject(body.string());
                } else {
                    Log.e("responseBody: ", "is null");
                }
                if (body != null) {
                    body.close();
                }
            } catch (Throwable th) {
                if (body != null) {
                    try {
                        body.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
        try {
            ResponseBody errorBody = response.errorBody();
            try {
                if (errorBody != null) {
                    this.errorBody = new JSONObject(errorBody.string());
                } else {
                    Log.e("responseErrorBody: ", "is null");
                }
                if (errorBody != null) {
                    errorBody.close();
                }
            } catch (Throwable th3) {
                if (errorBody != null) {
                    try {
                        errorBody.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (IOException | JSONException e2) {
            Log.e(TAG, Utils.getErrorMessage(e2));
        }
    }

    public void addHeader(String str, String str2) throws JSONException {
        this.responseHeaders.put(str, str2);
    }

    public String getHeaderValue(String str) throws JSONException {
        return this.responseHeaders.getString(str);
    }

    public String getRequestID() throws JSONException {
        String string = this.responseHeaders.has(AppConstants.REQUEST_ID) ? this.responseHeaders.getString(AppConstants.REQUEST_ID) : " ";
        return StringUtils.isEmptyOrNull(string) ? this.responseHeaders.getString(AppConstants.HV_REQUEST_ID) : string;
    }

    public void setRequestSignature(String str) throws JSONException {
        this.responseHeaders.put("X-HV-Request-Signature", str);
    }

    public void addRawResponseBody() throws JSONException {
        this.responseHeaders.put(AppConstants.RAW_RESPONSE, this.responseBody.toString());
    }

    public void addRawErrorBody() throws JSONException {
        this.responseHeaders.put(AppConstants.RAW_RESPONSE, this.errorBody.toString());
    }

    private JSONObject setCustomHeaders(Headers headers) {
        JSONObject jSONObject = new JSONObject();
        Map<String, List<String>> multimap = headers.toMultimap();
        for (String str : headers.names()) {
            try {
                if (str.equalsIgnoreCase("x-request-id") && headers.get(str) != null && !multimap.containsKey(AppConstants.HV_REQUEST_ID)) {
                    jSONObject.put(AppConstants.HV_REQUEST_ID, headers.get(str));
                } else if (str.equalsIgnoreCase("x-response-signature") && headers.get(str) != null && !multimap.containsKey("X-HV-Response-Signature")) {
                    jSONObject.put("X-HV-Response-Signature", headers.get(str));
                } else {
                    jSONObject.put(str, headers.get(str));
                }
            } catch (JSONException e) {
                logError(e);
            }
        }
        return jSONObject;
    }

    private void logError(Exception exc) {
        Log.e(TAG, Utils.getErrorMessage(exc));
        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(exc);
        }
    }
}
