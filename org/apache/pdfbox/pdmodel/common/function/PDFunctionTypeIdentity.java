package org.apache.pdfbox.pdmodel.common.function;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: classes5.dex */
public class PDFunctionTypeIdentity extends PDFunction {
    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] fArr) throws IOException {
        return fArr;
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public String toString() {
        return "FunctionTypeIdentity";
    }

    public PDFunctionTypeIdentity(COSBase cOSBase) {
        super(null);
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        throw new UnsupportedOperationException();
    }
}
