package co.hyperverge.hypersnapsdk.analytics;

import android.content.Context;
import android.util.Log;
import co.hyperverge.hypersnapsdk.analytics.rudderstack.HVApolloManager;
import co.hyperverge.hypersnapsdk.data.DefaultRemoteConfig;
import co.hyperverge.hypersnapsdk.data.models.FeatureConfig;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.model.LivenessResponse;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import java.util.Map;

/* loaded from: classes2.dex */
public class AnalyticsTracker implements AnalyticsTrackerService {
    private final String TAG = AnalyticsTracker.class.getCanonicalName();
    private AnalyticsTrackerService analyticsTrackerImplementation;

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionDeniedError() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionsGranted(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionsRequested() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCheckBrandingAPIError(String str, int i) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCheckBrandingAPISuccess() {
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
    public void logDocumentCaptureFailed(HVError hVError, HVDocConfig hVDocConfig, long j) {
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
    public void logDocumentCaptureScreenOpened(HVDocConfig hVDocConfig) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureSuccessful(HVDocConfig hVDocConfig, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCloseClicked() {
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
    public void logDocumentReviewScreenBackPressed() {
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
    public void logFaceDetectorMissing(String str) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchAPICallFailed(HVResponse hVResponse, HVError hVError, String str) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchAPIPost(String str, String str2, String str3, String str4) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceMatchAPIResponseReceived(HVResponse hVResponse, long j) {
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
    public void logHardwareError() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdAPIFailed(boolean z, String str, String str2, int i, String str3) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdAPISuccessful(boolean z, String str, String str2, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdCaptureReviewScreenLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdCaptureReviewScreenRetake() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdOldReviewScreenEvents(HVDocConfig hVDocConfig, String str) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logIdScreenClosedByUser(HVDocConfig hVDocConfig) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logLivenessAPIFailed(HVError hVError, LivenessResponse livenessResponse, HVFaceConfig hVFaceConfig) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logLivenessAPISuccessful(LivenessResponse livenessResponse, HVFaceConfig hVFaceConfig, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logQRParseFailed() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logQRScannerFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logQRScannerLaunched() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logRemoteConfigAPIError(String str, int i) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logRemoteConfigAPISuccess() {
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
    public void logSelfieCaptureButtonClicked(long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureFailed(HVError hVError, HVFaceConfig hVFaceConfig, long j) {
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
    public void logSelfieCaptureScreenOpened(HVFaceConfig hVFaceConfig) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCloseClicked() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenEnabled() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieInstructionsScreenLaunched() {
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
    public void logTextMatchAPICallFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIFailed(boolean z, String str, String str2, int i, String str3) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIMethodCalled() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIPost() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPIResponseReceived() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logTextMatchAPISuccessful(boolean z, String str, String str2, HyperSnapParams.FaceMatchMode faceMatchMode, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPICallFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPIFailed(String str, int i, String str2) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPIPost() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPIResponseReceived() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logValidationAPISuccess(String str, long j) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPICallFailed(HVError hVError) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPIFailed(boolean z, String str, String str2, int i, String str3) {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPIPost() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPIResponseReceived() {
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logVerifyAlignmentAPISuccess(boolean z, String str, String str2, long j) {
    }

    public AnalyticsTracker(Context context) {
        Map<String, FeatureConfig> featureConfigMap;
        DefaultRemoteConfig.MobileSdkConfig mobileSdkConfig;
        DefaultRemoteConfig.AnalyticsConfig analyticsConfig;
        try {
            DefaultRemoteConfig defaultRemoteConfig = SDKInternalConfig.getInstance().getDefaultRemoteConfig();
            String str = "";
            if (defaultRemoteConfig != null && (mobileSdkConfig = defaultRemoteConfig.getMobileSdkConfig()) != null && (analyticsConfig = mobileSdkConfig.getAnalyticsConfig()) != null) {
                str = analyticsConfig.getEndpoint();
            }
            boolean z = !StringUtils.isEmptyOrNull(str);
            if (z && (featureConfigMap = SDKInternalConfig.getInstance().getFeatureConfigMap()) != null && featureConfigMap.containsKey(AppConstants.ANALYTICS_FEATURE)) {
                z = featureConfigMap.get(AppConstants.ANALYTICS_FEATURE).isShouldEnable();
            }
            this.analyticsTrackerImplementation = z ? HVApolloManager.getInstance(context, str) : null;
        } catch (Exception | NoClassDefFoundError e) {
            this.analyticsTrackerImplementation = null;
            Log.e(this.TAG, Utils.getErrorMessage(e));
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logUserSessionStarted(boolean z) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logUserSessionEnded(String str) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSensorDataPostSuccessful() {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSensorDataPostFailure(HVError hVError) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logHyperSnapSDKInitialised() {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logHyperSnapSDKInitError(String str) {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logHyperSnapSDKInitError(str);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCameraOpen() {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logSelfieCameraOpen();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceDetectorTimedOut() {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logFaceDetectorTimedOut();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logFaceCaptureTimedOut() {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logFaceCaptureTimedOut();
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieFlowStarted(HVFaceConfig hVFaceConfig) {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logSelfieFlowStarted(hVFaceConfig);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieCaptureSuccessful(HVFaceConfig hVFaceConfig, long j) {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logSelfieCaptureSuccessful(hVFaceConfig, j);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieScreenClosedByUser(HVFaceConfig hVFaceConfig) {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logSelfieScreenClosedByUser(hVFaceConfig);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logCameraPermissionsRejected(HVError hVError, long j) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieRetakeScreenRetakeButtonClicked(HVFaceConfig hVFaceConfig, long j) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentDocFlowStarted(HVDocConfig hVDocConfig) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieVideoRecordSuccessful(String str, long j, long j2) {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logSelfieVideoRecordSuccessful(str, j, j2);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logSelfieVideoRecordFailed(HVError hVError, long j) {
        if (isAnalyticsImplNotNull()) {
            this.analyticsTrackerImplementation.logSelfieVideoRecordFailed(hVError, j);
        }
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentCaptureButtonClicked(HVDocConfig hVDocConfig, long j) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentReviewScreenConfirmButtonClicked(HVDocConfig hVDocConfig, int i, long j) {
        isAnalyticsImplNotNull();
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logDocumentRetakeScreenRetakeButtonClicked(HVDocConfig hVDocConfig, long j) {
        isAnalyticsImplNotNull();
    }

    private boolean isAnalyticsImplNotNull() {
        return this.analyticsTrackerImplementation != null;
    }

    @Override // co.hyperverge.hypersnapsdk.analytics.AnalyticsTrackerService
    public void logGenericTrackEvent(String str, Map<String, Object> map) {
        if (isAnalyticsImplNotNull() && SDKInternalConfig.getInstance().isShouldLogAnalyticsForThisUser()) {
            this.analyticsTrackerImplementation.logGenericTrackEvent(str, map);
        }
    }
}
