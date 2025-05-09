package co.hyperverge.hypersnapsdk.model;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class UIBorderRadius implements Serializable {
    private float primaryButtonRadius = 5.0f;
    private float secondaryButtonRadius = 5.0f;
    private float pickerBorderRadius = 5.0f;

    protected boolean canEqual(Object obj) {
        return obj instanceof UIBorderRadius;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UIBorderRadius)) {
            return false;
        }
        UIBorderRadius uIBorderRadius = (UIBorderRadius) obj;
        return uIBorderRadius.canEqual(this) && Float.compare(getPrimaryButtonRadius(), uIBorderRadius.getPrimaryButtonRadius()) == 0 && Float.compare(getSecondaryButtonRadius(), uIBorderRadius.getSecondaryButtonRadius()) == 0 && Float.compare(getPickerBorderRadius(), uIBorderRadius.getPickerBorderRadius()) == 0;
    }

    public int hashCode() {
        return ((((Float.floatToIntBits(getPrimaryButtonRadius()) + 59) * 59) + Float.floatToIntBits(getSecondaryButtonRadius())) * 59) + Float.floatToIntBits(getPickerBorderRadius());
    }

    public void setPickerBorderRadius(float f) {
        this.pickerBorderRadius = f;
    }

    public void setPrimaryButtonRadius(float f) {
        this.primaryButtonRadius = f;
    }

    public void setSecondaryButtonRadius(float f) {
        this.secondaryButtonRadius = f;
    }

    public String toString() {
        return "UIBorderRadius(primaryButtonRadius=" + getPrimaryButtonRadius() + ", secondaryButtonRadius=" + getSecondaryButtonRadius() + ", pickerBorderRadius=" + getPickerBorderRadius() + ")";
    }

    public float getPrimaryButtonRadius() {
        return this.primaryButtonRadius;
    }

    public float getSecondaryButtonRadius() {
        return this.secondaryButtonRadius;
    }

    public float getPickerBorderRadius() {
        return this.pickerBorderRadius;
    }

    public void applyDimension(DisplayMetrics displayMetrics) {
        this.primaryButtonRadius = TypedValue.applyDimension(1, this.primaryButtonRadius, displayMetrics);
        this.secondaryButtonRadius = TypedValue.applyDimension(1, this.secondaryButtonRadius, displayMetrics);
        this.pickerBorderRadius = TypedValue.applyDimension(1, this.pickerBorderRadius, displayMetrics);
    }
}
