package org.apache.pdfbox.contentstream.operator.graphics;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.MissingResourceException;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/* loaded from: classes5.dex */
public final class DrawObject extends GraphicsOperatorProcessor {
    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "Do";
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> list) throws IOException {
        COSName cOSName = (COSName) list.get(0);
        PDXObject xObject = this.context.getResources().getXObject(cOSName);
        if (xObject == null) {
            throw new MissingResourceException("Missing XObject: " + cOSName.getName());
        }
        if (xObject instanceof PDImageXObject) {
            this.context.drawImage((PDImageXObject) xObject);
        } else if (xObject instanceof PDFormXObject) {
            PDFormXObject pDFormXObject = (PDFormXObject) xObject;
            if (pDFormXObject.getGroup() != null && COSName.TRANSPARENCY.equals(pDFormXObject.getGroup().getSubType())) {
                getContext().showTransparencyGroup(pDFormXObject);
            } else {
                getContext().showForm(pDFormXObject);
            }
        }
    }
}
