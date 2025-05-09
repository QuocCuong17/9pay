package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationStrikeOut extends FDFAnnotation {
    public static final String SUBTYPE = "StrikeOut";

    public FDFAnnotationStrikeOut() {
        this.annot.setName(COSName.SUBTYPE, "StrikeOut");
    }

    public FDFAnnotationStrikeOut(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationStrikeOut(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "StrikeOut");
    }
}
