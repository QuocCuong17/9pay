package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationHighlight extends FDFAnnotation {
    public static final String SUBTYPE = "Highlight";

    public FDFAnnotationHighlight() {
        this.annot.setName(COSName.SUBTYPE, "Highlight");
    }

    public FDFAnnotationHighlight(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationHighlight(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Highlight");
    }
}
