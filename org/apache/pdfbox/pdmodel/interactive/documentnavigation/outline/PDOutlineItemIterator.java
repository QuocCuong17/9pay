package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;

import java.util.Iterator;

/* loaded from: classes5.dex */
class PDOutlineItemIterator implements Iterator<PDOutlineItem> {
    private PDOutlineItem currentItem;
    private final PDOutlineItem startingItem;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDOutlineItemIterator(PDOutlineItem pDOutlineItem) {
        this.startingItem = pDOutlineItem;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        PDOutlineItem pDOutlineItem;
        return this.startingItem != null && ((pDOutlineItem = this.currentItem) == null || !(pDOutlineItem.getNextSibling() == null || this.startingItem.equals(this.currentItem.getNextSibling())));
    }

    @Override // java.util.Iterator
    public PDOutlineItem next() {
        PDOutlineItem pDOutlineItem = this.currentItem;
        if (pDOutlineItem == null) {
            this.currentItem = this.startingItem;
        } else {
            this.currentItem = pDOutlineItem.getNextSibling();
        }
        return this.currentItem;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
