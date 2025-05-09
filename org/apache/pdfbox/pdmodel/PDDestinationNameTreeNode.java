package org.apache.pdfbox.pdmodel;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;

/* loaded from: classes5.dex */
public class PDDestinationNameTreeNode extends PDNameTreeNode {
    public PDDestinationNameTreeNode() {
        super(PDPageDestination.class);
    }

    public PDDestinationNameTreeNode(COSDictionary cOSDictionary) {
        super(cOSDictionary, PDPageDestination.class);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDNameTreeNode
    protected COSObjectable convertCOSToPD(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSDictionary) {
            cOSBase = ((COSDictionary) cOSBase).getDictionaryObject(COSName.D);
        }
        return PDDestination.create(cOSBase);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDNameTreeNode
    protected PDNameTreeNode createChildNode(COSDictionary cOSDictionary) {
        return new PDDestinationNameTreeNode(cOSDictionary);
    }
}
