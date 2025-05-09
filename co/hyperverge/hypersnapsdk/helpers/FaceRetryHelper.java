package co.hyperverge.hypersnapsdk.helpers;

import android.util.Log;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.tekartik.sqflite.Constant;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FaceRetryHelper {
    private static final String TAG = "co.hyperverge.hypersnapsdk.helpers.FaceRetryHelper";
    static FaceRetryHelper faceRetryHelper;

    /* loaded from: classes2.dex */
    public interface FaceRetryListener {
        void onResult(boolean z, String str, String str2, HVError hVError);
    }

    public static FaceRetryHelper get() {
        if (faceRetryHelper == null) {
            faceRetryHelper = new FaceRetryHelper();
        }
        return faceRetryHelper;
    }

    public static void destroy() {
        faceRetryHelper = null;
    }

    public JSONObject setLivenessHeaders(HVFaceConfig hVFaceConfig) {
        HVLogUtils.d(TAG, "setLivenessHeaders() called with: faceConfig = [" + hVFaceConfig + "]");
        JSONObject headers = hVFaceConfig.getHeaders();
        try {
            if (!SPHelper.getTransactionID().isEmpty() && !headers.has("transactionId")) {
                headers.put("transactionId", SPHelper.getTransactionID());
            }
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldActivateDeviceBlocklist() && !StringUtils.isEmptyOrNull(null)) {
                headers.put(AppConstants.DEVICE_ID, (Object) null);
            }
            hVFaceConfig.setLivenessAPIHeaders(headers);
        } catch (JSONException e) {
            String str = TAG;
            HVLogUtils.e(str, "setLivenessHeaders(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str, Utils.getErrorMessage(e));
        }
        return headers;
    }

    public JSONObject setLivenessParams(HVFaceConfig hVFaceConfig) {
        HVLogUtils.d(TAG, "setLivenessParams() called with: faceConfig = [" + hVFaceConfig + "]");
        JSONObject livenessParams = hVFaceConfig.getLivenessParams();
        try {
            JSONObject retryCountForKey = SPHelper.getRetryCountForKey(hVFaceConfig.getLivenessEndpoint(), "");
            if (retryCountForKey != null) {
                Iterator<String> keys = retryCountForKey.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        livenessParams.put(next, String.valueOf(retryCountForKey.getInt(next)));
                    } catch (JSONException e) {
                        Log.e(TAG, Utils.getErrorMessage(e));
                    }
                }
            }
            hVFaceConfig.setLivenessAPIParameters(livenessParams);
        } catch (Exception e2) {
            String str = TAG;
            HVLogUtils.e(str, "setLivenessParams(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            Log.e(str, Utils.getErrorMessage(e2));
        }
        return livenessParams;
    }

    public void setRetryLivenessParamsAndHeaders(HVFaceConfig hVFaceConfig) {
        HVLogUtils.d(TAG, "setRetryLivenessParamsAndHeaders() called with: faceConfig = [" + hVFaceConfig + "]");
        setLivenessHeaders(hVFaceConfig);
        setLivenessParams(hVFaceConfig);
    }

    public void checkForRetakeMessage(JSONObject jSONObject, HVFaceConfig hVFaceConfig, FaceRetryListener faceRetryListener) {
        HVLogUtils.d(TAG, "checkForRetakeMessage() called with: result = [" + jSONObject + "], faceConfig = [" + hVFaceConfig + "], listener = [" + faceRetryListener + "]");
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Constant.PARAM_RESULT);
            if (jSONObject2.has("summary")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("summary");
                String string = jSONObject3.getString("action");
                String str = AppConstants.RETAKE_DEFAULT_MESSAGE;
                String mapperForUrl = SPHelper.setMapperForUrl(hVFaceConfig.getLivenessEndpoint(), "");
                if (jSONObject3.has(AppConstants.RETAKE_MESSAGE)) {
                    str = jSONObject3.getString(AppConstants.RETAKE_MESSAGE);
                }
                if (jSONObject3.has("details")) {
                    JSONArray jSONArray = jSONObject3.getJSONArray("details");
                    if (jSONArray.length() > 0) {
                        JSONObject jSONObject4 = jSONArray.getJSONObject(0);
                        if (jSONObject4.has("code")) {
                            String string2 = jSONObject4.getString("code");
                            try {
                                HVJSONObject customUIStrings = hVFaceConfig.getCustomUIStrings();
                                if (customUIStrings.hasAndNotEmpty(string2)) {
                                    str = customUIStrings.getString(string2);
                                }
                            } catch (JSONException e) {
                                Log.e(TAG, Utils.getErrorMessage(e));
                                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                                }
                            }
                        }
                    }
                }
                if (!hVFaceConfig.isShouldHandleRetries()) {
                    faceRetryListener.onResult(false, str, string, null);
                    return;
                }
                if (!string.equalsIgnoreCase("retake")) {
                    faceRetryListener.onResult(false, "", string, null);
                    return;
                } else if (mapperForUrl != null) {
                    faceRetryListener.onResult(true, str, string, null);
                    return;
                } else {
                    faceRetryListener.onResult(false, str, string, getErrorForEmptyTransactionID());
                    return;
                }
            }
            faceRetryListener.onResult(false, "", "", null);
        } catch (JSONException e2) {
            String str2 = TAG;
            HVLogUtils.e(str2, "checkForRetakeMessage(): exception = [" + Utils.getErrorMessage(e2) + "]", e2);
            Log.e(str2, Utils.getErrorMessage(e2));
            faceRetryListener.onResult(false, "", "", null);
        }
    }

    @Deprecated
    public String getAdvertisingID() {
        String advertisingID = AdvertisingIDHelper.get().getAdvertisingID();
        HVLogUtils.d(TAG, "getAdvertisingID() returned: " + advertisingID);
        return advertisingID;
    }

    public HVError getErrorForEmptyTransactionID() {
        HVLogUtils.d(TAG, "getErrorForEmptyTransactionID() called");
        return new HVError(17, AppConstants.EMPTY_TRANSACTION_ERROR);
    }
}
