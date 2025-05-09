package com.beust.jcommander.internal;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class Maps {
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap();
    }

    public static <K, V> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap();
    }

    public static <T> Map<T, T> newHashMap(T... tArr) {
        Map<T, T> newHashMap = newHashMap();
        for (int i = 0; i < tArr.length; i += 2) {
            newHashMap.put(tArr[i], tArr[i + 1]);
        }
        return newHashMap;
    }
}
