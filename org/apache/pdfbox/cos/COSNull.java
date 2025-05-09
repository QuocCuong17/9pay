package org.apache.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public final class COSNull extends COSBase {
    public static final byte[] NULL_BYTES = {110, 117, 108, 108};
    public static final COSNull NULL = new COSNull();

    public String toString() {
        return "COSNull{}";
    }

    private COSNull() {
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromNull(this);
    }

    public static void writePDF(OutputStream outputStream) throws IOException {
        outputStream.write(NULL_BYTES);
    }
}
