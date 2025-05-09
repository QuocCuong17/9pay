package co.hyperverge.hypersnapsdk.model;

import android.util.DisplayMetrics;
import co.hyperverge.hypersnapsdk.utils.UIUtils;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UICharacterSpacing implements Serializable {
    private float titleTextCharSpacing = 0.0f;
    private float descriptionTextCharSpacing = 0.0f;
    private float statusTextCharSpacing = 0.0f;
    private float documentSideHintTextCharSpacing = 0.0f;
    private float retakeMessageCharSpacing = 0.0f;
    private float primaryButtonTextCharSpacing = 0.0f;
    private float secondaryButtonTextCharSpacing = 0.0f;
    private float alertTextBoxTextCharSpacing = 0.0f;
    private float pickerTextCharSpacing = 0.0f;
    private float countryListItemTextCharSpacing = 0.0f;
    private float countryListItemSelectedTextCharSpacing = 0.0f;
    private float countrySearchTextCharSpacing = 0.0f;
    private float statementHelperTextCharSpacing = 0.0f;
    private float statementTextCharSpacing = 0.0f;
    private float loaderTextCharSpacing = 0.0f;
    private float nfcStatusTextCharSpacing = 0.0f;
    private float apiLoadingTitleTextCharSpacing = 0.0f;
    private float apiLoadingHintTextCharSpacing = 0.0f;

    protected boolean canEqual(Object obj) {
        return obj instanceof UICharacterSpacing;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UICharacterSpacing)) {
            return false;
        }
        UICharacterSpacing uICharacterSpacing = (UICharacterSpacing) obj;
        return uICharacterSpacing.canEqual(this) && Float.compare(getTitleTextCharSpacing(), uICharacterSpacing.getTitleTextCharSpacing()) == 0 && Float.compare(getDescriptionTextCharSpacing(), uICharacterSpacing.getDescriptionTextCharSpacing()) == 0 && Float.compare(getStatusTextCharSpacing(), uICharacterSpacing.getStatusTextCharSpacing()) == 0 && Float.compare(getDocumentSideHintTextCharSpacing(), uICharacterSpacing.getDocumentSideHintTextCharSpacing()) == 0 && Float.compare(getRetakeMessageCharSpacing(), uICharacterSpacing.getRetakeMessageCharSpacing()) == 0 && Float.compare(getPrimaryButtonTextCharSpacing(), uICharacterSpacing.getPrimaryButtonTextCharSpacing()) == 0 && Float.compare(getSecondaryButtonTextCharSpacing(), uICharacterSpacing.getSecondaryButtonTextCharSpacing()) == 0 && Float.compare(getAlertTextBoxTextCharSpacing(), uICharacterSpacing.getAlertTextBoxTextCharSpacing()) == 0 && Float.compare(getPickerTextCharSpacing(), uICharacterSpacing.getPickerTextCharSpacing()) == 0 && Float.compare(getCountryListItemTextCharSpacing(), uICharacterSpacing.getCountryListItemTextCharSpacing()) == 0 && Float.compare(getCountryListItemSelectedTextCharSpacing(), uICharacterSpacing.getCountryListItemSelectedTextCharSpacing()) == 0 && Float.compare(getCountrySearchTextCharSpacing(), uICharacterSpacing.getCountrySearchTextCharSpacing()) == 0 && Float.compare(getStatementHelperTextCharSpacing(), uICharacterSpacing.getStatementHelperTextCharSpacing()) == 0 && Float.compare(getStatementTextCharSpacing(), uICharacterSpacing.getStatementTextCharSpacing()) == 0 && Float.compare(getLoaderTextCharSpacing(), uICharacterSpacing.getLoaderTextCharSpacing()) == 0 && Float.compare(getNfcStatusTextCharSpacing(), uICharacterSpacing.getNfcStatusTextCharSpacing()) == 0 && Float.compare(getApiLoadingTitleTextCharSpacing(), uICharacterSpacing.getApiLoadingTitleTextCharSpacing()) == 0 && Float.compare(getApiLoadingHintTextCharSpacing(), uICharacterSpacing.getApiLoadingHintTextCharSpacing()) == 0;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((Float.floatToIntBits(getTitleTextCharSpacing()) + 59) * 59) + Float.floatToIntBits(getDescriptionTextCharSpacing())) * 59) + Float.floatToIntBits(getStatusTextCharSpacing())) * 59) + Float.floatToIntBits(getDocumentSideHintTextCharSpacing())) * 59) + Float.floatToIntBits(getRetakeMessageCharSpacing())) * 59) + Float.floatToIntBits(getPrimaryButtonTextCharSpacing())) * 59) + Float.floatToIntBits(getSecondaryButtonTextCharSpacing())) * 59) + Float.floatToIntBits(getAlertTextBoxTextCharSpacing())) * 59) + Float.floatToIntBits(getPickerTextCharSpacing())) * 59) + Float.floatToIntBits(getCountryListItemTextCharSpacing())) * 59) + Float.floatToIntBits(getCountryListItemSelectedTextCharSpacing())) * 59) + Float.floatToIntBits(getCountrySearchTextCharSpacing())) * 59) + Float.floatToIntBits(getStatementHelperTextCharSpacing())) * 59) + Float.floatToIntBits(getStatementTextCharSpacing())) * 59) + Float.floatToIntBits(getLoaderTextCharSpacing())) * 59) + Float.floatToIntBits(getNfcStatusTextCharSpacing())) * 59) + Float.floatToIntBits(getApiLoadingTitleTextCharSpacing())) * 59) + Float.floatToIntBits(getApiLoadingHintTextCharSpacing());
    }

    public void setAlertTextBoxTextCharSpacing(float f) {
        this.alertTextBoxTextCharSpacing = f;
    }

    public void setApiLoadingHintTextCharSpacing(float f) {
        this.apiLoadingHintTextCharSpacing = f;
    }

    public void setApiLoadingTitleTextCharSpacing(float f) {
        this.apiLoadingTitleTextCharSpacing = f;
    }

    public void setCountryListItemSelectedTextCharSpacing(float f) {
        this.countryListItemSelectedTextCharSpacing = f;
    }

    public void setCountryListItemTextCharSpacing(float f) {
        this.countryListItemTextCharSpacing = f;
    }

    public void setCountrySearchTextCharSpacing(float f) {
        this.countrySearchTextCharSpacing = f;
    }

    public void setDescriptionTextCharSpacing(float f) {
        this.descriptionTextCharSpacing = f;
    }

    public void setDocumentSideHintTextCharSpacing(float f) {
        this.documentSideHintTextCharSpacing = f;
    }

    public void setLoaderTextCharSpacing(float f) {
        this.loaderTextCharSpacing = f;
    }

    public void setNfcStatusTextCharSpacing(float f) {
        this.nfcStatusTextCharSpacing = f;
    }

    public void setPickerTextCharSpacing(float f) {
        this.pickerTextCharSpacing = f;
    }

    public void setPrimaryButtonTextCharSpacing(float f) {
        this.primaryButtonTextCharSpacing = f;
    }

    public void setRetakeMessageCharSpacing(float f) {
        this.retakeMessageCharSpacing = f;
    }

    public void setSecondaryButtonTextCharSpacing(float f) {
        this.secondaryButtonTextCharSpacing = f;
    }

    public void setStatementHelperTextCharSpacing(float f) {
        this.statementHelperTextCharSpacing = f;
    }

    public void setStatementTextCharSpacing(float f) {
        this.statementTextCharSpacing = f;
    }

    public void setStatusTextCharSpacing(float f) {
        this.statusTextCharSpacing = f;
    }

    public void setTitleTextCharSpacing(float f) {
        this.titleTextCharSpacing = f;
    }

    public String toString() {
        return "UICharacterSpacing(titleTextCharSpacing=" + getTitleTextCharSpacing() + ", descriptionTextCharSpacing=" + getDescriptionTextCharSpacing() + ", statusTextCharSpacing=" + getStatusTextCharSpacing() + ", documentSideHintTextCharSpacing=" + getDocumentSideHintTextCharSpacing() + ", retakeMessageCharSpacing=" + getRetakeMessageCharSpacing() + ", primaryButtonTextCharSpacing=" + getPrimaryButtonTextCharSpacing() + ", secondaryButtonTextCharSpacing=" + getSecondaryButtonTextCharSpacing() + ", alertTextBoxTextCharSpacing=" + getAlertTextBoxTextCharSpacing() + ", pickerTextCharSpacing=" + getPickerTextCharSpacing() + ", countryListItemTextCharSpacing=" + getCountryListItemTextCharSpacing() + ", countryListItemSelectedTextCharSpacing=" + getCountryListItemSelectedTextCharSpacing() + ", countrySearchTextCharSpacing=" + getCountrySearchTextCharSpacing() + ", statementHelperTextCharSpacing=" + getStatementHelperTextCharSpacing() + ", statementTextCharSpacing=" + getStatementTextCharSpacing() + ", loaderTextCharSpacing=" + getLoaderTextCharSpacing() + ", nfcStatusTextCharSpacing=" + getNfcStatusTextCharSpacing() + ", apiLoadingTitleTextCharSpacing=" + getApiLoadingTitleTextCharSpacing() + ", apiLoadingHintTextCharSpacing=" + getApiLoadingHintTextCharSpacing() + ")";
    }

    public float getTitleTextCharSpacing() {
        return this.titleTextCharSpacing;
    }

    public float getDescriptionTextCharSpacing() {
        return this.descriptionTextCharSpacing;
    }

    public float getStatusTextCharSpacing() {
        return this.statusTextCharSpacing;
    }

    public float getDocumentSideHintTextCharSpacing() {
        return this.documentSideHintTextCharSpacing;
    }

    public float getRetakeMessageCharSpacing() {
        return this.retakeMessageCharSpacing;
    }

    public float getPrimaryButtonTextCharSpacing() {
        return this.primaryButtonTextCharSpacing;
    }

    public float getSecondaryButtonTextCharSpacing() {
        return this.secondaryButtonTextCharSpacing;
    }

    public float getAlertTextBoxTextCharSpacing() {
        return this.alertTextBoxTextCharSpacing;
    }

    public float getPickerTextCharSpacing() {
        return this.pickerTextCharSpacing;
    }

    public float getCountryListItemTextCharSpacing() {
        return this.countryListItemTextCharSpacing;
    }

    public float getCountryListItemSelectedTextCharSpacing() {
        return this.countryListItemSelectedTextCharSpacing;
    }

    public float getCountrySearchTextCharSpacing() {
        return this.countrySearchTextCharSpacing;
    }

    public float getStatementHelperTextCharSpacing() {
        return this.statementHelperTextCharSpacing;
    }

    public float getStatementTextCharSpacing() {
        return this.statementTextCharSpacing;
    }

    public float getLoaderTextCharSpacing() {
        return this.loaderTextCharSpacing;
    }

    public float getNfcStatusTextCharSpacing() {
        return this.nfcStatusTextCharSpacing;
    }

    public float getApiLoadingTitleTextCharSpacing() {
        return this.apiLoadingTitleTextCharSpacing;
    }

    public float getApiLoadingHintTextCharSpacing() {
        return this.apiLoadingHintTextCharSpacing;
    }

    public void applyDimension(DisplayMetrics displayMetrics) {
        this.titleTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.titleTextCharSpacing);
        this.descriptionTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.descriptionTextCharSpacing);
        this.statusTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.statusTextCharSpacing);
        this.documentSideHintTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.documentSideHintTextCharSpacing);
        this.retakeMessageCharSpacing = UIUtils.pxToEm(displayMetrics, this.retakeMessageCharSpacing);
        this.primaryButtonTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.primaryButtonTextCharSpacing);
        this.secondaryButtonTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.secondaryButtonTextCharSpacing);
        this.alertTextBoxTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.alertTextBoxTextCharSpacing);
        this.pickerTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.pickerTextCharSpacing);
        this.countryListItemTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.countryListItemTextCharSpacing);
        this.countryListItemSelectedTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.countryListItemSelectedTextCharSpacing);
        this.countrySearchTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.countrySearchTextCharSpacing);
        this.statementHelperTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.statementHelperTextCharSpacing);
        this.statementTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.statementTextCharSpacing);
        this.loaderTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.loaderTextCharSpacing);
        this.nfcStatusTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.nfcStatusTextCharSpacing);
        this.apiLoadingTitleTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.apiLoadingTitleTextCharSpacing);
        this.apiLoadingHintTextCharSpacing = UIUtils.pxToEm(displayMetrics, this.apiLoadingHintTextCharSpacing);
    }
}
