package org.apache.pdfbox.pdmodel;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
import org.apache.pdfbox.pdmodel.common.PDTextStream;

/* loaded from: classes5.dex */
public class PDJavascriptNameTreeNode extends PDNameTreeNode {
    public PDJavascriptNameTreeNode() {
        super(PDTextStream.class);
    }

    public PDJavascriptNameTreeNode(COSDictionary cOSDictionary) {
        super(cOSDictionary, PDTextStream.class);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDNameTreeNode
    protected COSObjectable convertCOSToPD(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSString) {
            return new PDTextStream((COSString) cOSBase);
        }
        if (cOSBase instanceof COSStream) {
            return new PDTextStream((COSStream) cOSBase);
        }
        throw new IOException("Error creating Javascript object, expected either COSString or COSStream and not " + cOSBase);
    }

    @Override // org.apache.pdfbox.pdmodel.common.PDNameTreeNode
    protected PDNameTreeNode createChildNode(COSDictionary cOSDictionary) {
        return new PDJavascriptNameTreeNode(cOSDictionary);
    }
}
