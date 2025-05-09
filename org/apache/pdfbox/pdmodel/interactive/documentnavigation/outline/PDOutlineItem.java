package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDestinationNameTreeNode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination;
import org.apache.pdfbox.util.awt.AWTColor;

/* loaded from: classes5.dex */
public final class PDOutlineItem extends PDOutlineNode {
    private static final int BOLD_FLAG = 2;
    private static final int ITALIC_FLAG = 1;

    public PDOutlineItem() {
    }

    public PDOutlineItem(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void insertSiblingAfter(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        PDOutlineNode parent = getParent();
        pDOutlineItem.setParent(parent);
        PDOutlineItem nextSibling = getNextSibling();
        setNextSibling(pDOutlineItem);
        pDOutlineItem.setPreviousSibling(this);
        if (nextSibling != null) {
            pDOutlineItem.setNextSibling(nextSibling);
            nextSibling.setPreviousSibling(pDOutlineItem);
        } else if (parent != null) {
            getParent().setLastChild(pDOutlineItem);
        }
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public void insertSiblingBefore(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        PDOutlineNode parent = getParent();
        pDOutlineItem.setParent(parent);
        PDOutlineItem previousSibling = getPreviousSibling();
        setPreviousSibling(pDOutlineItem);
        pDOutlineItem.setNextSibling(this);
        if (previousSibling != null) {
            previousSibling.setNextSibling(pDOutlineItem);
            pDOutlineItem.setPreviousSibling(previousSibling);
        } else if (parent != null) {
            getParent().setFirstChild(pDOutlineItem);
        }
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public PDOutlineItem getPreviousSibling() {
        return getOutlineItem(COSName.PREV);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPreviousSibling(PDOutlineNode pDOutlineNode) {
        getCOSDictionary().setItem(COSName.PREV, pDOutlineNode);
    }

    public PDOutlineItem getNextSibling() {
        return getOutlineItem(COSName.NEXT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNextSibling(PDOutlineNode pDOutlineNode) {
        getCOSDictionary().setItem(COSName.NEXT, pDOutlineNode);
    }

    public String getTitle() {
        return getCOSDictionary().getString(COSName.TITLE);
    }

    public void setTitle(String str) {
        getCOSDictionary().setString(COSName.TITLE, str);
    }

    public PDDestination getDestination() throws IOException {
        return PDDestination.create(getCOSDictionary().getDictionaryObject(COSName.DEST));
    }

    public void setDestination(PDDestination pDDestination) {
        getCOSDictionary().setItem(COSName.DEST, pDDestination);
    }

    public void setDestination(PDPage pDPage) {
        PDPageXYZDestination pDPageXYZDestination;
        if (pDPage != null) {
            pDPageXYZDestination = new PDPageXYZDestination();
            pDPageXYZDestination.setPage(pDPage);
        } else {
            pDPageXYZDestination = null;
        }
        setDestination(pDPageXYZDestination);
    }

    public PDPage findDestinationPage(PDDocument pDDocument) throws IOException {
        PDPageDestination pDPageDestination;
        int pageNumber;
        PDDestinationNameTreeNode dests;
        PDDestination destination = getDestination();
        if (destination == null) {
            PDAction action = getAction();
            if (!(action instanceof PDActionGoTo)) {
                return null;
            }
            destination = ((PDActionGoTo) action).getDestination();
        }
        if (destination instanceof PDNamedDestination) {
            PDNamedDestination pDNamedDestination = (PDNamedDestination) destination;
            PDDocumentNameDictionary names = pDDocument.getDocumentCatalog().getNames();
            if (names == null || (dests = names.getDests()) == null) {
                return null;
            }
            pDPageDestination = (PDPageDestination) dests.getValue(pDNamedDestination.getNamedDestination());
        } else {
            if (!(destination instanceof PDPageDestination)) {
                if (destination == null) {
                    return null;
                }
                throw new IOException("Error: Unknown destination type " + destination);
            }
            pDPageDestination = (PDPageDestination) destination;
        }
        PDPage page = pDPageDestination.getPage();
        return (page != null || (pageNumber = pDPageDestination.getPageNumber()) == -1) ? page : pDDocument.getPage(pageNumber - 1);
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) getCOSDictionary().getDictionaryObject(COSName.A));
    }

    public void setAction(PDAction pDAction) {
        getCOSDictionary().setItem(COSName.A, pDAction);
    }

    public PDStructureElement getStructureElement() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.SE);
        if (cOSDictionary != null) {
            return new PDStructureElement(cOSDictionary);
        }
        return null;
    }

    public void setStructuredElement(PDStructureElement pDStructureElement) {
        getCOSDictionary().setItem(COSName.SE, pDStructureElement);
    }

    public PDColor getTextColor() {
        COSArray cOSArray = (COSArray) getCOSDictionary().getDictionaryObject(COSName.C);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.growToSize(3, new COSFloat(0.0f));
            getCOSDictionary().setItem(COSName.C, (COSBase) cOSArray);
        }
        return new PDColor(cOSArray, PDDeviceRGB.INSTANCE);
    }

    public void setTextColor(PDColor pDColor) {
        getCOSDictionary().setItem(COSName.C, (COSBase) pDColor.toCOSArray());
    }

    public void setTextColor(AWTColor aWTColor) {
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) new COSFloat(aWTColor.getRed() / 255.0f));
        cOSArray.add((COSBase) new COSFloat(aWTColor.getGreen() / 255.0f));
        cOSArray.add((COSBase) new COSFloat(aWTColor.getBlue() / 255.0f));
        getCOSDictionary().setItem(COSName.C, (COSBase) cOSArray);
    }

    public boolean isItalic() {
        return getCOSDictionary().getFlag(COSName.F, 1);
    }

    public void setItalic(boolean z) {
        getCOSDictionary().setFlag(COSName.F, 1, z);
    }

    public boolean isBold() {
        return getCOSDictionary().getFlag(COSName.F, 2);
    }

    public void setBold(boolean z) {
        getCOSDictionary().setFlag(COSName.F, 2, z);
    }
}
