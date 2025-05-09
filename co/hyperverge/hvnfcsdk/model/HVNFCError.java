package co.hyperverge.hvnfcsdk.model;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class HVNFCError implements Serializable {
    public static final int AUTHENTICATION_FAILED = 44;
    public static final int FORMAT_ERROR = 46;
    public static final int INVALID_DOCUMENT = 47;
    int errorCode;
    String errorMessage;

    protected boolean canEqual(Object obj) {
        return obj instanceof HVNFCError;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HVNFCError)) {
            return false;
        }
        HVNFCError hVNFCError = (HVNFCError) obj;
        if (!hVNFCError.canEqual(this) || getErrorCode() != hVNFCError.getErrorCode()) {
            return false;
        }
        String errorMessage = getErrorMessage();
        String errorMessage2 = hVNFCError.getErrorMessage();
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
        return "HVNFCError(errorMessage=" + getErrorMessage() + ", errorCode=" + getErrorCode() + ")";
    }

    public HVNFCError(String str, int i) {
        this.errorMessage = str;
        this.errorCode = i;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
