package co.hyperverge.hypersnapsdk.model;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UILineHeight implements Serializable {
    private float titleTextLineHeight = 0.0f;
    private float descriptionTextLineHeight = 0.0f;
    private float statusTextLineHeight = 0.0f;
    private float documentSideHintTextLineHeight = 0.0f;
    private float retakeMessageLineHeight = 0.0f;
    private float primaryButtonTextLineHeight = 0.0f;
    private float secondaryButtonTextLineHeight = 0.0f;
    private float alertTextBoxTextLineHeight = 0.0f;
    private float pickerTextLineHeight = 0.0f;
    private float countryListItemTextLineHeight = 0.0f;
    private float countryListItemSelectedTextLineHeight = 0.0f;
    private float countrySearchTextLineHeight = 0.0f;
    private float statementHelperTextLineHeight = 0.0f;
    private float statementTextLineHeight = 0.0f;
    private float loaderTextLineHeight = 0.0f;
    private float nfcStatusTextLineHeight = 0.0f;
    private float apiLoadingTitleTextLineHeight = 0.0f;
    private float apiLoadingHintTextLineHeight = 0.0f;

    protected boolean canEqual(Object obj) {
        return obj instanceof UILineHeight;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UILineHeight)) {
            return false;
        }
        UILineHeight uILineHeight = (UILineHeight) obj;
        return uILineHeight.canEqual(this) && Float.compare(getTitleTextLineHeight(), uILineHeight.getTitleTextLineHeight()) == 0 && Float.compare(getDescriptionTextLineHeight(), uILineHeight.getDescriptionTextLineHeight()) == 0 && Float.compare(getStatusTextLineHeight(), uILineHeight.getStatusTextLineHeight()) == 0 && Float.compare(getDocumentSideHintTextLineHeight(), uILineHeight.getDocumentSideHintTextLineHeight()) == 0 && Float.compare(getRetakeMessageLineHeight(), uILineHeight.getRetakeMessageLineHeight()) == 0 && Float.compare(getPrimaryButtonTextLineHeight(), uILineHeight.getPrimaryButtonTextLineHeight()) == 0 && Float.compare(getSecondaryButtonTextLineHeight(), uILineHeight.getSecondaryButtonTextLineHeight()) == 0 && Float.compare(getAlertTextBoxTextLineHeight(), uILineHeight.getAlertTextBoxTextLineHeight()) == 0 && Float.compare(getPickerTextLineHeight(), uILineHeight.getPickerTextLineHeight()) == 0 && Float.compare(getCountryListItemTextLineHeight(), uILineHeight.getCountryListItemTextLineHeight()) == 0 && Float.compare(getCountryListItemSelectedTextLineHeight(), uILineHeight.getCountryListItemSelectedTextLineHeight()) == 0 && Float.compare(getCountrySearchTextLineHeight(), uILineHeight.getCountrySearchTextLineHeight()) == 0 && Float.compare(getStatementHelperTextLineHeight(), uILineHeight.getStatementHelperTextLineHeight()) == 0 && Float.compare(getStatementTextLineHeight(), uILineHeight.getStatementTextLineHeight()) == 0 && Float.compare(getLoaderTextLineHeight(), uILineHeight.getLoaderTextLineHeight()) == 0 && Float.compare(getNfcStatusTextLineHeight(), uILineHeight.getNfcStatusTextLineHeight()) == 0 && Float.compare(getApiLoadingTitleTextLineHeight(), uILineHeight.getApiLoadingTitleTextLineHeight()) == 0 && Float.compare(getApiLoadingHintTextLineHeight(), uILineHeight.getApiLoadingHintTextLineHeight()) == 0;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((Float.floatToIntBits(getTitleTextLineHeight()) + 59) * 59) + Float.floatToIntBits(getDescriptionTextLineHeight())) * 59) + Float.floatToIntBits(getStatusTextLineHeight())) * 59) + Float.floatToIntBits(getDocumentSideHintTextLineHeight())) * 59) + Float.floatToIntBits(getRetakeMessageLineHeight())) * 59) + Float.floatToIntBits(getPrimaryButtonTextLineHeight())) * 59) + Float.floatToIntBits(getSecondaryButtonTextLineHeight())) * 59) + Float.floatToIntBits(getAlertTextBoxTextLineHeight())) * 59) + Float.floatToIntBits(getPickerTextLineHeight())) * 59) + Float.floatToIntBits(getCountryListItemTextLineHeight())) * 59) + Float.floatToIntBits(getCountryListItemSelectedTextLineHeight())) * 59) + Float.floatToIntBits(getCountrySearchTextLineHeight())) * 59) + Float.floatToIntBits(getStatementHelperTextLineHeight())) * 59) + Float.floatToIntBits(getStatementTextLineHeight())) * 59) + Float.floatToIntBits(getLoaderTextLineHeight())) * 59) + Float.floatToIntBits(getNfcStatusTextLineHeight())) * 59) + Float.floatToIntBits(getApiLoadingTitleTextLineHeight())) * 59) + Float.floatToIntBits(getApiLoadingHintTextLineHeight());
    }

    public void setAlertTextBoxTextLineHeight(float f) {
        this.alertTextBoxTextLineHeight = f;
    }

    public void setApiLoadingHintTextLineHeight(float f) {
        this.apiLoadingHintTextLineHeight = f;
    }

    public void setApiLoadingTitleTextLineHeight(float f) {
        this.apiLoadingTitleTextLineHeight = f;
    }

    public void setCountryListItemSelectedTextLineHeight(float f) {
        this.countryListItemSelectedTextLineHeight = f;
    }

    public void setCountryListItemTextLineHeight(float f) {
        this.countryListItemTextLineHeight = f;
    }

    public void setCountrySearchTextLineHeight(float f) {
        this.countrySearchTextLineHeight = f;
    }

    public void setDescriptionTextLineHeight(float f) {
        this.descriptionTextLineHeight = f;
    }

    public void setDocumentSideHintTextLineHeight(float f) {
        this.documentSideHintTextLineHeight = f;
    }

    public void setLoaderTextLineHeight(float f) {
        this.loaderTextLineHeight = f;
    }

    public void setNfcStatusTextLineHeight(float f) {
        this.nfcStatusTextLineHeight = f;
    }

    public void setPickerTextLineHeight(float f) {
        this.pickerTextLineHeight = f;
    }

    public void setPrimaryButtonTextLineHeight(float f) {
        this.primaryButtonTextLineHeight = f;
    }

    public void setRetakeMessageLineHeight(float f) {
        this.retakeMessageLineHeight = f;
    }

    public void setSecondaryButtonTextLineHeight(float f) {
        this.secondaryButtonTextLineHeight = f;
    }

    public void setStatementHelperTextLineHeight(float f) {
        this.statementHelperTextLineHeight = f;
    }

    public void setStatementTextLineHeight(float f) {
        this.statementTextLineHeight = f;
    }

    public void setStatusTextLineHeight(float f) {
        this.statusTextLineHeight = f;
    }

    public void setTitleTextLineHeight(float f) {
        this.titleTextLineHeight = f;
    }

    public String toString() {
        return "UILineHeight(titleTextLineHeight=" + getTitleTextLineHeight() + ", descriptionTextLineHeight=" + getDescriptionTextLineHeight() + ", statusTextLineHeight=" + getStatusTextLineHeight() + ", documentSideHintTextLineHeight=" + getDocumentSideHintTextLineHeight() + ", retakeMessageLineHeight=" + getRetakeMessageLineHeight() + ", primaryButtonTextLineHeight=" + getPrimaryButtonTextLineHeight() + ", secondaryButtonTextLineHeight=" + getSecondaryButtonTextLineHeight() + ", alertTextBoxTextLineHeight=" + getAlertTextBoxTextLineHeight() + ", pickerTextLineHeight=" + getPickerTextLineHeight() + ", countryListItemTextLineHeight=" + getCountryListItemTextLineHeight() + ", countryListItemSelectedTextLineHeight=" + getCountryListItemSelectedTextLineHeight() + ", countrySearchTextLineHeight=" + getCountrySearchTextLineHeight() + ", statementHelperTextLineHeight=" + getStatementHelperTextLineHeight() + ", statementTextLineHeight=" + getStatementTextLineHeight() + ", loaderTextLineHeight=" + getLoaderTextLineHeight() + ", nfcStatusTextLineHeight=" + getNfcStatusTextLineHeight() + ", apiLoadingTitleTextLineHeight=" + getApiLoadingTitleTextLineHeight() + ", apiLoadingHintTextLineHeight=" + getApiLoadingHintTextLineHeight() + ")";
    }

    public float getTitleTextLineHeight() {
        return this.titleTextLineHeight;
    }

    public float getDescriptionTextLineHeight() {
        return this.descriptionTextLineHeight;
    }

    public float getStatusTextLineHeight() {
        return this.statusTextLineHeight;
    }

    public float getDocumentSideHintTextLineHeight() {
        return this.documentSideHintTextLineHeight;
    }

    public float getRetakeMessageLineHeight() {
        return this.retakeMessageLineHeight;
    }

    public float getPrimaryButtonTextLineHeight() {
        return this.primaryButtonTextLineHeight;
    }

    public float getSecondaryButtonTextLineHeight() {
        return this.secondaryButtonTextLineHeight;
    }

    public float getAlertTextBoxTextLineHeight() {
        return this.alertTextBoxTextLineHeight;
    }

    public float getPickerTextLineHeight() {
        return this.pickerTextLineHeight;
    }

    public float getCountryListItemTextLineHeight() {
        return this.countryListItemTextLineHeight;
    }

    public float getCountryListItemSelectedTextLineHeight() {
        return this.countryListItemSelectedTextLineHeight;
    }

    public float getCountrySearchTextLineHeight() {
        return this.countrySearchTextLineHeight;
    }

    public float getStatementHelperTextLineHeight() {
        return this.statementHelperTextLineHeight;
    }

    public float getStatementTextLineHeight() {
        return this.statementTextLineHeight;
    }

    public float getLoaderTextLineHeight() {
        return this.loaderTextLineHeight;
    }

    public float getNfcStatusTextLineHeight() {
        return this.nfcStatusTextLineHeight;
    }

    public float getApiLoadingTitleTextLineHeight() {
        return this.apiLoadingTitleTextLineHeight;
    }

    public float getApiLoadingHintTextLineHeight() {
        return this.apiLoadingHintTextLineHeight;
    }

    public void applyDimension(DisplayMetrics displayMetrics) {
        this.titleTextLineHeight = TypedValue.applyDimension(1, this.titleTextLineHeight, displayMetrics);
        this.descriptionTextLineHeight = TypedValue.applyDimension(1, this.descriptionTextLineHeight, displayMetrics);
        this.statusTextLineHeight = TypedValue.applyDimension(1, this.statusTextLineHeight, displayMetrics);
        this.documentSideHintTextLineHeight = TypedValue.applyDimension(1, this.documentSideHintTextLineHeight, displayMetrics);
        this.retakeMessageLineHeight = TypedValue.applyDimension(1, this.retakeMessageLineHeight, displayMetrics);
        this.primaryButtonTextLineHeight = TypedValue.applyDimension(1, this.primaryButtonTextLineHeight, displayMetrics);
        this.secondaryButtonTextLineHeight = TypedValue.applyDimension(1, this.secondaryButtonTextLineHeight, displayMetrics);
        this.alertTextBoxTextLineHeight = TypedValue.applyDimension(1, this.alertTextBoxTextLineHeight, displayMetrics);
        this.pickerTextLineHeight = TypedValue.applyDimension(1, this.pickerTextLineHeight, displayMetrics);
        this.countryListItemTextLineHeight = TypedValue.applyDimension(1, this.countryListItemTextLineHeight, displayMetrics);
        this.countryListItemSelectedTextLineHeight = TypedValue.applyDimension(1, this.countryListItemSelectedTextLineHeight, displayMetrics);
        this.countrySearchTextLineHeight = TypedValue.applyDimension(1, this.countrySearchTextLineHeight, displayMetrics);
        this.statementHelperTextLineHeight = TypedValue.applyDimension(1, this.statementHelperTextLineHeight, displayMetrics);
        this.statementTextLineHeight = TypedValue.applyDimension(1, this.statementTextLineHeight, displayMetrics);
        this.loaderTextLineHeight = TypedValue.applyDimension(1, this.loaderTextLineHeight, displayMetrics);
        this.nfcStatusTextLineHeight = TypedValue.applyDimension(1, this.nfcStatusTextLineHeight, displayMetrics);
        this.apiLoadingTitleTextLineHeight = TypedValue.applyDimension(1, this.apiLoadingTitleTextLineHeight, displayMetrics);
        this.apiLoadingHintTextLineHeight = TypedValue.applyDimension(1, this.apiLoadingHintTextLineHeight, displayMetrics);
    }
}
