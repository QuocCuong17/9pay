package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;

/* loaded from: classes5.dex */
public abstract class PDStandardAttributeObject extends PDAttributeObject {
    protected static final float UNSPECIFIED = -1.0f;

    public PDStandardAttributeObject() {
    }

    public PDStandardAttributeObject(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public boolean isSpecified(String str) {
        return getCOSDictionary().getDictionaryObject(str) != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getString(String str) {
        return getCOSDictionary().getString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setString(String str, String str2) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setString(str, str2);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[] getArrayOfString(String str) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        if (!(dictionaryObject instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        String[] strArr = new String[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            strArr[i] = ((COSName) cOSArray.getObject(i)).getName();
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArrayOfString(String str, String[] strArr) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        COSArray cOSArray = new COSArray();
        for (String str2 : strArr) {
            cOSArray.add((COSBase) new COSString(str2));
        }
        getCOSDictionary().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getName(String str) {
        return getCOSDictionary().getNameAsString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getName(String str, String str2) {
        return getCOSDictionary().getNameAsString(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getNameOrArrayOfName(String str, String str2) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        if (!(dictionaryObject instanceof COSArray)) {
            return dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : str2;
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        String[] strArr = new String[cOSArray.size()];
        for (int i = 0; i < cOSArray.size(); i++) {
            COSBase object = cOSArray.getObject(i);
            if (object instanceof COSName) {
                strArr[i] = ((COSName) object).getName();
            }
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String str, String str2) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setName(str, str2);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArrayOfName(String str, String[] strArr) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        COSArray cOSArray = new COSArray();
        for (String str2 : strArr) {
            cOSArray.add((COSBase) COSName.getPDFName(str2));
        }
        getCOSDictionary().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getNumberOrName(String str, String str2) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        }
        return dictionaryObject instanceof COSName ? ((COSName) dictionaryObject).getName() : str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getInteger(String str, int i) {
        return getCOSDictionary().getInt(str, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInteger(String str, int i) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setInt(str, i);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getNumber(String str, float f) {
        return getCOSDictionary().getFloat(str, f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getNumber(String str) {
        return getCOSDictionary().getFloat(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getNumberOrArrayOfNumber(String str, float f) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        if (dictionaryObject instanceof COSArray) {
            COSArray cOSArray = (COSArray) dictionaryObject;
            float[] fArr = new float[cOSArray.size()];
            for (int i = 0; i < cOSArray.size(); i++) {
                COSBase object = cOSArray.getObject(i);
                if (object instanceof COSNumber) {
                    fArr[i] = ((COSNumber) object).floatValue();
                }
            }
            return fArr;
        }
        if (dictionaryObject instanceof COSNumber) {
            return Float.valueOf(((COSNumber) dictionaryObject).floatValue());
        }
        if (f == -1.0f) {
            return null;
        }
        return Float.valueOf(f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNumber(String str, float f) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setFloat(str, f);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNumber(String str, int i) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setInt(str, i);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setArrayOfNumber(String str, float[] fArr) {
        COSArray cOSArray = new COSArray();
        for (float f : fArr) {
            cOSArray.add((COSBase) new COSFloat(f));
        }
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setItem(str, (COSBase) cOSArray);
        potentiallyNotifyChanged(dictionaryObject, getCOSDictionary().getDictionaryObject(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PDGamma getColor(String str) {
        COSArray cOSArray = (COSArray) getCOSDictionary().getDictionaryObject(str);
        if (cOSArray != null) {
            return new PDGamma(cOSArray);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getColorOrFourColors(String str) {
        COSArray cOSArray = (COSArray) getCOSDictionary().getDictionaryObject(str);
        if (cOSArray == null) {
            return null;
        }
        if (cOSArray.size() == 3) {
            return new PDGamma(cOSArray);
        }
        if (cOSArray.size() == 4) {
            return new PDFourColours(cOSArray);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setColor(String str, PDGamma pDGamma) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setItem(str, pDGamma);
        potentiallyNotifyChanged(dictionaryObject, pDGamma == null ? null : pDGamma.getCOSObject());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFourColors(String str, PDFourColours pDFourColours) {
        COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(str);
        getCOSDictionary().setItem(str, pDFourColours);
        potentiallyNotifyChanged(dictionaryObject, pDFourColours == null ? null : pDFourColours.getCOSObject());
    }
}
