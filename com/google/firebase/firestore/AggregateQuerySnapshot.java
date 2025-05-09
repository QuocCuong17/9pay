package com.google.firebase.firestore;

import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;

/* loaded from: classes4.dex */
public class AggregateQuerySnapshot {

    @Nonnull
    private final Map<String, Value> data;

    @Nonnull
    private final AggregateQuery query;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AggregateQuerySnapshot(AggregateQuery aggregateQuery, Map<String, Value> map) {
        Preconditions.checkNotNull(aggregateQuery);
        this.query = aggregateQuery;
        this.data = map;
    }

    static AggregateQuerySnapshot createWithCount(AggregateQuery aggregateQuery, long j) {
        return new AggregateQuerySnapshot(aggregateQuery, Collections.singletonMap(AggregateField.count().getAlias(), Value.newBuilder().setIntegerValue(j).build()));
    }

    public AggregateQuery getQuery() {
        return this.query;
    }

    public long getCount() {
        return get(AggregateField.count());
    }

    public Object get(@Nonnull AggregateField aggregateField) {
        return getInternal(aggregateField);
    }

    public long get(@Nonnull AggregateField.CountAggregateField countAggregateField) {
        Long l = getLong(countAggregateField);
        if (l == null) {
            throw new IllegalArgumentException("RunAggregationQueryResponse alias " + countAggregateField.getAlias() + " is null");
        }
        return l.longValue();
    }

    public Double get(@Nonnull AggregateField.AverageAggregateField averageAggregateField) {
        return getDouble(averageAggregateField);
    }

    public Double getDouble(@Nonnull AggregateField aggregateField) {
        Number number = (Number) getTypedValue(aggregateField, Number.class);
        if (number != null) {
            return Double.valueOf(number.doubleValue());
        }
        return null;
    }

    public Long getLong(@Nonnull AggregateField aggregateField) {
        Number number = (Number) getTypedValue(aggregateField, Number.class);
        if (number != null) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    private Object getInternal(@Nonnull AggregateField aggregateField) {
        if (!this.data.containsKey(aggregateField.getAlias())) {
            throw new IllegalArgumentException("'" + aggregateField.getOperator() + "(" + aggregateField.getFieldPath() + ")' was not requested in the aggregation query.");
        }
        return new UserDataWriter(this.query.getQuery().firestore, DocumentSnapshot.ServerTimestampBehavior.DEFAULT).convertValue(this.data.get(aggregateField.getAlias()));
    }

    private <T> T getTypedValue(@Nonnull AggregateField aggregateField, Class<T> cls) {
        return (T) castTypedValue(getInternal(aggregateField), aggregateField, cls);
    }

    private <T> T castTypedValue(Object obj, @Nonnull AggregateField aggregateField, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (!cls.isInstance(obj)) {
            throw new RuntimeException("AggregateField '" + aggregateField.getAlias() + "' is not a " + cls.getName());
        }
        return cls.cast(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateQuerySnapshot)) {
            return false;
        }
        AggregateQuerySnapshot aggregateQuerySnapshot = (AggregateQuerySnapshot) obj;
        return this.query.equals(aggregateQuerySnapshot.query) && this.data.equals(aggregateQuerySnapshot.data);
    }

    public int hashCode() {
        return Objects.hash(this.query, this.data);
    }
}
