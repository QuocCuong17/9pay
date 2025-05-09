package org.apache.pdfbox.pdmodel.graphics.optionalcontent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDOptionalContentProperties implements COSObjectable {
    private COSDictionary dict;

    /* loaded from: classes5.dex */
    public enum BaseState {
        ON(COSName.ON),
        OFF(COSName.OFF),
        UNCHANGED(COSName.UNCHANGED);

        private COSName name;

        BaseState(COSName cOSName) {
            this.name = cOSName;
        }

        public COSName getName() {
            return this.name;
        }

        public static BaseState valueOf(COSName cOSName) {
            if (cOSName == null) {
                return ON;
            }
            return valueOf(cOSName.getName().toUpperCase());
        }
    }

    public PDOptionalContentProperties() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.OCGS, (COSBase) new COSArray());
        this.dict.setItem(COSName.D, (COSBase) new COSDictionary());
    }

    public PDOptionalContentProperties(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dict;
    }

    private COSArray getOCGs() {
        COSArray cOSArray = (COSArray) this.dict.getItem(COSName.OCGS);
        if (cOSArray != null) {
            return cOSArray;
        }
        COSArray cOSArray2 = new COSArray();
        this.dict.setItem(COSName.OCGS, (COSBase) cOSArray2);
        return cOSArray2;
    }

    private COSDictionary getD() {
        COSDictionary cOSDictionary = (COSDictionary) this.dict.getDictionaryObject(COSName.D);
        if (cOSDictionary != null) {
            return cOSDictionary;
        }
        COSDictionary cOSDictionary2 = new COSDictionary();
        this.dict.setItem(COSName.D, (COSBase) cOSDictionary2);
        return cOSDictionary2;
    }

    public PDOptionalContentGroup getGroup(String str) {
        Iterator<COSBase> it = getOCGs().iterator();
        while (it.hasNext()) {
            COSDictionary dictionary = toDictionary(it.next());
            if (dictionary.getString(COSName.NAME).equals(str)) {
                return new PDOptionalContentGroup(dictionary);
            }
        }
        return null;
    }

    public void addGroup(PDOptionalContentGroup pDOptionalContentGroup) {
        getOCGs().add((COSBase) pDOptionalContentGroup.getCOSObject());
        COSArray cOSArray = (COSArray) getD().getDictionaryObject(COSName.ORDER);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            getD().setItem(COSName.ORDER, (COSBase) cOSArray);
        }
        cOSArray.add(pDOptionalContentGroup);
    }

    public Collection<PDOptionalContentGroup> getOptionalContentGroups() {
        ArrayList arrayList = new ArrayList();
        Iterator<COSBase> it = getOCGs().iterator();
        while (it.hasNext()) {
            arrayList.add(new PDOptionalContentGroup((COSDictionary) ((COSObject) it.next()).getObject()));
        }
        return arrayList;
    }

    public BaseState getBaseState() {
        return BaseState.valueOf((COSName) getD().getItem(COSName.BASE_STATE));
    }

    public void setBaseState(BaseState baseState) {
        getD().setItem(COSName.BASE_STATE, (COSBase) baseState.getName());
    }

    public String[] getGroupNames() {
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.OCGS);
        int size = cOSArray.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = toDictionary(cOSArray.get(i)).getString(COSName.NAME);
        }
        return strArr;
    }

    public boolean hasGroup(String str) {
        for (String str2 : getGroupNames()) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isGroupEnabled(String str) {
        COSDictionary d = getD();
        COSArray cOSArray = (COSArray) d.getDictionaryObject(COSName.ON);
        if (cOSArray != null) {
            Iterator<COSBase> it = cOSArray.iterator();
            while (it.hasNext()) {
                if (toDictionary(it.next()).getString(COSName.NAME).equals(str)) {
                    return true;
                }
            }
        }
        COSArray cOSArray2 = (COSArray) d.getDictionaryObject(COSName.OFF);
        if (cOSArray2 != null) {
            Iterator<COSBase> it2 = cOSArray2.iterator();
            while (it2.hasNext()) {
                if (toDictionary(it2.next()).getString(COSName.NAME).equals(str)) {
                    return false;
                }
            }
        }
        return !getBaseState().equals(BaseState.OFF);
    }

    private COSDictionary toDictionary(COSBase cOSBase) {
        if (cOSBase instanceof COSObject) {
            return (COSDictionary) ((COSObject) cOSBase).getObject();
        }
        return (COSDictionary) cOSBase;
    }

    public boolean setGroupEnabled(String str, boolean z) {
        COSDictionary d = getD();
        COSArray cOSArray = (COSArray) d.getDictionaryObject(COSName.ON);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            d.setItem(COSName.ON, (COSBase) cOSArray);
        }
        COSArray cOSArray2 = (COSArray) d.getDictionaryObject(COSName.OFF);
        if (cOSArray2 == null) {
            cOSArray2 = new COSArray();
            d.setItem(COSName.OFF, (COSBase) cOSArray2);
        }
        boolean z2 = false;
        if (z) {
            Iterator<COSBase> it = cOSArray2.iterator();
            while (it.hasNext()) {
                COSDictionary dictionary = toDictionary(it.next());
                if (dictionary.getString(COSName.NAME).equals(str)) {
                    cOSArray2.remove(dictionary);
                    cOSArray.add((COSBase) dictionary);
                    z2 = true;
                    break;
                }
            }
        } else {
            Iterator<COSBase> it2 = cOSArray.iterator();
            while (it2.hasNext()) {
                COSDictionary dictionary2 = toDictionary(it2.next());
                if (dictionary2.getString(COSName.NAME).equals(str)) {
                    cOSArray.remove(dictionary2);
                    cOSArray2.add((COSBase) dictionary2);
                    z2 = true;
                    break;
                }
            }
        }
        if (!z2) {
            PDOptionalContentGroup group = getGroup(str);
            if (z) {
                cOSArray.add((COSBase) group.getCOSObject());
            } else {
                cOSArray2.add((COSBase) group.getCOSObject());
            }
        }
        return z2;
    }
}
