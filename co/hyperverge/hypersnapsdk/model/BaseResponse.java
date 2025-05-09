package co.hyperverge.hypersnapsdk.model;

/* loaded from: classes2.dex */
public class BaseResponse {
    private int errorCode;
    private Integer httpStatusCode;
    private String statusMessage;

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseResponse)) {
            return false;
        }
        BaseResponse baseResponse = (BaseResponse) obj;
        if (!baseResponse.canEqual(this) || getErrorCode() != baseResponse.getErrorCode()) {
            return false;
        }
        Integer httpStatusCode = getHttpStatusCode();
        Integer httpStatusCode2 = baseResponse.getHttpStatusCode();
        if (httpStatusCode != null ? !httpStatusCode.equals(httpStatusCode2) : httpStatusCode2 != null) {
            return false;
        }
        String statusMessage = getStatusMessage();
        String statusMessage2 = baseResponse.getStatusMessage();
        return statusMessage != null ? statusMessage.equals(statusMessage2) : statusMessage2 == null;
    }

    public int hashCode() {
        int errorCode = getErrorCode() + 59;
        Integer httpStatusCode = getHttpStatusCode();
        int hashCode = (errorCode * 59) + (httpStatusCode == null ? 43 : httpStatusCode.hashCode());
        String statusMessage = getStatusMessage();
        return (hashCode * 59) + (statusMessage != null ? statusMessage.hashCode() : 43);
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setHttpStatusCode(Integer num) {
        this.httpStatusCode = num;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }

    public String toString() {
        return "BaseResponse(errorCode=" + getErrorCode() + ", statusMessage=" + getStatusMessage() + ", httpStatusCode=" + getHttpStatusCode() + ")";
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
