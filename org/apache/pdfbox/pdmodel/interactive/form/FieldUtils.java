package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.common.COSArrayList;

/* loaded from: classes5.dex */
public final class FieldUtils {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class KeyValue {
        private final String key;
        private final String value;

        public KeyValue(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return "(" + this.key + ", " + this.value + ")";
        }
    }

    /* loaded from: classes5.dex */
    static class KeyValueKeyComparator implements Serializable, Comparator<KeyValue> {
        private static final long serialVersionUID = 6715364290007167694L;

        KeyValueKeyComparator() {
        }

        @Override // java.util.Comparator
        public int compare(KeyValue keyValue, KeyValue keyValue2) {
            return keyValue.key.compareTo(keyValue2.key);
        }
    }

    /* loaded from: classes5.dex */
    static class KeyValueValueComparator implements Serializable, Comparator<KeyValue> {
        private static final long serialVersionUID = -3984095679894798265L;

        KeyValueValueComparator() {
        }

        @Override // java.util.Comparator
        public int compare(KeyValue keyValue, KeyValue keyValue2) {
            return keyValue.value.compareTo(keyValue2.value);
        }
    }

    private FieldUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<KeyValue> toKeyValueList(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new KeyValue(list.get(i), list2.get(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sortByValue(List<KeyValue> list) {
        Collections.sort(list, new KeyValueValueComparator());
    }

    static void sortByKey(List<KeyValue> list) {
        Collections.sort(list, new KeyValueKeyComparator());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> getPairableItems(COSBase cOSBase, int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Only 0 and 1 are allowed as an index into two-element arrays");
        }
        if (cOSBase instanceof COSString) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((COSString) cOSBase).getString());
            return arrayList;
        }
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            if (cOSArray.get(0) instanceof COSString) {
                return COSArrayList.convertCOSStringCOSArrayToList(cOSArray);
            }
            return getItemsFromPair(cOSBase, i);
        }
        return Collections.emptyList();
    }

    private static List<String> getItemsFromPair(COSBase cOSBase, int i) {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) cOSBase;
        int size = cOSArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(((COSString) ((COSArray) cOSArray.get(i2)).get(i)).getString());
        }
        return arrayList;
    }
}
