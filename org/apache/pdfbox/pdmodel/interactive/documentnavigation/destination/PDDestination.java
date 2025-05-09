package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.PDDestinationOrAction;

/* loaded from: classes5.dex */
public abstract class PDDestination implements PDDestinationOrAction {
    public static PDDestination create(COSBase cOSBase) throws IOException {
        PDNamedDestination pDNamedDestination;
        if (cOSBase == null) {
            return null;
        }
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            if (cOSArray.size() > 1 && (cOSArray.getObject(1) instanceof COSName)) {
                COSName cOSName = (COSName) cOSArray.getObject(1);
                String name = cOSName.getName();
                if (name.equals("Fit") || name.equals("FitB")) {
                    return new PDPageFitDestination(cOSArray);
                }
                if (name.equals("FitV") || name.equals("FitBV")) {
                    return new PDPageFitHeightDestination(cOSArray);
                }
                if (name.equals("FitR")) {
                    return new PDPageFitRectangleDestination(cOSArray);
                }
                if (name.equals("FitH") || name.equals("FitBH")) {
                    return new PDPageFitWidthDestination(cOSArray);
                }
                if (name.equals("XYZ")) {
                    return new PDPageXYZDestination(cOSArray);
                }
                throw new IOException("Unknown destination type: " + cOSName.getName());
            }
        }
        if (cOSBase instanceof COSString) {
            pDNamedDestination = new PDNamedDestination((COSString) cOSBase);
        } else if (cOSBase instanceof COSName) {
            pDNamedDestination = new PDNamedDestination((COSName) cOSBase);
        } else {
            throw new IOException("Error: can't convert to Destination " + cOSBase);
        }
        return pDNamedDestination;
    }
}
