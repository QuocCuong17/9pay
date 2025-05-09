package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationInk extends FDFAnnotation {
    public static final String SUBTYPE = "Ink";

    public FDFAnnotationInk() {
        this.annot.setName(COSName.SUBTYPE, "Ink");
    }

    public FDFAnnotationInk(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationInk(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Ink");
    }
}
