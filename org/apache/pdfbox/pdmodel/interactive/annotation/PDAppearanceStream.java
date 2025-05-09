package org.apache.pdfbox.pdmodel.interactive.annotation;

import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

/* loaded from: classes5.dex */
public class PDAppearanceStream extends PDFormXObject {
    public PDAppearanceStream(COSStream cOSStream) {
        super(new PDStream(cOSStream));
    }

    public PDAppearanceStream(PDDocument pDDocument) {
        super(pDDocument);
    }
}
