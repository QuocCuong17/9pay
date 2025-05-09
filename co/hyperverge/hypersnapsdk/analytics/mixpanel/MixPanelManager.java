package co.hyperverge.hypersnapsdk.analytics.mixpanel;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import co.hyperverge.hypersnapsdk.BuildConfig;
import co.hyperverge.hypersnapsdk.HyperSnapSDK;
import co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService;
import co.hyperverge.hypersnapsdk.analytics.Event;
import co.hyperverge.hypersnapsdk.analytics.TrackerAnalyticsData;
import co.hyperverge.hypersnapsdk.analytics.mixpanel.network.MixPanelIntentService;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.helpers.SPHelper;
import co.hyperverge.hypersnapsdk.model.LivenessResponse;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.gson.Gson;
import com.tekartik.sqflite.Constant;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes2.dex */
public class MixPanelManager implements AnalyticsTrackerService {
    public static final int MIX_PANEL_MAX_BATCH_EVENTS_COUNT = 50;
    private static MixPanelManager mixPanelManager;
    private final String TAG = MixPanelManager.class.getCanonicalName();
    private final Context context;

    public void flushData() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionsGranted(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionsRejected(HVError hVError, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionsRequested() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentAPICallFailed(HVResponse hVResponse, HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentAPIPost(String str, String str2) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentAPIResponseReceived(HVResponse hVResponse, String str, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureButtonClicked(HVDocConfig hVDocConfig, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureFlashButtonClicked() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureSaved(HVDocConfig hVDocConfig, String str, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureScreenBackPressed() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCloseClicked() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentDocFlowStarted(HVDocConfig hVDocConfig) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentInstructionsScreenEnabled() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentInstructionsScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentInstructionsScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentInstructionsScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentInstructionsScreenProceedButtonClicked(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentPickerScreenCloseClicked() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentPickerScreenDocumentCaptureButtonClicked(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentPickerScreenDocumentUploadButtonClicked(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentPickerScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentPickerScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentPickerScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentRetakeScreenBackPressed() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentRetakeScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentRetakeScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentRetakeScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentRetakeScreenRetakeButtonClicked(HVDocConfig hVDocConfig, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenBackPressed() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenConfirmButtonClicked(HVDocConfig hVDocConfig, int i, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenRetakeButtonClicked(HVDocConfig hVDocConfig, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceCaptureTimedOut() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceDetectorMissing(String str) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceDetectorTimedOut() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchEnabled() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchSuccess() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logGenericTrackEvent(String str, Map<String, Object> map) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logHyperSnapSDKInitError(String str) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logHyperSnapSDKInitialised() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieAPICallFailed(LivenessResponse livenessResponse, HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieAPIPost(String str, String str2) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieAPIResponseReceived(LivenessResponse livenessResponse, String str, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCameraOpen() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureButtonClicked(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureFlipCameraButtonClicked() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureSaved(HVFaceConfig hVFaceConfig, String str, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureScreenBackPressed() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCloseClicked() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieFlowStarted(HVFaceConfig hVFaceConfig) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenEnabled() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenProceedButtonClicked(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieRetakeScreenBackPressed() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieRetakeScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieRetakeScreenLoadFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieRetakeScreenLoadSuccess(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieRetakeScreenRetakeButtonClicked(HVFaceConfig hVFaceConfig, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieVideoRecordFailed(HVError hVError, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieVideoRecordSuccessful(String str, long j, long j2) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSensorDataPostFailure(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSensorDataPostSuccessful() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPICallFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIPost() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIResponseReceived() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPICallFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPIPost() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPIResponseReceived() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPICallFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPIPost() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPIResponseReceived() {
    }

    private MixPanelManager(Context context) {
        this.context = context;
        MixPanelIntentService.enqueuePendingEvents(context);
    }

    public static MixPanelManager getInstance(Context context) {
        if (mixPanelManager == null) {
            mixPanelManager = new MixPanelManager(context);
        }
        return mixPanelManager;
    }

    private void pitchEvent(String str, JSONObject jSONObject) {
        Log.d(this.TAG, "pitchEvent() called with: eventName = [" + str + "], props = [" + jSONObject + "]");
        try {
            if (SDKInternalConfig.getInstance().isShouldUseMixpanel()) {
                MixPanelIntentService.enqueueEvent(this.context, str, jSONObject);
                flushData();
            }
        } catch (Exception e) {
            Log.e(this.TAG, Utils.getErrorMessage(e));
            SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logUserSessionStarted(boolean z) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isUserSession()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.SSL_PINNING_ENABLED, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldEnableSSLPinning());
                trackerDataAsJSON.put(Keys.USE_BRANDING, SDKInternalConfig.getInstance().isShouldUseBranding());
                trackerDataAsJSON.put(Keys.RAW_RESPONSE_ENABLED, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldReturnRawResponse());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().isShouldUseSignature());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put(Keys.TRANSACTION_ID_SET_BY_CLIENT, z);
                trackerDataAsJSON.put(Keys.INIT_REGION, HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getHyperSnapRegion());
                pitchEvent(Event.EVENT_USER_SESSION_STARTED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logUserSessionEnded(String str) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isUserSession()) {
            try {
                pitchEvent(Event.EVENT_USER_SESSION_ENDED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenLaunched() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isInstructionsScreenLaunched()) {
            try {
                pitchEvent(Event.EVENT_SELFIE_INSTRUCTIONS_SCREEN_LAUNCHED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureScreenOpened(HVFaceConfig hVFaceConfig) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureScreenLaunched()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.SHOW_INSTRUCTIONS_PAGE, hVFaceConfig.isShouldShowInstructionPage());
                trackerDataAsJSON.put(Keys.ENABLE_PADDING, hVFaceConfig.isShouldSetPadding());
                trackerDataAsJSON.put(Keys.USE_BACK_CAMERA, hVFaceConfig.getShouldUseBackCamera());
                trackerDataAsJSON.put(Keys.SHOW_CAMERA_SWITCH, hVFaceConfig.isShouldUseFlip());
                trackerDataAsJSON.put(Keys.RETURN_FULL_IMAGE_URI, hVFaceConfig.isShouldReturnFullImageUrl());
                trackerDataAsJSON.put(Keys.REJECT_FACE_NOT_STRAIGHT, false);
                trackerDataAsJSON.put(Keys.LIVENESS_MODE, hVFaceConfig.getMode().toString());
                trackerDataAsJSON.put(Keys.CLIENT_ID, hVFaceConfig.getClientID());
                pitchEvent(Event.EVENT_SELFIE_CAPTURE_SCREEN_LAUNCHED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureSuccessful(HVFaceConfig hVFaceConfig, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.CLIENT_ID, hVFaceConfig.getClientID());
                trackerDataAsJSON.put(Keys.LIVENESS_MODE, hVFaceConfig.getMode().toString());
                if (hVFaceConfig.getHeaders() != null) {
                    JSONObject headers = hVFaceConfig.getHeaders();
                    if (headers.has("referenceId")) {
                        trackerDataAsJSON.put(Keys.REFERENCE_ID, headers.getString("referenceId"));
                    }
                }
                pitchEvent(Event.EVENT_SELFIE_CAPTURE_SUCCESSFUL, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureFailed(HVError hVError, HVFaceConfig hVFaceConfig, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put(Keys.CLIENT_ID, hVFaceConfig.getClientID());
                trackerDataAsJSON.put(Keys.LIVENESS_MODE, hVFaceConfig.getMode().toString());
                trackerDataAsJSON.put(Keys.ERROR_CODE, hVError.getErrorCode());
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, hVError.getErrorMessage());
                pitchEvent(Event.EVENT_SELFIE_CAPTURE_ERROR, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieScreenClosedByUser(HVFaceConfig hVFaceConfig) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureScreenClosed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.CLIENT_ID, hVFaceConfig.getClientID());
                pitchEvent(Event.EVENT_SELFIE_CAPTURE_CLOSED_BY_USER, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logLivenessAPISuccessful(LivenessResponse livenessResponse, HVFaceConfig hVFaceConfig, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.CLIENT_ID, hVFaceConfig.getClientID());
                trackerDataAsJSON.put(Keys.LIVENESS_MODE, hVFaceConfig.getMode().toString());
                trackerDataAsJSON.put(Keys.DATA_LOGGING, true);
                trackerDataAsJSON.put(Keys.REQUEST_ID, livenessResponse.getRequestID());
                trackerDataAsJSON.put(Keys.TIME_TAKEN_FOR_API_CALL, j);
                if (hVFaceConfig.getHeaders() != null) {
                    JSONObject headers = hVFaceConfig.getHeaders();
                    if (headers.has("referenceId")) {
                        trackerDataAsJSON.put(Keys.REFERENCE_ID, headers.getString("referenceId"));
                    }
                }
                JSONObject jSONObject = livenessResponse.getResponse().getJSONObject(Constant.PARAM_RESULT);
                trackerDataAsJSON.put(Keys.LIVENESS_SCORE, jSONObject.getString("liveness-score"));
                trackerDataAsJSON.put(Keys.LIVE, jSONObject.getString("live").equals("yes"));
                trackerDataAsJSON.put(Keys.TO_BE_REVIEWED, jSONObject.getString("to-be-reviewed"));
                pitchEvent(Event.EVENT_SELFIE_LIVENESS_API_SUCCESSFUL, trackerDataAsJSON);
            } catch (NullPointerException | JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logLivenessAPIFailed(HVError hVError, LivenessResponse livenessResponse, HVFaceConfig hVFaceConfig) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.CLIENT_ID, hVFaceConfig.getClientID());
                trackerDataAsJSON.put(Keys.LIVENESS_MODE, hVFaceConfig.getMode().toString());
                trackerDataAsJSON.put(Keys.ERROR_CODE, hVError.getErrorCode());
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, hVError.getErrorMessage());
                trackerDataAsJSON.put(Keys.DATA_LOGGING, true);
                if (livenessResponse != null) {
                    trackerDataAsJSON.put(Keys.REQUEST_ID, livenessResponse.getRequestID());
                }
                if (hVFaceConfig.getHeaders() != null) {
                    JSONObject headers = hVFaceConfig.getHeaders();
                    if (headers.has("referenceId")) {
                        trackerDataAsJSON.put(Keys.REFERENCE_ID, headers.getString("referenceId"));
                    }
                }
                pitchEvent(Event.EVENT_SELFIE_LIVENESS_API_ERROR, trackerDataAsJSON);
            } catch (NullPointerException | JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureScreenOpened(HVDocConfig hVDocConfig) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureScreenLaunched()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.SHOW_INSTRUCTIONS_PAGE, hVDocConfig.isShouldShowInstructionPage());
                trackerDataAsJSON.put(Keys.SHOW_REVIEW_PAGE, hVDocConfig.shouldShowReviewScreen());
                trackerDataAsJSON.put(Keys.ADD_PADDING, hVDocConfig.isShouldSetPadding());
                trackerDataAsJSON.put(Keys.DOCUMENT_TYPE, hVDocConfig.getDocument().toString());
                trackerDataAsJSON.put(Keys.ASPECT_RATIO, hVDocConfig.getDocument().getAspectRatio());
                trackerDataAsJSON.put(Keys.SHOW_FLASH_BUTTON, hVDocConfig.isShouldShowFlashIcon());
                trackerDataAsJSON.put(Keys.API_END_POINT, hVDocConfig.ocrEndpoint);
                if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.FRONT.toString())) {
                    pitchEvent(Event.EVENT_ID_FRONT_CAPTURE_SCREEN_LAUNCHED, trackerDataAsJSON);
                } else if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.BACK.toString())) {
                    pitchEvent(Event.EVENT_ID_BACK_CAPTURE_SCREEN_LAUNCHED, trackerDataAsJSON);
                }
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdOldReviewScreenEvents(HVDocConfig hVDocConfig, String str) {
        SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isOldDocReviewScreenEvents();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdCaptureReviewScreenLaunched() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isOldDocReviewScreenEvents()) {
            try {
                pitchEvent(Event.EVENT_ID_CAPTURE_REVIEW_SCREEN_LAUNCHED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdCaptureReviewScreenRetake() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isOldDocReviewScreenEvents()) {
            try {
                pitchEvent(Event.EVENT_ID_CAPTURE_REVIEW_SCREEN_RETAKE, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdScreenClosedByUser(HVDocConfig hVDocConfig) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureScreenClosed()) {
            try {
                if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.FRONT.toString())) {
                    pitchEvent(Event.EVENT_ID_FRONT_CAPTURE_SCREEN_CLOSED_BY_USER, getTrackerDataAsJSON());
                } else if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.BACK.toString())) {
                    pitchEvent(Event.EVENT_ID_BACK_CAPTURE_SCREEN_CLOSED_BY_USER, getTrackerDataAsJSON());
                }
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureSuccessful(HVDocConfig hVDocConfig, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureSuccessful()) {
            try {
                if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.FRONT.toString())) {
                    pitchEvent(Event.EVENT_ID_FRONT_CAPTURE_SUCCESSFUL, getTrackerDataAsJSON());
                } else if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.BACK.toString())) {
                    pitchEvent(Event.EVENT_ID_BACK_CAPTURE_SUCCESSFUL, getTrackerDataAsJSON());
                }
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureFailed(HVError hVError, HVDocConfig hVDocConfig, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.ERROR_CODE, hVError.getErrorCode());
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, hVError.getErrorMessage());
                if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.FRONT.toString())) {
                    pitchEvent(Event.EVENT_ID_FRONT_CAPTURE_FAILED, trackerDataAsJSON);
                } else if (hVDocConfig.getSuffixForDocument().equalsIgnoreCase(HVDocConfig.DocumentSide.BACK.toString())) {
                    pitchEvent(Event.EVENT_ID_BACK_CAPTURE_FAILED, trackerDataAsJSON);
                }
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdAPISuccessful(boolean z, String str, String str2, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put(Keys.DATA_LOGGING, z);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str2);
                trackerDataAsJSON.put(Keys.TIME_TAKEN_FOR_API_CALL, j);
                if (!StringUtils.isEmptyOrNull(str)) {
                    trackerDataAsJSON.put(Keys.REFERENCE_ID, str);
                }
                pitchEvent(Event.EVENT_ID_API_SUCCESSFUL, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdAPIFailed(boolean z, String str, String str2, int i, String str3) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put(Keys.DATA_LOGGING, z);
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, str2);
                trackerDataAsJSON.put(Keys.ERROR_CODE, i);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str3);
                if (!StringUtils.isEmptyOrNull(str)) {
                    trackerDataAsJSON.put(Keys.REFERENCE_ID, str);
                }
                pitchEvent(Event.EVENT_ID_API_ERROR, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchAPIPost(String str, String str2, String str3, String str4) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallMade()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.FACE_MATCH_MODE, str2.toString());
                pitchEvent(Event.EVENT_FACE_MATCH_API_POST, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchAPIResponseReceived(HVResponse hVResponse, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                pitchEvent(Event.EVENT_FACE_MATCH_API_RESPONSE_RECEIVED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchAPICallFailed(HVResponse hVResponse, HVError hVError, String str) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                pitchEvent(Event.EVENT_FACE_MATCH_API_CALL_FAILED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIMethodCalled() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallMade()) {
            try {
                pitchEvent(Event.EVENT_TEXT_MATCH_API_CALLED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPISuccessful(boolean z, String str, String str2, HyperSnapParams.FaceMatchMode faceMatchMode, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.DATA_LOGGING, z);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str2);
                trackerDataAsJSON.put(Keys.FACE_MATCH_MODE, faceMatchMode.toString());
                trackerDataAsJSON.put(Keys.TIME_TAKEN_FOR_API_CALL, j);
                if (!StringUtils.isEmptyOrNull(str)) {
                    trackerDataAsJSON.put(Keys.REFERENCE_ID, str);
                }
                pitchEvent(Event.EVENT_TEXT_MATCH_API_SUCCESSFUL, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIFailed(boolean z, String str, String str2, int i, String str3) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put(Keys.DATA_LOGGING, z);
                trackerDataAsJSON.put(Keys.ERROR_CODE, i);
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, str2);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str3);
                if (!StringUtils.isEmptyOrNull(str)) {
                    trackerDataAsJSON.put(Keys.REFERENCE_ID, str);
                }
                pitchEvent(Event.EVENT_TEXT_MATCH_API_FAILED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionDeniedError() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isOtherErrors()) {
            try {
                pitchEvent(Event.EVENT_CAMERA_PERMISSION_DENIED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logHardwareError() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isOtherErrors()) {
            try {
                pitchEvent(Event.EVENT_HARDWARE_ERROR, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPISuccess(boolean z, String str, String str2, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.DATA_LOGGING, z);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str2);
                trackerDataAsJSON.put(Keys.TIME_TAKEN_FOR_API_CALL, j);
                if (!StringUtils.isEmptyOrNull(str)) {
                    trackerDataAsJSON.put(Keys.REFERENCE_ID, str);
                }
                pitchEvent(Event.EVENT_DOC_VERIFY_ALIGNMENT_API_SUCCESS, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPIFailed(boolean z, String str, String str2, int i, String str3) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put(Keys.DATA_LOGGING, z);
                trackerDataAsJSON.put(Keys.ERROR_CODE, i);
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, str2);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str3);
                if (!StringUtils.isEmptyOrNull(str)) {
                    trackerDataAsJSON.put(Keys.REFERENCE_ID, str);
                }
                pitchEvent(Event.EVENT_DOC_VERIFY_ALIGNMENT_API_FAILED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPISuccess(String str, long j) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.REQUEST_ID, str);
                trackerDataAsJSON.put(Keys.TIME_TAKEN_FOR_API_CALL, j);
                pitchEvent(Event.EVENT_VALIDATION_API_SUCCESSFUL, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPIFailed(String str, int i, String str2) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put(Keys.ERROR_CODE, i);
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, str);
                trackerDataAsJSON.put(Keys.REQUEST_ID, str2);
                pitchEvent(Event.EVENT_VALIDATION_API_FAILED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logQRScannerLaunched() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureScreenLaunched()) {
            try {
                pitchEvent(Event.EVENT_QR_SCANNER_LAUNCHED, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logQRScannerFailed(HVError hVError) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isCaptureFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put(Keys.ERROR_CODE, hVError.getErrorCode());
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, hVError.getErrorMessage());
                pitchEvent(Event.EVENT_QR_SCANNER_FAILED, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logQRParseFailed() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isOtherErrors()) {
            try {
                pitchEvent(Event.EVENT_QR_PARSE_ERROR, getTrackerDataAsJSON());
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCheckBrandingAPISuccess() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                pitchEvent(Event.EVENT_BRANDING_CHECK_API_SUCCESSFUL, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCheckBrandingAPIError(String str, int i) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put(Keys.ERROR_CODE, i);
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, str);
                pitchEvent(Event.EVENT_BRANDING_CHECK_API_ERROR, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logRemoteConfigAPISuccess() {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallSuccessful()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                pitchEvent(Event.EVENT_REMOTE_CONFIG_API_SUCCESSFUL, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logRemoteConfigAPIError(String str, int i) {
        if (SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelEvents().isApiCallFailed()) {
            try {
                JSONObject trackerDataAsJSON = getTrackerDataAsJSON();
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getWriteTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getConnectTimeOut());
                trackerDataAsJSON.put("Use Signature", HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getReadTimeOut());
                trackerDataAsJSON.put(Keys.ERROR_CODE, i);
                trackerDataAsJSON.put(Keys.ERROR_MESSAGE, str);
                pitchEvent(Event.EVENT_REMOTE_CONFIG_API_ERROR, trackerDataAsJSON);
            } catch (JSONException e) {
                Log.e(this.TAG, Utils.getErrorMessage(e));
                SDKInternalConfig.getInstance().getErrorMonitoringService(this.context).sendErrorMessage(e);
            }
        }
    }

    private TrackerAnalyticsData getTrackerData() {
        String transactionID = SPHelper.getTransactionID();
        return TrackerAnalyticsData.builder().appId(HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAppId()).appName(SDKInternalConfig.getInstance().getAppName()).sdkVersionNumber(BuildConfig.HYPERSNAP_VERSION_NAME).token(HyperSnapSDK.getInstance().getHyperSnapSDKConfig().getAccessToken()).distinctId(!TextUtils.isEmpty(transactionID) ? transactionID : null).mixPanelToken(SDKInternalConfig.getInstance().getRemoteConfig().getMixpanelConfig().getMixpanelToken()).timeInMilliSeconds(System.currentTimeMillis()).screenHeight(UIUtils.getScreenHeight()).screenWidth(UIUtils.getScreenWidth()).osVersion(String.valueOf(Build.VERSION.SDK_INT)).deviceName(Build.BRAND).deviceModel(Build.MODEL).deviceManufacturer(Build.MANUFACTURER).transactionId(transactionID).abi(Utils.getDeviceAbi()).build();
    }

    private JSONObject getTrackerDataAsJSON() throws JSONException {
        return new JSONObject(new Gson().toJson(getTrackerData()));
    }
}
