package co.hyperverge.hypersnapsdk.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import co.hyperverge.hypersnapsdk.data.models.FeatureConfig;
import co.hyperverge.hypersnapsdk.helpers.SDKInternalConfig;
import co.hyperverge.hypersnapsdk.model.HVJSONObject;
import co.hyperverge.hypersnapsdk.utils.AppConstants;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import co.hyperverge.hypersnapsdk.utils.Utils;
import com.google.android.gms.common.ConnectionResult;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class HVFaceConfig extends HVBaseConfig implements Serializable {
    private static final String TAG = "co.hyperverge.hypersnapsdk.objects.HVFaceConfig";
    static HVFaceConfig faceConfigInstance;
    String customUIStrings;
    private byte[] faceCaptureOverlay;
    String faceCaptureSubtitle;
    String faceCaptureTitle;
    public String headers;
    String livenessEndpoint;
    private String moduleId;
    String params;
    private boolean showAlertTextBox;
    private int statusTypeFace;
    private int subtitleTypeface;
    private int titleTypeface;
    LivenessMode mode = LivenessMode.TEXTURELIVENESS;
    String clientID = "";
    boolean shouldShowInstructionPage = false;
    boolean dataLogging = true;
    boolean shouldUseBackCamera = false;
    boolean shouldUseFlip = false;
    boolean useBothImagesSignature = false;
    boolean shouldCheckActiveLiveness = false;
    int totalGestures = 3;
    private int faceCaptureOverlayDuration = 3000;
    private boolean showModuleBackButton = true;
    boolean shouldUseZoom = false;
    String customLoaderClass = null;
    boolean shouldUseEnhancedCameraFeatures = false;
    boolean shouldHandleRetries = true;
    boolean allowFaceTilt = true;
    int faceTiltAngle = 15;
    float leftPadding = 0.0f;
    float rightPadding = 0.0f;
    float topPadding = 0.0f;
    float bottomPadding = 0.0f;
    boolean shouldSetPadding = false;
    boolean useFlash = false;
    boolean shouldReturnFullImageUrl = false;
    private boolean shouldAddWaterMark = false;
    private int waterMarkColor = SupportMenu.CATEGORY_MASK;
    private int fullImageWaterMarkTextSizePx = 45;
    private int cropImageWaterMarkTextSizePx = 20;
    private boolean shouldRecordVideo = false;
    private int numberOfFrames = 50;
    private double bitrateM = 1.0d;
    private int fps = 30;
    private boolean shouldAutoCapture = false;
    private int autoCaptureDuration = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
    private boolean shouldUseDefaultZoom = false;
    private int phoneTiltThreshold = -75;
    private boolean shouldAllowPhoneTilt = true;
    private boolean enableOverlay = true;
    private long faceDetectorTimeout = 0;
    private long captureTimeout = 0;
    private boolean isFaceDetectionDisabled = false;
    private boolean retryIfFaceNotPresent = false;
    private int maxAttemptsForFaceNotPresent = 3;

    /* loaded from: classes2.dex */
    public enum LivenessMode {
        NONE,
        TEXTURELIVENESS
    }

    @Deprecated
    public boolean isDataLogging() {
        return true;
    }

    @Deprecated
    public void setShouldEnableDataLogging(boolean z) {
    }

    public void setModuleId(String str) {
        this.moduleId = str;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setFaceCaptureOverlay(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        bitmap.recycle();
        this.faceCaptureOverlay = byteArray;
    }

    public Bitmap getFaceCaptureOverlay() {
        byte[] bArr = this.faceCaptureOverlay;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public void setFaceCaptureOverlayDuration(int i) {
        this.faceCaptureOverlayDuration = i;
    }

    public int getFaceCaptureOverlayDuration() {
        return this.faceCaptureOverlayDuration;
    }

    public boolean isShouldCheckActiveLiveness() {
        Map<String, FeatureConfig> featureConfigMap;
        if (this.shouldCheckActiveLiveness && (featureConfigMap = SDKInternalConfig.getInstance().getFeatureConfigMap()) != null && featureConfigMap.containsKey(AppConstants.ACTIVE_LIVENESS)) {
            this.shouldCheckActiveLiveness = featureConfigMap.get(AppConstants.ACTIVE_LIVENESS).isShouldEnable();
        }
        return this.shouldCheckActiveLiveness;
    }

    public void setShouldCheckActiveLiveness(boolean z) {
        this.shouldCheckActiveLiveness = z;
        if (z) {
            setShouldEnableOverlay(false);
            SDKInternalConfig.getInstance().setFaceDetectionProcessor(SDKInternalConfig.FaceDetectionProcessor.MLKIT);
        }
    }

    public int getTotalGestures() {
        return this.totalGestures;
    }

    public void setTotalGestures(int i) {
        this.totalGestures = i;
    }

    public static HVFaceConfig getFaceConfigInstance() {
        return faceConfigInstance;
    }

    public static void setFaceConfigInstance(HVFaceConfig hVFaceConfig) {
        faceConfigInstance = hVFaceConfig;
    }

    public boolean isOverlayEnabled() {
        return this.enableOverlay;
    }

    public void setShouldEnableOverlay(boolean z) {
        if (this.shouldCheckActiveLiveness) {
            this.enableOverlay = false;
            this.faceCaptureOverlayDuration = 0;
        } else {
            this.enableOverlay = z;
        }
    }

    public long getFaceDetectorTimeout() {
        return this.faceDetectorTimeout;
    }

    public void setFaceDetectorTimeout(long j) {
        this.faceDetectorTimeout = j;
    }

    public long getCaptureTimeout() {
        return this.captureTimeout;
    }

    public void setCaptureTimeout(long j) {
        this.captureTimeout = j;
    }

    public void showRetryIfFaceNotPresent(boolean z) {
        this.retryIfFaceNotPresent = z;
    }

    public boolean shouldRetryIfFaceNotPresent() {
        return this.retryIfFaceNotPresent;
    }

    public void setMaxAttemptsForFaceNotPresent(int i) {
        this.maxAttemptsForFaceNotPresent = i;
    }

    public int getMaxAttemptsForFaceNotPresent() {
        return this.maxAttemptsForFaceNotPresent;
    }

    public boolean isShouldAllowPhoneTilt() {
        return this.shouldAllowPhoneTilt;
    }

    public void setShouldAllowPhoneTilt(boolean z) {
        this.shouldAllowPhoneTilt = z;
    }

    public int getPhoneTiltThreshold() {
        return this.phoneTiltThreshold;
    }

    public void setPhoneTiltAngleThreshold(int i) {
        this.phoneTiltThreshold = -i;
    }

    public boolean isShouldUseDefaultZoom() {
        boolean z = this.shouldUseDefaultZoom;
        return z ? SDKInternalConfig.getInstance().shouldUseDefaultZoom() : z;
    }

    public void setShouldUseDefaultZoom(boolean z) {
        this.shouldUseDefaultZoom = z;
    }

    public int getFaceTiltAngle() {
        return this.faceTiltAngle;
    }

    public void setFaceTiltAngle(int i) {
        this.faceTiltAngle = i;
    }

    public boolean isShouldAutoCapture() {
        Map<String, FeatureConfig> featureConfigMap;
        if (this.shouldAutoCapture && (featureConfigMap = SDKInternalConfig.getInstance().getFeatureConfigMap()) != null && featureConfigMap.containsKey(AppConstants.SELFIE_AUTO_CAPTURE_FEATURE)) {
            this.shouldAutoCapture = featureConfigMap.get(AppConstants.SELFIE_AUTO_CAPTURE_FEATURE).isShouldEnable();
        }
        return this.shouldAutoCapture;
    }

    public void setShouldAutoCapture(boolean z) {
        this.shouldAutoCapture = z;
    }

    public int getAutoCaptureDuration() {
        return this.autoCaptureDuration;
    }

    public void setAutoCaptureDuration(int i) {
        this.autoCaptureDuration = i;
    }

    public void setDisableFaceDetection(boolean z) {
        this.isFaceDetectionDisabled = z;
        SDKInternalConfig.getInstance().setFaceDetectionOn(!z);
    }

    public boolean isFaceDetectionDisabled() {
        return this.isFaceDetectionDisabled;
    }

    public boolean isShouldRecordVideo() {
        Map<String, FeatureConfig> featureConfigMap;
        boolean z = SDKInternalConfig.getInstance().getRemoteConfig().isUseSelfieVideoRecording() || this.shouldRecordVideo;
        this.shouldRecordVideo = z;
        if (z && (featureConfigMap = SDKInternalConfig.getInstance().getFeatureConfigMap()) != null && featureConfigMap.containsKey(AppConstants.VIDEO_RECORDING_FEATURE)) {
            this.shouldRecordVideo = featureConfigMap.get(AppConstants.VIDEO_RECORDING_FEATURE).isShouldEnable();
        }
        return this.shouldRecordVideo;
    }

    public void setShouldRecordVideo(boolean z) {
        this.shouldRecordVideo = z;
    }

    public int getNumberOfFrames() {
        return this.numberOfFrames;
    }

    public void setNumberOfFrames(int i) {
        this.numberOfFrames = i;
    }

    public double getBitrateM() {
        return this.bitrateM;
    }

    public void setBitrateM(double d) {
        this.bitrateM = d;
    }

    public int getFps() {
        return this.fps;
    }

    public void setFps(int i) {
        this.fps = i;
    }

    public boolean isShouldHandleRetries() {
        return this.shouldHandleRetries;
    }

    public void setShouldHandleRetries(boolean z) {
        this.shouldHandleRetries = z;
    }

    public boolean shouldCheckForFaceTilt() {
        return !this.allowFaceTilt;
    }

    public void setAllowFaceTilt(boolean z) {
        this.allowFaceTilt = z;
        if (z) {
            return;
        }
        SDKInternalConfig.getInstance().setFaceDetectionProcessor(SDKInternalConfig.FaceDetectionProcessor.MLKIT);
    }

    public boolean isShouldUseEnhancedCameraFeatures() {
        return this.shouldUseEnhancedCameraFeatures;
    }

    public void setShouldUseEnhancedCameraFeatures(boolean z) {
        this.shouldUseEnhancedCameraFeatures = z;
    }

    public boolean isShouldUseZoom() {
        return this.shouldUseZoom;
    }

    @Deprecated
    public void setShouldUseZoom(boolean z) {
        this.shouldUseZoom = z;
    }

    public HVJSONObject getCustomUIStrings() {
        HVJSONObject hVJSONObject = new HVJSONObject();
        if (this.customUIStrings == null) {
            return hVJSONObject;
        }
        try {
            return new HVJSONObject(this.customUIStrings);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return hVJSONObject;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return hVJSONObject;
        }
    }

    public void setCustomUIStrings(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.customUIStrings = jSONObject.toString();
        }
    }

    public void setUseBothImagesSignatureCalc(boolean z) {
        this.useBothImagesSignature = z;
    }

    public boolean isUseBothImagesSignature() {
        return this.useBothImagesSignature;
    }

    public String getLivenessEndpoint() {
        String str = this.livenessEndpoint;
        if (str == null || (str != null && str.trim().isEmpty())) {
            return SDKInternalConfig.getInstance().getFaceBaseUrl() + SDKInternalConfig.getInstance().getLivenessUri();
        }
        return this.livenessEndpoint;
    }

    public void setLivenessEndpoint(String str) {
        this.livenessEndpoint = str;
    }

    public boolean isShouldReturnFullImageUrl() {
        return this.shouldReturnFullImageUrl;
    }

    public void setShouldReturnFullImageUrl(boolean z) {
        this.shouldReturnFullImageUrl = z;
    }

    public String getClientID() {
        return this.clientID;
    }

    public float getLeftPadding() {
        return this.leftPadding;
    }

    public float getRightPadding() {
        return this.rightPadding;
    }

    public float getTopPadding() {
        return this.topPadding;
    }

    public float getBottomPadding() {
        return this.bottomPadding;
    }

    public JSONObject getLivenessParams() {
        JSONObject jSONObject = new JSONObject();
        if (this.params == null) {
            return jSONObject;
        }
        try {
            return new JSONObject(this.params);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return jSONObject;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return jSONObject;
        }
    }

    public void setClientID(String str) {
        this.clientID = str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("clientId", this.clientID);
            setLivenessAPIParameters(jSONObject);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() != null) {
                SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            }
        }
    }

    public void setShouldShowCameraSwitchButton(boolean z) {
        this.shouldUseFlip = z;
    }

    public boolean isShouldUseFlip() {
        return this.shouldUseFlip;
    }

    public boolean isShouldShowInstructionPage() {
        return this.shouldShowInstructionPage;
    }

    public void setShouldShowInstructionPage(boolean z) {
        this.shouldShowInstructionPage = z;
    }

    public LivenessMode getMode() {
        return this.mode;
    }

    public String getStringMode() {
        return this.mode == LivenessMode.NONE ? "NONE" : this.mode == LivenessMode.TEXTURELIVENESS ? "TEXTURELIVENESS" : "";
    }

    public void setCustomLoadingScreen(String str) {
        this.customLoaderClass = str;
    }

    public String getCustomLoaderClass() {
        return this.customLoaderClass;
    }

    public void setLivenessMode(LivenessMode livenessMode) {
        this.mode = livenessMode;
    }

    public String getFaceCaptureTitle() {
        return this.faceCaptureTitle;
    }

    public void setFaceCaptureTitle(String str) {
        this.faceCaptureTitle = str;
    }

    @Deprecated
    public String getFaceCaptureSubtitle() {
        return this.faceCaptureSubtitle;
    }

    @Deprecated
    public void setFaceCaptureSubtitle(String str) {
        this.faceCaptureSubtitle = str;
    }

    public boolean getShouldUseBackCamera() {
        return this.shouldUseBackCamera;
    }

    public void setShouldUseBackCamera(boolean z) {
        this.shouldUseBackCamera = z;
    }

    public JSONObject getHeaders() {
        JSONObject jSONObject = new JSONObject();
        if (this.headers == null) {
            return jSONObject;
        }
        try {
            return new JSONObject(this.headers);
        } catch (JSONException e) {
            Log.e(TAG, Utils.getErrorMessage(e));
            if (SDKInternalConfig.getInstance().getErrorMonitoringService() == null) {
                return jSONObject;
            }
            SDKInternalConfig.getInstance().getErrorMonitoringService().sendErrorMessage(e);
            return jSONObject;
        }
    }

    public void setShouldEnablePadding(boolean z) {
        this.shouldSetPadding = z;
        if (z) {
            setPadding(0.2f, 0.2f, 0.3f, 0.1f);
        }
    }

    public boolean isUseFlash() {
        return this.useFlash;
    }

    public void setPadding(float f, float f2, float f3, float f4) {
        this.leftPadding = f;
        this.rightPadding = f2;
        this.topPadding = f3;
        this.bottomPadding = f4;
    }

    public void setLivenessAPIParameters(JSONObject jSONObject) {
        this.params = jSONObject.toString();
    }

    public void setLivenessAPIHeaders(JSONObject jSONObject) {
        this.headers = jSONObject.toString();
    }

    public int getTitleTypeface() {
        return this.titleTypeface;
    }

    public void setTitleTypeface(int i) {
        this.titleTypeface = i;
    }

    public int getSubtitleTypeface() {
        return this.subtitleTypeface;
    }

    public void setSubtitleTypeface(int i) {
        this.subtitleTypeface = i;
    }

    public int getStatusTypeFace() {
        return this.statusTypeFace;
    }

    public void setStatusTypeFace(int i) {
        this.statusTypeFace = i;
    }

    public boolean isShouldAddWaterMark() {
        return this.shouldAddWaterMark;
    }

    public void setShouldAddWaterMark(boolean z) {
        this.shouldAddWaterMark = z;
    }

    public int getWaterMarkColor() {
        return this.waterMarkColor;
    }

    public void setWaterMarkColor(int i) {
        this.waterMarkColor = i;
    }

    public int getFullImageWaterMarkTextSizePx() {
        return this.fullImageWaterMarkTextSizePx;
    }

    public void setFullImageWaterMarkTextSizePx(int i) {
        this.fullImageWaterMarkTextSizePx = i;
    }

    public int getCropImageWaterMarkTextSizePx() {
        return this.cropImageWaterMarkTextSizePx;
    }

    public void setCropImageWaterMarkTextSizePx(int i) {
        this.cropImageWaterMarkTextSizePx = i;
    }

    public boolean isShouldSetPadding() {
        return this.shouldSetPadding;
    }

    public boolean isShowAlertTextBox() {
        return this.showAlertTextBox;
    }

    public boolean isShowAlertTextBox(DisplayMetrics displayMetrics) {
        return !UIUtils.isSmallScreenDevice(displayMetrics) && this.showAlertTextBox;
    }

    public void setShowAlertTextBox(boolean z) {
        this.showAlertTextBox = z;
    }

    public boolean shouldShowModuleBackButton() {
        return this.showModuleBackButton;
    }

    public void setShowModuleBackButton(boolean z) {
        this.showModuleBackButton = z;
    }
}
