package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationText extends FDFAnnotation {
    public static final String SUBTYPE = "Text";

    public FDFAnnotationText() {
        this.annot.setName(COSName.SUBTYPE, "Text");
    }

    public FDFAnnotationText(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationText(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Text");
    }
}
