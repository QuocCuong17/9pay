package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class RegularContiguousSet<C extends Comparable> extends ContiguousSet<C> {
    private static final long serialVersionUID = 0;
    private final Range<C> range;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegularContiguousSet(Range<C> range, DiscreteDomain<C> domain) {
        super(domain);
        this.range = range;
    }

    private ContiguousSet<C> intersectionInCurrentDomain(Range<C> other) {
        if (this.range.isConnected(other)) {
            return ContiguousSet.create(this.range.intersection(other), this.domain);
        }
        return new EmptyContiguousSet(this.domain);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet, com.google.common.collect.ImmutableSortedSet
    public ContiguousSet<C> headSetImpl(C toElement, boolean inclusive) {
        return intersectionInCurrentDomain(Range.upTo(toElement, BoundType.forBoolean(inclusive)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet, com.google.common.collect.ImmutableSortedSet
    public ContiguousSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        if (fromElement.compareTo(toElement) == 0 && !fromInclusive && !toInclusive) {
            return new EmptyContiguousSet(this.domain);
        }
        return intersectionInCurrentDomain(Range.range(fromElement, BoundType.forBoolean(fromInclusive), toElement, BoundType.forBoolean(toInclusive)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet, com.google.common.collect.ImmutableSortedSet
    public ContiguousSet<C> tailSetImpl(C fromElement, boolean inclusive) {
        return intersectionInCurrentDomain(Range.downTo(fromElement, BoundType.forBoolean(inclusive)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableSortedSet
    public int indexOf(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return -1;
        }
        DiscreteDomain<C> discreteDomain = this.domain;
        C first = first();
        Objects.requireNonNull(obj);
        return (int) discreteDomain.distance(first, (Comparable) obj);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<C> iterator() {
        return new AbstractSequentialIterator<C>(first()) { // from class: com.google.common.collect.RegularContiguousSet.1
            final C last;

            {
                this.last = (C) RegularContiguousSet.this.last();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractSequentialIterator
            @CheckForNull
            public C computeNext(C previous) {
                if (RegularContiguousSet.equalsOrThrow(previous, this.last)) {
                    return null;
                }
                return RegularContiguousSet.this.domain.next(previous);
            }
        };
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public UnmodifiableIterator<C> descendingIterator() {
        return new AbstractSequentialIterator<C>(last()) { // from class: com.google.common.collect.RegularContiguousSet.2
            final C first;

            {
                this.first = (C) RegularContiguousSet.this.first();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractSequentialIterator
            @CheckForNull
            public C computeNext(C previous) {
                if (RegularContiguousSet.equalsOrThrow(previous, this.first)) {
                    return null;
                }
                return RegularContiguousSet.this.domain.previous(previous);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equalsOrThrow(Comparable<?> left, @CheckForNull Comparable<?> right) {
        return right != null && Range.compareOrThrow(left, right) == 0;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public C first() {
        C leastValueAbove = this.range.lowerBound.leastValueAbove(this.domain);
        Objects.requireNonNull(leastValueAbove);
        return leastValueAbove;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public C last() {
        C greatestValueBelow = this.range.upperBound.greatestValueBelow(this.domain);
        Objects.requireNonNull(greatestValueBelow);
        return greatestValueBelow;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<C> createAsList() {
        if (this.domain.supportsFastOffset) {
            return new ImmutableAsList<C>() { // from class: com.google.common.collect.RegularContiguousSet.3
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableAsList
                public ImmutableSortedSet<C> delegateCollection() {
                    return RegularContiguousSet.this;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.List
                public C get(int i) {
                    Preconditions.checkElementIndex(i, size());
                    return (C) RegularContiguousSet.this.domain.offset(RegularContiguousSet.this.first(), i);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableAsList, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
                public Object writeReplace() {
                    return super.writeReplace();
                }
            };
        }
        return super.createAsList();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        long distance = this.domain.distance(first(), last());
        if (distance >= 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return ((int) distance) + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@CheckForNull Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return this.range.contains((Comparable) obj);
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> targets) {
        return Collections2.containsAllImpl(this, targets);
    }

    @Override // com.google.common.collect.ContiguousSet
    public ContiguousSet<C> intersection(ContiguousSet<C> other) {
        Preconditions.checkNotNull(other);
        Preconditions.checkArgument(this.domain.equals(other.domain));
        if (other.isEmpty()) {
            return other;
        }
        Comparable comparable = (Comparable) Ordering.natural().max(first(), (Comparable) other.first());
        Comparable comparable2 = (Comparable) Ordering.natural().min(last(), (Comparable) other.last());
        if (comparable.compareTo(comparable2) <= 0) {
            return ContiguousSet.create(Range.closed(comparable, comparable2), this.domain);
        }
        return new EmptyContiguousSet(this.domain);
    }

    @Override // com.google.common.collect.ContiguousSet
    public Range<C> range() {
        return range(BoundType.CLOSED, BoundType.CLOSED);
    }

    @Override // com.google.common.collect.ContiguousSet
    public Range<C> range(BoundType lowerBoundType, BoundType upperBoundType) {
        return Range.create(this.range.lowerBound.withLowerBoundType(lowerBoundType, this.domain), this.range.upperBound.withUpperBoundType(upperBoundType, this.domain));
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public boolean equals(@CheckForNull Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof RegularContiguousSet) {
            RegularContiguousSet regularContiguousSet = (RegularContiguousSet) object;
            if (this.domain.equals(regularContiguousSet.domain)) {
                return first().equals(regularContiguousSet.first()) && last().equals(regularContiguousSet.last());
            }
        }
        return super.equals(object);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    /* loaded from: classes3.dex */
    private static final class SerializedForm<C extends Comparable> implements Serializable {
        final DiscreteDomain<C> domain;
        final Range<C> range;

        private SerializedForm(Range<C> range, DiscreteDomain<C> domain) {
            this.range = range;
            this.domain = domain;
        }

        private Object readResolve() {
            return new RegularContiguousSet(this.range, this.domain);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ContiguousSet, com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this.range, this.domain);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }
}
