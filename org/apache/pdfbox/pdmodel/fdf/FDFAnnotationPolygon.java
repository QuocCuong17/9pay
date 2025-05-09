package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationPolygon extends FDFAnnotation {
    public static final String SUBTYPE = "Polygon";

    public FDFAnnotationPolygon() {
        this.annot.setName(COSName.SUBTYPE, "Polygon");
    }

    public FDFAnnotationPolygon(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationPolygon(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Polygon");
    }
}
