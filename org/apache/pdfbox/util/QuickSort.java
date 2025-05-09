package org.apache.pdfbox.util;

import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class QuickSort {
    private static final Comparator<? extends Comparable> objComp = new Comparator<Comparable>() { // from class: org.apache.pdfbox.util.QuickSort.1
        @Override // java.util.Comparator
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    private QuickSort() {
    }

    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        int size = list.size();
        if (size < 2) {
            return;
        }
        quicksort(list, comparator, 0, size - 1);
    }

    public static <T extends Comparable> void sort(List<T> list) {
        sort(list, objComp);
    }

    private static <T> void quicksort(List<T> list, Comparator<T> comparator, int i, int i2) {
        if (i < i2) {
            int split = split(list, comparator, i, i2);
            quicksort(list, comparator, i, split - 1);
            quicksort(list, comparator, split + 1, i2);
        }
    }

    private static <T> void swap(List<T> list, int i, int i2) {
        T t = list.get(i);
        list.set(i, list.get(i2));
        list.set(i2, t);
    }

    private static <T> int split(List<T> list, Comparator<T> comparator, int i, int i2) {
        int i3 = i2 - 1;
        T t = list.get(i2);
        int i4 = i;
        while (true) {
            if (comparator.compare(list.get(i4), t) > 0 || i4 >= i2) {
                while (comparator.compare(t, list.get(i3)) <= 0 && i3 > i) {
                    i3--;
                }
                if (i4 < i3) {
                    swap(list, i4, i3);
                }
                if (i4 >= i3) {
                    break;
                }
            } else {
                i4++;
            }
        }
        if (comparator.compare(t, list.get(i4)) < 0) {
            swap(list, i4, i2);
        }
        return i4;
    }
}
