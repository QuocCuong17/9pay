package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;

/* loaded from: classes5.dex */
public class PDUserProperty extends PDDictionaryWrapper {
    private final PDUserAttributeObject userAttributeObject;

    public PDUserProperty(PDUserAttributeObject pDUserAttributeObject) {
        this.userAttributeObject = pDUserAttributeObject;
    }

    public PDUserProperty(COSDictionary cOSDictionary, PDUserAttributeObject pDUserAttributeObject) {
        super(cOSDictionary);
        this.userAttributeObject = pDUserAttributeObject;
    }

    public String getName() {
        return getCOSDictionary().getNameAsString(COSName.N);
    }

    public void setName(String str) {
        potentiallyNotifyChanged(getName(), str);
        getCOSDictionary().setName(COSName.N, str);
    }

    public COSBase getValue() {
        return getCOSDictionary().getDictionaryObject(COSName.V);
    }

    public void setValue(COSBase cOSBase) {
        potentiallyNotifyChanged(getValue(), cOSBase);
        getCOSDictionary().setItem(COSName.V, cOSBase);
    }

    public String getFormattedValue() {
        return getCOSDictionary().getString(COSName.F);
    }

    public void setFormattedValue(String str) {
        potentiallyNotifyChanged(getFormattedValue(), str);
        getCOSDictionary().setString(COSName.F, str);
    }

    public boolean isHidden() {
        return getCOSDictionary().getBoolean(COSName.H, false);
    }

    public void setHidden(boolean z) {
        potentiallyNotifyChanged(Boolean.valueOf(isHidden()), Boolean.valueOf(z));
        getCOSDictionary().setBoolean(COSName.H, z);
    }

    public String toString() {
        return "Name=" + getName() + ", Value=" + getValue() + ", FormattedValue=" + getFormattedValue() + ", Hidden=" + isHidden();
    }

    private void potentiallyNotifyChanged(Object obj, Object obj2) {
        if (isEntryChanged(obj, obj2)) {
            this.userAttributeObject.userPropertyChanged(this);
        }
    }

    private static boolean isEntryChanged(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 != null;
        }
        return !obj.equals(obj2);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        PDUserAttributeObject pDUserAttributeObject = this.userAttributeObject;
        return hashCode + (pDUserAttributeObject == null ? 0 : pDUserAttributeObject.hashCode());
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        PDUserProperty pDUserProperty = (PDUserProperty) obj;
        PDUserAttributeObject pDUserAttributeObject = this.userAttributeObject;
        if (pDUserAttributeObject == null) {
            if (pDUserProperty.userAttributeObject != null) {
                return false;
            }
        } else if (!pDUserAttributeObject.equals(pDUserProperty.userAttributeObject)) {
            return false;
        }
        return true;
    }
}
