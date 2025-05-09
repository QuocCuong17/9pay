package org.apache.pdfbox.pdmodel.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public class COSArrayList<E> implements List<E> {
    private final List<E> actual;
    private final COSArray array;
    private COSName dictKey;
    private COSDictionary parentDict;

    public COSArrayList() {
        this.array = new COSArray();
        this.actual = new ArrayList();
    }

    public COSArrayList(List<E> list, COSArray cOSArray) {
        this.actual = list;
        this.array = cOSArray;
    }

    public COSArrayList(E e, COSBase cOSBase, COSDictionary cOSDictionary, COSName cOSName) {
        COSArray cOSArray = new COSArray();
        this.array = cOSArray;
        cOSArray.add(cOSBase);
        ArrayList arrayList = new ArrayList();
        this.actual = arrayList;
        arrayList.add(e);
        this.parentDict = cOSDictionary;
        this.dictKey = cOSName;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.actual.size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.actual.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.actual.contains(obj);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.actual.iterator();
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.actual.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <X> X[] toArray(X[] xArr) {
        return (X[]) this.actual.toArray(xArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        COSDictionary cOSDictionary = this.parentDict;
        if (cOSDictionary != null) {
            cOSDictionary.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        if (e instanceof String) {
            this.array.add((COSBase) new COSString((String) e));
        } else if (e instanceof DualCOSObjectable) {
            DualCOSObjectable dualCOSObjectable = (DualCOSObjectable) e;
            this.array.add(dualCOSObjectable.getFirstCOSObject());
            this.array.add(dualCOSObjectable.getSecondCOSObject());
        } else {
            COSArray cOSArray = this.array;
            if (cOSArray != null) {
                cOSArray.add(((COSObjectable) e).getCOSObject());
            }
        }
        return this.actual.add(e);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        int indexOf = this.actual.indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        this.actual.remove(indexOf);
        this.array.remove(indexOf);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.actual.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        if (this.parentDict != null && collection.size() > 0) {
            this.parentDict.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        this.array.addAll(toCOSObjectList(collection));
        return this.actual.addAll(collection);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        if (this.parentDict != null && collection.size() > 0) {
            this.parentDict.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        if (collection.size() > 0 && (collection.toArray()[0] instanceof DualCOSObjectable)) {
            this.array.addAll(i * 2, toCOSObjectList(collection));
        } else {
            this.array.addAll(i, toCOSObjectList(collection));
        }
        return this.actual.addAll(i, collection);
    }

    public static List<Integer> convertIntegerCOSArrayToList(COSArray cOSArray) {
        COSBase cOSBase;
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            if (cOSArray.get(i) instanceof COSObject) {
                cOSBase = ((COSObject) cOSArray.get(i)).getObject();
            } else {
                cOSBase = cOSArray.get(i);
            }
            arrayList.add(Integer.valueOf(((COSNumber) cOSBase).intValue()));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static List<Float> convertFloatCOSArrayToList(COSArray cOSArray) {
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(Float.valueOf(((COSNumber) cOSArray.get(i)).floatValue()));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static List<String> convertCOSNameCOSArrayToList(COSArray cOSArray) {
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(((COSName) cOSArray.getObject(i)).getName());
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static List<String> convertCOSStringCOSArrayToList(COSArray cOSArray) {
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(((COSString) cOSArray.getObject(i)).getString());
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static COSArray convertStringListToCOSNameCOSArray(List<String> list) {
        COSArray cOSArray = new COSArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            cOSArray.add((COSBase) COSName.getPDFName(it.next()));
        }
        return cOSArray;
    }

    public static COSArray convertStringListToCOSStringCOSArray(List<String> list) {
        COSArray cOSArray = new COSArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            cOSArray.add((COSBase) new COSString(it.next()));
        }
        return cOSArray;
    }

    public static COSArray converterToCOSArray(List<?> list) {
        if (list == null) {
            return null;
        }
        if (list instanceof COSArrayList) {
            return ((COSArrayList) list).array;
        }
        COSArray cOSArray = new COSArray();
        for (Object obj : list) {
            if (obj instanceof String) {
                cOSArray.add((COSBase) new COSString((String) obj));
            } else if ((obj instanceof Integer) || (obj instanceof Long)) {
                cOSArray.add((COSBase) COSInteger.get(((Number) obj).longValue()));
            } else if ((obj instanceof Float) || (obj instanceof Double)) {
                cOSArray.add((COSBase) new COSFloat(((Number) obj).floatValue()));
            } else if (obj instanceof COSObjectable) {
                cOSArray.add(((COSObjectable) obj).getCOSObject());
            } else if (obj instanceof DualCOSObjectable) {
                DualCOSObjectable dualCOSObjectable = (DualCOSObjectable) obj;
                cOSArray.add(dualCOSObjectable.getFirstCOSObject());
                cOSArray.add(dualCOSObjectable.getSecondCOSObject());
            } else if (obj == null) {
                cOSArray.add((COSBase) COSNull.NULL);
            } else {
                throw new RuntimeException("Error: Don't know how to convert type to COSBase '" + obj.getClass().getName() + "'");
            }
        }
        return cOSArray;
    }

    private List<COSBase> toCOSObjectList(Collection<?> collection) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (obj instanceof String) {
                arrayList.add(new COSString((String) obj));
            } else if (obj instanceof DualCOSObjectable) {
                DualCOSObjectable dualCOSObjectable = (DualCOSObjectable) obj;
                this.array.add(dualCOSObjectable.getFirstCOSObject());
                this.array.add(dualCOSObjectable.getSecondCOSObject());
            } else {
                arrayList.add(((COSObjectable) obj).getCOSObject());
            }
        }
        return arrayList;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        this.array.removeAll(toCOSObjectList(collection));
        return this.actual.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        this.array.retainAll(toCOSObjectList(collection));
        return this.actual.retainAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        COSDictionary cOSDictionary = this.parentDict;
        if (cOSDictionary != null) {
            cOSDictionary.setItem(this.dictKey, (COSBase) null);
        }
        this.actual.clear();
        this.array.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.actual.equals(obj);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.actual.hashCode();
    }

    @Override // java.util.List
    public E get(int i) {
        return this.actual.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public E set(int i, E e) {
        if (e instanceof String) {
            COSString cOSString = new COSString((String) e);
            COSDictionary cOSDictionary = this.parentDict;
            if (cOSDictionary != null && i == 0) {
                cOSDictionary.setItem(this.dictKey, (COSBase) cOSString);
            }
            this.array.set(i, (COSBase) cOSString);
        } else if (e instanceof DualCOSObjectable) {
            DualCOSObjectable dualCOSObjectable = (DualCOSObjectable) e;
            int i2 = i * 2;
            this.array.set(i2, dualCOSObjectable.getFirstCOSObject());
            this.array.set(i2 + 1, dualCOSObjectable.getSecondCOSObject());
        } else {
            COSDictionary cOSDictionary2 = this.parentDict;
            if (cOSDictionary2 != null && i == 0) {
                cOSDictionary2.setItem(this.dictKey, ((COSObjectable) e).getCOSObject());
            }
            this.array.set(i, ((COSObjectable) e).getCOSObject());
        }
        return this.actual.set(i, e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List
    public void add(int i, E e) {
        COSDictionary cOSDictionary = this.parentDict;
        if (cOSDictionary != null) {
            cOSDictionary.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        this.actual.add(i, e);
        if (e instanceof String) {
            this.array.add(i, new COSString((String) e));
            return;
        }
        if (e instanceof DualCOSObjectable) {
            DualCOSObjectable dualCOSObjectable = (DualCOSObjectable) e;
            int i2 = i * 2;
            this.array.add(i2, dualCOSObjectable.getFirstCOSObject());
            this.array.add(i2 + 1, dualCOSObjectable.getSecondCOSObject());
            return;
        }
        this.array.add(i, ((COSObjectable) e).getCOSObject());
    }

    @Override // java.util.List
    public E remove(int i) {
        if (this.array.size() > i && (this.array.get(i) instanceof DualCOSObjectable)) {
            this.array.remove(i);
            this.array.remove(i);
        } else {
            this.array.remove(i);
        }
        return this.actual.remove(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.actual.indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.actual.indexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return this.actual.listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return this.actual.listIterator(i);
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return this.actual.subList(i, i2);
    }

    public String toString() {
        return "COSArrayList{" + this.array.toString() + "}";
    }

    public COSArray toList() {
        return this.array;
    }
}
