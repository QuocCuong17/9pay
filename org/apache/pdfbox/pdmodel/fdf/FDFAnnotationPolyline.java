package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationPolyline extends FDFAnnotation {
    public static final String SUBTYPE = "Polyline";

    public FDFAnnotationPolyline() {
        this.annot.setName(COSName.SUBTYPE, SUBTYPE);
    }

    public FDFAnnotationPolyline(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationPolyline(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, SUBTYPE);
    }
}
