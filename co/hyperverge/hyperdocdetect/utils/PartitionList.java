package co.hyperverge.hyperdocdetect.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class PartitionList<T> extends AbstractList<List<T>> {
    private final int chunkSize;
    private final List<T> list;

    protected boolean canEqual(Object obj) {
        return obj instanceof PartitionList;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PartitionList)) {
            return false;
        }
        PartitionList partitionList = (PartitionList) obj;
        if (!partitionList.canEqual(this) || !super.equals(obj) || this.chunkSize != partitionList.chunkSize) {
            return false;
        }
        List<T> list = this.list;
        List<T> list2 = partitionList.list;
        return list != null ? list.equals(list2) : list2 == null;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int hashCode = (super.hashCode() * 59) + this.chunkSize;
        List<T> list = this.list;
        return (hashCode * 59) + (list == null ? 43 : list.hashCode());
    }

    public PartitionList(List<T> list, int i) {
        this.list = new ArrayList(list);
        this.chunkSize = i;
    }

    public static <T> PartitionList<T> ofSize(List<T> list, int i) {
        return new PartitionList<>(list, i);
    }

    @Override // java.util.AbstractList, java.util.List
    public List<T> get(int i) {
        int i2 = this.chunkSize;
        int i3 = i * i2;
        int min = Math.min(i2 + i3, this.list.size());
        if (i3 > min) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i);
            sb.append(" is out of the list range <0,");
            sb.append(size() - 1);
            sb.append(">");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return new ArrayList(this.list.subList(i3, min));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return (int) Math.ceil(this.list.size() / this.chunkSize);
    }
}
