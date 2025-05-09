package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class PDDocumentOutline extends PDOutlineNode {
    @Override // org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode
    public void closeNode() {
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode
    public boolean isNodeOpen() {
        return true;
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode
    public void openNode() {
    }

    public PDDocumentOutline() {
        getCOSDictionary().setName(COSName.TYPE, COSName.OUTLINES.getName());
    }

    public PDDocumentOutline(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        getCOSDictionary().setName(COSName.TYPE, COSName.OUTLINES.getName());
    }
}
