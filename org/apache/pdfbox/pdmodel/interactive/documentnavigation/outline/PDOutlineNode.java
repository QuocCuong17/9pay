package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;

import java.util.Iterator;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDDictionaryWrapper;

/* loaded from: classes5.dex */
public abstract class PDOutlineNode extends PDDictionaryWrapper {
    public PDOutlineNode() {
    }

    public PDOutlineNode(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDOutlineNode getParent() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(COSName.PARENT);
        if (cOSDictionary == null) {
            return null;
        }
        if (COSName.OUTLINES.equals(cOSDictionary.getCOSName(COSName.TYPE))) {
            return new PDDocumentOutline(cOSDictionary);
        }
        return new PDOutlineItem(cOSDictionary);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParent(PDOutlineNode pDOutlineNode) {
        getCOSDictionary().setItem(COSName.PARENT, pDOutlineNode);
    }

    public void addLast(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        append(pDOutlineItem);
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    public void addFirst(PDOutlineItem pDOutlineItem) {
        requireSingleNode(pDOutlineItem);
        prepend(pDOutlineItem);
        updateParentOpenCountForAddedChild(pDOutlineItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requireSingleNode(PDOutlineItem pDOutlineItem) {
        if (pDOutlineItem.getNextSibling() != null || pDOutlineItem.getPreviousSibling() != null) {
            throw new IllegalArgumentException("A single node with no siblings is required");
        }
    }

    private void append(PDOutlineItem pDOutlineItem) {
        pDOutlineItem.setParent(this);
        if (!hasChildren()) {
            setFirstChild(pDOutlineItem);
        } else {
            PDOutlineItem lastChild = getLastChild();
            lastChild.setNextSibling(pDOutlineItem);
            pDOutlineItem.setPreviousSibling(lastChild);
        }
        setLastChild(pDOutlineItem);
    }

    private void prepend(PDOutlineItem pDOutlineItem) {
        pDOutlineItem.setParent(this);
        if (!hasChildren()) {
            setLastChild(pDOutlineItem);
        } else {
            PDOutlineItem firstChild = getFirstChild();
            pDOutlineItem.setNextSibling(firstChild);
            firstChild.setPreviousSibling(pDOutlineItem);
        }
        setFirstChild(pDOutlineItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateParentOpenCountForAddedChild(PDOutlineItem pDOutlineItem) {
        pDOutlineItem.updateParentOpenCount(pDOutlineItem.isNodeOpen() ? 1 + pDOutlineItem.getOpenCount() : 1);
    }

    public boolean hasChildren() {
        return getFirstChild() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDOutlineItem getOutlineItem(COSName cOSName) {
        COSDictionary cOSDictionary = (COSDictionary) getCOSDictionary().getDictionaryObject(cOSName);
        if (cOSDictionary != null) {
            return new PDOutlineItem(cOSDictionary);
        }
        return null;
    }

    public PDOutlineItem getFirstChild() {
        return getOutlineItem(COSName.FIRST);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFirstChild(PDOutlineNode pDOutlineNode) {
        getCOSDictionary().setItem(COSName.FIRST, pDOutlineNode);
    }

    public PDOutlineItem getLastChild() {
        return getOutlineItem(COSName.LAST);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLastChild(PDOutlineNode pDOutlineNode) {
        getCOSDictionary().setItem(COSName.LAST, pDOutlineNode);
    }

    public int getOpenCount() {
        return getCOSDictionary().getInt(COSName.COUNT, 0);
    }

    void setOpenCount(int i) {
        getCOSDictionary().setInt(COSName.COUNT, i);
    }

    public void openNode() {
        if (isNodeOpen()) {
            return;
        }
        switchNodeCount();
    }

    public void closeNode() {
        if (isNodeOpen()) {
            switchNodeCount();
        }
    }

    private void switchNodeCount() {
        int i = -getOpenCount();
        setOpenCount(i);
        updateParentOpenCount(i);
    }

    public boolean isNodeOpen() {
        return getOpenCount() > 0;
    }

    void updateParentOpenCount(int i) {
        PDOutlineNode parent = getParent();
        if (parent != null) {
            if (parent.isNodeOpen()) {
                parent.setOpenCount(parent.getOpenCount() + i);
                parent.updateParentOpenCount(i);
            } else {
                parent.setOpenCount(parent.getOpenCount() - i);
            }
        }
    }

    public Iterable<PDOutlineItem> children() {
        return new Iterable<PDOutlineItem>() { // from class: org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode.1
            @Override // java.lang.Iterable
            public Iterator<PDOutlineItem> iterator() {
                return new PDOutlineItemIterator(PDOutlineNode.this.getFirstChild());
            }
        };
    }
}
