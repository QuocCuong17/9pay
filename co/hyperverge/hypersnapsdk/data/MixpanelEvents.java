package co.hyperverge.hypersnapsdk.data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class MixpanelEvents implements Serializable {

    @SerializedName("userSession")
    private boolean userSession = false;

    @SerializedName("instructionsScreenLaunched")
    private boolean instructionsScreenLaunched = false;

    @SerializedName("captureScreenLaunched")
    private boolean captureScreenLaunched = false;

    @SerializedName("captureScreenClosed")
    private boolean captureScreenClosed = false;

    @SerializedName("captureSuccessful")
    private boolean captureSuccessful = false;

    @SerializedName("captureFailed")
    private boolean captureFailed = true;

    @SerializedName("oldDocReviewScreenEvents")
    private boolean oldDocReviewScreenEvents = false;

    @SerializedName("apiCallMade")
    private boolean apiCallMade = false;

    @SerializedName("apiCallSuccessful")
    private boolean apiCallSuccessful = false;

    @SerializedName("apiCallFailed")
    private boolean apiCallFailed = false;

    @SerializedName("otherErrors")
    private boolean otherErrors = true;

    protected boolean canEqual(Object obj) {
        return obj instanceof MixpanelEvents;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MixpanelEvents)) {
            return false;
        }
        MixpanelEvents mixpanelEvents = (MixpanelEvents) obj;
        return mixpanelEvents.canEqual(this) && isUserSession() == mixpanelEvents.isUserSession() && isInstructionsScreenLaunched() == mixpanelEvents.isInstructionsScreenLaunched() && isCaptureScreenLaunched() == mixpanelEvents.isCaptureScreenLaunched() && isCaptureScreenClosed() == mixpanelEvents.isCaptureScreenClosed() && isCaptureSuccessful() == mixpanelEvents.isCaptureSuccessful() && isCaptureFailed() == mixpanelEvents.isCaptureFailed() && isOldDocReviewScreenEvents() == mixpanelEvents.isOldDocReviewScreenEvents() && isApiCallMade() == mixpanelEvents.isApiCallMade() && isApiCallSuccessful() == mixpanelEvents.isApiCallSuccessful() && isApiCallFailed() == mixpanelEvents.isApiCallFailed() && isOtherErrors() == mixpanelEvents.isOtherErrors();
    }

    public int hashCode() {
        return (((((((((((((((((((((isUserSession() ? 79 : 97) + 59) * 59) + (isInstructionsScreenLaunched() ? 79 : 97)) * 59) + (isCaptureScreenLaunched() ? 79 : 97)) * 59) + (isCaptureScreenClosed() ? 79 : 97)) * 59) + (isCaptureSuccessful() ? 79 : 97)) * 59) + (isCaptureFailed() ? 79 : 97)) * 59) + (isOldDocReviewScreenEvents() ? 79 : 97)) * 59) + (isApiCallMade() ? 79 : 97)) * 59) + (isApiCallSuccessful() ? 79 : 97)) * 59) + (isApiCallFailed() ? 79 : 97)) * 59) + (isOtherErrors() ? 79 : 97);
    }

    public void setApiCallFailed(boolean z) {
        this.apiCallFailed = z;
    }

    public void setApiCallMade(boolean z) {
        this.apiCallMade = z;
    }

    public void setApiCallSuccessful(boolean z) {
        this.apiCallSuccessful = z;
    }

    public void setCaptureFailed(boolean z) {
        this.captureFailed = z;
    }

    public void setCaptureScreenClosed(boolean z) {
        this.captureScreenClosed = z;
    }

    public void setCaptureScreenLaunched(boolean z) {
        this.captureScreenLaunched = z;
    }

    public void setCaptureSuccessful(boolean z) {
        this.captureSuccessful = z;
    }

    public void setInstructionsScreenLaunched(boolean z) {
        this.instructionsScreenLaunched = z;
    }

    public void setOldDocReviewScreenEvents(boolean z) {
        this.oldDocReviewScreenEvents = z;
    }

    public void setOtherErrors(boolean z) {
        this.otherErrors = z;
    }

    public void setUserSession(boolean z) {
        this.userSession = z;
    }

    public String toString() {
        return "MixpanelEvents(userSession=" + isUserSession() + ", instructionsScreenLaunched=" + isInstructionsScreenLaunched() + ", captureScreenLaunched=" + isCaptureScreenLaunched() + ", captureScreenClosed=" + isCaptureScreenClosed() + ", captureSuccessful=" + isCaptureSuccessful() + ", captureFailed=" + isCaptureFailed() + ", oldDocReviewScreenEvents=" + isOldDocReviewScreenEvents() + ", apiCallMade=" + isApiCallMade() + ", apiCallSuccessful=" + isApiCallSuccessful() + ", apiCallFailed=" + isApiCallFailed() + ", otherErrors=" + isOtherErrors() + ")";
    }

    public boolean isUserSession() {
        return this.userSession;
    }

    public boolean isInstructionsScreenLaunched() {
        return this.instructionsScreenLaunched;
    }

    public boolean isCaptureScreenLaunched() {
        return this.captureScreenLaunched;
    }

    public boolean isCaptureScreenClosed() {
        return this.captureScreenClosed;
    }

    public boolean isCaptureSuccessful() {
        return this.captureSuccessful;
    }

    public boolean isCaptureFailed() {
        return this.captureFailed;
    }

    public boolean isOldDocReviewScreenEvents() {
        return this.oldDocReviewScreenEvents;
    }

    public boolean isApiCallMade() {
        return this.apiCallMade;
    }

    public boolean isApiCallSuccessful() {
        return this.apiCallSuccessful;
    }

    public boolean isApiCallFailed() {
        return this.apiCallFailed;
    }

    public boolean isOtherErrors() {
        return this.otherErrors;
    }
}
