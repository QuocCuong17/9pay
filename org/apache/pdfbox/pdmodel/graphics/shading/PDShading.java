package org.apache.pdfbox.pdmodel.graphics.shading;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.function.PDFunction;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;

/* loaded from: classes5.dex */
public abstract class PDShading implements COSObjectable {
    public static final int SHADING_TYPE1 = 1;
    public static final int SHADING_TYPE2 = 2;
    public static final int SHADING_TYPE3 = 3;
    public static final int SHADING_TYPE4 = 4;
    public static final int SHADING_TYPE5 = 5;
    public static final int SHADING_TYPE6 = 6;
    public static final int SHADING_TYPE7 = 7;
    private PDRectangle bBox;
    private COSArray background;
    private PDColorSpace colorSpace;
    private COSDictionary dictionary;
    private PDFunction function;
    private PDFunction[] functionArray;

    public abstract int getShadingType();

    public PDShading() {
        this.background = null;
        this.bBox = null;
        this.colorSpace = null;
        this.function = null;
        this.functionArray = null;
        this.dictionary = new COSDictionary();
    }

    public PDShading(COSDictionary cOSDictionary) {
        this.background = null;
        this.bBox = null;
        this.colorSpace = null;
        this.function = null;
        this.functionArray = null;
        this.dictionary = cOSDictionary;
    }

    public COSDictionary getCOSDictionary() {
        return this.dictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.dictionary;
    }

    public String getType() {
        return COSName.SHADING.getName();
    }

    public void setShadingType(int i) {
        this.dictionary.setInt(COSName.SHADING_TYPE, i);
    }

    public void setBackground(COSArray cOSArray) {
        this.background = cOSArray;
        this.dictionary.setItem(COSName.BACKGROUND, (COSBase) cOSArray);
    }

    public COSArray getBackground() {
        if (this.background == null) {
            this.background = (COSArray) this.dictionary.getDictionaryObject(COSName.BACKGROUND);
        }
        return this.background;
    }

    public PDRectangle getBBox() {
        COSArray cOSArray;
        if (this.bBox == null && (cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.BBOX)) != null) {
            this.bBox = new PDRectangle(cOSArray);
        }
        return this.bBox;
    }

    public void setBBox(PDRectangle pDRectangle) {
        this.bBox = pDRectangle;
        if (pDRectangle == null) {
            this.dictionary.removeItem(COSName.BBOX);
        } else {
            this.dictionary.setItem(COSName.BBOX, (COSBase) this.bBox.getCOSArray());
        }
    }

    public void setAntiAlias(boolean z) {
        this.dictionary.setBoolean(COSName.ANTI_ALIAS, z);
    }

    public boolean getAntiAlias() {
        return this.dictionary.getBoolean(COSName.ANTI_ALIAS, false);
    }

    public void setColorSpace(PDColorSpace pDColorSpace) {
        this.colorSpace = pDColorSpace;
        if (pDColorSpace != null) {
            this.dictionary.setItem(COSName.COLORSPACE, pDColorSpace.getCOSObject());
        } else {
            this.dictionary.removeItem(COSName.COLORSPACE);
        }
    }

    public static PDShading create(COSDictionary cOSDictionary) throws IOException {
        int i = cOSDictionary.getInt(COSName.SHADING_TYPE, 0);
        switch (i) {
            case 1:
                return new PDShadingType1(cOSDictionary);
            case 2:
                return new PDShadingType2(cOSDictionary);
            case 3:
                return new PDShadingType3(cOSDictionary);
            case 4:
                return new PDShadingType4(cOSDictionary);
            case 5:
                return new PDShadingType5(cOSDictionary);
            case 6:
                return new PDShadingType6(cOSDictionary);
            case 7:
                return new PDShadingType7(cOSDictionary);
            default:
                throw new IOException("Error: Unknown shading type " + i);
        }
    }

    public void setFunction(PDFunction pDFunction) {
        this.functionArray = null;
        this.function = pDFunction;
        if (pDFunction == null) {
            getCOSDictionary().removeItem(COSName.FUNCTION);
        } else {
            getCOSDictionary().setItem(COSName.FUNCTION, pDFunction);
        }
    }

    public void setFunction(COSArray cOSArray) {
        this.functionArray = null;
        this.function = null;
        if (cOSArray == null) {
            getCOSDictionary().removeItem(COSName.FUNCTION);
        } else {
            getCOSDictionary().setItem(COSName.FUNCTION, (COSBase) cOSArray);
        }
    }

    public PDFunction getFunction() throws IOException {
        COSBase dictionaryObject;
        if (this.function == null && (dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.FUNCTION)) != null) {
            this.function = PDFunction.create(dictionaryObject);
        }
        return this.function;
    }

    private PDFunction[] getFunctionsArray() throws IOException {
        if (this.functionArray == null) {
            COSBase dictionaryObject = getCOSDictionary().getDictionaryObject(COSName.FUNCTION);
            if (dictionaryObject instanceof COSDictionary) {
                this.functionArray = r1;
                PDFunction[] pDFunctionArr = {PDFunction.create(dictionaryObject)};
            } else {
                COSArray cOSArray = (COSArray) dictionaryObject;
                int size = cOSArray.size();
                this.functionArray = new PDFunction[size];
                for (int i = 0; i < size; i++) {
                    this.functionArray[i] = PDFunction.create(cOSArray.get(i));
                }
            }
        }
        return this.functionArray;
    }

    public float[] evalFunction(float f) throws IOException {
        return evalFunction(new float[]{f});
    }

    public float[] evalFunction(float[] fArr) throws IOException {
        float[] fArr2;
        PDFunction[] functionsArray = getFunctionsArray();
        int length = functionsArray.length;
        if (length == 1) {
            fArr2 = functionsArray[0].eval(fArr);
        } else {
            float[] fArr3 = new float[length];
            for (int i = 0; i < length; i++) {
                fArr3[i] = functionsArray[i].eval(fArr)[0];
            }
            fArr2 = fArr3;
        }
        for (int i2 = 0; i2 < fArr2.length; i2++) {
            if (fArr2[i2] < 0.0f) {
                fArr2[i2] = 0.0f;
            } else if (fArr2[i2] > 1.0f) {
                fArr2[i2] = 1.0f;
            }
        }
        return fArr2;
    }
}
