package co.hyperverge.hypersnapsdk.helpers;

import android.content.Context;
import android.util.Log;
import co.hyperverge.hypersnapsdk.listeners.APICompletionCallback;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.network.HVNetworkHelper;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
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
public class DocOCRHelper {
    public static final String TAG = "co.hyperverge.hypersnapsdk.helpers.DocOCRHelper";
    private static DocOCRHelper docOCRHelper;
    String action;
    HVDocConfig hvdocConfig;
    DocOCRListener listener;
    String message;
    JSONObject params = new JSONObject();
    JSONObject requestHeaders = new JSONObject();
    JSONObject retryObj;
    String summry;

    /* loaded from: classes2.dex */
    public interface DocOCRListener {
        void onResult(boolean z, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, HVError hVError);
    }

    public static DocOCRHelper get() {
        if (docOCRHelper == null) {
            docOCRHelper = new DocOCRHelper();
        }
        return docOCRHelper;
    }

    public static void destroy() {
        HVLogUtils.d(TAG, "destroy() called");
        docOCRHelper = null;
    }

    public void makeOcrAPICall(Context context, String str, String str2, HVDocConfig hVDocConfig, final DocOCRListener docOCRListener) {
        HVLogUtils.d(TAG, "makeOcrAPICall() called with: context = [" + context + "], documentURI = [" + str + "], qrCodeCroppedImageUri = [" + str2 + "], docConfig = [" + hVDocConfig + "], ocrListener = [" + docOCRListener + "]");
        this.params = hVDocConfig.getOcrParams();
        this.requestHeaders = hVDocConfig.getOcrHeaders();
        this.hvdocConfig = hVDocConfig;
        this.listener = docOCRListener;
        try {
            this.params.put("expectedDocumentSide", hVDocConfig.getSuffixForDocument());
            if (!SPHelper.getTransactionID().isEmpty() && !this.requestHeaders.has("transactionId")) {
                this.requestHeaders.put("transactionId", SPHelper.getTransactionID());
            }
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
        generateRetryObj(this.hvdocConfig.ocrEndpoint, this.hvdocConfig.getSuffixForDocument());
        new TimingUtils();
        APICompletionCallback aPICompletionCallback = new APICompletionCallback() { // from class: co.hyperverge.hypersnapsdk.helpers.DocOCRHelper.1
            @Override // co.hyperverge.hypersnapsdk.listeners.APICompletionCallback
            public void onResult(HVError hVError, HVResponse hVResponse) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (hVError != null && (hVError.getErrorCode() == 18 || hVError.getErrorCode() == 15 || hVError.getErrorCode() == 12)) {
                    docOCRListener.onResult(false, DocOCRHelper.this.message, null, null, jSONObject2, hVError);
                    return;
                }
                if (hVResponse != null) {
                    jSONObject = hVResponse.getApiResult();
                    jSONObject2 = hVResponse.getApiHeaders();
                }
                JSONObject jSONObject3 = jSONObject;
                JSONObject jSONObject4 = jSONObject2;
                try {
                    if (jSONObject3 == null) {
                        docOCRListener.onResult(false, AppConstants.RETAKE_DEFAULT_MESSAGE, null, null, jSONObject4, hVError);
                    } else {
                        DocOCRHelper.this.processAPIResult(jSONObject3.getJSONObject(Constant.PARAM_RESULT), jSONObject3, jSONObject4, hVError);
                    }
                } catch (Exception e2) {
                    Log.e(DocOCRHelper.TAG, Utils.getErrorMessage(e2));
                    docOCRListener.onResult(false, "", null, jSONObject3, jSONObject4, null);
                }
            }
        };
        if (this.hvdocConfig.isShouldReadNIDQR() && str2 != null) {
            HVNetworkHelper.makeOCRCallWithQR(context, hVDocConfig.ocrEndpoint, str, str2, hVDocConfig, this.params, this.requestHeaders, aPICompletionCallback);
        } else {
            HVNetworkHelper.makeOCRCall(context, hVDocConfig.ocrEndpoint, str, this.params, this.requestHeaders, hVDocConfig.getAllowedStatusCodes(), aPICompletionCallback);
        }
    }

    public JSONObject generateRetryObj(String str, String str2) {
        HVLogUtils.d(TAG, "generateRetryObj() called with: url = [" + str + "], suffix = [" + str2 + "]");
        JSONObject retryCountForKey = SPHelper.getRetryCountForKey(str, str2);
        this.retryObj = retryCountForKey;
        if (retryCountForKey != null) {
            Iterator<String> keys = retryCountForKey.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    this.params.put(next, String.valueOf(this.retryObj.getInt(next)));
                } catch (JSONException e) {
                    Log.e(TAG, Utils.getErrorMessage(e));
                }
            }
        }
        return this.params;
    }

    public void processAPIResult(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, HVError hVError) {
        HVLogUtils.d(TAG, "processAPIResult() called with: results = [" + jSONObject + "], apiResponse = [" + jSONObject2 + "], responseHeaders = [" + jSONObject3 + "], error = [" + hVError + "]");
        try {
            if (jSONObject.has("summary")) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("summary");
                String mapperForUrl = SPHelper.setMapperForUrl(this.hvdocConfig.ocrEndpoint, this.hvdocConfig.getSuffixForDocument());
                if (jSONObject4.has("action")) {
                    this.action = jSONObject4.getString("action");
                }
                if (jSONObject4.has(AppConstants.RETAKE_MESSAGE)) {
                    this.message = jSONObject4.getString(AppConstants.RETAKE_MESSAGE);
                }
                if (!this.hvdocConfig.isShouldEnableRetries()) {
                    this.listener.onResult(false, this.message, this.action, jSONObject2, jSONObject3, null);
                    return;
                }
                if (jSONObject4.has("details")) {
                    JSONArray jSONArray = jSONObject4.getJSONArray("details");
                    if (jSONArray.length() > 0) {
                        JSONObject jSONObject5 = jSONArray.getJSONObject(0);
                        if (jSONObject5.has("code")) {
                            String string = jSONObject5.getString("code");
                            try {
                                HVJSONObject customUIStrings = this.hvdocConfig.getCustomUIStrings();
                                if (customUIStrings.hasAndNotEmpty(string)) {
                                    this.message = customUIStrings.getString(string);
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
                if (!this.action.equalsIgnoreCase("retake")) {
                    this.listener.onResult(false, this.message, this.action, jSONObject2, jSONObject3, null);
                    return;
                } else if (mapperForUrl != null) {
                    this.listener.onResult(true, this.message, this.action, jSONObject2, jSONObject3, null);
                    return;
                } else {
                    this.listener.onResult(false, this.message, this.action, jSONObject2, jSONObject3, getErrorForEmptyTransactionID());
                    return;
                }
            }
            this.listener.onResult(false, this.message, this.action, jSONObject2, jSONObject3, hVError);
        } catch (Exception e2) {
            if (StringUtils.isEmptyOrNull(Utils.getErrorMessage(e2))) {
                return;
            }
            Log.e(TAG, Utils.getErrorMessage(e2));
        }
    }

    public HVError getErrorForEmptyTransactionID() {
        HVLogUtils.d(TAG, "getErrorForEmptyTransactionID() called");
        return new HVError(17, AppConstants.EMPTY_TRANSACTION_ERROR);
    }
}
