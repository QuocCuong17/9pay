package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDSeedValue implements COSObjectable {
    public static final int FLAG_ADD_REV_INFO = 32;
    public static final int FLAG_DIGEST_METHOD = 64;
    public static final int FLAG_FILTER = 1;
    public static final int FLAG_LEGAL_ATTESTATION = 16;
    public static final int FLAG_REASON = 8;
    public static final int FLAG_SUBFILTER = 2;
    public static final int FLAG_V = 4;
    private COSDictionary dictionary;

    public PDSeedValue() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.SV);
        this.dictionary.setDirect(true);
    }

    public PDSeedValue(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return getDictionary();
    }

    public COSDictionary getDictionary() {
        return this.dictionary;
    }

    public boolean isFilterRequired() {
        return getDictionary().getFlag(COSName.FF, 1);
    }

    public void setFilterRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 1, z);
    }

    public boolean isSubFilterRequired() {
        return getDictionary().getFlag(COSName.FF, 2);
    }

    public void setSubFilterRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 2, z);
    }

    public boolean isDigestMethodRequired() {
        return getDictionary().getFlag(COSName.FF, 64);
    }

    public void setDigestMethodRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 64, z);
    }

    public boolean isVRequired() {
        return getDictionary().getFlag(COSName.FF, 4);
    }

    public void setVRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 4, z);
    }

    public boolean isReasonRequired() {
        return getDictionary().getFlag(COSName.FF, 8);
    }

    public void setReasonRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 8, z);
    }

    public boolean isLegalAttestationRequired() {
        return getDictionary().getFlag(COSName.FF, 16);
    }

    public void setLegalAttestationRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 16, z);
    }

    public boolean isAddRevInfoRequired() {
        return getDictionary().getFlag(COSName.FF, 32);
    }

    public void setAddRevInfoRequired(boolean z) {
        getDictionary().setFlag(COSName.FF, 32, z);
    }

    public String getFilter() {
        return this.dictionary.getNameAsString(COSName.FILTER);
    }

    public void setFilter(COSName cOSName) {
        this.dictionary.setItem(COSName.FILTER, (COSBase) cOSName);
    }

    public List<String> getSubFilter() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.SUB_FILTER);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            String name = cOSArray.getName(i);
            if (name != null) {
                arrayList.add(name);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setSubFilter(List<COSName> list) {
        this.dictionary.setItem(COSName.SUB_FILTER, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public List<String> getDigestMethod() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.DIGEST_METHOD);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            String name = cOSArray.getName(i);
            if (name != null) {
                arrayList.add(name);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setDigestMethod(List<COSName> list) {
        for (COSName cOSName : list) {
            if (!cOSName.equals(COSName.DIGEST_SHA1) && !cOSName.equals(COSName.DIGEST_SHA256) && !cOSName.equals(COSName.DIGEST_SHA384) && !cOSName.equals(COSName.DIGEST_SHA512) && !cOSName.equals(COSName.DIGEST_RIPEMD160)) {
                throw new IllegalArgumentException("Specified digest " + cOSName.getName() + " isn't allowed.");
            }
        }
        this.dictionary.setItem(COSName.DIGEST_METHOD, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public float getV() {
        return this.dictionary.getFloat(COSName.V);
    }

    public void setV(float f) {
        this.dictionary.setFloat(COSName.V, f);
    }

    public List<String> getReasons() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.REASONS);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            String string = cOSArray.getString(i);
            if (string != null) {
                arrayList.add(string);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setReasonsd(List<String> list) {
        this.dictionary.setItem(COSName.REASONS, (COSBase) COSArrayList.converterToCOSArray(list));
    }

    public PDSeedValueMDP getMDP() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.MDP);
        if (cOSDictionary != null) {
            return new PDSeedValueMDP(cOSDictionary);
        }
        return null;
    }

    public void setMPD(PDSeedValueMDP pDSeedValueMDP) {
        if (pDSeedValueMDP != null) {
            this.dictionary.setItem(COSName.MDP, pDSeedValueMDP.getCOSObject());
        }
    }

    public PDSeedValueTimeStamp getTimeStamp() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.TIME_STAMP);
        if (cOSDictionary != null) {
            return new PDSeedValueTimeStamp(cOSDictionary);
        }
        return null;
    }

    public void setTimeStamp(PDSeedValueTimeStamp pDSeedValueTimeStamp) {
        if (pDSeedValueTimeStamp != null) {
            this.dictionary.setItem(COSName.TIME_STAMP, pDSeedValueTimeStamp.getCOSObject());
        }
    }

    public List<String> getLegalAttestation() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.LEGAL_ATTESTATION);
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            String string = cOSArray.getString(i);
            if (string != null) {
                arrayList.add(string);
            }
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public void setLegalAttestation(List<String> list) {
        this.dictionary.setItem(COSName.LEGAL_ATTESTATION, (COSBase) COSArrayList.converterToCOSArray(list));
    }
}
