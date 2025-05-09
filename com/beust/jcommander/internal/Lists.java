package com.beust.jcommander.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class Lists {
    public static <K> List<K> newArrayList() {
        return new ArrayList();
    }

    public static <K> List<K> newArrayList(Collection<K> collection) {
        return new ArrayList(collection);
    }

    public static <K> List<K> newArrayList(K... kArr) {
        return new ArrayList(Arrays.asList(kArr));
    }

    public static <K> List<K> newArrayList(int i) {
        return new ArrayList(i);
    }

    public static <K> LinkedList<K> newLinkedList() {
        return new LinkedList<>();
    }

    public static <K> LinkedList<K> newLinkedList(Collection<K> collection) {
        return new LinkedList<>(collection);
    }
}
