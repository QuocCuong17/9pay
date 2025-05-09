package co.hyperverge.hypersnapsdk.analytics;

import co.hyperverge.hypersnapsdk.model.LivenessResponse;
import co.hyperverge.hypersnapsdk.objects.HVDocConfig;
import co.hyperverge.hypersnapsdk.objects.HVError;
import co.hyperverge.hypersnapsdk.objects.HVFaceConfig;
import co.hyperverge.hypersnapsdk.objects.HVResponse;
import co.hyperverge.hypersnapsdk.objects.HyperSnapParams;
import java.util.Map;

/* loaded from: classes2.dex */
public interface AnalyticsTrackerService {
    void logCameraPermissionDeniedError();

    void logCameraPermissionsGranted(long j);

    void logCameraPermissionsRejected(HVError hVError, long j);

    void logCameraPermissionsRequested();

    void logCheckBrandingAPIError(String str, int i);

    void logCheckBrandingAPISuccess();

    void logDocumentAPICallFailed(HVResponse hVResponse, HVError hVError);

    void logDocumentAPIPost(String str, String str2);

    void logDocumentAPIResponseReceived(HVResponse hVResponse, String str, long j);

    void logDocumentCaptureButtonClicked(HVDocConfig hVDocConfig, long j);

    void logDocumentCaptureFailed(HVError hVError, HVDocConfig hVDocConfig, long j);

    void logDocumentCaptureFlashButtonClicked();

    void logDocumentCaptureSaved(HVDocConfig hVDocConfig, String str, long j);

    void logDocumentCaptureScreenBackPressed();

    void logDocumentCaptureScreenLaunched();

    void logDocumentCaptureScreenLoadFailure(HVError hVError);

    void logDocumentCaptureScreenLoadSuccess(long j);

    void logDocumentCaptureScreenOpened(HVDocConfig hVDocConfig);

    void logDocumentCaptureSuccessful(HVDocConfig hVDocConfig, long j);

    void logDocumentCloseClicked();

    void logDocumentDocFlowStarted(HVDocConfig hVDocConfig);

    void logDocumentInstructionsScreenEnabled();

    void logDocumentInstructionsScreenLaunched();

    void logDocumentInstructionsScreenLoadFailure(HVError hVError);

    void logDocumentInstructionsScreenLoadSuccess(long j);

    void logDocumentInstructionsScreenProceedButtonClicked(long j);

    void logDocumentPickerScreenCloseClicked();

    void logDocumentPickerScreenDocumentCaptureButtonClicked(long j);

    void logDocumentPickerScreenDocumentUploadButtonClicked(long j);

    void logDocumentPickerScreenLaunched();

    void logDocumentPickerScreenLoadFailure(HVError hVError);

    void logDocumentPickerScreenLoadSuccess(long j);

    void logDocumentRetakeScreenBackPressed();

    void logDocumentRetakeScreenLaunched();

    void logDocumentRetakeScreenLoadFailure(HVError hVError);

    void logDocumentRetakeScreenLoadSuccess(long j);

    void logDocumentRetakeScreenRetakeButtonClicked(HVDocConfig hVDocConfig, long j);

    void logDocumentReviewScreenBackPressed();

    void logDocumentReviewScreenConfirmButtonClicked(HVDocConfig hVDocConfig, int i, long j);

    void logDocumentReviewScreenLaunched();

    void logDocumentReviewScreenLoadFailure(HVError hVError);

    void logDocumentReviewScreenLoadSuccess(long j);

    void logDocumentReviewScreenRetakeButtonClicked(HVDocConfig hVDocConfig, long j);

    void logFaceCaptureTimedOut();

    void logFaceDetectorMissing(String str);

    void logFaceDetectorTimedOut();

    void logFaceMatchAPICallFailed(HVResponse hVResponse, HVError hVError, String str);

    void logFaceMatchAPIPost(String str, String str2, String str3, String str4);

    void logFaceMatchAPIResponseReceived(HVResponse hVResponse, long j);

    void logFaceMatchEnabled();

    void logFaceMatchFailed(HVError hVError);

    void logFaceMatchSuccess();

    void logGenericTrackEvent(String str, Map<String, Object> map);

    void logHardwareError();

    void logHyperSnapSDKInitError(String str);

    void logHyperSnapSDKInitialised();

    void logIdAPIFailed(boolean z, String str, String str2, int i, String str3);

    void logIdAPISuccessful(boolean z, String str, String str2, long j);

    void logIdCaptureReviewScreenLaunched();

    void logIdCaptureReviewScreenRetake();

    void logIdOldReviewScreenEvents(HVDocConfig hVDocConfig, String str);

    void logIdScreenClosedByUser(HVDocConfig hVDocConfig);

    void logLivenessAPIFailed(HVError hVError, LivenessResponse livenessResponse, HVFaceConfig hVFaceConfig);

    void logLivenessAPISuccessful(LivenessResponse livenessResponse, HVFaceConfig hVFaceConfig, long j);

    void logQRParseFailed();

    void logQRScannerFailed(HVError hVError);

    void logQRScannerLaunched();

    void logRemoteConfigAPIError(String str, int i);

    void logRemoteConfigAPISuccess();

    void logSelfieAPICallFailed(LivenessResponse livenessResponse, HVError hVError);

    void logSelfieAPIPost(String str, String str2);

    void logSelfieAPIResponseReceived(LivenessResponse livenessResponse, String str, long j);

    void logSelfieCameraOpen();

    void logSelfieCaptureButtonClicked(long j);

    void logSelfieCaptureFailed(HVError hVError, HVFaceConfig hVFaceConfig, long j);

    void logSelfieCaptureFlipCameraButtonClicked();

    void logSelfieCaptureSaved(HVFaceConfig hVFaceConfig, String str, long j);

    void logSelfieCaptureScreenBackPressed();

    void logSelfieCaptureScreenLaunched();

    void logSelfieCaptureScreenLoadFailure(HVError hVError);

    void logSelfieCaptureScreenLoadSuccess(long j);

    void logSelfieCaptureScreenOpened(HVFaceConfig hVFaceConfig);

    void logSelfieCaptureSuccessful(HVFaceConfig hVFaceConfig, long j);

    void logSelfieCloseClicked();

    void logSelfieFlowStarted(HVFaceConfig hVFaceConfig);

    void logSelfieInstructionsScreenEnabled();

    void logSelfieInstructionsScreenLaunched();

    void logSelfieInstructionsScreenLoadFailure(HVError hVError);

    void logSelfieInstructionsScreenLoadSuccess(long j);

    void logSelfieInstructionsScreenProceedButtonClicked(long j);

    void logSelfieRetakeScreenBackPressed();

    void logSelfieRetakeScreenLaunched();

    void logSelfieRetakeScreenLoadFailure(HVError hVError);

    void logSelfieRetakeScreenLoadSuccess(long j);

    void logSelfieRetakeScreenRetakeButtonClicked(HVFaceConfig hVFaceConfig, long j);

    void logSelfieScreenClosedByUser(HVFaceConfig hVFaceConfig);

    void logSelfieVideoRecordFailed(HVError hVError, long j);

    void logSelfieVideoRecordSuccessful(String str, long j, long j2);

    void logSensorDataPostFailure(HVError hVError);

    void logSensorDataPostSuccessful();

    void logTextMatchAPICallFailed(HVError hVError);

    void logTextMatchAPIFailed(boolean z, String str, String str2, int i, String str3);

    void logTextMatchAPIMethodCalled();

    void logTextMatchAPIPost();

    void logTextMatchAPIResponseReceived();

    void logTextMatchAPISuccessful(boolean z, String str, String str2, HyperSnapParams.FaceMatchMode faceMatchMode, long j);

    void logUserSessionEnded(String str);

    void logUserSessionStarted(boolean z);

    void logValidationAPICallFailed(HVError hVError);

    void logValidationAPIFailed(String str, int i, String str2);

    void logValidationAPIPost();

    void logValidationAPIResponseReceived();

    void logValidationAPISuccess(String str, long j);

    void logVerifyAlignmentAPICallFailed(HVError hVError);

    void logVerifyAlignmentAPIFailed(boolean z, String str, String str2, int i, String str3);

    void logVerifyAlignmentAPIPost();

    void logVerifyAlignmentAPIResponseReceived();

    void logVerifyAlignmentAPISuccess(boolean z, String str, String str2, long j);
}
