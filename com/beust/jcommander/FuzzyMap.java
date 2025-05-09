package com.beust.jcommander;

import com.beust.jcommander.internal.Maps;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class FuzzyMap {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface IKey {
        String getName();
    }

    public static <V> V findInMap(Map<? extends IKey, V> map, IKey iKey, boolean z, boolean z2) {
        if (z2) {
            return (V) findAbbreviatedValue(map, iKey, z);
        }
        if (z) {
            return map.get(iKey);
        }
        for (IKey iKey2 : map.keySet()) {
            if (iKey2.getName().equalsIgnoreCase(iKey.getName())) {
                return map.get(iKey2);
            }
        }
        return null;
    }

    private static <V> V findAbbreviatedValue(Map<? extends IKey, V> map, IKey iKey, boolean z) {
        String name = iKey.getName();
        Map newHashMap = Maps.newHashMap();
        Iterator<? extends IKey> it = map.keySet().iterator();
        while (true) {
            boolean z2 = true;
            if (!it.hasNext()) {
                break;
            }
            IKey next = it.next();
            String name2 = next.getName();
            if ((!z || !name2.startsWith(name)) && (z || !name2.toLowerCase().startsWith(name.toLowerCase()))) {
                z2 = false;
            }
            if (z2) {
                newHashMap.put(name2, map.get(next));
            }
        }
        if (newHashMap.size() > 1) {
            throw new ParameterException("Ambiguous option: " + iKey + " matches " + newHashMap.keySet());
        }
        if (newHashMap.size() == 1) {
            return newHashMap.values().iterator().next();
        }
        return null;
    }
}
