package co.hyperverge.hypersnapsdk.model;

import co.hyperverge.hypersnapsdk.utils.StringUtils;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIColors implements Serializable {
    private String titleTextColor = "#050552";
    private String descriptionTextColor = "#050552CC";
    private String statusTextColor = "#050552";
    private String captureScreenTitleTextColor = "";
    private String captureScreenDescriptionTextColor = "";
    private String captureScreenStatusTextColor = "";
    private String documentSideHintTextColor = "#050552";
    private String primaryButtonBackgroundColor = "#554EF1";
    private String primaryButtonTextColor = "#FFFFFF";
    private String primaryButtonBorderColor = "#554EF1";
    private String primaryButtonDisabledBackgroundColor = "#CFDDFF";
    private String primaryButtonDisabledBorderColor = "#CFDDFF";
    private String secondaryButtonBackgroundColor = "#FFFFFF";
    private String secondaryButtonTextColor = "#554ef1";
    private String secondaryButtonBorderColor = "#554ef1";
    private String captureButtonColor = "#050552";
    private String pickerTextColor = "#050552";
    private String pickerIconColor = "#554ef1";
    private String pickerBackgroundColor = "#FFFFFF";
    private String pickerBorderColor = "#554ef126";
    private String countryListItemTextColor = "#050552";
    private String countrySearchTextColor = "#050552";
    private String animationPrimaryColor = "";
    private String errorAnimationPrimaryColor = "";
    private String successAnimationPrimaryColor = "";
    private String retakeMessageColor = "#050552";
    private String alertTextBoxTextColor = "#DD4A46";
    private String alertTextBoxBorderColor = "#DD4A46";
    private String alertTextBoxBackgroundColor = "#DD4A460C";
    private String formPlaceHolderTextColor = "#05055266";
    private String statementHelperTextColor = "#050552";
    private String statementTextColor = "#050552";
    private String loaderTextColor = "#ffffff";
    private String nfcStatusTextColor = "#050552";
    private String nfcStatusErrorTextColor = "#DD4A46";
    private String apiLoadingTitleTextColor = "#050552";
    private String apiLoadingHintTextColor = "#050552";

    protected boolean canEqual(Object obj) {
        return obj instanceof UIColors;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIColors)) {
            return false;
        }
        UIColors uIColors = (UIColors) obj;
        if (!uIColors.canEqual(this)) {
            return false;
        }
        String titleTextColor = getTitleTextColor();
        String titleTextColor2 = uIColors.getTitleTextColor();
        if (titleTextColor != null ? !titleTextColor.equals(titleTextColor2) : titleTextColor2 != null) {
            return false;
        }
        String descriptionTextColor = getDescriptionTextColor();
        String descriptionTextColor2 = uIColors.getDescriptionTextColor();
        if (descriptionTextColor != null ? !descriptionTextColor.equals(descriptionTextColor2) : descriptionTextColor2 != null) {
            return false;
        }
        String statusTextColor = getStatusTextColor();
        String statusTextColor2 = uIColors.getStatusTextColor();
        if (statusTextColor != null ? !statusTextColor.equals(statusTextColor2) : statusTextColor2 != null) {
            return false;
        }
        String captureScreenTitleTextColor = getCaptureScreenTitleTextColor();
        String captureScreenTitleTextColor2 = uIColors.getCaptureScreenTitleTextColor();
        if (captureScreenTitleTextColor != null ? !captureScreenTitleTextColor.equals(captureScreenTitleTextColor2) : captureScreenTitleTextColor2 != null) {
            return false;
        }
        String captureScreenDescriptionTextColor = getCaptureScreenDescriptionTextColor();
        String captureScreenDescriptionTextColor2 = uIColors.getCaptureScreenDescriptionTextColor();
        if (captureScreenDescriptionTextColor != null ? !captureScreenDescriptionTextColor.equals(captureScreenDescriptionTextColor2) : captureScreenDescriptionTextColor2 != null) {
            return false;
        }
        String captureScreenStatusTextColor = getCaptureScreenStatusTextColor();
        String captureScreenStatusTextColor2 = uIColors.getCaptureScreenStatusTextColor();
        if (captureScreenStatusTextColor != null ? !captureScreenStatusTextColor.equals(captureScreenStatusTextColor2) : captureScreenStatusTextColor2 != null) {
            return false;
        }
        String documentSideHintTextColor = getDocumentSideHintTextColor();
        String documentSideHintTextColor2 = uIColors.getDocumentSideHintTextColor();
        if (documentSideHintTextColor != null ? !documentSideHintTextColor.equals(documentSideHintTextColor2) : documentSideHintTextColor2 != null) {
            return false;
        }
        String primaryButtonBackgroundColor = getPrimaryButtonBackgroundColor();
        String primaryButtonBackgroundColor2 = uIColors.getPrimaryButtonBackgroundColor();
        if (primaryButtonBackgroundColor != null ? !primaryButtonBackgroundColor.equals(primaryButtonBackgroundColor2) : primaryButtonBackgroundColor2 != null) {
            return false;
        }
        String primaryButtonTextColor = getPrimaryButtonTextColor();
        String primaryButtonTextColor2 = uIColors.getPrimaryButtonTextColor();
        if (primaryButtonTextColor != null ? !primaryButtonTextColor.equals(primaryButtonTextColor2) : primaryButtonTextColor2 != null) {
            return false;
        }
        String primaryButtonBorderColor = getPrimaryButtonBorderColor();
        String primaryButtonBorderColor2 = uIColors.getPrimaryButtonBorderColor();
        if (primaryButtonBorderColor != null ? !primaryButtonBorderColor.equals(primaryButtonBorderColor2) : primaryButtonBorderColor2 != null) {
            return false;
        }
        String primaryButtonDisabledBackgroundColor = getPrimaryButtonDisabledBackgroundColor();
        String primaryButtonDisabledBackgroundColor2 = uIColors.getPrimaryButtonDisabledBackgroundColor();
        if (primaryButtonDisabledBackgroundColor != null ? !primaryButtonDisabledBackgroundColor.equals(primaryButtonDisabledBackgroundColor2) : primaryButtonDisabledBackgroundColor2 != null) {
            return false;
        }
        String primaryButtonDisabledBorderColor = getPrimaryButtonDisabledBorderColor();
        String primaryButtonDisabledBorderColor2 = uIColors.getPrimaryButtonDisabledBorderColor();
        if (primaryButtonDisabledBorderColor != null ? !primaryButtonDisabledBorderColor.equals(primaryButtonDisabledBorderColor2) : primaryButtonDisabledBorderColor2 != null) {
            return false;
        }
        String secondaryButtonBackgroundColor = getSecondaryButtonBackgroundColor();
        String secondaryButtonBackgroundColor2 = uIColors.getSecondaryButtonBackgroundColor();
        if (secondaryButtonBackgroundColor != null ? !secondaryButtonBackgroundColor.equals(secondaryButtonBackgroundColor2) : secondaryButtonBackgroundColor2 != null) {
            return false;
        }
        String secondaryButtonTextColor = getSecondaryButtonTextColor();
        String secondaryButtonTextColor2 = uIColors.getSecondaryButtonTextColor();
        if (secondaryButtonTextColor != null ? !secondaryButtonTextColor.equals(secondaryButtonTextColor2) : secondaryButtonTextColor2 != null) {
            return false;
        }
        String secondaryButtonBorderColor = getSecondaryButtonBorderColor();
        String secondaryButtonBorderColor2 = uIColors.getSecondaryButtonBorderColor();
        if (secondaryButtonBorderColor != null ? !secondaryButtonBorderColor.equals(secondaryButtonBorderColor2) : secondaryButtonBorderColor2 != null) {
            return false;
        }
        String captureButtonColor = getCaptureButtonColor();
        String captureButtonColor2 = uIColors.getCaptureButtonColor();
        if (captureButtonColor != null ? !captureButtonColor.equals(captureButtonColor2) : captureButtonColor2 != null) {
            return false;
        }
        String pickerTextColor = getPickerTextColor();
        String pickerTextColor2 = uIColors.getPickerTextColor();
        if (pickerTextColor != null ? !pickerTextColor.equals(pickerTextColor2) : pickerTextColor2 != null) {
            return false;
        }
        String pickerIconColor = getPickerIconColor();
        String pickerIconColor2 = uIColors.getPickerIconColor();
        if (pickerIconColor != null ? !pickerIconColor.equals(pickerIconColor2) : pickerIconColor2 != null) {
            return false;
        }
        String pickerBackgroundColor = getPickerBackgroundColor();
        String pickerBackgroundColor2 = uIColors.getPickerBackgroundColor();
        if (pickerBackgroundColor != null ? !pickerBackgroundColor.equals(pickerBackgroundColor2) : pickerBackgroundColor2 != null) {
            return false;
        }
        String pickerBorderColor = getPickerBorderColor();
        String pickerBorderColor2 = uIColors.getPickerBorderColor();
        if (pickerBorderColor != null ? !pickerBorderColor.equals(pickerBorderColor2) : pickerBorderColor2 != null) {
            return false;
        }
        String countryListItemTextColor = getCountryListItemTextColor();
        String countryListItemTextColor2 = uIColors.getCountryListItemTextColor();
        if (countryListItemTextColor != null ? !countryListItemTextColor.equals(countryListItemTextColor2) : countryListItemTextColor2 != null) {
            return false;
        }
        String countrySearchTextColor = getCountrySearchTextColor();
        String countrySearchTextColor2 = uIColors.getCountrySearchTextColor();
        if (countrySearchTextColor != null ? !countrySearchTextColor.equals(countrySearchTextColor2) : countrySearchTextColor2 != null) {
            return false;
        }
        String animationPrimaryColor = getAnimationPrimaryColor();
        String animationPrimaryColor2 = uIColors.getAnimationPrimaryColor();
        if (animationPrimaryColor != null ? !animationPrimaryColor.equals(animationPrimaryColor2) : animationPrimaryColor2 != null) {
            return false;
        }
        String errorAnimationPrimaryColor = getErrorAnimationPrimaryColor();
        String errorAnimationPrimaryColor2 = uIColors.getErrorAnimationPrimaryColor();
        if (errorAnimationPrimaryColor != null ? !errorAnimationPrimaryColor.equals(errorAnimationPrimaryColor2) : errorAnimationPrimaryColor2 != null) {
            return false;
        }
        String successAnimationPrimaryColor = getSuccessAnimationPrimaryColor();
        String successAnimationPrimaryColor2 = uIColors.getSuccessAnimationPrimaryColor();
        if (successAnimationPrimaryColor != null ? !successAnimationPrimaryColor.equals(successAnimationPrimaryColor2) : successAnimationPrimaryColor2 != null) {
            return false;
        }
        String retakeMessageColor = getRetakeMessageColor();
        String retakeMessageColor2 = uIColors.getRetakeMessageColor();
        if (retakeMessageColor != null ? !retakeMessageColor.equals(retakeMessageColor2) : retakeMessageColor2 != null) {
            return false;
        }
        String alertTextBoxTextColor = getAlertTextBoxTextColor();
        String alertTextBoxTextColor2 = uIColors.getAlertTextBoxTextColor();
        if (alertTextBoxTextColor != null ? !alertTextBoxTextColor.equals(alertTextBoxTextColor2) : alertTextBoxTextColor2 != null) {
            return false;
        }
        String alertTextBoxBorderColor = getAlertTextBoxBorderColor();
        String alertTextBoxBorderColor2 = uIColors.getAlertTextBoxBorderColor();
        if (alertTextBoxBorderColor != null ? !alertTextBoxBorderColor.equals(alertTextBoxBorderColor2) : alertTextBoxBorderColor2 != null) {
            return false;
        }
        String alertTextBoxBackgroundColor = getAlertTextBoxBackgroundColor();
        String alertTextBoxBackgroundColor2 = uIColors.getAlertTextBoxBackgroundColor();
        if (alertTextBoxBackgroundColor != null ? !alertTextBoxBackgroundColor.equals(alertTextBoxBackgroundColor2) : alertTextBoxBackgroundColor2 != null) {
            return false;
        }
        String formPlaceHolderTextColor = getFormPlaceHolderTextColor();
        String formPlaceHolderTextColor2 = uIColors.getFormPlaceHolderTextColor();
        if (formPlaceHolderTextColor != null ? !formPlaceHolderTextColor.equals(formPlaceHolderTextColor2) : formPlaceHolderTextColor2 != null) {
            return false;
        }
        String statementHelperTextColor = getStatementHelperTextColor();
        String statementHelperTextColor2 = uIColors.getStatementHelperTextColor();
        if (statementHelperTextColor != null ? !statementHelperTextColor.equals(statementHelperTextColor2) : statementHelperTextColor2 != null) {
            return false;
        }
        String statementTextColor = getStatementTextColor();
        String statementTextColor2 = uIColors.getStatementTextColor();
        if (statementTextColor != null ? !statementTextColor.equals(statementTextColor2) : statementTextColor2 != null) {
            return false;
        }
        String loaderTextColor = getLoaderTextColor();
        String loaderTextColor2 = uIColors.getLoaderTextColor();
        if (loaderTextColor != null ? !loaderTextColor.equals(loaderTextColor2) : loaderTextColor2 != null) {
            return false;
        }
        String nfcStatusTextColor = getNfcStatusTextColor();
        String nfcStatusTextColor2 = uIColors.getNfcStatusTextColor();
        if (nfcStatusTextColor != null ? !nfcStatusTextColor.equals(nfcStatusTextColor2) : nfcStatusTextColor2 != null) {
            return false;
        }
        String nfcStatusErrorTextColor = getNfcStatusErrorTextColor();
        String nfcStatusErrorTextColor2 = uIColors.getNfcStatusErrorTextColor();
        if (nfcStatusErrorTextColor != null ? !nfcStatusErrorTextColor.equals(nfcStatusErrorTextColor2) : nfcStatusErrorTextColor2 != null) {
            return false;
        }
        String apiLoadingTitleTextColor = getApiLoadingTitleTextColor();
        String apiLoadingTitleTextColor2 = uIColors.getApiLoadingTitleTextColor();
        if (apiLoadingTitleTextColor != null ? !apiLoadingTitleTextColor.equals(apiLoadingTitleTextColor2) : apiLoadingTitleTextColor2 != null) {
            return false;
        }
        String apiLoadingHintTextColor = getApiLoadingHintTextColor();
        String apiLoadingHintTextColor2 = uIColors.getApiLoadingHintTextColor();
        return apiLoadingHintTextColor != null ? apiLoadingHintTextColor.equals(apiLoadingHintTextColor2) : apiLoadingHintTextColor2 == null;
    }

    public int hashCode() {
        String titleTextColor = getTitleTextColor();
        int hashCode = titleTextColor == null ? 43 : titleTextColor.hashCode();
        String descriptionTextColor = getDescriptionTextColor();
        int hashCode2 = ((hashCode + 59) * 59) + (descriptionTextColor == null ? 43 : descriptionTextColor.hashCode());
        String statusTextColor = getStatusTextColor();
        int hashCode3 = (hashCode2 * 59) + (statusTextColor == null ? 43 : statusTextColor.hashCode());
        String captureScreenTitleTextColor = getCaptureScreenTitleTextColor();
        int hashCode4 = (hashCode3 * 59) + (captureScreenTitleTextColor == null ? 43 : captureScreenTitleTextColor.hashCode());
        String captureScreenDescriptionTextColor = getCaptureScreenDescriptionTextColor();
        int hashCode5 = (hashCode4 * 59) + (captureScreenDescriptionTextColor == null ? 43 : captureScreenDescriptionTextColor.hashCode());
        String captureScreenStatusTextColor = getCaptureScreenStatusTextColor();
        int hashCode6 = (hashCode5 * 59) + (captureScreenStatusTextColor == null ? 43 : captureScreenStatusTextColor.hashCode());
        String documentSideHintTextColor = getDocumentSideHintTextColor();
        int hashCode7 = (hashCode6 * 59) + (documentSideHintTextColor == null ? 43 : documentSideHintTextColor.hashCode());
        String primaryButtonBackgroundColor = getPrimaryButtonBackgroundColor();
        int hashCode8 = (hashCode7 * 59) + (primaryButtonBackgroundColor == null ? 43 : primaryButtonBackgroundColor.hashCode());
        String primaryButtonTextColor = getPrimaryButtonTextColor();
        int hashCode9 = (hashCode8 * 59) + (primaryButtonTextColor == null ? 43 : primaryButtonTextColor.hashCode());
        String primaryButtonBorderColor = getPrimaryButtonBorderColor();
        int hashCode10 = (hashCode9 * 59) + (primaryButtonBorderColor == null ? 43 : primaryButtonBorderColor.hashCode());
        String primaryButtonDisabledBackgroundColor = getPrimaryButtonDisabledBackgroundColor();
        int hashCode11 = (hashCode10 * 59) + (primaryButtonDisabledBackgroundColor == null ? 43 : primaryButtonDisabledBackgroundColor.hashCode());
        String primaryButtonDisabledBorderColor = getPrimaryButtonDisabledBorderColor();
        int hashCode12 = (hashCode11 * 59) + (primaryButtonDisabledBorderColor == null ? 43 : primaryButtonDisabledBorderColor.hashCode());
        String secondaryButtonBackgroundColor = getSecondaryButtonBackgroundColor();
        int hashCode13 = (hashCode12 * 59) + (secondaryButtonBackgroundColor == null ? 43 : secondaryButtonBackgroundColor.hashCode());
        String secondaryButtonTextColor = getSecondaryButtonTextColor();
        int hashCode14 = (hashCode13 * 59) + (secondaryButtonTextColor == null ? 43 : secondaryButtonTextColor.hashCode());
        String secondaryButtonBorderColor = getSecondaryButtonBorderColor();
        int hashCode15 = (hashCode14 * 59) + (secondaryButtonBorderColor == null ? 43 : secondaryButtonBorderColor.hashCode());
        String captureButtonColor = getCaptureButtonColor();
        int hashCode16 = (hashCode15 * 59) + (captureButtonColor == null ? 43 : captureButtonColor.hashCode());
        String pickerTextColor = getPickerTextColor();
        int hashCode17 = (hashCode16 * 59) + (pickerTextColor == null ? 43 : pickerTextColor.hashCode());
        String pickerIconColor = getPickerIconColor();
        int hashCode18 = (hashCode17 * 59) + (pickerIconColor == null ? 43 : pickerIconColor.hashCode());
        String pickerBackgroundColor = getPickerBackgroundColor();
        int hashCode19 = (hashCode18 * 59) + (pickerBackgroundColor == null ? 43 : pickerBackgroundColor.hashCode());
        String pickerBorderColor = getPickerBorderColor();
        int hashCode20 = (hashCode19 * 59) + (pickerBorderColor == null ? 43 : pickerBorderColor.hashCode());
        String countryListItemTextColor = getCountryListItemTextColor();
        int hashCode21 = (hashCode20 * 59) + (countryListItemTextColor == null ? 43 : countryListItemTextColor.hashCode());
        String countrySearchTextColor = getCountrySearchTextColor();
        int hashCode22 = (hashCode21 * 59) + (countrySearchTextColor == null ? 43 : countrySearchTextColor.hashCode());
        String animationPrimaryColor = getAnimationPrimaryColor();
        int hashCode23 = (hashCode22 * 59) + (animationPrimaryColor == null ? 43 : animationPrimaryColor.hashCode());
        String errorAnimationPrimaryColor = getErrorAnimationPrimaryColor();
        int hashCode24 = (hashCode23 * 59) + (errorAnimationPrimaryColor == null ? 43 : errorAnimationPrimaryColor.hashCode());
        String successAnimationPrimaryColor = getSuccessAnimationPrimaryColor();
        int hashCode25 = (hashCode24 * 59) + (successAnimationPrimaryColor == null ? 43 : successAnimationPrimaryColor.hashCode());
        String retakeMessageColor = getRetakeMessageColor();
        int hashCode26 = (hashCode25 * 59) + (retakeMessageColor == null ? 43 : retakeMessageColor.hashCode());
        String alertTextBoxTextColor = getAlertTextBoxTextColor();
        int hashCode27 = (hashCode26 * 59) + (alertTextBoxTextColor == null ? 43 : alertTextBoxTextColor.hashCode());
        String alertTextBoxBorderColor = getAlertTextBoxBorderColor();
        int hashCode28 = (hashCode27 * 59) + (alertTextBoxBorderColor == null ? 43 : alertTextBoxBorderColor.hashCode());
        String alertTextBoxBackgroundColor = getAlertTextBoxBackgroundColor();
        int hashCode29 = (hashCode28 * 59) + (alertTextBoxBackgroundColor == null ? 43 : alertTextBoxBackgroundColor.hashCode());
        String formPlaceHolderTextColor = getFormPlaceHolderTextColor();
        int hashCode30 = (hashCode29 * 59) + (formPlaceHolderTextColor == null ? 43 : formPlaceHolderTextColor.hashCode());
        String statementHelperTextColor = getStatementHelperTextColor();
        int hashCode31 = (hashCode30 * 59) + (statementHelperTextColor == null ? 43 : statementHelperTextColor.hashCode());
        String statementTextColor = getStatementTextColor();
        int hashCode32 = (hashCode31 * 59) + (statementTextColor == null ? 43 : statementTextColor.hashCode());
        String loaderTextColor = getLoaderTextColor();
        int hashCode33 = (hashCode32 * 59) + (loaderTextColor == null ? 43 : loaderTextColor.hashCode());
        String nfcStatusTextColor = getNfcStatusTextColor();
        int hashCode34 = (hashCode33 * 59) + (nfcStatusTextColor == null ? 43 : nfcStatusTextColor.hashCode());
        String nfcStatusErrorTextColor = getNfcStatusErrorTextColor();
        int hashCode35 = (hashCode34 * 59) + (nfcStatusErrorTextColor == null ? 43 : nfcStatusErrorTextColor.hashCode());
        String apiLoadingTitleTextColor = getApiLoadingTitleTextColor();
        int hashCode36 = (hashCode35 * 59) + (apiLoadingTitleTextColor == null ? 43 : apiLoadingTitleTextColor.hashCode());
        String apiLoadingHintTextColor = getApiLoadingHintTextColor();
        return (hashCode36 * 59) + (apiLoadingHintTextColor != null ? apiLoadingHintTextColor.hashCode() : 43);
    }

    public void setAlertTextBoxBackgroundColor(String str) {
        this.alertTextBoxBackgroundColor = str;
    }

    public void setAlertTextBoxBorderColor(String str) {
        this.alertTextBoxBorderColor = str;
    }

    public void setAlertTextBoxTextColor(String str) {
        this.alertTextBoxTextColor = str;
    }

    public void setAnimationPrimaryColor(String str) {
        this.animationPrimaryColor = str;
    }

    public void setApiLoadingHintTextColor(String str) {
        this.apiLoadingHintTextColor = str;
    }

    public void setApiLoadingTitleTextColor(String str) {
        this.apiLoadingTitleTextColor = str;
    }

    public void setCaptureButtonColor(String str) {
        this.captureButtonColor = str;
    }

    public void setCaptureScreenDescriptionTextColor(String str) {
        this.captureScreenDescriptionTextColor = str;
    }

    public void setCaptureScreenStatusTextColor(String str) {
        this.captureScreenStatusTextColor = str;
    }

    public void setCaptureScreenTitleTextColor(String str) {
        this.captureScreenTitleTextColor = str;
    }

    public void setCountryListItemTextColor(String str) {
        this.countryListItemTextColor = str;
    }

    public void setCountrySearchTextColor(String str) {
        this.countrySearchTextColor = str;
    }

    public void setDescriptionTextColor(String str) {
        this.descriptionTextColor = str;
    }

    public void setDocumentSideHintTextColor(String str) {
        this.documentSideHintTextColor = str;
    }

    public void setErrorAnimationPrimaryColor(String str) {
        this.errorAnimationPrimaryColor = str;
    }

    public void setFormPlaceHolderTextColor(String str) {
        this.formPlaceHolderTextColor = str;
    }

    public void setLoaderTextColor(String str) {
        this.loaderTextColor = str;
    }

    public void setNfcStatusErrorTextColor(String str) {
        this.nfcStatusErrorTextColor = str;
    }

    public void setNfcStatusTextColor(String str) {
        this.nfcStatusTextColor = str;
    }

    public void setPickerBackgroundColor(String str) {
        this.pickerBackgroundColor = str;
    }

    public void setPickerBorderColor(String str) {
        this.pickerBorderColor = str;
    }

    public void setPickerIconColor(String str) {
        this.pickerIconColor = str;
    }

    public void setPickerTextColor(String str) {
        this.pickerTextColor = str;
    }

    public void setPrimaryButtonBackgroundColor(String str) {
        this.primaryButtonBackgroundColor = str;
    }

    public void setPrimaryButtonBorderColor(String str) {
        this.primaryButtonBorderColor = str;
    }

    public void setPrimaryButtonDisabledBackgroundColor(String str) {
        this.primaryButtonDisabledBackgroundColor = str;
    }

    public void setPrimaryButtonDisabledBorderColor(String str) {
        this.primaryButtonDisabledBorderColor = str;
    }

    public void setPrimaryButtonTextColor(String str) {
        this.primaryButtonTextColor = str;
    }

    public void setRetakeMessageColor(String str) {
        this.retakeMessageColor = str;
    }

    public void setSecondaryButtonBackgroundColor(String str) {
        this.secondaryButtonBackgroundColor = str;
    }

    public void setSecondaryButtonBorderColor(String str) {
        this.secondaryButtonBorderColor = str;
    }

    public void setSecondaryButtonTextColor(String str) {
        this.secondaryButtonTextColor = str;
    }

    public void setStatementHelperTextColor(String str) {
        this.statementHelperTextColor = str;
    }

    public void setStatementTextColor(String str) {
        this.statementTextColor = str;
    }

    public void setStatusTextColor(String str) {
        this.statusTextColor = str;
    }

    public void setSuccessAnimationPrimaryColor(String str) {
        this.successAnimationPrimaryColor = str;
    }

    public void setTitleTextColor(String str) {
        this.titleTextColor = str;
    }

    public String toString() {
        return "UIColors(titleTextColor=" + getTitleTextColor() + ", descriptionTextColor=" + getDescriptionTextColor() + ", statusTextColor=" + getStatusTextColor() + ", captureScreenTitleTextColor=" + getCaptureScreenTitleTextColor() + ", captureScreenDescriptionTextColor=" + getCaptureScreenDescriptionTextColor() + ", captureScreenStatusTextColor=" + getCaptureScreenStatusTextColor() + ", documentSideHintTextColor=" + getDocumentSideHintTextColor() + ", primaryButtonBackgroundColor=" + getPrimaryButtonBackgroundColor() + ", primaryButtonTextColor=" + getPrimaryButtonTextColor() + ", primaryButtonBorderColor=" + getPrimaryButtonBorderColor() + ", primaryButtonDisabledBackgroundColor=" + getPrimaryButtonDisabledBackgroundColor() + ", primaryButtonDisabledBorderColor=" + getPrimaryButtonDisabledBorderColor() + ", secondaryButtonBackgroundColor=" + getSecondaryButtonBackgroundColor() + ", secondaryButtonTextColor=" + getSecondaryButtonTextColor() + ", secondaryButtonBorderColor=" + getSecondaryButtonBorderColor() + ", captureButtonColor=" + getCaptureButtonColor() + ", pickerTextColor=" + getPickerTextColor() + ", pickerIconColor=" + getPickerIconColor() + ", pickerBackgroundColor=" + getPickerBackgroundColor() + ", pickerBorderColor=" + getPickerBorderColor() + ", countryListItemTextColor=" + getCountryListItemTextColor() + ", countrySearchTextColor=" + getCountrySearchTextColor() + ", animationPrimaryColor=" + getAnimationPrimaryColor() + ", errorAnimationPrimaryColor=" + getErrorAnimationPrimaryColor() + ", successAnimationPrimaryColor=" + getSuccessAnimationPrimaryColor() + ", retakeMessageColor=" + getRetakeMessageColor() + ", alertTextBoxTextColor=" + getAlertTextBoxTextColor() + ", alertTextBoxBorderColor=" + getAlertTextBoxBorderColor() + ", alertTextBoxBackgroundColor=" + getAlertTextBoxBackgroundColor() + ", formPlaceHolderTextColor=" + getFormPlaceHolderTextColor() + ", statementHelperTextColor=" + getStatementHelperTextColor() + ", statementTextColor=" + getStatementTextColor() + ", loaderTextColor=" + getLoaderTextColor() + ", nfcStatusTextColor=" + getNfcStatusTextColor() + ", nfcStatusErrorTextColor=" + getNfcStatusErrorTextColor() + ", apiLoadingTitleTextColor=" + getApiLoadingTitleTextColor() + ", apiLoadingHintTextColor=" + getApiLoadingHintTextColor() + ")";
    }

    public String getTitleTextColor() {
        return this.titleTextColor;
    }

    public String getDescriptionTextColor() {
        return this.descriptionTextColor;
    }

    public String getStatusTextColor() {
        return this.statusTextColor;
    }

    public String getCaptureScreenTitleTextColor() {
        return this.captureScreenTitleTextColor;
    }

    public String getCaptureScreenDescriptionTextColor() {
        return this.captureScreenDescriptionTextColor;
    }

    public String getCaptureScreenStatusTextColor() {
        return this.captureScreenStatusTextColor;
    }

    public String getDocumentSideHintTextColor() {
        return this.documentSideHintTextColor;
    }

    public String getPrimaryButtonBackgroundColor() {
        return this.primaryButtonBackgroundColor;
    }

    public String getPrimaryButtonTextColor() {
        return this.primaryButtonTextColor;
    }

    public String getPrimaryButtonBorderColor() {
        return this.primaryButtonBorderColor;
    }

    public String getPrimaryButtonDisabledBackgroundColor() {
        return this.primaryButtonDisabledBackgroundColor;
    }

    public String getPrimaryButtonDisabledBorderColor() {
        return this.primaryButtonDisabledBorderColor;
    }

    public String getSecondaryButtonBackgroundColor() {
        return this.secondaryButtonBackgroundColor;
    }

    public String getSecondaryButtonTextColor() {
        return this.secondaryButtonTextColor;
    }

    public String getSecondaryButtonBorderColor() {
        return this.secondaryButtonBorderColor;
    }

    public String getCaptureButtonColor() {
        return this.captureButtonColor;
    }

    public String getPickerTextColor() {
        return this.pickerTextColor;
    }

    public String getPickerIconColor() {
        return this.pickerIconColor;
    }

    public String getPickerBackgroundColor() {
        return this.pickerBackgroundColor;
    }

    public String getPickerBorderColor() {
        return this.pickerBorderColor;
    }

    public String getCountryListItemTextColor() {
        return this.countryListItemTextColor;
    }

    public String getCountrySearchTextColor() {
        return this.countrySearchTextColor;
    }

    public String getAnimationPrimaryColor() {
        return this.animationPrimaryColor;
    }

    public String getErrorAnimationPrimaryColor() {
        return this.errorAnimationPrimaryColor;
    }

    public String getSuccessAnimationPrimaryColor() {
        return this.successAnimationPrimaryColor;
    }

    public String getRetakeMessageColor() {
        return this.retakeMessageColor;
    }

    public String getAlertTextBoxTextColor() {
        return this.alertTextBoxTextColor;
    }

    public String getAlertTextBoxBorderColor() {
        return this.alertTextBoxBorderColor;
    }

    public String getAlertTextBoxBackgroundColor() {
        return this.alertTextBoxBackgroundColor;
    }

    public String getFormPlaceHolderTextColor() {
        return this.formPlaceHolderTextColor;
    }

    public String getStatementHelperTextColor() {
        return this.statementHelperTextColor;
    }

    public String getStatementTextColor() {
        return this.statementTextColor;
    }

    public String getLoaderTextColor() {
        return this.loaderTextColor;
    }

    public String getNfcStatusTextColor() {
        return this.nfcStatusTextColor;
    }

    public String getNfcStatusErrorTextColor() {
        return this.nfcStatusErrorTextColor;
    }

    public String getApiLoadingTitleTextColor() {
        return this.apiLoadingTitleTextColor;
    }

    public String getApiLoadingHintTextColor() {
        return this.apiLoadingHintTextColor;
    }

    public void convertColors() {
        this.titleTextColor = getConvertedColor(this.titleTextColor);
        this.captureScreenTitleTextColor = getConvertedColor(this.captureScreenTitleTextColor);
        this.descriptionTextColor = getConvertedColor(this.descriptionTextColor);
        this.captureScreenDescriptionTextColor = getConvertedColor(this.captureScreenDescriptionTextColor);
        this.statusTextColor = getConvertedColor(this.statusTextColor);
        this.captureScreenStatusTextColor = getConvertedColor(this.captureScreenStatusTextColor);
        this.documentSideHintTextColor = getConvertedColor(this.documentSideHintTextColor);
        this.primaryButtonBackgroundColor = getConvertedColor(this.primaryButtonBackgroundColor);
        this.primaryButtonTextColor = getConvertedColor(this.primaryButtonTextColor);
        this.primaryButtonBorderColor = getConvertedColor(this.primaryButtonBorderColor);
        this.primaryButtonDisabledBackgroundColor = getConvertedColor(this.primaryButtonDisabledBackgroundColor);
        this.primaryButtonDisabledBorderColor = getConvertedColor(this.primaryButtonDisabledBorderColor);
        this.secondaryButtonBackgroundColor = getConvertedColor(this.secondaryButtonBackgroundColor);
        this.secondaryButtonTextColor = getConvertedColor(this.secondaryButtonTextColor);
        this.secondaryButtonBorderColor = getConvertedColor(this.secondaryButtonBorderColor);
        this.captureButtonColor = getConvertedColor(this.captureButtonColor);
        this.animationPrimaryColor = getConvertedColor(this.animationPrimaryColor);
        this.errorAnimationPrimaryColor = getConvertedColor(this.errorAnimationPrimaryColor);
        this.successAnimationPrimaryColor = getConvertedColor(this.successAnimationPrimaryColor);
        this.retakeMessageColor = getConvertedColor(this.retakeMessageColor);
        this.alertTextBoxTextColor = getConvertedColor(this.alertTextBoxTextColor);
        this.alertTextBoxBorderColor = getConvertedColor(this.alertTextBoxBorderColor);
        this.alertTextBoxBackgroundColor = getConvertedColor(this.alertTextBoxBackgroundColor);
        this.pickerTextColor = getConvertedColor(this.pickerTextColor);
        this.pickerIconColor = getConvertedColor(this.pickerIconColor);
        this.pickerBackgroundColor = getConvertedColor(this.pickerBackgroundColor);
        this.pickerBorderColor = getConvertedColor(this.pickerBorderColor);
        this.countryListItemTextColor = getConvertedColor(this.countryListItemTextColor);
        this.countrySearchTextColor = getConvertedColor(this.countrySearchTextColor);
        this.formPlaceHolderTextColor = getConvertedColor(this.formPlaceHolderTextColor);
        this.statementHelperTextColor = getConvertedColor(this.statementHelperTextColor);
        this.statementTextColor = getConvertedColor(this.statementTextColor);
        this.loaderTextColor = getConvertedColor(this.loaderTextColor);
        this.nfcStatusTextColor = getConvertedColor(this.nfcStatusTextColor);
        this.nfcStatusErrorTextColor = getConvertedColor(this.nfcStatusErrorTextColor);
        this.apiLoadingTitleTextColor = getConvertedColor(this.apiLoadingTitleTextColor);
        this.apiLoadingHintTextColor = getConvertedColor(this.apiLoadingHintTextColor);
    }

    private String getConvertedColor(String str) {
        return !StringUtils.isEmptyOrNull(str) && str.length() == 9 ? UIUtils.toARGB(str) : str;
    }
}
