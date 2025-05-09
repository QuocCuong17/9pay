package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationCircle extends FDFAnnotation {
    public static final String SUBTYPE = "Circle";

    public FDFAnnotationCircle() {
        this.annot.setName(COSName.SUBTYPE, "Circle");
    }

    public FDFAnnotationCircle(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationCircle(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Circle");
    }
}
