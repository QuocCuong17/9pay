package org.apache.pdfbox.pdmodel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class PDPageTree implements COSObjectable, Iterable<PDPage> {
    private final COSDictionary root;

    public PDPageTree() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.root = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.PAGES);
        cOSDictionary.setItem(COSName.KIDS, (COSBase) new COSArray());
        cOSDictionary.setItem(COSName.COUNT, (COSBase) COSInteger.ZERO);
    }

    public PDPageTree(COSDictionary cOSDictionary) {
        if (cOSDictionary == null) {
            throw new IllegalArgumentException("root cannot be null");
        }
        this.root = cOSDictionary;
    }

    public static COSBase getInheritableAttribute(COSDictionary cOSDictionary, COSName cOSName) {
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
        if (dictionaryObject != null) {
            return dictionaryObject;
        }
        COSDictionary cOSDictionary2 = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P);
        if (cOSDictionary2 != null) {
            return getInheritableAttribute(cOSDictionary2, cOSName);
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<PDPage> iterator() {
        return new PageIterator(this.root);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<COSDictionary> getKids(COSDictionary cOSDictionary) {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) cOSDictionary.getDictionaryObject(COSName.KIDS);
        if (cOSArray == null) {
            return arrayList;
        }
        int size = cOSArray.size();
        for (int i = 0; i < size; i++) {
            arrayList.add((COSDictionary) cOSArray.getObject(i));
        }
        return arrayList;
    }

    /* loaded from: classes5.dex */
    private final class PageIterator implements Iterator<PDPage> {
        private final Queue<COSDictionary> queue;

        private PageIterator(COSDictionary cOSDictionary) {
            this.queue = new ArrayDeque();
            enqueueKids(cOSDictionary);
        }

        private void enqueueKids(COSDictionary cOSDictionary) {
            if (PDPageTree.this.isPageTreeNode(cOSDictionary)) {
                Iterator it = PDPageTree.this.getKids(cOSDictionary).iterator();
                while (it.hasNext()) {
                    enqueueKids((COSDictionary) it.next());
                }
                return;
            }
            this.queue.add(cOSDictionary);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator
        public PDPage next() {
            COSDictionary poll = this.queue.poll();
            if (poll.getCOSName(COSName.TYPE) != COSName.PAGE) {
                throw new IllegalStateException("Expected Page but got " + poll);
            }
            return new PDPage(poll);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public PDPage get(int i) {
        COSDictionary cOSDictionary = get(i + 1, this.root, 0);
        if (cOSDictionary.getCOSName(COSName.TYPE) != COSName.PAGE) {
            throw new IllegalStateException("Expected Page but got " + cOSDictionary);
        }
        return new PDPage(cOSDictionary);
    }

    private COSDictionary get(int i, COSDictionary cOSDictionary, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
        if (!isPageTreeNode(cOSDictionary)) {
            if (i2 == i) {
                return cOSDictionary;
            }
            throw new IllegalStateException();
        }
        if (i <= cOSDictionary.getInt(COSName.COUNT, 0) + i2) {
            for (COSDictionary cOSDictionary2 : getKids(cOSDictionary)) {
                if (isPageTreeNode(cOSDictionary2)) {
                    int i3 = cOSDictionary2.getInt(COSName.COUNT, 0) + i2;
                    if (i <= i3) {
                        return get(i, cOSDictionary2, i2);
                    }
                    i2 = i3;
                } else {
                    i2++;
                    if (i == i2) {
                        return get(i, cOSDictionary2, i2);
                    }
                }
            }
            throw new IllegalStateException();
        }
        throw new IndexOutOfBoundsException("Index out of bounds: " + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPageTreeNode(COSDictionary cOSDictionary) {
        return cOSDictionary.getCOSName(COSName.TYPE) == COSName.PAGES || cOSDictionary.containsKey(COSName.KIDS);
    }

    public int indexOf(PDPage pDPage) {
        COSDictionary cOSObject = pDPage.getCOSObject();
        int i = 0;
        do {
            if (isPageTreeNode(cOSObject)) {
                Iterator<COSDictionary> it = getKids(cOSObject).iterator();
                while (it.hasNext() && it.next() != cOSObject) {
                    i++;
                }
            } else {
                i++;
            }
            cOSObject = (COSDictionary) cOSObject.getDictionaryObject(COSName.PARENT, COSName.P);
        } while (cOSObject != null);
        return i - 1;
    }

    public int getCount() {
        return this.root.getInt(COSName.COUNT, 0);
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.root;
    }

    public void remove(int i) {
        remove(get(i + 1, this.root, 0));
    }

    public void remove(PDPage pDPage) {
        remove(pDPage.getCOSObject());
    }

    private void remove(COSDictionary cOSDictionary) {
        if (!((COSArray) ((COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P)).getDictionaryObject(COSName.KIDS)).removeObject(cOSDictionary)) {
            return;
        }
        do {
            cOSDictionary = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.PARENT, COSName.P);
            if (cOSDictionary != null) {
                cOSDictionary.setInt(COSName.COUNT, cOSDictionary.getInt(COSName.COUNT) - 1);
            }
        } while (cOSDictionary != null);
    }

    public void add(PDPage pDPage) {
        COSDictionary cOSObject = pDPage.getCOSObject();
        cOSObject.setItem(COSName.PARENT, (COSBase) this.root);
        ((COSArray) this.root.getDictionaryObject(COSName.KIDS)).add((COSBase) cOSObject);
        do {
            cOSObject = (COSDictionary) cOSObject.getDictionaryObject(COSName.PARENT, COSName.P);
            if (cOSObject != null) {
                cOSObject.setInt(COSName.COUNT, cOSObject.getInt(COSName.COUNT) + 1);
            }
        } while (cOSObject != null);
    }
}
