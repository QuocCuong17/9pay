package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlin.UShort;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final byte ABSENT = -1;
    private static final int BYTE_MASK = 255;
    private static final int BYTE_MAX_SIZE = 128;
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_MAX_SIZE = 32768;
    private static final long serialVersionUID = 0;
    final transient Object[] alternatingKeysAndValues;

    @CheckForNull
    private final transient Object hashTable;
    private final transient int size;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> RegularImmutableMap<K, V> create(int n, Object[] alternatingKeysAndValues) {
        return create(n, alternatingKeysAndValues, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> RegularImmutableMap<K, V> create(int n, Object[] alternatingKeysAndValues, ImmutableMap.Builder<K, V> builder) {
        if (n == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (n == 1) {
            Object obj = alternatingKeysAndValues[0];
            Objects.requireNonNull(obj);
            Object obj2 = alternatingKeysAndValues[1];
            Objects.requireNonNull(obj2);
            CollectPreconditions.checkEntryNotNull(obj, obj2);
            return new RegularImmutableMap<>(null, alternatingKeysAndValues, 1);
        }
        Preconditions.checkPositionIndex(n, alternatingKeysAndValues.length >> 1);
        Object createHashTable = createHashTable(alternatingKeysAndValues, n, ImmutableSet.chooseTableSize(n), 0);
        if (createHashTable instanceof Object[]) {
            Object[] objArr = (Object[]) createHashTable;
            ImmutableMap.Builder.DuplicateKey duplicateKey = (ImmutableMap.Builder.DuplicateKey) objArr[2];
            if (builder == null) {
                throw duplicateKey.exception();
            }
            builder.duplicateKey = duplicateKey;
            Object obj3 = objArr[0];
            int intValue = ((Integer) objArr[1]).intValue();
            alternatingKeysAndValues = Arrays.copyOf(alternatingKeysAndValues, intValue * 2);
            createHashTable = obj3;
            n = intValue;
        }
        return new RegularImmutableMap<>(createHashTable, alternatingKeysAndValues, n);
    }

    @CheckForNull
    private static Object createHashTable(Object[] alternatingKeysAndValues, int n, int tableSize, int keyOffset) {
        ImmutableMap.Builder.DuplicateKey duplicateKey = null;
        if (n == 1) {
            Object obj = alternatingKeysAndValues[keyOffset];
            Objects.requireNonNull(obj);
            Object obj2 = alternatingKeysAndValues[keyOffset ^ 1];
            Objects.requireNonNull(obj2);
            CollectPreconditions.checkEntryNotNull(obj, obj2);
            return null;
        }
        int i = tableSize - 1;
        int i2 = -1;
        if (tableSize <= 128) {
            byte[] bArr = new byte[tableSize];
            Arrays.fill(bArr, (byte) -1);
            int i3 = 0;
            for (int i4 = 0; i4 < n; i4++) {
                int i5 = (i4 * 2) + keyOffset;
                int i6 = (i3 * 2) + keyOffset;
                Object obj3 = alternatingKeysAndValues[i5];
                Objects.requireNonNull(obj3);
                Object obj4 = alternatingKeysAndValues[i5 ^ 1];
                Objects.requireNonNull(obj4);
                CollectPreconditions.checkEntryNotNull(obj3, obj4);
                int smear = Hashing.smear(obj3.hashCode());
                while (true) {
                    int i7 = smear & i;
                    int i8 = bArr[i7] & 255;
                    if (i8 == 255) {
                        bArr[i7] = (byte) i6;
                        if (i3 < i4) {
                            alternatingKeysAndValues[i6] = obj3;
                            alternatingKeysAndValues[i6 ^ 1] = obj4;
                        }
                        i3++;
                    } else {
                        if (obj3.equals(alternatingKeysAndValues[i8])) {
                            int i9 = i8 ^ 1;
                            Object obj5 = alternatingKeysAndValues[i9];
                            Objects.requireNonNull(obj5);
                            duplicateKey = new ImmutableMap.Builder.DuplicateKey(obj3, obj4, obj5);
                            alternatingKeysAndValues[i9] = obj4;
                            break;
                        }
                        smear = i7 + 1;
                    }
                }
            }
            return i3 == n ? bArr : new Object[]{bArr, Integer.valueOf(i3), duplicateKey};
        }
        if (tableSize <= 32768) {
            short[] sArr = new short[tableSize];
            Arrays.fill(sArr, (short) -1);
            int i10 = 0;
            for (int i11 = 0; i11 < n; i11++) {
                int i12 = (i11 * 2) + keyOffset;
                int i13 = (i10 * 2) + keyOffset;
                Object obj6 = alternatingKeysAndValues[i12];
                Objects.requireNonNull(obj6);
                Object obj7 = alternatingKeysAndValues[i12 ^ 1];
                Objects.requireNonNull(obj7);
                CollectPreconditions.checkEntryNotNull(obj6, obj7);
                int smear2 = Hashing.smear(obj6.hashCode());
                while (true) {
                    int i14 = smear2 & i;
                    int i15 = sArr[i14] & UShort.MAX_VALUE;
                    if (i15 == 65535) {
                        sArr[i14] = (short) i13;
                        if (i10 < i11) {
                            alternatingKeysAndValues[i13] = obj6;
                            alternatingKeysAndValues[i13 ^ 1] = obj7;
                        }
                        i10++;
                    } else {
                        if (obj6.equals(alternatingKeysAndValues[i15])) {
                            int i16 = i15 ^ 1;
                            Object obj8 = alternatingKeysAndValues[i16];
                            Objects.requireNonNull(obj8);
                            duplicateKey = new ImmutableMap.Builder.DuplicateKey(obj6, obj7, obj8);
                            alternatingKeysAndValues[i16] = obj7;
                            break;
                        }
                        smear2 = i14 + 1;
                    }
                }
            }
            return i10 == n ? sArr : new Object[]{sArr, Integer.valueOf(i10), duplicateKey};
        }
        int[] iArr = new int[tableSize];
        Arrays.fill(iArr, -1);
        int i17 = 0;
        int i18 = 0;
        while (i17 < n) {
            int i19 = (i17 * 2) + keyOffset;
            int i20 = (i18 * 2) + keyOffset;
            Object obj9 = alternatingKeysAndValues[i19];
            Objects.requireNonNull(obj9);
            Object obj10 = alternatingKeysAndValues[i19 ^ 1];
            Objects.requireNonNull(obj10);
            CollectPreconditions.checkEntryNotNull(obj9, obj10);
            int smear3 = Hashing.smear(obj9.hashCode());
            while (true) {
                int i21 = smear3 & i;
                int i22 = iArr[i21];
                if (i22 == i2) {
                    iArr[i21] = i20;
                    if (i18 < i17) {
                        alternatingKeysAndValues[i20] = obj9;
                        alternatingKeysAndValues[i20 ^ 1] = obj10;
                    }
                    i18++;
                } else {
                    if (obj9.equals(alternatingKeysAndValues[i22])) {
                        int i23 = i22 ^ 1;
                        Object obj11 = alternatingKeysAndValues[i23];
                        Objects.requireNonNull(obj11);
                        duplicateKey = new ImmutableMap.Builder.DuplicateKey(obj9, obj10, obj11);
                        alternatingKeysAndValues[i23] = obj10;
                        break;
                    }
                    smear3 = i21 + 1;
                    i2 = -1;
                }
            }
            i17++;
            i2 = -1;
        }
        return i18 == n ? iArr : new Object[]{iArr, Integer.valueOf(i18), duplicateKey};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public static Object createHashTableOrThrow(Object[] alternatingKeysAndValues, int n, int tableSize, int keyOffset) {
        Object createHashTable = createHashTable(alternatingKeysAndValues, n, tableSize, keyOffset);
        if (createHashTable instanceof Object[]) {
            throw ((ImmutableMap.Builder.DuplicateKey) ((Object[]) createHashTable)[2]).exception();
        }
        return createHashTable;
    }

    private RegularImmutableMap(@CheckForNull Object hashTable, Object[] alternatingKeysAndValues, int size) {
        this.hashTable = hashTable;
        this.alternatingKeysAndValues = alternatingKeysAndValues;
        this.size = size;
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    @CheckForNull
    public V get(@CheckForNull Object obj) {
        V v = (V) get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, obj);
        if (v == null) {
            return null;
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public static Object get(@CheckForNull Object hashTableObject, Object[] alternatingKeysAndValues, int size, int keyOffset, @CheckForNull Object key) {
        if (key == null) {
            return null;
        }
        if (size == 1) {
            Object obj = alternatingKeysAndValues[keyOffset];
            Objects.requireNonNull(obj);
            if (!obj.equals(key)) {
                return null;
            }
            Object obj2 = alternatingKeysAndValues[keyOffset ^ 1];
            Objects.requireNonNull(obj2);
            return obj2;
        }
        if (hashTableObject == null) {
            return null;
        }
        if (hashTableObject instanceof byte[]) {
            byte[] bArr = (byte[]) hashTableObject;
            int length = bArr.length - 1;
            int smear = Hashing.smear(key.hashCode());
            while (true) {
                int i = smear & length;
                int i2 = bArr[i] & 255;
                if (i2 == 255) {
                    return null;
                }
                if (key.equals(alternatingKeysAndValues[i2])) {
                    return alternatingKeysAndValues[i2 ^ 1];
                }
                smear = i + 1;
            }
        } else if (hashTableObject instanceof short[]) {
            short[] sArr = (short[]) hashTableObject;
            int length2 = sArr.length - 1;
            int smear2 = Hashing.smear(key.hashCode());
            while (true) {
                int i3 = smear2 & length2;
                int i4 = sArr[i3] & UShort.MAX_VALUE;
                if (i4 == 65535) {
                    return null;
                }
                if (key.equals(alternatingKeysAndValues[i4])) {
                    return alternatingKeysAndValues[i4 ^ 1];
                }
                smear2 = i3 + 1;
            }
        } else {
            int[] iArr = (int[]) hashTableObject;
            int length3 = iArr.length - 1;
            int smear3 = Hashing.smear(key.hashCode());
            while (true) {
                int i5 = smear3 & length3;
                int i6 = iArr[i5];
                if (i6 == -1) {
                    return null;
                }
                if (key.equals(alternatingKeysAndValues[i6])) {
                    return alternatingKeysAndValues[i6 ^ 1];
                }
                smear3 = i5 + 1;
            }
        }
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int keyOffset;
        private final transient ImmutableMap<K, V> map;
        private final transient int size;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public EntrySet(ImmutableMap<K, V> map, Object[] alternatingKeysAndValues, int keyOffset, int size) {
            this.map = map;
            this.alternatingKeysAndValues = alternatingKeysAndValues;
            this.keyOffset = keyOffset;
            this.size = size;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] dst, int offset) {
            return asList().copyIntoArray(dst, offset);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.RegularImmutableMap.EntrySet.1
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.List
                public Map.Entry<K, V> get(int index) {
                    Preconditions.checkElementIndex(index, EntrySet.this.size);
                    int i = index * 2;
                    Object obj = EntrySet.this.alternatingKeysAndValues[EntrySet.this.keyOffset + i];
                    Objects.requireNonNull(obj);
                    Object obj2 = EntrySet.this.alternatingKeysAndValues[i + (EntrySet.this.keyOffset ^ 1)];
                    Objects.requireNonNull(obj2);
                    return new AbstractMap.SimpleImmutableEntry(obj, obj2);
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return EntrySet.this.size;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
                public Object writeReplace() {
                    return super.writeReplace();
                }
            };
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object object) {
            if (!(object instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) object;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.map.get(key));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    /* loaded from: classes3.dex */
    static final class KeysOrValuesAsList extends ImmutableList<Object> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public KeysOrValuesAsList(Object[] alternatingKeysAndValues, int offset, int size) {
            this.alternatingKeysAndValues = alternatingKeysAndValues;
            this.offset = offset;
            this.size = size;
        }

        @Override // java.util.List
        public Object get(int index) {
            Preconditions.checkElementIndex(index, this.size);
            Object obj = this.alternatingKeysAndValues[(index * 2) + this.offset];
            Objects.requireNonNull(obj);
            return obj;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    /* loaded from: classes3.dex */
    static final class KeySet<K> extends ImmutableSet<K> {
        private final transient ImmutableList<K> list;
        private final transient ImmutableMap<K, ?> map;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public KeySet(ImmutableMap<K, ?> map, ImmutableList<K> list) {
            this.map = map;
            this.list = list;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] dst, int offset) {
            return asList().copyIntoArray(dst, offset);
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public ImmutableList<K> asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object object) {
            return this.map.get(object) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection<V> createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return super.writeReplace();
    }
}
