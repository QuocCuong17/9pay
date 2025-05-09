package org.apache.pdfbox.cos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: classes5.dex */
public class COSArray extends COSBase implements Iterable<COSBase> {
    private final List<COSBase> objects = new ArrayList();

    public void add(COSBase cOSBase) {
        this.objects.add(cOSBase);
    }

    public void add(COSObjectable cOSObjectable) {
        this.objects.add(cOSObjectable.getCOSObject());
    }

    public void add(int i, COSBase cOSBase) {
        this.objects.add(i, cOSBase);
    }

    public void clear() {
        this.objects.clear();
    }

    public void removeAll(Collection<COSBase> collection) {
        this.objects.removeAll(collection);
    }

    public void retainAll(Collection<COSBase> collection) {
        this.objects.retainAll(collection);
    }

    public void addAll(Collection<COSBase> collection) {
        this.objects.addAll(collection);
    }

    public void addAll(COSArray cOSArray) {
        if (cOSArray != null) {
            this.objects.addAll(cOSArray.objects);
        }
    }

    public void addAll(int i, Collection<COSBase> collection) {
        this.objects.addAll(i, collection);
    }

    public void set(int i, COSBase cOSBase) {
        this.objects.set(i, cOSBase);
    }

    public void set(int i, int i2) {
        this.objects.set(i, COSInteger.get(i2));
    }

    public void set(int i, COSObjectable cOSObjectable) {
        this.objects.set(i, cOSObjectable != null ? cOSObjectable.getCOSObject() : null);
    }

    public COSBase getObject(int i) {
        COSBase cOSBase = this.objects.get(i);
        if (cOSBase instanceof COSObject) {
            cOSBase = ((COSObject) cOSBase).getObject();
        } else if (cOSBase instanceof COSNull) {
            cOSBase = null;
        }
        return cOSBase;
    }

    public COSBase get(int i) {
        return this.objects.get(i);
    }

    public int getInt(int i) {
        return getInt(i, -1);
    }

    public int getInt(int i, int i2) {
        if (i >= size()) {
            return i2;
        }
        COSBase cOSBase = this.objects.get(i);
        return cOSBase instanceof COSNumber ? ((COSNumber) cOSBase).intValue() : i2;
    }

    public void setInt(int i, int i2) {
        set(i, (COSBase) COSInteger.get(i2));
    }

    public void setName(int i, String str) {
        set(i, (COSBase) COSName.getPDFName(str));
    }

    public String getName(int i) {
        return getName(i, null);
    }

    public String getName(int i, String str) {
        if (i >= size()) {
            return str;
        }
        COSBase cOSBase = this.objects.get(i);
        return cOSBase instanceof COSName ? ((COSName) cOSBase).getName() : str;
    }

    public void setString(int i, String str) {
        if (str != null) {
            set(i, (COSBase) new COSString(str));
        } else {
            set(i, (COSBase) null);
        }
    }

    public String getString(int i) {
        return getString(i, null);
    }

    public String getString(int i, String str) {
        if (i >= size()) {
            return str;
        }
        COSBase cOSBase = this.objects.get(i);
        return cOSBase instanceof COSString ? ((COSString) cOSBase).getString() : str;
    }

    public int size() {
        return this.objects.size();
    }

    public COSBase remove(int i) {
        return this.objects.remove(i);
    }

    public boolean remove(COSBase cOSBase) {
        return this.objects.remove(cOSBase);
    }

    public boolean removeObject(COSBase cOSBase) {
        boolean remove = remove(cOSBase);
        if (!remove) {
            for (int i = 0; i < size(); i++) {
                COSBase cOSBase2 = get(i);
                if ((cOSBase2 instanceof COSObject) && ((COSObject) cOSBase2).getObject().equals(cOSBase)) {
                    return remove(cOSBase2);
                }
            }
        }
        return remove;
    }

    public String toString() {
        return "COSArray{" + this.objects + "}";
    }

    @Override // java.lang.Iterable
    public Iterator<COSBase> iterator() {
        return this.objects.iterator();
    }

    public int indexOf(COSBase cOSBase) {
        int i = -1;
        for (int i2 = 0; i < 0 && i2 < size(); i2++) {
            if (get(i2).equals(cOSBase)) {
                i = i2;
            }
        }
        return i;
    }

    public int indexOfObject(COSBase cOSBase) {
        for (int i = 0; i < size(); i++) {
            COSBase cOSBase2 = get(i);
            if (cOSBase2.equals(cOSBase)) {
                return i;
            }
            if ((cOSBase2 instanceof COSObject) && ((COSObject) cOSBase2).getObject().equals(cOSBase)) {
                return i;
            }
        }
        return -1;
    }

    public void growToSize(int i) {
        growToSize(i, null);
    }

    public void growToSize(int i, COSBase cOSBase) {
        while (size() < i) {
            add(cOSBase);
        }
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor iCOSVisitor) throws IOException {
        return iCOSVisitor.visitFromArray(this);
    }

    public float[] toFloatArray() {
        float[] fArr = new float[size()];
        for (int i = 0; i < size(); i++) {
            fArr[i] = ((COSNumber) getObject(i)).floatValue();
        }
        return fArr;
    }

    public void setFloatArray(float[] fArr) {
        clear();
        for (float f : fArr) {
            add((COSBase) new COSFloat(f));
        }
    }

    public List<?> toList() {
        ArrayList arrayList = new ArrayList(size());
        for (int i = 0; i < size(); i++) {
            arrayList.add(get(i));
        }
        return arrayList;
    }
}
