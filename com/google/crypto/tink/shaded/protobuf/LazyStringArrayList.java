package com.google.crypto.tink.shaded.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes4.dex */
public class LazyStringArrayList extends AbstractProtobufList<String> implements LazyStringList, RandomAccess {
    public static final LazyStringList EMPTY;
    private static final LazyStringArrayList EMPTY_LIST;
    private final List<Object> list;

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean add(Object e) {
        return super.add((LazyStringArrayList) e);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean equals(Object o) {
        return super.equals(o);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList
    public /* bridge */ /* synthetic */ boolean isModifiable() {
        return super.isModifiable();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean remove(Object o) {
        return super.remove(o);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean removeAll(Collection c) {
        return super.removeAll(c);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean retainAll(Collection c) {
        return super.retainAll(c);
    }

    static {
        LazyStringArrayList lazyStringArrayList = new LazyStringArrayList();
        EMPTY_LIST = lazyStringArrayList;
        lazyStringArrayList.makeImmutable();
        EMPTY = lazyStringArrayList;
    }

    static LazyStringArrayList emptyList() {
        return EMPTY_LIST;
    }

    public LazyStringArrayList() {
        this(10);
    }

    public LazyStringArrayList(int initialCapacity) {
        this((ArrayList<Object>) new ArrayList(initialCapacity));
    }

    public LazyStringArrayList(LazyStringList from) {
        this.list = new ArrayList(from.size());
        addAll(from);
    }

    public LazyStringArrayList(List<String> from) {
        this((ArrayList<Object>) new ArrayList(from));
    }

    private LazyStringArrayList(ArrayList<Object> list) {
        this.list = list;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.ProtobufList, com.google.crypto.tink.shaded.protobuf.Internal.BooleanList
    /* renamed from: mutableCopyWithCapacity */
    public LazyStringArrayList mutableCopyWithCapacity2(int capacity) {
        if (capacity < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(capacity);
        arrayList.addAll(this.list);
        return new LazyStringArrayList((ArrayList<Object>) arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int index) {
        Object obj = this.list.get(index);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.list.set(index, stringUtf8);
            }
            return stringUtf8;
        }
        byte[] bArr = (byte[]) obj;
        String stringUtf82 = Internal.toStringUtf8(bArr);
        if (Internal.isValidUtf8(bArr)) {
            this.list.set(index, stringUtf82);
        }
        return stringUtf82;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public String set(int index, String s) {
        ensureIsMutable();
        return asString(this.list.set(index, s));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int index, String element) {
        ensureIsMutable();
        this.list.add(index, element);
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void add(int index, ByteString element) {
        ensureIsMutable();
        this.list.add(index, element);
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void add(int index, byte[] element) {
        ensureIsMutable();
        this.list.add(index, element);
        this.modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends String> c) {
        return addAll(size(), c);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends String> c) {
        ensureIsMutable();
        if (c instanceof LazyStringList) {
            c = ((LazyStringList) c).getUnderlyingElements();
        }
        boolean addAll = this.list.addAll(index, c);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public boolean addAllByteString(Collection<? extends ByteString> values) {
        ensureIsMutable();
        boolean addAll = this.list.addAll(values);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public boolean addAllByteArray(Collection<byte[]> c) {
        ensureIsMutable();
        boolean addAll = this.list.addAll(c);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public String remove(int index) {
        ensureIsMutable();
        Object remove = this.list.remove(index);
        this.modCount++;
        return asString(remove);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        ensureIsMutable();
        this.list.clear();
        this.modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void add(ByteString element) {
        ensureIsMutable();
        this.list.add(element);
        this.modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void add(byte[] element) {
        ensureIsMutable();
        this.list.add(element);
        this.modCount++;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public Object getRaw(int index) {
        return this.list.get(index);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public ByteString getByteString(int index) {
        Object obj = this.list.get(index);
        ByteString asByteString = asByteString(obj);
        if (asByteString != obj) {
            this.list.set(index, asByteString);
        }
        return asByteString;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public byte[] getByteArray(int index) {
        Object obj = this.list.get(index);
        byte[] asByteArray = asByteArray(obj);
        if (asByteArray != obj) {
            this.list.set(index, asByteArray);
        }
        return asByteArray;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void set(int index, ByteString s) {
        setAndReturn(index, s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object setAndReturn(int index, ByteString s) {
        ensureIsMutable();
        return this.list.set(index, s);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void set(int index, byte[] s) {
        setAndReturn(index, s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object setAndReturn(int index, byte[] s) {
        ensureIsMutable();
        return this.list.set(index, s);
    }

    private static String asString(Object o) {
        if (o instanceof String) {
            return (String) o;
        }
        if (o instanceof ByteString) {
            return ((ByteString) o).toStringUtf8();
        }
        return Internal.toStringUtf8((byte[]) o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteString asByteString(Object o) {
        if (o instanceof ByteString) {
            return (ByteString) o;
        }
        if (o instanceof String) {
            return ByteString.copyFromUtf8((String) o);
        }
        return ByteString.copyFrom((byte[]) o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] asByteArray(Object o) {
        if (o instanceof byte[]) {
            return (byte[]) o;
        }
        if (o instanceof String) {
            return Internal.toByteArray((String) o);
        }
        return ((ByteString) o).toByteArray();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public List<?> getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public void mergeFrom(LazyStringList other) {
        ensureIsMutable();
        for (Object obj : other.getUnderlyingElements()) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                this.list.add(Arrays.copyOf(bArr, bArr.length));
            } else {
                this.list.add(obj);
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class ByteArrayListView extends AbstractList<byte[]> implements RandomAccess {
        private final LazyStringArrayList list;

        ByteArrayListView(LazyStringArrayList list) {
            this.list = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public byte[] get(int index) {
            return this.list.getByteArray(index);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.list.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public byte[] set(int index, byte[] s) {
            Object andReturn = this.list.setAndReturn(index, s);
            this.modCount++;
            return LazyStringArrayList.asByteArray(andReturn);
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int index, byte[] s) {
            this.list.add(index, s);
            this.modCount++;
        }

        @Override // java.util.AbstractList, java.util.List
        public byte[] remove(int index) {
            String remove = this.list.remove(index);
            this.modCount++;
            return LazyStringArrayList.asByteArray(remove);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public List<byte[]> asByteArrayList() {
        return new ByteArrayListView(this);
    }

    /* loaded from: classes4.dex */
    private static class ByteStringListView extends AbstractList<ByteString> implements RandomAccess {
        private final LazyStringArrayList list;

        ByteStringListView(LazyStringArrayList list) {
            this.list = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public ByteString get(int index) {
            return this.list.getByteString(index);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.list.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public ByteString set(int index, ByteString s) {
            Object andReturn = this.list.setAndReturn(index, s);
            this.modCount++;
            return LazyStringArrayList.asByteString(andReturn);
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int index, ByteString s) {
            this.list.add(index, s);
            this.modCount++;
        }

        @Override // java.util.AbstractList, java.util.List
        public ByteString remove(int index) {
            String remove = this.list.remove(index);
            this.modCount++;
            return LazyStringArrayList.asByteString(remove);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ProtocolStringList
    public List<ByteString> asByteStringList() {
        return new ByteStringListView(this);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return isModifiable() ? new UnmodifiableLazyStringList(this) : this;
    }
}
