package co.hyperverge.hypersnapsdk.objects;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class HVBaseConfig implements Serializable {
    private List<Integer> allowedStatusCodes;
    private String captureTimeoutDialogDesc;
    private String captureTimeoutDialogTitle;
    private String closeAlertDialogDesc;
    private String closeAlertDialogTitle;
    private String errorReviewRetakeButton;
    private int errorReviewScreenDescTypeface;
    private int errorReviewScreenRetakeButtonTypeface;
    private int errorReviewScreenTitleTypeface;
    private String errorReviewTitle;
    private boolean shouldShowCloseAlert = false;
    private boolean showTrustLogos = false;

    public String getCaptureTimeoutDialogTitle() {
        return this.captureTimeoutDialogTitle;
    }

    public void setCaptureTimeoutDialogTitle(String str) {
        this.captureTimeoutDialogTitle = str;
    }

    public String getCaptureTimeoutDialogDesc() {
        return this.captureTimeoutDialogDesc;
    }

    public void setCaptureTimeoutDialogDesc(String str) {
        this.captureTimeoutDialogDesc = str;
    }

    public int getErrorReviewScreenTitleTypeface() {
        return this.errorReviewScreenTitleTypeface;
    }

    public void setErrorReviewScreenTitleTypeface(int i) {
        this.errorReviewScreenTitleTypeface = i;
    }

    public int getErrorReviewScreenDescTypeface() {
        return this.errorReviewScreenDescTypeface;
    }

    public void setErrorReviewScreenDescTypeface(int i) {
        this.errorReviewScreenDescTypeface = i;
    }

    public int getErroReviewScreenRetakeButtonTypeface() {
        return this.errorReviewScreenRetakeButtonTypeface;
    }

    public void setErroReviewScreenRetakeButtonTypeface(int i) {
        this.errorReviewScreenRetakeButtonTypeface = i;
    }

    public String getErrorReviewTitle() {
        return this.errorReviewTitle;
    }

    public void setErrorReviewTitle(String str) {
        this.errorReviewTitle = str;
    }

    public String getErrorReviewRetakeButton() {
        return this.errorReviewRetakeButton;
    }

    public void setErrorReviewRetakeButton(String str) {
        this.errorReviewRetakeButton = str;
    }

    public boolean shouldShowCloseAlert() {
        return this.shouldShowCloseAlert;
    }

    public void setShouldShowCloseAlert(boolean z) {
        this.shouldShowCloseAlert = z;
    }

    public String getCloseAlertDialogTitle() {
        return this.closeAlertDialogTitle;
    }

    public void setCloseAlertDialogTitle(String str) {
        this.closeAlertDialogTitle = str;
    }

    public String getCloseAlertDialogDesc() {
        return this.closeAlertDialogDesc;
    }

    public void setCloseAlertDialogDesc(String str) {
        this.closeAlertDialogDesc = str;
    }

    @Deprecated
    public boolean shouldShowTrustLogos() {
        return this.showTrustLogos;
    }

    @Deprecated
    public void setShowTrustLogos(boolean z) {
        this.showTrustLogos = z;
    }

    public List<Integer> getAllowedStatusCodes() {
        List<Integer> list = this.allowedStatusCodes;
        if (list == null || list.isEmpty()) {
            return getDefaultStatusCodes();
        }
        return this.allowedStatusCodes;
    }

    public void setAllowedStatusCodes(List<Integer> list) {
        this.allowedStatusCodes = list;
    }

    private List<Integer> getDefaultStatusCodes() {
        ArrayList arrayList = new ArrayList();
        for (int i = 200; i <= 208; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        arrayList.add(226);
        ArrayList arrayList2 = new ArrayList(arrayList);
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 433; i2 <= 450; i2++) {
            arrayList3.add(Integer.valueOf(i2));
        }
        arrayList3.add(Integer.valueOf(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE));
        arrayList3.add(Integer.valueOf(TypedValues.CycleType.TYPE_WAVE_PERIOD));
        arrayList2.addAll(arrayList3);
        return arrayList2;
    }
}
