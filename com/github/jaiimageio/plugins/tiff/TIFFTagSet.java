package com.github.jaiimageio.plugins.tiff;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public class TIFFTagSet {
    private SortedMap allowedTagsByNumber = new TreeMap();
    private SortedMap allowedTagsByName = new TreeMap();

    private TIFFTagSet() {
    }

    public TIFFTagSet(List list) {
        if (list == null) {
            throw new IllegalArgumentException("tags == null!");
        }
        for (Object obj : list) {
            if (!(obj instanceof TIFFTag)) {
                throw new IllegalArgumentException("tags contains a non-TIFFTag!");
            }
            TIFFTag tIFFTag = (TIFFTag) obj;
            this.allowedTagsByNumber.put(new Integer(tIFFTag.getNumber()), tIFFTag);
            this.allowedTagsByName.put(tIFFTag.getName(), tIFFTag);
        }
    }

    public TIFFTag getTag(int i) {
        return (TIFFTag) this.allowedTagsByNumber.get(new Integer(i));
    }

    public TIFFTag getTag(String str) {
        if (str == null) {
            throw new IllegalArgumentException("tagName == null!");
        }
        return (TIFFTag) this.allowedTagsByName.get(str);
    }

    public SortedSet getTagNumbers() {
        SortedSet treeSet;
        Set keySet = this.allowedTagsByNumber.keySet();
        if (keySet instanceof SortedSet) {
            treeSet = (SortedSet) keySet;
        } else {
            treeSet = new TreeSet(keySet);
        }
        return Collections.unmodifiableSortedSet(treeSet);
    }

    public SortedSet getTagNames() {
        SortedSet treeSet;
        Set keySet = this.allowedTagsByName.keySet();
        if (keySet instanceof SortedSet) {
            treeSet = (SortedSet) keySet;
        } else {
            treeSet = new TreeSet(keySet);
        }
        return Collections.unmodifiableSortedSet(treeSet);
    }
}
