package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: classes5.dex */
public class FDFAnnotationFileAttachment extends FDFAnnotation {
    public static final String SUBTYPE = "FileAttachment";

    public FDFAnnotationFileAttachment() {
        this.annot.setName(COSName.SUBTYPE, "FileAttachment");
    }

    public FDFAnnotationFileAttachment(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public FDFAnnotationFileAttachment(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "FileAttachment");
    }
}
