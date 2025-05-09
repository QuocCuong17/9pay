package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationSquiggly extends FDFAnnotation {
    public static final String SUBTYPE = "Squiggly";

    public FDFAnnotationSquiggly() {
        this.annot.setName(COSName.SUBTYPE, "Squiggly");
    }

    public FDFAnnotationSquiggly(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationSquiggly(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Squiggly");
    }
}
