package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public class PDUserAttributeObject extends PDAttributeObject {
    public static final String OWNER_USER_PROPERTIES = "UserProperties";

    public void userPropertyChanged(PDUserProperty pDUserProperty) {
    }

    public PDUserAttributeObject() {
        setOwner(OWNER_USER_PROPERTIES);
    }

    public PDUserAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public List<PDUserProperty> getOwnerUserProperties() {
        COSArray cOSArray = (COSArray) getCOSDictionary().getDictionaryObject(COSName.P);
        ArrayList arrayList = new ArrayList(cOSArray.size());
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(new PDUserProperty((COSDictionary) cOSArray.getObject(i), this));
        }
        return arrayList;
    }

    public void setUserProperties(List<PDUserProperty> list) {
        COSArray cOSArray = new COSArray();
        Iterator<PDUserProperty> it = list.iterator();
        while (it.hasNext()) {
            cOSArray.add(it.next());
        }
        getCOSDictionary().setItem(COSName.P, (COSBase) cOSArray);
    }

    public void addUserProperty(PDUserProperty pDUserProperty) {
        ((COSArray) getCOSDictionary().getDictionaryObject(COSName.P)).add(pDUserProperty);
        notifyChanged();
    }

    public void removeUserProperty(PDUserProperty pDUserProperty) {
        if (pDUserProperty == null) {
            return;
        }
        ((COSArray) getCOSDictionary().getDictionaryObject(COSName.P)).remove(pDUserProperty.getCOSObject());
        notifyChanged();
    }

    @Override // org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject
    public String toString() {
        return super.toString() + ", userProperties=" + getOwnerUserProperties();
    }
}
