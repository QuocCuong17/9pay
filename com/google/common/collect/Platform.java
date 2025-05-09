package com.google.common.collect;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Platform {
    static int reduceExponentIfGwt(int exponent) {
        return exponent;
    }

    static int reduceIterationsIfGwt(int iterations) {
        return iterations;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map<K, V> newHashMapWithExpectedSize(int expectedSize) {
        return CompactHashMap.createWithExpectedSize(expectedSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        return CompactLinkedHashMap.createWithExpectedSize(expectedSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> newHashSetWithExpectedSize(int expectedSize) {
        return CompactHashSet.createWithExpectedSize(expectedSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        return CompactLinkedHashSet.createWithExpectedSize(expectedSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Map<K, V> preservesInsertionOrderOnPutsMap() {
        return CompactHashMap.create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> preservesInsertionOrderOnAddsSet() {
        return CompactHashSet.create();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] newArray(T[] tArr, int i) {
        if (tArr.length != 0) {
            tArr = (T[]) Arrays.copyOf(tArr, 0);
        }
        return (T[]) Arrays.copyOf(tArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T[] copy(Object[] objArr, int i, int i2, T[] tArr) {
        return (T[]) Arrays.copyOfRange(objArr, i, i2, tArr.getClass());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E extends Enum<E>> Class<E> getDeclaringClassOrObjectForJ2cl(E e) {
        return e.getDeclaringClass();
    }

    private Platform() {
    }
}
