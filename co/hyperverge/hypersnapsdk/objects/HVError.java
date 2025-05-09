package co.hyperverge.hypersnapsdk.objects;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class HVError implements Serializable {
    public static final int ACTIVE_SESSION_ERROR = 16;
    public static final int BLURRY_FACE_DETECTION_ERROR = 23;
    public static final int DETECTOR_NOT_AVAILABLE_ERROR = 60;
    public static final int FACE_CAPTURE_TIMEOUT = 35;
    public static final int FACE_DETECTION_ERROR = 22;
    public static final int GPS_ACCESS_DENIED = 33;
    public static final int HARDWARE_ERROR = 5;
    public static final int HYPER_DOC_DETECT_ERROR = 36;
    public static final int INITIALIZATION_ERROR = 11;
    public static final int INPUT_ERROR = 6;
    public static final int INSTRUCTION_ERROR = 31;
    public static final int INTERNAL_SDK_ERROR = 2;
    public static final int INTERNAL_SERVER_ERROR = 14;
    public static final int INVALID_FILE_ERROR = 37;
    public static final int LOCATION_PERMISSION_NOT_AVAILABLE_ERROR = 8;
    public static final int LOW_STORAGE_ERROR = 25;
    public static final int NETWORK_ERROR = 12;
    public static final int OPERATION_CANCELLED_BY_USER_ERROR = 3;
    public static final int PERMISSIONS_NOT_GRANTED_ERROR = 4;
    public static final int QR_PARSER_ERROR = 7;
    public static final int QR_SCANNER_ERROR = 32;
    public static final int SIGNATURE_FAILED_ERROR = 18;
    public static final int SSL_CONNECT_ERROR = 15;
    public static final int TILT_FACE_DETECTED_ERROR = 24;
    public static final int TRANSACTION_ID_EMPTY = 17;
    private int errorCode;
    private String errorMessage;

    protected boolean canEqual(Object obj) {
        return obj instanceof HVError;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVError)) {
            return false;
        }
        HVError hVError = (HVError) obj;
        if (!hVError.canEqual(this) || getErrorCode() != hVError.getErrorCode()) {
            return false;
        }
        String errorMessage = getErrorMessage();
        String errorMessage2 = hVError.getErrorMessage();
        return errorMessage != null ? errorMessage.equals(errorMessage2) : errorMessage2 == null;
    }

    public int hashCode() {
        int errorCode = getErrorCode() + 59;
        String errorMessage = getErrorMessage();
        return (errorCode * 59) + (errorMessage == null ? 43 : errorMessage.hashCode());
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public String toString() {
        return "HVError(errorCode=" + getErrorCode() + ", errorMessage=" + getErrorMessage() + ")";
    }

    public HVError() {
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public HVError(int i, String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }
}
