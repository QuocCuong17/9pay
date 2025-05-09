package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationFreeText extends FDFAnnotation {
    public static final String SUBTYPE = "FreeText";

    public FDFAnnotationFreeText() {
        this.annot.setName(COSName.SUBTYPE, "FreeText");
    }

    public FDFAnnotationFreeText(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationFreeText(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "FreeText");
    }
}
