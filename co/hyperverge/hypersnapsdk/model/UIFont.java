package co.hyperverge.hypersnapsdk.model;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIFont implements Serializable {
    private String titleTextFont = "mulish";
    private String descriptionTextFont = "mulish";
    private String statusTextFont = "mulish";
    private String documentSideHintTextFont = "mulish";
    private String retakeMessageFont = "mulish";
    private String primaryButtonTextFont = "mulish";
    private String secondaryButtonTextFont = "mulish";
    private String alertTextBoxTextFont = "mulish";
    private String pickerTextFont = "mulish";
    private String countryListItemTextFont = "mulish";
    private String countryListItemSelectedTextFont = "mulish";
    private String countrySearchTextFont = "mulish";
    private String statementHelperTextFont = "mulish";
    private String statementTextFont = "mulish";
    private String loaderTextFont = "mulish";
    private String nfcStatusTextFont = "mulish";
    private String apiLoadingTitleTextFont = "mulish";
    private String apiLoadingHintTextFont = "mulish";

    protected boolean canEqual(Object obj) {
        return obj instanceof UIFont;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIFont)) {
            return false;
        }
        UIFont uIFont = (UIFont) obj;
        if (!uIFont.canEqual(this)) {
            return false;
        }
        String titleTextFont = getTitleTextFont();
        String titleTextFont2 = uIFont.getTitleTextFont();
        if (titleTextFont != null ? !titleTextFont.equals(titleTextFont2) : titleTextFont2 != null) {
            return false;
        }
        String descriptionTextFont = getDescriptionTextFont();
        String descriptionTextFont2 = uIFont.getDescriptionTextFont();
        if (descriptionTextFont != null ? !descriptionTextFont.equals(descriptionTextFont2) : descriptionTextFont2 != null) {
            return false;
        }
        String statusTextFont = getStatusTextFont();
        String statusTextFont2 = uIFont.getStatusTextFont();
        if (statusTextFont != null ? !statusTextFont.equals(statusTextFont2) : statusTextFont2 != null) {
            return false;
        }
        String documentSideHintTextFont = getDocumentSideHintTextFont();
        String documentSideHintTextFont2 = uIFont.getDocumentSideHintTextFont();
        if (documentSideHintTextFont != null ? !documentSideHintTextFont.equals(documentSideHintTextFont2) : documentSideHintTextFont2 != null) {
            return false;
        }
        String retakeMessageFont = getRetakeMessageFont();
        String retakeMessageFont2 = uIFont.getRetakeMessageFont();
        if (retakeMessageFont != null ? !retakeMessageFont.equals(retakeMessageFont2) : retakeMessageFont2 != null) {
            return false;
        }
        String primaryButtonTextFont = getPrimaryButtonTextFont();
        String primaryButtonTextFont2 = uIFont.getPrimaryButtonTextFont();
        if (primaryButtonTextFont != null ? !primaryButtonTextFont.equals(primaryButtonTextFont2) : primaryButtonTextFont2 != null) {
            return false;
        }
        String secondaryButtonTextFont = getSecondaryButtonTextFont();
        String secondaryButtonTextFont2 = uIFont.getSecondaryButtonTextFont();
        if (secondaryButtonTextFont != null ? !secondaryButtonTextFont.equals(secondaryButtonTextFont2) : secondaryButtonTextFont2 != null) {
            return false;
        }
        String alertTextBoxTextFont = getAlertTextBoxTextFont();
        String alertTextBoxTextFont2 = uIFont.getAlertTextBoxTextFont();
        if (alertTextBoxTextFont != null ? !alertTextBoxTextFont.equals(alertTextBoxTextFont2) : alertTextBoxTextFont2 != null) {
            return false;
        }
        String pickerTextFont = getPickerTextFont();
        String pickerTextFont2 = uIFont.getPickerTextFont();
        if (pickerTextFont != null ? !pickerTextFont.equals(pickerTextFont2) : pickerTextFont2 != null) {
            return false;
        }
        String countryListItemTextFont = getCountryListItemTextFont();
        String countryListItemTextFont2 = uIFont.getCountryListItemTextFont();
        if (countryListItemTextFont != null ? !countryListItemTextFont.equals(countryListItemTextFont2) : countryListItemTextFont2 != null) {
            return false;
        }
        String countryListItemSelectedTextFont = getCountryListItemSelectedTextFont();
        String countryListItemSelectedTextFont2 = uIFont.getCountryListItemSelectedTextFont();
        if (countryListItemSelectedTextFont != null ? !countryListItemSelectedTextFont.equals(countryListItemSelectedTextFont2) : countryListItemSelectedTextFont2 != null) {
            return false;
        }
        String countrySearchTextFont = getCountrySearchTextFont();
        String countrySearchTextFont2 = uIFont.getCountrySearchTextFont();
        if (countrySearchTextFont != null ? !countrySearchTextFont.equals(countrySearchTextFont2) : countrySearchTextFont2 != null) {
            return false;
        }
        String statementHelperTextFont = getStatementHelperTextFont();
        String statementHelperTextFont2 = uIFont.getStatementHelperTextFont();
        if (statementHelperTextFont != null ? !statementHelperTextFont.equals(statementHelperTextFont2) : statementHelperTextFont2 != null) {
            return false;
        }
        String statementTextFont = getStatementTextFont();
        String statementTextFont2 = uIFont.getStatementTextFont();
        if (statementTextFont != null ? !statementTextFont.equals(statementTextFont2) : statementTextFont2 != null) {
            return false;
        }
        String loaderTextFont = getLoaderTextFont();
        String loaderTextFont2 = uIFont.getLoaderTextFont();
        if (loaderTextFont != null ? !loaderTextFont.equals(loaderTextFont2) : loaderTextFont2 != null) {
            return false;
        }
        String nfcStatusTextFont = getNfcStatusTextFont();
        String nfcStatusTextFont2 = uIFont.getNfcStatusTextFont();
        if (nfcStatusTextFont != null ? !nfcStatusTextFont.equals(nfcStatusTextFont2) : nfcStatusTextFont2 != null) {
            return false;
        }
        String apiLoadingTitleTextFont = getApiLoadingTitleTextFont();
        String apiLoadingTitleTextFont2 = uIFont.getApiLoadingTitleTextFont();
        if (apiLoadingTitleTextFont != null ? !apiLoadingTitleTextFont.equals(apiLoadingTitleTextFont2) : apiLoadingTitleTextFont2 != null) {
            return false;
        }
        String apiLoadingHintTextFont = getApiLoadingHintTextFont();
        String apiLoadingHintTextFont2 = uIFont.getApiLoadingHintTextFont();
        return apiLoadingHintTextFont != null ? apiLoadingHintTextFont.equals(apiLoadingHintTextFont2) : apiLoadingHintTextFont2 == null;
    }

    public int hashCode() {
        String titleTextFont = getTitleTextFont();
        int hashCode = titleTextFont == null ? 43 : titleTextFont.hashCode();
        String descriptionTextFont = getDescriptionTextFont();
        int hashCode2 = ((hashCode + 59) * 59) + (descriptionTextFont == null ? 43 : descriptionTextFont.hashCode());
        String statusTextFont = getStatusTextFont();
        int hashCode3 = (hashCode2 * 59) + (statusTextFont == null ? 43 : statusTextFont.hashCode());
        String documentSideHintTextFont = getDocumentSideHintTextFont();
        int hashCode4 = (hashCode3 * 59) + (documentSideHintTextFont == null ? 43 : documentSideHintTextFont.hashCode());
        String retakeMessageFont = getRetakeMessageFont();
        int hashCode5 = (hashCode4 * 59) + (retakeMessageFont == null ? 43 : retakeMessageFont.hashCode());
        String primaryButtonTextFont = getPrimaryButtonTextFont();
        int hashCode6 = (hashCode5 * 59) + (primaryButtonTextFont == null ? 43 : primaryButtonTextFont.hashCode());
        String secondaryButtonTextFont = getSecondaryButtonTextFont();
        int hashCode7 = (hashCode6 * 59) + (secondaryButtonTextFont == null ? 43 : secondaryButtonTextFont.hashCode());
        String alertTextBoxTextFont = getAlertTextBoxTextFont();
        int hashCode8 = (hashCode7 * 59) + (alertTextBoxTextFont == null ? 43 : alertTextBoxTextFont.hashCode());
        String pickerTextFont = getPickerTextFont();
        int hashCode9 = (hashCode8 * 59) + (pickerTextFont == null ? 43 : pickerTextFont.hashCode());
        String countryListItemTextFont = getCountryListItemTextFont();
        int hashCode10 = (hashCode9 * 59) + (countryListItemTextFont == null ? 43 : countryListItemTextFont.hashCode());
        String countryListItemSelectedTextFont = getCountryListItemSelectedTextFont();
        int hashCode11 = (hashCode10 * 59) + (countryListItemSelectedTextFont == null ? 43 : countryListItemSelectedTextFont.hashCode());
        String countrySearchTextFont = getCountrySearchTextFont();
        int hashCode12 = (hashCode11 * 59) + (countrySearchTextFont == null ? 43 : countrySearchTextFont.hashCode());
        String statementHelperTextFont = getStatementHelperTextFont();
        int hashCode13 = (hashCode12 * 59) + (statementHelperTextFont == null ? 43 : statementHelperTextFont.hashCode());
        String statementTextFont = getStatementTextFont();
        int hashCode14 = (hashCode13 * 59) + (statementTextFont == null ? 43 : statementTextFont.hashCode());
        String loaderTextFont = getLoaderTextFont();
        int hashCode15 = (hashCode14 * 59) + (loaderTextFont == null ? 43 : loaderTextFont.hashCode());
        String nfcStatusTextFont = getNfcStatusTextFont();
        int hashCode16 = (hashCode15 * 59) + (nfcStatusTextFont == null ? 43 : nfcStatusTextFont.hashCode());
        String apiLoadingTitleTextFont = getApiLoadingTitleTextFont();
        int hashCode17 = (hashCode16 * 59) + (apiLoadingTitleTextFont == null ? 43 : apiLoadingTitleTextFont.hashCode());
        String apiLoadingHintTextFont = getApiLoadingHintTextFont();
        return (hashCode17 * 59) + (apiLoadingHintTextFont != null ? apiLoadingHintTextFont.hashCode() : 43);
    }

    public void setAlertTextBoxTextFont(String str) {
        this.alertTextBoxTextFont = str;
    }

    public void setApiLoadingHintTextFont(String str) {
        this.apiLoadingHintTextFont = str;
    }

    public void setApiLoadingTitleTextFont(String str) {
        this.apiLoadingTitleTextFont = str;
    }

    public void setCountryListItemSelectedTextFont(String str) {
        this.countryListItemSelectedTextFont = str;
    }

    public void setCountryListItemTextFont(String str) {
        this.countryListItemTextFont = str;
    }

    public void setCountrySearchTextFont(String str) {
        this.countrySearchTextFont = str;
    }

    public void setDescriptionTextFont(String str) {
        this.descriptionTextFont = str;
    }

    public void setDocumentSideHintTextFont(String str) {
        this.documentSideHintTextFont = str;
    }

    public void setLoaderTextFont(String str) {
        this.loaderTextFont = str;
    }

    public void setNfcStatusTextFont(String str) {
        this.nfcStatusTextFont = str;
    }

    public void setPickerTextFont(String str) {
        this.pickerTextFont = str;
    }

    public void setPrimaryButtonTextFont(String str) {
        this.primaryButtonTextFont = str;
    }

    public void setRetakeMessageFont(String str) {
        this.retakeMessageFont = str;
    }

    public void setSecondaryButtonTextFont(String str) {
        this.secondaryButtonTextFont = str;
    }

    public void setStatementHelperTextFont(String str) {
        this.statementHelperTextFont = str;
    }

    public void setStatementTextFont(String str) {
        this.statementTextFont = str;
    }

    public void setStatusTextFont(String str) {
        this.statusTextFont = str;
    }

    public void setTitleTextFont(String str) {
        this.titleTextFont = str;
    }

    public String toString() {
        return "UIFont(titleTextFont=" + getTitleTextFont() + ", descriptionTextFont=" + getDescriptionTextFont() + ", statusTextFont=" + getStatusTextFont() + ", documentSideHintTextFont=" + getDocumentSideHintTextFont() + ", retakeMessageFont=" + getRetakeMessageFont() + ", primaryButtonTextFont=" + getPrimaryButtonTextFont() + ", secondaryButtonTextFont=" + getSecondaryButtonTextFont() + ", alertTextBoxTextFont=" + getAlertTextBoxTextFont() + ", pickerTextFont=" + getPickerTextFont() + ", countryListItemTextFont=" + getCountryListItemTextFont() + ", countryListItemSelectedTextFont=" + getCountryListItemSelectedTextFont() + ", countrySearchTextFont=" + getCountrySearchTextFont() + ", statementHelperTextFont=" + getStatementHelperTextFont() + ", statementTextFont=" + getStatementTextFont() + ", loaderTextFont=" + getLoaderTextFont() + ", nfcStatusTextFont=" + getNfcStatusTextFont() + ", apiLoadingTitleTextFont=" + getApiLoadingTitleTextFont() + ", apiLoadingHintTextFont=" + getApiLoadingHintTextFont() + ")";
    }

    public String getTitleTextFont() {
        return this.titleTextFont;
    }

    public String getDescriptionTextFont() {
        return this.descriptionTextFont;
    }

    public String getStatusTextFont() {
        return this.statusTextFont;
    }

    public String getDocumentSideHintTextFont() {
        return this.documentSideHintTextFont;
    }

    public String getRetakeMessageFont() {
        return this.retakeMessageFont;
    }

    public String getPrimaryButtonTextFont() {
        return this.primaryButtonTextFont;
    }

    public String getSecondaryButtonTextFont() {
        return this.secondaryButtonTextFont;
    }

    public String getAlertTextBoxTextFont() {
        return this.alertTextBoxTextFont;
    }

    public String getPickerTextFont() {
        return this.pickerTextFont;
    }

    public String getCountryListItemTextFont() {
        return this.countryListItemTextFont;
    }

    public String getCountryListItemSelectedTextFont() {
        return this.countryListItemSelectedTextFont;
    }

    public String getCountrySearchTextFont() {
        return this.countrySearchTextFont;
    }

    public String getStatementHelperTextFont() {
        return this.statementHelperTextFont;
    }

    public String getStatementTextFont() {
        return this.statementTextFont;
    }

    public String getLoaderTextFont() {
        return this.loaderTextFont;
    }

    public String getNfcStatusTextFont() {
        return this.nfcStatusTextFont;
    }

    public String getApiLoadingTitleTextFont() {
        return this.apiLoadingTitleTextFont;
    }

    public String getApiLoadingHintTextFont() {
        return this.apiLoadingHintTextFont;
    }
}
