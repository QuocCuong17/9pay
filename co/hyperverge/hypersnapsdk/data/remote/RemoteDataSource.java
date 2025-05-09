package co.hyperverge.hypersnapsdk.data.remote;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import androidx.webkit.internal.AssetHelper;
import co.hyperverge.hyperkyc.data.models.HyperKycConfig;
import co.hyperverge.hyperkyc.data.models.WorkflowAPIHeaders;
import co.hyperverge.hypersnapsdk.BuildConfig;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.Keys;
import co.hyperverge.hypersnapsdk.data.DataSource;
import co.hyperverge.hypersnapsdk.data.models.APIResponse;
import co.hyperverge.hypersnapsdk.helpers.FileHelper;
import co.hyperverge.hypersnapsdk.helpers.ImageComparisonHelper;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.helpers.TimingUtils;
import co.hyperverge.hypersnapsdk.listeners.APICompletionCallback;
import co.hyperverge.hypersnapsdk.model.GestureResponse;
import co.hyperverge.hypersnapsdk.model.LivenessResponse;
import co.hyperverge.hypersnapsdk.objects.HVBaseConfig;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.objects.HyperSnapSDKConfig;
import co.hyperverge.hypersnapsdk.service.security.GKYCSignatureVerify;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.HVLogUtils;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.beust.jcommander.Parameters;
import com.google.gson.Gson;
import com.tekartik.sqflite.Constant;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class RemoteDataSource extends DataSource {
    private static final String NETWORK_ERROR_OCCURRED = "Network error occurred";
    private static final String TAG = "co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource";
    private static RemoteDataSource instance;

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }

    public static boolean checkForDataLogging(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "checkForDataLogging() called with: parameters = [" + jSONObject + "]");
        if (jSONObject == null || !jSONObject.has("dataLogging")) {
            return false;
        }
        try {
            return jSONObject.getString("dataLogging").equals("yes");
        } catch (JSONException e) {
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return false;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return false;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(38:1|(1:5)|6|7|8|(3:10|(1:12)(1:14)|13)|15|(5:17|18|(4:20|21|22|23)(1:112)|24|(1:26))(1:114)|27|(1:106)|31|32|(1:34)(1:105)|35|(2:39|40)|46|(1:48)|49|50|(1:52)(1:102)|53|54|(1:56)|57|(1:100)(1:61)|62|(1:66)|67|(1:(1:70)(8:98|72|73|(1:78)|80|(1:93)(1:83)|84|(1:91)(2:88|89)))(1:99)|71|72|73|(2:76|78)|80|(0)|93|84|(2:86|91)(1:92)) */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0313, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0314, code lost:
    
        r1 = co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.TAG;
        co.hyperverge.hypersnapsdk.utils.HVLogUtils.e(r1, "getLivenessTextureScore(): exception = [" + co.hyperverge.hypersnapsdk.utils.Utils.getErrorMessage(r0) + "]", r0);
        android.util.Log.e(r1, co.hyperverge.hypersnapsdk.utils.Utils.getErrorMessage(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x033e, code lost:
    
        if (co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig.getInstance().getErrorMonitoringService() != null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0340, code lost:
    
        co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0307 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0355 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02f4  */
    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void getLivenessTextureScore(String str, String str2, String str3, List<Integer> list, final HVFaceConfig hVFaceConfig, final DataSource.NetworkCallback networkCallback) {
        HashMap hashMap;
        String str4;
        MultipartBody.Part part;
        final Map<String, String> map;
        final boolean isShouldUseSignature;
        String uniqueIdentifierIfNeeded;
        final String str5;
        Map<String, RequestBody> mapFromJson;
        final JSONObject jSONObject;
        final HashMap hashMap2;
        Call<ResponseBody> uploadImage;
        HVLogUtils.d(TAG, "getLivenessTextureScore() called with: filePath = [" + str + "], croppedFacePath = [" + str2 + "], videoUri = [" + str3 + "], faceCoords = [" + list + "], hvFaceConfig = [" + hVFaceConfig + "], callback = [" + networkCallback + "]");
        File file = new File(str);
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        HashMap hashMap3 = new HashMap();
        hashMap3.put("image", str);
        if (str3 != null && !str3.endsWith("hv_dummy_video.mp4")) {
            hashMap3.put("video", str3);
        }
        JSONObject livenessParams = hVFaceConfig.getLivenessParams();
        try {
            if (hVFaceConfig.isShouldUseDefaultZoom()) {
                livenessParams.put("zoom-factor", String.valueOf(hVFaceConfig.getShouldUseBackCamera() ? AppConstants.ZOOM_FACTOR_BACK_CAM : AppConstants.ZOOM_FACTOR_FRONT_CAM));
            }
            if (SDKInternalConfig.getInstance() != null) {
                livenessParams.put("face-detection-on", String.valueOf(SDKInternalConfig.getInstance().isFaceDetectionOn()));
                livenessParams.put("face-detection-processor", SDKInternalConfig.getInstance().getFaceDetectionProcessor().name());
                if (AppConstants.mlkitMissing.isEmpty()) {
                    hashMap = hashMap3;
                } else {
                    hashMap = hashMap3;
                    try {
                        livenessParams.put("mlkit-missing", AppConstants.mlkitMissing);
                        AppConstants.mlkitMissing = "";
                    } catch (JSONException e) {
                        e = e;
                        String str6 = TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("getLivenessTextureScore(): exception = [");
                        str4 = "no";
                        sb.append(Utils.getErrorMessage(e));
                        sb.append("]");
                        HVLogUtils.e(str6, sb.toString(), e);
                        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                        }
                        if (str3 == null) {
                        }
                        if (list != null) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("x1", list.get(0));
                                jSONObject2.put("y1", list.get(1));
                                jSONObject2.put("x2", list.get(2));
                                jSONObject2.put("y2", list.get(3));
                                livenessParams.put("face-coordinates", jSONObject2.toString());
                            } catch (Exception e2) {
                                String str7 = TAG;
                                HVLogUtils.e(str7, "getLivenessTextureScore(): face-coordinates exception = [" + Utils.getErrorMessage(e2) + "]", e2);
                                Log.e(str7, Utils.getErrorMessage(e2));
                                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
                                }
                            }
                        }
                        ImageComparisonHelper.get().addRequestBodyParams(livenessParams, hVFaceConfig);
                        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics()) {
                        }
                        livenessParams.put("isBackCamera", !hVFaceConfig.getShouldUseBackCamera() ? "yes" : str4);
                        new HashMap();
                        JSONObject jSONObject3 = new JSONObject();
                        if (hVFaceConfig.getHeaders() != null) {
                        }
                        JSONObject addHeaderParams = addHeaderParams(jSONObject3);
                        map = (Map) new Gson().fromJson(addHeaderParams.toString(), HashMap.class);
                        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() == null) {
                        }
                        map.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
                        map.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
                        isShouldUseSignature = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature();
                        if (!isShouldUseSignature) {
                            HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(true);
                        }
                        if (hVFaceConfig.isUseBothImagesSignature()) {
                        }
                        str5 = uniqueIdentifierIfNeeded;
                        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSHA256Signature()) {
                        }
                        mapFromJson = getMapFromJson(livenessParams);
                        if (!hVFaceConfig.isShouldRecordVideo()) {
                        }
                        jSONObject = livenessParams;
                        hashMap2 = hashMap;
                        uploadImage = ApiClient.getService().uploadImage(hVFaceConfig.getLivenessEndpoint(), map, createFormData, mapFromJson);
                        uploadImage.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.1
                            @Override // retrofit2.Callback
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String str8;
                                HVLogUtils.d(RemoteDataSource.TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                                APIResponse aPIResponse = new APIResponse(response);
                                LivenessResponse livenessResponse = new LivenessResponse();
                                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str8 = str5) != null) {
                                    try {
                                        aPIResponse.setRequestSignature(str8);
                                    } catch (JSONException e3) {
                                        HVLogUtils.e(RemoteDataSource.TAG, "getLivenessTextureScore(): onResponse exception = [" + Utils.getErrorMessage(e3) + "]", e3);
                                        RemoteDataSource.this.logError(e3);
                                    }
                                }
                                RemoteDataSource remoteDataSource = RemoteDataSource.this;
                                boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, str5, null, hashMap2, jSONObject, remoteDataSource.convertMapToJson(map), false, hVFaceConfig.getAllowedStatusCodes());
                                if (!isSignatureVerfied) {
                                    livenessResponse.setLivenessError(AppConstants.SIGNATURE_ERROR);
                                    livenessResponse.setErrorCode(18);
                                }
                                livenessResponse.setHttpStatusCode(aPIResponse.getStatusCode());
                                livenessResponse.setStatusMessage(aPIResponse.getStatusMessage());
                                livenessResponse.setHeaders(aPIResponse.getResponseHeaders());
                                JSONObject responseBody = aPIResponse.getResponseBody();
                                boolean isShouldReturnRawResponse = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse();
                                if (response.isSuccessful()) {
                                    if (responseBody != null) {
                                        if (isShouldReturnRawResponse) {
                                            try {
                                                aPIResponse.addRawResponseBody();
                                            } catch (JSONException e4) {
                                                RemoteDataSource.this.logError(e4);
                                            }
                                        }
                                        if (isSignatureVerfied) {
                                            livenessResponse.setResponse(responseBody);
                                        }
                                    }
                                } else if (isSignatureVerfied && aPIResponse.getErrorBody() != null) {
                                    if (isShouldReturnRawResponse) {
                                        try {
                                            aPIResponse.addRawErrorBody();
                                        } catch (JSONException e5) {
                                            RemoteDataSource.this.logError(e5);
                                        }
                                    }
                                    RemoteDataSource.this.setLivenessResponseForSummary(livenessResponse, aPIResponse);
                                }
                                HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(isShouldUseSignature);
                                networkCallback.onSuccess(livenessResponse);
                            }

                            @Override // retrofit2.Callback
                            public void onFailure(Call<ResponseBody> call, Throwable th) {
                                HVLogUtils.d(RemoteDataSource.TAG, "getLivenessTextureScore(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                                HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(isShouldUseSignature);
                                DataSource.NetworkCallback networkCallback2 = networkCallback;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Network error occurred url: ");
                                sb2.append(hVFaceConfig.getLivenessEndpoint());
                                networkCallback2.onFailure(12, Utils.getLocalizedErrorMessage(th, sb2.toString()));
                            }
                        });
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        }
                    }
                }
                if (!AppConstants.mlkitUnavailableError.isEmpty()) {
                    livenessParams.put("mlkit-unavailable", AppConstants.mlkitUnavailableError);
                    AppConstants.mlkitUnavailableError = "";
                }
            } else {
                hashMap = hashMap3;
            }
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getHyperSnapRegion() == HyperSnapParams.Region.ASIA_PACIFIC || HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getHyperSnapRegion() == HyperSnapParams.Region.AsiaPacific) {
                livenessParams.put("validateFaceSize", "no");
            }
            str4 = "no";
        } catch (JSONException e3) {
            e = e3;
            hashMap = hashMap3;
        }
        if (str3 == null) {
            File file2 = new File(str3);
            part = MultipartBody.Part.createFormData("video", file2.getName(), RequestBody.create(MediaType.parse(MimeTypes.VIDEO_MP4), file2));
        } else {
            part = null;
        }
        if (list != null && !list.isEmpty()) {
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put("x1", list.get(0));
            jSONObject22.put("y1", list.get(1));
            jSONObject22.put("x2", list.get(2));
            jSONObject22.put("y2", list.get(3));
            livenessParams.put("face-coordinates", jSONObject22.toString());
        }
        ImageComparisonHelper.get().addRequestBodyParams(livenessParams, hVFaceConfig);
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSensorBiometrics()) {
            SDKInternalConfig.getInstance().getHvSensorBiometrics().addSensorDataToRequestBody(livenessParams);
        }
        try {
            livenessParams.put("isBackCamera", !hVFaceConfig.getShouldUseBackCamera() ? "yes" : str4);
        } catch (JSONException e4) {
            String str8 = TAG;
            HVLogUtils.e(str8, "getLivenessTextureScore(): exception = [" + Utils.getErrorMessage(e4) + "]", e4);
            Log.e(str8, Utils.getErrorMessage(e4));
        }
        new HashMap();
        JSONObject jSONObject32 = new JSONObject();
        if (hVFaceConfig.getHeaders() != null) {
            jSONObject32 = hVFaceConfig.getHeaders();
        }
        JSONObject addHeaderParams2 = addHeaderParams(jSONObject32);
        map = (Map) new Gson().fromJson(addHeaderParams2.toString(), HashMap.class);
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() == null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            map.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            map.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            map.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        isShouldUseSignature = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature();
        if (!isShouldUseSignature && isApac(hVFaceConfig.getLivenessEndpoint())) {
            HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(true);
        }
        if (hVFaceConfig.isUseBothImagesSignature()) {
            uniqueIdentifierIfNeeded = SignatureHelper.getUniqueIdentifierIfNeeded(str, addHeaderParams2);
        } else if (str2 != null) {
            uniqueIdentifierIfNeeded = SignatureHelper.getUniqueIdentifierIfNeeded(str, str2, addHeaderParams2);
        } else {
            str5 = null;
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSHA256Signature() && str5 != null && !map.containsKey("uuid")) {
                map.put("uuid", str5);
            }
            mapFromJson = getMapFromJson(livenessParams);
            if (!hVFaceConfig.isShouldRecordVideo() && str3 != null) {
                jSONObject = livenessParams;
                hashMap2 = hashMap;
                uploadImage = ApiClient.getService().uploadImageAndVideo(hVFaceConfig.getLivenessEndpoint(), map, createFormData, part, mapFromJson);
            } else {
                jSONObject = livenessParams;
                hashMap2 = hashMap;
                uploadImage = ApiClient.getService().uploadImage(hVFaceConfig.getLivenessEndpoint(), map, createFormData, mapFromJson);
            }
            uploadImage.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.1
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String str82;
                    HVLogUtils.d(RemoteDataSource.TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    APIResponse aPIResponse = new APIResponse(response);
                    LivenessResponse livenessResponse = new LivenessResponse();
                    if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str82 = str5) != null) {
                        try {
                            aPIResponse.setRequestSignature(str82);
                        } catch (JSONException e32) {
                            HVLogUtils.e(RemoteDataSource.TAG, "getLivenessTextureScore(): onResponse exception = [" + Utils.getErrorMessage(e32) + "]", e32);
                            RemoteDataSource.this.logError(e32);
                        }
                    }
                    RemoteDataSource remoteDataSource = RemoteDataSource.this;
                    boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, str5, null, hashMap2, jSONObject, remoteDataSource.convertMapToJson(map), false, hVFaceConfig.getAllowedStatusCodes());
                    if (!isSignatureVerfied) {
                        livenessResponse.setLivenessError(AppConstants.SIGNATURE_ERROR);
                        livenessResponse.setErrorCode(18);
                    }
                    livenessResponse.setHttpStatusCode(aPIResponse.getStatusCode());
                    livenessResponse.setStatusMessage(aPIResponse.getStatusMessage());
                    livenessResponse.setHeaders(aPIResponse.getResponseHeaders());
                    JSONObject responseBody = aPIResponse.getResponseBody();
                    boolean isShouldReturnRawResponse = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse();
                    if (response.isSuccessful()) {
                        if (responseBody != null) {
                            if (isShouldReturnRawResponse) {
                                try {
                                    aPIResponse.addRawResponseBody();
                                } catch (JSONException e42) {
                                    RemoteDataSource.this.logError(e42);
                                }
                            }
                            if (isSignatureVerfied) {
                                livenessResponse.setResponse(responseBody);
                            }
                        }
                    } else if (isSignatureVerfied && aPIResponse.getErrorBody() != null) {
                        if (isShouldReturnRawResponse) {
                            try {
                                aPIResponse.addRawErrorBody();
                            } catch (JSONException e5) {
                                RemoteDataSource.this.logError(e5);
                            }
                        }
                        RemoteDataSource.this.setLivenessResponseForSummary(livenessResponse, aPIResponse);
                    }
                    HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(isShouldUseSignature);
                    networkCallback.onSuccess(livenessResponse);
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseBody> call, Throwable th) {
                    HVLogUtils.d(RemoteDataSource.TAG, "getLivenessTextureScore(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                    HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(isShouldUseSignature);
                    DataSource.NetworkCallback networkCallback2 = networkCallback;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Network error occurred url: ");
                    sb2.append(hVFaceConfig.getLivenessEndpoint());
                    networkCallback2.onFailure(12, Utils.getLocalizedErrorMessage(th, sb2.toString()));
                }
            });
            if (!SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser() || SDKInternalConfig.getInstance().getAnalyticsTrackerService() == null) {
            }
            SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSelfieAPIPost(hVFaceConfig.getLivenessEndpoint(), str);
            return;
        }
        str5 = uniqueIdentifierIfNeeded;
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSHA256Signature()) {
            map.put("uuid", str5);
        }
        mapFromJson = getMapFromJson(livenessParams);
        if (!hVFaceConfig.isShouldRecordVideo()) {
        }
        jSONObject = livenessParams;
        hashMap2 = hashMap;
        uploadImage = ApiClient.getService().uploadImage(hVFaceConfig.getLivenessEndpoint(), map, createFormData, mapFromJson);
        uploadImage.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.1
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str82;
                HVLogUtils.d(RemoteDataSource.TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                LivenessResponse livenessResponse = new LivenessResponse();
                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str82 = str5) != null) {
                    try {
                        aPIResponse.setRequestSignature(str82);
                    } catch (JSONException e32) {
                        HVLogUtils.e(RemoteDataSource.TAG, "getLivenessTextureScore(): onResponse exception = [" + Utils.getErrorMessage(e32) + "]", e32);
                        RemoteDataSource.this.logError(e32);
                    }
                }
                RemoteDataSource remoteDataSource = RemoteDataSource.this;
                boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, str5, null, hashMap2, jSONObject, remoteDataSource.convertMapToJson(map), false, hVFaceConfig.getAllowedStatusCodes());
                if (!isSignatureVerfied) {
                    livenessResponse.setLivenessError(AppConstants.SIGNATURE_ERROR);
                    livenessResponse.setErrorCode(18);
                }
                livenessResponse.setHttpStatusCode(aPIResponse.getStatusCode());
                livenessResponse.setStatusMessage(aPIResponse.getStatusMessage());
                livenessResponse.setHeaders(aPIResponse.getResponseHeaders());
                JSONObject responseBody = aPIResponse.getResponseBody();
                boolean isShouldReturnRawResponse = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse();
                if (response.isSuccessful()) {
                    if (responseBody != null) {
                        if (isShouldReturnRawResponse) {
                            try {
                                aPIResponse.addRawResponseBody();
                            } catch (JSONException e42) {
                                RemoteDataSource.this.logError(e42);
                            }
                        }
                        if (isSignatureVerfied) {
                            livenessResponse.setResponse(responseBody);
                        }
                    }
                } else if (isSignatureVerfied && aPIResponse.getErrorBody() != null) {
                    if (isShouldReturnRawResponse) {
                        try {
                            aPIResponse.addRawErrorBody();
                        } catch (JSONException e5) {
                            RemoteDataSource.this.logError(e5);
                        }
                    }
                    RemoteDataSource.this.setLivenessResponseForSummary(livenessResponse, aPIResponse);
                }
                HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(isShouldUseSignature);
                networkCallback.onSuccess(livenessResponse);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "getLivenessTextureScore(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                HyperSnapSDK.getInstance().getHyperSnapSDKConfig().setShouldUseSignature(isShouldUseSignature);
                DataSource.NetworkCallback networkCallback2 = networkCallback;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Network error occurred url: ");
                sb2.append(hVFaceConfig.getLivenessEndpoint());
                networkCallback2.onFailure(12, Utils.getLocalizedErrorMessage(th, sb2.toString()));
            }
        });
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
        }
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void verifyPair(String str, String str2, final DataSource.NetworkCallback networkCallback) {
        HVLogUtils.d(TAG, "verifyPair() called with: imageFilePath1 = [" + str + "], imageFilePath2 = [" + str2 + "], callback = [" + networkCallback + "]");
        File file = new File(str);
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("image1", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        File file2 = new File(str2);
        ApiClient.getDocsService().verifyPair(SDKInternalConfig.getInstance().getVerifyPairUri(), HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId(), HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey(), createFormData, MultipartBody.Part.createFormData("image2", file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2)), RequestBody.create(MediaType.parse(AssetHelper.DEFAULT_MIME_TYPE), "selfie")).enqueue(new Callback<GestureResponse>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.2
            @Override // retrofit2.Callback
            public void onResponse(Call<GestureResponse> call, Response<GestureResponse> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "verifyPair(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                GestureResponse body = response.body();
                body.setHttpStatusCode(Integer.valueOf(response.code()));
                body.setStatusMessage(response.message());
                networkCallback.onSuccess(body);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<GestureResponse> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "verifyPair(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                networkCallback.onFailure(12, Utils.getLocalizedErrorMessage(th, RemoteDataSource.NETWORK_ERROR_OCCURRED));
            }
        });
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void makeOcrCall(final Context context, final String str, final String str2, String str3, HVDocConfig hVDocConfig, final JSONObject jSONObject, JSONObject jSONObject2, final List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        String str4;
        MultipartBody.Part part;
        Call<ResponseBody> ocrCall;
        HVLogUtils.d(TAG, "makeOcrCall() called with: context = [" + context + "], endpoint = [" + str + "], documentUri = [" + str2 + "], qrCodeCroppedImageUri = [" + str3 + "], hvDocConfig = [" + hVDocConfig + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        if (aPICompletionCallback == null) {
            return;
        }
        if (context == null) {
            aPICompletionCallback.onResult(new HVError(6, "Context object is null"), null);
            return;
        }
        final HashMap hashMap = new HashMap();
        if (str2 == null || (str2 != null && (str2.trim().isEmpty() || !new File(str2).exists()))) {
            aPICompletionCallback.onResult(new HVError(6, "Document file path is null"), null);
            return;
        }
        File file = new File(str2);
        String str5 = "pdf";
        if (str2.contains(".pdf")) {
            hashMap.put("pdf", str2);
            str4 = "application/pdf";
        } else {
            hashMap.put("image", str2);
            str5 = "image";
            str4 = "image/jpeg";
        }
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData(str5, file.getName(), RequestBody.create(MediaType.parse(str4), file));
        if (!StringUtils.isEmptyOrNull(str3)) {
            hashMap.put("qrCroppedImage", str3);
        }
        if (hVDocConfig == null || !hVDocConfig.isShouldReadNIDQR() || StringUtils.isEmptyOrNull(str3)) {
            part = null;
        } else {
            File file2 = new File(str3);
            part = MultipartBody.Part.createFormData("qrCroppedImage", file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
        }
        JSONObject addHeaderParams = addHeaderParams(jSONObject2 == null ? new JSONObject() : jSONObject2);
        final String uniqueIdentifierIfNeeded = SignatureHelper.getUniqueIdentifierIfNeeded(str2, addHeaderParams);
        try {
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSHA256Signature() && uniqueIdentifierIfNeeded != null && !addHeaderParams.has("uuid")) {
                addHeaderParams.put("uuid", uniqueIdentifierIfNeeded);
            }
        } catch (JSONException e) {
            String str6 = TAG;
            HVLogUtils.e(str6, "makeOcrCall(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str6, Utils.getErrorMessage(e));
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
        }
        checkForDataLogging(jSONObject);
        Map<String, RequestBody> mapFromJson = getMapFromJson(jSONObject);
        Map<String, String> hashMap2 = new HashMap<>();
        if (addHeaderParams != null) {
            hashMap2 = (Map) new Gson().fromJson(addHeaderParams.toString(), HashMap.class);
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            hashMap2.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            hashMap2.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap2.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        if (hVDocConfig != null && hVDocConfig.isShouldReadNIDQR() && shouldSendQRCodeToOCREndpoint(str) && part != null) {
            ocrCall = ApiClient.getService().ocrPlusQRCall(str, hashMap2, createFormData, part, mapFromJson);
        } else {
            ocrCall = ApiClient.getService().ocrCall(str, hashMap2, createFormData, mapFromJson);
        }
        getReferenceId(addHeaderParams);
        final TimingUtils timingUtils = new TimingUtils();
        final Map<String, String> map = hashMap2;
        ocrCall.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.3
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str7;
                HVLogUtils.d(RemoteDataSource.TAG, "makeOcrCall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str7 = uniqueIdentifierIfNeeded) != null) {
                    try {
                        aPIResponse.setRequestSignature(str7);
                    } catch (JSONException e2) {
                        RemoteDataSource.this.logError(e2);
                    }
                }
                RemoteDataSource remoteDataSource = RemoteDataSource.this;
                boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, uniqueIdentifierIfNeeded, null, hashMap, jSONObject, remoteDataSource.convertMapToJson(map), false, list);
                HVError error = !isSignatureVerfied ? RemoteDataSource.this.getError(AppConstants.SIGNATURE_ERROR, 18) : null;
                HVResponse hVResponse = new HVResponse();
                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                hVResponse.setStatusCode(aPIResponse.getStatusCode());
                hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                boolean isShouldReturnRawResponse = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse();
                JSONObject responseBody = aPIResponse.getResponseBody();
                if (!response.isSuccessful()) {
                    HVError hVError = new HVError();
                    if (isSignatureVerfied) {
                        if (aPIResponse.getErrorBody() != null) {
                            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                                try {
                                    aPIResponse.addHeader(AppConstants.RAW_RESPONSE, aPIResponse.getErrorBody().toString());
                                    hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                                } catch (JSONException e3) {
                                    RemoteDataSource.this.logError(e3);
                                }
                            }
                            hVResponse = RemoteDataSource.this.setSummaryDetails(hVResponse, aPIResponse.getErrorBody());
                            error = RemoteDataSource.this.getError(aPIResponse.getErrorBody());
                        } else {
                            error = hVError;
                        }
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPICallFailed(null, error);
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                    return;
                }
                if (responseBody != null) {
                    if (isShouldReturnRawResponse) {
                        try {
                            aPIResponse.addRawResponseBody();
                            hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                        } catch (JSONException e4) {
                            RemoteDataSource.this.logError(e4);
                        }
                    }
                    if (isSignatureVerfied) {
                        hVResponse.setApiResult(responseBody);
                        HVResponse summaryDetails = RemoteDataSource.this.setSummaryDetails(hVResponse, responseBody);
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPIResponseReceived(summaryDetails, str2, timingUtils.getTimeDifferenceLong().longValue());
                        }
                        aPICompletionCallback.onResult(null, summaryDetails);
                        return;
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPICallFailed(null, error);
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVError hVError;
                HVLogUtils.d(RemoteDataSource.TAG, "makeOcrCall(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                Log.d(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                if (th.getLocalizedMessage().contains("Certificate pinning")) {
                    hVError = new HVError(15, "Secure connection could not be established.");
                } else {
                    hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                }
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPICallFailed(null, hVError);
                }
                aPICompletionCallback.onResult(hVError, null);
            }
        });
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPIPost(str, str2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01fe  */
    /* JADX WARN: Type inference failed for: r1v25, types: [java.util.Map] */
    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void makeFaceMatchCall(final Context context, final String str, String str2, String str3, final HyperSnapParams.FaceMatchMode faceMatchMode, JSONObject jSONObject, JSONObject jSONObject2, final List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        MultipartBody.Part part;
        HashMap hashMap;
        HVLogUtils.d(TAG, "makeFaceMatchCall() called with: context = [" + context + "], endpoint = [" + str + "], image1 = [" + str2 + "], image2 = [" + str3 + "], hyperSnapFaceMatchMode = [" + faceMatchMode + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        if (!isValidFaceMatchInputs(context, str, str2, str3, faceMatchMode, aPICompletionCallback)) {
            return;
        }
        final HashMap hashMap2 = new HashMap();
        JSONObject createFaceMatchHeaders = createFaceMatchHeaders(jSONObject2);
        final JSONObject createFaceMatchParams = createFaceMatchParams(jSONObject);
        final String uniqueIdentifierIfNeeded = SignatureHelper.getUniqueIdentifierIfNeeded(str3, str2, createFaceMatchHeaders);
        try {
            if (!StringUtils.isEmptyOrNull(SPHelper.getTransactionID()) && !createFaceMatchHeaders.has("transactionId")) {
                createFaceMatchHeaders.put("transactionId", SPHelper.getTransactionID());
            }
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSHA256Signature() && uniqueIdentifierIfNeeded != null && !createFaceMatchHeaders.has("uuid")) {
                createFaceMatchHeaders.put("uuid", uniqueIdentifierIfNeeded);
            }
        } catch (JSONException e) {
            String str4 = TAG;
            HVLogUtils.e(str4, "makeOcrCall(): exception = [" + Utils.getErrorMessage(e) + "]", e);
            Log.e(str4, Utils.getErrorMessage(e));
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
        }
        int i = AnonymousClass13.$SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode[faceMatchMode.ordinal()];
        String str5 = "image2";
        String str6 = "image1";
        if (i == 1) {
            if (isIndia(str)) {
                str5 = "selfie2";
                str6 = "selfie";
            } else if (isApac(str) || isAfrica(str)) {
                try {
                    createFaceMatchParams.put("type", "id");
                } catch (JSONException e2) {
                    Log.e(TAG, Utils.getErrorMessage(e2));
                    SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e2);
                }
            }
            File file = new File(str2);
            MultipartBody.Part createFormData = MultipartBody.Part.createFormData(str6, file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
            if (faceMatchMode != HyperSnapParams.FaceMatchMode.FACE_IDFACESTRING) {
            }
            part = null;
            hashMap2.put(str6, str2);
            hashMap2.put(str5, str3);
            Map<String, RequestBody> mapFromJson = getMapFromJson(createFaceMatchParams);
            if (createFaceMatchHeaders != null) {
            }
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() == null) {
            }
            hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
            final HashMap hashMap3 = hashMap;
            Call<ResponseBody> verifyPair = ApiClient.getService().verifyPair(str, hashMap, createFormData, part, mapFromJson);
            final TimingUtils timingUtils = new TimingUtils();
            verifyPair.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.4
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String str7;
                    HVLogUtils.d(RemoteDataSource.TAG, "makeOCRCall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    APIResponse aPIResponse = new APIResponse(response);
                    if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str7 = uniqueIdentifierIfNeeded) != null) {
                        try {
                            aPIResponse.setRequestSignature(str7);
                        } catch (JSONException e3) {
                            HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e3) + "]", e3);
                            RemoteDataSource.this.logError(e3);
                        }
                    }
                    RemoteDataSource remoteDataSource = RemoteDataSource.this;
                    boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, uniqueIdentifierIfNeeded, null, hashMap2, createFaceMatchParams, remoteDataSource.convertMapToJson(hashMap3), false, list);
                    HVError error = !isSignatureVerfied ? RemoteDataSource.this.getError(AppConstants.SIGNATURE_ERROR, 18) : null;
                    HVResponse hVResponse = new HVResponse();
                    hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                    hVResponse.setStatusCode(aPIResponse.getStatusCode());
                    hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                    JSONObject responseBody = aPIResponse.getResponseBody();
                    if (!response.isSuccessful()) {
                        HVError hVError = new HVError();
                        if (isSignatureVerfied) {
                            JSONObject errorBody = aPIResponse.getErrorBody();
                            if (errorBody != null) {
                                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                                    try {
                                        aPIResponse.addHeader(AppConstants.RAW_RESPONSE, errorBody.toString());
                                        hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                                    } catch (JSONException e4) {
                                        HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e4) + "]", e4);
                                        RemoteDataSource.this.logError(e4);
                                    }
                                }
                                HVResponse summaryDetails = RemoteDataSource.this.setSummaryDetails(hVResponse, errorBody);
                                hVError = RemoteDataSource.this.getError(errorBody);
                                hVResponse = summaryDetails;
                            }
                            error = hVError;
                        }
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPICallFailed(null, error);
                        }
                        aPICompletionCallback.onResult(error, hVResponse);
                        return;
                    }
                    if (responseBody != null) {
                        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                            try {
                                aPIResponse.addHeader(AppConstants.RAW_RESPONSE, responseBody.toString());
                                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                            } catch (JSONException e5) {
                                HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e5) + "]", e5);
                                RemoteDataSource.this.logError(e5);
                            }
                        }
                        if (isSignatureVerfied) {
                            if (responseBody.has(Constant.PARAM_RESULT)) {
                                try {
                                    JSONObject jSONObject3 = responseBody.getJSONObject(Constant.PARAM_RESULT);
                                    if (jSONObject3 != null && jSONObject3.has("error") && jSONObject3.getString("error").toLowerCase().contains("face not detected")) {
                                        HVError error2 = RemoteDataSource.this.getError(jSONObject3.getString("error"), 22);
                                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, error2, faceMatchMode.toString());
                                        }
                                        aPICompletionCallback.onResult(error2, hVResponse);
                                        return;
                                    }
                                } catch (JSONException e6) {
                                    HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e6) + "]", e6);
                                    RemoteDataSource.this.logError(e6);
                                }
                                hVResponse.setApiResult(responseBody);
                                HVResponse summaryDetails2 = RemoteDataSource.this.setSummaryDetails(hVResponse, responseBody);
                                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPIResponseReceived(summaryDetails2, timingUtils.getTimeDifferenceLong().longValue());
                                }
                                aPICompletionCallback.onResult(null, summaryDetails2);
                                return;
                            }
                            return;
                        }
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, error, faceMatchMode.toString());
                        }
                        aPICompletionCallback.onResult(error, hVResponse);
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseBody> call, Throwable th) {
                    HVError hVError;
                    HVLogUtils.d(RemoteDataSource.TAG, "onFailure() called with: call = [" + call + "], t = [" + th + "]");
                    Log.d(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                    if (th.getLocalizedMessage().contains("Certificate pinning")) {
                        hVError = new HVError(15, "Secure connection error");
                    } else {
                        hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, hVError, faceMatchMode.toString());
                    }
                    aPICompletionCallback.onResult(hVError, null);
                }
            });
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            }
        } else if (i != 2) {
            if (i == 3 || i == 4) {
                try {
                    createFaceMatchParams.put("idFaceString", str3);
                } catch (JSONException e3) {
                    Log.e(TAG, Utils.getErrorMessage(e3));
                    SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e3);
                }
            }
        } else if (!isIndia(str) && (isApac(str) || isAfrica(str))) {
            try {
                createFaceMatchParams.put("type", "selfie");
            } catch (JSONException e4) {
                Log.e(TAG, Utils.getErrorMessage(e4));
                SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e4);
            }
            File file2 = new File(str2);
            MultipartBody.Part createFormData2 = MultipartBody.Part.createFormData(str6, file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
            if (faceMatchMode != HyperSnapParams.FaceMatchMode.FACE_IDFACESTRING || faceMatchMode == HyperSnapParams.FaceMatchMode.FACE_ID_FACE_STRING) {
                part = null;
            } else {
                File file3 = new File(str3);
                part = MultipartBody.Part.createFormData(str5, file3.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file3));
            }
            hashMap2.put(str6, str2);
            hashMap2.put(str5, str3);
            Map<String, RequestBody> mapFromJson2 = getMapFromJson(createFaceMatchParams);
            hashMap = createFaceMatchHeaders != null ? (Map) new Gson().fromJson(createFaceMatchHeaders.toString(), HashMap.class) : new HashMap();
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() == null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
                hashMap.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
            } else {
                hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
                hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
            }
            final Map hashMap32 = hashMap;
            Call<ResponseBody> verifyPair2 = ApiClient.getService().verifyPair(str, hashMap, createFormData2, part, mapFromJson2);
            final TimingUtils timingUtils2 = new TimingUtils();
            verifyPair2.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.4
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String str7;
                    HVLogUtils.d(RemoteDataSource.TAG, "makeOCRCall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    APIResponse aPIResponse = new APIResponse(response);
                    if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str7 = uniqueIdentifierIfNeeded) != null) {
                        try {
                            aPIResponse.setRequestSignature(str7);
                        } catch (JSONException e32) {
                            HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e32) + "]", e32);
                            RemoteDataSource.this.logError(e32);
                        }
                    }
                    RemoteDataSource remoteDataSource = RemoteDataSource.this;
                    boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, uniqueIdentifierIfNeeded, null, hashMap2, createFaceMatchParams, remoteDataSource.convertMapToJson(hashMap32), false, list);
                    HVError error = !isSignatureVerfied ? RemoteDataSource.this.getError(AppConstants.SIGNATURE_ERROR, 18) : null;
                    HVResponse hVResponse = new HVResponse();
                    hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                    hVResponse.setStatusCode(aPIResponse.getStatusCode());
                    hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                    JSONObject responseBody = aPIResponse.getResponseBody();
                    if (!response.isSuccessful()) {
                        HVError hVError = new HVError();
                        if (isSignatureVerfied) {
                            JSONObject errorBody = aPIResponse.getErrorBody();
                            if (errorBody != null) {
                                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                                    try {
                                        aPIResponse.addHeader(AppConstants.RAW_RESPONSE, errorBody.toString());
                                        hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                                    } catch (JSONException e42) {
                                        HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e42) + "]", e42);
                                        RemoteDataSource.this.logError(e42);
                                    }
                                }
                                HVResponse summaryDetails = RemoteDataSource.this.setSummaryDetails(hVResponse, errorBody);
                                hVError = RemoteDataSource.this.getError(errorBody);
                                hVResponse = summaryDetails;
                            }
                            error = hVError;
                        }
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPICallFailed(null, error);
                        }
                        aPICompletionCallback.onResult(error, hVResponse);
                        return;
                    }
                    if (responseBody != null) {
                        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                            try {
                                aPIResponse.addHeader(AppConstants.RAW_RESPONSE, responseBody.toString());
                                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                            } catch (JSONException e5) {
                                HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e5) + "]", e5);
                                RemoteDataSource.this.logError(e5);
                            }
                        }
                        if (isSignatureVerfied) {
                            if (responseBody.has(Constant.PARAM_RESULT)) {
                                try {
                                    JSONObject jSONObject3 = responseBody.getJSONObject(Constant.PARAM_RESULT);
                                    if (jSONObject3 != null && jSONObject3.has("error") && jSONObject3.getString("error").toLowerCase().contains("face not detected")) {
                                        HVError error2 = RemoteDataSource.this.getError(jSONObject3.getString("error"), 22);
                                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, error2, faceMatchMode.toString());
                                        }
                                        aPICompletionCallback.onResult(error2, hVResponse);
                                        return;
                                    }
                                } catch (JSONException e6) {
                                    HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e6) + "]", e6);
                                    RemoteDataSource.this.logError(e6);
                                }
                                hVResponse.setApiResult(responseBody);
                                HVResponse summaryDetails2 = RemoteDataSource.this.setSummaryDetails(hVResponse, responseBody);
                                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPIResponseReceived(summaryDetails2, timingUtils2.getTimeDifferenceLong().longValue());
                                }
                                aPICompletionCallback.onResult(null, summaryDetails2);
                                return;
                            }
                            return;
                        }
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, error, faceMatchMode.toString());
                        }
                        aPICompletionCallback.onResult(error, hVResponse);
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseBody> call, Throwable th) {
                    HVError hVError;
                    HVLogUtils.d(RemoteDataSource.TAG, "onFailure() called with: call = [" + call + "], t = [" + th + "]");
                    Log.d(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                    if (th.getLocalizedMessage().contains("Certificate pinning")) {
                        hVError = new HVError(15, "Secure connection error");
                    } else {
                        hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, hVError, faceMatchMode.toString());
                    }
                    aPICompletionCallback.onResult(hVError, null);
                }
            });
            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPIPost(str, faceMatchMode.toString(), str2, str3);
                return;
            }
            return;
        }
        str6 = "selfie";
        str5 = "id";
        File file22 = new File(str2);
        MultipartBody.Part createFormData22 = MultipartBody.Part.createFormData(str6, file22.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file22));
        if (faceMatchMode != HyperSnapParams.FaceMatchMode.FACE_IDFACESTRING) {
        }
        part = null;
        hashMap2.put(str6, str2);
        hashMap2.put(str5, str3);
        Map<String, RequestBody> mapFromJson22 = getMapFromJson(createFaceMatchParams);
        if (createFaceMatchHeaders != null) {
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() == null) {
        }
        hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
        hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        final Map hashMap322 = hashMap;
        Call<ResponseBody> verifyPair22 = ApiClient.getService().verifyPair(str, hashMap, createFormData22, part, mapFromJson22);
        final TimingUtils timingUtils22 = new TimingUtils();
        verifyPair22.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.4
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str7;
                HVLogUtils.d(RemoteDataSource.TAG, "makeOCRCall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str7 = uniqueIdentifierIfNeeded) != null) {
                    try {
                        aPIResponse.setRequestSignature(str7);
                    } catch (JSONException e32) {
                        HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e32) + "]", e32);
                        RemoteDataSource.this.logError(e32);
                    }
                }
                RemoteDataSource remoteDataSource = RemoteDataSource.this;
                boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, uniqueIdentifierIfNeeded, null, hashMap2, createFaceMatchParams, remoteDataSource.convertMapToJson(hashMap322), false, list);
                HVError error = !isSignatureVerfied ? RemoteDataSource.this.getError(AppConstants.SIGNATURE_ERROR, 18) : null;
                HVResponse hVResponse = new HVResponse();
                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                hVResponse.setStatusCode(aPIResponse.getStatusCode());
                hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                JSONObject responseBody = aPIResponse.getResponseBody();
                if (!response.isSuccessful()) {
                    HVError hVError = new HVError();
                    if (isSignatureVerfied) {
                        JSONObject errorBody = aPIResponse.getErrorBody();
                        if (errorBody != null) {
                            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                                try {
                                    aPIResponse.addHeader(AppConstants.RAW_RESPONSE, errorBody.toString());
                                    hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                                } catch (JSONException e42) {
                                    HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e42) + "]", e42);
                                    RemoteDataSource.this.logError(e42);
                                }
                            }
                            HVResponse summaryDetails = RemoteDataSource.this.setSummaryDetails(hVResponse, errorBody);
                            hVError = RemoteDataSource.this.getError(errorBody);
                            hVResponse = summaryDetails;
                        }
                        error = hVError;
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logDocumentAPICallFailed(null, error);
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                    return;
                }
                if (responseBody != null) {
                    if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse()) {
                        try {
                            aPIResponse.addHeader(AppConstants.RAW_RESPONSE, responseBody.toString());
                            hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                        } catch (JSONException e5) {
                            HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e5) + "]", e5);
                            RemoteDataSource.this.logError(e5);
                        }
                    }
                    if (isSignatureVerfied) {
                        if (responseBody.has(Constant.PARAM_RESULT)) {
                            try {
                                JSONObject jSONObject3 = responseBody.getJSONObject(Constant.PARAM_RESULT);
                                if (jSONObject3 != null && jSONObject3.has("error") && jSONObject3.getString("error").toLowerCase().contains("face not detected")) {
                                    HVError error2 = RemoteDataSource.this.getError(jSONObject3.getString("error"), 22);
                                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, error2, faceMatchMode.toString());
                                    }
                                    aPICompletionCallback.onResult(error2, hVResponse);
                                    return;
                                }
                            } catch (JSONException e6) {
                                HVLogUtils.e(RemoteDataSource.TAG, "makeOCRCall(): onResponse exception = [" + Utils.getErrorMessage(e6) + "]", e6);
                                RemoteDataSource.this.logError(e6);
                            }
                            hVResponse.setApiResult(responseBody);
                            HVResponse summaryDetails2 = RemoteDataSource.this.setSummaryDetails(hVResponse, responseBody);
                            if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                                SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPIResponseReceived(summaryDetails2, timingUtils22.getTimeDifferenceLong().longValue());
                            }
                            aPICompletionCallback.onResult(null, summaryDetails2);
                            return;
                        }
                        return;
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, error, faceMatchMode.toString());
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVError hVError;
                HVLogUtils.d(RemoteDataSource.TAG, "onFailure() called with: call = [" + call + "], t = [" + th + "]");
                Log.d(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                if (th.getLocalizedMessage().contains("Certificate pinning")) {
                    hVError = new HVError(15, "Secure connection error");
                } else {
                    hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                }
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logFaceMatchAPICallFailed(null, hVError, faceMatchMode.toString());
                }
                aPICompletionCallback.onResult(hVError, null);
            }
        });
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource$13, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode;

        static {
            int[] iArr = new int[HyperSnapParams.FaceMatchMode.values().length];
            $SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode = iArr;
            try {
                iArr[HyperSnapParams.FaceMatchMode.FACE_FACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode[HyperSnapParams.FaceMatchMode.FACE_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode[HyperSnapParams.FaceMatchMode.FACE_IDFACESTRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode[HyperSnapParams.FaceMatchMode.FACE_ID_FACE_STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private JSONObject createFaceMatchParams(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "createFaceMatchParams() called with: parameters = [" + jSONObject + "]");
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    private JSONObject createFaceMatchHeaders(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "createFaceMatchHeaders() called with: headers = [" + jSONObject + "]");
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return addHeaderParams(jSONObject);
    }

    private boolean isValidFaceMatchInputs(Context context, String str, String str2, String str3, HyperSnapParams.FaceMatchMode faceMatchMode, APICompletionCallback aPICompletionCallback) {
        String str4;
        boolean z;
        HVLogUtils.d(TAG, "isValidFaceMatchInputs() called with: context = [" + context + "], endpoint = [" + str + "], image1 = [" + str2 + "], image2 = [" + str3 + "], hyperSnapFaceMatchMode = [" + faceMatchMode + "], callback = [" + aPICompletionCallback + "]");
        if (aPICompletionCallback == null) {
            return false;
        }
        if (context == null) {
            str4 = "Context object is null";
            z = false;
        } else {
            str4 = "";
            z = true;
        }
        if (StringUtils.isEmptyOrNull(str)) {
            str4 = "Face match endpoint is empty";
            z = false;
        }
        if (StringUtils.isEmptyOrNull(str2) || !new File(str2).exists()) {
            str4 = "Face file path is invalid";
            z = false;
        }
        int i = AnonymousClass13.$SwitchMap$co$hyperverge$hypersnapsdk$objects$HyperSnapParams$FaceMatchMode[faceMatchMode.ordinal()];
        if (i != 1) {
            if (i == 2) {
                if (StringUtils.isEmptyOrNull(str3) || !new File(str3).exists()) {
                    str4 = "Document file path is invalid";
                    z = false;
                }
            } else if ((i == 3 || i == 4) && StringUtils.isEmptyOrNull(str3)) {
                str4 = "ID Face String is invalid";
                z = false;
            }
        } else if (StringUtils.isEmptyOrNull(str3) || !new File(str3).exists()) {
            str4 = "Second face file path is invalid";
            z = false;
        }
        if (z) {
            return true;
        }
        aPICompletionCallback.onResult(new HVError(6, str4), null);
        return false;
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void makeVerifyDocAlignmentCall(final Context context, final String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, final List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        JSONObject jSONObject3 = jSONObject2;
        HVLogUtils.d(TAG, "makeVerifyDocAlignmentCall() called with: context = [" + context + "], endpoint = [" + str + "], documentUri = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject3 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        if (aPICompletionCallback == null) {
            return;
        }
        if (context == null) {
            aPICompletionCallback.onResult(new HVError(6, "Context object is null"), null);
            return;
        }
        if (str2 == null || (str2 != null && (str2.trim().isEmpty() || !new File(str2).exists()))) {
            aPICompletionCallback.onResult(new HVError(6, "Document file path is invalid"), null);
            return;
        }
        File file = new File(str2);
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        if (jSONObject3 == null) {
            jSONObject3 = new JSONObject();
        }
        JSONObject addHeaderParams = addHeaderParams(jSONObject3);
        final String uniqueIdentifierIfNeeded = SignatureHelper.getUniqueIdentifierIfNeeded(str2, addHeaderParams);
        try {
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && uniqueIdentifierIfNeeded != null) {
                addHeaderParams.put("uuid", uniqueIdentifierIfNeeded);
            }
        } catch (JSONException e) {
            printLogs(e);
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
        }
        final boolean checkForDataLogging = checkForDataLogging(jSONObject);
        Map<String, RequestBody> mapFromJson = getMapFromJson(jSONObject);
        Map<String, String> hashMap = new HashMap<>();
        if (addHeaderParams != null) {
            hashMap = (Map) new Gson().fromJson(addHeaderParams.toString(), HashMap.class);
        }
        final Map<String, String> map = hashMap;
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            map.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            map.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            map.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        Call<ResponseBody> verifyAlignment = ApiClient.getService().verifyAlignment(str, map, createFormData, mapFromJson);
        final String referenceId = getReferenceId(addHeaderParams);
        final TimingUtils timingUtils = new TimingUtils();
        verifyAlignment.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.5
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String str3;
                String str4;
                HVLogUtils.d(RemoteDataSource.TAG, "makeVerifyDocAlignmentCall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                HVResponse hVResponse = new HVResponse();
                JSONObject responseBody = aPIResponse.getResponseBody();
                if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && (str4 = uniqueIdentifierIfNeeded) != null) {
                    try {
                        aPIResponse.setRequestSignature(str4);
                    } catch (JSONException e2) {
                        RemoteDataSource.this.logError(e2);
                    }
                }
                RemoteDataSource remoteDataSource = RemoteDataSource.this;
                boolean isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, uniqueIdentifierIfNeeded, null, null, null, remoteDataSource.convertMapToJson(map), false, list);
                HVError error = !isSignatureVerfied ? RemoteDataSource.this.getError(AppConstants.SIGNATURE_ERROR, 18) : null;
                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                hVResponse.setStatusCode(aPIResponse.getStatusCode());
                hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                try {
                    str3 = aPIResponse.getRequestID();
                } catch (JSONException e3) {
                    RemoteDataSource.this.logError(e3);
                    str3 = null;
                }
                if (!response.isSuccessful()) {
                    JSONObject errorBody = aPIResponse.getErrorBody();
                    HVError hVError = new HVError();
                    if (isSignatureVerfied) {
                        error = errorBody != null ? RemoteDataSource.this.getError(errorBody) : hVError;
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logVerifyAlignmentAPIFailed(checkForDataLogging, referenceId, error.getErrorMessage(), error.getErrorCode(), str3);
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                    return;
                }
                if (responseBody != null) {
                    if (isSignatureVerfied) {
                        hVResponse.setApiResult(responseBody);
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logVerifyAlignmentAPISuccess(checkForDataLogging, referenceId, str3, timingUtils.getTimeDifferenceLong().longValue());
                        }
                        aPICompletionCallback.onResult(null, hVResponse);
                        return;
                    }
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logVerifyAlignmentAPIFailed(checkForDataLogging, referenceId, error.getErrorMessage(), error.getErrorCode(), str3);
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVError hVError;
                HVLogUtils.d(RemoteDataSource.TAG, "makeVerifyDocAlignmentCall(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                Log.e(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(th);
                if (th.getLocalizedMessage().contains("Certificate pinning")) {
                    hVError = new HVError(15, "Secure connection could not be established.");
                } else {
                    hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                }
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logVerifyAlignmentAPICallFailed(hVError);
                }
                aPICompletionCallback.onResult(hVError, null);
            }
        });
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logVerifyAlignmentAPIPost();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void makeTextMatchAPICall(final Context context, final String str, JSONObject jSONObject, JSONObject jSONObject2, final List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(TAG, "makeTextMatchAPICall() called with: context = [" + context + "], endpoint = [" + str + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        if (aPICompletionCallback == null) {
            return;
        }
        if (context == null) {
            aPICompletionCallback.onResult(new HVError(6, "Context object is null"), null);
            return;
        }
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        final JSONObject jSONObject3 = jSONObject;
        JSONObject addHeaderParams = addHeaderParams(jSONObject2);
        final String uniqueIdentifierForReqIfNeeded = SignatureHelper.getUniqueIdentifierForReqIfNeeded(jSONObject3.toString());
        Map<String, String> hashMap = new HashMap<>();
        if (addHeaderParams != null) {
            hashMap = (Map) new Gson().fromJson(addHeaderParams.toString(), HashMap.class);
        }
        final Map<String, String> map = hashMap;
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            map.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            map.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            map.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        Call<ResponseBody> matchingAPI = ApiClient.getService().matchingAPI(str, map, RequestBody.create(MediaType.parse("application/json"), jSONObject3.toString()));
        getReferenceId(addHeaderParams);
        new TimingUtils();
        matchingAPI.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.6
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                boolean isSignatureVerfied;
                HVLogUtils.d(RemoteDataSource.TAG, "makeTextMatchAPICall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                HVResponse hVResponse = new HVResponse();
                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                hVResponse.setStatusCode(aPIResponse.getStatusCode());
                hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                if (str.contains("apac")) {
                    RemoteDataSource remoteDataSource = RemoteDataSource.this;
                    isSignatureVerfied = remoteDataSource.isSignatureVerfied(aPIResponse, uniqueIdentifierForReqIfNeeded, null, null, jSONObject3, remoteDataSource.convertMapToJson(map), true, list);
                } else {
                    RemoteDataSource remoteDataSource2 = RemoteDataSource.this;
                    isSignatureVerfied = remoteDataSource2.isSignatureVerfied(aPIResponse, uniqueIdentifierForReqIfNeeded, null, null, jSONObject3, remoteDataSource2.convertMapToJson(map), false, list);
                }
                HVError error = !isSignatureVerfied ? RemoteDataSource.this.getError(AppConstants.SIGNATURE_ERROR, 18) : null;
                JSONObject responseBody = aPIResponse.getResponseBody();
                if (!response.isSuccessful()) {
                    if (isSignatureVerfied) {
                        if (aPIResponse.getErrorBody() != null) {
                            aPICompletionCallback.onResult(RemoteDataSource.this.getError(aPIResponse.getErrorBody()), hVResponse);
                            return;
                        }
                        return;
                    }
                    aPICompletionCallback.onResult(error, hVResponse);
                    return;
                }
                if (responseBody != null) {
                    if (isSignatureVerfied) {
                        hVResponse.setApiResult(responseBody);
                        aPICompletionCallback.onResult(null, hVResponse);
                    } else {
                        aPICompletionCallback.onResult(error, hVResponse);
                    }
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVError hVError;
                HVLogUtils.d(RemoteDataSource.TAG, "makeTextMatchAPICall(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                Log.e(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(th);
                if (th.getLocalizedMessage().contains("Certificate pinning")) {
                    hVError = new HVError(15, "Secure connection could not be established.");
                } else {
                    hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                }
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logTextMatchAPICallFailed(hVError);
                }
                aPICompletionCallback.onResult(hVError, null);
            }
        });
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logTextMatchAPIPost();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void makeValidationAPICall(final Context context, final String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, Boolean bool, List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(TAG, "makeValidationAPICall() called with: context = [" + context + "], endpoint = [" + str + "], userInput = [" + jSONObject + "], ocrResultFront = [" + jSONObject2 + "], ocrResultBack = [" + jSONObject3 + "], qr = [" + jSONObject4 + "], checkDatabase = [" + bool + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        if (aPICompletionCallback == null) {
            return;
        }
        if (context == null) {
            aPICompletionCallback.onResult(new HVError(6, "Context object is null"), null);
            return;
        }
        HashMap hashMap = new HashMap();
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            hashMap.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        JSONObject jSONObject5 = new JSONObject();
        if (jSONObject != null) {
            try {
                jSONObject5.put("userInput", jSONObject);
            } catch (Exception e) {
                printLogs(e);
                SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
            }
        }
        if (jSONObject2 != null && jSONObject3 != null) {
            jSONObject5.put("ocrResultFront", jSONObject2);
            jSONObject5.put("ocrResultBack", jSONObject3);
        }
        if (jSONObject2 != null && jSONObject3 == null) {
            jSONObject5.put("ocrResult", jSONObject2);
        }
        if (jSONObject4 != null) {
            jSONObject5.put("qrResult", jSONObject4);
        }
        jSONObject5.put("checkDatabase", bool);
        Call<ResponseBody> validationAPICall = ApiClient.getService().validationAPICall(str, hashMap, RequestBody.create(MediaType.parse("application/json"), jSONObject5.toString()));
        final TimingUtils timingUtils = new TimingUtils();
        validationAPICall.enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.7
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "makeValidationAPICall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                HVResponse hVResponse = new HVResponse();
                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                hVResponse.setStatusCode(aPIResponse.getStatusCode());
                hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                if (response.isSuccessful()) {
                    try {
                        hVResponse.setApiResult(aPIResponse.getResponseBody());
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logValidationAPISuccess(aPIResponse.getRequestID(), timingUtils.getTimeDifferenceLong().longValue());
                        }
                        aPICompletionCallback.onResult(null, hVResponse);
                        return;
                    } catch (JSONException e2) {
                        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                            try {
                                SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logValidationAPIFailed(e2.getLocalizedMessage(), response.code(), aPIResponse.getRequestID());
                            } catch (JSONException unused) {
                                RemoteDataSource.this.logError(e2);
                            }
                        }
                        aPICompletionCallback.onResult(RemoteDataSource.this.getErrorFromException(e2), hVResponse);
                        RemoteDataSource.this.printLogs(e2);
                        SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e2);
                        return;
                    }
                }
                try {
                    HVError errorValidationAPI = RemoteDataSource.this.getErrorValidationAPI(aPIResponse.getErrorBody());
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logValidationAPIFailed(errorValidationAPI.getErrorMessage(), errorValidationAPI.getErrorCode(), aPIResponse.getRequestID());
                    }
                    aPICompletionCallback.onResult(errorValidationAPI, hVResponse);
                } catch (JSONException e3) {
                    RemoteDataSource.this.printLogs(e3);
                    SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e3);
                    if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                        try {
                            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logValidationAPIFailed(e3.getLocalizedMessage(), response.code(), aPIResponse.getRequestID());
                        } catch (JSONException unused2) {
                            RemoteDataSource.this.logError(e3);
                        }
                    }
                    aPICompletionCallback.onResult(RemoteDataSource.this.getErrorFromException(e3), hVResponse);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVError hVError;
                HVLogUtils.d(RemoteDataSource.TAG, "makeValidationAPICall(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                Log.d(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(th);
                if (th.getLocalizedMessage().contains("Certificate pinning")) {
                    hVError = new HVError(15, "Secure connection could not be established.");
                } else {
                    hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, "Network error occurred url: " + str));
                }
                if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logValidationAPICallFailed(hVError);
                }
                aPICompletionCallback.onResult(hVError, null);
            }
        });
        if (SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            SDKInternalConfig.getInstance().getAnalyticsTrackerService(context).logValidationAPIPost();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void postSensorDataFiles(final Context context, String str, File file) {
        HVLogUtils.d(TAG, "postSensorDataFiles() called with: context = [" + context + "], endpoint = [" + str + "], sensorDataZipFile = [" + file + "]");
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("sensorDataZipFile", file.getName(), RequestBody.create(MediaType.parse("application/zip"), file));
        HashMap hashMap = new HashMap();
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            hashMap.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        ApiClient.getSensorDataInterface().postSensorData(str, hashMap, createFormData).enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.8
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "postSensorDataFiles(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                FileHelper.delete(new File(context.getFilesDir(), "hv/sensorData"));
                if (response.isSuccessful()) {
                    if (SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                        SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSensorDataPostSuccessful();
                    }
                } else if (SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSensorDataPostFailure(new HVError(2, "response.isSuccessful() is false"));
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "postSensorDataFiles(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                String str2 = RemoteDataSource.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFailure postSensorDataFiles : ");
                sb.append(Utils.getErrorMessage(th));
                Log.e(str2, sb.toString());
                FileHelper.delete(new File(context.getFilesDir(), "hv/sensorData"));
                if (SDKInternalConfig.getInstance().getAnalyticsTrackerService() != null) {
                    SDKInternalConfig.getInstance().getAnalyticsTrackerService().logSensorDataPostFailure(new HVError(2, Utils.getErrorMessage(th)));
                }
            }
        });
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void postAnalyticsData(Context context, String str, Map<String, Object> map) {
        String str2 = TAG;
        HVLogUtils.d(str2, "postAnalyticsData() called with: context = [" + context + "], endpoint = [" + str + "], analyticsData = [" + map + "]");
        HashMap hashMap = new HashMap();
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            hashMap.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("project", "mobile_sdk_analytics");
        hashMap2.put("properties", map);
        JSONObject jSONObject = new JSONObject(hashMap2);
        HVLogUtils.d(str2, "postAnalyticsData: requestBodyJsonObject: " + jSONObject);
        ApiClient.getAnalyticsApiInterface().postAnalyticsData(str, hashMap, RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.9
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "postAnalyticsData: onResponse() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "postAnalyticsData: onFailure() called with: call = [" + call + "], t = [" + th + "]");
            }
        });
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void ipAddressToGeo(final APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(TAG, "ipAddressToGeo() called with: callback = [" + aPICompletionCallback + "]");
        HashMap hashMap = new HashMap();
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            hashMap.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        try {
            if (!SPHelper.getTransactionID().trim().isEmpty() && !hashMap.containsKey("transactionId")) {
                hashMap.put("transactionId", SPHelper.getTransactionID());
            }
        } catch (Exception e) {
            Log.e(TAG, Utils.getErrorMessage(e));
        }
        ApiClient.getService().ipAddressToGeo("https://hypersnapweb.hyperverge.co/api/proxy/geoIP/", hashMap).enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.10
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "ipAddressToGeo(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                APIResponse aPIResponse = new APIResponse(response);
                HVResponse hVResponse = new HVResponse();
                hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
                hVResponse.setStatusCode(aPIResponse.getStatusCode());
                hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
                if (response.isSuccessful()) {
                    try {
                        hVResponse.setApiResult(aPIResponse.getResponseBody());
                        aPICompletionCallback.onResult(null, hVResponse);
                        return;
                    } catch (Exception e2) {
                        Log.e(RemoteDataSource.TAG, Utils.getErrorMessage(e2));
                        aPICompletionCallback.onResult(RemoteDataSource.this.getErrorFromException(e2), hVResponse);
                        return;
                    }
                }
                try {
                    aPICompletionCallback.onResult(RemoteDataSource.this.getError(aPIResponse.getErrorBody()), hVResponse);
                } catch (Exception e3) {
                    Log.e(RemoteDataSource.TAG, Utils.getErrorMessage(e3));
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e3);
                    aPICompletionCallback.onResult(RemoteDataSource.this.getErrorFromException(e3), hVResponse);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "ipAddressToGeo(): onFailure() called with: call = [" + call + "], t = [" + th + "]");
                Log.d(RemoteDataSource.TAG, Utils.getErrorMessage(th));
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(th);
                aPICompletionCallback.onResult(new HVError(12, Utils.getLocalizedErrorMessage(th, RemoteDataSource.NETWORK_ERROR_OCCURRED)), null);
            }
        });
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void makeFaceDedupeAPICall(final Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, final List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(TAG, "makeFaceDedupeAPICall() called with: context = [" + context + "], endpoint = [" + str + "], selfieImageURL = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        final String uUIDForImage = getUUIDForImage(context, str2, jSONObject2);
        getResponseBodyForSingleImageRequest(context, str, str2, jSONObject, jSONObject2).enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.11
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "makeFaceDedupeAPICall() onResponse() called with: call = [" + call + "], response = [" + response + "]");
                RemoteDataSource.this.processResponse(context, response, uUIDForImage, list, aPICompletionCallback);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "makeFaceDedupeAPICall(): onFailure() called with: call = [" + call + "], throwable = [" + th + "]");
                RemoteDataSource.this.processFailureResponse(context, th, aPICompletionCallback);
            }
        });
    }

    @Override // co.hyperverge.hypersnapsdk.data.DataSource
    public void makeFaceAuthAPICall(final Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, final List<Integer> list, final APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(TAG, "makeFaceAuthAPICall() called with: context = [" + context + "], endpoint = [" + str + "], selfieImageURL = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        final String uUIDForImage = getUUIDForImage(context, str2, jSONObject2);
        getResponseBodyForSingleImageRequest(context, str, str2, jSONObject, jSONObject2).enqueue(new Callback<ResponseBody>() { // from class: co.hyperverge.hypersnapsdk.data.remote.RemoteDataSource.12
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                HVLogUtils.d(RemoteDataSource.TAG, "makeFaceAuthAPICall(): onResponse() called with: call = [" + call + "], response = [" + response + "]");
                RemoteDataSource.this.processResponse(context, response, uUIDForImage, list, aPICompletionCallback);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                HVLogUtils.d(RemoteDataSource.TAG, "makeFaceAuthAPICall(): onFailure() called with: call = [" + call + "], throwable = [" + th + "]");
                RemoteDataSource.this.processFailureResponse(context, th, aPICompletionCallback);
            }
        });
    }

    public void processFailureResponse(Context context, Throwable th, APICompletionCallback aPICompletionCallback) {
        HVError hVError;
        HVLogUtils.d(TAG, "processFailureResponse() called with: context = [" + context + "], t = [" + th + "], callback = [" + aPICompletionCallback + "]");
        SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(th);
        if (th.getLocalizedMessage().contains("Certificate pinning")) {
            hVError = new HVError(15, "Secure connection could not be established.");
        } else {
            hVError = new HVError(12, Utils.getLocalizedErrorMessage(th, NETWORK_ERROR_OCCURRED));
        }
        aPICompletionCallback.onResult(hVError, null);
    }

    public void processResponse(Context context, Response<ResponseBody> response, String str, List<Integer> list, APICompletionCallback aPICompletionCallback) {
        HVLogUtils.d(TAG, "processResponse() called with: context = [" + context + "], response = [" + response + "], uuid = [" + str + "], allowedStatusCodes = [" + list + "], callback = [" + aPICompletionCallback + "]");
        APIResponse aPIResponse = new APIResponse(response);
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && str != null) {
            try {
                aPIResponse.setRequestSignature(str);
            } catch (JSONException e) {
                logError(e);
            }
        }
        boolean isSignatureVerfied = isSignatureVerfied(aPIResponse, str, null, null, null, null, false, list);
        HVError error = !isSignatureVerfied ? getError(AppConstants.SIGNATURE_ERROR, 18) : null;
        HVResponse hVResponse = new HVResponse();
        hVResponse.setApiHeaders(aPIResponse.getResponseHeaders());
        hVResponse.setStatusCode(aPIResponse.getStatusCode());
        hVResponse.setStatusMessage(aPIResponse.getStatusMessage());
        JSONObject responseBody = aPIResponse.getResponseBody();
        if (!response.isSuccessful()) {
            if (isSignatureVerfied) {
                if (aPIResponse.getErrorBody() != null) {
                    aPICompletionCallback.onResult(getError(aPIResponse.getErrorBody()), hVResponse);
                    return;
                }
                return;
            }
            aPICompletionCallback.onResult(error, hVResponse);
            return;
        }
        if (responseBody != null) {
            if (isSignatureVerfied) {
                hVResponse.setApiResult(responseBody);
                aPICompletionCallback.onResult(null, hVResponse);
            } else {
                aPICompletionCallback.onResult(error, hVResponse);
            }
        }
    }

    public Call<ResponseBody> getResponseBodyForSingleImageRequest(Context context, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2) {
        HVLogUtils.d(TAG, "getResponseBodyForSingleImageRequest() called with: context = [" + context + "], endpoint = [" + str + "], imageUri = [" + str2 + "], parameters = [" + jSONObject + "], headers = [" + jSONObject2 + "]");
        File file = new File(str2);
        MultipartBody.Part createFormData = MultipartBody.Part.createFormData("selfie", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        JSONObject addHeaderParams = addHeaderParams(jSONObject2);
        Map<String, RequestBody> mapFromJson = getMapFromJson(jSONObject);
        Map<String, String> hashMap = new HashMap<>();
        if (addHeaderParams != null) {
            hashMap = (Map) new Gson().fromJson(addHeaderParams.toString(), HashMap.class);
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken() != null && !HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken().isEmpty()) {
            hashMap.put("Authorization", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken());
        } else {
            hashMap.put("appId", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId());
            hashMap.put(HyperKycConfig.APP_KEY, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppKey());
        }
        return ApiClient.getService().singleImageUpload(str, hashMap, createFormData, mapFromJson);
    }

    public String getUUIDForImage(Context context, String str, JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getUUIDForImage() called with: context = [" + context + "], imageUri = [" + str + "], headers = [" + jSONObject + "]");
        String uniqueIdentifierIfNeeded = SignatureHelper.getUniqueIdentifierIfNeeded(str, jSONObject);
        try {
            if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && uniqueIdentifierIfNeeded != null) {
                jSONObject.put("uuid", uniqueIdentifierIfNeeded);
            }
        } catch (JSONException e) {
            printLogs(e);
            SDKInternalConfig.getInstance().getErrorMonitoringService(context).sendErrorMessage(e);
        }
        return uniqueIdentifierIfNeeded;
    }

    private MultipartBody.Part prepareFilePart(Context context, String str, String str2) {
        HVLogUtils.d(TAG, "prepareFilePart() called with: context = [" + context + "], partName = [" + str + "], filePath = [" + str2 + "]");
        File file = new File(str2);
        return MultipartBody.Part.createFormData(str, file.getName(), RequestBody.create(MediaType.parse(context.getContentResolver().getType(Uri.parse(str2))), file));
    }

    public String getReferenceId(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getReferenceId() called with: headers = [" + jSONObject + "]");
        if (jSONObject != null && jSONObject.has("referenceId")) {
            try {
                return jSONObject.getString("referenceId");
            } catch (JSONException e) {
                printLogs(e);
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                    SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                }
            }
        }
        return " ";
    }

    public HVError getError(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getError() called with: error = [" + jSONObject + "]");
        new JSONObject();
        HVError hVError = new HVError();
        try {
            String string = jSONObject.has("error") ? jSONObject.getString("error") : " ";
            if (jSONObject.has(Constant.PARAM_RESULT)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(Constant.PARAM_RESULT);
                if (jSONObject2.has("error")) {
                    string = jSONObject2.getString("error");
                }
            } else if (jSONObject.has("message")) {
                string = jSONObject.getString("message");
            }
            return new HVError(jSONObject.has(Keys.STATUS_CODE) ? jSONObject.getInt(Keys.STATUS_CODE) : 14, string);
        } catch (JSONException e) {
            printLogs(e);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return hVError;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return hVError;
        }
    }

    public HVError getErrorValidationAPI(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getErrorValidationAPI() called with: error = [" + jSONObject + "]");
        new JSONObject();
        HVError hVError = new HVError();
        try {
            int i = jSONObject.getInt(Keys.STATUS_CODE);
            JSONObject jSONObject2 = jSONObject.getJSONObject("error");
            if (jSONObject2.has("code")) {
                i = jSONObject2.getInt("code");
            }
            String string = jSONObject2.has("message") ? jSONObject2.getString("message") : "";
            if (jSONObject2.has(Constants.PATH)) {
                try {
                    string = string + ", path:" + jSONObject2.getJSONArray(Constants.PATH).toString();
                } catch (Exception e) {
                    printLogs(e);
                    if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                        SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                    }
                }
            }
            return new HVError(i, string);
        } catch (JSONException e2) {
            printLogs(e2);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return hVError;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            return hVError;
        }
    }

    public HVError getErrorFromException(Exception exc) {
        String str = TAG;
        HVLogUtils.d(str, "getErrorFromException() called with: e = [" + exc + "]");
        Log.e(str, Utils.getErrorMessage(exc));
        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(exc);
        }
        return new HVError(2, exc.getLocalizedMessage() != null ? exc.getLocalizedMessage() : "Internal SDK error occurred");
    }

    public HVError getError(String str, int i) {
        HVLogUtils.d(TAG, "getError() called with: errorMessage = [" + str + "], errorCode = [" + i + "]");
        return new HVError(i, str);
    }

    JSONObject setCustomHeaders(Headers headers, String str) {
        HVLogUtils.d(TAG, "setCustomHeaders() called with: headers = [" + headers + "], uuid = [" + str + "]");
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < headers.size(); i++) {
            try {
                String name = headers.name(i);
                String value = headers.value(i);
                if (name.toLowerCase().startsWith("x-hv-")) {
                    jSONObject.put(name, value);
                }
                if (name.equalsIgnoreCase("x-request-id")) {
                    jSONObject.put(AppConstants.HV_REQUEST_ID, value);
                }
                if (name.equalsIgnoreCase("x-response-signature")) {
                    jSONObject.put("X-HV-Response-Signature", value);
                }
            } catch (Exception e) {
                printLogs(e);
                if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                    return null;
                }
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
                return null;
            }
        }
        if (HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() && str != null) {
            jSONObject.put("X-HV-Request-Signature", str);
        }
        return jSONObject;
    }

    public JSONObject addHeaderParams(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "addHeaderParams() called with: header = [" + jSONObject + "]");
        try {
            if (jSONObject.has("transactionId") && jSONObject.getString("transactionId").equalsIgnoreCase("transactionId")) {
                jSONObject.remove("transactionId");
            }
        } catch (Exception e) {
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        try {
            if (!jSONObject.has(WorkflowAPIHeaders.SDK_VERSION)) {
                jSONObject.put(WorkflowAPIHeaders.SDK_VERSION, BuildConfig.HYPERSNAP_VERSION_NAME);
            }
            jSONObject.put("os", "android");
            jSONObject.put(WorkflowAPIHeaders.APP_VERSION, SDKInternalConfig.getInstance().getAppVersion());
            jSONObject.put("device", Build.MODEL);
            jSONObject.put("device-brand", Build.BRAND);
            jSONObject.put("device-manufacturer", Build.MANUFACTURER);
            jSONObject.put("abi-arch", Build.CPU_ABI);
            jSONObject.put("os-version", Build.VERSION.SDK_INT);
            jSONObject.put("sensor-orientation", AppConstants.sensorOrientation);
            if (!AppConstants.cameraLevel.isEmpty()) {
                jSONObject.put("camera-level", AppConstants.cameraLevel + Parameters.DEFAULT_OPTION_PREFIXES + AppConstants.cameraType);
            }
        } catch (Exception e2) {
            printLogs(e2);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            }
        }
        return jSONObject;
    }

    public Map<String, RequestBody> getMapFromJson(JSONObject jSONObject) {
        HVLogUtils.d(TAG, "getMapFromJson() called with: params = [" + jSONObject + "]");
        Gson gson = new Gson();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Map map = (Map) gson.fromJson(jSONObject.toString(), (Class) hashMap2.getClass());
        for (String str : map.keySet()) {
            hashMap.put(str, RequestBody.create(MediaType.parse(AssetHelper.DEFAULT_MIME_TYPE), (String) map.get(str)));
        }
        return hashMap;
    }

    public String getRequestId(Headers headers) {
        String str;
        HVLogUtils.d(TAG, "getRequestId() called with: object = [" + headers + "]");
        try {
            str = headers.get(AppConstants.REQUEST_ID);
        } catch (Exception e) {
            printLogs(e);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
            str = " ";
        }
        if (str != null && (str == null || !str.trim().isEmpty())) {
            return str;
        }
        try {
            return headers.get(AppConstants.HV_REQUEST_ID);
        } catch (Exception e2) {
            printLogs(e2);
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return " ";
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e2);
            return " ";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printLogs(Exception exc) {
        try {
            if (TextUtils.isEmpty(Utils.getErrorMessage(exc))) {
                return;
            }
            Log.e(TAG, Utils.getErrorMessage(exc));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLivenessResponseForSummary(LivenessResponse livenessResponse, APIResponse aPIResponse) {
        HVLogUtils.d(TAG, "setLivenessResponseForSummary() called with: livenessResponse = [" + livenessResponse + "], apiResponse = [" + aPIResponse + "]");
        try {
            JSONObject errorBody = aPIResponse.getErrorBody();
            HVError error = getError(errorBody);
            livenessResponse.setLivenessError(error.getErrorMessage());
            livenessResponse.setErrorCode(error.getErrorCode());
            if (errorBody.has(Constant.PARAM_RESULT) && errorBody.getJSONObject(Constant.PARAM_RESULT).has("summary")) {
                livenessResponse.setResponse(errorBody);
            }
            livenessResponse.setRequestID(aPIResponse.getRequestID());
        } catch (JSONException e) {
            logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HVResponse setSummaryDetails(HVResponse hVResponse, JSONObject jSONObject) {
        HVLogUtils.d(TAG, "setSummaryDetails() called with: response = [" + hVResponse + "], resultObj = [" + jSONObject + "]");
        try {
            if (jSONObject.has(Constant.PARAM_RESULT)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(Constant.PARAM_RESULT);
                if (jSONObject2.has("summary")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("summary");
                    String string = jSONObject3.getString("action");
                    String str = AppConstants.RETAKE_DEFAULT_MESSAGE;
                    if (jSONObject3.has(AppConstants.RETAKE_MESSAGE)) {
                        str = jSONObject3.getString(AppConstants.RETAKE_MESSAGE);
                    }
                    hVResponse.setAction(string);
                    hVResponse.setRetakeMessage(str);
                    hVResponse.setApiResult(jSONObject);
                }
            }
        } catch (Exception e) {
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
        return hVResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject convertMapToJson(Map<String, String> map) {
        HVLogUtils.d(TAG, "convertMapToJson() called with: map = [" + map + "]");
        try {
            return new JSONObject(new Gson().toJson(map));
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSignatureVerfied(APIResponse aPIResponse, String str, JSONObject jSONObject, HashMap<String, String> hashMap, JSONObject jSONObject2, JSONObject jSONObject3, boolean z, List<Integer> list) {
        boolean validateSignatureIfNeededAPAC;
        HVLogUtils.d(TAG, "isSignatureVerfied() called with: apiResponse = [" + aPIResponse + "], uuid = [" + str + "], requestQuery = [" + jSONObject + "], tagFileUriMap = [" + hashMap + "], requestBody = [" + jSONObject2 + "], requestHeaders = [" + jSONObject3 + "], shouldUseApacSignatureCheck = [" + z + "], allowedStatusCodes = [" + list + "]");
        if (list == null || list.isEmpty()) {
            list = new HVBaseConfig().getAllowedStatusCodes();
        }
        HyperSnapSDKConfig hyperSnapSDKConfig = HyperSnapSDK.getInstance().getHyperSnapSDKConfig();
        if (!hyperSnapSDKConfig.isShouldUseSignature() || !list.contains(aPIResponse.getStatusCode())) {
            return true;
        }
        JSONObject responseBody = aPIResponse.getResponseBody();
        if (responseBody == null) {
            responseBody = aPIResponse.getErrorBody();
        }
        JSONObject responseHeaders = aPIResponse.getResponseHeaders();
        if (!hyperSnapSDKConfig.isShouldUseSHA256Signature() || z) {
            validateSignatureIfNeededAPAC = SignatureHelper.validateSignatureIfNeededAPAC(responseBody, responseHeaders, str);
        } else {
            validateSignatureIfNeededAPAC = SignatureHelper.validateSignatureIfNeeded(responseBody, responseHeaders, str);
        }
        if (validateSignatureIfNeededAPAC) {
            return validateSignatureIfNeededAPAC;
        }
        try {
            return GKYCSignatureVerify.builder().requestQuery(jSONObject).tagFileUriMap(hashMap).requestBody(jSONObject2).requestHeaders(jSONObject3).responseBody(responseBody).responseHeaders(responseHeaders).build().verify(responseHeaders.getString("X-HV-Signature"));
        } catch (JSONException e) {
            logError(e);
            return validateSignatureIfNeededAPAC;
        }
    }

    private boolean isApac(String str) {
        HVLogUtils.d(TAG, "isApac() called with: endpoint = [" + str + "]");
        HyperSnapParams.Region hyperSnapRegion = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getHyperSnapRegion();
        return str.contains("apac") || hyperSnapRegion.equals(HyperSnapParams.Region.ASIA_PACIFIC) || hyperSnapRegion.equals(HyperSnapParams.Region.AsiaPacific);
    }

    private boolean isAfrica(String str) {
        HVLogUtils.d(TAG, "isAfrica() called with: endpoint = [" + str + "]");
        return str.contains("zaf") || HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getHyperSnapRegion().equals(HyperSnapParams.Region.AFRICA);
    }

    private boolean isIndia(String str) {
        HVLogUtils.d(TAG, "isIndia() called with: endpoint = [" + str + "]");
        HyperSnapParams.Region hyperSnapRegion = HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getHyperSnapRegion();
        return str.contains("ind") || hyperSnapRegion.equals(HyperSnapParams.Region.INDIA) || hyperSnapRegion.equals(HyperSnapParams.Region.India);
    }

    private boolean shouldSendQRCodeToOCREndpoint(String str) {
        HVLogUtils.d(TAG, "shouldSendQRCodeToOCREndpoint() called with: ocrEndpoint = [" + str + "]");
        return !StringUtils.isEmptyOrNull(str) && (str.contains("/v1.1/readNID") || str.contains("/v2/nationalID"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logError(Exception exc) {
        String str = TAG;
        HVLogUtils.d(str, "logError() called with: e = [" + exc + "]");
        Log.e(str, Utils.getErrorMessage(exc));
        if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(exc);
        }
    }

    private void updateAPIResponseWithRequestSignature(APIResponse aPIResponse, String str) {
        HVLogUtils.d(TAG, "updateAPIResponseWithRequestSignature() called with: apiResponse = [" + aPIResponse + "], uuid = [" + str + "]");
        if (!HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature() || str == null) {
            return;
        }
        try {
            aPIResponse.setRequestSignature(str);
        } catch (JSONException e) {
            logError(e);
        }
    }
}
