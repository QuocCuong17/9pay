package co.hyperverge.hypersnapsdk.utils;

import android.os.Environment;

/* loaded from: classes2.dex */
public class AppConstants {
    public static final String ACTION = "action";
    public static final String ACTIVE_LIVENESS = "active-liveness";
    public static final String ANALYTICS_FEATURE = "analytics";
    public static final String ATTEMPTS_KEY = "attempts";
    public static final String AUTO_CAM_SELECTION = "auto-cam-selection";
    public static final String CAMERA2 = "camera2";
    public static final int COMPRESSION_RATE = 90;
    public static final int DEFAULT_COMPRSSION_IMAGE_SIZE = 50;
    public static final String DEFAULT_ZOOM = "default-zoom";
    public static final String DETAILS = "details";
    public static final String DEVICE_ID = "deviceId";
    public static final String DOCUMENT_BAR_CODE = "barcodeString";
    public static final String DOCUMENT_QR_CODE = "qrString";
    public static final String DOCUMENT_SIDE = "expectedDocumentSide";
    public static final String DOC_AUTO_CAPTURE = "doc-auto-capture";
    public static final String EMPTY_TRANSACTION_ERROR = "Please call startSession before making OCR call within SDK. Transaction ID is empty";
    public static final String FACE_DETECTION_FEATURE = "face-detection";
    public static final String FEATURE_CONFIG = "feature-config";
    public static final String FEATURE_CONFIG_EXPIRY = "feature-config-expiry";
    public static final String FEATURE_CONFIG_LAST_MODIFIED = "feature-config-last-modified";
    public static final String HV_REQUEST_ID = "X-HV-Request-Id";
    public static final String IMAGE_INJECTION_FEATURE = "image-injection";
    public static final String IS_DOCUMENT_UPLOADED = "isDocumentUploaded";
    public static final String LAST_USED_HEIGHT = "lastUsedHeight";
    public static final String LAST_USED_WIDTH = "lastUsedWidth";
    public static final String LEVEL_3 = "level_3";
    public static final String LEVEL_FULL = "level_full";
    public static final String LEVEL_LEGACY = "level_legacy";
    public static final String LEVEL_LIMITED = "level_limited";
    public static final String ORIENTATION_BACK_CAM_FEATURE = "orientation-back-camera";
    public static final String PARAMS_UUID = "uuid";
    public static final String RAW_RESPONSE = "X-HV-Raw-Response";
    public static final String READ_DOC_QR_FEATURE = "read-doc-qr";
    public static final int REQUEST_CODE = 87;
    public static final String REQUEST_ID = "X-Request-Id";
    public static final String RESOLUTION_RANDOMIZE_FEATURE = "resolution-randomize";
    public static final String RETAKE = "retake";
    public static final String RETAKE_DEFAULT_MESSAGE = "Some issue with the image capture. Please try again.";
    public static final String RETAKE_ERROR_CODE = "code";
    public static final String RETAKE_MESSAGE = "retakeMessage";
    public static final String S3_CACHE_CONTROL = "Cache-Control";
    public static final String SDK_MODE = "sdk-mode";
    public static final String SELFIE_AUTO_CAPTURE_FEATURE = "selfie-auto-capture";
    public static final String SENSOR_DATA_ZIP_FILE_NAME = "sensorDataZipFileName";
    public static final String SIGNATURE_ERROR = "Network tampering detected";
    public static final String SUMMARY = "summary";
    public static final String TOTAL_ATTEMPTS = "totalAttempts";
    public static final String TRANSACTION_ID = "transactionId";
    public static final String USER_RANDOM_NUMBER = "userRandomNumber";
    public static final String VIDEO_RECORDING_FEATURE = "video-recording";
    public static final String PIC_DIR = Environment.getExternalStorageDirectory() + "/hyperlive";
    public static float ZOOM_FACTOR_BACK_CAM = 2.0f;
    public static float ZOOM_FACTOR_FRONT_CAM = 1.3f;
    public static String cameraLevel = "";
    public static String cameraType = "";
    public static String sensorOrientation = "";
    public static String mlkitUnavailableError = "";
    public static String mlkitMissing = "";
    public static String spFaceNotPresentKey = "hvFaceNotPresentKey";

    private AppConstants() {
    }
}
