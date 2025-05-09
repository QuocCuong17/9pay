package org.apache.pdfbox.pdmodel.common;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;

/* loaded from: classes5.dex */
public class COSDictionaryMap<K, V> implements Map<K, V> {
    private final Map<K, V> actuals;
    private final COSDictionary map;

    public COSDictionaryMap(Map<K, V> map, COSDictionary cOSDictionary) {
        this.actuals = map;
        this.map = cOSDictionary;
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.keySet().contains(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.actuals.containsValue(obj);
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return this.actuals.get(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V put(K k, V v) {
        this.map.setItem(COSName.getPDFName((String) k), ((COSObjectable) v).getCOSObject());
        return this.actuals.put(k, v);
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        this.map.removeItem(COSName.getPDFName((String) obj));
        return this.actuals.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
        this.actuals.clear();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.actuals.keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return this.actuals.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(this.actuals.entrySet());
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj instanceof COSDictionaryMap) {
            return ((COSDictionaryMap) obj).map.equals(this.map);
        }
        return false;
    }

    public String toString() {
        return this.actuals.toString();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    public static COSDictionary convert(Map<String, ?> map) {
        map.keySet().iterator();
        COSDictionary cOSDictionary = new COSDictionary();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            cOSDictionary.setItem(COSName.getPDFName(entry.getKey()), ((COSObjectable) entry.getValue()).getCOSObject());
        }
        return cOSDictionary;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static COSDictionaryMap<String, Object> convertBasicTypesToMap(COSDictionary cOSDictionary) throws IOException {
        Object obj;
        if (cOSDictionary == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (COSName cOSName : cOSDictionary.keySet()) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
            if (dictionaryObject instanceof COSString) {
                obj = ((COSString) dictionaryObject).getString();
            } else if (dictionaryObject instanceof COSInteger) {
                obj = Integer.valueOf(((COSInteger) dictionaryObject).intValue());
            } else if (dictionaryObject instanceof COSName) {
                obj = ((COSName) dictionaryObject).getName();
            } else if (dictionaryObject instanceof COSFloat) {
                obj = Float.valueOf(((COSFloat) dictionaryObject).floatValue());
            } else if (dictionaryObject instanceof COSBoolean) {
                obj = ((COSBoolean) dictionaryObject).getValue() ? Boolean.TRUE : Boolean.FALSE;
            } else {
                throw new IOException("Error:unknown type of object to convert:" + dictionaryObject);
            }
            hashMap.put(cOSName.getName(), obj);
        }
        return new COSDictionaryMap<>(hashMap, cOSDictionary);
    }
}
