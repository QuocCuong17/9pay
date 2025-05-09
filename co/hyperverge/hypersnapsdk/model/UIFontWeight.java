package co.hyperverge.hypersnapsdk.model;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIFontWeight implements Serializable {
    private String titleTextWeight = TtmlNode.BOLD;
    private String descriptionTextWeight = "regular";
    private String statusTextWeight = TtmlNode.BOLD;
    private String documentSideHintTextWeight = "regular";
    private String retakeMessageWeight = "regular";
    private String primaryButtonTextWeight = "semibold";
    private String secondaryButtonTextWeight = "semibold";
    private String alertTextBoxTextWeight = "regular";
    private String pickerTextWeight = "semibold";
    private String countryListItemTextFontWeight = "regular";
    private String countryListItemSelectedTextFontWeight = TtmlNode.BOLD;
    private String countrySearchTextFontWeight = "semibold";
    private String statementHelperTextWeight = TtmlNode.BOLD;
    private String statementTextWeight = TtmlNode.BOLD;
    private String loaderTextWeight = "regular";
    private String nfcStatusTextWeight = TtmlNode.BOLD;
    private String apiLoadingTitleTextWeight = TtmlNode.BOLD;
    private String apiLoadingHintTextWeight = "regular";

    protected boolean canEqual(Object obj) {
        return obj instanceof UIFontWeight;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIFontWeight)) {
            return false;
        }
        UIFontWeight uIFontWeight = (UIFontWeight) obj;
        if (!uIFontWeight.canEqual(this)) {
            return false;
        }
        String titleTextWeight = getTitleTextWeight();
        String titleTextWeight2 = uIFontWeight.getTitleTextWeight();
        if (titleTextWeight != null ? !titleTextWeight.equals(titleTextWeight2) : titleTextWeight2 != null) {
            return false;
        }
        String descriptionTextWeight = getDescriptionTextWeight();
        String descriptionTextWeight2 = uIFontWeight.getDescriptionTextWeight();
        if (descriptionTextWeight != null ? !descriptionTextWeight.equals(descriptionTextWeight2) : descriptionTextWeight2 != null) {
            return false;
        }
        String statusTextWeight = getStatusTextWeight();
        String statusTextWeight2 = uIFontWeight.getStatusTextWeight();
        if (statusTextWeight != null ? !statusTextWeight.equals(statusTextWeight2) : statusTextWeight2 != null) {
            return false;
        }
        String documentSideHintTextWeight = getDocumentSideHintTextWeight();
        String documentSideHintTextWeight2 = uIFontWeight.getDocumentSideHintTextWeight();
        if (documentSideHintTextWeight != null ? !documentSideHintTextWeight.equals(documentSideHintTextWeight2) : documentSideHintTextWeight2 != null) {
            return false;
        }
        String retakeMessageWeight = getRetakeMessageWeight();
        String retakeMessageWeight2 = uIFontWeight.getRetakeMessageWeight();
        if (retakeMessageWeight != null ? !retakeMessageWeight.equals(retakeMessageWeight2) : retakeMessageWeight2 != null) {
            return false;
        }
        String primaryButtonTextWeight = getPrimaryButtonTextWeight();
        String primaryButtonTextWeight2 = uIFontWeight.getPrimaryButtonTextWeight();
        if (primaryButtonTextWeight != null ? !primaryButtonTextWeight.equals(primaryButtonTextWeight2) : primaryButtonTextWeight2 != null) {
            return false;
        }
        String secondaryButtonTextWeight = getSecondaryButtonTextWeight();
        String secondaryButtonTextWeight2 = uIFontWeight.getSecondaryButtonTextWeight();
        if (secondaryButtonTextWeight != null ? !secondaryButtonTextWeight.equals(secondaryButtonTextWeight2) : secondaryButtonTextWeight2 != null) {
            return false;
        }
        String alertTextBoxTextWeight = getAlertTextBoxTextWeight();
        String alertTextBoxTextWeight2 = uIFontWeight.getAlertTextBoxTextWeight();
        if (alertTextBoxTextWeight != null ? !alertTextBoxTextWeight.equals(alertTextBoxTextWeight2) : alertTextBoxTextWeight2 != null) {
            return false;
        }
        String pickerTextWeight = getPickerTextWeight();
        String pickerTextWeight2 = uIFontWeight.getPickerTextWeight();
        if (pickerTextWeight != null ? !pickerTextWeight.equals(pickerTextWeight2) : pickerTextWeight2 != null) {
            return false;
        }
        String countryListItemTextFontWeight = getCountryListItemTextFontWeight();
        String countryListItemTextFontWeight2 = uIFontWeight.getCountryListItemTextFontWeight();
        if (countryListItemTextFontWeight != null ? !countryListItemTextFontWeight.equals(countryListItemTextFontWeight2) : countryListItemTextFontWeight2 != null) {
            return false;
        }
        String countryListItemSelectedTextFontWeight = getCountryListItemSelectedTextFontWeight();
        String countryListItemSelectedTextFontWeight2 = uIFontWeight.getCountryListItemSelectedTextFontWeight();
        if (countryListItemSelectedTextFontWeight != null ? !countryListItemSelectedTextFontWeight.equals(countryListItemSelectedTextFontWeight2) : countryListItemSelectedTextFontWeight2 != null) {
            return false;
        }
        String countrySearchTextFontWeight = getCountrySearchTextFontWeight();
        String countrySearchTextFontWeight2 = uIFontWeight.getCountrySearchTextFontWeight();
        if (countrySearchTextFontWeight != null ? !countrySearchTextFontWeight.equals(countrySearchTextFontWeight2) : countrySearchTextFontWeight2 != null) {
            return false;
        }
        String statementHelperTextWeight = getStatementHelperTextWeight();
        String statementHelperTextWeight2 = uIFontWeight.getStatementHelperTextWeight();
        if (statementHelperTextWeight != null ? !statementHelperTextWeight.equals(statementHelperTextWeight2) : statementHelperTextWeight2 != null) {
            return false;
        }
        String statementTextWeight = getStatementTextWeight();
        String statementTextWeight2 = uIFontWeight.getStatementTextWeight();
        if (statementTextWeight != null ? !statementTextWeight.equals(statementTextWeight2) : statementTextWeight2 != null) {
            return false;
        }
        String loaderTextWeight = getLoaderTextWeight();
        String loaderTextWeight2 = uIFontWeight.getLoaderTextWeight();
        if (loaderTextWeight != null ? !loaderTextWeight.equals(loaderTextWeight2) : loaderTextWeight2 != null) {
            return false;
        }
        String nfcStatusTextWeight = getNfcStatusTextWeight();
        String nfcStatusTextWeight2 = uIFontWeight.getNfcStatusTextWeight();
        if (nfcStatusTextWeight != null ? !nfcStatusTextWeight.equals(nfcStatusTextWeight2) : nfcStatusTextWeight2 != null) {
            return false;
        }
        String apiLoadingTitleTextWeight = getApiLoadingTitleTextWeight();
        String apiLoadingTitleTextWeight2 = uIFontWeight.getApiLoadingTitleTextWeight();
        if (apiLoadingTitleTextWeight != null ? !apiLoadingTitleTextWeight.equals(apiLoadingTitleTextWeight2) : apiLoadingTitleTextWeight2 != null) {
            return false;
        }
        String apiLoadingHintTextWeight = getApiLoadingHintTextWeight();
        String apiLoadingHintTextWeight2 = uIFontWeight.getApiLoadingHintTextWeight();
        return apiLoadingHintTextWeight != null ? apiLoadingHintTextWeight.equals(apiLoadingHintTextWeight2) : apiLoadingHintTextWeight2 == null;
    }

    public int hashCode() {
        String titleTextWeight = getTitleTextWeight();
        int hashCode = titleTextWeight == null ? 43 : titleTextWeight.hashCode();
        String descriptionTextWeight = getDescriptionTextWeight();
        int hashCode2 = ((hashCode + 59) * 59) + (descriptionTextWeight == null ? 43 : descriptionTextWeight.hashCode());
        String statusTextWeight = getStatusTextWeight();
        int hashCode3 = (hashCode2 * 59) + (statusTextWeight == null ? 43 : statusTextWeight.hashCode());
        String documentSideHintTextWeight = getDocumentSideHintTextWeight();
        int hashCode4 = (hashCode3 * 59) + (documentSideHintTextWeight == null ? 43 : documentSideHintTextWeight.hashCode());
        String retakeMessageWeight = getRetakeMessageWeight();
        int hashCode5 = (hashCode4 * 59) + (retakeMessageWeight == null ? 43 : retakeMessageWeight.hashCode());
        String primaryButtonTextWeight = getPrimaryButtonTextWeight();
        int hashCode6 = (hashCode5 * 59) + (primaryButtonTextWeight == null ? 43 : primaryButtonTextWeight.hashCode());
        String secondaryButtonTextWeight = getSecondaryButtonTextWeight();
        int hashCode7 = (hashCode6 * 59) + (secondaryButtonTextWeight == null ? 43 : secondaryButtonTextWeight.hashCode());
        String alertTextBoxTextWeight = getAlertTextBoxTextWeight();
        int hashCode8 = (hashCode7 * 59) + (alertTextBoxTextWeight == null ? 43 : alertTextBoxTextWeight.hashCode());
        String pickerTextWeight = getPickerTextWeight();
        int hashCode9 = (hashCode8 * 59) + (pickerTextWeight == null ? 43 : pickerTextWeight.hashCode());
        String countryListItemTextFontWeight = getCountryListItemTextFontWeight();
        int hashCode10 = (hashCode9 * 59) + (countryListItemTextFontWeight == null ? 43 : countryListItemTextFontWeight.hashCode());
        String countryListItemSelectedTextFontWeight = getCountryListItemSelectedTextFontWeight();
        int hashCode11 = (hashCode10 * 59) + (countryListItemSelectedTextFontWeight == null ? 43 : countryListItemSelectedTextFontWeight.hashCode());
        String countrySearchTextFontWeight = getCountrySearchTextFontWeight();
        int hashCode12 = (hashCode11 * 59) + (countrySearchTextFontWeight == null ? 43 : countrySearchTextFontWeight.hashCode());
        String statementHelperTextWeight = getStatementHelperTextWeight();
        int hashCode13 = (hashCode12 * 59) + (statementHelperTextWeight == null ? 43 : statementHelperTextWeight.hashCode());
        String statementTextWeight = getStatementTextWeight();
        int hashCode14 = (hashCode13 * 59) + (statementTextWeight == null ? 43 : statementTextWeight.hashCode());
        String loaderTextWeight = getLoaderTextWeight();
        int hashCode15 = (hashCode14 * 59) + (loaderTextWeight == null ? 43 : loaderTextWeight.hashCode());
        String nfcStatusTextWeight = getNfcStatusTextWeight();
        int hashCode16 = (hashCode15 * 59) + (nfcStatusTextWeight == null ? 43 : nfcStatusTextWeight.hashCode());
        String apiLoadingTitleTextWeight = getApiLoadingTitleTextWeight();
        int hashCode17 = (hashCode16 * 59) + (apiLoadingTitleTextWeight == null ? 43 : apiLoadingTitleTextWeight.hashCode());
        String apiLoadingHintTextWeight = getApiLoadingHintTextWeight();
        return (hashCode17 * 59) + (apiLoadingHintTextWeight != null ? apiLoadingHintTextWeight.hashCode() : 43);
    }

    public void setAlertTextBoxTextWeight(String str) {
        this.alertTextBoxTextWeight = str;
    }

    public void setApiLoadingHintTextWeight(String str) {
        this.apiLoadingHintTextWeight = str;
    }

    public void setApiLoadingTitleTextWeight(String str) {
        this.apiLoadingTitleTextWeight = str;
    }

    public void setCountryListItemSelectedTextFontWeight(String str) {
        this.countryListItemSelectedTextFontWeight = str;
    }

    public void setCountryListItemTextFontWeight(String str) {
        this.countryListItemTextFontWeight = str;
    }

    public void setCountrySearchTextFontWeight(String str) {
        this.countrySearchTextFontWeight = str;
    }

    public void setDescriptionTextWeight(String str) {
        this.descriptionTextWeight = str;
    }

    public void setDocumentSideHintTextWeight(String str) {
        this.documentSideHintTextWeight = str;
    }

    public void setLoaderTextWeight(String str) {
        this.loaderTextWeight = str;
    }

    public void setNfcStatusTextWeight(String str) {
        this.nfcStatusTextWeight = str;
    }

    public void setPickerTextWeight(String str) {
        this.pickerTextWeight = str;
    }

    public void setPrimaryButtonTextWeight(String str) {
        this.primaryButtonTextWeight = str;
    }

    public void setRetakeMessageWeight(String str) {
        this.retakeMessageWeight = str;
    }

    public void setSecondaryButtonTextWeight(String str) {
        this.secondaryButtonTextWeight = str;
    }

    public void setStatementHelperTextWeight(String str) {
        this.statementHelperTextWeight = str;
    }

    public void setStatementTextWeight(String str) {
        this.statementTextWeight = str;
    }

    public void setStatusTextWeight(String str) {
        this.statusTextWeight = str;
    }

    public void setTitleTextWeight(String str) {
        this.titleTextWeight = str;
    }

    public String toString() {
        return "UIFontWeight(titleTextWeight=" + getTitleTextWeight() + ", descriptionTextWeight=" + getDescriptionTextWeight() + ", statusTextWeight=" + getStatusTextWeight() + ", documentSideHintTextWeight=" + getDocumentSideHintTextWeight() + ", retakeMessageWeight=" + getRetakeMessageWeight() + ", primaryButtonTextWeight=" + getPrimaryButtonTextWeight() + ", secondaryButtonTextWeight=" + getSecondaryButtonTextWeight() + ", alertTextBoxTextWeight=" + getAlertTextBoxTextWeight() + ", pickerTextWeight=" + getPickerTextWeight() + ", countryListItemTextFontWeight=" + getCountryListItemTextFontWeight() + ", countryListItemSelectedTextFontWeight=" + getCountryListItemSelectedTextFontWeight() + ", countrySearchTextFontWeight=" + getCountrySearchTextFontWeight() + ", statementHelperTextWeight=" + getStatementHelperTextWeight() + ", statementTextWeight=" + getStatementTextWeight() + ", loaderTextWeight=" + getLoaderTextWeight() + ", nfcStatusTextWeight=" + getNfcStatusTextWeight() + ", apiLoadingTitleTextWeight=" + getApiLoadingTitleTextWeight() + ", apiLoadingHintTextWeight=" + getApiLoadingHintTextWeight() + ")";
    }

    public String getTitleTextWeight() {
        return this.titleTextWeight;
    }

    public String getDescriptionTextWeight() {
        return this.descriptionTextWeight;
    }

    public String getStatusTextWeight() {
        return this.statusTextWeight;
    }

    public String getDocumentSideHintTextWeight() {
        return this.documentSideHintTextWeight;
    }

    public String getRetakeMessageWeight() {
        return this.retakeMessageWeight;
    }

    public String getPrimaryButtonTextWeight() {
        return this.primaryButtonTextWeight;
    }

    public String getSecondaryButtonTextWeight() {
        return this.secondaryButtonTextWeight;
    }

    public String getAlertTextBoxTextWeight() {
        return this.alertTextBoxTextWeight;
    }

    public String getPickerTextWeight() {
        return this.pickerTextWeight;
    }

    public String getCountryListItemTextFontWeight() {
        return this.countryListItemTextFontWeight;
    }

    public String getCountryListItemSelectedTextFontWeight() {
        return this.countryListItemSelectedTextFontWeight;
    }

    public String getCountrySearchTextFontWeight() {
        return this.countrySearchTextFontWeight;
    }

    public String getStatementHelperTextWeight() {
        return this.statementHelperTextWeight;
    }

    public String getStatementTextWeight() {
        return this.statementTextWeight;
    }

    public String getLoaderTextWeight() {
        return this.loaderTextWeight;
    }

    public String getNfcStatusTextWeight() {
        return this.nfcStatusTextWeight;
    }

    public String getApiLoadingTitleTextWeight() {
        return this.apiLoadingTitleTextWeight;
    }

    public String getApiLoadingHintTextWeight() {
        return this.apiLoadingHintTextWeight;
    }
}
