package com.google.firebase.firestore.core;

import android.util.Pair;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.Value;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes4.dex */
public final class Target {
    public static final long NO_LIMIT = -1;
    private final String collectionGroup;
    private final Bound endAt;
    private final List<Filter> filters;
    private final long limit;
    private String memoizedCanonicalId;
    private final List<OrderBy> orderBys;
    private final ResourcePath path;
    private final Bound startAt;

    public Target(ResourcePath resourcePath, String str, List<Filter> list, List<OrderBy> list2, long j, Bound bound, Bound bound2) {
        this.path = resourcePath;
        this.collectionGroup = str;
        this.orderBys = list2;
        this.filters = list;
        this.limit = j;
        this.startAt = bound;
        this.endAt = bound2;
    }

    public ResourcePath getPath() {
        return this.path;
    }

    public String getCollectionGroup() {
        return this.collectionGroup;
    }

    public boolean isDocumentQuery() {
        return DocumentKey.isDocumentKey(this.path) && this.collectionGroup == null && this.filters.isEmpty();
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public long getLimit() {
        return this.limit;
    }

    public boolean hasLimit() {
        return this.limit != -1;
    }

    public Bound getStartAt() {
        return this.startAt;
    }

    public Bound getEndAt() {
        return this.endAt;
    }

    private List<FieldFilter> getFieldFiltersForPath(FieldPath fieldPath) {
        ArrayList arrayList = new ArrayList();
        for (Filter filter : this.filters) {
            if (filter instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) filter;
                if (fieldFilter.getField().equals(fieldPath)) {
                    arrayList.add(fieldFilter);
                }
            }
        }
        return arrayList;
    }

    public List<Value> getArrayValues(FieldIndex fieldIndex) {
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment == null) {
            return null;
        }
        for (FieldFilter fieldFilter : getFieldFiltersForPath(arraySegment.getFieldPath())) {
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[fieldFilter.getOperator().ordinal()];
            if (i == 1) {
                return fieldFilter.getValue().getArrayValue().getValuesList();
            }
            if (i == 2) {
                return Collections.singletonList(fieldFilter.getValue());
            }
        }
        return null;
    }

    public Collection<Value> getNotInValues(FieldIndex fieldIndex) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            for (FieldFilter fieldFilter : getFieldFiltersForPath(segment.getFieldPath())) {
                int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$FieldFilter$Operator[fieldFilter.getOperator().ordinal()];
                if (i == 3 || i == 4) {
                    linkedHashMap.put(segment.getFieldPath(), fieldFilter.getValue());
                } else if (i == 5 || i == 6) {
                    linkedHashMap.put(segment.getFieldPath(), fieldFilter.getValue());
                    return linkedHashMap.values();
                }
            }
        }
        return null;
    }

    public Bound getLowerBound(FieldIndex fieldIndex) {
        Pair<Value, Boolean> descendingBound;
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            if (segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING)) {
                descendingBound = getAscendingBound(segment, this.startAt);
            } else {
                descendingBound = getDescendingBound(segment, this.startAt);
            }
            arrayList.add((Value) descendingBound.first);
            z &= ((Boolean) descendingBound.second).booleanValue();
        }
        return new Bound(arrayList, z);
    }

    public Bound getUpperBound(FieldIndex fieldIndex) {
        Pair<Value, Boolean> ascendingBound;
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            if (segment.getKind().equals(FieldIndex.Segment.Kind.ASCENDING)) {
                ascendingBound = getDescendingBound(segment, this.endAt);
            } else {
                ascendingBound = getAscendingBound(segment, this.endAt);
            }
            arrayList.add((Value) ascendingBound.first);
            z &= ((Boolean) ascendingBound.second).booleanValue();
        }
        return new Bound(arrayList, z);
    }

    private Pair<Value, Boolean> getAscendingBound(FieldIndex.Segment segment, Bound bound) {
        Value value = Values.MIN_VALUE;
        Iterator<FieldFilter> it = getFieldFiltersForPath(segment.getFieldPath()).iterator();
        boolean z = true;
        while (true) {
            int i = 0;
            boolean z2 = false;
            if (it.hasNext()) {
                FieldFilter next = it.next();
                Value value2 = Values.MIN_VALUE;
                switch (next.getOperator()) {
                    case EQUAL:
                    case IN:
                    case GREATER_THAN_OR_EQUAL:
                        value2 = next.getValue();
                        break;
                    case NOT_IN:
                    case NOT_EQUAL:
                        value2 = Values.MIN_VALUE;
                        break;
                    case LESS_THAN:
                    case LESS_THAN_OR_EQUAL:
                        value2 = Values.getLowerBound(next.getValue().getValueTypeCase());
                        break;
                    case GREATER_THAN:
                        value2 = next.getValue();
                        break;
                }
                z2 = true;
                if (Values.lowerBoundCompare(value, z, value2, z2) < 0) {
                    z = z2;
                    value = value2;
                }
            } else {
                if (bound != null) {
                    while (true) {
                        if (i < this.orderBys.size()) {
                            if (this.orderBys.get(i).getField().equals(segment.getFieldPath())) {
                                Value value3 = bound.getPosition().get(i);
                                if (Values.lowerBoundCompare(value, z, value3, bound.isInclusive()) < 0) {
                                    z = bound.isInclusive();
                                    value = value3;
                                }
                            } else {
                                i++;
                            }
                        }
                    }
                }
                return new Pair<>(value, Boolean.valueOf(z));
            }
        }
    }

    private Pair<Value, Boolean> getDescendingBound(FieldIndex.Segment segment, Bound bound) {
        Value value = Values.MAX_VALUE;
        Iterator<FieldFilter> it = getFieldFiltersForPath(segment.getFieldPath()).iterator();
        boolean z = true;
        while (true) {
            int i = 0;
            r5 = false;
            boolean z2 = false;
            if (it.hasNext()) {
                FieldFilter next = it.next();
                Value value2 = Values.MAX_VALUE;
                switch (next.getOperator()) {
                    case EQUAL:
                    case IN:
                    case LESS_THAN_OR_EQUAL:
                        value2 = next.getValue();
                        break;
                    case NOT_IN:
                    case NOT_EQUAL:
                        value2 = Values.MAX_VALUE;
                        break;
                    case LESS_THAN:
                        value2 = next.getValue();
                        break;
                    case GREATER_THAN_OR_EQUAL:
                    case GREATER_THAN:
                        value2 = Values.getUpperBound(next.getValue().getValueTypeCase());
                        break;
                }
                z2 = true;
                if (Values.upperBoundCompare(value, z, value2, z2) > 0) {
                    z = z2;
                    value = value2;
                }
            } else {
                if (bound != null) {
                    while (true) {
                        if (i < this.orderBys.size()) {
                            if (this.orderBys.get(i).getField().equals(segment.getFieldPath())) {
                                Value value3 = bound.getPosition().get(i);
                                if (Values.upperBoundCompare(value, z, value3, bound.isInclusive()) > 0) {
                                    z = bound.isInclusive();
                                    value = value3;
                                }
                            } else {
                                i++;
                            }
                        }
                    }
                }
                return new Pair<>(value, Boolean.valueOf(z));
            }
        }
    }

    public List<OrderBy> getOrderBy() {
        return this.orderBys;
    }

    public OrderBy.Direction getKeyOrder() {
        return this.orderBys.get(r0.size() - 1).getDirection();
    }

    public int getSegmentCount() {
        HashSet hashSet = new HashSet();
        Iterator<Filter> it = this.filters.iterator();
        int i = 0;
        while (it.hasNext()) {
            for (FieldFilter fieldFilter : it.next().getFlattenedFilters()) {
                if (!fieldFilter.getField().isKeyField()) {
                    if (fieldFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS) || fieldFilter.getOperator().equals(FieldFilter.Operator.ARRAY_CONTAINS_ANY)) {
                        i = 1;
                    } else {
                        hashSet.add(fieldFilter.getField());
                    }
                }
            }
        }
        for (OrderBy orderBy : this.orderBys) {
            if (!orderBy.getField().isKeyField()) {
                hashSet.add(orderBy.getField());
            }
        }
        return hashSet.size() + i;
    }

    public String getCanonicalId() {
        String str = this.memoizedCanonicalId;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getPath().canonicalString());
        if (this.collectionGroup != null) {
            sb.append("|cg:");
            sb.append(this.collectionGroup);
        }
        sb.append("|f:");
        Iterator<Filter> it = getFilters().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getCanonicalId());
        }
        sb.append("|ob:");
        for (OrderBy orderBy : getOrderBy()) {
            sb.append(orderBy.getField().canonicalString());
            sb.append(orderBy.getDirection().equals(OrderBy.Direction.ASCENDING) ? "asc" : "desc");
        }
        if (hasLimit()) {
            sb.append("|l:");
            sb.append(getLimit());
        }
        if (this.startAt != null) {
            sb.append("|lb:");
            sb.append(this.startAt.isInclusive() ? "b:" : "a:");
            sb.append(this.startAt.positionString());
        }
        if (this.endAt != null) {
            sb.append("|ub:");
            sb.append(this.endAt.isInclusive() ? "a:" : "b:");
            sb.append(this.endAt.positionString());
        }
        String sb2 = sb.toString();
        this.memoizedCanonicalId = sb2;
        return sb2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Target target = (Target) obj;
        String str = this.collectionGroup;
        if (str == null ? target.collectionGroup != null : !str.equals(target.collectionGroup)) {
            return false;
        }
        if (this.limit != target.limit || !this.orderBys.equals(target.orderBys) || !this.filters.equals(target.filters) || !this.path.equals(target.path)) {
            return false;
        }
        Bound bound = this.startAt;
        if (bound == null ? target.startAt != null : !bound.equals(target.startAt)) {
            return false;
        }
        Bound bound2 = this.endAt;
        Bound bound3 = target.endAt;
        return bound2 != null ? bound2.equals(bound3) : bound3 == null;
    }

    public int hashCode() {
        int hashCode = this.orderBys.hashCode() * 31;
        String str = this.collectionGroup;
        int hashCode2 = (((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.filters.hashCode()) * 31) + this.path.hashCode()) * 31;
        long j = this.limit;
        int i = (hashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        Bound bound = this.startAt;
        int hashCode3 = (i + (bound != null ? bound.hashCode() : 0)) * 31;
        Bound bound2 = this.endAt;
        return hashCode3 + (bound2 != null ? bound2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Query(");
        sb.append(this.path.canonicalString());
        if (this.collectionGroup != null) {
            sb.append(" collectionGroup=");
            sb.append(this.collectionGroup);
        }
        if (!this.filters.isEmpty()) {
            sb.append(" where ");
            for (int i = 0; i < this.filters.size(); i++) {
                if (i > 0) {
                    sb.append(" and ");
                }
                sb.append(this.filters.get(i));
            }
        }
        if (!this.orderBys.isEmpty()) {
            sb.append(" order by ");
            for (int i2 = 0; i2 < this.orderBys.size(); i2++) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(this.orderBys.get(i2));
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
