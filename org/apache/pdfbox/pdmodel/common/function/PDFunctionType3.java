package org.apache.pdfbox.pdmodel.common.function;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRange;

/* loaded from: classes5.dex */
public class PDFunctionType3 extends PDFunction {
    private COSArray bounds;
    private COSArray encode;
    private COSArray functions;

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        return 3;
    }

    public PDFunctionType3(COSBase cOSBase) {
        super(cOSBase);
        this.functions = null;
        this.encode = null;
        this.bounds = null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        PDFunction pDFunction;
        float f = fArr[0];
        PDRange domainForInput = getDomainForInput(0);
        float clipToRange = clipToRange(f, domainForInput.getMin(), domainForInput.getMax());
        COSArray functions = getFunctions();
        if (functions.size() == 1) {
            pDFunction = PDFunction.create(functions.get(0));
            PDRange encodeForParameter = getEncodeForParameter(0);
            clipToRange = interpolate(clipToRange, domainForInput.getMin(), domainForInput.getMax(), encodeForParameter.getMin(), encodeForParameter.getMax());
        } else {
            float[] floatArray = getBounds().toFloatArray();
            int length = floatArray.length;
            int i = length + 2;
            float[] fArr2 = new float[i];
            fArr2[0] = domainForInput.getMin();
            int i2 = i - 1;
            fArr2[i2] = domainForInput.getMax();
            System.arraycopy(floatArray, 0, fArr2, 1, length);
            for (int i3 = 0; i3 < i2; i3++) {
                if (clipToRange >= fArr2[i3]) {
                    int i4 = i3 + 1;
                    if (clipToRange < fArr2[i4] || (i3 == i - 2 && clipToRange == fArr2[i4])) {
                        pDFunction = PDFunction.create(functions.get(i3));
                        PDRange encodeForParameter2 = getEncodeForParameter(i3);
                        clipToRange = interpolate(clipToRange, fArr2[i3], fArr2[i4], encodeForParameter2.getMin(), encodeForParameter2.getMax());
                        break;
                    }
                }
            }
            pDFunction = null;
        }
        if (pDFunction == null) {
            throw new IOException("partition not found in type 3 function");
        }
        return clipToRange(pDFunction.eval(new float[]{clipToRange}));
    }

    public COSArray getFunctions() {
        if (this.functions == null) {
            this.functions = (COSArray) getDictionary().getDictionaryObject(COSName.FUNCTIONS);
        }
        return this.functions;
    }

    public COSArray getBounds() {
        if (this.bounds == null) {
            this.bounds = (COSArray) getDictionary().getDictionaryObject(COSName.BOUNDS);
        }
        return this.bounds;
    }

    public COSArray getEncode() {
        if (this.encode == null) {
            this.encode = (COSArray) getDictionary().getDictionaryObject(COSName.ENCODE);
        }
        return this.encode;
    }

    private PDRange getEncodeForParameter(int i) {
        return new PDRange(getEncode(), i);
    }
}
