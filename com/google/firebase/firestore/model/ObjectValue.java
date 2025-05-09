package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class ObjectValue implements Cloneable {
    private final Map<String, Object> overlayMap;
    private Value partialValue;

    public static ObjectValue fromMap(Map<String, Value> map) {
        return new ObjectValue(Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(map)).build());
    }

    public ObjectValue(Value value) {
        this.overlayMap = new HashMap();
        Assert.hardAssert(value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE, "ObjectValues should be backed by a MapValue", new Object[0]);
        Assert.hardAssert(!ServerTimestamps.isServerTimestamp(value), "ServerTimestamps should not be used as an ObjectValue", new Object[0]);
        this.partialValue = value;
    }

    public ObjectValue() {
        this(Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build());
    }

    public Map<String, Value> getFieldsMap() {
        return buildProto().getMapValue().getFieldsMap();
    }

    public FieldMask getFieldMask() {
        return extractFieldMask(buildProto().getMapValue());
    }

    private FieldMask extractFieldMask(MapValue mapValue) {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, Value> entry : mapValue.getFieldsMap().entrySet()) {
            FieldPath fromSingleSegment = FieldPath.fromSingleSegment(entry.getKey());
            if (Values.isMapValue(entry.getValue())) {
                Set<FieldPath> mask = extractFieldMask(entry.getValue().getMapValue()).getMask();
                if (mask.isEmpty()) {
                    hashSet.add(fromSingleSegment);
                } else {
                    Iterator<FieldPath> it = mask.iterator();
                    while (it.hasNext()) {
                        hashSet.add(fromSingleSegment.append(it.next()));
                    }
                }
            } else {
                hashSet.add(fromSingleSegment);
            }
        }
        return FieldMask.fromSet(hashSet);
    }

    public Value get(FieldPath fieldPath) {
        return extractNestedValue(buildProto(), fieldPath);
    }

    private Value extractNestedValue(Value value, FieldPath fieldPath) {
        if (fieldPath.isEmpty()) {
            return value;
        }
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            value = value.getMapValue().getFieldsOrDefault(fieldPath.getSegment(i), null);
            if (!Values.isMapValue(value)) {
                return null;
            }
        }
        return value.getMapValue().getFieldsOrDefault(fieldPath.getLastSegment(), null);
    }

    private Value buildProto() {
        synchronized (this.overlayMap) {
            MapValue applyOverlay = applyOverlay(FieldPath.EMPTY_PATH, this.overlayMap);
            if (applyOverlay != null) {
                this.partialValue = Value.newBuilder().setMapValue(applyOverlay).build();
                this.overlayMap.clear();
            }
        }
        return this.partialValue;
    }

    public void delete(FieldPath fieldPath) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot delete field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, null);
    }

    public void set(FieldPath fieldPath, Value value) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot set field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, value);
    }

    public void setAll(Map<FieldPath, Value> map) {
        for (Map.Entry<FieldPath, Value> entry : map.entrySet()) {
            FieldPath key = entry.getKey();
            if (entry.getValue() == null) {
                delete(key);
            } else {
                set(key, entry.getValue());
            }
        }
    }

    private void setOverlay(FieldPath fieldPath, Value value) {
        Map<String, Object> hashMap;
        Map<String, Object> map = this.overlayMap;
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            String segment = fieldPath.getSegment(i);
            Object obj = map.get(segment);
            if (obj instanceof Map) {
                hashMap = (Map) obj;
            } else {
                if (obj instanceof Value) {
                    Value value2 = (Value) obj;
                    if (value2.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                        HashMap hashMap2 = new HashMap(value2.getMapValue().getFieldsMap());
                        map.put(segment, hashMap2);
                        map = hashMap2;
                    }
                }
                hashMap = new HashMap<>();
                map.put(segment, hashMap);
            }
            map = hashMap;
        }
        map.put(fieldPath.getLastSegment(), value);
    }

    private MapValue applyOverlay(FieldPath fieldPath, Map<String, Object> map) {
        MapValue.Builder newBuilder;
        Value extractNestedValue = extractNestedValue(this.partialValue, fieldPath);
        if (Values.isMapValue(extractNestedValue)) {
            newBuilder = extractNestedValue.getMapValue().toBuilder();
        } else {
            newBuilder = MapValue.newBuilder();
        }
        boolean z = false;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                MapValue applyOverlay = applyOverlay(fieldPath.append(key), (Map) value);
                if (applyOverlay != null) {
                    newBuilder.putFields(key, Value.newBuilder().setMapValue(applyOverlay).build());
                    z = true;
                }
            } else {
                if (value instanceof Value) {
                    newBuilder.putFields(key, (Value) value);
                } else if (newBuilder.containsFields(key)) {
                    Assert.hardAssert(value == null, "Expected entry to be a Map, a Value or null", new Object[0]);
                    newBuilder.removeFields(key);
                }
                z = true;
            }
        }
        if (z) {
            return newBuilder.build();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ObjectValue) {
            return Values.equals(buildProto(), ((ObjectValue) obj).buildProto());
        }
        return false;
    }

    public int hashCode() {
        return buildProto().hashCode();
    }

    public String toString() {
        return "ObjectValue{internalValue=" + Values.canonicalId(buildProto()) + '}';
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ObjectValue m853clone() {
        return new ObjectValue(buildProto());
    }
}
