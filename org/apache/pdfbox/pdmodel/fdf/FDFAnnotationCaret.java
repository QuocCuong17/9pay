package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationCaret extends FDFAnnotation {
    public static final String SUBTYPE = "Caret";

    public FDFAnnotationCaret() {
        this.annot.setName(COSName.SUBTYPE, "Caret");
    }

    public FDFAnnotationCaret(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationCaret(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Caret");
    }
}
