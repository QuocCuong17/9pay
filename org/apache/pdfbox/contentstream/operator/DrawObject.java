package org.apache.pdfbox.contentstream.operator;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.text.PDFMarkedContentExtractor;

/* loaded from: classes5.dex */
public class DrawObject extends OperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "Do";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        PDXObject xObject = this.context.getResources().getXObject((COSName) list.get(0));
        if (this.context instanceof PDFMarkedContentExtractor) {
            ((PDFMarkedContentExtractor) this.context).xobject(xObject);
        }
        if (xObject instanceof PDFormXObject) {
            this.context.showForm((PDFormXObject) xObject);
        }
    }
}
